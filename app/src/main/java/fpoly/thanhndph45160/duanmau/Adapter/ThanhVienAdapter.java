package fpoly.thanhndph45160.duanmau.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import fpoly.thanhndph45160.duanmau.DAO.ThanhVienDAO;
import fpoly.thanhndph45160.duanmau.Model.ThanhVien;
import fpoly.thanhndph45160.duanmau.R;
import fpoly.thanhndph45160.duanmau.fragment.ThanhVienFragment;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    ThanhVienFragment fragment;
    ArrayList<ThanhVien> lists;
    TextView tvMaTV, tvTenTV, tvNamSinh;
    ImageView imgDel;
    ThanhVienDAO thanhVienDAO;

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
        thanhVienDAO = new ThanhVienDAO(getContext());
        if (item != null) {
            tvMaTV = v.findViewById(R.id.tvMaTV);
            tvTenTV =v.findViewById(R.id.tvTenTV);
            tvNamSinh =v.findViewById(R.id.tvNamSinh);

            tvMaTV.setText("Mã thành viên: "+item.getMaTV());
            tvTenTV.setText("Tên thành viên: "+item.getHoTen());
            tvNamSinh.setText("Năm sinh thành viên: "+item.getNamSinh());


//            tvTenTV.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                    LayoutInflater inflater = ((Activity)context).getLayoutInflater();
//                    View view = inflater.inflate(R.layout.dialog_tenthanhvien, null);
//                    builder.setView(view);
//                    Dialog dialog = builder.create();
//                    dialog.show();
//
//                    EditText edTenTV = view.findViewById(R.id.edTenTV);
//                    Button btnSub = view.findViewById(R.id.btnSub);
//
//                    edTenTV.setText(item.getHoTen());
//
//                    btnSub.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            String ten = edTenTV.getText().toString().trim();
//                            item.setHoTen(ten);
//
//                            if (thanhVienDAO.update(item) > 0) {
//                                Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
//
//                                notifyDataSetChanged();
//                                dialog.dismiss();
//                            }
//                        }
//                    });
//
//                }
//            });

            imgDel = v.findViewById(R.id.imgDeleteLS);
        }


//        tvGioiTinh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle("Chọn giới tính");
//
//                builder.setSingleChoiceItems(gt, 0, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
////                            try {
////                                if (tvGioiTinh != null) {
////                                    tvGioiTinh.setTextColor(Color.parseColor(colors[which]));
////                                }
////                                Log.d("color", colors[which]);
////                                // Hiển thị thông báo thành công
////                                Toast.makeText(context, "Thay đổi màu thành công!", Toast.LENGTH_SHORT).show();
////                            } catch (Exception e) {
////                                e.printStackTrace();
////                                // Hiển thị thông báo thất bại
////                                Toast.makeText(context, "Thay đổi màu thất bại!", Toast.LENGTH_SHORT).show();
////                            }
////                            Log.d("color", colors[which]);
//
//
//
//                        // Cập nhật giới tính của item
//                        item.setGioiTinh(gt[which]);
//                        // Cập nhật giới tính trong cơ sở dữ liệu
//                        thanhVienDAO.update(item);
//                        notifyDataSetChanged();
//                        dialog.dismiss();
//
//                    }
//                });

//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        });

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getMaTV()));
            }
        });

        return v;
    }


}
