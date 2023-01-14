package com.voc.genshin_helper.data.material;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import java.text.DecimalFormat;
import java.util.List;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

/**
 * Created by ankit on 27/10/17.
 */

public class CrystalAdapter extends RecyclerView.Adapter<CrystalAdapter.CrystalViewHolder> {

    private List<Materials> materialsList;
    Context context;
    Activity activity;

    public CrystalAdapter(Context context, List<Materials> materialsList, Activity activity){
        this.context = context;
        this.activity = activity;
        this.materialsList = materialsList;
    }

    @NonNull
    @Override
    public CrystalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CrystalViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cal_img,parent,false));
    }

    @Override
    public void onBindViewHolder(CrystalViewHolder holder, int position) {
        Materials materials = materialsList.get(position);
        ItemRss item_rss = new ItemRss();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width_curr = displayMetrics.widthPixels-64;

        int column = width_curr/128;
        int size_per_img = width_curr/column;

        if(width_curr/column > 128){
            size_per_img = 128;
        }

        final int radius_circ = 360;
        final int margin_circ = 0;
        final Transformation transformation_circ = new RoundedCornersTransformation(radius_circ, margin_circ);

        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getItemIcoByName(materials.getInside_name(),context),context)).resize(size_per_img,size_per_img).transform(transformation_circ)
                .error (R.drawable.paimon_full)
                .into (holder.item_icon);

        holder.item_count.setText(prettyCount(materials.getCount()));
    }

    @Override
    public int getItemCount() {
        return materialsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return materialsList.get(position).getType();
    }

    public class CrystalViewHolder extends RecyclerView.ViewHolder {


        public TextView item_count,item_weekly_tv;
        public ImageView item_icon,item_press_mask;

        public CrystalViewHolder(@NonNull View itemView) {
            super(itemView);
            AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
            item_icon = itemView.findViewById(R.id.item_cal_img);
            item_count = itemView.findViewById(R.id.item_cal_tv);
            //item_weekly_tv = itemView.findViewById(R.id.item_weekly_tv);
            item_press_mask = itemView.findViewById(R.id.item_press_mask);
            item_press_mask.startAnimation(buttonClick);
        }
    }
    
    public String prettyCount(Number number) {
        char[] suffix = {' ', 'K', 'M', 'G', 'T', 'P', 'E', 'Z', 'Y'};
        long numValue = number.longValue();
        int value = (int) Math.floor(Math.log10(numValue));
        int base = value / 3;
        String plus = "";
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", MODE_PRIVATE);
        int decimal_num = sharedPreferences.getInt("decimal_num", 0);
        boolean decimal  = sharedPreferences.getBoolean("decimal", false);
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