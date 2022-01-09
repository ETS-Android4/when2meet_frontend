package com.example.when2meet.Retrofit;


import com.google.gson.annotations.SerializedName;

public class Model__Profile {
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

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public long getUserId() {
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

    public Boolean getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    private long userId;
    private String name;
    private String imageUrl;
    private String email;
    private Boolean gender;
    private Integer age;

//    @Override
//    public String toString() {
//        return "RepoCheckAlready{" +
//                "phone='" + phone + '\'' +
//                ", message='" + message + '\'' +
//                '}';
//    }
}