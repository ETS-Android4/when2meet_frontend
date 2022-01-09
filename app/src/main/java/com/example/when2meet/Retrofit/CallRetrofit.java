package com.example.when2meet.Retrofit;

import android.util.Log;

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

    public Model__Profile postProfileFunction(Model__Profile profile) {
        // Retrofit 호출
        Log.d("111", "111111111111");
        final Model__Profile[] returnVal = {new Model__Profile()};
        Log.d("111", "222222222222");
        Call<Model__Profile> call = RetrofitClient.getApiService().postProfile(profile.getUserId(), profile.getName(), profile.getImageUrl(), profile.getEmail(), profile.getGender(), profile.getAge());
        Log.d("111", "333333333333");
        call.enqueue(new Callback<Model__Profile>() {
            @Override
            public void onResponse(Call<Model__Profile> call, Response<Model__Profile> response) {
                if(!response.isSuccessful()){
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }

                returnVal[0] = response.body();
                Log.d("response body", response.body().toString());
                return;
            }

            @Override
            public void onFailure(Call<Model__Profile> call, Throwable t) {
                t.printStackTrace();
                Log.e("연결실패", t.getMessage());
            }
        });
        Log.d("111", "4444444444444444");

        return returnVal[0];
    }
}