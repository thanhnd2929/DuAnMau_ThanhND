package fpoly.thanhndph45160.duanmau.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.thanhndph45160.duanmau.Adapter.LoaiSachAdapter;
import fpoly.thanhndph45160.duanmau.DAO.LoaiSachDAO;
import fpoly.thanhndph45160.duanmau.Model.LoaiSach;
import fpoly.thanhndph45160.duanmau.R;


public class LoaiSachFragment extends Fragment {

    ListView lvLoaiSach;
    ArrayList<LoaiSach> lists;
    LoaiSachDAO loaiSachDAO;
    LoaiSachAdapter loaiSachAdapter;
    LoaiSach item;
    FloatingActionButton fab;

    Dialog dialog;
    EditText edMaLoai, edTenLoai;
    Button btnSaveLoai, btnCancelLoai;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        lvLoaiSach = v.findViewById(R.id.lvLoaiSach);
        loaiSachDAO = new LoaiSachDAO(getActivity());
        capNhatLv();
        fab = v.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getContext(), 0);
            }
        });

        lvLoaiSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = lists.get(position);
                openDialog(getContext(), 1);
                return false;
            }
        });
        return v;
    }

    void capNhatLv() {
        lists = (ArrayList<LoaiSach>) loaiSachDAO.getAll();
        loaiSachAdapter = new LoaiSachAdapter(getContext(), this, lists);
        lvLoaiSach.setAdapter(loaiSachAdapter);
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xoá");
        builder.setMessage("Bạn có muốn xoá không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loaiSachDAO.delete(Id);
                // cap nhat lai view
                capNhatLv();
                dialog.cancel();
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.loai_sach_dialog);

        edMaLoai = dialog.findViewById(R.id.edMaLoaiSach);
        edTenLoai = dialog.findViewById(R.id.edTenLoaiSach);
        btnSaveLoai = dialog.findViewById(R.id.btnSaveLoai);
        btnCancelLoai = dialog.findViewById(R.id.btnCancelLoai);

        // check type insert 0 hay update 1
        edMaLoai.setEnabled(false);
        if (type!=0) {
            edMaLoai.setText(String.valueOf(item.getMaLoai()));
            edTenLoai.setText(item.getTenLoai());
        }

        btnCancelLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSaveLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new LoaiSach();
                item.setTenLoai(edTenLoai.getText().toString());
                if (validate() > 0) {
                    if (type == 0) { // insert
                        if (loaiSachDAO.insert(item) > 0) {
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaLoai(Integer.parseInt(edMaLoai.getText().toString()));
                        if (loaiSachDAO.update(item) > 0) {
                            Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if (edTenLoai.getText().length()==0) {
            Toast.makeText(getContext(), "Bạn cần nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }



}