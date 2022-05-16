package com.example.dcuniverse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class listtop extends AppCompatActivity {
    TextView txtthum;
    ListView lvCharacter2;
    ListTopAdapter listTopAdapter2;
    ArrayList<Character> listchCharacters2 = new ArrayList<>();
    FirebaseDatabase firebaseDatabase2;
    DatabaseReference databaseReference2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        addView();
        addEvent();
    }


    private void addView() {
        txtthum = findViewById(R.id.txtThum);
        txtthum.setText("TOP 10");
        lvCharacter2 = findViewById(R.id.ListCharacter);
        listTopAdapter2 = new ListTopAdapter(listchCharacters2);
        lvCharacter2.setAdapter(listTopAdapter2);
        firebaseDatabase2 = FirebaseDatabase.getInstance("https://dcuniverser-36ae9-default-rtdb.firebaseio.com/");
    }

    private void addEvent() {
        lvCharacter2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(listtop.this,listchCharacters2.get(position).getName(), Toast.LENGTH_SHORT);
                Intent intentCharacter = new Intent(getBaseContext(),characterActivity.class);
                intentCharacter.putExtra("ch",listchCharacters2.get(position));
                startActivity(intentCharacter);
            }
        });
        databaseReference2 = firebaseDatabase2.getReference().child("Character");
//        Query query = databaseReference2.orderByChild("Top").limitToFirst(10);
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Character character;
                for (DataSnapshot snapshotCharacter : snapshot.getChildren()) {
                    for (DataSnapshot snapshotHero : snapshotCharacter.getChildren()) {
                        character = new Character();
                        for (DataSnapshot snapshotCharacterData : snapshotHero.getChildren()) {
                            if (snapshotCharacterData.getKey().equalsIgnoreCase("Anh")) {
                                character.setAvatar(snapshotCharacterData.getValue(String.class));
                            } else if (snapshotCharacterData.getKey().equalsIgnoreCase("Ten")) {
                                character.setName(snapshotCharacterData.getValue(String.class));
                            } else if (snapshotCharacterData.getKey().equalsIgnoreCase("Sucmanh")) {
                                character.setPower(snapshotCharacterData.getValue(String.class));
                            } else if (snapshotCharacterData.getKey().equalsIgnoreCase("Dautruyen")) {
                                ArrayList<String> listDautruyen = new ArrayList<>();
                                for (DataSnapshot dautruyen : snapshotCharacterData.getChildren()) {
                                    listDautruyen.add(dautruyen.getValue(String.class));
                                }
                                character.setComicin(listDautruyen);
                            } else if (snapshotCharacterData.getKey().equalsIgnoreCase("NguocgocSM")) {
                                character.setSoP(snapshotCharacterData.getValue(String.class));
                            } else if (snapshotCharacterData.getKey().equalsIgnoreCase("Chitiet")) {
                                character.setDetail(snapshotCharacterData.getValue(String.class));
                            } else if (snapshotCharacterData.getKey().equalsIgnoreCase("Top")) {
                                character.setTop(snapshotCharacterData.getValue(Integer.class));
                            }
                        }
                        character.setSide(snapshotCharacter.getKey());
                        listchCharacters2.add(character);
                        Comparator<Character> Top = new Comparator<Character>() {
                            @Override
                            public int compare(Character t0, Character t1) {
                                return Integer.compare(t0.getTop(),t1.getTop());
                            }
                        };
                        Collections.sort(listchCharacters2,Top);
                        List<Character> top10 = listchCharacters2.stream().limit(10).collect(Collectors.toList());
                        listchCharacters2.clear();
                        listchCharacters2.addAll(top10);
                        listTopAdapter2.notifyDataSetChanged();
                        Log.w("Connect Firebase", character.toString());

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}