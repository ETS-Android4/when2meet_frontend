package com.example.when2meet.Retrofit;

import android.util.Log;

import com.example.when2meet.Retrofit.Models.Model__CheckAlready;
import com.example.when2meet.Retrofit.Models.Model__PostSchedule;
import com.example.when2meet.Retrofit.Models.Model__Profile;
import com.example.when2meet.Retrofit.Models.Model__PutSchedule;
import com.example.when2meet.Retrofit.Models.Model__Schedule;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallRetrofit {
    public void updateSchedule(Model__PutSchedule schedule) {
        Call<Model__PutSchedule> call = RetrofitClient.getApiService().putToSchedule(schedule.getScheduleId(), schedule);
        call.enqueue(new Callback<Model__PutSchedule>() {
            @Override
            public void onResponse(Call<Model__PutSchedule> call, Response<Model__PutSchedule> response) {
                if(!response.isSuccessful()){
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

    public void postScheduleFunction(Model__PostSchedule schedule, ArrayList<String> schedule_id) {
        Call<Model__PostSchedule> call = RetrofitClient.getApiService().postSchedule(schedule);
        call.enqueue(new Callback<Model__PostSchedule>() {
            @Override
            public void onResponse(Call<Model__PostSchedule> call, Response<Model__PostSchedule> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                Model__PostSchedule schedule = response.body();
                schedule_id.add(schedule.getId());
            }

            @Override
            public void onFailure(Call<Model__PostSchedule> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    public boolean callPhoneAlreadyCheck(String phoneNumber){
        boolean isRight = false;

        //Retrofit 호출
        Model__CheckAlready modelCheckAlready = new Model__CheckAlready(phoneNumber);
        Call<Model__CheckAlready> call = RetrofitClient.getApiService().postOverlapCheck(modelCheckAlready);
        call.enqueue(new Callback<Model__CheckAlready>() {
            @Override
            public void onResponse(Call<Model__CheckAlready> call, Response<Model__CheckAlready> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                Model__CheckAlready checkAlready = response.body();
                Log.d("연결이 성공적 : ", response.body().toString());
                if(modelCheckAlready.getMessage() == "can use this number"){
                    Log.d("중복검사: ", "중복된 번호가 아닙니다");
                    modelCheckAlready.setRight(true);
                }
            }
            @Override
            public void onFailure(Call<Model__CheckAlready> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
            }
        });

        return modelCheckAlready.isRight();
    }

    public void postProfileFunction(Model__Profile profile) {
        // Retrofit 호출
        Call<Model__Profile> call = RetrofitClient.getApiService().postProfile(profile.getUserId(), profile.getName(), profile.getImageUrl(), profile.getEmail());
        call.enqueue(new Callback<Model__Profile>() {
            @Override
            public void onResponse(Call<Model__Profile> call, Response<Model__Profile> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
            }

            @Override
            public void onFailure(Call<Model__Profile> call, Throwable t) {
                t.printStackTrace();
                Log.e("연결실패", t.getMessage());
            }
        });
    }

    public void getNameWithUserId(String userId, ArrayList<String> userNames) {
        Call<Model__Profile> call = RetrofitClient.getApiService().getProfileWithId(String.valueOf(userId));
        call.enqueue(new Callback<Model__Profile>() {
            @Override
            public void onResponse(Call<Model__Profile> call, Response<Model__Profile> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }

                Model__Profile profile = response.body();
                userNames.add(profile.getName());
            }

            @Override
            public void onFailure(Call<Model__Profile> call, Throwable t) {
                Log.e("failed","fail");
                t.printStackTrace();
            }
        });
    }


    public void getAllSchedulesFunc(ArrayList<Model__Schedule> returnVal) {
        Call<List<Model__Schedule>> call = RetrofitClient.getApiService().getAllSchedules();
        call.enqueue(new Callback<List<Model__Schedule>>(){
            @Override
            public void onResponse(Call<List<Model__Schedule>> call, Response<List<Model__Schedule>> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                ArrayList<Model__Schedule> schedules = (ArrayList<Model__Schedule>) response.body();
                for(Model__Schedule sched: schedules){
                    Log.d("AAAAAAA", sched.getTitle());
                    returnVal.add(sched);
                }
                Log.e("스케듈 크기" , String.valueOf(returnVal.size()));
            }

            @Override
            public void onFailure(Call<List<Model__Schedule>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}