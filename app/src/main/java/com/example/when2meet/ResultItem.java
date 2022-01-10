package com.example.when2meet;

public class ResultItem {
    private int number1;
    private int number2;
    private String datetext;
    private String hText1;
    private String hText2;
//    int number1, number2;

    public ResultItem(String dtext,String text1, String text2, Integer int1, Integer int2) {
        datetext = dtext;
        hText1 = text1;
        hText2 = text2;
        number1 = int1;
        number2 = int2;
    }

    public String getDatetext() {
        return datetext;
    }

    public String gethText1() {
        return hText1;
    }

    public String gethText2() {
        return hText2;
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setDatetext(String datetext) {
        this.datetext = datetext;
    }

    public void sethText1(String hText1) {
        this.hText1 = hText1;
    }

    public void sethText2(String hText2) {
        this.hText2 = hText2;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }
}
