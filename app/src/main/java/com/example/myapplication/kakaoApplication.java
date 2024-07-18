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
                return null;
            }
        };
    }
}
