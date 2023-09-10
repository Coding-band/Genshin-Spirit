package com.voc.genshin_helper.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.voc.genshin_helper.R;
import com.voc.genshin_helper.ui.AlarmUI;
import com.voc.genshin_helper.ui.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.voc.genshin_helper.data.RoundRectImageView.getRoundBitmapByShader;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

/**
 * Created by ankit on 27/10/17.
 */

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {

    private Context context;
    private List<Alarm> alarmList;
    private AdapterView.OnItemClickListener mListener;
    private WebView webView ;

    SharedPreferences sharedPreferences;
    public AlarmAdapter(Context context, List<Alarm> alarmList) {
        this.context = context;
        this.alarmList = alarmList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm_list, parent, false);
        ViewHolder evh = new ViewHolder(v, (OnItemClickListener) mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Alarm alarm = alarmList.get(position);
        //Log.wtf("CHAR", String.valueOf(alarmList.size()));
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm");

        // Title
        holder.ui_title.setText(alarm.getTitle());

        // Finish Time
        long FinishTEMP = (long) (alarm.getFinish_time()/1000);
        Date FinishDATE = new java.util.Date(FinishTEMP*1000L);
        String FinishSTR = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(FinishDATE);
        holder.ui_finish_time.setText(FinishSTR);

        // Remain Time
        if(alarm.getFinish_time() - System.currentTimeMillis() >0){
            holder.ui_time.setText(FormatTime((alarm.getFinish_time() - System.currentTimeMillis())/1000));
        }else {
            holder.ui_time.setText("Finished !");
        }


        // Ids for each alarm (NOT TO SHOW)
        holder.ui_int.setText(String.valueOf(alarm.getId()));
        //Log.w("UI_INT"+String.valueOf(position),String.valueOf(alarm.getId()));

        // Type
        if(alarm.getType() == R.string.alarm_t1){
            holder.ui_progress.setVisibility(View.GONE);
            holder.ui_ico.setImageResource(R.drawable.dream_solvent);
        }else if(alarm.getType() == R.string.alarm_t2){
            holder.ui_ico.setImageResource(R.drawable.fragile_resin);
        }else if(alarm.getType() == R.string.alarm_t3){
            holder.ui_ico.setImageResource(R.drawable.realm_currency);
        }else if(alarm.getType() == R.string.alarm_t4){
            holder.ui_ico.setImageResource(R.drawable.dandelion_seed);
        }else if(alarm.getType() == R.string.alarm_t5){
            holder.ui_ico.setImageResource(R.drawable.primogem);
        }

        // Color setting
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info",MODE_PRIVATE);
        String color_hex = sharedPreferences.getString("theme_color_hex","#FF5A5A"); // Must include #

        ColorStateList myList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_pressed},
                        new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked},
                },
                new int[] {
                        context.getResources().getColor(R.color.tv_color),
                        context.getResources().getColor(R.color.tv_color),
                        Color.parseColor(color_hex)
                }
        );
        holder.ui_title.setTextColor(Color.parseColor(color_hex));
        holder.ui_time.setTextColor(Color.parseColor(color_hex));
        holder.ui_finish_time.setTextColor(Color.parseColor(color_hex));

    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ui_title,ui_time,ui_progress,ui_finish_time,ui_int;
        public ImageView ui_ico;
        public LinearLayout ui_ll;


        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            ui_ll = itemView.findViewById(R.id.ui_ll);
            ui_title = itemView.findViewById(R.id.ui_title);
            ui_time = itemView.findViewById(R.id.ui_time);
            ui_progress = itemView.findViewById(R.id.ui_progress);
            ui_finish_time = itemView.findViewById(R.id.ui_finish_time);
            ui_ico = itemView.findViewById(R.id.ui_ico);
            ui_int = itemView.findViewById(R.id.ui_int);
            ui_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.wtf("is context instanceof AlarmUI ?",context.getPackageName());
                    if (context instanceof AlarmUI){
                        Log.wtf("YES","IT's");

                        (((AlarmUI) context)).type_edit(Integer.parseInt(String.valueOf(ui_int.getText())),getLayoutPosition());
                    }
                }
            });

        }
    }
    public void filterList(List<Alarm> filteredList) {
        alarmList = filteredList;
        notifyDataSetChanged();
    }
    public String FormatTime (Long time){
        String Time_String = "";
        if (time >0){
            long days = time / 86400;
            long hours = time / 3600;
            long minutes = (time % 3600) / 60;
            long seconds = time % 60;

            if(days<1){Time_String = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            }else {
                Time_String = String.format("%02dd , %02d:%02d:%02d", days , hours, minutes, seconds);
            }
            return Time_String;
        }
        return "Finished !";
    }
}