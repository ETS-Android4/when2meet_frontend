package com.example.when2meet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.when2meet.Retrofit.CallRetrofit;
import com.example.when2meet.Retrofit.Models.Model__PutSchedule;
import com.example.when2meet.Retrofit.Models.Model__PutTimeslot;
import com.example.when2meet.Retrofit.Models.Model__Schedule;
import com.example.when2meet.Retrofit.RetrofitClient;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeActivity extends AppCompatActivity {

    TimeAdapter timeadapter1, timeadapter2, timeadapter3, timeadapter4, timeadapter5;
    RecyclerView toggles1, toggles2, toggles3, toggles4, toggles5;
    TextView day1, day2, day3, day4, day5;
    int hour1, hour2, num, dayi;
    String appointName, date1, date2, date3, date4, date5;
    Button uploadButton;
    List<ToggleItem> jsondata;
    Long userId;
    String scheduleId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        Intent intent = getIntent();
        userId = intent.getExtras().getLong("userId");
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
        uploadButton = findViewById(R.id.uploadbutton);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model__PutSchedule schedule = new Model__PutSchedule();
                ArrayList<Model__PutTimeslot> contents = new ArrayList<Model__PutTimeslot>();
                schedule.setScheduleId(scheduleId);
                schedule.setUserId(Long.toString(userId));
                for(int d=1; d<=dayi; d++) {
                    for (int h=0; h<num; h++) {
                        if(d==1) {
                            jsondata = timeadapter1.getList();
                            Model__PutTimeslot addcontent00 = new Model__PutTimeslot();
                            addcontent00.setDay(date1);
                            addcontent00.setTime(Integer.toString(hour1+h)+":00");
                            addcontent00.setAvailable(jsondata.get(h).getBool1());
                            contents.add(addcontent00);
                            Model__PutTimeslot addcontent30 = new Model__PutTimeslot();
                            addcontent30.setDay(date1);
                            addcontent30.setTime(Integer.toString(hour1+h)+":30");
                            addcontent30.setAvailable(jsondata.get(h).getBool2());
                            contents.add(addcontent30);
                        }else if(d==2) {
                            jsondata = timeadapter2.getList();
                            Model__PutTimeslot addcontent00 = new Model__PutTimeslot();
                            addcontent00.setDay(date2);
                            addcontent00.setTime(Integer.toString(hour1+h)+":00");
                            addcontent00.setAvailable(jsondata.get(h).getBool1());
                            contents.add(addcontent00);
                            Model__PutTimeslot addcontent30 = new Model__PutTimeslot();
                            addcontent30.setDay(date2);
                            addcontent30.setTime(Integer.toString(hour1+h)+":30");
                            addcontent30.setAvailable(jsondata.get(h).getBool2());
                            contents.add(addcontent30);
                        }else if(d==3) {
                            jsondata = timeadapter3.getList();
                            Model__PutTimeslot addcontent00 = new Model__PutTimeslot();
                            addcontent00.setDay(date3);
                            addcontent00.setTime(Integer.toString(hour1+h)+":00");
                            addcontent00.setAvailable(jsondata.get(h).getBool1());
                            contents.add(addcontent00);
                            Model__PutTimeslot addcontent30 = new Model__PutTimeslot();
                            addcontent30.setDay(date3);
                            addcontent30.setTime(Integer.toString(hour1+h)+":30");
                            addcontent30.setAvailable(jsondata.get(h).getBool2());
                            contents.add(addcontent30);
                        }else if(d==4) {
                            jsondata = timeadapter4.getList();
                            Model__PutTimeslot addcontent00 = new Model__PutTimeslot();
                            addcontent00.setDay(date4);
                            addcontent00.setTime(Integer.toString(hour1+h)+":00");
                            addcontent00.setAvailable(jsondata.get(h).getBool1());
                            contents.add(addcontent00);
                            Model__PutTimeslot addcontent30 = new Model__PutTimeslot();
                            addcontent30.setDay(date4);
                            addcontent30.setTime(Integer.toString(hour1+h)+":30");
                            addcontent30.setAvailable(jsondata.get(h).getBool2());
                            contents.add(addcontent30);
                        }else {
                            jsondata = timeadapter5.getList();
                            Model__PutTimeslot addcontent00 = new Model__PutTimeslot();
                            addcontent00.setDay(date5);
                            addcontent00.setTime(Integer.toString(hour1+h)+":00");
                            addcontent00.setAvailable(jsondata.get(h).getBool1());
                            contents.add(addcontent00);
                            Model__PutTimeslot addcontent30 = new Model__PutTimeslot();
                            addcontent30.setDay(date5);
                            addcontent30.setTime(Integer.toString(hour1+h)+":30");
                            addcontent30.setAvailable(jsondata.get(h).getBool2());
                            contents.add(addcontent30);
                        }
                    }
                }
                for(Model__PutTimeslot ts: contents) {
                    Log.e("", "");
                    Log.e("day", ts.getDay());
                    Log.e("time", ts.getTime());
                    Log.e("available", String.valueOf(ts.getAvailable()));
                    Log.e("", "");
                }
                schedule.setContents(contents);

                Call<Model__PutSchedule> call = RetrofitClient.getApiService().putToSchedule(schedule.getScheduleId(), schedule);
                call.enqueue(new Callback<Model__PutSchedule>() {
                    @Override
                    public void onResponse(Call<Model__PutSchedule> call, Response<Model__PutSchedule> response) {
                        if (!response.isSuccessful()) {
                            Log.e("연결이 비정상적 : ", "error code : " + response.code());
                            return;
                        }
                    }

                    @Override
                    public void onFailure(Call<Model__PutSchedule> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

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
        ArrayList<ToggleItem> dataList1 = new ArrayList<ToggleItem>();
        ArrayList<ToggleItem> dataList2 = new ArrayList<ToggleItem>();
        ArrayList<ToggleItem> dataList3 = new ArrayList<ToggleItem>();
        ArrayList<ToggleItem> dataList4 = new ArrayList<ToggleItem>();
        ArrayList<ToggleItem> dataList5 = new ArrayList<ToggleItem>();

        for(int i= hour1; i<hour2; i++) {
            ToggleItem item1 = new ToggleItem(date1,Integer.toString(i), Integer.toString(i+1),false, false);
            dataList1.add(item1);
            ToggleItem item2 = new ToggleItem(date2,Integer.toString(i), Integer.toString(i+1),false, false);
            dataList2.add(item2);
            ToggleItem item3 = new ToggleItem(date3,Integer.toString(i), Integer.toString(i+1),false, false);
            dataList3.add(item3);
            ToggleItem item4 = new ToggleItem(date4,Integer.toString(i), Integer.toString(i+1),false, false);
            dataList4.add(item4);
            ToggleItem item5 = new ToggleItem(date5,Integer.toString(i), Integer.toString(i+1),false, false);
            dataList5.add(item5);
        }

        for(int k=0; k<dayi; k++) {
            if (k == 0) {
                timeadapter1.submitList(dataList1);
            }else if(k==1){
                timeadapter2.submitList(dataList2);
            }else if(k==2){
                timeadapter3.submitList(dataList3);
            }else if(k==3){
                timeadapter4.submitList(dataList4);
            }else{
                timeadapter5.submitList(dataList5);
            }
        }



    }
}