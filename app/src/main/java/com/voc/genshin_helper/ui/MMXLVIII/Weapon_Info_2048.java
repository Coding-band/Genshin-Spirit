package com.voc.genshin_helper.ui.MMXLVIII;

import static android.content.Context.MODE_PRIVATE;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.google.android.material.tabs.TabLayout.MODE_FIXED;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.Html;
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
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.Material;
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
 * Copyright © 2023 Xectorda 版權所有
 */

public class Weapon_Info_2048 {
    /** Method of requirements */
    Context context;
    Activity activity;
    SharedPreferences sharedPreferences ;
    ItemRss item_rss;
    Material material;
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


    private ViewPager viewPager;
    private ArrayList<View> viewPager_List;
    View weaponDescPage, weaponSkillPage, weaponAdvicePage;

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
        material = new Material();


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
        View view = View.inflate(context, R.layout.fragment_weapon_info_frame_2048, null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Window dialogWindowX = activity.getWindow();
        dialogWindowX.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 2O48 DESIGN
        dialogWindowX.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        dialogWindowX.setStatusBarColor(context.getColor(R.color.status_bar_2048));
        dialogWindowX.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

        /** Method of header */
        TabLayout info_tablelayout = view.findViewById(R.id.info_tablelayout);
        ImageView info_back_btn = view.findViewById(R.id.info_back_btn);
        ImageView info_header_bg = view.findViewById(R.id.info_header_bg);
        viewPager = (ViewPager) view.findViewById(R.id.vp);

        final LayoutInflater mInflater = activity.getLayoutInflater().from(context);
        weaponDescPage = mInflater.inflate(R.layout.fragment_weapon_info_desc_2048, null,false);
        weaponSkillPage = mInflater.inflate(R.layout.fragment_weapon_info_skill_2048, null,false);
        weaponAdvicePage = mInflater.inflate(R.layout.fragment_weapon_info_advice_2048, null,false);

        viewPager_List = new ArrayList<View>();
        viewPager_List.add(weaponDescPage);
        viewPager_List.add(weaponSkillPage);
        viewPager_List.add(weaponAdvicePage);
        viewPager.setAdapter(new MyViewPagerAdapter(viewPager_List));

        BackgroundReload.BackgroundReload(context,view);

        /** Method of info_detail */
        ConstraintLayout info_weapon_bg = weaponDescPage.findViewById(R.id.info_weapon_bg);
        //ConstraintLayout weapon_bg = view.findViewById(R.id.info_weapon_bg);
        ImageView weapon_img = weaponDescPage.findViewById(R.id.info_weapon_img);
        //ImageView weapon_layer = view.findViewById(R.id.info_weapon_layer);
        TextView weapon_name = weaponDescPage.findViewById(R.id.info_weapon_name);
        TextView info_weapon_name_base = weaponDescPage.findViewById(R.id.info_weapon_name_base);
        TextView weapon_obtain_way_tv = weaponDescPage.findViewById(R.id.info_obtain_way_tv);
        //TextView weapon_title = weaponDescPage.findViewById(R.id.info_weapon_title);
        RatingBar weapon_stars = weaponDescPage.findViewById(R.id.info_stars);
        //ImageView weapon_element = weaponDescPage.findViewById(R.id.info_element_img);
        //TextView weapon_area = weaponDescPage.findViewById(R.id.info_area_tv);
        //ImageView weapon_area_ico = weaponDescPage.findViewById(R.id.info_area_ico);
        ImageView weapon_weapon = weaponDescPage.findViewById(R.id.info_weapon);

        /** Method of introduce */
        //TextView info_intro = view.findViewById(R.id.info_intro);

        /** Method of value btn */
        //ImageView info_talent1_value_btn = weaponSkillPage.findViewById(R.id.info_talent1_value_btn);
        //ImageView info_talent2_value_btn = weaponSkillPage.findViewById(R.id.info_talent2_value_btn);
        //ImageView info_talent3_value_btn = weaponSkillPage.findViewById(R.id.info_talent3_value_btn);
        ImageView info_weapon_base_value_btn = weaponDescPage.findViewById(R.id.info_weapon_base_value_btn);

        /** Method of battle_talent */
        CardView weapon_talent_card = weaponSkillPage.findViewById(R.id.info_talent_card);
        TextView weapon_talent_name = weaponSkillPage.findViewById(R.id.info_talent_name);
        TextView weapon_talent_normal = weaponSkillPage.findViewById(R.id.info_talent_normal);


        LinearLayout base_lvl_ll = weaponAdvicePage.findViewById(R.id.base_lvl_ll);

        readWeaponMaterialByBuff(base_lvl_ll);

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

        /** HEADER */
        int[] tabItemImageArray = new int[]{R.drawable.ic_2048_weapon_intro_btn,R.drawable.ic_2048_talent_btn,R.drawable.ic_2048_advice_btn};
        int[] tabItemImageSelectedArray = new int[]{R.drawable.ic_2048_weapon_intro_btn_selected,R.drawable.ic_2048_talent_btn_selected,R.drawable.ic_2048_advice_btn_selected};

        for (int x = 0 ; x < tabItemImageArray.length ; x++){
            View view1 = activity.getLayoutInflater().inflate(R.layout.item_custom_tab, null);
            ImageView ico_img = view1.findViewById(R.id.icon);
            ico_img.setImageResource(tabItemImageArray[x]);
            info_tablelayout.addTab(info_tablelayout.newTab().setCustomView(view1).setId(x));
        }

        info_header_bg.getLayoutParams().height = info_tablelayout.getLayoutParams().height;

        info_tablelayout.selectTab(info_tablelayout.getTabAt(0));

        View view1 = info_tablelayout.getTabAt(0).getCustomView();
        ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
        tab_icon.setImageResource(tabItemImageSelectedArray[0]);

        info_tablelayout.setTabMode(MODE_FIXED);
        info_tablelayout.setTabIndicatorAnimationMode(TabLayout.INDICATOR_ANIMATION_MODE_ELASTIC);
        info_tablelayout.getLayoutParams().width = WRAP_CONTENT;
        info_tablelayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
                tab_icon.setImageResource(tabItemImageSelectedArray[tab.getPosition()]);

                viewPager.setCurrentItem(tab.getPosition());
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
                tab_icon.setImageResource(tabItemImageArray[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //info_tablelayout.selectTab(info_tablelayout.getTabAt(position));
            }

            @Override
            public void onPageSelected(int position) {
                info_tablelayout.selectTab(info_tablelayout.getTabAt(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        info_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        info_weapon_base_value_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.fragment_char_skill_dmg_2048, null);

                String[] lvList = new String[]{"LV1","LV5","LV10","LV15","LV20","LV20+","LV25","LV30","LV35","LV40","LV40+","LV45","LV50","LV50+","LV55","LV60","LV60+","LV65","LV70","LV70+","LV75","LV80","LV80+","LV85","LV90"};
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
                            String[] item_name = new String[]{context.getString(R.string.weapon_stat_atk),context.getString(R.string.weapon_stat_HPP),context.getString(R.string.weapon_stat_atkP),context.getString(R.string.weapon_stat_DEFP),context.getString(R.string.weapon_stat_CritRateP),context.getString(R.string.weapon_stat_CritDMGP),context.getString(R.string.weapon_stat_EnRechP),context.getString(R.string.weapon_stat_EleMas),context.getString(R.string.weapon_stat_PhyDMGP)};

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
        if(sharedPreferences.getBoolean("isBaseNameDisplay",false) == true){
            info_weapon_name_base.setVisibility(View.VISIBLE);
            info_weapon_name_base.setText(name);
        }

        Animation animImgLTR = AnimationUtils.loadAnimation(context,R.anim.img_ltr);
        Animation animImgRTL = AnimationUtils.loadAnimation(context,R.anim.img_rtl);
        weapon_img.setAnimation(animImgLTR);
        LinearLayout info_detail = weaponDescPage.findViewById(R.id.info_detail);
        info_detail.setAnimation(animImgRTL);

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            ImageView weapon_imgL = view.findViewById(R.id.info_weapon_img);
            weapon_imgL.setAnimation(animImgLTR);
            weapon_imgL.setImageDrawable(FileLoader.loadIMG2Drawable(item_rss.getWeaponGachaByName(name,context)[1],context));
        }else{
            weapon_img.setImageDrawable(FileLoader.loadIMG2Drawable(item_rss.getWeaponGachaByName(name,context)[1],context));
        }

        weapon_obtain_way_tv.setText(item_rss.getObtainCode(obtain_way,context));
        //weapon_title.setText(nick);
        //Picasso.get().load(FileLoader.loadIMG(item_rss.getWeaponByName(name,context)[0],context)).centerCrop().into(weapon_img);

        switch (weapon){
            case "Sword" :
            case "Polearm" :
            case "Claymore" : {weapon_img.setRotation(30);break;}
            case "Bow" :
            case "Catalyst" : {weapon_img.setRotation(0);break;}
        }

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
        weapon_weapon.setImageResource(item_rss.getWeaponTypeIMG(weapon,context));
        //weapon_role.setText(item_rss.getLocaleName(role,context));
        //weapon_sex.setText(item_rss.getLocaleName(sex,context));
        //weapon_birth.setText(item_rss.getLocaleBirth(birth,context)); // -> If necessary will change to Month | Day -> E.g. July 24th
        //info_intro.setText(desc);

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

    public void readWeaponMaterialByBuff(LinearLayout base_lvl_ll) {
        Spinner base_lvl_spinner = weaponAdvicePage.findViewById(R.id.base_lvl_spinner);
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

                img_bg.setBackgroundResource(getRssByRare(itemRareListBASE[x]));
                //Picasso.get().load(getRssByRare(itemRareListBASE[x])).resize(150,180).into(img_bg);
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
                            material.getWeaponLocal1ListByItemName(local1REQUIRE)[0],
                            material.getWeaponLocal2ListByItemName(local2REQUIRE)[0],
                            material.getWeaponCommonListByItemName(commonREQUIRE)[0]
                    };
                    itemRareList = new int[]{2,2,1};
                    break;
                }
                case 1 : {
                    itemNameList = new String[]{
                            material.getWeaponLocal1ListByItemName(local1REQUIRE)[1],
                            material.getWeaponLocal2ListByItemName(local2REQUIRE)[0],
                            material.getWeaponCommonListByItemName(commonREQUIRE)[0]
                    };
                    itemRareList = new int[]{3,2,1};
                    break;
                }
                case 2 : {
                    itemNameList = new String[]{
                            material.getWeaponLocal1ListByItemName(local1REQUIRE)[1],
                            material.getWeaponLocal2ListByItemName(local2REQUIRE)[1],
                            material.getWeaponCommonListByItemName(commonREQUIRE)[1]
                    };
                    itemRareList = new int[]{3,3,1};
                    break;
                }
                case 3 : {
                    itemNameList = new String[]{
                            material.getWeaponLocal1ListByItemName(local1REQUIRE)[2],
                            material.getWeaponLocal2ListByItemName(local2REQUIRE)[1],
                            material.getWeaponCommonListByItemName(commonREQUIRE)[1]
                    };
                    itemRareList = new int[]{4,3,2};
                    break;
                }
                case 4 : {
                    itemNameList = new String[]{
                            material.getWeaponLocal1ListByItemName(local1REQUIRE)[2],
                            material.getWeaponLocal2ListByItemName(local2REQUIRE)[2],
                            material.getWeaponCommonListByItemName(commonREQUIRE)[2]
                    };
                    itemRareList = new int[]{4,4,3};
                    break;
                }
                case 5 : {
                    itemNameList = new String[]{
                            material.getWeaponLocal1ListByItemName(local1REQUIRE)[3],
                            material.getWeaponLocal2ListByItemName(local2REQUIRE)[2],
                            material.getWeaponCommonListByItemName(commonREQUIRE)[2]
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

                    img_bg.setBackgroundResource(getRssByRare(itemRareList[x]));
                    //Picasso.get().load(getRssByRare(itemRareList[x])).resize(150,180).into(img_bg);
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
