package com.example.when2meet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class TimeActivity extends AppCompatActivity {

    TimeAdapter timeadapter1, timeadapter2, timeadapter3, timeadapter4, timeadapter5;
    RecyclerView toggles1, toggles2, toggles3, toggles4, toggles5;
    TextView day1, day2, day3, day4, day5;
    int hour1, hour2, num, i;
    String date1, date2, date3, date4, date5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        day1 = findViewById(R.id.day1);
        day2 = findViewById(R.id.day2);
        day3 = findViewById(R.id.day3);
        day4 = findViewById(R.id.day4);
        day5 = findViewById(R.id.day5);

        Intent intent = getIntent();

        hour1 = intent.getExtras().getInt("start");
        hour2 = intent.getExtras().getInt("finish");
        i = intent.getExtras().getInt("number");
        for(int k=0; k<i; k++){
            if(k==0){
                date1 = intent.getExtras().getString("text1");
                day1.setText(date1);
            }else if(k==1){
                date2 = intent.getExtras().getString("text2");
                day2.setText(date2);
            }else if(k==2){
                date3 = intent.getExtras().getString("text3");
                day3.setText(date3);
            }else if(k==3){
                date4 = intent.getExtras().getString("text4");
                day4.setText(date4);
            }else{
                date5 = intent.getExtras().getString("text5");
                day5.setText(date5);
            }
        }
        num = hour2 - hour1;
        for(int k=0; k<i; k++) {
            if(k==0) {
                toggles1 = findViewById(R.id.toggle_rcv1);
                timeadapter1 = new TimeAdapter();
                toggles1.setAdapter(timeadapter1);
            }else if(k==1) {
                toggles2 = findViewById(R.id.toggle_rcv2);
                timeadapter2 = new TimeAdapter();
                toggles2.setAdapter(timeadapter2);
            }else if(k==2) {
                toggles3 = findViewById(R.id.toggle_rcv3);
                timeadapter3 = new TimeAdapter();
                toggles3.setAdapter(timeadapter3);
            }else if(k==3) {
                toggles4 = findViewById(R.id.toggle_rcv4);
                timeadapter4 = new TimeAdapter();
                toggles4.setAdapter(timeadapter4);
            }else {
                toggles5 = findViewById(R.id.toggle_rcv5);
                timeadapter5 = new TimeAdapter();
                toggles5.setAdapter(timeadapter5);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ArrayList<ToggleItem> dataList = new ArrayList<ToggleItem>();
        for(int i= hour1; i<hour2; i++) {
            ToggleItem item = new ToggleItem(Integer.toString(i), Integer.toString(i+1));
            dataList.add(item);
        }
        System.out.println("here!!!!");
        System.out.println(num);
        System.out.println(dataList);
        for(int k=0; k<i; k++) {
            if (k == 0) {
                timeadapter1.submitList(dataList);
            }else if(k==1){
                timeadapter2.submitList(dataList);
            }else if(k==2){
                timeadapter3.submitList(dataList);
            }else if(k==3){
                timeadapter4.submitList(dataList);
            }else{timeadapter5.submitList(dataList);
            }
        }



    }
}