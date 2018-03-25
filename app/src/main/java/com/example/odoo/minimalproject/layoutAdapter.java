package com.example.odoo.minimalproject;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class layoutAdapter extends ArrayAdapter {

    private ArrayList list;
    private Activity act;

    public layoutAdapter(Activity context, ArrayList objects) {
        super(context, R.layout.item_layout, objects);
        this.list = objects;
        this.act = context;
    }

    static class ViewHolder {
        protected ImageView icon;
        protected TextView nama;
        protected TextView password;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = act.getLayoutInflater();
            view = inflater.inflate(R.layout.item_layout, null);

            ViewHolder holder = new ViewHolder();
            holder.icon = view.findViewById(R.id.item_icon);
            holder.nama = view.findViewById(R.id.item_nama);
            holder.password = view.findViewById(R.id.item_password);
            view.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        mhs maha = (mhs) list.get(position);

        holder.icon.setImageResource(R.drawable.andro_icon);
        holder.nama.setText(maha.getNama());
        holder.password.setText(maha.getPassword());

        return view;
    }
}