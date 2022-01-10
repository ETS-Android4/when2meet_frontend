package com.example.when2meet.Retrofit.Models;

import java.util.List;

public class Model__Timeslot {
    private String _id;
    private String start;
    private List<String> members;

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String get_id() {
        return _id;
    }

    public String getStart() {
        return start;
    }

    public List<String> getMembers() {
        return members;
    }
}
