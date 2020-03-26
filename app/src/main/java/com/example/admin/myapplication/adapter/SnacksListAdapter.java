package com.example.admin.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.objects.snacks;

import java.util.ArrayList;

public class SnacksListAdapter extends BaseAdapter {
    ArrayList<snacks> snacksList;
    private LayoutInflater layoutInflater;
    Context context;

    public SnacksListAdapter(Context aContext,  ArrayList<snacks> snacksList) {
        this.context = aContext;
        this.snacksList = snacksList;
        layoutInflater = LayoutInflater.from(aContext);

    }

    @Override
    public int getCount() {
        return snacksList.size();
    }

    @Override
    public Object getItem(int position) {
        return snacksList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_snacklist_recyclerview, null);
            holder = new ViewHolder();
            holder.snackIdView = convertView.findViewById(R.id.snackIdView);
            holder.snackNameView = convertView.findViewById(R.id.snackNameView);
            holder.snackPriceView = convertView.findViewById(R.id.snackPriceView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final snacks snack = snacksList.get(position);
        holder.snackIdView.setText(Integer.toString(snack.getSnack_id()));
        holder.snackNameView.setText(snack.getSnack_name());
        holder.snackPriceView.setText(Float.toString(snack.getSnack_price()));

        return convertView;
    }
    static class ViewHolder {
        TextView snackIdView, snackNameView, snackPriceView;
    }
}
