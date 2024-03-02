package fpoly.thanhndph45160.duanmau.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import fpoly.thanhndph45160.duanmau.Adapter.LoaiSachSpinnerAdapter;
import fpoly.thanhndph45160.duanmau.Adapter.SachAdapter;
import fpoly.thanhndph45160.duanmau.DAO.LoaiSachDAO;
import fpoly.thanhndph45160.duanmau.DAO.SachDAO;
import fpoly.thanhndph45160.duanmau.Model.LoaiSach;
import fpoly.thanhndph45160.duanmau.Model.Sach;
import fpoly.thanhndph45160.duanmau.R;


public class SachFragment extends Fragment {

    ListView lvSach;
    SachDAO sachDAO;

    FloatingActionButton fab;
    EditText edMaSach, edTenSach, edtGiaThue;
    Spinner spinner;
    Dialog dialog;
    Button btnSave, btnCancel;
    SachAdapter sachAdapter;

    Sach item;
    List<Sach> list;

    LoaiSachSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiSach> listLoaiSach;
    LoaiSachDAO loaiSachDAO;
    LoaiSach loaiSach;
    SearchView searchView;
    int maLoaiSach, position;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sach, container, false);
        lvSach = v.findViewById(R.id.lvSach);
        sachDAO = new SachDAO(getActivity());
        capNhatLv();
        fab = v.findViewById(R.id.fab);
        searchView = v.findViewById(R.id.searchView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
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
                ArrayList<Sach> newList = new ArrayList<>();
                if (!newText.isEmpty()) {

                    for (Sach sach : list) {
                        int giaThue = sach.getGiaThue();
                        int giaInput = Integer.parseInt(newText);
                        if (giaThue > giaInput) {
                            newList.add(sach);
                        }
                    }
                } else {
                    newList.addAll(list);
                }

                sachAdapter = new SachAdapter(getActivity(), SachFragment.this, newList);
                lvSach.setAdapter(sachAdapter);
                return false;
            }
        });
        return v;
    }

    void capNhatLv() {
        list = (List<Sach>) sachDAO.getAll();
        sachAdapter = new SachAdapter(getActivity(), this, list);
        lvSach.setAdapter(sachAdapter);
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xoá");
        builder.setMessage("Bạn có muốn xoá không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sachDAO.delete(Id);
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
        dialog.setContentView(R.layout.sach_dialog);

        edMaSach = dialog.findViewById(R.id.edMaSach);
        edTenSach = dialog.findViewById(R.id.edTenSach);
        edtGiaThue = dialog.findViewById(R.id.edGiaThue);
        spinner = dialog.findViewById(R.id.spLoaiSach);
        btnSave = dialog.findViewById(R.id.btnSaveSach);
        btnCancel = dialog.findViewById(R.id.btnCancelSach);

        listLoaiSach = new ArrayList<LoaiSach>();
        loaiSachDAO = new LoaiSachDAO(getContext());
        listLoaiSach = (ArrayList<LoaiSach>) loaiSachDAO.getAll();

        spinnerAdapter = new LoaiSachSpinnerAdapter(getContext(), listLoaiSach);
        spinner.setAdapter(spinnerAdapter);
        // lay ma loai sach
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = listLoaiSach.get(position).getMaLoai();
                Toast.makeText(getContext(), "Chọn "+listLoaiSach.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // kiem tra insert hay update
        edMaSach.setEnabled(false);
        if (type!=0) {
            // hien thi su lieu cho Edit Text
            edMaSach.setText(String.valueOf(item.getMaSach()));
            edTenSach.setText(item.getTenSach());
            edtGiaThue.setText(String.valueOf(item.getGiaThue()));
            for (int i = 0; i < listLoaiSach.size(); i++) {
                if (item.getMaLoai() == (listLoaiSach.get(i).getMaLoai())) {
                    position = i;
                }
            }
            Log.i("demo", "posSach: " + position);
            spinner.setSelection(position);
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new Sach();
                item.setTenSach(edTenSach.getText().toString());
                item.setGiaThue(Integer.parseInt(edtGiaThue.getText().toString()));
                item.setMaLoai(maLoaiSach);
                if (validate() > 0) {
                    if (type == 0) {
                        // type = 0 (insert)
                        if (sachDAO.insert(item) > 0) {
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // type = 1 (update)
                        item.setMaSach(Integer.parseInt(edMaSach.getText().toString()));
                        if (sachDAO.update(item) > 0) {
                            Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                capNhatLv();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if (edTenSach.getText().length() == 0 || edtGiaThue.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return  check;
    }
}