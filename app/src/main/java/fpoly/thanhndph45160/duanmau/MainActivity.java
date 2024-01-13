package fpoly.thanhndph45160.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import fpoly.thanhndph45160.duanmau.DAO.ThuThuDAO;
import fpoly.thanhndph45160.duanmau.Model.ThuThu;
import fpoly.thanhndph45160.duanmau.fragment.ChangePassFragment;
import fpoly.thanhndph45160.duanmau.fragment.ThanhVienFragment;


public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar1;
    View mHeaderView;
    TextView edUSer;
    ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);
        toolbar1 = findViewById(R.id.toolbar1);

        // set toolbar thay the cho action bar
        setSupportActionBar(toolbar1);
        ActionBar ab = getSupportActionBar();

        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);


        NavigationView nv = findViewById(R.id.nvView);
        // show user in header
        mHeaderView = nv.getHeaderView(0);
        edUSer = mHeaderView.findViewById(R.id.txtUser);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        thuThuDAO = new ThuThuDAO(this);
        ThuThu thuThu = thuThuDAO.getID(user);
        String username = thuThu.getHoTen();
        edUSer.setText("Welcome "+username+"!");

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();

                int id = item.getItemId();

                if (id == R.id.nav_PhieuMuon) {
                    setTitle("Quản Lý Phiếu Mượn");

                } else if (id == R.id.nav_LoaiSach) {
                    setTitle("Quản Lý Loại Sách");
                } else if (id == R.id.nav_Sach) {
                    setTitle("Quản Lý Sách");
                } else if (id == R.id.nav_ThanhVien) {
                    setTitle("Quản Lý Thành Viên");
                    ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, thanhVienFragment)
                            .commit();
                } else if (id == R.id.sub_Top) {
                    setTitle("Top 10 Sách Cho Thuê Nhiều Nhất");
                }  else if (id == R.id.sub_DoanhThu) {
                    setTitle("Thống Kê Doanh Thu");
                } else if (id == R.id.sub_AddUser) {
                    setTitle("Thêm Người Dùng");
                } else if (id == R.id.sub_Pass) {
                    setTitle("Đổi Mật Khẩu");
                    ChangePassFragment changePassFragment = new ChangePassFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, changePassFragment)
                            .commit();
                } else {
                    setTitle("Đăng Xuất");
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }

                drawer.closeDrawers();

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}