package com.example.when2meet.Retrofit;

import android.util.Log;

import com.example.when2meet.Retrofit.Models.Model__PostSchedule;
import com.example.when2meet.Retrofit.Models.Model__Profile;
import com.example.when2meet.Retrofit.Models.Model__PutSchedule;
import com.example.when2meet.Retrofit.Models.Model__Schedule;
import com.example.when2meet.Retrofit.Models.Model__Timeslot;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallRetrofit {
    // TODO
    public void getTimeslotWithIdFunc(String timeslotId, ArrayList<Model__Timeslot> timeslots) {
        Call<Model__Timeslot> call = RetrofitClient.getApiService().getTimeslotWithId(timeslotId);
        call.enqueue(new Callback<Model__Timeslot>() {
            @Override
            public void onResponse(Call<Model__Timeslot> call, Response<Model__Timeslot> response) {
                if (!response.isSuccessful()) {
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }

                Model__Timeslot ts = response.body();
                timeslots.add(ts);
            }

            @Override
            public void onFailure(Call<Model__Timeslot> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    // TODO
    public void getNameWithUserId(String userId, ArrayList<String> userNames) {
        Call<Model__Profile> call = RetrofitClient.getApiService().getProfileWithId(String.valueOf(userId));
        call.enqueue(new Callback<Model__Profile>() {
            @Override
            public void onResponse(Call<Model__Profile> call, Response<Model__Profile> response) {
                if (!response.isSuccessful()) {
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }

                Model__Profile profile = response.body();
                userNames.add(profile.getName());
            }

            @Override
            public void onFailure(Call<Model__Profile> call, Throwable t) {
                Log.e("failed", "fail");
                t.printStackTrace();
            }
        });
    }


    // TODO
    public void getAllSchedulesFunc(ArrayList<Model__Schedule> returnVal) {
        Call<List<Model__Schedule>> call = RetrofitClient.getApiService().getAllSchedules();
        call.enqueue(new Callback<List<Model__Schedule>>() {
            @Override
            public void onResponse(Call<List<Model__Schedule>> call, Response<List<Model__Schedule>> response) {
                if (!response.isSuccessful()) {
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                ArrayList<Model__Schedule> schedules = (ArrayList<Model__Schedule>) response.body();
                for (Model__Schedule sched : schedules) {
                    Log.d("AAAAAAA", sched.getTitle());
                    returnVal.add(sched);
                }
                Log.e("스케듈 크기", String.valueOf(returnVal.size()));
            }

            @Override
            public void onFailure(Call<List<Model__Schedule>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    // TODO
    public void getScheduleWithIdFunc(String scheduleId, ArrayList<Model__Schedule> result) {
        Call<Model__Schedule> call = RetrofitClient.getApiService().getScheduleWithId(scheduleId);
        call.enqueue(new Callback<Model__Schedule>() {
            @Override
            public void onResponse(Call<Model__Schedule> call, Response<Model__Schedule> response) {
                if (!response.isSuccessful()) {
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                result.add(response.body());
            }

            @Override
            public void onFailure(Call<Model__Schedule> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}