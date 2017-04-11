package com.dhcs.admin.iiitdapp;

/**
 * Created by Admin on 4/11/2017.
 */

public class ScheduleItem {
    private String classTime,classType,classRoom;

    public ScheduleItem(String classTime, String classType, String classRoom) {
        this.classTime = classTime;
        this.classType = classType;
        this.classRoom = classRoom;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }
}
