package com.example.when2meet.Retrofit;

import com.example.when2meet.Retrofit.Models.Model__CheckAlready;
import com.example.when2meet.Retrofit.Models.Model__Profile;
import com.example.when2meet.Retrofit.Models.Model__Schedule;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitAPI {

    @POST("apiLink/info")
    Call<Model__CheckAlready> postOverlapCheck(@Body Model__CheckAlready modelCheckAlready); //이건 바디 요청시 사용하는거

    @FormUrlEncoded
    @POST("api/profiles")
    Call<Model__Profile> postProfile(@Field("userId") Long userId, @Field("name") String name,
                                     @Field("imageUrl") String imageUrl, @Field("email") String email);

    @GET("api/profiles/userId/{userId}")
    Call<Model__Profile> getProfileWithId(@Path("userId") String userId);

    @GET("api/schedules")
    Call<List<Model__Schedule>> getAllSchedules();
    //@FormUrlEncoded
    //@POST("/auth/overlapChecker")
    //Call<Model__CheckAlready> postOverlapCheck(@Field("phone") String phoneNum, @Field("message") String message); //이건 요청시 사용하는거 (*데이터를 보낼때)
}