package com.example.trg;

import android.app.Application;
import com.kakao.sdk.common.KakaoSdk;

public class LoginRequest extends Application {
    private static LoginRequest instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // 네이티브 앱 키로 초기화
        KakaoSdk.init(this, "3e77a5d1cc4ceb949ddddae7ca838165");
    }
}