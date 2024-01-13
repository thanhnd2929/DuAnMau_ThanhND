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

import fpoly.thanhndph45160.duanmau.Model.ThanhVien;
import fpoly.thanhndph45160.duanmau.R;
import fpoly.thanhndph45160.duanmau.fragment.ThanhVienFragment;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    ThanhVienFragment fragment;
    ArrayList<ThanhVien> lists;
    TextView tvMaTV, tvTenTV, tvNamSinh;
    ImageView imgDel;

    public ThanhVienAdapter(@NonNull Context context,ThanhVienFragment fragment, ArrayList<ThanhVien> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
        if (v==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanh_vien_item, null);
        }

        final ThanhVien item = lists.get(position);
        if (item != null) {
            tvMaTV = v.findViewById(R.id.tvMaTV);
            tvTenTV =v.findViewById(R.id.tvTenTV);
            tvNamSinh =v.findViewById(R.id.tvNamSinh);

            tvMaTV.setText("Mã thành viên: "+item.getMaTV());
            tvTenTV.setText("Tên thành viên: "+item.getHoTen());
            tvNamSinh.setText("Năm sinh thành viên: "+item.getNamSinh());

            imgDel = v.findViewById(R.id.imgDeleteLS);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getMaTV()));
            }
        });

        return v;
    }


}
