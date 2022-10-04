package com.voc.genshin_helper.ui.SipTik;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;

import com.squareup.picasso.Picasso;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.Material;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.FileLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */
public class CalculatorExtendSipTik {

    /** [TRANSFER] CHARACTER VAR FROM CalculatorUI*/
    int id = 0;
    String type = "CHAR";
    String name = "N/A";
    int beforeLvl = 1;
    int afterLvl = 70;
    int beforeBreakLvl = 0;
    int afterBreakLvl = 6;
    boolean beforeBreakUpLvl = false;
    boolean afterBreakUpLvl = false;
    int beforeSkill1Lvl = 1;
    int afterSkill1Lvl = 10;
    int beforeSkill2Lvl = 1;
    int afterSkill2Lvl = 10;
    int beforeSkill3Lvl = 1;
    int afterSkill3Lvl = 10;
    String follow = "N/A";
    int rare = 1;
    boolean isCal = true;

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
    View viewPager;
    ItemRss item_rss;

    /** [VAR] CHARACTER LVL EXP + MORA */
    int part0_exp = 0;
    int part1_exp = 0;
    int part2_exp = 0;
    int part3_exp = 0;
    int part4_exp = 0;
    int part5_exp = 0;
    int part6_exp = 0;
    int exp_small = 0;
    int exp_mid = 0;
    int exp_big = 0;
    int morax = 0;

    int rowNum = 1;
    int colNum = 1;
    LinearLayout tmp_ll;

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
    int weapon_part0_exp = 0;
    int weapon_part1_exp = 0;
    int weapon_part2_exp = 0;
    int weapon_part3_exp = 0;
    int weapon_part4_exp = 0;
    int weapon_part5_exp = 0;
    int weapon_part6_exp = 0;
    int weapon_small = 0;
    int weapon_mid = 0;
    int weapon_big = 0;

    /** TEMP */
    int artifact_mid = 0;
    int artifact_big = 0;

    boolean isNight = false;

    private boolean isReadPermissionGranted = false;
    private boolean isWritePermissionGranted = false;
    private static final int CAMERA = 100;

    Material material;
    boolean isInPreview = false;

    public void setPreview(boolean isInPreview){
        this.isInPreview = isInPreview;
    }

    public void setup(Context context, Activity activity, View viewPager) {
        this.context = context;
        this.activity = activity;
        this.viewPager = viewPager;
        material = new Material();
        // Background of item
        if (context.getResources().getString(R.string.mode).equals("Night")) {
            isNight = true;
        }

        item_rss = new ItemRss();

        if (!isInPreview){
            ImageView result_camera = viewPager.findViewById(R.id.result_camera);
            result_camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ScrollView scrollview = viewPager.findViewById(R.id.siptik_sc);

                    Bitmap bitmap = getBitmapFromView(scrollview, scrollview.getChildAt(0).getHeight(), scrollview.getChildAt(0).getWidth());
                    requestPermission();
                    if (isWritePermissionGranted){
                        if (saveImageToExternalStorage("SipTikCal"+"_"+prettyTime(),bitmap)){
                            CustomToast.toast(context,activity,context.getString(R.string.screenshot_saved));
                        }
                    }else {
                        CustomToast.toast(context,activity,context.getString(R.string.screenshot_not_saved_permission));
                    }
                }
            });
        }

        char_readJSON();
        weapon_readJSON();
    }

    public void cal_setup(
            int id_T, String type_T, String name_T,
            int beforeLvl_T, int afterLvl_T,
            int beforeBreakLvl_T, int afterBreakLvl_T,
            boolean beforeBreakUpLvl_T, boolean afterBreakUpLvl_T,
            int beforeSkill1Lvl_T, int afterSkill1Lvl_T,
            int beforeSkill2Lvl_T, int afterSkill2Lvl_T,
            int beforeSkill3Lvl_T, int afterSkill3Lvl_T,
            String follow_T, int rare_T, boolean isCal_T) {

        this.id = id_T;
        this.type = type_T;
        this.name = name_T;
        this.beforeLvl = beforeLvl_T;
        this.afterLvl = afterLvl_T;
        this.beforeBreakLvl = beforeBreakLvl_T;
        this.afterBreakLvl = afterBreakLvl_T;
        this.beforeBreakUpLvl = beforeBreakUpLvl_T;
        this.afterBreakUpLvl = afterBreakUpLvl_T;
        this.beforeSkill1Lvl = beforeSkill1Lvl_T;
        this.afterSkill1Lvl = afterSkill1Lvl_T;
        this.beforeSkill2Lvl = beforeSkill2Lvl_T;
        this.afterSkill2Lvl = afterSkill2Lvl_T;
        this.beforeSkill3Lvl = beforeSkill3Lvl_T;
        this.afterSkill3Lvl = afterSkill3Lvl_T;
        this.follow = follow_T;
        this.rare = rare_T;
        this.isCal = isCal_T;

        part0_exp	=	0;
        part1_exp	=	0;
        part2_exp	=	0;
        part3_exp	=	0;
        part4_exp	=	0;
        part5_exp	=	0;
        part6_exp	=	0;
        exp_small	=	0;
        exp_mid	=	0;
        exp_big	=	0;
        morax	=	0;

        asc_temp_item	=	new	ArrayList<String>();
        asc_temp_count	=	new	ArrayList<Integer>(Collections.nCopies(10,	0));
        skill1_temp_item	=	new	ArrayList<String>();
        skill1_temp_count	=	new	ArrayList<Integer>(Collections.nCopies(7,	0));
        skill2_temp_item	=	new	ArrayList<String>();
        skill2_temp_count	=	new	ArrayList<Integer>(Collections.nCopies(7,	0));
        skill3_temp_item	=	new	ArrayList<String>();
        skill3_temp_count	=	new	ArrayList<Integer>(Collections.nCopies(7,	0));

        material = new Material();

        calculate();
    }

    public void calculate () {
        //Log.wtf("HEY","TOMMY");

        if (type.equals("CHAR")){
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

            asc_temp_count = new ArrayList<Integer>(Collections.nCopies(10, 0));
            skill1_temp_count = new ArrayList<Integer>(Collections.nCopies(7, 0));
            skill2_temp_count = new ArrayList<Integer>(Collections.nCopies(7, 0));
            skill3_temp_count = new ArrayList<Integer>(Collections.nCopies(7, 0));

            for (int y = beforeLvl ; y < afterLvl ; y++){
                if(y>80 && y <= 90){exp_grade6 = exp_grade6 + expEXPList.get(y);}
                else if(y>70 && y <= 80){exp_grade5 = exp_grade5 + expEXPList.get(y);}
                else if(y>60 && y <= 70){exp_grade4 = exp_grade4 + expEXPList.get(y);}
                else if(y>50 && y <= 60){exp_grade3 = exp_grade3 + expEXPList.get(y);}
                else if(y>40 && y <= 50){exp_grade2 = exp_grade2 + expEXPList.get(y);}
                else if(y>20 && y <= 40){exp_grade1 = exp_grade1 + expEXPList.get(y);}
                else if(y<=20){exp_grade0 = exp_grade0 + expEXPList.get(y);}
                morax = morax + moraEXPList.get(y);
            }

            part0_exp = exp_grade0;
            System.out.println("exp_grade0 : "+exp_grade0);
            part1_exp = exp_grade1 ;
            part2_exp = exp_grade2 ;
            part3_exp = exp_grade3 ;
            part4_exp = exp_grade4 ;
            part5_exp = exp_grade5 ;
            part6_exp = exp_grade6 ;

            getCharEXPItemCount(exp_grade0);
            getCharEXPItemCount(exp_grade1);
            getCharEXPItemCount(exp_grade2);
            getCharEXPItemCount(exp_grade3);
            getCharEXPItemCount(exp_grade4);
            getCharEXPItemCount(exp_grade5);
            getCharEXPItemCount(exp_grade6);

            /** CAL ASC */
            int beforeUP = 0 , afterUP = 0;
            if(beforeBreakUpLvl == true){beforeUP = 1;}
            if(afterBreakUpLvl == true){afterUP = 1;}

            int z = getCharRealPosByName(name);
            asc_temp_item.add(nameREQUIREList.get(z));
            asc_temp_item.add(crystalREQUIREList.get(z));
            asc_temp_item.add(bossREQUIREList.get(z));
            asc_temp_item.add(localREQUIREList.get(z));
            asc_temp_item.add(commonREQUIREList.get(z));

            for (int y = beforeBreakLvl+beforeUP ; y < afterBreakLvl+afterUP ; y ++){

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

            z = getCharRealPosByName(name);
            skill1_temp_item.add(nameREQUIREList.get(z));
            skill1_temp_item.add(bookREQUIREList.get(z));
            skill1_temp_item.add(commonREQUIREList.get(z));
            skill1_temp_item.add(t_bossREQUIREList.get(z));

            for (int y = beforeSkill1Lvl ; y < afterSkill1Lvl ; y ++){

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

            z = getCharRealPosByName(name);
            skill2_temp_item.add(nameREQUIREList.get(z));
            skill2_temp_item.add(bookREQUIREList.get(z));
            skill2_temp_item.add(commonREQUIREList.get(z));
            skill2_temp_item.add(t_bossREQUIREList.get(z));

            for (int y = beforeSkill2Lvl ; y < afterSkill2Lvl ; y ++){

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

            z = getCharRealPosByName(name);
            skill3_temp_item.add(nameREQUIREList.get(z));
            skill3_temp_item.add(bookREQUIREList.get(z));
            skill3_temp_item.add(commonREQUIREList.get(z));
            skill3_temp_item.add(t_bossREQUIREList.get(z));

            for (int y = beforeSkill3Lvl ; y < afterSkill3Lvl ; y ++){

                skill3_temp_count.set(0,skill3_temp_count.get(0) + teachSKILLList.get(y));
                skill3_temp_count.set(1,skill3_temp_count.get(1) + guideSKILLList.get(y));
                skill3_temp_count.set(2,skill3_temp_count.get(2) + phiSKILLList.get(y));
                skill3_temp_count.set(3,skill3_temp_count.get(3) + com1SKILLList.get(y));
                skill3_temp_count.set(4,skill3_temp_count.get(4) + com2SKILLList.get(y));
                skill3_temp_count.set(5,skill3_temp_count.get(5) + com3SKILLList.get(y));
                skill3_temp_count.set(6,skill3_temp_count.get(6) + bossSKILLList.get(y));

                if(y == 9) {material.智識之冕 = material.智識之冕 + 1;}
                // mora.add(moraSkillList.get(y));
                morax = morax + moraSkillList.get(y);
            }
            material.FindItemByName(skill3_temp_item,skill3_temp_count);

        }else if(type.equals("WEAPON")){
            /** CAL EXP */
            int exp_grade0 = 0,exp_grade1= 0,exp_grade2= 0,exp_grade3= 0,exp_grade4= 0,exp_grade5= 0,exp_grade6 = 0;
            weapon_asc_temp_item.clear();
            weapon_asc_temp_count.clear();
            weapon_asc_temp_count = new ArrayList<Integer>(Collections.nCopies(10, 0));

            int rare_tmp = rare;

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

            int z = getWeaponRealPosByName(name);
            weapon_asc_temp_item.add(WeaponNameREQUIREList.get(z));
            weapon_asc_temp_item.add(WeaponCopy1REQUIREList.get(z));
            weapon_asc_temp_item.add(WeaponCopy2REQUIREList.get(z));
            weapon_asc_temp_item.add(WeaponCommonREQUIREList.get(z));

            for (int y = beforeBreakLvl ; y < afterBreakLvl ; y++){

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

            weapon_part0_exp = exp_grade0;
            weapon_part1_exp = exp_grade1;
            weapon_part2_exp = exp_grade2;
            weapon_part3_exp = exp_grade3;
            weapon_part4_exp = exp_grade4;
            weapon_part5_exp = exp_grade5;
            weapon_part6_exp = exp_grade6;

            getWeaponEXPItemCount(exp_grade0);
            getWeaponEXPItemCount(exp_grade1);
            getWeaponEXPItemCount(exp_grade2);
            getWeaponEXPItemCount(exp_grade3);
            getWeaponEXPItemCount(exp_grade4);
            getWeaponEXPItemCount(exp_grade5);
            getWeaponEXPItemCount(exp_grade6);

            /** CAL ASC */
            int beforeUP = 0 , afterUP = 0;
            if(beforeBreakUpLvl == true){beforeUP = 1;}
            if(afterBreakUpLvl == true){afterUP = 1;}

            for (int y = beforeBreakLvl+beforeUP ; y < afterBreakLvl+afterUP ; y ++){
                morax = morax + WeaponMoraASCList.get(y);
            }


            if (rare == 1 || rare == 2) {
                if ((this.afterBreakLvl + afterUP) >= 1 && (beforeBreakLvl+beforeUP) <=1) {
                    weapon_asc_temp_count.set(0,weapon_asc_temp_count.get(0) + WeaponCopy1List.get(0));
                    weapon_asc_temp_count.set(4,weapon_asc_temp_count.get(4) + WeaponCopy2List.get(0));
                    weapon_asc_temp_count.set(7,weapon_asc_temp_count.get(7) + WeaponCommonList.get(0));
                }
                if ((this.afterBreakLvl + afterUP) >= 2 && (beforeBreakLvl+beforeUP) <=2) {
                    weapon_asc_temp_count.set(1,weapon_asc_temp_count.get(1) + WeaponCopy1List.get(1));
                    weapon_asc_temp_count.set(4,weapon_asc_temp_count.get(4) + WeaponCopy2List.get(1));
                    weapon_asc_temp_count.set(7,weapon_asc_temp_count.get(7) + WeaponCommonList.get(1));
                }
                if ((this.afterBreakLvl + afterUP) >= 3 && (beforeBreakLvl+beforeUP) <=3) {
                    weapon_asc_temp_count.set(1,weapon_asc_temp_count.get(1) + WeaponCopy1List.get(2));
                    weapon_asc_temp_count.set(5,weapon_asc_temp_count.get(5) + WeaponCopy2List.get(2));
                    weapon_asc_temp_count.set(8,weapon_asc_temp_count.get(8) + WeaponCommonList.get(2));
                }
                if ((this.afterBreakLvl + afterUP) >= 4 && (beforeBreakLvl+beforeUP) <=4) {
                    weapon_asc_temp_count.set(2,weapon_asc_temp_count.get(2) + WeaponCopy1List.get(3));
                    weapon_asc_temp_count.set(5,weapon_asc_temp_count.get(5) + WeaponCopy2List.get(3));
                    weapon_asc_temp_count.set(8,weapon_asc_temp_count.get(8) + WeaponCommonList.get(3));
                }
            }else if (rare == 3 || rare == 4 || rare == 5) {
                if ((this.afterBreakLvl + afterUP) >= 1 && (beforeBreakLvl+beforeUP) <=1) {
                    weapon_asc_temp_count.set(0,weapon_asc_temp_count.get(0) + WeaponCopy1List.get(0));
                    weapon_asc_temp_count.set(4,weapon_asc_temp_count.get(4) + WeaponCopy2List.get(0));
                    weapon_asc_temp_count.set(7,weapon_asc_temp_count.get(7) + WeaponCommonList.get(0));
                }
                if ((this.afterBreakLvl + afterUP) >= 2 && (beforeBreakLvl+beforeUP) <=2) {
                    weapon_asc_temp_count.set(1,weapon_asc_temp_count.get(1) + WeaponCopy1List.get(1));
                    weapon_asc_temp_count.set(4,weapon_asc_temp_count.get(4) + WeaponCopy2List.get(1));
                    weapon_asc_temp_count.set(7,weapon_asc_temp_count.get(7) + WeaponCommonList.get(1));
                }
                if ((this.afterBreakLvl + afterUP) >= 3 && (beforeBreakLvl+beforeUP) <=3) {
                    weapon_asc_temp_count.set(1,weapon_asc_temp_count.get(1) + WeaponCopy1List.get(2));
                    weapon_asc_temp_count.set(5,weapon_asc_temp_count.get(5) + WeaponCopy2List.get(2));
                    weapon_asc_temp_count.set(8,weapon_asc_temp_count.get(8) + WeaponCommonList.get(2));
                }
                if ((this.afterBreakLvl + afterUP) >= 4 && (beforeBreakLvl+beforeUP) <=4) {
                    weapon_asc_temp_count.set(2,weapon_asc_temp_count.get(2) + WeaponCopy1List.get(3));
                    weapon_asc_temp_count.set(5,weapon_asc_temp_count.get(5) + WeaponCopy2List.get(3));
                    weapon_asc_temp_count.set(8,weapon_asc_temp_count.get(8) + WeaponCommonList.get(3));
                }
                if ((this.afterBreakLvl + afterUP) >= 5 && (beforeBreakLvl+beforeUP) <=5) {
                    weapon_asc_temp_count.set(2,weapon_asc_temp_count.get(2) + WeaponCopy1List.get(4));
                    weapon_asc_temp_count.set(6,weapon_asc_temp_count.get(6) + WeaponCopy2List.get(4));
                    weapon_asc_temp_count.set(9,weapon_asc_temp_count.get(9) + WeaponCommonList.get(4));
                }
                if ((this.afterBreakLvl + afterUP) >= 6 && (beforeBreakLvl+beforeUP) <=6) {
                    weapon_asc_temp_count.set(3,weapon_asc_temp_count.get(3) + WeaponCopy1List.get(5));
                    weapon_asc_temp_count.set(6,weapon_asc_temp_count.get(6) + WeaponCopy2List.get(5));
                    weapon_asc_temp_count.set(9,weapon_asc_temp_count.get(9) + WeaponCommonList.get(5));
                }
            }

            material.FindWeaponItemByName(weapon_asc_temp_item,weapon_asc_temp_count);

        }
        resultShow();
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

    public void itemAdd(int rowMax ,int size, String itemName, int itemCnt, int itemRare, LinearLayout siptik_result_ll){
        if(itemCnt>0){
            size = size-8;
            View view = View.inflate(context, R.layout.item_ico_2048, null);
            ImageView item_ico = view.findViewById(R.id.item_ico);
            ImageView item_bg = view.findViewById(R.id.item_bg);
            TextView item_lvl = view.findViewById(R.id.item_lvl);
            item_ico.getLayoutParams().height = size;
            item_ico.getLayoutParams().width = size;
            item_bg.getLayoutParams().height  = LinearLayout.LayoutParams.WRAP_CONTENT;
            item_bg.getLayoutParams().width = size;
            item_lvl.getLayoutParams().height  = LinearLayout.LayoutParams.WRAP_CONTENT;
            item_lvl.getLayoutParams().width = size;
            Picasso.get()
                    .load (FileLoader.loadIMG(item_rss.getItemIcoByName(itemName,context),context))
                    .resize(size,size)
                    .error (R.drawable.paimon_lost)
                    .into (item_ico);
            item_ico.setAdjustViewBounds(true);
            item_ico.setPadding(0,8,0,0);
            item_lvl.setText(prettyCount(itemCnt));
            int bg_rare_rss = R.drawable.rare1_800x1000_dark;

            switch (itemRare) {
                case 1:
                    if (isNight) {
                        bg_rare_rss = R.drawable.rare1_800x1000_dark;
                    } else {
                        bg_rare_rss = R.drawable.rare1_800x1000_light;
                    }
                    break;
                case 2:
                    if (isNight) {
                        bg_rare_rss = R.drawable.rare2_800x1000_dark;
                    } else {
                        bg_rare_rss = R.drawable.rare2_800x1000_light;
                    }
                    break;
                case 3:
                    if (isNight) {
                        bg_rare_rss = R.drawable.rare3_800x1000_dark;
                    } else {
                        bg_rare_rss = R.drawable.rare3_800x1000_light;
                    }
                    break;
                case 4:
                    if (isNight) {
                        bg_rare_rss = R.drawable.rare4_800x1000_dark;
                    } else {
                        bg_rare_rss = R.drawable.rare4_800x1000_light;
                    }
                    break;
                case 5:
                    if (isNight) {
                        bg_rare_rss = R.drawable.rare5_800x1000_dark;
                    } else {
                        bg_rare_rss = R.drawable.rare5_800x1000_light;
                    }
                    break;
                default:
                    if (isNight) {
                        bg_rare_rss = R.drawable.rare1_800x1000_dark;
                    } else {
                        bg_rare_rss = R.drawable.rare1_800x1000_light;
                    }
                    break;

            }

            if (isInPreview){
                DisplayMetrics displayMetrics_w = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics_w);
                Picasso.get()
                        .load (bg_rare_rss)
                        .resize((int) (72*displayMetrics_w.density), (int) (72*1.25*displayMetrics_w.density))
                        .error (R.drawable.paimon_lost)
                        .into (item_bg);
            }else{
                Picasso.get()
                        .load (bg_rare_rss)
                        .resize(size, (int) (size*1.25))
                        .error (R.drawable.paimon_lost)
                        .into (item_bg);
            }
            item_bg.setAdjustViewBounds(true);

            /**
             *
             TextView item_weekly_tv = view.findViewById(R.id.item_weekly_tv);
             item_weekly_tv.setText(item_rss.getLocaleTeaches(itemName,context));
             if(!item_rss.getLocaleTeaches(itemName,context).equals(context.getString(R.string.unknown))){
             item_weekly_tv.setVisibility(View.VISIBLE);
             }
             */
            if (rowNum > rowMax && isInPreview == false){
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setGravity(Gravity.CENTER);
                siptik_result_ll.addView(linearLayout);
                tmp_ll = linearLayout;
                tmp_ll.addView(view);

                rowNum = 2;
                colNum = colNum+1;
            }else{
                tmp_ll.addView(view);
                rowNum = rowNum+1;
            }
        }
    }

    /**EDIT WHEN ADD NEW ITEMS*/
    public void resultShow () {
        //Log.wtf("Procedure","resultShowBegin"+" || "+System.currentTimeMillis());
        /** INIT of UI GONE*/

        DisplayMetrics displayMetrics_w = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics_w);
        int width_w = (int) (displayMetrics_w.widthPixels-((32+16)*displayMetrics_w.density));
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info",MODE_PRIVATE);

        LinearLayout siptik_result_ll = null;
        ImageView item_img;
        TextView tv;
        final int radius = 180;
        final int margin = 4;

        int column = (width_w / 180) ;

        if(width_w < 180){
            column = 1;
        }
        int size = width_w/column;
        if(width_w/column > 168){
            size = 168;
        }

        rowNum = 1;
        colNum = 1;

        if (isInPreview){
            siptik_result_ll = viewPager.findViewById(R.id.db_material_ll);
        }else{
            siptik_result_ll = viewPager.findViewById(R.id.siptik_result_ll);
        }

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setGravity(Gravity.CENTER);
        siptik_result_ll.removeAllViews();
        siptik_result_ll.addView(linearLayout);
        tmp_ll = linearLayout;

        /** CRYSTAL*/
        for (int x = 0; isZero(material.crystalCountList()) == false && x < material.crystalNameList().length; x++) {
            itemAdd(column,size,material.crystalNameList()[x],material.crystalCountList()[x],material.crystalRareList()[x],siptik_result_ll);
        }

        /** BOSS */
        for (int x = 0; isZero(material.bossCountList()) == false && x < material.bossNameList().length; x++) {
            itemAdd(column,size,material.bossNameList()[x],material.bossCountList()[x],4,siptik_result_ll);
        }

        /** WEEK-BOSS */
        for (int x = 0; isZero(material.weekBossCountList()) == false && x < material.weekBossNameList().length; x++) {
            itemAdd(column,size,material.weekBossNameList()[x],material.weekBossCountList()[x],5,siptik_result_ll);
        }

        /** LOCAL */
        for (int x = 0; isZero(material.localCountList()) == false && x < material.localNameList().length; x++) {
            itemAdd(column,size,material.localNameList()[x],material.localCountList()[x],1,siptik_result_ll);
        }

        /** COMMON */
        for (int x = 0; isZero(material.commonCountList()) == false && x < material.commonNameList().length; x++) {
            itemAdd(column,size,material.commonNameList()[x],material.commonCountList()[x],material.commonRareList()[x],siptik_result_ll);
        }

        /** WEEKLY-MON-THUR-SUN */
        for (int x = 0; isZero(material.weekly1CountList()) == false && x < material.weekly1NameList().length; x++) {
            itemAdd(column,size,material.weekly1NameList()[x],material.weekly1CountList()[x],material.weekly1RareList()[x],siptik_result_ll);
        }

        /** WEEKLY-TUE-FRI-SUN */
        for (int x = 0; isZero(material.weekly2CountList()) == false && x < material.weekly2NameList().length; x++) {
            itemAdd(column,size,material.weekly2NameList()[x],material.weekly2CountList()[x],material.weekly2RareList()[x],siptik_result_ll);
        }

        /** WEEKLY-WED-SAT-SUN */
        for (int x = 0; isZero(material.weekly3CountList()) == false && x < material.weekly3NameList().length; x++) {
            itemAdd(column,size,material.weekly3NameList()[x],material.weekly3CountList()[x],material.weekly3RareList()[x],siptik_result_ll);
        }

        String[] other_temp = new String[]{"流浪者的經驗", "冒險家的經驗", "大英雄的經驗", "精鍛用雜礦", "精鍛用良礦", "精鍛用魔礦","祝聖油膏","祝聖精華","摩拉", "智識之冕"};
        int[] other_temp_cnt = new int[]{exp_small,exp_mid,exp_big,weapon_small,weapon_mid,weapon_big,artifact_mid,artifact_big,morax,material.智識之冕};
        int[] other_rare = new int[]{2,3,4,1,2,3,3,4,3,5};

        for (int x = 0; isZero(other_temp_cnt) == false && x < other_temp.length; x++) {
            itemAdd(column,size,other_temp[x],other_temp_cnt[x],other_rare[x],siptik_result_ll);
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
        /**
         if(!WeaponRareList.isEmpty()){
         weapon_calculate();
         }
         */

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

    private Bitmap getBitmapFromView(View view, int height, int width) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.TRANSPARENT);
        view.draw(canvas);
        return bitmap;
    }

    private boolean saveImageToExternalStorage(String imgName, Bitmap bmp){
        Uri ImageCollection = null;
        ContentResolver resolver = context.getContentResolver();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            ImageCollection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
        }else {
            ImageCollection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, imgName + ".png");
        contentValues.put(MediaStore.Images.Media.DATE_ADDED, new Date().getTime());
        contentValues.put(MediaStore.Images.Media.MIME_TYPE,"image/png");
        Uri imageUri = resolver.insert(ImageCollection, contentValues);
        System.out.println("imageUri "+imageUri);
        try {
            OutputStream outputStream = resolver.openOutputStream(Objects.requireNonNull(imageUri));
            bmp.compress(Bitmap.CompressFormat.PNG,100,outputStream);
            Objects.requireNonNull(outputStream);
            return true;
        }
        catch (Exception e){
            CustomToast.toast(context,activity,context.getString(R.string.screenshot_not_saved));
            e.printStackTrace();
        }
        return false;

    }

    private void requestPermission() {
        boolean minSDK = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;
        isReadPermissionGranted = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED;
        isWritePermissionGranted = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED;
        isWritePermissionGranted = isWritePermissionGranted || minSDK;
    }

    public String prettyTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}