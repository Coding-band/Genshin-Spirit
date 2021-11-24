package com.voc.genshin_helper.util;/*
 * Package com.voc.genshin_helper.util.CalculatorBuff was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.gridlayout.widget.GridLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.Characters;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.ScreenSizeUtils;
import com.voc.genshin_helper.database.DataBaseHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Important Method
     */
    Context context;
    View viewPager;
    ItemRss item_rss;
    String dataSheetName;

    ArrayList<String> artifactBuffMainItem = new ArrayList<>(5);
    ArrayList<Integer> artifactBuffMainValue = new ArrayList<>(5);
    ArrayList<String> artifactBuffSec1Item = new ArrayList<>(5);
    ArrayList<Integer> artifactBuffSec1Value = new ArrayList<>(5);
    ArrayList<String> artifactBuffSec2Item = new ArrayList<>(5);
    ArrayList<Integer> artifactBuffSec2Value = new ArrayList<>(5);
    ArrayList<String> artifactBuffSec3Item = new ArrayList<>(5);
    ArrayList<Integer> artifactBuffSec3Value = new ArrayList<>(5);
    ArrayList<String> artifactBuffSec4Item = new ArrayList<>(5);
    ArrayList<Integer> artifactBuffSec4Value = new ArrayList<>(5);


    public void ui_setup (Context context, View viewPager){
        this.context = context;
        this.viewPager = viewPager;
        item_rss = new ItemRss();
    }

    public void weapon_setup(ArrayList<String> arrayList, ArrayList<Integer> arrayList2, ArrayList<Integer> arrayList3, ArrayList<Integer> arrayList4, ArrayList<Integer> arrayList5, ArrayList<Boolean> arrayList6, ArrayList<Boolean> arrayList7, ArrayList<Boolean> arrayList8, ArrayList<String> arrayList9, ArrayList<Integer> arrayList10, String arg) {
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

    public void artifact_setup(ArrayList<String> artifactChoosedNameList, ArrayList<Integer> artifactChoosedBeforeLvlList, ArrayList<Integer> artifactChoosedAfterLvlList, ArrayList<Boolean> artifactChoosedIsCal, ArrayList<String> artifactChoosedFollowList, ArrayList<Integer> artifactChoosedRare,ArrayList<String> artifactChoosedType, String arg) {
        this.artifactChoosedNameList = artifactChoosedNameList;
        this.artifactChoosedBeforeLvlList = artifactChoosedBeforeLvlList;
        this.artifactChoosedAfterLvlList = artifactChoosedAfterLvlList;
        this.artifactChoosedIsCal = artifactChoosedIsCal;
        this.artifactChoosedFollowList = artifactChoosedFollowList;
        this.artifactChoosedRare = artifactChoosedRare;
        this.artifactChoosedType = artifactChoosedType;
    }

    public void view_setup(){
        /** INIT of UI GONE*/
        GridLayout buff_ui_view = viewPager.findViewById(R.id.buff_ui_view);
        buff_ui_view.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        buff_ui_view.setColumnCount(1);
        buff_ui_view.removeAllViewsInLayout();
        for (int x = 0 ; x < charChoosedNameList.size() ; x++) {

            View view = View.inflate(context, R.layout.item_buff_card, null);

            ImageView buff_ui_char_icon = view.findViewById(R.id.buff_ui_char_icon);
            TextView buff_ui_char_name = view.findViewById(R.id.buff_ui_char_name);
            ImageView buff_ui_weapon_icon = view.findViewById(R.id.buff_ui_weapon_icon);
            TextView buff_ui_press_more = view.findViewById(R.id.buff_ui_press_more);

            final int radius = 180;
            final int margin = 4;
            final Transformation transformation = new RoundedCornersTransformation(radius, margin);


            if(charChoosedIsCal.get(x) == true) {
                /** Char */
                Picasso.get()
                        .load(item_rss.getCharByName(charChoosedNameList.get(x))[3])
                        .transform(transformation)
                        .resize(128, 128)
                        .error(R.drawable.paimon_lost)
                        .into(buff_ui_char_icon);
                buff_ui_char_name.setText(item_rss.getCharByName(charChoosedNameList.get(x))[1]);

                /** Weapon */
                String tmp_weapon_name = "unknown";
                for (int y = 0 ; y < weaponChoosedNameList.size() ; y ++){
                    if(weaponChoosedFollowList.get(y).equals(charChoosedNameList.get(x))){
                        tmp_weapon_name = weaponChoosedNameList.get(y);
                    }
                }

                Picasso.get()
                        .load(item_rss.getWeaponByName(tmp_weapon_name)[1])
                        .transform(transformation)
                        .resize(96, 96)
                        .error(R.drawable.paimon_lost)
                        .into(buff_ui_weapon_icon);

                /** Artifact */

                ArrayList<String> tmp_artifact_name = new ArrayList<String>();
                ArrayList<String> tmp_artifact_type = new ArrayList<String>();
                int[] tmp_artifact_id = new int[]{R.id.buff_ui_artifact_icon1,R.id.buff_ui_artifact_icon2,R.id.buff_ui_artifact_icon3,R.id.buff_ui_artifact_icon4,R.id.buff_ui_artifact_icon5};

                for (int y = 0 ; y < artifactChoosedNameList.size() ; y ++){
                    if(artifactChoosedFollowList.get(y).equals(charChoosedNameList.get(x))){
                        tmp_artifact_name.add(artifactChoosedNameList.get(y));
                        tmp_artifact_type.add(artifactChoosedType.get(y));
                    }
                }

                for (int y = 0 ; y < tmp_artifact_name.size() && y < 5 ; y ++){
                    ImageView img = view.findViewById(tmp_artifact_id[y]);
                    int tmp_artifact_type_id = 1;
                    if(tmp_artifact_type.get(y).equals("Flower")){
                        tmp_artifact_type_id = 4;
                    }else if(tmp_artifact_type.get(y).equals("Plume")){
                        tmp_artifact_type_id = 2;
                    }else if(tmp_artifact_type.get(y).equals("Sand")){
                        tmp_artifact_type_id = 5;
                    }else if(tmp_artifact_type.get(y).equals("Goblet")){
                        tmp_artifact_type_id = 1;
                    }else if(tmp_artifact_type.get(y).equals("Circlet")){
                        tmp_artifact_type_id = 3;
                    }

                    Log.wtf("tmp_artifact_name",tmp_artifact_name.get(y));
                    Log.wtf("tmp_artifact_type_id", String.valueOf(tmp_artifact_type_id));

                    Picasso.get()
                            .load(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(tmp_artifact_name.get(y)))[tmp_artifact_type_id])
                            .transform(transformation)
                            .resize(64, 64)
                            .error(R.drawable.paimon_lost)
                            .into(img);
                }

                int finalX = x;
                String finalTmp_weapon_name = tmp_weapon_name;
                buff_ui_press_more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.wtf("TT","OK");
                        buffPage(finalX, finalTmp_weapon_name,tmp_artifact_name,tmp_artifact_type);
                    }
                });

            }

            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.MATCH_PARENT;
            param.setGravity(Gravity.CENTER_VERTICAL);
            view.setLayoutParams (param);
            buff_ui_view.addView(view);


            Log.wtf("TTSS","SWE");
        }

    }

    public void buffPage(int k, String weapon_name, ArrayList<String> artifact_name, ArrayList<String> artifact_type){
        /** init */

        artifactBuffMainItem.clear();
        artifactBuffMainValue.clear();
        artifactBuffSec1Item.clear();
        artifactBuffSec1Value.clear();
        artifactBuffSec2Item.clear();
        artifactBuffSec2Value.clear();
        artifactBuffSec3Item.clear();
        artifactBuffSec3Value.clear();
        artifactBuffSec4Item.clear();
        artifactBuffSec4Value.clear();

        final int radius = 25;
        final int margin = 4;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);

        /** Method */
        String charName = charChoosedNameList.get(k);

        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.fragment_buff_page, null);
        // Char_Info
        ImageView buff_char_icon = view.findViewById(R.id.buff_char_icon);
        ImageView buff_char_element = view.findViewById(R.id.buff_char_element);
        TextView buff_char_name = view.findViewById(R.id.buff_char_name);
        TextView buff_char_lvl = view.findViewById(R.id.buff_char_lvl);

        // Char_BUFF_Part
        TextView buff_char_atk_value = view.findViewById(R.id.buff_char_atk_value);
        TextView buff_char_hp_value = view.findViewById(R.id.buff_char_hp_value);
        TextView buff_char_def_value = view.findViewById(R.id.buff_char_def_value);
        TextView buff_char_crit_rate_value = view.findViewById(R.id.buff_char_crit_rate_value);
        TextView buff_char_crit_dmg_value = view.findViewById(R.id.buff_char_crit_dmg_value);
        TextView buff_char_other_value = view.findViewById(R.id.buff_char_other_value);
        TextView buff_char_other_name = view.findViewById(R.id.buff_char_other_name);

        // Weapon_Info
        ImageView buff_weapon_icon = view.findViewById(R.id.buff_weapon_icon);
        TextView buff_weapon_name = view.findViewById(R.id.buff_weapon_name);
        TextView buff_weapon_lvl = view.findViewById(R.id.buff_weapon_lvl);

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
        TextView buff_artifact_main_value = view.findViewById(R.id.buff_artifact_main_value);
        TextView buff_artifact_main_name = view.findViewById(R.id.buff_artifact_main_name);
        TextView buff_artifact_sec1_value = view.findViewById(R.id.buff_artifact_sec1_value);
        TextView buff_artifact_sec1_name = view.findViewById(R.id.buff_artifact_sec1_name);
        TextView buff_artifact_sec2_value = view.findViewById(R.id.buff_artifact_sec2_value);
        TextView buff_artifact_sec2_name = view.findViewById(R.id.buff_artifact_sec2_name);
        TextView buff_artifact_sec3_value = view.findViewById(R.id.buff_artifact_sec3_value);
        TextView buff_artifact_sec3_name = view.findViewById(R.id.buff_artifact_sec3_name);
        TextView buff_artifact_sec4_value = view.findViewById(R.id.buff_artifact_sec4_value);
        TextView buff_artifact_sec4_name = view.findViewById(R.id.buff_artifact_sec4_name);
        View buff_tab_view = view.findViewById(R.id.buff_tab_view);

        /** CHAR_INFO_SET_DATA*/
        Picasso.get()
                .load (item_rss.getCharByName(charName)[3])
                .transform(transformation)
                .fit()
                .error (R.drawable.paimon_full)
                .into (buff_char_icon);

        String name ,element = "Anemo";

        String json_base = LoadData("db/char/char_list.json");
        //Get data from JSON

        try {
            JSONArray array = new JSONArray(json_base);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                name = object.getString("name");
                if(name.equals(charName)){
                    element = object.getString("element");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        switch (element) {
            case "Anemo": buff_char_element.setImageResource(R.drawable.anemo);buff_char_icon.setBackgroundResource(R.drawable.bg_anemo_bg);break;
            case "Cryo": buff_char_element.setImageResource(R.drawable.cryo);buff_char_icon.setBackgroundResource(R.drawable.bg_cryo_bg);break;
            case "Electro": buff_char_element.setImageResource(R.drawable.electro);buff_char_icon.setBackgroundResource(R.drawable.bg_electro_bg);break;
            case "Geo": buff_char_element.setImageResource(R.drawable.geo);buff_char_icon.setBackgroundResource(R.drawable.bg_geo_bg);break;
            case "Hydro": buff_char_element.setImageResource(R.drawable.hydro);buff_char_icon.setBackgroundResource(R.drawable.bg_anemo_char);break;
            case "Pyro": buff_char_element.setImageResource(R.drawable.pyro);buff_char_icon.setBackgroundResource(R.drawable.bg_pyro_bg);break;
            case "Dendro": buff_char_element.setImageResource(R.drawable.dendro);buff_char_icon.setBackgroundResource(R.drawable.bg_dendro_bg);break;
        }

        buff_char_name.setText(item_rss.getLocaleName(charName,context));
        buff_char_lvl.setText(context.getString(R.string.curr_lvl)+" "+String.valueOf(charChoosedAfterLvlList.get(k)));


        /** WEAPON_INFO_SET_DATA*/

        Picasso.get()
                .load (item_rss.getWeaponByName(weapon_name)[1])
                .transform(transformation)
                .fit()
                .error (R.drawable.paimon_full)
                .into (buff_weapon_icon);

        buff_weapon_name.setText(context.getString(item_rss.getWeaponByName(weapon_name)[0]));
        buff_weapon_lvl.setText(context.getString(R.string.aim_lvl)+" "+weaponChoosedAfterLvlList.get(k));


        /** ARTIFACT_ICON_SET_DATA*/

        // 0     , 1    , 2   , 3     , 4
        // Flower, Plume, Sand, Goblet, Circlet
        String[] tmp_artifact_sort = new String[5];
        int[] tmp_artifact_order = new int[] {4,2,5,1,3};

        for (int x =0 ; x < 5 ; x ++){
            switch (artifact_type.get(x)) {
                case "Flower" : tmp_artifact_sort[0] = artifact_name.get(x); break;
                case "Plume" : tmp_artifact_sort[1] = artifact_name.get(x); break;
                case "Sand" : tmp_artifact_sort[2] = artifact_name.get(x); break;
                case "Goblet" : tmp_artifact_sort[3] = artifact_name.get(x); break;
                case "Circlet" : tmp_artifact_sort[4] = artifact_name.get(x); break;
            }
        }

        for (int x = 0 ; x < 5 ; x++){
            buff_artifact_tablayout.getTabAt(x).setIcon(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(tmp_artifact_sort[x]))[tmp_artifact_order[x]]);
        }

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

    /** UI -> PrettyCount */
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

    public void setDBName(String dataSheetName) {
        this.dataSheetName = dataSheetName;
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
