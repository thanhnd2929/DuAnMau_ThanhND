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
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.thanhndph45160.duanmau.Adapter.ThanhVienAdapter;
import fpoly.thanhndph45160.duanmau.DAO.ThanhVienDAO;
import fpoly.thanhndph45160.duanmau.Model.ThanhVien;
import fpoly.thanhndph45160.duanmau.R;


public class ThanhVienFragment extends Fragment {


    ListView lvThanhVien;
    ArrayList<ThanhVien> list;
    ThanhVienDAO thanhVienDAO;
    ThanhVienAdapter thanhVienAdapter;
    ThanhVien item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaTV, edTenTV, edNamSinh;
    Button btnSaveTV, btnCancelTV;
    SearchView searchView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        lvThanhVien = v.findViewById(R.id.lvThanhVien);
        thanhVienDAO = new ThanhVienDAO(getActivity());
        capNhatLv();
        fab = v.findViewById(R.id.fab);
        searchView = v.findViewById(R.id.searchView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });

        lvThanhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getContext(), 1);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<ThanhVien> newList = new ArrayList<>();
                if (!newText.isEmpty()) {
                    for (ThanhVien thanhVien : list) {
                        String mtv = String.valueOf(thanhVien.getMaTV());
                        if (mtv.toLowerCase().contains(newText.toLowerCase())) {
                            newList.add(thanhVien);
                        }
                    }
                    thanhVienAdapter = new ThanhVienAdapter(getActivity(), ThanhVienFragment.this, newList);
                    lvThanhVien.setAdapter(thanhVienAdapter);
                } else {
                    capNhatLv();
                }

                return false;
            }

        });
        return v;
    }
    void capNhatLv() {
        list = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienAdapter = new ThanhVienAdapter(getActivity(), this, list);
        lvThanhVien.setAdapter(thanhVienAdapter);
    }
    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xoá");
        builder.setMessage("Bạn có muốn xoá không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                thanhVienDAO.delete(Id);
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
        dialog.setContentView(R.layout.thanh_vien_dialog);
        edMaTV = dialog.findViewById(R.id.edMaTV);
        edTenTV = dialog.findViewById(R.id.edTenTV);
        edNamSinh = dialog.findViewById(R.id.edNamSinh);
        btnSaveTV = dialog.findViewById(R.id.btnSaveTV);
        btnCancelTV = dialog.findViewById(R.id.btnCancelTV);

        // check type insert 0 hay update 1
        edMaTV.setEnabled(false);
        if (type!=0) {
            edMaTV.setText(String.valueOf(item.getMaTV()));
            edTenTV.setText(item.getHoTen());
            edNamSinh.setText(item.getNamSinh());
        }

        btnCancelTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSaveTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new ThanhVien();
                item.setHoTen(edTenTV.getText().toString());
                item.setNamSinh(edNamSinh.getText().toString());
                if (validate()>0) {
                    if (type==0) {
                        //type = 0 (inser)
                        if (thanhVienDAO.insert(item)>0) {
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // type = 1 (update)
                        item.setMaTV(Integer.parseInt(edMaTV.getText().toString()));
                        if (thanhVienDAO.update(item)>0) {
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
        if (edTenTV.getText().length()==0||edNamSinh.getText().length()==0) {
            Toast.makeText(getContext(), "Bạn cần nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return  check;
    }
}