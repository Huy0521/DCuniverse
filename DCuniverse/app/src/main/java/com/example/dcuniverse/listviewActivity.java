package com.example.dcuniverse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class listviewActivity extends AppCompatActivity {
    ListView lvCharacter;
    TextView txtThum;
    ListcharacterAdapter listcharacterAdapter;
    ArrayList<Character> listchCharacters = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        addView();
        addEvent();
    }
    private void addView() {
        txtThum = findViewById(R.id.txtThum);
        txtThum.setText("Danh sách nhân vật");
        lvCharacter = findViewById(R.id.ListCharacter);
        listcharacterAdapter = new ListcharacterAdapter(listchCharacters);
        lvCharacter.setAdapter(listcharacterAdapter);
        firebaseDatabase = FirebaseDatabase.getInstance("https://dcuniverser-36ae9-default-rtdb.firebaseio.com/");
    }

    private void addEvent() {
        lvCharacter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(listviewActivity.this,listchCharacters.get(position).getName(), Toast.LENGTH_SHORT);
                Intent intentCharacter = new Intent(getBaseContext(),characterActivity.class);
                intentCharacter.putExtra("ch",listchCharacters.get(position));
                startActivity(intentCharacter);
            }
        });
        databaseReference = firebaseDatabase.getReference().child("Character");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Character character;
                for (DataSnapshot snapshotCharacter: snapshot.getChildren())
                {
                    for (DataSnapshot snapshotHero: snapshotCharacter.getChildren())
                    {
                        character = new Character();
                        for (DataSnapshot snapshotCharacterData:snapshotHero.getChildren())
                        {
                            if(snapshotCharacterData.getKey().equalsIgnoreCase("Anh"))
                            {
                                character.setAvatar(snapshotCharacterData.getValue(String.class));
                            }
                            else if(snapshotCharacterData.getKey().equalsIgnoreCase("Ten"))
                            {
                                character.setName(snapshotCharacterData.getValue(String.class));
                            }
                            else if (snapshotCharacterData.getKey().equalsIgnoreCase("Sucmanh"))
                            {
                                character.setPower(snapshotCharacterData.getValue(String.class));
                            }
                            else if(snapshotCharacterData.getKey().equalsIgnoreCase("Dautruyen"))
                            {
                                ArrayList<String> listDautruyen = new ArrayList<>();
                                for (DataSnapshot dautruyen: snapshotCharacterData.getChildren())
                                {
                                    listDautruyen.add(dautruyen.getValue(String.class));
                                }
                                character.setComicin(listDautruyen);
                            }
                            else if(snapshotCharacterData.getKey().equalsIgnoreCase("NguocgocSM"))
                            {
                                character.setSoP(snapshotCharacterData.getValue(String.class));
                            }
                            else if(snapshotCharacterData.getKey().equalsIgnoreCase("Chitiet"))
                            {
                                character.setDetail(snapshotCharacterData.getValue(String.class));
                            }
                        }
                        character.setSide(snapshotCharacter.getKey());
                        listchCharacters.add(character);
                                Collections.sort(listchCharacters, new Comparator<Character>() {
                                    @Override
                                    public int compare(Character t0, Character t1) {
                                        return t0.getName().compareTo(t1.getName());
                                    }
                                });
                        listcharacterAdapter.notifyDataSetChanged();
                        Log.w("Connect Firebase",character.toString());
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getBaseContext(), "Failed to read data", Toast.LENGTH_SHORT).show();
                Log.w("Connect Firebase", "Failed to read data", error.toException());
            }
        });
    }
}