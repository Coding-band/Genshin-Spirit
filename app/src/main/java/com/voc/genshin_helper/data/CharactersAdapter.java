package com.voc.genshin_helper.data;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
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

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.buff.BuffMainUI;
import com.voc.genshin_helper.buff.obj.BuffObject;
import com.voc.genshin_helper.buff.obj.Character;
import com.voc.genshin_helper.ui.CalculatorUI;
import com.voc.genshin_helper.ui.MMXLVIII.Calculator2048;
import com.voc.genshin_helper.ui.MMXLVIII.Desk2048;
import com.voc.genshin_helper.ui.MainActivity;
import com.voc.genshin_helper.ui.SipTik.CalculatorDB_SipTik;
import com.voc.genshin_helper.ui.SipTik.DeskSipTik;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
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
    private SharedPreferences sharedPreferences;
    private ArrayList<BuffObject> enkaBuffObject = null;
    private ArrayList<BuffObject> buffObject = null;

    private Dialog dialogFromBuffMainUI = null;

    public CharactersAdapter(Context context, List<Characters> charactersList,Activity activity, SharedPreferences sharedPreferences) {
        this.context = context;
        this.charactersList = charactersList;
        this.activity = activity;
        this.sharedPreferences = sharedPreferences;

        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("user_name",MODE_PRIVATE);
        }
    }

    public CharactersAdapter(Context context, List<Characters> charactersList,Activity activity, SharedPreferences sharedPreferences, ArrayList<BuffObject> enkaBuffObject, ArrayList<BuffObject> buffObject, Dialog dialog) {
        this.context = context;
        this.charactersList = charactersList;
        this.activity = activity;
        this.sharedPreferences = sharedPreferences;
        this.buffObject = buffObject;
        this.enkaBuffObject = enkaBuffObject;
        this.dialogFromBuffMainUI = dialog;

        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("user_name",MODE_PRIVATE);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char_ico_square_2048, parent, false);
        // 1) MainActivity's char_list
        // 2) CalculatorUI's char_list

        if(context instanceof BuffMainUI){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char_ico_square_2048, parent, false);
            return new ViewHolder(v, (OnItemClickListener) mListener);
        }else{
            switch (sharedPreferences.getString("curr_ui_grid", "2")) {
                case "2":
                    v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char_ico_rectangle_2048, parent, false);
                    break;
                case "3":
                    v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char_ico_square_2048, parent, false);
                    break;
                case "4":
                    v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char_ico_card_siptik, parent, false);
                    break;
                case "5":
                    v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char_ico_detail_siptik, parent, false);
                    break;
                default:
                    v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char_ico_square_2048, parent, false);
                    break;
            }
        }

        return new ViewHolder(v, (OnItemClickListener) mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        this.Characters = charactersList.get(position);
        holder.isComing = Characters.getIsComing();
        ItemRss item_rss = new ItemRss();
        int width = 0, height = 0;
        int count = 3;

        int radius = 80;
        int margin = 0;
        Transformation transformation = new RoundedCornersTransformation(radius, margin);

        final int radius_circ = 360;
        final int margin_circ = 0;
        final Transformation transformation_circ = new RoundedCornersTransformation(radius_circ, margin_circ);
        final int radius_circ_siptik = 80;
        final int margin_circ_siptik = 0;
        final Transformation transformation_circ_siptik = new RoundedCornersTransformation(radius_circ_siptik, margin_circ_siptik);
        final int radius_circ_siptik_ico = 30;
        final int margin_circ_siptik_ico = 0;
        final Transformation transformation_circ_siptik_ico = new RoundedCornersTransformation(radius_circ_siptik_ico, margin_circ_siptik_ico);

        String gridValue = (context instanceof BuffMainUI ? "3" : sharedPreferences.getString("curr_ui_grid", "2"));
        charactersA.add(Characters);
        holder.select_char = Characters;
        holder.char_name.setText(Characters.getName());
        holder.char_base_name.setText(Characters.getName());

        if(Characters.getRare() == 4){
            Picasso.get().load(R.drawable.item_char_list_bg_circ_4s).fit().into(holder.char_small_ico);
            //holder.char_small_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);
        }else if (Characters.getRare() == 5){
            Picasso.get().load(R.drawable.item_char_list_bg_circ_5s).fit().into(holder.char_small_ico);
            //holder.char_small_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);
        }else{
            Picasso.get().load(R.drawable.item_char_list_bg_circ_0s).fit().into(holder.char_small_ico);
            //holder.char_small_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_0s);
        }

        if(Characters.getIsComing() == 0){holder.char_isComing.setVisibility(View.GONE);}
        if(Characters.getIsComing() == 1){holder.char_isComing.setVisibility(View.VISIBLE);}

        if(Characters.getRare() >3 && Characters.getRare() < 6){
            holder.char_star.setNumStars(Characters.getRare());
            holder.char_star.setRating(Characters.getRare());
        }

        if(holder.char_tick != null){
            for (int k = 0 ; k < 8 ; k++){
                if(buffObject == null) break;
                if (buffObject.size()-1 >= k){
                    if(buffObject.get(k).getCharacter().getCharName().equals(Characters.getName())){holder.char_tick.setVisibility(View.VISIBLE);System.out.println("HIII");}
                }
                if (enkaBuffObject == null) break;
                if (enkaBuffObject.size()-1 >= k){
                    if(enkaBuffObject.get(k).getCharacter().getCharName().equals(Characters.getName())){holder.char_tick.setVisibility(View.VISIBLE);}
                }
            }
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

        Target target_char_bg = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                holder.char_bg.setBackground(new BitmapDrawable(bitmap));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        Target target_char_element = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                holder.char_element.setBackground(new BitmapDrawable(bitmap));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        switch (gridValue){
            default:
            case "2":{
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
                            //Picasso.get().load(R.drawable.cryo_800x1600_light).fit().into(target_char_bg);
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
                break;
            }
            case "3":{
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
                    //<clip-path
                    //                    android:pathData="M800 1520c0 44.18-35.82 80-80 80V0c44.18 0 80 35.82 80 80v1440Z"/>
                    //
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
                break;
            }
            case "4":{
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
                        .load (item_rss.getCharByName(Characters.getName(),context)[4]).resize((int) (width_curr),(int) ((width_curr)/2.1))//.transform(transformation_circ_siptik)
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
                holder.char_press_mask.setPadding(8,8,8,8);
                holder.char_card.setPadding(8,8,8,8);
                break;
            }
            case "5":{
                switch (Characters.getRare()){
                    case 1 : holder.char_icon.setBackgroundResource(R.drawable.bg_rare1_char_siptik);break;
                    case 2 : holder.char_icon.setBackgroundResource(R.drawable.bg_rare2_char_siptik);break;
                    case 3 : holder.char_icon.setBackgroundResource(R.drawable.bg_rare3_char_siptik);break;
                    case 4 : holder.char_icon.setBackgroundResource(R.drawable.bg_rare4_char_siptik);break;
                    case 5 : holder.char_icon.setBackgroundResource(R.drawable.bg_rare5_char_siptik);break;
                    default:  holder.char_icon.setBackgroundResource(R.drawable.bg_rare1_char_siptik);break;
                }
                holder.char_star.setVisibility(View.GONE);
                holder.char_star_ll.setVisibility(View.GONE);
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
                        .load (item_rss.getCharByName(Characters.getName(),context)[4]).resize((int) (width_curr),(int) ((width_curr)/2.1))//.transform(transformation_circ_siptik)
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

                //holder.char_card_bg.setPadding(8,8,8,8);
                //holder.char_card_mask.setPadding(8,8,8,8);
                holder.char_press_mask.setPadding(8,8,8,8);
                holder.char_cbg.setPadding(8,8,8,8);
                break;
            }
        }

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            size_per_img = 480;
            size_per_img_sq = 400;
            size_per_img_siptik = 640;
        }

        holder.char_small_ico.setVisibility(View.GONE);

        switch (gridValue){
            default:
            case "2":{
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

                holder.char_small_ico.setVisibility(View.VISIBLE);
                Picasso.get()
                        .load (item_rss.getCharByName(Characters.getName(),context)[3]).resize((int) (width/3.25),(int) (width/3.25)).transform(transformation_circ)
                        .error (R.drawable.paimon_lost)
                        .into (holder.char_small_ico);
                Picasso.get()
                        .load (item_rss.getCharByName(Characters.getName(),context)[0]).resize((int)(width/1.1),(int)(height/1.1)).centerCrop().transform(transformation)
                        .error (R.drawable.paimon_full)
                        .into (holder.char_icon);
                holder.char_icon.getLayoutParams().width = width;
                holder.char_icon.getLayoutParams().height = height;

                holder.char_small_ico.getLayoutParams().width = (int) (width/3.25);
                holder.char_small_ico.getLayoutParams().height = (int) (width/3.25);
                break;
            }
            case "3":{
                if(width_curr / ((int)width_curr/size_per_img_sq+1) > size_per_img_sq){
                    width = (width_curr) / ((int)width_curr/size_per_img_sq+1);
                    height = (width_curr) / ((int)width_curr/size_per_img_sq+1);
                    holder.char_name_ll.getLayoutParams().height = (width * 2) / 5;
                }else{
                    width = size_per_img_sq;
                    height = size_per_img_sq;
                    holder.char_name_ll.getLayoutParams().height = (width * 2) / 5;
                }

                Picasso.get()
                        .load (item_rss.getCharByName(Characters.getName(),context)[3]).resize((int) (width/1),(int) (width/1)).transform(transformation_circ)
                        .error (R.drawable.paimon_full)
                        .into (holder.char_icon);
                break;
            }
            case "4":{
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
                holder.char_card.getLayoutParams().width = width-16;
                holder.char_card.getLayoutParams().height = height-16;

                Picasso.get()
                        .load (item_rss.getCharByName(Characters.getName(),context)[3]).resize(96*width/315,96*width/315).transform(transformation_circ_siptik_ico)
                        .error (R.drawable.paimon_full)
                        .into (holder.char_icon);
                holder.char_icon.getLayoutParams().width = 96*width/315;
                holder.char_icon.getLayoutParams().height = 96*width/315;
                break;
            }
            case "5":{
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
                holder.char_cbg.getLayoutParams().width = width-16;
                holder.char_cbg.getLayoutParams().height = height-16;

                Picasso.get()
                        .load (item_rss.getCharByName(Characters.getName(),context)[3]).resize(96*width/315,96*width/315).transform(transformation_circ_siptik_ico)
                        .error (R.drawable.paimon_full)
                        .into (holder.char_icon);
                holder.char_icon.getLayoutParams().width = 96*width/315;
                holder.char_icon.getLayoutParams().height = 96*width/315;

                holder.char_region.setText(item_rss.getLocaleName(Characters.getNation(),context));
                holder.char_region_img.setImageResource(item_rss.getDistrictIMG(Characters.getNation()));
                break;
            }
        }

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && gridValue.equals("3")){
            holder.char_icon.setPadding(48,48,48,0);
        }

        holder.char_name.setText(item_rss.getCharByName(Characters.getName(),context)[1]);
        holder.char_weapon.setImageResource(item_rss.getWeaponTypeIMG(Characters.getWeapon(),context));
        //holder.char_role.setText(item_rss.getLocaleName(Characters.getRole(),context));
        holder.char_main_stat.setText(item_rss.getArtifactBuffName(Characters.getMainStat(),context));
    }

    @Override
    public int getItemCount() {
        return charactersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView char_name,char_base_name,char_main_stat;
        public ImageView char_icon,char_small_ico,char_isComing,char_element,char_press_mask,char_weapon;
        //public LinearLayout char_nl;
        public RatingBar char_star;
        public LinearLayout char_star_ll;
        public ConstraintLayout char_bg;
        public ImageView char_card_bg;
        public ImageView char_card_mask;
        public LinearLayout char_name_ll;
        public CardView char_card;
        public ImageView char_region_img ;
        public TextView char_region ;
        public LinearLayout char_cbg;

        public ImageView char_tick;

        public Characters select_char = null;

        public Character characterFinal = new Character();
        public int isComing = 0;

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
            char_star_ll = itemView.findViewById(R.id.char_star_ll);
            char_cbg = itemView.findViewById(R.id.char_cbg);
            char_tick = itemView.findViewById(R.id.char_tick);

            if (itemView.findViewById(R.id.char_region) != null && itemView.findViewById(R.id.char_region_img) != null){
                char_region = itemView.findViewById(R.id.char_region);
                char_region_img = itemView.findViewById(R.id.char_region_img);
            }

            char_press_mask = itemView.findViewById(R.id.char_press_mask);
            char_name_ll = itemView.findViewById(R.id.char_name_ll);
            char_weapon = itemView.findViewById(R.id.char_weapon);
            //char_role = itemView.findViewById(R.id.char_role);
            char_main_stat = itemView.findViewById(R.id.char_main_stat);
            char_card_bg = itemView.findViewById(R.id.char_card_bg);
            char_card_mask = itemView.findViewById(R.id.char_card_mask);
            char_card = itemView.findViewById(R.id.char_card);
            char_press_mask.startAnimation(buttonClick);

            char_press_mask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isComing == 1) {CustomToast.toast(context, view, context.getString(R.string.unreleased)); return;}
                    if (context instanceof MainActivity){
                        (((MainActivity) context)).startInfo(String.valueOf(char_base_name.getText()),activity);
                    }else if (context instanceof Desk2048){
                        (((Desk2048) context)).startCharInfo(String.valueOf(char_base_name.getText()),activity);
                    }else if (context instanceof DeskSipTik){
                        (((DeskSipTik) context)).startCharInfo(String.valueOf(char_base_name.getText()),activity);
                    }else if (context instanceof Calculator2048){
                        ArrayList<String> nameList = (((Calculator2048) context)).choosedNameList;
                        boolean have = false;
                        String name = String.valueOf(char_base_name.getText());

                        if(nameList.contains(name.replace("_"," "))){
                            have = true;
                        }

                        if (!have) {
                            (((Calculator2048) context)).addChar(String.valueOf(char_base_name.getText()));
                        } else {
                            CustomToast.toast(context,view,context.getString(R.string.cal_choosed_already));
                        }
                    }else if (context instanceof CalculatorDB_SipTik){
                        ArrayList<String> nameList = (((CalculatorDB_SipTik) context)).choosedNameList;
                        boolean have = false;
                        String name = String.valueOf(char_base_name.getText());

                        if(nameList.contains(name.replace("_"," "))){
                            have = true;
                        }

                        if (!have) {
                            (((CalculatorDB_SipTik) context)).addItem(String.valueOf(char_base_name.getText()),5,"CHAR");
                        } else {
                            CustomToast.toast(context,view,context.getString(R.string.cal_choosed_already));
                        }
                    }
                    else if (context instanceof CalculatorUI){
                        ArrayList<String> nameList = (((CalculatorUI) context)).checkNameList();
                        boolean have = false;
                        String name = String.valueOf(char_base_name.getText());

                        if(nameList.contains(name.replace("_"," "))){
                            have = true;
                        }

                        if (have == false) {
                            (((CalculatorUI) context)).charQuestion(String.valueOf(char_base_name.getText()), "ADD", 0);
                        } else {
                            //Toast.makeText(((CalculatorUI) context), context.getString(R.string.cal_choosed_already), Toast.LENGTH_SHORT).show();
                            CustomToast.toast(context,view,context.getString(R.string.cal_choosed_already));
                        }
                    }else if (context instanceof BuffMainUI){
                        if (buffObject.size() >= 8){
                            CustomToast.toast(context,view,context.getString(R.string.max_8_char));
                        }else{
                            if (dialogFromBuffMainUI != null){
                                if (dialogFromBuffMainUI.isShowing()){
                                    dialogFromBuffMainUI.dismiss();
                                }
                            }
                            (((BuffMainUI) context)).addCharToLocal(select_char);
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