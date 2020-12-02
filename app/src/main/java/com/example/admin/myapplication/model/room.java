package com.example.admin.myapplication.model;

import java.io.Serializable;

public class room implements Serializable {

    private int roomID;
    private String roomName;
    private int roomState;
    private int typeID;
    private String timeIn;
    private float snackMoney;

    public room(int roomID, String roomName, int typeID, int roomState, String timeIn, int snackMoney) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.roomState = roomState;
        this.typeID = typeID;
        this.timeIn = timeIn;
        this.snackMoney = 0;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomState() {
        return roomState;
    }

    public void setRoomState(int roomState) {
        this.roomState = roomState;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public float getSnackMoney() {
        return snackMoney;
    }

    public void setSnackMoney(float snackMoney) {
        this.snackMoney = snackMoney;
    }


}
