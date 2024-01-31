package fpoly.thanhndph45160.duanmau.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fpoly.thanhndph45160.duanmau.Adapter.PhieuMuonAdapter;
import fpoly.thanhndph45160.duanmau.Adapter.SachSpinnerAdapter;
import fpoly.thanhndph45160.duanmau.Adapter.ThanhVienSpinnerAdapter;
import fpoly.thanhndph45160.duanmau.DAO.PhieuMuonDAO;
import fpoly.thanhndph45160.duanmau.DAO.SachDAO;
import fpoly.thanhndph45160.duanmau.DAO.ThanhVienDAO;
import fpoly.thanhndph45160.duanmau.Model.PhieuMuon;
import fpoly.thanhndph45160.duanmau.Model.Sach;
import fpoly.thanhndph45160.duanmau.Model.ThanhVien;
import fpoly.thanhndph45160.duanmau.R;


public class PhieuMuonFragment extends Fragment {

    ListView lvPhieuMuon;
    ArrayList<PhieuMuon> list;
    PhieuMuonDAO phieuMuonDAO;
    PhieuMuonAdapter phieuMuonAdapter;
    PhieuMuon item;

    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV, spSach;
    TextView tvNgay, tvTienThue;
    CheckBox chkTraSach;
    Button btnSave, btnCancel;

    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;

    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    Sach sach;
    int maSach, tienThue;
    int positionTV, positionSach;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        lvPhieuMuon = v.findViewById(R.id.lvPhieuMuon);
        phieuMuonDAO = new PhieuMuonDAO(getActivity());
        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });

        lvPhieuMuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });

        capNhatLv();
        return v;
    }

    void capNhatLv() {
        list = (ArrayList<PhieuMuon>) phieuMuonDAO.getAll();
        phieuMuonAdapter = new PhieuMuonAdapter(getActivity(), this, list);
        lvPhieuMuon.setAdapter(phieuMuonAdapter);
    }

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.phieu_muon_dialog);
        edMaPM = dialog.findViewById(R.id.edMaPM);
        spTV = dialog.findViewById(R.id.spMaTV);
        spSach = dialog.findViewById(R.id.spMaSach);
        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvTienThue = dialog.findViewById(R.id.tvTienThue);
        chkTraSach = dialog.findViewById(R.id.chkTraSach);
        btnCancel = dialog.findViewById(R.id.btnCancelPM);
        btnSave = dialog.findViewById(R.id.btnSavePM);

        // set Ngày thuê, mặc định ngày iện tại
        tvNgay.setText("Ngày thuê: "+ sdf.format(new Date()));
        edMaPM.setEnabled(false);

        thanhVienDAO = new ThanhVienDAO(context);
        listThanhVien = new ArrayList<ThanhVien>();
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context, listThanhVien);
        spTV.setAdapter(thanhVienSpinnerAdapter);
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maThanhVien = listThanhVien.get(position).getMaTV();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sachDAO = new SachDAO(context);
        listSach = new ArrayList<Sach>();
        listSach = (ArrayList<Sach>) sachDAO.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context, listSach);
        spSach.setAdapter(sachSpinnerAdapter);
        // lay maLoaiSach
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).getMaSach();
                tienThue = listSach.get(position).getGiaThue();
                tvTienThue.setText("Tiền thuê: "+tienThue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // set data len form khi sua PM
        if (type!=0) {
            edMaPM.setText(String.valueOf(item.getMaPM()));

            for (int i = 0; i < listThanhVien.size(); i++) {
                if (item.getMaTV()==(listThanhVien.get(i).getMaTV())) {
                    positionTV = i;
                }
            }
            spTV.setSelection(positionTV);


            for (int i = 0; i < listSach.size(); i++) {
                if (item.getMaSach() == listSach.get(i).getMaSach()) {
                    positionSach = i;
                }
            }
            spSach.setSelection(positionSach);

            tvNgay.setText("Ngày thuê:"+sdf.format(item.getNgay()));
            tvTienThue.setText("Tiền thuê: "+item.getTienThue());

            if (item.getTraSach()==1) {
                chkTraSach.setChecked(true);
            } else {
                chkTraSach.setChecked(false);
            }
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
                item = new PhieuMuon();
                item.setMaSach(maSach);
                item.setMaTV(maThanhVien);
                item.setNgay(new Date());
                item.setTienThue(tienThue);

                SharedPreferences sharedPreferences = getContext().getSharedPreferences("USER_FILE", getContext().MODE_PRIVATE);
                String maTT = sharedPreferences.getString("USERNAME", "");
                Log.d("zzzzz", "Lay dc maTT: "+maTT);
                item.setMaTT(maTT);

                if (chkTraSach.isChecked()) {
                    item.setTraSach(1);
                } else {
                    item.setTraSach(0);
                }

                if (type == 0) {
                    // type 0 == insert
                    if (phieuMuonDAO.insert(item) >0) {
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        capNhatLv();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // type 1 == update
                    item.setMaPM(Integer.parseInt(edMaPM.getText().toString()));
                    if (phieuMuonDAO.update(item) > 0) {
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        capNhatLv();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        dialog.show();
    }

    public void xoa (String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xoá");
        builder.setMessage("Bạn có muốn xoá không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                phieuMuonDAO.delete(Id);
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

}