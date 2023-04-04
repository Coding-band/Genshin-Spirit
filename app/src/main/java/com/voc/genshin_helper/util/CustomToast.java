package com.voc.genshin_helper.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.voc.genshin_helper.R;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class CustomToast {

    public static void toast(Context context, Activity activity, String str) {
        View inflate = activity.getLayoutInflater().inflate(R.layout.item_toast, (ViewGroup) activity.findViewById(R.id.toast_frame));
        TextView textView = (TextView) inflate.findViewById(R.id.toast_tv);
        textView.setText(str);
        textView.setTextColor(context.getResources().getColor(R.color.tv_color));
        textView.setBackgroundResource(R.drawable.item_toast);
        Toast toast = new Toast(context.getApplicationContext());
        toast.setGravity(80, 0, 150);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(inflate);
        toast.show();

    }

    public static void toast(Context context, View view, String str) {
        View inflate = View.inflate(context,R.layout.item_toast, (ViewGroup) view.findViewById(R.id.toast_frame));
        TextView textView = (TextView) inflate.findViewById(R.id.toast_tv);
        textView.setText(str);
        textView.setTextColor(context.getResources().getColor(R.color.tv_color));
        textView.setBackgroundResource(R.drawable.item_toast);
        Toast toast = new Toast(context.getApplicationContext());
        toast.setGravity(80, 0, 150);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(inflate);
        toast.show();

    }
}
