package com.dhcs.admin.iiitdapp;

/**
 * Created by Admin on 4/8/2017.
 */
public class Directory {

    private String rank;
    private String name;
    private String roomno;
    private String extension;
    private String email;

    public Directory(String rank, String name,String roomno,String extension, String email) {
        this.rank = rank;
        this.name = name;
        this.email = email;
        this.roomno= roomno;
        this.extension= extension;
    }

    public String getRank() {
        return this.rank;
    }

    public String getname() {
        return this.name;
    }

    public String getemail() {
        return this.email;
    }

    public String getroomno(){ return this.roomno;}
    public String getextension(){ return this.extension;}
}
