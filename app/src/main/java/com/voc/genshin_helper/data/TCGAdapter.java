package com.voc.genshin_helper.data;

import static android.content.Context.MODE_PRIVATE;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.ui.MMXLVIII.Desk2048;
import com.voc.genshin_helper.util.CustomTextView;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

/**
 * Created by ankit on 27/10/17.
 */

public class TCGAdapter extends RecyclerView.Adapter<TCGAdapter.ViewHolder> {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    private Context context;
    private List<TCG> tcgList;
    private AdapterView.OnItemClickListener mListener;
    private WebView webView ;
    private Activity activity ;
    private TCG tcg ;
    private ArrayList<TCG> tcgA = new ArrayList<TCG>();
    private SharedPreferences sharedPreferences;


    public TCGAdapter(Context context, List<TCG> tcgList, Activity activity, SharedPreferences sharedPreferences) {
        this.context = context;
        this.tcgList = tcgList;
        this.activity = activity;
        this.sharedPreferences = sharedPreferences;

        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("user_name",MODE_PRIVATE);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artifact_ico_rectangle_2048, parent, false);
        // P.S. TCG will only have one type displayment
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tcg_card, parent, false);
        return new ViewHolder(v, (OnItemClickListener) mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        this.tcg = tcgList.get(position);
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
        final int radius_circ_siptik_ico = 120;
        final int margin_circ_siptik_ico = 0;
        final Transformation transformation_circ_siptik_ico = new RoundedCornersTransformation(radius_circ_siptik_ico, margin_circ_siptik_ico);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width_curr = displayMetrics.widthPixels;
        int img_width = 320;

        if (width_curr < (img_width+displayMetrics.density*20)*3){
            img_width = (int) ((width_curr-displayMetrics.density*20*3)/3);
        }

        // Weird
        //tcgA.add(tcg);
        holder.tcg_data = tcg;
        holder.tcg_width = (int) (img_width+displayMetrics.density*(20));

        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getTCGByNameBase(tcg.getName(),context)[0],context))
                .resize(img_width,(int) (img_width*12/7))
                .error (R.drawable.tcg_card_unknown)
                .into (holder.tcg_card_img);

        holder.tcg_card_item.getLayoutParams().width = img_width;
        holder.tcg_card_item.getLayoutParams().height = (int) (img_width*12/7);
        //holder.tcg_card_item.setPadding(0,0, (int) (displayMetrics.density*8),0);


        holder.tcg_card_name.setText(item_rss.getTCGByName(tcg.getName(),context)[1]);
        holder.tcg_card_name_base.setText(tcg.getName());
        holder.tcg_press_mask.getLayoutParams().width = (int) (img_width);
        holder.tcg_press_mask.getLayoutParams().height = (int) (img_width*12/7);

        switch (tcg.getType()){
            case TCG.CHAR:{
                holder.tcg_card_hp.setVisibility(View.VISIBLE);
                holder.tcg_card_dice.setVisibility(View.GONE);

                holder.tcg_hp_tv.setText(String.valueOf(tcg.getHP()));
                break;
            }
            case TCG.EQUIP:
            case TCG.SUPPORT:
            case TCG.EVENT:
            case TCG.BACKSIDE:{
                holder.tcg_card_hp.setVisibility(View.GONE);
                holder.tcg_card_dice.setVisibility(View.VISIBLE);
                int diceType = R.drawable.bg_tcg_dice_specific;

                switch (tcg.getDiceType()){
                    case TCG.Anemo: diceType = R.drawable.bg_tcg_dice_anemo;break;
                    case TCG.Cryo: diceType = R.drawable.bg_tcg_dice_cryo;break;
                    case TCG.Dendro: diceType = R.drawable.bg_tcg_dice_dendro;break;
                    case TCG.Electro: diceType = R.drawable.bg_tcg_dice_electro;break;
                    case TCG.Geo: diceType = R.drawable.bg_tcg_dice_geo;break;
                    case TCG.Hydro: diceType = R.drawable.bg_tcg_dice_hydro;break;
                    case TCG.Pyro: diceType = R.drawable.bg_tcg_dice_pyro;break;
                    case TCG.SPEC: diceType = R.drawable.bg_tcg_dice_specific;break;
                    case TCG.RAND: diceType = R.drawable.bg_tcg_dice_random;break;
                }

                holder.tcg_dice_bg.setImageResource(diceType);
                holder.tcg_dice_tv.setText(String.valueOf(tcg.getDiceCost()));
                break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return tcgList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView tcg_press_mask ;
        TextView tcg_card_name, tcg_card_name_base;
        ImageView tcg_card_kwang;
        GifImageView tcg_card_img;
        ImageView tcg_hp_bg, tcg_dice_bg;
        CustomTextView tcg_hp_tv, tcg_dice_tv;
        FrameLayout tcg_card_item, tcg_card_hp, tcg_card_dice, tcg_card;
        TCG tcg_data;
        int tcg_width;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            tcg_card = itemView.findViewById(R.id.tcg_card);
            tcg_hp_bg = itemView.findViewById(R.id.tcg_hp_bg);
            tcg_dice_bg = itemView.findViewById(R.id.tcg_dice_bg);
            tcg_card_name = itemView.findViewById(R.id.tcg_card_name);
            tcg_card_name_base = itemView.findViewById(R.id.tcg_card_name_base);
            tcg_card_img = itemView.findViewById(R.id.tcg_card_img);
            tcg_card_kwang = itemView.findViewById(R.id.tcg_card_kwang);
            tcg_hp_tv = itemView.findViewById(R.id.tcg_hp_tv);
            tcg_dice_tv = itemView.findViewById(R.id.tcg_dice_tv);
            tcg_card_item = itemView.findViewById(R.id.tcg_card_item);
            tcg_card_hp = itemView.findViewById(R.id.tcg_card_hp);
            tcg_card_dice = itemView.findViewById(R.id.tcg_card_dice);
            tcg_press_mask = itemView.findViewById(R.id.tcg_press_mask);
            tcg_press_mask.startAnimation(buttonClick);

            tcg_press_mask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (context instanceof Desk2048) {
                        //CustomToast.toast(context,activity,"This is "+tcg_card_name.getText().toString());
                        int[] screenPos = new int[2];
                        //tcg_card.getLocationInWindow(originalPos);
                        tcg_card.getLocationOnScreen(screenPos);

                        System.out.println("Item `"+tcg_card_name_base.getText()+"` (x,y) SCREEN = ("+tcg_card.getMeasuredWidth()+","+tcg_card.getMeasuredHeight()+")");
                        (((Desk2048) context)).startTCGInfo(
                                tcg_card,
                                tcg_data,
                                tcg_width,
                                activity,
                                screenPos);
                    }
                }
            });

        }
    }




    public void filterList(List<TCG> filteredList) {
        tcgList = filteredList;
        notifyDataSetChanged();
    }

}