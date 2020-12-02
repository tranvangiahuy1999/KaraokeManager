package com.example.admin.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.model.room;

import java.util.ArrayList;

public class RoomListAdapter extends BaseAdapter {

    private ArrayList<room> roomList;
    private LayoutInflater layoutInflater;
    private Context context;
    ViewHolder holder;

    public RoomListAdapter(Context context, ArrayList<room> roomList) {
        this.roomList = roomList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return roomList.size();
    }

    @Override
    public Object getItem(int position) {
        return roomList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_room, null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.imageRoomState);
            holder.textView = convertView.findViewById(R.id.textViewRoomItem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        room room = roomList.get(position);
        holder.textView.setText(room.getRoomName());
        if(room.getRoomState() == 1){
            holder.imageView.setImageResource(R.drawable.sofa_icon_available);
        } else {
            holder.imageView.setImageResource(R.drawable.sofa_icon_inused);
        }

        return convertView;
    }
}
