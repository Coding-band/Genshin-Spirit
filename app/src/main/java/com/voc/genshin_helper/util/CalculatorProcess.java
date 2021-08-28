package com.voc.genshin_helper.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.gridlayout.widget.GridLayout;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.Characters;
import com.voc.genshin_helper.data.CharactersAdapter;
import com.voc.genshin_helper.data.Characters_Rss;
import com.voc.genshin_helper.data.ScreenSizeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Package com.voc.genshin_helper.data was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2020 Xectorda 版權所有
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

    /** COMMON */ /**[牢固的箭簇,銳利的箭簇,歷戰的箭簇]*/
    ArrayList<Integer> 歷戰的箭簇 = new ArrayList<Integer>();
    ArrayList<Integer> 禁咒繪卷 = new ArrayList<Integer>();
    ArrayList<Integer> 攫金鴉印 = new ArrayList<Integer>();
    ArrayList<Integer> 不祥的面具 = new ArrayList<Integer>();
    ArrayList<Integer> 尉官的徽記 = new ArrayList<Integer>();
    ArrayList<Integer> 原素花蜜 = new ArrayList<Integer>();
    ArrayList<Integer> 史萊姆原漿 = new ArrayList<Integer>();
    ArrayList<Integer> 名刀鐔 = new ArrayList<Integer>();

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

    /** T-BOSS ASC*/

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

    int 智識之冕 = 0;

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
    Characters_Rss characters_rss;

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

    ArrayList<String> asc_temp_item = new ArrayList<String>();
    ArrayList<Integer> asc_temp_count = new ArrayList<Integer>();
    ArrayList<String> skill1_temp_item = new ArrayList<String>();
    ArrayList<Integer> skill1_temp_count = new ArrayList<Integer>();
    ArrayList<String> skill2_temp_item = new ArrayList<String>();
    ArrayList<Integer> skill2_temp_count = new ArrayList<Integer>();
    ArrayList<String> skill3_temp_item = new ArrayList<String>();
    ArrayList<Integer> skill3_temp_count = new ArrayList<Integer>();


    public void setVP(ViewPager viewPager, View viewPager3) {
        this.viewPager = viewPager;
        this.cal_view = viewPager3;
    }

    public void setup(Context context ,ArrayList<String> NameList, ArrayList<Integer> BeforeLvlList, ArrayList<Integer> AfterLvlList, ArrayList<Integer> BeforeBreakLvlList, ArrayList<Integer> AfterBreakLvlList, ArrayList<Integer> BeforeSkill1LvlList, ArrayList<Integer> AfterSkill1LvlList, ArrayList<Integer> BeforeSkill2LvlList, ArrayList<Integer> AfterSkill2LvlList, ArrayList<Integer> BeforeSkill3LvlList, ArrayList<Integer> AfterSkill3LvlList, ArrayList<Boolean> IsCal, ArrayList<Boolean> BeforeBreakUPLvlList, ArrayList<Boolean> AfterBreakUPLvlList) {
        this.context = context;
        this.NameList = NameList;
        System.out.println(NameList);
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

        for (int k = 0 ; k < 9 ; k ++){asc_temp_count.add(0);}
        for (int k = 0 ; k < 8 ; k ++){skill1_temp_count.add(0);}
        for (int k = 0 ; k < 8 ; k ++){skill2_temp_count.add(0);}
        for (int k = 0 ; k < 8 ; k ++){skill3_temp_count.add(0);}

        characters_rss = new Characters_Rss();

        readJSON();
    }

    public void calculate () {
        Log.wtf("HEY","TOMMY");
        for (int x = 0 ; x < NameList.size() ; x ++){
            /** CAL EXP */
            int exp_grade0 = 0,exp_grade1= 0,exp_grade2= 0,exp_grade3= 0,exp_grade4= 0,exp_grade5= 0,exp_grade6 = 0;
            for (int y = BeforeLvlList.get(x) ; y < AfterLvlList.get(x) ; y++){
                if(y<=20){exp_grade0 = exp_grade0 + lvlEXPList.get(y-1);}
                else if(y>20 && y <= 40){exp_grade1 = exp_grade1 + lvlEXPList.get(y-1);}
                else if(y>40 && y <= 50){exp_grade2 = exp_grade2 + lvlEXPList.get(y-1);}
                else if(y>50 && y <= 60){exp_grade3 = exp_grade3 + lvlEXPList.get(y-1);}
                else if(y>60 && y <= 70){exp_grade4 = exp_grade4 + lvlEXPList.get(y-1);}
                else if(y>70 && y <= 80){exp_grade5 = exp_grade5 + lvlEXPList.get(y-1);}
                else if(y>80 && y <= 90){exp_grade6 = exp_grade6 + lvlEXPList.get(y-1);}
                mora.add(moraEXPList.get(y-1));
                // LATER WILL TURN TO EXP_BIG / SMALL / MID ONE BY ONE
            }

            part0_exp.add(exp_grade0);
            part1_exp.add(exp_grade1);
            part2_exp.add(exp_grade2);
            part3_exp.add(exp_grade3);
            part4_exp.add(exp_grade4);
            part5_exp.add(exp_grade5);
            part6_exp.add(exp_grade6);
            getEXPItemCount(x,part0_exp);
            getEXPItemCount(x,part1_exp);
            getEXPItemCount(x,part2_exp);
            getEXPItemCount(x,part3_exp);
            getEXPItemCount(x,part4_exp);
            getEXPItemCount(x,part5_exp);
            getEXPItemCount(x,part6_exp);

            Log.wtf("HEY","PAIMON");

            /** CAL ASC */
            int beforeUP = 0 , afterUP = 0;
            if(BeforeBreakUPLvlList.get(x) == true){beforeUP = 1;}
            if(AfterBreakUPLvlList.get(x) == true){afterUP = 1;}

            System.out.println("SS"+BeforeBreakLvlList);
            System.out.println("RR"+AfterBreakLvlList);

            for (int y = BeforeBreakLvlList.get(x)+beforeUP ; y < AfterBreakLvlList.get(x)+afterUP+1 ; y ++){

                int z = getRealPosByName(NameList.get(x));
                asc_temp_item.add(nameREQUIREList.get(z));
                asc_temp_item.add(crystalREQUIREList.get(z));
                asc_temp_item.add(bossREQUIREList.get(z));
                asc_temp_item.add(localREQUIREList.get(z));
                asc_temp_item.add(commonREQUIREList.get(z));

                asc_temp_count.set(0,asc_temp_count.get(0) + silverASCList.get(y));
                asc_temp_count.set(1,asc_temp_count.get(1) +fragASCList.get(y));
                asc_temp_count.set(2,asc_temp_count.get(2) +chunkASCList.get(y));
                asc_temp_count.set(3,asc_temp_count.get(3) +gemASCList.get(y));
                asc_temp_count.set(4,asc_temp_count.get(4) +localASCList.get(y));
                asc_temp_count.set(5,asc_temp_count.get(5) +com1ASCList.get(y));
                asc_temp_count.set(6,asc_temp_count.get(6) +com2ASCList.get(y));
                asc_temp_count.set(7,asc_temp_count.get(7) +com3ASCList.get(y));
                asc_temp_count.set(8,asc_temp_count.get(8) +bossASCList.get(y));
                mora.add(moraASCList.get(y));
                System.out.println("KAMI "+asc_temp_count);

            }
            FindItemByName(asc_temp_item,asc_temp_count);


            /** CAL SKILL1 */

            for (int y = BeforeSkill1LvlList.get(x) ; y < AfterSkill1LvlList.get(x) ; y ++){

                int z = getRealPosByName(NameList.get(x));
                skill1_temp_item.add(nameREQUIREList.get(z));
                skill1_temp_item.add(bookREQUIREList.get(z));
                skill1_temp_item.add(commonREQUIREList.get(z));
                skill1_temp_item.add(t_bossREQUIREList.get(z));

                skill1_temp_count.set(0,skill1_temp_count.get(0) + teachSKILLList.get(y));
                skill1_temp_count.set(1,skill1_temp_count.get(1) + guideSKILLList.get(y));
                skill1_temp_count.set(2,skill1_temp_count.get(2) + phiSKILLList.get(y));
                skill1_temp_count.set(3,skill1_temp_count.get(3) + com1SKILLList.get(y));
                skill1_temp_count.set(4,skill1_temp_count.get(4) + com2SKILLList.get(y));
                skill1_temp_count.set(5,skill1_temp_count.get(5) + com3SKILLList.get(y));
                skill1_temp_count.set(6,skill1_temp_count.get(6) + bossSKILLList.get(y));

                if(y == 10) {智識之冕 = 智識之冕 + 1;}

                mora.add(moraSkillList.get(x));
                FindItemByName(skill1_temp_item,skill1_temp_count);
            }

            for (int y = BeforeSkill2LvlList.get(x) ; y < AfterSkill2LvlList.get(x) ; y ++){

                int z = getRealPosByName(NameList.get(x));
                skill2_temp_item.add(nameREQUIREList.get(z));
                skill2_temp_item.add(bookREQUIREList.get(z));
                skill2_temp_item.add(commonREQUIREList.get(z));
                skill2_temp_item.add(t_bossREQUIREList.get(z));

                skill2_temp_count.set(0,skill2_temp_count.get(0) + teachSKILLList.get(y));
                skill2_temp_count.set(1,skill2_temp_count.get(1) + guideSKILLList.get(y));
                skill2_temp_count.set(2,skill2_temp_count.get(2) + phiSKILLList.get(y));
                skill2_temp_count.set(3,skill2_temp_count.get(3) + com1SKILLList.get(y));
                skill2_temp_count.set(4,skill2_temp_count.get(4) + com2SKILLList.get(y));
                skill2_temp_count.set(5,skill2_temp_count.get(5) + com3SKILLList.get(y));
                skill2_temp_count.set(6,skill2_temp_count.get(6) + bossSKILLList.get(y));

                if(y == 10) {智識之冕 = 智識之冕 + 1;}
                mora.add(moraSkillList.get(x));
                FindItemByName(skill2_temp_item,skill2_temp_count);
            }

            for (int y = BeforeSkill3LvlList.get(x) ; y < AfterSkill3LvlList.get(x) ; y ++){

                int z = getRealPosByName(NameList.get(x));
                skill3_temp_item.add(nameREQUIREList.get(z));
                skill3_temp_item.add(bookREQUIREList.get(z));
                skill3_temp_item.add(commonREQUIREList.get(z));
                skill3_temp_item.add(t_bossREQUIREList.get(z));

                skill3_temp_count.set(0,skill3_temp_count.get(0) + teachSKILLList.get(y));
                skill3_temp_count.set(1,skill3_temp_count.get(1) + guideSKILLList.get(y));
                skill3_temp_count.set(2,skill3_temp_count.get(2) + phiSKILLList.get(y));
                skill3_temp_count.set(3,skill3_temp_count.get(3) + com1SKILLList.get(y));
                skill3_temp_count.set(4,skill3_temp_count.get(4) + com2SKILLList.get(y));
                skill3_temp_count.set(5,skill3_temp_count.get(5) + com3SKILLList.get(y));
                skill3_temp_count.set(6,skill3_temp_count.get(6) + bossSKILLList.get(y));

                if(y == 10) {智識之冕 = 智識之冕 + 1;}
                mora.add(moraSkillList.get(x));
                FindItemByName(skill3_temp_item,skill3_temp_count);
            }
            resultShow();
        }
    }

    public int getRealPosByName(String s) {
        for (int x = 0 ; x < nameREQUIREList.size() ; x++){
            if(nameREQUIREList.get(x).equals(s)){
                return x;
            }
        }
        return 0;
    }

    public void resultShow () {
        /** CHARACTER ICON*/
        GridLayout gridLayout = new GridLayout(context);
        gridLayout = viewPager.findViewById(R.id.result_char_gl);
        gridLayout.removeAllViews();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        ImageView item_img;
        TextView tv;
        final int radius = 180;
        final int margin = 4;
        //gridLayout.setRowCount(5);
        //        gridLayout.setColumnCount((int)(NameList.size()/5)+1);
        //        int width = (int) ((ScreenSizeUtils.getInstance(context).getScreenWidth()-64)/5);
        int column = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth()-128)/128;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);
        for (int x = 0, c = 0, r = 0; NameList != null && x < NameList.size(); x++, c++) {
            if(c == column) { c = 0;r++; }
            item_img = new ImageView(context);
            Picasso.get()
                    .load (characters_rss.getCharByName(NameList.get(x))[3])
                    .transform(transformation)
                    .resize(128,128)
                    .error (R.drawable.paimon_lost)
                    .into (item_img);
            gridLayout.addView(item_img, x);
            item_img.setAdjustViewBounds(true);
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER_HORIZONTAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            item_img.setLayoutParams (param);


        }

        /** CRYSTAL*/
        String[] crystal_temp = new String[]{"燃願瑪瑙碎屑","燃願瑪瑙斷片","燃願瑪瑙塊","燃願瑪瑙","滌淨青金碎屑","滌淨青金斷片","滌淨青金塊","滌淨青金","最勝紫晶碎屑","最勝紫晶斷片","最勝紫晶塊","最勝紫晶","哀敘冰玉碎屑","哀敘冰玉斷片","哀敘冰玉塊","哀敘冰玉","自在松石碎屑","自在松石斷片","自在松石塊","自在松石","堅牢黃玉碎屑","堅牢黃玉斷片","堅牢黃玉塊","堅牢黃玉"};
        int[] crystal_temp_cnt = new int[]{燃願瑪瑙.get(0),燃願瑪瑙.get(1),燃願瑪瑙.get(2),燃願瑪瑙.get(3),滌淨青金.get(0),滌淨青金.get(1),滌淨青金.get(2),滌淨青金.get(3),最勝紫晶.get(0),最勝紫晶.get(1),最勝紫晶.get(2),最勝紫晶.get(3),哀敘冰玉.get(0),哀敘冰玉.get(1),哀敘冰玉.get(2),哀敘冰玉.get(3),自在松石.get(0),自在松石.get(1),自在松石.get(2),自在松石.get(3),堅牢黃玉.get(0),堅牢黃玉.get(1),堅牢黃玉.get(2),堅牢黃玉.get(3)};
        gridLayout = viewPager.findViewById(R.id.result_crystal_gl);
        gridLayout.removeAllViews();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; crystal_temp != null && x < crystal_temp.length; x++, c++) {
            if(c == column) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
            TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
            Picasso.get()
                    .load (characters_rss.getItemIcoByName(crystal_temp[x],context))
                    .transform(transformation)
                    .resize(128,128)
                    .error (R.drawable.paimon_lost)
                    .into (item_cal_img);
            item_cal_tv.setText(prettyCount(crystal_temp_cnt[x]));
            item_cal_img.setAdjustViewBounds(true);
            if(crystal_temp_cnt[x] > 0){
                gridLayout.setVisibility(View.VISIBLE);
                TextView result_crystal_title = viewPager.findViewById(R.id.result_crystal_title);
                result_crystal_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view, x);
            }
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER_HORIZONTAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }

        /** BOSS */
        String[] boss_temp = new String[]{"常燃火種","淨水之心","雷光棱鏡","極寒之核","颶風之種","玄岩之塔","未熟之玉","晶凝之華","魔偶機心","恒常機關之心","陰燃之珠"};
        int[] boss_temp_cnt = new int[]{常燃火種,淨水之心,雷光棱鏡,極寒之核,颶風之種,玄岩之塔,未熟之玉,晶凝之華,魔偶機心,恒常機關之心,陰燃之珠};
        gridLayout = viewPager.findViewById(R.id.result_boss_gl);
        gridLayout.removeAllViews();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; boss_temp != null && x < boss_temp.length; x++, c++) {
            if(c == column) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
            TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
            Picasso.get()
                    .load (characters_rss.getItemIcoByName(boss_temp[x],context))
                    .transform(transformation)
                    .resize(128,128)
                    .error (R.drawable.paimon_lost)
                    .into (item_cal_img);
            item_cal_tv.setText(prettyCount(boss_temp_cnt[x]));
            item_cal_img.setAdjustViewBounds(true);
            if(boss_temp_cnt[x] > 0){
                gridLayout.setVisibility(View.VISIBLE);
                TextView result_boss_title = viewPager.findViewById(R.id.result_boss_title);
                result_boss_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view, x);
            }
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER_HORIZONTAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }

        /** WEEK-BOSS */
        String[] week_boss_temp = new String[]{"北風之環","東風的吐息","東風之翎","北風的魂匣","東風之爪","北風之尾","魔王之刃·殘片","吞天之鯨·只角","武煉之魂·孤影","龍王之冕","血玉之枝","鎏金之鱗"};
        int[] week_boss_temp_cnt = new int[]{北風之環,東風的吐息,東風之翎,北風的魂匣,東風之爪,北風之尾,魔王之刃_殘片,吞天之鯨_只角,武煉之魂_孤影,龍王之冕,血玉之枝,鎏金之鱗};
        gridLayout = viewPager.findViewById(R.id.result_weekboss_gl);
        gridLayout.removeAllViews();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; week_boss_temp != null && x < week_boss_temp.length; x++, c++) {
            if(c == column) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
            TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
            Picasso.get()
                    .load (characters_rss.getItemIcoByName(week_boss_temp[x],context))
                    .transform(transformation)
                    .resize(128,128)
                    .error (R.drawable.paimon_lost)
                    .into (item_cal_img);
            item_cal_tv.setText(prettyCount(week_boss_temp_cnt[x]));
            item_cal_img.setAdjustViewBounds(true);
            if(week_boss_temp_cnt[x] > 0){
                gridLayout.setVisibility(View.VISIBLE);
                TextView result_weekboss_title = viewPager.findViewById(R.id.result_weekboss_title);
                result_weekboss_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view, x);
            }
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER_HORIZONTAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }

        /** LOCAL */
        String[] local_temp = new String[]{"小燈草","慕風蘑菇","夜泊石","風車菊","石珀","蒲公英籽","嘟嘟蓮","落落莓","琉璃百合","琉璃袋","鉤鉤果","塞西莉亞花","絕雲椒椒","霓裳花","星螺","清心","海靈芝","緋櫻繡球","鳴草","晶化骨髓"};
        int[] local_temp_cnt = new int[]{小燈草,慕風蘑菇,夜泊石,風車菊,石珀,蒲公英籽,嘟嘟蓮,落落莓,琉璃百合,琉璃袋,鉤鉤果,塞西莉亞花,絕雲椒椒,霓裳花,星螺,清心,海靈芝,緋櫻繡球,鳴草,晶化骨髓};
        gridLayout = viewPager.findViewById(R.id.result_local_gl);
        gridLayout.removeAllViews();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; local_temp != null && x < local_temp.length; x++, c++) {
            if(c == column) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
            TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
            Picasso.get()
                    .load (characters_rss.getItemIcoByName(local_temp[x],context))
                    .transform(transformation)
                    .resize(128,128)
                    .error (R.drawable.paimon_lost)
                    .into (item_cal_img);
            item_cal_tv.setText(prettyCount(local_temp_cnt[x]));
            item_cal_img.setAdjustViewBounds(true);
            if(local_temp_cnt[x] > 0){
                gridLayout.setVisibility(View.VISIBLE);
                TextView result_local_title = viewPager.findViewById(R.id.result_local_title);
                result_local_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view, x);
            }
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER_HORIZONTAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }

        /** COMMON */
        String[] common_temp = new String[]{"牢固的箭簇","銳利的箭簇","歷戰的箭簇","導能繪卷","封魔繪卷","禁咒繪卷","尋寶鴉印","藏銀鴉印","攫金鴉印","破損的面具","污穢的面具","不祥的面具","新兵的徽記","士官的徽記","尉官的徽記","騙騙花蜜","微光花蜜","原素花蜜","史萊姆凝液","史萊姆清","史萊姆原漿","破舊的刀鐔","影打刀鐔","名刀鐔"};
        int[] common_temp_cnt = new int[]{歷戰的箭簇.get(0),歷戰的箭簇.get(1),歷戰的箭簇.get(2),禁咒繪卷.get(0),禁咒繪卷.get(1),禁咒繪卷.get(2),攫金鴉印.get(0),攫金鴉印.get(1),攫金鴉印.get(2),不祥的面具.get(0),不祥的面具.get(1),不祥的面具.get(2),尉官的徽記.get(0),尉官的徽記.get(1),尉官的徽記.get(2),原素花蜜.get(0),原素花蜜.get(1),原素花蜜.get(2),史萊姆原漿.get(0),史萊姆原漿.get(1),史萊姆原漿.get(2),名刀鐔.get(0),名刀鐔.get(1),名刀鐔.get(2)};
        gridLayout = viewPager.findViewById(R.id.result_common_gl);
        gridLayout.removeAllViews();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; common_temp != null && x < common_temp.length; x++, c++) {
            if(c == column) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
            TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
            Picasso.get()
                    .load (characters_rss.getItemIcoByName(common_temp[x],context))
                    .transform(transformation)
                    .resize(128,128)
                    .error (R.drawable.paimon_lost)
                    .into (item_cal_img);
            item_cal_tv.setText(prettyCount(common_temp_cnt[x]));
            item_cal_img.setAdjustViewBounds(true);
            if(common_temp_cnt[x] > 0){
                gridLayout.setVisibility(View.VISIBLE);
                TextView result_common_title = viewPager.findViewById(R.id.result_common_title);
                result_common_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view, x);
            }
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER_HORIZONTAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }

        /** WEEKLY-MON-THUR-SUN */
        String[] weeklybk1_temp = new String[]{"「自由」的教導","「自由」的指引","「自由」的哲學","「繁榮」的教導","「繁榮」的指引","「繁榮」的哲學","「浮世」的教導","「浮世」的指引","「浮世」的哲學"};
        int[] weeklybk1_temp_cnt = new int[]{自由_的哲學.get(0),自由_的哲學.get(1),自由_的哲學.get(2),繁榮_的哲學.get(0),繁榮_的哲學.get(1),繁榮_的哲學.get(2),浮世_的哲學.get(0),浮世_的哲學.get(1),浮世_的哲學.get(2)};
        gridLayout = viewPager.findViewById(R.id.result_weeklybk1_gl);
        gridLayout.removeAllViews();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; weeklybk1_temp != null && x < weeklybk1_temp.length; x++, c++) {
            if(c == column) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
            TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
            Picasso.get()
                    .load (characters_rss.getItemIcoByName(weeklybk1_temp[x],context))
                    .transform(transformation)
                    .resize(128,128)
                    .error (R.drawable.paimon_lost)
                    .into (item_cal_img);
            item_cal_tv.setText(prettyCount(weeklybk1_temp_cnt[x]));
            item_cal_img.setAdjustViewBounds(true);
            if(weeklybk1_temp_cnt[x] > 0){
                gridLayout.setVisibility(View.VISIBLE);
                TextView result_weeklybk1_title = viewPager.findViewById(R.id.result_weeklybk1_title);
                result_weeklybk1_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view, x);
            }
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER_HORIZONTAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }

        /** WEEKLY-TUE-FRI-SUN */
        String[] weeklybk2_temp = new String[]{"「抗爭」的教導","「抗爭」的指引","「抗爭」的哲學","「勤勞」的教導","「勤勞」的指引","「勤勞」的哲學","「風雅」的教導","「風雅」的指引","「風雅」的哲學"};
        int[] weeklybk2_temp_cnt = new int[]{抗爭_的哲學.get(0),抗爭_的哲學.get(1),抗爭_的哲學.get(2),勤勞_的哲學.get(0),勤勞_的哲學.get(1),勤勞_的哲學.get(2),風雅_的哲學.get(0),風雅_的哲學.get(1),風雅_的哲學.get(2)};
        gridLayout = viewPager.findViewById(R.id.result_weeklybk2_gl);
        gridLayout.removeAllViews();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; weeklybk2_temp != null && x < weeklybk2_temp.length; x++, c++) {
            if(c == column) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
            TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
            Picasso.get()
                    .load (characters_rss.getItemIcoByName(weeklybk2_temp[x],context))
                    .transform(transformation)
                    .resize(128,128)
                    .error (R.drawable.paimon_lost)
                    .into (item_cal_img);
            item_cal_tv.setText(prettyCount(weeklybk2_temp_cnt[x]));
            item_cal_img.setAdjustViewBounds(true);
            if(weeklybk2_temp_cnt[x] > 0){
                gridLayout.setVisibility(View.VISIBLE);
                TextView result_weeklybk2_title = viewPager.findViewById(R.id.result_weeklybk2_title);
                result_weeklybk2_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view, x);
            }
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER_HORIZONTAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }

        /** WEEKLY-WED-SAT-SUN */
        String[] weeklybk3_temp = new String[]{"「黃金」的教導","「黃金」的指引","「黃金」的哲學","「詩文」的教導","「詩文」的指引","「詩文」的哲學","「天光」的教導","「天光」的指引","「天光」的哲學"};
        int[] weeklybk3_temp_cnt = new int[]{黃金_的哲學.get(0),黃金_的哲學.get(1),黃金_的哲學.get(2),詩文_的哲學.get(0),詩文_的哲學.get(1),詩文_的哲學.get(2),天光_的哲學.get(0),天光_的哲學.get(1),天光_的哲學.get(2)};
        gridLayout = viewPager.findViewById(R.id.result_weeklybk3_gl);
        gridLayout.removeAllViews();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; weeklybk3_temp != null && x < weeklybk3_temp.length; x++, c++) {
            if(c == column) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
            TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
            Picasso.get()
                    .load (characters_rss.getItemIcoByName(weeklybk3_temp[x],context))
                    .transform(transformation)
                    .resize(128,128)
                    .error (R.drawable.paimon_lost)
                    .into (item_cal_img);
            item_cal_tv.setText(prettyCount(weeklybk3_temp_cnt[x]));
            item_cal_img.setAdjustViewBounds(true);
            if(weeklybk3_temp_cnt[x] > 0){
                gridLayout.setVisibility(View.VISIBLE);
                TextView result_weeklybk3_title = viewPager.findViewById(R.id.result_weeklybk3_title);
                result_weeklybk3_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view, x);
            }
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER_HORIZONTAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }

        /** WEEKLY-WED-SAT-SUN */
        int mora_final = 0;
        for (int e = 0 ; e < mora.size() ; e ++){
            mora_final = mora_final + mora.get(e);
        }

        String[] other_temp = new String[]{"流浪者的經驗","冒險家的經驗","大英雄的經驗","摩拉","智識之冕"};
        int[] other_temp_cnt = new int[]{exp_small,exp_mid,exp_big,mora_final,智識之冕};
        gridLayout = viewPager.findViewById(R.id.result_other_gl);
        gridLayout.removeAllViews();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int x = 0, c = 0, r = 0; other_temp != null && x < other_temp.length; x++, c++) {
            if(c == column) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_cal_img, null);
            ImageView item_cal_img = view.findViewById(R.id.item_cal_img);
            TextView item_cal_tv = view.findViewById(R.id.item_cal_tv);
            Picasso.get()
                    .load (characters_rss.getItemIcoByName(other_temp[x],context))
                    .transform(transformation)
                    .resize(128,128)
                    .error (R.drawable.paimon_lost)
                    .into (item_cal_img);
            item_cal_tv.setText(prettyCount(other_temp_cnt[x]));
            item_cal_img.setAdjustViewBounds(true);
            if(other_temp_cnt[x] > 0){
                gridLayout.setVisibility(View.VISIBLE);
                TextView result_other_title = viewPager.findViewById(R.id.result_other_title);
                result_other_title.setVisibility(View.VISIBLE);
                gridLayout.addView(view, x);
            }
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER_HORIZONTAL);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }
    }

    public void getEXPItemCount(int pos, ArrayList<Integer> part_exp){
        int big0 = 0 , mid0 = 0 , small0 = 0;
        float temp1 = 0f , temp2 = 0f , temp3 = 0f;
        int part0 = part_exp.get(pos);

        if(part0 >= 20000) {
            big0 = (int) part0 / 20000;
            temp1 = (part0/20000 - big0)*20000;
            if(temp1 >= 5000){
                mid0 = (int) temp1/5000;
                temp2 = (temp1/5000 - mid0)*5000;
                if(temp2 >= 1000){
                    small0 = (int) temp2/1000;
                    temp3 = (temp2/1000 - small0)*1000;
                    if(temp3 >0){
                        small0 = small0 + 1;
                    }
                }else {
                    small0 = small0 + 1;
                }
            }else {
                if(temp1 >= 1000){
                    small0 = (int) temp1/1000;
                    temp2 = (temp1/1000 - small0)*1000;
                    if(temp2 >0){
                        small0 = small0 + 1;
                    }
                }else {
                    small0 = small0 + 1;
                }
            }
        }else if(part0 >= 5000){
            mid0 = (int) part0/5000;
            temp1 = (part0/5000 - mid0)*5000;
            if(temp1 >= 1000){
                small0 = (int) temp1/1000;
                temp2 = (temp1/1000 - small0)*1000;
                if(temp2 >0){
                    small0 = small0 + 1;
                }
            }else {
                small0 = small0 + 1;
            }
        }else {
            if(part0 >= 1000){
                small0 = (int) part0/1000;
                temp1 = (part0/1000 - small0)*1000;
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


    /**
     * @param temp_item
     * @param temp_count
     */
    public void FindItemByName(ArrayList<String> temp_item, ArrayList<Integer> temp_count){
        /** CRYSTAL -> USING temp_count's pos 0-3*/
        System.out.println("temp_item : "+temp_item);
        System.out.println("temp_count : "+temp_count);

        if(temp_item.get(1).equals("燃願瑪瑙")){addCountIntoVar(燃願瑪瑙,temp_count,"CRYSTAL");}
        if(temp_item.get(1).equals("滌淨青金")){addCountIntoVar(滌淨青金,temp_count,"CRYSTAL");}
        if(temp_item.get(1).equals("最勝紫晶")){addCountIntoVar(最勝紫晶,temp_count,"CRYSTAL");}
        if(temp_item.get(1).equals("哀敘冰玉")){addCountIntoVar(哀敘冰玉,temp_count,"CRYSTAL");}
        if(temp_item.get(1).equals("自在松石")){addCountIntoVar(自在松石,temp_count,"CRYSTAL");}
        if(temp_item.get(1).equals("堅牢黃玉")){addCountIntoVar(堅牢黃玉,temp_count,"CRYSTAL");}

        if(temp_item.size()>4){
            /** COMMON -> USING temp_count's pos 7-9*/
            if(temp_item.get(4).equals("歷戰的箭簇")){addCountIntoVar(歷戰的箭簇,temp_count,"COMMON");}
            if(temp_item.get(4).equals("禁咒繪卷")){addCountIntoVar(禁咒繪卷,temp_count,"COMMON");}
            if(temp_item.get(4).equals("攫金鴉印")){addCountIntoVar(攫金鴉印,temp_count,"COMMON");}
            if(temp_item.get(4).equals("不祥的面具")){addCountIntoVar(不祥的面具,temp_count,"COMMON");}
            if(temp_item.get(4).equals("尉官的徽記")){addCountIntoVar(尉官的徽記,temp_count,"COMMON");}
            if(temp_item.get(4).equals("原素花蜜")){addCountIntoVar(原素花蜜,temp_count,"COMMON");}
            if(temp_item.get(4).equals("史萊姆原漿")){addCountIntoVar(史萊姆原漿,temp_count,"COMMON");}
            if(temp_item.get(4).equals("名刀鐔")){addCountIntoVar(名刀鐔,temp_count,"COMMON");}
        }

        /** T-COMMON -> USING temp_count's pos 1-3*/
        if(temp_item.get(2).equals("歷戰的箭簇")){addCountIntoVar(歷戰的箭簇,temp_count,"T-COMMON");}
        if(temp_item.get(2).equals("禁咒繪卷")){addCountIntoVar(禁咒繪卷,temp_count,"T-COMMON");}
        if(temp_item.get(2).equals("攫金鴉印")){addCountIntoVar(攫金鴉印,temp_count,"T-COMMON");}
        if(temp_item.get(2).equals("不祥的面具")){addCountIntoVar(不祥的面具,temp_count,"T-COMMON");}
        if(temp_item.get(2).equals("尉官的徽記")){addCountIntoVar(尉官的徽記,temp_count,"T-COMMON");}
        if(temp_item.get(2).equals("原素花蜜")){addCountIntoVar(原素花蜜,temp_count,"T-COMMON");}
        if(temp_item.get(2).equals("史萊姆原漿")){addCountIntoVar(史萊姆原漿,temp_count,"T-COMMON");}
        if(temp_item.get(2).equals("名刀鐔")){addCountIntoVar(名刀鐔,temp_count,"T-COMMON");}

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

        /** BOSS -> USING temp_count's pos 8 */
        if(temp_item.get(1).equals("常燃火種")){addCountIntoVar(常燃火種,temp_count,"BOSS");}
        if(temp_item.get(1).equals("淨水之心")){addCountIntoVar(淨水之心,temp_count,"BOSS");}
        if(temp_item.get(1).equals("雷光棱鏡")){addCountIntoVar(雷光棱鏡,temp_count,"BOSS");}
        if(temp_item.get(1).equals("極寒之核")){addCountIntoVar(極寒之核,temp_count,"BOSS");}
        if(temp_item.get(1).equals("颶風之種")){addCountIntoVar(颶風之種,temp_count,"BOSS");}
        if(temp_item.get(1).equals("玄岩之塔")){addCountIntoVar(玄岩之塔,temp_count,"BOSS");}
        if(temp_item.get(1).equals("未熟之玉")){addCountIntoVar(未熟之玉,temp_count,"BOSS");}
        if(temp_item.get(1).equals("晶凝之華")){addCountIntoVar(晶凝之華,temp_count,"BOSS");}
        if(temp_item.get(1).equals("魔偶機心")){addCountIntoVar(魔偶機心,temp_count,"BOSS");}
        if(temp_item.get(1).equals("恒常機關之心")){addCountIntoVar(恒常機關之心,temp_count,"BOSS");}
        if(temp_item.get(1).equals("陰燃之珠")){addCountIntoVar(陰燃之珠,temp_count,"BOSS");}

    }

    /**
     * @param ITEM -> ArrayList of item STORE VAR
     * @param temp_count -> ArrayList
     * @param XPR -> TYPE -> TELL FUN Total pos have in the ITEM
     */
    public void addCountIntoVar(ArrayList<Integer> ITEM, ArrayList<Integer> temp_count, String XPR){
        if(XPR.equals("CRYSTAL")) {
            for (int x = 0; x < 4; x++) {
                System.out.println("XPR "+temp_count.get(x));
                ITEM.set(x, temp_count.get(x));
            }
        }else if(XPR.equals("COMMON")){
            for (int x = 0 ; x < 2 ; x++){
                ITEM.set(x,temp_count.get(x+7));
            }
        }else if(XPR.equals("T-BOOK")){
            for (int x = 0 ; x < 2 ; x++){
                ITEM.set(x,temp_count.get(x));
            }
        }else if(XPR.equals("T-COMMON")){
            for (int x = 0 ; x < 2 ; x++){
                ITEM.set(x,temp_count.get(x+1));
            }
        }
    }

    /**
     *
     * @param ITEM -> Integer of item STORE VAR
     * @param temp_count -> ArrayList
     * @param XPR -> TYPE -> TELL FUN Total pos have in the ITEM
     */
    public void addCountIntoVar(int ITEM, ArrayList<Integer> temp_count, String XPR){
        if(XPR.equals("BOSS")) {
            for (int x = 0; x < 3; x++) {
                ITEM = ITEM + temp_count.get(temp_count.get(8));
            }
        }
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
                return plus+new DecimalFormat("#,###").format(numValue);
            }}
        return plus+new DecimalFormat("###,###,###,###,###").format(numValue);
    }

}
