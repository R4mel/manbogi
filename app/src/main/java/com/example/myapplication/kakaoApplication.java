package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class kakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSdk.init(this, "0a592c00ce4a1585ccf0c5e38576a82b");

        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                updateKakaoLoginUi();
                return null;
            }
        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(login.this)) {
                    UserApiClient.getInstance().loginWithKakaoTalk(login.this, callback);
                } else {
                    UserApiClient.getInstance().loginWithKakaoAccount(login.this, callback);
                }
            }
        });
    }

    private void updateKakaoLoginUi() {
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if (user != null) {
                    Log.d(TAG, "invoke: id" + user.getId());
                    Log.d(TAG, "invoke: email" + user.getKakaoAccount().getEmail());
                    Log.d(TAG, "invoke: nickname" + user.getKakaoAccount().getProfile().getNickname());
                    Log.d(TAG, "invoke: gender" + user.getKakaoAccount().getGender());
                    Log.d(TAG, "invoke: age" + user.getKakaoAccount().getAgeRange());
                } else {
                    Log.d(TAG, "로그인이 안되어 있습니다.");
                }
                return null;
            }
        });
    }
}
