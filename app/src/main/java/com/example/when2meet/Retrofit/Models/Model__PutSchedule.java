package com.example.when2meet.Retrofit.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Model__PutSchedule {
    private String scheduleId;

    @SerializedName("userId")
    private String userId;

    @SerializedName("contents")
    private ArrayList<Model__PutTimeslot> contents;

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleId() {
        return scheduleId;
    }


    public String getUserId() {
        return userId;
    }

    public ArrayList<Model__PutTimeslot> getContents() {
        return contents;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setContents(ArrayList<Model__PutTimeslot> contents) {
        this.contents = contents;
    }
}