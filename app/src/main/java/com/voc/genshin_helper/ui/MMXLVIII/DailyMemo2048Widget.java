package com.voc.genshin_helper.ui.MMXLVIII;

import android.app.ActivityManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.List;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

/**
 * Implementation of App Widget functionality.
 */
public class DailyMemo2048Widget extends AppWidgetProvider {
    public static final String TAG = "DailyMemo2048Widget";

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
                ComponentName thisWidget = new ComponentName(context, DailyMemo2048Widget.class);
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
        context.stopService(new Intent(context, DailyMemo2048Service.class));
    }
    /**啟動Service*/
    private void startRunService(Context context) {
        Intent intent = new Intent(context, DailyMemo2048Service.class);
        context.startService(intent);
    }
    /**判斷此是否已有我的Service再跑*/
    private Boolean isServiceRun(Context context){
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> list =  manager.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo info : list){
            if (DailyMemo2048Service.class.getName().equals(info.service.getClassName()))return true;
        }
        return false;
    }


}