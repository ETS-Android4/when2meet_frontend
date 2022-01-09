package com.example.when2meet;

public class SelectItem {
    private String appText;
    private String makerText;


    public SelectItem(String text1, String text2){
        appText = text1;
        makerText = text2;
    }


    public String getAppText() {
        return appText;
    }

    public String getMakerText() {
        return makerText;
    }
}
