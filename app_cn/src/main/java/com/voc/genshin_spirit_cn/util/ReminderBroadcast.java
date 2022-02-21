package com.voc.genshin_spirit_cn.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.voc.genshin_spirit_cn.R;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */
public class ReminderBroadcast extends BroadcastReceiver {

    String title = "Genshin Helper";
    String info = "Genshin Helper info";
    long alarm_count = -1;

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();

        if( bundle.getString("title") != null){title =  bundle.getString("title");}
        if( bundle.getString("info") != null){info =  bundle.getString("info");}
        if( bundle.getLong("alarm_count") != -1){alarm_count =  bundle.getLong("alarm_count");}

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "GenshinHelper")
                .setSmallIcon(R.drawable.app_ico)
                .setContentTitle(title)
                .setContentText(info)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify((int) alarm_count,builder.build());
    }
}
