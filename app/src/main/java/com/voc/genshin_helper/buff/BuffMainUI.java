package com.voc.genshin_helper.buff;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.buff.db.BuffDBHelper;
import com.voc.genshin_helper.buff.obj.Artifact;
import com.voc.genshin_helper.buff.obj.BuffObject;
import com.voc.genshin_helper.buff.obj.Character;
import com.voc.genshin_helper.buff.obj.Weapon;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import org.apache.commons.lang3.ArrayUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuffMainUI extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<View> dynamicView = new ArrayList<>();
    ArrayList<BuffObject> buffObjects = new ArrayList<>();

    ItemRss item_rss;

    Context context;
    Activity activity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    BuffCatelogy buffCatelogy;

    TabLayout team_tablayout;
    DisplayMetrics displayMetrics;

    String SetName = "N/A";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_buff_main_ui_2048);
        context = this;
        activity = this;
        sharedPreferences = context.getSharedPreferences("user_info",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        item_rss = new ItemRss();
        buffCatelogy = new BuffCatelogy();
        SetName = (String) getIntent().getStringExtra("SetName");
        dynamicView.clear();

        team_tablayout = findViewById(R.id.team_tablayout);
        team_tablayout.removeAllTabs();
        displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        buffObjects = readDataBase(context,SetName);

        /*
        Just for checking
        for (int x = 0 ; x < buffObjects.size() ; x++){
            System.out.println(buffObjects.get(x).toString(0));
        }
        */

        TextView ui_title = findViewById(R.id.ui_title);
        ui_title.setText(SetName.replace("Set_",""));

        list_init();

        team_tablayout.setTabIndicatorFullWidth(false);

        viewPager = (ViewPager) findViewById(R.id.ui_vp);
        viewPager.setAdapter(new MyViewPagerAdapter(dynamicView));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float positionOffset, int positionOffsetPx) {
            }

            @Override
            public void onPageSelected(int position) {
                team_tablayout.selectTab(team_tablayout.getTabAt(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        team_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
                tab_icon.setForeground(context.getDrawable(R.drawable.bg_buff_tab_selected_kwang));
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
                tab_icon.setForeground(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
                tab_icon.setForeground(context.getDrawable(R.drawable.bg_buff_tab_selected_kwang));
                viewPager.setCurrentItem(tab.getPosition());

            }
        });

        viewPager.setCurrentItem(0);
        team_tablayout.selectTab(team_tablayout.getTabAt(0));

    }


    public ArrayList<BuffObject> readDataBase(Context context, String SetName) {
        BuffDBHelper dbHelper = new BuffDBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(SetName,null,null,null,null,null,null);
        ArrayList<BuffObject> buffObjects = new ArrayList<>();
        while (cursor.moveToNext()){
            switch (cursor.getString(cursor.getColumnIndexOrThrow("itemType"))){
                case "CHAR_ANEMO":
                case "CHAR_CRYO":
                case "CHAR_DENDRO":
                case "CHAR_HYDRO":
                case "CHAR_GEO":
                case "CHAR_ELECTRO":
                case "CHAR_PYRO":
                case "CHAR":{
                    Character character = new Character();
                    character.setCharId(cursor.getInt(cursor.getColumnIndexOrThrow("itemId")));
                    character.setCharName(cursor.getString(cursor.getColumnIndexOrThrow("itemName")));
                    character.setCharLvl(cursor.getInt(cursor.getColumnIndexOrThrow("itemLvl")));
                    character.setCharRare(cursor.getInt(cursor.getColumnIndexOrThrow("itemRare")));
                    character.setCharCon(cursor.getInt(cursor.getColumnIndexOrThrow("itemConstellationLvl")));
                    character.setCharElement(cursor.getString(cursor.getColumnIndexOrThrow("itemType")));
                    character.setCharEXP(cursor.getInt(cursor.getColumnIndexOrThrow("itemEXP")));
                    character.setCharTalentLvl(new int[]{
                            cursor.getInt(cursor.getColumnIndexOrThrow("charTalent1Lvl")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("charTalent2Lvl")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("charTalent3Lvl"))
                    });
                    character.setCharBaseHP(cursor.getDouble(cursor.getColumnIndexOrThrow("charBaseHP")));
                    character.setCharHP(cursor.getDouble(cursor.getColumnIndexOrThrow("charHP")));
                    character.setCharHPP(cursor.getDouble(cursor.getColumnIndexOrThrow("charHPP")));
                    character.setCharBaseATK(cursor.getDouble(cursor.getColumnIndexOrThrow("charBaseATK")));
                    character.setCharATK(cursor.getDouble(cursor.getColumnIndexOrThrow("charATK")));
                    character.setCharATKP(cursor.getDouble(cursor.getColumnIndexOrThrow("charATKP")));
                    character.setCharBaseDEF(cursor.getDouble(cursor.getColumnIndexOrThrow("charBaseDEF")));
                    character.setCharDEF(cursor.getDouble(cursor.getColumnIndexOrThrow("charDEF")));
                    character.setCharDEFP(cursor.getDouble(cursor.getColumnIndexOrThrow("charDEFP")));
                    character.setCharSpdP(cursor.getDouble(cursor.getColumnIndexOrThrow("charSpdP")));
                    character.setCharCritRate(cursor.getDouble(cursor.getColumnIndexOrThrow("charCritRate")));
                    character.setCharCritDMG(cursor.getDouble(cursor.getColumnIndexOrThrow("charCritDMG")));
                    character.setCharEnRech(cursor.getDouble(cursor.getColumnIndexOrThrow("charEnRech")));
                    character.setCharHealP(cursor.getDouble(cursor.getColumnIndexOrThrow("charHealP")));
                    character.setCharGotHealP(cursor.getDouble(cursor.getColumnIndexOrThrow("charGotHealP")));
                    character.setCharEleMas(cursor.getDouble(cursor.getColumnIndexOrThrow("charEleMas")));
                    character.setCharPhyRes(cursor.getDouble(cursor.getColumnIndexOrThrow("charPhyRes")));
                    character.setCharPhyDMGP(cursor.getDouble(cursor.getColumnIndexOrThrow("charPhyDMGP")));
                    character.setCharPyroDMGP(cursor.getDouble(cursor.getColumnIndexOrThrow("charPyroDMGP")));
                    character.setCharElectroDMGP(cursor.getDouble(cursor.getColumnIndexOrThrow("charElectroDMGP")));
                    character.setCharHydroDMGP(cursor.getDouble(cursor.getColumnIndexOrThrow("charHydroDMGP")));
                    character.setCharDendroDMGP(cursor.getDouble(cursor.getColumnIndexOrThrow("charDendroDMGP")));
                    character.setCharAnemoDMGP(cursor.getDouble(cursor.getColumnIndexOrThrow("charAnemoDMGP")));
                    character.setCharGeoDMGP(cursor.getDouble(cursor.getColumnIndexOrThrow("charGeoDMGP")));
                    character.setCharCryoDMGP(cursor.getDouble(cursor.getColumnIndexOrThrow("charCryoDMGP")));
                    character.setCharPyroResP(cursor.getDouble(cursor.getColumnIndexOrThrow("charPyroResP")));
                    character.setCharElectroResP(cursor.getDouble(cursor.getColumnIndexOrThrow("charElectroResP")));
                    character.setCharHydroResP(cursor.getDouble(cursor.getColumnIndexOrThrow("charHydroResP")));
                    character.setCharDendroResP(cursor.getDouble(cursor.getColumnIndexOrThrow("charDendroResP")));
                    character.setCharAnemoResP(cursor.getDouble(cursor.getColumnIndexOrThrow("charAnemoResP")));
                    character.setCharGeoResP(cursor.getDouble(cursor.getColumnIndexOrThrow("charGeoResP")));
                    character.setCharCryoResP(cursor.getDouble(cursor.getColumnIndexOrThrow("charCryoResP")));
                    character.setCharMaxHP(cursor.getDouble(cursor.getColumnIndexOrThrow("charMaxHP")));
                    character.setCharMaxATK(cursor.getDouble(cursor.getColumnIndexOrThrow("charMaxATK")));
                    character.setCharMaxDEF(cursor.getDouble(cursor.getColumnIndexOrThrow("charMaxDEF")));

                    BuffObject buffObject = new BuffObject();
                    buffObject.setCharacter(character);
                    buffObjects.add(buffObject);
                    break;
                }

                case "WEAPON_POLEARM":
                case "WEAPON_SWORD":
                case "WEAPON_CLAYMORE":
                case "WEAPON_BOW":
                case "WEAPON_CATALYST":
                case "WEAPON" :{
                    Weapon weapon = new Weapon();
                    weapon.setWeaponName(cursor.getString(cursor.getColumnIndexOrThrow("itemName")));
                    weapon.setWeaponId(cursor.getInt(cursor.getColumnIndexOrThrow("itemId")));
                    weapon.setWeaponLvl(cursor.getInt(cursor.getColumnIndexOrThrow("itemLvl")));
                    weapon.setWeaponAffixLvl(cursor.getInt(cursor.getColumnIndexOrThrow("itemAffixLvl")));
                    weapon.setWeaponASCLvl(cursor.getInt(cursor.getColumnIndexOrThrow("itemASCLvl")));
                    weapon.setWeaponRare(cursor.getInt(cursor.getColumnIndexOrThrow("itemRare")));
                    weapon.setWeaponType(cursor.getString(cursor.getColumnIndexOrThrow("itemType")));
                    weapon.setWeaponStatStr(new String[]{
                            cursor.getString(cursor.getColumnIndexOrThrow("itemMainStatusStr")),
                            cursor.getString(cursor.getColumnIndexOrThrow("itemSubStatus1Str"))
                    });
                    weapon.setWeaponStatValue(new double[]{
                            cursor.getDouble(cursor.getColumnIndexOrThrow("itemMainStatusValue")),
                            cursor.getDouble(cursor.getColumnIndexOrThrow("itemSubStatus1Value"))
                    });
                    weapon.setWeaponFollowId(cursor.getInt(cursor.getColumnIndexOrThrow("itemFollowCharId")));
                    weapon.setWeaponFollow(cursor.getString(cursor.getColumnIndexOrThrow("itemFollowChar")));

                    for (int x = 0 ; x < buffObjects.size() ; x++){
                        if(buffObjects.get(x).getCharacter().getCharId() == weapon.getWeaponFollowId()){
                            // Does it really refresh data ?
                            buffObjects.get(x).setWeapon(weapon);
                        }
                    }
                    break;
                }

                case "ARTIFACT_FLOWER" :
                case "ARTIFACT_PLUME" :
                case "ARTIFACT_SAND" :
                case "ARTIFACT_CIRCLET" :
                case "ARTIFACT_GOBLET" :
                case "ARTIFACT" :{
                    Artifact artifact = new Artifact();
                    artifact.setArtifactName(cursor.getString(cursor.getColumnIndexOrThrow("itemName")));
                    artifact.setArtifactId(cursor.getInt(cursor.getColumnIndexOrThrow("itemId")));
                    artifact.setArtifactLvl(cursor.getInt(cursor.getColumnIndexOrThrow("itemLvl")));
                    artifact.setArtifactRare(cursor.getInt(cursor.getColumnIndexOrThrow("itemRare")));
                    artifact.setArtifactType(cursor.getString(cursor.getColumnIndexOrThrow("itemType")));
                    artifact.setArtifactStatStr(new String[]{
                            cursor.getString(cursor.getColumnIndexOrThrow("itemMainStatusStr")),
                            cursor.getString(cursor.getColumnIndexOrThrow("itemSubStatus1Str")),
                            cursor.getString(cursor.getColumnIndexOrThrow("itemSubStatus2Str")),
                            cursor.getString(cursor.getColumnIndexOrThrow("itemSubStatus3Str")),
                            cursor.getString(cursor.getColumnIndexOrThrow("itemSubStatus4Str"))
                    });
                    artifact.setArtifactStatValue(new double[]{
                            cursor.getDouble(cursor.getColumnIndexOrThrow("itemMainStatusValue")),
                            cursor.getDouble(cursor.getColumnIndexOrThrow("itemSubStatus1Value")),
                            cursor.getDouble(cursor.getColumnIndexOrThrow("itemSubStatus2Value")),
                            cursor.getDouble(cursor.getColumnIndexOrThrow("itemSubStatus3Value")),
                            cursor.getDouble(cursor.getColumnIndexOrThrow("itemSubStatus4Value"))
                    });
                    artifact.setArtifactFollowId(cursor.getInt(cursor.getColumnIndexOrThrow("itemFollowCharId")));
                    artifact.setArtifactFollow(cursor.getString(cursor.getColumnIndexOrThrow("itemFollowChar")));

                    for (int x = 0 ; x < buffObjects.size() ; x++){
                        if(buffObjects.get(x).getCharacter().getCharId() == artifact.getArtifactFollowId()){
                            // Does it really refresh data ?
                            switch (artifact.getArtifactType()){
                                case Artifact.FLOWER: {
                                    buffObjects.get(x).setArtifactFlower(artifact);
                                    break;
                                }
                                case Artifact.PLUME: {
                                    buffObjects.get(x).setArtifactPlume(artifact);
                                    break;
                                }
                                case Artifact.SAND: {
                                    buffObjects.get(x).setArtifactSand(artifact);
                                    break;
                                }
                                case Artifact.CIRCLET: {
                                    buffObjects.get(x).setArtifactCirclet(artifact);
                                    break;
                                }
                                case Artifact.GOBLET: {
                                    buffObjects.get(x).setArtifactGoblet(artifact);
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
            }
            /*
            if(cursor.isLast()){
                for (int x = 0 ; x < buffObjects.size() ; x++){
                    LogExport.export("BuffMainUI","readDataBase() -> end of function", buffObjects.get(x).toString(0), context, BETA_TESTING);
                }
            }
             */
        }
        cursor.close();

        return buffObjects;
    }

    private void list_init() {
        final LayoutInflater mInflater = getLayoutInflater().from(this);
        final int radius = 180;
        final int margin = 0;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);

        for (int x = 0 ; x < buffObjects.size() ; x++){
            BuffObject buffObject = buffObjects.get(x);
            View view = mInflater.inflate(R.layout.fragment_buff_main_group_2048, null,false);
            ImageView buff_dmg_char_ico = view.findViewById(R.id.buff_dmg_char_ico);
            TextView buff_dmg_char_name = view.findViewById(R.id.buff_dmg_char_name);
            Spinner buff_dmg_char_lvl = view.findViewById(R.id.buff_dmg_char_lvl);
            Spinner buff_dmg_char_sof = view.findViewById(R.id.buff_dmg_char_sof);
            ImageView buff_dmg_char_talent1_ico = view.findViewById(R.id.buff_dmg_char_talent1_ico);
            ImageView buff_dmg_char_talent2_ico = view.findViewById(R.id.buff_dmg_char_talent2_ico);
            ImageView buff_dmg_char_talent3_ico = view.findViewById(R.id.buff_dmg_char_talent3_ico);
            Spinner buff_dmg_char_talent1_lvl = view.findViewById(R.id.buff_dmg_char_talent1_lvl);
            Spinner buff_dmg_char_talent2_lvl = view.findViewById(R.id.buff_dmg_char_talent2_lvl);
            Spinner buff_dmg_char_talent3_lvl = view.findViewById(R.id.buff_dmg_char_talent3_lvl);
            TextView buff_dmg_char_talent1_name = view.findViewById(R.id.buff_dmg_char_talent1_name);
            TextView buff_dmg_char_talent2_name = view.findViewById(R.id.buff_dmg_char_talent2_name);
            TextView buff_dmg_char_talent3_name = view.findViewById(R.id.buff_dmg_char_talent3_name);

            ImageView buff_dmg_weapon_ico = view.findViewById(R.id.buff_dmg_weapon_ico);
            TextView buff_dmg_weapon_name = view.findViewById(R.id.buff_dmg_weapon_name);
            Spinner buff_dmg_weapon_lvl = view.findViewById(R.id.buff_dmg_weapon_lvl);
            Spinner buff_dmg_weapon_affix_lvl = view.findViewById(R.id.buff_dmg_weapon_affix_lvl);

            ArrayAdapter char_lvl = new ArrayAdapter(context, R.layout.spinner_item_cal_2048, BuffCatelogy.lvlListChar);
            char_lvl.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);
            ArrayAdapter char_con = new ArrayAdapter(context, R.layout.spinner_item_cal_2048, BuffCatelogy.lvlListCharCon);
            char_con.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);
            ArrayAdapter char_skill = new ArrayAdapter(context, R.layout.spinner_item_cal_2048, BuffCatelogy.lvlListSkill);
            char_skill.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);
            ArrayAdapter weapon_lvl = new ArrayAdapter(context, R.layout.spinner_item_cal_2048, (buffObject.getWeapon().getWeaponRare() >= 3 ? BuffCatelogy.lvlListWeapon90 : BuffCatelogy.lvlListWeapon70));
            weapon_lvl.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);
            ArrayAdapter weapon_affix = new ArrayAdapter(context, R.layout.spinner_item_cal_2048, BuffCatelogy.lvlListWeaponAffix);
            weapon_affix.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);

            //Character
            Character character = buffObject.getCharacter();
            int talentBg = R.drawable.bg_colored_talent_pyro;
            int sofBg = R.drawable.bg_colored_sof_pyro;
            String CharName_BASE_UNDERSCORE = character.getCharName().replace(" ", "_");
            String lang = sharedPreferences.getString("curr_lang", "zh-HK");
            String is = null; String is_default = null; String result1 = null;

            is_default = LoadData("db/char/en-US/" + CharName_BASE_UNDERSCORE + ".json");
            is = LoadData("db/char/" + lang + "/" + CharName_BASE_UNDERSCORE + ".json");

            if (is != null) {
                result1 = is;
            } else if (is_default != null) {
                result1 = is_default;
            }

            switch (character.getCharRare()){
                case 1 : buff_dmg_char_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                case 2 : buff_dmg_char_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_2s);break;
                case 3 : buff_dmg_char_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_3s);break;
                case 4 : buff_dmg_char_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);break;
                case 5 : buff_dmg_char_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);break;
                default:  buff_dmg_char_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
            }
            switch (character.getCharElement()){
                case Character.ANEMO: talentBg = R.drawable.bg_colored_talent_anemo; sofBg = R.drawable.bg_colored_sof_anemo;break;
                case Character.CRYO: talentBg = R.drawable.bg_colored_talent_cryo; sofBg = R.drawable.bg_colored_sof_cryo;break;
                case Character.DENDRO: talentBg = R.drawable.bg_colored_talent_dendro; sofBg = R.drawable.bg_colored_sof_dendro;break;
                case Character.ELECTRO: talentBg = R.drawable.bg_colored_talent_electro; sofBg = R.drawable.bg_colored_sof_electro;break;
                case Character.GEO: talentBg = R.drawable.bg_colored_talent_geo; sofBg = R.drawable.bg_colored_sof_geo;break;
                case Character.HYDRO: talentBg = R.drawable.bg_colored_talent_hydro; sofBg = R.drawable.bg_colored_sof_hydro;break;
                case Character.PYRO: talentBg = R.drawable.bg_colored_talent_pyro; sofBg = R.drawable.bg_colored_sof_pyro;break;
                default: talentBg = R.drawable.bg_colored_talent_pyro; sofBg = R.drawable.bg_colored_sof_pyro;break;
            }
            Picasso.get()
                    .load (FileLoader.loadIMG(item_rss.getCharByName(character.getCharName(),context)[3],context))
                    .transform(transformation)
                    .fit()
                    .error (R.drawable.paimon_full)
                    .into (buff_dmg_char_ico);
            buff_dmg_char_name.setText(item_rss.getCharByName(character.getCharName(),context)[1]);

            buff_dmg_char_lvl.setAdapter(char_lvl);
            buff_dmg_char_sof.setAdapter(char_con);
            buff_dmg_char_talent1_lvl.setAdapter(char_skill);
            buff_dmg_char_talent2_lvl.setAdapter(char_skill);
            buff_dmg_char_talent3_lvl.setAdapter(char_skill);
            buff_dmg_char_lvl.setSelection(getLvlPosByList(character));
            buff_dmg_char_sof.setSelection(character.getCharCon());
            buff_dmg_char_talent1_lvl.setSelection(character.getCharTalentLvl()[0]-1);
            buff_dmg_char_talent2_lvl.setSelection(character.getCharTalentLvl()[1]-1);
            buff_dmg_char_talent3_lvl.setSelection(character.getCharTalentLvl()[2]-1);

            if (result1 != null) {
                try {
                    JSONObject jsonObject = new JSONObject(result1);
                    JSONObject battle_talent = jsonObject.getJSONObject("battle_talent");
                    buff_dmg_char_talent1_ico.setImageDrawable(item_rss.getTalentIcoByName(battle_talent.getString("normal_img"),context));
                    buff_dmg_char_talent2_ico.setImageDrawable(item_rss.getTalentIcoByName(battle_talent.getString("element_img"),context));
                    buff_dmg_char_talent3_ico.setImageDrawable(item_rss.getTalentIcoByName(battle_talent.getString("final_img"),context));
                    buff_dmg_char_talent1_name.setText(battle_talent.getString("normal_name"));
                    buff_dmg_char_talent2_name.setText(battle_talent.getString("element_name"));
                    buff_dmg_char_talent3_name.setText(battle_talent.getString("final_name"));
                    buff_dmg_char_talent1_ico.setBackgroundResource(talentBg);
                    buff_dmg_char_talent2_ico.setBackgroundResource(talentBg);
                    buff_dmg_char_talent3_ico.setBackgroundResource(talentBg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            int finalX = x;
            buff_dmg_char_lvl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    buffObjects.get(finalX).getCharacter().setCharLvl(getNumByStrPlus(BuffCatelogy.lvlListChar[position])[0]);
                    buffObjects.get(finalX).getCharacter().setCharASC(getNumByStrPlus(BuffCatelogy.lvlListChar[position])[1]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            buff_dmg_char_sof.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    buffObjects.get(finalX).getCharacter().setCharCon(Integer.parseInt(BuffCatelogy.lvlListCharCon[position]));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            buff_dmg_char_talent1_lvl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    buffObjects.get(finalX).getCharacter().setCharTalentLvl(new int[]{
                            Integer.parseInt(BuffCatelogy.lvlListSkill[position]),
                            buffObjects.get(finalX).getCharacter().getCharTalentLvl()[1],
                            buffObjects.get(finalX).getCharacter().getCharTalentLvl()[2]
                    });
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            buff_dmg_char_talent2_lvl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    buffObjects.get(finalX).getCharacter().setCharTalentLvl(new int[]{
                            buffObjects.get(finalX).getCharacter().getCharTalentLvl()[0],
                            Integer.parseInt(BuffCatelogy.lvlListSkill[position]),
                            buffObjects.get(finalX).getCharacter().getCharTalentLvl()[2]
                    });
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            buff_dmg_char_talent3_lvl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    buffObjects.get(finalX).getCharacter().setCharTalentLvl(new int[]{
                            buffObjects.get(finalX).getCharacter().getCharTalentLvl()[0],
                            buffObjects.get(finalX).getCharacter().getCharTalentLvl()[1],
                            Integer.parseInt(BuffCatelogy.lvlListSkill[position])
                    });
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //Weapon
            Weapon weapon = buffObject.getWeapon();
            String WeaponName_BASE_UNDERSCORE = weapon.getWeaponName().replace(" ", "").replace("'", "").replace("-", "").toLowerCase();
            if(WeaponName_BASE_UNDERSCORE.equals(weapon.getWeaponName().toLowerCase())){
                WeaponName_BASE_UNDERSCORE = WeaponName_BASE_UNDERSCORE.substring(0, 1).toUpperCase() + WeaponName_BASE_UNDERSCORE.substring(1).toLowerCase();
            }

            Picasso.get()
                    .load (FileLoader.loadIMG(item_rss.getWeaponByName(weapon.getWeaponName(),context)[1],context))
                    .transform(transformation)
                    .fit()
                    .error (R.drawable.paimon_full)
                    .into (buff_dmg_weapon_ico);
            switch (weapon.getWeaponRare()){
                case 1 : buff_dmg_weapon_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                case 2 : buff_dmg_weapon_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_2s);break;
                case 3 : buff_dmg_weapon_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_3s);break;
                case 4 : buff_dmg_weapon_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);break;
                case 5 : buff_dmg_weapon_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);break;
                default:  buff_dmg_weapon_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
            }
            buff_dmg_weapon_name.setText(item_rss.getWeaponByName(weapon.getWeaponName(),context)[0]);
            buff_dmg_weapon_lvl.setAdapter(weapon_lvl);
            buff_dmg_weapon_lvl.setSelection(getLvlPosByList(weapon));
            buff_dmg_weapon_affix_lvl.setAdapter(weapon_affix);
            buff_dmg_weapon_affix_lvl.setSelection(weapon.getWeaponAffixLvl()-1);

            buff_dmg_weapon_lvl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    buffObjects.get(finalX).getWeapon().setWeaponLvl(getNumByStrPlus(BuffCatelogy.lvlListChar[position])[0]);
                    buffObjects.get(finalX).getWeapon().setWeaponASCLvl(getNumByStrPlus(BuffCatelogy.lvlListChar[position])[1]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            buff_dmg_weapon_affix_lvl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    buffObjects.get(finalX).getWeapon().setWeaponAffixLvl(Integer.parseInt(BuffCatelogy.lvlListWeaponAffix[position]));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //Artifact
            Artifact[] artifacts = new Artifact[]{buffObject.getArtifactFlower(),buffObject.getArtifactPlume(),buffObject.getArtifactSand(),buffObject.getArtifactCirclet(),buffObject.getArtifactGoblet()};
            int[] buff_art_include_list = new int[]{R.id.buff_art_flower_include,R.id.buff_art_plume_include,R.id.buff_art_sand_include,R.id.buff_art_circlet_include,R.id.buff_art_goblet_include};
            String[][] art_rare_lvl_list = new String[][]{BuffCatelogy.lvlListArt4,BuffCatelogy.lvlListArt8,BuffCatelogy.lvlListArt12,BuffCatelogy.lvlListArt16,BuffCatelogy.lvlListArt20};
            String[] art_status_mix = new String[]{
                    context.getString(R.string.weapon_stat_HP),
                    context.getString(R.string.weapon_stat_atk),
                    context.getString(R.string.weapon_stat_DEF),
                    context.getString(R.string.weapon_stat_HPP),
                    context.getString(R.string.weapon_stat_atkP),
                    context.getString(R.string.weapon_stat_DEFP),
                    context.getString(R.string.weapon_stat_CritRateP),
                    context.getString(R.string.weapon_stat_CritDMGP),
                    context.getString(R.string.weapon_stat_EnRechP),
                    context.getString(R.string.weapon_stat_EleMas),
                    context.getString(R.string.weapon_stat_HealingP),
                    context.getString(R.string.weapon_stat_EleDMGP_Anemo),
                    context.getString(R.string.weapon_stat_EleDMGP_Cryo),
                    context.getString(R.string.weapon_stat_EleDMGP_Dendor),
                    context.getString(R.string.weapon_stat_EleDMGP_Electro),
                    context.getString(R.string.weapon_stat_EleDMGP_Geo),
                    context.getString(R.string.weapon_stat_EleDMGP_Hydro),
                    context.getString(R.string.weapon_stat_EleDMGP_Pyro),
                    context.getString(R.string.weapon_stat_PhyDMGP)
            };
            String[] art_status_mix_base = new String[]{
                    BuffObject.FIGHT_PROP_HP,
                    BuffObject.FIGHT_PROP_ATK,
                    BuffObject.FIGHT_PROP_DEF,
                    BuffObject.FIGHT_PROP_HP_P,
                    BuffObject.FIGHT_PROP_ATK_P,
                    BuffObject.FIGHT_PROP_DEF_P,
                    BuffObject.FIGHT_PROP_CRIT_RATE,
                    BuffObject.FIGHT_PROP_CRIT_DMG,
                    BuffObject.FIGHT_PROP_EN_RECH,
                    BuffObject.FIGHT_PROP_ELE_MAS,
                    BuffObject.FIGHT_PROP_HEAL_P,
                    BuffObject.FIGHT_PROP_ANEMO_DMG,
                    BuffObject.FIGHT_PROP_CRYO_DMG,
                    BuffObject.FIGHT_PROP_DENDRO_DMG,
                    BuffObject.FIGHT_PROP_ELECTRO_DMG,
                    BuffObject.FIGHT_PROP_GEO_DMG,
                    BuffObject.FIGHT_PROP_HYDRO_DMG,
                    BuffObject.FIGHT_PROP_PYRO_DMG,
                    BuffObject.FIGHT_PROP_PHY_DMG
            };

            String[] art_status_sub = new String[]{
                    context.getString(R.string.weapon_stat_HP),
                    context.getString(R.string.weapon_stat_atk),
                    context.getString(R.string.weapon_stat_DEF),
                    context.getString(R.string.weapon_stat_HPP),
                    context.getString(R.string.weapon_stat_atkP),
                    context.getString(R.string.weapon_stat_DEFP),
                    context.getString(R.string.weapon_stat_CritRateP),
                    context.getString(R.string.weapon_stat_CritDMGP),
                    context.getString(R.string.weapon_stat_EnRechP),
                    context.getString(R.string.weapon_stat_EleMas),
                    context.getString(R.string.weapon_stat_HealingP),
                    context.getString(R.string.weapon_stat_EleDMGP_Anemo),
                    context.getString(R.string.weapon_stat_EleDMGP_Cryo),
                    context.getString(R.string.weapon_stat_EleDMGP_Dendor),
                    context.getString(R.string.weapon_stat_EleDMGP_Electro),
                    context.getString(R.string.weapon_stat_EleDMGP_Geo),
                    context.getString(R.string.weapon_stat_EleDMGP_Hydro),
                    context.getString(R.string.weapon_stat_EleDMGP_Pyro),
                    context.getString(R.string.weapon_stat_PhyDMGP)
            };
            String[] art_status_sub_base = new String[]{
                    BuffObject.FIGHT_PROP_HP,
                    BuffObject.FIGHT_PROP_ATK,
                    BuffObject.FIGHT_PROP_DEF,
                    BuffObject.FIGHT_PROP_HP_P,
                    BuffObject.FIGHT_PROP_ATK_P,
                    BuffObject.FIGHT_PROP_DEF_P,
                    BuffObject.FIGHT_PROP_CRIT_RATE,
                    BuffObject.FIGHT_PROP_CRIT_DMG,
                    BuffObject.FIGHT_PROP_EN_RECH,
                    BuffObject.FIGHT_PROP_ELE_MAS,
                    BuffObject.FIGHT_PROP_HEAL_P,
                    BuffObject.FIGHT_PROP_ANEMO_DMG,
                    BuffObject.FIGHT_PROP_CRYO_DMG,
                    BuffObject.FIGHT_PROP_DENDRO_DMG,
                    BuffObject.FIGHT_PROP_ELECTRO_DMG,
                    BuffObject.FIGHT_PROP_GEO_DMG,
                    BuffObject.FIGHT_PROP_HYDRO_DMG,
                    BuffObject.FIGHT_PROP_PYRO_DMG,
                    BuffObject.FIGHT_PROP_PHY_DMG
            };
            String[] art_status_flower = new String[]{
                    context.getString(R.string.weapon_stat_HP)
            };
            String[] art_status_plume = new String[]{
                    context.getString(R.string.weapon_stat_atk)
            };
            String[] art_status_flower_base = new String[]{
                    BuffObject.FIGHT_PROP_HP
            };
            String[] art_status_plume_base = new String[]{
                    BuffObject.FIGHT_PROP_ATK
            };
            for (int y = 0 ; y < 5 ; y++){
                if(artifacts[y] != null) {
                    Artifact artifact = artifacts[y];
                    View buff_art_include = view.findViewById(buff_art_include_list[y]);
                    Spinner buff_art_main_status = buff_art_include.findViewById(R.id.buff_art_main_status);
                    Spinner buff_art_main_rare = buff_art_include.findViewById(R.id.buff_art_main_rare);
                    Spinner buff_art_main_lvl = buff_art_include.findViewById(R.id.buff_art_main_lvl);

                    LinearLayout buff_art_sub_status_ll = buff_art_include.findViewById(R.id.buff_art_sub_status_ll);
                    TextView buff_art_sub_status_1 = buff_art_include.findViewById(R.id.buff_art_sub_status_1);
                    TextView buff_art_sub_status_2 = buff_art_include.findViewById(R.id.buff_art_sub_status_2);
                    TextView buff_art_sub_status_3 = buff_art_include.findViewById(R.id.buff_art_sub_status_3);
                    TextView buff_art_sub_status_4 = buff_art_include.findViewById(R.id.buff_art_sub_status_4);


                    ArrayAdapter art_lvl = new ArrayAdapter(context, R.layout.spinner_item_cal_2048, art_rare_lvl_list[artifact.getArtifactRare() - 1]);
                    art_lvl.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);
                    buff_art_main_lvl.setAdapter(art_lvl);

                    ArrayAdapter art_rare = new ArrayAdapter(context, R.layout.spinner_item_cal_2048, BuffCatelogy.lvlListArtRare);
                    art_rare.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);
                    buff_art_main_rare.setAdapter(art_rare);

                    ArrayAdapter art_status = new ArrayAdapter(context, R.layout.spinner_item_cal_2048,
                            (artifact.getArtifactType().equals(Artifact.FLOWER) ? art_status_flower :
                                    artifact.getArtifactType().equals(Artifact.PLUME) ? art_status_plume : art_status_mix
                            ));
                    art_status.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);
                    buff_art_main_status.setAdapter(art_status);

                    buff_art_main_lvl.setSelection(artifact.getArtifactLvl()-1);
                    buff_art_main_rare.setSelection(artifact.getArtifactRare()-1);
                    buff_art_main_status.setSelection(Arrays.asList(
                            (artifact.getArtifactType().equals(Artifact.FLOWER) ? art_status_flower_base :
                            artifact.getArtifactType().equals(Artifact.PLUME) ? art_status_plume_base : art_status_mix_base
                    )).indexOf(artifact.getArtifactStatStr()[0]));

                    buff_art_main_lvl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            switch (artifact.getArtifactType()){
                                case Artifact.FLOWER: buffObjects.get(finalX).getArtifactFlower().setArtifactLvl(position+1);break;
                                case Artifact.PLUME: buffObjects.get(finalX).getArtifactPlume().setArtifactLvl(position+1);break;
                                case Artifact.SAND: buffObjects.get(finalX).getArtifactSand().setArtifactLvl(position+1);break;
                                case Artifact.CIRCLET: buffObjects.get(finalX).getArtifactCirclet().setArtifactLvl(position+1);break;
                                case Artifact.GOBLET: buffObjects.get(finalX).getArtifactGoblet().setArtifactLvl(position+1);break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    buff_art_main_rare.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            switch (artifact.getArtifactType()){
                                case Artifact.FLOWER: buffObjects.get(finalX).getArtifactFlower().setArtifactRare(position+1);break;
                                case Artifact.PLUME: buffObjects.get(finalX).getArtifactPlume().setArtifactRare(position+1);break;
                                case Artifact.SAND: buffObjects.get(finalX).getArtifactSand().setArtifactRare(position+1);break;
                                case Artifact.CIRCLET: buffObjects.get(finalX).getArtifactCirclet().setArtifactRare(position+1);break;
                                case Artifact.GOBLET: buffObjects.get(finalX).getArtifactGoblet().setArtifactRare(position+1);break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    buff_art_main_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            switch (artifact.getArtifactType()){
                                case Artifact.FLOWER: {
                                    String[] tmp = buffObjects.get(finalX).getArtifactFlower().getArtifactStatStr();
                                    tmp[0] = art_status_mix_base[position];
                                    buffObjects.get(finalX).getArtifactFlower().setArtifactStatStr(tmp);
                                    break;
                                }
                                case Artifact.PLUME: {
                                    String[] tmp = buffObjects.get(finalX).getArtifactPlume().getArtifactStatStr();
                                    tmp[0] = art_status_mix_base[position];
                                    buffObjects.get(finalX).getArtifactPlume().setArtifactStatStr(tmp);
                                    break;
                                }
                                case Artifact.SAND: {
                                    String[] tmp = buffObjects.get(finalX).getArtifactSand().getArtifactStatStr();
                                    tmp[0] = art_status_mix_base[position];
                                    buffObjects.get(finalX).getArtifactSand().setArtifactStatStr(tmp);
                                    break;
                                }
                                case Artifact.CIRCLET: {
                                    String[] tmp = buffObjects.get(finalX).getArtifactCirclet().getArtifactStatStr();
                                    tmp[0] = art_status_mix_base[position];
                                    buffObjects.get(finalX).getArtifactCirclet().setArtifactStatStr(tmp);
                                    break;
                                }
                                case Artifact.GOBLET: {
                                    String[] tmp = buffObjects.get(finalX).getArtifactGoblet().getArtifactStatStr();
                                    tmp[0] = art_status_mix_base[position];
                                    buffObjects.get(finalX).getArtifactGoblet().setArtifactStatStr(tmp);
                                    break;
                                }
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    buff_art_sub_status_ll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (artifact.getArtifactType()){
                                case Artifact.FLOWER: {
                                    buffObject.setArtifactPlume(buffStatusSelectDialog(context,buffObject.getArtifactFlower(),Artifact.FLOWER, buffCatelogy));
                                    break;
                                }
                                case Artifact.PLUME: {
                                    buffObject.setArtifactPlume(buffStatusSelectDialog(context,buffObject.getArtifactPlume(),Artifact.PLUME, buffCatelogy));
                                    break;
                                }
                                case Artifact.SAND: {
                                    buffObject.setArtifactPlume(buffStatusSelectDialog(context,buffObject.getArtifactSand(),Artifact.SAND, buffCatelogy));
                                    break;
                                }
                                case Artifact.CIRCLET: {
                                    buffObject.setArtifactPlume(buffStatusSelectDialog(context,buffObject.getArtifactCirclet(),Artifact.CIRCLET, buffCatelogy));
                                    break;
                                }
                                case Artifact.GOBLET: {
                                    buffObject.setArtifactPlume(buffStatusSelectDialog(context,buffObject.getArtifactGoblet(),Artifact.GOBLET, buffCatelogy));
                                    break;
                                }
                            }
                        }
                    });

                }

            }


            //TabLayout
            View view1 = activity.getLayoutInflater().inflate(R.layout.item_custom_tab, null);
            ImageView ico_img = view1.findViewById(R.id.icon);

            TextView ico_tv = view1.findViewById(R.id.name);
            ico_tv.setVisibility(View.GONE);

            Picasso.get()
                    .load (FileLoader.loadIMG(item_rss.getCharByName(character.getCharName(),context)[3],context))
                    .transform(transformation)
                    .resize((int) (48*displayMetrics.density),(int) (48*displayMetrics.density))
                    .error (R.drawable.paimon_full)
                    .into (ico_img);
            switch (character.getCharRare()){
                case 1 : ico_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                case 2 : ico_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_2s);break;
                case 3 : ico_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_3s);break;
                case 4 : ico_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);break;
                case 5 : ico_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);break;
                default:  ico_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
            }

            ico_img.setPadding((int) (displayMetrics.density*4),(int) (displayMetrics.density*4),(int) (displayMetrics.density*4),(int) (displayMetrics.density*4));
            team_tablayout.addTab(team_tablayout.newTab().setCustomView(view1).setId(x));

            dynamicView.add(view);
        }
    }

    public Artifact buffStatusSelectDialog(Context context, Artifact artifact, String TYPE, BuffCatelogy buffCatelogy){
        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.fragment_buff_artifact_dialog_2048, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        // 2O48 DESIGN
        dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_2048));
        dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = (int) (width*0.8);
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        dialog.show();

        int[] buff_set_tick_list = new int[]{R.id.buff_set_atk_tick,R.id.buff_set_hp_tick,R.id.buff_set_def_tick,R.id.buff_set_atkp_tick,R.id.buff_set_hpp_tick,R.id.buff_set_defp_tick,R.id.buff_set_crit_rate_tick,R.id.buff_set_crit_dmg_tick,R.id.buff_set_enrech_tick,R.id.buff_set_elemas_tick};
        int[] buff_set_increase_list = new int[]{R.id.buff_set_atk_increase,R.id.buff_set_hp_increase,R.id.buff_set_def_increase,R.id.buff_set_atkp_increase,R.id.buff_set_hpp_increase,R.id.buff_set_defp_increase,R.id.buff_set_crit_rate_increase,R.id.buff_set_crit_dmg_increase,R.id.buff_set_enrech_increase,R.id.buff_set_elemas_increase};
        int[] buff_set_reduce_list = new int[]{R.id.buff_set_atk_reduce,R.id.buff_set_hp_reduce,R.id.buff_set_def_reduce,R.id.buff_set_atkp_reduce,R.id.buff_set_hpp_reduce,R.id.buff_set_defp_reduce,R.id.buff_set_crit_rate_reduce,R.id.buff_set_crit_dmg_reduce,R.id.buff_set_enrech_reduce,R.id.buff_set_elemas_reduce};
        int[] buff_set_ll_list = new int[]{R.id.buff_set_atk_ll,R.id.buff_set_hp_ll,R.id.buff_set_def_ll,R.id.buff_set_atkp_ll,R.id.buff_set_hpp_ll,R.id.buff_set_defp_ll,R.id.buff_set_crit_rate_ll,R.id.buff_set_crit_dmg_ll,R.id.buff_set_enrech_ll,R.id.buff_set_elemas_ll};
        int[] buff_set_value_list = new int[]{R.id.buff_set_atk_value,R.id.buff_set_hp_value,R.id.buff_set_def_value,R.id.buff_set_atkp_value,R.id.buff_set_hpp_value,R.id.buff_set_defp_value,R.id.buff_set_crit_rate_value,R.id.buff_set_crit_dmg_value,R.id.buff_set_enrech_value,R.id.buff_set_elemas_value};
        int[] buff_set_seek_list = new int[]{R.id.buff_set_atk_seek,R.id.buff_set_hp_seek,R.id.buff_set_def_seek,R.id.buff_set_atkp_seek,R.id.buff_set_hpp_seek,R.id.buff_set_defp_seek,R.id.buff_set_crit_rate_seek,R.id.buff_set_crit_dmg_seek,R.id.buff_set_enrech_seek,R.id.buff_set_elemas_seek};

        String[] sub_status_name_tmp = new String[]{
            BuffObject.FIGHT_PROP_ATK, BuffObject.FIGHT_PROP_HP, BuffObject.FIGHT_PROP_DEF,
            BuffObject.FIGHT_PROP_ATK_P, BuffObject.FIGHT_PROP_HP_P, BuffObject.FIGHT_PROP_DEF_P,
            BuffObject.FIGHT_PROP_CRIT_RATE, BuffObject.FIGHT_PROP_CRIT_DMG, BuffObject.FIGHT_PROP_EN_RECH,BuffObject.FIGHT_PROP_ELE_MAS
        };

        ArrayList<String> sub_status_name = new ArrayList<>(Arrays.asList(sub_status_name_tmp));

        double[] itemValue = artifact.getArtifactStatValue();
        String[] itemStr = artifact.getArtifactStatStr();

        for (int x = 0 ; x < buff_set_ll_list.length ; x++){
            ImageView buff_set_tick = view.findViewById(buff_set_tick_list[x]);
            LinearLayout buff_set_ll = view.findViewById(buff_set_ll_list[x]);
            SeekBar buff_set_seek = view.findViewById(buff_set_seek_list[x]);
            TextView buff_set_value = view.findViewById(buff_set_value_list[x]);
            ImageView buff_set_increase = view.findViewById(buff_set_increase_list[x]);
            ImageView buff_set_reduce = view.findViewById(buff_set_reduce_list[x]);

            for (int y = 0 ; y < itemStr.length ; y ++){
                if (sub_status_name.contains(itemStr[y])){
                    buff_set_tick.setImageResource(R.drawable.ic_2048_need_tick);
                    buff_set_value.setText(String.valueOf(itemValue[y]));
                    double[] itemValueEnum = buffCatelogy.getArtifactStatusValue(artifact.getArtifactRare(),itemStr[y]);
                    ArrayList<Double> itemValueEnumArray = new ArrayList<>(Arrays.asList(ArrayUtils.toObject(itemValueEnum)));
                    for (double value : itemValueEnum){buff_set_seek.incrementProgressBy((int) (value*10));}
                    buff_set_seek.setMax((int) (itemValueEnum[itemValueEnum.length-1]*10));
                    buff_set_seek.setProgress((int) (itemValue[y]*10));

                    int finalY = y;
                    buff_set_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            buff_set_value.setText(String.valueOf(progress/10));
                            itemValue[finalY] = (double) (progress / 10);
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });

                    buff_set_increase.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int index = itemValueEnumArray.indexOf(itemValue[finalY]);
                            if (index != -1 && index < itemValueEnumArray.size()-1){
                                buff_set_seek.setProgress((int) (itemValueEnumArray.get(index+1)*10));
                            }
                        }
                    });

                    buff_set_reduce.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int index = itemValueEnumArray.indexOf(itemValue[finalY]);
                            if (index != -1 && index-1 >= 0){
                                buff_set_seek.setProgress((int) (itemValueEnumArray.get(index-1)*10));
                            }
                        }
                    });
                }
            }

        }

        return artifact;
    }


    public String prettyCountY(Number number, boolean isInt) {
        String suffix = "";
        if(isInt){
            return new DecimalFormat("#,###").format(number.longValue());
        }else{
            return new DecimalFormat("#,###.#").format(number.doubleValue()*100)+"%";
        }
    }

    public String prettyBuffCount(Number number, String buffTag) {
        String suffix = "";
        if(
                buffTag.equals(BuffObject.FIGHT_PROP_ATK) ||
                buffTag.equals(BuffObject.FIGHT_PROP_DEF) ||
                buffTag.equals(BuffObject.FIGHT_PROP_HP) ||
                buffTag.equals(BuffObject.FIGHT_PROP_BASE_ATK) ||
                buffTag.equals(BuffObject.FIGHT_PROP_ELE_MAS)){
            if(number.longValue() < 1000){
                return "+"+ new DecimalFormat("#,###.#").format(number);
            }else{
                return "+"+ new DecimalFormat("#,###").format(number);
            }
        }
        return "+"+new DecimalFormat("#,###.#").format(number)+"%";
    }

    public SpannableString setSpanAndTv(String str, ArrayList<String> value){
        SpannableString mSpannavleString = new SpannableString(str);

        for (int x = 0 ; x < value.size() ; x++){
            for (int i = -1; (i = str.indexOf(value.get(x), i + 1)) != -1; i++) {
                mSpannavleString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.buff_tv_main_2048)),str.indexOf(value.get(x)),str.indexOf(value.get(x))+value.get(x).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return mSpannavleString;
    }

    public String LoadData(String inFile) {
        String tContents = "";
        try {
            File file = new File(context.getFilesDir() + "/" + inFile);
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

    private Integer[] getNumByStrPlus(String numStr) {
        int num = Integer.parseInt(numStr.replace("+", ""));
        int breakNum = 0;
        int isBreak = 0; // just like boolean

        if(num <= 20){breakNum = 0;}
        else if(num <= 40){breakNum = 1;}
        else if(num <= 50){breakNum = 2;}
        else if(num <= 60){breakNum = 3;}
        else if(num <= 70){breakNum = 4;}
        else if(num <= 80){breakNum = 5;}
        else if(num <= 90){breakNum = 6;}

        if (numStr.contains("+")) {
            switch (numStr) {
                case "20+":
                case "40+":
                case "50+":
                case "60+":
                case "70+":
                case "80+":
                    isBreak = 1;
                    break;
            }
        }
        return new Integer[]{num, breakNum,isBreak};
    }

    private int getLvlPosByList(Character character) {
        int lvlNum = character.getCharLvl();
        boolean isBreakUp = (character.getCharLvl() == BuffCatelogy.charLvlBreak[character.getCharASC() + 1]);
        int realLvl = lvlNum - 1;
        if (lvlNum > 20 || lvlNum == 20 && isBreakUp) {
            realLvl = realLvl + 1;
        }
        if (lvlNum > 40 || lvlNum == 40 && isBreakUp) {
            realLvl = realLvl + 1;
        }
        if (lvlNum > 50 || lvlNum == 50 && isBreakUp) {
            realLvl = realLvl + 1;
        }
        if (lvlNum > 60 || lvlNum == 60 && isBreakUp) {
            realLvl = realLvl + 1;
        }
        if (lvlNum > 70 || lvlNum == 70 && isBreakUp) {
            realLvl = realLvl + 1;
        }
        if (lvlNum > 80 || lvlNum == 80 && isBreakUp) {
            realLvl = realLvl + 1;
        }
        return realLvl;
    }
    private int getLvlPosByList(Weapon weapon) {
        int lvlNum = weapon.getWeaponLvl();
        boolean isBreakUp = (weapon.getWeaponLvl() == BuffCatelogy.weaponLvlBreak[weapon.getWeaponASCLvl() + 1]);
        int realLvl = lvlNum - 1;
        if (lvlNum > 20 || lvlNum == 20 && isBreakUp) {
            realLvl = realLvl + 1;
        }
        if (lvlNum > 40 || lvlNum == 40 && isBreakUp) {
            realLvl = realLvl + 1;
        }
        if (lvlNum > 50 || lvlNum == 50 && isBreakUp) {
            realLvl = realLvl + 1;
        }
        if (lvlNum > 60 || lvlNum == 60 && isBreakUp) {
            realLvl = realLvl + 1;
        }
        if (lvlNum > 70 || lvlNum == 70 && isBreakUp) {
            realLvl = realLvl + 1;
        }
        if (lvlNum > 80 || lvlNum == 80 && isBreakUp) {
            realLvl = realLvl + 1;
        }
        return realLvl;
    }


    public class MyViewPagerAdapter extends PagerAdapter {
        private List<View> mListViews;

        public MyViewPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mListViews.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

    }
}
