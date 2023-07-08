package com.voc.genshin_helper.data;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.ui.AlarmUI;
import com.voc.genshin_helper.ui.MMXLVIII.Desk2048;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

/**
 * Created by ankit on 27/10/17.
 */

public class IconCardAdapter extends RecyclerView.Adapter<IconCardAdapter.ViewHolder> {

    private Context context;
    private Activity activity ;
    private View settingView;
    private List<IconCard> iconCardList;
    private AdapterView.OnItemClickListener mListener;
    private WebView webView ;
    private int TYPE = 0;
    private int CARD = 200;
    private int ICON = 201;
    private View viewX;
    private Dialog dialog;


    SharedPreferences sharedPreferences;
    public IconCardAdapter(Context context,List<IconCard> iconCardList, Activity activity, int TYPE, View viewX, Dialog dialog) {
        this.context = context;
        this.activity = activity;
        this.iconCardList = iconCardList;
        this.TYPE = TYPE;
        this.viewX = viewX;
        this.dialog = dialog;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting_icon_card, parent, false);
        ViewHolder evh = new ViewHolder(v, (OnItemClickListener) mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        IconCard iconCard = iconCardList.get(position);

        ItemRss item_rss = new ItemRss();

        final int radius_circ = 360;
        final int margin_circ = 0;
        final Transformation transformation_circ = new RoundedCornersTransformation(radius_circ, margin_circ);

        final int radius_card = 24;
        final int margin_card = 0;
        final Transformation transformation_card = new RoundedCornersTransformation(radius_card, margin_card);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height_curr = displayMetrics.heightPixels;
        int width_curr = displayMetrics.widthPixels;

        int width = width_curr;

        holder.icon_card_tv.setText(item_rss.getCharByName(iconCard.getItemName(),context)[1]);
        holder.baseName = iconCard.getItemBaseName();
        holder.rare = iconCard.getItemRare();

        if (TYPE == CARD){
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                width = (int) (width_curr-16)/3-16;
            }else{
                width = (int) (width_curr-16)/2-16;
            }
            Picasso.get()
                    .load (item_rss.getCharByName(iconCard.getItemBaseName(), context)[4])
                    .transform(transformation_card)
                    .resize((int) width, (int) (width/2.1))
                    .error (R.drawable.unknown_card)
                    .into (holder.icon_card_img);
        }else{
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                width = (int) (width_curr-16)/7-16;
            }else{
                width = (int) (width_curr-16)/4-16;
            }
            Picasso.get()
                    .load (item_rss.getCharByName(iconCard.getItemBaseName(), context)[3])
                    .transform(transformation_circ)
                    .resize((int) width, (int) (width))
                    .error (R.drawable.paimon_lost)
                    .into (holder.icon_card_img);
        }
    }

    @Override
    public int getItemCount() {
        return iconCardList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView icon_card_tv;
        public ImageView icon_card_img;
        public String baseName = "";
        public int rare = 5;


        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            icon_card_tv = itemView.findViewById(R.id.icon_card_tv);
            icon_card_img = itemView.findViewById(R.id.icon_card_img);

            icon_card_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (context instanceof Desk2048){
                        (((Desk2048) context)).update_memo(dialog,viewX,TYPE, baseName, rare);
                    }
                }
            });

        }
    }
    public void filterList(List<IconCard> filteredList) {
        iconCardList = filteredList;
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