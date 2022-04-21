package com.voc.genshin_helper.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.ui.CalculatorUI;
import com.voc.genshin_helper.ui.MMXLVIII.Desk2048;
import com.voc.genshin_helper.ui.MainActivity;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

/**
 * Created by ankit on 27/10/17.
 */

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.ViewHolder> {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    private Context context;
    private List<Characters> charactersList;
    private AdapterView.OnItemClickListener mListener;
    private WebView webView ;
    private Activity activity ;
    private Characters Characters ;
    private ArrayList<Characters> charactersA = new ArrayList<Characters>();

    public CharactersAdapter(Context context, List<Characters> charactersList,Activity activity) {
        this.context = context;
        this.charactersList = charactersList;
        this.activity = activity;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        ViewHolder evh = null;
        // 1) MainActivity's char_list
        // 2) CalculatorUI's char_list

        if(context instanceof MainActivity){
            if(((MainActivity) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("2")){
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char_ico_rectangle_2048, parent, false);
                evh = new ViewHolder(v, (OnItemClickListener) mListener);
            }else if(((MainActivity) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("3")){
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char_ico_square_2048, parent, false);
                evh = new ViewHolder(v, (OnItemClickListener) mListener);
            }else if(((MainActivity) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("4")){
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char_ico_siptik, parent, false);
                evh = new ViewHolder(v, (OnItemClickListener) mListener);
            }else{
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char_ico_square_2048, parent, false);
                evh = new ViewHolder(v, (OnItemClickListener) mListener);
            }

        }else if(context instanceof Desk2048){
            if(((Desk2048) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("2")){
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char_ico_rectangle_2048, parent, false);
                evh = new ViewHolder(v, (OnItemClickListener) mListener);
            }else if(((Desk2048) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("3")){
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char_ico_square_2048, parent, false);
                evh = new ViewHolder(v, (OnItemClickListener) mListener);
            }else if(((Desk2048) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("4")){
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char_ico_siptik, parent, false);
                evh = new ViewHolder(v, (OnItemClickListener) mListener);
            }else{
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char_ico_square_2048, parent, false);
                evh = new ViewHolder(v, (OnItemClickListener) mListener);
            }

        }else if(context instanceof CalculatorUI){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char_ico_square_2048, parent, false);
            evh = new ViewHolder(v, (OnItemClickListener) mListener);
        }

        return evh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        this.Characters = charactersList.get(position);
        ItemRss item_rss = new ItemRss();
        int width = 0, height = 0;
        int count = 3;

        int radius = 80;
        int margin = 0;
        Transformation transformation = new RoundedCornersTransformation(radius, margin);

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            transformation= new RoundedCornersTransformation(80, 0);
        }


        final int radius_circ = 360;
        final int margin_circ = 0;
        final Transformation transformation_circ = new RoundedCornersTransformation(radius_circ, margin_circ);
        final int radius_circ_siptik = 80;
        final int margin_circ_siptik = 0;
        final Transformation transformation_circ_siptik = new RoundedCornersTransformation(radius_circ_siptik, margin_circ_siptik);
        final int radius_circ_siptik_ico = 120;
        final int margin_circ_siptik_ico = 0;
        final Transformation transformation_circ_siptik_ico = new RoundedCornersTransformation(radius_circ_siptik_ico, margin_circ_siptik_ico);

        charactersA.add(Characters);

        holder.char_name.setText(Characters.getName());
        holder.char_base_name.setText(Characters.getName());

        if(Characters.getRare() == 4){
            holder.char_small_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);
        }else{
            holder.char_small_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);
        }

        if(Characters.getIsComing() == 0){holder.char_isComing.setVisibility(View.GONE);}
        if(Characters.getIsComing() == 1){holder.char_isComing.setVisibility(View.VISIBLE);}

        if(Characters.getRare() >3 && Characters.getRare() < 6){
            holder.char_star.setNumStars(Characters.getRare());
            holder.char_star.setRating(Characters.getRare());
        }

        boolean isNight = false;
        // Background of item
        if (context.getResources().getString(R.string.mode).equals("Night")) {
            isNight = true;
        }


        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height_curr = displayMetrics.heightPixels;
        int width_curr = displayMetrics.widthPixels;
        int curr = width_curr;

        int size_per_img = width_curr/2;
        int size_per_img_sq = width_curr/3;
        int size_per_img_siptik = width_curr;

        if(context.getSharedPreferences("user_info",MODE_PRIVATE).getString("curr_ui_grid", "2").equals("2") ){
            switch (Characters.getElement()){
                case "Anemo" : {
                    holder.char_element.setImageResource(R.drawable.anemo_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.anemo_800x1600_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.anemo_800x1600_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.anemo_800x1600_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.anemo_800x1600_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }

                case "Cryo" : {
                    holder.char_element.setImageResource(R.drawable.cryo_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.cryo_800x1600_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.cryo_800x1600_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.cryo_800x1600_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.cryo_800x1600_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }

                case "Dendro" : {
                    holder.char_element.setImageResource(R.drawable.dendro_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.dendro_800x1600_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.dendro_800x1600_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.dendro_800x1600_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.dendro_800x1600_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }

                case "Electro" : {
                    holder.char_element.setImageResource(R.drawable.electro_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.electro_800x1600_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.electro_800x1600_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.electro_800x1600_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.electro_800x1600_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }

                case "Geo" : {
                    holder.char_element.setImageResource(R.drawable.geo_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.geo_800x1600_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.geo_800x1600_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.geo_800x1600_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.geo_800x1600_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }

                case "Hydro" : {
                    holder.char_element.setImageResource(R.drawable.hydro_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.hydro_800x1600_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.hydro_800x1600_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.hydro_800x1600_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.hydro_800x1600_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }

                case "Pyro" : {
                    holder.char_element.setImageResource(R.drawable.pyro_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.pyro_800x1600_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.pyro_800x1600_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.pyro_800x1600_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.pyro_800x1600_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }


            }
        }else if(context.getSharedPreferences("user_info",MODE_PRIVATE).getString("curr_ui_grid", "2").equals("3") ){
            switch (Characters.getElement()){
                case "Anemo" : {
                    holder.char_element.setImageResource(R.drawable.anemo_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.anemo_800x1000_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.anemo_800x1000_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.anemo_800x1000_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.anemo_800x1000_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }

                case "Cryo" : {
                    holder.char_element.setImageResource(R.drawable.cryo_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.cryo_800x1000_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.cryo_800x1000_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.cryo_800x1000_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.cryo_800x1000_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }

                case "Dendro" : {
                    holder.char_element.setImageResource(R.drawable.dendro_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.dendro_800x1000_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.dendro_800x1000_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.dendro_800x1000_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.dendro_800x1000_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }

                case "Electro" : {
                    holder.char_element.setImageResource(R.drawable.electro_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.electro_800x1000_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.electro_800x1000_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.electro_800x1000_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.electro_800x1000_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }

                case "Geo" : {
                    holder.char_element.setImageResource(R.drawable.geo_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.geo_800x1000_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.geo_800x1000_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.geo_800x1000_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.geo_800x1000_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }

                case "Hydro" : {
                    holder.char_element.setImageResource(R.drawable.hydro_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.hydro_800x1000_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.hydro_800x1000_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.hydro_800x1000_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.hydro_800x1000_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }

                case "Pyro" : {
                    holder.char_element.setImageResource(R.drawable.pyro_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.pyro_800x1000_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.pyro_800x1000_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.pyro_800x1000_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.pyro_800x1000_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }


            }
        }else if(context.getSharedPreferences("user_info",MODE_PRIVATE).getString("curr_ui_grid", "2").equals("4") ){

            switch (Characters.getElement()){
                case "Anemo" : {
                    holder.char_element.setImageResource(R.drawable.anemo_ico);
                    break;
                }

                case "Cryo" : {
                    holder.char_element.setImageResource(R.drawable.cryo_ico);
                    break;
                }

                case "Dendro" : {
                    holder.char_element.setImageResource(R.drawable.dendro_ico);
                    break;
                }

                case "Electro" : {
                    holder.char_element.setImageResource(R.drawable.electro_ico);
                    break;
                }

                case "Geo" : {
                    holder.char_element.setImageResource(R.drawable.geo_ico);
                    break;
                }

                case "Hydro" : {
                    holder.char_element.setImageResource(R.drawable.hydro_ico);
                    break;
                }

                case "Pyro" : {
                    holder.char_element.setImageResource(R.drawable.pyro_ico);
                    break;
                }
            }

            Picasso.get()
                    .load (FileLoader.loadIMG(item_rss.getCharByName(Characters.getName(),context)[4],context)).resize((int) (width_curr),(int) ((width_curr)/2.1))//.transform(transformation_circ_siptik)
                    .error (R.drawable.paimon_lost)
                    .into(holder.char_card_bg);

            int frame = R.drawable.bg_day_frame;

            if (isNight){
                frame = R.drawable.bg_night_frame;
            }

            Picasso.get()
                    .load (frame).resize((int) (width_curr),(int) ((width_curr)/2.1))//.transform(transformation_circ_siptik)
                    .error (frame)
                    .into (holder.char_card_mask);

            holder.char_card_bg.setPadding(8,8,8,8);
            holder.char_card_mask.setPadding(8,8,8,8);
            holder.char_press_mask.setPadding(8,8,8,8);
            holder.char_card.setPadding(8,8,8,8);

        }else{
            switch (Characters.getElement()){
                case "Anemo" : {
                    holder.char_element.setImageResource(R.drawable.anemo_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.anemo_800x1000_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.anemo_800x1000_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.anemo_800x1000_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.anemo_800x1000_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }

                case "Cryo" : {
                    holder.char_element.setImageResource(R.drawable.cryo_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.cryo_800x1000_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.cryo_800x1000_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.cryo_800x1000_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.cryo_800x1000_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }

                case "Dendro" : {
                    holder.char_element.setImageResource(R.drawable.dendro_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.dendro_800x1000_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.dendro_800x1000_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.dendro_800x1000_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.dendro_800x1000_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }

                case "Electro" : {
                    holder.char_element.setImageResource(R.drawable.electro_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.electro_800x1000_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.electro_800x1000_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.electro_800x1000_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.electro_800x1000_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }

                case "Geo" : {
                    holder.char_element.setImageResource(R.drawable.geo_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.geo_800x1000_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.geo_800x1000_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.geo_800x1000_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.geo_800x1000_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }

                case "Hydro" : {
                    holder.char_element.setImageResource(R.drawable.hydro_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.hydro_800x1000_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.hydro_800x1000_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.hydro_800x1000_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.hydro_800x1000_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }

                case "Pyro" : {
                    holder.char_element.setImageResource(R.drawable.pyro_ico);
                    if(isNight == true){
                        holder.char_bg.setBackgroundResource(R.drawable.pyro_800x1000_dark);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.pyro_800x1000_dark_mask);
                        holder.char_bg.setForeground(drawable);
                    }else{
                        holder.char_bg.setBackgroundResource(R.drawable.pyro_800x1000_light);
                        Drawable drawable = context.getResources().getDrawable(R.drawable.pyro_800x1000_light_mask);
                        holder.char_bg.setForeground(drawable);
                    }
                    break;
                }


            }
        }

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            size_per_img = 480;
            size_per_img_sq = 400;
            size_per_img_siptik = 960;
        }

        if(context instanceof MainActivity){
            if (((MainActivity) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("2")) {
                if(width_curr / ((int)width_curr/size_per_img+1) > size_per_img){
                    width = (width_curr) / ((int)width_curr/size_per_img+1);
                    height = (width * 14) / 8;
                    holder.char_name_ll.getLayoutParams().height = (width * 2) / 8;

                }else{
                    width = size_per_img;
                    height = (width * 14) / 8;
                    holder.char_name_ll.getLayoutParams().width = size_per_img;
                    holder.char_name_ll.getLayoutParams().height = (width * 2) / 8;
                }
            } else if (((MainActivity) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("3")) {
                if(width_curr / ((int)width_curr/size_per_img_sq+1) > size_per_img_sq){
                    width = (width_curr) / ((int)width_curr/size_per_img_sq+1);
                    height = (width_curr) / ((int)width_curr/size_per_img_sq+1);
                    holder.char_name_ll.getLayoutParams().height = (width * 2) / 5;
                }else{
                    width = size_per_img_sq;
                    height = size_per_img_sq;
                    holder.char_name_ll.getLayoutParams().height = (width * 2) / 5;
                }
            } else if (((MainActivity) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("4")) {
                if(width_curr / ((int)width_curr/size_per_img_siptik+1) > size_per_img_siptik){
                    width = (width_curr) / ((int)width_curr/size_per_img_siptik+1);
                    height = (int) ((width) / 2.1);

                }else{
                    width = (width_curr) / (int) (width_curr/size_per_img_siptik);
                    height = (int) ((width) / 2.1);
                }

                holder.char_card_bg.getLayoutParams().width = width;
                holder.char_card_bg.getLayoutParams().height = height;
                holder.char_card_mask.getLayoutParams().width = width;
                holder.char_card_mask.getLayoutParams().height = height;
                holder.char_card.getLayoutParams().width = width-36;
                holder.char_card.getLayoutParams().height = height-36;
            }


        }else if(context instanceof Desk2048){
            if (((Desk2048) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("2")) {
                if(width_curr / ((int)width_curr/size_per_img+1) > size_per_img){
                    width = (width_curr) / ((int)width_curr/size_per_img+1);
                    height = (width * 14) / 8;
                    holder.char_name_ll.getLayoutParams().height = (width * 2) / 8;

                }else{
                    width = size_per_img;
                    height = (width * 14) / 8;
                    holder.char_name_ll.getLayoutParams().width = size_per_img;
                    holder.char_name_ll.getLayoutParams().height = (width * 2) / 8;
                }
            } else if (((Desk2048) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("3")) {
                if(width_curr / ((int)width_curr/size_per_img_sq+1) > size_per_img_sq){
                    width = (width_curr) / ((int)width_curr/size_per_img_sq+1);
                    height = (width_curr) / ((int)width_curr/size_per_img_sq+1);
                    holder.char_name_ll.getLayoutParams().height = (width * 2) / 5;
                }else{
                    width = size_per_img_sq;
                    height = size_per_img_sq;
                    holder.char_name_ll.getLayoutParams().height = (width * 2) / 5;
                }
            } else if (((Desk2048) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("4")) {
                if(width_curr / ((int)width_curr/size_per_img_siptik+1) > size_per_img_siptik){
                    width = (width_curr) / ((int)width_curr/size_per_img_siptik+1);
                    height = (int) ((width) / 2.1);

                }else{
                    width = (width_curr) / (int) (width_curr/size_per_img_siptik);
                    height = (int) ((width) / 2.1);
                }

                holder.char_card_bg.getLayoutParams().width = width;
                holder.char_card_bg.getLayoutParams().height = height;
                holder.char_card_mask.getLayoutParams().width = width;
                holder.char_card_mask.getLayoutParams().height = height;
                holder.char_card.getLayoutParams().width = width-36;
                holder.char_card.getLayoutParams().height = height-36;
            }


        }else if(context instanceof CalculatorUI){
            if(width_curr / ((int)width_curr/size_per_img_sq+1) > size_per_img_sq){
                width = (width_curr) / ((int)width_curr/size_per_img_sq+1);
                height = (width_curr) / ((int)width_curr/size_per_img_sq+1);
                holder.char_name_ll.getLayoutParams().height = (width * 2) / 5;
            }else{
                width = size_per_img_sq;
                height = size_per_img_sq;
                holder.char_name_ll.getLayoutParams().height = (width * 2) / 5;
            }
        }


        /*
        if(Characters.getElement().equals("Anemo")){holder.char_element.setImageResource(R.drawable.anemo);holder.char_bg.setBackgroundResource(R.drawable.bg_anemo_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_anemo_char);}
        if(Characters.getElement().equals("Cryo")){holder.char_element.setImageResource(R.drawable.cryo);holder.char_bg.setBackgroundResource(R.drawable.bg_cryo_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_cryo_char);}
        if(Characters.getElement().equals("Electro")){holder.char_element.setImageResource(R.drawable.electro);holder.char_bg.setBackgroundResource(R.drawable.bg_electro_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_electro_char);}
        if(Characters.getElement().equals("Geo")){holder.char_element.setImageResource(R.drawable.geo);holder.char_bg.setBackgroundResource(R.drawable.bg_geo_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_geo_char);}
        if(Characters.getElement().equals("Hydro")){holder.char_element.setImageResource(R.drawable.hydro);holder.char_bg.setBackgroundResource(R.drawable.bg_hydro_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_hydro_char);}
        if(Characters.getElement().equals("Pyro")){holder.char_element.setImageResource(R.drawable.pyro);holder.char_bg.setBackgroundResource(R.drawable.bg_pyro_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_pyro_char);}
        if(Characters.getElement().equals("Dendro")){holder.char_element.setImageResource(R.drawable.dendro);holder.char_bg.setBackgroundResource(R.drawable.bg_dendro_bg);holder.char_nl.setBackgroundResource(R.drawable.bg_dendro_char);}

         */

        holder.char_icon.getLayoutParams().width = width;
        holder.char_icon.getLayoutParams().height = height;

        Drawable drawable = context.getResources().getDrawable(R.drawable.item_selected_circle_effect);
        //holder.char_press_mask.setForeground(drawable);

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && context.getSharedPreferences("user_info",MODE_PRIVATE).getString("curr_ui_grid", "2").equals("3")){
            holder.char_icon.setPadding(48,48,48,0);
        }

        //Bitmap bitmap ;
        //Bitmap outBitmap ;
        // Already knew that IMG size is not the main reason of main_list lag
        //bitmap = BitmapFactory.decodeResource(context.getResources(),item_rss.getCharByName(Characters.getName())[0]);
        //outBitmap =getRoundBitmapByShader(bitmap, (int) Math.round(width/2),(int)Math.round(height/2),20, 0);

        holder.char_small_ico.setMaxWidth((int) (width/3.25));
        holder.char_small_ico.setMaxHeight((int) (width/3.25));

        holder.char_small_ico.setVisibility(View.GONE);

        if(context instanceof MainActivity){
            if (((MainActivity) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("2")) {
                holder.char_small_ico.setVisibility(View.VISIBLE);
                Picasso.get()
                        .load (FileLoader.loadIMG(item_rss.getCharByName(Characters.getName(),context)[3],context)).resize((int) (width/3.25),(int) (width/3.25)).transform(transformation_circ)
                        .error (R.drawable.paimon_lost)
                        .into (holder.char_small_ico);
                Picasso.get()
                        .load (FileLoader.loadIMG(item_rss.getCharByName(Characters.getName(),context)[0],context)).fit().centerCrop().transform(transformation)
                        .error (R.drawable.paimon_full)
                        .into (holder.char_icon);
            } else if (((MainActivity) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("3")) {
                Picasso.get()
                        .load (FileLoader.loadIMG(item_rss.getCharByName(Characters.getName(),context)[3],context)).resize((int) (width/1),(int) (width/1)).transform(transformation_circ)
                        .error (R.drawable.paimon_full)
                        .into (holder.char_icon);
            } else if (((MainActivity) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("4")) {
                holder.char_icon.getLayoutParams().width = 96*width/315;
                holder.char_icon.getLayoutParams().height = 96*width/315;
                Picasso.get()
                        .load (FileLoader.loadIMG(item_rss.getCharByName(Characters.getName(),context)[3],context)).resize(96*width/315,96*width/315).transform(transformation_circ_siptik_ico)
                        .error (R.drawable.paimon_full)
                        .into (holder.char_icon);
            }
        }else if(context instanceof Desk2048){

            if (((Desk2048) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("2")) {
                holder.char_small_ico.setVisibility(View.VISIBLE);
                Picasso.get()
                        .load (FileLoader.loadIMG(item_rss.getCharByName(Characters.getName(),context)[3],context)).resize((int) (width/3.25),(int) (width/3.25)).transform(transformation_circ)
                        .error (R.drawable.paimon_lost)
                        .into (holder.char_small_ico);
                Picasso.get()
                        .load (FileLoader.loadIMG(item_rss.getCharByName(Characters.getName(),context)[0],context)).fit().centerCrop().transform(transformation)
                        .error (R.drawable.paimon_full)
                        .into (holder.char_icon);
            } else if (((Desk2048) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("3")) {
                Picasso.get()
                        .load (FileLoader.loadIMG(item_rss.getCharByName(Characters.getName(),context)[3],context)).resize((int) (width/1),(int) (width/1)).transform(transformation_circ)
                        .error (R.drawable.paimon_full)
                        .into (holder.char_icon);
            } else if (((Desk2048) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("4")) {
                holder.char_icon.getLayoutParams().width = 96*width/315;
                holder.char_icon.getLayoutParams().height = 96*width/315;
                Picasso.get()
                        .load (FileLoader.loadIMG(item_rss.getCharByName(Characters.getName(),context)[3],context)).resize(96*width/315,96*width/315).transform(transformation_circ_siptik_ico)
                        .error (R.drawable.paimon_full)
                        .into (holder.char_icon);
            }
        }else if(context instanceof CalculatorUI){
            Picasso.get()
                    .load (FileLoader.loadIMG(item_rss.getCharByName(Characters.getName(),context)[3],context)).resize((int) (width/1),(int) (width/1)).transform(transformation_circ)
                    .error (R.drawable.paimon_full)
                    .into (holder.char_icon);

        }

        holder.char_name.setText(item_rss.getCharByName(Characters.getName(),context)[1]);
        holder.char_weapon.setImageResource(item_rss.getWeaponTypeIMG(Characters.getWeapon()));
        holder.char_role.setText(item_rss.getLocaleName(Characters.getRole(),context));
        holder.char_main_stat.setText(item_rss.getArtifactBuffName(Characters.getMainStat(),context));

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

        System.out.println("ITEM WIDTH * HEIGH = " + width +"*" + height +" || SCREEN WIDTH * HEIGH = "+ width_curr +"*" + height_curr+" || MASK WIDTH * HEIGH = "+ holder.char_bg.getWidth() +"*" + holder.char_bg.getHeight());

    }

    @Override
    public int getItemCount() {
        return charactersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView char_name,char_base_name,char_role,char_main_stat;
        public ImageView char_icon,char_small_ico,char_isComing,char_element,char_press_mask,char_weapon;
        //public LinearLayout char_nl;
        public RatingBar char_star;
        public ConstraintLayout char_bg;
        public ImageView char_card_bg;
        public ImageView char_card_mask;
        public LinearLayout char_name_ll;
        public CardView char_card;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            char_icon = itemView.findViewById(R.id.char_img);
            char_small_ico = itemView.findViewById(R.id.char_small_ico);
            char_name = itemView.findViewById(R.id.char_name);
            char_star = itemView.findViewById(R.id.char_star);
            char_element = itemView.findViewById(R.id.char_element);
            char_isComing = itemView.findViewById(R.id.char_is_coming);
            char_base_name = itemView.findViewById(R.id.char_base_name);
            //char_nl = itemView.findViewById(R.id.char_nl);
            char_bg = itemView.findViewById(R.id.char_bg);

            char_press_mask = itemView.findViewById(R.id.char_press_mask);
            char_name_ll = itemView.findViewById(R.id.char_name_ll);
            char_weapon = itemView.findViewById(R.id.char_weapon);
            char_role = itemView.findViewById(R.id.char_role);
            char_main_stat = itemView.findViewById(R.id.char_main_stat);
            char_card_bg = itemView.findViewById(R.id.char_card_bg);
            char_card_mask = itemView.findViewById(R.id.char_card_mask);
            char_card = itemView.findViewById(R.id.char_card);
            char_press_mask.startAnimation(buttonClick);


            // Release at 2.7.0
            /*
            char_press_mask.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (context instanceof MainActivity){Log.wtf("YES","IT's");
                        if (charactersList.get(getAdapterPosition()).getName().equals(char_base_name.getText())){
                            (((MainActivity) context)).runSipTikCal(charactersA.get(getAdapterPosition()),activity);
                        }

                    }
                    return false;
                }
            });
             */

            char_press_mask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.wtf("is context instanceof MainActivity ?",context.getPackageName());
                    if (context instanceof MainActivity){Log.wtf("YES","IT's");
                        (((MainActivity) context)).startInfo(String.valueOf(char_base_name.getText()),activity);

                    }else if (context instanceof Desk2048){Log.wtf("YES","IT's");
                        (((Desk2048) context)).startInfo(String.valueOf(char_base_name.getText()),activity);

                    }
                    else if (context instanceof CalculatorUI){Log.wtf("YES","IT's");
                        ArrayList<String> nameList = (((CalculatorUI) context)).checkNameList();
                        boolean have = false;
                        String name = String.valueOf(char_base_name.getText());

                        if(nameList.contains(name.replace("_"," "))){
                            have = true;
                        }

                        /*
                        if(char_isComing.getVisibility() == View.GONE) {
                            if (have == false) {
                                (((CalculatorUI) context)).charQuestion(String.valueOf(char_base_name.getText()), "ADD", 0);
                            } else {
                                Toast.makeText(((CalculatorUI) context), "You have already set this character !", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(((CalculatorUI) context), "暫時沒有他/她的相關資料,無法計算", Toast.LENGTH_SHORT).show();
                        }

                         */

                        if (have == false) {
                            (((CalculatorUI) context)).charQuestion(String.valueOf(char_base_name.getText()), "ADD", 0);
                        } else {
                            //Toast.makeText(((CalculatorUI) context), context.getString(R.string.cal_choosed_already), Toast.LENGTH_SHORT).show();
                            CustomToast.toast(context,view,context.getString(R.string.cal_choosed_already));
                        }
                    }

                }
            });

        }
    }




    public void filterList(List<Characters> filteredList) {
        charactersList = filteredList;
        notifyDataSetChanged();
    }

}