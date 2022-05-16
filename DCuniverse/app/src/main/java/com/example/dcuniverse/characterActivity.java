package com.example.dcuniverse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class characterActivity extends AppCompatActivity {
    ImageView imgAva;
    TextView txtNameD,txtSideD,txtPowerD,txtComicD,txtSoP,txtDetail;
    String comicin="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        addView();
    }

    private void addView() {
        imgAva = findViewById(R.id.imgAvaD);
        txtNameD = findViewById(R.id.txtNameD);
        txtSideD = findViewById(R.id.txtSideD);
        txtPowerD = findViewById(R.id.txtPowerD);
        txtComicD = findViewById(R.id.txtInD);
        txtSoP = findViewById(R.id.txtSoP);
        txtDetail = findViewById(R.id.txtDetail);
        Intent intent = getIntent();
        Character character = (Character) intent.getSerializableExtra("ch");
        Picasso.get().load(character.getAvatar()).resize(410,200).centerCrop().into(imgAva);
        txtNameD.setText(character.getName());
        String phe = character.getSide();
        if(phe.equalsIgnoreCase("Hero"))
        {
            txtSideD.setTextColor(getResources().getColor(R.color.Hero));
        }
        else if(phe.equalsIgnoreCase("Villian"))
        {
            txtSideD.setTextColor(getResources().getColor(R.color.purple_700));
        }
        else if(phe.equalsIgnoreCase("Above All"))
        {
            txtSideD.setTextColor(getResources().getColor(R.color.God));
        }
        else if(phe.equalsIgnoreCase("Maverl Universe"))
        {
            txtSideD.setTextColor(getResources().getColor(R.color.teal_200));
        }
        txtSideD.setText(character.getSide());
        txtPowerD.setText("Chỉ số sức mạnh:"+character.getPower());
        ArrayList<String> listComic = character.getComicin();
        for (int i=0;i<listComic.size();i++)
        {
            comicin += listComic.get(i)+", ";
        }
        txtComicD.setText("Xuất hiện trong: "+comicin);
        txtSoP.setText("Nguồn gốc: "+character.getSoP());
        txtDetail.setText("Chi tiết sức mạnh: "+character.getDetail());
    }
}