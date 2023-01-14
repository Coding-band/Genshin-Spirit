package com.voc.genshin_helper.buff;/*
 * Package com.voc.genshin_helper.buff.BuffCal was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class BuffCal_old {

    /**
     * System var
     */
    private Context context;
    ItemRss itemRss;

    /**
     * FINAL METHOD
     */

    double 角色基礎生命值,角色基礎攻擊力,角色基礎防禦力,角色生命值加成,角色攻擊力加成,角色防禦力加成,角色暴擊率,角色暴擊傷害,角色元素充能,角色元素精通,角色治療加成,角色火元素傷害加成,角色水元素傷害加成,角色風元素傷害加成,角色雷元素傷害加成,角色草元素傷害加成,角色冰元素傷害加成,角色岩元素傷害加成,角色物理傷害加成;
    double 普通攻擊_一段傷害,普通攻擊_二段傷害,普通攻擊_三段傷害,普通攻擊_四段傷害,普通攻擊_五段傷害,普通攻擊_六段傷害,普通攻擊_下墜期間傷害,普通攻擊_低空墜地衝擊傷害,普通攻擊_高空墜地衝擊傷害,普通攻擊_瞄準射擊,普通攻擊_滿蓄力瞄準射擊,普通攻擊_重擊傷害,普通攻擊_重擊循環傷害,普通攻擊_重擊終結傷害;

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

    float damage,baseDamage,damageBonus,crit;
    // Maybe not include
    float enemyDefenseMultiplier,enemyResistanceMultiplier,amplifyingReaction,otherBonus,transformativeReaction,proc;

    //============================================================================//

    public void setup (Context context){
        this.context = context;
        itemRss = new ItemRss();
    }

    public void char_setup(String charName, int charAfterLvl,  int charAfterBreakLvl, boolean charAfterBreakUP, int charSkill1AfterLvl, int charSkill2AfterLvl, int charSkill3AfterLvl) {
        this.charName = charName;
        this.charAfterLvl = charAfterLvl;
        this.charAfterBreakLvl = charAfterBreakLvl;
        this.charAfterBreakUP = charAfterBreakUP;
        this.charSkill1AfterLvl = charSkill1AfterLvl;
        this.charSkill2AfterLvl = charSkill2AfterLvl;
        this.charSkill3AfterLvl = charSkill3AfterLvl;
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
            角色生命值加成 = 生命值加成.getDouble(tmp_break)/100;

            JSONArray 攻擊力加成 = 角色突破.getJSONArray("攻擊力加成");
            角色攻擊力加成 = 攻擊力加成.getDouble(tmp_break)/100;

            JSONArray 防禦力加成 = 角色突破.getJSONArray("防禦力加成");
            角色防禦力加成 = 防禦力加成.getDouble(tmp_break)/100;

            JSONArray 暴擊率 = 角色突破.getJSONArray("暴擊率");
            角色暴擊率 = 暴擊率.getDouble(tmp_break)/100;

            JSONArray 暴擊傷害 = 角色突破.getJSONArray("暴擊傷害");
            角色暴擊傷害 = 暴擊傷害.getDouble(tmp_break)/100;

            JSONArray 元素充能 = 角色突破.getJSONArray("元素充能");
            角色元素充能 = 元素充能.getDouble(tmp_break)/100;

            JSONArray 元素精通 = 角色突破.getJSONArray("元素精通");
            角色元素精通 = 元素精通.getDouble(tmp_break);

            JSONArray 治療加成 = 角色突破.getJSONArray("治療加成");
            角色治療加成 = 治療加成.getDouble(tmp_break)/100;

            JSONArray 火元素傷害加成 = 角色突破.getJSONArray("火元素傷害加成");
            角色火元素傷害加成 = 火元素傷害加成.getDouble(tmp_break)/100;

            JSONArray 水元素傷害加成 = 角色突破.getJSONArray("水元素傷害加成");
            角色水元素傷害加成 = 水元素傷害加成.getDouble(tmp_break)/100;

            JSONArray 風元素傷害加成 = 角色突破.getJSONArray("風元素傷害加成");
            角色風元素傷害加成 = 風元素傷害加成.getDouble(tmp_break)/100;

            JSONArray 雷元素傷害加成 = 角色突破.getJSONArray("雷元素傷害加成");
            角色雷元素傷害加成 = 雷元素傷害加成.getDouble(tmp_break)/100;

            JSONArray 草元素傷害加成 = 角色突破.getJSONArray("草元素傷害加成");
            角色草元素傷害加成 = 草元素傷害加成.getDouble(tmp_break)/100;

            JSONArray 冰元素傷害加成 = 角色突破.getJSONArray("冰元素傷害加成");
            角色冰元素傷害加成 = 冰元素傷害加成.getDouble(tmp_break)/100;

            JSONArray 岩元素傷害加成 = 角色突破.getJSONArray("岩元素傷害加成");
            角色岩元素傷害加成 = 岩元素傷害加成.getDouble(tmp_break)/100;

            JSONArray 物理傷害加成 = 角色突破.getJSONArray("物理傷害加成");
            角色物理傷害加成 = 物理傷害加成.getDouble(tmp_break)/100;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(char_json_stat);
            JSONObject 普通攻擊 = jsonObject.getJSONObject("普通攻擊");

            JSONArray 一段傷害 = 普通攻擊.getJSONArray("一段傷害");
            普通攻擊_一段傷害 = 一段傷害.getDouble(tmp_break)/100;

            JSONArray 二段傷害 = 普通攻擊.getJSONArray("二段傷害");
            普通攻擊_二段傷害 = 二段傷害.getDouble(tmp_break)/100;

            JSONArray 三段傷害 = 普通攻擊.getJSONArray("三段傷害");
            普通攻擊_三段傷害 = 三段傷害.getDouble(tmp_break)/100;

            JSONArray 四段傷害 = 普通攻擊.getJSONArray("四段傷害");
            普通攻擊_四段傷害 = 四段傷害.getDouble(tmp_break)/100;

            JSONArray 五段傷害 = 普通攻擊.getJSONArray("五段傷害");
            普通攻擊_五段傷害 = 五段傷害.getDouble(tmp_break)/100;

            JSONArray 六段傷害 = 普通攻擊.getJSONArray("六段傷害");
            普通攻擊_六段傷害 = 六段傷害.getDouble(tmp_break)/100;

            JSONArray 下墜期間傷害 = 普通攻擊.getJSONArray("下墜期間傷害");
            普通攻擊_下墜期間傷害 = 下墜期間傷害.getDouble(tmp_break)/100;

            JSONArray 低空墜地衝擊傷害 = 普通攻擊.getJSONArray("低空墜地衝擊傷害");
            普通攻擊_低空墜地衝擊傷害 = 低空墜地衝擊傷害.getDouble(tmp_break)/100;

            JSONArray 高空墜地衝擊傷害 = 普通攻擊.getJSONArray("高空墜地衝擊傷害");
            普通攻擊_高空墜地衝擊傷害 = 高空墜地衝擊傷害.getDouble(tmp_break)/100;

            JSONArray 瞄準射擊 = 普通攻擊.getJSONArray("瞄準射擊");
            普通攻擊_瞄準射擊 = 瞄準射擊.getDouble(tmp_break)/100;

            JSONArray 滿蓄力瞄準射擊 = 普通攻擊.getJSONArray("滿蓄力瞄準射擊");
            普通攻擊_滿蓄力瞄準射擊 = 滿蓄力瞄準射擊.getDouble(tmp_break)/100;

            JSONArray 重擊傷害 = 普通攻擊.getJSONArray("重擊傷害");
            普通攻擊_重擊傷害 = 重擊傷害.getDouble(tmp_break)/100;

            JSONArray 重擊循環傷害 = 普通攻擊.getJSONArray("重擊循環傷害");
            普通攻擊_重擊循環傷害 = 重擊循環傷害.getDouble(tmp_break)/100;

            JSONArray 重擊終結傷害 = 普通攻擊.getJSONArray("重擊終結傷害");
            普通攻擊_重擊終結傷害 = 重擊終結傷害.getDouble(tmp_break)/100;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        /**
         * Weapon Part
         */

        String weapon_json = LoadData("db/buff/weapons/"+weaponName.replace(" ","_")+".json");
        try {
            JSONObject jsonObject = new JSONObject(weapon_json);

            JSONArray 基礎攻擊力 = jsonObject.getJSONArray("基礎攻擊力");
            武器基礎攻擊力 = 基礎攻擊力.getDouble(weapon_tmp_break);

            JSONArray 生命值加成 = jsonObject.getJSONArray("生命值加成");
            武器百分比生命值 = 生命值加成.getDouble(weapon_tmp_break)/100;

            JSONArray 攻擊力加成 = jsonObject.getJSONArray("攻擊力加成");
            武器百分比攻擊力 = 攻擊力加成.getDouble(weapon_tmp_break)/100;

            JSONArray 防禦力加成 = jsonObject.getJSONArray("防禦力加成");
            武器百分比防禦力 = 防禦力加成.getDouble(weapon_tmp_break)/100;

            JSONArray 暴擊率 = jsonObject.getJSONArray("暴擊率");
            武器百分比暴擊率 = 暴擊率.getDouble(weapon_tmp_break)/100;

            JSONArray 暴擊傷害 = jsonObject.getJSONArray("暴擊傷害");
            武器百分比暴擊傷害 = 暴擊傷害.getDouble(weapon_tmp_break)/100;

            JSONArray 元素充能 = jsonObject.getJSONArray("元素充能");
            武器百分比元素充能 = 元素充能.getDouble(weapon_tmp_break)/100;

            JSONArray 元素精通 = jsonObject.getJSONArray("元素精通");
            武器元素精通 = 元素精通.getDouble(weapon_tmp_break);

            JSONArray 物理傷害加成 = jsonObject.getJSONArray("物理傷害加成");
            武器百分比物理傷害加成 = 物理傷害加成.getDouble(weapon_tmp_break)/100;


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    int 角色等級 = charAfterLvl;

    /**
     * Method :
     * Var -> just a non-changed number
     * Function -> Changeable number / result
     */


    /** Total_ATK = Base_ATK * (1 + ATK%) + Flat_ATK
     * Base_ATK => CharBaseATK + WeaponBaseATK
     * ATK% => ArtifactMainStatATK% + ArtifactSubStatATK% + WeaponSubATK%
     * Flat_ATK => ArtifactMainStatATK + ArtifactSubStatATK
     *
     */

    public double 生命值面板(){
        return (角色基礎生命值) * (1 + 武器百分比生命值+角色生命值加成+0+0+0+0+0) + (0+0);
    }
    public double 防禦力面板(){
        return (角色基礎防禦力) * (1 + 武器百分比防禦力+角色防禦力加成+0+0+0+0+0) + (0+0);
    }
    //攻擊力面板=基礎攻擊力(白字)+攻擊力加成(黃字)
    public double 攻擊力面板(){
        return (角色基礎攻擊力+武器基礎攻擊力)*(1+攻擊力加成());
    }
    /**
     * 若一次攻擊沒有觸發增幅反應與暴擊效果，它的最終傷害計算為
     *  技能傷害=攻擊力面板*技能倍率*(1+總傷害加成)*怪物免傷率
     * 若其觸發了增幅反應，它的最終傷害計算為
     *  技能傷害=攻擊力面板*技能倍率*(1+總傷害加成)* [(增幅反應倍率+反應倍率加成)*(1+元素精通附加值)] *怪物免傷率
     * 若其觸發了暴擊效果，它的最終傷害計算為
     *  技能傷害=攻擊力面板*技能倍率*(1+總傷害加成)* (1+暴擊加成) *怪物免傷率
     * 極端情況下，當一次技能傷害觸發了增幅反應與暴擊效果，它的最終傷害計算為
     *  技能傷害=攻擊力面板*技能倍率*(1+總傷害加成)* (1+暴擊加成) * [(增幅反應倍率+反應倍率加成)*(1+元素精通附加值)] *怪物免傷率
     */
    public double 技能傷害_沒增幅暴擊(String type){
        return 攻擊力面板()*技能倍率(type)*(1+總傷害加成())*怪物免傷率();
    }
    public double 技能傷害_增幅(String type){
        return 攻擊力面板()*技能倍率(type)*(1+總傷害加成())* ((反應倍率()+反應倍率加成())*(1+元素精通附加值()))*怪物免傷率();
    }
    public double 技能傷害_暴擊(String type){
        return 攻擊力面板()*技能倍率(type)*(1+總傷害加成())*(1+暴擊率())*怪物免傷率();
    }
    public double 技能傷害_增幅暴擊(String type){
        return 攻擊力面板()*技能倍率(type)*(1+總傷害加成())* (1+暴擊率()) * ((反應倍率()+反應倍率加成())*(1+元素精通附加值())) *怪物免傷率();
    }

    /**
     * 劇變傷害=基礎傷害*(1+元素精通附加值+反應傷害加成)*(1-抗性)
     */
    public double 劇變傷害(){
        return 攻擊力面板()*(1+元素精通附加值()+反應傷害加成())*(1-抗性());
    }
    //暴擊傷害
    /**
     * 一般，這種效果由聖遺物五號位、聖遺物副詞條、聖遺物套裝效果(例：戰狂)、武器副詞條(例：四風原典)、武器效果(例：天空之刃)、元素共鳴(雙冰)提供。
     * 每名角色初始擁有5%的暴擊率和50%的暴擊傷害基礎值。
     * 此外，迪盧克在突破時還會獲得一定暴擊率加成；刻晴在突破時還會獲得一定暴擊傷害加成。
     */
    public double 暴擊傷害(){
        return 1+(角色暴擊傷害+武器百分比暴擊傷害+0);
    }

    //攻擊力加成=固定攻擊力加成+百分比基礎攻擊力的加成
    public double 攻擊力加成(){
        return 固定攻擊力加成()+百分比基礎攻擊力的加成();
    }
    //固定攻擊力加成=聖遺物二號位(羽毛)攻擊力加成+聖遺物副詞條攻擊力加成(非%)
    public double 固定攻擊力加成(){
        return 0+0;
    }
    //百分比基礎攻擊力的加成=武器副詞條+武器效果+聖遺物套裝效果+聖遺物副詞條+元素共鳴中的雙火隊
    public double 百分比基礎攻擊力的加成(){
        return 武器百分比攻擊力+角色攻擊力加成+0+0+0+0;
    }
    //傷害加成，指反應傷害加成外的所有傷害提升效果(特定元素傷害加成,特定來源傷害加成,全傷害加成)
    public double 總傷害加成(){
        return 角色物理傷害加成+角色水元素傷害加成+角色火元素傷害加成+角色冰元素傷害加成+角色雷元素傷害加成+角色風元素傷害加成+角色岩元素傷害加成+0+0;
    }


    /**元素反應——增幅
     * 增幅反應，具體而言就是水火蒸發反應與冰火融化反應。
     * 它們的效果是對基礎傷害進行特定倍率的增幅，增幅倍率為：
     * 火觸發蒸發、冰觸發融化=1.5倍
     * 水觸發蒸發、火觸發融化=2倍
     * 以上倍率應當爛熟於心。
     * 不計其它效果，增幅反應的計算公式為基礎傷害*增幅倍率
     * 若存在“反應倍率加成"效果，即”“熾烈的炎之魔女“4件的”蒸發/融化反應傷害加成係數+15%”效果，增幅反應的計算公式變為為基礎傷害*(增幅倍率+15%)
     *
     * 元素反應——劇變
     * 增幅反應，具體而言就是水雷感電反應、冰雷超導反應、火雷超載反應、碎冰、風+水火冰雷擴散反應。
     * 劇變反應的基礎傷害=f(角色等級)*反應倍率。
     * 記LV=f(角色等級)指一個隨角色等級增加而漸增的函數
     * 根據 西風驛站@其音清 的研究，55級之前，此函數指數上升；55級之後，此函數線性上升。
     * 不同反應之間的倍率大小為——超導：擴散：感電(一跳)：碎冰：超載=1:1.2:2.4：3:4
     * 若令超導反應的反應倍率為1，則擴散、感電(一跳)、碎冰、超載的反應倍率則對應為1.2、2.4、3、4。
     * 此時LV對應超導反應的基礎傷害。
     */

    public double 反應傷害加成(){
        return 0;
    }

    //技能倍率
    public double 技能倍率(String type){
        switch (type){
            //普通攻擊
            case "一段傷害" :  return 普通攻擊_一段傷害;
            case "二段傷害" :  return 普通攻擊_二段傷害;
            case "三段傷害" :  return 普通攻擊_三段傷害;
            case "四段傷害" :  return 普通攻擊_四段傷害;
            case "五段傷害" :  return 普通攻擊_五段傷害;
            case "六段傷害" :  return 普通攻擊_六段傷害;
            case "瞄準射擊" :  return 普通攻擊_瞄準射擊;

            //重擊
            case "滿蓄力瞄準射擊" :  return 普通攻擊_滿蓄力瞄準射擊;
            case "重擊傷害" :  return 普通攻擊_重擊傷害;
            case "重擊循環傷害" :  return 普通攻擊_重擊循環傷害;
            case "重擊終結傷害" :  return 普通攻擊_重擊終結傷害;

            //俯衝攻擊
            case "下墜期間傷害" :  return 普通攻擊_下墜期間傷害;
            case "低空墜地衝擊傷害" :  return 普通攻擊_低空墜地衝擊傷害;
            case "高空墜地衝擊傷害" :  return 普通攻擊_高空墜地衝擊傷害;


            //元素戰技

            //元素爆發

            default: return 0;
        }
    }
    //反應倍率
    public double 反應倍率(){
        return 0;
    }
    //怪物免傷率
    public double 怪物免傷率(){
        return 1;
    }
    //抗性
    public double 抗性(){
        return 0;
    }
    //暴擊率
    /**
     * 一般，這種效果由聖遺物五號位、聖遺物副詞條、聖遺物套裝效果(例：戰狂)、武器副詞條(例：四風原典)、武器效果(例：天空之刃)、元素共鳴(雙冰)提供。
     * 每名角色初始擁有5%的暴擊率和50%的暴擊傷害基礎值。
     * 此外，迪盧克在突破時還會獲得一定暴擊率加成；刻晴在突破時還會獲得一定暴擊傷害加成。
     */
    public double 暴擊率(){
        return (角色暴擊率+武器百分比暴擊率+0);
    }
    //若存在“反應倍率加成"效果，即”“熾烈的炎之魔女“4件的”蒸發/融化反應傷害加成係數+15%”效果，增幅反應的計算公式變為為基礎傷害*(增幅倍率+15%)
    public double 反應倍率加成(){
        return 0;
    }
    // 1 -> Base EnRech 100%
    public double 元素充能(){
        return 1+角色元素充能+武器百分比元素充能;
    }
    public double 元素精通附加值(){
        return 角色元素精通+0;
    }
    public double 治療加成(){
        return 角色治療加成+0;
    }
    public double 火元素傷害加成(){
        return 角色火元素傷害加成+0;
    }
    public double 水元素傷害加成(){
        return 角色水元素傷害加成+0;
    }
    public double 風元素傷害加成(){
        return 角色風元素傷害加成+0;
    }
    public double 雷元素傷害加成(){
        return 角色雷元素傷害加成+0;
    }
    public double 草元素傷害加成(){
        return 角色草元素傷害加成+0;
    }
    public double 冰元素傷害加成(){
        return 角色冰元素傷害加成+0;
    }
    public double 岩元素傷害加成(){
        return 角色岩元素傷害加成+0;
    }
    public double 物理傷害加成(){
        return 角色物理傷害加成+武器百分比物理傷害加成;
    }

    //====================================================//

    public double hpReturn(){
        return 生命值面板();
    }
    public double atkReturn(){
        return 攻擊力面板();
    }
    public double defReturn(){
        return 防禦力面板();
    }
    public double critDMGReturn(){
        Log.wtf("暴擊傷害", String.valueOf(暴擊傷害()));
        return 暴擊傷害();
    }
    public double critRateReturn(){
        Log.wtf("暴擊率", String.valueOf(暴擊率()));
        return 暴擊率();
    }
    public double enRechReturn(){
        return 元素充能();
    }
    public double skillPReturn(String str){
        return 技能倍率(str);
    }

    public String[] otherReturn(){
        if(角色元素精通>0){return new String[]{"角色元素精通", String.valueOf(元素精通附加值())};}
        else if(角色治療加成>0){return new String[]{"角色治療加成", String.valueOf(治療加成())};}
        else if(角色火元素傷害加成>0){return new String[]{"角色火元素傷害加成", String.valueOf(火元素傷害加成())};}
        else if(角色水元素傷害加成>0){return new String[]{"角色水元素傷害加成", String.valueOf(水元素傷害加成())};}
        else if(角色風元素傷害加成>0){return new String[]{"角色風元素傷害加成", String.valueOf(風元素傷害加成())};}
        else if(角色雷元素傷害加成>0){return new String[]{"角色雷元素傷害加成", String.valueOf(雷元素傷害加成())};}
        else if(角色草元素傷害加成>0){return new String[]{"角色草元素傷害加成", String.valueOf(草元素傷害加成())};}
        else if(角色冰元素傷害加成>0){return new String[]{"角色冰元素傷害加成", String.valueOf(冰元素傷害加成())};}
        else if(角色岩元素傷害加成>0){return new String[]{"角色岩元素傷害加成", String.valueOf(岩元素傷害加成())};}
        else if(角色物理傷害加成>0){return new String[]{"角色物理傷害加成", String.valueOf(物理傷害加成())};}
        else {return new String[]{"NaN", String.valueOf(0)};}
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

    public String prettyCount(Number number) {
        char[] suffix = {' ', 'K', 'M', 'G', 'T', 'P', 'E', 'Z', 'Y'};
        long numValue = Math.round(number.longValue());
        int value = (int) Math.floor(Math.log10(numValue));
        int base = value / 3;
        String plus = "";
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        int decimal_num = sharedPreferences.getInt("decimal_num", 0);
        boolean decimal  = sharedPreferences.getBoolean("decimal", false);
        if (decimal == true){
            if (value >= 3 && base < suffix.length) {
                if (decimal_num == 0){
                    return plus+new DecimalFormat("##").format(numValue / Math.pow(10, base * 3)) + suffix[base];
                }
                if (decimal_num == 1){
                    return plus+new DecimalFormat("##.#").format(numValue / Math.pow(10, base * 3)) + suffix[base];
                }
                if (decimal_num == 2){
                    return plus+new DecimalFormat("##.##").format(numValue / Math.pow(10, base * 3)) + suffix[base];
                }
                if (decimal_num == 3){
                    return plus+new DecimalFormat("##.###").format(numValue / Math.pow(10, base * 3)) + suffix[base];
                }
                if (decimal_num == 4){
                    return plus+new DecimalFormat("##.####").format(numValue / Math.pow(10, base * 3)) + suffix[base];
                }
                if (decimal_num == 5){
                    return plus+new DecimalFormat("##.#####").format(numValue / Math.pow(10, base * 3)) + suffix[base];
                }
                // Muility
            } else {
                return plus+new DecimalFormat("###,###,###,###,###").format(numValue);
            }
        }
        return plus+new DecimalFormat("###,###,###,###,###").format(numValue);
    }
}
