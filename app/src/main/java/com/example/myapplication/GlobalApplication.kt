package com.example.myapplication

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application(){
    override fun onCreate() {

        super.onCreate()
        KakaoSdk.init(this, "0a592c00ce4a1585ccf0c5e38576a82b")
    }
}
