package com.example.when2meet.Retrofit.Models;

import com.google.gson.annotations.SerializedName;

public class Model__PutTimeslot {
    @SerializedName("day")
    private String day;

    @SerializedName("time")
    private String time;

    @SerializedName("available")
    private Boolean available;

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}