package com.example.dcuniverse;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListTopAdapter extends BaseAdapter {
    ArrayList<Character> listCharacters;
    public ListTopAdapter(ArrayList<Character> listCharacters) {
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
            itemView = View.inflate(viewGroup.getContext(),R.layout.item2,null);
        }
        else{itemView = view;}
        ImageView imgAnh;
        TextView txtName,txtSide,txtPower,txtTop;

        imgAnh = itemView.findViewById(R.id.imgAva);
        txtName = itemView.findViewById(R.id.txtName);
        txtSide = itemView.findViewById(R.id.txtSide);
        txtPower = itemView.findViewById(R.id.txtPower);
        txtTop = itemView.findViewById(R.id.txtTop);
        Picasso.get().load(listCharacters.get(position).getAvatar()).resize(100,100).centerCrop().into(imgAnh);
        txtName.setText(listCharacters.get(position).getName());
        txtPower.setText(listCharacters.get(position).getPower());
        txtTop.setText(Integer.toString(listCharacters.get(position).getTop()));
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