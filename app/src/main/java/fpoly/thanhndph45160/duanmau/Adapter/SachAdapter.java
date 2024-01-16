package fpoly.thanhndph45160.duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import fpoly.thanhndph45160.duanmau.DAO.LoaiSachDAO;
import fpoly.thanhndph45160.duanmau.Model.LoaiSach;
import fpoly.thanhndph45160.duanmau.Model.Sach;
import fpoly.thanhndph45160.duanmau.R;
import fpoly.thanhndph45160.duanmau.fragment.SachFragment;

public class SachAdapter extends ArrayAdapter<Sach> {
    Context context;
    SachFragment sachFragment;
    List<Sach> list;
    TextView tvMaSach, tvTenSach, tvGiaThue, tvLoai;
    ImageView imgDel;

    public SachAdapter(@NonNull Context context, SachFragment sachFragment, List<Sach> list) {
        super(context, 0, list );
        this.context = context;
        this.sachFragment = sachFragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sach_item, null);
        }

        final Sach item = list.get(position);
        if (item!=null) {
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(item.getMaLoai()));

            tvMaSach = v.findViewById(R.id.tvMaSach);
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvGiaThue = v.findViewById(R.id.tvGiaThue);
            tvLoai = v.findViewById(R.id.tvLoai);
            imgDel = v.findViewById(R.id.imgDeleteLS);

            tvMaSach.setText("Mã sách: "+item.getMaSach());
            tvTenSach.setText("Tên sách: "+item.getTenSach());
            tvGiaThue.setText("Giá thuê: "+item.getGiaThue());
            tvLoai.setText("Loại sách: "+item.getMaLoai() + " - "+loaiSach.getTenLoai());
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            sachFragment.xoa(String.valueOf(item.getMaSach()));
            }
        });

        return v;
    }
}
