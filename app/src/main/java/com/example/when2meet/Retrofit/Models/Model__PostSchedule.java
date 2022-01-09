package com.example.when2meet.Retrofit.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Model__PostSchedule {
    @SerializedName("_id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("passwd")
    private String passwd = "";

    @SerializedName("days")
    private ArrayList<String> days;

    @SerializedName("start_time")
    private String start_time;

    @SerializedName("end_time")
    private String end_time;

    public void setId(String id) {  this.id = id; }

    public String getId() { return id; }

    public String getTitle() {
        return title;
    }

    public String getPasswd() {
        return passwd;
    }

    public ArrayList<String> getDays() {
        return days;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setDays(ArrayList<String> days) {
        this.days = days;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}