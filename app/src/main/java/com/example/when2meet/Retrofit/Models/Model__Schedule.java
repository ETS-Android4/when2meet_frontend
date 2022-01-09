package com.example.when2meet.Retrofit.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Model__Schedule {
    @SerializedName("_id")
    private String _id;

    @SerializedName("title")
    private String title;

    @SerializedName("passwd")
    private String passwd;

    @SerializedName("timeslots")
    private ArrayList<String> timeslots;

    @SerializedName("members")
    private ArrayList<String> members;

    public String get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getPasswd() {
        return passwd;
    }

    public ArrayList<String> getTimeslots() {
        return timeslots;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setTimeslots(ArrayList<String> timeslots) {
        this.timeslots = timeslots;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }
}
