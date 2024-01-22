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

import fpoly.thanhndph45160.duanmau.Model.LoaiSach;
import fpoly.thanhndph45160.duanmau.R;
import fpoly.thanhndph45160.duanmau.fragment.LoaiSachFragment;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    Context context;
    LoaiSachFragment loaiSachFragment;
    ArrayList<LoaiSach> lists;
    TextView tvMaLoai, tvTenLoai;
    ImageView imgDel;


    public LoaiSachAdapter(@NonNull Context context, LoaiSachFragment loaiSachFragment, ArrayList<LoaiSach> lists) {
        super(context, 0, lists);
        this.context = context;
        this.loaiSachFragment = loaiSachFragment;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
        if (v==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item, null);
        }

        final LoaiSach item = lists.get(position);

        if (item!=null) {
            tvMaLoai = v.findViewById(R.id.tvMaLoai);
            tvTenLoai = v.findViewById(R.id.tvTenLoai);

            tvMaLoai.setText("Mã loại sách: "+ item.getMaLoai());
            tvTenLoai.setText("Tên loại sách: "+ item.getTenLoai());

            imgDel = v.findViewById(R.id.imgDeleteLS);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiSachFragment.xoa(String.valueOf(item.getMaLoai()));
            }
        });

        return v;
    }
}
