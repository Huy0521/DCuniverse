package com.example.dcuniverse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridviewAdapter extends BaseAdapter {

    String[] tenlogo;
    int[] hinhlogo;

    public GridviewAdapter( String[] tenlogo, int[] hinhlogo) {

        this.tenlogo = tenlogo;
        this.hinhlogo = hinhlogo;
    }

    @Override
    public int getCount() {
        return tenlogo.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int postion, View view, ViewGroup viewGroup) {
        View gridView;
        if(view==null)
        {
            gridView = View.inflate(viewGroup.getContext(),R.layout.gridview,null);
        }
        else{gridView = view;}
        TextView textView = gridView.findViewById(R.id.gridtext);
        ImageView imageView = gridView.findViewById(R.id.Gridlogo);
        textView.setText(tenlogo[postion]);
        imageView.setImageResource(hinhlogo[postion]);
        return gridView;
    }
}
