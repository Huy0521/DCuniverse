package com.example.dcuniverse;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListXMLAdapter extends BaseAdapter {
    ArrayList<XMLitem> listNews;


    public ListXMLAdapter(ArrayList<XMLitem> listNews) {
        this.listNews = listNews;
    }

    @Override
    public int getCount() {
        return this.listNews.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listNews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView;
        if (convertView == null) {
            itemView = convertView = View.inflate(parent.getContext(), R.layout.item3, null);
        } else {
            itemView = convertView;
        }

        TextView txtTitle = itemView.findViewById(R.id.txtTitle);
        TextView txtPubDate = itemView.findViewById(R.id.txtDate);
        ImageView imgNews = itemView.findViewById(R.id.imgXML);
        String imgUrl = listNews.get(position).getEnclosure().replace("http","https");
        Picasso.get().load(imgUrl).resize(100, 100).centerCrop().into(imgNews);

        txtTitle.setText(listNews.get(position).getTitle());
        txtPubDate.setText(listNews.get(position).getDate());

        return itemView;
    }

    public String getImgSrc(String data) {
        int firstPosition = data.indexOf("src=\"");
        String imgUrl = "";
        for (int i = firstPosition + 5; i < data.length(); i++) {
            if (data.charAt(i) == '"') {
                break;
            }
            imgUrl += data.charAt(i);
        }

        return imgUrl;
    }
}
