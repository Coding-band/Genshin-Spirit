package com.voc.genshin_helper.buff;/*
 * Package com.voc.genshin_helper.buff.CalculatorBuff was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.gridlayout.widget.GridLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.Enemys;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.ScreenSizeUtils;
import com.voc.genshin_helper.util.BackgroundReload;
import com.voc.genshin_helper.util.FileLoader;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

public class CalculatorBuff {

    /**
     * Char Transfer
     */
    ArrayList<String> charChoosedNameList = new ArrayList<String>();
    ArrayList<Integer> charChoosedBeforeLvlList= new ArrayList<Integer>();
    ArrayList<Integer> charChoosedAfterLvlList= new ArrayList<Integer>();
    ArrayList<Integer> charChoosedBeforeBreakLvlList= new ArrayList<Integer>();
    ArrayList<Boolean> charChoosedBeforeBreakUPLvlList= new ArrayList<Boolean>();
    ArrayList<Integer> charChoosedAfterBreakLvlList= new ArrayList<Integer>();
    ArrayList<Boolean> charChoosedAfterBreakUPLvlList= new ArrayList<Boolean>();
    ArrayList<Integer> charChoosedBeforeSkill1LvlList= new ArrayList<Integer>();
    ArrayList<Integer> charChoosedAfterSkill1LvlList= new ArrayList<Integer>();
    ArrayList<Integer> charChoosedBeforeSkill2LvlList= new ArrayList<Integer>();
    ArrayList<Integer> charChoosedAfterSkill2LvlList= new ArrayList<Integer>();
    ArrayList<Integer> charChoosedBeforeSkill3LvlList= new ArrayList<Integer>();
    ArrayList<Integer> charChoosedAfterSkill3LvlList= new ArrayList<Integer>();
    ArrayList<Boolean> charChoosedIsCal= new ArrayList<Boolean>();

    /**
     * Weapon Transfer
     */

    ArrayList<String> weaponChoosedNameList = new ArrayList<>();
    ArrayList<Integer> weaponChoosedBeforeLvlList = new ArrayList<>();
    ArrayList<Integer> weaponChoosedAfterLvlList = new ArrayList<>();
    ArrayList<Integer> weaponChoosedBeforeBreakLvlList = new ArrayList<>();
    ArrayList<Boolean> weaponChoosedBeforeBreakUPLvlList = new ArrayList<>();
    ArrayList<Integer> weaponChoosedAfterBreakLvlList = new ArrayList<>();
    ArrayList<Boolean> weaponChoosedAfterBreakUPLvlList = new ArrayList<>();
    ArrayList<String> weaponChoosedFollowList = new ArrayList<>();
    ArrayList<Integer> weaponChoosedRare = new ArrayList<>();
    ArrayList<Boolean> weaponChoosedIsCal = new ArrayList<>();
    ArrayList<Integer> weaponChoosedId = new ArrayList<>();

    /**
     * Artifact Transfer
     */

    ArrayList<String> artifactChoosedNameList = new ArrayList<>();
    ArrayList<Integer> artifactChoosedBeforeLvlList = new ArrayList<>();
    ArrayList<Integer> artifactChoosedAfterLvlList = new ArrayList<>();
    ArrayList<String> artifactChoosedFollowList = new ArrayList<>();
    ArrayList<Boolean> artifactChoosedIsCal = new ArrayList<>();
    ArrayList<Integer> artifactChoosedRare = new ArrayList<>();
    ArrayList<String> artifactChoosedType = new ArrayList<>();
    ArrayList<Integer> artifactChoosedId = new ArrayList<>();

    /**
     * Important Method
     */
    public Context context;
    View viewPager;
    ItemRss item_rss;
    String dataSheetName;
    SharedPreferences sharedPreferences;
    String color_hex;

    ArrayList<String> artifactBuffMainItem = new ArrayList<>(5);
    ArrayList<Double> artifactBuffMainValue = new ArrayList<>(5);
    ArrayList<String> artifactBuffSec1Item = new ArrayList<>(5);
    ArrayList<Double> artifactBuffSec1Value = new ArrayList<>(5);
    ArrayList<String> artifactBuffSec2Item = new ArrayList<>(5);
    ArrayList<Double> artifactBuffSec2Value = new ArrayList<>(5);
    ArrayList<String> artifactBuffSec3Item = new ArrayList<>(5);
    ArrayList<Double> artifactBuffSec3Value = new ArrayList<>(5);
    ArrayList<String> artifactBuffSec4Item = new ArrayList<>(5);
    ArrayList<Double> artifactBuffSec4Value = new ArrayList<>(5);

    ArrayList<String> artifactBuffSec1ItemTMP = new ArrayList<>(5);
    ArrayList<Double> artifactBuffSec1ValueTMP = new ArrayList<>(5);
    ArrayList<String> artifactBuffSec2ItemTMP = new ArrayList<>(5);
    ArrayList<Double> artifactBuffSec2ValueTMP = new ArrayList<>(5);
    ArrayList<String> artifactBuffSec3ItemTMP = new ArrayList<>(5);
    ArrayList<Double> artifactBuffSec3ValueTMP = new ArrayList<>(5);
    ArrayList<String> artifactBuffSec4ItemTMP = new ArrayList<>(5);
    ArrayList<Double> artifactBuffSec4ValueTMP = new ArrayList<>(5);

    int[] artifactBuffSec1Times = new int[]{1,1,1,1,1};
    int[] artifactBuffSec2Times = new int[]{1,1,1,1,1};
    int[] artifactBuffSec3Times = new int[]{1,1,1,1,1};
    int[] artifactBuffSec4Times = new int[]{1,1,1,1,1};


    //============================================================================//

    ArrayList<Double> artifactBasicBaseHP = new ArrayList<>();
    ArrayList<Double> artifactBasicBaseATK = new ArrayList<>();
    ArrayList<Double> artifactBasicHP = new ArrayList<>();
    ArrayList<Double> artifactBasicATK = new ArrayList<>();
    ArrayList<Double> artifactBasicDEF = new ArrayList<>();
    ArrayList<Double> artifactBasicEleMas = new ArrayList<>();
    ArrayList<Double> artifactBasicEnRech = new ArrayList<>();
    ArrayList<Double> artifactBasicPhyDMG = new ArrayList<>();
    ArrayList<Double> artifactBasicEleDMG = new ArrayList<>();
    ArrayList<Double> artifactBasicCritRate = new ArrayList<>();
    ArrayList<Double> artifactBasicCritDMG = new ArrayList<>();
    ArrayList<Double> artifactBasicHealing = new ArrayList<>();

    ArrayList<Double> artifactTierBaseHP = new ArrayList<>();
    ArrayList<Double> artifactTierBaseDEF = new ArrayList<>();
    ArrayList<Double> artifactTierBaseATK = new ArrayList<>();
    ArrayList<Double> artifactTierHP = new ArrayList<>();
    ArrayList<Double> artifactTierDEF = new ArrayList<>();
    ArrayList<Double> artifactTierATK = new ArrayList<>();
    ArrayList<Double> artifactTierEleMas = new ArrayList<>();
    ArrayList<Double> artifactTierEnRech = new ArrayList<>();
    ArrayList<Double> artifactTierCritRate = new ArrayList<>();
    ArrayList<Double> artifactTierCritDMG = new ArrayList<>();

    //============================================================================//

    String[] tmp_artifact_sort ;
    int[] tmp_artifact_lvl ;
    int[] tmp_artifact_rare ;
    int[] tmp_artifact_order ;

    TextView buff_artifact_main_value ;
    TextView buff_artifact_main_name ;
    Spinner buff_artifact_main_name_sp ;
    TextView buff_artifact_sec1_value ;
    TextView buff_artifact_sec1_name ;
    TextView buff_artifact_sec2_value ;
    TextView buff_artifact_sec2_name ;
    TextView buff_artifact_sec3_value ;
    TextView buff_artifact_sec3_name ;
    TextView buff_artifact_sec4_value ;
    TextView buff_artifact_sec4_name ;

    ImageView buff_enemy_ico;
    TextView buff_enemy_lvl;
    TextView buff_enemy_name;
    LinearLayout buff_enemy_ll;

    LinearLayout buff_artifact_main ;
    LinearLayout buff_artifact_sec1 ;
    LinearLayout buff_artifact_sec2 ;
    LinearLayout buff_artifact_sec3 ;
    LinearLayout buff_artifact_sec4 ;

    public BuffCal2 buffCal;

    // --------- BUFF PAGE --------//
    // Char_Info
    ImageView buff_char_icon;
    ImageView buff_char_element;
    TextView buff_char_name;
    TextView buff_char_lvl;

    // Char_BUFF_Part
    TextView buff_char_atk_value;
    TextView buff_char_hp_value;
    TextView buff_char_def_value;
    TextView buff_char_crit_rate_value;
    TextView buff_char_crit_dmg_value;
    TextView buff_char_enrech_value;
    TextView buff_char_elemas_value;
    TextView buff_char_healing_value;
    TextView buff_char_eledmg_pyro_value;
    TextView buff_char_eledmg_hydro_value;
    TextView buff_char_eledmg_anemo_value;
    TextView buff_char_eledmg_electro_value;
    TextView buff_char_eledmg_dendor_value;
    TextView buff_char_eledmg_cryo_value;
    TextView buff_char_eledmg_geo_value;
    TextView buff_char_phy_value;


    // Weapon_Info
    ImageView buff_weapon_icon;
    TextView buff_weapon_name;
    TextView buff_weapon_lvl;

    LinearLayout buff_tab_view;

    // Enemy List
    public ArrayList<Enemys> enemysList = new ArrayList<>();

    boolean show_enemy_t1;
    boolean show_enemy_t2;
    boolean show_enemy_t3;
    boolean show_enemy_t4;
    boolean show_enemy_t5;
    boolean show_enemy_t6;
    boolean show_enemy_t7;
    boolean show_enemy_t8;
    boolean show_enemy_t9;
    boolean show_enemy_t10;
    boolean show_enemy_t11;
    boolean show_enemy_t12;
    boolean show_enemy_t13;
    boolean show_enemy_t14;
    boolean show_enemy_t15;

    GridLayout gridLayout;

    String[] artifactList = new String[]{"N/A","N/A","N/A","N/A"};

    boolean display_normal = true;
    boolean display_charging = false;
    boolean display_pluging = false;
    boolean display_element_atk = false;
    boolean display_final_atk = false;

    String charName = "unknown";
    String charElement = "Physical";
    String charWeapon = "Sword";

    public void ui_setup (Context context, View viewPager){
        this.context = context;
        this.viewPager = viewPager;
        item_rss = new ItemRss();
        sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        color_hex = sharedPreferences.getString("theme_color_hex","#FF5A5A");
        readBasicTier();
    }

    public void weapon_setup(ArrayList<String> arrayList, ArrayList<Integer> arrayList2, ArrayList<Integer> arrayList3, ArrayList<Integer> arrayList4, ArrayList<Integer> arrayList5, ArrayList<Boolean> arrayList6, ArrayList<Boolean> arrayList7, ArrayList<Boolean> arrayList8, ArrayList<String> arrayList9, ArrayList<Integer> arrayList10, ArrayList<Integer> arrayList11, String arg) {
        this.weaponChoosedNameList = arrayList;
        this.weaponChoosedBeforeLvlList = arrayList2;
        this.weaponChoosedAfterLvlList = arrayList3;
        this.weaponChoosedBeforeBreakLvlList = arrayList4;
        this.weaponChoosedAfterBreakLvlList = arrayList5;
        this.weaponChoosedIsCal = arrayList6;
        this.weaponChoosedBeforeBreakUPLvlList = arrayList7;
        this.weaponChoosedAfterBreakUPLvlList = arrayList8;
        this.weaponChoosedFollowList = arrayList9;
        this.weaponChoosedRare = arrayList10;
        this.weaponChoosedId = arrayList11;
    }

    public void char_setup(ArrayList<String> arrayList, ArrayList<Integer> arrayList2, ArrayList<Integer> arrayList3, ArrayList<Integer> arrayList4, ArrayList<Integer> arrayList5, ArrayList<Integer> arrayList6, ArrayList<Integer> arrayList7, ArrayList<Integer> arrayList8, ArrayList<Integer> arrayList9, ArrayList<Integer> arrayList10, ArrayList<Integer> arrayList11, ArrayList<Boolean> arrayList12, ArrayList<Boolean> arrayList13, ArrayList<Boolean> arrayList14,String arg) {
        this.charChoosedNameList = arrayList;
        this.charChoosedBeforeLvlList = arrayList2;
        this.charChoosedAfterLvlList = arrayList3;
        this.charChoosedBeforeBreakLvlList = arrayList4;
        this.charChoosedAfterBreakLvlList = arrayList5;
        this.charChoosedBeforeSkill1LvlList = arrayList6;
        this.charChoosedAfterSkill1LvlList = arrayList7;
        this.charChoosedBeforeSkill2LvlList = arrayList8;
        this.charChoosedAfterSkill2LvlList = arrayList9;
        this.charChoosedBeforeSkill3LvlList = arrayList10;
        this.charChoosedAfterSkill3LvlList = arrayList11;
        this.charChoosedIsCal = arrayList12;
        this.charChoosedBeforeBreakUPLvlList = arrayList13;
        this.charChoosedAfterBreakUPLvlList = arrayList14;
    }

    public void artifact_setup(ArrayList<String> artifactChoosedNameList, ArrayList<Integer> artifactChoosedBeforeLvlList, ArrayList<Integer> artifactChoosedAfterLvlList, ArrayList<Boolean> artifactChoosedIsCal, ArrayList<String> artifactChoosedFollowList, ArrayList<Integer> artifactChoosedRare,ArrayList<String> artifactChoosedType,ArrayList<Integer> artifactChoosedId, String arg) {
        this.artifactChoosedNameList = artifactChoosedNameList;
        this.artifactChoosedBeforeLvlList = artifactChoosedBeforeLvlList;
        this.artifactChoosedAfterLvlList = artifactChoosedAfterLvlList;
        this.artifactChoosedIsCal = artifactChoosedIsCal;
        this.artifactChoosedFollowList = artifactChoosedFollowList;
        this.artifactChoosedRare = artifactChoosedRare;
        this.artifactChoosedType = artifactChoosedType;
        this.artifactChoosedId = artifactChoosedId;


    }

    public void view_setup(){
        /** INIT of UI GONE*/
        GridLayout buff_ui_view = viewPager.findViewById(R.id.buff_ui_view);
        buff_ui_view.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        buff_ui_view.setColumnCount(1);
        buff_ui_view.removeAllViewsInLayout();
        String tmp_weapon_name = "unknown";
        for (int x = 0 ; x < charChoosedNameList.size() ; x++) {

            View view = View.inflate(context, R.layout.item_buff_card, null);

            ImageView buff_ui_char_icon = view.findViewById(R.id.buff_ui_char_icon);
            TextView buff_ui_char_name = view.findViewById(R.id.buff_ui_char_name);
            ImageView buff_ui_weapon_icon = view.findViewById(R.id.buff_ui_weapon_icon);
            TextView buff_ui_press_more = view.findViewById(R.id.buff_ui_press_more);

            final int radius = 180;
            final int margin = 4;
            final Transformation transformation = new RoundedCornersTransformation(radius, margin);

            Log.wtf("XXX : ",charChoosedNameList.get(x));
            Log.wtf("YYY : ",Boolean.toString(checkDataIsExist("db/buff/char/"+item_rss.getCharNameByTranslatedName(charChoosedNameList.get(x),context).replace(" ","_")+".json")));
            Log.wtf("ZZZ : ","db/buff/char/"+item_rss.getCharNameByTranslatedName(charChoosedNameList.get(x),context).replace(" ","_")+".json");

            if(charChoosedIsCal.get(x) == true && checkDataIsExist("db/buff/char/"+item_rss.getCharNameByTranslatedName(charChoosedNameList.get(x),context).replace(" ","_")+".json") == true) {
                /** Char */
                Picasso.get()
                        .load(FileLoader.loadIMG(item_rss.getCharByName(charChoosedNameList.get(x),context)[3],context))
                        .transform(transformation)
                        .resize(128, 128)
                        .error(R.drawable.paimon_lost)
                        .into(buff_ui_char_icon);
                buff_ui_char_name.setText(item_rss.getCharByName(charChoosedNameList.get(x),context)[1]);

                /** Weapon */
                for (int y = 0 ; y < weaponChoosedNameList.size() ; y ++){
                    if(weaponChoosedFollowList.get(y).equals(charChoosedNameList.get(x))){
                        tmp_weapon_name = weaponChoosedNameList.get(y);
                    }
                }

                if(!tmp_weapon_name.equals("unknown") && tmp_weapon_name != null){
                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getWeaponByName(tmp_weapon_name,context)[1],context))
                            .transform(transformation)
                            .resize(96, 96)
                            .error(R.drawable.paimon_lost)
                            .into(buff_ui_weapon_icon);
                }

                /** Artifact */

                ArrayList<String> tmp_artifact_name = new ArrayList<String>();
                ArrayList<String> tmp_artifact_type = new ArrayList<String>();
                ArrayList<Integer> tmp_artifact_lvl = new ArrayList<Integer>();
                ArrayList<Integer> tmp_artifact_rare = new ArrayList<Integer>();
                int[] tmp_artifact_id = new int[]{R.id.buff_ui_artifact_icon1,R.id.buff_ui_artifact_icon2,R.id.buff_ui_artifact_icon3,R.id.buff_ui_artifact_icon4,R.id.buff_ui_artifact_icon5};

                for (int y = 0 ; y < artifactChoosedNameList.size() ; y ++){
                    if(artifactChoosedFollowList.get(y).equals(charChoosedNameList.get(x)) && !tmp_artifact_type.contains(artifactChoosedType.get(y))){
                        tmp_artifact_name.add(artifactChoosedNameList.get(y));
                        tmp_artifact_type.add(artifactChoosedType.get(y));
                        tmp_artifact_lvl.add(artifactChoosedAfterLvlList.get(y));
                        tmp_artifact_rare.add(artifactChoosedRare.get(y));
                    }
                }

                for (int y = 0 ; y < tmp_artifact_name.size() && y < 5 ; y ++){
                    int tmp_artifact_type_id = 0;
                    int tmp_artifact_type_img_id = 0;
                    if(tmp_artifact_type.get(y).equals("Flower")){
                        tmp_artifact_type_id = 1;
                        tmp_artifact_type_img_id = 4;
                    }else if(tmp_artifact_type.get(y).equals("Plume")){
                        tmp_artifact_type_id = 2;
                        tmp_artifact_type_img_id = 2;
                    }else if(tmp_artifact_type.get(y).equals("Sand")){
                        tmp_artifact_type_id = 3;
                        tmp_artifact_type_img_id = 5;
                    }else if(tmp_artifact_type.get(y).equals("Goblet")){
                        tmp_artifact_type_id = 4;
                        tmp_artifact_type_img_id = 1;
                    }else if(tmp_artifact_type.get(y).equals("Circlet")){
                        tmp_artifact_type_id = 5;
                        tmp_artifact_type_img_id = 3;
                    }

                    Log.wtf("tmp_artifact_name",tmp_artifact_name.get(y));
                    Log.wtf("tmp_artifact_type_id", String.valueOf(tmp_artifact_type_id));

                    if(tmp_artifact_type_id > 0){
                        ImageView img = view.findViewById(tmp_artifact_id[tmp_artifact_type_id-1]);
                        Picasso.get()
                                .load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(tmp_artifact_name.get(y)),context)[tmp_artifact_type_img_id],context))
                                .transform(transformation)
                                .resize(64, 64)
                                .error(R.drawable.paimon_lost)
                                .into(img);
                    }
                }

                int finalX = x;
                String finalTmp_weapon_name = tmp_weapon_name;
                buff_ui_press_more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.wtf("TT","OK");
                        buffPage(finalX, finalTmp_weapon_name,tmp_artifact_name,tmp_artifact_type,tmp_artifact_lvl,tmp_artifact_rare);
                    }
                });

            }

            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.MATCH_PARENT;
            param.setGravity(Gravity.CENTER_VERTICAL);
            view.setLayoutParams (param);

            if(!tmp_weapon_name.equals("unknown") && tmp_weapon_name != null){
                buff_ui_view.addView(view);
            }

        }

    }

    @SuppressLint("SetTextI18n")
    public void buffPage(int k, String weapon_name, ArrayList<String> artifact_name, ArrayList<String> artifact_type, ArrayList<Integer> artifact_lvl, ArrayList<Integer> artifact_rare){
        /** init */

        buffCal = new BuffCal2();
        /**
         *
         */

        final int radius = 25;
        final int margin = 4;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);

        /** Method */

        charName = charChoosedNameList.get(k);

        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.fragment_buff_page, null);

        BackgroundReload.BackgroundReload(context,view);
        // Char_Info
        buff_char_icon = view.findViewById(R.id.buff_char_icon);
        buff_char_element = view.findViewById(R.id.buff_char_element);
        buff_char_name = view.findViewById(R.id.buff_char_name);
        buff_char_lvl = view.findViewById(R.id.buff_char_lvl);

        // Char_BUFF_Part
        buff_char_atk_value = view.findViewById(R.id.buff_char_atk_value);
        buff_char_hp_value = view.findViewById(R.id.buff_char_hp_value);
        buff_char_def_value = view.findViewById(R.id.buff_char_def_value);
        buff_char_crit_rate_value = view.findViewById(R.id.buff_char_crit_rate_value);
        buff_char_crit_dmg_value = view.findViewById(R.id.buff_char_crit_dmg_value);
        buff_char_enrech_value = view.findViewById(R.id.buff_char_enrech_value);
        buff_char_elemas_value = view.findViewById(R.id.buff_char_elemas_value);
        buff_char_healing_value = view.findViewById(R.id.buff_char_healing_value);
        buff_char_eledmg_pyro_value = view.findViewById(R.id.buff_char_eledmg_pyro_value);
        buff_char_eledmg_hydro_value = view.findViewById(R.id.buff_char_eledmg_hydro_value);
        buff_char_eledmg_anemo_value = view.findViewById(R.id.buff_char_eledmg_anemo_value);
        buff_char_eledmg_electro_value = view.findViewById(R.id.buff_char_eledmg_electro_value);
        buff_char_eledmg_dendor_value = view.findViewById(R.id.buff_char_eledmg_dendor_value);
        buff_char_eledmg_cryo_value = view.findViewById(R.id.buff_char_eledmg_cryo_value);
        buff_char_eledmg_geo_value = view.findViewById(R.id.buff_char_eledmg_geo_value);
        buff_char_phy_value = view.findViewById(R.id.buff_char_phy_value);


        // Weapon_Info
        buff_weapon_icon = view.findViewById(R.id.buff_weapon_icon);
        buff_weapon_name = view.findViewById(R.id.buff_weapon_name);
        buff_weapon_lvl = view.findViewById(R.id.buff_weapon_lvl);

        // Artifact_Icon
        TabLayout buff_artifact_tablayout = view.findViewById(R.id.buff_artifact_tablayout);
        TabItem buff_artifact_tab_flower = view.findViewById(R.id.buff_artifact_tab_flower);
        TabItem buff_artifact_tab_plume = view.findViewById(R.id.buff_artifact_tab_plume);
        TabItem buff_artifact_tab_sand = view.findViewById(R.id.buff_artifact_tab_sand);
        TabItem buff_artifact_tab_goblet = view.findViewById(R.id.buff_artifact_tab_goblet);
        TabItem buff_artifact_tab_circlet = view.findViewById(R.id.buff_artifact_tab_circlet);

        TabLayout buff_buff_tablayout = view.findViewById(R.id.buff_buff_tablayout);

        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String color_hex = sharedPreferences.getString("theme_color_hex","#FF5A5A"); // Must include #

        buff_buff_tablayout.setTabTextColors(context.getResources().getColor(R.color.tv_color), Color.parseColor(color_hex));

        // Artifact_BUFF
        buff_artifact_main_value = view.findViewById(R.id.buff_artifact_main_value);
        buff_artifact_main_name = view.findViewById(R.id.buff_artifact_main_name);
        buff_artifact_main_name_sp = view.findViewById(R.id.buff_artifact_main_name_sp);
        buff_artifact_sec1_value = view.findViewById(R.id.buff_artifact_sec1_value);
        buff_artifact_sec1_name = view.findViewById(R.id.buff_artifact_sec1_name);
        buff_artifact_sec2_value = view.findViewById(R.id.buff_artifact_sec2_value);
        buff_artifact_sec2_name = view.findViewById(R.id.buff_artifact_sec2_name);
        buff_artifact_sec3_value = view.findViewById(R.id.buff_artifact_sec3_value);
        buff_artifact_sec3_name = view.findViewById(R.id.buff_artifact_sec3_name);
        buff_artifact_sec4_value = view.findViewById(R.id.buff_artifact_sec4_value);
        buff_artifact_sec4_name = view.findViewById(R.id.buff_artifact_sec4_name);

        buff_artifact_main = view.findViewById(R.id.buff_artifact_main);
        buff_artifact_sec1 = view.findViewById(R.id.buff_artifact_sec1);
        buff_artifact_sec2 = view.findViewById(R.id.buff_artifact_sec2);
        buff_artifact_sec3 = view.findViewById(R.id.buff_artifact_sec3);
        buff_artifact_sec4 = view.findViewById(R.id.buff_artifact_sec4);

        // Final Display
        TabItem buff_tab_element_reaction = view.findViewById(R.id.buff_tab_element_reaction);
        TabItem buff_tab_normal = view.findViewById(R.id.buff_tab_normal);
        TabItem buff_tab_charged_atk = view.findViewById(R.id.buff_tab_charged_atk);
        TabItem buff_tab_plunging_atk = view.findViewById(R.id.buff_tab_plunging_atk);
        TabItem buff_tab_element = view.findViewById(R.id.buff_tab_element);
        TabItem buff_tab_final = view.findViewById(R.id.buff_tab_final);
        TabItem buff_tab_other = view.findViewById(R.id.buff_tab_other);
        buff_tab_view = view.findViewById(R.id.buff_tab_view);

        // Enemy
        buff_enemy_ll = view.findViewById(R.id.buff_enemy_ll);
        buff_enemy_ico = view.findViewById(R.id.buff_enemy_icon);
        buff_enemy_lvl = view.findViewById(R.id.buff_enemy_lvl);
        buff_enemy_name = view.findViewById(R.id.buff_enemy_name);

        buff_enemy_lvl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.item_enter_lvl, null);

                EditText buff_enemy_lvl_et = view.findViewById(R.id.buff_enemy_lvl_et);
                Button buff_ok = view.findViewById(R.id.buff_ok);

                buff_enemy_lvl_et.setText(String.valueOf(buffCal.LvlEnemy));
                buff_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(Integer.parseInt(buff_enemy_lvl_et.getText().toString()) <= 120 && Integer.parseInt(buff_enemy_lvl_et.getText().toString()) > 0){
                            buffCal.enemy_setup(buffCal.enemyName, Integer.parseInt(buff_enemy_lvl_et.getText().toString()));
                        }else if(Integer.parseInt(buff_enemy_lvl_et.getText().toString()) > 120){
                            buffCal.enemy_setup(buffCal.enemyName,120);
                        }else if(Integer.parseInt(buff_enemy_lvl_et.getText().toString()) <1){
                            buffCal.enemy_setup(buffCal.enemyName,1);
                        }

                        Picasso.get()
                                .load(FileLoader.loadIMG(item_rss.getEnemyByName(buffCal.enemyName,context)[1],context))
                                .transform(transformation)
                                .resize(72, 72)
                                .error(R.drawable.paimon_full)
                                .into(buff_enemy_ico);

                        buff_enemy_lvl.setText(context.getString(R.string.curr_lvl) + prettyCount(buffCal.LvlEnemy,0));
                        buff_enemy_name.setText(item_rss.getEnemyByName(buffCal.enemyName,context)[0]);

                        dialog.dismiss();
                    }
                });

                dialog.setContentView(view);
                dialog.setCanceledOnTouchOutside(true);
                //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.width = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth()*0.9);
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialogWindow.setAttributes(lp);
                dialog.show();
            }
        });

        buff_enemy_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view1 = View.inflate(context, R.layout.fragment_enemy_choose, null);

                ArrayList<Enemys> filteredList = new ArrayList<Enemys>();

                enemy_list_reload();

                enemy_list_setup(view1,enemysList,dialog);

                EditText enemy_et = view1.findViewById(R.id.enemy_et);
                enemy_et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        filteredList.clear();
                        int x = 0;
                        for (Enemys item : enemysList) {
                            String str = String.valueOf(s).toLowerCase();
                            if (item_rss.getEnemyByName(item.getName(),context)[0].contains(str)||item_rss.getCharByName(item.getName(),context)[0].toLowerCase().contains(str)||item_rss.getCharByName(item.getName(),context)[0].toUpperCase().contains(str)||item.getName().toLowerCase().contains(str)){ // EN -> ZH
                                filteredList.add(item);
                            }
                            x = x +1;
                        }
                        enemy_list_setup(view1,filteredList,dialog);
                    }
                });

                ImageView enemy_filter = view1.findViewById(R.id.enemy_filter);
                enemy_filter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                        View view = View.inflate(context, R.layout.menu_enemy_filter, null);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        // Tpes
                        CheckBox type1 	= view.findViewById(R.id.enemy_cb1);
                        CheckBox type2	= view.findViewById(R.id.enemy_cb2);
                        CheckBox type3	= view.findViewById(R.id.enemy_cb3);
                        CheckBox type4	= view.findViewById(R.id.enemy_cb4);
                        CheckBox type5	= view.findViewById(R.id.enemy_cb5);
                        CheckBox type6	= view.findViewById(R.id.enemy_cb6);
                        CheckBox type7	= view.findViewById(R.id.enemy_cb7);
                        CheckBox type8	= view.findViewById(R.id.enemy_cb8);
                        CheckBox type9	= view.findViewById(R.id.enemy_cb9);
                        CheckBox type10	= view.findViewById(R.id.enemy_cb10);
                        CheckBox type11	= view.findViewById(R.id.enemy_cb11);
                        CheckBox type12	= view.findViewById(R.id.enemy_cb12);
                        CheckBox type13	= view.findViewById(R.id.enemy_cb13);
                        CheckBox type14	= view.findViewById(R.id.enemy_cb14);
                        CheckBox type15	= view.findViewById(R.id.enemy_cb15);


                        // Function Buttons
                        Button cancel = view.findViewById(R.id.menu_cancel);
                        Button reset = view.findViewById(R.id.menu_reset);
                        Button ok = view.findViewById(R.id.menu_ok);

                        show_enemy_t1 	= sharedPreferences.getBoolean("show_enemy_t1",true);
                        show_enemy_t2	= sharedPreferences.getBoolean("show_enemy_t2",true);
                        show_enemy_t3	= sharedPreferences.getBoolean("show_enemy_t3",true);
                        show_enemy_t4	= sharedPreferences.getBoolean("show_enemy_t4",true);
                        show_enemy_t5	= sharedPreferences.getBoolean("show_enemy_t5",true);
                        show_enemy_t6	= sharedPreferences.getBoolean("show_enemy_t6",true);
                        show_enemy_t7	= sharedPreferences.getBoolean("show_enemy_t7",true);
                        show_enemy_t8	= sharedPreferences.getBoolean("show_enemy_t8",true);
                        show_enemy_t9	= sharedPreferences.getBoolean("show_enemy_t9",true);
                        show_enemy_t10	= sharedPreferences.getBoolean("show_enemy_t10",true);
                        show_enemy_t11	= sharedPreferences.getBoolean("show_enemy_t11",true);
                        show_enemy_t12	= sharedPreferences.getBoolean("show_enemy_t12",true);
                        show_enemy_t13	= sharedPreferences.getBoolean("show_enemy_t13",true);
                        show_enemy_t14	= sharedPreferences.getBoolean("show_enemy_t14",true);
                        show_enemy_t15	= sharedPreferences.getBoolean("show_enemy_t15",true);

                        if(show_enemy_t1)	{type1.setChecked(true);}
                        if(show_enemy_t2)	{type2.setChecked(true);}
                        if(show_enemy_t3)	{type3.setChecked(true);}
                        if(show_enemy_t4)	{type4.setChecked(true);}
                        if(show_enemy_t5)	{type5.setChecked(true);}
                        if(show_enemy_t6)	{type6.setChecked(true);}
                        if(show_enemy_t7)	{type7.setChecked(true);}
                        if(show_enemy_t8)	{type8.setChecked(true);}
                        if(show_enemy_t9)	{type9.setChecked(true);}
                        if(show_enemy_t10)	{type10.setChecked(true);}
                        if(show_enemy_t11)	{type11.setChecked(true);}
                        if(show_enemy_t12)	{type12.setChecked(true);}
                        if(show_enemy_t13)	{type13.setChecked(true);}
                        if(show_enemy_t14)	{type14.setChecked(true);}
                        if(show_enemy_t15)	{type15.setChecked(true);}

                        type1.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type1.isChecked()){	show_enemy_t1 = true;}	else{show_enemy_t1 = false;}}});
                        type2.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type2.isChecked()){	show_enemy_t2 = true;}	else{show_enemy_t2 = false;}}});
                        type3.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type3.isChecked()){	show_enemy_t3 = true;}	else{show_enemy_t3 = false;}}});
                        type4.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type4.isChecked()){	show_enemy_t4 = true;}	else{show_enemy_t4 = false;}}});
                        type5.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type5.isChecked()){	show_enemy_t5 = true;}	else{show_enemy_t5 = false;}}});
                        type6.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type6.isChecked()){	show_enemy_t6 = true;}	else{show_enemy_t6 = false;}}});
                        type7.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type7.isChecked()){	show_enemy_t7 = true;}	else{show_enemy_t7 = false;}}});
                        type8.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type8.isChecked()){	show_enemy_t8 = true;}	else{show_enemy_t8 = false;}}});
                        type9.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type9.isChecked()){	show_enemy_t9 = true;}	else{show_enemy_t9 = false;}}});
                        type10.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type10.isChecked()){	show_enemy_t10 = true;}	else{show_enemy_t10 = false;}}});
                        type11.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type11.isChecked()){	show_enemy_t11 = true;}	else{show_enemy_t11 = false;}}});
                        type12.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type12.isChecked()){	show_enemy_t12 = true;}	else{show_enemy_t12 = false;}}});
                        type13.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type13.isChecked()){	show_enemy_t13 = true;}	else{show_enemy_t13 = false;}}});
                        type14.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type14.isChecked()){	show_enemy_t14 = true;}	else{show_enemy_t14 = false;}}});
                        type15.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type15.isChecked()){	show_enemy_t15 = true;}	else{show_enemy_t15 = false;}}});

                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        reset.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                show_enemy_t1 = true;
                                show_enemy_t2 = true;
                                show_enemy_t3 = true;
                                show_enemy_t4 = true;
                                show_enemy_t5 = true;
                                show_enemy_t6 = true;
                                show_enemy_t7 = true;
                                show_enemy_t8 = true;
                                show_enemy_t9 = true;
                                show_enemy_t10 = true;
                                show_enemy_t11 = true;
                                show_enemy_t12 = true;
                                show_enemy_t13 = true;
                                show_enemy_t14 = true;
                                show_enemy_t15 = true;

                                gridLayout.removeAllViews();
                                editor.putBoolean("show_enemy_t1"	,show_enemy_t1);
                                editor.putBoolean("show_enemy_t2"	,show_enemy_t2);
                                editor.putBoolean("show_enemy_t3"	,show_enemy_t3);
                                editor.putBoolean("show_enemy_t4"	,show_enemy_t4);
                                editor.putBoolean("show_enemy_t5"	,show_enemy_t5);
                                editor.putBoolean("show_enemy_t6"	,show_enemy_t6);
                                editor.putBoolean("show_enemy_t7"	,show_enemy_t7);
                                editor.putBoolean("show_enemy_t8"	,show_enemy_t8);
                                editor.putBoolean("show_enemy_t9"	,show_enemy_t9);
                                editor.putBoolean("show_enemy_t10"	,show_enemy_t10);
                                editor.putBoolean("show_enemy_t11"	,show_enemy_t11);
                                editor.putBoolean("show_enemy_t12"	,show_enemy_t12);
                                editor.putBoolean("show_enemy_t13"	,show_enemy_t13);
                                editor.putBoolean("show_enemy_t14"	,show_enemy_t14);
                                editor.putBoolean("show_enemy_t15"	,show_enemy_t15);
                                editor.apply();

                                type1.setChecked(true);
                                type2.setChecked(true);
                                type3.setChecked(true);
                                type4.setChecked(true);
                                type5.setChecked(true);
                                type6.setChecked(true);
                                type7.setChecked(true);
                                type8.setChecked(true);
                                type9.setChecked(true);
                                type10.setChecked(true);
                                type11.setChecked(true);
                                type12.setChecked(true);
                                type13.setChecked(true);
                                type14.setChecked(true);
                                type15.setChecked(true);


                                enemy_list_setup(view1,filteredList,dialog);
                                dialog.dismiss();


                            }
                        });

                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ArrayList<Enemys> filteredList = new ArrayList<>();
                                for (Enemys item : enemysList) {
                                    if(show_enemy_t1 && item.getTypeId() == 0 ||show_enemy_t2&& item.getTypeId() == 1 ||show_enemy_t3&& item.getTypeId() == 2 ||show_enemy_t4&& item.getTypeId() == 3 ||show_enemy_t5&& item.getTypeId() == 4 ||show_enemy_t6&& item.getTypeId() == 5 ||show_enemy_t7&& item.getTypeId() == 6 ||show_enemy_t8&& item.getTypeId() == 7 ||show_enemy_t9&& item.getTypeId() == 8 ||show_enemy_t10&& item.getTypeId() == 9 ||show_enemy_t11&& item.getTypeId() == 10 ||show_enemy_t12&& item.getTypeId() == 11 ||show_enemy_t13&& item.getTypeId() == 12 ||show_enemy_t14&& item.getTypeId() == 100 ||show_enemy_t15&& item.getTypeId() == 200 ){
                                        filteredList.add(item);
                                    }
                                }

                                gridLayout.removeAllViews();
                                editor.putBoolean("show_enemy_t1"	,show_enemy_t1);
                                editor.putBoolean("show_enemy_t2"	,show_enemy_t2);
                                editor.putBoolean("show_enemy_t3"	,show_enemy_t3);
                                editor.putBoolean("show_enemy_t4"	,show_enemy_t4);
                                editor.putBoolean("show_enemy_t5"	,show_enemy_t5);
                                editor.putBoolean("show_enemy_t6"	,show_enemy_t6);
                                editor.putBoolean("show_enemy_t7"	,show_enemy_t7);
                                editor.putBoolean("show_enemy_t8"	,show_enemy_t8);
                                editor.putBoolean("show_enemy_t9"	,show_enemy_t9);
                                editor.putBoolean("show_enemy_t10"	,show_enemy_t10);
                                editor.putBoolean("show_enemy_t11"	,show_enemy_t11);
                                editor.putBoolean("show_enemy_t12"	,show_enemy_t12);
                                editor.putBoolean("show_enemy_t13"	,show_enemy_t13);
                                editor.putBoolean("show_enemy_t14"	,show_enemy_t14);
                                editor.putBoolean("show_enemy_t15"	,show_enemy_t15);
                                editor.apply();

                                enemy_list_setup(view1,filteredList,dialog);

                                dialog.dismiss();
                            }
                        });

                        dialog.setContentView(view);
                        dialog.setCanceledOnTouchOutside(true);
                        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
                        Window dialogWindow = dialog.getWindow();
                        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                        lp.width = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth());
                        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                        lp.gravity = Gravity.BOTTOM;
                        dialogWindow.setAttributes(lp);
                        dialog.show();
                    }
                });


                dialog.setContentView(view1);
                dialog.setCanceledOnTouchOutside(true);
                //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.width = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth());
                lp.height = (int) (ScreenSizeUtils.getInstance(context).getScreenHeight());
                lp.gravity = Gravity.BOTTOM;
                dialogWindow.setAttributes(lp);
                dialog.show();
            }

        });

        buff_enemy_ico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view1 = View.inflate(context, R.layout.fragment_enemy_choose, null);

                ArrayList<Enemys> filteredList = new ArrayList<Enemys>();

                enemy_list_reload();

                enemy_list_setup(view1,enemysList,dialog);

                EditText enemy_et = view1.findViewById(R.id.enemy_et);
                enemy_et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        filteredList.clear();
                        int x = 0;
                        for (Enemys item : enemysList) {
                            String str = String.valueOf(s).toLowerCase();
                            if (item_rss.getEnemyByName(item.getName(),context)[0].contains(str)||item_rss.getCharByName(item.getName(),context)[0].toLowerCase().contains(str)||item_rss.getCharByName(item.getName(),context)[0].toUpperCase().contains(str)||item.getName().toLowerCase().contains(str)){ // EN -> ZH
                                filteredList.add(item);
                            }
                            x = x +1;
                        }
                        enemy_list_setup(view1,filteredList,dialog);
                    }
                });

                ImageView enemy_filter = view1.findViewById(R.id.enemy_filter);
                enemy_filter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                        View view = View.inflate(context, R.layout.menu_enemy_filter, null);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        // Tpes
                        CheckBox type1 	= view.findViewById(R.id.enemy_cb1);
                        CheckBox type2	= view.findViewById(R.id.enemy_cb2);
                        CheckBox type3	= view.findViewById(R.id.enemy_cb3);
                        CheckBox type4	= view.findViewById(R.id.enemy_cb4);
                        CheckBox type5	= view.findViewById(R.id.enemy_cb5);
                        CheckBox type6	= view.findViewById(R.id.enemy_cb6);
                        CheckBox type7	= view.findViewById(R.id.enemy_cb7);
                        CheckBox type8	= view.findViewById(R.id.enemy_cb8);
                        CheckBox type9	= view.findViewById(R.id.enemy_cb9);
                        CheckBox type10	= view.findViewById(R.id.enemy_cb10);
                        CheckBox type11	= view.findViewById(R.id.enemy_cb11);
                        CheckBox type12	= view.findViewById(R.id.enemy_cb12);
                        CheckBox type13	= view.findViewById(R.id.enemy_cb13);
                        CheckBox type14	= view.findViewById(R.id.enemy_cb14);
                        CheckBox type15	= view.findViewById(R.id.enemy_cb15);


                        // Function Buttons
                        Button cancel = view.findViewById(R.id.menu_cancel);
                        Button reset = view.findViewById(R.id.menu_reset);
                        Button ok = view.findViewById(R.id.menu_ok);

                        show_enemy_t1 	= sharedPreferences.getBoolean("show_enemy_t1",true);
                        show_enemy_t2	= sharedPreferences.getBoolean("show_enemy_t2",true);
                        show_enemy_t3	= sharedPreferences.getBoolean("show_enemy_t3",true);
                        show_enemy_t4	= sharedPreferences.getBoolean("show_enemy_t4",true);
                        show_enemy_t5	= sharedPreferences.getBoolean("show_enemy_t5",true);
                        show_enemy_t6	= sharedPreferences.getBoolean("show_enemy_t6",true);
                        show_enemy_t7	= sharedPreferences.getBoolean("show_enemy_t7",true);
                        show_enemy_t8	= sharedPreferences.getBoolean("show_enemy_t8",true);
                        show_enemy_t9	= sharedPreferences.getBoolean("show_enemy_t9",true);
                        show_enemy_t10	= sharedPreferences.getBoolean("show_enemy_t10",true);
                        show_enemy_t11	= sharedPreferences.getBoolean("show_enemy_t11",true);
                        show_enemy_t12	= sharedPreferences.getBoolean("show_enemy_t12",true);
                        show_enemy_t13	= sharedPreferences.getBoolean("show_enemy_t13",true);
                        show_enemy_t14	= sharedPreferences.getBoolean("show_enemy_t14",true);
                        show_enemy_t15	= sharedPreferences.getBoolean("show_enemy_t15",true);

                        if(show_enemy_t1)	{type1.setChecked(true);}
                        if(show_enemy_t2)	{type2.setChecked(true);}
                        if(show_enemy_t3)	{type3.setChecked(true);}
                        if(show_enemy_t4)	{type4.setChecked(true);}
                        if(show_enemy_t5)	{type5.setChecked(true);}
                        if(show_enemy_t6)	{type6.setChecked(true);}
                        if(show_enemy_t7)	{type7.setChecked(true);}
                        if(show_enemy_t8)	{type8.setChecked(true);}
                        if(show_enemy_t9)	{type9.setChecked(true);}
                        if(show_enemy_t10)	{type10.setChecked(true);}
                        if(show_enemy_t11)	{type11.setChecked(true);}
                        if(show_enemy_t12)	{type12.setChecked(true);}
                        if(show_enemy_t13)	{type13.setChecked(true);}
                        if(show_enemy_t14)	{type14.setChecked(true);}
                        if(show_enemy_t15)	{type15.setChecked(true);}

                        type1.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type1.isChecked()){	show_enemy_t1 = true;}	else{show_enemy_t1 = false;}}});
                        type2.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type2.isChecked()){	show_enemy_t2 = true;}	else{show_enemy_t2 = false;}}});
                        type3.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type3.isChecked()){	show_enemy_t3 = true;}	else{show_enemy_t3 = false;}}});
                        type4.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type4.isChecked()){	show_enemy_t4 = true;}	else{show_enemy_t4 = false;}}});
                        type5.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type5.isChecked()){	show_enemy_t5 = true;}	else{show_enemy_t5 = false;}}});
                        type6.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type6.isChecked()){	show_enemy_t6 = true;}	else{show_enemy_t6 = false;}}});
                        type7.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type7.isChecked()){	show_enemy_t7 = true;}	else{show_enemy_t7 = false;}}});
                        type8.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type8.isChecked()){	show_enemy_t8 = true;}	else{show_enemy_t8 = false;}}});
                        type9.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type9.isChecked()){	show_enemy_t9 = true;}	else{show_enemy_t9 = false;}}});
                        type10.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type10.isChecked()){	show_enemy_t10 = true;}	else{show_enemy_t10 = false;}}});
                        type11.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type11.isChecked()){	show_enemy_t11 = true;}	else{show_enemy_t11 = false;}}});
                        type12.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type12.isChecked()){	show_enemy_t12 = true;}	else{show_enemy_t12 = false;}}});
                        type13.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type13.isChecked()){	show_enemy_t13 = true;}	else{show_enemy_t13 = false;}}});
                        type14.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type14.isChecked()){	show_enemy_t14 = true;}	else{show_enemy_t14 = false;}}});
                        type15.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {	if (type15.isChecked()){	show_enemy_t15 = true;}	else{show_enemy_t15 = false;}}});

                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        reset.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                show_enemy_t1 = true;
                                show_enemy_t2 = true;
                                show_enemy_t3 = true;
                                show_enemy_t4 = true;
                                show_enemy_t5 = true;
                                show_enemy_t6 = true;
                                show_enemy_t7 = true;
                                show_enemy_t8 = true;
                                show_enemy_t9 = true;
                                show_enemy_t10 = true;
                                show_enemy_t11 = true;
                                show_enemy_t12 = true;
                                show_enemy_t13 = true;
                                show_enemy_t14 = true;
                                show_enemy_t15 = true;

                                gridLayout.removeAllViews();
                                editor.putBoolean("show_enemy_t1"	,show_enemy_t1);
                                editor.putBoolean("show_enemy_t2"	,show_enemy_t2);
                                editor.putBoolean("show_enemy_t3"	,show_enemy_t3);
                                editor.putBoolean("show_enemy_t4"	,show_enemy_t4);
                                editor.putBoolean("show_enemy_t5"	,show_enemy_t5);
                                editor.putBoolean("show_enemy_t6"	,show_enemy_t6);
                                editor.putBoolean("show_enemy_t7"	,show_enemy_t7);
                                editor.putBoolean("show_enemy_t8"	,show_enemy_t8);
                                editor.putBoolean("show_enemy_t9"	,show_enemy_t9);
                                editor.putBoolean("show_enemy_t10"	,show_enemy_t10);
                                editor.putBoolean("show_enemy_t11"	,show_enemy_t11);
                                editor.putBoolean("show_enemy_t12"	,show_enemy_t12);
                                editor.putBoolean("show_enemy_t13"	,show_enemy_t13);
                                editor.putBoolean("show_enemy_t14"	,show_enemy_t14);
                                editor.putBoolean("show_enemy_t15"	,show_enemy_t15);
                                editor.apply();

                                type1.setChecked(true);
                                type2.setChecked(true);
                                type3.setChecked(true);
                                type4.setChecked(true);
                                type5.setChecked(true);
                                type6.setChecked(true);
                                type7.setChecked(true);
                                type8.setChecked(true);
                                type9.setChecked(true);
                                type10.setChecked(true);
                                type11.setChecked(true);
                                type12.setChecked(true);
                                type13.setChecked(true);
                                type14.setChecked(true);
                                type15.setChecked(true);


                                enemy_list_setup(view1,filteredList,dialog);
                                dialog.dismiss();


                            }
                        });

                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ArrayList<Enemys> filteredList = new ArrayList<>();
                                for (Enemys item : enemysList) {
                                    if(show_enemy_t1 && item.getTypeId() == 0 ||show_enemy_t2&& item.getTypeId() == 1 ||show_enemy_t3&& item.getTypeId() == 2 ||show_enemy_t4&& item.getTypeId() == 3 ||show_enemy_t5&& item.getTypeId() == 4 ||show_enemy_t6&& item.getTypeId() == 5 ||show_enemy_t7&& item.getTypeId() == 6 ||show_enemy_t8&& item.getTypeId() == 7 ||show_enemy_t9&& item.getTypeId() == 8 ||show_enemy_t10&& item.getTypeId() == 9 ||show_enemy_t11&& item.getTypeId() == 10 ||show_enemy_t12&& item.getTypeId() == 11 ||show_enemy_t13&& item.getTypeId() == 12 ||show_enemy_t14&& item.getTypeId() == 100 ||show_enemy_t15&& item.getTypeId() == 200 ){
                                        filteredList.add(item);
                                    }
                                }

                                gridLayout.removeAllViews();
                                editor.putBoolean("show_enemy_t1"	,show_enemy_t1);
                                editor.putBoolean("show_enemy_t2"	,show_enemy_t2);
                                editor.putBoolean("show_enemy_t3"	,show_enemy_t3);
                                editor.putBoolean("show_enemy_t4"	,show_enemy_t4);
                                editor.putBoolean("show_enemy_t5"	,show_enemy_t5);
                                editor.putBoolean("show_enemy_t6"	,show_enemy_t6);
                                editor.putBoolean("show_enemy_t7"	,show_enemy_t7);
                                editor.putBoolean("show_enemy_t8"	,show_enemy_t8);
                                editor.putBoolean("show_enemy_t9"	,show_enemy_t9);
                                editor.putBoolean("show_enemy_t10"	,show_enemy_t10);
                                editor.putBoolean("show_enemy_t11"	,show_enemy_t11);
                                editor.putBoolean("show_enemy_t12"	,show_enemy_t12);
                                editor.putBoolean("show_enemy_t13"	,show_enemy_t13);
                                editor.putBoolean("show_enemy_t14"	,show_enemy_t14);
                                editor.putBoolean("show_enemy_t15"	,show_enemy_t15);
                                editor.apply();

                                enemy_list_setup(view1,filteredList,dialog);

                                dialog.dismiss();
                            }
                        });

                        dialog.setContentView(view);
                        dialog.setCanceledOnTouchOutside(true);
                        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
                        Window dialogWindow = dialog.getWindow();
                        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                        lp.width = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth());
                        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                        lp.gravity = Gravity.BOTTOM;
                        dialogWindow.setAttributes(lp);
                        dialog.show();
                    }
                });


                dialog.setContentView(view1);
                dialog.setCanceledOnTouchOutside(true);
                //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.width = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth());
                lp.height = (int) (ScreenSizeUtils.getInstance(context).getScreenHeight());
                lp.gravity = Gravity.BOTTOM;
                dialogWindow.setAttributes(lp);
                dialog.show();
            }

        });

        /** CHAR_INFO_SET_DATA*/
        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getCharByName(charName,context)[3],context ))
                .transform(transformation)
                .fit()
                .error (R.drawable.paimon_full)
                .into (buff_char_icon);

        String name ,element = "Anemo", weapon = "Sword";

        String json_base = LoadData("db/char/char_list.json");
        //Get data from JSON

        try {
            JSONArray array = new JSONArray(json_base);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                name = object.getString("name");
                if(name.equals(charName)){
                    element = object.getString("element");
                    weapon = object.getString("weapon");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        switch (element) {
            case "Anemo": buff_char_element.setImageResource(R.drawable.anemo);buff_char_icon.setBackgroundResource(R.drawable.bg_anemo_bg);charElement = buffCal.Anemo;break;
            case "Cryo": buff_char_element.setImageResource(R.drawable.cryo);buff_char_icon.setBackgroundResource(R.drawable.bg_cryo_bg);charElement = buffCal.Cryo;break;
            case "Electro": buff_char_element.setImageResource(R.drawable.electro);buff_char_icon.setBackgroundResource(R.drawable.bg_electro_bg);charElement = buffCal.Electro;break;
            case "Geo": buff_char_element.setImageResource(R.drawable.geo);buff_char_icon.setBackgroundResource(R.drawable.bg_geo_bg);charElement = buffCal.Geo;break;
            case "Hydro": buff_char_element.setImageResource(R.drawable.hydro);buff_char_icon.setBackgroundResource(R.drawable.bg_anemo_char);charElement = buffCal.Hydro;break;
            case "Pyro": buff_char_element.setImageResource(R.drawable.pyro);buff_char_icon.setBackgroundResource(R.drawable.bg_pyro_bg);charElement = buffCal.Pyro;break;
            case "Dendro": buff_char_element.setImageResource(R.drawable.dendro);buff_char_icon.setBackgroundResource(R.drawable.bg_dendro_bg);charElement = buffCal.Dendro;break;
        }

        charWeapon = weapon;

        buff_char_name.setText(item_rss.getCharByName(charName,context)[1]);
        buff_char_lvl.setText(context.getString(R.string.curr_lvl)+" "+String.valueOf(charChoosedAfterLvlList.get(k)));


        /** WEAPON_INFO_SET_DATA*/

        Log.wtf("HE::","XXX"+weapon_name);

        if(!weapon_name.equals("unknown")) {

            Picasso.get()
                    .load(FileLoader.loadIMG(item_rss.getWeaponByName(weapon_name,context)[1],context))
                    .transform(transformation)
                    .fit()
                    .error(R.drawable.paimon_full)
                    .into(buff_weapon_icon);

            buff_weapon_name.setText(item_rss.getWeaponByName(weapon_name,context)[0]);
            buff_weapon_lvl.setText(context.getString(R.string.aim_lvl) + " " + weaponChoosedAfterLvlList.get(k));
        }

        /** ARTIFACT_ICON_SET_DATA*/

        // 0     , 1    , 2   , 3     , 4
        // Flower, Plume, Sand, Goblet, Circlet
        tmp_artifact_sort = new String[5];
        tmp_artifact_lvl = new int[5];
        tmp_artifact_order = new int[] {4,2,5,1,3};
        tmp_artifact_rare = new int[5];

        for (int x =0 ; x < artifact_type.size() ; x ++){
            switch (artifact_type.get(x)) {
                case "Flower" : tmp_artifact_sort[0] = artifact_name.get(x); tmp_artifact_lvl[0] = artifact_lvl.get(x); tmp_artifact_rare[0] = artifact_rare.get(x); break;
                case "Plume" : tmp_artifact_sort[1] = artifact_name.get(x); tmp_artifact_lvl[1] = artifact_lvl.get(x); tmp_artifact_rare[1] = artifact_rare.get(x); break;
                case "Sand" : tmp_artifact_sort[2] = artifact_name.get(x); tmp_artifact_lvl[2] = artifact_lvl.get(x); tmp_artifact_rare[2] = artifact_rare.get(x); break;
                case "Goblet" : tmp_artifact_sort[3] = artifact_name.get(x); tmp_artifact_lvl[3] = artifact_lvl.get(x); tmp_artifact_rare[3] = artifact_rare.get(x); break;
                case "Circlet" : tmp_artifact_sort[4] = artifact_name.get(x); tmp_artifact_lvl[4] = artifact_lvl.get(x); tmp_artifact_rare[4] = artifact_rare.get(x); break;
            }

        }
        for (int x = 0 ; x < 5 ; x ++) {
            if (tmp_artifact_sort[x] != null) {
                buff_artifact_tablayout.getTabAt(x).setIcon(FileLoader.loadIMG2Drawable(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(tmp_artifact_sort[x]),context)[tmp_artifact_order[x]],context));
                buff_artifact_tablayout.getTabAt(x).setId(100+x);
            }
        }

        // Get Artifact Set & Character Weapon Type

        artifactList = new String[4];
        artifactList[0] = "N/A";
        artifactList[1] = "N/A";
        artifactList[2] = "N/A";
        artifactList[3] = "N/A";

        Set<String> set = new HashSet<>();
        ArrayList<String> duplicates = new ArrayList<String>();
        for (String s : tmp_artifact_sort) {
            if (!set.add(s)) {
                duplicates.add(s);
            }
        }

        List<String> list = new ArrayList<String>();
        for (int x = 0 ; x < 5 ; x++){
            list.add(tmp_artifact_sort[x]);
        }

        List<String> checkedList = new ArrayList<String>();
        for (int x = 0 ; x < duplicates.size() && x < 5 ; x++){
            if (duplicates.get(x) != null && !duplicates.get(x).equals("")) {
                if (Collections.frequency(list, duplicates.get(x)) >= 2 && Collections.frequency(list, duplicates.get(x)) < 4 && !checkedList.contains(duplicates.get(x))) {
                    if (artifactList[0].equals("N/A")) {
                        artifactList[0] = duplicates.get(x);
                    } else if (artifactList[2].equals("N/A")) {
                        artifactList[2] = duplicates.get(x);
                    }
                } else if (Collections.frequency(list, duplicates.get(x)) >= 4 && !checkedList.contains(duplicates.get(x))) {
                    if (artifactList[1].equals("N/A")) {
                        artifactList[1] = duplicates.get(x);
                    }
                }
            }
        }

        String base = LoadData("db/char/char_list.json");
        try {
            JSONArray array = new JSONArray(base);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String t_name = object.getString("name");
                if(t_name.equals(charName)){
                    artifactList[3] = object.getString("weapon");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SharedPreferences sharedPreferencesB = context.getSharedPreferences("buff_list",Context.MODE_PRIVATE);

        artifactBuffMainItem = new ArrayList<>(5);
        artifactBuffMainValue = new ArrayList<>(5);
        artifactBuffSec1Item = new ArrayList<>(5);
        artifactBuffSec1Value = new ArrayList<>(5);
        artifactBuffSec2Item = new ArrayList<>(5);
        artifactBuffSec2Value = new ArrayList<>(5);
        artifactBuffSec3Item = new ArrayList<>(5);
        artifactBuffSec3Value = new ArrayList<>(5);
        artifactBuffSec4Item = new ArrayList<>(5);
        artifactBuffSec4Value = new ArrayList<>(5);

        artifactBuffSec1ItemTMP = new ArrayList<>(5);
        artifactBuffSec1ValueTMP = new ArrayList<>(5);
        artifactBuffSec2ItemTMP = new ArrayList<>(5);
        artifactBuffSec2ValueTMP = new ArrayList<>(5);
        artifactBuffSec3ItemTMP = new ArrayList<>(5);
        artifactBuffSec3ValueTMP = new ArrayList<>(5);
        artifactBuffSec4ItemTMP = new ArrayList<>(5);
        artifactBuffSec4ValueTMP = new ArrayList<>(5);

        for (int x = 0 ; x < 5 ; x++){
            artifactBuffMainItem.add("");
            artifactBuffSec1Item.add("");
            artifactBuffSec2Item.add("");
            artifactBuffSec3Item.add("");
            artifactBuffSec4Item.add("");

            artifactBuffMainValue.add(0.0);
            artifactBuffSec1Value.add(0.0);
            artifactBuffSec2Value.add(0.0);
            artifactBuffSec3Value.add(0.0);
            artifactBuffSec4Value.add(0.0);

            artifactBuffSec1ItemTMP.add("");
            artifactBuffSec2ItemTMP.add("");
            artifactBuffSec3ItemTMP.add("");
            artifactBuffSec4ItemTMP.add("");

            artifactBuffSec1ValueTMP.add(0.0);
            artifactBuffSec2ValueTMP.add(0.0);
            artifactBuffSec3ValueTMP.add(0.0);
            artifactBuffSec4ValueTMP.add(0.0);

        }

        for (int  i = 0 ; i < 5 ; i++){
            if(tmp_artifact_sort[i] != null && !tmp_artifact_sort[i].equals("")){
                artifactBuffMainItem.set(i,sharedPreferencesB.getString(dataSheetName+"_"+"artifactBuffMainItem"+String.valueOf(i),""));
                artifactBuffSec1Item.set(i,sharedPreferencesB.getString(dataSheetName+"_"+"artifactBuffSec1Item"+String.valueOf(i),""));
                artifactBuffSec2Item.set(i,sharedPreferencesB.getString(dataSheetName+"_"+"artifactBuffSec2Item"+String.valueOf(i),""));
                artifactBuffSec3Item.set(i,sharedPreferencesB.getString(dataSheetName+"_"+"artifactBuffSec3Item"+String.valueOf(i),""));
                artifactBuffSec4Item.set(i,sharedPreferencesB.getString(dataSheetName+"_"+"artifactBuffSec4Item"+String.valueOf(i),""));

                artifactBuffMainValue.set(i,Double.parseDouble(sharedPreferencesB.getString(dataSheetName+"_"+"artifactBuffMainValue"+String.valueOf(i),"0.0")));
                artifactBuffSec1Value.set(i,Double.parseDouble(sharedPreferencesB.getString(dataSheetName+"_"+"artifactBuffSec1Value"+String.valueOf(i),"0.0")));
                artifactBuffSec2Value.set(i,Double.parseDouble(sharedPreferencesB.getString(dataSheetName+"_"+"artifactBuffSec2Value"+String.valueOf(i),"0.0")));
                artifactBuffSec3Value.set(i,Double.parseDouble(sharedPreferencesB.getString(dataSheetName+"_"+"artifactBuffSec3Value"+String.valueOf(i),"0.0")));
                artifactBuffSec4Value.set(i,Double.parseDouble(sharedPreferencesB.getString(dataSheetName+"_"+"artifactBuffSec4Value"+String.valueOf(i),"0.0")));

                artifactBuffSec1ItemTMP.set(i,sharedPreferencesB.getString(dataSheetName+"_"+"artifactBuffSec1Item"+String.valueOf(i),""));
                artifactBuffSec2ItemTMP.set(i,sharedPreferencesB.getString(dataSheetName+"_"+"artifactBuffSec2Item"+String.valueOf(i),""));
                artifactBuffSec3ItemTMP.set(i,sharedPreferencesB.getString(dataSheetName+"_"+"artifactBuffSec3Item"+String.valueOf(i),""));
                artifactBuffSec4ItemTMP.set(i,sharedPreferencesB.getString(dataSheetName+"_"+"artifactBuffSec4Item"+String.valueOf(i),""));

                artifactBuffSec1ValueTMP.set(i,Double.parseDouble(sharedPreferencesB.getString(dataSheetName+"_"+"artifactBuffSec1Value"+String.valueOf(i),"0.0")));
                artifactBuffSec2ValueTMP.set(i,Double.parseDouble(sharedPreferencesB.getString(dataSheetName+"_"+"artifactBuffSec2Value"+String.valueOf(i),"0.0")));
                artifactBuffSec3ValueTMP.set(i,Double.parseDouble(sharedPreferencesB.getString(dataSheetName+"_"+"artifactBuffSec3Value"+String.valueOf(i),"0.0")));
                artifactBuffSec4ValueTMP.set(i,Double.parseDouble(sharedPreferencesB.getString(dataSheetName+"_"+"artifactBuffSec4Value"+String.valueOf(i),"0.0")));
            }

        }


        ArtifactBuffView("Flower",tmp_artifact_sort,view);

        buff_artifact_tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getId()){
                    case 100 : ArtifactBuffView("Flower",tmp_artifact_sort,view);break;
                    case 101 : ArtifactBuffView("Plume",tmp_artifact_sort,view);break;
                    case 102 : ArtifactBuffView("Sand",tmp_artifact_sort,view);break;
                    case 103 : ArtifactBuffView("Goblet",tmp_artifact_sort,view);break;
                    case 104 : ArtifactBuffView("Circlet",tmp_artifact_sort,view);break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        /**
         * Calculate Part -> new class
         */

        buffCal.setup(context);
        buffCal.char_setup(charName,charChoosedAfterLvlList.get(k),charChoosedAfterBreakLvlList.get(k),charChoosedAfterBreakUPLvlList.get(k),charChoosedAfterSkill1LvlList.get(k),charChoosedAfterSkill2LvlList.get(k),charChoosedAfterSkill3LvlList.get(k));
        buffCal.weapon_setup(weaponChoosedNameList.get(k),weaponChoosedAfterLvlList.get(k),weaponChoosedAfterBreakLvlList.get(k),weaponChoosedAfterBreakUPLvlList.get(k));
        buffCal.artifact_setup(tmp_artifact_sort,tmp_artifact_lvl,artifactBuffMainValue,artifactBuffMainItem,artifactBuffSec1Value,artifactBuffSec1Item,artifactBuffSec2Value,artifactBuffSec2Item,artifactBuffSec3Value,artifactBuffSec3Item,artifactBuffSec4Value,artifactBuffSec4Item);
        buffCal.startReading();

        /**
         * Feedback -> UI Setting
         */

        sharedPreferencesB = context.getSharedPreferences("buff_list",Context.MODE_PRIVATE);
        buffCal.enemy_setup(sharedPreferencesB.getString("enemyName","Hilichurl"),sharedPreferencesB.getInt("enemyLvl",1));

        Picasso.get()
                .load(FileLoader.loadIMG(item_rss.getEnemyByName(buffCal.enemyName,context)[1],context))
                .transform(transformation)
                .resize(72, 72)
                .error(R.drawable.paimon_full)
                .into(buff_enemy_ico);

        buff_enemy_lvl.setText(context.getString(R.string.curr_lvl) + prettyCount(buffCal.LvlEnemy,0));
        buff_enemy_name.setText(item_rss.getEnemyByName(buffCal.enemyName,context)[0]);


        buff_base();

        buff_normal();

        buff_buff_tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0 : buff_tab_view.removeAllViews(); buff_normal(); break;
                    case 1 : buff_tab_view.removeAllViews(); buff_charged(); break;
                    case 2 : buff_tab_view.removeAllViews(); buff_pluging(); break;
                    case 3 : buff_tab_view.removeAllViews(); buff_element_atk(); break;
                    case 4 : buff_tab_view.removeAllViews(); buff_final_atk(); break;
                    case 5 : buff_tab_view.removeAllViews(); break;
                    case 6 : buff_tab_view.removeAllViews(); break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0 : buff_tab_view.removeAllViews(); buff_normal(); break;
                    case 1 : buff_tab_view.removeAllViews(); buff_charged(); break;
                    case 2 : buff_tab_view.removeAllViews(); buff_pluging(); break;
                    case 3 : buff_tab_view.removeAllViews(); buff_element_atk(); break;
                    case 4 : buff_tab_view.removeAllViews(); buff_final_atk(); break;
                    case 5 : buff_tab_view.removeAllViews(); break;
                    case 6 : buff_tab_view.removeAllViews(); break;
                }
            }
        });

        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth());
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }


    public void enemy_list_setup(View view, ArrayList<Enemys> arrayList, Dialog dialog) {
        /** CHARACTER ICON + WEAPON ICON*/
        gridLayout = new GridLayout(context);
        gridLayout = view.findViewById(R.id.enemy_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        ImageView item_img;
        TextView tv;
        final int radius = 180;
        final int margin = 4;

        int column = 2;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);

        for (int x = 0, c = 0, r = 0; x < arrayList.size(); x++, c++) {
            if(c == column) { c = 0;r++; }
            View view1 = View.inflate(context, R.layout.item_enemy_icon, null);
            ImageView enemy_img = view1.findViewById(R.id.enemy_img);
            TextView enemy_name = view1.findViewById(R.id.enemy_name);
            TextView enemy_base_name = view1.findViewById(R.id.enemy_base_name);
            LinearLayout enemy_bg = view1.findViewById(R.id.enemy_bg);

            enemy_name.setText(item_rss.getEnemyByName(arrayList.get(x).getBaseName(),context)[0]);

            enemy_base_name.setText(arrayList.get(x).getBaseName());

            Picasso.get()
                    .load (FileLoader.loadIMG(item_rss.getEnemyByName(arrayList.get(x).getBaseName(),context)[1],context))
                    .transform(transformation)
                    .resize(96,96)
                    .error (R.drawable.paimon_lost)
                    .into (enemy_img);

            enemy_bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    buffCal.enemy_setup(enemy_base_name.getText().toString(),buffCal.LvlEnemy);

                    final int radius = 25;
                    final int margin = 4;
                    final Transformation transformation = new RoundedCornersTransformation(radius, margin);

                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getEnemyByName(buffCal.enemyName,context)[1],context))
                            .transform(transformation)
                            .resize(72, 72)
                            .error(R.drawable.paimon_full)
                            .into(buff_enemy_ico);

                    buff_enemy_lvl.setText(context.getString(R.string.curr_lvl) + prettyCount(buffCal.LvlEnemy,0));
                    buff_enemy_name.setText(item_rss.getEnemyByName(buffCal.enemyName,context)[0]);

                    buffCal.enemy_setup(buffCal.enemyName,buffCal.LvlEnemy);
                    buff_base();
                    dialog.dismiss();
                }
            });

            enemy_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    buffCal.enemy_setup(enemy_base_name.getText().toString(),buffCal.LvlEnemy);

                    final int radius = 25;
                    final int margin = 4;
                    final Transformation transformation = new RoundedCornersTransformation(radius, margin);

                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getEnemyByName(buffCal.enemyName,context)[1],context))
                            .transform(transformation)
                            .resize(72, 72)
                            .error(R.drawable.paimon_full)
                            .into(buff_enemy_ico);

                    buff_enemy_lvl.setText(context.getString(R.string.curr_lvl) + prettyCount(buffCal.LvlEnemy,0));
                    buff_enemy_name.setText(item_rss.getEnemyByName(buffCal.enemyName,context)[0]);
                    buffCal.enemy_setup(buffCal.enemyName,buffCal.LvlEnemy);
                    buff_base();
                    dialog.dismiss();
                }
            });

            enemy_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    buffCal.enemy_setup(enemy_base_name.getText().toString(),buffCal.LvlEnemy);

                    final int radius = 25;
                    final int margin = 4;
                    final Transformation transformation = new RoundedCornersTransformation(radius, margin);

                    Picasso.get()
                            .load(FileLoader.loadIMG(item_rss.getEnemyByName(buffCal.enemyName,context)[1],context))
                            .transform(transformation)
                            .resize(72, 72)
                            .error(R.drawable.paimon_full)
                            .into(buff_enemy_ico);

                    buff_enemy_lvl.setText(context.getString(R.string.curr_lvl) + prettyCount(buffCal.LvlEnemy,0));
                    buff_enemy_name.setText(item_rss.getEnemyByName(buffCal.enemyName,context)[0]);

                    buffCal.enemy_setup(buffCal.enemyName,buffCal.LvlEnemy);
                    buff_base();
                    dialog.dismiss();
                }
            });


            gridLayout.addView(view1);
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth()/2);;
            param.setGravity(Gravity.CENTER_VERTICAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view1.setLayoutParams (param);

        }
    }

    /*
    Sub UI
     */

    public void buff_base(){
        buff_char_atk_value.setText(prettyCount(buffCal.atkReturn(artifactList),0));
        buff_char_hp_value.setText(prettyCount(buffCal.hpReturn(artifactList),0));
        buff_char_def_value.setText(prettyCount(buffCal.defReturn(artifactList),0));

        buff_char_crit_rate_value.setText(prettyCount(buffCal.critRateReturn(artifactList),1));
        buff_char_crit_dmg_value.setText(prettyCount(buffCal.critDMGReturn()-1,1));
        buff_char_enrech_value.setText(prettyCount(buffCal.enRechReturn(artifactList),1));

        buff_char_elemas_value.setText(prettyCount(buffCal.元素精通附加值(artifactList),0));
        buff_char_healing_value.setText(prettyCount(buffCal.治療加成(artifactList),1));
        buff_char_eledmg_pyro_value.setText(prettyCount(buffCal.火元素傷害加成(artifactList),1));
        buff_char_eledmg_hydro_value.setText(prettyCount(buffCal.水元素傷害加成(artifactList),1));
        buff_char_eledmg_anemo_value.setText(prettyCount(buffCal.風元素傷害加成(artifactList),1));
        buff_char_eledmg_electro_value.setText(prettyCount(buffCal.雷元素傷害加成(artifactList),1));
        buff_char_eledmg_dendor_value.setText(prettyCount(buffCal.草元素傷害加成(artifactList),1));
        buff_char_eledmg_cryo_value.setText(prettyCount(buffCal.冰元素傷害加成(artifactList),1));
        buff_char_eledmg_geo_value.setText(prettyCount(buffCal.岩元素傷害加成(artifactList),1));
        buff_char_phy_value.setText(prettyCount(buffCal.物理傷害加成(artifactList),1));


    }

    public void buff_normal(){
        buff_tab_view.removeAllViews();
        display_normal= true;
        display_charging = false;
        display_pluging  =false;
        display_element_atk  =false;
        display_final_atk  =false;

        String[] item_name_recongize = new String[]{"一段傷害","二段傷害","三段傷害","四段傷害","五段傷害","六段傷害","瞄準射擊"};

        String[] item_name = new String[]{context.getString(R.string.buff_normal_1_hit),context.getString(R.string.buff_normal_2_hit),context.getString(R.string.buff_normal_3_hit),context.getString(R.string.buff_normal_4_hit),context.getString(R.string.buff_normal_5_hit),context.getString(R.string.buff_normal_6_hit),context.getString(R.string.buff_normal_shoot)};

        for (int i = 0 ; i < item_name_recongize.length ; i++){
            if(buffCal.skillPReturn(item_name_recongize[i],artifactList)*100 != 0){
                View view = View.inflate(context, R.layout.item_buff_display, null);
                TextView buff_item_name = view.findViewById(R.id.buff_item_name);
                TextView buff_item_value = view.findViewById(R.id.buff_item_value);
                TextView buff_uncrit_value = view.findViewById(R.id.buff_uncrit_value);
                TextView buff_crit_value = view.findViewById(R.id.buff_crit_value);

                buff_item_name.setText(item_name[i]);
                buff_item_value.setText(prettyCount(buffCal.skillPReturn(item_name_recongize[i],artifactList),1));
                buff_item_value.setTextColor(Color.parseColor(color_hex));
                //buff_uncrit_value.setText(prettyCount(buffCal.atkReturn()*buffCal.skillPReturn(item_name_recongize[i])));
                if(charWeapon.equals("Catalyst")){
                    buff_uncrit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],false,charElement,artifactList),0));
                    buff_uncrit_value.setTextColor(context.getColor(R.color.uncrit));
                    //buff_crit_value.setText(prettyCount(buffCal.atkReturn()*buffCal.skillPReturn(item_name_recongize[i])*buffCal.critDMGReturn()));
                    buff_crit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],true,charElement,artifactList),0));
                    buff_crit_value.setTextColor(context.getColor(R.color.crit));
                }else{
                    buff_uncrit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],false,"Physical",artifactList),0));
                    buff_uncrit_value.setTextColor(context.getColor(R.color.uncrit));
                    //buff_crit_value.setText(prettyCount(buffCal.atkReturn()*buffCal.skillPReturn(item_name_recongize[i])*buffCal.critDMGReturn()));
                    buff_crit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],true,"Physical",artifactList),0));
                    buff_crit_value.setTextColor(context.getColor(R.color.crit));
                }

                buff_tab_view.addView(view);
            }
        }

        /*
        switch (element) {
                    case "Anemo": buff_item_value.setTextColor(context.getColor(R.color.anemo));break;
                    case "Cryo": buff_item_value.setTextColor(context.getColor(R.color.cryo));break;
                    case "Electro": buff_item_value.setTextColor(context.getColor(R.color.electro));break;
                    case "Geo": buff_item_value.setTextColor(context.getColor(R.color.geo));break;
                    case "Hydro": buff_item_value.setTextColor(context.getColor(R.color.hydro));break;
                    case "Pyro": buff_item_value.setTextColor(context.getColor(R.color.pyro));break;
                    case "Dendro": buff_item_value.setTextColor(context.getColor(R.color.dendor));break;
                }
         */
    }

    public void buff_charged(){
        buff_tab_view.removeAllViews();
        display_charging= true;
        display_normal = false;
        display_pluging  =false;
        display_element_atk  =false;
        display_final_atk  =false;

        String[] item_name_recongize = new String[]{"滿蓄力瞄準射擊","重擊傷害","重擊循環傷害","重擊終結傷害"};

        String[] item_name = new String[]{context.getString(R.string.buff_charged_shoot),context.getString(R.string.buff_charged_dmg),context.getString(R.string.buff_charged_spinning_dmg),context.getString(R.string.buff_charged_final_dmg)};

        for (int i = 0 ; i < item_name_recongize.length; i++){
            if(buffCal.skillPReturn(item_name_recongize[i],artifactList)*100 != 0){
                View view = View.inflate(context, R.layout.item_buff_display, null);
                TextView buff_item_name = view.findViewById(R.id.buff_item_name);
                TextView buff_item_value = view.findViewById(R.id.buff_item_value);
                TextView buff_uncrit_value = view.findViewById(R.id.buff_uncrit_value);
                TextView buff_crit_value = view.findViewById(R.id.buff_crit_value);

                buff_item_name.setText(item_name[i]);
                buff_item_value.setText(prettyCount(buffCal.skillPReturn(item_name_recongize[i],artifactList),1));
                buff_item_value.setTextColor(Color.parseColor(color_hex));

                if(charWeapon.equals("Bow") && i == 0){
                    buff_uncrit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],false,charElement,artifactList),0));
                    buff_uncrit_value.setTextColor(context.getColor(R.color.uncrit));
                    buff_crit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],true,charElement,artifactList),0));
                    buff_crit_value.setTextColor(context.getColor(R.color.crit));
                }else{
                    buff_uncrit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],false,"Physical",artifactList),0));
                    buff_uncrit_value.setTextColor(context.getColor(R.color.uncrit));
                    buff_crit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],true,"Physical",artifactList),0));
                    buff_crit_value.setTextColor(context.getColor(R.color.crit));
                }

                buff_tab_view.addView(view);
            }
        }
    }

    public void buff_pluging(){
        buff_tab_view.removeAllViews();

        display_pluging= true;
        display_normal = false;
        display_charging  =false;
        display_element_atk  =false;
        display_final_atk  =false;

        String[] item_name_recongize = new String[]{"下墜期間傷害","低空墜地衝擊傷害","高空墜地衝擊傷害"};

        String[] item_name = new String[]{context.getString(R.string.buff_plunge_dmg),context.getString(R.string.buff_low_plunge_dmg),context.getString(R.string.buff_high_plunge_dmg)};

        for (int i = 0 ; i < item_name_recongize.length ; i++){
            if(buffCal.skillPReturn(item_name_recongize[i],artifactList)*100 != 0){
                View view = View.inflate(context, R.layout.item_buff_display, null);
                TextView buff_item_name = view.findViewById(R.id.buff_item_name);
                TextView buff_item_value = view.findViewById(R.id.buff_item_value);
                TextView buff_uncrit_value = view.findViewById(R.id.buff_uncrit_value);
                TextView buff_crit_value = view.findViewById(R.id.buff_crit_value);

                buff_item_name.setText(item_name[i]);
                buff_item_value.setText(prettyCount(buffCal.skillPReturn(item_name_recongize[i],artifactList),1));
                buff_item_value.setTextColor(Color.parseColor(color_hex));

                if(charWeapon.equals("Catalyst")){
                    buff_uncrit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],false,charElement,artifactList),0));
                    buff_uncrit_value.setTextColor(context.getColor(R.color.uncrit));
                    buff_crit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],true,charElement,artifactList),0));
                    buff_crit_value.setTextColor(context.getColor(R.color.crit));
                }else{
                    buff_uncrit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],false,"Physical",artifactList),0));
                    buff_uncrit_value.setTextColor(context.getColor(R.color.uncrit));
                    buff_crit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],true,"Physical",artifactList),0));
                    buff_crit_value.setTextColor(context.getColor(R.color.crit));
                }

                buff_tab_view.addView(view);
            }
        }
    }

    /**
     * Please note that, if u got "xxx_BASE", pls not to recognize it as two things.
     */
    public void buff_element_atk(){
        buff_tab_view.removeAllViews();
        display_element_atk  =true;
        display_final_atk  =false;
        display_charging = false;
        display_pluging  =false;
        display_normal= false;


        String[] item_name_recongize = new String[]{"元素戰技0",	"元素戰技1",	"元素戰技2",	"元素戰技3",	"元素戰技4",	"元素戰技5",	"元素戰技6",	"元素戰技7",	"元素戰技8",	"元素戰技9",	"元素戰技10",	"元素戰技11"};

        for (int i = 0 ; i < buffCal.returnElementATKArraySize() ; i++){
            if(buffCal.skillPReturn(item_name_recongize[i],artifactList)*100 != 0 && !buffCal.returnElementATKArray()[i].contains("_BASE")&& !buffCal.returnElementATKArray()[i].contains("治療")&& !buffCal.returnElementATKArray()[i].contains("吸收")&& !buffCal.returnElementATKArray()[i].contains("護盾")){
                View view = View.inflate(context, R.layout.item_buff_display, null);
                TextView buff_item_name = view.findViewById(R.id.buff_item_name);
                TextView buff_item_value = view.findViewById(R.id.buff_item_value);
                TextView buff_uncrit_value = view.findViewById(R.id.buff_uncrit_value);
                TextView buff_crit_value = view.findViewById(R.id.buff_crit_value);

                buff_item_name.setText(item_rss.getSkillNameByCustomName(buffCal.returnElementATKArray()[i],context));
                buff_item_value.setText(prettyCount(buffCal.skillPReturn(item_name_recongize[i],artifactList),1));
                buff_item_value.setTextColor(Color.parseColor(color_hex));
                //buff_uncrit_value.setText(prettyCount(buffCal.atkReturn()*buffCal.skillPReturn(item_name_recongize[i])));
                buff_uncrit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],false,"Physical",artifactList),0));
                buff_uncrit_value.setTextColor(context.getColor(R.color.uncrit));
                //buff_crit_value.setText(prettyCount(buffCal.atkReturn()*buffCal.skillPReturn(item_name_recongize[i])*buffCal.critDMGReturn()));
                buff_crit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],true,"Physical",artifactList),0));
                buff_crit_value.setTextColor(context.getColor(R.color.crit));

                buff_tab_view.addView(view);
            }else if (buffCal.returnElementATKArraySize() > (i+1)){
                if (buffCal.returnElementATKArray()[i+1].contains("_")){
                    View view = View.inflate(context, R.layout.item_buff_display, null);
                    TextView buff_item_name = view.findViewById(R.id.buff_item_name);
                    TextView buff_crit_name = view.findViewById(R.id.buff_crit_name);
                    TextView buff_uncrit_name = view.findViewById(R.id.buff_uncrit_name);
                    TextView buff_item_value = view.findViewById(R.id.buff_item_value);
                    TextView buff_uncrit_value = view.findViewById(R.id.buff_uncrit_value);
                    TextView buff_crit_value = view.findViewById(R.id.buff_crit_value);
                    buff_crit_value.setVisibility(View.GONE);
                    buff_crit_name.setVisibility(View.GONE);

                    buff_item_name.setText(item_rss.getSkillNameByCustomName(buffCal.returnElementATKArray()[i],context));
                    buff_item_value.setText(prettyCount(buffCal.skillPReturn(item_name_recongize[i+1],artifactList),0)+" + "+prettyCount(buffCal.skillPReturn(item_name_recongize[i],artifactList),1));
                    buff_item_value.setTextColor(Color.parseColor(color_hex));

                    buff_uncrit_name.setText(item_rss.getSkillNameByCustomName(buffCal.returnElementATKArray()[i],context));
                    buff_uncrit_value.setTextColor(context.getColor(R.color.uncrit));

                    if(buffCal.returnElementATKArray()[i].contains("治療")){
                        if(charName.equals("Qiqi") || charName.equals("七七") || charName.equals("七七") || charName.equals("七七") || charName.equals("Ци Ци") || charName.equals("Jean") || charName.equals("琴") || charName.equals("琴") || charName.equals("ジン") || charName.equals("Джинн")){
                            buff_uncrit_value.setText(prettyCount(buffCal.HealATK(item_name_recongize[i],item_name_recongize[i+1],artifactList),0));
                        }else {
                            buff_uncrit_value.setText(prettyCount(buffCal.HealHP(item_name_recongize[i],item_name_recongize[i+1],artifactList),0));
                        }
                    }else if(buffCal.returnElementATKArray()[i].contains("護盾")){
                        buff_uncrit_value.setText(prettyCount(buffCal.Shield(item_name_recongize[i],item_name_recongize[i+1],artifactList),0));
                    }


                    buff_tab_view.addView(view);
                }
            }
        }

        /*
        switch (element) {
                    case "Anemo": buff_item_value.setTextColor(context.getColor(R.color.anemo));break;
                    case "Cryo": buff_item_value.setTextColor(context.getColor(R.color.cryo));break;
                    case "Electro": buff_item_value.setTextColor(context.getColor(R.color.electro));break;
                    case "Geo": buff_item_value.setTextColor(context.getColor(R.color.geo));break;
                    case "Hydro": buff_item_value.setTextColor(context.getColor(R.color.hydro));break;
                    case "Pyro": buff_item_value.setTextColor(context.getColor(R.color.pyro));break;
                    case "Dendro": buff_item_value.setTextColor(context.getColor(R.color.dendor));break;
                }
         */
    }

    public void buff_final_atk(){
        buff_tab_view.removeAllViews();
        display_element_atk  =false;
        display_final_atk  =true;
        display_normal= false;
        display_charging = false;
        display_pluging  =false;

        String[] item_name_recongize = new String[]{"元素爆發0",	"元素爆發1",	"元素爆發2",	"元素爆發3",	"元素爆發4",	"元素爆發5",	"元素爆發6",	"元素爆發7",	"元素爆發8",	"元素爆發9",	"元素爆發10",	"元素爆發11",};

        for (int i = 0 ; i < buffCal.returnFinalATKArraySize() ; i++){
            if(buffCal.skillPReturn(item_name_recongize[i],artifactList)*100 != 0 && !buffCal.returnFinalATKArray()[i].contains("_BASE")&& !buffCal.returnFinalATKArray()[i].contains("治療")&& !buffCal.returnFinalATKArray()[i].contains("吸收")&& !buffCal.returnFinalATKArray()[i].contains("護盾")){
                View view = View.inflate(context, R.layout.item_buff_display, null);
                TextView buff_item_name = view.findViewById(R.id.buff_item_name);
                TextView buff_item_value = view.findViewById(R.id.buff_item_value);
                TextView buff_uncrit_value = view.findViewById(R.id.buff_uncrit_value);
                TextView buff_crit_value = view.findViewById(R.id.buff_crit_value);

                // item_rss.getSkillNameByCustomName()
                buff_item_name.setText(item_rss.getSkillNameByCustomName(buffCal.returnFinalATKArray()[i],context));
                buff_item_value.setText(prettyCount(buffCal.skillPReturn(item_name_recongize[i],artifactList),1));
                buff_item_value.setTextColor(Color.parseColor(color_hex));
                //buff_uncrit_value.setText(prettyCount(buffCal.atkReturn()*buffCal.skillPReturn(item_name_recongize[i])));
                buff_uncrit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],false,"Physical",artifactList),0));
                buff_uncrit_value.setTextColor(context.getColor(R.color.uncrit));
                //buff_crit_value.setText(prettyCount(buffCal.atkReturn()*buffCal.skillPReturn(item_name_recongize[i])*buffCal.critDMGReturn()));
                buff_crit_value.setText(prettyCount(buffCal.Damage(item_name_recongize[i],true,"Physical",artifactList),0));
                buff_crit_value.setTextColor(context.getColor(R.color.crit));

                buff_tab_view.addView(view);
            }else if (buffCal.returnFinalATKArraySize() > (i+1)){
                if(buffCal.returnFinalATKArray()[i+1].contains("_")) {
                    View view = View.inflate(context, R.layout.item_buff_display, null);
                    TextView buff_item_name = view.findViewById(R.id.buff_item_name);
                    TextView buff_crit_name = view.findViewById(R.id.buff_crit_name);
                    TextView buff_uncrit_name = view.findViewById(R.id.buff_uncrit_name);
                    TextView buff_item_value = view.findViewById(R.id.buff_item_value);
                    TextView buff_uncrit_value = view.findViewById(R.id.buff_uncrit_value);
                    TextView buff_crit_value = view.findViewById(R.id.buff_crit_value);
                    buff_crit_value.setVisibility(View.GONE);
                    buff_crit_name.setVisibility(View.GONE);

                    buff_item_name.setText(item_rss.getSkillNameByCustomName(buffCal.returnFinalATKArray()[i], context));
                    buff_item_value.setText(prettyCount(buffCal.skillPReturn(item_name_recongize[i + 1], artifactList), 0) + " + " + prettyCount(buffCal.skillPReturn(item_name_recongize[i], artifactList), 1));
                    buff_item_value.setTextColor(Color.parseColor(color_hex));

                    buff_uncrit_name.setText(item_rss.getSkillNameByCustomName(buffCal.returnFinalATKArray()[i], context));
                    buff_uncrit_value.setTextColor(context.getColor(R.color.uncrit));

                    if (buffCal.returnFinalATKArray()[i].contains("治療")) {
                        if(charName.equals("Qiqi") || charName.equals("七七") || charName.equals("七七") || charName.equals("七七") || charName.equals("Ци Ци") || charName.equals("Jean") || charName.equals("琴") || charName.equals("琴") || charName.equals("ジン") || charName.equals("Джинн")){
                            buff_uncrit_value.setText(prettyCount(buffCal.HealATK(item_name_recongize[i], item_name_recongize[i + 1], artifactList), 0));
                        } else {

                            buff_uncrit_value.setText(prettyCount(buffCal.HealHP(item_name_recongize[i], item_name_recongize[i + 1], artifactList), 0));
                        }
                    } else if (buffCal.returnFinalATKArray()[i].contains("護盾")) {
                        buff_uncrit_value.setText(prettyCount(buffCal.Shield(item_name_recongize[i], item_name_recongize[i + 1], artifactList), 0));
                    }
                    buff_tab_view.addView(view);
                }
            }
        }
    }

    public void secSetShow(int sec_id, int i,int pos_id,EditText valueTV,TextView valueP, RadioButton buff_artifact_0t, RadioButton buff_artifact_1t, RadioButton buff_artifact_2t, RadioButton buff_artifact_3t, RadioButton buff_artifact_4t,RadioButton buff_artifact_ct, RadioButton buff_artifact_1u, RadioButton buff_artifact_2u, RadioButton buff_artifact_3u, RadioButton buff_artifact_4u, RadioButton buff_artifact_5u, String[] tmp_artifact_sort){

        double[][] rare5SecBuffData = new double[][]{
                {0.041,0.041,0.051,14,209,16,16,0.045,0.027,0.054},
                {0.047,0.047,0.058,16,239,19,19,0.052,0.031,0.062},
                {0.053,0.053,0.066,18,269,21,21,0.058,0.035,0.070},
                {0.058,0.058,0.073,19,299,23,23,0.065,0.039,0.078}
        };

        //String[] artifactSecBuffList = new String[]{context.getString(R.string.weapon_stat_atkP),context.getString(R.string.weapon_stat_HPP),context.getString(R.string.weapon_stat_DEFP),context.getString(R.string.weapon_stat_atk),context.getString(R.string.weapon_stat_HP),context.getString(R.string.weapon_stat_DEF),context.getString(R.string.weapon_stat_EleMas),context.getString(R.string.weapon_stat_EnRechP),context.getString(R.string.weapon_stat_CritRateP),context.getString(R.string.weapon_stat_CritDMGP)};
        String[] artifactSecBuffList = new String[]{"ATK","HP","DEF","baseATK","baseHP","baseDEF","EleMas","EnRech","CritRate","CritDMG"};



        int finalTmp_grade = 0;
        int finalTmp_upgrade = 0;

        if(buff_artifact_0t.isChecked()){
            finalTmp_grade = -1;
        }else if(buff_artifact_1t.isChecked()){
            finalTmp_grade = 0;
        }else if(buff_artifact_2t.isChecked()){
            finalTmp_grade = 1;
        }else if(buff_artifact_3t.isChecked()){
            finalTmp_grade = 2;
        }else if(buff_artifact_4t.isChecked()){
            finalTmp_grade = 3;
        }else if(buff_artifact_ct.isChecked()){
            finalTmp_grade = -2;
        }

        if(buff_artifact_1u.isChecked()){
            finalTmp_upgrade = 1;
        }else if(buff_artifact_2u.isChecked()){
            finalTmp_upgrade = 2;
        }else if(buff_artifact_3u.isChecked()){
            finalTmp_upgrade = 3;
        }else if(buff_artifact_4u.isChecked()){
            finalTmp_upgrade = 4;
        }else if(buff_artifact_5u.isChecked()){
            finalTmp_upgrade = 5;
        }

        pos_id = pos_id -1;

        if(finalTmp_grade != -1 && finalTmp_grade != -2){
            valueTV.setVisibility(View.GONE);
            valueP.setVisibility(View.VISIBLE);
            if(i !=3 && i != 4 && i != 5 && i != 6){
                valueP.setText(prettyCount(rare5SecBuffData[finalTmp_grade][i]*finalTmp_upgrade,1));
            }else{
                valueP.setText(prettyCount(rare5SecBuffData[finalTmp_grade][i]*finalTmp_upgrade,0));
            }
        }else if(finalTmp_grade == -2){
            valueTV.setEnabled(true);
            valueTV.setVisibility(View.VISIBLE);
            valueP.setVisibility(View.GONE);

            int finalPos_id = pos_id;
            valueTV.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    System.out.println("editable : "+editable);
                    if(editable != null && !editable.toString().equals("")){
                        if(editable.toString().startsWith("0")||editable.toString().startsWith("1")||editable.toString().startsWith("2")||editable.toString().startsWith("3")||editable.toString().startsWith("4")||editable.toString().startsWith("5")||editable.toString().startsWith("6")||editable.toString().startsWith("7")||editable.toString().startsWith("8")||editable.toString().startsWith("9")){
                            switch (sec_id){
                            case 1 : {
                                artifactBuffSec1ItemTMP.set(finalPos_id, artifactSecBuffList[i]);
                                artifactBuffSec1ValueTMP.set(finalPos_id,Double.parseDouble(editable.toString()));
                                break;
                            }
                            case 2 : {
                                artifactBuffSec2ItemTMP.set(finalPos_id, artifactSecBuffList[i]);
                                artifactBuffSec2ValueTMP.set(finalPos_id,Double.parseDouble(editable.toString()));
                                break;
                            }
                            case 3 : {
                                artifactBuffSec3ItemTMP.set(finalPos_id, artifactSecBuffList[i]);
                                artifactBuffSec3ValueTMP.set(finalPos_id,Double.parseDouble(editable.toString()));
                                break;
                            }
                            case 4 : {
                                artifactBuffSec4ItemTMP.set(finalPos_id, artifactSecBuffList[i]);
                                artifactBuffSec4ValueTMP.set(finalPos_id,Double.parseDouble(editable.toString()));
                                break;
                            }
                        }
                        }
                    }
                }
            });
        }else{
            valueTV.setVisibility(View.GONE);
            valueP.setVisibility(View.VISIBLE);
            if(i !=3 && i != 4 && i != 5 && i != 6){
                valueP.setText(prettyCount(0,1));
            }else{
                valueP.setText(prettyCount(0,0));
            }
        }

        switch (sec_id){
            case 1 : {
                artifactBuffSec1ItemTMP.set(pos_id, artifactSecBuffList[i]);
                if(finalTmp_grade != -1 && finalTmp_grade != -2) {
                    artifactBuffSec1ValueTMP.set(pos_id, rare5SecBuffData[finalTmp_grade][i] * finalTmp_upgrade);
                }else{
                    artifactBuffSec1ValueTMP.set(pos_id,0.0);
                }
                break;
            }
            case 2 : {
                artifactBuffSec2ItemTMP.set(pos_id, artifactSecBuffList[i]);
                if(finalTmp_grade != -1 && finalTmp_grade != -2) {
                    artifactBuffSec2ValueTMP.set(pos_id, rare5SecBuffData[finalTmp_grade][i] * finalTmp_upgrade);
                }else{
                    artifactBuffSec2ValueTMP.set(pos_id,0.0);
                }
                break;
            }
            case 3 : {
                artifactBuffSec3ItemTMP.set(pos_id, artifactSecBuffList[i]);
                if(finalTmp_grade != -1 && finalTmp_grade != -2) {
                    artifactBuffSec3ValueTMP.set(pos_id, rare5SecBuffData[finalTmp_grade][i] * finalTmp_upgrade);
                }else{
                    artifactBuffSec3ValueTMP.set(pos_id,0.0);
                }
                break;
            }
            case 4 : {
                artifactBuffSec4ItemTMP.set(pos_id, artifactSecBuffList[i]);
                if(finalTmp_grade != -1 && finalTmp_grade != -2) {
                    artifactBuffSec4ValueTMP.set(pos_id, rare5SecBuffData[finalTmp_grade][i] * finalTmp_upgrade);
                }else{
                    artifactBuffSec4ValueTMP.set(pos_id,0.0);
                }
                break;
            }
        }

        saveData(tmp_artifact_sort);
    }

    public void showResult(int tmp_id){

        artifactBufSetting(tmp_id, this.tmp_artifact_lvl, this.tmp_artifact_rare);
        buff_artifact_sec1_name.setText(item_rss.getArtifactBuffName(artifactBuffSec1Item.get(tmp_id),context));
        if(!artifactBuffSec1Item.get(tmp_id).equals("baseATK") && !artifactBuffSec1Item.get(tmp_id).equals("baseHP") && !artifactBuffSec1Item.get(tmp_id).equals("baseDEF") && !artifactBuffSec1Item.get(tmp_id).equals("EleMas")){
            buff_artifact_sec1_value.setText(prettyCount(artifactBuffSec1Value.get(tmp_id),1));
        }else{
            buff_artifact_sec1_value.setText(prettyCount(artifactBuffSec1Value.get(tmp_id),0));
        }

        buff_artifact_sec2_name.setText(item_rss.getArtifactBuffName(artifactBuffSec2Item.get(tmp_id),context));
        if(!artifactBuffSec2Item.get(tmp_id).equals("baseATK") && !artifactBuffSec2Item.get(tmp_id).equals("baseHP") && !artifactBuffSec2Item.get(tmp_id).equals("baseDEF") && !artifactBuffSec2Item.get(tmp_id).equals("EleMas")){
            buff_artifact_sec2_value.setText(prettyCount(artifactBuffSec2Value.get(tmp_id),1));
        }else{
            buff_artifact_sec2_value.setText(prettyCount(artifactBuffSec2Value.get(tmp_id),0));
        }

        buff_artifact_sec3_name.setText(item_rss.getArtifactBuffName(artifactBuffSec3Item.get(tmp_id),context));
        if(!artifactBuffSec3Item.get(tmp_id).equals("baseATK") && !artifactBuffSec3Item.get(tmp_id).equals("baseHP") && !artifactBuffSec3Item.get(tmp_id).equals("baseDEF") && !artifactBuffSec3Item.get(tmp_id).equals("EleMas")){
            buff_artifact_sec3_value.setText(prettyCount(artifactBuffSec3Value.get(tmp_id),1));
        }else{
            buff_artifact_sec3_value.setText(prettyCount(artifactBuffSec3Value.get(tmp_id),0));
        }

        buff_artifact_sec4_name.setText(item_rss.getArtifactBuffName(artifactBuffSec4Item.get(tmp_id),context));
        if(!artifactBuffSec4Item.get(tmp_id).equals("baseATK") && !artifactBuffSec4Item.get(tmp_id).equals("baseHP") && !artifactBuffSec4Item.get(tmp_id).equals("baseDEF") && !artifactBuffSec4Item.get(tmp_id).equals("EleMas")){
            buff_artifact_sec4_value.setText(prettyCount(artifactBuffSec4Value.get(tmp_id),1));
        }else{
            buff_artifact_sec4_value.setText(prettyCount(artifactBuffSec4Value.get(tmp_id),0));
        }

    }

    public void BuffDialogDisplay(int pos_id,int sec_id, int tmp_id, String[] tmp_artifact_sort){
        String[] artifactSecBuffList = new String[]{context.getString(R.string.weapon_stat_atkP),context.getString(R.string.weapon_stat_HPP),context.getString(R.string.weapon_stat_DEFP),context.getString(R.string.weapon_stat_atk),context.getString(R.string.weapon_stat_HP),context.getString(R.string.weapon_stat_DEF),context.getString(R.string.weapon_stat_EleMas),context.getString(R.string.weapon_stat_EnRechP),context.getString(R.string.weapon_stat_CritRateP),context.getString(R.string.weapon_stat_CritDMGP)};

        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.menu_buff_artifact, null);

        Spinner sec_sp = view.findViewById(R.id.buff_sec_name);
        EditText valueTV = view.findViewById(R.id.buff_sec_num_et);
        TextView valueP = view.findViewById(R.id.buff_sec_num);

        RadioButton buff_artifact_0t = view.findViewById(R.id.buff_artifact_0t);
        RadioButton buff_artifact_1t = view.findViewById(R.id.buff_artifact_1t);
        RadioButton buff_artifact_2t = view.findViewById(R.id.buff_artifact_2t);
        RadioButton buff_artifact_3t = view.findViewById(R.id.buff_artifact_3t);
        RadioButton buff_artifact_4t = view.findViewById(R.id.buff_artifact_4t);
        RadioButton buff_artifact_ct = view.findViewById(R.id.buff_artifact_ct);

        RadioButton buff_artifact_1u = view.findViewById(R.id.buff_artifact_1u);
        RadioButton buff_artifact_2u = view.findViewById(R.id.buff_artifact_2u);
        RadioButton buff_artifact_3u = view.findViewById(R.id.buff_artifact_3u);
        RadioButton buff_artifact_4u = view.findViewById(R.id.buff_artifact_4u);
        RadioButton buff_artifact_5u = view.findViewById(R.id.buff_artifact_5u);

        Button buff_ok = view.findViewById(R.id.buff_ok);
        Button buff_cancel = view.findViewById(R.id.buff_cancel);

        ArrayAdapter sec_aa = new ArrayAdapter(context,R.layout.spinner_item,artifactSecBuffList);
        sec_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);
        sec_sp.setAdapter(sec_aa);

        //if(artifactBasicBaseHP.get(0).isNaN()){}
        // init -- TMP -- Will change after function done
        // Note : CritDMG = 5*5.4% = 27.0% , in this case, the number "5" means the rare of this artifact.

        valueTV.setEnabled(false);

        sec_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                buff_artifact_0t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_0t.setChecked(true);
                        buff_artifact_1t.setChecked(false);
                        buff_artifact_2t.setChecked(false);
                        buff_artifact_3t.setChecked(false);
                        buff_artifact_4t.setChecked(false);
                        buff_artifact_ct.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,valueP,buff_artifact_0t,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_ct,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u,tmp_artifact_sort);
                    }
                });
                buff_artifact_1t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_1t.setChecked(true);
                        buff_artifact_2t.setChecked(false);
                        buff_artifact_3t.setChecked(false);
                        buff_artifact_4t.setChecked(false);
                        buff_artifact_0t.setChecked(false);
                        buff_artifact_ct.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,valueP,buff_artifact_0t,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_ct,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u,tmp_artifact_sort);
                    }
                });
                buff_artifact_2t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_2t.setChecked(true);
                        buff_artifact_1t.setChecked(false);
                        buff_artifact_3t.setChecked(false);
                        buff_artifact_4t.setChecked(false);
                        buff_artifact_0t.setChecked(false);
                        buff_artifact_ct.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,valueP,buff_artifact_0t,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_ct,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u,tmp_artifact_sort);
                    }
                });
                buff_artifact_3t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_3t.setChecked(true);
                        buff_artifact_2t.setChecked(false);
                        buff_artifact_1t.setChecked(false);
                        buff_artifact_4t.setChecked(false);
                        buff_artifact_0t.setChecked(false);
                        buff_artifact_ct.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,valueP,buff_artifact_0t,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_ct,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u,tmp_artifact_sort);
                    }
                });
                buff_artifact_4t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_4t.setChecked(true);
                        buff_artifact_2t.setChecked(false);
                        buff_artifact_3t.setChecked(false);
                        buff_artifact_1t.setChecked(false);
                        buff_artifact_0t.setChecked(false);
                        buff_artifact_ct.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,valueP,buff_artifact_0t,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_ct,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u,tmp_artifact_sort);
                    }
                });

                buff_artifact_ct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_ct.setChecked(true);
                        buff_artifact_2t.setChecked(false);
                        buff_artifact_3t.setChecked(false);
                        buff_artifact_1t.setChecked(false);
                        buff_artifact_0t.setChecked(false);
                        buff_artifact_4t.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,valueP,buff_artifact_0t,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_ct,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u,tmp_artifact_sort);
                    }
                });


                buff_artifact_1u.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_1u.setChecked(true);
                        buff_artifact_2u.setChecked(false);
                        buff_artifact_3u.setChecked(false);
                        buff_artifact_4u.setChecked(false);
                        buff_artifact_5u.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,valueP,buff_artifact_0t,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_ct,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u,tmp_artifact_sort);
                    }
                });
                buff_artifact_2u.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_2u.setChecked(true);
                        buff_artifact_1u.setChecked(false);
                        buff_artifact_3u.setChecked(false);
                        buff_artifact_4u.setChecked(false);
                        buff_artifact_5u.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,valueP,buff_artifact_0t,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_ct,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u,tmp_artifact_sort);
                    }
                });
                buff_artifact_3u.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_3u.setChecked(true);
                        buff_artifact_2u.setChecked(false);
                        buff_artifact_1u.setChecked(false);
                        buff_artifact_4u.setChecked(false);
                        buff_artifact_5u.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,valueP,buff_artifact_0t,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_ct,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u,tmp_artifact_sort);
                    }
                });
                buff_artifact_4u.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_4u.setChecked(true);
                        buff_artifact_2u.setChecked(false);
                        buff_artifact_3u.setChecked(false);
                        buff_artifact_1u.setChecked(false);
                        buff_artifact_5u.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,valueP,buff_artifact_0t,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_ct,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u,tmp_artifact_sort);
                    }
                });
                buff_artifact_5u.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buff_artifact_5u.setChecked(true);
                        buff_artifact_2u.setChecked(false);
                        buff_artifact_3u.setChecked(false);
                        buff_artifact_1u.setChecked(false);
                        buff_artifact_4u.setChecked(false);
                        secSetShow(sec_id,i,pos_id,valueTV,valueP,buff_artifact_0t,buff_artifact_1t,buff_artifact_2t,buff_artifact_3t,buff_artifact_4t,buff_artifact_ct,buff_artifact_1u,buff_artifact_2u,buff_artifact_3u,buff_artifact_4u,buff_artifact_5u,tmp_artifact_sort);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        buff_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                artifactBuffSec1Item = artifactBuffSec1ItemTMP;
                artifactBuffSec1Value = artifactBuffSec1ValueTMP;
                artifactBuffSec2Item = artifactBuffSec2ItemTMP;
                artifactBuffSec2Value = artifactBuffSec2ValueTMP;
                artifactBuffSec3Item = artifactBuffSec3ItemTMP;
                artifactBuffSec3Value = artifactBuffSec3ValueTMP;
                artifactBuffSec4Item = artifactBuffSec4ItemTMP;
                artifactBuffSec4Value = artifactBuffSec4ValueTMP;

                // Show results of Artifacts UI
                showResult(tmp_id);

                // Send current result to buffCal
                buffCal.artifact_setup(tmp_artifact_sort,tmp_artifact_lvl,artifactBuffMainValue,artifactBuffMainItem,artifactBuffSec1Value,artifactBuffSec1Item,artifactBuffSec2Value,artifactBuffSec2Item,artifactBuffSec3Value,artifactBuffSec3Item,artifactBuffSec4Value,artifactBuffSec4Item);

                // Show results of Buff
                buff_base();
                if(display_normal){
                    buff_normal();
                }else if(display_charging){
                    buff_charged();
                }else if (display_pluging){
                    buff_pluging();
                }

                saveData(tmp_artifact_sort);

                dialog.dismiss();
            }
        });

        buff_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth()*0.9);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    public void ArtifactBuffView(String type,String[] tmp_artifact_sort, View view){
        readBasicTier();
        int tmp_id = 0;

        switch (type){
            case "Flower" : {
                showResult(0);
                if (tmp_artifact_sort[0] != null) {
                    buff_artifact_sec1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(1, 1, 0, tmp_artifact_sort);
                        }
                    });
                    buff_artifact_sec2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(1, 2, 0, tmp_artifact_sort);
                        }
                    });
                    buff_artifact_sec3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(1, 3, 0, tmp_artifact_sort);
                        }
                    });
                    buff_artifact_sec4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(1, 4, 0, tmp_artifact_sort);
                        }
                    });
                    tmp_id = 0;
                    artifactBufSetting(tmp_id, this.tmp_artifact_lvl, this.tmp_artifact_rare);
                    break;
                }
                break;
            }
            case "Plume" : {
                showResult(1);

                if (tmp_artifact_sort[1] != null) {
                    buff_artifact_sec1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(2, 1, 1, tmp_artifact_sort);
                        }
                    });
                    buff_artifact_sec2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(2, 2, 1, tmp_artifact_sort);
                        }
                    });
                    buff_artifact_sec3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(2, 3, 1, tmp_artifact_sort);
                        }
                    });
                    buff_artifact_sec4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(2, 4, 1, tmp_artifact_sort);
                        }
                    });
                    tmp_id = 1;
                    artifactBufSetting(tmp_id, this.tmp_artifact_lvl, this.tmp_artifact_rare);
                    break;
                }
                break;
            }
            case "Sand" : {
                showResult(2);
                if (tmp_artifact_sort[2] != null) {
                    buff_artifact_sec1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(3, 1, 2, tmp_artifact_sort);
                        }
                    });
                    buff_artifact_sec2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(3, 2, 2, tmp_artifact_sort);
                        }
                    });
                    buff_artifact_sec3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(3, 3, 2, tmp_artifact_sort);
                        }
                    });
                    buff_artifact_sec4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(3, 4, 2, tmp_artifact_sort);
                        }
                    });
                    tmp_id = 2;
                    artifactBufSetting(tmp_id, this.tmp_artifact_lvl, this.tmp_artifact_rare);
                    break;
                }
                break;
            }
            case "Goblet" : {
                showResult(3);
                if (tmp_artifact_sort[3] != null) {
                    buff_artifact_sec1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(4, 1, 3, tmp_artifact_sort);
                        }
                    });
                    buff_artifact_sec2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(4, 2, 3, tmp_artifact_sort);
                        }
                    });
                    buff_artifact_sec3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(4, 3, 3, tmp_artifact_sort);
                        }
                    });
                    buff_artifact_sec4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(4, 4, 3, tmp_artifact_sort);
                        }
                    });
                    tmp_id = 3;
                    artifactBufSetting(tmp_id, this.tmp_artifact_lvl, this.tmp_artifact_rare);
                    break;
                }
            }
            case "Circlet" : {
                showResult(4);
                if (tmp_artifact_sort[4] != null) {
                    buff_artifact_sec1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(5, 1, 4, tmp_artifact_sort);
                        }
                    });
                    buff_artifact_sec2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(5, 2, 4, tmp_artifact_sort);
                        }
                    });
                    buff_artifact_sec3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(5, 3, 4, tmp_artifact_sort);
                        }
                    });
                    buff_artifact_sec4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BuffDialogDisplay(5, 4, 4, tmp_artifact_sort);
                        }
                    });
                    tmp_id = 4;
                    artifactBufSetting(tmp_id, this.tmp_artifact_lvl, this.tmp_artifact_rare);
                    break;
                }
                break;
            }
        }

    }

    public void artifactBufSetting(int tmp_id, int[] tmp_artifact_lvl, int[] tmp_artifact_rare){
        String[] artifactBuffList = new String[]{"baseHP","baseATK"};
        String[] artifactBuffList_LOCAL = new String[]{context.getString(R.string.weapon_stat_HP),context.getString(R.string.weapon_stat_atk)};

        if(tmp_artifact_rare[0] != 0){
            artifactBuffMainItem.set(0,artifactBuffList[0]);
            artifactBuffMainValue.set(0,artifactBasicBaseHP.get(tmp_artifact_lvl[0]));
        }

        if(tmp_artifact_rare[1] != 0){
            artifactBuffMainItem.set(1,artifactBuffList[1]);
            artifactBuffMainValue.set(1,artifactBasicBaseATK.get(tmp_artifact_lvl[1]));
        }

        if(tmp_id == 2){
            artifactBuffList = new String[]{"ATK","HP","DEF","EleMas","EnRech"};
        }else if(tmp_id == 3){
            artifactBuffList = new String[]{"ATK","HP","DEF","EleMas","EleDMG_Electro","EleDMG_Pyro","EleDMG_Hydro","EleDMG_Cryo","EleDMG_Anemo","EleDMG_Geo","PhyDMG"};
        }else if(tmp_id == 4){
            artifactBuffList = new String[]{"ATK","HP","DEF","EleMas","CritRate","CritDMG"};
        }

        if(tmp_id == 2){
            artifactBuffList_LOCAL = new String[]{context.getString(R.string.weapon_stat_atkP),context.getString(R.string.weapon_stat_HPP),context.getString(R.string.weapon_stat_DEFP),context.getString(R.string.weapon_stat_EleMas),context.getString(R.string.weapon_stat_EnRechP)};
        }else if(tmp_id == 3){
            artifactBuffList_LOCAL = new String[]{context.getString(R.string.weapon_stat_atkP),context.getString(R.string.weapon_stat_HPP),context.getString(R.string.weapon_stat_DEFP),context.getString(R.string.weapon_stat_EleMas),context.getString(R.string.weapon_stat_EleDMGP_Electro),context.getString(R.string.weapon_stat_EleDMGP_Pyro),context.getString(R.string.weapon_stat_EleDMGP_Hydro),context.getString(R.string.weapon_stat_EleDMGP_Cryo),context.getString(R.string.weapon_stat_EleDMGP_Anemo),context.getString(R.string.weapon_stat_EleDMGP_Geo),context.getString(R.string.weapon_stat_PhyDMGP)};
        }else if(tmp_id == 4){
            artifactBuffList_LOCAL = new String[]{context.getString(R.string.weapon_stat_atkP),context.getString(R.string.weapon_stat_HPP),context.getString(R.string.weapon_stat_DEFP),context.getString(R.string.weapon_stat_EleMas),context.getString(R.string.weapon_stat_CritRateP),context.getString(R.string.weapon_stat_CritDMGP)};
        }




        ArrayAdapter artifact_aa = new ArrayAdapter(context,R.layout.item_spinner_base,artifactBuffList_LOCAL);
        artifact_aa.setDropDownViewResource(R.layout.item_spinner);

        buff_artifact_main_name_sp.setAdapter(artifact_aa);
        String[] finalArtifactBuffList = artifactBuffList;

        buff_artifact_main_name.setText(item_rss.getArtifactBuffName(artifactBuffMainItem.get(tmp_id),context));

        if(tmp_id != 0 && tmp_id != 1){
            buff_artifact_main_name.setVisibility(View.GONE);
            buff_artifact_main_name_sp.setVisibility(View.VISIBLE);

            buff_artifact_main_name_sp.setSelection(0);
            for (int x = 0 ;x < artifactBuffList.length ; x++){
                if(artifactBuffMainItem.get(tmp_id).equals(artifactBuffList[x])){
                    buff_artifact_main_name_sp.setSelection(x);
                }
            }
        }else{
            buff_artifact_main_name.setVisibility(View.VISIBLE);
            buff_artifact_main_name_sp.setVisibility(View.GONE);
        }


        if(!artifactBuffMainItem.get(tmp_id).equals("baseATK") && !artifactBuffMainItem.get(tmp_id).equals("baseHP") && !artifactBuffMainItem.get(tmp_id).equals("baseDEF") && !artifactBuffMainItem.get(tmp_id).equals("EleMas")){
            buff_artifact_main_value.setText(prettyCount(artifactBuffMainValue.get(tmp_id),1));
        }else{
            buff_artifact_main_value.setText(prettyCount(artifactBuffMainValue.get(tmp_id),0));
        }

        buff_artifact_main_name_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                artifactBuffMainTranslate(finalArtifactBuffList,position,buff_artifact_main_value,tmp_id,tmp_artifact_lvl,tmp_artifact_rare);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void artifactBuffMainTranslate(String[] finalArtifactBuffList, int position, TextView buff_artifact_main_value, int tmp_id,int[] tmp_artifact_lvl, int[] tmp_artifact_rare) {
        ArrayList<Double> finalType = new ArrayList<>();
        String type = "SLEEP";

        if(finalArtifactBuffList[position].equals("ATK")){
            type = "ATK";
            finalType = artifactBasicATK;
        }else if(finalArtifactBuffList[position].equals("HP")){
            type = "HP";
            finalType = artifactBasicHP;
        }else if(finalArtifactBuffList[position].equals("DEF")){
            type = "DEF";
            finalType = artifactBasicDEF;
        }else if(finalArtifactBuffList[position].equals("EleMas")){
            type = "EleMas";
            finalType = artifactBasicEleMas;
        }else if(finalArtifactBuffList[position].equals("EnRech")){
            type = "EnRech";
            finalType = artifactBasicEnRech;
        }else if(finalArtifactBuffList[position].equals("EleDMG_Electro")){
            type = "EleDMG_Electro";
            finalType = artifactBasicEleDMG;
        }else if(finalArtifactBuffList[position].equals("EleDMG_Pyro")){
            type = "EleDMG_Pyro";
            finalType = artifactBasicEleDMG;
        }else if(finalArtifactBuffList[position].equals("EleDMG_Hydro")){
            type = "EleDMG_Hydro";
            finalType = artifactBasicEleDMG;
        }else if(finalArtifactBuffList[position].equals("EleDMG_Cryo")){
            type = "EleDMG_Cryo";
            finalType = artifactBasicEleDMG;
        }else if(finalArtifactBuffList[position].equals("EleDMG_Anemo")){
            type = "EleDMG_Anemo";
            finalType = artifactBasicEleDMG;
        }else if(finalArtifactBuffList[position].equals("EleDMG_Geo")){
            type = "EleDMG_Geo";
            finalType = artifactBasicEleDMG;
        }else if(finalArtifactBuffList[position].equals("EleDMG_Dendor")){
            type = "EleDMG_Dendor";
            finalType = artifactBasicEleDMG;
        }else if(finalArtifactBuffList[position].equals("PhyDMG")){
            type = "PhyDMG";
            finalType = artifactBasicPhyDMG;
        }else if(finalArtifactBuffList[position].equals("CritDMG")){
            type = "CritDMG";
            finalType = artifactBasicCritDMG;
        }else if(finalArtifactBuffList[position].equals("CritRate")){
            type = "CritRate";
            finalType = artifactBasicCritRate;
        }else if(finalArtifactBuffList[position].equals("baseATK")){
            type = "baseATK";
            finalType = artifactBasicBaseATK;
        }else if(finalArtifactBuffList[position].equals("baseHP")){
            type = "baseHP";
            finalType = artifactBasicBaseHP;
        }

        System.out.println(Arrays.toString(finalArtifactBuffList));

        artifactBuffMainItem.set(tmp_id,finalArtifactBuffList[position]);
        artifactBuffMainValue.set(tmp_id,finalType.get(tmp_artifact_lvl[tmp_id]));

        if(!artifactBuffMainItem.get(tmp_id).equals("baseATK") && !artifactBuffMainItem.get(tmp_id).equals("baseHP") && !artifactBuffMainItem.get(tmp_id).equals("baseDEF") && !artifactBuffMainItem.get(tmp_id).equals("EleMas")){
            buff_artifact_main_value.setText(prettyCount(artifactBuffMainValue.get(tmp_id),1));
        }else{
            buff_artifact_main_value.setText(prettyCount(artifactBuffMainValue.get(tmp_id),0));
        }

        saveData(tmp_artifact_sort);
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

    public void setDBName(String dataSheetName) {
        this.dataSheetName = dataSheetName;
    }

    public void readBasicTier(){
        String json_base = LoadData("db/buff/artifact_basic_tier.json");
        try {
            JSONObject jsonObject = new JSONObject(json_base);
            JSONObject basic = jsonObject.getJSONObject("Basic");

            JSONArray baseHP = basic.getJSONArray("baseHP");
            JSONArray baseATK = basic.getJSONArray("baseATK");
            JSONArray HP = basic.getJSONArray("HP");
            JSONArray ATK = basic.getJSONArray("ATK");
            JSONArray DEF = basic.getJSONArray("DEF");
            JSONArray EleMas = basic.getJSONArray("EleMas");
            JSONArray EnRech = basic.getJSONArray("EnRech");
            JSONArray PhyDMG = basic.getJSONArray("PhyDMG");
            JSONArray EleDMG = basic.getJSONArray("EleDMG");
            JSONArray CritRate = basic.getJSONArray("CritRate");
            JSONArray CritDMG = basic.getJSONArray("CritDMG");
            JSONArray Healing = basic.getJSONArray("Healing");

            JSONObject tier = jsonObject.getJSONObject("Tier");
            JSONArray TbaseHP = tier.getJSONArray("baseHP");
            JSONArray TbaseATK = tier.getJSONArray("baseATK");
            JSONArray TbaseDEF = tier.getJSONArray("baseDEF");
            JSONArray THP = tier.getJSONArray("HP");
            JSONArray TATK = tier.getJSONArray("ATK");
            JSONArray TDEF = tier.getJSONArray("DEF");
            JSONArray TEleMas = tier.getJSONArray("EleMas");
            JSONArray TEnRech = tier.getJSONArray("EnRech");
            JSONArray TCritRate = tier.getJSONArray("CritRate");
            JSONArray TCritDMG = tier.getJSONArray("CritDMG");

            for (int i = 0 ; i < baseHP.length() ; i++){
                artifactBasicBaseHP.add(i,baseHP.getDouble(i));
                artifactBasicBaseATK.add(i,baseATK.getDouble(i));
                artifactBasicHP.add(i,HP.getDouble(i));
                artifactBasicATK.add(i,ATK.getDouble(i));
                artifactBasicDEF.add(i,DEF.getDouble(i));
                artifactBasicEleMas.add(i,EleMas.getDouble(i));
                artifactBasicEnRech.add(i,EnRech.getDouble(i));
                artifactBasicPhyDMG.add(i,PhyDMG.getDouble(i));
                artifactBasicEleDMG.add(i,EleDMG.getDouble(i));
                artifactBasicCritRate.add(i,CritRate.getDouble(i));
                artifactBasicCritDMG.add(i,CritDMG.getDouble(i));
                artifactBasicHealing.add(i,Healing.getDouble(i));
            }

            for (int i = 0 ; i < TbaseHP.length() ; i++){
                artifactTierBaseHP.add(i,TbaseHP.getDouble(i));
                artifactTierBaseATK.add(i,TbaseATK.getDouble(i));
                artifactTierBaseDEF.add(i,TbaseDEF.getDouble(i));
                artifactTierHP.add(i,THP.getDouble(i));
                artifactTierATK.add(i,TATK.getDouble(i));
                artifactTierDEF.add(i,TDEF.getDouble(i));
                artifactTierEleMas.add(i,TEleMas.getDouble(i));
                artifactTierEnRech.add(i,TEnRech.getDouble(i));
                artifactTierCritRate.add(i,TCritRate.getDouble(i));
                artifactTierCritDMG.add(i,TCritDMG.getDouble(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public boolean checkDataIsExist(String path){
        File file = new File(context.getFilesDir()+"/"+path);
        if(file.isFile()){
            return file.exists();
        }
        return false;
    }


    private void enemy_list_reload() {
        Log.wtf("DAAM","YEE");
        String name ;
        int type_id,element_id;
        enemysList.clear();

        String json_base = LoadData("db/buff/enemy_list.json");
        //Get data from JSON
        try {
            JSONArray array = new JSONArray(json_base);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                name = object.getString("name");
                type_id = object.getInt("type_id");
                element_id = object.getInt("element_id");

                Enemys enemys = new Enemys();
                enemys.setName(name);
                enemys.setBaseName(name);
                enemys.setTypeId(type_id);
                enemys.setElementId(element_id);

                enemysList.add(enemys);
            }
            //mEnemyAdapter.filterList(enemysList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void saveData (String[] tmp_artifact_sort){
        SharedPreferences sharedPreferences = context.getSharedPreferences("buff_list",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("enemyName",buffCal.enemyName);
        editor.putInt("enemyLvl",buffCal.LvlEnemy);

        for (int x = 0 ; x< 5 ;x ++){
            if(tmp_artifact_sort[x] != null) {
                if (!tmp_artifact_sort[x].equals("")) {
                    editor.putString(dataSheetName+"_"+"artifactBuffMainItem" + String.valueOf(x), artifactBuffMainItem.get(x));
                    editor.putString(dataSheetName+"_"+"artifactBuffMainValue" + String.valueOf(x), String.valueOf(artifactBuffMainValue.get(x)));
                    editor.putString(dataSheetName+"_"+"artifactBuffSec1Item" + String.valueOf(x), artifactBuffSec1Item.get(x));
                    editor.putString(dataSheetName+"_"+"artifactBuffSec1Value" + String.valueOf(x), String.valueOf(artifactBuffSec1Value.get(x)));
                    editor.putString(dataSheetName+"_"+"artifactBuffSec2Item" + String.valueOf(x), artifactBuffSec2Item.get(x));
                    editor.putString(dataSheetName+"_"+"artifactBuffSec2Value" + String.valueOf(x), String.valueOf(artifactBuffSec2Value.get(x)));
                    editor.putString(dataSheetName+"_"+"artifactBuffSec3Item" + String.valueOf(x), artifactBuffSec3Item.get(x));
                    editor.putString(dataSheetName+"_"+"artifactBuffSec3Value" + String.valueOf(x), String.valueOf(artifactBuffSec3Value.get(x)));
                    editor.putString(dataSheetName+"_"+"artifactBuffSec4Item" + String.valueOf(x), artifactBuffSec4Item.get(x));
                    editor.putString(dataSheetName+"_"+"artifactBuffSec4Value" + String.valueOf(x), String.valueOf(artifactBuffSec4Value.get(x)));
                }
            }
        }

        editor.apply();
    }
}
