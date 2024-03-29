package com.voc.genshin_helper.buff;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.voc.genshin_helper.buff.obj.BuffObject;
import com.voc.genshin_helper.buff.obj.Character;
import com.voc.genshin_helper.buff.obj.Talents;
import com.voc.genshin_helper.buff.obj.Weapon;
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

public class BuffCalculate {

    /**
     * This is 3rd version of BuffCal
     * Some parts were from BuffCal2
     * begin in 20230322
     */

    Context context;
    Activity activity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    BuffCatelogy buffCatelogy;
    ItemRss itemRss;
    ArrayList<BuffObject> buffObjectArrayList = new ArrayList<>();

    public static final int CODE_ATK_BOARD = 7190100;
    public static final int CODE_ATK_TALENT = 7190101;

    public void initGlobalVar(Context context, Activity activity, BuffCatelogy buffCatelogy, ItemRss itemRss, ArrayList<BuffObject> buffObjectArrayList){
        this.context = context;
        this.activity = activity;
        this.buffCatelogy = (buffCatelogy == null ? new BuffCatelogy() : buffCatelogy);
        this.itemRss = (itemRss == null ? new ItemRss() : itemRss);
        this.buffObjectArrayList = buffObjectArrayList;
    }
    public void initDataOfCharBuffFile (BuffObject buffObject){
        /**
         * Char Part
         */

        String char_json_stat = LoadData("db/buff/char/"+buffObject.getCharacter().getCharName().replace(" ","_")+".json");

        int charLvl = buffObject.getCharacter().getCharLvl();
        int charASC = buffObject.getCharacter().getCharASC();
        int weaponLvl = buffObject.getWeapon().getWeaponLvl();
        int weaponASC = buffObject.getWeapon().getWeaponASCLvl();
        int[] charSkillLvl = buffObject.getCharacter().getCharTalentLvl();


        int tmp_break = charASC*2 + (charLvl == BuffCatelogy.charLvlBreak[charASC+1] ? 1 : 0);
        int weapon_tmp_break = (weaponLvl >= 40 ? (weaponASC + 1)*3 : weaponASC*4) + (charLvl == BuffCatelogy.weaponLvlBreak[weaponASC+1] ? 1 : (int)((charLvl - BuffCatelogy.weaponLvlBreak[weaponASC]) / 5));

        System.out.println(weapon_tmp_break);

        Character character = buffObject.getCharacter();
        Talents talents = (buffObject.getCharacter().getTalents() == null ? new Talents() : buffObject.getCharacter().getTalents());
        Weapon weapon = (buffObject.getWeapon() == null ? new Weapon() : buffObject.getWeapon());

        try {
            JSONObject jsonObject = new JSONObject(char_json_stat);
            JSONObject 角色突破 = jsonObject.getJSONObject("角色突破");

            character.setCharBaseHP(角色突破.getJSONArray("基礎生命值").getDouble(tmp_break));
            character.setCharBaseATK(角色突破.getJSONArray("基礎攻擊力").getDouble(tmp_break));
            character.setCharBaseDEF(角色突破.getJSONArray("基礎防禦力").getDouble(tmp_break));
            character.setCharHPP(角色突破.getJSONArray("生命值加成").getDouble(tmp_break));
            character.setCharATKP(角色突破.getJSONArray("攻擊力加成").getDouble(tmp_break));
            character.setCharDEFP(角色突破.getJSONArray("防禦力加成").getDouble(tmp_break));
            character.setCharCritRate(角色突破.getJSONArray("暴擊率").getDouble(tmp_break));
            character.setCharCritDMG(角色突破.getJSONArray("暴擊傷害").getDouble(tmp_break));
            character.setCharEnRech(角色突破.getJSONArray("元素充能").getDouble(tmp_break));
            character.setCharEleMas(角色突破.getJSONArray("元素精通").getDouble(tmp_break));
            character.setCharHealP(角色突破.getJSONArray("治療加成").getDouble(tmp_break));
            character.setCharPyroDMGP(角色突破.getJSONArray("火元素傷害加成").getDouble(tmp_break));
            character.setCharHydroDMGP(角色突破.getJSONArray("水元素傷害加成").getDouble(tmp_break));
            character.setCharAnemoDMGP(角色突破.getJSONArray("風元素傷害加成").getDouble(tmp_break));
            character.setCharElectroDMGP(角色突破.getJSONArray("雷元素傷害加成").getDouble(tmp_break));
            character.setCharDendroDMGP(角色突破.getJSONArray("草元素傷害加成").getDouble(tmp_break));
            character.setCharCryoDMGP(角色突破.getJSONArray("冰元素傷害加成").getDouble(tmp_break));
            character.setCharGeoDMGP(角色突破.getJSONArray("岩元素傷害加成").getDouble(tmp_break));
            character.setCharPhyDMGP(角色突破.getJSONArray("物理傷害加成").getDouble(tmp_break));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(char_json_stat);
            JSONObject 普通攻擊 = jsonObject.getJSONObject("普通攻擊");

            talents.setNorm_1_Hit(普通攻擊.getJSONArray("一段傷害").getDouble(charSkillLvl[0] - 1));
            talents.setNorm_2_Hit(普通攻擊.getJSONArray("二段傷害").getDouble(charSkillLvl[0] - 1));
            talents.setNorm_3_Hit(普通攻擊.getJSONArray("三段傷害").getDouble(charSkillLvl[0] - 1));
            talents.setNorm_4_Hit(普通攻擊.getJSONArray("四段傷害").getDouble(charSkillLvl[0] - 1));
            talents.setNorm_5_Hit(普通攻擊.getJSONArray("五段傷害").getDouble(charSkillLvl[0] - 1));
            talents.setNorm_6_Hit(普通攻擊.getJSONArray("六段傷害").getDouble(charSkillLvl[0] - 1));
            talents.setNorm_Plunging(普通攻擊.getJSONArray("下墜期間傷害").getDouble(charSkillLvl[0] - 1));
            talents.setNorm_Low_Plunge(普通攻擊.getJSONArray("低空墜地衝擊傷害").getDouble(charSkillLvl[0] - 1));
            talents.setNorm_High_Plunge(普通攻擊.getJSONArray("高空墜地衝擊傷害").getDouble(charSkillLvl[0] - 1));
            talents.setNorm_Aimed_Shot(普通攻擊.getJSONArray("瞄準射擊").getDouble(charSkillLvl[0] - 1));
            talents.setNorm_Fully_Charged_Aimed_Shot(普通攻擊.getJSONArray("滿蓄力瞄準射擊").getDouble(charSkillLvl[0] - 1));
            talents.setNorm_Charged_Attack(普通攻擊.getJSONArray("重擊傷害").getDouble(charSkillLvl[0] - 1));
            talents.setNorm_Charged_Attack_Cyclic(普通攻擊.getJSONArray("重擊循環傷害").getDouble(charSkillLvl[0] - 1));
            talents.setNorm_Charged_Final_Cyclic(普通攻擊.getJSONArray("重擊終結傷害").getDouble(charSkillLvl[0] - 1));

            // Ganyu
            if(普通攻擊.has("一段蓄力瞄準射擊") && 普通攻擊.has("霜華矢命中傷害") && 普通攻擊.has("霜華矢·霜華綻發傷害")){
                talents.setSpecial_Talent_Value(new double[]{
                                普通攻擊.getJSONArray("一段蓄力瞄準射擊").getDouble(charSkillLvl[0] - 1),
                                普通攻擊.getJSONArray("霜華矢命中傷害").getDouble(charSkillLvl[0] - 1),
                                普通攻擊.getJSONArray("霜華矢·霜華綻發傷害").getDouble(charSkillLvl[0] - 1)
                        }
                );
            }

            // Yoimiya
            if(普通攻擊.has("焰硝矢傷害") ) {
                talents.setSpecial_Talent_Value(new double[]{
                            普通攻擊.getJSONArray("焰硝矢傷害").getDouble(charSkillLvl[0] - 1)
                        }
                );
            }

            // Itto
            if(普通攻擊.has("荒瀧逆袈裟連斬傷害") && 普通攻擊.has("荒瀧逆袈裟終結傷害") && 普通攻擊.has("左一文字斬傷害")){
                talents.setSpecial_Talent_Value(new double[]{
                            普通攻擊.getJSONArray("荒瀧逆袈裟連斬傷害").getDouble(charSkillLvl[0]-1),
                            普通攻擊.getJSONArray("荒瀧逆袈裟連斬傷害").getDouble(charSkillLvl[0]-1),
                            普通攻擊.getJSONArray("左一文字斬傷害").getDouble(charSkillLvl[0]-1)
                        }
                );
            }

            // Tartaglia
            if(普通攻擊.has("斷流·閃 傷害") && 普通攻擊.has("斷流·破 傷害")){
                talents.setSpecial_Talent_Value(new double[]{
                            普通攻擊.getJSONArray("斷流·閃 傷害").getDouble(charSkillLvl[0]-1),
                            普通攻擊.getJSONArray("斷流·破 傷害").getDouble(charSkillLvl[0]-1)
                        }
                );
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

            String[] 元素戰技_str =  new String[元素戰技STR.length()];
            double[][] 元素戰技_value = new double[元素戰技STR.length()][15];

            for (int x = 0 ; x<元素戰技STR.length() ; x++ ){
                元素戰技_str[x] = String.valueOf(元素戰技STR.get(x));
                for (int y = 0 ; y< 15 ; y++){
                    元素戰技_value[x][y] = (double) 元素戰技.getJSONArray(String.valueOf(元素戰技STR.get(x))).getDouble(y);
                }
            }

            talents.setElement_Str(元素戰技_str);
            talents.setElement_Value(元素戰技_value);


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

            String[] 元素爆發_baseName = new String[元素爆發STR.length()];
            double[][] 元素爆發_value = new double[元素爆發STR.length()][15];

            for (int x = 0 ; x<元素爆發STR.length() ; x++ ){
                元素爆發_baseName[x] = String.valueOf(元素爆發STR.get(x));
                for (int y = 0 ; y< 15 ; y++){
                    元素爆發_value[x][y] = (double) 元素爆發.getJSONArray(String.valueOf(元素爆發STR.get(x))).getDouble(y);
                }
            }

            talents.setFinal_Str(元素爆發_baseName);
            talents.setFinal_Value(元素爆發_value);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        /**
         * Weapon Part
         */

        String weapon_json = LoadData("db/buff/weapons/"+weapon.getWeaponName().replace(" ","_").replace("'","").replace("-","_")+".json");
        try {
            JSONObject jsonObject = new JSONObject(weapon_json);

            String[] mainItem = {"生命值加成","攻擊力加成","防禦力加成","暴擊率","暴擊傷害","元素充能","元素精通","物理傷害加成"};
            String[] strArr = new String[]{buffCatelogy.getStatusNameByLocaleName("基礎攻擊力",context),"N/A"};
            double[] valueArr = new double[]{jsonObject.getJSONArray("基礎攻擊力").getDouble(weapon_tmp_break),0};

            for (int x = 0 ; x < strArr.length ; x++){
                double value = jsonObject.getJSONArray(mainItem[x]).getDouble(weapon_tmp_break);
                if(value != 0){
                    strArr[1] = buffCatelogy.getStatusNameByLocaleName(mainItem[x],context);
                    valueArr[1] = value;
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    //================↑[init]===========↓[Formula]=======================//

    private double mainFormula(BuffObject buffObject, int CODE){
        return 0d;
    }


    //================↑[Formula]===========↓[Tools]=======================//

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
