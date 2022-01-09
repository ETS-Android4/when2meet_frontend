package com.example.when2meet.Retrofit;

import android.util.Log;

import com.example.when2meet.Retrofit.Models.Model__CheckAlready;
import com.example.when2meet.Retrofit.Models.Model__Profile;
import com.example.when2meet.Retrofit.Models.Model__Schedule;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallRetrofit {
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

    public Model__Profile getProfileWithUserId(Model__Profile profile) {
        final Model__Profile[] model = new Model__Profile[1];
        Call<Model__Profile> call = RetrofitClient.getApiService().getProfileWithId(String.valueOf(profile.getUserId()));
        call.enqueue(new Callback<Model__Profile>() {
            @Override
            public void onResponse(Call<Model__Profile> call, Response<Model__Profile> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                model[0] = response.body();
            }

            @Override
            public void onFailure(Call<Model__Profile> call, Throwable t) {
                Log.e("failed","fail");
                t.printStackTrace();
            }
        });

        return model[0];
    }

    public ArrayList<Model__Schedule> getAllSchedulesFunc() {
        final ArrayList<Model__Schedule>[] schedules = new ArrayList[]{new ArrayList<Model__Schedule>()};
        Call<List<Model__Schedule>> call = RetrofitClient.getApiService().getAllSchedules();
        call.enqueue(new Callback<List<Model__Schedule>>(){
            @Override
            public void onResponse(Call<List<Model__Schedule>> call, Response<List<Model__Schedule>> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                schedules[0] = (ArrayList<Model__Schedule>) response.body();
                for(int i=0; i<schedules[0].size(); ++i){
                    Log.d("schedule[0]", schedules[0].get(i).getTitle());
                }
            }

            @Override
            public void onFailure(Call<List<Model__Schedule>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return schedules[0];
    }

}