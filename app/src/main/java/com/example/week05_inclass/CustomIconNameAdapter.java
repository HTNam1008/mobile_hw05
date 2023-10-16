package com.example.week05_inclass;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomIconNameAdapter extends ArrayAdapter {
    Context context;
    String[] ids;
    Integer[] icons;
    public CustomIconNameAdapter(Context context, int layoutToBeInflated,String[] id,Integer[] icons) {
        super(context,R.layout.custom_row_icon_name,id);
        this.context=context;
        this.ids=id;
        this.icons=icons;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View row=inflater.inflate(R.layout.custom_row_icon_name,null);
        TextView id=(TextView)row.findViewById(R.id.txtId);
        ImageView icon=(ImageView)row.findViewById(R.id.icon);
        id.setText(ids[position]);
        icon.setImageResource(icons[position]);
        return(row);
    }
}
