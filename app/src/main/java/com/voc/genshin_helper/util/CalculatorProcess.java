package com.voc.genshin_helper.util;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;
import androidx.gridlayout.widget.GridLayout;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.CalculatorDB;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.ScreenSizeUtils;
import com.voc.genshin_helper.database.DataBaseContract;
import com.voc.genshin_helper.database.DataBaseHelper;
import com.voc.genshin_helper.ui.CalculatorDBActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

/*
 * Package com.voc.genshin_helper.util.CalculatorProcess was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */
public class CalculatorProcess {
    /** Crystal*/ /**[碎屑,斷片,塊,原顆]*/
    ArrayList<Integer> 燃願瑪瑙 = new ArrayList<Integer>();
    ArrayList<Integer> 滌淨青金 = new ArrayList<Integer>();
    ArrayList<Integer> 最勝紫晶 = new ArrayList<Integer>();
    ArrayList<Integer> 哀敘冰玉 = new ArrayList<Integer>();
    ArrayList<Integer> 自在松石 = new ArrayList<Integer>();
    ArrayList<Integer> 堅牢黃玉 = new ArrayList<Integer>();

    /** BOSS ASC*/
    int  常燃火種  = 0 ;
    int  淨水之心  = 0 ;
    int  雷光棱鏡  = 0 ;
    int  極寒之核  = 0 ;
    int  颶風之種  = 0 ;
    int  玄岩之塔  = 0 ;
    int  未熟之玉  = 0 ;
    int  晶凝之華  = 0 ;
    int  魔偶機心  = 0 ;
    int  恒常機關之心  = 0 ;
    int  陰燃之珠  = 0 ;
    //add in 20210910
    int  雷霆數珠 = 0;
    int  排異之露 = 0;

    /** LOCAL */
    int  小燈草  = 0 ;
    int  慕風蘑菇  = 0 ;
    int  夜泊石  = 0 ;
    int  風車菊  = 0 ;
    int  石珀  = 0 ;
    int  蒲公英籽  = 0 ;
    int  嘟嘟蓮  = 0 ;
    int  落落莓  = 0 ;
    int  琉璃百合  = 0 ;
    int  琉璃袋  = 0 ;
    int  鉤鉤果  = 0 ;
    int  塞西莉亞花  = 0 ;
    int  絕雲椒椒  = 0 ;
    int  霓裳花  = 0 ;
    int  星螺  = 0 ;
    int  清心  = 0 ;
    int  海靈芝  = 0 ;
    int  緋櫻繡球  = 0 ;
    int  鳴草  = 0 ;
    int  晶化骨髓  = 0 ;
    //add in 20210910
    int  天雲草實  = 0 ;
    int  血斛  = 0 ;
    int  幽燈蕈  = 0 ;
    int  珊瑚真珠  = 0 ;

    /** T-BOSS ASC*/

    int 智識之冕 = 0;
    int  東風的吐息  = 0 ;
    int  北風之環  = 0 ;
    int  東風之翎  = 0 ;
    int  北風的魂匣  = 0 ;
    int  東風之爪  = 0 ;
    int  北風之尾  = 0 ;
    int  魔王之刃_殘片  = 0 ;
    int  吞天之鯨_只角  = 0 ;
    int  武煉之魂_孤影  = 0 ;
    int  龍王之冕  = 0 ;
    int  血玉之枝  = 0 ;
    int  鎏金之鱗  = 0 ;
    //add in 20210910
    int  熔毀之刻  = 0 ;
    int  灰燼之心  = 0 ;
    int  獄火之蝶  = 0 ;


    /** COMMON */ /**[牢固的箭簇,銳利的箭簇,歷戰的箭簇]*/
    ArrayList<Integer> 歷戰的箭簇 = new ArrayList<Integer>();
    ArrayList<Integer> 禁咒繪卷 = new ArrayList<Integer>();
    ArrayList<Integer> 攫金鴉印 = new ArrayList<Integer>();
    ArrayList<Integer> 不祥的面具 = new ArrayList<Integer>();
    ArrayList<Integer> 尉官的徽記 = new ArrayList<Integer>();
    ArrayList<Integer> 原素花蜜 = new ArrayList<Integer>();
    ArrayList<Integer> 史萊姆原漿 = new ArrayList<Integer>();
    ArrayList<Integer> 名刀鐔 = new ArrayList<Integer>();
    //add in 20210910
    ArrayList<Integer> 浮游晶化核 = new ArrayList<Integer>();

    /** T-COMMON*/

    ArrayList<Integer> 自由_的哲學 = new ArrayList<Integer>();
    ArrayList<Integer> 黃金_的哲學 = new ArrayList<Integer>();
    ArrayList<Integer> 抗爭_的哲學 = new ArrayList<Integer>();
    ArrayList<Integer> 勤勞_的哲學 = new ArrayList<Integer>();
    ArrayList<Integer> 詩文_的哲學 = new ArrayList<Integer>();
    ArrayList<Integer> 繁榮_的哲學 = new ArrayList<Integer>();
    ArrayList<Integer> 風雅_的哲學 = new ArrayList<Integer>();
    ArrayList<Integer> 浮世_的哲學 = new ArrayList<Integer>();
    ArrayList<Integer> 天光_的哲學 = new ArrayList<Integer>();

    /** W-COMMON*/
    ArrayList<Integer> 	漆黑隕鐵的一塊	 = new ArrayList<Integer>();
    ArrayList<Integer> 	獅牙鬥士的理想	 = new ArrayList<Integer>();
    ArrayList<Integer> 	孤雲寒林的神體	 = new ArrayList<Integer>();
    ArrayList<Integer> 	霧海雲間的轉還	 = new ArrayList<Integer>();
    ArrayList<Integer> 	今昔劇畫的鬼人	 = new ArrayList<Integer>();
    ArrayList<Integer> 	鳴神御靈的勇武	 = new ArrayList<Integer>();
    ArrayList<Integer> 	高塔孤王的碎夢	 = new ArrayList<Integer>();
    ArrayList<Integer> 	遠海夷地的金枝	 = new ArrayList<Integer>();
    ArrayList<Integer> 	凜風奔狼的懷鄉	 = new ArrayList<Integer>();

    ArrayList<Integer> 	混沌真眼	 = new ArrayList<Integer>();
    ArrayList<Integer> 	混沌爐心	 = new ArrayList<Integer>();
    ArrayList<Integer> 	石化的骨片	 = new ArrayList<Integer>();
    ArrayList<Integer> 	霧虛燈芯	 = new ArrayList<Integer>();
    ArrayList<Integer> 	督察長祭刀	 = new ArrayList<Integer>();
    ArrayList<Integer> 	黑晶號角	 = new ArrayList<Integer>();
    ArrayList<Integer> 	地脈的新芽	 = new ArrayList<Integer>();
    ArrayList<Integer> 	偏光棱鏡	 = new ArrayList<Integer>();
    ArrayList<Integer> 	隱獸鬼爪	 = new ArrayList<Integer>();



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
    ArrayList<Integer> asc_temp_count = new ArrayList<Integer>();
    ArrayList<String> skill1_temp_item = new ArrayList<String>();
    ArrayList<Integer> skill1_temp_count = new ArrayList<Integer>();
    ArrayList<String> skill2_temp_item = new ArrayList<String>();
    ArrayList<Integer> skill2_temp_count = new ArrayList<Integer>();
    ArrayList<String> skill3_temp_item = new ArrayList<String>();
    ArrayList<Integer> skill3_temp_count = new ArrayList<Integer>();

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

    ArrayList<Integer> weapon_asc_temp_count = new ArrayList<>();
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


    public void setVP(ViewPager viewPager, View viewPager3) {
        this.viewPager = viewPager;
        this.cal_view = viewPager3;
    }

    public void setup(Context context ,ArrayList<String> NameList, ArrayList<Integer> BeforeLvlList, ArrayList<Integer> AfterLvlList, ArrayList<Integer> BeforeBreakLvlList, ArrayList<Integer> AfterBreakLvlList, ArrayList<Integer> BeforeSkill1LvlList, ArrayList<Integer> AfterSkill1LvlList, ArrayList<Integer> BeforeSkill2LvlList, ArrayList<Integer> AfterSkill2LvlList, ArrayList<Integer> BeforeSkill3LvlList, ArrayList<Integer> AfterSkill3LvlList, ArrayList<Boolean> IsCal, ArrayList<Boolean> BeforeBreakUPLvlList, ArrayList<Boolean> AfterBreakUPLvlList) {
        this.context = context;
        this.NameList = NameList;
        //System.out.println(NameList);
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

        for (int x = 0 ; x < 4 ; x++){
            燃願瑪瑙.add(0);
            滌淨青金.add(0);
            最勝紫晶.add(0);
            哀敘冰玉.add(0);
            自在松石.add(0);
            堅牢黃玉.add(0);

            漆黑隕鐵的一塊.add(0);
            獅牙鬥士的理想.add(0);
            孤雲寒林的神體.add(0);
            霧海雲間的轉還.add(0);
            今昔劇畫的鬼人.add(0);
            鳴神御靈的勇武.add(0);
            高塔孤王的碎夢.add(0);
            遠海夷地的金枝.add(0);
            凜風奔狼的懷鄉.add(0);

        }

        for (int x = 0 ; x < 3 ; x++){
            歷戰的箭簇.add(0);
            禁咒繪卷.add(0);
            攫金鴉印.add(0);
            不祥的面具.add(0);
            尉官的徽記.add(0);
            原素花蜜.add(0);
            史萊姆原漿.add(0);
            名刀鐔.add(0);
            浮游晶化核.add(0);

            混沌真眼.add(0);
            混沌爐心.add(0);
            石化的骨片.add(0);
            霧虛燈芯.add(0);
            督察長祭刀.add(0);
            黑晶號角.add(0);
            地脈的新芽.add(0);
            偏光棱鏡.add(0);
            隱獸鬼爪.add(0);

            自由_的哲學.add(0);
            黃金_的哲學.add(0);
            抗爭_的哲學.add(0);
            勤勞_的哲學.add(0);
            詩文_的哲學.add(0);
            繁榮_的哲學.add(0);
            風雅_的哲學.add(0);
            浮世_的哲學.add(0);
            天光_的哲學.add(0);

        }

        for (int k = 0 ; k < NameList.size() ; k ++){part0_exp.add(0);}
        for (int k = 0 ; k < NameList.size() ; k ++){part1_exp.add(0);}
        for (int k = 0 ; k < NameList.size() ; k ++){part2_exp.add(0);}
        for (int k = 0 ; k < NameList.size() ; k ++){part3_exp.add(0);}
        for (int k = 0 ; k < NameList.size() ; k ++){part4_exp.add(0);}
        for (int k = 0 ; k < NameList.size() ; k ++){part5_exp.add(0);}
        for (int k = 0 ; k < NameList.size() ; k ++){part6_exp.add(0);}
        for (int k = 0 ; k < 10 ; k ++){asc_temp_count.add(0);}
        for (int k = 0 ; k < 7 ; k ++){skill1_temp_count.add(0);}
        for (int k = 0 ; k < 7 ; k ++){skill2_temp_count.add(0);}
        for (int k = 0 ; k < 7 ; k ++){skill3_temp_count.add(0);}


        for (int k = 0 ; k < NameList.size() ; k ++){weapon_part0_exp.add(0);}
        for (int k = 0 ; k < NameList.size() ; k ++){weapon_part1_exp.add(0);}
        for (int k = 0 ; k < NameList.size() ; k ++){weapon_part2_exp.add(0);}
        for (int k = 0 ; k < NameList.size() ; k ++){weapon_part3_exp.add(0);}
        for (int k = 0 ; k < NameList.size() ; k ++){weapon_part4_exp.add(0);}
        for (int k = 0 ; k < NameList.size() ; k ++){weapon_part5_exp.add(0);}
        for (int k = 0 ; k < NameList.size() ; k ++){weapon_part6_exp.add(0);}


        for (int k = 0 ; k < 10 ; k ++){weapon_asc_temp_count.add(0);}

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

        if(arg.equals("READ")){
        char_readJSON();
        }
    }

    public void char_calculate () {
        Log.wtf("HEY","TOMMY");
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

                for (int k = 0 ; k < 9 ; k ++){asc_temp_count.add(0);}
                for (int k = 0 ; k < 7 ; k ++){skill1_temp_count.add(0);}
                for (int k = 0 ; k < 7 ; k ++){skill2_temp_count.add(0);}
                for (int k = 0 ; k < 7 ; k ++){skill3_temp_count.add(0);}

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
                getCharEXPItemCount(x,exp_grade0);
                getCharEXPItemCount(x,exp_grade1);
                getCharEXPItemCount(x,exp_grade2);
                getCharEXPItemCount(x,exp_grade3);
                getCharEXPItemCount(x,exp_grade4);
                getCharEXPItemCount(x,exp_grade5);
                getCharEXPItemCount(x,exp_grade6);

                Log.wtf("HEY","PAIMON");

                /** CAL ASC */
                int beforeUP = 0 , afterUP = 0;
                if(BeforeBreakUPLvlList.get(x) == true){beforeUP = 1;}
                if(AfterBreakUPLvlList.get(x) == true){afterUP = 1;}

                //System.out.println("SS"+BeforeBreakLvlList);
                //System.out.println("RR"+AfterBreakLvlList);

                Log.wtf("BEFORE_LVL",String.valueOf(BeforeLvlList.get(x)));
                Log.wtf("AFTER_LVL",String.valueOf(AfterLvlList.get(x)));
                Log.wtf("BEFORE_UP",String.valueOf(beforeUP));
                Log.wtf("AFTER_UP",String.valueOf(afterUP));

                int z = getCharRealPosByName(NameList.get(x));
                asc_temp_item.add(nameREQUIREList.get(z));
                asc_temp_item.add(crystalREQUIREList.get(z));
                asc_temp_item.add(bossREQUIREList.get(z));
                asc_temp_item.add(localREQUIREList.get(z));
                asc_temp_item.add(commonREQUIREList.get(z));

                for (int y = BeforeBreakLvlList.get(x)+beforeUP ; y < AfterBreakLvlList.get(x)+afterUP ; y ++){

                    Log.wtf("Y is",String.valueOf(y));
                    Log.wtf("AfterBreakLvlList.get(x) is",String.valueOf(AfterBreakLvlList.get(x)));
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
                FindItemByName(asc_temp_item,asc_temp_count);


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

                    if(y == 9) {智識之冕 = 智識之冕 + 1;}

                    // mora.add(moraSkillList.get(y));
                    morax = morax + moraSkillList.get(y);
                }
                FindItemByName(skill1_temp_item,skill1_temp_count);

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

                    if(y == 9) {智識之冕 = 智識之冕 + 1;}
                    //mora.add(moraSkillList.get(y));
                    morax = morax + moraSkillList.get(y);
                }
                FindItemByName(skill2_temp_item,skill2_temp_count);

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

                    if(y ==9) {智識之冕 = 智識之冕 + 1;}
                    // mora.add(moraSkillList.get(y));
                    morax = morax + moraSkillList.get(y);
                }
                FindItemByName(skill3_temp_item,skill3_temp_count);

            }
        }
        char_fin = true;
        check_cal_finished();
    }

    public void weapon_calculate () {
        Log.wtf("HEY","TOMMY");
        for (int x = 0 ; x < WeaponNameList.size() ; x ++) {
            if(WeaponIsCal.get(x) == true){
                /** CAL EXP */
                int exp_grade0 = 0,exp_grade1= 0,exp_grade2= 0,exp_grade3= 0,exp_grade4= 0,exp_grade5= 0,exp_grade6 = 0;
                weapon_asc_temp_item.clear();
                weapon_asc_temp_count.clear();

                for (int k = 0 ; k < 10 ; k ++){weapon_asc_temp_count.add(0);}

                int rare_tmp = WeaponRareList.get(x);
                Log.wtf("rare_tmp", String.valueOf(rare_tmp));

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

                getWeaponEXPItemCount(x,exp_grade0);
                getWeaponEXPItemCount(x,exp_grade1);
                getWeaponEXPItemCount(x,exp_grade2);
                getWeaponEXPItemCount(x,exp_grade3);
                getWeaponEXPItemCount(x,exp_grade4);
                getWeaponEXPItemCount(x,exp_grade5);
                getWeaponEXPItemCount(x,exp_grade6);

                Log.wtf("HEY","PAIMON");

                /** CAL ASC */
                int beforeUP = 0 , afterUP = 0;
                if(WeaponBeforeBreakUPLvlList.get(x) == true){beforeUP = 1;}
                if(WeaponAfterBreakUPLvlList.get(x) == true){afterUP = 1;}

                //System.out.println("SS"+BeforeBreakLvlList);
                //System.out.println("RR"+AfterBreakLvlList);

                Log.wtf("BEFORE_LVL",String.valueOf(WeaponBeforeLvlList.get(x)));
                Log.wtf("AFTER_LVL",String.valueOf(WeaponAfterLvlList.get(x)));
                Log.wtf("BEFORE_UP",String.valueOf(beforeUP));
                Log.wtf("AFTER_UP",String.valueOf(afterUP));
                Log.wtf("BEFORE_BREAK",String.valueOf(WeaponBeforeBreakLvlList.get(x)));
                Log.wtf("AFTER_BREAK",String.valueOf(WeaponAfterBreakLvlList.get(x)));

                for (int y = WeaponBeforeBreakLvlList.get(x)+beforeUP ; y < WeaponAfterBreakLvlList.get(x)+afterUP ; y ++){
                    morax = morax + WeaponMoraASCList.get(y);
                }

                if (WeaponRareList.get(x) == 1 || WeaponRareList.get(x) == 2) {
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 1 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=1) {
                        weapon_asc_temp_count.set(0,weapon_asc_temp_count.get(0) + WeaponCopy1List.get(0));
                        weapon_asc_temp_count.set(4,weapon_asc_temp_count.get(4) + WeaponCopy2List.get(0));
                        weapon_asc_temp_count.set(7,weapon_asc_temp_count.get(7) + WeaponCommonList.get(0));
                        Log.wtf("ABC","X");
                    }
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 2 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=2) {
                        weapon_asc_temp_count.set(1,weapon_asc_temp_count.get(1) + WeaponCopy1List.get(1));
                        weapon_asc_temp_count.set(4,weapon_asc_temp_count.get(4) + WeaponCopy2List.get(1));
                        weapon_asc_temp_count.set(7,weapon_asc_temp_count.get(7) + WeaponCommonList.get(1));
                        Log.wtf("ABC","X");
                    }
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 3 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=3) {
                        weapon_asc_temp_count.set(1,weapon_asc_temp_count.get(1) + WeaponCopy1List.get(2));
                        weapon_asc_temp_count.set(5,weapon_asc_temp_count.get(5) + WeaponCopy2List.get(2));
                        weapon_asc_temp_count.set(8,weapon_asc_temp_count.get(8) + WeaponCommonList.get(2));
                        Log.wtf("ABC","X");
                    }
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 4 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=4) {
                        weapon_asc_temp_count.set(2,weapon_asc_temp_count.get(2) + WeaponCopy1List.get(3));
                        weapon_asc_temp_count.set(5,weapon_asc_temp_count.get(5) + WeaponCopy2List.get(3));
                        weapon_asc_temp_count.set(8,weapon_asc_temp_count.get(8) + WeaponCommonList.get(3));
                        Log.wtf("ABC","X");
                    }
                }else if (WeaponRareList.get(x) == 3 || WeaponRareList.get(x) == 4 || WeaponRareList.get(x) == 5) {
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 1 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=1) {
                        weapon_asc_temp_count.set(0,weapon_asc_temp_count.get(0) + WeaponCopy1List.get(0));
                        weapon_asc_temp_count.set(4,weapon_asc_temp_count.get(4) + WeaponCopy2List.get(0));
                        weapon_asc_temp_count.set(7,weapon_asc_temp_count.get(7) + WeaponCommonList.get(0));
                        Log.wtf("ABC","X");
                    }
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 2 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=2) {
                        weapon_asc_temp_count.set(1,weapon_asc_temp_count.get(1) + WeaponCopy1List.get(1));
                        weapon_asc_temp_count.set(4,weapon_asc_temp_count.get(4) + WeaponCopy2List.get(1));
                        weapon_asc_temp_count.set(7,weapon_asc_temp_count.get(7) + WeaponCommonList.get(1));
                        Log.wtf("ABC","X");
                    }
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 3 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=3) {
                        weapon_asc_temp_count.set(1,weapon_asc_temp_count.get(1) + WeaponCopy1List.get(2));
                        weapon_asc_temp_count.set(5,weapon_asc_temp_count.get(5) + WeaponCopy2List.get(2));
                        weapon_asc_temp_count.set(8,weapon_asc_temp_count.get(8) + WeaponCommonList.get(2));
                        Log.wtf("ABC","X");
                    }
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 4 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=4) {
                        weapon_asc_temp_count.set(2,weapon_asc_temp_count.get(2) + WeaponCopy1List.get(3));
                        weapon_asc_temp_count.set(5,weapon_asc_temp_count.get(5) + WeaponCopy2List.get(3));
                        weapon_asc_temp_count.set(8,weapon_asc_temp_count.get(8) + WeaponCommonList.get(3));
                        Log.wtf("ABC","X");
                    }
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 5 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=5) {
                        weapon_asc_temp_count.set(2,weapon_asc_temp_count.get(2) + WeaponCopy1List.get(4));
                        weapon_asc_temp_count.set(6,weapon_asc_temp_count.get(6) + WeaponCopy2List.get(4));
                        weapon_asc_temp_count.set(9,weapon_asc_temp_count.get(9) + WeaponCommonList.get(4));
                        Log.wtf("ABC","X");
                    }
                    if ((this.WeaponAfterBreakLvlList.get(x) + afterUP) >= 6 && (WeaponBeforeBreakLvlList.get(x)+beforeUP) <=6) {
                        weapon_asc_temp_count.set(3,weapon_asc_temp_count.get(3) + WeaponCopy1List.get(5));
                        weapon_asc_temp_count.set(6,weapon_asc_temp_count.get(6) + WeaponCopy2List.get(5));
                        weapon_asc_temp_count.set(9,weapon_asc_temp_count.get(9) + WeaponCommonList.get(5));
                        Log.wtf("ABC","X");
                    }
                }
                FindWeaponItemByName(weapon_asc_temp_item,weapon_asc_temp_count);

            }
        }
        weapon_fin = true;
        check_cal_finished();
    }

    public void artifact_calculate () {
        Log.wtf("HEY","TOMMYS");
        for (int x = 0 ; x < ArtifactNameList.size() ; x ++) {
            if (ArtifactIsCal.get(x) == true) {
                /** CAL EXP */
                int exp_tmp = 0;
                
                int rare_tmp = ArtifactRareList.get(x);
                Log.wtf("rare_tmp", String.valueOf(rare_tmp));

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
                    Log.wtf("exp_tmp", String.valueOf(exp_tmp)+" EE");
                    morax = morax + ArtifactMoraList.get(y);
                }

                getArtifactEXPItemCount(x, exp_tmp);

            }
        }
        artifact_fin = true;
        check_cal_finished();
    }

    public void check_cal_finished(){
        if(weapon_fin == true && char_fin == true){
            resultShow();
            saveToDB();
            weapon_fin = false;
            char_fin = false;
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

        result_crystal_title.setVisibility(View.GONE);
        result_boss_title.setVisibility(View.GONE);
        result_weekboss_title.setVisibility(View.GONE);
        result_local_title.setVisibility(View.GONE);
        result_weeklybk1_title.setVisibility(View.GONE);
        result_weeklybk2_title.setVisibility(View.GONE);
        result_weeklybk3_title.setVisibility(View.GONE);
        result_other_title.setVisibility(View.GONE);
        result_common_title.setVisibility(View.GONE);

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
        int column = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth()-150)/150;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);
        for (int x = 0, c = 0, r = 0; NameList != null && WeaponNameList != null && x < NameList.size() + WeaponNameList.size(); x++, c++) {
            if(c == column) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
            TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);

            if(x < NameList.size()){
                Picasso.get()
                        .load (item_rss.getCharByName(NameList.get(x))[3])
                        .transform(transformation)
                        .resize(150,150)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(context.getString(item_rss.getCharByName(NameList.get(x))[1]));

                if(IsCal.get(x) == false){
                    Drawable drawable = context.getResources().getDrawable(R.drawable.barrier);
                    item_cal_img.setForeground(drawable);
                }

            }else if (NameList.size() + WeaponNameList.size() >0){
                Picasso.get()
                        .load (item_rss.getWeaponByName(WeaponNameList.get(x-NameList.size()))[1])
                        .transform(transformation)
                        .resize(150,150)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(context.getString(item_rss.getWeaponByName(WeaponNameList.get(x-NameList.size()))[0]));

                if(WeaponIsCal.get(x-NameList.size()) == false){
                    Drawable drawable = context.getResources().getDrawable(R.drawable.barrier);
                    item_cal_img.setForeground(drawable);
                }
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


        /** CRYSTAL*/
        String[] crystal_temp = new String[]{"燃願瑪瑙碎屑","燃願瑪瑙斷片","燃願瑪瑙塊","燃願瑪瑙","滌淨青金碎屑","滌淨青金斷片","滌淨青金塊","滌淨青金","最勝紫晶碎屑","最勝紫晶斷片","最勝紫晶塊","最勝紫晶","哀敘冰玉碎屑","哀敘冰玉斷片","哀敘冰玉塊","哀敘冰玉","自在松石碎屑","自在松石斷片","自在松石塊","自在松石","堅牢黃玉碎屑","堅牢黃玉斷片","堅牢黃玉塊","堅牢黃玉"};
        int[] crystal_temp_cnt = new int[]{燃願瑪瑙.get(0),燃願瑪瑙.get(1),燃願瑪瑙.get(2),燃願瑪瑙.get(3),滌淨青金.get(0),滌淨青金.get(1),滌淨青金.get(2),滌淨青金.get(3),最勝紫晶.get(0),最勝紫晶.get(1),最勝紫晶.get(2),最勝紫晶.get(3),哀敘冰玉.get(0),哀敘冰玉.get(1),哀敘冰玉.get(2),哀敘冰玉.get(3),自在松石.get(0),自在松石.get(1),自在松石.get(2),自在松石.get(3),堅牢黃玉.get(0),堅牢黃玉.get(1),堅牢黃玉.get(2),堅牢黃玉.get(3)};
        gridLayout = viewPager.findViewById(R.id.result_crystal_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; isZero(crystal_temp_cnt) == false && x < crystal_temp.length; x++) {
            if(c == column) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
            TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
            if(crystal_temp_cnt[x] > 0){
                Picasso.get()
                        .load (item_rss.getItemIcoByName(crystal_temp[x],context))
                        .resize(128,128)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(prettyCount(crystal_temp_cnt[x]));
                item_cal_img.setAdjustViewBounds(true);
                gridLayout.setVisibility(View.VISIBLE);
                result_crystal_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view);
                c++;
            }
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER_VERTICAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }

        /** BOSS */
        String[] boss_temp = new String[]{"常燃火種","淨水之心","雷光棱鏡","極寒之核","颶風之種","玄岩之塔","未熟之玉","晶凝之華","魔偶機心","恒常機關之心","陰燃之珠","雷霆數珠","排異之露"};
        int[] boss_temp_cnt = new int[]{常燃火種,淨水之心,雷光棱鏡,極寒之核,颶風之種,玄岩之塔,未熟之玉,晶凝之華,魔偶機心,恒常機關之心,陰燃之珠,雷霆數珠,排異之露};
        gridLayout = viewPager.findViewById(R.id.result_boss_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; isZero(boss_temp_cnt) == false && x < boss_temp.length; x++) {
            if(c == column) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
            TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
            if(boss_temp_cnt[x] > 0){
                Picasso.get()
                        .load (item_rss.getItemIcoByName(boss_temp[x],context))
                        .resize(128,128)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(prettyCount(boss_temp_cnt[x]));
                item_cal_img.setAdjustViewBounds(true);
                gridLayout.setVisibility(View.VISIBLE);
                result_boss_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view);
                c++;
            }
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER_VERTICAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }

        /** WEEK-BOSS */
        String[] week_boss_temp = new String[]{"北風之環","東風的吐息","東風之翎","北風的魂匣","東風之爪","北風之尾","魔王之刃·殘片","吞天之鯨·只角","武煉之魂·孤影","龍王之冕","血玉之枝","鎏金之鱗","熔毀之刻","灰燼之心","獄火之蝶"};
        int[] week_boss_temp_cnt = new int[]{北風之環,東風的吐息,東風之翎,北風的魂匣,東風之爪,北風之尾,魔王之刃_殘片,吞天之鯨_只角,武煉之魂_孤影,龍王之冕,血玉之枝,鎏金之鱗,熔毀之刻,灰燼之心,獄火之蝶};
        gridLayout = viewPager.findViewById(R.id.result_weekboss_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; isZero(week_boss_temp_cnt) == false && x < week_boss_temp.length; x++) {
            if(c == column) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
            TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
            if(week_boss_temp_cnt[x] > 0){
                Picasso.get()
                        .load (item_rss.getItemIcoByName(week_boss_temp[x],context))
                        .resize(128,128)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(prettyCount(week_boss_temp_cnt[x]));
                item_cal_img.setAdjustViewBounds(true);
                gridLayout.setVisibility(View.VISIBLE);
                result_weekboss_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view);
                c++;
            }
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER_VERTICAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }

        /** LOCAL */
        String[] local_temp = new String[]{"小燈草","慕風蘑菇","夜泊石","風車菊","石珀","蒲公英籽","嘟嘟蓮","落落莓","琉璃百合","琉璃袋","鉤鉤果","塞西莉亞花","絕雲椒椒","霓裳花","星螺","清心","海靈芝","緋櫻繡球","鳴草","晶化骨髓","天雲草實","血斛","幽燈蕈","珊瑚真珠"};
        int[] local_temp_cnt = new int[]{小燈草,慕風蘑菇,夜泊石,風車菊,石珀,蒲公英籽,嘟嘟蓮,落落莓,琉璃百合,琉璃袋,鉤鉤果,塞西莉亞花,絕雲椒椒,霓裳花,星螺,清心,海靈芝,緋櫻繡球,鳴草,晶化骨髓,天雲草實,血斛,幽燈蕈,珊瑚真珠};
        gridLayout = viewPager.findViewById(R.id.result_local_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; isZero(local_temp_cnt) == false && x < local_temp.length; x++) {
            if(c == column) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
            TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
            if(local_temp_cnt[x] > 0){
                Picasso.get()
                        .load (item_rss.getItemIcoByName(local_temp[x],context))
                        .resize(128,128)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(prettyCount(local_temp_cnt[x]));
                item_cal_img.setAdjustViewBounds(true);
                gridLayout.setVisibility(View.VISIBLE);
                result_local_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view);
                c++;
            }
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER_VERTICAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }

        /** COMMON */
        String[] common_temp = new String[]{"牢固的箭簇","銳利的箭簇","歷戰的箭簇","導能繪卷","封魔繪卷","禁咒繪卷","尋寶鴉印","藏銀鴉印","攫金鴉印","破損的面具","污穢的面具","不祥的面具","新兵的徽記","士官的徽記","尉官的徽記","騙騙花蜜","微光花蜜","原素花蜜","史萊姆凝液","史萊姆清","史萊姆原漿","破舊的刀鐔","影打刀鐔","名刀鐔","浮游乾核","浮游幽核","浮游晶化核", "混沌機關", "混沌樞紐", "混沌真眼", "混沌裝置", "混沌迴路", "混沌爐心", "脆弱的骨片", "結實的骨片", "石化的骨片", "霧虛花粉", "霧虛草囊", "霧虛燈芯", "獵兵祭刀", "特工祭刀", "督察長祭刀", "沉重號角", "黑銅號角", "黑晶號角", "地脈的舊枝", "地脈的枯葉", "地脈的新芽", "黯淡棱鏡", "水晶棱鏡", "偏光棱鏡","隱獸指爪","隱獸利爪","隱獸鬼爪"};
        int[] common_temp_cnt = new int[]{歷戰的箭簇.get(0),歷戰的箭簇.get(1),歷戰的箭簇.get(2),禁咒繪卷.get(0),禁咒繪卷.get(1),禁咒繪卷.get(2),攫金鴉印.get(0),攫金鴉印.get(1),攫金鴉印.get(2),不祥的面具.get(0),不祥的面具.get(1),不祥的面具.get(2),尉官的徽記.get(0),尉官的徽記.get(1),尉官的徽記.get(2),原素花蜜.get(0),原素花蜜.get(1),原素花蜜.get(2),史萊姆原漿.get(0),史萊姆原漿.get(1),史萊姆原漿.get(2),名刀鐔.get(0),名刀鐔.get(1),名刀鐔.get(2),浮游晶化核.get(0),浮游晶化核.get(1),浮游晶化核.get(2),混沌真眼.get(0),混沌真眼.get(1),混沌真眼.get(2),混沌爐心.get(0),混沌爐心.get(1),混沌爐心.get(2),石化的骨片.get(0),石化的骨片.get(1),石化的骨片.get(2),霧虛燈芯.get(0),霧虛燈芯.get(1),霧虛燈芯.get(2),督察長祭刀.get(0),督察長祭刀.get(1),督察長祭刀.get(2),黑晶號角.get(0),黑晶號角.get(1),黑晶號角.get(2),地脈的新芽.get(0),地脈的新芽.get(1),地脈的新芽.get(2),偏光棱鏡.get(0),偏光棱鏡.get(1),偏光棱鏡.get(2),隱獸鬼爪.get(0),隱獸鬼爪.get(1),隱獸鬼爪.get(2)};
        gridLayout = viewPager.findViewById(R.id.result_common_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; isZero(common_temp_cnt) == false && x < common_temp.length; x++) {
            if(c == column) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
            TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
            if(common_temp_cnt[x] > 0){
                Picasso.get()
                        .load (item_rss.getItemIcoByName(common_temp[x],context))
                        .resize(128,128)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(prettyCount(common_temp_cnt[x]));
                item_cal_img.setAdjustViewBounds(true);
                gridLayout.setVisibility(View.VISIBLE);
                result_common_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view);
                c++;
            }
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER_VERTICAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }

        /** WEEKLY-MON-THUR-SUN */
        String[] weeklybk1_temp = new String[]{"「自由」的教導","「自由」的指引","「自由」的哲學","「繁榮」的教導","「繁榮」的指引","「繁榮」的哲學","「浮世」的教導","「浮世」的指引","「浮世」的哲學","高塔孤王的破瓦", "高塔孤王的殘垣", "高塔孤王的斷片", "高塔孤王的碎夢", "孤雲寒林的光砂", "孤雲寒林的輝岩", "孤雲寒林的聖骸", "孤雲寒林的神體", "遠海夷地的瑚枝", "遠海夷地的玉枝", "遠海夷地的瓊枝", "遠海夷地的金枝"};
        int[] weeklybk1_temp_cnt = new int[]{自由_的哲學.get(0),自由_的哲學.get(1),自由_的哲學.get(2),繁榮_的哲學.get(0),繁榮_的哲學.get(1),繁榮_的哲學.get(2),浮世_的哲學.get(0),浮世_的哲學.get(1),浮世_的哲學.get(2),高塔孤王的碎夢.get(0),高塔孤王的碎夢.get(1),高塔孤王的碎夢.get(2),高塔孤王的碎夢.get(3),孤雲寒林的神體.get(0),孤雲寒林的神體.get(1),孤雲寒林的神體.get(2),孤雲寒林的神體.get(3),遠海夷地的金枝.get(0),遠海夷地的金枝.get(1),遠海夷地的金枝.get(2),遠海夷地的金枝.get(3)};
        gridLayout = viewPager.findViewById(R.id.result_weeklybk1_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; isZero(weeklybk1_temp_cnt) == false && x < weeklybk1_temp.length; x++) {
            if(c == column) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
            TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
            TextView item_weekly_tv = view.findViewById(R.id.item_weekly_tv);
            if(weeklybk1_temp_cnt[x] > 0){
                Picasso.get()
                        .load (item_rss.getItemIcoByName(weeklybk1_temp[x],context))
                        .resize(128,128)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(prettyCount(weeklybk1_temp_cnt[x]));
                item_weekly_tv.setText(item_rss.getLocaleTeaches(weeklybk1_temp[x],context));
                if(item_rss.getLocaleTeaches(weeklybk1_temp[x],context).equals(context.getString(R.string.unknown))){
                    item_weekly_tv.setVisibility(View.GONE);
                }
                item_cal_img.setAdjustViewBounds(true);
                gridLayout.setVisibility(View.VISIBLE);
                result_weeklybk1_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view);
                c++;
            }
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER_VERTICAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }

        /** WEEKLY-TUE-FRI-SUN */
        String[] weeklybk2_temp = new String[]{"「抗爭」的教導","「抗爭」的指引","「抗爭」的哲學","「勤勞」的教導","「勤勞」的指引","「勤勞」的哲學","「風雅」的教導","「風雅」的指引","「風雅」的哲學" , "凜風奔狼的始齔", "凜風奔狼的裂齒", "凜風奔狼的斷牙", "凜風奔狼的懷鄉", "霧海雲間的鉛丹", "霧海雲間的汞丹", "霧海雲間的金丹", "霧海雲間的轉還", "鳴神御靈的明惠", "鳴神御靈的歡喜", "鳴神御靈的親愛", "鳴神御靈的勇武"};
        int[] weeklybk2_temp_cnt = new int[]{抗爭_的哲學.get(0),抗爭_的哲學.get(1),抗爭_的哲學.get(2),勤勞_的哲學.get(0),勤勞_的哲學.get(1),勤勞_的哲學.get(2),風雅_的哲學.get(0),風雅_的哲學.get(1),風雅_的哲學.get(2),凜風奔狼的懷鄉.get(0),凜風奔狼的懷鄉.get(1),凜風奔狼的懷鄉.get(2),凜風奔狼的懷鄉.get(3),霧海雲間的轉還.get(0),霧海雲間的轉還.get(1),霧海雲間的轉還.get(2),霧海雲間的轉還.get(3),鳴神御靈的勇武.get(0),鳴神御靈的勇武.get(1),鳴神御靈的勇武.get(2),鳴神御靈的勇武.get(3)};
        gridLayout = viewPager.findViewById(R.id.result_weeklybk2_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; isZero(weeklybk2_temp_cnt) == false && x < weeklybk2_temp.length; x++) {
            if(c == column) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
            TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
            TextView item_weekly_tv = view.findViewById(R.id.item_weekly_tv);
            if(weeklybk2_temp_cnt[x] > 0){
                Picasso.get()
                        .load (item_rss.getItemIcoByName(weeklybk2_temp[x],context))
                        .resize(128,128)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(prettyCount(weeklybk2_temp_cnt[x]));
                item_weekly_tv.setText(item_rss.getLocaleTeaches(weeklybk2_temp[x],context));
                if(item_rss.getLocaleTeaches(weeklybk2_temp[x],context).equals(context.getString(R.string.unknown))){
                    item_weekly_tv.setVisibility(View.GONE);
                }
                item_cal_img.setAdjustViewBounds(true);
                gridLayout.setVisibility(View.VISIBLE);
                result_weeklybk2_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view);
                c++;
            }
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER_VERTICAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }

        /** WEEKLY-WED-SAT-SUN */
        String[] weeklybk3_temp = new String[]{"「黃金」的教導","「黃金」的指引","「黃金」的哲學","「詩文」的教導","「詩文」的指引","「詩文」的哲學","「天光」的教導","「天光」的指引","「天光」的哲學", "獅牙鬥士的枷鎖", "獅牙鬥士的鐵鍊", "獅牙鬥士的鐐銬", "獅牙鬥士的理想", "漆黑隕鐵的一粒", "漆黑隕鐵的一片", "漆黑隕鐵的一角", "漆黑隕鐵的一塊", "今昔劇畫的惡尉", "今昔劇畫的虎囓", "今昔劇畫的一角", "今昔劇畫的鬼人"};
        int[] weeklybk3_temp_cnt = new int[]{黃金_的哲學.get(0),黃金_的哲學.get(1),黃金_的哲學.get(2),詩文_的哲學.get(0),詩文_的哲學.get(1),詩文_的哲學.get(2),天光_的哲學.get(0),天光_的哲學.get(1),天光_的哲學.get(2),獅牙鬥士的理想.get(0),獅牙鬥士的理想.get(1),獅牙鬥士的理想.get(2),獅牙鬥士的理想.get(3),漆黑隕鐵的一塊.get(0),漆黑隕鐵的一塊.get(1),漆黑隕鐵的一塊.get(2),漆黑隕鐵的一塊.get(3),今昔劇畫的鬼人.get(0),今昔劇畫的鬼人.get(1),今昔劇畫的鬼人.get(2),今昔劇畫的鬼人.get(3)};
        gridLayout = viewPager.findViewById(R.id.result_weeklybk3_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; isZero(weeklybk3_temp_cnt) == false && x < weeklybk3_temp.length; x++) {
            if(c == column) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
            TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
            TextView item_weekly_tv = view.findViewById(R.id.item_weekly_tv);
            if(weeklybk3_temp_cnt[x] > 0){
                Picasso.get()
                        .load (item_rss.getItemIcoByName(weeklybk3_temp[x],context))
                        .resize(128,128)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(prettyCount(weeklybk3_temp_cnt[x]));
                item_weekly_tv.setText(item_rss.getLocaleTeaches(weeklybk3_temp[x],context));
                if(item_rss.getLocaleTeaches(weeklybk3_temp[x],context).equals(context.getString(R.string.unknown))){
                    item_weekly_tv.setVisibility(View.GONE);
                }
                item_cal_img.setAdjustViewBounds(true);
                gridLayout.setVisibility(View.VISIBLE);
                result_weeklybk3_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view);
                c++;
            }
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER_VERTICAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }

        /** OTHERS*/
        /*
        int mora_final = 0;
        for (int e = 0 ; e < mora.size() ; e ++){
            mora_final = mora_final + mora.get(e);
        }
         */

        Log.wtf("OTHERS","exp_small : "+String.valueOf(exp_small)+" | exp_mid : "+String.valueOf(exp_mid)+" | exp_big : "+String.valueOf(exp_big)+" | mora : "+String.valueOf(morax)+" | 智識之冕 : "+String.valueOf(智識之冕));
        String[] other_temp = new String[]{"流浪者的經驗", "冒險家的經驗", "大英雄的經驗", "精鍛用雜礦", "精鍛用良礦", "精鍛用魔礦","祝聖油膏","祝聖精華","摩拉", "智識之冕"};
        int[] other_temp_cnt = new int[]{exp_small,exp_mid,exp_big,weapon_small,weapon_mid,weapon_big,artifact_mid,artifact_big,morax,智識之冕};
        gridLayout = viewPager.findViewById(R.id.result_other_gl);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; isZero(other_temp_cnt) == false && x < other_temp.length; x++) {
            if(c == column) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
            TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
            if(other_temp_cnt[x] > 0){
                Picasso.get()
                        .load (item_rss.getItemIcoByName(other_temp[x],context))
                        .resize(128,128)
                        .error (R.drawable.paimon_lost)
                        .into (item_cal_img);
                item_cal_tv.setText(prettyCount(other_temp_cnt[x]));
                item_cal_img.setAdjustViewBounds(true);
                gridLayout.setVisibility(View.VISIBLE);
                result_other_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view);
                c++;
            }
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER_VERTICAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }
    }

    public void getCharEXPItemCount(int pos, int part_exp){
        int big0 = 0 , mid0 = 0 , small0 = 0;
        float temp1 = 0f , temp2 = 0f , temp3 = 0f;
        int part0 = part_exp;

        /**
         * Constant
         */

        int big_const = 20000;
        int mid_const = 5000;
        int small_const = 1000;

        if(part0 >= big_const) {
            big0 = (int) part0 / big_const;
            temp1 = ((float) part0/big_const - big0)*big_const;

            if(temp1 >= mid_const){
                mid0 = (int) temp1/mid_const;
                temp2 = ((float) temp1/mid_const - mid0)*mid_const;
                if(temp2 >= small_const){
                    small0 = (int) temp2/small_const;
                    temp3 = ((float) temp2/small_const - small0)*small_const;
                    if(temp3 >0){
                        small0 = small0 + 1;
                    }
                }else {
                    small0 = small0 + 1;
                }
            }else {
                if(temp1 >= small_const){
                    small0 = (int) temp1/small_const;
                    temp2 = ((float) temp1/small_const - small0)*small_const;
                    if(temp2 >0){
                        small0 = small0 + 1;
                    }
                }else {
                    small0 = small0 + 1;
                }
            }
        }else if(part0 >= mid_const){
            mid0 = (int) part0/mid_const;
            temp1 = ((float) part0/mid_const - mid0)*mid_const;
            if(temp1 >= small_const){
                small0 = (int) temp1/small_const;
                temp2 = ((float) temp1/small_const - small0)*small_const;
                if(temp2 >0){
                    small0 = small0 + 1;
                }
            }else {
                small0 = small0 + 1;
            }
        }else {
            if(part0 >= small_const){
                small0 = (int) part0/small_const;
                temp1 = ((float) part0/small_const - small0)*small_const;
                if(temp1 >0){
                    small0 = small0 + 1;
                }
            }else {
                small0 = small0 + 1;
            }
        }
        exp_big = exp_big + big0;
        exp_mid = exp_mid + mid0;
        exp_small = exp_small + small0;
    }

    public void getWeaponEXPItemCount(int pos, int part_exp){
        int big0 = 0 , mid0 = 0 , small0 = 0;
        float temp1 = 0f , temp2 = 0f , temp3 = 0f;
        int part0 = part_exp;

        /**
         * Constant
         */

        int big_const = 10000;
        int mid_const = 2000;
        int small_const = 400;

        if(part0 >= big_const) {
            big0 = (int) part0 / big_const;
            temp1 = ((float) part0/big_const - big0)*big_const;

            if(temp1 >= mid_const){
                mid0 = (int) temp1/mid_const;
                temp2 = ((float) temp1/mid_const - mid0)*mid_const;
                if(temp2 >= small_const){
                    small0 = (int) temp2/small_const;
                    temp3 = ((float) temp2/small_const - small0)*small_const;
                    if(temp3 >0){
                        small0 = small0 + 1;
                    }
                }else {
                    small0 = small0 + 1;
                }
            }else {
                if(temp1 >= small_const){
                    small0 = (int) temp1/small_const;
                    temp2 = ((float) temp1/small_const - small0)*small_const;
                    if(temp2 >0){
                        small0 = small0 + 1;
                    }
                }else {
                    small0 = small0 + 1;
                }
            }
        }else if(part0 >= mid_const){
            mid0 = (int) part0/mid_const;
            temp1 = ((float) part0/mid_const - mid0)*mid_const;
            if(temp1 >= small_const){
                small0 = (int) temp1/small_const;
                temp2 = ((float) temp1/small_const - small0)*small_const;
                if(temp2 >0){
                    small0 = small0 + 1;
                }
            }else {
                small0 = small0 + 1;
            }
        }else {
            if(part0 >= small_const){
                small0 = (int) part0/small_const;
                temp1 = ((float) part0/small_const - small0)*small_const;
                if(temp1 >0){
                    small0 = small0 + 1;
                }
            }else {
                small0 = small0 + 1;
            }
        }
        weapon_big = weapon_big + big0;
        weapon_mid = weapon_mid + mid0;
        weapon_small = weapon_small + small0;
    }

    public void getArtifactEXPItemCount(int pos, int part_exp){
        int big0 = 0 , mid0 = 0 ;
        float temp1 = 0f , temp2 = 0f , temp3 = 0f;
        int part0 = part_exp;

        /**
         * Constant
         */

        int big_const = 10000;
        int mid_const = 2500;

        if(part0 >= big_const) {
            big0 = (int) part0 / big_const;
            temp1 = ((float) part0/big_const - big0)*big_const;

            if(temp1 >= mid_const){
                mid0 = (int) temp1/mid_const;
            }else {
                mid0 = mid0 + 1;
            }
        }else if(part0 >= mid_const){
            mid0 = (int) part0/mid_const;
        }else {
            mid0 = mid0 + 1;
        }
        artifact_big = artifact_big + big0;
        artifact_mid = artifact_mid + mid0;
    }


    /**EDIT WHEN ADD NEW ITEMS*/
    public void FindItemByName(ArrayList<String> temp_item, ArrayList<Integer> temp_count){

        if(temp_item.size() >= 5){
            /** CRYSTAL -> USING temp_count's pos 0-3*/

            if(temp_item.get(1).equals("燃願瑪瑙")){addCountIntoVar(燃願瑪瑙,temp_count,"CRYSTAL");}
            if(temp_item.get(1).equals("滌淨青金")){addCountIntoVar(滌淨青金,temp_count,"CRYSTAL");}
            if(temp_item.get(1).equals("最勝紫晶")){addCountIntoVar(最勝紫晶,temp_count,"CRYSTAL");}
            if(temp_item.get(1).equals("哀敘冰玉")){addCountIntoVar(哀敘冰玉,temp_count,"CRYSTAL");}
            if(temp_item.get(1).equals("自在松石")){addCountIntoVar(自在松石,temp_count,"CRYSTAL");}
            if(temp_item.get(1).equals("堅牢黃玉")){addCountIntoVar(堅牢黃玉,temp_count,"CRYSTAL");}

            /** COMMON -> USING temp_count's pos 7-9*/
            if(temp_item.get(4).equals("歷戰的箭簇")){addCountIntoVar(歷戰的箭簇,temp_count,"COMMON");}
            if(temp_item.get(4).equals("禁咒繪卷")){addCountIntoVar(禁咒繪卷,temp_count,"COMMON");}
            if(temp_item.get(4).equals("攫金鴉印")){addCountIntoVar(攫金鴉印,temp_count,"COMMON");}
            if(temp_item.get(4).equals("不祥的面具")){addCountIntoVar(不祥的面具,temp_count,"COMMON");}
            if(temp_item.get(4).equals("尉官的徽記")){addCountIntoVar(尉官的徽記,temp_count,"COMMON");}
            if(temp_item.get(4).equals("原素花蜜")){addCountIntoVar(原素花蜜,temp_count,"COMMON");}
            if(temp_item.get(4).equals("史萊姆原漿")){addCountIntoVar(史萊姆原漿,temp_count,"COMMON");}
            if(temp_item.get(4).equals("名刀鐔")){addCountIntoVar(名刀鐔,temp_count,"COMMON");}
            //add in 20210910
            if(temp_item.get(4).equals("浮游晶化核")){addCountIntoVar(浮游晶化核,temp_count,"COMMON");}
            //add in 20211030
            if(temp_item.get(4).equals("隱獸鬼爪")){addCountIntoVar(隱獸鬼爪,temp_count,"COPY2");}

            /** BOSS -> USING temp_count's pos 8 */
            if(temp_item.get(2).equals("常燃火種")){常燃火種 = addCountIntoVar(常燃火種,temp_count,"BOSS");}
            if(temp_item.get(2).equals("淨水之心")){淨水之心 = addCountIntoVar(淨水之心,temp_count,"BOSS");}
            if(temp_item.get(2).equals("雷光棱鏡")){雷光棱鏡 = addCountIntoVar(雷光棱鏡,temp_count,"BOSS");}
            if(temp_item.get(2).equals("極寒之核")){極寒之核 = addCountIntoVar(極寒之核,temp_count,"BOSS");}
            if(temp_item.get(2).equals("颶風之種")){颶風之種 = addCountIntoVar(颶風之種,temp_count,"BOSS");}
            if(temp_item.get(2).equals("玄岩之塔")){玄岩之塔 = addCountIntoVar(玄岩之塔,temp_count,"BOSS");}
            if(temp_item.get(2).equals("未熟之玉")){未熟之玉 = addCountIntoVar(未熟之玉,temp_count,"BOSS");}
            if(temp_item.get(2).equals("晶凝之華")){晶凝之華 = addCountIntoVar(晶凝之華,temp_count,"BOSS");}
            if(temp_item.get(2).equals("魔偶機心")){魔偶機心 = addCountIntoVar(魔偶機心,temp_count,"BOSS");}
            if(temp_item.get(2).equals("恒常機關之心")){恒常機關之心 = addCountIntoVar(恒常機關之心,temp_count,"BOSS");}
            if(temp_item.get(2).equals("陰燃之珠")){陰燃之珠 = addCountIntoVar(陰燃之珠,temp_count,"BOSS");}
            //add in 20210910
            if(temp_item.get(2).equals("雷霆數珠")){雷霆數珠 = addCountIntoVar(雷霆數珠,temp_count,"BOSS");}
            if(temp_item.get(2).equals("排異之露")){排異之露 = addCountIntoVar(排異之露,temp_count,"BOSS");}

            /** LOCAL */
            if(temp_item.get(3).equals("小燈草")){小燈草 = addCountIntoVar(小燈草,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("慕風蘑菇")){慕風蘑菇 = addCountIntoVar(慕風蘑菇,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("夜泊石")){夜泊石 = addCountIntoVar(夜泊石,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("風車菊")){風車菊 = addCountIntoVar(風車菊,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("石珀")){石珀 = addCountIntoVar(石珀,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("蒲公英籽")){蒲公英籽 = addCountIntoVar(蒲公英籽,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("嘟嘟蓮")){嘟嘟蓮 = addCountIntoVar(嘟嘟蓮,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("落落莓")){落落莓 = addCountIntoVar(落落莓,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("琉璃百合")){琉璃百合 = addCountIntoVar(琉璃百合,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("琉璃袋")){琉璃袋 = addCountIntoVar(琉璃袋,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("鉤鉤果")){鉤鉤果 = addCountIntoVar(鉤鉤果,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("塞西莉亞花")){塞西莉亞花 = addCountIntoVar(塞西莉亞花,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("絕雲椒椒")){絕雲椒椒 = addCountIntoVar(絕雲椒椒,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("霓裳花")){霓裳花 = addCountIntoVar(霓裳花,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("星螺")){星螺 = addCountIntoVar(星螺,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("清心")){清心 = addCountIntoVar(清心,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("海靈芝")){海靈芝 = addCountIntoVar(海靈芝,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("緋櫻繡球")){緋櫻繡球 = addCountIntoVar(緋櫻繡球,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("鳴草")){鳴草 = addCountIntoVar(鳴草,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("晶化骨髓")){晶化骨髓 = addCountIntoVar(晶化骨髓,temp_count,"LOCAL");}
            //add in 20210910
            else if(temp_item.get(3).equals("天雲草實")){天雲草實 = addCountIntoVar(天雲草實,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("血斛")){血斛 = addCountIntoVar(血斛,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("幽燈蕈")){幽燈蕈 = addCountIntoVar(幽燈蕈,temp_count,"LOCAL");}
            else if(temp_item.get(3).equals("珊瑚真珠")){珊瑚真珠 = addCountIntoVar(珊瑚真珠,temp_count,"LOCAL");}

            //add in 2021024 (RE)

        }

        if(temp_item.size() > 3){
            /** T-COMMON -> USING temp_count's pos 1-3*/
            if(temp_item.get(2).equals("歷戰的箭簇")){addCountIntoVar(歷戰的箭簇,temp_count,"T-COMMON");}
            if(temp_item.get(2).equals("禁咒繪卷")){addCountIntoVar(禁咒繪卷,temp_count,"T-COMMON");}
            if(temp_item.get(2).equals("攫金鴉印")){addCountIntoVar(攫金鴉印,temp_count,"T-COMMON");}
            if(temp_item.get(2).equals("不祥的面具")){addCountIntoVar(不祥的面具,temp_count,"T-COMMON");}
            if(temp_item.get(2).equals("尉官的徽記")){addCountIntoVar(尉官的徽記,temp_count,"T-COMMON");}
            if(temp_item.get(2).equals("原素花蜜")){addCountIntoVar(原素花蜜,temp_count,"T-COMMON");}
            if(temp_item.get(2).equals("史萊姆原漿")){addCountIntoVar(史萊姆原漿,temp_count,"T-COMMON");}
            if(temp_item.get(2).equals("名刀鐔")){addCountIntoVar(名刀鐔,temp_count,"T-COMMON");}
            //add in 20210910
            if(temp_item.get(2).equals("浮游晶化核")){addCountIntoVar(浮游晶化核,temp_count,"T-COMMON");}
            //add in 20211030
            if(temp_item.get(2).equals("隱獸鬼爪")){addCountIntoVar(隱獸鬼爪,temp_count,"COPY2");}

            /** T-BOOK -> USING temp_count's pos 0-2 */
            if(temp_item.get(1).equals("「自由」的哲學")){addCountIntoVar(自由_的哲學,temp_count,"T-BOOK");}
            if(temp_item.get(1).equals("「黃金」的哲學")){addCountIntoVar(黃金_的哲學,temp_count,"T-BOOK");}
            if(temp_item.get(1).equals("「抗爭」的哲學")){addCountIntoVar(抗爭_的哲學,temp_count,"T-BOOK");}
            if(temp_item.get(1).equals("「勤勞」的哲學")){addCountIntoVar(勤勞_的哲學,temp_count,"T-BOOK");}
            if(temp_item.get(1).equals("「詩文」的哲學")){addCountIntoVar(詩文_的哲學,temp_count,"T-BOOK");}
            if(temp_item.get(1).equals("「繁榮」的哲學")){addCountIntoVar(繁榮_的哲學,temp_count,"T-BOOK");}
            if(temp_item.get(1).equals("「風雅」的哲學")){addCountIntoVar(風雅_的哲學,temp_count,"T-BOOK");}
            if(temp_item.get(1).equals("「浮世」的哲學")){addCountIntoVar(浮世_的哲學,temp_count,"T-BOOK");}
            if(temp_item.get(1).equals("「天光」的哲學")){addCountIntoVar(天光_的哲學,temp_count,"T-BOOK");}

            if(temp_item.get(3).equals("北風之環")){北風之環 = addCountIntoVar(北風之環,temp_count,"T-BOSS");}
            if(temp_item.get(3).equals("東風的吐息")){東風的吐息 = addCountIntoVar(東風的吐息,temp_count,"T-BOSS");}
            if(temp_item.get(3).equals("東風之翎")){東風之翎 = addCountIntoVar(東風之翎,temp_count,"T-BOSS");}
            if(temp_item.get(3).equals("北風的魂匣")){北風的魂匣 = addCountIntoVar(北風的魂匣,temp_count,"T-BOSS");}
            if(temp_item.get(3).equals("東風之爪")){東風之爪 = addCountIntoVar(東風之爪,temp_count,"T-BOSS");}
            if(temp_item.get(3).equals("北風之尾")){北風之尾 = addCountIntoVar(北風之尾,temp_count,"T-BOSS");}
            if(temp_item.get(3).equals("魔王之刃·殘片")){魔王之刃_殘片 = addCountIntoVar(魔王之刃_殘片,temp_count,"T-BOSS");}
            if(temp_item.get(3).equals("吞天之鯨·只角")){吞天之鯨_只角 = addCountIntoVar(吞天之鯨_只角,temp_count,"T-BOSS");}
            if(temp_item.get(3).equals("武煉之魂·孤影")){武煉之魂_孤影 = addCountIntoVar(武煉之魂_孤影,temp_count,"T-BOSS");}
            if(temp_item.get(3).equals("龍王之冕")){龍王之冕 = addCountIntoVar(龍王之冕,temp_count,"T-BOSS");}
            if(temp_item.get(3).equals("血玉之枝")){血玉之枝 = addCountIntoVar(血玉之枝,temp_count,"T-BOSS");}
            if(temp_item.get(3).equals("鎏金之鱗")){鎏金之鱗 = addCountIntoVar(鎏金之鱗,temp_count,"T-BOSS");}
            //add in 20210910
            if(temp_item.get(3).equals("熔毀之刻")){熔毀之刻 = addCountIntoVar(熔毀之刻,temp_count,"T-BOSS");}
            if(temp_item.get(3).equals("灰燼之心")){灰燼之心 = addCountIntoVar(灰燼之心,temp_count,"T-BOSS");}
            if(temp_item.get(3).equals("獄火之蝶")){獄火之蝶 = addCountIntoVar(獄火之蝶,temp_count,"T-BOSS");}

        }

    }


    /**EDIT WHEN ADD NEW ITEMS*/
    public void FindWeaponItemByName(ArrayList<String> temp_item, ArrayList<Integer> temp_count){

        System.out.println(temp_item);

        /** COPY1 -> */
        if(temp_item.get(1).equals("漆黑隕鐵的一塊")){addCountIntoVar(漆黑隕鐵的一塊,temp_count,"COPY1");}
        if(temp_item.get(1).equals("鳴神御靈的勇武")){addCountIntoVar(鳴神御靈的勇武,temp_count,"COPY1");}
        if(temp_item.get(1).equals("遠海夷地的金枝")){addCountIntoVar(遠海夷地的金枝,temp_count,"COPY1");}
        if(temp_item.get(1).equals("凜風奔狼的懷鄉")){addCountIntoVar(凜風奔狼的懷鄉,temp_count,"COPY1");}
        if(temp_item.get(1).equals("高塔孤王的碎夢")){addCountIntoVar(高塔孤王的碎夢,temp_count,"COPY1");}
        if(temp_item.get(1).equals("霧海雲間的轉還")){addCountIntoVar(霧海雲間的轉還,temp_count,"COPY1");}
        if(temp_item.get(1).equals("獅牙鬥士的理想")){addCountIntoVar(獅牙鬥士的理想,temp_count,"COPY1");}
        if(temp_item.get(1).equals("孤雲寒林的神體")){addCountIntoVar(孤雲寒林的神體,temp_count,"COPY1");}
        if(temp_item.get(1).equals("今昔劇畫的鬼人")){addCountIntoVar(今昔劇畫的鬼人,temp_count,"COPY1");}

        /** COPY2 -> */
        if(temp_item.get(2).equals("混沌真眼")){addCountIntoVar(混沌真眼,temp_count,"COPY2");}
        if(temp_item.get(2).equals("混沌爐心")){addCountIntoVar(混沌爐心,temp_count,"COPY2");}
        if(temp_item.get(2).equals("石化的骨片")){addCountIntoVar(石化的骨片,temp_count,"COPY2");}
        if(temp_item.get(2).equals("霧虛燈芯")){addCountIntoVar(霧虛燈芯,temp_count,"COPY2");}
        if(temp_item.get(2).equals("督察長祭刀")){addCountIntoVar(督察長祭刀,temp_count,"COPY2");}
        if(temp_item.get(2).equals("黑晶號角")){addCountIntoVar(黑晶號角,temp_count,"COPY2");}
        if(temp_item.get(2).equals("地脈的新芽")){addCountIntoVar(地脈的新芽,temp_count,"COPY2");}
        if(temp_item.get(2).equals("偏光棱鏡")){addCountIntoVar(偏光棱鏡,temp_count,"COPY2");}
        //add in 20211030
        if(temp_item.get(2).equals("隱獸鬼爪")){addCountIntoVar(隱獸鬼爪,temp_count,"COPY2");}


        /** COMMON -> */
        if(temp_item.get(3).equals("歷戰的箭簇")){addCountIntoVar(歷戰的箭簇,temp_count,"W-COMMON");}
        if(temp_item.get(3).equals("禁咒繪卷")){addCountIntoVar(禁咒繪卷,temp_count,"W-COMMON");}
        if(temp_item.get(3).equals("攫金鴉印")){addCountIntoVar(攫金鴉印,temp_count,"W-COMMON");}
        if(temp_item.get(3).equals("不祥的面具")){addCountIntoVar(不祥的面具,temp_count,"W-COMMON");}
        if(temp_item.get(3).equals("尉官的徽記")){addCountIntoVar(尉官的徽記,temp_count,"W-COMMON");}
        if(temp_item.get(3).equals("原素花蜜")){addCountIntoVar(原素花蜜,temp_count,"W-COMMON");}
        if(temp_item.get(3).equals("史萊姆原漿")){addCountIntoVar(史萊姆原漿,temp_count,"W-COMMON");}
        if(temp_item.get(3).equals("名刀鐔")){addCountIntoVar(名刀鐔,temp_count,"W-COMMON");}
        //add in 20210910
        if(temp_item.get(3).equals("浮游晶化核")){addCountIntoVar(浮游晶化核,temp_count,"W-COMMON");}

    }

    /**
     * @param ITEM -> ArrayList of item STORE VAR
     * @param temp_count -> ArrayList
     * @param XPR -> TYPE -> TELL FUN Total pos have in the ITEM
     */
    public void addCountIntoVar(ArrayList<Integer> ITEM, ArrayList<Integer> temp_count, String XPR){
        if(XPR.equals("CRYSTAL")) {
            for (int x = 0; x < 4; x++) {
                ITEM.set(x, ITEM.get(x)+temp_count.get(x));
            }
        }else if(XPR.equals("COMMON")){
            for (int x = 0 ; x < 3 ; x++){
                ITEM.set(x,ITEM.get(x)+temp_count.get(x+5));
            }
        }else if(XPR.equals("T-BOOK")){
            for (int x = 0 ; x < 3 ; x++){
                ITEM.set(x,ITEM.get(x)+temp_count.get(x));
            }
        }else if(XPR.equals("T-COMMON")){
            for (int x = 0 ; x < 3 ; x++){
                System.out.println("SYS : "+temp_count);
                ITEM.set(x,ITEM.get(x)+temp_count.get(x+3));
            }
        }else if(XPR.equals("COPY1")){
            for (int x = 0 ; x < 4 ; x++){
                System.out.println("SYS : "+temp_count);
                ITEM.set(x,ITEM.get(x)+temp_count.get(x));
            }
        }else if(XPR.equals("COPY2")){
            for (int x = 0 ; x < 3 ; x++){
                System.out.println("SYS : "+temp_count);
                ITEM.set(x,ITEM.get(x)+temp_count.get(x+4));
            }
        }else if(XPR.equals("W-COMMON")){
            for (int x = 0 ; x < 3 ; x++){
                System.out.println("SYS : "+temp_count);
                ITEM.set(x,ITEM.get(x)+temp_count.get(x+7));
            }
        }
    }

    /**
     *
     * @param ITEM -> Integer of item STORE VAR
     * @param temp_count -> ArrayList
     * @param XPR -> TYPE -> TELL FUN Total pos have in the ITEM
     */
    public int addCountIntoVar(int ITEM, ArrayList<Integer> temp_count, String XPR){
        if(XPR.equals("BOSS")) {
                ITEM = ITEM + temp_count.get(8);
                return ITEM;
        }else if(XPR.equals("T-BOSS")) {
                ITEM = ITEM + temp_count.get(6);
                return ITEM;
        }else if(XPR.equals("LOCAL")) {
                ITEM = ITEM + temp_count.get(4);
                return ITEM;
        }
        return ITEM;
    }


    public void char_readJSON () {
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
        artifact_calculate();

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

    public String prettyCount(Number number) {
        char[] suffix = {' ', 'K', 'M', 'G', 'T', 'P', 'E', 'Z', 'Y'};
        long numValue = number.longValue();
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
        Log.wtf("DB","saveToDB !");
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db ;

        /**
         * Database Char Part
         */

        Log.wtf("DB",String.valueOf(NameList.size()));
        Log.wtf("DB",String.valueOf(WeaponNameList.size()));
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

                        " WHERE artifactName = \""+ArtifactNameList.get(x)+"\";");
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

}
