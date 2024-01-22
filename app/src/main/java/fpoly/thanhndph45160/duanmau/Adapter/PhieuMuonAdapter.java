package fpoly.thanhndph45160.duanmau.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fpoly.thanhndph45160.duanmau.DAO.SachDAO;
import fpoly.thanhndph45160.duanmau.DAO.ThanhVienDAO;
import fpoly.thanhndph45160.duanmau.Model.PhieuMuon;
import fpoly.thanhndph45160.duanmau.Model.Sach;
import fpoly.thanhndph45160.duanmau.Model.ThanhVien;
import fpoly.thanhndph45160.duanmau.R;
import fpoly.thanhndph45160.duanmau.fragment.PhieuMuonFragment;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {

    Context context;
    PhieuMuonFragment phieuMuonFragment;
    ArrayList<PhieuMuon> lists;
    TextView tvMaPM, tvTenTV, tvTenSach, tvTienThue, tvNgay, tvTraSach;
    ImageView imgDel;
    ThanhVienDAO thanhVienDAO;
    SachDAO sachDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public PhieuMuonAdapter(@NonNull Context context, PhieuMuonFragment phieuMuonFragment, ArrayList<PhieuMuon> lists) {
        super(context, 0,lists);
        this.context = context;
        this.phieuMuonFragment = phieuMuonFragment;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v =inflater.inflate(R.layout.phieu_muon_item, null);
        }

        PhieuMuon item = lists.get(position);

        if (item!=null) {

            tvMaPM = v.findViewById(R.id.tvMaPM);
            tvMaPM.setText("Mã phiếu mượn: "+item.getMaPM());

            sachDAO = new SachDAO(context);
            Sach sach = sachDAO.getID(String.valueOf(item.getMaSach()));
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách: "+sach.getTenSach());

            thanhVienDAO = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(item.getMaTV()));
            tvTenTV = (TextView) v.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Thành viên: "+thanhVien.getHoTen());
            Log.d("yyyyyy", "thanh vien: "+thanhVien.getHoTen());

            tvTienThue = v.findViewById(R.id.tvTienThue);
            tvTienThue.setText("Tiền thuê:" + item.getTienThue());

            tvNgay = v.findViewById(R.id.tvNgayPM);
            tvNgay.setText("Ngày thuê: "+sdf.format(item.getNgay()));
            Log.d("zzzzzzz", "Log: "+sdf.format(item.getNgay()));


            tvTraSach = v.findViewById(R.id.tvTraSach);
            if (item.getTraSach() == 1) {
                tvTraSach.setText("Đã trả sách");
                tvTraSach.setTextColor(Color.BLUE);
            } else {
                tvTraSach.setText("Chưa trả sách");
                tvTraSach.setTextColor(Color.RED);
            }
            imgDel = v.findViewById(R.id.imgDeleteLS);
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }

        return v;
    }
}
