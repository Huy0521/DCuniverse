package com.example.dcuniverse;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SignActivity extends AppCompatActivity {
    EditText edTK2,edMK2,edXN,edTK3;
    Button btnSign;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        addView();
        addEvent();
    }

    private void addEvent() {
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Sign();
            }
        });
    }

    private void Sign() {
        databaseReference = firebaseDatabase.getReference("User");
        String account = edTK3.getText().toString();
        String email = edTK2.getText().toString();
        String password = edMK2.getText().toString();
        String confirm = edXN.getText().toString();
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!email.contains("@gmail.com"))
        {
            Toast.makeText(this, "Email không chính xác!", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(password.length()<8)
        {
            Toast.makeText(this, "Mật khẩu phải nhiều hơn 8 ký tự", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (!password.equals(confirm)) {
            Toast.makeText(this, "Mật khẩu xác nhận không chính xác!", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(account)) {
                        Toast.makeText(SignActivity.this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();

                    } else {
                        User user = new User(account, email, password);
                        databaseReference.child(account).setValue(user);
                        Toast.makeText(SignActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        Intent intentDN = new Intent(SignActivity.this,LoginActivity.class);
                        startActivity(intentDN);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(SignActivity.this, "Thiết bị không kết nối mạng!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void addView() {
        btnSign = findViewById(R.id.btnSign);
        edTK3 = findViewById(R.id.edTK3);
        edTK2 = findViewById(R.id.edTK2);
        edMK2 = findViewById(R.id.edMK2);
        edXN = findViewById(R.id.edXN);
        firebaseDatabase = FirebaseDatabase.getInstance("https://dcuniverser-36ae9-default-rtdb.firebaseio.com/");
    }
}
