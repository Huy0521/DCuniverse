package com.example.dcuniverse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InforActivity extends AppCompatActivity {
    EditText edEmail,edPass,edPass2;
    TextView txtAcc;
    Button BtnUpdate;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);
        addView();
        addEvent();
    }

    private void addEvent() {
        BtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUpdate();
            }
        });
    }

    private void addUpdate() {
        String email,mk,mknew,tk;
        databaseReference = firebaseDatabase.getReference().child("User");
        email= edEmail.getText().toString();
        mk = edPass.getText().toString();
        mknew = edPass2.getText().toString();
        tk = txtAcc.getText().toString();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(mk) || TextUtils.isEmpty(mknew)) {
                    Toast.makeText(InforActivity.this,"Vui lòng nhập đầy đủ thông tin!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(snapshot.hasChild(tk))
                {
                    String pass = snapshot.child(tk).child("password").getValue(String.class);
                    if(mk.equals(pass))
                    {
                        User user = new User(tk, email, mknew);
                        databaseReference.child(tk).setValue(user);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addView() {
        edEmail = findViewById(R.id.edEmail);
        edPass = findViewById(R.id.edPass);
        edPass2 = findViewById(R.id.edPassNew);
        txtAcc = findViewById(R.id.txtAcc);
        BtnUpdate = findViewById(R.id.BtnUpdate);
        SharedPreferences preferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        String user = preferences.getString("tk","");
        txtAcc.setText(user);
        firebaseDatabase = FirebaseDatabase.getInstance("https://dcuniverser-36ae9-default-rtdb.firebaseio.com/");
    }
}