package com.example.dcuniverse;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText edTK, edMK;
    Button btnLogin,BtnSign;
    CheckBox checkBox;
    String Login ="thongtinTK";
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addView();
        addEvent();
    }

    private void addEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        BtnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentsign = new Intent(LoginActivity.this,SignActivity.class);
                startActivity(intentsign);

            }
        });
    }

    private void login() {
        String tk,mk;
        Intent intentLogin = new Intent(LoginActivity.this,InsideActivity.class);
        databaseReference = firebaseDatabase.getReference().child("User");
        tk = edTK.getText().toString();
        mk = edMK.getText().toString();
        if(TextUtils.isEmpty(tk)|| TextUtils.isEmpty(mk))
        {
            Toast.makeText(this,"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(tk))
                    {
                        String pass = snapshot.child(tk).child("password").getValue(String.class);
                        if(pass.equals(mk))
                        {
                            SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("tk",edTK.getText().toString());
                            editor.commit();
                            startActivity(intentLogin);
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences(Login,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user",edTK.getText().toString());
        editor.putString("pass",edMK.getText().toString());
        editor.putBoolean("save",checkBox.isChecked());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(Login,MODE_PRIVATE);
        String user = preferences.getString("user","");
        String pass = preferences.getString("pass","");
        boolean save = preferences.getBoolean("save",false);
        if(save)
        {
            edTK.setText(user);
            edMK.setText(pass);
        }
        checkBox.setChecked(save);
    }

    private void addView() {
        edTK = findViewById(R.id.edTK);
        edMK = findViewById(R.id.edMK);
        btnLogin = findViewById(R.id.btnLogin);
        checkBox = findViewById(R.id.CBsave);
        BtnSign = findViewById(R.id.btnSign);
        firebaseDatabase = FirebaseDatabase.getInstance("https://dcuniverser-36ae9-default-rtdb.firebaseio.com/");
    }
}
