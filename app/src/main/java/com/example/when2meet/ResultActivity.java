package com.example.when2meet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.when2meet.Retrofit.CallRetrofit;
import com.example.when2meet.Retrofit.Models.Model__Schedule;
import com.example.when2meet.Retrofit.Models.Model__Timeslot;
import com.example.when2meet.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    int maxPeople;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        userId = intent.getExtras().getLong("userId");
        appointName = intent.getExtras().getString("name");
        hour1 = intent.getExtras().getInt("start");
        hour2 = intent.getExtras().getInt("finish");
        dayi = intent.getExtras().getInt("number");
        scheduleId = intent.getExtras().getString("scheduleId");
        maxPeople = intent.getExtras().getInt("maxPeople");
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
                resultAdapter1 = new ResultAdapter(maxPeople);
                result1.setAdapter(resultAdapter1);
            }else if(k==1) {
                result2 = findViewById(R.id.result_rcv2);
                resultAdapter2 = new ResultAdapter(maxPeople);
                result2.setAdapter(resultAdapter2);
            }else if(k==2) {
                result3 = findViewById(R.id.result_rcv3);
                resultAdapter3 = new ResultAdapter(maxPeople);
                result3.setAdapter(resultAdapter3);
            }else if(k==3) {
                result4 = findViewById(R.id.result_rcv4);
                resultAdapter4 = new ResultAdapter(maxPeople);
                result4.setAdapter(resultAdapter4);
            }else {
                result5 = findViewById(R.id.result_rcv5);
                resultAdapter5 = new ResultAdapter(maxPeople);
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

        CallRetrofit retrofitClient = new CallRetrofit();
        ArrayList<Model__Timeslot> timeslots = new ArrayList<Model__Timeslot>();
//        retrofitClient.getScheduleWithIdFunc(scheduleId, schedules);


        Call<Model__Schedule> call = RetrofitClient.getApiService().getScheduleWithId(scheduleId);
        call.enqueue(new Callback<Model__Schedule>() {
            @Override
            public void onResponse(Call<Model__Schedule> call, Response<Model__Schedule> response) {
                if (!response.isSuccessful()) {
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                Model__Schedule schedule = response.body();
                Integer total_members = schedule.getMembers().size();

                ArrayList<String> timeslotIds = schedule.getTimeslots();
                for(String timeslotId: timeslotIds) {
                    retrofitClient.getTimeslotWithIdFunc(timeslotId, timeslots);
                }

                new Handler().postDelayed(new Runnable()
                {
                    int number1_1, number1_2, number2_1, number2_2, number3_1, number3_2, number4_1, number4_2, number5_1, number5_2;
                    @Override
                    public void run() {
                        for (int i = hour1; i < hour2; i++) {
                            for(Model__Timeslot t: timeslots){
                                if (t.getStart().contains((date1 + "T" + String.format("%02d", i) + ":00:00.000"))) {
                                    number1_1 = t.getMembers().size();
                                }
                                if (t.getStart().contains(date1 + "T" + String.format("%02d", i) + ":30:00.000")) {
                                    number1_2 = t.getMembers().size();
                                }
                            }
                            ResultItem item1 = new ResultItem(date1, Integer.toString(i), Integer.toString(i + 1), number1_1, number1_2);
                            dataList1.add(item1);
                            for(Model__Timeslot t: timeslots){
                                if (t.getStart().contains(date2 + "T" + String.format("%02d", i) + ":00:00.000")) {
                                    number2_1 = t.getMembers().size();
                                }
                                if (t.getStart().contains(date2 + "T" + String.format("%02d", i) + ":30:00.000")) {
                                    number2_2 = t.getMembers().size();
                                }
                            }
                            ResultItem item2 = new ResultItem(date2, Integer.toString(i), Integer.toString(i + 1), number2_1, number2_2);
                            dataList2.add(item2);
                            for(Model__Timeslot t: timeslots){
                                if (t.getStart().contains(date3 + "T" + String.format("%02d", i) + ":00:00.000")) {
                                    number3_1 = t.getMembers().size();
                                }
                                if (t.getStart().contains(date3 + "T" + String.format("%02d", i) + ":30:00.000")) {
                                    number3_2 = t.getMembers().size();
                                }
                            }
                            ResultItem item3 = new ResultItem(date3, Integer.toString(i), Integer.toString(i + 1), number3_1, number3_2);
                            dataList3.add(item3);
                            for(Model__Timeslot t: timeslots){
                                if (t.getStart().contains(date4 + "T" + String.format("%02d", i) + ":00:00.000")) {
                                    number4_1 = t.getMembers().size();
                                }
                                if (t.getStart().contains(date4 + "T" + String.format("%02d", i) + ":30:00.000")) {
                                    number4_2 = t.getMembers().size();
                                }
                            }
                            ResultItem item4 = new ResultItem(date4, Integer.toString(i), Integer.toString(i + 1), number4_1, number4_2);
                            dataList4.add(item4);
                            for(Model__Timeslot t: timeslots){
                                if (t.getStart().contains(date5 + "T" + String.format("%02d", i) + ":00:00.000")) {
                                    number5_1 = t.getMembers().size();
                                }
                                if (t.getStart().contains(date5 + "T" + String.format("%02d", i) + ":30:00.000")) {
                                    number5_2 = t.getMembers().size();
                                }
                            }
                            ResultItem item5 = new ResultItem(date5, Integer.toString(i), Integer.toString(i + 1), number5_1, number5_2);
                            dataList5.add(item5);
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
                        timeslots.clear();
                    }
                }, 300);
            }

            @Override
            public void onFailure(Call<Model__Schedule> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}