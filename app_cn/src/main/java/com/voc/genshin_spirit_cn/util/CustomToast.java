package com.voc.genshin_spirit_cn.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.voc.genshin_spirit_cn.R;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

public class CustomToast {

    public static void toast(Context context, Activity activity, String str) {
        View inflate = activity.getLayoutInflater().inflate(R.layout.item_toast, (ViewGroup) activity.findViewById(R.id.toast_frame));
        String string = context.getSharedPreferences("user_info", 0).getString("theme_color_hex", "#FF5A5A");
        ColorStateList colorStateList = new ColorStateList(new int[][]{new int[]{16842919}, new int[]{-16842912}, new int[]{16842912}}, new int[]{Color.parseColor(string), Color.parseColor(string), Color.parseColor(string)});
        new ColorStateList(new int[][]{new int[]{16842919}, new int[]{-16842912}, new int[]{16842912}}, new int[]{context.getResources().getColor(R.color.tv_anti_color), context.getResources().getColor(R.color.tv_anti_color), context.getResources().getColor(R.color.tv_anti_color)});
        string.toUpperCase().equals("#FFFFFFFF");
        TextView textView = (TextView) inflate.findViewById(R.id.toast_tv);
        textView.setText(str);
        textView.setTextColor(context.getResources().getColor(R.color.tv_anti_color));
        textView.setBackgroundTintList(colorStateList);
        Toast toast = new Toast(context.getApplicationContext());
        toast.setGravity(80, 0, 150);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(inflate);
        toast.show();

    }

    public static void toast(Context context, View view, String str) {
        View inflate = view.inflate(context,R.layout.item_toast, (ViewGroup) view.findViewById(R.id.toast_frame));
        String string = context.getSharedPreferences("user_info", 0).getString("theme_color_hex", "#FF5A5A");
        ColorStateList colorStateList = new ColorStateList(new int[][]{new int[]{16842919}, new int[]{-16842912}, new int[]{16842912}}, new int[]{Color.parseColor(string), Color.parseColor(string), Color.parseColor(string)});
        new ColorStateList(new int[][]{new int[]{16842919}, new int[]{-16842912}, new int[]{16842912}}, new int[]{context.getResources().getColor(R.color.tv_anti_color), context.getResources().getColor(R.color.tv_anti_color), context.getResources().getColor(R.color.tv_anti_color)});
        string.toUpperCase().equals("#FFFFFFFF");
        TextView textView = (TextView) inflate.findViewById(R.id.toast_tv);
        textView.setText(str);
        textView.setTextColor(context.getResources().getColor(R.color.tv_anti_color));
        textView.setBackgroundTintList(colorStateList);
        Toast toast = new Toast(context.getApplicationContext());
        toast.setGravity(80, 0, 150);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(inflate);
        toast.show();

    }
}
