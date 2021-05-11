package com.example.bepongbau.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bepongbau.Activity.MainActivity;
import com.example.bepongbau.Model.User;
import com.example.bepongbau.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityLogin extends AppCompatActivity {
    Button btnLogin;
    TextView txtDangki,hintPassword;
    EditText edtTenDangNhap, edtMatKhau;
    private boolean isShow = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        setOnClick();
        setEvent();
        SharedPreferences sharedPreferences = getSharedPreferences("datafile",MODE_PRIVATE);
        if (sharedPreferences.contains("taikhoan")&& sharedPreferences.contains("password"))
        {
            edtTenDangNhap.setText(sharedPreferences.getString("taikhoan",""));
            edtMatKhau.setText(sharedPreferences.getString("password",""));
            User user = new User();
            user.setUsername(edtTenDangNhap.getText().toString());
            user.setPassword(edtMatKhau.getText().toString());
            Toast.makeText(this, "Đăng Nhập Thành Công!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
            startActivity(intent);
            finish();
     }
    }
    private void setOnClick() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase login = FirebaseDatabase.getInstance();
                DatabaseReference reference = login.getReference();
                reference.child("QuanLyTaiKhoan").child(edtTenDangNhap.getText().toString()).child("ThongTinDangNhap").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists())
                        {
                            User user = dataSnapshot.getValue(User.class);
                            String username = user.username;
                            String password = user.password;
                            String taikhoan = edtTenDangNhap.getText().toString();
                            String matkhau = edtMatKhau.getText().toString();
                            if (taikhoan.equals(""))
                            {
                                edtTenDangNhap.setError("Bạn cần nhập tài khoản");
                            }
                            if (matkhau.equals(""))
                            {
                                edtMatKhau.setError("Bạn cần nhâp mật khẩu");
                            }
                            if (taikhoan.equals(username) && matkhau.equals(password))
                            {
                                Toast.makeText(ActivityLogin.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                SharedPreferences sharedPreferences = getSharedPreferences("datafile",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("taikhoan",taikhoan);
                                editor.putString("password",matkhau);
                                editor.commit();
                                Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                            else {
                                Toast.makeText(ActivityLogin.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(ActivityLogin.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(ActivityLogin.this, "Dang Nhap That Bai", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    private void setEvent() {
        txtDangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class);
                startActivity(intent);
            }
        });
        hintPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePass();
            }
        });
    }

    private void AnhXa() {
        hintPassword = findViewById(R.id.hintPassword);
        btnLogin = findViewById(R.id.btnDongYDN);
        txtDangki = findViewById(R.id.txtDangKi);
        edtTenDangNhap = findViewById(R.id.edTenDangNhapDN);
        edtMatKhau = findViewById(R.id.edMatKhauDN);
    }
    public boolean togglePass()
    {
        if(isShow){
            edtMatKhau.setTransformationMethod(null);
            isShow = !isShow;
        }
        else
        {
            isShow = !isShow;
            edtMatKhau.setTransformationMethod(new PasswordTransformationMethod());
        }
        return isShow;
    }
}