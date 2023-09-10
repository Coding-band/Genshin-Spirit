package com.voc.genshin_helper.util;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.gridlayout.widget.GridLayout;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.Material;
import com.voc.genshin_helper.database.DataBaseHelper;

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

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
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
    Activity activity;
    ViewPager viewPager;
    View cal_view;
    ItemRss item_rss;

    /** [VAR] CHARACTER LVL EXP + MORA */
    public ArrayList<Integer> mora = new ArrayList<Integer>();

    public ArrayList<Integer> part0_exp = new ArrayList<Integer>();
    public ArrayList<Integer> part1_exp = new ArrayList<Integer>();
    public ArrayList<Integer> part2_exp = new ArrayList<Integer>();
    public ArrayList<Integer> part3_exp = new ArrayList<Integer>();
    public ArrayList<Integer> part4_exp = new ArrayList<Integer>();
    public ArrayList<Integer> part5_exp = new ArrayList<Integer>();
    public ArrayList<Integer> part6_exp = new ArrayList<Integer>();
    int exp_small = 0;
    int exp_mid = 0;
    int exp_big = 0;
    int morax = 0;

    ArrayList<String> asc_temp_item = new ArrayList<String>();
    ArrayList<Integer> asc_temp_count = new ArrayList<Integer>(Collections.nCopies(10, 0));
    ArrayList<String> skill1_temp_item = new ArrayList<String>();
    ArrayList<Integer> skill1_temp_count = new ArrayList<Integer>(Collections.nCopies(7, 0));
    ArrayList<String> skill2_temp_item = new ArrayList<String>();
    ArrayList<Integer> skill2_temp_count = new ArrayList<Integer>(Collections.nCopies(7, 0));
    ArrayList<String> skill3_temp_item = new ArrayList<String>();
    ArrayList<Integer> skill3_temp_count = new ArrayList<Integer>(Collections.nCopies(7, 0));

    /** WEAPONS */
    /** FROM CalculatorUI*/
    public ArrayList<String> WeaponNameList = new ArrayList<>();
    public ArrayList<Integer> WeaponIdList = new ArrayList<>();
    public ArrayList<Integer> WeaponAfterBreakLvlList = new ArrayList<>();
    public ArrayList<Boolean> WeaponAfterBreakUPLvlList = new ArrayList<>();
    public ArrayList<Integer> WeaponAfterLvlList = new ArrayList<>();
    public ArrayList<Integer> WeaponBeforeBreakLvlList = new ArrayList<>();
    public ArrayList<Boolean> WeaponBeforeBreakUPLvlList = new ArrayList<>();
    public ArrayList<Integer> WeaponBeforeLvlList = new ArrayList<>();
    public ArrayList<String> WeaponFollowList = new ArrayList<>();
    public ArrayList<Boolean> WeaponIsCal = new ArrayList<>();

    public ArrayList<String> WeaponCommonREQUIREList = new ArrayList<>();
    public ArrayList<String> WeaponCopy1REQUIREList = new ArrayList<>();
    public ArrayList<String> WeaponCopy2REQUIREList = new ArrayList<>();

    public ArrayList<Integer> WeaponExpList = new ArrayList<>();
    public ArrayList<Integer> WeaponLvlList = new ArrayList<>();
    public ArrayList<Integer> WeaponMoraList = new ArrayList<>();
    public ArrayList<Integer> WeaponCopy1List = new ArrayList<>();
    public ArrayList<Integer> WeaponCopy2List = new ArrayList<>();
    public ArrayList<Integer> WeaponCommonList = new ArrayList<>();
    public ArrayList<Integer> WeaponMoraASCList = new ArrayList<>();
    public ArrayList<String> WeaponNameREQUIREList = new ArrayList<>();

    /** WEAPONS RARE*/
    public ArrayList<Integer> WeaponRare1CommonASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare1Copy1ASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare1Copy2ASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare1ExpList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare1LvlASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare1LvlList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare1MoraASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare1MoraList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare2CommonASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare2Copy1ASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare2Copy2ASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare2ExpList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare2LvlASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare2LvlList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare2MoraASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare2MoraList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare3CommonASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare3Copy1ASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare3Copy2ASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare3ExpList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare3LvlASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare3LvlList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare3MoraASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare3MoraList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare4CommonASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare4Copy1ASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare4Copy2ASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare4ExpList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare4LvlASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare4LvlList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare4MoraASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare4MoraList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare5CommonASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare5Copy1ASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare5Copy2ASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare5ExpList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare5LvlASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare5LvlList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare5MoraASCList = new ArrayList<>();
    public ArrayList<Integer> WeaponRare5MoraList = new ArrayList<>();

    public ArrayList<Integer> WeaponRareList = new ArrayList<>();
    public ArrayList<Integer> WeaponRareREQUIREList = new ArrayList<>();
    public ArrayList<String> WeaponTypeREQUIREList = new ArrayList<>();

    ArrayList<Integer> weapon_asc_temp_count = new ArrayList<>(Arrays.asList(new Integer[10]));
    ArrayList<String> weapon_asc_temp_item = new ArrayList<>();

    /** WEAPONS EXP*/
    public ArrayList<Integer> weapon_mora = new ArrayList<>();
    public ArrayList<Integer> weapon_part0_exp = new ArrayList<>();
    public ArrayList<Integer> weapon_part1_exp = new ArrayList<>();
    public ArrayList<Integer> weapon_part2_exp = new ArrayList<>();
    public ArrayList<Integer> weapon_part3_exp = new ArrayList<>();
    public ArrayList<Integer> weapon_part4_exp = new ArrayList<>();
    public ArrayList<Integer> weapon_part5_exp = new ArrayList<>();
    public ArrayList<Integer> weapon_part6_exp = new ArrayList<>();
    int weapon_small = 0;
    int weapon_mid = 0;
    int weapon_big = 0;

    // STATUS_CAL_FINISHED
    boolean char_fin = false;
    boolean weapon_fin = false;
    boolean artifact_fin = false;
    String dataSheetName = "NaN";

    /** Artifacts from CalculatorUI */
    public ArrayList<String> ArtifactNameList = new ArrayList<>();
    public ArrayList<Integer> ArtifactAfterLvlList = new ArrayList<>();
    public ArrayList<Integer> ArtifactBeforeLvlList = new ArrayList<>();
    public ArrayList<String> ArtifactFollowList = new ArrayList<>();
    public ArrayList<Integer> ArtifactRareList = new ArrayList<>();
    public ArrayList<Boolean> ArtifactIsCal = new ArrayList<>();
    public ArrayList<String> artifactType = new ArrayList<>();
    public ArrayList<Integer> artifactId = new ArrayList<>();


    public ArrayList<Integer> ArtifactRare1LvlList = new ArrayList<>();
    public ArrayList<Integer> ArtifactRare1ExpList = new ArrayList<>();
    public ArrayList<Integer> ArtifactRare1MoraList = new ArrayList<>();
    public ArrayList<Integer> ArtifactRare2LvlList = new ArrayList<>();
    public ArrayList<Integer> ArtifactRare2ExpList = new ArrayList<>();
    public ArrayList<Integer> ArtifactRare2MoraList = new ArrayList<>();
    public ArrayList<Integer> ArtifactRare3LvlList = new ArrayList<>();
    public ArrayList<Integer> ArtifactRare3ExpList = new ArrayList<>();
    public ArrayList<Integer> ArtifactRare3MoraList = new ArrayList<>();
    public ArrayList<Integer> ArtifactRare4LvlList = new ArrayList<>();
    public ArrayList<Integer> ArtifactRare4ExpList = new ArrayList<>();
    public ArrayList<Integer> ArtifactRare4MoraList = new ArrayList<>();
    public ArrayList<Integer> ArtifactRare5LvlList = new ArrayList<>();
    public ArrayList<Integer> ArtifactRare5ExpList = new ArrayList<>();
    public ArrayList<Integer> ArtifactRare5MoraList = new ArrayList<>();

    /** TEMP */
    public ArrayList<Integer> ArtifactExpList = new ArrayList<>();
    public ArrayList<Integer> ArtifactLvlList = new ArrayList<>();
    public ArrayList<Integer> ArtifactMoraList = new ArrayList<>();
    int artifact_mid = 0;
    int artifact_big = 0;

    RecyclerView mList;
    Material material;


    public void setVP(ViewPager viewPager, View viewPager3) {
        //System.out.println("setVP_BEFORE : "+System.currentTimeMillis());
        this.viewPager = viewPager;
        this.cal_view = viewPager3;
        mList = viewPager.findViewById(R.id.siptik_result_ll);
        //System.out.println("setVP_AFTER : "+System.currentTimeMillis());
    }

    public void setup(Context context,Activity activity) {
        this.context = context;
        this.activity = activity;
        material = new Material();

        for (int k = 0 ; k < NameList.size() ; k ++){
            part0_exp.add(0);
            part1_exp.add(0);
            part2_exp.add(0);
            part3_exp.add(0);
            part4_exp.add(0);
            part5_exp.add(0);
            part6_exp.add(0);

            weapon_part1_exp.add(0);
            weapon_part2_exp.add(0);
            weapon_part3_exp.add(0);
            weapon_part4_exp.add(0);
            weapon_part5_exp.add(0);
            weapon_part6_exp.add(0);
        }

        item_rss = new ItemRss();

    }

    public void weapon_setup(ArrayList<String> arrayList, ArrayList<Integer> arrayList2, ArrayList<Integer> arrayList3, ArrayList<Integer> arrayList4, ArrayList<Integer> arrayList5, ArrayList<Boolean> arrayList6, ArrayList<Boolean> arrayList7, ArrayList<Boolean> arrayList8, ArrayList<String> arrayList9, ArrayList<Integer> arrayList10,ArrayList<Integer> arrayList11,String arg) {

        this.WeaponNameList = arrayList;
        this.WeaponBeforeLvlList = arrayList2;
        this.WeaponAfterLvlList = arrayList3;
        this.WeaponBeforeBreakLvlList = arrayList4;
        this.WeaponAfterBreakLvlList = arrayList5;
        this.WeaponIsCal = arrayList6;
        this.WeaponBeforeBreakUPLvlList = arrayList7;
        this.WeaponAfterBreakUPLvlList = arrayList8;
        this.WeaponFollowList = arrayList9;
        this.WeaponRareList = arrayList10;
        this.WeaponIdList = arrayList11;


        //Log.wtf("Procedure","weapon_setup"+" || "+System.currentTimeMillis());
        if(arg.equals("READ")){
            weapon_readJSON();
        }

    }


    public void artifact_setup(ArrayList<String> artifactChoosedNameList, ArrayList<Integer> artifactChoosedBeforeLvlList, ArrayList<Integer> artifactChoosedAfterLvlList, ArrayList<Boolean> artifactChoosedIsCal, ArrayList<String> artifactChoosedFollowList, ArrayList<Integer> artifactChoosedRare, ArrayList<String> artifactChoosedType, ArrayList<Integer> artifactChoosedIdList ,String arg) {
        this.ArtifactNameList = artifactChoosedNameList;
        this.ArtifactBeforeLvlList = artifactChoosedBeforeLvlList;
        this.ArtifactAfterLvlList = artifactChoosedAfterLvlList;
        this.ArtifactIsCal = artifactChoosedIsCal;
        this.ArtifactFollowList = artifactChoosedFollowList;
        this.ArtifactRareList = artifactChoosedRare;
        this.artifactType = artifactChoosedType;
        this.artifactId = artifactChoosedIdList;

        //Log.wtf("Procedure","artifact_setup"+" || "+System.currentTimeMillis());
        if(arg.equals("READ")){
            artifact_readJSON();
        }
    }

    public void char_setup(ArrayList<String> arrayList, ArrayList<Integer> arrayList2, ArrayList<Integer> arrayList3, ArrayList<Integer> arrayList4, ArrayList<Integer> arrayList5, ArrayList<Integer> arrayList6, ArrayList<Integer> arrayList7, ArrayList<Integer> arrayList8, ArrayList<Integer> arrayList9, ArrayList<Integer> arrayList10, ArrayList<Integer> arrayList11, ArrayList<Boolean> arrayList12, ArrayList<Boolean> arrayList13, ArrayList<Boolean> arrayList14,String arg) {
        this.NameList = arrayList;
        this.BeforeLvlList = arrayList2;
        this.AfterLvlList = arrayList3;
        this.BeforeBreakLvlList = arrayList4;
        this.AfterBreakLvlList = arrayList5;
        this.BeforeSkill1LvlList = arrayList6;
        this.AfterSkill1LvlList = arrayList7;
        this.BeforeSkill2LvlList = arrayList8;
        this.AfterSkill2LvlList = arrayList9;
        this.BeforeSkill3LvlList = arrayList10;
        this.AfterSkill3LvlList = arrayList11;
        this.IsCal = arrayList12;
        this.BeforeBreakUPLvlList = arrayList13;
        this.AfterBreakUPLvlList = arrayList14;

        //Log.wtf("Procedure","char_setup"+" || "+System.currentTimeMillis());
        if(arg.equals("READ")){
            char_readJSON();
        }
    }

    public void char_calculate () {
        //Log.wtf("HEY","TOMMY");
        for (int x = 0 ; x < NameList.size() ; x ++) {
            if(IsCal.get(x) == true){
                /** CAL EXP */
                int exp_grade0 = 0,exp_grade1= 0,exp_grade2= 0,exp_grade3= 0,exp_grade4= 0,exp_grade5= 0,exp_grade6 = 0;
                asc_temp_item.clear();
                asc_temp_count.clear();
                skill1_temp_item.clear();
                skill1_temp_count.clear();
                skill2_temp_item.clear();
                skill2_temp_count.clear();
                skill3_temp_item.clear();
                skill3_temp_count.clear();

                for (int k = 0 ; k < 9 ; k ++){
                    asc_temp_count.add(0);
                }
                for (int k = 0 ; k < 7 ; k ++){
                    skill1_temp_count.add(0);
                    skill2_temp_count.add(0);
                    skill3_temp_count.add(0);
                }

                for (int y = BeforeLvlList.get(x) ; y < AfterLvlList.get(x) ; y++){
                    if(y>80 && y <= 90){exp_grade6 = exp_grade6 + expEXPList.get(y);}
                    else if(y>70 && y <= 80){exp_grade5 = exp_grade5 + expEXPList.get(y);}
                    else if(y>60 && y <= 70){exp_grade4 = exp_grade4 + expEXPList.get(y);}
                    else if(y>50 && y <= 60){exp_grade3 = exp_grade3 + expEXPList.get(y);}
                    else if(y>40 && y <= 50){exp_grade2 = exp_grade2 + expEXPList.get(y);}
                    else if(y>20 && y <= 40){exp_grade1 = exp_grade1 + expEXPList.get(y);}
                    else if(y<=20){exp_grade0 = exp_grade0 + expEXPList.get(y);}
                    morax = morax + moraEXPList.get(y);
                }

                part0_exp.add(exp_grade0);
                part1_exp.add(exp_grade1);
                part2_exp.add(exp_grade2);
                part3_exp.add(exp_grade3);
                part4_exp.add(exp_grade4);
                part5_exp.add(exp_grade5);
                part6_exp.add(exp_grade6);

                getCharEXPItemCount(exp_grade0);
                getCharEXPItemCount(exp_grade1);
                getCharEXPItemCount(exp_grade2);
                getCharEXPItemCount(exp_grade3);
                getCharEXPItemCount(exp_grade4);
                getCharEXPItemCount(exp_grade5);
                getCharEXPItemCount(exp_grade6);

                //Log.wtf("HEY","PAIMON");

                /** CAL ASC */
                int beforeUP = 0 , afterUP = 0;
                if(BeforeBreakUPLvlList.get(x) == true){beforeUP = 1;}
                if(AfterBreakUPLvlList.get(x) == true){afterUP = 1;}

                //System.out.println("SS"+BeforeBreakLvlList);
                //System.out.println("RR"+AfterBreakLvlList);

                //Log.wtf("BEFORE_LVL",String.valueOf(BeforeLvlList.get(x)));
                //Log.wtf("AFTER_LVL",String.valueOf(AfterLvlList.get(x)));
                //Log.wtf("BEFORE_UP",String.valueOf(beforeUP));
                //Log.wtf("AFTER_UP",String.valueOf(afterUP));

                int z = getCharRealPosByName(NameList.get(x));
                asc_temp_item.add(nameREQUIREList.get(z));
                asc_temp_item.add(crystalREQUIREList.get(z));
                asc_temp_item.add(bossREQUIREList.get(z));
                asc_temp_item.add(localREQUIREList.get(z));
                asc_temp_item.add(commonREQUIREList.get(z));

                for (int y = BeforeBreakLvlList.get(x)+beforeUP ; y < AfterBreakLvlList.get(x)+afterUP ; y ++){

                    //Log.wtf("Y is",String.valueOf(y));
                    //Log.wtf("AfterBreakLvlList.get(x) is",String.valueOf(AfterBreakLvlList.get(x)));
                    asc_temp_count.set(0,asc_temp_count.get(0) + silverASCList.get(y));
                    asc_temp_count.set(1,asc_temp_count.get(1) +fragASCList.get(y));
                    asc_temp_count.set(2,asc_temp_count.get(2) +chunkASCList.get(y));
                    asc_temp_count.set(3,asc_temp_count.get(3) +gemASCList.get(y));
                    asc_temp_count.set(4,asc_temp_count.get(4) +localASCList.get(y));
                    asc_temp_count.set(5,asc_temp_count.get(5) +com1ASCList.get(y));
                    asc_temp_count.set(6,asc_temp_count.get(6) +com2ASCList.get(y));
                    asc_temp_count.set(7,asc_temp_count.get(7) +com3ASCList.get(y));
                    asc_temp_count.set(8,asc_temp_count.get(8) +bossASCList.get(y));
                    //mora.add(moraASCList.get(y));
                    morax = morax + moraASCList.get(y);

                }
                material.FindItemByName(asc_temp_item,asc_temp_count);


                /** CAL SKILL1 */

                z = getCharRealPosByName(NameList.get(x));
                skill1_temp_item.add(nameREQUIREList.get(z));
                skill1_temp_item.add(bookREQUIREList.get(z));
                skill1_temp_item.add(commonREQUIREList.get(z));
                skill1_temp_item.add(t_bossREQUIREList.get(z));

                for (int y = BeforeSkill1LvlList.get(x) ; y < AfterSkill1LvlList.get(x) ; y ++){

                    skill1_temp_count.set(0,skill1_temp_count.get(0) + teachSKILLList.get(y));
                    skill1_temp_count.set(1,skill1_temp_count.get(1) + guideSKILLList.get(y));
                    skill1_temp_count.set(2,skill1_temp_count.get(2) + phiSKILLList.get(y));
                    skill1_temp_count.set(3,skill1_temp_count.get(3) + com1SKILLList.get(y));
                    skill1_temp_count.set(4,skill1_temp_count.get(4) + com2SKILLList.get(y));
                    skill1_temp_count.set(5,skill1_temp_count.get(5) + com3SKILLList.get(y));
                    skill1_temp_count.set(6,skill1_temp_count.get(6) + bossSKILLList.get(y));

                    if(y == 9) {material.智識之冕 = material.智識之冕 + 1;}

                    // mora.add(moraSkillList.get(y));
                    morax = morax + moraSkillList.get(y);
                }
                material.FindItemByName(skill1_temp_item,skill1_temp_count);

                z = getCharRealPosByName(NameList.get(x));
                skill2_temp_item.add(nameREQUIREList.get(z));
                skill2_temp_item.add(bookREQUIREList.get(z));
                skill2_temp_item.add(commonREQUIREList.get(z));
                skill2_temp_item.add(t_bossREQUIREList.get(z));

                for (int y = BeforeSkill2LvlList.get(x) ; y < AfterSkill2LvlList.get(x) ; y ++){

                    skill2_temp_count.set(0,skill2_temp_count.get(0) + teachSKILLList.get(y));
                    skill2_temp_count.set(1,skill2_temp_count.get(1) + guideSKILLList.get(y));
                    skill2_temp_count.set(2,skill2_temp_count.get(2) + phiSKILLList.get(y));
                    skill2_temp_count.set(3,skill2_temp_count.get(3) + com1SKILLList.get(y));
                    skill2_temp_count.set(4,skill2_temp_count.get(4) + com2SKILLList.get(y));
                    skill2_temp_count.set(5,skill2_temp_count.get(5) + com3SKILLList.get(y));
                    skill2_temp_count.set(6,skill2_temp_count.get(6) + bossSKILLList.get(y));

                    if(y == 9) {material.智識之冕 = material.智識之冕 + 1;}
                    //mora.add(moraSkillList.get(y));
                    morax = morax + moraSkillList.get(y);
                }
                material.FindItemByName(skill2_temp_item,skill2_temp_count);

                z = getCharRealPosByName(NameList.get(x));
                skill3_temp_item.add(nameREQUIREList.get(z));
                skill3_temp_item.add(bookREQUIREList.get(z));
                skill3_temp_item.add(commonREQUIREList.get(z));
                skill3_temp_item.add(t_bossREQUIREList.get(z));

                for (int y = BeforeSkill3LvlList.get(x) ; y < AfterSkill3LvlList.get(x) ; y ++){

                    skill3_temp_count.set(0,skill3_temp_count.get(0) + teachSKILLList.get(y));
                    skill3_temp_count.set(1,skill3_temp_count.get(1) + guideSKILLList.get(y));
                    skill3_temp_count.set(2,skill3_temp_count.get(2) + phiSKILLList.get(y));
                    skill3_temp_count.set(3,skill3_temp_count.get(3) + com1SKILLList.get(y));
                    skill3_temp_count.set(4,skill3_temp_count.get(4) + com2SKILLList.get(y));
                    skill3_temp_count.set(5,skill3_temp_count.get(5) + com3SKILLList.get(y));
                    skill3_temp_count.set(6,skill3_temp_count.get(6) + bossSKILLList.get(y));

                    if(y ==9) {material.智識之冕 = material.智識之冕 + 1;}
                    // mora.add(moraSkillList.get(y));
                    morax = morax + moraSkillList.get(y);
                }
                material.FindItemByName(skill3_temp_item,skill3_temp_count);

            }
        }
        char_fin = true;
        check_cal_finished();
    }

    public void weapon_calculate () {

        //Log.wtf("Procedure","weapon_calculate1"+" || "+System.currentTimeMillis());
        //Log.wtf("HEY","TOMMY");
        for (int x = 0 ; x < WeaponNameList.size() ; x ++) {
            if(WeaponIsCal.get(x) == true){
                /** CAL EXP */
                int exp_grade0 = 0,exp_grade1= 0,exp_grade2= 0,exp_grade3= 0,exp_grade4= 0,exp_grade5= 0,exp_grade6 = 0;
                weapon_asc_temp_item.clear();
                weapon_asc_temp_count.clear();

                for (int k = 0 ; k < 10 ; k ++){weapon_asc_temp_count.add(0);}

                int rare_tmp = WeaponRareList.get(x);

                if(rare_tmp == 1){

                    WeaponLvlList = WeaponRare1LvlList;
                    WeaponExpList = WeaponRare1ExpList;
                    WeaponMoraList = WeaponRare1MoraList;
                    WeaponCopy1List = WeaponRare1Copy1ASCList;
                    WeaponCopy2List = WeaponRare1Copy2ASCList;
                    WeaponCommonList = WeaponRare1CommonASCList;
                    WeaponMoraASCList = WeaponRare1MoraASCList;
                }else if(rare_tmp == 2){
                    WeaponLvlList = WeaponRare2LvlList;
                    WeaponExpList = WeaponRare2ExpList;
                    WeaponMoraList = WeaponRare2MoraList;
                    WeaponCopy1List = WeaponRare2Copy1ASCList;
                    WeaponCopy2List = WeaponRare2Copy2ASCList;
                    WeaponCommonList = WeaponRare2CommonASCList;
                    WeaponMoraASCList = WeaponRare2MoraASCList;
                }else if (rare_tmp == 3) {
                    WeaponLvlList = WeaponRare3LvlList;
                    WeaponExpList = WeaponRare3ExpList;
                    WeaponMoraList = WeaponRare3MoraList;
                    WeaponCopy1List = WeaponRare3Copy1ASCList;
                    WeaponCopy2List = WeaponRare3Copy2ASCList;
                    WeaponCommonList = WeaponRare3CommonASCList;
                    WeaponMoraASCList = WeaponRare3MoraASCList;
                }else if (rare_tmp == 4){
                    WeaponLvlList = WeaponRare4LvlList;
                    WeaponExpList = WeaponRare4ExpList;
                    WeaponMoraList = WeaponRare4MoraList;
                    WeaponCopy1List = WeaponRare4Copy1ASCList;
                    WeaponCopy2List = WeaponRare4Copy2ASCList;
                    WeaponCommonList = WeaponRare4CommonASCList;
                    WeaponMoraASCList = WeaponRare4MoraASCList;
                }else if (rare_tmp == 5){
                    WeaponLvlList = WeaponRare5LvlList;
                    WeaponExpList = WeaponRare5ExpList;
                    WeaponMoraList = WeaponRare5MoraList;
                    WeaponCopy1List = WeaponRare5Copy1ASCList;
                    WeaponCopy2List = WeaponRare5Copy2ASCList;
                    WeaponCommonList = WeaponRare5CommonASCList;
                    WeaponMoraASCList = WeaponRare5MoraASCList;
                }

                int z = getWeaponRealPosByName(WeaponNameList.get(x));
                weapon_asc_temp_item.add(WeaponNameREQUIREList.get(z));
                weapon_asc_temp_item.add(WeaponCopy1REQUIREList.get(z));
                weapon_asc_temp_item.add(WeaponCopy2REQUIREList.get(z));
                weapon_asc_temp_item.add(WeaponCommonREQUIREList.get(z));

                for (int y = WeaponBeforeLvlList.get(x) ; y < WeaponAfterLvlList.get(x) ; y++){

                    if(y>80 && y <= 90){exp_grade6 = exp_grade6 + WeaponExpList.get(y);}
                    else if(y>70 && y <= 80){exp_grade5 = exp_grade5 + WeaponExpList.get(y);}
                    else if(y>60 && y <= 70){exp_grade4 = exp_grade4 + WeaponExpList.get(y);}
                    else if(y>50 && y <= 60){exp_grade3 = exp_grade3 + WeaponExpList.get(y);}
                    else if(y>40 && y <= 50){exp_grade2 = exp_grade2 + WeaponExpList.get(y);}
                    else if(y>20 && y <= 40){exp_grade1 = exp_grade1 + WeaponExpList.get(y);}
                    else if(y<=20){exp_grade0 = exp_grade0 + WeaponExpList.get(y);}

                    //mora.add(moraEXPList.get(y));
                    morax = morax + WeaponMoraList.get(y);
                }

                weapon_part0_exp.add(exp_grade0);
                weapon_part1_exp.add(exp_grade1);
                weapon_part2_exp.add(exp_grade2);
                weapon_part3_exp.add(exp_grade3);
                weapon_part4_exp.add(exp_grade4);
                weapon_part5_exp.add(exp_grade5);
                weapon_part6_exp.add(exp_grade6);

                getWeaponEXPItemCount(exp_grade0);
                getWeaponEXPItemCount(exp_grade1);
                getWeaponEXPItemCount(exp_grade2);
                getWeaponEXPItemCount(exp_grade3);
                getWeaponEXPItemCount(exp_grade4);
                getWeaponEXPItemCount(exp_grade5);
                getWeaponEXPItemCount(exp_grade6);

                //Log.wtf("HEY","PAIMON");

                /** CAL ASC */
                int beforeUP = 0 , afterUP = 0;
                if(WeaponBeforeBreakUPLvlList.get(x) == true){beforeUP = 1;}
                if(WeaponAfterBreakUPLvlList.get(x) == true){afterUP = 1;}

                //System.out.println("SS"+BeforeBreakLvlList);
                //System.out.println("RR"+AfterBreakLvlList);

                //Log.wtf("BEFORE_LVL",String.valueOf(WeaponBeforeLvlList.get(x)));
                //Log.wtf("AFTER_LVL",String.valueOf(WeaponAfterLvlList.get(x)));
                //Log.wtf("BEFORE_UP",String.valueOf(beforeUP));
                //Log.wtf("AFTER_UP",String.valueOf(afterUP));
                //Log.wtf("BEFORE_BREAK",String.valueOf(WeaponBeforeBreakLvlList.get(x)));
                //Log.wtf("AFTER_BREAK",String.valueOf(WeaponAfterBreakLvlList.get(x)));

                for (int y = WeaponBeforeBreakLvlList.get(x)+beforeUP ; y < WeaponAfterBreakLvlList.get(x)+afterUP ; y ++){
                    morax = morax + WeaponMoraASCList.get(y);
                }

                if (WeaponRareList.get(x) == 1 || WeaponRareList.get(x) == 2) {
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 1 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=1) {
                        weapon_asc_temp_count.set(0,weapon_asc_temp_count.get(0) + WeaponCopy1List.get(0));
                        weapon_asc_temp_count.set(4,weapon_asc_temp_count.get(4) + WeaponCopy2List.get(0));
                        weapon_asc_temp_count.set(7,weapon_asc_temp_count.get(7) + WeaponCommonList.get(0));
                        //Log.wtf("ABC","X");
                    }
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 2 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=2) {
                        weapon_asc_temp_count.set(1,weapon_asc_temp_count.get(1) + WeaponCopy1List.get(1));
                        weapon_asc_temp_count.set(4,weapon_asc_temp_count.get(4) + WeaponCopy2List.get(1));
                        weapon_asc_temp_count.set(7,weapon_asc_temp_count.get(7) + WeaponCommonList.get(1));
                        //Log.wtf("ABC","X");
                    }
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 3 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=3) {
                        weapon_asc_temp_count.set(1,weapon_asc_temp_count.get(1) + WeaponCopy1List.get(2));
                        weapon_asc_temp_count.set(5,weapon_asc_temp_count.get(5) + WeaponCopy2List.get(2));
                        weapon_asc_temp_count.set(8,weapon_asc_temp_count.get(8) + WeaponCommonList.get(2));
                        //Log.wtf("ABC","X");
                    }
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 4 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=4) {
                        weapon_asc_temp_count.set(2,weapon_asc_temp_count.get(2) + WeaponCopy1List.get(3));
                        weapon_asc_temp_count.set(5,weapon_asc_temp_count.get(5) + WeaponCopy2List.get(3));
                        weapon_asc_temp_count.set(8,weapon_asc_temp_count.get(8) + WeaponCommonList.get(3));
                        //Log.wtf("ABC","X");
                    }
                }else if (WeaponRareList.get(x) == 3 || WeaponRareList.get(x) == 4 || WeaponRareList.get(x) == 5) {
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 1 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=1) {
                        weapon_asc_temp_count.set(0,weapon_asc_temp_count.get(0) + WeaponCopy1List.get(0));
                        weapon_asc_temp_count.set(4,weapon_asc_temp_count.get(4) + WeaponCopy2List.get(0));
                        weapon_asc_temp_count.set(7,weapon_asc_temp_count.get(7) + WeaponCommonList.get(0));
                        //Log.wtf("ABC","X");
                    }
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 2 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=2) {
                        weapon_asc_temp_count.set(1,weapon_asc_temp_count.get(1) + WeaponCopy1List.get(1));
                        weapon_asc_temp_count.set(4,weapon_asc_temp_count.get(4) + WeaponCopy2List.get(1));
                        weapon_asc_temp_count.set(7,weapon_asc_temp_count.get(7) + WeaponCommonList.get(1));
                        //Log.wtf("ABC","X");
                    }
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 3 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=3) {
                        weapon_asc_temp_count.set(1,weapon_asc_temp_count.get(1) + WeaponCopy1List.get(2));
                        weapon_asc_temp_count.set(5,weapon_asc_temp_count.get(5) + WeaponCopy2List.get(2));
                        weapon_asc_temp_count.set(8,weapon_asc_temp_count.get(8) + WeaponCommonList.get(2));
                        //Log.wtf("ABC","X");
                    }
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 4 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=4) {
                        weapon_asc_temp_count.set(2,weapon_asc_temp_count.get(2) + WeaponCopy1List.get(3));
                        weapon_asc_temp_count.set(5,weapon_asc_temp_count.get(5) + WeaponCopy2List.get(3));
                        weapon_asc_temp_count.set(8,weapon_asc_temp_count.get(8) + WeaponCommonList.get(3));
                        //Log.wtf("ABC","X");
                    }
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 5 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=5) {
                        weapon_asc_temp_count.set(2,weapon_asc_temp_count.get(2) + WeaponCopy1List.get(4));
                        weapon_asc_temp_count.set(6,weapon_asc_temp_count.get(6) + WeaponCopy2List.get(4));
                        weapon_asc_temp_count.set(9,weapon_asc_temp_count.get(9) + WeaponCommonList.get(4));
                        //Log.wtf("ABC","X");
                    }
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 6 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=6) {
                        weapon_asc_temp_count.set(3,weapon_asc_temp_count.get(3) + WeaponCopy1List.get(5));
                        weapon_asc_temp_count.set(6,weapon_asc_temp_count.get(6) + WeaponCopy2List.get(5));
                        weapon_asc_temp_count.set(9,weapon_asc_temp_count.get(9) + WeaponCommonList.get(5));
                        //Log.wtf("ABC","X");
                    }
                }

                //Log.wtf("Procedure","weapon_calculate2"+" || "+System.currentTimeMillis());
                material.FindWeaponItemByName(weapon_asc_temp_item,weapon_asc_temp_count);

            }
        }
        weapon_fin = true;

        //Log.wtf("Procedure","weapon_calculate3"+" || "+System.currentTimeMillis());
        check_cal_finished();
    }

    public void artifact_calculate () {
        //Log.wtf("HEY","TOMMYS");

        //Log.wtf("Procedure","artifact_calculate1"+" || "+System.currentTimeMillis());
        for (int x = 0 ; x < ArtifactNameList.size() ; x ++) {
            if (ArtifactIsCal.get(x) == true) {
                /** CAL EXP */
                int exp_tmp = 0;

                int rare_tmp = ArtifactRareList.get(x);
                //Log.wtf("rare_tmp", String.valueOf(rare_tmp));

                if (rare_tmp == 1) {
                    ArtifactLvlList = ArtifactRare1LvlList;
                    ArtifactExpList = ArtifactRare1ExpList;
                    ArtifactMoraList = ArtifactRare1MoraList;
                } else if (rare_tmp == 2) {
                    ArtifactLvlList = ArtifactRare2LvlList;
                    ArtifactExpList = ArtifactRare2ExpList;
                    ArtifactMoraList = ArtifactRare2MoraList;
                } else if (rare_tmp == 3) {
                    ArtifactLvlList = ArtifactRare3LvlList;
                    ArtifactExpList = ArtifactRare3ExpList;
                    ArtifactMoraList = ArtifactRare3MoraList;
                } else if (rare_tmp == 4) {
                    ArtifactLvlList = ArtifactRare4LvlList;
                    ArtifactExpList = ArtifactRare4ExpList;
                    ArtifactMoraList = ArtifactRare4MoraList;
                } else if (rare_tmp == 5) {
                    ArtifactLvlList = ArtifactRare5LvlList;
                    ArtifactExpList = ArtifactRare5ExpList;
                    ArtifactMoraList = ArtifactRare5MoraList;
                }

                for (int y = ArtifactBeforeLvlList.get(x); y < ArtifactAfterLvlList.get(x); y++) {
                    exp_tmp = exp_tmp + ArtifactExpList.get(y);
                    morax = morax + ArtifactMoraList.get(y);
                }

                getArtifactEXPItemCount(exp_tmp);

            }
        }
        artifact_fin = true;

        //Log.wtf("Procedure","artifact_calculate2"+" || "+System.currentTimeMillis());
        check_cal_finished();
    }

    public void check_cal_finished(){
        if(weapon_fin == true && char_fin == true){

            //Log.wtf("Procedure","check_cal_finished"+" || "+System.currentTimeMillis());
            weapon_fin = false;
            char_fin = false;
            resultShow();
            saveToDB();
        }
    }

    public int getCharRealPosByName(String s) {
        for (int x = 0 ; x < nameREQUIREList.size() ; x++){
            if(nameREQUIREList.get(x).equals(s)){
                return x;
            }
        }
        return 0;
    }

    public int getWeaponRealPosByName(String s) {
        for (int x = 0 ; x < WeaponNameREQUIREList.size() ; x++){
            if(WeaponNameREQUIREList.get(x).equals(s)){
                return x;
            }
        }
        return 0;
    }

    /**EDIT WHEN ADD NEW ITEMS*/
    public void resultShow () {
        Log.wtf("Procedure","resultShowBegin"+" || "+System.currentTimeMillis());
        /** INIT of UI GONE*/
        TextView result_crystal_title = viewPager.findViewById(R.id.result_crystal_title);
        TextView result_boss_title = viewPager.findViewById(R.id.result_boss_title);
        TextView result_weekboss_title = viewPager.findViewById(R.id.result_weekboss_title);
        TextView result_local_title = viewPager.findViewById(R.id.result_local_title);
        TextView result_weeklybk1_title = viewPager.findViewById(R.id.result_weeklybk1_title);
        TextView result_weeklybk2_title = viewPager.findViewById(R.id.result_weeklybk2_title);
        TextView result_weeklybk3_title = viewPager.findViewById(R.id.result_weeklybk3_title);
        TextView result_other_title = viewPager.findViewById(R.id.result_other_title);
        TextView result_common_title = viewPager.findViewById(R.id.result_common_title);

        TextView result_set_title = viewPager.findViewById(R.id.result_set_title);
        TextView result_result_title = viewPager.findViewById(R.id.result_result_title);
        TextView barrier6 = viewPager.findViewById(R.id.barrier6);
        TextView barrier7 = viewPager.findViewById(R.id.barrier7);

        result_crystal_title.setVisibility(View.GONE);
        result_boss_title.setVisibility(View.GONE);
        result_weekboss_title.setVisibility(View.GONE);
        result_local_title.setVisibility(View.GONE);
        result_weeklybk1_title.setVisibility(View.GONE);
        result_weeklybk2_title.setVisibility(View.GONE);
        result_weeklybk3_title.setVisibility(View.GONE);
        result_other_title.setVisibility(View.GONE);
        result_common_title.setVisibility(View.GONE);

        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info",MODE_PRIVATE);
        String color_hex = sharedPreferences.getString("theme_color_hex","#FF5A5A"); // Must include #
        boolean isColorGradient = sharedPreferences.getBoolean("theme_color_gradient",false); // Must include #
        String start_color = sharedPreferences.getString("start_color","#AEFEFF"); // Must include #
        String end_color = sharedPreferences.getString("end_color","#35858B"); // Must include #

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



        colorGradient(result_crystal_title,start_color,end_color,isColorGradient,color_hex);
        colorGradient(result_boss_title,start_color,end_color,isColorGradient,color_hex);
        colorGradient(result_weekboss_title,start_color,end_color,isColorGradient,color_hex);
        colorGradient(result_local_title,start_color,end_color,isColorGradient,color_hex);
        colorGradient(result_weeklybk1_title,start_color,end_color,isColorGradient,color_hex);
        colorGradient(result_weeklybk2_title,start_color,end_color,isColorGradient,color_hex);
        colorGradient(result_weeklybk3_title,start_color,end_color,isColorGradient,color_hex);
        colorGradient(result_other_title,start_color,end_color,isColorGradient,color_hex);
        colorGradient(result_common_title,start_color,end_color,isColorGradient,color_hex);
        colorGradient(result_set_title,start_color,end_color,isColorGradient,color_hex);
        colorGradient(result_result_title,start_color,end_color,isColorGradient,color_hex);

        if(isColorGradient){
            color_hex = start_color;
        }

        barrier6	.setBackgroundColor(Color.parseColor(color_hex));
        barrier7	.setBackgroundColor(Color.parseColor(color_hex));


        /** CHARACTER ICON + WEAPON ICON*/
        GridLayout gridLayout = new GridLayout(context);
        gridLayout = viewPager.findViewById(R.id.result_char_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        ImageView item_img;
        TextView tv;
        final int radius = 180;
        final int margin = 4;
        //gridLayout.setRowCount(5);
        //        gridLayout.setColumnCount((int)(NameList.size()/5)+1);
        //        int width = (int) ((ScreenSizeUtils.getInstance(context).getScreenWidth()-64)/5);


        DisplayMetrics displayMetrics_w = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics_w);
        int height_w = displayMetrics_w.heightPixels;
        int width_w = displayMetrics_w.widthPixels-64;

        int column = (width_w / 128) ;
        int column150 = (width_w / 150) ;
        int column200 = (width_w / 200) ;

        if(width_w < 128){
            column = 1;
            column150 = 1;
            column200 = 1;
        }

        int size = width_w/column;
        int size150 = width_w/column150;
        int size200 = width_w/column200;


        if(width_w/column > 128){
            size = 128;
        }
        if(width_w/column150 > 150){
            size150 = 150;
        }
        if(width_w/column200 > 200){
            size200 = 200;
        }

        gridLayout = viewPager.findViewById(R.id.result_char_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        //Log.wtf("Procedure","resultShow0"+" || "+System.currentTimeMillis());

        final Transformation transformation = new RoundedCornersTransformation(radius, margin);
        for (int x = 0, c = 0, r = 0; NameList != null && WeaponNameList != null && x < NameList.size() + WeaponNameList.size(); x++, c++) {
            if(c == column150) { c = 0;r++; }

            if(x < NameList.size()){
                View view = View.inflate(context, R.layout.item_cal_img, null);
                ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
                TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
                LinearLayout linearLayout5 = view.findViewById(R.id.linearLayout5);
                item_cal_img.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
                item_cal_img.getLayoutParams().width = size150;
                linearLayout5.getLayoutParams().height  = LinearLayout.LayoutParams.WRAP_CONTENT;
                linearLayout5.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
                Picasso.get()
                        .load (item_rss.getCharByName(NameList.get(x),context)[3])
                        .transform(transformation)
                        .resize(size150,size150)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(item_rss.getCharByName(NameList.get(x),context)[1]);

                if(IsCal.get(x) == false){
                    Drawable drawable = context.getResources().getDrawable(R.drawable.barrier);
                    item_cal_img.setForeground(drawable);
                }
                gridLayout.addView(view);
                GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                param.height = GridLayout.LayoutParams.WRAP_CONTENT;
                param.width = GridLayout.LayoutParams.WRAP_CONTENT;
                param.setGravity(Gravity.CENTER_VERTICAL);
                param.columnSpec = GridLayout.spec(c);
                param.rowSpec = GridLayout.spec(r);
                view.setLayoutParams (param);

            }else if (NameList.size() + WeaponNameList.size() >0){
                View view = View.inflate(context, R.layout.item_cal_img, null);
                ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
                TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
                LinearLayout linearLayout5 = view.findViewById(R.id.linearLayout5);
                item_cal_img.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
                item_cal_img.getLayoutParams().width = size150;
                linearLayout5.getLayoutParams().height  = LinearLayout.LayoutParams.WRAP_CONTENT;
                linearLayout5.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
                Picasso.get()
                        .load (item_rss.getWeaponByName(WeaponNameList.get(x-NameList.size()))[1])
                        .transform(transformation)
                        .resize(size150,size150)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(item_rss.getWeaponByName(WeaponNameList.get(x-NameList.size()))[0]);

                if(WeaponIsCal.get(x-NameList.size()) == false){
                    Drawable drawable = context.getResources().getDrawable(R.drawable.barrier);
                    item_cal_img.setForeground(drawable);
                }
                gridLayout.addView(view);
                GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                param.height = GridLayout.LayoutParams.WRAP_CONTENT;
                param.width = GridLayout.LayoutParams.WRAP_CONTENT;
                param.setGravity(Gravity.CENTER_VERTICAL);
                param.columnSpec = GridLayout.spec(c);
                param.rowSpec = GridLayout.spec(r);
                view.setLayoutParams (param);
            }
            //Log.wtf("Procedure","resultShowCHAR&WEAPON"+" || "+x+" || "+System.currentTimeMillis());
        }

        /** CRYSTAL*/
        gridLayout = viewPager.findViewById(R.id.result_crystal_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; isZero(material.crystalCountList()) == false && x < material.crystalNameList().length; x++) {
            if(c == column) { c = 0;r++; }
            if(material.crystalCountList()[x] > 0){

                View view = View.inflate(context, R.layout.item_cal_img, null);
                ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
                LinearLayout linearLayout5 = view.findViewById(R.id.linearLayout5);
                TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
                item_cal_img.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
                item_cal_img.getLayoutParams().width = size;
                linearLayout5.getLayoutParams().height  = LinearLayout.LayoutParams.WRAP_CONTENT;
                linearLayout5.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
                        Picasso.get()
                        .load (item_rss.getItemIcoByName(material.crystalNameList()[x]))
                        .resize(size,size)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(prettyCount(material.crystalCountList()[x]));
                item_cal_img.setAdjustViewBounds(true);
                gridLayout.setVisibility(View.VISIBLE);
                result_crystal_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view);
                c++;
                GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                param.height = GridLayout.LayoutParams.WRAP_CONTENT;
                param.width = GridLayout.LayoutParams.WRAP_CONTENT;
                param.setGravity(Gravity.CENTER_VERTICAL);
                param.columnSpec = GridLayout.spec(c);
                param.rowSpec = GridLayout.spec(r);
                view.setLayoutParams (param);
                //Log.wtf("Procedure","resultShowCRYSTAL"+" || "+x+" || "+System.currentTimeMillis());
            }
        }

        /** BOSS */
        gridLayout = viewPager.findViewById(R.id.result_boss_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; isZero(material.bossCountList()) == false && x < material.bossNameList().length; x++) {
            if(c == column) { c = 0;r++; }
            //Log.wtf("Procedure","resultShowBOSS"+" || "+x+" || "+System.currentTimeMillis());
            if(material.bossCountList()[x] > 0){
                View view = View.inflate(context, R.layout.item_cal_img, null);
                ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
                LinearLayout linearLayout5 = view.findViewById(R.id.linearLayout5);
                TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
                item_cal_img.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
                item_cal_img.getLayoutParams().width = size;
                linearLayout5.getLayoutParams().height  = LinearLayout.LayoutParams.WRAP_CONTENT;
                linearLayout5.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
                Picasso.get()
                        .load (item_rss.getItemIcoByName(material.bossNameList()[x]))
                        .resize(size,size)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(prettyCount(material.bossCountList()[x]));
                item_cal_img.setAdjustViewBounds(true);
                gridLayout.setVisibility(View.VISIBLE);
                result_boss_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view);
                c++;
                GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                param.height = GridLayout.LayoutParams.WRAP_CONTENT;
                param.width = GridLayout.LayoutParams.WRAP_CONTENT;
                param.setGravity(Gravity.CENTER_VERTICAL);
                param.columnSpec = GridLayout.spec(c);
                param.rowSpec = GridLayout.spec(r);
                view.setLayoutParams (param);
            }
        }

        /** WEEK-BOSS */
        gridLayout = viewPager.findViewById(R.id.result_weekboss_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; isZero(material.weekBossCountList()) == false && x < material.weekBossNameList().length; x++) {
            if(c == column) { c = 0;r++; }
            if(material.weekBossCountList()[x] > 0){
                View view = View.inflate(context, R.layout.item_cal_img, null);
                ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
                LinearLayout linearLayout5 = view.findViewById(R.id.linearLayout5);
                TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
                item_cal_img.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
                item_cal_img.getLayoutParams().width = size;
                linearLayout5.getLayoutParams().height  = LinearLayout.LayoutParams.WRAP_CONTENT;
                linearLayout5.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
                Picasso.get()
                        .load (item_rss.getItemIcoByName(material.weekBossNameList()[x]))
                        .resize(size,size)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(prettyCount(material.weekBossCountList()[x]));
                item_cal_img.setAdjustViewBounds(true);
                gridLayout.setVisibility(View.VISIBLE);
                result_weekboss_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view);
                c++;
                //Log.wtf("Procedure","resultShowWEEK_BOSS"+" || "+x+" || "+System.currentTimeMillis());
                GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                param.height = GridLayout.LayoutParams.WRAP_CONTENT;
                param.width = GridLayout.LayoutParams.WRAP_CONTENT;
                param.setGravity(Gravity.CENTER_VERTICAL);
                param.columnSpec = GridLayout.spec(c);
                param.rowSpec = GridLayout.spec(r);
                view.setLayoutParams (param);
            }
        }

        /** LOCAL */
        gridLayout = viewPager.findViewById(R.id.result_local_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; isZero(material.localCountList()) == false && x < material.localNameList().length; x++) {
            if(c == column) { c = 0;r++; }
            if(material.localCountList()[x] > 0){
                View view = View.inflate(context, R.layout.item_cal_img, null);
                ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
                LinearLayout linearLayout5 = view.findViewById(R.id.linearLayout5);
                TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
                item_cal_img.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
                item_cal_img.getLayoutParams().width = size;
                linearLayout5.getLayoutParams().height  = LinearLayout.LayoutParams.WRAP_CONTENT;
                linearLayout5.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
                Picasso.get()
                        .load (item_rss.getItemIcoByName(material.localNameList()[x]))
                        .resize(size,size)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(prettyCount(material.localCountList()[x]));
                item_cal_img.setAdjustViewBounds(true);
                gridLayout.setVisibility(View.VISIBLE);
                result_local_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view);
                c++;
                GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                param.height = GridLayout.LayoutParams.WRAP_CONTENT;
                param.width = GridLayout.LayoutParams.WRAP_CONTENT;
                param.setGravity(Gravity.CENTER_VERTICAL);
                param.columnSpec = GridLayout.spec(c);
                param.rowSpec = GridLayout.spec(r);
                view.setLayoutParams (param);
                //Log.wtf("Procedure","resultShowLOCAL"+" || "+x+" || "+System.currentTimeMillis());
            }
        }

        /** COMMON */
        gridLayout = viewPager.findViewById(R.id.result_common_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; isZero(material.commonCountList()) == false && x < material.commonNameList().length; x++) {
            if(c == column) { c = 0;r++; }
            if(material.commonCountList()[x] > 0){
                View view = View.inflate(context, R.layout.item_cal_img, null);
                ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
                LinearLayout linearLayout5 = view.findViewById(R.id.linearLayout5);
                TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
                item_cal_img.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
                item_cal_img.getLayoutParams().width = size;
                linearLayout5.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
                linearLayout5.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
                Picasso.get()
                        .load (item_rss.getItemIcoByName(material.commonNameList()[x]))
                        .resize(size,size)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(prettyCount(material.commonCountList()[x]));
                item_cal_img.setAdjustViewBounds(true);
                gridLayout.setVisibility(View.VISIBLE);
                result_common_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view);
                c++;
                //Log.wtf("Procedure","resultShowCOMMON"+" || "+x+" || "+System.currentTimeMillis());
                GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                param.height = GridLayout.LayoutParams.WRAP_CONTENT;
                param.width = GridLayout.LayoutParams.WRAP_CONTENT;
                param.setGravity(Gravity.CENTER_VERTICAL);
                param.columnSpec = GridLayout.spec(c);
                param.rowSpec = GridLayout.spec(r);
                view.setLayoutParams (param);
            }
        }

        /** WEEKLY-MON-THUR-SUN */
        gridLayout = viewPager.findViewById(R.id.result_weeklybk1_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; isZero(material.weekly1CountList()) == false && x < material.weekly1NameList().length; x++) {
            if(c == column200) { c = 0;r++; }
            if(material.weekly1CountList()[x] > 0){
                View view = View.inflate(context, R.layout.item_cal_img, null);
                ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
                TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
                TextView item_weekly_tv = view.findViewById(R.id.item_weekly_tv);
                LinearLayout linearLayout5 = view.findViewById(R.id.linearLayout5);
                item_cal_img.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
                item_cal_img.getLayoutParams().width = size;
                linearLayout5.getLayoutParams().height  = LinearLayout.LayoutParams.WRAP_CONTENT;
                linearLayout5.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
                Picasso.get()
                        .load (item_rss.getItemIcoByName(material.weekly1NameList()[x]))
                        .resize(size,size)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(prettyCount(material.weekly1CountList()[x]));
                item_weekly_tv.setText(item_rss.getLocaleTeaches(material.weekly1NameList()[x],context));
                item_weekly_tv.setVisibility(View.VISIBLE);
                /*
                if(!item_rss.getLocaleTeaches(weeklybk1_temp[x],context).equals(context.getString(R.string.unknown))){
                    item_weekly_tv.setVisibility(View.VISIBLE);
                }
                 */
                item_cal_img.setAdjustViewBounds(true);
                gridLayout.setVisibility(View.VISIBLE);
                result_weeklybk1_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view);
                c++;
                GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                param.height = GridLayout.LayoutParams.WRAP_CONTENT;
                param.width = GridLayout.LayoutParams.WRAP_CONTENT;
                param.setGravity(Gravity.CENTER_VERTICAL);
                param.columnSpec = GridLayout.spec(c);
                param.rowSpec = GridLayout.spec(r);
                view.setLayoutParams (param);
                //Log.wtf("Procedure","resultShowWEEKLY1"+" || "+x+" || "+System.currentTimeMillis());
            }
        }

        /** WEEKLY-TUE-FRI-SUN */
        gridLayout = viewPager.findViewById(R.id.result_weeklybk2_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; isZero(material.weekly2CountList()) == false && x < material.weekly2NameList().length; x++) {
            if(c == column200) { c = 0;r++; }
            if(material.weekly2CountList()[x] > 0){
                View view = View.inflate(context, R.layout.item_cal_img, null);
                ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
                TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
                TextView item_weekly_tv = view.findViewById(R.id.item_weekly_tv);
                LinearLayout linearLayout5 = view.findViewById(R.id.linearLayout5);
                item_cal_img.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
                item_cal_img.getLayoutParams().width = size;
                linearLayout5.getLayoutParams().height  = LinearLayout.LayoutParams.WRAP_CONTENT;
                linearLayout5.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
                Picasso.get()
                        .load (item_rss.getItemIcoByName(material.weekly2NameList()[x]))
                        .resize(size,size)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(prettyCount(material.weekly2CountList()[x]));
                item_weekly_tv.setText(item_rss.getLocaleTeaches(material.weekly2NameList()[x],context));
                item_weekly_tv.setVisibility(View.VISIBLE);
                /*
                if(!item_rss.getLocaleTeaches(weeklybk2_temp[x],context).equals(context.getString(R.string.unknown))){
                    item_weekly_tv.setVisibility(View.VISIBLE);
                }
                 */
                item_cal_img.setAdjustViewBounds(true);
                gridLayout.setVisibility(View.VISIBLE);
                result_weeklybk2_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view);
                c++;
                GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                param.height = GridLayout.LayoutParams.WRAP_CONTENT;
                param.width = GridLayout.LayoutParams.WRAP_CONTENT;
                param.setGravity(Gravity.CENTER_VERTICAL);
                param.columnSpec = GridLayout.spec(c);
                param.rowSpec = GridLayout.spec(r);
                view.setLayoutParams (param);
                //Log.wtf("Procedure","resultShowWEEKLY2"+" || "+x+" || "+System.currentTimeMillis());
            }
        }
        //Log.wtf("Procedure","resultShowWEEKLY2"+" || "+System.currentTimeMillis());

        /** WEEKLY-WED-SAT-SUN */
        gridLayout = viewPager.findViewById(R.id.result_weeklybk3_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; isZero(material.weekly3CountList()) == false && x < material.weekly3NameList().length; x++) {
            if(c == column200) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            if(material.weekly3CountList()[x] > 0){
                ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
                TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
                TextView item_weekly_tv = view.findViewById(R.id.item_weekly_tv);
                LinearLayout linearLayout5 = view.findViewById(R.id.linearLayout5);
                item_cal_img.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
                item_cal_img.getLayoutParams().width = size;
                linearLayout5.getLayoutParams().height  = LinearLayout.LayoutParams.WRAP_CONTENT;
                linearLayout5.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
                Picasso.get()
                        .load (item_rss.getItemIcoByName(material.weekly3NameList()[x]))
                        .resize(size,size)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(prettyCount(material.weekly3CountList()[x]));
                item_weekly_tv.setText(item_rss.getLocaleTeaches(material.weekly3NameList()[x],context));
                item_weekly_tv.setVisibility(View.VISIBLE);
                /*
                if(!item_rss.getLocaleTeaches(weeklybk3_temp[x],context).equals(context.getString(R.string.unknown))){
                    item_weekly_tv.setVisibility(View.VISIBLE);
                }
                 */
                gridLayout.setVisibility(View.VISIBLE);
                result_weeklybk3_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view);
                c++;
                GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                param.height = GridLayout.LayoutParams.WRAP_CONTENT;
                param.width = GridLayout.LayoutParams.WRAP_CONTENT;
                param.setGravity(Gravity.CENTER_VERTICAL);
                param.columnSpec = GridLayout.spec(c);
                param.rowSpec = GridLayout.spec(r);
                view.setLayoutParams (param);
                //Log.wtf("Procedure","resultShowWEEKLY3"+" || "+x+" || "+System.currentTimeMillis());
            }
        }

        /** OTHERS*/
        String[] other_temp = new String[]{"流浪者的經驗", "冒險家的經驗", "大英雄的經驗", "精鍛用雜礦", "精鍛用良礦", "精鍛用魔礦","祝聖油膏","祝聖精華","摩拉", "智識之冕"};
        int[] other_temp_cnt = new int[]{exp_small,exp_mid,exp_big,weapon_small,weapon_mid,weapon_big,artifact_mid,artifact_big,morax,material.智識之冕};
        gridLayout = viewPager.findViewById(R.id.result_other_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; isZero(other_temp_cnt) == false && x < other_temp.length; x++) {
            if(c == column) { c = 0;r++; }
            if(other_temp_cnt[x] > 0){
                View view = View.inflate(context, R.layout.item_cal_img, null);
                ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
                TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
                LinearLayout linearLayout5 = view.findViewById(R.id.linearLayout5);
                item_cal_img.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
                item_cal_img.getLayoutParams().width = size;
                linearLayout5.getLayoutParams().height  = LinearLayout.LayoutParams.WRAP_CONTENT;
                linearLayout5.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
                Picasso.get()
                        .load (item_rss.getItemIcoByName(other_temp[x]))
                        .resize(size,size)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(prettyCount(other_temp_cnt[x]));
                item_cal_img.setAdjustViewBounds(true);
                gridLayout.setVisibility(View.VISIBLE);
                result_other_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view);
                c++;
                GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                param.height = GridLayout.LayoutParams.WRAP_CONTENT;
                param.width = GridLayout.LayoutParams.WRAP_CONTENT;
                param.setGravity(Gravity.CENTER_VERTICAL);
                param.columnSpec = GridLayout.spec(c);
                param.rowSpec = GridLayout.spec(r);
                view.setLayoutParams (param);
            }
        }
    }

    public void getCharEXPItemCount(int exp){

        int exp_big = (int) exp / 20000;
        exp = exp % 20000;
        int exp_mid = (int) exp / 5000;
        exp = exp % 5000;
        int exp_small = (int) exp / 1000;
        if (exp % 1000 < 1000 && exp != 0){
            exp_small = exp_small + 1;
        }

        this.exp_big = this.exp_big + exp_big;
        this.exp_mid = this.exp_mid + exp_mid;
        this.exp_small = this.exp_small + exp_small;

    }

    public void getWeaponEXPItemCount(int exp){

        int exp_big = (int) exp / 10000;
        exp = exp % 10000;
        int exp_mid = (int) exp / 2000;
        exp = exp % 2000;
        int exp_small = (int) exp / 400;
        if (exp % 400 < 400 && exp != 0){
            exp_small = exp_small + 1;
        }

        this.weapon_big = this.weapon_big + exp_big;
        this.weapon_mid = this.weapon_mid + exp_mid;
        this.weapon_small = this.weapon_small + exp_small;

    }

    public void getArtifactEXPItemCount(int exp){

        int exp_big = (int) exp / 10000;
        exp = exp % 10000;
        int exp_mid = (int) exp / 2500;
        if (exp % 2500 < 2500 && exp != 0){
            exp_mid = exp_mid + 1;
        }
        this.artifact_big = this.artifact_big + exp_big;
        this.artifact_mid = this.artifact_mid + exp_mid;
    }


    public void char_readJSON () {
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

        //Log.wtf("Procedure","char_readJSON_2"+" || "+System.currentTimeMillis());
        char_calculate();
    }

    public void weapon_readJSON() {
        String weapon_rare1_exp = LoadData("db/weapons/weapon_rare1_exp.json");
        String weapon_rare2_exp = LoadData("db/weapons/weapon_rare2_exp.json");
        String weapon_rare3_exp = LoadData("db/weapons/weapon_rare3_exp.json");
        String weapon_rare4_exp = LoadData("db/weapons/weapon_rare4_exp.json");
        String weapon_rare5_exp = LoadData("db/weapons/weapon_rare5_exp.json");

        String weapon_rare1_asc = LoadData("db/weapons/weapon_rare1_asc_lvl.json");
        String weapon_rare2_asc = LoadData("db/weapons/weapon_rare2_asc_lvl.json");
        String weapon_rare3_asc = LoadData("db/weapons/weapon_rare3_asc_lvl.json");
        String weapon_rare4_asc = LoadData("db/weapons/weapon_rare4_asc_lvl.json");
        String weapon_rare5_asc = LoadData("db/weapons/weapon_rare5_asc_lvl.json");

        String weapon_require_asc = LoadData("db/weapons/weapon_require_asc.json");

        //Log.wtf("Procedure","weapon_readJSON_1"+" || "+System.currentTimeMillis());
        /** EXP */
        try {
            JSONArray array = new JSONArray(weapon_rare1_exp);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int lvl = object.getInt("lvl");
                int exp = object.getInt("exp");
                int mora = object.getInt("mora");
                WeaponRare1LvlList.add(lvl);
                WeaponRare1ExpList.add(exp);
                WeaponRare1MoraList.add(mora);
            }

            array = new JSONArray(weapon_rare2_exp);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int lvl = object.getInt("lvl");
                int exp = object.getInt("exp");
                int mora = object.getInt("mora");
                WeaponRare2LvlList.add(lvl);
                WeaponRare2ExpList.add(exp);
                WeaponRare2MoraList.add(mora);
            }

            array = new JSONArray(weapon_rare3_exp);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int lvl = object.getInt("lvl");
                int exp = object.getInt("exp");
                int mora = object.getInt("mora");
                WeaponRare3LvlList.add(lvl);
                WeaponRare3ExpList.add(exp);
                WeaponRare3MoraList.add(mora);
            }

            array = new JSONArray(weapon_rare4_exp);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int lvl = object.getInt("lvl");
                int exp = object.getInt("exp");
                int mora = object.getInt("mora");
                WeaponRare4LvlList.add(lvl);
                WeaponRare4ExpList.add(exp);
                WeaponRare4MoraList.add(mora);
            }

            array = new JSONArray(weapon_rare5_exp);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int lvl = object.getInt("lvl");
                int exp = object.getInt("exp");
                int mora = object.getInt("mora");
                WeaponRare5LvlList.add(lvl);
                WeaponRare5ExpList.add(exp);
                WeaponRare5MoraList.add(mora);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /** ASC */
        try {
            JSONArray array = new JSONArray(weapon_rare1_asc);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int lvl = object.getInt("lvl");
                int copy1 = object.getInt("copy1");
                int copy2 = object.getInt("copy2");
                int common = object.getInt("common");
                int mora = object.getInt("mora");
                WeaponRare1LvlASCList.add(lvl);
                WeaponRare1Copy1ASCList.add(copy1);
                WeaponRare1Copy2ASCList.add(copy2);
                WeaponRare1CommonASCList.add(common);
                WeaponRare1MoraASCList.add(mora);
            }

            array = new JSONArray(weapon_rare2_asc);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int lvl = object.getInt("lvl");
                int copy1 = object.getInt("copy1");
                int copy2 = object.getInt("copy2");
                int common = object.getInt("common");
                int mora = object.getInt("mora");
                WeaponRare2LvlASCList.add(lvl);
                WeaponRare2Copy1ASCList.add(copy1);
                WeaponRare2Copy2ASCList.add(copy2);
                WeaponRare2CommonASCList.add(common);
                WeaponRare2MoraASCList.add(mora);
            }

            array = new JSONArray(weapon_rare3_asc);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int lvl = object.getInt("lvl");
                int copy1 = object.getInt("copy1");
                int copy2 = object.getInt("copy2");
                int common = object.getInt("common");
                int mora = object.getInt("mora");
                WeaponRare3LvlASCList.add(lvl);
                WeaponRare3Copy1ASCList.add(copy1);
                WeaponRare3Copy2ASCList.add(copy2);
                WeaponRare3CommonASCList.add(common);
                WeaponRare3MoraASCList.add(mora);
            }

            array = new JSONArray(weapon_rare4_asc);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int lvl = object.getInt("lvl");
                int copy1 = object.getInt("copy1");
                int copy2 = object.getInt("copy2");
                int common = object.getInt("common");
                int mora = object.getInt("mora");
                WeaponRare4LvlASCList.add(lvl);
                WeaponRare4Copy1ASCList.add(copy1);
                WeaponRare4Copy2ASCList.add(copy2);
                WeaponRare4CommonASCList.add(common);
                WeaponRare4MoraASCList.add(mora);
            }

            array = new JSONArray(weapon_rare5_asc);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int lvl = object.getInt("lvl");
                int copy1 = object.getInt("copy1");
                int copy2 = object.getInt("copy2");
                int common = object.getInt("common");
                int mora = object.getInt("mora");
                WeaponRare5LvlASCList.add(lvl);
                WeaponRare5Copy1ASCList.add(copy1);
                WeaponRare5Copy2ASCList.add(copy2);
                WeaponRare5CommonASCList.add(common);
                WeaponRare5MoraASCList.add(mora);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // REQUIRE
        try {
            JSONArray array = new JSONArray(weapon_require_asc);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String name = object.getString("name");
                String type = object.getString("type");
                int rare = object.getInt("rare");
                String copy1 = object.getString("copy1");
                String copy2 = object.getString("copy2");
                String common = object.getString("common");
                WeaponNameREQUIREList.add(name);
                WeaponTypeREQUIREList.add(name);
                WeaponRareREQUIREList.add(rare);
                WeaponCopy1REQUIREList.add(copy1);
                WeaponCopy2REQUIREList.add(copy2);
                WeaponCommonREQUIREList.add(common);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Log.wtf("Procedure","weapon_readJSON_2"+" || "+System.currentTimeMillis());
        weapon_calculate();
        /**
         if(!WeaponRareList.isEmpty()){
         weapon_calculate();
         }
         */
    }

    public void artifact_readJSON() {
        String artifact_rare1_exp = LoadData("db/artifacts/artifact_rare1_exp.json");
        String artifact_rare2_exp = LoadData("db/artifacts/artifact_rare2_exp.json");
        String artifact_rare3_exp = LoadData("db/artifacts/artifact_rare3_exp.json");
        String artifact_rare4_exp = LoadData("db/artifacts/artifact_rare4_exp.json");
        String artifact_rare5_exp = LoadData("db/artifacts/artifact_rare5_exp.json");

        //Log.wtf("Procedure","artifact_readJSON_1"+" || "+System.currentTimeMillis());
        /** EXP */
        try {
            JSONArray array = new JSONArray(artifact_rare1_exp);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int lvl = object.getInt("lvl");
                int exp = object.getInt("exp");
                int mora = object.getInt("mora");
                ArtifactRare1LvlList.add(lvl);
                ArtifactRare1ExpList.add(exp);
                ArtifactRare1MoraList.add(mora);
            }

            array = new JSONArray(artifact_rare2_exp);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int lvl = object.getInt("lvl");
                int exp = object.getInt("exp");
                int mora = object.getInt("mora");
                ArtifactRare2LvlList.add(lvl);
                ArtifactRare2ExpList.add(exp);
                ArtifactRare2MoraList.add(mora);
            }

            array = new JSONArray(artifact_rare3_exp);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int lvl = object.getInt("lvl");
                int exp = object.getInt("exp");
                int mora = object.getInt("mora");
                ArtifactRare3LvlList.add(lvl);
                ArtifactRare3ExpList.add(exp);
                ArtifactRare3MoraList.add(mora);
            }

            array = new JSONArray(artifact_rare4_exp);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int lvl = object.getInt("lvl");
                int exp = object.getInt("exp");
                int mora = object.getInt("mora");
                ArtifactRare4LvlList.add(lvl);
                ArtifactRare4ExpList.add(exp);
                ArtifactRare4MoraList.add(mora);
            }

            array = new JSONArray(artifact_rare5_exp);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int lvl = object.getInt("lvl");
                int exp = object.getInt("exp");
                int mora = object.getInt("mora");
                ArtifactRare5LvlList.add(lvl);
                ArtifactRare5ExpList.add(exp);
                ArtifactRare5MoraList.add(mora);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Log.wtf("Procedure","artifact_readJSON_2"+" || "+System.currentTimeMillis());
        artifact_calculate();

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

    public String prettyCount(Number number) {
        char[] suffix = {' ', 'K', 'M', 'G', 'T', 'P', 'E', 'Z', 'Y'};
        long numValue = number.longValue();
        int value = (int) Math.floor(Math.log10(numValue));
        int base = value / 3;
        String plus = "";
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", MODE_PRIVATE);
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

    public boolean isZero (int[] list){
        boolean reallyZero = true;
        for (int x = 0 ; x < list.length ; x++){
            if(list[x] > 0){
                reallyZero = false;
            }
        }
        return reallyZero;
    }



    public void saveToDB() {
        //Log.wtf("DB","saveToDB !");
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db ;

        /**
         * Database Char Part
         */

        //Log.wtf("DB",String.valueOf(NameList.size()));
        //Log.wtf("DB",String.valueOf(WeaponNameList.size()));
        for (int x = 0 ; x < NameList.size() ; x ++){
            db = dbHelper.getReadableDatabase();
            String[] projection = {"charName"};
            String selection = "charName" + " = ?";
            String[] selectionArgs = { NameList.get(x) };
            Cursor cursor = db.query(
                    dataSheetName+"_char",   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );
            // DEMO -> UPDATE demo SET ID = 1,Name = "SPP",Hint = "OK" WHERE Name = "Twitter";
            if(cursor.getCount()>0){
                db.execSQL("UPDATE "+dataSheetName+"_char"+" SET "+
                        "charBeforeLvl = "+String.valueOf(BeforeLvlList.get(x))+","+
                        "charAfterLvl = "+String.valueOf(AfterLvlList.get(x))+","+

                        "charBeforeBreakLvl = "+String.valueOf(BeforeBreakLvlList.get(x))+","+
                        "charAfterBreakLvl = "+String.valueOf(AfterBreakLvlList.get(x))+","+

                        "charBeforeBreakUpLvl = "+String.valueOf((BeforeBreakUPLvlList.get(x)) ? 1 : 0 )+","+
                        "charAfterBreakUpLvl = "+String.valueOf((AfterBreakUPLvlList.get(x)) ? 1 : 0 )+","+

                        "charBeforeSkill1Lvl = "+String.valueOf(BeforeSkill1LvlList.get(x))+","+
                        "charAfterSkill1Lvl = "+String.valueOf(AfterSkill1LvlList.get(x))+","+

                        "charBeforeSkill2Lvl = "+String.valueOf(BeforeSkill2LvlList.get(x))+","+
                        "charAfterSkill2Lvl = "+String.valueOf(AfterSkill2LvlList.get(x))+","+

                        "charBeforeSkill3Lvl = "+String.valueOf(BeforeSkill3LvlList.get(x))+","+
                        "charAfterSkill3Lvl = "+String.valueOf(AfterSkill3LvlList.get(x))+","+

                        "charIsCal = "+String.valueOf((IsCal.get(x)) ? 1 : 0 )+

                        " WHERE charName = \""+NameList.get(x)+"\";");
            }else {
                // DEMO -> INSERT INTO demo (ID,Name) VALUES (-3,"SSS");
                db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("charName", NameList.get(x));
                values.put("charBeforeLvl", BeforeLvlList.get(x));
                values.put("charAfterLvl", AfterLvlList.get(x));
                values.put("charBeforeBreakLvl", BeforeBreakLvlList.get(x));
                values.put("charAfterBreakLvl", AfterBreakLvlList.get(x));
                values.put("charBeforeBreakUpLvl", String.valueOf((BeforeBreakUPLvlList.get(x)) ? 1 : 0 ));
                values.put("charAfterBreakUpLvl", String.valueOf((AfterBreakUPLvlList.get(x)) ? 1 : 0 ));
                values.put("charBeforeSkill1Lvl", BeforeSkill1LvlList.get(x));
                values.put("charAfterSkill1Lvl", AfterSkill1LvlList.get(x));
                values.put("charBeforeSkill2Lvl", BeforeSkill2LvlList.get(x));
                values.put("charAfterSkill2Lvl", AfterSkill2LvlList.get(x));
                values.put("charBeforeSkill3Lvl", BeforeSkill3LvlList.get(x));
                values.put("charAfterSkill3Lvl", AfterSkill3LvlList.get(x));
                values.put("charIsCal", String.valueOf((IsCal.get(x)) ? 1 : 0 ));

                db.insert(dataSheetName+"_char", null, values);

            }
            cursor.close();
        }

        for (int x = 0 ; x < WeaponNameList.size() ; x ++){
            db = dbHelper.getReadableDatabase();
            String[] projection = {"weaponName"};
            String selection = "weaponName" + " = ?";
            String[] selectionArgs = { WeaponNameList.get(x) };
            Cursor cursor = db.query(
                    dataSheetName+"_weapon",   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );
            // DEMO -> UPDATE demo SET ID = 1,Name = "SPP",Hint = "OK" WHERE Name = "Twitter";
            if(cursor.getCount()>0){
                db.execSQL("UPDATE "+dataSheetName+"_weapon"+" SET "+
                        "weaponBeforeLvl = "+String.valueOf(WeaponBeforeLvlList.get(x))+","+
                        "weaponAfterLvl = "+String.valueOf(WeaponAfterLvlList.get(x))+","+

                        "weaponBeforeBreakLvl = "+String.valueOf(WeaponBeforeBreakLvlList.get(x))+","+
                        "weaponAfterBreakLvl = "+String.valueOf(WeaponAfterBreakLvlList.get(x))+","+

                        "weaponBeforeBreakUpLvl = "+String.valueOf((WeaponBeforeBreakUPLvlList.get(x)) ? 1 : 0 )+","+
                        "weaponAfterBreakUpLvl = "+String.valueOf((WeaponAfterBreakUPLvlList.get(x)) ? 1 : 0 )+","+

                        "weaponFollow = \""+WeaponFollowList.get(x)+"\","+
                        "weaponRare = "+String.valueOf(WeaponRareList.get(x))+","+
                        "weaponIsCal = "+String.valueOf((WeaponIsCal.get(x)) ? 1 : 0 )+

                        " WHERE weaponName = \""+WeaponNameList.get(x)+"\";");
            }else {
                // DEMO -> INSERT INTO demo (ID,Name) VALUES (-3,"SSS");
                db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("weaponName", WeaponNameList.get(x));
                values.put("weaponBeforeLvl", WeaponBeforeLvlList.get(x));
                values.put("weaponAfterLvl", WeaponAfterLvlList.get(x));
                values.put("weaponBeforeBreakLvl", WeaponBeforeBreakLvlList.get(x));
                values.put("weaponAfterBreakLvl", WeaponAfterBreakLvlList.get(x));
                values.put("weaponBeforeBreakUpLvl", String.valueOf((WeaponBeforeBreakUPLvlList.get(x)) ? 1 : 0 ));
                values.put("weaponAfterBreakUpLvl", String.valueOf((WeaponAfterBreakUPLvlList.get(x)) ? 1 : 0 ));
                values.put("weaponRare", WeaponRareList.get(x));
                values.put("weaponFollow", WeaponFollowList.get(x));
                values.put("weaponIsCal", String.valueOf((WeaponIsCal.get(x)) ? 1 : 0 ));

                db.insert(dataSheetName+"_weapon", null, values);

            }
            cursor.close();
        }

        for (int x = 0 ; x < ArtifactNameList.size() ; x ++){
            db = dbHelper.getReadableDatabase();
            String[] projection = {"artifactName","artifactType","artifactFollow"};
            String selection = "artifactName" + " = ?";
            String[] selectionArgs = { ArtifactNameList.get(x) };
            Cursor cursor = db.query(
                    dataSheetName+"_artifact",   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );
            // DEMO -> UPDATE demo SET ID = 1,Name = "SPP",Hint = "OK" WHERE Name = "Twitter";

            ArrayList<String> tmp_art_name = new ArrayList<String>();
            ArrayList<String> tmp_art_type = new ArrayList<String>();
            ArrayList<String> tmp_art_follow = new ArrayList<String>();
            while(cursor.moveToNext()) {
                tmp_art_name.add(cursor.getString(cursor.getColumnIndexOrThrow("artifactName")));
                tmp_art_type.add(cursor.getString(cursor.getColumnIndexOrThrow("artifactType")));
                tmp_art_follow.add(cursor.getString(cursor.getColumnIndexOrThrow("artifactFollow")));
            }

            if(cursor.getCount()>0 && tmp_art_name.contains(ArtifactNameList.get(x)) && tmp_art_type.contains(artifactType.get(x)) && tmp_art_follow.contains(ArtifactFollowList.get(x))){
                db.execSQL("UPDATE "+dataSheetName+"_artifact"+" SET "+
                        "artifactBeforeLvl = "+String.valueOf(ArtifactBeforeLvlList.get(x))+","+
                        "artifactAfterLvl = "+String.valueOf(ArtifactAfterLvlList.get(x))+","+

                        "artifactFollow = \""+ArtifactFollowList.get(x)+"\","+
                        "artifactRare = "+String.valueOf(ArtifactRareList.get(x))+","+
                        "artifactIsCal = "+String.valueOf((ArtifactIsCal.get(x)) ? 1 : 0 )+

                        " WHERE artifactName = \""+ArtifactNameList.get(x)+"\" AND artifactFollow = \""+ArtifactFollowList.get(x)+"\" AND artifactType = \""+artifactType.get(x)+"\";");
            }else {
                // DEMO -> INSERT INTO demo (ID,Name) VALUES (-3,"SSS");
                db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("artifactName", ArtifactNameList.get(x));
                values.put("artifactBeforeLvl", ArtifactBeforeLvlList.get(x));
                values.put("artifactAfterLvl", ArtifactAfterLvlList.get(x));
                values.put("artifactRare", ArtifactRareList.get(x));
                values.put("artifactFollow", ArtifactFollowList.get(x));
                values.put("artifactType", artifactType.get(x));
                values.put("artifactIsCal", String.valueOf((ArtifactIsCal.get(x)) ? 1 : 0 ));

                db.insert(dataSheetName+"_artifact", null, values);

                /*
                db.execSQL("INSERT INTO "+dataSheetName+"_artifact"+
                        " (artifactName,artifactBeforeLvl,artifactAfterLvl,artifactRare,artifactFollow,artifactIsCal)"+

                        " VALUES (\""+ArtifactNameList.get(x)+"\","+ArtifactBeforeLvlList.get(x)+","+ArtifactAfterLvlList.get(x)+","+ArtifactRareList.get(x)+",\""+ArtifactFollowList.get(x)+"\","+ArtifactIsCal.get(x)+");");

                 */
            }
            cursor.close();
        }


        /**
         * IndexDB part
         */

        db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE IndexDB SET "+
                "char_cnt = "+String.valueOf(NameList.size())+","+
                "weapon_cnt = "+String.valueOf(WeaponNameList.size())+","+
                "artifact_cnt = "+String.valueOf(ArtifactNameList.size())+
                " WHERE name = "+"\""+dataSheetName+"\";");
    }

    public void setDBName(String dataSheetName) {
        this.dataSheetName = dataSheetName;
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
}
