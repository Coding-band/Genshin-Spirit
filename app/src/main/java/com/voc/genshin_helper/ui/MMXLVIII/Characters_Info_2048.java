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

public class Characters_Info_2048 {
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

    // Buff
    double 角色基礎生命值,角色基礎攻擊力,角色基礎防禦力,角色生命值加成,角色攻擊力加成,角色防禦力加成,角色暴擊率,角色暴擊傷害,角色元素充能,角色元素精通,角色治療加成,角色火元素傷害加成,角色水元素傷害加成,角色風元素傷害加成,角色雷元素傷害加成,角色草元素傷害加成,角色冰元素傷害加成,角色岩元素傷害加成,角色物理傷害加成;
    double 普通攻擊_一段傷害,普通攻擊_二段傷害,普通攻擊_三段傷害,普通攻擊_四段傷害,普通攻擊_五段傷害,普通攻擊_六段傷害,普通攻擊_下墜期間傷害,普通攻擊_低空墜地衝擊傷害,普通攻擊_高空墜地衝擊傷害,普通攻擊_瞄準射擊,普通攻擊_滿蓄力瞄準射擊,普通攻擊_重擊傷害,普通攻擊_重擊循環傷害,普通攻擊_重擊終結傷害;

    double 普通攻擊_一段蓄力瞄準射擊;
    double 普通攻擊_霜華矢命中傷害;
    double 普通攻擊_霜華矢_霜華綻發傷害;
    double 普通攻擊_焰硝矢傷害;
    double 普通攻擊_荒瀧逆袈裟連斬傷害;
    double 普通攻擊_荒瀧逆袈裟終結傷害;
    double 普通攻擊_左一文字斬傷害;
    double 普通攻擊_斷流_閃_傷害;
    double 普通攻擊_斷流_破_傷害;

    String[] 元素戰技_baseName ;
    double[][] 元素戰技_value; //[ITEM, VALUE]

    String[] 元素爆發_baseName ;
    double[][] 元素爆發_value; //[ITEM, VALUE]

    private ViewPager viewPager;
    private ArrayList<View> viewPager_List;
    View charDescPage, charSkillPage, charSofPage, charAdvicePage;

    /** [BASIC] CHARACTER LVL EXP + MORA */
    public ArrayList<Integer> lvlEXPList = new ArrayList<Integer>();
    public ArrayList<Integer> expEXPList = new ArrayList<Integer>();
    public ArrayList<Integer> moraEXPList = new ArrayList<Integer>();

    /** [BASIC] CHARACTER ASC LVL + MORA */
    public ArrayList<Integer> lvlASCList = new ArrayList<Integer>();
    public ArrayList<Integer> silverASCList = new ArrayList<Integer>();
    public ArrayList<Integer> fragASCList = new ArrayList<Integer>();
    public ArrayList<Integer> chunkASCList = new ArrayList<Integer>();
    public ArrayList<Integer> gemASCList = new ArrayList<Integer>();
    public ArrayList<Integer> bossASCList = new ArrayList<Integer>();
    public ArrayList<Integer> localASCList = new ArrayList<Integer>();
    public ArrayList<Integer> com1ASCList = new ArrayList<Integer>();
    public ArrayList<Integer> com2ASCList = new ArrayList<Integer>();
    public ArrayList<Integer> com3ASCList = new ArrayList<Integer>();
    public ArrayList<Integer> moraASCList = new ArrayList<Integer>();

    /** [BASIC] CHARACTER SKILL LVL + MORA */
    public ArrayList<Integer> lvlSKILLList = new ArrayList<Integer>();
    public ArrayList<Integer> teachSKILLList = new ArrayList<Integer>();
    public ArrayList<Integer> guideSKILLList = new ArrayList<Integer>();
    public ArrayList<Integer> phiSKILLList = new ArrayList<Integer>();
    public ArrayList<Integer> com1SKILLList = new ArrayList<Integer>();
    public ArrayList<Integer> com2SKILLList = new ArrayList<Integer>();
    public ArrayList<Integer> com3SKILLList = new ArrayList<Integer>();
    public ArrayList<Integer> bossSKILLList = new ArrayList<Integer>();
    public ArrayList<Integer> moraSkillList = new ArrayList<Integer>();

    /** [CHAR] CHARACTER REQUIRE ON ASC AND SKILL */
    public  String crystalREQUIRE = "XPR";
    public  String bossREQUIRE = "XPR";
    public  String localREQUIRE = "XPR";
    public  String commonREQUIRE = "XPR";
    public  String bookREQUIRE = "XPR";
    public  String t_bossREQUIRE = "XPR";

    /** https://stackoverflow.com/questions/45247927/how-to-parse-json-object-inside-json-object-in-java */
    public void JsonToStr (String str , String str_dps){
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
                readCharAscData();
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
        View view = View.inflate(context, R.layout.fragment_char_info_frame_2048, null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        /** Method of header */
        TabLayout info_tablelayout = view.findViewById(R.id.info_tablelayout);
        ImageView info_back_btn = view.findViewById(R.id.info_back_btn);
        ImageView info_header_bg = view.findViewById(R.id.info_header_bg);
        viewPager = (ViewPager) view.findViewById(R.id.vp);

        final LayoutInflater mInflater = activity.getLayoutInflater().from(context);
        charDescPage = mInflater.inflate(R.layout.fragment_char_info_desc_2048, null,false);
        charSkillPage = mInflater.inflate(R.layout.fragment_char_info_skill_2048, null,false);
        charSofPage = mInflater.inflate(R.layout.fragment_char_info_sof_2048, null,false);
        charAdvicePage = mInflater.inflate(R.layout.fragment_char_info_advice_2048, null,false);

        viewPager_List = new ArrayList<View>();
        viewPager_List.add(charDescPage);
        viewPager_List.add(charSkillPage);
        viewPager_List.add(charSofPage);
        viewPager_List.add(charAdvicePage);
        viewPager.setAdapter(new MyViewPagerAdapter(viewPager_List));

        BackgroundReload.BackgroundReload(context,view);

        /** Method of info_detail */
        ConstraintLayout info_char_bg = charDescPage.findViewById(R.id.info_char_bg);
        //ConstraintLayout char_bg = view.findViewById(R.id.info_char_bg);
        ImageView char_img = charDescPage.findViewById(R.id.info_char_img);
        //ImageView char_layer = view.findViewById(R.id.info_char_layer);
        TextView char_name = charDescPage.findViewById(R.id.info_char_name);
        TextView char_title = charDescPage.findViewById(R.id.info_char_title);
        RatingBar char_stars = charDescPage.findViewById(R.id.info_stars);
        ImageView char_element = charDescPage.findViewById(R.id.info_element_img);
        TextView char_area = charDescPage.findViewById(R.id.info_area_tv);
        ImageView char_area_ico = charDescPage.findViewById(R.id.info_area_ico);
        ImageView char_weapon = charDescPage.findViewById(R.id.info_weapon);
        TextView char_role = charDescPage.findViewById(R.id.info_role);
        //TextView char_sex = view.findViewById(R.id.info_sex);
        TextView char_birth = charDescPage.findViewById(R.id.info_date);

        /** Method of introduce */
        //TextView info_intro = view.findViewById(R.id.info_intro);

        /** Method of value btn */
        ImageView info_talent1_value_btn = charSkillPage.findViewById(R.id.info_talent1_value_btn);
        ImageView info_talent2_value_btn = charSkillPage.findViewById(R.id.info_talent2_value_btn);
        ImageView info_talent3_value_btn = charSkillPage.findViewById(R.id.info_talent3_value_btn);
        ImageView info_char_base_value_btn = charDescPage.findViewById(R.id.info_char_base_value_btn);

        /** Method of battle_talent */
        CardView char_talent1_card = charSkillPage.findViewById(R.id.info_talent1_card);
        ImageView char_talent1_ico = charSkillPage.findViewById(R.id.info_talent1_ico);
        TextView char_talent1_name = charSkillPage.findViewById(R.id.info_talent1_name);
        TextView char_talent1_normal = charSkillPage.findViewById(R.id.info_talent1_normal);
        TextView char_talent1_hard = charSkillPage.findViewById(R.id.info_talent1_hard);
        TextView char_talent1_drop = charSkillPage.findViewById(R.id.info_talent1_drop);

        CardView char_talent2_card = charSkillPage.findViewById(R.id.info_talent2_card);
        ImageView char_talent2_ico = charSkillPage.findViewById(R.id.info_talent2_ico);
        TextView char_talent2_name = charSkillPage.findViewById(R.id.info_talent2_name);
        TextView char_talent2_normal = charSkillPage.findViewById(R.id.info_talent2_normal);

        CardView char_talent3_card = charSkillPage.findViewById(R.id.info_talent3_card);
        ImageView char_talent3_ico = charSkillPage.findViewById(R.id.info_talent3_ico);
        TextView char_talent3_name = charSkillPage.findViewById(R.id.info_talent3_name);
        TextView char_talent3_normal = charSkillPage.findViewById(R.id.info_talent3_normal);

        CardView char_talent4_card = charSkillPage.findViewById(R.id.info_talent4_card);
        ImageView char_talent4_ico = charSkillPage.findViewById(R.id.info_talent4_ico);
        TextView char_talent4_name = charSkillPage.findViewById(R.id.info_talent4_name);
        TextView char_talent4_normal = charSkillPage.findViewById(R.id.info_talent4_normal);

        /** Method of basic_talent */
        CardView char_basic_talent1_card = charSkillPage.findViewById(R.id.info_btalent1_card);
        ImageView char_basic_talent1_ico = charSkillPage.findViewById(R.id.info_btalent1_ico);
        TextView char_basic_talent1_name = charSkillPage.findViewById(R.id.info_btalent1_name);
        TextView char_basic_talent1_normal = charSkillPage.findViewById(R.id.info_btalent1_normal);

        CardView char_basic_talent2_card = charSkillPage.findViewById(R.id.info_btalent2_card);
        ImageView char_basic_talent2_ico = charSkillPage.findViewById(R.id.info_btalent2_ico);
        TextView char_basic_talent2_name = charSkillPage.findViewById(R.id.info_btalent2_name);
        TextView char_basic_talent2_normal = charSkillPage.findViewById(R.id.info_btalent2_normal);

        CardView char_basic_talent3_card = charSkillPage.findViewById(R.id.info_btalent3_card);
        ImageView char_basic_talent3_ico = charSkillPage.findViewById(R.id.info_btalent3_ico);
        TextView char_basic_talent3_name = charSkillPage.findViewById(R.id.info_btalent3_name);
        TextView char_basic_talent3_normal = charSkillPage.findViewById(R.id.info_btalent3_normal);

        /** Method of sof */
        CardView char_sof1_card = charSofPage.findViewById(R.id.info_sof1_card);
        ImageView char_sof1_ico = charSofPage.findViewById(R.id.info_sof1_ico);
        TextView char_sof1_name = charSofPage.findViewById(R.id.info_sof1_name);
        TextView char_sof1_normal = charSofPage.findViewById(R.id.info_sof1_normal);

        CardView char_sof2_card = charSofPage.findViewById(R.id.info_sof2_card);
        ImageView char_sof2_ico = charSofPage.findViewById(R.id.info_sof2_ico);
        TextView char_sof2_name = charSofPage.findViewById(R.id.info_sof2_name);
        TextView char_sof2_normal = charSofPage.findViewById(R.id.info_sof2_normal);

        CardView char_sof3_card = charSofPage.findViewById(R.id.info_sof3_card);
        ImageView char_sof3_ico = charSofPage.findViewById(R.id.info_sof3_ico);
        TextView char_sof3_name = charSofPage.findViewById(R.id.info_sof3_name);
        TextView char_sof3_normal = charSofPage.findViewById(R.id.info_sof3_normal);

        CardView char_sof4_card = charSofPage.findViewById(R.id.info_sof4_card);
        ImageView char_sof4_ico = charSofPage.findViewById(R.id.info_sof4_ico);
        TextView char_sof4_name = charSofPage.findViewById(R.id.info_sof4_name);
        TextView char_sof4_normal = charSofPage.findViewById(R.id.info_sof4_normal);

        CardView char_sof5_card = charSofPage.findViewById(R.id.info_sof5_card);
        ImageView char_sof5_ico = charSofPage.findViewById(R.id.info_sof5_ico);
        TextView char_sof5_name = charSofPage.findViewById(R.id.info_sof5_name);
        TextView char_sof5_normal = charSofPage.findViewById(R.id.info_sof5_normal);

        CardView char_sof6_card = charSofPage.findViewById(R.id.info_sof6_card);
        ImageView char_sof6_ico = charSofPage.findViewById(R.id.info_sof6_ico);
        TextView char_sof6_name = charSofPage.findViewById(R.id.info_sof6_name);
        TextView char_sof6_normal = charSofPage.findViewById(R.id.info_sof6_normal);

        /** Method of advice */
        //GridLayout advice_main_weapon_ll = view.findViewById(R.id.advice_main_weapon_ll);
        LinearLayout advice_main_weapon_sc = charAdvicePage.findViewById(R.id.advice_main_weapon_sc);
        LinearLayout advice_main_art_ll1 = charAdvicePage.findViewById(R.id.advice_main_art_ll1);
        LinearLayout advice_main_art_ll2 = charAdvicePage.findViewById(R.id.advice_main_art_ll2);
        LinearLayout advice_main_art_ll3 = charAdvicePage.findViewById(R.id.advice_main_art_ll3);
        LinearLayout advice_main_art_ll4 = charAdvicePage.findViewById(R.id.advice_main_art_ll4);
        LinearLayout advice_main_art_ll5 = charAdvicePage.findViewById(R.id.advice_main_art_ll5);

        //GridLayout advice_support_weapon_ll = view.findViewById(R.id.advice_support_weapon_ll);
        LinearLayout advice_support_weapon_sc = charAdvicePage.findViewById(R.id.advice_support_weapon_sc);
        LinearLayout advice_support_art_ll1 = charAdvicePage.findViewById(R.id.advice_support_art_ll1);
        LinearLayout advice_support_art_ll2 = charAdvicePage.findViewById(R.id.advice_support_art_ll2);
        LinearLayout advice_support_art_ll3 = charAdvicePage.findViewById(R.id.advice_support_art_ll3);
        LinearLayout advice_support_art_ll4 = charAdvicePage.findViewById(R.id.advice_support_art_ll4);
        LinearLayout advice_support_art_ll5 = charAdvicePage.findViewById(R.id.advice_support_art_ll5);

        //GridLayout advice_util_weapon_ll = view.findViewById(R.id.advice_util_weapon_ll);
        LinearLayout advice_util_weapon_sc = charAdvicePage.findViewById(R.id.advice_util_weapon_sc);
        LinearLayout advice_util_art_ll1 = charAdvicePage.findViewById(R.id.advice_util_art_ll1);
        LinearLayout advice_util_art_ll2 = charAdvicePage.findViewById(R.id.advice_util_art_ll2);
        LinearLayout advice_util_art_ll3 = charAdvicePage.findViewById(R.id.advice_util_art_ll3);
        LinearLayout advice_util_art_ll4 = charAdvicePage.findViewById(R.id.advice_util_art_ll4);
        LinearLayout advice_util_art_ll5 = charAdvicePage.findViewById(R.id.advice_util_art_ll5);

        LinearLayout info_main_ll = charAdvicePage.findViewById(R.id.info_main_ll);
        TextView info_main_sand_tv = charAdvicePage.findViewById(R.id.info_main_sand_tv);
        TextView info_main_goblet_tv = charAdvicePage.findViewById(R.id.info_main_goblet_tv);
        TextView info_main_circlet_tv = charAdvicePage.findViewById(R.id.info_main_circlet_tv);
        TextView info_main_sub_tv = charAdvicePage.findViewById(R.id.info_main_sub_tv);

        LinearLayout info_support_ll = charAdvicePage.findViewById(R.id.info_support_ll);
        TextView info_support_sand_tv = charAdvicePage.findViewById(R.id.info_support_sand_tv);
        TextView info_support_goblet_tv = charAdvicePage.findViewById(R.id.info_support_goblet_tv);
        TextView info_support_circlet_tv = charAdvicePage.findViewById(R.id.info_support_circlet_tv);
        TextView info_support_sub_tv = charAdvicePage.findViewById(R.id.info_support_sub_tv);

        LinearLayout info_util_ll = charAdvicePage.findViewById(R.id.info_util_ll);
        TextView info_util_sand_tv = charAdvicePage.findViewById(R.id.info_util_sand_tv);
        TextView info_util_goblet_tv = charAdvicePage.findViewById(R.id.info_util_goblet_tv);
        TextView info_util_circlet_tv = charAdvicePage.findViewById(R.id.info_util_circlet_tv);
        TextView info_util_sub_tv = charAdvicePage.findViewById(R.id.info_util_sub_tv);

        CardView info_advice_main_card = charAdvicePage.findViewById(R.id.info_advice_main_card);
        CardView info_advice_support_card = charAdvicePage.findViewById(R.id.info_advice_support_card);
        CardView info_advice_util_card = charAdvicePage.findViewById(R.id.info_advice_util_card);

        LinearLayout advice_team_ll1 = charAdvicePage.findViewById(R.id.advice_team1);
        LinearLayout advice_team_ll2 = charAdvicePage.findViewById(R.id.advice_team2);
        LinearLayout advice_team_ll3 = charAdvicePage.findViewById(R.id.advice_team3);
        LinearLayout advice_team_ll4 = charAdvicePage.findViewById(R.id.advice_team4);
        LinearLayout advice_team_ll5 = charAdvicePage.findViewById(R.id.advice_team5);

        //TextView info_advice_main_art_info = view.findViewById(R.id.info_advice_main_art_info);
        //TextView info_advice_support_art_info = view.findViewById(R.id.info_advice_support_art_info);
        //TextView info_advice_util_art_info = view.findViewById(R.id.info_advice_util_art_info);

        TextView info_advice_main = charAdvicePage.findViewById(R.id.info_advice_main);
        TextView info_advice_support = charAdvicePage.findViewById(R.id.info_advice_support);
        TextView info_advice_util = charAdvicePage.findViewById(R.id.info_advice_util);
        TextView info_advice_team = charAdvicePage.findViewById(R.id.info_advice_team_tv);
        TextView info_advice_result = charAdvicePage.findViewById(R.id.info_advice_result);

        LinearLayout base_lvl_ll = charAdvicePage.findViewById(R.id.base_lvl_ll);
        LinearLayout talent_lvl_ll = charAdvicePage.findViewById(R.id.talent_lvl_ll);

        readCharMaterialByBuff(base_lvl_ll);
        readSkillMaterialByBuff(talent_lvl_ll);

        info_advice_main.setVisibility(View.GONE);
        info_advice_support.setVisibility(View.GONE);
        info_advice_util.setVisibility(View.GONE);
        info_advice_team.setVisibility(View.GONE);

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
        int[] tabItemImageArray = new int[]{R.drawable.ic_2048_char_intro_btn,R.drawable.ic_2048_talent_btn,R.drawable.ic_2048_constelations_btn,R.drawable.ic_2048_advice_btn};
        int[] tabItemImageSelectedArray = new int[]{R.drawable.ic_2048_char_intro_btn_selected,R.drawable.ic_2048_talent_btn_selected,R.drawable.ic_2048_constelations_btn_selected,R.drawable.ic_2048_advice_btn_selected};

        for (int x = 0 ; x < 4 ; x++){
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

        info_talent1_value_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.fragment_char_skill_dmg_2048, null);

                String[] lvList = new String[]{"LV1","LV2","LV3","LV4","LV5","LV6","LV7","LV8","LV9","LV10","LV11","LV12","LV13","LV14","LV15"};

                view.setElevation(8);
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
                        if (readCharNormalATKDataFromBuff(name,position)){
                            TextView[] item_title_tv = new TextView[]{menu_part_title_tv1,menu_part_title_tv2,menu_part_title_tv3,menu_part_title_tv4,menu_part_title_tv5,menu_part_title_tv6,menu_part_title_tv7,menu_part_title_tv8,menu_part_title_tv9,menu_part_title_tv10,menu_part_title_tv11,menu_part_title_tv12,menu_part_title_tv13,menu_part_title_tv14,menu_part_title_tv15,menu_part_title_tv16};
                            TextView[] item_value_tv = new TextView[]{menu_part_value_tv1,menu_part_value_tv2,menu_part_value_tv3,menu_part_value_tv4,menu_part_value_tv5,menu_part_value_tv6,menu_part_value_tv7,menu_part_value_tv8,menu_part_value_tv9,menu_part_value_tv10,menu_part_value_tv11,menu_part_value_tv12,menu_part_value_tv13,menu_part_value_tv14,menu_part_value_tv15,menu_part_value_tv16};
                            double[] item_name_value = new double[]{普通攻擊_一段傷害,普通攻擊_二段傷害,普通攻擊_三段傷害,普通攻擊_四段傷害,普通攻擊_五段傷害,普通攻擊_六段傷害,普通攻擊_瞄準射擊,普通攻擊_滿蓄力瞄準射擊,普通攻擊_重擊傷害,普通攻擊_重擊循環傷害,普通攻擊_重擊終結傷害,普通攻擊_下墜期間傷害,普通攻擊_低空墜地衝擊傷害,普通攻擊_高空墜地衝擊傷害,普通攻擊_一段蓄力瞄準射擊,普通攻擊_霜華矢命中傷害,普通攻擊_霜華矢_霜華綻發傷害,普通攻擊_焰硝矢傷害,普通攻擊_荒瀧逆袈裟連斬傷害,普通攻擊_荒瀧逆袈裟終結傷害,普通攻擊_左一文字斬傷害,普通攻擊_斷流_閃_傷害,普通攻擊_斷流_破_傷害};
                            String[] item_name = new String[]{context.getString(R.string.buff_normal_1_hit),context.getString(R.string.buff_normal_2_hit),context.getString(R.string.buff_normal_3_hit),context.getString(R.string.buff_normal_4_hit),context.getString(R.string.buff_normal_5_hit),context.getString(R.string.buff_normal_6_hit),context.getString(R.string.buff_normal_shoot),context.getString(R.string.buff_charged_shoot),context.getString(R.string.buff_charged_dmg),context.getString(R.string.buff_charged_spinning_dmg),context.getString(R.string.buff_charged_final_dmg),context.getString(R.string.buff_plunge_dmg),context.getString(R.string.buff_low_plunge_dmg),context.getString(R.string.buff_high_plunge_dmg),"一段蓄力瞄準射擊","霜華矢命中傷害","霜華矢·霜華綻發傷害","焰硝矢傷害","荒瀧逆袈裟連斬傷害","荒瀧逆袈裟終結傷害","左一文字斬傷害","斷流·閃·傷害","斷流·破·傷害"};

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
                                    if (isType0(item_name_value[x])){
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

                lp.width = (int) (width);
                lp.height = WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;
                dialogWindow.setAttributes(lp);
                dialog.show();
            }
        });

        info_talent2_value_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.fragment_char_skill_dmg_2048, null);

                String[] lvList = new String[]{"LV1","LV2","LV3","LV4","LV5","LV6","LV7","LV8","LV9","LV10","LV11","LV12","LV13","LV14","LV15"};

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
                        if(readCharESkillDataFromBuff(name,position)){
                            TextView[] item_title_tv = new TextView[]{menu_part_title_tv1,menu_part_title_tv2,menu_part_title_tv3,menu_part_title_tv4,menu_part_title_tv5,menu_part_title_tv6,menu_part_title_tv7,menu_part_title_tv8,menu_part_title_tv9,menu_part_title_tv10,menu_part_title_tv11,menu_part_title_tv12,menu_part_title_tv13,menu_part_title_tv14,menu_part_title_tv15,menu_part_title_tv16};
                            TextView[] item_value_tv = new TextView[]{menu_part_value_tv1,menu_part_value_tv2,menu_part_value_tv3,menu_part_value_tv4,menu_part_value_tv5,menu_part_value_tv6,menu_part_value_tv7,menu_part_value_tv8,menu_part_value_tv9,menu_part_value_tv10,menu_part_value_tv11,menu_part_value_tv12,menu_part_value_tv13,menu_part_value_tv14,menu_part_value_tv15,menu_part_value_tv16};

                            for (int x = 0 ; x < item_title_tv.length ; x++){
                                item_title_tv[x].setVisibility(View.GONE);
                                item_value_tv[x].setVisibility(View.GONE);
                            }
                            int real_pos_cnt = 0;
                            for (int x = 0 ; x < 元素戰技_baseName.length && real_pos_cnt < 15 ; x++){
                                if (元素戰技_value[x][position] != 0d){
                                    item_title_tv[real_pos_cnt].setVisibility(View.VISIBLE);
                                    item_value_tv[real_pos_cnt].setVisibility(View.VISIBLE);
                                    item_title_tv[real_pos_cnt].setText(元素戰技_baseName[x]);
                                    if(isType0(元素戰技_value[x][position])){
                                        item_value_tv[real_pos_cnt].setText(prettyCount(元素戰技_value[x][position],0));
                                    }else{
                                        item_value_tv[real_pos_cnt].setText(prettyCount(元素戰技_value[x][position],1));
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

        info_talent3_value_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.fragment_char_skill_dmg_2048, null);

                String[] lvList = new String[]{"LV1","LV2","LV3","LV4","LV5","LV6","LV7","LV8","LV9","LV10","LV11","LV12","LV13","LV14","LV15"};

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
                        if(readCharQSkillDataFromBuff(name,position)){
                            TextView[] item_title_tv = new TextView[]{menu_part_title_tv1,menu_part_title_tv2,menu_part_title_tv3,menu_part_title_tv4,menu_part_title_tv5,menu_part_title_tv6,menu_part_title_tv7,menu_part_title_tv8,menu_part_title_tv9,menu_part_title_tv10,menu_part_title_tv11,menu_part_title_tv12,menu_part_title_tv13,menu_part_title_tv14,menu_part_title_tv15,menu_part_title_tv16};
                            TextView[] item_value_tv = new TextView[]{menu_part_value_tv1,menu_part_value_tv2,menu_part_value_tv3,menu_part_value_tv4,menu_part_value_tv5,menu_part_value_tv6,menu_part_value_tv7,menu_part_value_tv8,menu_part_value_tv9,menu_part_value_tv10,menu_part_value_tv11,menu_part_value_tv12,menu_part_value_tv13,menu_part_value_tv14,menu_part_value_tv15,menu_part_value_tv16};

                            for (int x = 0 ; x < item_title_tv.length ; x++){
                                item_title_tv[x].setVisibility(View.GONE);
                                item_value_tv[x].setVisibility(View.GONE);
                            }
                            int real_pos_cnt = 0;
                            for (int x = 0 ; x < 元素爆發_baseName.length && real_pos_cnt < 15 ; x++){
                                if (元素爆發_value[x][position] != 0d){
                                    item_title_tv[real_pos_cnt].setVisibility(View.VISIBLE);
                                    item_value_tv[real_pos_cnt].setVisibility(View.VISIBLE);
                                    item_title_tv[real_pos_cnt].setText(元素爆發_baseName[x]);
                                    if(isType0(元素爆發_value[x][position])){
                                        item_value_tv[real_pos_cnt].setText(prettyCount(元素爆發_value[x][position],0));
                                    }else{
                                        item_value_tv[real_pos_cnt].setText(prettyCount(元素爆發_value[x][position],1));
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

        info_char_base_value_btn.setOnClickListener(new View.OnClickListener() {
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
                        if(readCharBaseDataFromBuff(name,position)){
                            TextView[] item_title_tv = new TextView[]{menu_part_title_tv1,menu_part_title_tv2,menu_part_title_tv3,menu_part_title_tv4,menu_part_title_tv5,menu_part_title_tv6,menu_part_title_tv7,menu_part_title_tv8,menu_part_title_tv9,menu_part_title_tv10,menu_part_title_tv11,menu_part_title_tv12,menu_part_title_tv13,menu_part_title_tv14,menu_part_title_tv15,menu_part_title_tv16};
                            TextView[] item_value_tv = new TextView[]{menu_part_value_tv1,menu_part_value_tv2,menu_part_value_tv3,menu_part_value_tv4,menu_part_value_tv5,menu_part_value_tv6,menu_part_value_tv7,menu_part_value_tv8,menu_part_value_tv9,menu_part_value_tv10,menu_part_value_tv11,menu_part_value_tv12,menu_part_value_tv13,menu_part_value_tv14,menu_part_value_tv15,menu_part_value_tv16};
                            double[] item_name_value = new double[]{角色基礎生命值,角色基礎攻擊力,角色基礎防禦力,角色生命值加成,角色攻擊力加成,角色防禦力加成,角色暴擊率,角色暴擊傷害,角色元素充能,角色元素精通,角色治療加成,角色火元素傷害加成,角色水元素傷害加成,角色風元素傷害加成,角色雷元素傷害加成,角色草元素傷害加成,角色冰元素傷害加成,角色岩元素傷害加成,角色物理傷害加成};
                            String[] item_name = new String[]{context.getString(R.string.weapon_stat_HP),context.getString(R.string.weapon_stat_atk),context.getString(R.string.weapon_stat_DEF),context.getString(R.string.weapon_stat_HPP),context.getString(R.string.weapon_stat_atkP),context.getString(R.string.weapon_stat_DEFP),context.getString(R.string.weapon_stat_CritRateP),context.getString(R.string.weapon_stat_EnRechP),context.getString(R.string.weapon_stat_EleMas),context.getString(R.string.weapon_stat_HealingP),context.getString(R.string.weapon_stat_EleDMGP_Pyro),context.getString(R.string.weapon_stat_EleDMGP_Hydro),context.getString(R.string.weapon_stat_EleDMGP_Anemo),context.getString(R.string.weapon_stat_EleDMGP_Electro),context.getString(R.string.weapon_stat_EleDMGP_Dendor),context.getString(R.string.weapon_stat_EleDMGP_Cryo),context.getString(R.string.weapon_stat_EleDMGP_Geo),context.getString(R.string.weapon_stat_PhyDMGP)};

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
        char_name.setText(item_rss.getCharByName(name,context)[1]);
        char_title.setText(nick);
        //Picasso.get().load(FileLoader.loadIMG(item_rss.getCharByName(name,context)[0],context)).centerCrop().into(char_img);
        char_img.setImageDrawable(FileLoader.loadIMG2Drawable(item_rss.getCharByName(name,context)[0],context));

        displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height_curr = displayMetrics.heightPixels;
        int width_curr = displayMetrics.widthPixels;

        //char_img.setBackgroundResource(item_rss.getElementByName(element)[2]);

        boolean isNight = false;
        // Background of item
        if (context.getResources().getString(R.string.mode).equals("Night")) {
            isNight = true;
        }

        char_stars.setNumStars(star);
        char_stars.setRating(star);
        char_element.setImageResource(item_rss.getElementByName(element)[0]);
        char_area.setText(item_rss.getLocaleName(area,context));
        char_area_ico.setImageResource(item_rss.getDistrictIMG(area));
        char_weapon.setImageResource(item_rss.getWeaponTypeIMG(weapon));
        char_role.setText(item_rss.getLocaleName(role,context));
        //char_sex.setText(item_rss.getLocaleName(sex,context));
        char_birth.setText(item_rss.getLocaleBirth(birth,context)); // -> If necessary will change to Month | Day -> E.g. July 24th
        //info_intro.setText(desc);

        ForegroundColorSpan mSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.pyro));
        String mWord = context.getString(R.string.element_Pyro);
        switch (element) {
            case "Anemo":
                mSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.anemo));mWord = context.getString(R.string.element_Anemo);break;
            case "Cryo":
                mSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.cryo));mWord = context.getString(R.string.element_Cryo);break;
            case "Dendor":
                mSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.dendor));mWord = context.getString(R.string.element_Dendor);break;
            case "Electro":
                mSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.electro));mWord = context.getString(R.string.element_Electro);break;
            case "Geo":
                mSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.geo));mWord = context.getString(R.string.element_Geo);break;
            case "Hydro":
                mSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.hydro));mWord = context.getString(R.string.element_Hydro);break;
            case "Pyro":
                mSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.pyro));mWord = context.getString(R.string.element_Pyro);break;
        }

        /** BATTLE_TALENT */
        char_talent1_ico.setImageDrawable(item_rss.getTalentIcoByName(normal_img,context));
        char_talent1_name.setText(talent1_name);


        char_talent1_normal.setText(setSpanAndTv(mSpan,normal_desc1,mWord), TextView.BufferType.SPANNABLE);
        char_talent1_hard.setText(setSpanAndTv(mSpan,normal_desc2,mWord), TextView.BufferType.SPANNABLE);
        char_talent1_drop.setText(setSpanAndTv(mSpan,normal_desc3,mWord), TextView.BufferType.SPANNABLE);

        char_talent2_ico.setImageDrawable(item_rss.getTalentIcoByName(element_img,context));
        char_talent2_name.setText(element_name);
        char_talent2_normal.setText(setSpanAndTv(mSpan, String.valueOf(Html.fromHtml(element_desc)),mWord), TextView.BufferType.SPANNABLE);

        char_talent3_ico.setImageDrawable(item_rss.getTalentIcoByName(final_img,context));
        char_talent3_name.setText(final_name);
        char_talent3_normal.setText(setSpanAndTv(mSpan, String.valueOf(Html.fromHtml(final_desc)),mWord), TextView.BufferType.SPANNABLE);

        if(other_name != "XPR"){
            char_talent4_card.setVisibility(View.VISIBLE);
            char_talent4_ico.setImageDrawable(item_rss.getTalentIcoByName(other_img,context));
            char_talent4_name.setText(other_name);
            char_talent4_normal.setText(setSpanAndTv(mSpan,String.valueOf(Html.fromHtml(other_desc)),mWord), TextView.BufferType.SPANNABLE);
        }

        /** BASIC_TALENT */
        char_basic_talent1_ico.setImageDrawable(item_rss.getTalentIcoByName(talent1_img,context));
        char_basic_talent1_name.setText(talent1_name);
        char_basic_talent1_normal.setText(setSpanAndTv(mSpan,talent1_desc,mWord), TextView.BufferType.SPANNABLE);

        char_basic_talent2_ico.setImageDrawable(item_rss.getTalentIcoByName(talent2_img,context));
        char_basic_talent2_name.setText(talent2_name);
        char_basic_talent2_normal.setText(setSpanAndTv(mSpan,talent2_desc,mWord), TextView.BufferType.SPANNABLE);

        if(!talent3_name.isEmpty() && !talent3_name.equals("XPR")){
            char_basic_talent3_card.setVisibility(View.VISIBLE);
            char_basic_talent3_ico.setImageDrawable(item_rss.getTalentIcoByName(talent3_img,context));
            char_basic_talent3_name.setText(talent3_name);
            char_basic_talent3_normal.setText(setSpanAndTv(mSpan,talent3_desc,mWord), TextView.BufferType.SPANNABLE);
        }

        /** Soul of Life*/
        char_sof1_ico.setImageDrawable(item_rss.getTalentIcoByName(sof1_img,context));
        char_sof1_name.setText(sof1_name);
        char_sof1_normal.setText(setSpanAndTv(mSpan,sof1_desc,mWord), TextView.BufferType.SPANNABLE);

        char_sof2_ico.setImageDrawable(item_rss.getTalentIcoByName(sof2_img,context));
        char_sof2_name.setText(sof2_name);
        char_sof2_normal.setText(setSpanAndTv(mSpan,sof2_desc,mWord), TextView.BufferType.SPANNABLE);

        char_sof3_ico.setImageDrawable(item_rss.getTalentIcoByName(sof3_img,context));
        char_sof3_name.setText(sof3_name);
        char_sof3_normal.setText(setSpanAndTv(mSpan,sof3_desc,mWord), TextView.BufferType.SPANNABLE);

        char_sof4_ico.setImageDrawable(item_rss.getTalentIcoByName(sof4_img,context));
        char_sof4_name.setText(sof4_name);
        char_sof4_normal.setText(setSpanAndTv(mSpan,sof4_desc,mWord), TextView.BufferType.SPANNABLE);

        char_sof5_ico.setImageDrawable(item_rss.getTalentIcoByName(sof5_img,context));
        char_sof5_name.setText(sof5_name);
        char_sof5_normal.setText(setSpanAndTv(mSpan,sof5_desc,mWord), TextView.BufferType.SPANNABLE);

        char_sof6_ico.setImageDrawable(item_rss.getTalentIcoByName(sof6_img,context));
        char_sof6_name.setText(sof6_name);
        char_sof6_normal.setText(setSpanAndTv(mSpan,sof6_desc,mWord), TextView.BufferType.SPANNABLE);

        info_advice_main_card.setVisibility(View.GONE);
        info_advice_support_card.setVisibility(View.GONE);
        info_advice_util_card.setVisibility(View.GONE);

        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(25, 0, RoundedCornersTransformation.CornerType.ALL);

        if(jsonObjectDps != null){
            /** ADVICE */
            if(jsonObjectDps.has("dps_weapons")){
                info_advice_main.setVisibility(View.VISIBLE);
                setWeaponIntoScroll(main_weapon_advice,advice_main_weapon_sc,info_advice_main_card);
            }
            if(jsonObjectDps.has("sup_dps_weapons")){
                info_advice_support.setVisibility(View.VISIBLE);
                setWeaponIntoScroll(support_weapon_advice,advice_support_weapon_sc,info_advice_main_card);
            }
            if(jsonObjectDps.has("util_weapons")){
                info_advice_util.setVisibility(View.VISIBLE);
                setWeaponIntoScroll(util_weapon_advice,advice_util_weapon_sc,info_advice_util_card);
            }

            if(jsonObjectDps.has("dps_art1")){
                info_advice_main.setVisibility(View.VISIBLE);
                setArtifactIntoScroll(main_artifacts1,advice_main_art_ll1);
            }

            if(jsonObjectDps.has("dps_art2")){
                info_advice_main.setVisibility(View.VISIBLE);
                setArtifactIntoScroll(main_artifacts2,advice_main_art_ll2);
            }

            if(jsonObjectDps.has("dps_art3")){
                info_advice_main.setVisibility(View.VISIBLE);
                setArtifactIntoScroll(main_artifacts3,advice_main_art_ll3);
            }

            if(jsonObjectDps.has("dps_art4")){
                info_advice_main.setVisibility(View.VISIBLE);
                setArtifactIntoScroll(main_artifacts4,advice_main_art_ll4);
            }

            if(jsonObjectDps.has("dps_art5")){
                info_advice_main.setVisibility(View.VISIBLE);
                setArtifactIntoScroll(main_artifacts5,advice_main_art_ll5);
            }

            if(jsonObjectDps.has("sup_dps_art1")){
                info_advice_support.setVisibility(View.VISIBLE);
                setArtifactIntoScroll(support_artifacts1,advice_support_art_ll1);
            }

            if(jsonObjectDps.has("sup_dps_art2")){
                info_advice_support.setVisibility(View.VISIBLE);
                setArtifactIntoScroll(support_artifacts2,advice_support_art_ll2);
            }

            if(jsonObjectDps.has("sup_dps_art3")){
                info_advice_support.setVisibility(View.VISIBLE);
                setArtifactIntoScroll(support_artifacts3,advice_support_art_ll3);
            }
            if(jsonObjectDps.has("sup_dps_art4")){
                info_advice_support.setVisibility(View.VISIBLE);
                setArtifactIntoScroll(support_artifacts4,advice_support_art_ll4);
            }
            if(jsonObjectDps.has("sup_dps_art5")){
                info_advice_support.setVisibility(View.VISIBLE);
                setArtifactIntoScroll(support_artifacts5,advice_support_art_ll5);
            }
            if(jsonObjectDps.has("util_art1")){
                info_advice_util.setVisibility(View.VISIBLE);
                setArtifactIntoScroll(util_artifacts1,advice_util_art_ll1);

            }

            if(jsonObjectDps.has("util_art2")){
                info_advice_util.setVisibility(View.VISIBLE);
                setArtifactIntoScroll(util_artifacts2,advice_util_art_ll2);
            }

            if(jsonObjectDps.has("util_art3")){
                info_advice_util.setVisibility(View.VISIBLE);
                setArtifactIntoScroll(util_artifacts3,advice_util_art_ll3);
            }
            if(jsonObjectDps.has("util_art4")){
                info_advice_util.setVisibility(View.VISIBLE);
                setArtifactIntoScroll(util_artifacts4,advice_util_art_ll4);
            }
            if(jsonObjectDps.has("util_art5")){
                info_advice_util.setVisibility(View.VISIBLE);
                setArtifactIntoScroll(util_artifacts5,advice_util_art_ll5);
            }

            setAdviceText("dps",info_main_ll,info_main_sand_tv,info_main_goblet_tv,info_main_circlet_tv,info_main_sub_tv);
            setAdviceText("sup_dps", info_support_ll, info_support_sand_tv, info_support_goblet_tv, info_support_circlet_tv, info_support_sub_tv);
            setAdviceText("util", info_util_ll, info_util_sand_tv, info_util_goblet_tv, info_util_circlet_tv, info_util_sub_tv);

            if(jsonObjectDps.has("team1")){
                info_advice_team.setVisibility(View.VISIBLE);
                advice_team_ll1.removeAllViews();
                for (int x = 0 ; x < team1.length; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice_team_2048, advice_team_ll1, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);


                    final int radius_circ = 360;
                    final int margin_circ = 0;
                    final Transformation transformation_circ = new RoundedCornersTransformation(radius_circ, margin_circ);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getCharByName(team1[x].replace("XPR"," "),context)[3],context))
                            .transform(transformation_circ)
                            .error(R.drawable.paimon_lost)
                            .resize((width_curr-32-16)/4-16,(width_curr-32-16)/4-16)
                            .into(item_img);

                    item_img.getLayoutParams().width=WRAP_CONTENT;
                    item_img.getLayoutParams().height=WRAP_CONTENT;

                    item_img.setAdjustViewBounds(true);
                    String json_base = LoadData("db/char/char_list.json");
                    String name ;
                    int rare;
                    try {
                        JSONArray array = new JSONArray(json_base);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            name = object.getString("name");
                            rare = object.getInt("rare");

                            if (name.equals(team1[x].replace("XPR"," "))){
                                if (rare == 4){
                                    item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);
                                }else if (rare == 5){
                                    item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    advice_team_ll1.addView(char_view);
                }
                advice_team_ll1.setVisibility(View.VISIBLE);
            }

            if(jsonObjectDps.has("team2")){
                info_advice_team.setVisibility(View.VISIBLE);
                advice_team_ll2.removeAllViews();
                for (int x = 0 ; x < team2.length; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice_team_2048, advice_team_ll2, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);


                    final int radius_circ = 360;
                    final int margin_circ = 0;
                    final Transformation transformation_circ = new RoundedCornersTransformation(radius_circ, margin_circ);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getCharByName(team2[x].replace("XPR"," "),context)[3],context))
                            .transform(transformation_circ)
                            .error(R.drawable.paimon_lost)
                            .resize((width_curr-32-16)/4-16,(width_curr-32-16)/4-16)
                            .into(item_img);

                    item_img.getLayoutParams().width=WRAP_CONTENT;
                    item_img.getLayoutParams().height=WRAP_CONTENT;

                    item_img.setAdjustViewBounds(true);
                    String json_base = LoadData("db/char/char_list.json");
                    String name ;
                    int rare;
                    try {
                        JSONArray array = new JSONArray(json_base);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            name = object.getString("name");
                            rare = object.getInt("rare");

                            if (name.equals(team2[x].replace("XPR"," "))){
                                if (rare == 4){
                                    item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);
                                }else if (rare == 5){
                                    item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    advice_team_ll2.addView(char_view);
                }
                advice_team_ll2.setVisibility(View.VISIBLE);
            }

            if(jsonObjectDps.has("team3")){
                info_advice_team.setVisibility(View.VISIBLE);
                advice_team_ll3.removeAllViews();
                for (int x = 0 ; x < team3.length; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice_team_2048, advice_team_ll3, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);


                    final int radius_circ = 360;
                    final int margin_circ = 0;
                    final Transformation transformation_circ = new RoundedCornersTransformation(radius_circ, margin_circ);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getCharByName(team3[x].replace("XPR"," "),context)[3],context))
                            .transform(transformation_circ)
                            .error(R.drawable.paimon_lost)
                            .resize((width_curr-32-16)/4-16,(width_curr-32-16)/4-16)
                            .into(item_img);

                    item_img.getLayoutParams().width=WRAP_CONTENT;
                    item_img.getLayoutParams().height=WRAP_CONTENT;

                    item_img.setAdjustViewBounds(true);
                    String json_base = LoadData("db/char/char_list.json");
                    String name ;
                    int rare;
                    try {
                        JSONArray array = new JSONArray(json_base);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            name = object.getString("name");
                            rare = object.getInt("rare");

                            if (name.equals(team3[x].replace("XPR"," "))){
                                if (rare == 4){
                                    item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);
                                }else if (rare == 5){
                                    item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    advice_team_ll3.addView(char_view);
                }
                advice_team_ll3.setVisibility(View.VISIBLE);
            }

            if(jsonObjectDps.has("team4")){
                info_advice_team.setVisibility(View.VISIBLE);
                advice_team_ll4.removeAllViews();
                for (int x = 0 ; x < team4.length; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice_team_2048, advice_team_ll4, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);


                    final int radius_circ = 360;
                    final int margin_circ = 0;
                    final Transformation transformation_circ = new RoundedCornersTransformation(radius_circ, margin_circ);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getCharByName(team4[x].replace("XPR"," "),context)[3],context))
                            .transform(transformation_circ)
                            .error(R.drawable.paimon_lost)
                            .resize((width_curr-32-16)/4-16,(width_curr-32-16)/4-16)
                            .into(item_img);

                    item_img.getLayoutParams().width=WRAP_CONTENT;
                    item_img.getLayoutParams().height=WRAP_CONTENT;

                    item_img.setAdjustViewBounds(true);
                    String json_base = LoadData("db/char/char_list.json");
                    String name ;
                    int rare;
                    try {
                        JSONArray array = new JSONArray(json_base);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            name = object.getString("name");
                            rare = object.getInt("rare");

                            if (name.equals(team4[x].replace("XPR"," "))){
                                if (rare == 4){
                                    item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);
                                }else if (rare == 5){
                                    item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    advice_team_ll4.addView(char_view);
                }
                advice_team_ll4.setVisibility(View.VISIBLE);
            }

            if(jsonObjectDps.has("team5")){
                info_advice_team.setVisibility(View.VISIBLE);
                advice_team_ll5.removeAllViews();
                for (int x = 0 ; x < team5.length; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice_team_2048, advice_team_ll5, false);
                    ImageView item_img = char_view.findViewById(R.id.advice_item_img);


                    final int radius_circ = 360;
                    final int margin_circ = 0;
                    final Transformation transformation_circ = new RoundedCornersTransformation(radius_circ, margin_circ);
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getCharByName(team5[x].replace("XPR"," "),context)[3],context))
                            .transform(transformation_circ)
                            .error(R.drawable.paimon_lost)
                            .resize((width_curr-32-16)/4-16,(width_curr-32-16)/4-16)
                            .into(item_img);

                    item_img.getLayoutParams().width=WRAP_CONTENT;
                    item_img.getLayoutParams().height=WRAP_CONTENT;

                    item_img.setAdjustViewBounds(true);
                    String json_base = LoadData("db/char/char_list.json");
                    String name ;
                    int rare;
                    try {
                        JSONArray array = new JSONArray(json_base);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            name = object.getString("name");
                            rare = object.getInt("rare");

                            if (name.equals(team5[x].replace("XPR"," "))){
                                if (rare == 4){
                                    item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);
                                }else if (rare == 5){
                                    item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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

    public boolean readCharESkillDataFromBuff(String name, int charSkill1AfterLvl) {
        String char_json_stat = LoadData("db/buff/char/"+name.replace(" ","_")+".json");
        charSkill1AfterLvl = charSkill1AfterLvl +1;
        if (char_json_stat.length() > 0){
            try {
                JSONObject jsonObject = new JSONObject(char_json_stat);

                // getting inner array Ingredients
                JSONArray 元素戰技STR = jsonObject.getJSONArray("元素戰技STR");
                JSONObject 元素戰技 = jsonObject.getJSONObject("元素戰技");

            /*
            "T1" [0.111,...,...]
            "T2" [0.653,...,...]
            ...
             */

                元素戰技_baseName = new String[元素戰技STR.length()];
                元素戰技_value = new double[元素戰技STR.length()][15];

                for (int x = 0 ; x<元素戰技STR.length() ; x++ ){
                    元素戰技_baseName[x] = String.valueOf(元素戰技STR.get(x));
                    for (int y = 0 ; y< 15 ; y++){
                        元素戰技_value[x][y] = (double) 元素戰技.getJSONArray(String.valueOf(元素戰技STR.get(x))).getDouble(y);
                    }
                }

            /*
            System.out.println("元素戰技_value : ");

            for (int x = 0 ; x <元素戰技STR.length() ; x++ ){
                System.out.println(Arrays.toString(元素戰技_value[x]));
            }
             */



            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }else {
            CustomToast.toast(context,activity,context.getString(R.string.none_info));
            return false;
        }
    }

    public boolean readCharQSkillDataFromBuff(String name, int charSkill1AfterLvl) {
        String char_json_stat = LoadData("db/buff/char/"+name.replace(" ","_")+".json");
        charSkill1AfterLvl = charSkill1AfterLvl +1;
        if(char_json_stat.length() > 0){
            try {
                JSONObject jsonObject = new JSONObject(char_json_stat);

                // getting inner array Ingredients
                JSONArray 元素爆發STR = jsonObject.getJSONArray("元素爆發STR");
                JSONObject 元素爆發 = jsonObject.getJSONObject("元素爆發");

            /*
            "T1" [0.111,...,...]
            "T2" [0.653,...,...]
            ...
             */

                元素爆發_baseName = new String[元素爆發STR.length()];
                元素爆發_value = new double[元素爆發STR.length()][15];

                for (int x = 0 ; x<元素爆發STR.length() ; x++ ){
                    元素爆發_baseName[x] = String.valueOf(元素爆發STR.get(x));
                    for (int y = 0 ; y< 15 ; y++){
                        元素爆發_value[x][y] = (double) 元素爆發.getJSONArray(String.valueOf(元素爆發STR.get(x))).getDouble(y);
                    }
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }else {
            CustomToast.toast(context,activity,context.getString(R.string.none_info));
            return false;
        }
    }

    public boolean readCharNormalATKDataFromBuff(String name, int charSkill1AfterLvl) {
        String char_json_stat = LoadData("db/buff/char/"+name.replace(" ","_")+".json");
        charSkill1AfterLvl = charSkill1AfterLvl +1;
        if (char_json_stat.length() > 0) {
            try {
                JSONObject jsonObject = new JSONObject(char_json_stat);
                JSONObject 普通攻擊 = jsonObject.getJSONObject("普通攻擊");

                JSONArray 一段傷害 = 普通攻擊.getJSONArray("一段傷害");
                普通攻擊_一段傷害 = 一段傷害.getDouble(charSkill1AfterLvl - 1);

                JSONArray 二段傷害 = 普通攻擊.getJSONArray("二段傷害");
                普通攻擊_二段傷害 = 二段傷害.getDouble(charSkill1AfterLvl - 1);

                JSONArray 三段傷害 = 普通攻擊.getJSONArray("三段傷害");
                普通攻擊_三段傷害 = 三段傷害.getDouble(charSkill1AfterLvl - 1);

                JSONArray 四段傷害 = 普通攻擊.getJSONArray("四段傷害");
                普通攻擊_四段傷害 = 四段傷害.getDouble(charSkill1AfterLvl - 1);

                JSONArray 五段傷害 = 普通攻擊.getJSONArray("五段傷害");
                普通攻擊_五段傷害 = 五段傷害.getDouble(charSkill1AfterLvl - 1);

                JSONArray 六段傷害 = 普通攻擊.getJSONArray("六段傷害");
                普通攻擊_六段傷害 = 六段傷害.getDouble(charSkill1AfterLvl - 1);

                JSONArray 下墜期間傷害 = 普通攻擊.getJSONArray("下墜期間傷害");
                普通攻擊_下墜期間傷害 = 下墜期間傷害.getDouble(charSkill1AfterLvl - 1);

                JSONArray 低空墜地衝擊傷害 = 普通攻擊.getJSONArray("低空墜地衝擊傷害");
                普通攻擊_低空墜地衝擊傷害 = 低空墜地衝擊傷害.getDouble(charSkill1AfterLvl - 1);

                JSONArray 高空墜地衝擊傷害 = 普通攻擊.getJSONArray("高空墜地衝擊傷害");
                普通攻擊_高空墜地衝擊傷害 = 高空墜地衝擊傷害.getDouble(charSkill1AfterLvl - 1);

                JSONArray 瞄準射擊 = 普通攻擊.getJSONArray("瞄準射擊");
                普通攻擊_瞄準射擊 = 瞄準射擊.getDouble(charSkill1AfterLvl - 1);

                JSONArray 滿蓄力瞄準射擊 = 普通攻擊.getJSONArray("滿蓄力瞄準射擊");
                普通攻擊_滿蓄力瞄準射擊 = 滿蓄力瞄準射擊.getDouble(charSkill1AfterLvl - 1);

                JSONArray 重擊傷害 = 普通攻擊.getJSONArray("重擊傷害");
                普通攻擊_重擊傷害 = 重擊傷害.getDouble(charSkill1AfterLvl - 1);

                JSONArray 重擊循環傷害 = 普通攻擊.getJSONArray("重擊循環傷害");
                普通攻擊_重擊循環傷害 = 重擊循環傷害.getDouble(charSkill1AfterLvl - 1);

                JSONArray 重擊終結傷害 = 普通攻擊.getJSONArray("重擊終結傷害");
                普通攻擊_重擊終結傷害 = 重擊終結傷害.getDouble(charSkill1AfterLvl - 1);

                //----- Special For Some Char (Ganyu, Yoimiya, Itto)

                // Ganyu
                if (普通攻擊.has("一段蓄力瞄準射擊") && 普通攻擊.has("霜華矢命中傷害") && 普通攻擊.has("霜華矢·霜華綻發傷害")) {
                    JSONArray 一段蓄力瞄準射擊 = 普通攻擊.getJSONArray("一段蓄力瞄準射擊");
                    普通攻擊_一段蓄力瞄準射擊 = 一段蓄力瞄準射擊.getDouble(charSkill1AfterLvl - 1);

                    JSONArray 霜華矢命中傷害 = 普通攻擊.getJSONArray("霜華矢命中傷害");
                    普通攻擊_霜華矢命中傷害 = 霜華矢命中傷害.getDouble(charSkill1AfterLvl - 1);

                    JSONArray 霜華矢_霜華綻發傷害 = 普通攻擊.getJSONArray("霜華矢·霜華綻發傷害");
                    普通攻擊_霜華矢_霜華綻發傷害 = 霜華矢_霜華綻發傷害.getDouble(charSkill1AfterLvl - 1);
                }

                // Yoimiya
                if (普通攻擊.has("焰硝矢傷害")) {
                    JSONArray 焰硝矢傷害 = 普通攻擊.getJSONArray("焰硝矢傷害");
                    普通攻擊_焰硝矢傷害 = 焰硝矢傷害.getDouble(charSkill1AfterLvl - 1);
                }

                // Itto
                if (普通攻擊.has("荒瀧逆袈裟連斬傷害") && 普通攻擊.has("荒瀧逆袈裟終結傷害") && 普通攻擊.has("左一文字斬傷害")) {
                    JSONArray 荒瀧逆袈裟連斬傷害 = 普通攻擊.getJSONArray("荒瀧逆袈裟連斬傷害");
                    普通攻擊_荒瀧逆袈裟連斬傷害 = 荒瀧逆袈裟連斬傷害.getDouble(charSkill1AfterLvl - 1);

                    JSONArray 荒瀧逆袈裟終結傷害 = 普通攻擊.getJSONArray("荒瀧逆袈裟終結傷害");
                    普通攻擊_荒瀧逆袈裟終結傷害 = 荒瀧逆袈裟終結傷害.getDouble(charSkill1AfterLvl - 1);

                    JSONArray 左一文字斬傷害 = 普通攻擊.getJSONArray("左一文字斬傷害");
                    普通攻擊_左一文字斬傷害 = 左一文字斬傷害.getDouble(charSkill1AfterLvl - 1);
                }

                // Tartaglia
                if (普通攻擊.has("斷流·閃 傷害") && 普通攻擊.has("斷流·破 傷害")) {
                    JSONArray 斷流_閃_傷害 = 普通攻擊.getJSONArray("斷流·閃 傷害");
                    普通攻擊_斷流_閃_傷害 = 斷流_閃_傷害.getDouble(charSkill1AfterLvl - 1);

                    JSONArray 斷流_破_傷害 = 普通攻擊.getJSONArray("斷流·破 傷害");
                    普通攻擊_斷流_破_傷害 = 斷流_破_傷害.getDouble(charSkill1AfterLvl - 1);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }else{
            CustomToast.toast(context,activity,context.getString(R.string.none_info));
            return false;
        }
    }

    public boolean readCharBaseDataFromBuff(String name, int tmp_break) {
        String char_json_stat = LoadData("db/buff/char/"+name.replace(" ","_")+".json");
        if (char_json_stat.length() > 0){
            try {
                JSONObject jsonObject = new JSONObject(char_json_stat);
                JSONObject 角色突破 = jsonObject.getJSONObject("角色突破");

                JSONArray 基礎生命值 = 角色突破.getJSONArray("基礎生命值");
                角色基礎生命值 = 基礎生命值.getDouble(tmp_break);

                JSONArray 基礎攻擊力 = 角色突破.getJSONArray("基礎攻擊力");
                角色基礎攻擊力 = 基礎攻擊力.getDouble(tmp_break);

                JSONArray 基礎防禦力 = 角色突破.getJSONArray("基礎防禦力");
                角色基礎防禦力 = 基礎防禦力.getDouble(tmp_break);

                JSONArray 生命值加成 = 角色突破.getJSONArray("生命值加成");
                角色生命值加成 = 生命值加成.getDouble(tmp_break);

                JSONArray 攻擊力加成 = 角色突破.getJSONArray("攻擊力加成");
                角色攻擊力加成 = 攻擊力加成.getDouble(tmp_break);

                JSONArray 防禦力加成 = 角色突破.getJSONArray("防禦力加成");
                角色防禦力加成 = 防禦力加成.getDouble(tmp_break);

                JSONArray 暴擊率 = 角色突破.getJSONArray("暴擊率");
                角色暴擊率 = 暴擊率.getDouble(tmp_break);

                JSONArray 暴擊傷害 = 角色突破.getJSONArray("暴擊傷害");
                角色暴擊傷害 = 暴擊傷害.getDouble(tmp_break);

                JSONArray 元素充能 = 角色突破.getJSONArray("元素充能");
                角色元素充能 = 元素充能.getDouble(tmp_break);

                JSONArray 元素精通 = 角色突破.getJSONArray("元素精通");
                角色元素精通 = 元素精通.getDouble(tmp_break);

                JSONArray 治療加成 = 角色突破.getJSONArray("治療加成");
                角色治療加成 = 治療加成.getDouble(tmp_break);

                JSONArray 火元素傷害加成 = 角色突破.getJSONArray("火元素傷害加成");
                角色火元素傷害加成 = 火元素傷害加成.getDouble(tmp_break);

                JSONArray 水元素傷害加成 = 角色突破.getJSONArray("水元素傷害加成");
                角色水元素傷害加成 = 水元素傷害加成.getDouble(tmp_break);

                JSONArray 風元素傷害加成 = 角色突破.getJSONArray("風元素傷害加成");
                角色風元素傷害加成 = 風元素傷害加成.getDouble(tmp_break);

                JSONArray 雷元素傷害加成 = 角色突破.getJSONArray("雷元素傷害加成");
                角色雷元素傷害加成 = 雷元素傷害加成.getDouble(tmp_break);

                JSONArray 草元素傷害加成 = 角色突破.getJSONArray("草元素傷害加成");
                角色草元素傷害加成 = 草元素傷害加成.getDouble(tmp_break);

                JSONArray 冰元素傷害加成 = 角色突破.getJSONArray("冰元素傷害加成");
                角色冰元素傷害加成 = 冰元素傷害加成.getDouble(tmp_break);

                JSONArray 岩元素傷害加成 = 角色突破.getJSONArray("岩元素傷害加成");
                角色岩元素傷害加成 = 岩元素傷害加成.getDouble(tmp_break);

                JSONArray 物理傷害加成 = 角色突破.getJSONArray("物理傷害加成");
                角色物理傷害加成 = 物理傷害加成.getDouble(tmp_break);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }else{
            CustomToast.toast(context,activity,context.getString(R.string.none_info));
            return false;
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

    public void setWeaponIntoScroll(String[] advice, LinearLayout advice_sc, CardView cardView){
        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(25, 0, RoundedCornersTransformation.CornerType.ALL);
        advice_sc.removeAllViews();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        int width_per_one = (int) ((width-16*2-8*2)/5-16);

        for (int x = 0 ; x < advice.length; x++) {
            View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice_2048, advice_sc, false);
            ImageView img_bg  = char_view.findViewById(R.id.img_bg);
            ImageView img_img = char_view.findViewById(R.id.img_weapon);
            TextView img_tv = char_view.findViewById(R.id.img_tv);

            img_tv.setText(item_rss.getWeaponByName(item_rss.getWeaponNameByFileName(advice[x]),context)[0]);
            int min_width = 240;

            boolean isNight = false;
            // Background of item
            if (context.getResources().getString(R.string.mode).equals("Night")) {
                isNight = true;
            }

            switch (getWeaponRareFromListJson(item_rss.getWeaponNameByFileName(advice[x]))){
                case 1 : {
                    if(isNight){
                        Picasso.get().load(R.drawable.rare1_800x1000_dark).resize(min_width,(int)(min_width*10/8)).into(img_bg);
                    }else{
                        Picasso.get().load(R.drawable.rare1_800x1000_light).resize(min_width,(int)(min_width*10/8)).into(img_bg);
                    }
                    break;
                }
                case 2 : {
                    if(isNight){
                        Picasso.get().load(R.drawable.rare2_800x1000_dark).resize(min_width,(int)(min_width*10/8)).into(img_bg);
                    }else{
                        Picasso.get().load(R.drawable.rare2_800x1000_light).resize(min_width,(int)(min_width*10/8)).into(img_bg);
                    }
                    break;
                }
                case 3 : {
                    if(isNight){
                        Picasso.get().load(R.drawable.rare3_800x1000_dark).resize(min_width,(int)(min_width*10/8)).into(img_bg);
                    }else{
                        Picasso.get().load(R.drawable.rare3_800x1000_light).resize(min_width,(int)(min_width*10/8)).into(img_bg);
                    }
                    break;
                }
                case 4 : {
                    if(isNight){
                        Picasso.get().load(R.drawable.rare4_800x1000_dark).resize(min_width,(int)(min_width*10/8)).into(img_bg);
                    }else{
                        Picasso.get().load(R.drawable.rare4_800x1000_light).resize(min_width,(int)(min_width*10/8)).into(img_bg);
                    }
                    break;
                }
                case 5 : {
                    if(isNight){
                        Picasso.get().load(R.drawable.rare5_800x1000_dark).resize(min_width,(int)(min_width*10/8)).into(img_bg);
                    }else{
                        Picasso.get().load(R.drawable.rare5_800x1000_light).resize(min_width,(int)(min_width*10/8)).into(img_bg);
                    }
                    break;
                }
                default: {
                    if(isNight){
                        Picasso.get().load(R.drawable.rare1_800x1000_dark).resize(min_width,(int)(min_width*10/8)).into(img_bg);
                    }else{
                        Picasso.get().load(R.drawable.rare1_800x1000_light).resize(min_width,(int)(min_width*10/8)).into(img_bg);
                    }
                    break;
                }
            }

            Picasso.get()
                    .load(FileLoader.loadIMG(item_rss.getWeaponByName(item_rss.getWeaponNameByFileName(advice[x]),context)[1],context))
                    .resize(min_width,min_width).transform(roundedCornersTransformation)
                    .error(R.drawable.paimon_lost)
                    .into(img_img);
            img_img.getLayoutParams().width = min_width;
            img_img.getLayoutParams().height = min_width;
            img_bg.getLayoutParams().width = min_width;
            img_bg.getLayoutParams().height = (int)(min_width*10/8);
            advice_sc.addView(char_view);
        }

        cardView.setVisibility(View.VISIBLE);
        advice_sc.setVisibility(View.VISIBLE);
    }

    public void setArtifactIntoScroll(String[] advice, LinearLayout advice_sc){
        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(25, 0, RoundedCornersTransformation.CornerType.ALL);
        advice_sc.removeAllViews();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        int min_width = (int) ((width)/7.5);

        int[] pos = new int[]{4,2,5,1,3};
        for (int x = 0 ; x < advice.length; x++) {
            View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_advice_2048, advice_sc, false);
            ImageView img_bg  = char_view.findViewById(R.id.img_bg);
            ImageView img_img = char_view.findViewById(R.id.img_weapon);
            TextView img_tv = char_view.findViewById(R.id.img_tv);

            boolean isNight = false;
            // Background of item
            if (context.getResources().getString(R.string.mode).equals("Night")) {
                isNight = true;
            }

            switch (getArtifactRareFromListJson(item_rss.getArtifactNameByFileName(advice[x]))){
                case 1 : {
                    if(isNight){
                        Picasso.get().load(R.drawable.rare1_800x800).resize(min_width,min_width).into(img_bg);
                    }else{
                        Picasso.get().load(R.drawable.rare1_800x800).resize(min_width,min_width).into(img_bg);
                    }
                    break;
                }
                case 2 : {
                    if(isNight){
                        Picasso.get().load(R.drawable.rare2_800x800).resize(min_width,min_width).into(img_bg);
                    }else{
                        Picasso.get().load(R.drawable.rare2_800x800).resize(min_width,min_width).into(img_bg);
                    }
                    break;
                }
                case 3 : {
                    if(isNight){
                        Picasso.get().load(R.drawable.rare3_800x800).resize(min_width,min_width).into(img_bg);
                    }else{
                        Picasso.get().load(R.drawable.rare3_800x800).resize(min_width,min_width).into(img_bg);
                    }
                    break;
                }
                case 4 : {
                    if(isNight){
                        Picasso.get().load(R.drawable.rare4_800x800).resize(min_width,min_width).into(img_bg);
                    }else{
                        Picasso.get().load(R.drawable.rare4_800x800).resize(min_width,min_width).into(img_bg);
                    }
                    break;
                }
                case 5 : {
                    if(isNight){
                        Picasso.get().load(R.drawable.rare5_800x800).resize(min_width,min_width).into(img_bg);
                    }else{
                        Picasso.get().load(R.drawable.rare5_800x800).resize(min_width,min_width).into(img_bg);
                    }
                    break;
                }
                default: {
                    if(isNight){
                        Picasso.get().load(R.drawable.rare1_800x800).resize(min_width,min_width).into(img_bg);
                    }else{
                        Picasso.get().load(R.drawable.rare1_800x800).resize(min_width,min_width).into(img_bg);
                    }
                    break;
                }
            }

            Picasso.get()
                    .load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(advice[x]),context)[pos[x]],context))
                    .fit().transform(roundedCornersTransformation)
                    .error(R.drawable.paimon_lost)
                    .into(img_img);
            img_tv.setText(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(advice[x]),context)[0]);
            img_tv.setTextSize(10);
            img_tv.setVisibility(View.GONE);
            img_img.getLayoutParams().width = min_width;
            img_img.getLayoutParams().height = min_width;
            img_bg.getLayoutParams().width = min_width;
            img_bg.getLayoutParams().height = min_width;
            advice_sc.addView(char_view);
        }
        advice_sc.setVisibility(View.VISIBLE);
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

    public void readCharAscData(){
        String char_lvl_exp = LoadData("db/char/char_lvl_exp.json");
        String char_asc_lvl = LoadData("db/char/char_asc_lvl.json");
        String char_skill_lvl = LoadData("db/char/char_skill_lvl.json");
        String char_require_asc_skill = LoadData("db/char/char_require_asc_skill.json");

        //Log.wtf("Procedure","char_readJSON_1"+" || "+System.currentTimeMillis());
        try {
            JSONArray array = new JSONArray(char_lvl_exp);
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
            JSONArray array = new JSONArray(char_asc_lvl);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int lvl = object.getInt("lvl");
                int silver = object.getInt("silver");
                int fragment = object.getInt("fragment");
                int chunk = object.getInt("chunk");
                int gemstone = object.getInt("gemstone");
                int local = object.getInt("local");
                int common1 = object.getInt("common1");
                int common2 = object.getInt("common2");
                int common3 = object.getInt("common3");
                int boss = object.getInt("boss");
                int mora = object.getInt("mora");
                lvlASCList.add(lvl);
                silverASCList.add(silver);
                fragASCList.add(fragment);
                chunkASCList.add(chunk);
                gemASCList.add(gemstone);
                localASCList.add(local);
                com1ASCList.add(common1);
                com2ASCList.add(common2);
                com3ASCList.add(common3);
                bossASCList.add(boss);
                moraASCList.add(mora);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONArray array = new JSONArray(char_skill_lvl);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int lvl = object.getInt("lvl");
                int teach = object.getInt("teach");
                int guide = object.getInt("guide");
                int phi = object.getInt("phi");
                int common1 = object.getInt("common1");
                int common2 = object.getInt("common2");
                int common3 = object.getInt("common3");
                int boss = object.getInt("boss");
                int mora = object.getInt("mora");
                lvlSKILLList.add(lvl);
                teachSKILLList.add(teach);
                guideSKILLList.add(guide);
                phiSKILLList.add(phi);
                com1SKILLList.add(common1);
                com2SKILLList.add(common2);
                com3SKILLList.add(common3);
                bossSKILLList.add(boss);
                moraSkillList.add(mora);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONArray array = new JSONArray(char_require_asc_skill);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String name = object.getString("name");
                String crystal = object.getString("crystal");
                String boss = object.getString("boss");
                String local = object.getString("local");
                String common = object.getString("common");
                String book = object.getString("book");
                String t_boss = object.getString("t_boss");

                System.out.println("CharName_BASE CIF : "+CharName_BASE);
                String CharName = CharName_BASE.replace("_"," ");
                if(name.equals(CharName)){
                    crystalREQUIRE = crystal;
                    bossREQUIRE = boss;
                    localREQUIRE = local;
                    commonREQUIRE = common;
                    bookREQUIRE = book;
                    t_bossREQUIRE = t_boss;

                    System.out.println("crystal CIF : "+crystal);
                    System.out.println("boss CIF : "+boss);
                    System.out.println("local CIF : "+local);
                    System.out.println("common CIF : "+common);
                    System.out.println("book CIF : "+book);
                    System.out.println("t_boss CIF : "+t_boss);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void readCharMaterialByBuff(LinearLayout base_lvl_ll) {
        Spinner base_lvl_spinner = charAdvicePage.findViewById(R.id.base_lvl_spinner);
        String[] lvList = new String[]{"LV20","LV40","LV50","LV60","LV70","LV80","LV90"};
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


    public void readSkillMaterialByBuff(LinearLayout talent_lvl_ll) {
        Spinner base_lvl_spinner = charAdvicePage.findViewById(R.id.talent_lvl_spinner);
        String[] lvList = new String[]{"LV1","LV2","LV3","LV4","LV5","LV6","LV7","LV8","LV9","LV10"};
        ArrayAdapter lv_aa = new ArrayAdapter(context,R.layout.spinner_item_anti_2048,lvList);
        lv_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_anti_2048);
        base_lvl_spinner.setAdapter(lv_aa);

        base_lvl_spinner.setSelection(0);
        base_lvl_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                talent_lvl_ll.removeAllViews();
                talent_lvl_ll.removeAllViewsInLayout();
                addItemInLLT(pos,talent_lvl_ll);
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

        String[] itemNameListBASE = new String[]{"摩拉","流浪者的經驗", "冒險家的經驗","大英雄的經驗"};
        int[] itemValueListBASE = new int[]{mora,exp_small,exp_mid,exp_big};
        int[] itemRareListBASE = new int[]{2,2,3,4};
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
            String[] itemNameList = new String[]{
                    getCharCrystalListByItemName(crystalREQUIRE)[0],
                    getCharCrystalListByItemName(crystalREQUIRE)[1],
                    getCharCrystalListByItemName(crystalREQUIRE)[2],
                    getCharCrystalListByItemName(crystalREQUIRE)[3],
                    bossREQUIRE,
                    localREQUIRE,
                    getCharCommonListByItemName(commonREQUIRE)[0],
                    getCharCommonListByItemName(commonREQUIRE)[1],
                    getCharCommonListByItemName(commonREQUIRE)[2]
            };

            int[] itemValueList = new int[]{
                    silverASCList.get(pos-1),
                    fragASCList.get(pos-1),
                    chunkASCList.get(pos-1),
                    gemASCList.get(pos-1),
                    bossASCList.get(pos-1),
                    localASCList.get(pos-1),
                    com1ASCList.get(pos-1),
                    com2ASCList.get(pos-1),
                    com3ASCList.get(pos-1)
            };

            int[] itemRareList = new int[]{2,3,4,5,4,1,1,2,3};
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

    public void addItemInLLT(int pos, LinearLayout talent_lvl_ll){
        int mora = 0;

        mora = mora + moraSkillList.get(pos);

        if (pos != 0){
            String[] itemNameList = new String[]{
                    "摩拉",
                    getBookListByItemName(bookREQUIRE)[0],
                    getBookListByItemName(bookREQUIRE)[1],
                    getBookListByItemName(bookREQUIRE)[2],
                    getCharCommonListByItemName(commonREQUIRE)[0],
                    getCharCommonListByItemName(commonREQUIRE)[1],
                    getCharCommonListByItemName(commonREQUIRE)[2],
                    t_bossREQUIRE
            };

            int[] itemValueList = new int[]{
                    mora,
                    teachSKILLList.get(pos),
                    guideSKILLList.get(pos),
                    phiSKILLList.get(pos),
                    com1SKILLList.get(pos),
                    com2SKILLList.get(pos),
                    com3SKILLList.get(pos),
                    bossSKILLList.get(pos)
            };

            int[] itemRareList = new int[]{2,2,3,4,1,2,3,5};
            for (int x = 0 ; x < itemNameList.length ; x++){
                if (itemValueList[x] > 0) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_base_lvl_material_2048, talent_lvl_ll, false);
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
                    talent_lvl_ll.addView(char_view);
                }
            }
            if (pos == 9) {
                View char_view = LayoutInflater.from(context).inflate(R.layout.item_char_base_lvl_material_2048, talent_lvl_ll, false);
                ImageView img_bg  = char_view.findViewById(R.id.base_lvl_bg);
                ImageView img_img = char_view.findViewById(R.id.base_lvl_img);
                TextView img_tv = char_view.findViewById(R.id.base_lvl_tv);
                Picasso.get().load(getRssByRare(5)).resize(150,180).into(img_bg);
                Picasso.get().load(FileLoader.loadIMG(item_rss.getItemIcoByName("智識之冕",context),context)).resize(144,144).into(img_img);
                img_tv.setText(prettyCount(1,0));

                img_bg.getLayoutParams().width = 150;
                img_bg.getLayoutParams().height = 180;
                img_img.getLayoutParams().width = 144;
                img_img.getLayoutParams().height = 144;
                img_tv.getLayoutParams().width = 150;
                img_tv.getLayoutParams().height = 36;
                talent_lvl_ll.addView(char_view);
            }
        }
    }

    public String[] getCharCrystalListByItemName (String str){
        switch (str){
            case "燃願瑪瑙" : return new String[]{"燃願瑪瑙碎屑","燃願瑪瑙斷片","燃願瑪瑙塊","燃願瑪瑙"};
            case "滌淨青金" : return new String[]{"滌淨青金碎屑","滌淨青金斷片","滌淨青金塊","滌淨青金"};
            case "最勝紫晶" : return new String[]{"最勝紫晶碎屑","最勝紫晶斷片","最勝紫晶塊","最勝紫晶"};
            case "哀敘冰玉" : return new String[]{"哀敘冰玉碎屑","哀敘冰玉斷片","哀敘冰玉塊","哀敘冰玉"};
            case "自在松石" : return new String[]{"自在松石碎屑","自在松石斷片","自在松石塊","自在松石"};
            case "堅牢黃玉" : return new String[]{"堅牢黃玉碎屑","堅牢黃玉斷片","堅牢黃玉塊","堅牢黃玉"};

            default: return new String[]{"N/A","N/A","N/A","N/A"};
        }
    }
    public String[] getCharCommonListByItemName (String str){
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
    public String[] getBookListByItemName (String str){
        switch (str){
            case "「自由」的哲學" : return new String[]{"「自由」的教導","「自由」的指引","「自由」的哲學"};
            case "「黃金」的哲學" : return new String[]{"「黃金」的教導","「黃金」的指引","「黃金」的哲學"};
            case "「抗爭」的哲學" : return new String[]{"「抗爭」的教導","「抗爭」的指引","「抗爭」的哲學"};
            case "「繁榮」的哲學" : return new String[]{"「繁榮」的教導","「繁榮」的指引","「繁榮」的哲學"};
            case "「詩文」的哲學" : return new String[]{"「詩文」的教導","「詩文」的指引","「詩文」的哲學"};
            case "「勤勞」的哲學" : return new String[]{"「勤勞」的教導","「勤勞」的指引","「勤勞」的哲學"};
            case "「風雅」的哲學" : return new String[]{"「風雅」的教導","「風雅」的指引","「風雅」的哲學"};
            case "「浮世」的哲學" : return new String[]{"「浮世」的教導","「浮世」的指引","「浮世」的哲學"};
            case "「天光」的哲學" : return new String[]{"「天光」的教導","「天光」的指引","「天光」的哲學"};

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
        exp_big = (int) exp / 20000;
        exp = exp % 20000;
        exp_mid = (int) exp / 5000;
        exp = exp % 5000;
        exp_small = (int) exp / 1000;
        if (exp % 1000 < 1000){
            exp_small = exp_small + 1;
        }

        return new int[]{exp_small,exp_mid,exp_big};
    }
}
