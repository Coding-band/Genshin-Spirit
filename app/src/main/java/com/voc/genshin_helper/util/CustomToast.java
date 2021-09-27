package com.voc.genshin_helper.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.voc.genshin_helper.R;

import static android.content.Context.MODE_PRIVATE;

/*
 * Package com.voc.genshin_helper.util.CustomToast was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */
public class CustomToast {

    public void toast(Context context, Activity activity,String txt){
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.item_toast,activity.findViewById(R.id.toast_frame));
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info",MODE_PRIVATE);

        String color_hex = sharedPreferences.getString("theme_color_hex","#FF5A5A"); // Must include #
        ColorStateList myList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_pressed},
                        new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked},
                },
                new int[] {
                        Color.parseColor(color_hex),
                        Color.parseColor(color_hex),
                        Color.parseColor(color_hex)
                }
        );
        ColorStateList myListD = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_pressed},
                        new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked},
                },
                new int[] {
                        context.getResources().getColor(R.color.tv_anti_color),
                        context.getResources().getColor(R.color.tv_anti_color),
                        context.getResources().getColor(R.color.tv_anti_color)
                }
        );

        if(color_hex.toUpperCase().equals("#FFFFFFFF")){
            color_hex = "#000000";
        }

        TextView text = (TextView) layout.findViewById(R.id.toast_tv);
        text.setText(txt);
        text.setTextColor(context.getResources().getColor(R.color.tv_anti_color));
        text.setBackgroundTintList(myList);
        Toast toast = new Toast(context.getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 150);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
