package com.example.admin.myapplication.data_access;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.admin.myapplication.model.room;

public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor cursor = null;

    //private contructor so that creation from out side the class is avoided
    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    //return instance of database
    public static DatabaseAccess getInstance(Context context){

        if(instance == null){
            instance = new DatabaseAccess(context);
        }

        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
        this.db = openHelper.getReadableDatabase();
    }

    public void close(){
        if(this.db != null){
            this.db.close();
        }
    }

    public boolean checkAccount(String username, String password){
        cursor = db.rawQuery("select * from account where username = '" + username + "' and password = '" + password + "'",new String[]{});
        if(cursor.getCount()>0){
            return true;
        } else {
            return false;
        }
    }

    public boolean insertAccount(String name, String pass){
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", name);
        contentValues.put("password",pass);
        long result = db.insert("account", null, contentValues);
        if(result == -1){
            return false;
        } else{
            return true;
        }
    }

    public boolean insertCustomer(String name, String phone, String address){
        ContentValues contentValues = new ContentValues();
        contentValues.put("customer_name", name);
        contentValues.put("customer_phone",phone);
        contentValues.put("customer_address",address);

        long result = db.insert("customer", null, contentValues);
        if(result == -1){
            return false;
        } else{
            return true;
        }
    }

    public Cursor viewRoomData(){
        cursor = db.rawQuery("select * from room", null);
        return cursor;
    }

    public Cursor getRoomTimeIn(int roomId){
        cursor = db.rawQuery("select timeIn from room where room_id='" + roomId + "'", null);
        return cursor;
    }

    public Cursor getCustomerById(int id) {
        cursor = db.rawQuery("select customer_name from customer where customer_id = '" + id + "'", null);
        return cursor;
    }

    public Cursor viewSnackData(){
        cursor = db.rawQuery("select * from snacks", null);
        return cursor;
    }

    public Cursor getRoomState(int roomId) {
        cursor = db.rawQuery("select room_state from room where room_id='" + roomId + "'", null);
        return cursor;
    }

    public Cursor getRoomTotalPayment(int roomId){
        cursor = db.rawQuery("select total from room where room_id='" + roomId + "'", null);
        return cursor;
    }

    public boolean updateTimeIn(int room_id, String timeIn){
        ContentValues contentValues = new ContentValues();
        contentValues.put("timeIn", timeIn);
        long result = db.update("room", contentValues, "room_id = '" + room_id + "'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor getRoomPriceByRoomType(int room_type) {
        cursor = db.rawQuery("select type_price from room_type where type_id='" + room_type + "'", null);
        return cursor;
    }

    public boolean updateRoomTotal(int room_id, float total){
        ContentValues contentValues = new ContentValues();
        contentValues.put("total", total);
        long result = db.update("room", contentValues, "room_id = '" + room_id + "'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean resetRoomTotal(int room_id, int state, String timeIn, float total){
        ContentValues contentValues = new ContentValues();
        contentValues.put("room_state", state);
        contentValues.put("timeIn", timeIn);
        contentValues.put("total", total);
        long result = db.update("room", contentValues, "room_id = '" + room_id + "'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    //Đổi trạng thái phòng
    public boolean changeRoomState(int room_id, int room_state){
        ContentValues contentValues = new ContentValues();
        contentValues.put("room_state", room_state);
        long result = db.update("room", contentValues, "room_id = '" + room_id + "'", null);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    //Lưu hoá đơn thanh toán
    public boolean insertPaymentData(String payment_id, int customer_id, int book_id, String time_create, float payment_total){
        ContentValues contentValues = new ContentValues();

            contentValues.put("payment_id", payment_id);
            contentValues.put("customer_id", customer_id);
            contentValues.put("book_id", book_id);
            contentValues.put("time_create", time_create);
            contentValues.put("payment_total", payment_total);
        long result = db.insert("payment", null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    //Lấy hoá đơn dựa trên payment_id
    public Cursor getPaymentDataWithPaymentId(String payment_id){
        cursor = db.rawQuery("select payment_id, customer_id, book_id, time_create, payment_total from payment where payment_id = '" + payment_id + "'", null);
        return cursor;
    }

    //Lấy hoá đơn thanh toán
    public Cursor getPaymentData(){
        cursor = db.rawQuery("select * from payment", null);
        return cursor;
    }

    //Lấy tiền phòng dựa trên ID room type theo phòng
    public Cursor getRoomMoney(int room_id){
        cursor = db.rawQuery("select type_price from room, room_type where room_id = '" + room_id + "' and room.type_id = room_type.type_id", null);
        return cursor;
    }

    //Lấy tiền sản phẩm dựa trên ID sản phẩm
    public Cursor getSnackMoney(int snack_id){
        cursor = db.rawQuery("select snack_price from snacks where snack_id = '" + snack_id + "'", null);
        return cursor;
    }

    //Lưu hoá đơn sản phẩm
    public boolean insertOrderData(String payment_id, int snack_id, int quantity, float pay_order){
        ContentValues contentValues = new ContentValues();
        contentValues.put("payment_id", payment_id);
        contentValues.put("snack_id", snack_id);
        contentValues.put("quantity", quantity);
        contentValues.put("pay_order", pay_order);
        long result = db.insert("order_snack", null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    //Thống kê sản phẩm đã order
    public Cursor viewOrderData(String payment_id){
        cursor = db.rawQuery("select order_snack.snack_id,snack_name,SUM(quantity),SUM(pay_order) from snacks,order_snack where payment_id = '" + payment_id + "' and snacks.snack_id = order_snack.snack_id GROUP BY order_snack.snack_id,snack_name", null);
        return cursor;
    }

    //Tạo thông tin đặt phòng - cập nhật thời gian rời phòng và thành tiền khi đóng phòng
    public boolean insertReservationRoom(int customer_id, int room_id, String time_in, String time_out, float pay, room room){
        ContentValues contentValues = new ContentValues();

        if (room.getRoomState() == 1) {
            contentValues.put("customer_id", customer_id);
            contentValues.put("room_id", room_id);
            contentValues.put("time_in", time_in);
        } else {
            contentValues.put("time_out", time_out);
            contentValues.put("pay", pay);
        }
        long result = db.insert("reservation", null, contentValues);
        if(result == -1){
            return false;
        } else{
            return true;
        }
    }
}