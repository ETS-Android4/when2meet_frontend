package com.example.when2meet.Retrofit.Models;


import com.google.gson.annotations.SerializedName;

public class Model__Profile {
    @SerializedName("_id")
    private String _id;

    @SerializedName("userId")
    private long userId;

    @SerializedName("name")
    private String name;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("email")
    private String email;

    public void set_id(String _id) { this._id = _id; }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String get_id() { return _id; }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getEmail() {
        return email;
    }
}