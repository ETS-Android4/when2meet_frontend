package com.example.when2meet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    ResultAdapter resultAdapter1, resultAdapter2, resultAdapter3, resultAdapter4, resultAdapter5;
    RecyclerView result1, result2, result3, result4, result5;
    TextView day1, day2, day3, day4, day5;
    int hour1, hour2, num, dayi;
    String appointName, date1, date2, date3, date4, date5;
    Button uploadButton;
    List<ResultItem> jsondata;
    Long userId;
    String scheduleId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        userId = intent.getExtras().getLong("userId");
        System.out.println("userId!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(userId);
        appointName = intent.getExtras().getString("name");
        hour1 = intent.getExtras().getInt("start");
        hour2 = intent.getExtras().getInt("finish");
        dayi = intent.getExtras().getInt("number");
        scheduleId = intent.getExtras().getString("scheduleId");
        num = hour2 - hour1;
        day1 = findViewById(R.id.day1);
        day2 = findViewById(R.id.day2);
        day3 = findViewById(R.id.day3);
        day4 = findViewById(R.id.day4);
        day5 = findViewById(R.id.day5);

        for(int k=0; k<dayi; k++){
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
        for(int k=0; k<dayi; k++) {
            if(k==0) {
                result1 = findViewById(R.id.result_rcv1);
                resultAdapter1 = new ResultAdapter();
                result1.setAdapter(resultAdapter1);
            }else if(k==1) {
                result2 = findViewById(R.id.result_rcv2);
                resultAdapter2 = new ResultAdapter();
                result2.setAdapter(resultAdapter2);
            }else if(k==2) {
                result3 = findViewById(R.id.result_rcv3);
                resultAdapter3 = new ResultAdapter();
                result3.setAdapter(resultAdapter3);
            }else if(k==3) {
                result4 = findViewById(R.id.result_rcv4);
                resultAdapter4 = new ResultAdapter();
                result4.setAdapter(resultAdapter4);
            }else {
                result5 = findViewById(R.id.result_rcv5);
                resultAdapter5 = new ResultAdapter();
                result5.setAdapter(resultAdapter5);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ArrayList<ResultItem> dataList1 = new ArrayList<ResultItem>();
        ArrayList<ResultItem> dataList2 = new ArrayList<ResultItem>();
        ArrayList<ResultItem> dataList3 = new ArrayList<ResultItem>();
        ArrayList<ResultItem> dataList4 = new ArrayList<ResultItem>();
        ArrayList<ResultItem> dataList5 = new ArrayList<ResultItem>();

        for (int i = hour1; i < hour2; i++) {
            ResultItem item1 = new ResultItem(date1, Integer.toString(i), Integer.toString(i + 1), 1, 2);
            dataList1.add(item1);
            ResultItem item2 = new ResultItem(date2, Integer.toString(i), Integer.toString(i + 1), 1, 2);
            dataList2.add(item2);
            ResultItem item3 = new ResultItem(date3, Integer.toString(i), Integer.toString(i + 1), 1, 2);
            dataList3.add(item3);
            ResultItem item4 = new ResultItem(date4, Integer.toString(i), Integer.toString(i + 1), 1, 2);
            dataList4.add(item4);
            ResultItem item5 = new ResultItem(date5, Integer.toString(i), Integer.toString(i + 1), 1, 2);
            dataList5.add(item5);
        }
        System.out.println("here!!!!");
        System.out.println(num);
        System.out.println(dataList1);
        for (int k = 0; k < dayi; k++) {
            if (k == 0) {
                resultAdapter1.submitList(dataList1);
            } else if (k == 1) {
                resultAdapter2.submitList(dataList2);
            } else if (k == 2) {
                resultAdapter3.submitList(dataList3);
            } else if (k == 3) {
                resultAdapter4.submitList(dataList4);
            } else {
                resultAdapter5.submitList(dataList5);
            }
        }
    }
}