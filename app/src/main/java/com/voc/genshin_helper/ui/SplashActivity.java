package com.voc.genshin_helper.ui;/*
 * Package com.voc.genshin_helper.ui.SplashActivity was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.play.core.splitinstall.SplitInstallRequest;
import com.voc.genshin_helper.BuildConfig;
import com.voc.genshin_helper.R;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTheme(R.style.SplashTheme);
        setContentView(R.layout.activity_splash);
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", 0);
        if (sharedPreferences.getBoolean("theme_light",true) == true){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        if (sharedPreferences.getBoolean("theme_night",false) == true){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        if (sharedPreferences.getBoolean("theme_default",false) == true){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
        ((TextView) findViewById(R.id.version_tv)).setText(BuildConfig.VERSION_NAME);

        new Handler(Looper.getMainLooper()).postDelayed(new RunnableC12321(), 2000);

    }

    /* renamed from: com.voc.genshin_helper.ui.SplashActivity$1 */
    /* loaded from: classes.dex */
    class RunnableC12321 implements Runnable {
        RunnableC12321() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
            SplashActivity.this.finish();
        }
    }
}