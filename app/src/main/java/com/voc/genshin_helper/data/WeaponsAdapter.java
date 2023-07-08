package com.voc.genshin_helper.data;/*
 * Package com.voc.genshin_helper.data.WeaponsAdapter was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

import static android.content.Context.MODE_PRIVATE;

import static com.voc.genshin_helper.util.RoundedCornersTransformation.CornerType.TOP;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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
import com.voc.genshin_helper.data.buff_old.SipTikCal;
import com.voc.genshin_helper.ui.CalculatorUI;
import com.voc.genshin_helper.ui.MMXLVIII.Calculator2048;
import com.voc.genshin_helper.ui.MMXLVIII.Desk2048;
import com.voc.genshin_helper.ui.MainActivity;
import com.voc.genshin_helper.ui.SipTik.CalculatorDB_SipTik;
import com.voc.genshin_helper.ui.SipTik.DeskSipTik;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.FadingImageView;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.RoundedCornersTransformation;
import com.voc.genshin_helper.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

/* loaded from: classes.dex */
public class WeaponsAdapter extends RecyclerView.Adapter<WeaponsAdapter.ViewHolder> {
    private Context context;
    private AdapterView.OnItemClickListener mListener;
    private List<Weapons> weaponsList;
    private WebView webView;
    private Activity activity;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private ArrayList<Weapons> weaponA = new ArrayList<Weapons>();
    private SharedPreferences sharedPreferences;

    /* loaded from: classes.dex */
    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    public WeaponsAdapter(Context context, List<Weapons> list, Activity activity, SharedPreferences sharedPreferences) {
        this.context = context;
        this.weaponsList = list;
        this.activity = activity;
        this.sharedPreferences = sharedPreferences;

        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("user_name",MODE_PRIVATE);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artifact_ico_rectangle_2048, viewGroup, false);

        switch (sharedPreferences.getString("curr_ui_grid", "2")){
            case "2":{
                inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weapon_ico_rectangle_2048, viewGroup, false);
                break;
            }
            case "3":{
                inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weapon_ico_square_2048, viewGroup, false);
                break;
            }
            case "4":{
                inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weapon_ico_card_siptik, viewGroup, false);
                break;
            }
            case "5":{
                inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weapon_ico_detail_siptik, viewGroup, false);
                break;
            }
            default:{
                inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weapon_ico_square_2048, viewGroup, false);
                break;
            }
        }
        return new ViewHolder(inflate, (OnItemClickListener) this.mListener);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Weapons weapons = this.weaponsList.get(i);
        ItemRss itemRss = new ItemRss();
        Context context = this.context;

        final int radius = 50;
        final int margin = 0;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin,TOP);


        final int radius_circ_siptik = 80;
        final int margin_circ_siptik = 0;
        final Transformation transformation_circ_siptik = new RoundedCornersTransformation(radius_circ_siptik, margin_circ_siptik);
        final int radius_circ_siptik_ico = 30;
        final int margin_circ_siptik_ico = 0;
        final Transformation transformation_circ_siptik_ico = new RoundedCornersTransformation(radius_circ_siptik_ico, margin_circ_siptik_ico);

        int width = 0;
        int height = 0;


        weaponA.add(weapons);

        boolean isNight = false;
        // Background of item
        if (context.getResources().getString(R.string.mode).equals("Night")) {
            isNight = true;
        }

        viewHolder.weapon_name.setText(weapons.getName());
        viewHolder.weapon_base_name.setText(weapons.getName());

        if (weapons.getIsComing() == 0) {
            viewHolder.weapon_isComing.setVisibility(View.GONE);
        }
        if (weapons.getIsComing() == 1) {
            viewHolder.weapon_isComing.setVisibility(View.GONE);
        }

        if (weapons.getRare() > 0 && weapons.getRare() < 6) {
            viewHolder.weapon_star.setNumStars(weapons.getRare());
            viewHolder.weapon_star.setRating((float) weapons.getRare());
        }


        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height_curr = displayMetrics.heightPixels;
        int width_curr = displayMetrics.widthPixels;
        int curr = width_curr;
        int size_per_img = width_curr/2;
        int size_per_img_sq = width_curr/3;
        int size_per_img_siptik = width_curr;

        Drawable drawable = context.getResources().getDrawable(R.drawable.item_selected_circle_effect);
        viewHolder.weapon_icon.setForeground(drawable);

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            size_per_img = 480;
            size_per_img_sq = 360;
            size_per_img_siptik = 960;
        }

        switch (sharedPreferences.getString("curr_ui_grid", "2")){
            case "2":{
                if(width_curr / ((int)width_curr/size_per_img+1) > size_per_img){
                    width = (width_curr) / ((int)width_curr/size_per_img+1);
                    height = (int) ((width * 9) / 8);
                }else{
                    width = size_per_img;
                    height = (int) ((width * 9) / 8);
                }
                break;
            }
            case "3":{
                if(width_curr / ((int)width_curr/size_per_img_sq+1) > size_per_img_sq){
                    width = (width_curr) / ((int)width_curr/size_per_img_sq+1);
                    height = (width_curr) / ((int)width_curr/size_per_img_sq+1);
                }else{
                    width = size_per_img_sq;
                    height = size_per_img_sq;
                }
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

                viewHolder.weapon_card_bg.getLayoutParams().width = width;
                viewHolder.weapon_card_bg.getLayoutParams().height = height;
                viewHolder.weapon_card_mask.getLayoutParams().width = width;
                viewHolder.weapon_card_mask.getLayoutParams().height = height;
                viewHolder.weapon_card.getLayoutParams().width = width-16;
                viewHolder.weapon_card.getLayoutParams().height = height-16;
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

                viewHolder.weapon_card_bg.getLayoutParams().width = width;
                viewHolder.weapon_card_bg.getLayoutParams().height = height;
                viewHolder.weapon_card_mask.getLayoutParams().width = width;
                viewHolder.weapon_card_mask.getLayoutParams().height = height;
                viewHolder.weapon_cbg.getLayoutParams().width = width-16;
                viewHolder.weapon_cbg.getLayoutParams().height = height-16;
                break;
            }
        }

        int one_curr = height;
        if(height > width){
            one_curr = width;
        }

        viewHolder.weapon_stat.setText(this.context.getString(itemRss.getSecAttr(weapons.stat_1)));
        viewHolder.weapon_name.setText(itemRss.getWeaponByName(weapons.getName())[0]);
        viewHolder.weapon_weapon.setImageResource(itemRss.getWeaponTypeIMG(weapons.getWeapon(),context));
        //viewHolder.weapon_icon.setBackgroundResource(itemRss.getRareColorByName(weapons.getRare())[0]);
        //viewHolder.weapon_bg.setBackgroundResource(itemRss.getRareColorByName(weapons.getRare())[0]);
        //viewHolder.weapon_nl.setBackgroundResource(itemRss.getRareColorByName(weapons.getRare())[1]);

        switch (sharedPreferences.getString("curr_ui_grid", "2")){
            case "2":{
                switch (weapons.getRare()){
                    case 1 : {
                        if(isNight == true){
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare1_800x1400_dark);
                        }else{
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare1_800x1400_light);
                        }
                        break;
                    }
                    case 2 : {
                        if(isNight == true){
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare2_800x1400_dark);
                        }else{
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare2_800x1400_light);
                        }
                        break;
                    }
                    case 3 : {
                        if(isNight == true){
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare3_800x1400_dark);
                        }else{
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare3_800x1400_light);
                        }
                        break;
                    }
                    case 4 : {
                        if(isNight == true){
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare4_800x1400_dark);
                        }else{
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare4_800x1400_light);
                        }
                        break;
                    }
                    case 5 : {
                        if(isNight == true){
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare5_800x1400_dark);
                        }else{
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare5_800x1400_light);
                        }
                        break;
                    }

                }

                viewHolder.weapon_name_ll.getLayoutParams().width = width;
                viewHolder.weapon_name_ll.getLayoutParams().height = width*4/8;
                viewHolder.weapon_main.getLayoutParams().width = width;
                viewHolder.weapon_main.getLayoutParams().height = height*10/9;
                break;
            }

            case "3":{
                switch (weapons.getRare()){
                    case 1 : {
                        if(isNight == true){
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare1_800x1000_dark);
                        }else{
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare1_800x1000_light);
                        }
                        break;
                    }
                    case 2 : {
                        if(isNight == true){
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare2_800x1000_dark);
                        }else{
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare2_800x1000_light);
                        }
                        break;
                    }
                    case 3 : {
                        if(isNight == true){
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare3_800x1000_dark);
                        }else{
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare3_800x1000_light);
                        }
                        break;
                    }
                    case 4 : {
                        if(isNight == true){
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare4_800x1000_dark);
                        }else{
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare4_800x1000_light);
                        }
                        break;
                    }
                    case 5 : {
                        if(isNight == true){
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare5_800x1000_dark);
                        }else{
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare5_800x1000_light);
                        }
                        break;
                    }

                }

                viewHolder.weapon_name_ll.getLayoutParams().width = width;
                viewHolder.weapon_name_ll.getLayoutParams().height = width*2/8;
                viewHolder.weapon_main.getLayoutParams().width = width;
                viewHolder.weapon_main.getLayoutParams().height = width;
                break;
            }
            case "4":
            case "5":{
                switch (weapons.getRare()){
                    case 1 : {
                        Picasso.get()
                                .load (R.drawable.bg_rare1_siptik).resize(width, height)//.transform(transformation_circ_siptik)
                                .error (R.drawable.bg_rare1_siptik)//.transform(transformation_circ_siptik)
                                .into (viewHolder.weapon_card_bg);
                        break;
                    }
                    case 2 : {
                        Picasso.get()
                                .load (R.drawable.bg_rare2_siptik).resize(width, height)//.transform(transformation_circ_siptik)
                                .error (R.drawable.bg_rare2_siptik)//.transform(transformation_circ_siptik)
                                .into (viewHolder.weapon_card_bg);
                        break;
                    }
                    case 3 : {
                        Picasso.get()
                                .load (R.drawable.bg_rare3_siptik).resize(width, height)//.transform(transformation_circ_siptik)
                                .error (R.drawable.bg_rare3_siptik)//.transform(transformation_circ_siptik)
                                .into (viewHolder.weapon_card_bg);
                        break;
                    }
                    case 4 : {
                        Picasso.get()
                                .load (R.drawable.bg_rare4_siptik).resize(width, height)//.transform(transformation_circ_siptik)
                                .error (R.drawable.bg_rare4_siptik)//.transform(transformation_circ_siptik)
                                .into (viewHolder.weapon_card_bg);
                        break;
                    }
                    case 5 : {
                        Picasso.get()
                                .load (R.drawable.bg_rare5_siptik).resize(width, height)//.transform(transformation_circ_siptik)
                                .error (R.drawable.bg_rare5_siptik)//.transform(transformation_circ_siptik)
                                .into(viewHolder.weapon_card_bg);
                        break;
                    }
                }

                if (context.getSharedPreferences("user_info",MODE_PRIVATE).getString("curr_ui_grid", "2").equals("5")){
                    switch (weapons.getRare()){
                        case 1 : viewHolder.weapon_icon.setBackgroundResource(R.drawable.bg_rare1_char_siptik);break;
                        case 2 : viewHolder.weapon_icon.setBackgroundResource(R.drawable.bg_rare2_char_siptik);break;
                        case 3 : viewHolder.weapon_icon.setBackgroundResource(R.drawable.bg_rare3_char_siptik);break;
                        case 4 : viewHolder.weapon_icon.setBackgroundResource(R.drawable.bg_rare4_char_siptik);break;
                        case 5 : viewHolder.weapon_icon.setBackgroundResource(R.drawable.bg_rare5_char_siptik);break;
                        default:  viewHolder.weapon_icon.setBackgroundResource(R.drawable.bg_rare1_char_siptik);break;
                    }
                    viewHolder.weapon_star.setVisibility(View.GONE);
                    viewHolder.weapon_star_ll.setVisibility(View.GONE);
                    viewHolder.weapon_cbg.setPadding(8,8,8,8);
                }


                viewHolder.weapon_card_bg.getLayoutParams().width = width+32;
                viewHolder.weapon_card_bg.getLayoutParams().height = height+32;
                //viewHolder.weapon_name_ll.getLayoutParams().width = width;
                //viewHolder.weapon_name_ll.getLayoutParams().height = width*2/8;
                //viewHolder.weapon_main.getLayoutParams().width = width;
                //viewHolder.weapon_main.getLayoutParams().height = width;

                int frame = R.drawable.bg_day_frame;

                if (isNight){
                    frame = R.drawable.bg_night_frame;
                }

                Picasso.get()
                        .load (frame).resize((int) (width_curr),(int) ((width_curr)/2.1)).transform(transformation_circ_siptik)
                        .error (frame).transform(transformation_circ_siptik)
                        .into (viewHolder.weapon_card_mask);

                Picasso.get()
                        .load (R.drawable.bg_weapon_siptik_square).resize((int) (height_curr),(int) (height_curr)).transform(transformation_circ_siptik)
                        .error (R.drawable.bg_weapon_siptik_square)
                        .into (viewHolder.weapon_card_ico_deco);

                //viewHolder.weapon_card_bg.setPadding(8,8,8,8);
                viewHolder.weapon_card_mask.setPadding(8,8,8,8);
                viewHolder.weapon_card_ico_deco.setPadding(8,8,8,8);

                viewHolder.weapon_card_ico_deco.setEdgeLength(100);
                viewHolder.weapon_card_ico_deco.setFadeDirection(FadingImageView.FadeSide.RIGHT_SIDE);
                break;
            }
            default:{
                switch (weapons.getRare()){
                    case 1 : {
                        if(isNight == true){
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare1_800x1000_dark);
                        }else{
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare1_800x1000_light);
                        }
                        break;
                    }
                    case 2 : {
                        if(isNight == true){
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare2_800x1000_dark);
                        }else{
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare2_800x1000_light);
                        }
                        break;
                    }
                    case 3 : {
                        if(isNight == true){
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare3_800x1000_dark);
                        }else{
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare3_800x1000_light);
                        }
                        break;
                    }
                    case 4 : {
                        if(isNight == true){
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare4_800x1000_dark);
                        }else{
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare4_800x1000_light);
                        }
                        break;
                    }
                    case 5 : {
                        if(isNight == true){
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare5_800x1000_dark);
                        }else{
                            viewHolder.weapon_bg.setBackgroundResource(R.drawable.rare5_800x1000_light);
                        }
                        break;
                    }

                }

                viewHolder.weapon_name_ll.getLayoutParams().width = width;
                viewHolder.weapon_name_ll.getLayoutParams().height = width*2/8;
                viewHolder.weapon_main.getLayoutParams().width = width;
                viewHolder.weapon_main.getLayoutParams().height = width;
                break;
            }
        }

        viewHolder.weapon_icon.getLayoutParams().width = width;
        viewHolder.weapon_icon.getLayoutParams().height = height;

        switch (sharedPreferences.getString("curr_ui_grid", "2")){
            case "2":{
                Picasso.get()
                        .load(itemRss.getWeaponByName(weapons.getName())[1])
                        .fit().centerCrop()
                        .error(R.drawable.paimon_full)
                        .into(viewHolder.weapon_icon);
                break;
            }
            case "3":{
                Picasso.get()
                        .load(itemRss.getWeaponByName(weapons.getName())[1])
                        .resize(one_curr,one_curr)
                        .error(R.drawable.paimon_full)
                        .into(viewHolder.weapon_icon);
                break;
            }
            case "4":
            case "5": {
                viewHolder.weapon_icon.getLayoutParams().width = 96*width/315;
                viewHolder.weapon_icon.getLayoutParams().height = 96*width/315;
                Picasso.get()
                        .load(itemRss.getWeaponByName(weapons.getName())[1])
                        .resize(96*width/315,96*width/315)
                        .transform(transformation_circ_siptik_ico)
                        .error (R.drawable.paimon_full)
                        .into (viewHolder.weapon_icon);
                break;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.weaponsList.size();
    }

    /* loaded from: classes.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView weapon_base_name;
        public ConstraintLayout weapon_bg;
        public ImageView weapon_element;
        public ImageView weapon_icon;
        public ImageView weapon_isComing;
        public TextView weapon_name;
        public LinearLayout weapon_nl;
        public ConstraintLayout weapon_main;
        public LinearLayout weapon_name_ll;
        public RatingBar weapon_star;
        public LinearLayout weapon_star_ll;
        public TextView weapon_stat;
        public ImageView weapon_press_mask;
        public ImageView weapon_weapon;
        public ImageView weapon_card_bg;
        public ImageView weapon_card_mask;
        public FadingImageView weapon_card_ico_deco;
        public CardView weapon_card;
        public LinearLayout weapon_cbg;

        public ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view);
            this.weapon_icon = (ImageView) view.findViewById(R.id.weapon_img);
            this.weapon_name = (TextView) view.findViewById(R.id.weapon_name);
            this.weapon_star = (RatingBar) view.findViewById(R.id.weapon_star);
            this.weapon_star_ll = view.findViewById(R.id.weapon_star_ll);
            this.weapon_isComing = (ImageView) view.findViewById(R.id.weapon_is_coming);
            this.weapon_base_name = (TextView) view.findViewById(R.id.weapon_base_name);
            this.weapon_nl = (LinearLayout) view.findViewById(R.id.weapon_nl);
            this.weapon_name_ll = (LinearLayout) view.findViewById(R.id.weapon_name_ll);
            this.weapon_bg = (ConstraintLayout) view.findViewById(R.id.weapon_bg);
            this.weapon_stat = (TextView) view.findViewById(R.id.weapon_stat);
            this.weapon_press_mask = (ImageView) view.findViewById(R.id.weapon_press_mask);
            this.weapon_main = (ConstraintLayout) view.findViewById(R.id.weapon_main);
            this.weapon_cbg = view.findViewById(R.id.weapon_cbg);

            this.weapon_weapon = view.findViewById(R.id.weapon_weapon);
            this.weapon_card_bg = view.findViewById(R.id.weapon_card_bg);
            this.weapon_card_mask = view.findViewById(R.id.weapon_card_mask);
            this.weapon_card_ico_deco = view.findViewById(R.id.weapon_card_ico_deco);
            this.weapon_card = view.findViewById(R.id.weapon_card);

            weapon_press_mask.startAnimation(buttonClick);

            this.weapon_press_mask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.wtf("is context instanceof MainActivity ?", WeaponsAdapter.this.context.getPackageName());
                    if (context instanceof MainActivity) {

                        String valueOf = String.valueOf(ViewHolder.this.weapon_base_name.getText());

                        SharedPreferences sharedPreferences = WeaponsAdapter.this.context.getSharedPreferences("user_info", 0);
                        String CharName_BASE = valueOf.replace(" ", "_");

                        String lang = sharedPreferences.getString("curr_lang", "zh-HK");
                        AssetManager assets = WeaponsAdapter.this.context.getResources().getAssets();
                        Log.wtf("CharName_BASE", CharName_BASE);

                        Log.wtf("lang", lang);
                        String str = LoadData("db/weapons/" + lang + "/" + CharName_BASE + ".json");
                        if (str == null){
                            str = LoadData("db/weapons/en-US/" + CharName_BASE + ".json");
                        }

                        if (str != null) {
                            try {
                                JSONObject jSONObject = new JSONObject(str);
                                String name = jSONObject.getString("name");
                                int rare = jSONObject.getInt("rare");
                                String weapon = jSONObject.getString("weapon");
                                Boolean isComingSoon = jSONObject.getBoolean("isComingSoon");
                                String desc = jSONObject.getString("desc");
                                String first_status = jSONObject.getString("first_status");
                                String second_status = jSONObject.getString("second_status");
                                String skill_name = jSONObject.getString("skill_name");
                                String skill_desc = jSONObject.getString("skill_desc");
                                String obtain_way = jSONObject.getString("obtain_way");

                                ItemRss itemRss = new ItemRss();

                                final int radius = 25;
                                final int margin = 0;
                                final Transformation transformation = new RoundedCornersTransformation(radius, margin,TOP);

                                Dialog dialog = new Dialog(WeaponsAdapter.this.context, R.style.NormalDialogStyle_N);
                                View inflate = View.inflate(WeaponsAdapter.this.context, R.layout.item_weapon_info, null);

                                ImageView item_img = (ImageView) inflate.findViewById(R.id.item_img);
                                LinearLayout item_nl = (LinearLayout) inflate.findViewById(R.id.item_nl);
                                RatingBar item_star = (RatingBar) inflate.findViewById(R.id.item_star);
                                ImageView item_is_coming = (ImageView) inflate.findViewById(R.id.item_is_coming);
                                ImageView info_item1 = (ImageView) inflate.findViewById(R.id.info_item1);
                                ImageView info_item2 = (ImageView) inflate.findViewById(R.id.info_item2);
                                ImageView info_item3 = (ImageView) inflate.findViewById(R.id.info_item3);
                                ImageView info_item4 = (ImageView) inflate.findViewById(R.id.info_item4);
                                ImageView info_item5 = (ImageView) inflate.findViewById(R.id.info_item5);
                                LinearLayout item_talent = (LinearLayout) inflate.findViewById(R.id.item_talent);
                                LinearLayout item_skill = (LinearLayout) inflate.findViewById(R.id.item_skill);
                                TextView info_skill_title = (TextView) inflate.findViewById(R.id.info_skill_title);

                                Picasso.get()
                                        .load(itemRss.getWeaponByName(name)[1]).fit().centerInside().transform(transformation)
                                        .error(R.drawable.paimon_lost)
                                        .into(item_img);

                                //.setText(itemRss.getWeaponByName(name,context)[0]);
                                //((TextView) inflate.findViewById(R.id.item_name)).setText(weapon_base_name.getText());
                                ((TextView) inflate.findViewById(R.id.item_name)).setText(itemRss.getWeaponByName(name)[0]);
                                ((TextView) inflate.findViewById(R.id.item_info)).setText(desc);
                                ((ImageView) inflate.findViewById(R.id.item_element)).setVisibility(View.GONE);

                                item_star.setNumStars(rare);
                                item_star.setRating(rare);

                                ((ImageView) inflate.findViewById(R.id.item_weapon)).setImageResource(itemRss.getWeaponTypeIMG(weapon,context));
                                item_img.setBackgroundResource(itemRss.getRareColorByName(rare)[0]);
                                item_nl.setBackgroundResource(itemRss.getRareColorByName(rare)[1]);
                                item_talent.setVisibility(View.GONE);
                                item_skill.setVisibility(View.VISIBLE);
                                info_skill_title.setText(skill_name);
                                ((TextView) inflate.findViewById(R.id.info_skill_desc)).setText(skill_desc);
                                if (isComingSoon ) {
                                    item_is_coming.setVisibility(View.VISIBLE);
                                } else {
                                    item_is_coming.setVisibility(View.GONE);
                                }
                                dialog.setContentView(inflate);
                                dialog.setCanceledOnTouchOutside(true);
                                Window window = dialog.getWindow();
                                WindowManager.LayoutParams attributes = window.getAttributes();
                                DisplayMetrics displayMetrics = new DisplayMetrics();
                                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                                int height = displayMetrics.heightPixels;
                                int width = displayMetrics.widthPixels;
                                attributes.width = width;
                                attributes.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                                attributes.gravity = Gravity.BOTTOM;
                                window.setAttributes(attributes);
                                dialog.show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            //Toast.makeText(context, context.getString(R.string.none_info), Toast.LENGTH_SHORT).show();

                            CustomToast.toast(context,v,context.getString(R.string.none_info));
                            return;
                        }

                    }
                    else if (WeaponsAdapter.this.context instanceof CalculatorUI) {
                        Log.wtf("YES", "IT's");
                        Log.w("WEAPON_BASE", (String) ViewHolder.this.weapon_base_name.getText());
                        ((CalculatorUI) WeaponsAdapter.this.context).weaponQuestion(String.valueOf(weapon_base_name.getText()), "ADD", 0, (int) weapon_star.getRating());
                    } else if (context instanceof Desk2048){Log.wtf("YES","IT's "+String.valueOf(weapon_base_name.getText()));
                        (((Desk2048) context)).startWeaponInfo(String.valueOf(weapon_base_name.getText()),activity);

                    } else if (context instanceof DeskSipTik){Log.wtf("YES","IT's");
                        (((DeskSipTik) context)).startWeaponInfo(String.valueOf(weapon_base_name.getText()),activity);

                    } else if (context instanceof CalculatorDB_SipTik){Log.wtf("YES","IT's");
                        (((CalculatorDB_SipTik) context)).addItem(String.valueOf(weapon_base_name.getText()), (int) weapon_star.getRating(),"WEAPON");
                    }else if (context instanceof Calculator2048){Log.wtf("YES","IT's");
                        (((Calculator2048) context)).addWeapon(String.valueOf(weapon_base_name.getText()), (int) weapon_star.getRating());
                    }else if (context instanceof SipTikCal){Log.wtf("YES","IT's");
                        for (int x = 0 ; x < weaponsList.size() ; x++){
                            if (weaponsList.get(x).getName().equals(weapon_base_name.getText())){
                                (((SipTikCal) context)).weaponChoosed(weaponA.get(x));
                            }
                        }
                    }
                }
            });
        }

    }

    public void filterList(List<Weapons> list) {
        this.weaponsList = list;
        notifyDataSetChanged();
    }

    public String LoadData(String inFile) {
        String tContents = "";

        try {
            File file = new File(context.getFilesDir()+"/"+inFile);
            InputStream stream = new FileInputStream(file);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }

        return tContents;

    }
}