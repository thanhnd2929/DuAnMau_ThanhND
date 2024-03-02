package fpoly.thanhndph45160.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.thanhndph45160.duanmau.DAO.ThuThuDAO;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName, edPassword;
    Button btnLogin, btnCancel;
    CheckBox chkRememberPass;
    String strUser, strPass;
    ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Đăng Nhập");
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        thuThuDAO = new ThuThuDAO(this);

        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = preferences.getString("USERNAME", "");
        String pass = preferences.getString("PASSWORD", "");
        Boolean rem = preferences.getBoolean("REMEMBER", false);



        edUserName.setText(user);
        edPassword.setText(pass);
        chkRememberPass.setChecked(rem);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassword.setText("");
                edPassword.setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

    }

    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences sp = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (!status) {
            // xoa tinh trang luu truoc do
            editor.clear();
        } else {
            // luu du lieu
            editor.putString("USERNAME", u);
            editor.putString("PASSWORD", p);
            editor.putBoolean("REMEMBER", status);
        }
        // luu toan bo
        editor.commit();
    }

    public void checkLogin() {
        strUser = edUserName.getText().toString();
        strPass = edPassword.getText().toString();

        if (strUser.isEmpty() || strPass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Không bỏ trống dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            if (thuThuDAO.checkLogin(strUser, strPass) > 0) {
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strUser, strPass, chkRememberPass.isChecked());
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("user", strUser);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
            }
        }
    }




}