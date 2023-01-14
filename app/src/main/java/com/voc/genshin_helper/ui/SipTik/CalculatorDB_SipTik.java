package com.voc.genshin_helper.ui.SipTik;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.BaseColumns;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.Artifacts;
import com.voc.genshin_helper.data.CalculatorDB;
import com.voc.genshin_helper.data.CalculatorDBAdapter;
import com.voc.genshin_helper.data.Characters;
import com.voc.genshin_helper.data.CharactersAdapter;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.ScreenSizeUtils;
import com.voc.genshin_helper.data.Weapons;
import com.voc.genshin_helper.data.WeaponsAdapter;
import com.voc.genshin_helper.database.DataBaseContract;
import com.voc.genshin_helper.database.DataBaseHelper;
import com.voc.genshin_helper.ui.SipTik.DataBaseHelper_SipTik;
import com.voc.genshin_helper.ui.MMXLVIII.Calculator2048;
import com.voc.genshin_helper.util.BackgroundReload;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.LangUtils;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Refer from https://developer.android.com/training/data-storage/sqlite
 */

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class CalculatorDB_SipTik extends AppCompatActivity {

    RecyclerView mMainList;
    RecyclerView mItemList;
    Context context;
    CalculatorItemAdapter mAdapter;
    public List<CalculatorItem> calculatorItemList = new ArrayList<>();

    private static SQLiteDatabase db;
    DataBaseHelper_SipTik dbHelper = null;
    CardView director_menu_card;
    CardView director_char_card;
    ImageView db_char;
    CardView director_weapon_card;
    boolean isMenuDisplay = false;
    boolean isResultDisplay = false;
    Dialog dialog;
    Dialog dialogChoose;

    public static String[] lvlListChar = new String[]{"Lv1", "Lv2", "Lv3", "Lv4", "Lv5", "Lv6", "Lv7", "Lv8", "Lv9", "Lv10", "Lv11", "Lv12", "Lv13", "Lv14", "Lv15", "Lv16", "Lv17", "Lv18", "Lv19", "Lv20", "Lv20+", "Lv21", "Lv22", "Lv23", "Lv24", "Lv25", "Lv26", "Lv27", "Lv28", "Lv29", "Lv30", "Lv31", "Lv32", "Lv33", "Lv34", "Lv35", "Lv36", "Lv37", "Lv38", "Lv39", "Lv40", "Lv40+", "Lv41", "Lv42", "Lv43", "Lv44", "Lv45", "Lv46", "Lv47", "Lv48", "Lv49", "Lv50", "Lv50+", "Lv51", "Lv52", "Lv53", "Lv54", "Lv55", "Lv56", "Lv57", "Lv58", "Lv59", "Lv60", "Lv60+", "Lv61", "Lv62", "Lv63", "Lv64", "Lv65", "Lv66", "Lv67", "Lv68", "Lv69", "Lv70", "Lv70+", "Lv71", "Lv72", "Lv73", "Lv74", "Lv75", "Lv76", "Lv77", "Lv78", "Lv79", "Lv80", "Lv80+", "Lv81", "Lv82", "Lv83", "Lv84", "Lv85", "Lv86", "Lv87", "Lv88", "Lv89", "Lv90"};
    public static String[] lvlListWeapon70 = new String[]{"Lv1", "Lv2", "Lv3", "Lv4", "Lv5", "Lv6", "Lv7", "Lv8", "Lv9", "Lv10", "Lv11", "Lv12", "Lv13", "Lv14", "Lv15", "Lv16", "Lv17", "Lv18", "Lv19", "Lv20", "Lv20+", "Lv21", "Lv22", "Lv23", "Lv24", "Lv25", "Lv26", "Lv27", "Lv28", "Lv29", "Lv30", "Lv31", "Lv32", "Lv33", "Lv34", "Lv35", "Lv36", "Lv37", "Lv38", "Lv39", "Lv40", "Lv40+", "Lv41", "Lv42", "Lv43", "Lv44", "Lv45", "Lv46", "Lv47", "Lv48", "Lv49", "Lv50", "Lv50+", "Lv51", "Lv52", "Lv53", "Lv54", "Lv55", "Lv56", "Lv57", "Lv58", "Lv59", "Lv60", "Lv60+", "Lv61", "Lv62", "Lv63", "Lv64", "Lv65", "Lv66", "Lv67", "Lv68", "Lv69", "Lv70"};
    public static String[] lvlListWeapon90 = new String[]{"Lv1", "Lv2", "Lv3", "Lv4", "Lv5", "Lv6", "Lv7", "Lv8", "Lv9", "Lv10", "Lv11", "Lv12", "Lv13", "Lv14", "Lv15", "Lv16", "Lv17", "Lv18", "Lv19", "Lv20", "Lv20+", "Lv21", "Lv22", "Lv23", "Lv24", "Lv25", "Lv26", "Lv27", "Lv28", "Lv29", "Lv30", "Lv31", "Lv32", "Lv33", "Lv34", "Lv35", "Lv36", "Lv37", "Lv38", "Lv39", "Lv40", "Lv40+", "Lv41", "Lv42", "Lv43", "Lv44", "Lv45", "Lv46", "Lv47", "Lv48", "Lv49", "Lv50", "Lv50+", "Lv51", "Lv52", "Lv53", "Lv54", "Lv55", "Lv56", "Lv57", "Lv58", "Lv59", "Lv60", "Lv60+", "Lv61", "Lv62", "Lv63", "Lv64", "Lv65", "Lv66", "Lv67", "Lv68", "Lv69", "Lv70", "Lv70+", "Lv71", "Lv72", "Lv73", "Lv74", "Lv75", "Lv76", "Lv77", "Lv78", "Lv79", "Lv80", "Lv80+", "Lv81", "Lv82", "Lv83", "Lv84", "Lv85", "Lv86", "Lv87", "Lv88", "Lv89", "Lv90"};
    public static String[] lvlListSkill = new String[]{"Lv1", "Lv2", "Lv3", "Lv4", "Lv5", "Lv6", "Lv7", "Lv8", "Lv9", "Lv10"};
    public static String[] lvlListArt4 = new String[]{"Lv1", "Lv2", "Lv3", "Lv4"};
    public static String[] lvlListArt8 = new String[]{"Lv1", "Lv2", "Lv3", "Lv4", "Lv5", "Lv6", "Lv7", "Lv8"};
    public static String[] lvlListArt12 = new String[]{"Lv1", "Lv2", "Lv3", "Lv4", "Lv5", "Lv6", "Lv7", "Lv8", "Lv9", "Lv10", "Lv11", "Lv12"};
    public static String[] lvlListArt16 = new String[]{"Lv1", "Lv2", "Lv3", "Lv4", "Lv5", "Lv6", "Lv7", "Lv8", "Lv9", "Lv10", "Lv11", "Lv12", "Lv13", "Lv14", "Lv15", "Lv16"};
    public static String[] lvlListArt20 = new String[]{"Lv1", "Lv2", "Lv3", "Lv4", "Lv5", "Lv6", "Lv7", "Lv8", "Lv9", "Lv10", "Lv11", "Lv12", "Lv13", "Lv14", "Lv15", "Lv16", "Lv17", "Lv18", "Lv19", "Lv20"};


    int tmp_cnt_dis = 0;
    int char_pos = 0;
    int weapon_pos = 0;

    Activity activity;
    public SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String[] rules = new String[]{
            "0","1","2","3","4","5","6","7","8","9",
            "&","*","@","\"","{","}","^",":",",","#","$","\"","!","/","<",">","-","%",".","+","?",";","'"," ","~","_","|","="};

    public final static int DIALOG_DISMISS = 16384;
    public final static int REFRESH_DB_LIST = 8192;

    ArrayList<Characters> charactersList = new ArrayList<>();
    CharactersAdapter charactersAdapter;
    ArrayList<Weapons> weaponsList = new ArrayList<>();
    WeaponsAdapter weaponsAdapter;

    public boolean show_pyro = true;
    public boolean show_hydro = true;
    public boolean show_anemo = true;
    public boolean show_dendor = true;
    public boolean show_electro = true;
    public boolean show_cryo = true;
    public boolean show_geo = true;

    public boolean show_sword = true;
    public boolean show_claymore = true;
    public boolean show_polearm = true;
    public boolean show_bow = true;
    public boolean show_catalyst = true;

    public boolean show_rare1 = false;
    public boolean show_rare2 = false;
    public boolean show_rare3 = false;
    public boolean show_rare4 = false;
    public boolean show_rare5 = false;
    public boolean show_released = false;
    public boolean show_unreleased = false;
    public boolean show_dps = false;
    public boolean show_sub_dps = false;
    public boolean show_util = false;

    public boolean show_flower = true;
    public boolean show_plume = true;
    public boolean show_sand = true;
    public boolean show_goblet = true;
    public boolean show_circlet = true;

    public int show_stars = 0;

    public ArrayList<String> choosedNameList = new ArrayList<String>();
    public ArrayList<String> choosedTypeList = new ArrayList<String>();
    public ArrayList<Integer> choosedBeforeLvlList = new ArrayList<Integer>();
    public ArrayList<Integer> choosedAfterLvlList = new ArrayList<Integer>();
    public ArrayList<Integer> choosedBeforeBreakLvlList = new ArrayList<Integer>();
    public ArrayList<Boolean> choosedBeforeBreakUPLvlList = new ArrayList<Boolean>();
    public ArrayList<Integer> choosedAfterBreakLvlList = new ArrayList<Integer>();
    public ArrayList<Boolean> choosedAfterBreakUPLvlList = new ArrayList<Boolean>();
    public ArrayList<Integer> choosedBeforeSkill1LvlList = new ArrayList<Integer>();
    public ArrayList<Integer> choosedAfterSkill1LvlList = new ArrayList<Integer>();
    public ArrayList<Integer> choosedBeforeSkill2LvlList = new ArrayList<Integer>();
    public ArrayList<Integer> choosedAfterSkill2LvlList = new ArrayList<Integer>();
    public ArrayList<Integer> choosedBeforeSkill3LvlList = new ArrayList<Integer>();
    public ArrayList<Integer> choosedAfterSkill3LvlList = new ArrayList<Integer>();
    public ArrayList<Boolean> choosedIsCal = new ArrayList<Boolean>();

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

    boolean bInit = true;
    boolean aInit = true;
    boolean sa1Init = true;
    boolean sa2Init = true;
    boolean sa3Init = true;
    boolean sb1Init = true;
    boolean sb2Init = true;
    boolean sb3Init = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cal_item_choose_siptik);
        context = this;
        dbHelper = new DataBaseHelper_SipTik(this);
        activity = this;
        sharedPreferences = getSharedPreferences("user_info",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        BackgroundReload.BackgroundReload(context,activity);

        mMainList = findViewById(R.id.main_list);
        mAdapter = new CalculatorItemAdapter(this, calculatorItemList,activity);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        mMainList.setLayoutManager(mLayoutManager);
        mMainList.setAdapter(mAdapter);
        mMainList.removeAllViewsInLayout();

        charactersAdapter = new CharactersAdapter(context,charactersList,activity,sharedPreferences);
        weaponsAdapter = new WeaponsAdapter(context,weaponsList,activity,sharedPreferences);

        readIndexRecord();

        director_menu_card = findViewById(R.id.director_menu_card);
        director_char_card = findViewById(R.id.director_char_card);
        db_char = findViewById(R.id.db_char);
        director_weapon_card = findViewById(R.id.director_weapon_card);

        director_menu_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMenuDisplay == false){

                    director_char_card.setVisibility(View.VISIBLE);
                    director_weapon_card.setVisibility(View.VISIBLE);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable(){
                        @Override
                        public void run() {
                            director_menu_card.animate()
                                    .setDuration(300)
                                    .rotation(45)
                                    .setListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationStart(Animator animation) {
                                            super.onAnimationStart(animation);

                                            if (tmp_cnt_dis == 0){
                                                char_pos = director_char_card.getTop();
                                                weapon_pos = director_weapon_card.getTop();
                                            }

                                            director_char_card.setY(director_menu_card.getTop());
                                            director_weapon_card.setY(director_menu_card.getTop());
                                            director_char_card.animate().setDuration(300).alpha(1f).y(char_pos);
                                            director_weapon_card.animate().setDuration(300).alpha(1f).y(weapon_pos);

                                            isMenuDisplay = true;

                                            director_char_card.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {

                                                }
                                            });
                                        }
                                    });
                        }}, 100);
                }else{
                    director_menu_card.animate()
                            .setDuration(300)
                            .rotation(0)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                    super.onAnimationStart(animation);

                                    director_char_card.setTop(char_pos);
                                    director_weapon_card.setTop(weapon_pos);

                                    director_weapon_card.animate().alpha(0f).setDuration(300).y(director_menu_card.getTop());
                                    director_char_card.animate().alpha(0f).setDuration(300).y(director_menu_card.getTop());
                                    isMenuDisplay = false;
                                }
                            });
                }
            }
        });

        director_weapon_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listUISetup("WEAPON");
            }
        });
        db_char.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listUISetup("CHAR");
            }
        });

    }


    public void addItem(String charName_base, int rare, String type) {
        DataBaseHelper_SipTik dbHelper = new DataBaseHelper_SipTik(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int maxLvl = 70;
        int maxBreakLvl = 4;
        switch (rare){
            case 1:
            case 2: maxLvl = 70;maxBreakLvl = 4;break;
            case 3:
            case 4:
            case 5: maxLvl = 90;maxBreakLvl = 6;break;
        }

        choosedNameList.add(charName_base);
        choosedBeforeLvlList.add(1);
        choosedAfterLvlList.add(maxLvl);
        choosedBeforeBreakLvlList.add(0);
        choosedAfterBreakLvlList.add(maxBreakLvl);
        choosedBeforeSkill1LvlList.add(1);
        choosedAfterSkill1LvlList.add(10);
        choosedBeforeSkill2LvlList.add(1);
        choosedAfterSkill2LvlList.add(10);
        choosedBeforeSkill3LvlList.add(1);
        choosedAfterSkill3LvlList.add(10);
        choosedIsCal.add(true);
        choosedBeforeBreakUPLvlList.add(false);
        choosedAfterBreakUPLvlList.add(false);

        ContentValues values = new ContentValues();
        values.put("type", type);
        values.put("name", charName_base);
        values.put("beforeLvl", 1);
        values.put("afterLvl", maxLvl);
        values.put("beforeBreakLvl", 0);
        values.put("afterBreakLvl", maxBreakLvl);
        values.put("beforeBreakUpLvl", String.valueOf((false) ? 1 : 0 ));
        values.put("afterBreakUpLvl", String.valueOf((false) ? 1 : 0 ));
        values.put("beforeSkill1Lvl", 1);
        values.put("afterSkill1Lvl", 10);
        values.put("beforeSkill2Lvl", 1);
        values.put("afterSkill2Lvl", 10);
        values.put("beforeSkill3Lvl", 1);
        values.put("afterSkill3Lvl", 10);
        values.put("follow", "N/A");
        values.put("rare", rare);
        values.put("isCal", String.valueOf((true) ? 1 : 0 ));

        db.insert("SipTik", null, values);

        if(dialog != null){
            dialog.dismiss();
        }
        if(dialogChoose != null){
            dialogChoose.dismiss();
        }
        readIndexRecord();
    }

    public void listUISetup(String type){
        dialogChoose = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.fragment_cal_choose_siptik, null);
        dialogChoose.setContentView(view);
        dialogChoose.setCanceledOnTouchOutside(true);
        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
        Window dialogWindow = dialogChoose.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        mItemList = view.findViewById(R.id.main_list);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        if (sharedPreferences.getString("curr_ui_grid", "2").equals("2")) {
            if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                mLayoutManager = new GridLayoutManager(context, width / 480 + 1);
            } else {
                mLayoutManager = new GridLayoutManager(context, 2);
            }
        } else if (sharedPreferences.getString("curr_ui_grid", "2").equals("3")) {
            if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                mLayoutManager = new GridLayoutManager(context, width / 400 + 1);
            } else {
                mLayoutManager = new GridLayoutManager(context, 3);
            }
        } else if (sharedPreferences.getString("curr_ui_grid", "2").equals("4")) {
            if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                int tmp_cnt = (int) width / 960;
                if (tmp_cnt < 1) {
                    tmp_cnt = 1;
                }
                mLayoutManager = new GridLayoutManager(context, tmp_cnt);
            } else {
                mLayoutManager = new GridLayoutManager(context, 1);
            }
        } else if (sharedPreferences.getString("curr_ui_grid", "2").equals("5")) {
            if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                int tmp_cnt = (int) width / 960;
                if (tmp_cnt < 1) {
                    tmp_cnt = 1;
                }
                mLayoutManager = new GridLayoutManager(context, tmp_cnt);
            } else {
                mLayoutManager = new GridLayoutManager(context, 1);
            }
        }
        LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsMsg.gravity = Gravity.CENTER;

        switch (type){
            case "CHAR" : {
                mItemList.setAdapter(charactersAdapter);
                break;
            }
            case "WEAPON" : {
                mItemList.setAdapter(weaponsAdapter);
                break;
            }
        }
        mItemList.setLayoutManager(mLayoutManager);
        mItemList.setLayoutParams(paramsMsg);
        mItemList.removeAllViewsInLayout();

        char_list_reload();
        weapon_list_reload();


        ItemRss css = new ItemRss();

        ImageView char_search = view.findViewById(R.id.char_search);
        char_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View header_search = view.findViewById(R.id.header_search);
                EditText header_search_et = view.findViewById(R.id.header_search_et);
                Button menu_search_cancel = view.findViewById(R.id.menu_search_cancel);
                ImageView header_search_reset = view.findViewById(R.id.header_search_reset);

                header_search.animate()
                        .alpha(1.0f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                header_search.setVisibility(View.VISIBLE);
                            }
                        });

                header_search_et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (header_search_et.getText() != null) {
                            String request = header_search_et.getText().toString();
                            if (!request.equals("")) {
                                ArrayList<Characters> filteredListC = new ArrayList<>();
                                ArrayList<Weapons> filteredListW = new ArrayList<>();
                                ArrayList<Artifacts> filteredListA = new ArrayList<>();

                                switch (type.toLowerCase()){
                                    case "char" : {
                                        int x = 0;
                                        for (Characters item : charactersList) {
                                            String str = request.toLowerCase();
                                            if (css.getCharByName(item.getName(), context)[1].contains(str) || css.getCharByName(item.getName(), context)[1].toLowerCase().contains(str) || item.getName().toLowerCase().contains(str)) { // EN -> ZH
                                                filteredListC.add(item);
                                            }
                                            x = x + 1;
                                        }
                                        charactersAdapter.filterList(filteredListC);
                                        break;
                                    }

                                    case "weapon" : {
                                        int x = 0;
                                        for (Weapons item : weaponsList) {
                                            String str = request.toLowerCase();
                                            if (css.getWeaponByName(item.getName(),context)[0].contains(str)||css.getWeaponByName(item.getName(),context)[0].toLowerCase().contains(str)||css.getWeaponByName(item.getName(),context)[0].toUpperCase().contains(str)||item.getName().toLowerCase().contains(str)){ // EN -> ZH
                                                filteredListW.add(item);
                                            }
                                            x = x +1;
                                        }
                                        weaponsAdapter.filterList(filteredListW);
                                        break;
                                    }
                                }

                            } else {
                                switch (type.toLowerCase()){
                                    case "char" : charactersAdapter.filterList(charactersList);break;
                                    case "weapon" : weaponsAdapter.filterList(weaponsList);break;
                                }
                            }
                        } else {
                            switch (type.toLowerCase()){
                                case "char" : charactersAdapter.filterList(charactersList);break;
                                case "weapon" : weaponsAdapter.filterList(weaponsList);break;
                            }
                        }
                    }
                });

                menu_search_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        header_search_et.setText("");
                        header_search.animate()
                                .alpha(0.0f)
                                .setDuration(300)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        header_search.setVisibility(View.GONE);
                                    }
                                });
                    }
                });
                header_search_reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        header_search_et.setText("");
                    }
                });

            }

            ;
        });

        switch (type){
            case "CHAR" : {
                ImageView char_filter = view.findViewById(R.id.char_filter);
                char_filter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                        View view = View.inflate(context, R.layout.menu_char_filter_2048, null);
                        // Elements
                        ImageView pyro = view.findViewById(R.id.pyro_ico);
                        ImageView hydro = view.findViewById(R.id.hydro_ico);
                        ImageView anemo = view.findViewById(R.id.anemo_ico);
                        ImageView electro = view.findViewById(R.id.electro_ico);
                        ImageView dendor = view.findViewById(R.id.dendor_ico);
                        ImageView cryo = view.findViewById(R.id.cryo_ico);
                        ImageView geo = view.findViewById(R.id.geo_ico);
                        // Weapons
                        ImageView ico_sword = view.findViewById(R.id.ico_sword);
                        ImageView ico_claymore = view.findViewById(R.id.ico_claymore);
                        ImageView ico_polearm = view.findViewById(R.id.ico_polearm);
                        ImageView ico_bow = view.findViewById(R.id.ico_bow);
                        ImageView ico_catalyst = view.findViewById(R.id.ico_catalyst);
                        // Rarity
                        CheckBox menu_rare4 = view.findViewById(R.id.menu_rare4);
                        CheckBox menu_rare5 = view.findViewById(R.id.menu_rare5);
                        RatingBar menu_rating = view.findViewById(R.id.menu_rating);
                        menu_rating.setVisibility(View.GONE);
                        // Release
                        CheckBox menu_release_0 = view.findViewById(R.id.menu_release_0);
                        CheckBox menu_release_1 = view.findViewById(R.id.menu_release_1);
                        // Role
                        CheckBox menu_role_dps = view.findViewById(R.id.menu_role_dps);
                        CheckBox menu_role_sub_dps = view.findViewById(R.id.menu_role_sub_dps);
                        CheckBox menu_role_utility = view.findViewById(R.id.menu_role_utility);
                        // Function Buttons
                        ImageView cancel = view.findViewById(R.id.menu_cancel);
                        FrameLayout reset = view.findViewById(R.id.menu_reset);
                        FrameLayout ok = view.findViewById(R.id.menu_ok);

                        show_pyro = sharedPreferences.getBoolean("show_pyro",false);
                        show_hydro = sharedPreferences.getBoolean("show_hydro",false);
                        show_anemo = sharedPreferences.getBoolean("show_anemo",false);
                        show_electro = sharedPreferences.getBoolean("show_electro",false);
                        show_dendor = sharedPreferences.getBoolean("show_dendor",false);
                        show_cryo = sharedPreferences.getBoolean("show_cryo",false);
                        show_geo = sharedPreferences.getBoolean("show_geo",false);
                        show_sword = sharedPreferences.getBoolean("show_sword",false);
                        show_claymore = sharedPreferences.getBoolean("show_claymore",false);
                        show_polearm = sharedPreferences.getBoolean("show_polearm",false);
                        show_bow = sharedPreferences.getBoolean("show_bow",false);
                        show_catalyst = sharedPreferences.getBoolean("show_catalyst",false);
                        show_rare4 = sharedPreferences.getBoolean("show_rare4",false);
                        show_rare5 = sharedPreferences.getBoolean("show_rare5",false);
                        show_released = sharedPreferences.getBoolean("show_released",false);
                        show_unreleased = sharedPreferences.getBoolean("show_unreleased",false);
                        show_dps = sharedPreferences.getBoolean("show_dps",false);
                        show_sub_dps = sharedPreferences.getBoolean("show_sub_dps",false);
                        show_util = sharedPreferences.getBoolean("show_util",false);

                        if(show_pyro){show_pyro = true;pyro.setColorFilter(Color.parseColor("#00000000"));}else{show_pyro = false;pyro.setColorFilter(Color.parseColor("#66313131"));}
                        if(show_hydro){show_hydro = true;hydro.setColorFilter(Color.parseColor("#00000000"));}else{show_hydro = false;hydro.setColorFilter(Color.parseColor("#66313131"));}
                        if(show_anemo){show_anemo = true;anemo.setColorFilter(Color.parseColor("#00000000"));}else{show_anemo = false;anemo.setColorFilter(Color.parseColor("#66313131"));}
                        if(show_electro){show_electro = true;electro.setColorFilter(Color.parseColor("#00000000"));}else{show_electro = false;electro.setColorFilter(Color.parseColor("#66313131"));}
                        if(show_dendor){show_dendor = true;dendor.setColorFilter(Color.parseColor("#00000000"));}else{show_dendor = false;dendor.setColorFilter(Color.parseColor("#66313131"));}
                        if(show_cryo){show_cryo = true;cryo.setColorFilter(Color.parseColor("#00000000"));}else{show_cryo = false;cryo.setColorFilter(Color.parseColor("#66313131"));}
                        if(show_geo){show_geo = true;geo.setColorFilter(Color.parseColor("#00000000"));}else{show_geo = false;geo.setColorFilter(Color.parseColor("#66313131"));}
                        if(show_sword){show_sword = true;ico_sword.setColorFilter(Color.parseColor("#00000000"));}else{show_sword = false;ico_sword.setColorFilter(Color.parseColor("#66313131"));}
                        if(show_claymore){show_claymore = true;ico_claymore.setColorFilter(Color.parseColor("#00000000"));}else{show_claymore = false;ico_claymore.setColorFilter(Color.parseColor("#66313131"));}
                        if(show_polearm){show_polearm = true;ico_polearm.setColorFilter(Color.parseColor("#00000000"));}else{show_polearm = false;ico_polearm.setColorFilter(Color.parseColor("#66313131"));}
                        if(show_bow){show_bow = true;ico_bow.setColorFilter(Color.parseColor("#00000000"));}else{show_bow = false;ico_bow.setColorFilter(Color.parseColor("#66313131"));}
                        if(show_catalyst){show_catalyst = true;ico_catalyst.setColorFilter(Color.parseColor("#00000000"));}else{show_catalyst = false;ico_catalyst.setColorFilter(Color.parseColor("#66313131"));}

                        if (show_rare4){ menu_rare4.setChecked(true); }
                        if (show_rare5){ menu_rare5.setChecked(true); }
                        if (show_released){ menu_release_0.setChecked(true); }
                        if (show_unreleased){ menu_release_1.setChecked(true); }
                        if (show_dps){ menu_role_dps.setChecked(true); }
                        if (show_sub_dps){ menu_role_sub_dps.setChecked(true); }
                        if (show_util){ menu_role_utility.setChecked(true); }

                        pyro.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_pyro){show_pyro = false;pyro.setColorFilter(Color.parseColor("#66313131"));}else{show_pyro = true;pyro.setColorFilter(Color.parseColor("#00000000"));}}});
                        hydro.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_hydro){show_hydro = false;hydro.setColorFilter(Color.parseColor("#66313131"));}else{show_hydro = true;hydro.setColorFilter(Color.parseColor("#00000000"));}}});
                        anemo.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_anemo){show_anemo = false;anemo.setColorFilter(Color.parseColor("#66313131"));}else{show_anemo = true;anemo.setColorFilter(Color.parseColor("#00000000"));}}});
                        electro.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_electro){show_electro = false;electro.setColorFilter(Color.parseColor("#66313131"));}else{show_electro = true;electro.setColorFilter(Color.parseColor("#00000000"));}}});
                        dendor.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_dendor){show_dendor = false;dendor.setColorFilter(Color.parseColor("#66313131"));}else{show_dendor = true;dendor.setColorFilter(Color.parseColor("#00000000"));}}});
                        cryo.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_cryo){show_cryo = false;cryo.setColorFilter(Color.parseColor("#66313131"));}else{show_cryo = true;cryo.setColorFilter(Color.parseColor("#00000000"));}}});
                        geo.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_geo){show_geo = false;geo.setColorFilter(Color.parseColor("#66313131"));}else{show_geo = true;geo.setColorFilter(Color.parseColor("#00000000"));}}});
                        ico_sword.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_sword){show_sword = false;ico_sword.setColorFilter(Color.parseColor("#66313131"));}else{show_sword = true;ico_sword.setColorFilter(Color.parseColor("#00000000"));}}});
                        ico_claymore.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_claymore){show_claymore = false;ico_claymore.setColorFilter(Color.parseColor("#66313131"));}else{show_claymore = true;ico_claymore.setColorFilter(Color.parseColor("#00000000"));}}});
                        ico_polearm.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_polearm){show_polearm = false;ico_polearm.setColorFilter(Color.parseColor("#66313131"));}else{show_polearm = true;ico_polearm.setColorFilter(Color.parseColor("#00000000"));}}});
                        ico_bow.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_bow){show_bow = false;ico_bow.setColorFilter(Color.parseColor("#66313131"));}else{show_bow = true;ico_bow.setColorFilter(Color.parseColor("#00000000"));}}});
                        ico_catalyst.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_catalyst){show_catalyst = false;ico_catalyst.setColorFilter(Color.parseColor("#66313131"));}else{show_catalyst = true;ico_catalyst.setColorFilter(Color.parseColor("#00000000"));}}});
                        ico_catalyst.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_catalyst){show_catalyst = false;ico_catalyst.setColorFilter(Color.parseColor("#66313131"));}else{show_catalyst = true;ico_catalyst.setColorFilter(Color.parseColor("#00000000"));}}});

                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        reset.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                show_pyro = false;
                                show_hydro = false;
                                show_anemo = false;
                                show_dendor = false;
                                show_electro = false;
                                show_cryo = false;
                                show_geo = false;

                                show_sword = false;
                                show_claymore = false;
                                show_polearm = false;
                                show_bow = false;
                                show_catalyst = false;

                                show_rare1 = false;
                                show_rare2 = false;
                                show_rare3 = false;
                                show_rare4 = false;
                                show_rare5 = false;
                                show_released = false;
                                show_unreleased = false;
                                show_dps = false;
                                show_sub_dps = false;
                                show_util = false;

                                editor.putBoolean("show_pyro",show_pyro);
                                editor.putBoolean("show_hydro",show_hydro);
                                editor.putBoolean("show_anemo",show_anemo);
                                editor.putBoolean("show_electro",show_electro);
                                editor.putBoolean("show_dendor",show_dendor);
                                editor.putBoolean("show_cryo",show_cryo);
                                editor.putBoolean("show_geo",show_geo);
                                editor.putBoolean("show_sword",show_sword);
                                editor.putBoolean("show_claymore",show_claymore);
                                editor.putBoolean("show_polearm",show_polearm);
                                editor.putBoolean("show_bow",show_bow);
                                editor.putBoolean("show_catalyst",show_catalyst);
                                editor.putBoolean("show_rare1",show_rare1);
                                editor.putBoolean("show_rare2",show_rare2);
                                editor.putBoolean("show_rare3",show_rare3);
                                editor.putBoolean("show_rare4",show_rare4);
                                editor.putBoolean("show_rare5",show_rare5);
                                editor.putBoolean("show_released",show_released);
                                editor.putBoolean("show_unreleased",show_unreleased);
                                editor.putBoolean("show_dps",show_dps);
                                editor.putBoolean("show_sub_dps",show_sub_dps);
                                editor.putBoolean("show_util",show_util);
                                editor.apply();
                                dialog.dismiss();

                                charactersAdapter.filterList(charactersList);

                            }
                        });

                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (menu_rare4.isChecked()){show_rare4 = true;}else{show_rare4 = false;}
                                if (menu_rare5.isChecked()){show_rare5 = true;}else{show_rare5 = false;}
                                if (menu_release_0.isChecked()){show_released = true;}else{show_released = false;}
                                if (menu_release_1.isChecked()){show_unreleased = true;}else{show_unreleased = false;}
                                if (menu_role_dps.isChecked()){show_dps = true;}else{show_dps = false;}
                                if (menu_role_sub_dps.isChecked()){show_sub_dps = true;}else{show_sub_dps = false;}
                                if (menu_role_utility.isChecked()){show_util = true;}else{show_util = false;}
                                filterCharAlgothm();
                                dialog.dismiss();
                            }
                        });

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

                        lp.width = width;
                        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                        lp.gravity = Gravity.BOTTOM;
                        dialogWindow.setAttributes(lp);
                        dialog.show();
                    }
                });
                break;
            }
            case "WEAPON" : {
                ImageView weapon_filter = view.findViewById(R.id.char_filter);
                weapon_filter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                        View view = View.inflate(context, R.layout.menu_char_filter_2048, null);
                        // Element
                        TextView menu_elements_title_tv = view.findViewById(R.id.menu_elements_title_tv);
                        LinearLayout menu_elements_ll = view.findViewById(R.id.menu_elements_ll);
                        menu_elements_title_tv.setVisibility(View.GONE);
                        menu_elements_ll.setVisibility(View.GONE);

                        // Weapons
                        ImageView ico_sword = view.findViewById(R.id.ico_sword);
                        ImageView ico_claymore = view.findViewById(R.id.ico_claymore);
                        ImageView ico_polearm = view.findViewById(R.id.ico_polearm);
                        ImageView ico_bow = view.findViewById(R.id.ico_bow);
                        ImageView ico_catalyst = view.findViewById(R.id.ico_catalyst);
                        // Rarity
                        CheckBox menu_rare4 = view.findViewById(R.id.menu_rare4);
                        CheckBox menu_rare5 = view.findViewById(R.id.menu_rare5);
                        RatingBar menu_rating = view.findViewById(R.id.menu_rating);

                        menu_rare4.setVisibility(View.GONE);
                        menu_rare5.setVisibility(View.GONE);
                        menu_rating.setVisibility(View.VISIBLE);

                        // Release
                        CheckBox menu_release_0 = view.findViewById(R.id.menu_release_0);
                        CheckBox menu_release_1 = view.findViewById(R.id.menu_release_1);

                        // Role
                        TextView menu_role_title_tv = view.findViewById(R.id.menu_role_title_tv);
                        LinearLayout menu_role_ll = view.findViewById(R.id.menu_role_ll);
                        menu_role_title_tv.setVisibility(View.GONE);
                        menu_role_ll.setVisibility(View.GONE);

                        // Function Buttons
                        ImageView cancel = view.findViewById(R.id.menu_cancel);
                        FrameLayout reset = view.findViewById(R.id.menu_reset);
                        FrameLayout ok = view.findViewById(R.id.menu_ok);

                        show_pyro = sharedPreferences.getBoolean("show_pyro",false);
                        show_hydro = sharedPreferences.getBoolean("show_hydro",false);
                        show_anemo = sharedPreferences.getBoolean("show_anemo",false);
                        show_electro = sharedPreferences.getBoolean("show_electro",false);
                        show_dendor = sharedPreferences.getBoolean("show_dendor",false);
                        show_cryo = sharedPreferences.getBoolean("show_cryo",false);
                        show_geo = sharedPreferences.getBoolean("show_geo",false);
                        show_sword = sharedPreferences.getBoolean("show_sword",false);
                        show_claymore = sharedPreferences.getBoolean("show_claymore",false);
                        show_polearm = sharedPreferences.getBoolean("show_polearm",false);
                        show_bow = sharedPreferences.getBoolean("show_bow",false);
                        show_catalyst = sharedPreferences.getBoolean("show_catalyst",false);
                        show_rare1  = sharedPreferences.getBoolean("show_rare1",false);
                        show_rare2 = sharedPreferences.getBoolean("show_rare2",false);
                        show_rare3 = sharedPreferences.getBoolean("show_rare3",false);
                        show_rare4 = sharedPreferences.getBoolean("show_rare4",false);
                        show_rare5 = sharedPreferences.getBoolean("show_rare5",false);
                        show_released = sharedPreferences.getBoolean("show_released",false);
                        show_unreleased = sharedPreferences.getBoolean("show_unreleased",false);
                        show_dps = sharedPreferences.getBoolean("show_dps",false);
                        show_sub_dps = sharedPreferences.getBoolean("show_sub_dps",false);
                        show_util = sharedPreferences.getBoolean("show_util",false);

                        if (show_rare1){ menu_rating.setRating(1); }
                        if (show_rare2){ menu_rating.setRating(2); }
                        if (show_rare3){ menu_rating.setRating(3); }
                        if (show_rare4){ menu_rating.setRating(4); }
                        if (show_rare5){ menu_rating.setRating(5); }
                        if (show_released){ menu_release_0.setChecked(true); }
                        if (show_unreleased){ menu_release_1.setChecked(true); }

                        if(show_sword){show_sword = true;ico_sword.setColorFilter(Color.parseColor("#00000000"));}else{show_sword = false;ico_sword.setColorFilter(Color.parseColor("#66313131"));}
                        if(show_claymore){show_claymore = true;ico_claymore.setColorFilter(Color.parseColor("#00000000"));}else{show_claymore = false;ico_claymore.setColorFilter(Color.parseColor("#66313131"));}
                        if(show_polearm){show_polearm = true;ico_polearm.setColorFilter(Color.parseColor("#00000000"));}else{show_polearm = false;ico_polearm.setColorFilter(Color.parseColor("#66313131"));}
                        if(show_bow){show_bow = true;ico_bow.setColorFilter(Color.parseColor("#00000000"));}else{show_bow = false;ico_bow.setColorFilter(Color.parseColor("#66313131"));}
                        if(show_catalyst){show_catalyst = true;ico_catalyst.setColorFilter(Color.parseColor("#00000000"));}else{show_catalyst = false;ico_catalyst.setColorFilter(Color.parseColor("#66313131"));}


                        ico_sword.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_sword){show_sword = false;ico_sword.setColorFilter(Color.parseColor("#66313131"));}else{show_sword = true;ico_sword.setColorFilter(Color.parseColor("#00000000"));}}});
                        ico_claymore.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_claymore){show_claymore = false;ico_claymore.setColorFilter(Color.parseColor("#66313131"));}else{show_claymore = true;ico_claymore.setColorFilter(Color.parseColor("#00000000"));}}});
                        ico_polearm.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_polearm){show_polearm = false;ico_polearm.setColorFilter(Color.parseColor("#66313131"));}else{show_polearm = true;ico_polearm.setColorFilter(Color.parseColor("#00000000"));}}});
                        ico_bow.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_bow){show_bow = false;ico_bow.setColorFilter(Color.parseColor("#66313131"));}else{show_bow = true;ico_bow.setColorFilter(Color.parseColor("#00000000"));}}});
                        ico_catalyst.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_catalyst){show_catalyst = false;ico_catalyst.setColorFilter(Color.parseColor("#66313131"));}else{show_catalyst = true;ico_catalyst.setColorFilter(Color.parseColor("#00000000"));}}});
                        ico_catalyst.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_catalyst){show_catalyst = false;ico_catalyst.setColorFilter(Color.parseColor("#66313131"));}else{show_catalyst = true;ico_catalyst.setColorFilter(Color.parseColor("#00000000"));}}});

                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        reset.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                show_pyro = false;
                                show_hydro = false;
                                show_anemo = false;
                                show_dendor = false;
                                show_electro = false;
                                show_cryo = false;
                                show_geo = false;

                                show_sword = false;
                                show_claymore = false;
                                show_polearm = false;
                                show_bow = false;
                                show_catalyst = false;

                                show_rare1 = false;
                                show_rare2 = false;
                                show_rare3 = false;
                                show_rare4 = false;
                                show_rare5 = false;
                                show_released = false;
                                show_unreleased = false;
                                show_dps = false;
                                show_sub_dps = false;
                                show_util = false;

                                editor.putBoolean("show_pyro",show_pyro);
                                editor.putBoolean("show_hydro",show_hydro);
                                editor.putBoolean("show_anemo",show_anemo);
                                editor.putBoolean("show_electro",show_electro);
                                editor.putBoolean("show_dendor",show_dendor);
                                editor.putBoolean("show_cryo",show_cryo);
                                editor.putBoolean("show_geo",show_geo);
                                editor.putBoolean("show_sword",show_sword);
                                editor.putBoolean("show_claymore",show_claymore);
                                editor.putBoolean("show_polearm",show_polearm);
                                editor.putBoolean("show_bow",show_bow);
                                editor.putBoolean("show_catalyst",show_catalyst);
                                editor.putBoolean("show_rare1",show_rare1);
                                editor.putBoolean("show_rare2",show_rare2);
                                editor.putBoolean("show_rare3",show_rare3);
                                editor.putBoolean("show_rare4",show_rare4);
                                editor.putBoolean("show_rare5",show_rare5);
                                editor.putBoolean("show_released",show_released);
                                editor.putBoolean("show_unreleased",show_unreleased);
                                editor.putBoolean("show_dps",show_dps);
                                editor.putBoolean("show_sub_dps",show_sub_dps);
                                editor.putBoolean("show_util",show_util);
                                editor.apply();
                                dialog.dismiss();

                                weaponsAdapter.filterList(weaponsList);

                            }
                        });

                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (menu_release_0.isChecked()){show_released = true;}else{show_released = false;}
                                if (menu_release_1.isChecked()){show_unreleased = true;}else{show_unreleased = false;}
                                filterWeaponAlgothm((int) menu_rating.getRating());
                                dialog.dismiss();
                            }
                        });

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

                        lp.width = width;
                        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                        lp.gravity = Gravity.BOTTOM;
                        dialogWindow.setAttributes(lp);
                        dialog.show();
                    }
                });
                break;
            }
        }

        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        dialogChoose.show();
    }



    public void filterCharAlgothm() {
        ArrayList<Characters> filteredList = new ArrayList<>();
        for (Characters item : charactersList) {
            // DEFAULT
            if ((show_sword == false && show_claymore == false && show_catalyst == false && show_bow == false && show_polearm == false) &&
                    (show_anemo == false && show_cryo == false && show_dendor == false && show_electro == false && show_hydro == false && show_geo == false && show_pyro == false) &&
                    (show_released == false && show_unreleased == false) &&
                    (show_rare1 == false && show_rare2 == false && show_rare3 == false && show_rare4 == false && show_rare5 == false) &&
                    (show_dps == false && show_sub_dps == false && show_util == false)) {
                filteredList.add(item);
            } else {
                boolean isAllTrue = true;
                int isSingleElement = 0;
                int isSingleWeapon = 0;
                int isSingleRare = 0;
                int isSingleRelease = 0;
                int isSingleRole = 0;

                if (show_pyro) {
                    isSingleElement = isSingleElement + 1;
                }
                if (show_hydro) {
                    isSingleElement = isSingleElement + 1;
                }
                if (show_anemo) {
                    isSingleElement = isSingleElement + 1;
                }
                if (show_dendor) {
                    isSingleElement = isSingleElement + 1;
                }
                if (show_electro) {
                    isSingleElement = isSingleElement + 1;
                }
                if (show_cryo) {
                    isSingleElement = isSingleElement + 1;
                }
                if (show_geo) {
                    isSingleElement = isSingleElement + 1;
                }

                if (show_sword) {
                    isSingleWeapon = isSingleWeapon + 1;
                }
                if (show_claymore) {
                    isSingleWeapon = isSingleWeapon + 1;
                }
                if (show_polearm) {
                    isSingleWeapon = isSingleWeapon + 1;
                }
                if (show_bow) {
                    isSingleWeapon = isSingleWeapon + 1;
                }
                if (show_catalyst) {
                    isSingleWeapon = isSingleWeapon + 1;
                }

                if (show_rare4) {
                    isSingleRare = isSingleRare + 1;
                }
                if (show_rare5) {
                    isSingleRare = isSingleRare + 1;
                }
                if (show_released) {
                    isSingleRelease = isSingleRelease + 1;
                }
                if (show_unreleased) {
                    isSingleRelease = isSingleRelease + 1;
                }
                if (show_dps) {
                    isSingleRelease = isSingleRelease + 1;
                }
                if (show_sub_dps) {
                    isSingleRelease = isSingleRelease + 1;
                }
                if (show_util) {
                    isSingleRelease = isSingleRelease + 1;
                }

                if (isSingleElement == 1) {
                    if (show_pyro && !item.getElement().toLowerCase().equals("pyro")) {
                        isAllTrue = false;
                    }
                    if (show_hydro && !item.getElement().toLowerCase().equals("hydro")) {
                        isAllTrue = false;
                    }
                    if (show_anemo && !item.getElement().toLowerCase().equals("anemo")) {
                        isAllTrue = false;
                    }
                    if (show_dendor && !item.getElement().toLowerCase().equals("dendro")) {
                        isAllTrue = false;
                    }
                    if (show_electro && !item.getElement().toLowerCase().equals("electro")) {
                        isAllTrue = false;
                    }
                    if (show_cryo && !item.getElement().toLowerCase().equals("cryo")) {
                        isAllTrue = false;
                    }
                    if (show_geo && !item.getElement().toLowerCase().equals("geo")) {
                        isAllTrue = false;
                    }
                } else if ((show_anemo == false && show_cryo == false && show_dendor == false && show_electro == false && show_hydro == false && show_geo == false && show_pyro == false) == false) {
                    if (!show_pyro && item.getElement().toLowerCase().equals("pyro")) {
                        isAllTrue = false;
                    }
                    if (!show_hydro && item.getElement().toLowerCase().equals("hydro")) {
                        isAllTrue = false;
                    }
                    if (!show_anemo && item.getElement().toLowerCase().equals("anemo")) {
                        isAllTrue = false;
                    }
                    if (!show_dendor && item.getElement().toLowerCase().equals("dendro")) {
                        isAllTrue = false;
                    }
                    if (!show_electro && item.getElement().toLowerCase().equals("electro")) {
                        isAllTrue = false;
                    }
                    if (!show_cryo && item.getElement().toLowerCase().equals("cryo")) {
                        isAllTrue = false;
                    }
                    if (!show_geo && item.getElement().toLowerCase().equals("geo")) {
                        isAllTrue = false;
                    }
                }

                if (isSingleWeapon == 1) {
                    if (show_sword && !item.getWeapon().toLowerCase().equals("sword")) {
                        isAllTrue = false;
                    }
                    if (show_claymore && !item.getWeapon().toLowerCase().equals("claymore")) {
                        isAllTrue = false;
                    }
                    if (show_polearm && !item.getWeapon().toLowerCase().equals("polearm")) {
                        isAllTrue = false;
                    }
                    if (show_bow && !item.getWeapon().toLowerCase().equals("bow")) {
                        isAllTrue = false;
                    }
                    if (show_catalyst && !item.getWeapon().toLowerCase().equals("catalyst")) {
                        isAllTrue = false;
                    }
                } else if ((show_sword == false && show_claymore == false && show_catalyst == false && show_bow == false && show_polearm == false) == false) {
                    if (!show_sword && item.getWeapon().toLowerCase().equals("sword")) {
                        isAllTrue = false;
                    }
                    if (!show_claymore && item.getWeapon().toLowerCase().equals("claymore")) {
                        isAllTrue = false;
                    }
                    if (!show_polearm && item.getWeapon().toLowerCase().equals("polearm")) {
                        isAllTrue = false;
                    }
                    if (!show_bow && item.getWeapon().toLowerCase().equals("bow")) {
                        isAllTrue = false;
                    }
                    if (!show_catalyst && item.getWeapon().toLowerCase().equals("catalyst")) {
                        isAllTrue = false;
                    }
                }

                if (isSingleRare == 1) {
                    if (show_rare4 && item.getRare() != 4) {
                        isAllTrue = false;
                    }
                    if (show_rare5 && item.getRare() != 5) {
                        isAllTrue = false;
                    }
                } else if ((show_rare4 == false && show_rare5 == false) == false) {
                    if (!show_rare4 && item.getRare() == 4) {
                        isAllTrue = false;
                    }
                    if (!show_rare5 && item.getRare() == 5) {
                        isAllTrue = false;
                    }
                }

                if (isSingleRelease == 1) {
                    if (show_released && item.getIsComing() != 0) {
                        isAllTrue = false;
                    }
                    if (show_unreleased && item.getIsComing() != 1) {
                        isAllTrue = false;
                    }
                } else if ((show_released == false && show_unreleased == false) == false) {
                    if (!show_released && item.getIsComing() == 0) {
                        isAllTrue = false;
                    }
                    if (!show_unreleased && item.getIsComing() == 1) {
                        isAllTrue = false;
                    }
                }

                if (isSingleRole == 1) {
                    if (show_dps && !item.getRole().equals("Main_DPS")) {
                        isAllTrue = false;
                    }
                    if (show_sub_dps && !item.getRole().equals("Support_DPS")) {
                        isAllTrue = false;
                    }
                    if (show_util && !item.getRole().equals("Utility")) {
                        isAllTrue = false;
                    }
                } else if ((show_dps == false && show_sub_dps == false && show_util == false) == false) {
                    if (!show_dps && item.getRole().equals("Main_DPS")) {
                        isAllTrue = false;
                    }
                    if (!show_sub_dps && item.getRole().equals("Support_DPS")) {
                        isAllTrue = false;
                    }
                    if (!show_util && item.getRole().equals("Utility")) {
                        isAllTrue = false;
                    }
                }

                if (isAllTrue == true) {
                    filteredList.add(item);
                }
            }
        }

        mItemList.removeAllViews();
        charactersAdapter.filterList(filteredList);
        editor.putBoolean("show_pyro", show_pyro);
        editor.putBoolean("show_hydro", show_hydro);
        editor.putBoolean("show_anemo", show_anemo);
        editor.putBoolean("show_electro", show_electro);
        editor.putBoolean("show_dendor", show_dendor);
        editor.putBoolean("show_cryo", show_cryo);
        editor.putBoolean("show_geo", show_geo);
        editor.putBoolean("show_sword", show_sword);
        editor.putBoolean("show_claymore", show_claymore);
        editor.putBoolean("show_polearm", show_polearm);
        editor.putBoolean("show_bow", show_bow);
        editor.putBoolean("show_catalyst", show_catalyst);
        editor.putBoolean("show_rare1", show_rare1);
        editor.putBoolean("show_rare2", show_rare2);
        editor.putBoolean("show_rare3", show_rare3);
        editor.putBoolean("show_rare4", show_rare4);
        editor.putBoolean("show_rare5", show_rare5);
        editor.putBoolean("show_released", show_released);
        editor.putBoolean("show_unreleased", show_unreleased);
        editor.putBoolean("show_dps", show_dps);
        editor.putBoolean("show_sub_dps", show_sub_dps);
        editor.putBoolean("show_util", show_util);
        editor.apply();
    }

    public void filterWeaponAlgothm(int star) {
        switch (star) {
            case 0:
                show_rare1 = false;
                show_rare2 = false;
                show_rare3 = false;
                show_rare4 = false;
                show_rare5 = false;
                break;
            case 1:
                show_rare1 = true;
                show_rare2 = false;
                show_rare3 = false;
                show_rare4 = false;
                show_rare5 = false;
                break;
            case 2:
                show_rare2 = true;
                show_rare1 = false;
                show_rare3 = false;
                show_rare4 = false;
                show_rare5 = false;
                break;
            case 3:
                show_rare3 = true;
                show_rare1 = false;
                show_rare2 = false;
                show_rare4 = false;
                show_rare5 = false;
                break;
            case 4:
                show_rare4 = true;
                show_rare1 = false;
                show_rare2 = false;
                show_rare3 = false;
                show_rare5 = false;
                break;
            case 5:
                show_rare5 = true;
                show_rare1 = false;
                show_rare2 = false;
                show_rare3 = false;
                show_rare4 = false;
                break;
        }

        ArrayList<Weapons> filteredList = new ArrayList<>();
        for (Weapons item : weaponsList) {
            // DEFAULT
            if ((show_sword == false && show_claymore == false && show_catalyst == false && show_bow == false && show_polearm == false) &&
                    (show_released == false && show_unreleased == false) &&
                    (show_rare1 == false && show_rare2 == false && show_rare3 == false && show_rare4 == false && show_rare5 == false)) {
                filteredList.add(item);
            } else {
                boolean isAllTrue = true;
                int isSingleWeapon = 0;
                int isSingleRare = 0;
                int isSingleRelease = 0;

                if (show_sword) {
                    isSingleWeapon = isSingleWeapon + 1;
                }
                if (show_claymore) {
                    isSingleWeapon = isSingleWeapon + 1;
                }
                if (show_polearm) {
                    isSingleWeapon = isSingleWeapon + 1;
                }
                if (show_bow) {
                    isSingleWeapon = isSingleWeapon + 1;
                }
                if (show_catalyst) {
                    isSingleWeapon = isSingleWeapon + 1;
                }

                if (show_rare1) {
                    isSingleRare = isSingleRare + 1;
                }
                if (show_rare2) {
                    isSingleRare = isSingleRare + 1;
                }
                if (show_rare3) {
                    isSingleRare = isSingleRare + 1;
                }
                if (show_rare4) {
                    isSingleRare = isSingleRare + 1;
                }
                if (show_rare5) {
                    isSingleRare = isSingleRare + 1;
                }
                if (show_released) {
                    isSingleRelease = isSingleRelease + 1;
                }
                if (show_unreleased) {
                    isSingleRelease = isSingleRelease + 1;
                }

                if (isSingleWeapon == 1) {
                    if (show_sword && !item.getWeapon().toLowerCase().equals("sword")) {
                        isAllTrue = false;
                    }
                    if (show_claymore && !item.getWeapon().toLowerCase().equals("claymore")) {
                        isAllTrue = false;
                    }
                    if (show_polearm && !item.getWeapon().toLowerCase().equals("polearm")) {
                        isAllTrue = false;
                    }
                    if (show_bow && !item.getWeapon().toLowerCase().equals("bow")) {
                        isAllTrue = false;
                    }
                    if (show_catalyst && !item.getWeapon().toLowerCase().equals("catalyst")) {
                        isAllTrue = false;
                    }
                } else if ((show_sword == false && show_claymore == false && show_catalyst == false && show_bow == false && show_polearm == false) == false) {
                    if (!show_sword && item.getWeapon().toLowerCase().equals("sword")) {
                        isAllTrue = false;
                    }
                    if (!show_claymore && item.getWeapon().toLowerCase().equals("claymore")) {
                        isAllTrue = false;
                    }
                    if (!show_polearm && item.getWeapon().toLowerCase().equals("polearm")) {
                        isAllTrue = false;
                    }
                    if (!show_bow && item.getWeapon().toLowerCase().equals("bow")) {
                        isAllTrue = false;
                    }
                    if (!show_catalyst && item.getWeapon().toLowerCase().equals("catalyst")) {
                        isAllTrue = false;
                    }
                }

                if (isSingleRare == 1) {
                    if (show_rare1 && item.getRare() != 1) {
                        isAllTrue = false;
                    }
                    if (show_rare2 && item.getRare() != 2) {
                        isAllTrue = false;
                    }
                    if (show_rare3 && item.getRare() != 3) {
                        isAllTrue = false;
                    }
                    if (show_rare4 && item.getRare() != 4) {
                        isAllTrue = false;
                    }
                    if (show_rare5 && item.getRare() != 5) {
                        isAllTrue = false;
                    }
                } else if ((show_rare1 == false && show_rare2 == false && show_rare3 == false && show_rare4 == false && show_rare5 == false) == false) {
                    if (!show_rare1 && item.getRare() == 1) {
                        isAllTrue = false;
                    }
                    if (!show_rare2 && item.getRare() == 2) {
                        isAllTrue = false;
                    }
                    if (!show_rare3 && item.getRare() == 3) {
                        isAllTrue = false;
                    }
                    if (!show_rare4 && item.getRare() == 4) {
                        isAllTrue = false;
                    }
                    if (!show_rare5 && item.getRare() == 5) {
                        isAllTrue = false;
                    }
                }

                if (isSingleRelease == 1) {
                    if (show_released && item.getIsComing() != 0) {
                        isAllTrue = false;
                    }
                    if (show_unreleased && item.getIsComing() != 1) {
                        isAllTrue = false;
                    }
                } else if ((show_released == false && show_unreleased == false) == false) {
                    if (!show_released && item.getIsComing() == 0) {
                        isAllTrue = false;
                    }
                    if (!show_unreleased && item.getIsComing() == 1) {
                        isAllTrue = false;
                    }
                }

                if (isAllTrue == true) {
                    filteredList.add(item);
                }
            }
        }
        mItemList.removeAllViews();
        weaponsAdapter.filterList(filteredList);
        editor.putBoolean("show_pyro", show_pyro);
        editor.putBoolean("show_hydro", show_hydro);
        editor.putBoolean("show_anemo", show_anemo);
        editor.putBoolean("show_electro", show_electro);
        editor.putBoolean("show_dendor", show_dendor);
        editor.putBoolean("show_cryo", show_cryo);
        editor.putBoolean("show_geo", show_geo);
        editor.putBoolean("show_sword", show_sword);
        editor.putBoolean("show_claymore", show_claymore);
        editor.putBoolean("show_polearm", show_polearm);
        editor.putBoolean("show_bow", show_bow);
        editor.putBoolean("show_catalyst", show_catalyst);
        editor.putBoolean("show_rare1", show_rare1);
        editor.putBoolean("show_rare2", show_rare2);
        editor.putBoolean("show_rare3", show_rare3);
        editor.putBoolean("show_rare4", show_rare4);
        editor.putBoolean("show_rare5", show_rare5);
        editor.putBoolean("show_released", show_released);
        editor.putBoolean("show_unreleased", show_unreleased);
        editor.putBoolean("show_dps", show_dps);
        editor.putBoolean("show_sub_dps", show_sub_dps);
        editor.putBoolean("show_util", show_util);
        editor.apply();
    }

    private void char_list_reload() {
        Log.wtf("DAAM", "YEE");
        String name, element, weapon, nation, sex, mainStat, role;
        int rare, isComing;
        charactersList.clear();

        String json_base = LoadData("db/char/char_list.json");
        //Get data from JSON
        try {
            JSONArray array = new JSONArray(json_base);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                name = object.getString("name");
                element = object.getString("element");
                weapon = object.getString("weapon");
                nation = object.getString("nation");
                sex = object.getString("sex");
                role = object.getString("role");
                mainStat = object.getString("mainStat");
                rare = object.getInt("rare");
                isComing = object.getInt("isComing");

                Characters characters = new Characters();
                characters.setName(name);
                characters.setElement(element);
                characters.setWeapon(weapon);
                characters.setNation(nation);
                characters.setSex(sex);
                characters.setRole(role);
                characters.setRare(rare);
                characters.setMainStat(mainStat);
                characters.setIsComing(isComing);
                if (!characters.getName().contains("Traveler")) {
                    charactersList.add(characters);
                }
            }
            charactersAdapter.filterList(charactersList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void weapon_list_reload() {
        Log.wtf("DAAM", "YEE");
        weaponsList.clear();
        String name, weapon, stat_1;
        int rare, isComing;

        String json_base = LoadData("db/weapons/weapon_list.json");
        //Get data from JSON
        try {
            JSONArray array = new JSONArray(json_base);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                name = object.getString("name");
                weapon = object.getString("weapon");
                stat_1 = object.getString("stat_1");
                rare = object.getInt("rare");
                isComing = object.getInt("isComing");

                Weapons weapons = new Weapons();
                weapons.setName(name);
                weapons.setWeapon(weapon);
                weapons.setRare(rare);
                weapons.setStat_1(stat_1);
                weapons.setIsComing(isComing);
                weaponsList.add(weapons);
            }
            weaponsAdapter.filterList(weaponsList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void readIndexRecord (){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                "_id",
                "type",
                "name",
                "beforeLvl",
                "afterLvl",
                "beforeBreakLvl",
                "afterBreakLvl",
                "beforeBreakUpLvl",
                "afterBreakUpLvl",
                "beforeSkill1Lvl",
                "afterSkill1Lvl",
                "beforeSkill2Lvl",
                "afterSkill2Lvl",
                "beforeSkill3Lvl",
                "afterSkill3Lvl",
                "follow",
                "rare",
                "isCal"
        };
        // How you want the results sorted in the resulting Cursor

        String sortOrder = "_id"+ " DESC";

        Cursor cursor = db.query(
                "SipTik",   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        calculatorItemList.clear();
        while(cursor.moveToNext()) {
            CalculatorItem calculatorDB = new CalculatorItem();
            calculatorDB.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            calculatorDB.setType(cursor.getString(cursor.getColumnIndexOrThrow("type")));
            calculatorDB.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            calculatorDB.setBeforeLvl(cursor.getInt(cursor.getColumnIndexOrThrow("beforeLvl")));
            calculatorDB.setAfterLvl(cursor.getInt(cursor.getColumnIndexOrThrow("afterLvl")));
            calculatorDB.setBeforeBreakLvl(cursor.getInt(cursor.getColumnIndexOrThrow("beforeBreakLvl")));
            calculatorDB.setAfterBreakLvl(cursor.getInt(cursor.getColumnIndexOrThrow("afterBreakLvl")));
            calculatorDB.setBeforeBreakUpLvl(getBooleanByInt(cursor.getInt(cursor.getColumnIndexOrThrow("beforeBreakUpLvl"))));
            calculatorDB.setAfterBreakUpLvl(getBooleanByInt(cursor.getInt(cursor.getColumnIndexOrThrow("afterBreakUpLvl"))));
            calculatorDB.setBeforeSkill1Lvl(cursor.getInt(cursor.getColumnIndexOrThrow("beforeSkill1Lvl")));
            calculatorDB.setAfterSkill1Lvl(cursor.getInt(cursor.getColumnIndexOrThrow("afterSkill1Lvl")));
            calculatorDB.setBeforeSkill2Lvl(cursor.getInt(cursor.getColumnIndexOrThrow("beforeSkill2Lvl")));
            calculatorDB.setAfterSkill2Lvl(cursor.getInt(cursor.getColumnIndexOrThrow("afterSkill2Lvl")));
            calculatorDB.setBeforeSkill3Lvl(cursor.getInt(cursor.getColumnIndexOrThrow("beforeSkill3Lvl")));
            calculatorDB.setAfterSkill3Lvl(cursor.getInt(cursor.getColumnIndexOrThrow("afterSkill3Lvl")));
            calculatorDB.setFollow(cursor.getString(cursor.getColumnIndexOrThrow("follow")));
            calculatorDB.setRare(cursor.getInt(cursor.getColumnIndexOrThrow("rare")));
            calculatorDB.setIsCal(getBooleanByInt(cursor.getInt(cursor.getColumnIndexOrThrow("isCal"))));

            choosedNameList.add(cursor.getString(cursor.getColumnIndexOrThrow("name")));

            calculatorItemList.add(calculatorDB);
        }
        cursor.close();
        mAdapter.filterList(calculatorItemList);
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

    private void insertNewRecord (String name , int rare , String type){
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int tmpMaxLvl = 70;
        int tmpMaxBreakLvl = 4;
        switch (rare){
            case 1 :
            case 2 : tmpMaxLvl = 70; tmpMaxBreakLvl = 4; break;
            case 3 :
            case 4 :
            case 5 : tmpMaxLvl = 90; tmpMaxBreakLvl = 6; break;
        }

        ContentValues values = new ContentValues();
        values.put("type", type);
        values.put("name", name);
        values.put("beforeLvl", 1);
        values.put("afterLvl", tmpMaxLvl);
        values.put("beforeBreakLvl", 0);
        values.put("afterBreakLvl", tmpMaxBreakLvl);
        values.put("beforeBreakUpLvl", String.valueOf((false) ? 1 : 0 ));
        values.put("afterBreakUpLvl", String.valueOf((true) ? 1 : 0 ));
        values.put("beforeSkill1Lvl", 1);
        values.put("afterSkill1Lvl", 10);
        values.put("beforeSkill2Lvl", 1);
        values.put("afterSkill2Lvl", 10);
        values.put("beforeSkill3Lvl", 1);
        values.put("afterSkill3Lvl", 10);
        values.put("follow", "N/A");
        values.put("rare", rare);
        values.put("isCal", String.valueOf((true) ? 1 : 0 ));

        db.insert("SipTik", null, values);
        readIndexRecord();
    }

    public void displayDataToUI(int id_T, String type_T, String name_T,
                                int beforeLvl_T, int afterLvl_T,
                                int beforeBreakLvl_T, int afterBreakLvl_T,
                                boolean beforeBreakUpLvl_T, boolean afterBreakUpLvl_T,
                                int beforeSkill1Lvl_T, int afterSkill1Lvl_T,
                                int beforeSkill2Lvl_T, int afterSkill2Lvl_T,
                                int beforeSkill3Lvl_T, int afterSkill3Lvl_T,
                                String follow_T, int rare_T, boolean isCal_T){

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

        bInit = true;
        aInit = true;
        sa1Init = true;
        sa2Init = true;
        sa3Init = true;
        sb1Init = true;
        sb2Init = true;
        sb3Init = true;

        //--------------------//
        Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.fragment_cal_item_detail_siptik, null);
        ItemRss item_rss = new ItemRss();
        CalculatorExtendSipTik ces = new CalculatorExtendSipTik();
        ces.setup(context,activity,view);

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                saveToDB();
            }
        });

        BackgroundReload.BackgroundReload(context,view);

        TextView item_name = view.findViewById(R.id.siptik_item_name);
        Spinner curr_tv = view.findViewById(R.id.db_curr_tv);
        Spinner aim_tv = view.findViewById(R.id.db_aim_tv);
        ImageView ico = view.findViewById(R.id.db_ico);

        TextView talent_title = view.findViewById(R.id.siptik_talent);
        LinearLayout talent_ll = view.findViewById(R.id.siptik_talent_ll);
        ImageView char_skill_ico1 = view.findViewById(R.id.char_skill_ico1);
        ImageView char_skill_ico2 = view.findViewById(R.id.char_skill_ico2);
        ImageView char_skill_ico3 = view.findViewById(R.id.char_skill_ico3);
        TextView char_skill_name1 = view.findViewById(R.id.char_skill_name1);
        TextView char_skill_name2 = view.findViewById(R.id.char_skill_name2);
        TextView char_skill_name3 = view.findViewById(R.id.char_skill_name3);
        Spinner char_skillBeforeLvl1 = view.findViewById(R.id.char_skillBeforeLvl1);
        Spinner char_skillBeforeLvl2 = view.findViewById(R.id.char_skillBeforeLvl2);
        Spinner char_skillBeforeLvl3 = view.findViewById(R.id.char_skillBeforeLvl3);
        Spinner char_skillAfterLvl1 = view.findViewById(R.id.char_skillAfterLvl1);
        Spinner char_skillAfterLvl2 = view.findViewById(R.id.char_skillAfterLvl2);
        Spinner char_skillAfterLvl3 = view.findViewById(R.id.char_skillAfterLvl3);

        ArrayAdapter lvl_char = new ArrayAdapter(context, R.layout.spinner_item_cal_2048, lvlListChar);
        ArrayAdapter lvl_skill = new ArrayAdapter(context, R.layout.spinner_item_cal_2048, lvlListSkill);
        ArrayAdapter lvl_weapon = new ArrayAdapter(context, R.layout.spinner_item_cal_2048, lvlListWeapon70);

        switch (rare){
            case 1:
            case 2:lvl_weapon = new ArrayAdapter(context, R.layout.spinner_item_cal_2048, lvlListWeapon70);break;
            case 3:
            case 4:
            case 5:lvl_weapon = new ArrayAdapter(context, R.layout.spinner_item_cal_2048, lvlListWeapon90);break;
        }

        lvl_skill.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);
        lvl_char.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);
        lvl_weapon.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);

        final Transformation transformation = new RoundedCornersTransformation(64, 0);


        switch (type){
            case "CHAR": {

                item_name.setText(item_rss.getCharByName(name,context)[1]);
                Picasso.get()
                        .load(FileLoader.loadIMG(item_rss.getCharByName(name, context)[3], context))
                        .fit()
                        .transform(transformation)
                        .error(R.drawable.paimon_lost)
                        .into(ico);

                String CharName_BASE_UNDERSCORE = name.replace(" ", "_");

                String lang = sharedPreferences.getString("curr_lang", "zh-HK");

                String is = null;
                String is_default = null;
                String result1 = null;

                is_default = LoadData("db/char/en-US/" + CharName_BASE_UNDERSCORE + ".json");
                is = LoadData("db/char/" + lang + "/" + CharName_BASE_UNDERSCORE + ".json");

                if (is != null) {
                    result1 = is;
                } else if (is_default != null) {
                    result1 = is_default;
                }


                if (result1 != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(result1);
                        JSONObject battle_talent = jsonObject.getJSONObject("battle_talent");
                        char_skill_name1.setText(battle_talent.getString("normal_name"));
                        char_skill_name2.setText(battle_talent.getString("element_name"));
                        char_skill_name3.setText(battle_talent.getString("final_name"));

                        char_skill_ico1.setImageDrawable(item_rss.getTalentIcoByName(battle_talent.getString("normal_img"), context));
                        char_skill_ico2.setImageDrawable(item_rss.getTalentIcoByName(battle_talent.getString("element_img"), context));
                        char_skill_ico3.setImageDrawable(item_rss.getTalentIcoByName(battle_talent.getString("final_img"), context));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    char_skill_name1.setText(getString(R.string.unknown));
                    char_skill_name2.setText(getString(R.string.unknown));
                    char_skill_name3.setText(getString(R.string.unknown));
                }
                break;
            }
            case "WEAPON" : {
                talent_ll.setVisibility(View.GONE);
                talent_title.setVisibility(View.GONE);

                item_name.setText(item_rss.getWeaponByName(name,context)[0]);
                Picasso.get()
                        .load(FileLoader.loadIMG(item_rss.getWeaponByName(name, context)[1], context))
                        .fit()
                        .transform(transformation)
                        .error(R.drawable.paimon_lost)
                        .into(ico);

                break;
            }


        }

        curr_tv.setAdapter(lvl_char);
        curr_tv.setSelection(getLvlPosByList(beforeLvl, beforeBreakUpLvl));
        curr_tv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idL) {
                Integer[] numsReturn = getNumByStrPlus(lvlListChar[position]);
                beforeLvl = numsReturn[0];
                beforeBreakLvl = numsReturn[1];
                if (numsReturn[2] != 0) {
                    beforeBreakUpLvl = true;
                } else {
                    beforeBreakUpLvl = false;
                }

                if (bInit == true){
                    bInit = false;
                    ces.cal_setup(id,type,name,beforeLvl,afterLvl,beforeBreakLvl,afterBreakLvl,beforeBreakUpLvl,afterBreakUpLvl,beforeSkill1Lvl,afterSkill1Lvl,beforeSkill2Lvl,afterSkill2Lvl,beforeSkill3Lvl,afterSkill3Lvl,follow,rare,isCal);
                }else if (bInit == false){
                    ces.cal_setup(id,type,name,beforeLvl,afterLvl,beforeBreakLvl,afterBreakLvl,beforeBreakUpLvl,afterBreakUpLvl,beforeSkill1Lvl,afterSkill1Lvl,beforeSkill2Lvl,afterSkill2Lvl,beforeSkill3Lvl,afterSkill3Lvl,follow,rare,isCal);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        aim_tv.setAdapter(lvl_char);
        aim_tv.setSelection(getLvlPosByList(afterLvl, afterBreakUpLvl));
        aim_tv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idL) {
                Integer[] numsReturn = getNumByStrPlus(lvlListChar[position]);
                afterLvl = numsReturn[0];
                afterBreakLvl = numsReturn[1];
                if (numsReturn[2] != 0) {
                    afterBreakUpLvl = true;
                } else {
                    afterBreakUpLvl = false;
                }

                if (aInit == true){
                    aInit = false;
                    ces.cal_setup(id,type,name,beforeLvl,afterLvl,beforeBreakLvl,afterBreakLvl,beforeBreakUpLvl,afterBreakUpLvl,beforeSkill1Lvl,afterSkill1Lvl,beforeSkill2Lvl,afterSkill2Lvl,beforeSkill3Lvl,afterSkill3Lvl,follow,rare,isCal);
                }else if (aInit == false){
                    ces.cal_setup(id,type,name,beforeLvl,afterLvl,beforeBreakLvl,afterBreakLvl,beforeBreakUpLvl,afterBreakUpLvl,beforeSkill1Lvl,afterSkill1Lvl,beforeSkill2Lvl,afterSkill2Lvl,beforeSkill3Lvl,afterSkill3Lvl,follow,rare,isCal);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        char_skillBeforeLvl1.setAdapter(lvl_skill);
        char_skillBeforeLvl1.setSelection(beforeSkill1Lvl-1);
        char_skillBeforeLvl1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idL) {
                beforeSkill1Lvl = position+1;
                if (sb1Init == true){
                    sb1Init = false;
                    ces.cal_setup(id,type,name,beforeLvl,afterLvl,beforeBreakLvl,afterBreakLvl,beforeBreakUpLvl,afterBreakUpLvl,beforeSkill1Lvl,afterSkill1Lvl,beforeSkill2Lvl,afterSkill2Lvl,beforeSkill3Lvl,afterSkill3Lvl,follow,rare,isCal);
                }else if (sb1Init == false){
                    ces.cal_setup(id,type,name,beforeLvl,afterLvl,beforeBreakLvl,afterBreakLvl,beforeBreakUpLvl,afterBreakUpLvl,beforeSkill1Lvl,afterSkill1Lvl,beforeSkill2Lvl,afterSkill2Lvl,beforeSkill3Lvl,afterSkill3Lvl,follow,rare,isCal);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        char_skillAfterLvl1.setAdapter(lvl_skill);
        char_skillAfterLvl1.setSelection(afterSkill1Lvl-1);
        char_skillAfterLvl1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idL) {
                afterSkill1Lvl = position+1;
                if (sa1Init == true){
                    sa1Init = false;
                    ces.cal_setup(id,type,name,beforeLvl,afterLvl,beforeBreakLvl,afterBreakLvl,beforeBreakUpLvl,afterBreakUpLvl,beforeSkill1Lvl,afterSkill1Lvl,beforeSkill2Lvl,afterSkill2Lvl,beforeSkill3Lvl,afterSkill3Lvl,follow,rare,isCal);
                }else if (sa1Init == false){
                    ces.cal_setup(id,type,name,beforeLvl,afterLvl,beforeBreakLvl,afterBreakLvl,beforeBreakUpLvl,afterBreakUpLvl,beforeSkill1Lvl,afterSkill1Lvl,beforeSkill2Lvl,afterSkill2Lvl,beforeSkill3Lvl,afterSkill3Lvl,follow,rare,isCal);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        char_skillBeforeLvl2.setAdapter(lvl_skill);
        char_skillBeforeLvl2.setSelection(beforeSkill2Lvl-1);
        char_skillBeforeLvl2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idL) {
                beforeSkill2Lvl = position+1;
                if (sb2Init == true){
                    sb1Init = false;
                    ces.cal_setup(id,type,name,beforeLvl,afterLvl,beforeBreakLvl,afterBreakLvl,beforeBreakUpLvl,afterBreakUpLvl,beforeSkill1Lvl,afterSkill1Lvl,beforeSkill2Lvl,afterSkill2Lvl,beforeSkill3Lvl,afterSkill3Lvl,follow,rare,isCal);
                }else if (sb2Init == false){
                    ces.cal_setup(id,type,name,beforeLvl,afterLvl,beforeBreakLvl,afterBreakLvl,beforeBreakUpLvl,afterBreakUpLvl,beforeSkill1Lvl,afterSkill1Lvl,beforeSkill2Lvl,afterSkill2Lvl,beforeSkill3Lvl,afterSkill3Lvl,follow,rare,isCal);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        char_skillAfterLvl2.setAdapter(lvl_skill);
        char_skillAfterLvl2.setSelection(afterSkill2Lvl-1);
        char_skillAfterLvl2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idL) {
                afterSkill2Lvl = position+1;
                if (sa2Init == true){
                    sa2Init = false;
                    ces.cal_setup(id,type,name,beforeLvl,afterLvl,beforeBreakLvl,afterBreakLvl,beforeBreakUpLvl,afterBreakUpLvl,beforeSkill1Lvl,afterSkill1Lvl,beforeSkill2Lvl,afterSkill2Lvl,beforeSkill3Lvl,afterSkill3Lvl,follow,rare,isCal);

                }else if (sa2Init == false){
                    ces.cal_setup(id,type,name,beforeLvl,afterLvl,beforeBreakLvl,afterBreakLvl,beforeBreakUpLvl,afterBreakUpLvl,beforeSkill1Lvl,afterSkill1Lvl,beforeSkill2Lvl,afterSkill2Lvl,beforeSkill3Lvl,afterSkill3Lvl,follow,rare,isCal);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        char_skillBeforeLvl3.setAdapter(lvl_skill);
        char_skillBeforeLvl3.setSelection(beforeSkill3Lvl-1);
        char_skillBeforeLvl3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idL) {
                beforeSkill3Lvl = position+1;
                if (sb3Init == true){
                    sb3Init = false;
                    ces.cal_setup(id,type,name,beforeLvl,afterLvl,beforeBreakLvl,afterBreakLvl,beforeBreakUpLvl,afterBreakUpLvl,beforeSkill1Lvl,afterSkill1Lvl,beforeSkill2Lvl,afterSkill2Lvl,beforeSkill3Lvl,afterSkill3Lvl,follow,rare,isCal);
                }else if (sb3Init == false){
                    ces.cal_setup(id,type,name,beforeLvl,afterLvl,beforeBreakLvl,afterBreakLvl,beforeBreakUpLvl,afterBreakUpLvl,beforeSkill1Lvl,afterSkill1Lvl,beforeSkill2Lvl,afterSkill2Lvl,beforeSkill3Lvl,afterSkill3Lvl,follow,rare,isCal);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        char_skillAfterLvl3.setAdapter(lvl_skill);
        char_skillAfterLvl3.setSelection(afterSkill3Lvl-1);
        char_skillAfterLvl3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idL) {
                afterSkill3Lvl = position+1;
                if (sa3Init == true){
                    sa3Init = false;
                    ces.cal_setup(id,type,name,beforeLvl,afterLvl,beforeBreakLvl,afterBreakLvl,beforeBreakUpLvl,afterBreakUpLvl,beforeSkill1Lvl,afterSkill1Lvl,beforeSkill2Lvl,afterSkill2Lvl,beforeSkill3Lvl,afterSkill3Lvl,follow,rare,isCal);
                }else if (sa3Init == false){
                    ces.cal_setup(id,type,name,beforeLvl,afterLvl,beforeBreakLvl,afterBreakLvl,beforeBreakUpLvl,afterBreakUpLvl,beforeSkill1Lvl,afterSkill1Lvl,beforeSkill2Lvl,afterSkill2Lvl,beforeSkill3Lvl,afterSkill3Lvl,follow,rare,isCal);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER_VERTICAL;
        dialogWindow.setAttributes(lp);
        dialog.show();

        isResultDisplay = true;

        ces.cal_setup(id,type,name,beforeLvl,afterLvl,beforeBreakLvl,afterBreakLvl,beforeBreakUpLvl,afterBreakUpLvl,beforeSkill1Lvl,afterSkill1Lvl,beforeSkill2Lvl,afterSkill2Lvl,beforeSkill3Lvl,afterSkill3Lvl,follow,rare,isCal);


    }

    private Integer[] getNumByStrPlus(String numStr) {
        int num = Integer.parseInt(numStr.replace("+", "").replace("Lv", ""));
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
            switch (numStr.replace("Lv", "")) {
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

    private String checkLvlPlus(Boolean isBreakUp) {
        if (isBreakUp) {
            return "+";
        } else return "";
    }

    private int getLvlPosByList(Integer lvlNum, Boolean isBreakUp) {
        int realLvl = lvlNum - 1;
        if (lvlNum > 20 || lvlNum == 20 && isBreakUp == true) {
            realLvl = realLvl + 1;
        }
        if (lvlNum > 40 || lvlNum == 40 && isBreakUp == true) {
            realLvl = realLvl + 1;
        }
        if (lvlNum > 50 || lvlNum == 50 && isBreakUp == true) {
            realLvl = realLvl + 1;
        }
        if (lvlNum > 60 || lvlNum == 60 && isBreakUp == true) {
            realLvl = realLvl + 1;
        }
        if (lvlNum > 70 || lvlNum == 70 && isBreakUp == true) {
            realLvl = realLvl + 1;
        }
        if (lvlNum > 80 || lvlNum == 80 && isBreakUp == true) {
            realLvl = realLvl + 1;
        }
        return realLvl;
    }

    private void dialogSetup() {
        dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.fragment_wait_dialog, null);

        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER_VERTICAL;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==DIALOG_DISMISS)
        {
            if (dialog != null){
                dialog.dismiss();
            }
        }
    }


    private long getIndexDBLength(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, "IndexDB");
        return count;
    }

    private boolean getBooleanByInt (int x){
        if(x == 0){
            return false;
        }else if(x == 1){
            return true;
        }else{
            return false;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (db != null) {
            db.close();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user_info",MODE_PRIVATE);
        sharedPreferences.getInt("curr_lang_pos",2);
        super.attachBaseContext(LangUtils.getAttachBaseContext(newBase, sharedPreferences.getInt("curr_lang_pos",2)));
    }


    public void saveToDB() {
        DataBaseHelper_SipTik dbHelper = new DataBaseHelper_SipTik(context);
        SQLiteDatabase db ;

        /**
         * Database Char Part
         */

        db = dbHelper.getReadableDatabase();
        String[] projection = {"name"};
        String selection = "_id = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(
                "SipTik",   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        // DEMO -> UPDATE demo SET ID = 1,Name = "SPP",Hint = "OK" WHERE Name = "Twitter";
        if(cursor.getCount()>0){
            db.execSQL("UPDATE "+"SipTik"+" SET "+
                    "beforeLvl = "+String.valueOf(beforeLvl)+","+
                    "afterLvl = "+String.valueOf(afterLvl)+","+

                    "beforeBreakLvl = "+String.valueOf(beforeBreakLvl)+","+
                    "afterBreakLvl = "+String.valueOf(afterBreakLvl)+","+

                    "beforeBreakUpLvl = "+String.valueOf((beforeBreakUpLvl) ? 1 : 0 )+","+
                    "afterBreakUpLvl = "+String.valueOf((afterBreakUpLvl) ? 1 : 0 )+","+

                    "beforeSkill1Lvl = "+String.valueOf(beforeSkill1Lvl)+","+
                    "afterSkill1Lvl = "+String.valueOf(afterSkill1Lvl)+","+

                    "beforeSkill2Lvl = "+String.valueOf(beforeSkill2Lvl)+","+
                    "afterSkill2Lvl = "+String.valueOf(afterSkill2Lvl)+","+

                    "beforeSkill3Lvl = "+String.valueOf(beforeSkill3Lvl)+","+
                    "afterSkill3Lvl = "+String.valueOf(afterSkill3Lvl)+","+

                    "follow = "+String.valueOf("\""+follow+"\"")+","+
                    "rare = "+String.valueOf(rare)+","+

                    "isCal = "+String.valueOf((isCal) ? 1 : 0 )+

                    " WHERE _id = \""+id+"\";");


            readIndexRecord();
        }
        cursor.close();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        readIndexRecord();
    }

}
