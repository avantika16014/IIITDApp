package com.dhcs.admin.iiitdapp;

/**
 * Created by Admin on 4/7/2017.
 */

public class MenuTask {
    String mTitle;
    String mDetail;
    int mId;

    public MenuTask(String title, String detail, int mId){
        this.mTitle=title;
        this.mDetail=detail;
        this.mId=mId;
    }

    public int getmId() {
        return mId;
    }

    public String getDetail() {
        return mDetail;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setDetail(String detail) {
        this.mDetail = detail;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }
}
