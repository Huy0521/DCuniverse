package com.example.dcuniverse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText edTK, edMK;
    TextView a;
    Button btnLogin,BtnSign;
    CheckBox checkBox;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();
        addEvent();
    }

    private void addEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,InsideActivity.class);
                startActivity(intent);
            }
        });
    }
    private void addView() {
        edTK = findViewById(R.id.edTK);
        a = findViewById(R.id.txtMK);
        edMK = findViewById(R.id.edMK);
        btnLogin = findViewById(R.id.btnLogin);
        checkBox = findViewById(R.id.CBsave);
    }
}