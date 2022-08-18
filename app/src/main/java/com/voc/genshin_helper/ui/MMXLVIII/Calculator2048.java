package com.voc.genshin_helper.ui.MMXLVIII;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.buff.CalculatorBuff;
import com.voc.genshin_helper.data.Artifacts;
import com.voc.genshin_helper.data.ArtifactsAdapter;
import com.voc.genshin_helper.data.Characters;
import com.voc.genshin_helper.data.CharactersAdapter;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.ScreenSizeUtils;
import com.voc.genshin_helper.data.Weapons;
import com.voc.genshin_helper.data.WeaponsAdapter;
import com.voc.genshin_helper.database.DataBaseHelper;
import com.voc.genshin_helper.databinding.ActivityMainBinding;
import com.voc.genshin_helper.ui.SipTik.DeskSipTik;
import com.voc.genshin_helper.util.BackgroundReload;
import com.voc.genshin_helper.util.CalculatorProcess;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.LangUtils;
import com.voc.genshin_helper.util.NumberPickerDialog;
import com.voc.genshin_helper.util.RoundedCornersTransformation;
import com.voc.genshin_helper.util.Spinner2048;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

public class Calculator2048 extends AppCompatActivity{

    /**
     * Method of requirements
     */
    ItemRss item_rss;
    ItemRss css;
    private ViewPager viewPager;
    private ArrayList<View> viewPager_List;
    Context context;
    public SharedPreferences sharedPreferences;
    SharedPreferences calShared; // Only record CalculatorUI's vars, user can get last time data when restart this page (ALSO CAN USE RESET BTN)
    SharedPreferences.Editor editor;

    static String[] lvlListChar = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "20+", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "40+", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "50+", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "60+", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "70+", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "80+", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90"};
    static String[] lvlListSkill = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
    static String[] lvlListArt = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};

    // Char Page
    RecyclerView mCharacterList;
    CharactersAdapter mCharAdapter;
    RecyclerView mArtifactList;
    ArtifactsAdapter mArtifactAdapter;
    RecyclerView mWeaponList;
    WeaponsAdapter mWeaponAdapter;
    public List<Characters> charactersList = new ArrayList<>();
    public List<Weapons> weaponsList = new ArrayList();
    public List<Artifacts> artifactsList = new ArrayList();
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

    /**
     * Method of Char Choosed List
     */
    public ArrayList<String> choosedNameList = new ArrayList<String>();
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
    public ArrayList<Boolean> choosedHasWeapon = new ArrayList<Boolean>();
    //public ArrayList<Integer> choosedWeaponIdList= new ArrayList<Integer>();
    //public ArrayList<Integer> choosedFlowerIdList= new ArrayList<Integer>();
    //public ArrayList<Integer> choosedPlumeIdList= new ArrayList<Integer>();
    //public ArrayList<Integer> choosedSandIdList= new ArrayList<Integer>();
    //public ArrayList<Integer> choosedGobletIdList= new ArrayList<Integer>();
    //public ArrayList<Integer> choosedCircletIdList= new ArrayList<Integer>();

    /** Method of Char's details' container */
    /**
     * Since String can't be null, so there will have "XPR" for identify is result correct
     */

    // Battle Talent
    String normal_name = "Unknown";
    String element_name = "Unknown";
    String final_name = "Unknown";

    /**
     * Calculator vars -> Might change to int[] which sort by char update time
     */
    int before_lvl = 1;
    int after_lvl = 90;
    int before_break = 0;
    int after_break = 6;
    int skill1_lvl = 1;
    int skill2_lvl = 1;
    int skill3_lvl = 1;

    /**
     * Method of Weapon Choosed List
     */
    public ArrayList<String> weaponChoosedNameList = new ArrayList<>();
    public ArrayList<Integer> weaponChoosedIdList = new ArrayList<>();
    public ArrayList<Integer> weaponChoosedBeforeLvlList = new ArrayList<>();
    public ArrayList<Integer> weaponChoosedAfterLvlList = new ArrayList<>();
    public ArrayList<Integer> weaponChoosedBeforeBreakLvlList = new ArrayList<>();
    public ArrayList<Boolean> weaponChoosedBeforeBreakUPLvlList = new ArrayList<>();
    public ArrayList<Integer> weaponChoosedAfterBreakLvlList = new ArrayList<>();
    public ArrayList<Boolean> weaponChoosedAfterBreakUPLvlList = new ArrayList<>();
    public ArrayList<String> weaponChoosedFollowList = new ArrayList<>();
    public ArrayList<String> weaponChoosedTypeList = new ArrayList<>();
    public ArrayList<Boolean> weaponChoosedIsCal = new ArrayList<>();
    public ArrayList<Integer> weaponChoosedRare = new ArrayList<>(); // INNER USE ONLY

    /**
     * Method of Artifact Choosed List
     */
    public ArrayList<String> artifactChoosedNameList = new ArrayList<>();
    public ArrayList<Integer> artifactChoosedIdList = new ArrayList<>();
    public ArrayList<Integer> artifactChoosedBeforeLvlList = new ArrayList<>();
    public ArrayList<Integer> artifactChoosedAfterLvlList = new ArrayList<>();
    public ArrayList<String> artifactChoosedFollowList = new ArrayList<>();
    public ArrayList<Boolean> artifactChoosedIsCal = new ArrayList<>();
    public ArrayList<Integer> artifactChoosedRare = new ArrayList<>();
    public ArrayList<String> artifactChoosedType = new ArrayList<>();

    View viewPager0, viewPager1, viewPager2;
    //View viewPager3,viewPager4;
    ImageView ui_cal_char, ui_cal_weapon, ui_cal_art, ui_back, ui_add;
    TextView ui_title;
    FrameLayout ui_cal;


    String normal_skill_name = "Unknown";
    String element_skill_name = "Unknown";
    String final_skill_name = "Unknown";
    String normal_skill_img = "Unknown";
    String element_skill_img = "Unknown";
    String final_skill_img = "Unknown";
    String follow_char_tmp = "Unknown";
    String tmp_artifact_type = "N/A";
    int position_tmp = 0;

    String dataSheetName = "NaN";

    public ArrayList<Boolean> charHasWeapon = new ArrayList<>();
    public ArrayList<Boolean> charHasFlower = new ArrayList<>();
    public ArrayList<Boolean> charHasPlume = new ArrayList<>();
    public ArrayList<Boolean> charHasSand = new ArrayList<>();
    public ArrayList<Boolean> charHasGoblet = new ArrayList<>();
    public ArrayList<Boolean> charHasCirclet = new ArrayList<>();

    public ArrayList<String> charUseSword = new ArrayList<String>();
    public ArrayList<String> charUseClaymore = new ArrayList<String>();
    public ArrayList<String> charUseBow = new ArrayList<String>();
    public ArrayList<String> charUseCatalyst = new ArrayList<String>();
    public ArrayList<String> charUsePolearm = new ArrayList<String>();
    public ArrayList<String> charNameTranslated = new ArrayList<String>();

    Activity activity;
    boolean isNight = false;
    String type = "Flower";
    Dialog dialogX = null;
    Dialog dialogResult = null;

    ArrayList<String> tmpCharName = new ArrayList<String>();
    ArrayList<Integer> tmpCharRare = new ArrayList<Integer>();

    public final static int DIALOG_DISMISS = 16384;
    public final static int REFRESH_DB_LIST = 8192;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cal_ui_2048);

        long beforeRun = System.currentTimeMillis();
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        css = new ItemRss();
        item_rss = new ItemRss();
        context = this;
        activity = this;
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // Background of item
        if (context.getResources().getString(R.string.mode).equals("Night")) {
            isNight = true;
        }

        BackgroundReload.BackgroundReload(context, activity);
        /**
         * INIT OF TRANSFER
         */

        Bundle extras = getIntent().getExtras();

        dataSheetName = (String) extras.getString("dataSheetName");
        Cursor cursor = db.query(
                dataSheetName+"_char",   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()) {
            choosedNameList.add(cursor.getString(cursor.getColumnIndexOrThrow("charName")));

            choosedBeforeLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charBeforeLvl")));
            choosedAfterLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charAfterLvl")));

            choosedBeforeBreakLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charBeforeBreakLvl")));
            choosedAfterBreakLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charAfterBreakLvl")));

            choosedBeforeBreakUPLvlList.add(getBooleanByInt(cursor.getInt(cursor.getColumnIndexOrThrow("charBeforeBreakUpLvl"))));
            choosedAfterBreakUPLvlList.add(getBooleanByInt(cursor.getInt(cursor.getColumnIndexOrThrow("charAfterBreakUpLvl"))));

            choosedBeforeSkill1LvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charBeforeSkill1Lvl")));
            choosedAfterSkill1LvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charAfterSkill1Lvl")));

            choosedBeforeSkill2LvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charBeforeSkill2Lvl")));
            choosedAfterSkill2LvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charAfterSkill2Lvl")));

            choosedBeforeSkill3LvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charBeforeSkill3Lvl")));
            choosedAfterSkill3LvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charAfterSkill3Lvl")));

            choosedIsCal.add(getBooleanByInt(cursor.getInt(cursor.getColumnIndexOrThrow("charIsCal"))));
        }
        cursor.close();

        /**
         * READ DATA -> Weapon
         */

        cursor = db.query(
                dataSheetName+"_weapon",   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()) {
            weaponChoosedIdList.add(cursor.getInt(cursor.getColumnIndexOrThrow("weaponId")));
            weaponChoosedNameList.add(cursor.getString(cursor.getColumnIndexOrThrow("weaponName")));

            weaponChoosedBeforeLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("weaponBeforeLvl")));
            weaponChoosedAfterLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("weaponAfterLvl")));

            weaponChoosedBeforeBreakLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("weaponBeforeBreakLvl")));
            weaponChoosedAfterBreakLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("weaponAfterBreakLvl")));

            weaponChoosedBeforeBreakUPLvlList.add(getBooleanByInt(cursor.getInt(cursor.getColumnIndexOrThrow("weaponBeforeBreakUpLvl"))));
            weaponChoosedAfterBreakUPLvlList.add(getBooleanByInt(cursor.getInt(cursor.getColumnIndexOrThrow("weaponAfterBreakUpLvl"))));

            weaponChoosedRare.add(cursor.getInt(cursor.getColumnIndexOrThrow("weaponRare")));
            weaponChoosedFollowList.add(cursor.getString(cursor.getColumnIndexOrThrow("weaponFollow")));
            weaponChoosedIsCal.add(getBooleanByInt(cursor.getInt(cursor.getColumnIndexOrThrow("weaponIsCal"))));
        }
        cursor.close();

        /**
         * READ DATA -> Artifact
         */

        cursor = db.query(
                dataSheetName+"_artifact",   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()) {
            artifactChoosedIdList.add(cursor.getInt(cursor.getColumnIndexOrThrow("artifactId")));
            artifactChoosedNameList.add(cursor.getString(cursor.getColumnIndexOrThrow("artifactName")));

            artifactChoosedBeforeLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("artifactBeforeLvl")));
            artifactChoosedAfterLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("artifactAfterLvl")));

            artifactChoosedFollowList.add(cursor.getString(cursor.getColumnIndexOrThrow("artifactFollow")));
            artifactChoosedRare.add(cursor.getInt(cursor.getColumnIndexOrThrow("artifactRare")));
            artifactChoosedIsCal.add(getBooleanByInt(cursor.getInt(cursor.getColumnIndexOrThrow("artifactIsCal"))));
            artifactChoosedType.add(cursor.getString(cursor.getColumnIndexOrThrow("artifactType")));
        }
        cursor.close();

        while(cursor.moveToNext()) {
            artifactChoosedIdList.add(cursor.getInt(cursor.getColumnIndexOrThrow("artifactId")));
            artifactChoosedNameList.add(cursor.getString(cursor.getColumnIndexOrThrow("artifactName")));

            artifactChoosedBeforeLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("artifactBeforeLvl")));
            artifactChoosedAfterLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("artifactAfterLvl")));

            artifactChoosedFollowList.add(cursor.getString(cursor.getColumnIndexOrThrow("artifactFollow")));
            artifactChoosedRare.add(cursor.getInt(cursor.getColumnIndexOrThrow("artifactRare")));
            artifactChoosedIsCal.add(getBooleanByInt(cursor.getInt(cursor.getColumnIndexOrThrow("artifactIsCal"))));
            artifactChoosedType.add(cursor.getString(cursor.getColumnIndexOrThrow("artifactType")));
        }
        cursor.close();

        /*
        for (int x = 0; x < choosedNameList.size(); x++) {
            charHasFlower.add(false);
            charHasPlume.add(false);
            charHasSand.add(false);
            charHasGoblet.add(false);
            charHasCirclet.add(false);
            charHasWeapon.add(false);
        }

        for (int x = 0; x < choosedNameList.size(); x++) {
            for (int y = 0; y < artifactChoosedFollowList.size(); y++) {
                if (choosedNameList.get(x).equals(artifactChoosedFollowList.get(y))) {
                    switch (artifactChoosedType.get(y)) {
                        case "Flower":
                            charHasFlower.set(x, true);
                            break;
                        case "Plume":
                            charHasPlume.set(x, true);
                            break;
                        case "Sand":
                            charHasSand.set(x, true);
                            break;
                        case "Goblet":
                            charHasGoblet.set(x, true);
                            break;
                        case "Circlet":
                            charHasCirclet.set(x, true);
                            break;
                    }
                }
            }
        }
        */

        /**
         * INIT (UI)
         */

        final LayoutInflater mInflater = getLayoutInflater().from(this);
        viewPager0 = mInflater.inflate(R.layout.fragment_cal_char_2048, null, false);
        viewPager1 = mInflater.inflate(R.layout.fragment_cal_weapon_2048, null, false);
        viewPager2 = mInflater.inflate(R.layout.fragment_cal_art_2048, null, false);

        viewPager_List = new ArrayList<View>();
        viewPager_List.add(viewPager0);
        viewPager_List.add(viewPager1);
        viewPager_List.add(viewPager2);

        viewPager.setAdapter(new MyViewPagerAdapter(viewPager_List));
        viewPager.setCurrentItem(0);

        sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        calShared = getSharedPreferences("cal_ui", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        ui_cal_char = findViewById(R.id.ui_cal_char);
        ui_cal_weapon = findViewById(R.id.ui_cal_weapon);
        ui_cal_art = findViewById(R.id.ui_cal_art);
        ui_title = findViewById(R.id.ui_title);
        ui_back = findViewById(R.id.ui_back);
        ui_add = findViewById(R.id.ui_add);
        ui_cal = findViewById(R.id.ui_cal);

        ui_title.setText(dataSheetName);
        ui_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDB();
                finish();
            }
        });

        ui_cal_char.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
                ui_cal_char.setImageResource(R.drawable.ic_2048_char_intro_btn_selected);
                ui_cal_weapon.setImageResource(R.drawable.ic_2048_weapon_intro_btn);
                ui_cal_art.setImageResource(R.drawable.ic_2048_artifact_intro_btn);
            }
        });
        ui_cal_weapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
                ui_cal_char.setImageResource(R.drawable.ic_2048_char_intro_btn);
                ui_cal_weapon.setImageResource(R.drawable.ic_2048_weapon_intro_btn_selected);
                ui_cal_art.setImageResource(R.drawable.ic_2048_artifact_intro_btn);
            }
        });
        ui_cal_art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
                ui_cal_char.setImageResource(R.drawable.ic_2048_char_intro_btn);
                ui_cal_weapon.setImageResource(R.drawable.ic_2048_weapon_intro_btn);
                ui_cal_art.setImageResource(R.drawable.ic_2048_artifact_intro_btn_selected);
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0: {
                        ui_cal_char.setImageResource(R.drawable.ic_2048_char_intro_btn_selected);
                        ui_cal_weapon.setImageResource(R.drawable.ic_2048_weapon_intro_btn);
                        ui_cal_art.setImageResource(R.drawable.ic_2048_artifact_intro_btn);
                        break;
                    }
                    case 1: {
                        ui_cal_char.setImageResource(R.drawable.ic_2048_char_intro_btn);
                        ui_cal_weapon.setImageResource(R.drawable.ic_2048_weapon_intro_btn_selected);
                        ui_cal_art.setImageResource(R.drawable.ic_2048_artifact_intro_btn);
                        break;
                    }
                    case 2: {
                        ui_cal_char.setImageResource(R.drawable.ic_2048_char_intro_btn);
                        ui_cal_weapon.setImageResource(R.drawable.ic_2048_weapon_intro_btn);
                        ui_cal_art.setImageResource(R.drawable.ic_2048_artifact_intro_btn_selected);
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initCharWeaponType(context);

        displayCharData();
        displayWeaponData();
        displayArtifactData();
        ui_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.fragment_cal_choose_2048, null);
                dialogX = dialog;
                mCharAdapter = new CharactersAdapter(context,charactersList,activity);
                mWeaponAdapter = new WeaponsAdapter(context,weaponsList,activity);
                mArtifactAdapter = new ArtifactsAdapter(context,artifactsList,activity);

                switch (viewPager.getCurrentItem()){
                    case 0 : addSetup(view,"char");break;
                    case 1 : addSetup(view,"weapon");break;
                    case 2 : addSetup(view,"art");break;
                }

                dialog.setContentView(view);
                dialog.setCanceledOnTouchOutside(true);
                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                // 2O48 DESIGN
                dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_2048));
                dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                lp.gravity = Gravity.CENTER;
                dialogWindow.setAttributes(lp);
                dialog.show();
            }
        });

        ui_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.fragment_cal_result_2048, null);
                dialogResult = dialog;

                BackgroundReload.BackgroundReload(context,view);
                ImageView result_back = view.findViewById(R.id.result_back);
                TextView result_title = view.findViewById(R.id.result_title);
                LinearLayout db_char_ll = view.findViewById(R.id.cal_char_ll);
                LinearLayout db_weapon_ll = view.findViewById(R.id.cal_weapon_ll);
                LinearLayout db_art_ll = view.findViewById(R.id.cal_art_ll);

                saveToDB();

                CalculatorExtend2048 calculatorExtend2048 = new CalculatorExtend2048();
                calculatorExtend2048.setup(context,activity,view,dataSheetName);
                calculatorExtend2048.char_setup(choosedNameList,choosedBeforeLvlList,choosedAfterLvlList,choosedBeforeBreakLvlList,choosedAfterBreakLvlList,choosedBeforeSkill1LvlList,choosedAfterSkill1LvlList,choosedBeforeSkill2LvlList,choosedAfterSkill2LvlList,choosedBeforeSkill3LvlList,choosedAfterSkill3LvlList,choosedIsCal,choosedBeforeBreakUPLvlList,choosedAfterBreakUPLvlList,"READ");
                calculatorExtend2048.artifact_setup(artifactChoosedNameList,artifactChoosedBeforeLvlList,artifactChoosedAfterLvlList,artifactChoosedIsCal,artifactChoosedFollowList,artifactChoosedRare,artifactChoosedType,artifactChoosedIdList,"READ");
                calculatorExtend2048.weapon_setup(weaponChoosedNameList,weaponChoosedBeforeLvlList,weaponChoosedAfterLvlList,weaponChoosedBeforeBreakLvlList,weaponChoosedAfterBreakLvlList,weaponChoosedIsCal,weaponChoosedBeforeBreakUPLvlList,weaponChoosedAfterBreakUPLvlList,weaponChoosedFollowList,weaponChoosedRare,weaponChoosedIdList,"READ");

                result_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogResult.dismiss();
                    }
                });
                result_title.setText(dataSheetName);
                db_char_ll.removeAllViews();
                db_weapon_ll.removeAllViews();
                db_art_ll.removeAllViews();

                for (int x = 0 ; x < choosedNameList.size() ; x++){
                    if(choosedIsCal.get(x)){
                        View char_view = LayoutInflater.from(context).inflate(R.layout.item_img_2048, db_char_ll, false);
                        ImageView item_img = char_view.findViewById(R.id.item_img);

                        String json_base = LoadData("db/char/char_require_asc_skill.json");
                        String name = "";
                        int rare = 1;
                        int tmp_cnt = 0;
                        try {
                            JSONArray array = new JSONArray(json_base);
                            while(!name.equals(choosedNameList.get(x))) {
                                JSONObject object = array.getJSONObject(tmp_cnt);
                                name = object.getString("name");
                                rare = object.getInt("rare");
                                tmp_cnt ++;
                            }

                            final int radius = 180;
                            final int margin = 0;
                            final Transformation transformation = new RoundedCornersTransformation(radius, margin);

                            switch (rare){
                                case 1 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                                case 2 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_2s);break;
                                case 3 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_3s);break;
                                case 4 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);break;
                                case 5 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);break;
                                default:  item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                            }

                            Picasso.get()
                                    .load (FileLoader.loadIMG(item_rss.getCharByName(choosedNameList.get(x),context)[3],context))
                                    .transform(transformation)
                                    .fit()
                                    .error (R.drawable.paimon_full)
                                    .into (item_img);

                            // if character is exist in list
                            //tick.setVisibility(View.VISIBLE);
                            String finalName = name;
                            item_img.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Characters_Info_2048 cif = new Characters_Info_2048();
                                    cif.setup(finalName,context,activity);
                                }
                            });

                            db_char_ll.addView(char_view);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                for (int x = 0 ; x < weaponChoosedNameList.size() ; x++){
                    if(weaponChoosedIsCal.get(x)){
                        View char_view = LayoutInflater.from(context).inflate(R.layout.item_img_2048, db_weapon_ll, false);
                        ImageView item_img = char_view.findViewById(R.id.item_img);

                        String name = "";
                        int rare = 1;

                        final int radius = 180;
                        final int margin = 0;
                        final Transformation transformation = new RoundedCornersTransformation(radius, margin);

                        switch (weaponChoosedRare.get(x)){
                            case 1 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                            case 2 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_2s);break;
                            case 3 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_3s);break;
                            case 4 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);break;
                            case 5 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);break;
                            default:  item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                        }

                        Picasso.get()
                                .load (FileLoader.loadIMG(item_rss.getWeaponByName(weaponChoosedNameList.get(x),context)[1],context))
                                .transform(transformation)
                                .fit()
                                .error (R.drawable.paimon_full)
                                .into (item_img);

                        // if character is exist in list
                        //tick.setVisibility(View.VISIBLE);
                        String finalName = weaponChoosedNameList.get(x);
                        item_img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Weapon_Info_2048 wif = new Weapon_Info_2048();
                                wif.setup(finalName,context,activity);
                            }
                        });

                        db_weapon_ll.addView(char_view);
                    }
                }

                for (int x = 0 ; x < artifactChoosedNameList.size() ; x++){
                    if(artifactChoosedIsCal.get(x)){
                        View char_view = LayoutInflater.from(context).inflate(R.layout.item_img_2048, db_art_ll, false);
                        ImageView item_img = char_view.findViewById(R.id.item_img);

                        String name = "";
                        int rare = 1;

                        final int radius = 180;
                        final int margin = 0;
                        final Transformation transformation = new RoundedCornersTransformation(radius, margin);

                        switch (artifactChoosedRare.get(x)){
                            case 1 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                            case 2 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_2s);break;
                            case 3 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_3s);break;
                            case 4 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);break;
                            case 5 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);break;
                            default:  item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                        }

                        int tmp_artifact_type_id = 1;
                        if(artifactChoosedType.get(x).equals("Flower")){
                            tmp_artifact_type_id = 4;
                        }else if(artifactChoosedType.get(x).equals("Plume")){
                            tmp_artifact_type_id = 2;
                        }else if(artifactChoosedType.get(x).equals("Sand")){
                            tmp_artifact_type_id = 5;
                        }else if(artifactChoosedType.get(x).equals("Goblet")){
                            tmp_artifact_type_id = 1;
                        }else if(artifactChoosedType.get(x).equals("Circlet")){
                            tmp_artifact_type_id = 3;
                        }

                        Picasso.get()
                                .load (FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(artifactChoosedNameList.get(x)),context)[tmp_artifact_type_id],context))
                                .transform(transformation)
                                .fit()
                                .error (R.drawable.paimon_full)
                                .into (item_img);

                        // if character is exist in list
                        //tick.setVisibility(View.VISIBLE);
                        String finalName = artifactChoosedNameList.get(x);
                        item_img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Artifact_Info_2048 aif = new Artifact_Info_2048();
                                aif.setup(finalName,context,activity);
                            }
                        });

                        db_art_ll.addView(char_view);
                    }
                }

                dialog.setContentView(view);
                dialog.setCanceledOnTouchOutside(true);
                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                // 2O48 DESIGN
                dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_2048));
                dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                lp.gravity = Gravity.CENTER;
                dialogWindow.setAttributes(lp);
                dialog.show();
            }
        });


        //long afterRun = System.currentTimeMillis();
        //System.out.println("READ ALL Total Cost "+(afterRun-beforeRun)+"ms");
        Intent intent=new Intent();
        setResult(DIALOG_DISMISS,intent);
    }

    public void addSetup(View view, String type){
        BackgroundReload.BackgroundReload(context, view);
        RecyclerView mList = view.findViewById(R.id.main_list);

        switch (type){
            case "char" : mCharacterList = mList;break;
            case "weapon" : mWeaponList = mList;break;
            case "art" : mArtifactList = mList;break;
        }

        char_list_reload();
        weapon_list_reload();
        artifact_list_reload();

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
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
        mList.setLayoutManager(mLayoutManager);
        mList.setLayoutParams(paramsMsg);

        switch (type){
            case "char" : mList.setAdapter(mCharAdapter);break;
            case "weapon" : mList.setAdapter(mWeaponAdapter);break;
            case "art" : mList.setAdapter(mArtifactAdapter);break;

        }
        mList.removeAllViewsInLayout();

        ImageView char_back = view.findViewById(R.id.char_back);
        char_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogX.dismiss();
            }
        });

        ImageView char_search = view.findViewById(R.id.char_search);
        char_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout header_con = view.findViewById(R.id.header_con);
                View header_search = view.findViewById(R.id.header_search);
                EditText header_search_et = view.findViewById(R.id.header_search_et);
                Button menu_search_cancel = view.findViewById(R.id.menu_search_cancel);
                ImageView header_search_reset = view.findViewById(R.id.header_search_reset);

                header_con.animate()
                        .alpha(0.0f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                header_con.setVisibility(View.GONE);
                            }
                        });

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

                                switch (type){
                                    case "char" : {
                                        int x = 0;
                                        for (Characters item : charactersList) {
                                            String str = request.toLowerCase();
                                            if (css.getCharByName(item.getName(), context)[1].contains(str) || css.getCharByName(item.getName(), context)[1].toLowerCase().contains(str) || item.getName().toLowerCase().contains(str)) { // EN -> ZH
                                                filteredListC.add(item);
                                            }
                                            x = x + 1;
                                        }
                                        mCharAdapter.filterList(filteredListC);
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
                                        mWeaponAdapter.filterList(filteredListW);
                                        break;
                                    }
                                    case "art" : {

                                        int x = 0;
                                        for (Artifacts item : artifactsList) {
                                            String str = request.toLowerCase();
                                            if (css.getArtifactByName(item.getName(),context)[0].contains(str)||css.getArtifactByName(item.getName(),context)[0].toLowerCase().contains(str)||css.getArtifactByName(item.getName(),context)[0].toUpperCase().contains(str)||item.getName().toLowerCase().contains(str)){ // EN -> ZH
                                                filteredListA.add(item);
                                            }
                                            x = x +1;
                                        }
                                        mArtifactAdapter.filterList(filteredListA);
                                        break;
                                    }
                                }

                            } else {
                                switch (type){
                                    case "char" : mCharAdapter.filterList(charactersList);break;
                                    case "weapon" : mWeaponAdapter.filterList(weaponsList);break;
                                    case "art" : mArtifactAdapter.filterList(artifactsList);break;
                                }
                            }
                        } else {
                            switch (type){
                                case "char" : mCharAdapter.filterList(charactersList);break;
                                case "weapon" : mWeaponAdapter.filterList(weaponsList);break;
                                case "art" : mArtifactAdapter.filterList(artifactsList);break;
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
                        header_con.animate()
                                .alpha(1.0f)
                                .setDuration(300)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        header_con.setVisibility(View.VISIBLE);
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
            case "char" : {
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

                                mCharAdapter.filterList(charactersList);

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
            case "weapon" : {
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

                                mWeaponAdapter.filterList(weaponsList);

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
            case "art" : {
                ImageView artifact_filter = view.findViewById(R.id.char_filter);
                artifact_filter.setOnClickListener(new View.OnClickListener() {
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
                        TextView menu_weapons_title_tv = view.findViewById(R.id.menu_weapons_title_tv);
                        LinearLayout menu_weapons_ll = view.findViewById(R.id.menu_weapons_ll);
                        menu_weapons_title_tv.setVisibility(View.GONE);
                        menu_weapons_ll.setVisibility(View.GONE);

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

                                mArtifactAdapter.filterList(artifactsList);

                            }
                        });

                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (menu_release_0.isChecked()){show_released = true;}else{show_released = false;}
                                if (menu_release_1.isChecked()){show_unreleased = true;}else{show_unreleased = false;}
                                filterArtifactAlgothm((int) menu_rating.getRating());
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
    }

    public void displayCharData() {

        long beforeRun = System.currentTimeMillis();

        // SQL DATA DISPLAY
        // Character
        LinearLayout cal_choosed_list = viewPager0.findViewById(R.id.cal_choosed_list);
        cal_choosed_list.removeAllViews();
        for (int x = 0; x < choosedNameList.size(); x++) {
            int finalX = x;
            View char_view = LayoutInflater.from(context).inflate(R.layout.item_calculator_char_2048, cal_choosed_list, false);
            ImageView char_ico = char_view.findViewById(R.id.char_ico);
            TextView char_name = char_view.findViewById(R.id.char_name);
            TextView char_lvl = char_view.findViewById(R.id.char_lvl);
            Spinner char_beforeLvl = char_view.findViewById(R.id.char_beforeLvl);
            Spinner char_afterLvl = char_view.findViewById(R.id.char_afterLvl);
            Spinner char_skillBeforeLvl1 = char_view.findViewById(R.id.char_skillBeforeLvl1);
            Spinner char_skillBeforeLvl2 = char_view.findViewById(R.id.char_skillBeforeLvl2);
            Spinner char_skillBeforeLvl3 = char_view.findViewById(R.id.char_skillBeforeLvl3);
            Spinner char_skillAfterLvl1 = char_view.findViewById(R.id.char_skillAfterLvl1);
            Spinner char_skillAfterLvl2 = char_view.findViewById(R.id.char_skillAfterLvl2);
            Spinner char_skillAfterLvl3 = char_view.findViewById(R.id.char_skillAfterLvl3);
            ImageView char_cal = char_view.findViewById(R.id.char_cal);
            LinearLayout char_bg = char_view.findViewById(R.id.char_bg);
            TextView char_skill_name1 = char_view.findViewById(R.id.char_skill_name1);
            TextView char_skill_name2 = char_view.findViewById(R.id.char_skill_name2);
            TextView char_skill_name3 = char_view.findViewById(R.id.char_skill_name3);
            ImageView char_skill_ico1 = char_view.findViewById(R.id.char_skill_ico1);
            ImageView char_skill_ico2 = char_view.findViewById(R.id.char_skill_ico2);
            ImageView char_skill_ico3 = char_view.findViewById(R.id.char_skill_ico3);
            TextView char_skill_lvl1 = char_view.findViewById(R.id.char_skill_lvl1);
            TextView char_skill_lvl2 = char_view.findViewById(R.id.char_skill_lvl2);
            TextView char_skill_lvl3 = char_view.findViewById(R.id.char_skill_lvl3);
            ImageView char_delete = char_view.findViewById(R.id.char_delete);

            char_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataBaseHelper dbHelper = new DataBaseHelper(context);
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    // Define 'where' part of query.
                    String selection = "charName" + " LIKE ?";
                    // Specify arguments in placeholder order.
                    String[] selectionArgs = { choosedNameList.get(finalX) };
                    // Issue SQL statement.
                    db.delete(dataSheetName+"_char", selection, selectionArgs);

                    choosedNameList.remove(finalX);
                    choosedBeforeLvlList.remove(finalX);
                    choosedAfterLvlList.remove(finalX);
                    choosedBeforeBreakLvlList.remove(finalX);
                    choosedAfterBreakLvlList.remove(finalX);
                    choosedBeforeSkill1LvlList.remove(finalX);
                    choosedAfterSkill1LvlList.remove(finalX);
                    choosedBeforeSkill2LvlList.remove(finalX);
                    choosedAfterSkill2LvlList.remove(finalX);
                    choosedBeforeSkill3LvlList.remove(finalX);
                    choosedAfterSkill3LvlList.remove(finalX);
                    choosedIsCal.remove(finalX);
                    choosedBeforeBreakUPLvlList.remove(finalX);
                    choosedAfterBreakUPLvlList.remove(finalX);

                    saveToDB();
                    initCharWeaponType(context);
                    displayCharData();
                    displayWeaponData();
                    displayArtifactData();
                }
            });

            switch (tmpCharRare.get(tmpCharName.indexOf(choosedNameList.get(x)))) {
                case 4:
                    if (isNight) {
                        char_bg.setBackgroundResource(R.drawable.rare4_800x1000_dark);
                    } else {
                        char_bg.setBackgroundResource(R.drawable.rare4_800x1000_light);
                    }
                    break;
                case 5:
                    if (isNight) {
                        char_bg.setBackgroundResource(R.drawable.rare5_800x1000_dark);
                    } else {
                        char_bg.setBackgroundResource(R.drawable.rare5_800x1000_light);
                    }
                    break;
                default:
                    if (isNight) {
                        char_bg.setBackgroundResource(R.drawable.rare1_800x1000_dark);
                    } else {
                        char_bg.setBackgroundResource(R.drawable.rare1_800x1000_light);
                    }
                    break;

            }

            String CharName_BASE_UNDERSCORE = choosedNameList.get(x).replace(" ", "_");

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
                    normal_skill_name = battle_talent.getString("normal_name");
                    element_skill_name = battle_talent.getString("element_name");
                    final_skill_name = battle_talent.getString("final_name");
                    normal_skill_img = battle_talent.getString("normal_img");
                    element_skill_img = battle_talent.getString("element_img");
                    final_skill_img = battle_talent.getString("final_img");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                normal_skill_name = getString(R.string.unknown);
                element_skill_name = getString(R.string.unknown);
                final_skill_name = getString(R.string.unknown);
            }


            Picasso.get()
                    .load(FileLoader.loadIMG(item_rss.getCharByName(choosedNameList.get(x), context)[3], context))
                    .fit()
                    .error(R.drawable.paimon_lost)
                    .into(char_ico);
            char_ico.setPadding(0, 8, 0, 0);

            ArrayAdapter lvl_char = new ArrayAdapter(context, R.layout.spinner_item_cal_2048, lvlListChar);
            lvl_char.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);
            ArrayAdapter lvl_skill = new ArrayAdapter(context, R.layout.spinner_item_cal_2048, lvlListSkill);
            lvl_skill.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);
            char_beforeLvl.setAdapter(lvl_char);
            char_afterLvl.setAdapter(lvl_char);
            char_skillBeforeLvl1.setAdapter(lvl_skill);
            char_skillBeforeLvl2.setAdapter(lvl_skill);
            char_skillBeforeLvl3.setAdapter(lvl_skill);
            char_skillAfterLvl1.setAdapter(lvl_skill);
            char_skillAfterLvl2.setAdapter(lvl_skill);
            char_skillAfterLvl3.setAdapter(lvl_skill);

            char_name.setText(item_rss.getCharByName(choosedNameList.get(x), context)[1]);
            char_beforeLvl.setSelection(getLvlPosByList(choosedBeforeLvlList.get(x), choosedBeforeBreakUPLvlList.get(x)));
            char_afterLvl.setSelection(getLvlPosByList(choosedAfterLvlList.get(x), choosedAfterBreakUPLvlList.get(x)));
            char_skillBeforeLvl1.setSelection(choosedBeforeSkill1LvlList.get(x) - 1);
            char_skillBeforeLvl2.setSelection(choosedBeforeSkill2LvlList.get(x) - 1);
            char_skillBeforeLvl3.setSelection(choosedBeforeSkill3LvlList.get(x) - 1);
            char_skillAfterLvl1.setSelection(choosedAfterSkill1LvlList.get(x) - 1);
            char_skillAfterLvl2.setSelection(choosedAfterSkill2LvlList.get(x) - 1);
            char_skillAfterLvl3.setSelection(choosedAfterSkill3LvlList.get(x) - 1);
            char_skill_name1.setText(normal_skill_name);
            char_skill_name2.setText(element_skill_name);
            char_skill_name3.setText(final_skill_name);
            char_skill_ico1.setImageDrawable(item_rss.getTalentIcoByName(normal_skill_img, context));
            char_skill_ico2.setImageDrawable(item_rss.getTalentIcoByName(element_skill_img, context));
            char_skill_ico3.setImageDrawable(item_rss.getTalentIcoByName(final_skill_img, context));
            char_skill_lvl1.setText("Lv." + choosedBeforeSkill1LvlList.get(x));
            char_skill_lvl2.setText("Lv." + choosedBeforeSkill2LvlList.get(x));
            char_skill_lvl3.setText("Lv." + choosedBeforeSkill3LvlList.get(x));
            if (choosedIsCal.get(x) == true) {
                char_cal.setImageResource(R.drawable.ic_2048_need_tick);
            } else {
                char_cal.setImageResource(R.drawable.ic_2048_no_tick);
            }

            char_cal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (choosedIsCal.get(finalX) == true) {
                        char_cal.setImageResource(R.drawable.ic_2048_no_tick);
                        choosedIsCal.set(finalX, false);
                    } else {
                        char_cal.setImageResource(R.drawable.ic_2048_need_tick);
                        choosedIsCal.set(finalX, true);
                    }
                }
            });

            char_beforeLvl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Integer[] numsReturn = getNumByStrPlus(lvlListChar[position]);
                    choosedBeforeLvlList.set(finalX, numsReturn[0]);
                    choosedBeforeBreakLvlList.set(finalX, numsReturn[1]);
                    if (numsReturn[2] != 0) {
                        choosedBeforeBreakUPLvlList.set(finalX, true);
                    } else {
                        choosedBeforeBreakUPLvlList.set(finalX, false);
                    }

                    char_lvl.setText("Lv." + choosedBeforeLvlList.get(finalX) + checkLvlPlus(choosedBeforeBreakUPLvlList.get(finalX)));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            char_afterLvl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Integer[] numsReturn = getNumByStrPlus(lvlListChar[position]);
                    choosedAfterLvlList.set(finalX, numsReturn[0]);
                    choosedAfterBreakLvlList.set(finalX, numsReturn[1]);
                    if (numsReturn[2] != 0) {
                        choosedAfterBreakUPLvlList.set(finalX, true);
                    } else {
                        choosedAfterBreakUPLvlList.set(finalX, false);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            char_skillBeforeLvl1.setOnItemSelectedListener(spinnerListener(0, char_skillAfterLvl1, choosedBeforeSkill1LvlList, choosedAfterSkill1LvlList, finalX, char_skill_lvl1));
            char_skillBeforeLvl2.setOnItemSelectedListener(spinnerListener(0, char_skillAfterLvl2, choosedBeforeSkill2LvlList, choosedAfterSkill2LvlList, finalX, char_skill_lvl2));
            char_skillBeforeLvl3.setOnItemSelectedListener(spinnerListener(0, char_skillAfterLvl3, choosedBeforeSkill3LvlList, choosedAfterSkill3LvlList, finalX, char_skill_lvl3));
            char_skillAfterLvl1.setOnItemSelectedListener(spinnerListener(1, char_skillAfterLvl1, choosedAfterSkill1LvlList, choosedBeforeSkill1LvlList, finalX, null));
            char_skillAfterLvl2.setOnItemSelectedListener(spinnerListener(1, char_skillAfterLvl2, choosedAfterSkill2LvlList, choosedBeforeSkill2LvlList, finalX, null));
            char_skillAfterLvl3.setOnItemSelectedListener(spinnerListener(1, char_skillAfterLvl3, choosedAfterSkill3LvlList, choosedBeforeSkill3LvlList, finalX, null));

            cal_choosed_list.addView(char_view);
        }
        //long afterRun = System.currentTimeMillis();
        //System.out.println("Read CHAR Total Cost "+(afterRun-beforeRun)+"ms");


        saveToDB();
        //beforeRun = System.currentTimeMillis();
        //afterRun = System.currentTimeMillis();
        //System.out.println("SAVE DB Total Cost "+(afterRun-beforeRun)+"ms");
    }

    public void displayWeaponData() {
        long beforeRun = System.currentTimeMillis();
        // SQL Data Display
        // Weapon
        LinearLayout cal_choosed_list = viewPager1.findViewById(R.id.cal_choosed_list);
        cal_choosed_list.removeAllViews();
        for (int x = 0; x < weaponChoosedNameList.size(); x++) {
            int finalX = x;
            View char_view = LayoutInflater.from(context).inflate(R.layout.item_calculator_weapon_2048, cal_choosed_list, false);
            ImageView weapon_ico = char_view.findViewById(R.id.weapon_ico);
            LinearLayout weapon_bg = char_view.findViewById(R.id.weapon_bg);
            TextView weapon_name = char_view.findViewById(R.id.weapon_name);
            TextView weapon_lvl = char_view.findViewById(R.id.weapon_lvl);
            Spinner weapon_beforeLvl = char_view.findViewById(R.id.weapon_beforeLvl);
            Spinner weapon_afterLvl = char_view.findViewById(R.id.weapon_afterLvl);
            Spinner weapon_spinner = char_view.findViewById(R.id.weapon_spinner);
            ImageView weapon_cal = char_view.findViewById(R.id.weapon_cal);
            ImageView weapon_delete = char_view.findViewById(R.id.weapon_delete);

            weapon_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataBaseHelper dbHelper = new DataBaseHelper(context);
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    // Define 'where' part of query.
                    String selection = "weaponId = ?";
                    // Specify arguments in placeholder order.
                    String[] selectionArgs = {String.valueOf(weaponChoosedIdList.get(finalX))};
                    // Issue SQL statement.
                    db.delete(dataSheetName+"_weapon", selection, selectionArgs);

                    weaponChoosedNameList.remove(finalX);
                    weaponChoosedBeforeLvlList.remove(finalX);
                    weaponChoosedAfterLvlList.remove(finalX);
                    weaponChoosedBeforeBreakLvlList.remove(finalX);
                    weaponChoosedAfterBreakLvlList.remove(finalX);
                    weaponChoosedBeforeBreakUPLvlList.remove(finalX);
                    weaponChoosedAfterBreakUPLvlList.remove(finalX);
                    weaponChoosedRare.remove(finalX);
                    weaponChoosedIsCal.remove(finalX);
                    weaponChoosedFollowList.remove(finalX);

                    saveToDB();
                    cursorWeaponIds();
                    initCharWeaponType(context);
                    displayWeaponData();
                }
            });

            Picasso.get()
                    .load(FileLoader.loadIMG(item_rss.getWeaponByName(weaponChoosedNameList.get(x), context)[1], context))
                    .fit()
                    .error(R.drawable.paimon_lost)
                    .into(weapon_ico);
            weapon_ico.setPadding(0, 8, 0, 0);
            weapon_name.setText(item_rss.getWeaponByName(weaponChoosedNameList.get(x), context)[0]);
            weapon_lvl.setText("Lv." + weaponChoosedBeforeLvlList.get(x) + checkLvlPlus(weaponChoosedBeforeBreakUPLvlList.get(x)));

            ArrayAdapter lvl_weapon = new ArrayAdapter(context, R.layout.spinner_item_cal_2048, lvlListChar);
            lvl_weapon.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);
            weapon_beforeLvl.setAdapter(lvl_weapon);
            weapon_afterLvl.setAdapter(lvl_weapon);
            weapon_beforeLvl.setSelection(getLvlPosByList(weaponChoosedBeforeLvlList.get(x), weaponChoosedBeforeBreakUPLvlList.get(x)));
            weapon_afterLvl.setSelection(getLvlPosByList(weaponChoosedAfterLvlList.get(x), weaponChoosedAfterBreakUPLvlList.get(x)));

            if (weaponChoosedIsCal.get(x) == true) {
                weapon_cal.setImageResource(R.drawable.ic_2048_need_tick);
            } else {
                weapon_cal.setImageResource(R.drawable.ic_2048_no_tick);
            }

            weapon_cal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (weaponChoosedIsCal.get(finalX) == true) {
                        weapon_cal.setImageResource(R.drawable.ic_2048_no_tick);
                        weaponChoosedIsCal.set(finalX, false);
                    } else {
                        weapon_cal.setImageResource(R.drawable.ic_2048_need_tick);
                        weaponChoosedIsCal.set(finalX, true);
                    }
                }
            });

            weapon_beforeLvl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Integer[] numsReturn = getNumByStrPlus(lvlListChar[position]);
                    weaponChoosedBeforeLvlList.set(finalX, numsReturn[0]);
                    weaponChoosedBeforeBreakLvlList.set(finalX, numsReturn[1]);
                    if (numsReturn[2] != 0) {
                        weaponChoosedBeforeBreakUPLvlList.set(finalX, true);
                    } else {
                        weaponChoosedBeforeBreakUPLvlList.set(finalX, false);
                    }

                    weapon_lvl.setText("Lv." + weaponChoosedBeforeLvlList.get(finalX) + checkLvlPlus(weaponChoosedBeforeBreakUPLvlList.get(finalX)));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            weapon_afterLvl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Integer[] numsReturn = getNumByStrPlus(lvlListChar[position]);
                    weaponChoosedAfterLvlList.set(finalX, numsReturn[0]);
                    weaponChoosedAfterBreakLvlList.set(finalX, numsReturn[1]);
                    if (numsReturn[2] != 0) {
                        weaponChoosedAfterBreakUPLvlList.set(finalX, true);
                    } else {
                        weaponChoosedAfterBreakUPLvlList.set(finalX, false);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            switch (weaponChoosedRare.get(x)) {
                case 1:
                    if (isNight) {
                        weapon_bg.setBackgroundResource(R.drawable.rare1_800x1000_dark);
                    } else {
                        weapon_bg.setBackgroundResource(R.drawable.rare1_800x1000_light);
                    }
                    break;
                case 2:
                    if (isNight) {
                        weapon_bg.setBackgroundResource(R.drawable.rare2_800x1000_dark);
                    } else {
                        weapon_bg.setBackgroundResource(R.drawable.rare2_800x1000_light);
                    }
                    break;
                case 3:
                    if (isNight) {
                        weapon_bg.setBackgroundResource(R.drawable.rare3_800x1000_dark);
                    } else {
                        weapon_bg.setBackgroundResource(R.drawable.rare3_800x1000_light);
                    }
                    break;
                case 4:
                    if (isNight) {
                        weapon_bg.setBackgroundResource(R.drawable.rare4_800x1000_dark);
                    } else {
                        weapon_bg.setBackgroundResource(R.drawable.rare4_800x1000_light);
                    }
                    break;
                case 5:
                    if (isNight) {
                        weapon_bg.setBackgroundResource(R.drawable.rare5_800x1000_dark);
                    } else {
                        weapon_bg.setBackgroundResource(R.drawable.rare5_800x1000_light);
                    }
                    break;
                default:
                    if (isNight) {
                        weapon_bg.setBackgroundResource(R.drawable.rare1_800x1000_dark);
                    } else {
                        weapon_bg.setBackgroundResource(R.drawable.rare1_800x1000_light);
                    }
                    break;

            }


            // Check which char is suitable to use this weapon
            // 1. Char Weapon Type

            try {
                String json_base = LoadData("db/weapons/weapon_list.json");
                String name = "";
                String weapon = "Sword";
                JSONObject object = null;
                int rare = 1;
                int tmp_cnt = 0;
                JSONArray array = new JSONArray(json_base);
                while (!name.equals(weaponChoosedNameList.get(x)) && tmp_cnt < array.length()) {
                    object = array.getJSONObject(tmp_cnt);
                    name = object.getString("name");
                    tmp_cnt++;
                }
                int tmp_pos = 0;
                ArrayAdapter char_aa = new ArrayAdapter(context, R.layout.spinner_item_cal_2048, charUseSword);

                switch (object.getString("weapon")) {
                    case "Sword":{
                        char_aa = new ArrayAdapter(context, R.layout.spinner_item_cal_2048_spec, charUseSword);
                        char_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);
                        weapon_spinner.setAdapter(char_aa);
                        tmp_pos = 0;
                        while (!item_rss.getCharNameByTranslatedName(charUseSword.get(tmp_pos), context).equals(weaponChoosedFollowList.get(x)) && tmp_pos < charUseSword.size()-1) {
                            tmp_pos++;
                        }
                        if(!item_rss.getCharNameByTranslatedName(charUseSword.get(tmp_pos), context).equals(weaponChoosedFollowList.get(x))){
                            weapon_spinner.setSelection(0);
                        }else{
                            weapon_spinner.setSelection(tmp_pos);
                        }
                        break;}
                    case "Bow":{
                        char_aa = new ArrayAdapter(context, R.layout.spinner_item_cal_2048_spec, charUseBow);
                        char_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);
                        weapon_spinner.setAdapter(char_aa);

                        tmp_pos = 0;
                        while (!item_rss.getCharNameByTranslatedName(charUseBow.get(tmp_pos), context).equals(weaponChoosedFollowList.get(x)) && tmp_pos < charUseBow.size()-1) {
                            tmp_pos++;
                        }
                        if(!item_rss.getCharNameByTranslatedName(charUseBow.get(tmp_pos), context).equals(weaponChoosedFollowList.get(x))){
                            weapon_spinner.setSelection(0);
                        }else{
                            weapon_spinner.setSelection(tmp_pos);
                        }
                        break;}
                    case "Claymore":{
                        char_aa = new ArrayAdapter(context, R.layout.spinner_item_cal_2048_spec, charUseClaymore);
                        char_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);
                        weapon_spinner.setAdapter(char_aa);

                        tmp_pos = 0;
                        while (!item_rss.getCharNameByTranslatedName(charUseClaymore.get(tmp_pos), context).equals(weaponChoosedFollowList.get(x)) && tmp_pos < charUseClaymore.size()-1) {
                            tmp_pos++;
                        }
                        if(!item_rss.getCharNameByTranslatedName(charUseClaymore.get(tmp_pos), context).equals(weaponChoosedFollowList.get(x))){
                            weapon_spinner.setSelection(0);
                        }else{
                            weapon_spinner.setSelection(tmp_pos);
                        }
                        break;}
                    case "Polearm":{
                        char_aa = new ArrayAdapter(context, R.layout.spinner_item_cal_2048_spec, charUsePolearm);
                        char_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);
                        weapon_spinner.setAdapter(char_aa);

                        tmp_pos = 0;
                        while (!item_rss.getCharNameByTranslatedName(charUsePolearm.get(tmp_pos), context).equals(weaponChoosedFollowList.get(x)) && tmp_pos < charUsePolearm.size()-1) {
                            tmp_pos++;

                        }
                        if(!item_rss.getCharNameByTranslatedName(charUsePolearm.get(tmp_pos), context).equals(weaponChoosedFollowList.get(x))){
                            weapon_spinner.setSelection(0);
                        }else{
                            weapon_spinner.setSelection(tmp_pos);
                        }
                        break;}
                    case "Catalyst":{
                        char_aa = new ArrayAdapter(context, R.layout.spinner_item_cal_2048_spec, charUseCatalyst);
                        char_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);
                        weapon_spinner.setAdapter(char_aa);

                        tmp_pos = 0;
                        while (!item_rss.getCharNameByTranslatedName(charUseCatalyst.get(tmp_pos), context).equals(weaponChoosedFollowList.get(x)) && tmp_pos < charUseCatalyst.size()-1) {
                            tmp_pos++;
                        }
                        if(!item_rss.getCharNameByTranslatedName(charUseCatalyst.get(tmp_pos), context).equals(weaponChoosedFollowList.get(x))){
                            weapon_spinner.setSelection(0);
                        }else{
                            weapon_spinner.setSelection(tmp_pos);
                        }
                        break;}
                }

                weapon_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        //System.out.println(finalX + " WeaponType : "+weaponChoosedTypeList);
                        //System.out.println(finalX + " Weapons : "+weaponChoosedNameList);
                        switch (weaponChoosedTypeList.get(finalX)) {
                            case "Sword":
                                //System.out.println(finalX + " S Before : "+weaponChoosedFollowList);
                                weaponChoosedFollowList.set(finalX, item_rss.getCharNameByTranslatedName(charUseSword.get(position), context));
                                //System.out.println(finalX + " S After : "+weaponChoosedFollowList);
                                break;
                            case "Bow":
                                //System.out.println(finalX + " B Before : "+weaponChoosedFollowList);
                                weaponChoosedFollowList.set(finalX, item_rss.getCharNameByTranslatedName(charUseBow.get(position), context));
                                //System.out.println(finalX + " B After : "+weaponChoosedFollowList);
                                break;
                            case "Claymore":
                                //System.out.println(finalX + " Cl Before : "+weaponChoosedFollowList);
                                weaponChoosedFollowList.set(finalX, item_rss.getCharNameByTranslatedName(charUseClaymore.get(position), context));
                                //System.out.println(finalX + " After : "+weaponChoosedFollowList);
                                break;
                            case "Polearm":
                                //System.out.println(finalX + " P Before : "+weaponChoosedFollowList);
                                weaponChoosedFollowList.set(finalX, item_rss.getCharNameByTranslatedName(charUsePolearm.get(position), context));
                                //System.out.println(finalX + " After : "+weaponChoosedFollowList);
                                break;
                            case "Catalyst":
                                //System.out.println(finalX + " Ca Before : "+weaponChoosedFollowList);
                                weaponChoosedFollowList.set(finalX, item_rss.getCharNameByTranslatedName(charUseCatalyst.get(position), context));
                                //System.out.println(finalX + " Ca After : "+weaponChoosedFollowList);
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }

            cal_choosed_list.addView(char_view);
        }

        saveToDB();
        //long afterRun = System.currentTimeMillis();
        //System.out.println("READ Weapon Total Cost "+(afterRun-beforeRun)+"ms");
    }


    private void displayArtifactData() {

        long beforeRun = System.currentTimeMillis();
        // SQL Data Display
        // Weapon
        LinearLayout cal_choosed_list = viewPager2.findViewById(R.id.cal_choosed_list);
        cal_choosed_list.removeAllViews();
        for (int x = 0; x < artifactChoosedNameList.size(); x++) {
            int finalX = x;
            View char_view = LayoutInflater.from(context).inflate(R.layout.item_calculator_artifact_2048, cal_choosed_list, false);
            ImageView art_ico = char_view.findViewById(R.id.art_ico);
            LinearLayout art_bg = char_view.findViewById(R.id.art_bg);
            TextView art_name = char_view.findViewById(R.id.art_name);
            TextView art_lvl = char_view.findViewById(R.id.art_lvl);
            Spinner art_beforeLvl = char_view.findViewById(R.id.art_beforeLvl);
            Spinner art_afterLvl = char_view.findViewById(R.id.art_afterLvl);
            Spinner art_spinner = char_view.findViewById(R.id.art_spinner);
            ImageView art_cal = char_view.findViewById(R.id.art_cal);
            ImageView art_flower = char_view.findViewById(R.id.art_flower);
            ImageView art_plume = char_view.findViewById(R.id.art_plume);
            ImageView art_sand = char_view.findViewById(R.id.art_sand);
            ImageView art_goblet = char_view.findViewById(R.id.art_goblet);
            ImageView art_circlet = char_view.findViewById(R.id.art_circlet);
            ImageView art_delete = char_view.findViewById(R.id.art_delete);

            art_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataBaseHelper dbHelper = new DataBaseHelper(context);
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    // Define 'where' part of query.
                    String selection = "artifactId" + " LIKE ?";
                    // Specify arguments in placeholder order.
                    String[] selectionArgs = {String.valueOf(artifactChoosedIdList.get(finalX))};
                    // Issue SQL statement.
                    db.delete(dataSheetName+"_artifact", selection, selectionArgs);

                    artifactChoosedNameList.remove(finalX);
                    artifactChoosedBeforeLvlList.remove(finalX);
                    artifactChoosedAfterLvlList.remove(finalX);
                    artifactChoosedRare.remove(finalX);
                    artifactChoosedIsCal.remove(finalX);
                    artifactChoosedFollowList.remove(finalX);
                    artifactChoosedType.remove(finalX);

                    cursorArtifactIds();
                    initCharWeaponType(context);
                    displayArtifactData();
                }
            });

            art_flower.setImageResource(R.drawable.ic_2048_flower);
            art_plume.setImageResource(R.drawable.ic_2048_plume);
            art_sand.setImageResource(R.drawable.ic_2048_sand);
            art_goblet.setImageResource(R.drawable.ic_2048_goblet);
            art_circlet.setImageResource(R.drawable.ic_2048_circlet);

            int tmp_artifact_type_id = 1;

            switch (artifactChoosedType.get(x)) {
                case "Flower":
                    tmp_artifact_type_id = 4;
                    art_flower.setImageResource(R.drawable.ic_2048_flower_selected);
                    break;
                case "Plume":
                    tmp_artifact_type_id = 2;
                    art_plume.setImageResource(R.drawable.ic_2048_plume_selected);
                    break;
                case "Sand":
                    tmp_artifact_type_id = 5;
                    art_sand.setImageResource(R.drawable.ic_2048_sand_selected);
                    break;
                case "Goblet":
                    tmp_artifact_type_id = 1;
                    art_goblet.setImageResource(R.drawable.ic_2048_goblet_selected);
                    break;
                case "Circlet":
                    tmp_artifact_type_id = 3;
                    art_circlet.setImageResource(R.drawable.ic_2048_circlet_selected);
                    break;
            }

            Picasso.get()
                    .load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(artifactChoosedNameList.get(x)), context)[tmp_artifact_type_id], context))
                    .fit()
                    .error(R.drawable.paimon_lost)
                    .into(art_ico);

            art_ico.setPadding(0, 8, 0, 0);
            art_name.setText(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(artifactChoosedNameList.get(x)), context)[0]);
            art_lvl.setText("Lv." + artifactChoosedBeforeLvlList.get(x));

            ArrayAdapter lvl_art = new ArrayAdapter(context, R.layout.spinner_item_cal_2048, lvlListArt);
            lvl_art.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);
            art_beforeLvl.setAdapter(lvl_art);
            art_afterLvl.setAdapter(lvl_art);
            art_beforeLvl.setSelection(artifactChoosedBeforeLvlList.get(x) - 1);
            art_afterLvl.setSelection(artifactChoosedAfterLvlList.get(x) - 1);

            if (artifactChoosedIsCal.get(x) == true) {
                art_cal.setImageResource(R.drawable.ic_2048_need_tick);
            } else {
                art_cal.setImageResource(R.drawable.ic_2048_no_tick);
            }

            art_cal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (artifactChoosedIsCal.get(finalX) == true) {
                        art_cal.setImageResource(R.drawable.ic_2048_no_tick);
                        artifactChoosedIsCal.set(finalX, false);
                    } else {
                        art_cal.setImageResource(R.drawable.ic_2048_need_tick);
                        artifactChoosedIsCal.set(finalX, true);
                    }
                }
            });

            art_beforeLvl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    artifactChoosedBeforeLvlList.set(finalX, position + 1);
                    art_lvl.setText("Lv." + artifactChoosedBeforeLvlList.get(finalX));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            art_afterLvl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    artifactChoosedAfterLvlList.set(finalX, position + 1);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            switch (artifactChoosedRare.get(x)) {
                case 1:
                    if (isNight) {
                        art_bg.setBackgroundResource(R.drawable.rare1_800x1000_dark);
                    } else {
                        art_bg.setBackgroundResource(R.drawable.rare1_800x1000_light);
                    }
                    break;
                case 2:
                    if (isNight) {
                        art_bg.setBackgroundResource(R.drawable.rare2_800x1000_dark);
                    } else {
                        art_bg.setBackgroundResource(R.drawable.rare2_800x1000_light);
                    }
                    break;
                case 3:
                    if (isNight) {
                        art_bg.setBackgroundResource(R.drawable.rare3_800x1000_dark);
                    } else {
                        art_bg.setBackgroundResource(R.drawable.rare3_800x1000_light);
                    }
                    break;
                case 4:
                    if (isNight) {
                        art_bg.setBackgroundResource(R.drawable.rare4_800x1000_dark);
                    } else {
                        art_bg.setBackgroundResource(R.drawable.rare4_800x1000_light);
                    }
                    break;
                case 5:
                    if (isNight) {
                        art_bg.setBackgroundResource(R.drawable.rare5_800x1000_dark);
                    } else {
                        art_bg.setBackgroundResource(R.drawable.rare5_800x1000_light);
                    }
                    break;
                default:
                    if (isNight) {
                        art_bg.setBackgroundResource(R.drawable.rare1_800x1000_dark);
                    } else {
                        art_bg.setBackgroundResource(R.drawable.rare1_800x1000_light);
                    }
                    break;

            }

            // Check which char is suitable to use this art
            // 1. Char Weapon Type
            ArrayAdapter char_aa = new ArrayAdapter(context, R.layout.spinner_item_cal_2048_spec, charNameTranslated);
            char_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_cal_2048);
            art_spinner.setAdapter(char_aa);

            int tmp_pos = 0;
            while (!item_rss.getCharNameByTranslatedName(charNameTranslated.get(tmp_pos), context).equals(artifactChoosedFollowList.get(x)) && tmp_pos < choosedNameList.size()) {
                tmp_pos++;
            }
            art_spinner.setSelection(tmp_pos);

            art_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    artifactChoosedFollowList.set(finalX, item_rss.getCharNameByTranslatedName(charNameTranslated.get(position), context));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            cal_choosed_list.addView(char_view);
        }

        saveToDB();
        //long afterRun = System.currentTimeMillis();
        //System.out.println("READ Artifact Total Cost "+(afterRun-beforeRun)+"ms");
    }

    private void initCharWeaponType(Context context) {
        long beforeRun = System.currentTimeMillis();
        charUseSword.clear();
        charUseBow.clear();
        charUseClaymore.clear();
        charUsePolearm.clear();
        charUseCatalyst.clear();
        charNameTranslated.clear();
        weaponChoosedTypeList.clear();
        tmpCharName.clear();
        tmpCharRare.clear();

        charUseSword.add("N/A");
        charUseBow.add("N/A");
        charUseClaymore.add("N/A");
        charUsePolearm.add("N/A");
        charUseCatalyst.add("N/A");
        charNameTranslated.add("N/A");

        try {
            String json_base = LoadData("db/char/char_require_asc_skill.json");
            JSONObject object = null;
            JSONArray array = new JSONArray(json_base);

            for (int y = 0 ; y < array.length() ; y++){
                object = array.getJSONObject(y);
                tmpCharName.add(object.getString("name"));
                tmpCharRare.add(object.getInt("rare"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int x = 0; x < choosedNameList.size(); x++) {
            charNameTranslated.add(item_rss.getCharByName(choosedNameList.get(x), context)[1]);

            try {
                String json_base = LoadData("db/char/char_list.json");
                String name = "";
                JSONArray array = new JSONArray(json_base);
                int y = 0;
                while (!array.getJSONObject(y).getString("name").equals(choosedNameList.get(x)) && y < array.length()){
                    y++;
                }
                name = array.getJSONObject(y).getString("name");
                switch (array.getJSONObject(y).getString("weapon")) {
                    case "Sword":
                        charUseSword.add(item_rss.getCharByName(name, context)[1]);
                        break;
                    case "Bow":
                        charUseBow.add(item_rss.getCharByName(name, context)[1]);
                        break;
                    case "Claymore":
                        charUseClaymore.add(item_rss.getCharByName(name, context)[1]);
                        break;
                    case "Polearm":
                        charUsePolearm.add(item_rss.getCharByName(name, context)[1]);
                        break;
                    case "Catalyst":
                        charUseCatalyst.add(item_rss.getCharByName(name, context)[1]);
                }

                }catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for (int x = 0; x < weaponChoosedNameList.size(); x++){
            try {
                String json_base = LoadData("db/weapons/weapon_list.json");
                String name = "";
                JSONArray array = new JSONArray(json_base);
                int y = 0;
                while (!array.getJSONObject(y).getString("name").equals(weaponChoosedNameList.get(x)) && y < array.length()){
                    y++;
                }
                weaponChoosedTypeList.add(array.getJSONObject(y).getString("weapon"));

            }catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //long afterRun = System.currentTimeMillis();
        //System.out.println("DO INIT Total Cost "+(afterRun-beforeRun)+"ms");
    }

    /**
     * @param beforeAfter : 0 is Before, 1 is After
     * @return
     */
    private AdapterView.OnItemSelectedListener spinnerListener(int beforeAfter, Spinner nextSpinner, ArrayList<Integer> list, ArrayList<Integer> nextList, int finalX, TextView tv) {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer[] numsReturn = getNumByStrPlus(lvlListSkill[position]);
                list.set(finalX, numsReturn[0]);
                switch (beforeAfter) {
                    case 0: {
                        if (list.get(finalX) > nextList.get(finalX)) {
                            nextList.set(finalX, list.get(finalX));
                            nextSpinner.setSelection(list.get(finalX) - 1);
                        }
                        break;
                    }
                    case 1: {
                        if (nextList.get(finalX) > list.get(finalX)) {
                            list.set(finalX, nextList.get(finalX));
                            nextSpinner.setSelection(nextList.get(finalX) - 1);
                        }
                        break;
                    }
                }
                if (tv != null) {
                    tv.setText("Lv." + list.get(finalX));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    private Integer[] getNumByStrPlus(String numStr) {
        int num = Integer.parseInt(numStr.replace("+", ""));
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
            switch (numStr) {
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
            mCharAdapter.filterList(charactersList);
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
            mWeaponAdapter.filterList(weaponsList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void artifact_list_reload() {
        Log.wtf("DAAM", "YEE");
        artifactsList.clear();
        String name, img;
        int rare, isComing;

        String json_base = LoadData("db/artifacts/artifact_list.json");
        //Get data from JSON
        try {
            JSONArray array = new JSONArray(json_base);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                name = object.getString("name");
                img = object.getString("img");
                rare = object.getInt("rare");
                isComing = object.getInt("isComing");

                Artifacts artifacts = new Artifacts();
                artifacts.setName(name);
                artifacts.setBaseName(img);
                artifacts.setRare(rare);
                artifacts.setIsComing(isComing);
                artifactsList.add(artifacts);
            }
            mArtifactAdapter.filterList(artifactsList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public void addChar(String charName_base) {
        choosedNameList.add(charName_base);
        choosedBeforeLvlList.add(1);
        choosedAfterLvlList.add(90);
        choosedBeforeBreakLvlList.add(0);
        choosedAfterBreakLvlList.add(6);
        choosedBeforeSkill1LvlList.add(1);
        choosedAfterSkill1LvlList.add(10);
        choosedBeforeSkill2LvlList.add(1);
        choosedAfterSkill2LvlList.add(10);
        choosedBeforeSkill3LvlList.add(1);
        choosedAfterSkill3LvlList.add(10);
        choosedIsCal.add(true);
        choosedBeforeBreakUPLvlList.add(false);
        choosedAfterBreakUPLvlList.add(false);
        charHasFlower.add(false);
        charHasPlume.add(false);
        charHasSand.add(false);
        charHasGoblet.add(false);
        charHasCirclet.add(false);
        charHasWeapon.add(false);
        if(dialogX != null){
            dialogX.dismiss();
        }

        initCharWeaponType(context);
        displayCharData();
    }

    public void addWeapon(String charName_base, int rare){
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("weaponName", charName_base);
        values.put("weaponBeforeLvl", 1);
        values.put("weaponAfterLvl", 90);
        values.put("weaponBeforeBreakLvl", 0);
        values.put("weaponAfterBreakLvl", 6);
        values.put("weaponBeforeBreakUpLvl", 0);
        values.put("weaponAfterBreakUpLvl", 0);
        values.put("weaponRare", rare);
        values.put("weaponFollow", "N/A");
        values.put("weaponIsCal", String.valueOf((true) ? 1 : 0 ));

        db.insert(dataSheetName+"_weapon", null, values);

        cursorWeaponIds();

        weaponChoosedNameList.add(charName_base);
        weaponChoosedBeforeLvlList.add(1);
        weaponChoosedAfterLvlList.add(90);
        weaponChoosedBeforeBreakLvlList.add(0);
        weaponChoosedAfterBreakLvlList.add(6);
        weaponChoosedIsCal.add(true);
        weaponChoosedBeforeBreakUPLvlList.add(false);
        weaponChoosedAfterBreakUPLvlList.add(false);
        weaponChoosedRare.add(rare);
        weaponChoosedFollowList.add("N/A");
        if(dialogX != null){
            dialogX.dismiss();
        }
        initCharWeaponType(context);
        displayWeaponData();
    }

    public void addArtifact(String charName_base, int rare) {
        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.fragment_art_choose_2048, null);

        ImageView art_flower = view.findViewById(R.id.art_flower);
        ImageView art_plume = view.findViewById(R.id.art_plume);
        ImageView art_sand = view.findViewById(R.id.art_sand);
        ImageView art_goblet = view.findViewById(R.id.art_goblet);
        ImageView art_circlet = view.findViewById(R.id.art_circlet);
        FrameLayout db_ok = view.findViewById(R.id.db_ok);

        art_flower.setImageResource(R.drawable.ic_2048_flower);
        art_plume.setImageResource(R.drawable.ic_2048_plume);
        art_sand.setImageResource(R.drawable.ic_2048_sand);
        art_goblet.setImageResource(R.drawable.ic_2048_goblet);
        art_circlet.setImageResource(R.drawable.ic_2048_circlet);

        art_flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "Flower";
                art_flower.setImageResource(R.drawable.ic_2048_flower_selected);
                art_plume.setImageResource(R.drawable.ic_2048_plume);
                art_sand.setImageResource(R.drawable.ic_2048_sand);
                art_goblet.setImageResource(R.drawable.ic_2048_goblet);
                art_circlet.setImageResource(R.drawable.ic_2048_circlet);
            }
        });
        art_plume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "Plume";
                art_flower.setImageResource(R.drawable.ic_2048_flower);
                art_plume.setImageResource(R.drawable.ic_2048_plume_selected);
                art_sand.setImageResource(R.drawable.ic_2048_sand);
                art_goblet.setImageResource(R.drawable.ic_2048_goblet);
                art_circlet.setImageResource(R.drawable.ic_2048_circlet);
            }
        });
        art_sand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "Sand";
                art_flower.setImageResource(R.drawable.ic_2048_flower);
                art_plume.setImageResource(R.drawable.ic_2048_plume);
                art_sand.setImageResource(R.drawable.ic_2048_sand_selected);
                art_goblet.setImageResource(R.drawable.ic_2048_goblet);
                art_circlet.setImageResource(R.drawable.ic_2048_circlet);
            }
        });
        art_goblet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "Goblet";
                art_flower.setImageResource(R.drawable.ic_2048_flower);
                art_plume.setImageResource(R.drawable.ic_2048_plume);
                art_sand.setImageResource(R.drawable.ic_2048_sand);
                art_goblet.setImageResource(R.drawable.ic_2048_goblet_selected);
                art_circlet.setImageResource(R.drawable.ic_2048_circlet);
            }
        });
        art_circlet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "Circlet";
                art_flower.setImageResource(R.drawable.ic_2048_flower);
                art_plume.setImageResource(R.drawable.ic_2048_plume);
                art_sand.setImageResource(R.drawable.ic_2048_sand);
                art_goblet.setImageResource(R.drawable.ic_2048_goblet);
                art_circlet.setImageResource(R.drawable.ic_2048_circlet_selected);
            }
        });
        db_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dbHelper = new DataBaseHelper(context);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("artifactName", charName_base);
                values.put("artifactBeforeLvl", before_lvl);
                values.put("artifactAfterLvl", after_lvl);
                values.put("artifactRare", rare);
                values.put("artifactFollow", "N/A");
                values.put("artifactType", type);
                values.put("artifactIsCal", String.valueOf((true) ? 1 : 0 ));

                db.insert(dataSheetName+"_artifact", null, values);

                artifactChoosedNameList.add(charName_base);
                artifactChoosedBeforeLvlList.add(1);
                artifactChoosedAfterLvlList.add(20);
                artifactChoosedIsCal.add(true);
                artifactChoosedRare.add(rare);
                artifactChoosedFollowList.add("N/A");
                artifactChoosedType.add(type);
                dialog.dismiss();
                if(dialogX != null){
                    dialogX.dismiss();
                }

                cursorArtifactIds();
                saveToDB();
                initCharWeaponType(context);
                displayArtifactData();
            }
        });

        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        // 2O48 DESIGN
        dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_2048));
        dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private List<View> mListViews;

        public MyViewPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mListViews.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

    }

    public void saveToDB() {
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db ;

        /**
         * Database Char Part
         */

        for (int x = 0 ; x < choosedNameList.size() ; x ++){
            db = dbHelper.getReadableDatabase();
            String[] projection = {"charName"};
            String selection = "charName" + " = ?";
            String[] selectionArgs = { choosedNameList.get(x) };
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
                        "charBeforeLvl = "+String.valueOf(choosedBeforeLvlList.get(x))+","+
                        "charAfterLvl = "+String.valueOf(choosedAfterLvlList.get(x))+","+

                        "charBeforeBreakLvl = "+String.valueOf(choosedBeforeBreakLvlList.get(x))+","+
                        "charAfterBreakLvl = "+String.valueOf(choosedAfterBreakLvlList.get(x))+","+

                        "charBeforeBreakUpLvl = "+String.valueOf((choosedBeforeBreakUPLvlList.get(x)) ? 1 : 0 )+","+
                        "charAfterBreakUpLvl = "+String.valueOf((choosedAfterBreakUPLvlList.get(x)) ? 1 : 0 )+","+

                        "charBeforeSkill1Lvl = "+String.valueOf(choosedBeforeSkill1LvlList.get(x))+","+
                        "charAfterSkill1Lvl = "+String.valueOf(choosedAfterSkill1LvlList.get(x))+","+

                        "charBeforeSkill2Lvl = "+String.valueOf(choosedBeforeSkill2LvlList.get(x))+","+
                        "charAfterSkill2Lvl = "+String.valueOf(choosedAfterSkill2LvlList.get(x))+","+

                        "charBeforeSkill3Lvl = "+String.valueOf(choosedBeforeSkill3LvlList.get(x))+","+
                        "charAfterSkill3Lvl = "+String.valueOf(choosedAfterSkill3LvlList.get(x))+","+

                        "charIsCal = "+String.valueOf((choosedIsCal.get(x)) ? 1 : 0 )+

                        " WHERE charName = \""+choosedNameList.get(x)+"\";");
            }else {
                // DEMO -> INSERT INTO demo (ID,Name) VALUES (-3,"SSS");
                db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("charName", choosedNameList.get(x));
                values.put("charBeforeLvl", choosedBeforeLvlList.get(x));
                values.put("charAfterLvl", choosedAfterLvlList.get(x));
                values.put("charBeforeBreakLvl", choosedBeforeBreakLvlList.get(x));
                values.put("charAfterBreakLvl",choosedAfterBreakLvlList.get(x));
                values.put("charBeforeBreakUpLvl", String.valueOf((choosedBeforeBreakUPLvlList.get(x)) ? 1 : 0 ));
                values.put("charAfterBreakUpLvl", String.valueOf((choosedAfterBreakUPLvlList.get(x)) ? 1 : 0 ));
                values.put("charBeforeSkill1Lvl", choosedBeforeSkill1LvlList.get(x));
                values.put("charAfterSkill1Lvl", choosedAfterSkill1LvlList.get(x));
                values.put("charBeforeSkill2Lvl", choosedBeforeSkill2LvlList.get(x));
                values.put("charAfterSkill2Lvl", choosedAfterSkill2LvlList.get(x));
                values.put("charBeforeSkill3Lvl", choosedBeforeSkill3LvlList.get(x));
                values.put("charAfterSkill3Lvl", choosedAfterSkill3LvlList.get(x));
                values.put("charIsCal", String.valueOf((choosedIsCal.get(x)) ? 1 : 0 ));

                db.insert(dataSheetName+"_char", null, values);

            }
            cursor.close();
        }

        for (int x = 0 ; x < weaponChoosedNameList.size() ; x ++){
            db = dbHelper.getReadableDatabase();
            String[] projection = {"weaponName"};
            String selection = "weaponName" + " = ?";
            String[] selectionArgs = { weaponChoosedNameList.get(x) };
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
                        "weaponBeforeLvl = "+String.valueOf(weaponChoosedBeforeLvlList.get(x))+","+
                        "weaponAfterLvl = "+String.valueOf(weaponChoosedAfterLvlList.get(x))+","+

                        "weaponBeforeBreakLvl = "+String.valueOf(weaponChoosedBeforeBreakLvlList.get(x))+","+
                        "weaponAfterBreakLvl = "+String.valueOf(weaponChoosedAfterBreakLvlList.get(x))+","+

                        "weaponBeforeBreakUpLvl = "+String.valueOf((weaponChoosedBeforeBreakUPLvlList.get(x)) ? 1 : 0 )+","+
                        "weaponAfterBreakUpLvl = "+String.valueOf((weaponChoosedAfterBreakUPLvlList.get(x)) ? 1 : 0 )+","+

                        "weaponFollow = \""+weaponChoosedFollowList.get(x)+"\","+
                        "weaponRare = "+String.valueOf(weaponChoosedRare.get(x))+","+
                        "weaponIsCal = "+String.valueOf((weaponChoosedIsCal.get(x)) ? 1 : 0 )+

                        " WHERE weaponName = \""+weaponChoosedNameList.get(x)+"\";");
            }else {
                // DEMO -> INSERT INTO demo (ID,Name) VALUES (-3,"SSS");
                db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("weaponName", weaponChoosedNameList.get(x));
                values.put("weaponBeforeLvl", weaponChoosedBeforeLvlList.get(x));
                values.put("weaponAfterLvl", weaponChoosedAfterLvlList.get(x));
                values.put("weaponBeforeBreakLvl", weaponChoosedBeforeBreakLvlList.get(x));
                values.put("weaponAfterBreakLvl", weaponChoosedAfterBreakLvlList.get(x));
                values.put("weaponBeforeBreakUpLvl", String.valueOf((weaponChoosedBeforeBreakUPLvlList.get(x)) ? 1 : 0 ));
                values.put("weaponAfterBreakUpLvl", String.valueOf((weaponChoosedAfterBreakUPLvlList.get(x)) ? 1 : 0 ));
                values.put("weaponRare", weaponChoosedRare.get(x));
                values.put("weaponFollow", weaponChoosedFollowList.get(x));
                values.put("weaponIsCal", String.valueOf((weaponChoosedIsCal.get(x)) ? 1 : 0 ));

                db.insert(dataSheetName+"_weapon", null, values);

            }
            cursor.close();
        }

        for (int x = 0 ; x < artifactChoosedNameList.size() ; x ++){
            db = dbHelper.getReadableDatabase();
            String[] projection = {"artifactName","artifactType","artifactFollow"};
            String selection = "artifactName" + " = ?";
            String[] selectionArgs = { artifactChoosedNameList.get(x) };
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

            if(cursor.getCount()>0 && tmp_art_name.contains(artifactChoosedNameList.get(x)) && tmp_art_type.contains(artifactChoosedType.get(x)) && tmp_art_follow.contains(artifactChoosedFollowList.get(x))){
                db.execSQL("UPDATE "+dataSheetName+"_artifact"+" SET "+
                        "artifactBeforeLvl = "+String.valueOf(artifactChoosedBeforeLvlList.get(x))+","+
                        "artifactAfterLvl = "+String.valueOf(artifactChoosedAfterLvlList.get(x))+","+

                        "artifactFollow = \""+artifactChoosedFollowList.get(x)+"\","+
                        "artifactRare = "+String.valueOf(artifactChoosedRare.get(x))+","+
                        "artifactIsCal = "+String.valueOf((artifactChoosedIsCal.get(x)) ? 1 : 0 )+

                        " WHERE artifactName = \""+artifactChoosedNameList.get(x)+"\" AND artifactFollow = \""+artifactChoosedFollowList.get(x)+"\" AND artifactType = \""+artifactChoosedType.get(x)+"\";");
            }else {
                db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("artifactName", artifactChoosedNameList.get(x));
                values.put("artifactBeforeLvl", artifactChoosedBeforeLvlList.get(x));
                values.put("artifactAfterLvl", artifactChoosedAfterLvlList.get(x));
                values.put("artifactRare", artifactChoosedRare.get(x));
                values.put("artifactFollow", artifactChoosedFollowList.get(x));
                values.put("artifactType", artifactChoosedType.get(x));
                values.put("artifactIsCal", String.valueOf((artifactChoosedIsCal.get(x)) ? 1 : 0 ));

                db.insert(dataSheetName+"_artifact", null, values);
            }
            cursor.close();
        }

        /**
         * IndexDB part
         */

        db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE IndexDB SET "+
                "char_cnt = "+String.valueOf(choosedNameList.size())+","+
                "weapon_cnt = "+String.valueOf(weaponChoosedNameList.size())+","+
                "artifact_cnt = "+String.valueOf(artifactChoosedNameList.size())+
                " WHERE name = "+"\""+dataSheetName+"\";");
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user_info", MODE_PRIVATE);
        sharedPreferences.getInt("curr_lang_pos", 2);
        super.attachBaseContext(LangUtils.getAttachBaseContext(newBase, sharedPreferences.getInt("curr_lang_pos", 2)));
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

        mCharacterList.removeAllViews();
        mCharAdapter.filterList(filteredList);
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
        mWeaponList.removeAllViews();
        mWeaponAdapter.filterList(filteredList);
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

    public void filterArtifactAlgothm(int star) {
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
        ArrayList<Artifacts> filteredList = new ArrayList<>();
        for (Artifacts item : artifactsList) {
            // DEFAULT
            if ((show_released == false && show_unreleased == false) &&
                    (show_rare1 == false && show_rare2 == false && show_rare3 == false && show_rare4 == false && show_rare5 == false)) {
                filteredList.add(item);
            } else {
                boolean isAllTrue = true;
                int isSingleRare = 0;
                int isSingleRelease = 0;

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
        mArtifactList.removeAllViews();
        mArtifactAdapter.filterList(filteredList);
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

    private boolean getBooleanByInt (int x){
        if(x == 0){
            return false;
        }else if(x == 1){
            return true;
        }else{
            return false;
        }

    }


    public void cursorWeaponIds(){
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                dataSheetName+"_weapon",   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        weaponChoosedIdList.clear();

        while(cursor.moveToNext()) {
            weaponChoosedIdList.add(cursor.getInt(cursor.getColumnIndexOrThrow("weaponId")));
        }
    }
    public void cursorArtifactIds(){
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                dataSheetName+"_artifact",   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        artifactChoosedIdList.clear();

        while(cursor.moveToNext()) {
            artifactChoosedIdList.add(cursor.getInt(cursor.getColumnIndexOrThrow("artifactId")));
        }
    }

    @Override
    public void onBackPressed() {
        saveToDB();
        finish();
        return;
    }

}