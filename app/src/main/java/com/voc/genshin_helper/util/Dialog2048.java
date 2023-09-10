package com.voc.genshin_helper.util;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import static android.content.Context.MODE_PRIVATE;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import static com.voc.genshin_helper.util.LogExport.DOWNLOADTASK;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.voc.genshin_helper.R;

import java.text.DecimalFormat;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class Dialog2048 {


    Dialog dialog;
    View view;
    Context context;
    Activity activity;

    TextView dialog_title_tv;
    LinearLayout dialog_progress;
    ProgressBar dialog_progress_bar;
    TextView dialog_progress_tv;
    TextView dialog_progress_detail;
    LinearLayout dialog_ask;
    TextView dialog_ask_tv;
    FrameLayout dialog_cancel;
    FrameLayout dialog_ok;

    boolean isUnzip = false;

    public static final int MODE_PROGRESS_DOWNLOAD = 1;
    public static final int MODE_PROGRESS_UNZIP = 2;
    public static final int MODE_DOWNLOAD_BASE = 3;
    public static final int MODE_DOWNLOAD_BASE_DESK = 4;
    public static final int MODE_DOWNLOAD_UPDATE = 5;
    public static final int MODE_DOWNLOAD_PAUSED_NETWORK = 6;
    public static final int MODE_DOWNLOAD_PAUSED_CRASH = 7;

    long size_max = 0;
    int countX = 0;

    public void setup(Context context, Activity activity){
        this.context = context;
        this.activity = activity;

        dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        view = View.inflate(context, R.layout.fragment_dialog_2048, null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        dialog.setCancelable(false);

        dialog_title_tv = view.findViewById(R.id.dialog_title_tv);

        dialog_progress = view.findViewById(R.id.dialog_progress);
        dialog_progress_bar = view.findViewById(R.id.dialog_progress_bar);
        dialog_progress_tv = view.findViewById(R.id.dialog_progress_tv);
        dialog_progress_detail = view.findViewById(R.id.dialog_progress_detail);

        dialog_ask = view.findViewById(R.id.dialog_ask);
        dialog_ask_tv = view.findViewById(R.id.dialog_ask_tv);
        dialog_cancel = view.findViewById(R.id.dialog_cancel);
        dialog_ok = view.findViewById(R.id.dialog_ok);

        dialog_progress_bar.setMax(100);
        dialog_progress_bar.setProgress(0);
        dialog_progress_tv.setText("0%");
        if (isUnzip){
            dialog_progress_detail.setText("0 / 0");
        }else{
            dialog_progress_detail.setText("0B / 0B");
        }

        Window dialogWindowX = activity.getWindow();
        dialogWindowX.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 2O48 DESIGN
        dialogWindowX.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        dialogWindowX.setStatusBarColor(context.getColor(R.color.status_bar_2048));
        dialogWindowX.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

        /** Method of dialog */
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        // 2O48 DESIGN
        dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_2048));
        dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

        lp.width = (int) (width);
        lp.height = WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
    }

    public void show(){
        dialog.show();
    }
    public void updateProgress(long size_progress){
        if (countX < 50){
            countX++;
        }else{
            countX = 0;
            double progress = ((double) (size_progress*100/size_max));
            dialog_progress_bar.setProgress((int) progress);
            dialog_progress_tv.setText(prettyCount(progress)+"%");
            //LogExport.export("DownloadTask","DownloadFileFromURL.doInBackground => updateProgress", "Progress : "+prettyCount(progress), context, DOWNLOADTASK);

            if (isUnzip){
                dialog_progress_detail.setText(prettyCount(size_progress)+" / "+prettyCount(size_max));
            }else{
                dialog_progress_detail.setText(prettyByteCount(size_progress)+" / "+prettyByteCount(size_max));
            }
        }

    }

    public void updateProgressWaited(long size_progress){
        double progress = ((double) (size_progress*100/size_max));
        dialog_progress_bar.setProgress((int) progress);
        dialog_progress_tv.setText(prettyCount(progress)+"%");
        //LogExport.export("DownloadTask","DownloadFileFromURL.doInBackground => updateProgress", "Progress : "+prettyCount(progress), context, DOWNLOADTASK);

        if (isUnzip){
            dialog_progress_detail.setText(prettyCount(size_progress)+" / "+prettyCount(size_max));
        }else{
            dialog_progress_detail.setText(prettyByteCount(size_progress)+" / "+prettyByteCount(size_max));
        }

    }

    public void updateMax(long size_max){
        this.size_max = size_max;
    }
    public long getMax(){
        return size_max;
    }

    public FrameLayout getPositiveBtn(){
        return dialog_ok;
    }
    public FrameLayout getNegativeBtn(){
        return dialog_cancel;
    }

    public void mode(int mode){
        isUnzip = false;
        switch (mode){
            case MODE_PROGRESS_DOWNLOAD: {
                dialog_progress.setVisibility(View.VISIBLE);
                dialog_ask.setVisibility(View.GONE);
                dialog_title_tv.setText(context.getString(R.string.update_download_downloading));

                break;
            }
            case MODE_PROGRESS_UNZIP: {
                dialog_progress.setVisibility(View.VISIBLE);
                dialog_ask.setVisibility(View.GONE);
                dialog_title_tv.setText(context.getString(R.string.update_download_unzipping));
                isUnzip = true;
                break;
            }
            case MODE_DOWNLOAD_UPDATE: {
                dialog_progress.setVisibility(View.GONE);
                dialog_ask.setVisibility(View.VISIBLE);
                dialog_title_tv.setText(context.getString(R.string.update_download_found_update));
                dialog_ask_tv.setText(context.getString(R.string.update_download_base_file_size)+" "+prettyByteCount(size_max)+"\n"+context.getString(R.string.update_download_advice));

                break;
            }
            case MODE_DOWNLOAD_BASE: {
                dialog_progress.setVisibility(View.GONE);
                dialog_ask.setVisibility(View.VISIBLE);
                dialog_title_tv.setText(context.getString(R.string.update_download_update_base));
                dialog_ask_tv.setText(context.getString(R.string.update_download_base_file_size)+" "+prettyByteCount(size_max)+"\n"+context.getString(R.string.update_download_advice));
                dialog_cancel.setVisibility(View.GONE);
                break;
            }
            case MODE_DOWNLOAD_BASE_DESK: {
                dialog_progress.setVisibility(View.GONE);
                dialog_ask.setVisibility(View.VISIBLE);
                dialog_title_tv.setText(context.getString(R.string.update_download_update_base));
                dialog_ask_tv.setText(context.getString(R.string.update_download_base_file_size)+" "+prettyByteCount(size_max)+"\n"+context.getString(R.string.update_download_advice));

                break;
            }
            case MODE_DOWNLOAD_PAUSED_NETWORK: {
                dialog_progress.setVisibility(View.VISIBLE);
                dialog_ask.setVisibility(View.GONE);
                dialog_title_tv.setText("Retrying to connect in 30s... Please stay and wait...");
                break;
            }
        }
    }

    public void dismiss(){
        if (dialog != null){
            if (dialog.isShowing()){
                dialog.dismiss();
            }
        }
    }



    public String prettyByteCount(Number number) {
        String[] suffix = {"B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};
        long numValue = ((long) number.longValue());
        int base = 0 ;
        double tmp_val = numValue;
        while (tmp_val> 1024){
            tmp_val = tmp_val/1024;
            base = base +1;
        }

        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        int decimal_num = 2;//sharedPreferences.getInt("decimal_num", 0);
        boolean decimal  = sharedPreferences.getBoolean("decimal", false);
        if (base < suffix.length) {
            return new DecimalFormat("##.##").format(numValue / Math.pow(1024, base)) + suffix[base];
            // Muility
        } else {
            return new DecimalFormat("#,###").format(numValue);
        }
    }


    public String prettyCount(Number number) {
        char[] suffix = {' ', 'K', 'M', 'G', 'T', 'P', 'E', 'Z', 'Y'};
        long numValue = number.longValue();
        int value = (int) Math.floor(Math.log10(numValue));
        int base = value / 3;
        String plus = "";
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", MODE_PRIVATE);
        boolean decimal  =true;
        int decimal_num = 2;
        if (decimal == true){
            if (value >= 3 && base < suffix.length) {
                if (decimal_num == 0){
                    return plus+new DecimalFormat("##").format(numValue / Math.pow(10, base * 3)) + suffix[base];
                }
                if (decimal_num == 1){
                    return plus+new DecimalFormat("##.#").format(numValue / Math.pow(10, base * 3)) + suffix[base];
                }
                if (decimal_num == 2){
                    return plus+new DecimalFormat("##.##").format(numValue / Math.pow(10, base * 3)) + suffix[base];
                }
                if (decimal_num == 3){
                    return plus+new DecimalFormat("##.###").format(numValue / Math.pow(10, base * 3)) + suffix[base];
                }
                if (decimal_num == 4){
                    return plus+new DecimalFormat("##.####").format(numValue / Math.pow(10, base * 3)) + suffix[base];
                }
                if (decimal_num == 5){
                    return plus+new DecimalFormat("##.#####").format(numValue / Math.pow(10, base * 3)) + suffix[base];
                }
                // Muility
            } else {
                return plus+new DecimalFormat("###,###,###,###,###").format(numValue);
            }
        }
        return plus+new DecimalFormat("###,###,###,###,###").format(numValue);
    }

}
