package com.voc.genshin_helper.ui.SipTik;

import static android.content.Context.MODE_PRIVATE;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.google.android.material.tabs.TabLayout.MODE_FIXED;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.util.BackgroundReload;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.MyViewPagerAdapter;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

//https://stackoverflow.com/questions/3592836/check-for-file-existence-in-androids-assets-folder/7337516

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

public class Weapon_Info_Siptik {
    /** Method of requirements */
    Context context;
    Activity activity;
    SharedPreferences sharedPreferences ;
    ItemRss item_rss;
    BackgroundReload backgroundReload;

    /** Method of Weapon's details' container */
    /** Since String can't be null, so there will have "XPR" for identify is result correct */
    String WeaponName_BASE = "";

    // Main
    String name = "XPR" ;
    int star = 4;
    String weapon = "XPR" ;
    boolean isComing = false ;
    String desc = "XPR" ;
    String skill_name = "XPR" ;
    String skill_desc = "XPR" ;
    String obtain_way = "XPR" ;
    String status = "XPR" ;
    JSONObject jsonObject;
    JSONObject jsonObjectDps;

    // Basic Talent
    String talent_name = "XPR";
    String talent_img = "XPR";
    String talent_desc = "XPR";

    // Advice
    String[] main_weapon_advice = {};
    String[] support_weapon_advice = {};
    String[] util_weapon_advice = {};

    String[] main_artifacts1 = {};
    String[] main_artifacts2 = {};
    String[] main_artifacts3 = {};
    String[] main_artifacts4 = {};
    String[] main_artifacts5 = {};

    String[] support_artifacts1 = {};
    String[] support_artifacts2 = {};
    String[] support_artifacts3 = {};
    String[] support_artifacts4 = {};
    String[] support_artifacts5 = {};

    String[] util_artifacts1 = {};
    String[] util_artifacts2 = {};
    String[] util_artifacts3 = {};
    String[] util_artifacts4 = {};
    String[] util_artifacts5 = {};

    String[] team1 = {};
    String[] team2 = {};
    String[] team3 = {};
    String[] team4 = {};
    String[] team5 = {};

    /** [BASIC] CHARACTER LVL EXP + MORA */
    public ArrayList<Integer> lvlEXPList = new ArrayList<Integer>();
    public ArrayList<Integer> expEXPList = new ArrayList<Integer>();
    public ArrayList<Integer> moraEXPList = new ArrayList<Integer>();
    public ArrayList<Integer> local1ASCList = new ArrayList<Integer>();
    public ArrayList<Integer> local2ASCList = new ArrayList<Integer>();
    public ArrayList<Integer> commonASCList = new ArrayList<Integer>();
    public ArrayList<Integer> moraASCList = new ArrayList<Integer>();

    /** [WEAPON] WEAPON REQUIRE ON ASC  */
    public  String commonREQUIRE = "XPR";
    public  String local1REQUIRE = "XPR";
    public  String local2REQUIRE = "XPR";
    // Buff
    double 武器基礎攻擊力,武器生命值加成,武器攻擊力加成,武器防禦力加成,武器暴擊率,武器暴擊傷害,武器元素充能,武器元素精通,武器物理傷害加成;


    /** https://stackoverflow.com/questions/45247927/how-to-parse-json-object-inside-json-object-in-java */
    public void JsonToStr (String str){
        if(!str.equals("")){
            try {
                jsonObject = new JSONObject(str);
                name = jsonObject.getString("name");
                star = jsonObject.getInt("rare");
                weapon = jsonObject.getString("weapon");
                isComing = jsonObject.getBoolean("isComingSoon");
                desc = jsonObject.getString("desc");
                obtain_way = jsonObject.getString("obtain_way");
                status = jsonObject.getString("second_status");
                skill_name = jsonObject.getString("skill_name");
                skill_desc = jsonObject.getString("skill_desc");

                readWeaponAscData();
                show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            CustomToast.toast(context,activity,context.getString(R.string.none_info));
        }
    }

    public void setup (String WeaponName_BASE, Context context,Activity activity){
        sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        this.WeaponName_BASE = WeaponName_BASE.replace(" ","_");
        this.context = context;
        this.activity = activity;
        item_rss = new ItemRss();


        String lang = sharedPreferences.getString("curr_lang","zh-HK");
        String is = null;
        String is_dps = null;
        String is_default = null;

        //is_dps = LoadData("db/weapon/weapon_advice/"+this.WeaponName_BASE+".json");
        is_default = LoadData("db/weapons/en-US/"+this.WeaponName_BASE+".json");
        is = LoadData("db/weapons/"+lang+"/"+this.WeaponName_BASE+".json");

        if(is != null){
            //JsonToStr(is,is_dps);
            JsonToStr(is);
        }else if(is_default != null){
            //JsonToStr(is_default,is_dps);
            JsonToStr(is_default);
        }
    }


    public void show() {
        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.fragment_weapon_info_frame_siptik, null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        BackgroundReload.BackgroundReload(context,view);

        /** Method of info_detail */
        ConstraintLayout info_weapon_bg = view.findViewById(R.id.info_weapon_bg);
        ImageView weapon_img = view.findViewById(R.id.info_weapon_img);
        TextView weapon_name = view.findViewById(R.id.info_weapon_name);
        TextView weapon_obtain_way_tv = view.findViewById(R.id.info_obtain_way_tv);
        RatingBar weapon_stars = view.findViewById(R.id.info_stars);
        ImageView weapon_weapon = view.findViewById(R.id.info_weapon);

        /** Method of introduce */
        TextView info_intro = view.findViewById(R.id.info_intro_tv);

        /** Method of value btn */
        ImageView info_weapon_base_value_btn = view.findViewById(R.id.info_weapon_base_value_btn);

        /** Method of battle_talent */
        CardView weapon_talent_card = view.findViewById(R.id.info_talent_card);
        TextView weapon_talent_name = view.findViewById(R.id.info_talent_name);
        TextView weapon_talent_normal = view.findViewById(R.id.info_talent_normal);

        LinearLayout base_lvl_ll = view.findViewById(R.id.base_lvl_ll);

        readWeaponMaterialByBuff(base_lvl_ll,view);

        weapon_talent_name.setText(skill_name);
        weapon_talent_normal.setText(skill_desc);


        /** THEME COLOR SET*/
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info",MODE_PRIVATE);
        String color_hex = sharedPreferences.getString("theme_color_hex","#FF5A5A"); // Must include #
        boolean isColorGradient = sharedPreferences.getBoolean("theme_color_gradient",false); // Must include #
        String start_color = sharedPreferences.getString("start_color","#AEFEFF"); // Must include #
        String end_color = sharedPreferences.getString("end_color","#35858B"); // Must include #

        if(isColorGradient){
            color_hex = start_color;
        }

        ColorStateList myList ;

        if (isColorGradient){
            myList= new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_pressed},
                            new int[]{-android.R.attr.state_checked},
                            new int[]{android.R.attr.state_checked},
                    },
                    new int[] {
                            Color.parseColor(start_color),
                            Color.parseColor(start_color),
                            Color.parseColor(end_color)
                    }
            );
        }else{
            myList = new ColorStateList(
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
        }

        //TextView barrier1 = view.findViewById(R.id.barrier1);
        //TextView barrier2 = view.findViewById(R.id.barrier2);
        //TextView barrier3 = view.findViewById(R.id.barrier3);
        //TextView barrier4 = view.findViewById(R.id.barrier4);
        // TextView barrier5 = view.findViewById(R.id.barrier5);
        //TextView info_intro_title = view.findViewById(R.id.info_intro_title);
        //TextView info_talent = view.findViewById(R.id.info_talent);
        //TextView info_btalent = view.findViewById(R.id.info_btalent);
        //TextView info_sof = view.findViewById(R.id.info_sof);
        //TextView info_advice = view.findViewById(R.id.info_advice);

        /*
        colorGradient(info_talent1_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_talent1_normal_title ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_talent1_hard_title ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_talent1_normal_title ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_talent1_drop_title ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_talent2_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_talent3_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_talent4_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_btalent1_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_btalent2_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_btalent3_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_sof1_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_sof2_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_sof3_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_sof4_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_sof5_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_sof6_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_advice_main_art_info ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_advice_support_art_info ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_advice_util_art_info ,start_color,end_color,isColorGradient,color_hex);
         */

        /**
         * PLS REMEMBER ADD BACK SUGGESTED WEAPON,ART IN XML
         */


        info_weapon_base_value_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.fragment_char_skill_dmg_2048, null);

                String[] lvList = new String[]{"LV1","LV20","LV20+","LV40","LV40+","LV50","LV50+","LV60","LV60+","LV70","LV70+","LV80","LV80+","LV90"};

                Spinner menu_lv_sp = view.findViewById(R.id.menu_lv_spinner);
                FrameLayout menu_ok = view.findViewById(R.id.menu_ok);

                menu_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                TextView menu_part_title_tv1 = view.findViewById(R.id.menu_part_title_tv1);
                TextView menu_part_title_tv2 = view.findViewById(R.id.menu_part_title_tv2);
                TextView menu_part_title_tv3 = view.findViewById(R.id.menu_part_title_tv3);
                TextView menu_part_title_tv4 = view.findViewById(R.id.menu_part_title_tv4);
                TextView menu_part_title_tv5 = view.findViewById(R.id.menu_part_title_tv5);
                TextView menu_part_title_tv6 = view.findViewById(R.id.menu_part_title_tv6);
                TextView menu_part_title_tv7 = view.findViewById(R.id.menu_part_title_tv7);
                TextView menu_part_title_tv8 = view.findViewById(R.id.menu_part_title_tv8);
                TextView menu_part_title_tv9 = view.findViewById(R.id.menu_part_title_tv9);
                TextView menu_part_title_tv10 = view.findViewById(R.id.menu_part_title_tv10);
                TextView menu_part_title_tv11 = view.findViewById(R.id.menu_part_title_tv11);
                TextView menu_part_title_tv12 = view.findViewById(R.id.menu_part_title_tv12);
                TextView menu_part_title_tv13 = view.findViewById(R.id.menu_part_title_tv13);
                TextView menu_part_title_tv14 = view.findViewById(R.id.menu_part_title_tv14);
                TextView menu_part_title_tv15 = view.findViewById(R.id.menu_part_title_tv15);
                TextView menu_part_title_tv16 = view.findViewById(R.id.menu_part_title_tv16);

                TextView menu_part_value_tv1  = view.findViewById(R.id.menu_part_value_tv1);
                TextView menu_part_value_tv2 = view.findViewById(R.id.menu_part_value_tv2);
                TextView menu_part_value_tv3 = view.findViewById(R.id.menu_part_value_tv3);
                TextView menu_part_value_tv4 = view.findViewById(R.id.menu_part_value_tv4);
                TextView menu_part_value_tv5 = view.findViewById(R.id.menu_part_value_tv5);
                TextView menu_part_value_tv6 = view.findViewById(R.id.menu_part_value_tv6);
                TextView menu_part_value_tv7 = view.findViewById(R.id.menu_part_value_tv7);
                TextView menu_part_value_tv8 = view.findViewById(R.id.menu_part_value_tv8);
                TextView menu_part_value_tv9 = view.findViewById(R.id.menu_part_value_tv9);
                TextView menu_part_value_tv10 = view.findViewById(R.id.menu_part_value_tv10);
                TextView menu_part_value_tv11 = view.findViewById(R.id.menu_part_value_tv11);
                TextView menu_part_value_tv12 = view.findViewById(R.id.menu_part_value_tv12);
                TextView menu_part_value_tv13 = view.findViewById(R.id.menu_part_value_tv13);
                TextView menu_part_value_tv14 = view.findViewById(R.id.menu_part_value_tv14);
                TextView menu_part_value_tv15 = view.findViewById(R.id.menu_part_value_tv15);
                TextView menu_part_value_tv16 = view.findViewById(R.id.menu_part_value_tv16);

                ArrayAdapter lv_aa = new ArrayAdapter(context,R.layout.spinner_item_2048,lvList);
                lv_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_2048);
                menu_lv_sp.setAdapter(lv_aa);

                menu_lv_sp.setSelection(0);
                menu_lv_sp.setDropDownWidth(menu_lv_sp.getLayoutParams().width);
                menu_lv_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(readWeaponBaseDataFromBuff(name,position)){
                            TextView[] item_title_tv = new TextView[]{menu_part_title_tv1,menu_part_title_tv2,menu_part_title_tv3,menu_part_title_tv4,menu_part_title_tv5,menu_part_title_tv6,menu_part_title_tv7,menu_part_title_tv8,menu_part_title_tv9,menu_part_title_tv10,menu_part_title_tv11,menu_part_title_tv12,menu_part_title_tv13,menu_part_title_tv14,menu_part_title_tv15,menu_part_title_tv16};
                            TextView[] item_value_tv = new TextView[]{menu_part_value_tv1,menu_part_value_tv2,menu_part_value_tv3,menu_part_value_tv4,menu_part_value_tv5,menu_part_value_tv6,menu_part_value_tv7,menu_part_value_tv8,menu_part_value_tv9,menu_part_value_tv10,menu_part_value_tv11,menu_part_value_tv12,menu_part_value_tv13,menu_part_value_tv14,menu_part_value_tv15,menu_part_value_tv16};
                            double[] item_name_value = new double[]{武器基礎攻擊力,武器生命值加成,武器攻擊力加成,武器防禦力加成,武器暴擊率,武器暴擊傷害,武器元素充能,武器元素精通,武器物理傷害加成};
                            String[] item_name = new String[]{context.getString(R.string.weapon_stat_atk),context.getString(R.string.weapon_stat_HP),context.getString(R.string.weapon_stat_HPP),context.getString(R.string.weapon_stat_atkP),context.getString(R.string.weapon_stat_DEFP),context.getString(R.string.weapon_stat_CritRateP),context.getString(R.string.weapon_stat_CritDMGP),context.getString(R.string.weapon_stat_EnRechP),context.getString(R.string.weapon_stat_EleMas),context.getString(R.string.weapon_stat_PhyDMGP)};

                            for (int x = 0 ; x < item_title_tv.length ; x++){
                                item_title_tv[x].setVisibility(View.GONE);
                                item_value_tv[x].setVisibility(View.GONE);
                            }

                            int real_pos_cnt = 0;
                            for (int x = 0 ; x < item_name_value.length && real_pos_cnt < 15 ; x++){
                                if (item_name_value[x] != 0d){
                                    item_title_tv[real_pos_cnt].setVisibility(View.VISIBLE);
                                    item_value_tv[real_pos_cnt].setVisibility(View.VISIBLE);
                                    item_title_tv[real_pos_cnt].setText(item_name[x]);
                                    if(isType0(item_name_value[x])){
                                        item_value_tv[real_pos_cnt].setText(prettyCount(item_name_value[x],0));
                                    }else{
                                        item_value_tv[real_pos_cnt].setText(prettyCount(item_name_value[x],1));
                                    }
                                    real_pos_cnt = real_pos_cnt+1;
                                }
                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                /** Method of dialog */
                dialog.setContentView(view);
                dialog.setCanceledOnTouchOutside(true);
                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                // 2O48 DESIGN
                dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_2048));
                dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

                DisplayMetrics displayMetrics = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int height = displayMetrics.heightPixels;
                int width = displayMetrics.widthPixels;

                lp.width = (int) (width);
                lp.height = WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;
                dialogWindow.setAttributes(lp);
                dialog.show();
            }
        });

        /** MAIN */
        weapon_name.setText(item_rss.getWeaponByName(name,context)[0]);
        weapon_obtain_way_tv.setText(item_rss.getObtainCode(obtain_way,context));
        //weapon_title.setText(nick);
        //Picasso.get().load(FileLoader.loadIMG(item_rss.getWeaponByName(name,context)[0],context)).centerCrop().into(weapon_img);
        weapon_img.setImageDrawable(FileLoader.loadIMG2Drawable(item_rss.getWeaponGachaByName(name,context)[1],context));
        switch (weapon){
            case "Sword" :
            case "Polearm" :
            case "Claymore" : {weapon_img.setRotation(30);break;}
            case "Bow" :
            case "Catalyst" : {weapon_img.setRotation(0);break;}
        }

        Animation animImgLTR = AnimationUtils.loadAnimation(context,R.anim.img_ltr);
        Animation animImgRTL = AnimationUtils.loadAnimation(context,R.anim.img_rtl);
        weapon_img.setAnimation(animImgLTR);
        LinearLayout info_detail = view.findViewById(R.id.info_detail);
        info_detail.setAnimation(animImgRTL);

        displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height_curr = displayMetrics.heightPixels;
        int width_curr = displayMetrics.widthPixels;

        //weapon_img.setBackgroundResource(item_rss.getElementByName(element)[2]);

        boolean isNight = false;
        // Background of item
        if (context.getResources().getString(R.string.mode).equals("Night")) {
            isNight = true;
        }

        weapon_stars.setNumStars(star);
        weapon_stars.setRating(star);
        //weapon_element.setImageResource(item_rss.getElementByName(element)[0]);
        //weapon_area.setText(item_rss.getLocaleName(area,context));
        //weapon_area_ico.setImageResource(item_rss.getDistrictIMG(area));
        weapon_weapon.setImageResource(item_rss.getWeaponTypeIMG(weapon));
        weapon_weapon.setImageResource(item_rss.getWeaponTypeIMG(weapon));
        //weapon_role.setText(item_rss.getLocaleName(role,context));
        //weapon_sex.setText(item_rss.getLocaleName(sex,context));
        //weapon_birth.setText(item_rss.getLocaleBirth(birth,context)); // -> If necessary will change to Month | Day -> E.g. July 24th
        info_intro.setText(desc);

        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(25, 0, RoundedCornersTransformation.CornerType.ALL);


        /** Method of dialog */
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        // 2O48 DESIGN
        dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_2048));
        dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    private boolean isType0(double v) {
        if ((int) v == v){
            return true;
        }
        return false;
    }

    public int getWeaponRareFromListJson(String str) {
        String json_base = LoadData("db/weapons/weapon_list.json");
        String name ;
        int rare;
        try {
            JSONArray array = new JSONArray(json_base);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                name = object.getString("name");
                rare = object.getInt("rare");

                if (name.equals(str)){
                    return rare;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int getArtifactRareFromListJson(String str) {
        String json_base = LoadData("db/artifacts/artifact_list.json");
        String name ;
        int rare;
        try {
            JSONArray array = new JSONArray(json_base);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                name = object.getString("name");
                rare = object.getInt("rare");

                if (name.equals(str)){
                    return rare;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 1;
    }


    public boolean readWeaponBaseDataFromBuff(String name, int tmp_break) {
        String weapon_json_stat = LoadData("db/buff/weapons/"+name.replace(" ","_")+".json");
        if (weapon_json_stat.length() > 0){
            try {
                JSONObject jsonObject = new JSONObject(weapon_json_stat);

                JSONArray 基礎生命值 = jsonObject.getJSONArray("基礎攻擊力");
                武器基礎攻擊力 = 基礎生命值.getDouble(tmp_break);

                JSONArray 生命值加成 = jsonObject.getJSONArray("生命值加成");
                武器生命值加成 = 生命值加成.getDouble(tmp_break);

                JSONArray 攻擊力加成 = jsonObject.getJSONArray("攻擊力加成");
                武器攻擊力加成 = 攻擊力加成.getDouble(tmp_break);

                JSONArray 防禦力加成 = jsonObject.getJSONArray("防禦力加成");
                武器防禦力加成 = 防禦力加成.getDouble(tmp_break);

                JSONArray 暴擊率 = jsonObject.getJSONArray("暴擊率");
                武器暴擊率 = 暴擊率.getDouble(tmp_break);

                JSONArray 暴擊傷害 = jsonObject.getJSONArray("暴擊傷害");
                武器暴擊傷害 = 暴擊傷害.getDouble(tmp_break);

                JSONArray 元素充能 = jsonObject.getJSONArray("元素充能");
                武器元素充能 = 元素充能.getDouble(tmp_break);

                JSONArray 元素精通 = jsonObject.getJSONArray("元素精通");
                武器元素精通 = 元素精通.getDouble(tmp_break);

                JSONArray 物理傷害加成 = jsonObject.getJSONArray("物理傷害加成");
                武器物理傷害加成 = 物理傷害加成.getDouble(tmp_break);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }else{
            CustomToast.toast(context,activity,context.getString(R.string.none_info));
            return false;
        }
    }

    public void readWeaponAscData(){
        String weapon_require_asc = LoadData("db/weapons/weapon_require_asc.json");
        String weapon_exp = LoadData("db/weapons/weapon_rare"+String.valueOf(star)+"_exp.json");
        String weapon_asc = LoadData("db/weapons/weapon_rare"+String.valueOf(star)+"_asc_lvl.json");

        //Log.wtf("Procedure","char_readJSON_1"+" || "+System.currentTimeMillis());

        try {
            JSONArray array = new JSONArray(weapon_require_asc);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                if (object.getString("name").equals(name)){
                    local1REQUIRE = object.getString("copy1");
                    local2REQUIRE = object.getString("copy2");
                    commonREQUIRE = object.getString("common");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONArray array = new JSONArray(weapon_exp);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int lvl = object.getInt("lvl");
                int exp = object.getInt("exp");
                int mora = object.getInt("mora");
                lvlEXPList.add(lvl);
                expEXPList.add(exp);
                moraEXPList.add(mora);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONArray array = new JSONArray(weapon_asc);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int copy1 = object.getInt("copy1");
                int copy2 = object.getInt("copy2");
                int common = object.getInt("common");
                int mora = object.getInt("mora");
                local1ASCList.add(copy1);
                local2ASCList.add(copy2);
                commonASCList.add(common);
                moraASCList.add(mora);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void readWeaponMaterialByBuff(LinearLayout base_lvl_ll,View view) {
        Spinner base_lvl_spinner = view.findViewById(R.id.base_lvl_spinner);
        String[] lvList = new String[]{"LV20","LV40","LV50","LV60","LV70"};

        switch (star){
            case 1 :
            case 2 : {lvList = new String[]{"LV20","LV40","LV50","LV60","LV70"};break;}
            case 3 :
            case 4 :
            case 5 : {lvList = new String[]{"LV20","LV40","LV50","LV60","LV70","LV80","LV90"};break;}
        }

        ArrayAdapter lv_aa = new ArrayAdapter(context,R.layout.spinner_item_anti_2048,lvList);
        lv_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_anti_2048);
        base_lvl_spinner.setAdapter(lv_aa);

        base_lvl_spinner.setSelection(0);
        base_lvl_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                base_lvl_ll.removeAllViews();
                base_lvl_ll.removeAllViewsInLayout();
                addItemInLL(pos,base_lvl_ll);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void addItemInLL(int pos, LinearLayout base_lvl_ll){
        int mora = 0;
        int exp = 0;
        int exp_big = 0;
        int exp_mid = 0;
        int exp_small = 0;

        int[] lvIntBegin = new int[]{0,21,41,51,61,71,81};
        int[] lvIntEnd = new int[]{20,40,50,60,70,80,90}; // UN ASC

        for (int x = lvIntBegin[pos] ; x < lvIntEnd[pos] ; x++){
            mora = mora+ moraEXPList.get(x);
            exp = exp+ expEXPList.get(x);
        }

        if(pos != 0){
            mora = moraASCList.get(pos-1);
        }

        exp_small = expReturn(exp)[0];
        exp_mid = expReturn(exp)[1];
        exp_big = expReturn(exp)[2];

        String[] itemNameListBASE = new String[]{"摩拉","精鍛用雜礦","精鍛用良礦","精鍛用魔礦"};
        int[] itemValueListBASE = new int[]{mora,exp_small,exp_mid,exp_big};
        int[] itemRareListBASE = new int[]{2,1,2,3};
        for (int x = 0 ; x < itemNameListBASE.length ; x++){
            if (itemValueListBASE[x] > 0) {
                View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_base_lvl_material_2048, base_lvl_ll, false);
                ImageView img_bg  = char_view.findViewById(R.id.base_lvl_bg);
                ImageView img_img = char_view.findViewById(R.id.base_lvl_img);
                TextView img_tv = char_view.findViewById(R.id.base_lvl_tv);

                Picasso.get().load(getRssByRare(itemRareListBASE[x])).resize(150,180).into(img_bg);
                Picasso.get().load(FileLoader.loadIMG(item_rss.getItemIcoByName(itemNameListBASE[x],context),context)).resize(144,144).into(img_img);
                img_tv.setText(prettyCount(itemValueListBASE[x],0));

                img_bg.getLayoutParams().width = 150;
                img_bg.getLayoutParams().height = 180;
                img_img.getLayoutParams().width = 144;
                img_img.getLayoutParams().height = 144;
                img_tv.getLayoutParams().width = 150;
                img_tv.getLayoutParams().height = 36;
                base_lvl_ll.addView(char_view);
            }
        }

        if (pos != 0){
            String[] itemNameList = new String[3];
            int[] itemRareList = new int[3];

            int[] itemValueList = new int[]{
                    local1ASCList.get(pos-1),
                    local2ASCList.get(pos-1),
                    commonASCList.get(pos-1)
            };

            switch (pos-1){
                case 0 : {
                    itemNameList = new String[]{
                            getWeaponLocal1ListByItemName(local1REQUIRE)[0],
                            getWeaponLocal2ListByItemName(local2REQUIRE)[0],
                            getWeaponCommonListByItemName(commonREQUIRE)[0]
                    };
                    itemRareList = new int[]{2,2,1};
                    break;
                }
                case 1 : {
                    itemNameList = new String[]{
                            getWeaponLocal1ListByItemName(local1REQUIRE)[1],
                            getWeaponLocal2ListByItemName(local2REQUIRE)[0],
                            getWeaponCommonListByItemName(commonREQUIRE)[0]
                    };
                    itemRareList = new int[]{3,2,1};
                    break;
                }
                case 2 : {
                    itemNameList = new String[]{
                            getWeaponLocal1ListByItemName(local1REQUIRE)[1],
                            getWeaponLocal2ListByItemName(local2REQUIRE)[1],
                            getWeaponCommonListByItemName(commonREQUIRE)[1]
                    };
                    itemRareList = new int[]{3,3,1};
                    break;
                }
                case 3 : {
                    itemNameList = new String[]{
                            getWeaponLocal1ListByItemName(local1REQUIRE)[2],
                            getWeaponLocal2ListByItemName(local2REQUIRE)[1],
                            getWeaponCommonListByItemName(commonREQUIRE)[1]
                    };
                    itemRareList = new int[]{4,3,2};
                    break;
                }
                case 4 : {
                    itemNameList = new String[]{
                            getWeaponLocal1ListByItemName(local1REQUIRE)[2],
                            getWeaponLocal2ListByItemName(local2REQUIRE)[2],
                            getWeaponCommonListByItemName(commonREQUIRE)[2]
                    };
                    itemRareList = new int[]{4,4,3};
                    break;
                }
                case 5 : {
                    itemNameList = new String[]{
                            getWeaponLocal1ListByItemName(local1REQUIRE)[3],
                            getWeaponLocal2ListByItemName(local2REQUIRE)[2],
                            getWeaponCommonListByItemName(commonREQUIRE)[2]
                    };
                    itemRareList = new int[]{5,4,3};
                    break;
                }
            }

            for (int x = 0 ; x < itemNameList.length ; x++){
                if (itemValueList[x] > 0) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_base_lvl_material_2048, base_lvl_ll, false);
                    ImageView img_bg  = char_view.findViewById(R.id.base_lvl_bg);
                    ImageView img_img = char_view.findViewById(R.id.base_lvl_img);
                    TextView img_tv = char_view.findViewById(R.id.base_lvl_tv);
                    Picasso.get().load(getRssByRare(itemRareList[x])).resize(150,180).into(img_bg);
                    Picasso.get().load(FileLoader.loadIMG(item_rss.getItemIcoByName(itemNameList[x],context),context)).resize(144,144).into(img_img);
                    img_tv.setText(prettyCount(itemValueList[x],0));

                    img_bg.getLayoutParams().width = 150;
                    img_bg.getLayoutParams().height = 180;
                    img_img.getLayoutParams().width = 144;
                    img_img.getLayoutParams().height = 144;
                    img_tv.getLayoutParams().width = 150;
                    img_tv.getLayoutParams().height = 36;
                    base_lvl_ll.addView(char_view);
                }
            }
        }
    }

    public Bitmap findWeaponByName(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();
        filePath = filePath.replace("\"","");
        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            if(e != null){Log.wtf("MISSED_IMG","暫時沒有 >>"+filePath+"<< 的相片");};
        }

        return bitmap;
    }

    public Bitmap findArtByName(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();
        filePath = filePath.replace("\"","");
        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            if(e != null){Log.wtf("MISSED_IMG","暫時沒有 >>"+filePath+"<< 的相片");};
        }

        return bitmap;
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

    public void colorGradient(TextView textView,String start_color, String end_color, boolean isColorGradient , String color){
        if(isColorGradient){
            Shader shader = new LinearGradient(0, 0, textView.getLineCount(), textView.getLineHeight(),
                    Color.parseColor(start_color),  Color.parseColor(end_color), Shader.TileMode.CLAMP);
            textView.getPaint().setShader(shader);
            textView.setTextColor(Color.parseColor(start_color));
        }else{
            textView.setTextColor(Color.parseColor(color));
        }

    }

    public void setAdviceText(String part, LinearLayout info_main_ll, TextView info_main_sand_tv, TextView info_main_goblet_tv, TextView info_main_circlet_tv, TextView info_main_sub_tv){
        String dps_advice = "";
        info_main_ll.setVisibility(View.VISIBLE);

        if(jsonObjectDps.has(part+"_art_sand_entry")){
            try {
                JSONArray dps_advice_tmpJ = jsonObjectDps.getJSONArray(part+"_art_sand_entry");
                String[] dps_advice_tmp = dps_advice_tmpJ.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                for (int x = 0 ; x < dps_advice_tmp.length ; x++){
                    dps_advice = dps_advice+item_rss.getArtifactBuffName(dps_advice_tmp[x],context);
                    if(x+1 < dps_advice_tmpJ.length()){
                        dps_advice = dps_advice+" > ";
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            info_main_sand_tv.setText(dps_advice);
            info_main_sand_tv.setVisibility(View.VISIBLE);
        }

        dps_advice = "";
        if(jsonObjectDps.has(part+"_art_goblet_entry")){
            try {
                JSONArray dps_advice_tmpJ = jsonObjectDps.getJSONArray(part+"_art_goblet_entry");
                String[] dps_advice_tmp = dps_advice_tmpJ.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                for (int x = 0 ; x < dps_advice_tmp.length ; x++){
                    dps_advice = dps_advice+item_rss.getArtifactBuffName(dps_advice_tmp[x],context);
                    if(x+1 < dps_advice_tmpJ.length()){
                        dps_advice = dps_advice+" > ";
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            info_main_goblet_tv.setText(dps_advice);
            info_main_goblet_tv.setVisibility(View.VISIBLE);
        }

        dps_advice = "";
        if(jsonObjectDps.has(part+"_art_circlet_entry")){
            try {
                JSONArray dps_advice_tmpJ = jsonObjectDps.getJSONArray(part+"_art_circlet_entry");
                String[] dps_advice_tmp = dps_advice_tmpJ.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                for (int x = 0 ; x < dps_advice_tmp.length ; x++){
                    dps_advice = dps_advice+item_rss.getArtifactBuffName(dps_advice_tmp[x],context);
                    if(x+1 < dps_advice_tmpJ.length()){
                        dps_advice = dps_advice+" > ";
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            info_main_circlet_tv.setText(dps_advice);
            info_main_circlet_tv.setVisibility(View.VISIBLE);
        }
        dps_advice = "";
        if(jsonObjectDps.has(part+"_art_sub_entry")){
            try {
                JSONArray dps_advice_tmpJ = jsonObjectDps.getJSONArray(part+"_art_sub_entry");
                String[] dps_advice_tmp = dps_advice_tmpJ.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                for (int x = 0 ; x < dps_advice_tmp.length ; x++){
                    dps_advice = dps_advice+item_rss.getArtifactBuffName(dps_advice_tmp[x],context);
                    if(x+1 < dps_advice_tmpJ.length()){
                        dps_advice = dps_advice+" > ";
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            info_main_sub_tv.setText(dps_advice);
            info_main_sub_tv.setVisibility(View.VISIBLE);
        }
        System.out.println(dps_advice);
    }

    public SpannableString setSpanAndTv(ForegroundColorSpan mColor, String str, String mWord){
        SpannableString mSpannavleString = new SpannableString(str);
        for (int i = -1; (i = str.indexOf(mWord, i + 1)) != -1; i++) {
            mSpannavleString.setSpan(mColor,str.indexOf(mWord),str.indexOf(mWord)+mWord.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return mSpannavleString;
    }

    /** UI -> PrettyCount */
    /**
     * @param type : Type of number's display
     *             0 : Int display
     *             1 : Percentage display
     * @return
     */
    public String prettyCount(Number number,int type) {
        char[] suffix = {' ', 'K', 'M', 'G', 'T', 'P', 'E', 'Z', 'Y'};
        double numDouble = number.doubleValue();
        String plus = "";
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        int decimal_num =  sharedPreferences.getInt("decimal_num", 0);
        boolean decimal  = sharedPreferences.getBoolean("decimal", false);

        switch (type){
            case 0 : return plus+new DecimalFormat("###,###,###,###,###").format(number);
            case 1 : return plus+(new DecimalFormat("###,###,###,###,###.##").format(numDouble*100))+"%";
            default: return plus+new DecimalFormat("###,###,###,###,###.#").format(number);
        }
    }
    public String[] getWeaponLocal1ListByItemName (String str){
        switch (str){
            case "漆黑隕鐵的一塊" : return new String[]{"漆黑隕鐵的一粒","漆黑隕鐵的一片","漆黑隕鐵的一角","漆黑隕鐵的一塊"};
            case "鳴神御靈的勇武" : return new String[]{"鳴神御靈的明惠","鳴神御靈的歡喜","鳴神御靈的親愛","鳴神御靈的勇武"};
            case "遠海夷地的金枝" : return new String[]{"遠海夷地的瑚枝","遠海夷地的玉枝","遠海夷地的瓊枝","遠海夷地的金枝"};
            case "凜風奔狼的懷鄉" : return new String[]{"凜風奔狼的始齔","凜風奔狼的裂齒","凜風奔狼的斷牙","凜風奔狼的懷鄉"};
            case "高塔孤王的碎夢" : return new String[]{"高塔孤王的破瓦","高塔孤王的殘垣","高塔孤王的斷片","高塔孤王的碎夢"};
            case "霧海雲間的轉還" : return new String[]{"霧海雲間的鉛丹","霧海雲間的汞丹","霧海雲間的金丹","霧海雲間的轉還"};
            case "獅牙鬥士的理想" : return new String[]{"獅牙鬥士的枷鎖","獅牙鬥士的鐵鍊","獅牙鬥士的鐐銬","獅牙鬥士的理想"};
            case "孤雲寒林的神體" : return new String[]{"孤雲寒林的光砂","孤雲寒林的輝岩","孤雲寒林的聖骸","孤雲寒林的神體"};
            case "今昔劇畫的鬼人" : return new String[]{"今昔劇畫的惡尉","今昔劇畫的虎囓","今昔劇畫的一角","今昔劇畫的鬼人"};

            default: return new String[]{"N/A","N/A","N/A","N/A"};
        }
    }
    public String[] getWeaponLocal2ListByItemName (String str){
        switch (str){
            case "混沌真眼" : return new String[]{"混沌機關","混沌樞紐","混沌真眼"};
            case "混沌爐心" : return new String[]{"混沌裝置","混沌迴路","混沌爐心"};
            case "石化的骨片" : return new String[]{"脆弱的骨片","結實的骨片","石化的骨片"};
            case "霧虛燈芯" : return new String[]{"霧虛草囊","霧虛草囊","霧虛燈芯"};
            case "督察長祭刀" : return new String[]{"獵兵祭刀","特工祭刀","督察長祭刀"};
            case "黑晶號角" : return new String[]{"沉重號角","黑銅號角","黑晶號角"};
            case "地脈的新芽" : return new String[]{"地脈的舊枝","地脈的枯葉","地脈的新芽"};
            case "偏光棱鏡" : return new String[]{"黯淡棱鏡","水晶棱鏡","偏光棱鏡"};
            case "隱獸鬼爪" : return new String[]{"隱獸指爪","隱獸利爪","隱獸鬼爪"};

            default: return new String[]{"N/A","N/A","N/A"};
        }
    }
    public String[] getWeaponCommonListByItemName (String str){
        switch (str){
            case "歷戰的箭簇" : return new String[]{"牢固的箭簇","銳利的箭簇","歷戰的箭簇"};
            case "禁咒繪卷" : return new String[]{"導能繪卷","封魔繪卷","禁咒繪卷"};
            case "攫金鴉印" : return new String[]{"尋寶鴉印","藏銀鴉印","攫金鴉印"};
            case "不祥的面具" : return new String[]{"破損的面具","污穢的面具","不祥的面具"};
            case "尉官的徽記" : return new String[]{"新兵的徽記","士官的徽記","尉官的徽記"};
            case "原素花蜜" : return new String[]{"騙騙花蜜","微光花蜜","原素花蜜"};
            case "史萊姆原漿" : return new String[]{"史萊姆凝液","史萊姆清","史萊姆原漿"};
            case "名刀鐔" : return new String[]{"破舊的刀鐔","影打刀鐔","名刀鐔"};
            case "浮游晶化核" : return new String[]{"浮游乾核","浮游幽核","浮游晶化核"};

            default: return new String[]{"N/A","N/A","N/A"};
        }
    }
    public int getRssByRare (int lvl){
        switch (lvl){
            case 1: return R.drawable.rare1_800x1000_light;
            case 2: return R.drawable.rare2_800x1000_light;
            case 3: return R.drawable.rare3_800x1000_light;
            case 4: return R.drawable.rare4_800x1000_light;
            case 5: return R.drawable.rare5_800x1000_light;
            default: return R.drawable.rare1_800x1000_light;
        }
    }

    public int[] expReturn(int exp){
        int exp_big , exp_mid , exp_small = 0;
        exp_big = (int) exp / 10000;
        exp = exp % 10000;
        exp_mid = (int) exp / 2000;
        exp = exp % 2000;
        exp_small = (int) exp / 400;
        if (exp % 400 < 400){
            exp_small = exp_small + 1;
        }

        return new int[]{exp_small,exp_mid,exp_big};
    }

}
