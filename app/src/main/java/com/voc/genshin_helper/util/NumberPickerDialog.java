package com.voc.genshin_helper.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.appcompat.app.AlertDialog;

import com.voc.genshin_helper.R;

/**
 * Package com.voc.genshin_helper.util was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2020 Xectorda 版權所有
 */

//https://thumbb13555.pixnet.net/blog/post/326868843-interface%26numberpicker
public class NumberPickerDialog {
    private Activity activity;
    int min_var_from_ui = 0;
    int max_var_from_ui = 1;
    int value_from_ui = 0;
    /**使InterFace可以被類別使用*/
    public OnDialogRespond onDialogRespond;

    public NumberPickerDialog(Activity activity) {
        this.activity = activity;
    }

    public void showDialog(String XPR) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(activity);
        View view = LayoutInflater.from(activity).inflate(R.layout.fragment_number_picker, null);
        mBuilder.setView(view);
        AlertDialog dialog = mBuilder.create();
        /**這裡是設置NumberPicker相關*/
        NumberPicker np;
        np = view.findViewById(R.id.num_picker);
        /**設置NumberPicker的最大、最小以及NumberPicker現在要顯示的內容*/
        np.setMaxValue(max_var_from_ui);
        np.setMinValue(min_var_from_ui);
        np.setValue(value_from_ui);
        Button ok = view.findViewById(R.id.np_ok);
        Button cancel = view.findViewById(R.id.np_cancel);

        dialog.show();
        //dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        ok.setOnClickListener((v -> {
            /**這邊將值放進OnDialogRespond中*/
            onDialogRespond.onRespond(np.getValue(),XPR);
            dialog.dismiss();
        }));
        //dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
        cancel.setOnClickListener((v -> {
            dialog.dismiss();
        }));
    }

    public void setLastValue(int curr) {
        value_from_ui = curr;
    }
    public void setMaxValue(int max) {
        max_var_from_ui = max;
    }
    public void setMinValue(int min) {
        min_var_from_ui = min;
    }

    /**設置Interface，使取到的直可以被回傳*/
    public interface OnDialogRespond{
        void onRespond(int value , String XPR);
    }
}