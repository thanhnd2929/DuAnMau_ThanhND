package fpoly.thanhndph45160.duanmau.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import fpoly.thanhndph45160.duanmau.DAO.ThuThuDAO;
import fpoly.thanhndph45160.duanmau.Model.ThuThu;
import fpoly.thanhndph45160.duanmau.R;


public class ChangePassFragment extends Fragment {

    TextInputEditText edPassOld, edPass, edRePass;
    Button btnSave, btnCancel;
    ThuThuDAO dao;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_change_pass, container, false);
        edPass = v.findViewById(R.id.edPassChange);
        edPassOld = v.findViewById(R.id.edPassOld);
        edRePass = v.findViewById(R.id.edtRePassChange);
        btnSave = v.findViewById(R.id.btnSaveUserChange);
        btnCancel = v.findViewById(R.id.btnCancelUserChange);

        dao = new ThuThuDAO(getContext());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPass.setText("");
                edRePass.setText("");
                edPassOld.setText("");
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = sp.getString("USERNAME", "");
                if (validate() > 0) {
                    ThuThu thuThu = dao.getID(user);
                    String newPassword = edPass.getText().toString();
                    thuThu.setMatKhau(edPass.getText().toString());
                    if (dao.updatePass(thuThu) > 0) {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Log.d("NewPassword", "New Password: " + newPassword);
                        edPassOld.setText("");
                        edPass.setText("");
                        edRePass.setText("");
                    } else {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return v;
    }

    public int validate() {
        int check = 1;
        if (edPassOld.getText().length()==0 || edPass.getText().length()==0 || edRePass.getText().length()==0) {
            Toast.makeText(getContext(), "Bạn cần nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            SharedPreferences sp = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = sp.getString("PASSWORD", "");
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if (!passOld.equals(edPassOld.getText().toString())) {
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(rePass)) {
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}