package com.voc.genshin_helper.util;

import android.content.Context;

import com.voc.genshin_helper.data.Characters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Package com.voc.genshin_helper.data was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2020 Xectorda 版權所有
 */
public class CalculatorProcess {

    /** [TRANSFER] CHARACTER VAR FROM CalculatorUI*/
    public ArrayList<String> NameList = new ArrayList<String>();
    public ArrayList<Integer> BeforeLvlList= new ArrayList<Integer>();
    public ArrayList<Integer> AfterLvlList= new ArrayList<Integer>();
    public ArrayList<Integer> BeforeBreakLvlList= new ArrayList<Integer>();
    public ArrayList<Boolean> BeforeBreakUPLvlList= new ArrayList<Boolean>();
    public ArrayList<Integer> AfterBreakLvlList= new ArrayList<Integer>();
    public ArrayList<Boolean> AfterBreakUPLvlList= new ArrayList<Boolean>();
    public ArrayList<Integer> BeforeSkill1LvlList= new ArrayList<Integer>();
    public ArrayList<Integer> AfterSkill1LvlList= new ArrayList<Integer>();
    public ArrayList<Integer> BeforeSkill2LvlList= new ArrayList<Integer>();
    public ArrayList<Integer> AfterSkill2LvlList= new ArrayList<Integer>();
    public ArrayList<Integer> BeforeSkill3LvlList= new ArrayList<Integer>();
    public ArrayList<Integer> AfterSkill3LvlList= new ArrayList<Integer>();
    public ArrayList<Boolean> IsCal= new ArrayList<Boolean>();

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
    public ArrayList<String> nameREQUIREList = new ArrayList<String>();
    public ArrayList<String> crystalREQUIREList = new ArrayList<String>();
    public ArrayList<String> bossREQUIREList = new ArrayList<String>();
    public ArrayList<String> localREQUIREList = new ArrayList<String>();
    public ArrayList<String> commonREQUIREList = new ArrayList<String>();
    public ArrayList<String> bookREQUIREList = new ArrayList<String>();
    public ArrayList<String> t_bossREQUIREList = new ArrayList<String>();

    /** [METHOD] SOME CLASS AND VARS*/
    Context context;

    /** [VAR] CHARACTER LVL EXP + MORA */
    public ArrayList<Integer> mora = new ArrayList<Integer>();
    public ArrayList<Integer> exp_small = new ArrayList<Integer>();
    public ArrayList<Integer> exp_mid = new ArrayList<Integer>();
    public ArrayList<Integer> exp_big = new ArrayList<Integer>();





    public void setup(Context context ,ArrayList<String> NameList, ArrayList<Integer> BeforeLvlList, ArrayList<Integer> AfterLvlList, ArrayList<Integer> BeforeBreakLvlList, ArrayList<Integer> AfterBreakLvlList, ArrayList<Integer> BeforeSkill1LvlList, ArrayList<Integer> AfterSkill1LvlList, ArrayList<Integer> BeforeSkill2LvlList, ArrayList<Integer> AfterSkill2LvlList, ArrayList<Integer> BeforeSkill3LvlList, ArrayList<Integer> AfterSkill3LvlList, ArrayList<Boolean> IsCal, ArrayList<Boolean> BeforeBreakUPLvlList, ArrayList<Boolean> AfterBreakUPLvlList) {
        this.context = context;
        this.NameList = NameList;
        this.BeforeLvlList = BeforeLvlList;
        this.AfterLvlList = AfterLvlList;
        this.BeforeBreakLvlList = BeforeBreakLvlList;
        this.AfterBreakLvlList = AfterBreakLvlList;
        this.BeforeSkill1LvlList = BeforeSkill1LvlList;
        this.AfterSkill1LvlList = AfterSkill1LvlList;
        this.BeforeSkill2LvlList = BeforeSkill2LvlList;
        this.AfterSkill2LvlList = AfterSkill2LvlList;
        this.BeforeSkill3LvlList = BeforeSkill3LvlList;
        this.AfterSkill3LvlList = AfterSkill3LvlList;
        this.IsCal = IsCal;
        this.BeforeBreakUPLvlList = BeforeBreakUPLvlList;
        this.AfterBreakUPLvlList = AfterBreakUPLvlList;

        for(int x = 0 ; x < IsCal.size() ; x ++){
            NameList.remove(x);
            BeforeLvlList.remove(x);
            AfterLvlList.remove(x);
            BeforeBreakLvlList.remove(x);
            AfterBreakLvlList.remove(x);
            BeforeSkill1LvlList.remove(x);
            AfterSkill1LvlList.remove(x);
            BeforeSkill2LvlList.remove(x);
            AfterSkill2LvlList.remove(x);
            BeforeSkill3LvlList.remove(x);
            AfterSkill3LvlList.remove(x);
            IsCal.remove(x);
            BeforeBreakUPLvlList.remove(x);
            AfterBreakUPLvlList.remove(x);
        }
        readJSON();
    }

    public void calculate () {
        for (int x = 0 ; x < NameList.size() ; x ++){
            /** CAL EXP */
            for (int y = BeforeLvlList.get(x) ; y < AfterLvlList.get(x) ; y++){
                int exp_grade0 = 0,exp_grade1= 0,exp_grade2= 0,exp_grade3= 0,exp_grade4= 0,exp_grade5= 0,exp_grade6 = 0;
                if(y<=20){exp_grade0 = exp_grade0 + lvlEXPList.get(y-1);}
                else if(y>20 && y <= 40){exp_grade1 = exp_grade1 + lvlEXPList.get(y-1);}
                else if(y>40 && y <= 50){exp_grade2 = exp_grade2 + lvlEXPList.get(y-1);}
                else if(y>50 && y <= 60){exp_grade3 = exp_grade3 + lvlEXPList.get(y-1);}
                else if(y>60 && y <= 70){exp_grade4 = exp_grade4 + lvlEXPList.get(y-1);}
                else if(y>70 && y <= 80){exp_grade5 = exp_grade5 + lvlEXPList.get(y-1);}
                else if(y>80 && y <= 90){exp_grade6 = exp_grade6 + lvlEXPList.get(y-1);}
                // LATER WILL TURN TO EXP_BIG / SMALL / MID ONE BY ONE
            }

            /** CAL ASC */
            int beforeUP = 0 , afterUP = 0;
            if(BeforeBreakUPLvlList.get(x) == true){beforeUP = 1;}
            if(AfterBreakUPLvlList.get(x) == true){afterUP = 1;}

            for (int y = BeforeBreakLvlList.get(x)+beforeUP ; y < AfterBreakLvlList.get(x)+afterUP ; y ++){
                ArrayList<String> temp_item = new ArrayList<String>();
                temp_item.add(nameREQUIREList.get(x));
                temp_item.add(crystalREQUIREList.get(x));
                temp_item.add(bossREQUIREList.get(x));
                temp_item.add(localREQUIREList.get(x));
                temp_item.add(commonREQUIREList.get(x));
                temp_item.add(t_bossREQUIREList.get(x));
                FindItemByName(temp_item);
            }

        }
    }


    public void resultShow () {
        // Do later
    }

    /**
     *
     * @return crystal, boss, local, common, book, t-boss
     * @param temp_item
     */
    public int[] FindItemByName(ArrayList<String> temp_item){
        /** CRYSTAL*/
        if(temp_item.get(0).equals(""))

    }


    public void readJSON () {
        String char_lvl_exp = LoadData("db/char/char_lvl_exp.json");
        String char_asc_lvl = LoadData("db/char/char_asc_lvl.json");
        String char_skill_lvl = LoadData("db/char/char_skill_lvl.json");
        String char_require_asc_skill = LoadData("db/char/char_require_asc_skill.json");

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
                int mora = object.getInt("mora");
                lvlSKILLList.add(lvl);
                teachSKILLList.add(teach);
                guideSKILLList.add(guide);
                phiSKILLList.add(phi);
                com1SKILLList.add(common1);
                com2SKILLList.add(common2);
                com3SKILLList.add(common3);
                moraEXPList.add(mora);
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
                nameREQUIREList.add(name);
                crystalREQUIREList.add(crystal);
                bossREQUIREList.add(boss);
                localREQUIREList.add(local);
                commonREQUIREList.add(common);
                bookREQUIREList.add(book);
                t_bossREQUIREList.add(t_boss);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        calculate();
    }

    public String LoadData(String inFile) {
        String tContents = "";

        try {
            InputStream stream = context.getAssets().open(inFile);

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
