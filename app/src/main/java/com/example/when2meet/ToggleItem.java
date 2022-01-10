package com.example.when2meet;

public class ToggleItem {
    private String mText1;
    private String mText2;
    private String datetext;
    private Boolean bool1 , bool2;
    public ToggleItem(String dtext,String text1, String text2, Boolean bol1, Boolean bol2) {
        mText1 = text1;
        mText2 = text2;
        datetext = dtext;
        bool1 = bol1;
        bool2 = bol2;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }

    public Boolean getBool1() {
        return bool1;
    }

    public Boolean getBool2() {
        return bool2;
    }

    public String getDatetext() {
        return datetext;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }

    public void setDatetext(String datetext) {
        this.datetext = datetext;
    }

    public void setBool1(Boolean b) {
        System.out.println(bool1);
        this.bool1 = b;
        System.out.println(mText1);
        System.out.println(bool1);
        System.out.println(datetext);
    }

    public void setBool2(Boolean b) {
        System.out.println(bool2);
        this.bool2 = b;
        System.out.println(mText1);
        System.out.println(bool2);
        System.out.println(datetext);
    }
}
