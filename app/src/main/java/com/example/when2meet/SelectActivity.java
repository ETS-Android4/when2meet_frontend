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
import com.example.when2meet.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        System.out.println("userNameselect!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(userName);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeLayout);
        plusButton = (ImageButton) findViewById(R.id.plus);
        selectrcv = findViewById(R.id.select_rcv);
        selectAdapter = new SelectAdapter(userName, userId);
        selectrcv.setAdapter(selectAdapter);

        CallRetrofit retrofitClient = new CallRetrofit();
        ArrayList<Model__Schedule> schedules = new ArrayList<Model__Schedule>();
        ArrayList<SelectItem> dataList = new ArrayList<SelectItem>();

        Call<List<Model__Schedule>> call = RetrofitClient.getApiService().getAllSchedules();
        call.enqueue(new Callback<List<Model__Schedule>>() {
            @Override
            public void onResponse(Call<List<Model__Schedule>> call, Response<List<Model__Schedule>> response) {
                if (!response.isSuccessful()) {
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                ArrayList<Model__Schedule> schedulesResopnse = (ArrayList<Model__Schedule>) response.body();
                for (Model__Schedule sched : schedulesResopnse) {
                    schedules.add(sched);
                }

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
                            Collections.sort(names);
                            SelectItem item1 = new SelectItem(schedule.getTitle(), String.join(", ", names),schedule.get_id());
                            dataList.add(item1);
                            selectAdapter.submitList(dataList);
                        }
                    }, 300);
                }

            }

            @Override
            public void onFailure(Call<List<Model__Schedule>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this, DetailActivity.class);
                intent.putExtra("userId",userId);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh () {
                CallRetrofit retrofitClient = new CallRetrofit();
                ArrayList<Model__Schedule> schedules = new ArrayList<Model__Schedule>();
                ArrayList<SelectItem> dataList = new ArrayList<SelectItem>();

                Call<List<Model__Schedule>> call = RetrofitClient.getApiService().getAllSchedules();
                call.enqueue(new Callback<List<Model__Schedule>>() {
                    @Override
                    public void onResponse(Call<List<Model__Schedule>> call, Response<List<Model__Schedule>> response) {
                        if (!response.isSuccessful()) {
                            Log.e("연결이 비정상적 : ", "error code : " + response.code());
                            return;
                        }
                        ArrayList<Model__Schedule> schedulesResponse = (ArrayList<Model__Schedule>) response.body();
                        for (Model__Schedule sched : schedulesResponse) {
                            schedules.add(sched);
                        }


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
                                    Collections.sort(names);
                                    SelectItem item1 = new SelectItem(schedule.getTitle(), String.join(", ", names),schedule.get_id());
                                    dataList.add(item1);
                                    selectAdapter.submitList(dataList);
                                }
                            }, 300);
                        }

                        if (swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Model__Schedule>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}