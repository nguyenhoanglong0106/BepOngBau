package com.example.bepongbau.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bepongbau.Model.Information;
import com.example.bepongbau.Model.User;
import com.example.bepongbau.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.PropertyPermission;

public class ActivityRegister extends AppCompatActivity {
    EditText edtTenDN,edtMatKhauDN,edtHoVaTen,edtNgaySinh,edtSDT;
    RadioButton GioiTinhNam,GioiTinhNu;
    Button btnDangKy,btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        anhxa();
        setEvent();
    }



    private void setEvent() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTenDN.getText().toString().equals("") ||  edtMatKhauDN.getText().toString().equals("") ||
                        edtHoVaTen.getText().toString().equals("") || edtNgaySinh.getText().toString().equals("")
                        || edtSDT.getText().toString().equals("")
                )
                {
                    edtTenDN.setError("Kh??ng ???????c ????? tr???ng");
                    edtMatKhauDN.setError("Kh??ng ???????c ????? tr???ng");
                    edtHoVaTen.setError("Kh??ng ???????c ????? tr???ng");
                    edtNgaySinh.setError("Kh??ng ???????c ????? tr???ng");
                    edtSDT.setError("Kh??ng ???????c ????? tr???ng");
                }
                else
                {
                    //L???y th??ng tin ????ng nh???p
                    User user = new User();
                    user.username = edtTenDN.getText().toString();
                    user.password = edtMatKhauDN.getText().toString();
                    //L???y th??ng tin kh??ch h??ng
                    Information information = new Information();
                    information.hovaten = edtHoVaTen.getText().toString();
                    information.ngaysinh = edtNgaySinh.getText().toString();
                    information.sdt = edtSDT.getText().toString();
                    if (GioiTinhNam.isChecked())
                    {
                        information.gioitinh = "Nam";
                    }
                    else if (GioiTinhNu.isChecked())
                    {
                        information.gioitinh = "N???";
                    }
                    //l??u th??ng tin t??i khoan
                    FirebaseDatabase firebaseDatabase =  FirebaseDatabase.getInstance();
                    DatabaseReference reference = firebaseDatabase.getReference();
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(!snapshot.child("QuanLyTaiKhoan").exists())
                            {
                                reference.child("QuanLyTaiKhoan").child(edtTenDN.getText().toString())
                                        .child("ThongTinDangNhap").setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        FirebaseDatabase firebaseDatabase1 =  FirebaseDatabase.getInstance();
                                        DatabaseReference reference1 = firebaseDatabase1.getReference();
                                        reference1.child("QuanLyTaiKhoan").child(edtTenDN.getText().toString()).child("ThongTinKhachHang").setValue(information);
                                        Toast.makeText(ActivityRegister.this, "????ng k?? th??nh c??ng!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(ActivityRegister.this,ActivityLogin.class);
                                        startActivity(intent);
                                        //tr??? d??? li???u tr???ng
                                        edtTenDN.setText("");
                                        edtMatKhauDN.setText("");
                                        edtSDT.setText("");
                                        edtHoVaTen.setText("");
                                        edtNgaySinh.setText("");
                                        GioiTinhNam.isChecked();
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(ActivityRegister.this, "????ng k?? th???t b???i!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else{
                                Toast.makeText(ActivityRegister.this, "T??i kho???n ???? t???n t???i!", Toast.LENGTH_SHORT).show();
                                edtTenDN.setText("");
                                edtMatKhauDN.setText("");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(ActivityRegister.this, "T??i kho???n ???? t???n t???i!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    //luu thong tin kh??ch h??ng
                       
                }
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseDay();
            }
        });
    }

    private void anhxa() {
        edtTenDN = findViewById(R.id.edTenDangNhapDK);
        edtMatKhauDN = findViewById(R.id.edMatKhauDK);
        GioiTinhNam = findViewById(R.id.rdNam);
        GioiTinhNu = findViewById(R.id.rdNu);
        edtHoVaTen = findViewById(R.id.edtHoVaTen);
        edtNgaySinh = findViewById(R.id.edNgaySinhDK);
        edtSDT = findViewById(R.id.edtSodienThoai);
        btnDangKy = findViewById(R.id.btnDongYDK);
        btnLogout = findViewById(R.id.btnThoatDK);
    }
    //Ch???n ng??y
    @SuppressLint({"NewApi", "LocalSuppress", "SimpleDateFormat"})
    public void ChooseDay(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if (!edtNgaySinh.getText().toString().equals(""))
                cal.setTime(sdf.parse(edtNgaySinh.getText().toString()));
            else
                cal.getTime();

            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog, (datePicker, yearSelected, monthSelected, daySelected) -> {
                monthSelected = monthSelected + 1;
                Date date = StringToDate(daySelected + "/" + monthSelected + "/" + yearSelected, "dd/MM/yyyy");
                edtNgaySinh.setText(new SimpleDateFormat("dd/MM/yyyy").format(date));
            }, year, month, day);

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setTitle("Ch???n ng??y sinh");

            dialog.show();
        } catch (ParseException e) {
            Toast.makeText(this, "L???i: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SimpleDateFormat")
    public Date StringToDate(String dob, String format){
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            return formatter.parse(dob);
        } catch (ParseException e) {
            Toast.makeText(this, "L???i: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}