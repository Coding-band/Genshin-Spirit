package com.voc.genshin_helper.buff;/*
 * Package com.voc.genshin_helper.buff.BuffCal was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ArtifactsBuff;
import com.voc.genshin_helper.data.ItemRss;

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

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

public class BuffCal2 {

    /**
     * System var
     */
    private Context context;
    ItemRss itemRss;
    ArtifactsBuff art;

    /**
     * FINAL METHOD
     */

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
    double[][] 元素戰技_value;

    String[] 元素爆發_baseName ;
    double[][] 元素爆發_value;

    double 武器基礎攻擊力 = 0;
    double 武器百分比攻擊力 = 0;
    double 武器百分比防禦力 = 0;
    double 武器百分比生命值 = 0;
    double 武器百分比暴擊率 = 0;
    double 武器百分比暴擊傷害 = 0;
    double 武器百分比元素充能 = 0;
    double 武器元素精通 = 0;
    double 武器百分比物理傷害加成 = 0;

    double 聖遺物雷元素傷害加成 = 0;
    double 聖遺物火元素傷害加成 = 0;
    double 聖遺物水元素傷害加成 = 0;
    double 聖遺物草元素傷害加成 = 0;
    double 聖遺物冰元素傷害加成 = 0;
    double 聖遺物風元素傷害加成 = 0;
    double 聖遺物岩元素傷害加成 = 0;
    double 聖遺物暴擊傷害 = 0;
    double 聖遺物暴擊率 = 0;
    double 聖遺物防禦力 = 0;
    double 聖遺物元素精通 = 0;
    double 聖遺物元素充能效率 = 0;
    double 聖遺物生命值 = 0;
    double 聖遺物物理傷害 = 0;
    double 聖遺物基礎攻擊力 = 0;
    double 聖遺物基礎防禦力 = 0;
    double 聖遺物基礎生命值 = 0;
    double 聖遺物攻擊力 = 0;
    double 聖遺物治療加成 = 0;

    /**
     * Help for txt
     */

    String Pyro = "Pyro";
    String Cryo = "Cryo";
    String Hydro = "Hydro";
    String Geo = "Geo";
    String Dendro = "Dendro";
    String Anemo = "Anemo";
    String Electro = "Electro";
    String PHY = "Physical";


    // Have to wait for adding diff element's DMG
    double atk_final,hp_final,def_final,critRate_final,critDMG_final,enRech,phyDMG;

    String weaponName;
    int weaponAfterLvl, weaponAfterBreakLvl;
    boolean weaponAfterBreakUP;

    String charName;
    int charAfterLvl, charAfterBreakLvl,charSkill1AfterLvl,charSkill2AfterLvl,charSkill3AfterLvl;
    boolean charAfterBreakUP;
    String statBuff;

    String[] artifactName;
    int[] artifactAfterLvl ;

    String enemyName = "Hilichurl" ;
    int LvlCharacter ;
    int LvlEnemy = 60;

    int tmp_break = 0;
    int weapon_tmp_break = 0;
    String otherName;
    double otherVal;

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

    /**
     * General methods of Damage Formula
     * https://library.keqingmains.com/combat-mechanics/damage/damage-formula
     * https://ngabbs.com/read.php?tid=23611714&_fp=2&rand=157
     *
     */

    /**
     * String[] artifactList will used in whole BuffCal2 !
     * Format : ["artifactP2Name1","artifactP4Name1","artifactP2Name2","weaponType"]
     * "weaponType" must use These words : "Sword","Claymore","Polearm","Catalyst","Bow"
     */

    //============================================================================//

    public void setup (Context context){
        this.context = context;
        itemRss = new ItemRss();
        art = new ArtifactsBuff();
        art.setup(context);

        SharedPreferences sharedPreferencesB = context.getSharedPreferences("buff_list",Context.MODE_PRIVATE);
        enemyName = sharedPreferencesB.getString("enemyName","Hilichurl");
        LvlEnemy = sharedPreferencesB.getInt("enemyLvl",1);
    }

    public void enemy_setup(String name, int lvl){
        // Will use Locale
        enemyName = name;
        LvlEnemy = lvl;
        SharedPreferences sharedPreferencesB = context.getSharedPreferences("buff_list",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesB.edit();
        editor.putString("enemyName",name);
        editor.putInt("enemyLvl",lvl);
        editor.apply();
    }

    public void char_setup(String charName, int charAfterLvl,  int charAfterBreakLvl, boolean charAfterBreakUP, int charSkill1AfterLvl, int charSkill2AfterLvl, int charSkill3AfterLvl) {
        this.charName = charName;
        this.charAfterLvl = charAfterLvl;
        this.charAfterBreakLvl = charAfterBreakLvl;
        this.charAfterBreakUP = charAfterBreakUP;
        this.charSkill1AfterLvl = charSkill1AfterLvl;
        this.charSkill2AfterLvl = charSkill2AfterLvl;
        this.charSkill3AfterLvl = charSkill3AfterLvl;

        LvlCharacter = charAfterLvl;
    }
    public void weapon_setup(String weaponName, int weaponAfterLvl,  int weaponAfterBreakLvl, boolean weaponAfterBreakUP) {
        this.weaponName = weaponName;
        this.weaponAfterLvl = weaponAfterLvl;
        this.weaponAfterBreakLvl = weaponAfterBreakLvl;
        this.weaponAfterBreakUP = weaponAfterBreakUP;
    }

    // Need to add their main and sub stat...
    public void artifact_setup(String[] artifactName, int[] artifactAfterLvl, ArrayList<Double> artifactBuffMainValue, ArrayList<String> artifactBuffMainItem, ArrayList<Double> artifactBuffSec1Value, ArrayList<String> artifactBuffSec1Item, ArrayList<Double> artifactBuffSec2Value, ArrayList<String> artifactBuffSec2Item, ArrayList<Double> artifactBuffSec3Value, ArrayList<String> artifactBuffSec3Item, ArrayList<Double> artifactBuffSec4Value, ArrayList<String> artifactBuffSec4Item) {
        this.artifactName = artifactName;
        this.artifactAfterLvl = artifactAfterLvl;
        this.artifactBuffMainItem = artifactBuffMainItem;
        this.artifactBuffMainValue = artifactBuffMainValue;
        this.artifactBuffSec1Item = artifactBuffSec1Item;
        this.artifactBuffSec1Value = artifactBuffSec1Value;
        this.artifactBuffSec2Item = artifactBuffSec2Item;
        this.artifactBuffSec2Value = artifactBuffSec2Value;
        this.artifactBuffSec3Item = artifactBuffSec3Item;
        this.artifactBuffSec3Value = artifactBuffSec3Value;
        this.artifactBuffSec4Item = artifactBuffSec4Item;
        this.artifactBuffSec4Value = artifactBuffSec4Value;

        聖遺物雷元素傷害加成 = 0;
        聖遺物火元素傷害加成 = 0;
        聖遺物水元素傷害加成 = 0;
        聖遺物草元素傷害加成 = 0;
        聖遺物冰元素傷害加成 = 0;
        聖遺物風元素傷害加成 = 0;
        聖遺物岩元素傷害加成 = 0;
        聖遺物暴擊傷害 = 0;
        聖遺物暴擊率 = 0;
        聖遺物防禦力 = 0;
        聖遺物元素精通 = 0;
        聖遺物元素充能效率 = 0;
        聖遺物生命值 = 0;
        聖遺物物理傷害 = 0;
        聖遺物基礎攻擊力 = 0;
        聖遺物基礎防禦力 = 0;
        聖遺物基礎生命值 = 0;
        聖遺物攻擊力 = 0;
        聖遺物治療加成 = 0;

        for (int x = 0 ; x < 5 ; x++){
            ArtifactBuff(artifactBuffMainItem.get(x),artifactBuffMainValue.get(x));
            ArtifactBuff(artifactBuffSec1Item.get(x),artifactBuffSec1Value.get(x));
            ArtifactBuff(artifactBuffSec2Item.get(x),artifactBuffSec2Value.get(x));
            ArtifactBuff(artifactBuffSec3Item.get(x),artifactBuffSec3Value.get(x));
            ArtifactBuff(artifactBuffSec4Item.get(x),artifactBuffSec4Value.get(x));
        }

        /*
        System.out.println("聖遺物雷元素傷害加成" + prettyCount(聖遺物雷元素傷害加成,1));
        System.out.println("聖遺物火元素傷害加成" + prettyCount(聖遺物火元素傷害加成,1));
        System.out.println("聖遺物水元素傷害加成" + prettyCount(聖遺物水元素傷害加成,1));
        System.out.println("聖遺物草元素傷害加成" + prettyCount(聖遺物草元素傷害加成,1));
        System.out.println("聖遺物冰元素傷害加成" + prettyCount(聖遺物冰元素傷害加成,1));
        System.out.println("聖遺物風元素傷害加成" + prettyCount(聖遺物風元素傷害加成,1));
        System.out.println("聖遺物岩元素傷害加成" + prettyCount(聖遺物岩元素傷害加成,1));
        System.out.println("聖遺物暴擊傷害" + prettyCount(聖遺物暴擊傷害,1));
        System.out.println("聖遺物暴擊率" + prettyCount(聖遺物暴擊率,1));
        System.out.println("聖遺物防禦力" + prettyCount(聖遺物防禦力,1));
        System.out.println("聖遺物元素精通" + prettyCount(聖遺物元素精通,0));
        System.out.println("聖遺物元素充能效率" + prettyCount(聖遺物元素充能效率,1));
        System.out.println("聖遺物生命值" + prettyCount(聖遺物生命值,1));
        System.out.println("聖遺物物理傷害" + prettyCount(聖遺物物理傷害,1));
        System.out.println("聖遺物基礎攻擊力" + prettyCount(聖遺物基礎攻擊力,0));
        System.out.println("聖遺物基礎防禦力" + prettyCount(聖遺物基礎防禦力,0));
        System.out.println("聖遺物基礎生命值" + prettyCount(聖遺物基礎生命值,0));
        System.out.println("聖遺物攻擊力" + prettyCount(聖遺物攻擊力,1));
        System.out.println("聖遺物治療加成" + prettyCount(聖遺物治療加成,1));
         */

    }

    public void startReading (){
        /**
         * Char Part
         */
        String char_json_base = LoadData("db/char/char_list.json");
        try {
            JSONArray array = new JSONArray(char_json_base);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String name = object.getString("name");
                if(name.equals(charName)){
                    statBuff = object.getString("statBuff");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int breakUP = 0;
        if(charAfterBreakUP){
            breakUP = 1;
        }
        if(charAfterLvl < 20){tmp_break=0;}
        else if(charAfterLvl == 20 && charAfterBreakUP == false){tmp_break=1;}
        else if(charAfterLvl >= 20 && charAfterLvl < 40 && charAfterBreakUP == true){tmp_break=2;}
        else if(charAfterLvl == 40 && charAfterBreakUP == false){tmp_break=3;}
        else if(charAfterLvl >= 40 && charAfterLvl < 50 && charAfterBreakUP == true){tmp_break=4;}
        else if(charAfterLvl == 50 && charAfterBreakUP == false){tmp_break=5;}
        else if(charAfterLvl >= 50 && charAfterLvl < 60 && charAfterBreakUP == true){tmp_break=6;}
        else if(charAfterLvl == 60 && charAfterBreakUP == false){tmp_break=7;}
        else if(charAfterLvl >= 60 && charAfterLvl < 70 && charAfterBreakUP == true){tmp_break=8;}
        else if(charAfterLvl == 70 && charAfterBreakUP == false){tmp_break=9;}
        else if(charAfterLvl >= 70 && charAfterLvl < 80 && charAfterBreakUP == true){tmp_break=10;}
        else if(charAfterLvl == 80 && charAfterBreakUP == false){tmp_break=11;}
        else if(charAfterLvl >= 80 && charAfterLvl < 90 && charAfterBreakUP == true){tmp_break=12;}
        else if(charAfterLvl == 90 && charAfterBreakUP == false){tmp_break=13;}

        if(weaponAfterLvl < 5){weapon_tmp_break=0;}
        else if(weaponAfterLvl <10){weapon_tmp_break=1;}
        else if(weaponAfterLvl <15){weapon_tmp_break=2;}
        else if(weaponAfterLvl <20){weapon_tmp_break=3;}
        else if(weaponAfterLvl == 20 && weaponAfterBreakUP == false){weapon_tmp_break=4;}
        else if(weaponAfterLvl <25){weapon_tmp_break=5;}
        else if(weaponAfterLvl <30){weapon_tmp_break=6;}
        else if(weaponAfterLvl <35){weapon_tmp_break=7;}
        else if(weaponAfterLvl <40){weapon_tmp_break=8;}
        else if(weaponAfterLvl == 40 && weaponAfterBreakUP == false){weapon_tmp_break=9;}
        else if(weaponAfterLvl <45){weapon_tmp_break=10;}
        else if(weaponAfterLvl <50){weapon_tmp_break=11;}
        else if(weaponAfterLvl == 50 && weaponAfterBreakUP == false){weapon_tmp_break=12;}
        else if(weaponAfterLvl <55){weapon_tmp_break=13;}
        else if(weaponAfterLvl <60){weapon_tmp_break=14;}
        else if(weaponAfterLvl == 60 && weaponAfterBreakUP == false){weapon_tmp_break=15;}
        else if(weaponAfterLvl <65){weapon_tmp_break=16;}
        else if(weaponAfterLvl <70){weapon_tmp_break=17;}
        else if(weaponAfterLvl == 70 && weaponAfterBreakUP == false){weapon_tmp_break=18;}
        else if(weaponAfterLvl <75){weapon_tmp_break=19;}
        else if(weaponAfterLvl <80){weapon_tmp_break=20;}
        else if(weaponAfterLvl == 80 && weaponAfterBreakUP == false){weapon_tmp_break=21;}
        else if(weaponAfterLvl <85){weapon_tmp_break=22;}
        else if(weaponAfterLvl <90){weapon_tmp_break=23;}
        else if(weaponAfterLvl == 90 && weaponAfterBreakUP == false){weapon_tmp_break=24;}


        String char_json_stat = LoadData("db/buff/char/"+charName.replace(" ","_")+".json");

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

        try {
            JSONObject jsonObject = new JSONObject(char_json_stat);
            JSONObject 普通攻擊 = jsonObject.getJSONObject("普通攻擊");

            JSONArray 一段傷害 = 普通攻擊.getJSONArray("一段傷害");
            普通攻擊_一段傷害 = 一段傷害.getDouble(charSkill1AfterLvl-1);

            JSONArray 二段傷害 = 普通攻擊.getJSONArray("二段傷害");
            普通攻擊_二段傷害 = 二段傷害.getDouble(charSkill1AfterLvl-1);

            JSONArray 三段傷害 = 普通攻擊.getJSONArray("三段傷害");
            普通攻擊_三段傷害 = 三段傷害.getDouble(charSkill1AfterLvl-1);

            JSONArray 四段傷害 = 普通攻擊.getJSONArray("四段傷害");
            普通攻擊_四段傷害 = 四段傷害.getDouble(charSkill1AfterLvl-1);

            JSONArray 五段傷害 = 普通攻擊.getJSONArray("五段傷害");
            普通攻擊_五段傷害 = 五段傷害.getDouble(charSkill1AfterLvl-1);

            JSONArray 六段傷害 = 普通攻擊.getJSONArray("六段傷害");
            普通攻擊_六段傷害 = 六段傷害.getDouble(charSkill1AfterLvl-1);

            JSONArray 下墜期間傷害 = 普通攻擊.getJSONArray("下墜期間傷害");
            普通攻擊_下墜期間傷害 = 下墜期間傷害.getDouble(charSkill1AfterLvl-1);

            JSONArray 低空墜地衝擊傷害 = 普通攻擊.getJSONArray("低空墜地衝擊傷害");
            普通攻擊_低空墜地衝擊傷害 = 低空墜地衝擊傷害.getDouble(charSkill1AfterLvl-1);

            JSONArray 高空墜地衝擊傷害 = 普通攻擊.getJSONArray("高空墜地衝擊傷害");
            普通攻擊_高空墜地衝擊傷害 = 高空墜地衝擊傷害.getDouble(charSkill1AfterLvl-1);

            JSONArray 瞄準射擊 = 普通攻擊.getJSONArray("瞄準射擊");
            普通攻擊_瞄準射擊 = 瞄準射擊.getDouble(charSkill1AfterLvl-1);

            JSONArray 滿蓄力瞄準射擊 = 普通攻擊.getJSONArray("滿蓄力瞄準射擊");
            普通攻擊_滿蓄力瞄準射擊 = 滿蓄力瞄準射擊.getDouble(charSkill1AfterLvl-1);

            JSONArray 重擊傷害 = 普通攻擊.getJSONArray("重擊傷害");
            普通攻擊_重擊傷害 = 重擊傷害.getDouble(charSkill1AfterLvl-1);

            JSONArray 重擊循環傷害 = 普通攻擊.getJSONArray("重擊循環傷害");
            普通攻擊_重擊循環傷害 = 重擊循環傷害.getDouble(charSkill1AfterLvl-1);

            JSONArray 重擊終結傷害 = 普通攻擊.getJSONArray("重擊終結傷害");
            普通攻擊_重擊終結傷害 = 重擊終結傷害.getDouble(charSkill1AfterLvl-1);

            //----- Special For Some Char (Ganyu, Yoimiya, Itto)

            // Ganyu
            if(普通攻擊.has("一段蓄力瞄準射擊") && 普通攻擊.has("霜華矢命中傷害") && 普通攻擊.has("霜華矢·霜華綻發傷害")){
                JSONArray 一段蓄力瞄準射擊 = 普通攻擊.getJSONArray("一段蓄力瞄準射擊");
                普通攻擊_一段蓄力瞄準射擊 = 一段蓄力瞄準射擊.getDouble(charSkill1AfterLvl-1);

                JSONArray 霜華矢命中傷害 = 普通攻擊.getJSONArray("霜華矢命中傷害");
                普通攻擊_霜華矢命中傷害 = 霜華矢命中傷害.getDouble(charSkill1AfterLvl-1);

                JSONArray 霜華矢_霜華綻發傷害 = 普通攻擊.getJSONArray("霜華矢·霜華綻發傷害");
                普通攻擊_霜華矢_霜華綻發傷害 = 霜華矢_霜華綻發傷害.getDouble(charSkill1AfterLvl-1);
            }

            // Yoimiya
            if(普通攻擊.has("焰硝矢傷害") ) {
                JSONArray 焰硝矢傷害 = 普通攻擊.getJSONArray("焰硝矢傷害");
                普通攻擊_焰硝矢傷害 = 焰硝矢傷害.getDouble(charSkill1AfterLvl - 1) ;
            }

            // Itto
            if(普通攻擊.has("荒瀧逆袈裟連斬傷害") && 普通攻擊.has("荒瀧逆袈裟終結傷害") && 普通攻擊.has("左一文字斬傷害")){
                JSONArray 荒瀧逆袈裟連斬傷害 = 普通攻擊.getJSONArray("荒瀧逆袈裟連斬傷害");
                普通攻擊_荒瀧逆袈裟連斬傷害 = 荒瀧逆袈裟連斬傷害.getDouble(charSkill1AfterLvl-1);

                JSONArray 荒瀧逆袈裟終結傷害 = 普通攻擊.getJSONArray("荒瀧逆袈裟終結傷害");
                普通攻擊_荒瀧逆袈裟終結傷害 = 荒瀧逆袈裟終結傷害.getDouble(charSkill1AfterLvl-1);

                JSONArray 左一文字斬傷害 = 普通攻擊.getJSONArray("左一文字斬傷害");
                普通攻擊_左一文字斬傷害 = 左一文字斬傷害.getDouble(charSkill1AfterLvl-1);
            }

            // Tartaglia
            if(普通攻擊.has("斷流·閃 傷害") && 普通攻擊.has("斷流·破 傷害")){
                JSONArray 斷流_閃_傷害 = 普通攻擊.getJSONArray("斷流·閃 傷害");
                普通攻擊_斷流_閃_傷害 = 斷流_閃_傷害.getDouble(charSkill1AfterLvl-1);

                JSONArray 斷流_破_傷害 = 普通攻擊.getJSONArray("斷流·破 傷害");
                普通攻擊_斷流_破_傷害 = 斷流_破_傷害.getDouble(charSkill1AfterLvl-1);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


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

        /**
         * Weapon Part
         */

        String weapon_json = LoadData("db/buff/weapons/"+weaponName.replace(" ","_").replace("'","").replace("-","_")+".json");
        try {
            JSONObject jsonObject = new JSONObject(weapon_json);

            JSONArray 基礎攻擊力 = jsonObject.getJSONArray("基礎攻擊力");
            武器基礎攻擊力 = 基礎攻擊力.getDouble(weapon_tmp_break);

            JSONArray 生命值加成 = jsonObject.getJSONArray("生命值加成");
            武器百分比生命值 = 生命值加成.getDouble(weapon_tmp_break);

            JSONArray 攻擊力加成 = jsonObject.getJSONArray("攻擊力加成");
            武器百分比攻擊力 = 攻擊力加成.getDouble(weapon_tmp_break);

            JSONArray 防禦力加成 = jsonObject.getJSONArray("防禦力加成");
            武器百分比防禦力 = 防禦力加成.getDouble(weapon_tmp_break);

            JSONArray 暴擊率 = jsonObject.getJSONArray("暴擊率");
            武器百分比暴擊率 = 暴擊率.getDouble(weapon_tmp_break);

            JSONArray 暴擊傷害 = jsonObject.getJSONArray("暴擊傷害");
            武器百分比暴擊傷害 = 暴擊傷害.getDouble(weapon_tmp_break);

            JSONArray 元素充能 = jsonObject.getJSONArray("元素充能");
            武器百分比元素充能 = 元素充能.getDouble(weapon_tmp_break);

            JSONArray 元素精通 = jsonObject.getJSONArray("元素精通");
            武器元素精通 = 元素精通.getDouble(weapon_tmp_break);

            JSONArray 物理傷害加成 = jsonObject.getJSONArray("物理傷害加成");
            武器百分比物理傷害加成 = 物理傷害加成.getDouble(weapon_tmp_break);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    //元素戰技.length()
    public int returnElementATKArraySize(){
        String char_json_stat = LoadData("db/buff/char/"+charName.replace(" ","_")+".json");
        try {
            JSONObject jsonObject = new JSONObject(char_json_stat);
            JSONArray 元素戰技STR = jsonObject.getJSONArray("元素戰技STR");
            return 元素戰技STR.length();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //元素戰技.length()
    public String[] returnElementATKArray(){
        return 元素戰技_baseName;
    }
    //元素爆發.length()
    public String[] returnFinalATKArray(){
        return 元素爆發_baseName;
    }

    //元素爆發.length()
    public int returnFinalATKArraySize(){
        String char_json_stat = LoadData("db/buff/char/"+charName.replace(" ","_")+".json");
        try {
            JSONObject jsonObject = new JSONObject(char_json_stat);
            JSONArray 元素爆發STR = jsonObject.getJSONArray("元素爆發STR");
            return 元素爆發STR.length();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //====================================================//

    public ArrayList<Double> readRESData(String enemyTag){
        String enemy_res = LoadData("db/buff/enemy_res.json");
        ArrayList<Double> resArray = new ArrayList<Double>();
        try {
            JSONObject jsonObject = new JSONObject(enemy_res);

            JSONArray resData = jsonObject.getJSONArray(enemyTag);

            for (int i = 0 ; i < resData.length() ; i++){
                resArray.add(resData.getDouble(i));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resArray;
    }

    //====================================================//
    public double 生命值面板(String[] artifactList){
        return (角色基礎生命值+ art.冒險家P2(artifactList)) * (1 + 武器百分比生命值+角色生命值加成+聖遺物生命值+art.千岩牢固P2(artifactList)) +聖遺物基礎生命值;
    }
    public double 防禦力面板(String[] artifactList){
        return (角色基礎防禦力+ art.幸運兒P2(artifactList)) * (1 + 武器百分比防禦力+角色防禦力加成+聖遺物防禦力+art.守護之心P2(artifactList)+art.華館夢醒形骸記P2(artifactList)) +聖遺物基礎防禦力;
    }
    //攻擊力面板=基礎攻擊力(白字)+攻擊力加成(黃字)
    public double 攻擊力面板(String[] artifactList){
        return (角色基礎攻擊力+武器基礎攻擊力)*(1+AttackP(artifactList)) + 聖遺物基礎攻擊力;
    }

    // 1 -> Base EnRech 100%
    public double 元素充能(String[] artifactList){
        return 1+角色元素充能+武器百分比元素充能
                +art.學士P2(artifactList)
                +art.流放者P2(artifactList)
                +art.絕緣之旗印P2(artifactList);

    }
    public double 元素精通附加值(String[] artifactList){
        return 角色元素精通+武器元素精通+聖遺物元素精通
                +art.流浪大地的樂團P2(artifactList)
                +art.教官P2(artifactList)
                +art.教官P4(artifactList);

    }
    public double 治療加成(String[] artifactList){
        return 角色治療加成
                +art.遊醫P2(artifactList)
                +art.被憐愛的少女P2(artifactList)
                +art.海染硨磲P2(artifactList);

    }
    public double 火元素傷害加成(String[] artifactList){
        return 角色火元素傷害加成+art.熾烈的炎之魔女P2(artifactList);
    }
    public double 水元素傷害加成(String[] artifactList){
        return 角色水元素傷害加成+art.沉淪之心P2(artifactList);
    }
    public double 風元素傷害加成(String[] artifactList){
        return 角色風元素傷害加成+art.翠綠之影P2(artifactList);
    }
    public double 雷元素傷害加成(String[] artifactList){
        return 角色雷元素傷害加成+art.如雷的盛怒P2(artifactList);
    }
    public double 草元素傷害加成(String[] artifactList){
        return 角色草元素傷害加成+0;
    }
    public double 冰元素傷害加成(String[] artifactList){
        return 角色冰元素傷害加成+art.冰風迷途的勇士P2(artifactList);
    }
    public double 岩元素傷害加成(String[] artifactList){
        return 角色岩元素傷害加成+art.悠古的磐岩P2(artifactList);
    }
    public double 物理傷害加成(String[] artifactList){
        return 角色物理傷害加成+武器百分比物理傷害加成
                +art.染血的騎士道P2(artifactList)
                +art.蒼白之火P2(artifactList);

    }

    //====================================================//

    public double hpReturn(String[] artifactList){
        return 生命值面板(artifactList);
    }
    public double atkReturn(String[] artifactName){
        return 攻擊力面板(artifactName);
    }
    public double defReturn(String[] artifactList){
        return 防禦力面板(artifactList);
    }
    public double critDMGReturn(){
        return CritDamageP(true);
    }
    public double critRateReturn(String[] artifactList){
        return CritRateP(artifactList);
    }
    public double enRechReturn(String[] artifactList){
        return 元素充能(artifactList);
    }
    public double skillPReturn(String str,String[] artifactList){
        return TalentP(str,artifactList);
    }

    //====================================================//


    /* (BASE)
    public double Damage (String type, boolean isCritDMG, String element_used){
        // Artifact Set Buff will edit in here directly.

        Log.wtf("1.1", String.valueOf(TalentP(type)));
        Log.wtf("1.2", String.valueOf(((AtkCharacter()+AtkWeapon())*(1+AttackP())+FlatAttack())));
        Log.wtf("2", String.valueOf(SpecialMultiplier(0)));
        Log.wtf("3", String.valueOf(FlatDamage(type)));
        Log.wtf("4", String.valueOf((1+DamageBounsP())));
        Log.wtf("5", String.valueOf(CritDamageP(isCritDMG)));
        Log.wtf("6", String.valueOf(EnemyDefMult(type)));
        Log.wtf("7", String.valueOf(EnemyResMult(element_used)));
        Log.wtf("8", String.valueOf(AmplifyingReaction(Geo,"N/A",element_used)));
        Log.wtf("9", String.valueOf(TransformativeReaction(Geo,"N/A",element_used)));
        Log.wtf("0", String.valueOf(Proc()));

        return (TalentP(type)*((AtkCharacter()+AtkWeapon())*(1+AttackP())+FlatAttack()) // BaseDamage
                *SpecialMultiplier(0) // SpecialMultiplier -> https://library.keqingmains.com/combat-mechanics/damage/damage-formula#special-multiplier
                +FlatDamage(type)) // Flat Damage -> https://library.keqingmains.com/combat-mechanics/damage/damage-formula#flat-damage-sources
                *(1+DamageBounsP()) // Damage Bonus
                *CritDamageP(isCritDMG) // Crit
                *EnemyDefMult(type)
                *EnemyResMult(element_used)
                *AmplifyingReaction(Geo,"N/A",element_used)
                +TransformativeReaction(Geo,"N/A",element_used)
                +Proc();
         }
        /* Old version (non-suitable for Raiden)
        return TalentP(type)
                *((AtkCharacter()+AtkWeapon())*(1+AttackP())+FlatAttack())
                *((1+CritDamageP(isCritDMG)))
                *(1+DamageBounsP())
                *((LvlCharacter+100)/((LvlCharacter + 100)+(LvlEnemy + 100)*(1-DefReductionP())*(1-DefIgnore())))
                *RES(0.171) // 210 when 0.2, 236 when 0.1 -> Not a really good way... //https://www.reddit.com/r/Genshin_Impact/comments/jq9r8g/game_mechanism_the_real_formula_for_fatui_shields/
                *((VaporizeMelt("Geo","Pyro")+ReactionBonusP()))
                *Evilsoother(0)
                +ElementReaction("Geo","Pyro")
                *MonaC1("Geo","Pyro",false)
                *ReactionRES(0);
         */

    /**
    @param artifactList : ["artifactP2Name1","artifactP4Name1","artifactP2Name2", "weaponType"]
     */
    public double Damage (String type, boolean isCritDMG, String element_used, String[] artifactList){
        // Artifact Set Buff will edit in here directly.

        return (TalentP(type,artifactList)*((AtkCharacter()+AtkWeapon())*(1+AttackP(artifactList))+FlatAttack()) // BaseDamage
                *SpecialMultiplier(0) // SpecialMultiplier -> https://library.keqingmains.com/combat-mechanics/damage/damage-formula#special-multiplier
                +FlatDamage(type)) // Flat Damage -> https://library.keqingmains.com/combat-mechanics/damage/damage-formula#flat-damage-sources
                *(1+DamageBounsP()) // Damage Bonus
                *CritDamageP(isCritDMG) // Crit
                *EnemyDefMult(type)
                *EnemyResMult(element_used,artifactList)
                *AmplifyingReaction(element_used,"N/A",element_used,artifactList)
                +TransformativeReaction(element_used,"N/A",element_used,artifactList)
                +Proc();

    }
    /*
    Will do it later
    ATK=(AttackCharacter+AttackWeapon)×(1+AttackBonus)+FlatAttack
    -> DEF=DefenseCharacter×(1+DefenseBonus)+FlatDefense
    -> Max HP=HealthCharacter×(1+HealthBonus)+FlatHealth
     */

    // https://genshin-impact.fandom.com/wiki/Heal
    public double HealHP (String type, String type_base , String[] artifactList){

        return TalentP(type,artifactList) * 生命值面板(artifactList) *(1+art.海染硨磲P2(artifactList)+art.被憐愛的少女P2(artifactList)+art.遊醫P2(artifactList))
                +  TalentP(type_base,artifactList);
    }



    public double HealATK (String type, String type_base , String[] artifactList){

        return TalentP(type,artifactList) * 攻擊力面板(artifactList) *(1+art.海染硨磲P2(artifactList)+art.被憐愛的少女P2(artifactList)+art.遊醫P2(artifactList))
                +  TalentP(type_base,artifactList);
    }

    // https://genshin-impact.fandom.com/wiki/Shields -> https://wikimedia.org/api/rest_v1/media/math/render/png/7871f5c053f843b25a1d9490e292d179579fd0ea
    public double Shield (String type, String type_base ,  String[] artifactList){
        return 防禦力面板(artifactList) * (1+TalentP(type,artifactList)) + TalentP(type_base,artifactList)   ;
    }

    /*
    https://library.keqingmains.com/combat-mechanics/damage/damage-formula#proc
     */
    public double Proc() {
        return 0;
    }

    /*
        EnemyDefMult=(LevelCharacter+100)/(LevelCharacter+100)+(LevelEnemy+100)×(1−DefReduction)×(1−DefIgnore)
     */
    public double EnemyDefMult(String type) {
        return ((LvlCharacter+100)/((100+LvlCharacter)+(LvlEnemy + 100)*(1-DefReductionP(type))*(1-DefIgnore())));
    }
    public double AmplifyingReaction (String charEle, String enemyEle,String element_used,String[] artifactList){
        if(charEle.equals(Hydro) && enemyEle.equals(Pyro)){
            return 2*(1+(2.78*EM(artifactList))/1400+EM(artifactList) + ReactionBonusP(artifactList)  + art.熾烈的炎之魔女P4_2(artifactList));
        }else if(charEle.equals(Pyro) && enemyEle.equals(Cryo)){
            return 1.5*(1+(2.78*EM(artifactList))/1400+EM(artifactList) + ReactionBonusP(artifactList)  + art.熾烈的炎之魔女P4_2(artifactList));
        }else {
            return 1;
        }
    }
    public double TransformativeReaction (String charEle, String enemyEle,String element_used,String[] artifactList){

        Log.wtf("9.1",String.valueOf(BaseMultipler(charEle,enemyEle)));
        Log.wtf("9.2",String.valueOf((1+(16*EM(artifactList))/(2000+EM(artifactList)) + ReactionBonusP(artifactList))));
        Log.wtf("9.3",String.valueOf(LevelMultiplier()));
        Log.wtf("9.4",String.valueOf(EnemyResMult(element_used,artifactList)));

        return BaseMultipler(charEle,enemyEle)
                *(1+(16*EM(artifactList))/(2000+EM(artifactList)) + ReactionBonusP(artifactList))
                *LevelMultiplier()
                *EnemyResMult(element_used,artifactList)
                *ArtifactMultipler(charEle,enemyEle,artifactList);
    }
    public double BaseMultipler(String charEle, String enemyEle){
        if(charEle.equals(Electro) && enemyEle.equals(Hydro)){ //感電
            return 1;
        }else if(charEle.equals(Pyro) && enemyEle.equals(Electro)){ //超載
            return 4;
        }else if(charEle.equals(Cryo) && enemyEle.equals(Electro)){ //超導
            return 2.4*ElectroChargedTriggers();
        }else if(charEle.equals(Anemo) && enemyEle.equals(Electro) || charEle.equals(Anemo) && enemyEle.equals(Pyro) || charEle.equals(Anemo) && enemyEle.equals(Hydro)){ //擴散
            return 1.2;
        }else if(charEle.equals(Cryo) && enemyEle.equals(Hydro)){ //冰凍
            return 3;
        }else if(charEle.equals(PHY)){ //物理
            return 3;
        }else {
            return 0;
        }
    }

    public double ArtifactMultipler(String charEle, String enemyEle,String[] artifactList){
        if(charEle.equals(Electro) && enemyEle.equals(Hydro)){ //感電
            return 1+art.如雷的盛怒P4(artifactList);
        }else if(charEle.equals(Pyro) && enemyEle.equals(Electro)){ //超載
            return 1+art.如雷的盛怒P4(artifactList)+art.熾烈的炎之魔女P4(artifactList);
        }else if(charEle.equals(Cryo) && enemyEle.equals(Electro)){ //超導
            return 1+art.如雷的盛怒P4(artifactList);
        }else if(charEle.equals(Anemo) && enemyEle.equals(Electro) || charEle.equals(Anemo) && enemyEle.equals(Pyro) || charEle.equals(Anemo) && enemyEle.equals(Hydro)){ //擴散
            return 1+art.翠綠之影P4(artifactList);
        }else if(charEle.equals(Cryo) && enemyEle.equals(Hydro)){ //冰凍
            return 1;
        }else if(charEle.equals(PHY)){ //物理
            return 1;
        }else {
            return 1;
        }
    }
    public double LevelMultiplier(){
        if(LvlCharacter >0 && LvlCharacter<60){
            return 0.0002325*Math.pow(LvlCharacter,3)+0.05547*Math.pow(LvlCharacter,2)-0.2523*LvlCharacter+14.47;
        }else if(LvlCharacter>=60 && LvlCharacter <= 90){
            return 0.00194*Math.pow(LvlCharacter,3)-0.319*Math.pow(LvlCharacter,2)+30.7*LvlCharacter-868;
        }else {
            return 1;
        }
    }

    //====================================================//

    public double AtkCharacter(){
        return (角色基礎攻擊力);
    }
    public double AtkWeapon(){
        return (武器基礎攻擊力);
    }
    public double AttackP (String[] artifactList){
        return 武器百分比攻擊力+聖遺物攻擊力
                +art.行者之心P2(artifactList)
                +art.勇士之心P2(artifactList)
                +art.勇士之心P4(artifactList)
                +art.染血的騎士道P4(artifactList)
                +art.角鬥士的終幕禮P2(artifactList)
                +art.蒼白之火P4(artifactList)
                +art.昔日宗室之儀P4(artifactList)
                +art.追憶之注連P2(artifactList)
                +art.千岩牢固P4(artifactList);

    }
    public double FlatAttack (){
        return 聖遺物基礎攻擊力;
    }
    public double CritDamageP (boolean isCritDMG){
        if(isCritDMG){
            return 1+角色暴擊傷害+武器百分比暴擊傷害+聖遺物暴擊傷害;
        }
        else return 1;
    }
    public double CritRateP (String[] artifactList){
        return 角色暴擊率+武器百分比暴擊率+聖遺物暴擊率
                +art.行者之心P4(artifactList)
                +art.戰狂P2(artifactList)
                +art.戰狂P4(artifactList);
    }

    /*
    Sum of all percentage damage increases from goblets, weapons, set bonuses and other buffs.
    Excludes Xingqiu's C4 effect Evilsoother and Yoimiya's Elemental Skill Niwabi Fire-Dance.
     */
    public double DamageBounsP (){
        return 0;
    }
    public double DefReductionP (String type){ // 各種防禦降低效果的總防禦%（但不包括抗性）
        //https://zhuanlan.zhihu.com/p/378611871
        /*
        Razor's Bite (C4): 15%
        Lisa's Static Electricity Field (A4): 15%
        Ayaka's Ebb and Flow (C4): 30%
        Klee's Explosive Frags (C2): 23%
         */

        // NOT END
        return 0;
    }

    public double DefIgnore (){ // 雷神的二命是 Ignore defense
        if(charName.equals("Raiden Shogun")){
            return 0.1;
        }else return 0;
    }

    public double EnemyResMult (String element_used, String[] artifactList){ // 敵人對元素攻擊的元素抗性
        double res = BaseResistance(enemyName,element_used)-ResistanceReduction(artifactList);
        // Aim is -> Hilichurl
        //Resistance=BaseResistance−ResistanceReduction
        if(res < 0){
            return 1 - res/2;
        }else if (res >= 0 && res < 0.75){
            return 1 - res;
        }else if (res >= 0.75){
            return 1/(4*res+1);
        }else {
            return 1;
        }
    }

    public double ResistanceReduction(String[] artifactList) {
        return art.翠綠之影P4(artifactList);
    }
    public double BaseResistance(String enemyName, String element_used) {
        // READ JSON
        // -10 : Immune

        int tmp = -1;
        switch (element_used){
            case "Pyro": tmp = 0; break;
            case "Electro": tmp = 1; break;
            case "Cryo": tmp = 2; break;
            case "Hydro": tmp = 3; break;
            case "Anemo": tmp = 4; break;
            case "Geo": tmp = 5; break;
            case "Dendro": tmp = 6; break;
            case "Physical": tmp = 7; break;
            // TMP 8 is for TYPE SORT
            // TMP 9 is for ENEMY ELEMENT (Base)

            default: tmp = -1;break;
        }
        if(tmp != -1 && readRESData(enemyName).get(tmp) == -10){
            return 1;
        }else if(tmp != -1){
            return readRESData(enemyName).get(tmp);
        }else{
            return 1;
        }
    }


    public double EM (String[] artifactList){ // 元素精通
        return 角色元素精通+武器元素精通+聖遺物元素精通
                +art.流浪大地的樂團P2(artifactList)
                +art.教官P2(artifactList)
                +art.教官P4(artifactList);

    }

    public double ReactionBonusP(String[] artifactList){ //來自 Crimson Witch 4 件獎勵（用於蒸發和融化）和 Mona 的 C1（用於蒸發）的反應傷害獎勵
        return 0;
    }

    public double SpecialMultiplier(int triggered){ //Xingqiu C4 only -> Always triggered ?
        switch (triggered){
            case 1 : return 1.5; // Evilsoother triggered
            case 2 : return 1.7060; // [MAX] Niwabi Fire-Dance activated
            case 3 : return 1.3791; // [MIN] Niwabi Fire-Dance activated
            default:return 1;
        }
    }
    public double FlatDamage(String type){
        /**
         * Zhongli deals bonus DMG based on his Max HP:
         * • Normal Attack, Charged attack, and Plunge DMG is increased by 1.39% Max HP.
         * • Dominus Lapidis’ Stone Stele, resonance, and hold DMG are increased by 1.9% Max HP.
         * • Planet Befall’s DMG is increased by 33% Max HP.
         */
        if (charName.equals("Zhongli") && charAfterBreakLvl >= 4){
            switch (type){
                //普通攻擊
                case "一段傷害" :
                case "二段傷害" :
                case "三段傷害" :
                case "四段傷害" :
                case "五段傷害" :
                case "六段傷害" :
                    return 1.0139;
                //重擊
                case "重擊傷害" :
                    return 1.0139;
                //俯衝攻擊
                case "低空墜地衝擊傷害" :
                case "高空墜地衝擊傷害" :
                    return 1.0139;

                // NOT END
            }
            // NOT END
        }
        return 0;
    }

    //是 Electro-Charged 觸發的次數，取決於施加在敵人身上的水電和電元素的元素規格強度。
    public double ElectroChargedTriggers() {
        return 1;
    }

    public double MonaC1 (String charEle, String enemyEle, boolean isMonaC1Active){
        if(isMonaC1Active){
            if(charEle.equals(Electro) && enemyEle.equals(Hydro)) { //感電
                return 1.15;
            }else if(charEle.equals(Anemo) && enemyEle.equals(Hydro) ){ //擴散 [水]
                return 1.15;
            }else{
                return 1;
            }
        }
        return 1;
    }

    public void ArtifactBuff(String name, double value){

        // Mixed main & sec 1-4

        if(name.equals("ATK")){
            聖遺物攻擊力 = 聖遺物攻擊力 + value;
        }else if(name.equals("HP")){
            聖遺物生命值 = 聖遺物生命值 + value;
        }else if(name.equals("DEF")){
            聖遺物防禦力 = 聖遺物防禦力 + value;
        }else if(name.equals("EleMas")){
            聖遺物元素精通 = 聖遺物元素精通 + value;
        }else if(name.equals("EnRech")){
            聖遺物元素充能效率 = 聖遺物元素充能效率 + value;
        }else if(name.equals("EleDMG_Electro")){
            聖遺物雷元素傷害加成 = 聖遺物雷元素傷害加成 + value;
        }else if(name.equals("EleDMG_Pyro")){
            聖遺物火元素傷害加成 = 聖遺物火元素傷害加成 + value;
        }else if(name.equals("EleDMG_Hydro")){
            聖遺物水元素傷害加成 = 聖遺物水元素傷害加成 + value;
        }else if(name.equals("EleDMG_Cryo")){
            聖遺物冰元素傷害加成 = 聖遺物冰元素傷害加成 + value;
        }else if(name.equals("EleDMG_Anemo")){
            聖遺物風元素傷害加成 = 聖遺物風元素傷害加成 + value;
        }else if(name.equals("EleDMG_Geo")){
            聖遺物岩元素傷害加成 = 聖遺物岩元素傷害加成 + value;
        }else if(name.equals("EleDMG_Dendor")){
            聖遺物草元素傷害加成 = 聖遺物草元素傷害加成 + value;
        }else if(name.equals("PhyDMG")){
            聖遺物物理傷害 = 聖遺物物理傷害 + value;
        }else if(name.equals("CritDMG")){
            聖遺物暴擊傷害 = 聖遺物暴擊傷害 + value;
        }else if(name.equals("CritRate")){
            聖遺物暴擊率 = 聖遺物暴擊率 + value;
        }else if(name.equals("baseATK")){
            聖遺物基礎攻擊力 = 聖遺物基礎攻擊力 + value;
        }else if(name.equals("baseHP")){
            聖遺物基礎生命值 = 聖遺物基礎生命值 + value;
        }else if(name.equals("baseDEF")){
            聖遺物基礎防禦力 = 聖遺物基礎防禦力 + value;
        }else if(name.equals("Healing")){
            聖遺物治療加成 = 聖遺物治療加成 + value;
        }
    }


    //TalentP
    public double TalentP(String type,String[] artifactList){

        double normalATKArtifactSetBuff =
                art.武人P2(artifactList)
                        +art.武人P4(artifactList)
                        +art.角鬥士的終幕禮P4(artifactList)
                        +art.沉淪之心P4(artifactList)
                        +art.追憶之注連P2(artifactList)
                        +art.逆飛的流星P4(artifactList);


        double chargedATKArtifactSetBuff =
                art.武人P2(artifactList)
                        +art.武人P4(artifactList)
                        +art.流浪大地的樂團P4(artifactList)
                        +art.沉淪之心P4(artifactList)
                        +art.逆飛的流星P4(artifactList)
                        +art.行者之心P4(artifactList);

        switch (type){
            //普通攻擊
            case "一段傷害" :  return 普通攻擊_一段傷害+normalATKArtifactSetBuff;
            case "二段傷害" :  return 普通攻擊_二段傷害+normalATKArtifactSetBuff;
            case "三段傷害" :  return 普通攻擊_三段傷害+normalATKArtifactSetBuff;
            case "四段傷害" :  return 普通攻擊_四段傷害+normalATKArtifactSetBuff;
            case "五段傷害" :  return 普通攻擊_五段傷害+normalATKArtifactSetBuff;
            case "六段傷害" :  return 普通攻擊_六段傷害+normalATKArtifactSetBuff;
            case "瞄準射擊" :  return 普通攻擊_瞄準射擊+normalATKArtifactSetBuff;

            //重擊
            case "滿蓄力瞄準射擊" :  return 普通攻擊_滿蓄力瞄準射擊+chargedATKArtifactSetBuff;
            case "重擊傷害" :  return 普通攻擊_重擊傷害+chargedATKArtifactSetBuff;
            case "重擊循環傷害" :  return 普通攻擊_重擊循環傷害+chargedATKArtifactSetBuff;
            case "重擊終結傷害" :  return 普通攻擊_重擊終結傷害+chargedATKArtifactSetBuff;

            //俯衝攻擊
            case "下墜期間傷害" :  return 普通攻擊_下墜期間傷害;
            case "低空墜地衝擊傷害" :  return 普通攻擊_低空墜地衝擊傷害;
            case "高空墜地衝擊傷害" :  return 普通攻擊_高空墜地衝擊傷害;

            //元素戰技 -> Max 12 , RealMax is 7 -> Aloy
            case "元素戰技0" : return (元素戰技_value[0][charSkill1AfterLvl-1]);
            case "元素戰技1" : return (元素戰技_value[1][charSkill1AfterLvl-1]);
            case "元素戰技2" : return (元素戰技_value[2][charSkill1AfterLvl-1]);
            case "元素戰技3" : return (元素戰技_value[3][charSkill1AfterLvl-1]);
            case "元素戰技4" : return (元素戰技_value[4][charSkill1AfterLvl-1]);
            case "元素戰技5" : return (元素戰技_value[5][charSkill1AfterLvl-1]);
            case "元素戰技6" : return (元素戰技_value[6][charSkill1AfterLvl-1]);
            case "元素戰技7" : return (元素戰技_value[7][charSkill1AfterLvl-1]);
            case "元素戰技8" : return (元素戰技_value[8][charSkill1AfterLvl-1]);
            case "元素戰技9" : return (元素戰技_value[9][charSkill1AfterLvl-1]);
            case "元素戰技10" : return (元素戰技_value[10][charSkill1AfterLvl-1]);
            case "元素戰技11" : return (元素戰技_value[11][charSkill1AfterLvl-1]);

            //元素爆發 -> Max 12 , RealMax is 7 -> Aloy
            case "元素爆發0" : return (元素爆發_value[0][charSkill1AfterLvl-1]);
            case "元素爆發1" : return (元素爆發_value[1][charSkill1AfterLvl-1]);
            case "元素爆發2" : return (元素爆發_value[2][charSkill1AfterLvl-1]);
            case "元素爆發3" : return (元素爆發_value[3][charSkill1AfterLvl-1]);
            case "元素爆發4" : return (元素爆發_value[4][charSkill1AfterLvl-1]);
            case "元素爆發5" : return (元素爆發_value[5][charSkill1AfterLvl-1]);
            case "元素爆發6" : return (元素爆發_value[6][charSkill1AfterLvl-1]);
            case "元素爆發7" : return (元素爆發_value[7][charSkill1AfterLvl-1]);
            case "元素爆發8" : return (元素爆發_value[8][charSkill1AfterLvl-1]);
            case "元素爆發9" : return (元素爆發_value[9][charSkill1AfterLvl-1]);
            case "元素爆發10" : return (元素爆發_value[10][charSkill1AfterLvl-1]);
            case "元素爆發11" : return (元素爆發_value[11][charSkill1AfterLvl-1]);

            default: return 0;
        }
    }

    //====================================================//

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
            case 1 : return plus+(new DecimalFormat("###,###,###,###,###.#").format(numDouble*100))+"%";
            default: return plus+new DecimalFormat("###,###,###,###,###.#").format(number);
        }
    }

}
