package com.voc.genshin_helper.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.github.lzyzsd.circleprogress.CircleProgress;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.util.BackgroundReload;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class NotificationActivity extends AppCompatActivity {
    Context context;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        context = this;
        activity = this;

        BackgroundReload.BackgroundReload(context,activity);

        CircleProgress resin_pb = findViewById(R.id.resin_pb);
        resin_pb.setMax(160);
        resin_pb.setProgress(80);
        resin_pb.setSuffixText("/160");
    }
}