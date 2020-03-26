package com.example.admin.myapplication.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.adapter.RoomListAdapter;
import com.example.admin.myapplication.database.DatabaseAccess;
import com.example.admin.myapplication.Enter_Room;
import com.example.admin.myapplication.objects.room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Fragment_roomMap extends Fragment {
    View viewMap;
    Context mContext;
    GridView gridView;
    ArrayList<room> roomList;
    RoomListAdapter adapter;
    Dialog roomDialog,paymentDialog;
    float total_payment = 0;

    //Popup room option
    TextView roomName, timeIn;
    Button openRoom, bookRoom, payRoom;

    //Popup payment info
    TextView paymentIdView, customerIdView, bookIdView, timeCreateView, paymentTotalView;
    Button backBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewMap = inflater.inflate(R.layout.activity_fragment_room_map, container, false);
        gridView = viewMap.findViewById(R.id.gridView);
        mContext = container.getContext();

        //Create ArrayList object to store room objects
        roomList = new ArrayList<>();

        viewRoomList();
        ClickOnGridItem();

        return viewMap;
    }


    public void ClickOnGridItem(){
        //click on room item in GridView
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Object o = gridView.getItemAtPosition(position);
                final room room = (com.example.admin.myapplication.objects.room) o;
                showRoomOption(room);
                clickButton(room);
            }
        });
    }

    //Collect room list from database and show it in GridView
    private void viewRoomList(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(mContext);
        databaseAccess.open();
        Cursor cursor = databaseAccess.viewRoomData();

        if(cursor.getCount() == 0){
            Toast.makeText(mContext, "No data to show", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                roomList.add(new room(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),cursor.getInt(3), "00:00:00", 0));
            }
            adapter = new RoomListAdapter(mContext, roomList);
            gridView.setAdapter(adapter);
        }

    }

    private void showRoomOption(final room room){
        roomDialog = new Dialog(mContext);
        roomDialog.setContentView(R.layout.custom_popup);
        addControls();

        roomName.setText(room.getRoomName());
        timeIn.setText("Thời gian vào: " + room.getTimeIn());
    }

    private void showPaymentInfo(String paymentID){
        paymentDialog = new Dialog(mContext);
        paymentDialog.setContentView(R.layout.payment_form);
        addControls2();
        getPaymentInfo(paymentID);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentDialog.dismiss();
            }
        });
    }

    //get payment info from database
    private void getPaymentInfo(String paymentID){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(mContext);
        databaseAccess.open();

        Cursor cursor = databaseAccess.getPaymentDataWithPaymentId(paymentID);

        if(cursor != null && cursor.moveToFirst()) {
            paymentIdView.setText("MÃ ĐƠN HÀNG: " + cursor.getString(0));
            customerIdView.setText("MÃ KHÁCH HÀNG: " + Integer.toString(cursor.getInt(1)));
            bookIdView.setText("MÃ ĐẶT PHÒNG: " + Integer.toString(cursor.getInt(2)));
            timeCreateView.setText("GIỜ XUẤT HOÁ ĐƠN: " + cursor.getString(3));
            paymentTotalView.setText("TỔNG THANH TOÁN: " + Float.toString(cursor.getFloat(4)));
        }

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        paymentDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        paymentDialog.getWindow().setLayout((6 * width)/7, (4 * height)/5);
        paymentDialog.show();
    }

    private void addControls2(){
        paymentIdView = paymentDialog.findViewById(R.id.paymentIdView);
        customerIdView = paymentDialog.findViewById(R.id.customerIdView);
        bookIdView = paymentDialog.findViewById(R.id.bookIdView);
        timeCreateView = paymentDialog.findViewById(R.id.timeCreateView);
        paymentTotalView = paymentDialog.findViewById(R.id.paymentTotalView);
        backBtn = paymentDialog.findViewById(R.id.backBtn);
    }

    private void addControls(){
        roomName = roomDialog.findViewById(R.id.textRoomPop);
        timeIn = roomDialog.findViewById(R.id.textTimeIn);
        openRoom = roomDialog.findViewById(R.id.openBtn);
        bookRoom = roomDialog.findViewById(R.id.bookBtn);
        payRoom = roomDialog.findViewById(R.id.payBtn);
    }

    private void clickButton(final room room){
        openRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomDialog.dismiss();

                if(room.getRoomState() == 1 ) {
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                    String timeIn = format.format(new Date());

                    //Tạo mã hoá đơn của phòng

                    //Set thời gian I/O và trạng thái phòng
                    room.setTimeIn(timeIn);
                    room.setRoomState(2);

                    //Cập nhật hiển thị phòng
                    adapter.notifyDataSetChanged();
                    gridView.setAdapter(adapter);

                    //Tạo chi tiết đặt phòng trên Database
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(mContext);
                    databaseAccess.open();
                    boolean cursor = databaseAccess.insertReservationRoom(5 ,room.getRoomID(), room.getTimeIn(), "00:00:00", 0, room);

                    //Khoá phòng và check kết quả
                    boolean cursor1 = databaseAccess.changeRoomState(room.getRoomID(),room.getRoomState());

                    if (cursor && cursor1) {
                        Toast.makeText(mContext, "Mở phòng thành công", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(mContext, "Mở phòng thất bại", Toast.LENGTH_SHORT).show();
                    }

                }

                //Chuyển qua layout chi tiết phòng
                Intent intent = new Intent(mContext, Enter_Room.class);
                intent.putExtra("room", room);
                startActivity(intent);
            }
        });

        bookRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        payRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomDialog.dismiss();

                if (room.getRoomState() == 1){
                    Toast.makeText(mContext, "Phòng chưa sử dụng", Toast.LENGTH_SHORT).show();
                }

                if(room.getRoomState() == 2) {
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                    String timeOut = format.format(new Date());
                    String paymentID = genId();

                    //Thanh toán
                    Date time1, time2;

                    try {
                        time1 = format.parse(room.getTimeIn());
                        time2 = format.parse(timeOut);
                        long diff = time2.getTime() - time1.getTime();
                        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(mContext);

                        databaseAccess.open();
                        Cursor cursor = databaseAccess.getRoomMoney(room.getRoomID());
                        if (cursor != null && cursor.moveToFirst()) {
                            total_payment = ((diff / 1000) / 60) * (cursor.getFloat(0) / 60) + room.getSnackMoney();
                            Log.d("BBB",Float.toString(room.getSnackMoney()));
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //Tạo và lưu hoá đơn

                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(mContext);
                    databaseAccess.open();

                    boolean cursor = databaseAccess.insertReservationRoom(5, room.getRoomID(), room.getTimeIn(), timeOut, total_payment, room);

                    boolean cursor2 = databaseAccess.insertPaymentData(paymentID, 5, 0, timeOut, total_payment);

                    boolean cursor1 = databaseAccess.changeRoomState(room.getRoomID(),room.getRoomState());

                    //Kiểm tra thanh toán
                    if(cursor && cursor1 && cursor2){
                        Toast.makeText(mContext, "Đóng phòng thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, "Đóng phòng thất bại", Toast.LENGTH_SHORT).show();
                    }

                    //Set thời gian I/O và trạng thái phòng
                    room.setTimeIn("00:00:00");
                    room.setRoomState(1);
                    room.setSnackMoney(0);

                    showPaymentInfo(paymentID);

//                    Intent intent = new Intent(mContext, Payment_Activity.class);
//                    intent.putExtra("payment_id", paymentID);
//                    startActivity(intent);

                    //Cập nhật hiển thị phòng
                    adapter.notifyDataSetChanged();
                    gridView.setAdapter(adapter);
                }
            }
        });
        roomDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        roomDialog.show();
    }

    private String genId(){
        String genId = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault()).format(new Date());
        return genId;
    }

}