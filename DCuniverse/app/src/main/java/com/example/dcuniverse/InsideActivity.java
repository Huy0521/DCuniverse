package com.example.dcuniverse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.material.slider.Slider;
import com.squareup.picasso.Picasso;

public class InsideActivity extends AppCompatActivity {
    GridView gridView;
    String[] ten={
            "danh sách nhân vật", "top sức mạnh","tin tức","thông tin cá nhân"
    };
    int[] logo={
            R.drawable.dsnv,R.drawable.topsucmanh,R.drawable.tintuc,R.drawable.anhdaidien
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside);
        gridView = findViewById(R.id.GridView);
        GridviewAdapter gridviewAdapter = new GridviewAdapter(ten,logo);
        gridView.setAdapter(gridviewAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int postion, long id) {
                if(postion==0)
                {
                    Intent intentDSNV = new Intent(InsideActivity.this,listviewActivity.class);
                    startActivity(intentDSNV);
                }
                if (postion==1)
                {
                    Intent intentTOP = new Intent(InsideActivity.this,listtop.class);
                    startActivity(intentTOP);
                }
                if (postion==2)
                {
                    Intent intentXML = new Intent(InsideActivity.this,XmlActivity.class);
                    startActivity(intentXML);
                }
                if (postion==3)
                {
                    Intent intentUser = new Intent(InsideActivity.this,InforActivity.class);
                    startActivity(intentUser);
                }
            }
        });
    }
}