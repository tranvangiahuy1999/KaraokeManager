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
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.adapter.RoomListAdapter;
import com.example.admin.myapplication.data_access.DatabaseAccess;
import com.example.admin.myapplication.activity.Enter_Room;
import com.example.admin.myapplication.model.payment;
import com.example.admin.myapplication.model.room;
import com.example.admin.myapplication.payment.CreditCardStrategy;
import com.example.admin.myapplication.payment.PaymentMethod;
import com.example.admin.myapplication.payment.PaypalStrategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class roomap_fragment extends Fragment {
    View viewMap;
    Context mContext;
    GridView gridView;
    ArrayList<room> roomList;
    RoomListAdapter adapter;
    Dialog roomDialog, paymentDialog, payMethodDialog;

    TextView roomName, paymentIdView, customerIdView, timeCreateView, paymentTotalView;
    Button openRoom, payRoom, backBtn, payAccept;

    RadioButton paypalCheck, creditCheck;
    EditText emailTextInput, passTextInput, nameTextInput, cardnumberTextInput, ccvTextInput;
    LinearLayout paypalMethod, creditcardMethod;

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
        databaseAccess.close();
    }

    public void ClickOnGridItem(){
        //click on room item in GridView
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Object o = gridView.getItemAtPosition(position);
                final room room = (com.example.admin.myapplication.model.room) o;
                showRoomOption(room);
                clickButton(room);
            }
        });
    }

    private void showRoomOption(room room){
        roomDialog = new Dialog(mContext);
        roomDialog.setContentView(R.layout.custom_popup);
        addControls();
        roomName.setText(room.getRoomName());
    }

    private void showPayMethod(String paymentID, float amount){
        payMethodDialog = new Dialog(mContext);
        payMethodDialog.setContentView(R.layout.payment_method);

        paypalCheck = payMethodDialog.findViewById(R.id.paypalCheck);
        creditCheck = payMethodDialog.findViewById(R.id.creditCheck);
        emailTextInput = payMethodDialog.findViewById(R.id.emailTextInput);
        passTextInput = payMethodDialog.findViewById(R.id.passTextInput);
        nameTextInput = payMethodDialog.findViewById(R.id.nameTextInput);
        cardnumberTextInput = payMethodDialog.findViewById(R.id.cardnumberTextInput);
        ccvTextInput = payMethodDialog.findViewById(R.id.ccvTextInput);
        payAccept = payMethodDialog.findViewById(R.id.payAccept);
        paypalMethod = payMethodDialog.findViewById(R.id.paypalMethod);
        creditcardMethod = payMethodDialog.findViewById(R.id.creditcardMethod);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        payMethodDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        payMethodDialog.getWindow().setLayout((6 * width)/7, (4 * height)/5);
        payMethodDialog.show();

        paymentMethodOnClickListener(paymentID, amount);
    }

    private void paymentMethodOnClickListener(String paymentID, float amount){
        paypalCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creditCheck.setChecked(false);
                paypalMethod.setVisibility(View.VISIBLE);
                creditcardMethod.setVisibility(View.GONE);
            }
        });

        creditCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paypalCheck.setChecked(false);
                creditcardMethod.setVisibility(View.VISIBLE);
                paypalMethod.setVisibility(View.GONE);
            }
        });

        payAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payMethodDialog.dismiss();
                if(paypalCheck.isChecked()){
                    String email = emailTextInput.getText().toString().trim();
                    String password = passTextInput.getText().toString().trim();

                    if(email.length() > 0 && password.length() > 0){
                        PaymentMethod paymethod = new PaymentMethod(new PaypalStrategy(email, password));
                        paymethod.executeStrategy(amount);
                        showPaymentInfo(paymentID, "Paypal");
                    }
                }
                if(creditCheck.isChecked()){
                    String name = nameTextInput.getText().toString().trim();
                    String cardnumber = cardnumberTextInput.getText().toString().trim();
                    String ccv = ccvTextInput.getText().toString().trim();

                    if(name.length() > 0 && cardnumber.length() > 0 && ccv.length() > 0){
                        PaymentMethod paymethod = new PaymentMethod(new CreditCardStrategy(name, cardnumber, ccv));
                        paymethod.executeStrategy(amount);
                        showPaymentInfo(paymentID, "Credit Card");
                    }
                }
            }
        });
    }

    private void showPaymentInfo(String paymentID, String method){
        paymentDialog = new Dialog(mContext);
        paymentDialog.setContentView(R.layout.payment_form);
        addControls2();

        getPaymentInfo(paymentID, method);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentDialog.dismiss();
            }
        });
    }

    //get payment info from database
    private void getPaymentInfo(String paymentID, String method){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(mContext);
        databaseAccess.open();

        Cursor cursor = databaseAccess.getPaymentDataWithPaymentId(paymentID);
        if(cursor != null && cursor.moveToFirst()){
            Cursor customer_name = databaseAccess.getCustomerById(cursor.getInt(1));

            if(customer_name.getCount()>0 && customer_name.moveToFirst()) {
                paymentIdView.setText("MÃ ĐƠN HÀNG: " + cursor.getString(0));
                customerIdView.setText("TÊN KHÁCH HÀNG: " + customer_name.getString(0));
                timeCreateView.setText("PHƯƠNG THỨC: " + method);
                paymentTotalView.setText("TỔNG THANH TOÁN: " + Float.toString(cursor.getFloat(4)));
            }
        }

        databaseAccess.close();

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
        timeCreateView = paymentDialog.findViewById(R.id.timeCreateView);
        paymentTotalView = paymentDialog.findViewById(R.id.paymentTotalView);
        backBtn = paymentDialog.findViewById(R.id.backBtn);
    }

    private void addControls(){
        roomName = roomDialog.findViewById(R.id.textRoomPop);
        openRoom = roomDialog.findViewById(R.id.openBtn);
        payRoom = roomDialog.findViewById(R.id.payBtn);
    }

    private void clickButton(final room room){
        openRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomDialog.dismiss();
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(mContext);
                databaseAccess.open();

                Cursor room_state = databaseAccess.getRoomState(room.getRoomID());
                if(room_state.moveToFirst() && room_state.getCount() >= 1) {
                    if(room_state.getInt(0) == 1) {
                        //Tạo mã hoá đơn của phòng
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                        String timeIn = format.format(new Date());

                        room.setTimeIn(timeIn);
                        room.setRoomState(2);

                        //Set thời gian I/O và trạng thái phòng
                        boolean res = databaseAccess.updateTimeIn(room.getRoomID(), timeIn);
                        boolean result = databaseAccess.changeRoomState(room.getRoomID(), 2);

                        //Cập nhật hiển thị phòng
                        adapter.notifyDataSetChanged();
                        gridView.setAdapter(adapter);

                        //Tạo chi tiết đặt phòng trên Database

//                    //Khoá phòng và check kết quả
                        if(res && result) {
                            Toast.makeText(mContext, "Đã mở phòng", Toast.LENGTH_SHORT).show();
                        }
                        databaseAccess.close();
                    }
                }

                //Chuyển qua layout chi tiết phòng
                Intent intent = new Intent(mContext, Enter_Room.class);
                intent.putExtra("room", room);
                startActivity(intent);
            }
        });

        payRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomDialog.dismiss();

                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(mContext);
                databaseAccess.open();
                Cursor room_state = databaseAccess.getRoomState(room.getRoomID());
                if(room_state.moveToFirst() && room_state.getCount() > 0){
                    if (room_state.getInt(0) == 1){
                        Toast.makeText(mContext, "Phòng chưa sử dụng", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else if(room_state.getInt(0) == 2) {
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                        String timeOut = format.format(new Date());
                        String paymentID = genId();

                        Cursor roomprice = databaseAccess.getRoomPriceByRoomType(room.getTypeID());
                        Cursor total = databaseAccess.getRoomTotalPayment(room.getRoomID());

                        if(roomprice.getCount()>0 && roomprice.moveToFirst() && total.moveToFirst() && total.getCount() > 0){
                            float roomtotal = roomprice.getFloat(0) + total.getFloat(0);
                            databaseAccess.updateRoomTotal(room.getRoomID(), roomtotal);
                            //Thanh toán

                            //Tạo và lưu hoá đơn
                            boolean res1 = databaseAccess.insertPaymentData(paymentID, 2, 0, timeOut, roomtotal);
                            boolean res2 = databaseAccess.resetRoomTotal(room.getRoomID(), 1, "00:00:00", 0);

                            //Kiểm tra thanh toán
                            if(res1 && res2) {
                                Toast.makeText(mContext, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                            }

                            //Set thời gian I/O và trạng thái phòng
                            room.setTimeIn("00:00:00");
                            room.setRoomState(1);

//                            showPaymentInfo(paymentID);
                            showPayMethod(paymentID, roomtotal);

                            //Cập nhật hiển thị phòng
                            adapter.notifyDataSetChanged();
                            gridView.setAdapter(adapter);
                        }
                    }
                }
                databaseAccess.close();
            }
        });
        roomDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        roomDialog.show();
    }

    private String genId(){
        String genId = new SimpleDateFormat("ddyyyyHHmmss", Locale.getDefault()).format(new Date());
        return genId;
    }

}