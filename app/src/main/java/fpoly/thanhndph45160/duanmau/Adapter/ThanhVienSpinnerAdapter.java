package fpoly.thanhndph45160.duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpoly.thanhndph45160.duanmau.Model.ThanhVien;
import fpoly.thanhndph45160.duanmau.R;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    private ArrayList<ThanhVien> list;
    TextView tvMaTV, tvTenTV;
    public ThanhVienSpinnerAdapter(@NonNull Context context, ArrayList<ThanhVien> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanh_vien_item_spinner, null);
        }

        final ThanhVien item = list.get(position);

        if (item!=null) {
            tvMaTV = v.findViewById(R.id.tvMaTVSp);
            tvTenTV = v.findViewById(R.id.tvTenTVSp);

            tvMaTV.setText(item.getMaTV()+". ");
            tvTenTV.setText(item.getHoTen());
        }

        return v;

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanh_vien_item_spinner, null);
        }

        final ThanhVien item = list.get(position);

        if (item!=null) {
            tvMaTV = v.findViewById(R.id.tvMaTVSp);
            tvTenTV = v.findViewById(R.id.tvTenTVSp);

            tvMaTV.setText(item.getMaTV()+". ");
            tvTenTV.setText(item.getHoTen());
        }

        return v;
    }
}
