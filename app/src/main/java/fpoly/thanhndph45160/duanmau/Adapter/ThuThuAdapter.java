package fpoly.thanhndph45160.duanmau.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import fpoly.thanhndph45160.duanmau.Model.ThuThu;
import fpoly.thanhndph45160.duanmau.fragment.ThemNguoiDungFragment;

public class ThuThuAdapter extends ArrayAdapter<ThuThu> {

    Context context;
    ThemNguoiDungFragment themNguoiDungFragment;
    ArrayList<ThuThu> list;

    public ThuThuAdapter(@NonNull Context context, ThemNguoiDungFragment themNguoiDungFragment, ArrayList<ThuThu> list) {
        super(context, 0, list);
        this.context = context;
        this.themNguoiDungFragment = themNguoiDungFragment;
        this.list = list;
    }


}
