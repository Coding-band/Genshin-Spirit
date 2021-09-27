package com.voc.genshin_helper.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// https://developer.android.com/training/scheduling/alarms#java

/*
 * Package com.voc.genshin_helper.util.SampleBootReceiver was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

public class SampleBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            // Set the alarm here.
        }
    }
}