package com.example.when2meet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.when2meet.Retrofit.CallRetrofit;
import com.example.when2meet.Retrofit.Models.Model__Profile;
import com.example.when2meet.Retrofit.Models.Model__Schedule;
import com.example.when2meet.Retrofit.RetrofitClient;
import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private View loginButton, logoutButton, selectButton;
    private TextView nickname;
    private ImageView profileImage;
    private Long userId;
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.login);
        logoutButton = findViewById(R.id.logout);
        nickname = findViewById(R.id.nickname);
        profileImage = findViewById(R.id.profile);
        selectButton = findViewById(R.id.toselect);
        selectButton.setVisibility(View.GONE);
        logoutButton.setVisibility(View.GONE);
        profileImage.setImageDrawable(getResources().getDrawable(R.drawable.frontpng));

        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if (oAuthToken != null) {
                    //로그인 성공
                    System.out.println("Sucess");
                }
                if (throwable != null) {
                    //fail
                }
                updateKakaoLoginUi();
                return null;
            }
        };

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginClient.getInstance().isKakaoTalkLoginAvailable(MainActivity.this)) {
                    LoginClient.getInstance().loginWithKakaoTalk(MainActivity.this, callback);
                } else {
                    LoginClient.getInstance().loginWithKakaoAccount(MainActivity.this, callback);
                }
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {
                        updateKakaoLoginUi();
                        return null;
                    }
                });
            }
        });

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectActivity.class);
                intent.putExtra("userId",userId);
                intent.putExtra("userName",userName);
                startActivity(intent);
            }
        });
        updateKakaoLoginUi();

    }
    private void updateKakaoLoginUi() {
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if (user != null) {
                    // logged in successfully
                    Log.i(TAG, "invoke: id=" + user.getId());
                    Log.i(TAG, "invoke: email=" + user.getKakaoAccount().getEmail());
                    Log.i(TAG, "invoke: nickname=" + user.getKakaoAccount().getProfile().getNickname());
                    Log.i(TAG, "invoke: gender=" + user.getKakaoAccount().getGender());
                    Log.i(TAG, "invoke: age=" + user.getKakaoAccount().getAgeRange());
                    userId = user.getId();
                    userName = user.getKakaoAccount().getProfile().getNickname();
                    nickname.setText(user.getKakaoAccount().getProfile().getNickname());
                    Glide.with(profileImage).load(user.getKakaoAccount().getProfile().getThumbnailImageUrl()).circleCrop().into(profileImage);
                    loginButton.setVisibility(View.GONE);
                    logoutButton.setVisibility(View.VISIBLE);
                    selectButton.setVisibility(View.VISIBLE);

                    Model__Profile profile = new Model__Profile();
                    profile.setUserId(user.getId());
                    profile.setName(user.getKakaoAccount().getProfile().getNickname());
                    profile.setEmail(user.getKakaoAccount().getEmail());
                    profile.setImageUrl(user.getKakaoAccount().getProfile().getThumbnailImageUrl());

                    Call<Model__Profile> call = RetrofitClient.getApiService().postProfile(profile.getUserId(), profile.getName(), profile.getImageUrl(), profile.getEmail());
                    call.enqueue(new Callback<Model__Profile>() {
                        @Override
                        public void onResponse(Call<Model__Profile> call, Response<Model__Profile> response) {
                            if (!response.isSuccessful()) {
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
                } else {
                    // logout
                    nickname.setText(null);
                    profileImage.setImageDrawable(getResources().getDrawable(R.drawable.frontpng));
                    loginButton.setVisibility(View.VISIBLE);
                    logoutButton.setVisibility(View.GONE);
                    selectButton.setVisibility(View.GONE);
                }
                if (throwable != null) {
                    Log.w(TAG, "invoke: " + throwable.getLocalizedMessage());
                }
                return null;
            }
        });
    }
}