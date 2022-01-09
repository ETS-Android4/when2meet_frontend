package com.example.when2meet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.when2meet.Retrofit.CallRetrofit;
import com.example.when2meet.Retrofit.Models.Model__Schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SelectActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    SelectAdapter selectAdapter;
    RecyclerView selectrcv;
    List<SelectItem> selectdata;
    ImageButton plusButton;
    private Long userId;
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        Intent intent = getIntent();
        userId = intent.getExtras().getLong("userId");
        userName = intent.getExtras().getString("userName");
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeLayout);
        plusButton = (ImageButton) findViewById(R.id.plus);
        selectrcv = findViewById(R.id.select_rcv);
        selectAdapter = new SelectAdapter(userName);
        selectrcv.setAdapter(selectAdapter);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this, DetailActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh () {
                CallRetrofit retrofitClient = new CallRetrofit();
                ArrayList<Model__Schedule> schedules = new ArrayList<Model__Schedule>();
                ArrayList<SelectItem> dataList = new ArrayList<SelectItem>();
                retrofitClient.getAllSchedulesFunc(schedules);
                new Handler().postDelayed(new Runnable()
                {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void run() {
                        for (Model__Schedule schedule : schedules) {
                            ArrayList<String> names = new ArrayList<String>();
                            for (String member : schedule.getMembers()) {
                                retrofitClient.getNameWithUserId(member, names);
                            }
                            new Handler().postDelayed(new Runnable()
                            {
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                @Override
                                public void run() {
                                    SelectItem item1 = new SelectItem(schedule.getTitle(), String.join(", ", names));
                                    dataList.add(item1);
                                    selectAdapter.submitList(dataList);
                                }
                            }, 100);
                        }
                    }
                }, 100);
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public void onResume() {
        super.onResume();

        CallRetrofit retrofitClient = new CallRetrofit();
        ArrayList<Model__Schedule> schedules = new ArrayList<Model__Schedule>();
        ArrayList<SelectItem> dataList = new ArrayList<SelectItem>();
        retrofitClient.getAllSchedulesFunc(schedules);
        new Handler().postDelayed(new Runnable()
        {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                for (Model__Schedule schedule : schedules) {
                    ArrayList<String> names = new ArrayList<String>();
                    for (String member : schedule.getMembers()) {
                        retrofitClient.getNameWithUserId(member, names);
                    }
                    new Handler().postDelayed(new Runnable()
                    {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void run() {
                            SelectItem item1 = new SelectItem(schedule.getTitle(), String.join(", ", names));
                            dataList.add(item1);
                            selectAdapter.submitList(dataList);
                        }
                    }, 100);
                }
            }
        }, 100);

    }
}