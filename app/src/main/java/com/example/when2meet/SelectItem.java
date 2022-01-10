package com.example.when2meet;

public class SelectItem {
    private String appText;
    private String makerText;
    private String scheduleId;


    public SelectItem(String text1, String text2, String text3){
        appText = text1;
        makerText = text2;
        scheduleId = text3;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public String getAppText() {
        return appText;
    }

    public String getMakerText() {
        return makerText;
    }
}
