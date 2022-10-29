package com.voc.genshin_helper.ui.MMXLVIII;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.RemoteViews;

import com.voc.genshin_helper.R;

import java.util.List;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

/**
 * Implementation of App Widget functionality.
 */
public class DailyMemoWidget extends AppWidgetProvider {
    public static final String TAG = "DailyMemoWidget";

    DisplayMetrics displayMetrics;

    /**接收廣播資訊*/
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d(TAG, "onReceive: "+intent.getAction());
        switch (intent.getAction()){
            case "android.appwidget.action.APPWIDGET_UPDATE":
                Boolean isRun = isServiceRun(context);
                Log.d(TAG, "onReceive: 有Service再跑？: "+isRun);
                if (!isRun)startRunService(context);
                break;
            /*
            case "PRESS_EXT" :
                displayMetrics = context.getResources().getDisplayMetrics();

                RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.daily_memo_widget);
                ComponentName thisWidget = new ComponentName(context, DailyMemoWidget.class);
                remoteViews.setViewLayoutMargin();
                AppWidgetManager manager = AppWidgetManager.getInstance(context);
                manager.updateAppWidget(thisWidget, remoteViews);
                break;
             */
        }
    }
    /**當小工具被建立時*/
    @Override
    public void onEnabled(Context context) {
        startRunService(context);
    }
    /**當小工具被刪除時*/
    @Override
    public void onDisabled(Context context) {
        context.stopService(new Intent(context,DailyMemoService.class));
    }
    /**啟動Service*/
    private void startRunService(Context context) {
        Intent intent = new Intent(context,DailyMemoService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent);
        }
        context.startService(intent);
    }
    /**判斷此是否已有我的Service再跑*/
    private Boolean isServiceRun(Context context){
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> list =  manager.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo info : list){
            if (DailyMemoService.class.getName().equals(info.service.getClassName()))return true;
        }
        return false;
    }


    public class ShowAnim extends Animation {
        int targetTop;
        View view;
        boolean isPushDownAnim;

        public ShowAnim(View view, int targetTop, boolean isPushDownAnim) {
            this.view = view;
            this.targetTop = targetTop;
            this.isPushDownAnim = isPushDownAnim;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {

            float interpolatedTime_auth = interpolatedTime;
            if (isPushDownAnim){
                interpolatedTime_auth = (1-interpolatedTime);
            }

            ViewGroup.MarginLayoutParams layoutParams =
                    (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            layoutParams.setMargins((int) (16*displayMetrics.density), (int) (targetTop*interpolatedTime_auth*displayMetrics.density-16*displayMetrics.density), (int) (16*displayMetrics.density), (int) (8*displayMetrics.density));
            view.setLayoutParams(layoutParams);
        }

        @Override
        public void initialize(int width, int height, int parentWidth,
                               int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }
}