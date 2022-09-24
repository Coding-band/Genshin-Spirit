package com.voc.genshin_helper.ui;

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
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.gridlayout.widget.GridLayout;

import com.squareup.picasso.Picasso;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.util.BackgroundReload;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.content.Context.MODE_PRIVATE;
import static com.voc.genshin_helper.data.RoundRectImageView.getRoundBitmapByShader;

//https://stackoverflow.com/questions/3592836/check-for-file-existence-in-androids-assets-folder/7337516

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

public class Characters_Info {
    /** Method of requirements */
    Context context;
    Activity activity;
    SharedPreferences sharedPreferences ;
    ItemRss item_rss;
    BackgroundReload backgroundReload;

    /** Method of Char's details' container */
    /** Since String can't be null, so there will have "XPR" for identify is result correct */
    String CharName_BASE = "";

    // Main
    String name = "XPR" ;
    int star = 4;
    String area = "XPR";
    String element = "XPR" ;
    String weapon = "XPR" ;
    String sex = "XPR" ;
    String birth = "XPR" ;
    String role = "XPR" ;
    boolean isComing = false ;
    String desc = "XPR" ;
    String nick = "XPR" ;
    JSONObject jsonObject;
    JSONObject jsonObjectDps;

    // Battle Talent
    String normal_name = "XPR";
    String normal_img = "XPR";
    String normal_desc1 = "XPR";
    String normal_desc2 = "XPR";
    String normal_desc3 = "XPR";
    String element_name = "XPR";
    String element_img = "XPR";
    String element_desc = "XPR";
    String final_name = "XPR";
    String final_img = "XPR";
    String final_desc = "XPR";
    String other_name = "XPR";
    String other_img = "XPR";
    String other_desc = "XPR";

    // Basic Talent
    String talent1_name = "XPR";
    String talent1_img = "XPR";
    String talent1_desc = "XPR";

    String talent2_name = "XPR";
    String talent2_img = "XPR";
    String talent2_desc = "XPR";

    String talent3_name = "XPR";
    String talent3_img = "XPR";
    String talent3_desc = "XPR";
    // Other skill
    String talent4_name = "XPR";
    String talent4_img = "XPR";
    String talent4_desc = "XPR";

    // SOF
    String sof1_name = "XPR";
    String sof1_img = "XPR";
    String sof1_desc = "XPR";
    String sof2_name = "XPR";
    String sof2_img = "XPR";
    String sof2_desc = "XPR";
    String sof3_name = "XPR";
    String sof3_img = "XPR";
    String sof3_desc = "XPR";
    String sof4_name = "XPR";
    String sof4_img = "XPR";
    String sof4_desc = "XPR";
    String sof5_name = "XPR";
    String sof5_img = "XPR";
    String sof5_desc = "XPR";
    String sof6_name = "XPR";
    String sof6_img = "XPR";
    String sof6_desc = "XPR";

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

    /** https://stackoverflow.com/questions/45247927/how-to-parse-json-object-inside-json-object-in-java */
    public void JsonToStr (String str , String str_dps){
        System.out.println("SSS"+str+"FFF");
        if(!str.equals("")){
            try {
            jsonObject = new JSONObject(str);
            name = jsonObject.getString("name");
            star = jsonObject.getInt("rare");
            area = jsonObject.getString("area");
            element = jsonObject.getString("element");
            weapon = jsonObject.getString("weapon");
            sex = jsonObject.getString("sex");
            birth = jsonObject.getString("birth");
            role = jsonObject.getString("role");
            isComing = jsonObject.getBoolean("isComingSoon");
            desc = jsonObject.getString("desc");
            nick = jsonObject.getString("nick");

            JSONObject battle_talent = jsonObject.getJSONObject("battle_talent");
            normal_name = battle_talent.getString("normal_name");
            normal_img = battle_talent.getString("normal_img");
            normal_desc1 = battle_talent.getString("normal_desc1");
            normal_desc2 = battle_talent.getString("normal_desc2");
            normal_desc3 = battle_talent.getString("normal_desc3");
            element_name = battle_talent.getString("element_name");
            element_img = battle_talent.getString("element_img");
            element_desc = battle_talent.getString("element_desc");
            final_name = battle_talent.getString("final_name");
            final_img = battle_talent.getString("final_img");
            final_desc = battle_talent.getString("final_desc");
            if(battle_talent.has("other_name")){
                other_name = battle_talent.getString("other_name");
                other_img = battle_talent.getString("other_img");
                other_desc = battle_talent.getString("other_desc");
            }

            JSONObject basic_talent = jsonObject.getJSONObject("basic_talent");
            talent1_name = basic_talent.getString("talent1_name");
            talent1_img = basic_talent.getString("talent1_img");
            talent1_desc = basic_talent.getString("talent1_desc");
            talent2_name = basic_talent.getString("talent2_name");
            talent2_img = basic_talent.getString("talent2_img");
            talent2_desc = basic_talent.getString("talent2_desc");
            if(basic_talent.has("talent3_name")){
                talent3_name = basic_talent.getString("talent3_name");
                talent3_img = basic_talent.getString("talent3_img");
                talent3_desc = basic_talent.getString("talent3_desc");
            }

            JSONObject sof = jsonObject.getJSONObject("sof");
            sof1_name = sof.getString("sof1_name");
            sof1_img = sof.getString("sof1_img");
            sof1_desc = sof.getString("sof1_desc");
            sof2_name = sof.getString("sof2_name");
            sof2_img = sof.getString("sof2_img");
            sof2_desc = sof.getString("sof2_desc");
            sof3_name = sof.getString("sof3_name");
            sof3_img = sof.getString("sof3_img");
            sof3_desc = sof.getString("sof3_desc");
            sof4_name = sof.getString("sof4_name");
            sof4_img = sof.getString("sof4_img");
            sof4_desc = sof.getString("sof4_desc");
            sof5_name = sof.getString("sof5_name");
            sof5_img = sof.getString("sof5_img");
            sof5_desc = sof.getString("sof5_desc");
            sof6_name = sof.getString("sof6_name");
            sof6_img = sof.getString("sof6_img");
            sof6_desc = sof.getString("sof6_desc");

            if(str_dps != null){
                try {
                    jsonObjectDps = new JSONObject(str_dps);

                    if(jsonObjectDps.has("dps_weapons")){
                        JSONArray weapons_temp = jsonObjectDps.getJSONArray("dps_weapons");
                        main_weapon_advice = weapons_temp.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                    }
                    if(jsonObjectDps.has("sup_dps_weapons")){
                        JSONArray weapons_temp = jsonObjectDps.getJSONArray("sup_dps_weapons");
                        support_weapon_advice = weapons_temp.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                    }
                    if(jsonObjectDps.has("util_weapons")){
                        JSONArray weapons_temp = jsonObjectDps.getJSONArray("util_weapons");
                        util_weapon_advice = weapons_temp.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                    }
            /*

            if(jsonObjectDps.has("weapons_star")) {
                JSONArray stars_temp = jsonObjectDps.getJSONArray("weapons_star");
                String[] strx = stars_temp.toString().replace("[", "").replace("]", "").replace(", ", "").split(",");
                Log.wtf("DDD", Arrays.toString(strx));
                weapon_stars = new int[strx.length];
                for (int x = 0 ; x < strx.length ; x ++){
                    weapon_stars[x] = Integer.parseInt(strx[x]);
                }
            }
             */

                    if(jsonObjectDps.has("dps_art1")) {
                        JSONArray art1_temp = jsonObjectDps.getJSONArray("dps_art1");
                        main_artifacts1 = art1_temp.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                    }

                    if(jsonObjectDps.has("dps_art2")) {
                        JSONArray art2_temp = jsonObjectDps.getJSONArray("dps_art2");
                        main_artifacts2 = art2_temp.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                    }

                    if(jsonObjectDps.has("dps_art3")) {
                        JSONArray art3_temp = jsonObjectDps.getJSONArray("dps_art3");
                        main_artifacts3 = art3_temp.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                    }

                    if(jsonObjectDps.has("dps_art4")) {
                        JSONArray art4_temp = jsonObjectDps.getJSONArray("dps_art4");
                        main_artifacts4 = art4_temp.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                    }

                    if(jsonObjectDps.has("dps_art5")) {
                        JSONArray art5_temp = jsonObjectDps.getJSONArray("dps_art5");
                        main_artifacts5 = art5_temp.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                    }

                    if(jsonObjectDps.has("sup_dps_art1")) {
                        JSONArray art1_temp = jsonObjectDps.getJSONArray("sup_dps_art1");
                        support_artifacts1 = art1_temp.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                    }
                    if(jsonObjectDps.has("sup_dps_art2")) {
                        JSONArray art2_temp = jsonObjectDps.getJSONArray("sup_dps_art2");
                        support_artifacts2 = art2_temp.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                    }
                    if(jsonObjectDps.has("sup_dps_art3")) {
                        JSONArray art3_temp = jsonObjectDps.getJSONArray("sup_dps_art3");
                        support_artifacts3 = art3_temp.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                    }
                    if(jsonObjectDps.has("sup_dps_art4")) {
                        JSONArray art4_temp = jsonObjectDps.getJSONArray("sup_dps_art4");
                        support_artifacts4 = art4_temp.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                    }
                    if(jsonObjectDps.has("sup_dps_art5")) {
                        JSONArray art5_temp = jsonObjectDps.getJSONArray("sup_dps_art5");
                        support_artifacts5 = art5_temp.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                    }

                    if(jsonObjectDps.has("util_art1")) {
                        JSONArray art1_temp = jsonObjectDps.getJSONArray("util_art1");
                        util_artifacts1 = art1_temp.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                    }
                    if(jsonObjectDps.has("util_art2")) {
                        JSONArray art2_temp = jsonObjectDps.getJSONArray("util_art2");
                        util_artifacts2 = art2_temp.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                    }
                    if(jsonObjectDps.has("util_art3")) {
                        JSONArray art3_temp = jsonObjectDps.getJSONArray("util_art3");
                        util_artifacts3 = art3_temp.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                    }
                    if(jsonObjectDps.has("util_art4")) {
                        JSONArray art4_temp = jsonObjectDps.getJSONArray("util_art4");
                        util_artifacts4 = art4_temp.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                    }
                    if(jsonObjectDps.has("util_art5")) {
                        JSONArray art5_temp = jsonObjectDps.getJSONArray("util_art5");
                        util_artifacts5 = art5_temp.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                    }

                    if(jsonObjectDps.has("team1")) {
                        JSONArray art1_temp = jsonObjectDps.getJSONArray("team1");
                        team1 = art1_temp.toString().replace("[", "").replace("]", "").replace("\"","").replace(" ", "XPR").replace(",", " ").split(" ");
                    }
                    if(jsonObjectDps.has("team2")) {
                        JSONArray art2_temp = jsonObjectDps.getJSONArray("team2");
                        team2 = art2_temp.toString().replace("[", "").replace("]", "").replace("\"","").replace(" ", "XPR").replace(",", " ").split(" ");
                    }
                    if(jsonObjectDps.has("team3")) {
                        JSONArray art3_temp = jsonObjectDps.getJSONArray("team3");
                        team3 = art3_temp.toString().replace("[", "").replace("]", "").replace("\"","").replace(" ", "XPR").replace(",", " ").split(" ");
                    }

                    if(jsonObjectDps.has("team4")) {
                        JSONArray art4_temp = jsonObjectDps.getJSONArray("team4");
                        team4 = art4_temp.toString().replace("[", "").replace("]", "").replace("\"","").replace(" ", "XPR").replace(",", " ").split(" ");
                    }
                    if(jsonObjectDps.has("team5")) {
                        JSONArray art5_temp = jsonObjectDps.getJSONArray("team5");
                        team5 = art5_temp.toString().replace("[", "").replace("]", "").replace("\"","").replace(" ", "XPR").replace(",", " ").split(" ");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            show();
        } catch (JSONException e) {
            e.printStackTrace();
            }
        }else{
            CustomToast.toast(context,activity,context.getString(R.string.none_info));
        }
    }

    public void setup (String CharName_BASE, Context context,Activity activity){
        sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        this.CharName_BASE = CharName_BASE.replace(" ","_");
        this.context = context;
        this.activity = activity;
        item_rss = new ItemRss();


        String lang = sharedPreferences.getString("curr_lang","zh-HK");
        String is = null;
        String is_dps = null;
        String is_default = null;

        is_dps = LoadData("db/char/char_advice/"+this.CharName_BASE+".json");
        is_default = LoadData("db/char/en-US/"+this.CharName_BASE+".json");
        is = LoadData("db/char/"+lang+"/"+this.CharName_BASE+".json");

        if(is != null){
            JsonToStr(is,is_dps);
        }else if(is_default != null){
            JsonToStr(is_default,is_dps);
        }
    }


    public void show() {
        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.fragment_char_info, null);

        //BackgroundReload.BackgroundReload(context,view);

        /** Method of info_detail */
        FrameLayout char_bg = view.findViewById(R.id.info_char_bg);
        ImageView char_img = view.findViewById(R.id.info_char_img);
        ImageView char_layer = view.findViewById(R.id.info_char_layer);
        TextView char_name = view.findViewById(R.id.info_char_name);
        RatingBar char_stars = view.findViewById(R.id.info_stars);
        ImageView char_element = view.findViewById(R.id.info_element_img);
        TextView char_area = view.findViewById(R.id.info_area_tv);
        ImageView char_area_ico = view.findViewById(R.id.info_area_ico);
        ImageView char_weapon = view.findViewById(R.id.info_weapon);
        TextView char_role = view.findViewById(R.id.info_role);
        TextView char_sex = view.findViewById(R.id.info_sex);
        TextView char_birth = view.findViewById(R.id.info_date);

        /** Method of introduce */
        TextView info_intro = view.findViewById(R.id.info_intro);

        /** Method of battle_talent */
        CardView char_talent1_card = view.findViewById(R.id.info_talent1_card);
        ImageView char_talent1_ico = view.findViewById(R.id.info_talent1_ico);
        TextView char_talent1_name = view.findViewById(R.id.info_talent1_name);
        TextView char_talent1_normal = view.findViewById(R.id.info_talent1_normal);
        TextView char_talent1_hard = view.findViewById(R.id.info_talent1_hard);
        TextView char_talent1_drop = view.findViewById(R.id.info_talent1_drop);

        CardView char_talent2_card = view.findViewById(R.id.info_talent2_card);
        ImageView char_talent2_ico = view.findViewById(R.id.info_talent2_ico);
        TextView char_talent2_name = view.findViewById(R.id.info_talent2_name);
        TextView char_talent2_normal = view.findViewById(R.id.info_talent2_normal);

        CardView char_talent3_card = view.findViewById(R.id.info_talent3_card);
        ImageView char_talent3_ico = view.findViewById(R.id.info_talent3_ico);
        TextView char_talent3_name = view.findViewById(R.id.info_talent3_name);
        TextView char_talent3_normal = view.findViewById(R.id.info_talent3_normal);

        CardView char_talent4_card = view.findViewById(R.id.info_talent4_card);
        ImageView char_talent4_ico = view.findViewById(R.id.info_talent4_ico);
        TextView char_talent4_name = view.findViewById(R.id.info_talent4_name);
        TextView char_talent4_normal = view.findViewById(R.id.info_talent4_normal);

        /** Method of basic_talent */
        CardView char_basic_talent1_card = view.findViewById(R.id.info_btalent1_card);
        ImageView char_basic_talent1_ico = view.findViewById(R.id.info_btalent1_ico);
        TextView char_basic_talent1_name = view.findViewById(R.id.info_btalent1_name);
        TextView char_basic_talent1_normal = view.findViewById(R.id.info_btalent1_normal);

        CardView char_basic_talent2_card = view.findViewById(R.id.info_btalent2_card);
        ImageView char_basic_talent2_ico = view.findViewById(R.id.info_btalent2_ico);
        TextView char_basic_talent2_name = view.findViewById(R.id.info_btalent2_name);
        TextView char_basic_talent2_normal = view.findViewById(R.id.info_btalent2_normal);

        CardView char_basic_talent3_card = view.findViewById(R.id.info_btalent3_card);
        ImageView char_basic_talent3_ico = view.findViewById(R.id.info_btalent3_ico);
        TextView char_basic_talent3_name = view.findViewById(R.id.info_btalent3_name);
        TextView char_basic_talent3_normal = view.findViewById(R.id.info_btalent3_normal);

        /** Method of sof */
        CardView char_sof1_card = view.findViewById(R.id.info_sof1_card);
        ImageView char_sof1_ico = view.findViewById(R.id.info_sof1_ico);
        TextView char_sof1_name = view.findViewById(R.id.info_sof1_name);
        TextView char_sof1_normal = view.findViewById(R.id.info_sof1_normal);

        CardView char_sof2_card = view.findViewById(R.id.info_sof2_card);
        ImageView char_sof2_ico = view.findViewById(R.id.info_sof2_ico);
        TextView char_sof2_name = view.findViewById(R.id.info_sof2_name);
        TextView char_sof2_normal = view.findViewById(R.id.info_sof2_normal);

        CardView char_sof3_card = view.findViewById(R.id.info_sof3_card);
        ImageView char_sof3_ico = view.findViewById(R.id.info_sof3_ico);
        TextView char_sof3_name = view.findViewById(R.id.info_sof3_name);
        TextView char_sof3_normal = view.findViewById(R.id.info_sof3_normal);

        CardView char_sof4_card = view.findViewById(R.id.info_sof4_card);
        ImageView char_sof4_ico = view.findViewById(R.id.info_sof4_ico);
        TextView char_sof4_name = view.findViewById(R.id.info_sof4_name);
        TextView char_sof4_normal = view.findViewById(R.id.info_sof4_normal);

        CardView char_sof5_card = view.findViewById(R.id.info_sof5_card);
        ImageView char_sof5_ico = view.findViewById(R.id.info_sof5_ico);
        TextView char_sof5_name = view.findViewById(R.id.info_sof5_name);
        TextView char_sof5_normal = view.findViewById(R.id.info_sof5_normal);

        CardView char_sof6_card = view.findViewById(R.id.info_sof6_card);
        ImageView char_sof6_ico = view.findViewById(R.id.info_sof6_ico);
        TextView char_sof6_name = view.findViewById(R.id.info_sof6_name);
        TextView char_sof6_normal = view.findViewById(R.id.info_sof6_normal);

        /** Method of advice */
        GridLayout advice_main_weapon_ll = view.findViewById(R.id.advice_main_weapon_ll);
        LinearLayout advice_main_art_ll1 = view.findViewById(R.id.advice_main_art_ll1);
        LinearLayout advice_main_art_ll2 = view.findViewById(R.id.advice_main_art_ll2);
        LinearLayout advice_main_art_ll3 = view.findViewById(R.id.advice_main_art_ll3);
        LinearLayout advice_main_art_ll4 = view.findViewById(R.id.advice_main_art_ll4);
        LinearLayout advice_main_art_ll5 = view.findViewById(R.id.advice_main_art_ll5);

        GridLayout advice_support_weapon_ll = view.findViewById(R.id.advice_support_weapon_ll);
        LinearLayout advice_support_art_ll1 = view.findViewById(R.id.advice_support_art_ll1);
        LinearLayout advice_support_art_ll2 = view.findViewById(R.id.advice_support_art_ll2);
        LinearLayout advice_support_art_ll3 = view.findViewById(R.id.advice_support_art_ll3);
        LinearLayout advice_support_art_ll4 = view.findViewById(R.id.advice_support_art_ll4);
        LinearLayout advice_support_art_ll5 = view.findViewById(R.id.advice_support_art_ll5);

        GridLayout advice_util_weapon_ll = view.findViewById(R.id.advice_util_weapon_ll);
        LinearLayout advice_util_art_ll1 = view.findViewById(R.id.advice_util_art_ll1);
        LinearLayout advice_util_art_ll2 = view.findViewById(R.id.advice_util_art_ll2);
        LinearLayout advice_util_art_ll3 = view.findViewById(R.id.advice_util_art_ll3);
        LinearLayout advice_util_art_ll4 = view.findViewById(R.id.advice_util_art_ll4);
        LinearLayout advice_util_art_ll5 = view.findViewById(R.id.advice_util_art_ll5);

        CardView info_advice_main_card = view.findViewById(R.id.info_advice_main_card);
        CardView info_advice_support_card = view.findViewById(R.id.info_advice_support_card);
        CardView info_advice_util_card = view.findViewById(R.id.info_advice_util_card);
        CardView info_advice_team_card = view.findViewById(R.id.info_advice_team_card);

        LinearLayout advice_team_ll1 = view.findViewById(R.id.advice_team1);
        LinearLayout advice_team_ll2 = view.findViewById(R.id.advice_team2);
        LinearLayout advice_team_ll3 = view.findViewById(R.id.advice_team3);
        LinearLayout advice_team_ll4 = view.findViewById(R.id.advice_team4);
        LinearLayout advice_team_ll5 = view.findViewById(R.id.advice_team5);

        TextView info_advice_main_art_info = view.findViewById(R.id.info_advice_main_art_info);
        TextView info_advice_support_art_info = view.findViewById(R.id.info_advice_support_art_info);
        TextView info_advice_util_art_info = view.findViewById(R.id.info_advice_util_art_info);

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



        TextView barrier1 = view.findViewById(R.id.barrier1);
        TextView barrier2 = view.findViewById(R.id.barrier2);
        TextView barrier3 = view.findViewById(R.id.barrier3);
        TextView barrier4 = view.findViewById(R.id.barrier4);
        TextView barrier5 = view.findViewById(R.id.barrier5);
        TextView info_intro_title = view.findViewById(R.id.info_intro_title);
        TextView info_talent = view.findViewById(R.id.info_talent);
        TextView info_btalent = view.findViewById(R.id.info_btalent);
        TextView info_sof = view.findViewById(R.id.info_sof);
        TextView info_advice = view.findViewById(R.id.info_advice);

        TextView info_talent1_name = view.findViewById(R.id.info_talent1_name);
        TextView info_talent1_normal_title = view.findViewById(R.id.info_talent1_normal_title);
        TextView info_talent1_hard_title = view.findViewById(R.id.info_talent1_hard_title);
        TextView info_talent1_drop_title = view.findViewById(R.id.info_talent1_drop_title);
        TextView info_talent2_name = view.findViewById(R.id.info_talent2_name);
        TextView info_talent3_name = view.findViewById(R.id.info_talent3_name);
        TextView info_talent4_name = view.findViewById(R.id.info_talent4_name);

        TextView info_btalent1_name = view.findViewById(R.id.info_btalent1_name);
        TextView info_btalent2_name = view.findViewById(R.id.info_btalent2_name);
        TextView info_btalent3_name = view.findViewById(R.id.info_btalent3_name);

        TextView info_sof1_name = view.findViewById(R.id.info_sof1_name);
        TextView info_sof2_name = view.findViewById(R.id.info_sof2_name);
        TextView info_sof3_name = view.findViewById(R.id.info_sof3_name);
        TextView info_sof4_name = view.findViewById(R.id.info_sof4_name);
        TextView info_sof5_name = view.findViewById(R.id.info_sof5_name);
        TextView info_sof6_name = view.findViewById(R.id.info_sof6_name);

        LinearLayout info_advice_ll = view.findViewById(R.id.info_advice_ll);
        info_advice_ll.setVisibility(View.GONE);
        barrier5.setVisibility(View.GONE);

        if(isColorGradient){
            barrier1.setBackgroundColor(Color.parseColor(start_color));
            barrier2.setBackgroundColor(Color.parseColor(start_color));
            barrier3.setBackgroundColor(Color.parseColor(start_color));
            barrier4.setBackgroundColor(Color.parseColor(start_color));
            barrier5.setBackgroundColor(Color.parseColor(start_color));


        }else{
            barrier1.setBackgroundColor(Color.parseColor(color_hex));
            barrier2.setBackgroundColor(Color.parseColor(color_hex));
            barrier3.setBackgroundColor(Color.parseColor(color_hex));
            barrier4.setBackgroundColor(Color.parseColor(color_hex));
            barrier5.setBackgroundColor(Color.parseColor(color_hex));

        }

        colorGradient(info_intro_title ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_talent ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_btalent ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_sof ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_advice ,start_color,end_color,isColorGradient,color_hex);
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

        /**
         * PLS REMEMBER ADD BACK SUGGESTED WEAPON,ART IN XML
         */

        /** MAIN */
        char_name.setText("【"+nick+"】 "+item_rss.getCharByName(name,context)[1]);
        char_img.setImageDrawable(FileLoader.loadIMG2Drawable(item_rss.getCharByName(name,context)[2],context));

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height_curr = displayMetrics.heightPixels;
        int width_curr = displayMetrics.widthPixels;

        char_layer.getLayoutParams().height = width_curr/20;
        char_layer.requestLayout();

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            char_layer.getLayoutParams().height = width_curr/40;
            char_layer.requestLayout();
        }

        //char_img.setBackgroundResource(item_rss.getElementByName(element)[2]);

        boolean isNight = false;
        // Background of item
        if (context.getResources().getString(R.string.mode).equals("Night")) {
            isNight = true;
        }

        switch (element){
            case "Anemo" : {
                Picasso.get()
                        .load(R.drawable.anemo_layer).fit().centerCrop()
                        .error(R.drawable.paimon_lost)
                        .into(char_layer);
                if(isNight == true){
                    char_bg.setBackgroundResource(R.drawable.anemo_800x800_dark);
                }else{
                    char_bg.setBackgroundResource(R.drawable.anemo_800x800_light);
                }
                break;
            }

            case "Cryo" : {
                Picasso.get()
                        .load(R.drawable.cryo_layer).fit().centerCrop()
                        .error(R.drawable.paimon_lost)
                        .into(char_layer);
                if(isNight == true){
                    char_bg.setBackgroundResource(R.drawable.cryo_800x800_dark);
                }else{
                    char_bg.setBackgroundResource(R.drawable.cryo_800x800_light);
                }
                break;
            }

            case "Dendro" : {

                Picasso.get()
                        .load(R.drawable.dendro_layer).fit().centerCrop()
                        .error(R.drawable.paimon_lost)
                        .into(char_layer);
                if(isNight == true){
                    char_bg.setBackgroundResource(R.drawable.dendro_800x800_dark);
                }else{
                    char_bg.setBackgroundResource(R.drawable.dendro_800x800_light);
                }
                break;
            }

            case "Electro" : {
                Picasso.get()
                        .load(R.drawable.electro_layer).fit().centerCrop()
                        .error(R.drawable.paimon_lost)
                        .into(char_layer);
                if(isNight == true){
                    char_bg.setBackgroundResource(R.drawable.electro_800x800_dark);
                }else{
                    char_bg.setBackgroundResource(R.drawable.electro_800x800_light);
                }
                break;
            }

            case "Geo" : {
                Picasso.get()
                        .load(R.drawable.geo_layer).fit().centerCrop()
                        .error(R.drawable.paimon_lost)
                        .into(char_layer);
                if(isNight == true){
                    char_bg.setBackgroundResource(R.drawable.geo_800x800_dark);
                }else{
                    char_bg.setBackgroundResource(R.drawable.geo_800x800_light);
                }
                break;
            }

            case "Hydro" : {
                Picasso.get()
                        .load(R.drawable.hydro_layer).fit().centerCrop()
                        .error(R.drawable.paimon_lost)
                        .into(char_layer);
                if(isNight == true){
                    char_bg.setBackgroundResource(R.drawable.hydro_800x800_dark);
                }else{
                    char_bg.setBackgroundResource(R.drawable.hydro_800x800_light);
                }
                break;
            }

            case "Pyro" : {
                Picasso.get()
                        .load(R.drawable.pyro_layer).fit().centerCrop()
                        .error(R.drawable.paimon_lost)
                        .into(char_layer);
                if(isNight == true){
                    char_bg.setBackgroundResource(R.drawable.pyro_800x800_dark);
                }else{
                    char_bg.setBackgroundResource(R.drawable.pyro_800x800_light);
                }
                break;
            }


        }

        char_stars.setNumStars(star);
        char_stars.setRating(star);
        char_element.setImageResource(item_rss.getElementByName(element)[0]);
        char_area.setText(item_rss.getLocaleName(area,context));
        char_area_ico.setImageResource(item_rss.getDistrictIMG(area));
        char_weapon.setImageResource(item_rss.getWeaponTypeIMG(weapon));
        char_role.setText(item_rss.getLocaleName(role,context));
        char_sex.setText(item_rss.getLocaleName(sex,context));
        char_birth.setText(item_rss.getLocaleBirth(birth,context,false)); // -> If necessary will change to Month | Day -> E.g. July 24th
        info_intro.setText(desc);

        /** BATTLE_TALENT */
        char_talent1_ico.setImageDrawable(item_rss.getTalentIcoByName(normal_img,context));
        char_talent1_name.setText(talent1_name);
        char_talent1_normal.setText(normal_desc1);
        char_talent1_hard.setText(normal_desc2);
        char_talent1_drop.setText(normal_desc3);

        char_talent2_ico.setImageDrawable(item_rss.getTalentIcoByName(element_img,context));
        char_talent2_name.setText(element_name);
        char_talent2_normal.setText(Html.fromHtml(element_desc));

        char_talent3_ico.setImageDrawable(item_rss.getTalentIcoByName(final_img,context));
        char_talent3_name.setText(final_name);
        char_talent3_normal.setText(Html.fromHtml(final_desc));

        if(other_name != "XPR"){
            char_talent4_card.setVisibility(View.VISIBLE);
            char_talent4_ico.setImageDrawable(item_rss.getTalentIcoByName(other_img,context));
            char_talent4_name.setText(other_name);
            char_talent4_normal.setText(Html.fromHtml(other_desc));
        }

        /** BASIC_TALENT */
        char_basic_talent1_ico.setImageDrawable(item_rss.getTalentIcoByName(talent1_img,context));
        char_basic_talent1_name.setText(talent1_name);
        char_basic_talent1_normal.setText(talent1_desc);

        char_basic_talent2_ico.setImageDrawable(item_rss.getTalentIcoByName(talent2_img,context));
        char_basic_talent2_name.setText(talent2_name);
        char_basic_talent2_normal.setText(talent2_desc);

        if(!talent3_name.isEmpty() && !talent3_name.equals("XPR")){
            char_basic_talent3_card.setVisibility(View.VISIBLE);
            char_basic_talent3_ico.setImageDrawable(item_rss.getTalentIcoByName(talent3_img,context));
            char_basic_talent3_name.setText(talent3_name);
            char_basic_talent3_normal.setText(talent3_desc);
        }

        /** Soul of Life*/
        char_sof1_ico.setImageDrawable(item_rss.getTalentIcoByName(sof1_img,context));
        char_sof1_name.setText(sof1_name);
        char_sof1_normal.setText(sof1_desc);

        char_sof2_ico.setImageDrawable(item_rss.getTalentIcoByName(sof2_img,context));
        char_sof2_name.setText(sof2_name);
        char_sof2_normal.setText(sof2_desc);

        char_sof3_ico.setImageDrawable(item_rss.getTalentIcoByName(sof3_img,context));
        char_sof3_name.setText(sof3_name);
        char_sof3_normal.setText(sof3_desc);

        char_sof4_ico.setImageDrawable(item_rss.getTalentIcoByName(sof4_img,context));
        char_sof4_name.setText(sof4_name);
        char_sof4_normal.setText(sof4_desc);

        char_sof5_ico.setImageDrawable(item_rss.getTalentIcoByName(sof5_img,context));
        char_sof5_name.setText(sof5_name);
        char_sof5_normal.setText(sof5_desc);

        char_sof6_ico.setImageDrawable(item_rss.getTalentIcoByName(sof6_img,context));
        char_sof6_name.setText(sof6_name);
        char_sof6_normal.setText(sof6_desc);

        info_advice_main_card.setVisibility(View.GONE);
        info_advice_support_card.setVisibility(View.GONE);
        info_advice_util_card.setVisibility(View.GONE);
        info_advice_team_card.setVisibility(View.GONE);

        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(25, 0, RoundedCornersTransformation.CornerType.ALL);

        if(jsonObjectDps != null){
            info_advice_ll.setVisibility(View.VISIBLE);
            barrier5.setVisibility(View.VISIBLE);
            /** ADVICE */
            if(jsonObjectDps.has("dps_weapons")){
                Log.wtf("OK","AK");
                advice_main_weapon_ll.removeAllViews();
                advice_main_weapon_ll.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
                for (int x = 0 , c = 0, r = 0; x < main_weapon_advice.length; x++,c++) {
                    if(c == 5) { c = 0;r++; }
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice, advice_main_weapon_ll, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getWeaponByName(item_rss.getWeaponNameByFileName(main_weapon_advice[x]),context)[1],context)).resize(128,128).transform(roundedCornersTransformation)
                            .error(R.drawable.paimon_lost)
                            .into(item_img);
                    GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                    param.height = GridLayout.LayoutParams.WRAP_CONTENT;
                    param.width = GridLayout.LayoutParams.WRAP_CONTENT;
                    param.setGravity(Gravity.CENTER_VERTICAL);
                    param.columnSpec = GridLayout.spec(c);
                    param.rowSpec = GridLayout.spec(r);
                    param.setMargins(16,0,16,0);
                    char_view.setLayoutParams (param);
                    advice_main_weapon_ll.addView(char_view);
                }
                info_advice_main_card.setVisibility(View.VISIBLE);
                advice_main_weapon_ll.setVisibility(View.VISIBLE);
            }
            if(jsonObjectDps.has("sup_dps_weapons")){
                advice_support_weapon_ll.removeAllViews();
                advice_support_weapon_ll.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
                for (int x = 0  , c = 0, r = 0; x < support_weapon_advice.length; x++,c++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice, advice_support_weapon_ll, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getWeaponByName(item_rss.getWeaponNameByFileName(support_weapon_advice[x]),context)[1],context)).resize(128,128).transform(roundedCornersTransformation)
                            .error(R.drawable.paimon_lost)
                            .into(item_img);
                    GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                    param.height = GridLayout.LayoutParams.WRAP_CONTENT;
                    param.width = GridLayout.LayoutParams.WRAP_CONTENT;
                    param.setGravity(Gravity.CENTER_VERTICAL);
                    param.columnSpec = GridLayout.spec(c);
                    param.rowSpec = GridLayout.spec(r);
                    param.setMargins(16,0,16,0);
                    char_view.setLayoutParams (param);
                    advice_support_weapon_ll.addView(char_view);
                }
                info_advice_support_card.setVisibility(View.VISIBLE);
                advice_support_weapon_ll.setVisibility(View.VISIBLE);
            }
            if(jsonObjectDps.has("util_weapons")){
                advice_util_weapon_ll.removeAllViews();
                advice_util_weapon_ll.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
                for (int x = 0 ,c = 0 , r = 0; x < util_weapon_advice.length; x++,c++) {
                    if(c == 5) { c = 0;r++; }
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice, advice_util_weapon_ll, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getWeaponByName(item_rss.getWeaponNameByFileName(util_weapon_advice[x]),context)[1],context)).resize(128,128).transform(roundedCornersTransformation)
                            .error(R.drawable.paimon_lost)
                            .into(item_img);
                    GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                    param.height = GridLayout.LayoutParams.MATCH_PARENT;
                    param.width = GridLayout.LayoutParams.MATCH_PARENT;
                    param.setGravity(Gravity.CENTER_VERTICAL);
                    param.columnSpec = GridLayout.spec(c);
                    param.rowSpec = GridLayout.spec(r);
                    param.setMargins(16,0,16,0);
                    char_view.setLayoutParams (param);
                    advice_util_weapon_ll.addView(char_view);
                }
                info_advice_util_card.setVisibility(View.VISIBLE);
                advice_util_weapon_ll.setVisibility(View.VISIBLE);
            }

            if(jsonObjectDps.has("dps_art1")){
                advice_main_art_ll1.removeAllViews();
                for (int x = 0 ; x < main_artifacts1.length; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice, advice_main_art_ll1, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(main_artifacts1[x]),context)[x+1],context)).fit().centerInside().transform(roundedCornersTransformation)
                            .error(R.drawable.paimon_lost)
                            .into(item_img);
                    advice_main_art_ll1.addView(char_view);
                }
                advice_main_art_ll1.setVisibility(View.VISIBLE);
            }

            if(jsonObjectDps.has("dps_art2")){
                advice_main_art_ll2.removeAllViews();
                for (int x = 0 ; x < main_artifacts2.length; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice, advice_main_art_ll2, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(main_artifacts2[x]),context)[x+1],context)).fit().centerInside().transform(roundedCornersTransformation)
                            .error(R.drawable.paimon_lost)
                            .into(item_img);
                    advice_main_art_ll2.addView(char_view);
                }
                advice_main_art_ll2.setVisibility(View.VISIBLE);
            }

            if(jsonObjectDps.has("dps_art3")){
                advice_main_art_ll3.removeAllViews();
                for (int x = 0 ; x < main_artifacts3.length; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice, advice_main_art_ll3, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(main_artifacts3[x]),context)[x+1],context)).fit().centerInside().transform(roundedCornersTransformation)
                            .error(R.drawable.paimon_lost)
                            .into(item_img);
                    advice_main_art_ll3.addView(char_view);
                }
                advice_main_art_ll3.setVisibility(View.VISIBLE);
            }

            if(jsonObjectDps.has("dps_art4")){
                advice_main_art_ll4.removeAllViews();
                for (int x = 0 ,c = 0 , r = 0; x < main_artifacts4.length; x++, c++) {
                    if(c == 5) { c = 0;r++; }
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice, advice_main_art_ll4, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(main_artifacts4[x]),context)[x+1],context)).fit().centerInside().transform(roundedCornersTransformation)
                            .error(R.drawable.paimon_lost)
                            .into(item_img);
                }
                advice_main_art_ll4.setVisibility(View.VISIBLE);
            }

            if(jsonObjectDps.has("dps_art5")){
                advice_main_art_ll5.removeAllViews();
                for (int x = 0 ,c = 0 , r = 0; x < main_artifacts5.length; x++, c++) {
                    if(c == 5) { c = 0;r++; }
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice, advice_main_art_ll5, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(main_artifacts5[x]),context)[x+1],context)).fit().centerInside().transform(roundedCornersTransformation)
                            .error(R.drawable.paimon_lost)
                            .into(item_img);
                    advice_main_art_ll5.addView(char_view);
                }
                advice_main_art_ll5.setVisibility(View.VISIBLE);
            }

            if(jsonObjectDps.has("sup_dps_art1")){
                advice_support_art_ll1.removeAllViews();
                for (int x = 0 ; x < support_artifacts1.length; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice, advice_support_art_ll1, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(support_artifacts1[x]),context)[x+1],context)).fit().centerInside().transform(roundedCornersTransformation)
                            .error(R.drawable.paimon_lost)
                            .into(item_img);
                    advice_support_art_ll1.addView(char_view);
                }
                advice_support_art_ll1.setVisibility(View.VISIBLE);
            }

            if(jsonObjectDps.has("sup_dps_art2")){
                advice_support_art_ll2.removeAllViews();
                for (int x = 0 ; x < support_artifacts2.length; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice, advice_support_art_ll2, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(support_artifacts2[x]),context)[x+1],context)).fit().centerInside().transform(roundedCornersTransformation)
                            .error(R.drawable.paimon_lost)
                            .into(item_img);
                    advice_support_art_ll2.addView(char_view);
                }
                advice_support_art_ll2.setVisibility(View.VISIBLE);
            }

            if(jsonObjectDps.has("sup_dps_art3")){
                advice_support_art_ll3.removeAllViews();
                for (int x = 0 ; x < support_artifacts3.length; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice, advice_support_art_ll3, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(support_artifacts3[x]),context)[x+1],context)).fit().centerInside().transform(roundedCornersTransformation)
                            .error(R.drawable.paimon_lost)
                            .into(item_img);
                    advice_support_art_ll3.addView(char_view);
                }
                advice_support_art_ll3.setVisibility(View.VISIBLE);
            }
            if(jsonObjectDps.has("sup_dps_art4")){
                advice_support_art_ll4.removeAllViews();
                for (int x = 0 ,c = 0 , r = 0; x < support_artifacts4.length; x++, c++) {
                    if(c == 5) { c = 0;r++; }
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice, advice_support_art_ll4, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(support_artifacts4[x]),context)[x+1],context)).fit().centerInside().transform(roundedCornersTransformation)
                            .error(R.drawable.paimon_lost)
                            .into(item_img);
                    advice_support_art_ll4.addView(char_view);
                }
                advice_support_art_ll4.setVisibility(View.VISIBLE);
            }
            if(jsonObjectDps.has("sup_dps_art5")){
                advice_support_art_ll5.removeAllViews();
                for (int x = 0 ,c = 0 , r = 0; x < support_artifacts5.length; x++, c++) {
                    if(c == 5) { c = 0;r++; }
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice, advice_support_art_ll5, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(support_artifacts5[x]),context)[x+1],context)).fit().centerInside().transform(roundedCornersTransformation)
                            .error(R.drawable.paimon_lost)
                            .into(item_img);
                    advice_support_art_ll5.addView(char_view);
                }
                advice_support_art_ll5.setVisibility(View.VISIBLE);
            }
            if(jsonObjectDps.has("util_art1")){
                advice_util_art_ll1.removeAllViews();
                for (int x = 0 ; x < util_artifacts1.length; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice, advice_util_art_ll1, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(util_artifacts1[x]),context)[x+1],context)).fit().centerInside().transform(roundedCornersTransformation)
                            .error(R.drawable.paimon_lost)
                            .into(item_img);
                    advice_util_art_ll1.addView(char_view);
                }
                advice_util_art_ll1.setVisibility(View.VISIBLE);
            }

            if(jsonObjectDps.has("util_art2")){
                advice_util_art_ll2.removeAllViews();
                for (int x = 0 ; x < util_artifacts2.length; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice, advice_util_art_ll2, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(util_artifacts2[x]),context)[x+1],context)).fit().centerInside().transform(roundedCornersTransformation)
                            .error(R.drawable.paimon_lost)
                            .into(item_img);
                    advice_util_art_ll2.addView(char_view);
                }
                advice_util_art_ll2.setVisibility(View.VISIBLE);
            }

            if(jsonObjectDps.has("util_art3")){
                advice_util_art_ll3.removeAllViews();
                for (int x = 0 ; x < util_artifacts3.length; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice, advice_util_art_ll3, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(util_artifacts3[x]),context)[x+1],context)).fit().centerInside().transform(roundedCornersTransformation)
                            .error(R.drawable.paimon_lost)
                            .into(item_img);
                    advice_util_art_ll3.addView(char_view);
                }
                advice_util_art_ll3.setVisibility(View.VISIBLE);
            }
            if(jsonObjectDps.has("util_art4")){
                advice_util_art_ll4.removeAllViews();
                for (int x = 0 ,c = 0 , r = 0; x < util_artifacts4.length; x++, c++) {
                    if(c == 5) { c = 0;r++; }
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice, advice_util_art_ll4, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(util_artifacts4[x]),context)[x+1],context)).fit().centerInside().transform(roundedCornersTransformation)
                            .error(R.drawable.paimon_lost)
                            .into(item_img);
                    advice_util_art_ll4.addView(char_view);
                }
                advice_util_art_ll4.setVisibility(View.VISIBLE);
            }
            if(jsonObjectDps.has("util_art5")){
                advice_util_art_ll5.removeAllViews();
                for (int x = 0 ,c = 0 , r = 0; x < util_artifacts4.length; x++, c++) {
                    if(c == 5) { c = 0;r++; }
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice, advice_util_art_ll5, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(util_artifacts4[x]),context)[x+1],context)).fit().centerInside().transform(roundedCornersTransformation)
                            .error(R.drawable.paimon_lost)
                            .into(item_img);
                    advice_util_art_ll5.addView(char_view);
                }
                advice_util_art_ll5.setVisibility(View.VISIBLE);
            }

            setAdviceText("dps",info_advice_main_art_info);
            setAdviceText("sup_dps",info_advice_support_art_info);
            setAdviceText("util",info_advice_util_art_info);

            if(jsonObjectDps.has("team1")){
                advice_team_ll1.removeAllViews();
                for (int x = 0 ; x < team1.length; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice_team, advice_team_ll1, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Bitmap icon = FileLoader.loadIMG2Bitmap(item_rss.getCharByName(team1[x].replace("XPR"," "),context)[3],context);
                    item_img.setImageBitmap(getRoundBitmapByShader(icon, (int) Math.round(128),(int)Math.round(128),20, 0));
                    Log.wtf("team1[x])[2]",team1[x]);
                    advice_team_ll1.addView(char_view);
                }
                info_advice_team_card.setVisibility(View.VISIBLE);
                advice_team_ll1.setVisibility(View.VISIBLE);
            }

            if(jsonObjectDps.has("team2")){
                advice_team_ll2.removeAllViews();
                for (int x = 0 ; x < team2.length; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice_team, advice_team_ll2, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Bitmap icon = FileLoader.loadIMG2Bitmap(item_rss.getCharByName(team2[x].replace("XPR"," "),context)[3],context);
                    item_img.setImageBitmap(getRoundBitmapByShader(icon, (int) Math.round(128),(int)Math.round(128),20, 0));
                    Log.wtf("team2[x])[2]",team2[x]);
                    advice_team_ll2.addView(char_view);
                }
                advice_team_ll2.setVisibility(View.VISIBLE);
            }

            if(jsonObjectDps.has("team3")){
                advice_team_ll3.removeAllViews();
                for (int x = 0 ; x < team3.length; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice_team, advice_team_ll3, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Bitmap icon = FileLoader.loadIMG2Bitmap(item_rss.getCharByName(team3[x].replace("XPR"," "),context)[3],context);
                    item_img.setImageBitmap(getRoundBitmapByShader(icon, (int) Math.round(128),(int)Math.round(128),20, 0));
                    advice_team_ll3.addView(char_view);
                }
                advice_team_ll3.setVisibility(View.VISIBLE);
            }

            if(jsonObjectDps.has("team4")){
                advice_team_ll4.removeAllViews();
                for (int x = 0 ; x < team4.length; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice_team, advice_team_ll4, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Bitmap icon = FileLoader.loadIMG2Bitmap(item_rss.getCharByName(team4[x].replace("XPR"," "),context)[3],context);
                    item_img.setImageBitmap(getRoundBitmapByShader(icon, (int) Math.round(128),(int)Math.round(128),20, 0));
                    advice_team_ll4.addView(char_view);
                }
                advice_team_ll4.setVisibility(View.VISIBLE);
            }

            if(jsonObjectDps.has("team5")){
                advice_team_ll5.removeAllViews();
                for (int x = 0 ; x < team4.length; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice_team, advice_team_ll5, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);
                    Bitmap icon = FileLoader.loadIMG2Bitmap(item_rss.getCharByName(team4[x].replace("XPR"," "),context)[3],context);
                    item_img.setImageBitmap(getRoundBitmapByShader(icon, (int) Math.round(128),(int)Math.round(128),20, 0));
                    advice_team_ll5.addView(char_view);
                }
                advice_team_ll5.setVisibility(View.VISIBLE);
            }
        }

        /** Method of dialog */
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        dialog.show();
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

    public void setAdviceText(String part, TextView info){
        String dps_advice = context.getString(R.string.main_entry)+"\n\t";

        if(jsonObjectDps.has(part+"_art_sand_entry")){
            dps_advice = dps_advice + context.getString(R.string.sand) + " : ";
            try {
                JSONArray dps_advice_tmpJ = jsonObjectDps.getJSONArray(part+"_art_sand_entry");
                String[] dps_advice_tmp = dps_advice_tmpJ.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                for (int x = 0 ; x < dps_advice_tmp.length ; x++){
                    dps_advice = dps_advice+item_rss.getArtifactBuffName(dps_advice_tmp[x],context);
                    if(x+1 < dps_advice_tmpJ.length()){
                        dps_advice = dps_advice+"/";
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            info.setText(dps_advice);
            info.setVisibility(View.VISIBLE);
        }


        if(jsonObjectDps.has(part+"_art_goblet_entry")){
            dps_advice = dps_advice+"\n\t";
            dps_advice = dps_advice + context.getString(R.string.goblet) + " : ";
            try {
                JSONArray dps_advice_tmpJ = jsonObjectDps.getJSONArray(part+"_art_goblet_entry");
                String[] dps_advice_tmp = dps_advice_tmpJ.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                for (int x = 0 ; x < dps_advice_tmp.length ; x++){
                    dps_advice = dps_advice+item_rss.getArtifactBuffName(dps_advice_tmp[x],context);
                    if(x+1 < dps_advice_tmpJ.length()){
                        dps_advice = dps_advice+"/";
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            info.setText(dps_advice);
            info.setVisibility(View.VISIBLE);
        }

        if(jsonObjectDps.has(part+"_art_circlet_entry")){
            dps_advice = dps_advice+"\n\t";
            dps_advice = dps_advice + context.getString(R.string.circlet) + " : ";
            try {
                JSONArray dps_advice_tmpJ = jsonObjectDps.getJSONArray(part+"_art_circlet_entry");
                String[] dps_advice_tmp = dps_advice_tmpJ.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                for (int x = 0 ; x < dps_advice_tmp.length ; x++){
                    dps_advice = dps_advice+item_rss.getArtifactBuffName(dps_advice_tmp[x],context);
                    if(x+1 < dps_advice_tmpJ.length()){
                        dps_advice = dps_advice+"/";
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            info.setText(dps_advice);
            info.setVisibility(View.VISIBLE);
        }
        if(jsonObjectDps.has(part+"_art_sub_entry")){
            dps_advice = dps_advice+"\n";
            dps_advice = dps_advice + context.getString(R.string.sub_entry);
            dps_advice = dps_advice+"\n\t";
            try {
                JSONArray dps_advice_tmpJ = jsonObjectDps.getJSONArray(part+"_art_sub_entry");
                String[] dps_advice_tmp = dps_advice_tmpJ.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                for (int x = 0 ; x < dps_advice_tmp.length ; x++){
                    dps_advice = dps_advice+item_rss.getArtifactBuffName(dps_advice_tmp[x],context);
                    if(x+1 < dps_advice_tmpJ.length()){
                        dps_advice = dps_advice+"/";
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            info.setText(dps_advice);
            info.setVisibility(View.VISIBLE);
        }
        System.out.println(dps_advice);
    }

}
