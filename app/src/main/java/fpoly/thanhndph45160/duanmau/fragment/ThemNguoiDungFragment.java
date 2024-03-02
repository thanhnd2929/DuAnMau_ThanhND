package fpoly.thanhndph45160.duanmau.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.thanhndph45160.duanmau.DAO.ThuThuDAO;
import fpoly.thanhndph45160.duanmau.Model.ThuThu;
import fpoly.thanhndph45160.duanmau.R;


public class ThemNguoiDungFragment extends Fragment {


    View v;
    ThuThuDAO thuThuDAO;
    EditText edt_username, edt_hoTen, edt_password;
    Button btnSave, btnCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_them_nguoi_dung, container, false);

        thuThuDAO = new ThuThuDAO(getActivity());
        edt_username = v.findViewById(R.id.edt_username_add);
        edt_hoTen = v.findViewById(R.id.edt_hoTen_add);
        edt_password = v.findViewById(R.id.edtPassword_Add);
        btnSave = v.findViewById(R.id.btnSave_addTT);
        btnCancel = v.findViewById(R.id.btnCancle_addTT);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_username.getText().toString().trim();
                String hoTen = edt_hoTen.getText().toString().trim();
                String password = edt_password.getText().toString().trim();

                if (username.isEmpty() || hoTen.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng cung cấp đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    ThuThu thuThu = new ThuThu(username, hoTen, password);
                    if (thuThuDAO.insert(thuThu) > 0) {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetEditText();
            }
        });


        return v;
    }

    public void resetEditText() {
        edt_username.setText("");
        edt_hoTen.setText("");
        edt_password.setText("");
    }
}