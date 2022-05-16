package com.example.dcuniverse;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListcharacterAdapter extends BaseAdapter {
    ArrayList<Character> listCharacters;

    public ListcharacterAdapter(ArrayList<Character> listCharacters) {
        this.listCharacters = listCharacters;
    }

    @Override
    public int getCount() {
        return listCharacters.size();
    }

    @Override
    public Object getItem(int position) {
        return listCharacters.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemView;
        if(view==null)
        {
            itemView = View.inflate(viewGroup.getContext(),R.layout.item,null);
        }
        else{itemView = view;}
        ImageView imgAnh;
        TextView txtName,txtSide,txtComicin;
        String dautruyen="";
        imgAnh = itemView.findViewById(R.id.imgAva);
        txtName = itemView.findViewById(R.id.txtName);
        txtSide = itemView.findViewById(R.id.txtSide);
        txtComicin = itemView.findViewById(R.id.txtIn);
        Picasso.get().load(listCharacters.get(position).getAvatar()).resize(100,100).centerCrop().into(imgAnh);
        txtName.setText(listCharacters.get(position).getName());
        ArrayList<String>listdautruyen = listCharacters.get(position).getComicin();
        for (int i=0;i<listdautruyen.size();i++)
        {
            dautruyen += listdautruyen.get(i)+", ";
        }
        txtComicin.setText(dautruyen);
        String phe = listCharacters.get(position).getSide();
        if(phe.equalsIgnoreCase("Hero"))
        {
            txtSide.setBackgroundColor(viewGroup.getResources().getColor(R.color.Hero));
        }
        else if(phe.equalsIgnoreCase("Villian"))
        {
            txtSide.setBackgroundColor(viewGroup.getResources().getColor(R.color.purple_700));
        }
        else if(phe.equalsIgnoreCase("Above All"))
        {
            txtSide.setBackgroundColor(viewGroup.getResources().getColor(R.color.God));
        }
        else if(phe.equalsIgnoreCase("Maverl Universe"))
        {
            txtSide.setBackgroundColor(viewGroup.getResources().getColor(R.color.teal_200));
        }
        txtSide.setText(phe);
        return itemView;
    }
}
