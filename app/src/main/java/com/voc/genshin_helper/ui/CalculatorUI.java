package com.voc.genshin_helper.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.buff.BuffCal2;
import com.voc.genshin_helper.data.Artifacts;
import com.voc.genshin_helper.data.ArtifactsAdapter;
import com.voc.genshin_helper.data.Characters;
import com.voc.genshin_helper.data.CharactersAdapter;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.ScreenSizeUtils;
import com.voc.genshin_helper.data.Weapons;
import com.voc.genshin_helper.data.WeaponsAdapter;
import com.voc.genshin_helper.database.DataBaseHelper;
import com.voc.genshin_helper.util.BackgroundReload;
import com.voc.genshin_helper.util.CalculatorProcess;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.NumberPickerDialog;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.voc.genshin_helper.buff.CalculatorBuff;


/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

public class CalculatorUI extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    /** Method of requirements */
    ItemRss item_rss;
    ItemRss css ;
    private ViewPager viewPager;
    private ArrayList<View> viewPager_List;
    BottomNavigationView nav_view;
    Context context;
    public SharedPreferences sharedPreferences;
    SharedPreferences calShared; // Only record CalculatorUI's vars, user can get last time data when restart this page (ALSO CAN USE RESET BTN)
    SharedPreferences.Editor editor;
    NumberPickerDialog npd;

    // Char Page
    RecyclerView mList_char;
    CharactersAdapter mCharAdapter;
    RecyclerView mList_artifact;
    ArtifactsAdapter mArtifactAdapter;
    RecyclerView mList_weapon;
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

    public boolean show_flower = true;
    public boolean show_plume = true;
    public boolean show_sand = true;
    public boolean show_goblet = true;
    public boolean show_circlet = true;

    public int show_stars = 0;

    /** Method of Char Choosed List*/
    public ArrayList<String> choosedNameList = new ArrayList<String>();
    public ArrayList<Integer> choosedBeforeLvlList= new ArrayList<Integer>();
    public ArrayList<Integer> choosedAfterLvlList= new ArrayList<Integer>();
    public ArrayList<Integer> choosedBeforeBreakLvlList= new ArrayList<Integer>();
    public ArrayList<Boolean> choosedBeforeBreakUPLvlList= new ArrayList<Boolean>();
    public ArrayList<Integer> choosedAfterBreakLvlList= new ArrayList<Integer>();
    public ArrayList<Boolean> choosedAfterBreakUPLvlList= new ArrayList<Boolean>();
    public ArrayList<Integer> choosedBeforeSkill1LvlList= new ArrayList<Integer>();
    public ArrayList<Integer> choosedAfterSkill1LvlList= new ArrayList<Integer>();
    public ArrayList<Integer> choosedBeforeSkill2LvlList= new ArrayList<Integer>();
    public ArrayList<Integer> choosedAfterSkill2LvlList= new ArrayList<Integer>();
    public ArrayList<Integer> choosedBeforeSkill3LvlList= new ArrayList<Integer>();
    public ArrayList<Integer> choosedAfterSkill3LvlList= new ArrayList<Integer>();
    public ArrayList<Boolean> choosedIsCal= new ArrayList<Boolean>();
    //public ArrayList<Integer> choosedWeaponIdList= new ArrayList<Integer>();
    //public ArrayList<Integer> choosedFlowerIdList= new ArrayList<Integer>();
    //public ArrayList<Integer> choosedPlumeIdList= new ArrayList<Integer>();
    //public ArrayList<Integer> choosedSandIdList= new ArrayList<Integer>();
    //public ArrayList<Integer> choosedGobletIdList= new ArrayList<Integer>();
    //public ArrayList<Integer> choosedCircletIdList= new ArrayList<Integer>();

    /** Method of Char's details' container */
    /** Since String can't be null, so there will have "XPR" for identify is result correct */

    // Battle Talent
    String normal_name = "Unknown";
    String element_name = "Unknown";
    String final_name = "Unknown";

    /** Calculator vars -> Might change to int[] which sort by char update time*/
    int before_lvl = 1;
    int after_lvl = 90;
    int before_break = 0;
    int after_break = 6;
    int skill1_lvl = 1;
    int skill2_lvl = 1;
    int skill3_lvl = 1;

    /**  Method of Weapon Choosed List */
    public ArrayList<String> weaponChoosedNameList = new ArrayList<>();
    public ArrayList<Integer> weaponChoosedIdList = new ArrayList<>();
    public ArrayList<Integer> weaponChoosedBeforeLvlList = new ArrayList<>();
    public ArrayList<Integer> weaponChoosedAfterLvlList = new ArrayList<>();
    public ArrayList<Integer> weaponChoosedBeforeBreakLvlList = new ArrayList<>();
    public ArrayList<Boolean> weaponChoosedBeforeBreakUPLvlList = new ArrayList<>();
    public ArrayList<Integer> weaponChoosedAfterBreakLvlList = new ArrayList<>();
    public ArrayList<Boolean> weaponChoosedAfterBreakUPLvlList = new ArrayList<>();
    public ArrayList<String> weaponChoosedFollowList = new ArrayList<>();
    public ArrayList<Boolean> weaponChoosedIsCal = new ArrayList<>();
    public ArrayList<Integer> weaponChoosedRare = new ArrayList<>(); // INNER USE ONLY

    /**  Method of Artifact Choosed List */
    public ArrayList<String> artifactChoosedNameList = new ArrayList<>();
    public ArrayList<Integer> artifactChoosedIdList = new ArrayList<>();
    public ArrayList<Integer> artifactChoosedBeforeLvlList = new ArrayList<>();
    public ArrayList<Integer> artifactChoosedAfterLvlList = new ArrayList<>();
    public ArrayList<String> artifactChoosedFollowList = new ArrayList<>();
    public ArrayList<Boolean> artifactChoosedIsCal = new ArrayList<>();
    public ArrayList<Integer> artifactChoosedRare = new ArrayList<>();
    public ArrayList<String> artifactChoosedType = new ArrayList<>();

    View viewPager0,viewPager1,viewPager2,viewPager3,viewPager4;


    String normal_skill_name = "Unknown";
    String element_skill_name = "Unknown";
    String final_skill_name = "Unknown";
    String follow_char_tmp = "Unknown";
    String tmp_artifact_type = "N/A";
    int position_tmp = 0;

    String dataSheetName = "NaN";
    CalculatorProcess calculatorProcess;
    CalculatorBuff calculatorBuff;


    public ArrayList<Boolean> charHasWeapon = new ArrayList<>();
    public ArrayList<Boolean> charHasFlower = new ArrayList<>();
    public ArrayList<Boolean> charHasPlume = new ArrayList<>();
    public ArrayList<Boolean> charHasSand = new ArrayList<>();
    public ArrayList<Boolean> charHasGoblet = new ArrayList<>();
    public ArrayList<Boolean> charHasCirclet = new ArrayList<>();

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_ui);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        nav_view = findViewById(R.id.nav_view_cal);


        css = new ItemRss();
        calculatorProcess = new CalculatorProcess();
        calculatorBuff = new CalculatorBuff();
        npd = new NumberPickerDialog(this);
        context = this;
        activity = this;

        BackgroundReload.BackgroundReload(context,activity);
        /**
         * INIT OF TRANSFER
         */
        Bundle extras = getIntent().getExtras();

        dataSheetName = (String) extras.getString("dataSheetName");

        choosedNameList = (ArrayList<String>) extras.getStringArrayList("charChoosedNameList");
        choosedBeforeLvlList = (ArrayList<Integer>) extras.getIntegerArrayList("charChoosedBeforeLvlList");
        choosedAfterLvlList = (ArrayList<Integer>) extras.getIntegerArrayList("charChoosedAfterLvlList");
        choosedBeforeBreakLvlList = (ArrayList<Integer>) extras.getIntegerArrayList("charChoosedBeforeBreakLvlList");
        choosedBeforeBreakUPLvlList = (ArrayList<Boolean>) extras.get("charChoosedBeforeBreakUPLvlList");
        choosedAfterBreakLvlList = (ArrayList<Integer>) extras.getIntegerArrayList("charChoosedAfterBreakLvlList");
        choosedAfterBreakUPLvlList = (ArrayList<Boolean>) extras.get("charChoosedAfterBreakUPLvlList");
        choosedBeforeSkill1LvlList = (ArrayList<Integer>) extras.getIntegerArrayList("charChoosedBeforeSkill1LvlList");
        choosedAfterSkill1LvlList = (ArrayList<Integer>) extras.getIntegerArrayList("charChoosedAfterSkill1LvlList");
        choosedBeforeSkill2LvlList = (ArrayList<Integer>) extras.getIntegerArrayList("charChoosedBeforeSkill2LvlList");
        choosedAfterSkill2LvlList = (ArrayList<Integer>) extras.getIntegerArrayList("charChoosedAfterSkill2LvlList");
        choosedBeforeSkill3LvlList = (ArrayList<Integer>) extras.getIntegerArrayList("charChoosedBeforeSkill3LvlList");
        choosedAfterSkill3LvlList = (ArrayList<Integer>) extras.getIntegerArrayList("charChoosedAfterSkill3LvlList");
        choosedIsCal = (ArrayList<Boolean>) extras.get("charChoosedIsCal");
        //choosedWeaponIdList = (ArrayList<Integer>) extras.get("charChoosedWeaponIdList");
        //choosedFlowerIdList = (ArrayList<Integer>) extras.get("charChoosedFlowerIdList");
        //choosedPlumeIdList = (ArrayList<Integer>) extras.get("charChoosedPlumeIdList");
        //choosedSandIdList = (ArrayList<Integer>) extras.get("charChoosedSandIdList");
        //choosedGobletIdList = (ArrayList<Integer>) extras.get("charChoosedGobletIdList");
        //choosedCircletIdList = (ArrayList<Integer>) extras.get("charChoosedCircletIdList");

        weaponChoosedNameList = (ArrayList<String>) extras.getStringArrayList("weaponChoosedNameList");
        weaponChoosedIdList = (ArrayList<Integer>) extras.getIntegerArrayList("weaponChoosedIdList");
        weaponChoosedBeforeLvlList = (ArrayList<Integer>) extras.getIntegerArrayList("weaponChoosedBeforeLvlList");
        weaponChoosedAfterLvlList = (ArrayList<Integer>) extras.getIntegerArrayList("weaponChoosedAfterLvlList");
        weaponChoosedBeforeBreakLvlList = (ArrayList<Integer>) extras.getIntegerArrayList("weaponChoosedBeforeBreakLvlList");
        weaponChoosedBeforeBreakUPLvlList = (ArrayList<Boolean>) extras.get("weaponChoosedBeforeBreakUPLvlList");
        weaponChoosedAfterBreakLvlList = (ArrayList<Integer>) extras.getIntegerArrayList("weaponChoosedAfterBreakLvlList");
        weaponChoosedAfterBreakUPLvlList = (ArrayList<Boolean>) extras.get("weaponChoosedAfterBreakUPLvlList");
        weaponChoosedFollowList = (ArrayList<String>) extras.getStringArrayList("weaponChoosedFollowList");
        weaponChoosedRare = (ArrayList<Integer>) extras.getIntegerArrayList("weaponChoosedRare");
        weaponChoosedIsCal = (ArrayList<Boolean>) extras.get("weaponChoosedIsCal");

        artifactChoosedNameList = (ArrayList<String>) extras.getStringArrayList("artifactChoosedNameList");
        artifactChoosedIdList = (ArrayList<Integer>) extras.getIntegerArrayList("artifactChoosedIdList");
        artifactChoosedBeforeLvlList = (ArrayList<Integer>) extras.getIntegerArrayList("artifactChoosedBeforeLvlList");
        artifactChoosedAfterLvlList = (ArrayList<Integer>) extras.getIntegerArrayList("artifactChoosedAfterLvlList");
        artifactChoosedFollowList = (ArrayList<String>) extras.getStringArrayList("artifactChoosedFollowList");
        artifactChoosedIsCal = (ArrayList<Boolean>) extras.get("artifactChoosedIsCal");
        artifactChoosedRare = (ArrayList<Integer>) extras.getIntegerArrayList("artifactChoosedRare");
        artifactChoosedType = (ArrayList<String>) extras.getStringArrayList("artifactChoosedType");

        for (int x = 0 ; x < choosedNameList.size() ; x++){
            charHasFlower.add(false);
            charHasPlume.add(false);
            charHasSand.add(false);
            charHasGoblet.add(false);
            charHasCirclet.add(false);
            charHasWeapon.add(false);
        }

        for (int x = 0 ; x < choosedNameList.size() ; x++){
            for (int y = 0 ; y < artifactChoosedFollowList.size() ; y++){
                if(choosedNameList.get(x).equals(artifactChoosedFollowList.get(y))){
                    switch (artifactChoosedType.get(y)){
                        case "Flower" : charHasFlower.set(x,true);break;
                        case "Plume" : charHasPlume.set(x,true);break;
                        case "Sand" : charHasSand.set(x,true);break;
                        case "Goblet" : charHasGoblet.set(x,true);break;
                        case "Circlet" : charHasCirclet.set(x,true);break;
                    }
                }
            }
        }

        /**
         * INIT (UI)
         */

        final LayoutInflater mInflater = getLayoutInflater().from(this);
        viewPager0 = mInflater.inflate(R.layout.fragment_cal_char, null,false);
        viewPager1 = mInflater.inflate(R.layout.fragment_cal_weapon, null,false);
        viewPager2 = mInflater.inflate(R.layout.fragment_cal_art, null,false);
        viewPager3 = mInflater.inflate(R.layout.fragment_result, null,false);
        viewPager4 = mInflater.inflate(R.layout.fragment_cal_buff, null,false);

        viewPager_List = new ArrayList<View>();
        viewPager_List.add(viewPager0);
        viewPager_List.add(viewPager1);
        viewPager_List.add(viewPager2);
        viewPager_List.add(viewPager3);
        viewPager_List.add(viewPager4);

        item_rss = new ItemRss();

        viewPager.setAdapter(new MyViewPagerAdapter(viewPager_List));
        nav_view.setSelectedItemId(R.id.nav_char);
        viewPager.setCurrentItem(0);

        sharedPreferences = getSharedPreferences("user_info",MODE_PRIVATE);
        calShared = getSharedPreferences("cal_ui",MODE_PRIVATE);
        editor = sharedPreferences.edit();
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
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if(color_hex.toUpperCase().equals("#FFFFFFFF")&&isColorGradient == false){
            window.setStatusBarColor(Color.parseColor("#000000"));
        }
        else if(isColorGradient){
            window.setStatusBarColor(Color.parseColor(end_color));
        }
        else{
            window.setStatusBarColor(Color.parseColor(color_hex));
        }

        char_setup();
        weapon_setup();
        artifact_setup();

        nav_view.setItemIconTintList(myList);
        nav_view.setItemTextColor(myList);
        nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                // REMEMBER TO return true; thx !!!
                if (item.getItemId() == R.id.nav_char){
                    viewPager.setCurrentItem(0);
                    return true;
                }else if (item.getItemId() == R.id.nav_weapons){
                    viewPager.setCurrentItem(1);
                    return true;
                }else if (item.getItemId() == R.id.nav_artifacts){
                    viewPager.setCurrentItem(2);
                    return true;
                }else if (item.getItemId() == R.id.nav_result){
                    viewPager.setCurrentItem(3);
                    return true;
                }else if (item.getItemId() == R.id.nav_buff){
                    viewPager.setCurrentItem(4);
                    return true;
                }
                return false;
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        break;

                    case 1:
                        break;

                    case 2:
                        break;

                    case 3:

                        cursorArtifactIds();
                        cursorWeaponIds();

                        CalculatorProcess calculatorProcess = new CalculatorProcess();
                        calculatorProcess.setVP(viewPager,viewPager3);
                        calculatorProcess.setDBName(dataSheetName);
                        calculatorProcess.setup(context,activity);
                        calculatorProcess.char_setup(choosedNameList,choosedBeforeLvlList,choosedAfterLvlList,choosedBeforeBreakLvlList,choosedAfterBreakLvlList,choosedBeforeSkill1LvlList,choosedAfterSkill1LvlList,choosedBeforeSkill2LvlList,choosedAfterSkill2LvlList,choosedBeforeSkill3LvlList,choosedAfterSkill3LvlList,choosedIsCal,choosedBeforeBreakUPLvlList,choosedAfterBreakUPLvlList,"READ");
                        calculatorProcess.artifact_setup(artifactChoosedNameList,artifactChoosedBeforeLvlList,artifactChoosedAfterLvlList,artifactChoosedIsCal,artifactChoosedFollowList,artifactChoosedRare,artifactChoosedType,artifactChoosedIdList,"READ");
                        calculatorProcess.weapon_setup(weaponChoosedNameList,weaponChoosedBeforeLvlList,weaponChoosedAfterLvlList,weaponChoosedBeforeBreakLvlList,weaponChoosedAfterBreakLvlList,weaponChoosedIsCal,weaponChoosedBeforeBreakUPLvlList,weaponChoosedAfterBreakUPLvlList,weaponChoosedFollowList,weaponChoosedRare,weaponChoosedIdList,"READ");


                        break;

                    case 4:

                        cursorArtifactIds();
                        cursorWeaponIds();

                        CalculatorBuff calculatorBuff = new CalculatorBuff();
                        calculatorBuff.ui_setup(context,viewPager4);
                        calculatorBuff.char_setup(choosedNameList,choosedBeforeLvlList,choosedAfterLvlList,choosedBeforeBreakLvlList,choosedAfterBreakLvlList,choosedBeforeSkill1LvlList,choosedAfterSkill1LvlList,choosedBeforeSkill2LvlList,choosedAfterSkill2LvlList,choosedBeforeSkill3LvlList,choosedAfterSkill3LvlList,choosedIsCal,choosedBeforeBreakUPLvlList,choosedAfterBreakUPLvlList,"READ");
                        calculatorBuff.weapon_setup(weaponChoosedNameList,weaponChoosedBeforeLvlList,weaponChoosedAfterLvlList,weaponChoosedBeforeBreakLvlList,weaponChoosedAfterBreakLvlList,weaponChoosedIsCal,weaponChoosedBeforeBreakUPLvlList,weaponChoosedAfterBreakUPLvlList,weaponChoosedFollowList,weaponChoosedRare,weaponChoosedIdList,"READ");
                        calculatorBuff.artifact_setup(artifactChoosedNameList,artifactChoosedBeforeLvlList,artifactChoosedAfterLvlList,artifactChoosedIsCal,artifactChoosedFollowList,artifactChoosedRare,artifactChoosedType,artifactChoosedIdList,"READ");

                        calculatorBuff.setDBName(dataSheetName);
                        calculatorBuff.view_setup();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void char_setup() {
        viewPager.setCurrentItem(0);
        mList_char = viewPager0.findViewById(R.id.main_list);
        mCharAdapter = new CharactersAdapter(context, charactersList,activity);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);
        DisplayMetrics displayMetrics_a = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics_a);
        int height_a = displayMetrics_a.heightPixels;
        int width_a = displayMetrics_a.widthPixels;
        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            mLayoutManager = new GridLayoutManager(context,  width_a/400+1);
        }else{
            mLayoutManager = new GridLayoutManager(context,  3);
        }
        LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsMsg.gravity = Gravity.CENTER;
        mList_char.setLayoutManager(mLayoutManager);
        mList_char.setLayoutParams(paramsMsg);
        mList_char.setAdapter(mCharAdapter);
        mList_char.removeAllViewsInLayout();
        char_list_reload();

        // SQL DATA DISPLAY
        LinearLayout cal_choosed_list = viewPager0.findViewById(R.id.cal_choosed_list);
        cal_choosed_list.removeAllViews();
        for (int x = 0 ; x < choosedNameList.size(); x++){
            Log.w("choosedNameList"+String.valueOf(x),choosedNameList.get(x));
            View char_view = LayoutInflater.from(context).inflate(R.layout.item_img, cal_choosed_list, false);
            ImageView item_img = char_view.findViewById(R.id.item_img);
            String charName = choosedNameList.get(x);
            int finalX = x;
            item_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    charQuestion(charName,"EDIT", finalX);
                }
            });

            final int radius = 180;
            final int margin = 4;
            final Transformation transformation = new RoundedCornersTransformation(radius, margin);
            Picasso.get()
                    .load (FileLoader.loadIMG(item_rss.getCharByName(choosedNameList.get(x),context)[3],context))
                    .transform(transformation)
                    .fit()
                    .error (R.drawable.paimon_full)
                    .into (item_img);
            cal_choosed_list.addView(char_view);
        }

        EditText char_et = viewPager0.findViewById(R.id.char_et);
        char_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                ArrayList<Characters> filteredList = new ArrayList<>();
                int x = 0;
                for (Characters item : charactersList) {
                    String str = String.valueOf(s).toLowerCase();
                    if (css.getCharByName(item.getName(),context)[1].contains(str)||css.getCharByName(item.getName(),context)[1].toLowerCase().contains(str)||css.getCharByName(item.getName(),context)[1].toUpperCase().contains(str)||item.getName().toLowerCase().contains(str)){ // EN -> ZH
                        filteredList.add(item);
                    }
                    x = x +1;
                }
                mCharAdapter.filterList(filteredList);
            }
        });

        ImageView char_filter = viewPager0.findViewById(R.id.char_filter);
        char_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.menu_char_filter, null);
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
                // Rating
                RatingBar ratingBar = view.findViewById(R.id.menu_rating);
                // Function Buttons
                Button cancel = view.findViewById(R.id.menu_cancel);
                Button reset = view.findViewById(R.id.menu_reset);
                Button ok = view.findViewById(R.id.menu_ok);

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
                show_stars = sharedPreferences.getInt("char_stars",0);

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
                ratingBar.setNumStars(5);
                ratingBar.setRating(show_stars);

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

                        ratingBar.setRating(0);

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
                        editor.putInt("char_stars", (int) ratingBar.getRating());
                        editor.apply();
                        dialog.dismiss();

                        mCharAdapter.filterList(charactersList);

                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<Characters> filteredList = new ArrayList<>();
                        for (Characters item : charactersList) {
                            if((show_sword == false && show_claymore == false && show_catalyst == false && show_bow == false && show_polearm == false) &&
                                    (show_anemo == false  && show_cryo == false && show_dendor == false && show_electro == false && show_hydro == false && show_geo == false && show_pyro == false)){
                                filteredList.add(item);
                            }else if ((item.getElement().toLowerCase().equals("pyro") && show_pyro||item.getElement().toLowerCase().equals("hydro") && show_hydro||item.getElement().toLowerCase().equals("anemo") && show_anemo||item.getElement().toLowerCase().equals("electro") && show_electro||item.getElement().toLowerCase().equals("dendor") && show_dendor||item.getElement().toLowerCase().equals("cryo") && show_cryo||item.getElement().toLowerCase().equals("geo") && show_geo)) {
                                if((show_sword == false && show_claymore == false && show_catalyst == false && show_bow == false && show_polearm == false)){
                                    filteredList.add(item);
                                }else if(item.getWeapon().toLowerCase().equals("sword") && show_sword||item.getWeapon().toLowerCase().equals("claymore") && show_claymore||item.getWeapon().toLowerCase().equals("polearm") && show_polearm||item.getWeapon().toLowerCase().equals("bow") && show_bow||item.getWeapon().toLowerCase().equals("catalyst") && show_catalyst){
                                    if(ratingBar.getRating() != 0 && item.getRare() == ratingBar.getRating()){
                                        filteredList.add(item);
                                    }else if (ratingBar.getRating() == 0){
                                        filteredList.add(item);
                                    }
                                }
                            }else if ((item.getWeapon().toLowerCase().equals("sword") && show_sword||item.getWeapon().toLowerCase().equals("claymore") && show_claymore||item.getWeapon().toLowerCase().equals("polearm") && show_polearm||item.getWeapon().toLowerCase().equals("bow") && show_bow||item.getWeapon().toLowerCase().equals("catalyst") && show_catalyst)) {
                                if((show_anemo == false  && show_cryo == false && show_dendor == false && show_electro == false && show_hydro == false && show_geo == false && show_pyro == false)){
                                    filteredList.add(item);
                                }else if((item.getElement().toLowerCase().equals("pyro") && show_pyro||item.getElement().toLowerCase().equals("hydro") && show_hydro||item.getElement().toLowerCase().equals("anemo") && show_anemo||item.getElement().toLowerCase().equals("electro") && show_electro||item.getElement().toLowerCase().equals("dendor") && show_dendor||item.getElement().toLowerCase().equals("cryo") && show_cryo||item.getElement().toLowerCase().equals("geo") && show_geo)){
                                    if(ratingBar.getRating() != 0 && item.getRare() == ratingBar.getRating()){
                                        filteredList.add(item);
                                    }else if (ratingBar.getRating() == 0){
                                        filteredList.add(item);
                                    }
                                }

                            }
                        }

                        mList_char.removeAllViews();
                        mCharAdapter.filterList(filteredList);
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
                        editor.putInt("char_stars", (int) ratingBar.getRating());
                        editor.apply();
                        dialog.dismiss();
                    }
                });

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
        });
    }

    private void weapon_setup() {
        mList_weapon = viewPager1.findViewById(R.id.main_list);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);
        mWeaponAdapter = new WeaponsAdapter(context,weaponsList,activity);
        DisplayMetrics displayMetrics_a = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics_a);
        int height_a = displayMetrics_a.heightPixels;
        int width_a = displayMetrics_a.widthPixels;
        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            mLayoutManager = new GridLayoutManager(context,  width_a/400+1);
        }else{
            mLayoutManager = new GridLayoutManager(context,  3);
        }
        LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsMsg.gravity = Gravity.CENTER;
        mList_weapon.setLayoutManager(mLayoutManager);
        mList_weapon.setLayoutParams(paramsMsg);
        mList_weapon.setAdapter(mWeaponAdapter);
        mList_weapon.removeAllViewsInLayout();
        weapon_list_reload();

        // SQL DATA DISPLAY
        LinearLayout cal_choosed_list = viewPager1.findViewById(R.id.cal_weapon_choosed_list);
        cal_choosed_list.removeAllViews();
        for (int x = 0 ; x < weaponChoosedNameList.size(); x++){
            Log.w("weaponChoosedNameList"+String.valueOf(x),weaponChoosedNameList.get(x));
            View char_view = LayoutInflater.from(this).inflate(R.layout.item_img, cal_choosed_list, false);
            ImageView item_img = char_view.findViewById(R.id.item_img);
            String charName = weaponChoosedNameList.get(x);
            int finalX = x;
            item_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    weaponQuestion(charName,"EDIT", finalX,weaponChoosedRare.get(finalX));
                }
            });

            final int radius = 180;
            final int margin = 4;
            final Transformation transformation = new RoundedCornersTransformation(radius, margin);
            Picasso.get()
                    .load (FileLoader.loadIMG(item_rss.getWeaponByName(weaponChoosedNameList.get(x),context)[1],context))
                    .transform(transformation)
                    .fit()
                    .error (R.drawable.paimon_full)
                    .into (item_img);
            cal_choosed_list.addView(char_view);
        }

        EditText weapon_et = viewPager1.findViewById(R.id.weapon_et);
        weapon_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                ArrayList<Weapons> filteredList = new ArrayList<>();
                int x = 0;
                for (Weapons item : weaponsList) {
                    String str = String.valueOf(s).toLowerCase();
                    if (css.getWeaponByName(item.getName(),context)[0].contains(str)||css.getWeaponByName(item.getName(),context)[0].toLowerCase().contains(str)||css.getWeaponByName(item.getName(),context)[0].toUpperCase().contains(str)||item.getName().toLowerCase().contains(str)){ // EN -> ZH
                        filteredList.add(item);
                    }
                    x = x +1;
                }
                mWeaponAdapter.filterList(filteredList);
            }
        });

        ImageView weapon_filter = viewPager1.findViewById(R.id.weapon_filter);
        weapon_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.menu_weapon_filter, null);
                // Weapons
                ImageView ico_sword = view.findViewById(R.id.ico_sword);
                ImageView ico_claymore = view.findViewById(R.id.ico_claymore);
                ImageView ico_polearm = view.findViewById(R.id.ico_polearm);
                ImageView ico_bow = view.findViewById(R.id.ico_bow);
                ImageView ico_catalyst = view.findViewById(R.id.ico_catalyst);
                // Rating
                RatingBar ratingBar = view.findViewById(R.id.menu_rating);
                // Function Buttons
                Button cancel = view.findViewById(R.id.menu_cancel);
                Button reset = view.findViewById(R.id.menu_reset);
                Button ok = view.findViewById(R.id.menu_ok);

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
                show_catalyst = sharedPreferences.getBoolean("show_catalyst",false);
                show_stars = sharedPreferences.getInt("weapon_stars",0);

                if(show_sword){show_sword = true;ico_sword.setColorFilter(Color.parseColor("#00000000"));}else{show_sword = false;ico_sword.setColorFilter(Color.parseColor("#66313131"));}
                if(show_claymore){show_claymore = true;ico_claymore.setColorFilter(Color.parseColor("#00000000"));}else{show_claymore = false;ico_claymore.setColorFilter(Color.parseColor("#66313131"));}
                if(show_polearm){show_polearm = true;ico_polearm.setColorFilter(Color.parseColor("#00000000"));}else{show_polearm = false;ico_polearm.setColorFilter(Color.parseColor("#66313131"));}
                if(show_bow){show_bow = true;ico_bow.setColorFilter(Color.parseColor("#00000000"));}else{show_bow = false;ico_bow.setColorFilter(Color.parseColor("#66313131"));}
                if(show_catalyst){show_catalyst = true;ico_catalyst.setColorFilter(Color.parseColor("#00000000"));}else{show_catalyst = false;ico_catalyst.setColorFilter(Color.parseColor("#66313131"));}
                ratingBar.setNumStars(5);
                ratingBar.setRating(show_stars);

                ico_sword.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_sword){show_sword = false;ico_sword.setColorFilter(Color.parseColor("#66313131"));}else{show_sword = true;ico_sword.setColorFilter(Color.parseColor("#00000000"));}}});
                ico_claymore.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_claymore){show_claymore = false;ico_claymore.setColorFilter(Color.parseColor("#66313131"));}else{show_claymore = true;ico_claymore.setColorFilter(Color.parseColor("#00000000"));}}});
                ico_polearm.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_polearm){show_polearm = false;ico_polearm.setColorFilter(Color.parseColor("#66313131"));}else{show_polearm = true;ico_polearm.setColorFilter(Color.parseColor("#00000000"));}}});
                ico_bow.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_bow){show_bow = false;ico_bow.setColorFilter(Color.parseColor("#66313131"));}else{show_bow = true;ico_bow.setColorFilter(Color.parseColor("#00000000"));}}});
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

                        ratingBar.setRating(0);

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
                        editor.putInt("weapon_stars", (int) ratingBar.getRating());
                        editor.apply();
                        dialog.dismiss();

                        mWeaponAdapter.filterList(weaponsList);

                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<Weapons> filteredList = new ArrayList<>();
                        for (Weapons item : weaponsList) {
                            if((show_sword == false && show_claymore == false && show_catalyst == false && show_bow == false && show_polearm == false)){
                                filteredList.add(item);
                            }else if(item.getWeapon().toLowerCase().equals("sword") && show_sword||item.getWeapon().toLowerCase().equals("claymore") && show_claymore||item.getWeapon().toLowerCase().equals("polearm") && show_polearm||item.getWeapon().toLowerCase().equals("bow") && show_bow||item.getWeapon().toLowerCase().equals("catalyst") && show_catalyst){
                                if(ratingBar.getRating() != 0 && item.getRare() == ratingBar.getRating()){
                                    filteredList.add(item);
                                }else if (ratingBar.getRating() == 0){
                                    filteredList.add(item);
                                }
                            }
                        }

                        mList_weapon.removeAllViews();
                        mWeaponAdapter.filterList(filteredList);
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
                        editor.putInt("weapon_stars", (int) ratingBar.getRating());
                        editor.apply();
                        dialog.dismiss();
                    }
                });

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
        });
    }

    private void artifact_setup() {
        mList_artifact = viewPager2.findViewById(R.id.main_list);
        mArtifactAdapter = new ArtifactsAdapter(context, artifactsList,activity);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);
        DisplayMetrics displayMetrics_a = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics_a);
        int height_a = displayMetrics_a.heightPixels;
        int width_a = displayMetrics_a.widthPixels;
        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            mLayoutManager = new GridLayoutManager(context,  width_a/400+1);
        }else{
            mLayoutManager = new GridLayoutManager(context,  3);
        }
        LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsMsg.gravity = Gravity.CENTER;
        mList_artifact.setLayoutManager(mLayoutManager);
        mList_artifact.setLayoutParams(paramsMsg);
        mList_artifact.setAdapter(mArtifactAdapter);
        mList_artifact.removeAllViewsInLayout();
        artifact_list_reload();

        // SQL DATA DISPLAY
        LinearLayout cal_choosed_list = viewPager2.findViewById(R.id.cal_artifact_choosed_list);
        cal_choosed_list.removeAllViews();
        for (int x = 0 ; x < artifactChoosedNameList.size(); x++){
            Log.w("artifactChoosedNameList"+String.valueOf(x),artifactChoosedNameList.get(x));
            View char_view = LayoutInflater.from(this).inflate(R.layout.item_img, cal_choosed_list, false);
            ImageView item_img = char_view.findViewById(R.id.item_img);
            String charName = artifactChoosedNameList.get(x);
            int finalX = x;
            item_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    artifactQuestion(charName,"EDIT", finalX,artifactChoosedRare.get(finalX));
                }
            });

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

            final int radius = 180;
            final int margin = 4;
            final Transformation transformation = new RoundedCornersTransformation(radius, margin);
            Picasso.get()
                    .load (FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(artifactChoosedNameList.get(x)),context)[tmp_artifact_type_id],context))
                    .transform(transformation)
                    .fit()
                    .error (R.drawable.paimon_full)
                    .into (item_img);

            ImageView item_user_img = char_view.findViewById(R.id.item_user_img);

            if(!artifactChoosedFollowList.get(x).isEmpty() && !artifactChoosedFollowList.get(x).equals("N/A")) {
                item_user_img.setVisibility(View.VISIBLE);
                Picasso.get()
                        .load(FileLoader.loadIMG(item_rss.getCharByName(artifactChoosedFollowList.get(x),context)[3],context))
                        .transform(transformation)
                        .resize(36, 36)
                        .error(R.drawable.paimon_full)
                        .into(item_user_img);
            }

            cal_choosed_list.addView(char_view);
        }

        EditText artifact_et = viewPager2.findViewById(R.id.artifact_et);
        artifact_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                ArrayList<Artifacts> filteredList = new ArrayList<>();
                int x = 0;
                for (Artifacts item : artifactsList) {
                    String str = String.valueOf(s).toLowerCase();
                    if (css.getArtifactByName(item.getName(),context)[0].contains(str)||css.getArtifactByName(item.getName(),context)[0].toLowerCase().contains(str)||css.getArtifactByName(item.getName(),context)[0].toUpperCase().contains(str)||item.getName().toLowerCase().contains(str)){ // EN -> ZH
                        filteredList.add(item);
                    }
                    x = x +1;
                }
                mArtifactAdapter.filterList(filteredList);
            }
        });

        ImageView artifact_filter = viewPager2.findViewById(R.id.artifact_filter);
        artifact_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.menu_artifact_filter, null);
                // Artifacts
                ImageView ico_flower = view.findViewById(R.id.ico_flower);
                ImageView ico_plume = view.findViewById(R.id.ico_plume);
                ImageView ico_sand = view.findViewById(R.id.ico_sand);
                ImageView ico_goblet = view.findViewById(R.id.ico_goblet);
                ImageView ico_circlet = view.findViewById(R.id.ico_circlet);
                // Rating
                RatingBar ratingBar = view.findViewById(R.id.menu_rating);
                // Function Buttons
                Button cancel = view.findViewById(R.id.menu_cancel);
                Button reset = view.findViewById(R.id.menu_reset);
                Button ok = view.findViewById(R.id.menu_ok);

                show_stars = sharedPreferences.getInt("artifact_stars",0);

                //if(show_sword){show_sword = true;ico_sword.setColorFilter(Color.parseColor("#00000000"));}else{show_sword = false;ico_sword.setColorFilter(Color.parseColor("#66313131"));}
                ratingBar.setNumStars(5);
                ratingBar.setRating(show_stars);

                //ico_sword.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_sword){show_sword = false;ico_sword.setColorFilter(Color.parseColor("#66313131"));}else{show_sword = true;ico_sword.setColorFilter(Color.parseColor("#00000000"));}}});

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ratingBar.setRating(0);

                        editor.putInt("artifact_stars", (int) ratingBar.getRating());
                        editor.apply();
                        dialog.dismiss();

                        mArtifactAdapter.filterList(artifactsList);

                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<Artifacts> filteredList = new ArrayList<>();
                        for (Artifacts item : artifactsList) {
                            if(ratingBar.getRating() != 0 && item.getRare() == ratingBar.getRating()){
                                filteredList.add(item);
                            }else if (ratingBar.getRating() == 0){
                                filteredList.add(item);
                            }
                        }

                        mList_artifact.removeAllViews();
                        mArtifactAdapter.filterList(filteredList);
                        editor.putInt("artifact_stars", (int) ratingBar.getRating());
                        editor.apply();
                        dialog.dismiss();
                    }
                });

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
        });
    }


    @SuppressLint("SetTextI18n")
    public void charQuestion(String CharName_BASE, String XPR, int k){
        normal_name = "XPR";
        element_name = "XPR";
        final_name = "XPR";

        /** Calculator vars -> Might change to int[] which sort by char update time*/
        before_lvl = 1;
        after_lvl = 90;
        before_break = 0;
        after_break = 6;
        skill1_lvl = 1;
        skill2_lvl = 1;
        skill3_lvl = 1;

        normal_skill_name = getString(R.string.unknown);
        element_skill_name = getString(R.string.unknown);
        final_skill_name = getString(R.string.unknown);

        sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        item_rss = new ItemRss();

        String CharName_BASE_UNDERSCORE = CharName_BASE.replace(" ","_");

        String lang = sharedPreferences.getString("curr_lang","zh-HK");
        AssetManager mg = context.getResources().getAssets();

        String is = null;
        String is_default = null;
        String result1 = null;

        is_default = LoadData("db/char/en-US/"+CharName_BASE_UNDERSCORE+".json");
        is = LoadData("db/char/"+lang+"/"+CharName_BASE_UNDERSCORE+".json");

        if(is != null){
            result1 = is;
        }else if(is_default != null){
            result1 = is_default;
        }


        if(result1 != null){
            try {
                JSONObject jsonObject = new JSONObject(result1);
                JSONObject battle_talent = jsonObject.getJSONObject("battle_talent");
                normal_skill_name = battle_talent.getString("normal_name");
                element_skill_name = battle_talent.getString("element_name");
                final_skill_name = battle_talent.getString("final_name");
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            normal_skill_name = getString(R.string.unknown);
            element_skill_name = getString(R.string.unknown);
            final_skill_name = getString(R.string.unknown);
            //Toast.makeText(context, "暫時沒有他/她的相關資料X1", Toast.LENGTH_SHORT).show();
        }

        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.menu_char_add, null);

        // Function method
        Button cancel = view.findViewById(R.id.menu_cancel);
        Button ok = view.findViewById(R.id.menu_ok);
        Button delete = view.findViewById(R.id.menu_delete);
        TextView menu_title = view.findViewById(R.id.menu_title);
        Button menu_char_lvl_before = view.findViewById(R.id.menu_char_lvl_before);
        Button menu_char_lvl_after = view.findViewById(R.id.menu_char_lvl_after);
        //RatingBar menu_break_lvl_before_rating = view.findViewById(R.id.menu_break_lvl_before_rating);
        //RatingBar menu_break_lvl_after_rating = view.findViewById(R.id.menu_break_lvl_after_rating);
        TextView menu_skill1_title = view.findViewById(R.id.menu_skill1_title);
        TextView menu_skill2_title = view.findViewById(R.id.menu_skill2_title);
        TextView menu_skill3_title = view.findViewById(R.id.menu_skill3_title);

        SeekBar menu_skill1_before_pb = view.findViewById(R.id.menu_skill1_before_pb);
        TextView menu_skill1_before_tv = view.findViewById(R.id.menu_skill1_before_tv);
        SeekBar menu_skill1_after_pb = view.findViewById(R.id.menu_skill1_after_pb);
        TextView menu_skill1_after_tv = view.findViewById(R.id.menu_skill1_after_tv);
        SeekBar menu_skill2_before_pb = view.findViewById(R.id.menu_skill2_before_pb);
        TextView menu_skill2_before_tv = view.findViewById(R.id.menu_skill2_before_tv);
        SeekBar menu_skill2_after_pb = view.findViewById(R.id.menu_skill2_after_pb);
        TextView menu_skill2_after_tv = view.findViewById(R.id.menu_skill2_after_tv);
        SeekBar menu_skill3_before_pb = view.findViewById(R.id.menu_skill3_before_pb);
        TextView menu_skill3_before_tv = view.findViewById(R.id.menu_skill3_before_tv);
        SeekBar menu_skill3_after_pb = view.findViewById(R.id.menu_skill3_after_pb);
        TextView menu_skill3_after_tv = view.findViewById(R.id.menu_skill3_after_tv);

        Switch menu_cal = view.findViewById(R.id.menu_cal);
        Switch menu_break_lvl_before_switch = view.findViewById(R.id.menu_break_lvl_before_switch);
        Switch menu_break_lvl_after_switch = view.findViewById(R.id.menu_break_lvl_after_switch);

        SharedPreferences sharedPreferences = getSharedPreferences("user_info",MODE_PRIVATE);
        String color_hex = sharedPreferences.getString("theme_color_hex","#FF5A5A"); // Must include #

        ColorStateList myList = new ColorStateList(
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

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        if(color_hex.toUpperCase().equals("#FFFFFFFF")){
            window.setStatusBarColor(Color.parseColor("#000000"));}
        else {
            window.setStatusBarColor(Color.parseColor(color_hex));
            Log.w("WRF",color_hex);
        }

        menu_cal.setThumbTintList(myList);
        menu_cal.setTrackTintList(myList);
        menu_break_lvl_before_switch.setThumbTintList(myList);
        menu_break_lvl_before_switch.setTrackTintList(myList);
        menu_break_lvl_after_switch.setThumbTintList(myList);
        menu_break_lvl_after_switch.setTrackTintList(myList);

        menu_title.setText(item_rss.getCharByName(CharName_BASE,context)[1]);
        menu_char_lvl_before.setText(getString(R.string.curr_lvl)+String.valueOf(1));
        menu_char_lvl_after.setText(getString(R.string.aim_lvl)+String.valueOf(90));

        // Will set to check zh / en later
        menu_skill1_title.setText(normal_skill_name);
        menu_skill2_title.setText(element_skill_name);
        menu_skill3_title.setText(final_skill_name);

        if(XPR.equals("EDIT")){
            delete.setVisibility(View.VISIBLE);
            before_lvl = choosedBeforeLvlList.get(k);
            after_lvl = choosedAfterLvlList.get(k);
            before_break = choosedBeforeBreakLvlList.get(k);
            after_break = choosedAfterBreakLvlList.get(k);
            menu_char_lvl_before.setText(getString(R.string.curr_lvl)+String.valueOf(choosedBeforeLvlList.get(k)));
            menu_char_lvl_after.setText(getString(R.string.aim_lvl)+String.valueOf(choosedAfterLvlList.get(k)));
            menu_skill1_before_tv.setText(String.valueOf(choosedBeforeSkill1LvlList.get(k)));
            menu_skill1_after_tv.setText(String.valueOf(choosedAfterSkill1LvlList.get(k)));
            menu_skill2_before_tv.setText(String.valueOf(choosedBeforeSkill2LvlList.get(k)));
            menu_skill2_after_tv.setText(String.valueOf(choosedAfterSkill2LvlList.get(k)));
            menu_skill3_before_tv.setText(String.valueOf(choosedBeforeSkill3LvlList.get(k)));
            menu_skill3_after_tv.setText(String.valueOf(choosedAfterSkill3LvlList.get(k)));
            menu_skill1_before_pb.setProgress(choosedBeforeSkill1LvlList.get(k));
            menu_skill1_after_pb.setProgress(choosedAfterSkill1LvlList.get(k));
            menu_skill2_before_pb.setProgress(choosedBeforeSkill2LvlList.get(k));
            menu_skill2_after_pb.setProgress(choosedAfterSkill2LvlList.get(k));
            menu_skill3_before_pb.setProgress(choosedBeforeSkill3LvlList.get(k));
            menu_skill3_after_pb.setProgress(choosedAfterSkill3LvlList.get(k));
            menu_cal.setChecked(choosedIsCal.get(k));
            menu_break_lvl_before_switch.setChecked(choosedBeforeBreakUPLvlList.get(k));
            menu_break_lvl_after_switch.setChecked(choosedAfterBreakUPLvlList.get(k));
        }

        menu_break_lvl_before_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(before_lvl ==20 | before_lvl ==40 | before_lvl ==50 | before_lvl ==60 | before_lvl ==70 | before_lvl ==80){
                    if(menu_break_lvl_before_switch.isChecked()){
                        if(after_break >= before_break +1){
                            menu_break_lvl_before_switch.setChecked(true);
                        }
                        else{menu_break_lvl_before_switch.setChecked(false);}
                    }
                    else {menu_break_lvl_before_switch.setChecked(false);}
                }
                else {menu_break_lvl_before_switch.setChecked(false);}
            }
        });


        menu_break_lvl_after_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(after_lvl ==20 | after_lvl ==40 | after_lvl ==50 | after_lvl ==60 | after_lvl ==70 | after_lvl ==80){
                    if(menu_break_lvl_after_switch.isChecked()){
                        if(before_break <= after_break){
                            menu_break_lvl_after_switch.setChecked(true);
                        }
                        else{menu_break_lvl_after_switch.setChecked(false);}
                    }
                    else {menu_break_lvl_after_switch.setChecked(false);}
                }
                else {menu_break_lvl_after_switch.setChecked(false);}
            }
        });

        menu_char_lvl_before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                npd.setLastValue(before_lvl);

                npd.setMaxValue(90);
                npd.setMinValue(1);
                npd.showDialog("LVL_BEFORE");
            }
        });

        menu_char_lvl_after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                npd.setLastValue(after_lvl);
                npd.setMaxValue(90);
                npd.setMinValue(before_lvl);
                npd.showDialog("LVL_AFTER");
            }
        });
        /**這邊取得自己所設置之模組回調*/

        npd.onDialogRespond = new NumberPickerDialog.OnDialogRespond() {
            @Override
            public void onRespond(int value , String XPR) {
                if(XPR.equals("LVL_BEFORE")){
                    before_lvl = value;
                    if(before_lvl > 80 ){before_break =6;}
                    else if(before_lvl > 70 && before_lvl <= 80){before_break =5;}
                    else if(before_lvl > 60 && before_lvl <= 70){before_break =4;}
                    else if(before_lvl > 50 && before_lvl <= 60){before_break =3;}
                    else if(before_lvl > 40 && before_lvl <= 50){before_break =2;}
                    else if(before_lvl > 20 && before_lvl <= 40){before_break =1;}
                    else if(before_lvl <= 20 ){before_break =0;}
                    menu_char_lvl_before.setText(getString(R.string.curr_lvl)+String.valueOf(before_lvl));

                    if(value > after_lvl){
                        after_lvl = (int) value;
                        menu_char_lvl_after.setText(getString(R.string.aim_lvl)+String.valueOf(after_lvl));
                    }

                    if(after_lvl > 80 ){after_break =6;}
                    else if(after_lvl > 70 ){after_break =5;}
                    else if(after_lvl > 60 ){after_break =4;}
                    else if(after_lvl > 50 ){after_break =3;}
                    else if(after_lvl > 40 ){after_break =2;}
                    else if(after_lvl > 20 ){after_break =1;}
                    else if(after_lvl < 20 ){after_break =0;}

                    if(menu_break_lvl_before_switch.isChecked()){
                        if(before_lvl !=20 | before_lvl !=40 | before_lvl !=50 | before_lvl !=60 | before_lvl !=70 | before_lvl !=80){
                            menu_break_lvl_before_switch.setChecked(false);
                        }
                    }

                    if(menu_break_lvl_after_switch.isChecked()){
                        if(after_lvl !=20 | after_lvl !=40 | after_lvl !=50 | after_lvl !=60 | after_lvl !=70 | after_lvl !=80){
                            menu_break_lvl_after_switch.setChecked(false);
                        }
                    }

                }else if(XPR.equals("LVL_AFTER")){
                    after_lvl = value;
                    if(after_lvl > 80 ){after_break =6;}
                    else if(after_lvl > 70 && after_lvl <=80){after_break =5;}
                    else if(after_lvl > 60 && after_lvl <=70){after_break =4;}
                    else if(after_lvl > 50 && after_lvl <=60){after_break =3;}
                    else if(after_lvl > 40 && after_lvl <=50){after_break =2;}
                    else if(after_lvl > 20 && after_lvl <=40){after_break =1;}
                    else if(after_lvl <= 20 ){after_break =0;}
                    menu_char_lvl_after.setText(getString(R.string.aim_lvl)+String.valueOf(after_lvl));

                    if(value < before_lvl){
                        before_lvl = (int) value;
                        menu_char_lvl_before.setText(getString(R.string.curr_lvl)+String.valueOf(before_lvl));
                    }

                    if(before_lvl > 80 ){before_break =6;}
                    else if(before_lvl > 70 ){before_break =5;}
                    else if(before_lvl > 60 ){before_break =4;}
                    else if(before_lvl > 50 ){before_break =3;}
                    else if(before_lvl > 40 ){before_break =2;}
                    else if(before_lvl > 20 ){before_break =1;}
                    else if(before_lvl < 20 ){before_break =0;}

                    if(menu_break_lvl_before_switch.isChecked()){
                        if(before_lvl !=20 | before_lvl !=40 | before_lvl !=50 | before_lvl !=60 | before_lvl !=70 | before_lvl !=80){
                            menu_break_lvl_before_switch.setChecked(false);
                        }
                    }

                    if(menu_break_lvl_after_switch.isChecked()){
                        if(after_lvl !=20 | after_lvl !=40 | after_lvl !=50 | after_lvl !=60 | after_lvl !=70 | after_lvl !=80){
                            menu_break_lvl_after_switch.setChecked(false);
                        }
                    }
                }
            }
        };
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                String name_del = CharName_BASE;
                for (int p = 0 ; p < choosedNameList.size() ; p ++){
                    if(choosedNameList.get(p).equals(name_del)){

                        // SQL !

                        DataBaseHelper dbHelper = new DataBaseHelper(context);
                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                        // Define 'where' part of query.
                        String selection = "charName" + " LIKE ?";
                        // Specify arguments in placeholder order.
                        String[] selectionArgs = { name_del };
                        // Issue SQL statement.
                        db.delete(dataSheetName+"_char", selection, selectionArgs);

                        choosedNameList.remove(p);
                        choosedBeforeLvlList.remove(p);
                        choosedAfterLvlList.remove(p);
                        choosedBeforeBreakLvlList.remove(p);
                        choosedAfterBreakLvlList.remove(p);
                        choosedBeforeSkill1LvlList.remove(p);
                        choosedAfterSkill1LvlList.remove(p);
                        choosedBeforeSkill2LvlList.remove(p);
                        choosedAfterSkill2LvlList.remove(p);
                        choosedBeforeSkill3LvlList.remove(p);
                        choosedAfterSkill3LvlList.remove(p);
                        choosedIsCal.remove(p);
                        choosedBeforeBreakUPLvlList.remove(p);
                        choosedAfterBreakUPLvlList.remove(p);

                        for (int x = 0 ; x < weaponChoosedNameList.size() ; x++){
                            if(weaponChoosedNameList.get(x).equals(name_del)){
                                weaponChoosedFollowList.set(x,"NaN");
                            }
                        }

                        charHasFlower.remove(p);
                        charHasPlume.remove(p);
                        charHasSand.remove(p);
                        charHasGoblet.remove(p);
                        charHasCirclet.remove(p);
                        charHasWeapon.remove(p);

                        LinearLayout cal_choosed_list = findViewById(R.id.cal_choosed_list);
                        cal_choosed_list.removeAllViews();

                        for (int x = 0 ; x < choosedNameList.size(); x++){
                            Log.w("choosedNameList"+String.valueOf(x),choosedNameList.get(x));
                            View char_view = LayoutInflater.from(context).inflate(R.layout.item_img, cal_choosed_list, false);
                            ImageView item_img = char_view.findViewById(R.id.item_img);
                            String charName = choosedNameList.get(x);
                            int finalX = x;
                            item_img.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    charQuestion(charName,"EDIT", finalX);
                                }
                            });

                            final int radius = 180;
                            final int margin = 4;
                            final Transformation transformation = new RoundedCornersTransformation(radius, margin);
                            Picasso.get()
                                    .load (FileLoader.loadIMG(item_rss.getCharByName(choosedNameList.get(x),context)[3],context))
                                    .transform(transformation)
                                    .fit()
                                    .error (R.drawable.paimon_full)
                                    .into (item_img);

                            cal_choosed_list.addView(char_view);
                        }
                    }
                }
                calculatorProcess.setDBName(dataSheetName);
                calculatorProcess.setup(context,activity);
                calculatorProcess.char_setup(choosedNameList,choosedBeforeLvlList,choosedAfterLvlList,choosedBeforeBreakLvlList,choosedAfterBreakLvlList,choosedBeforeSkill1LvlList,choosedAfterSkill1LvlList,choosedBeforeSkill2LvlList,choosedAfterSkill2LvlList,choosedBeforeSkill3LvlList,choosedAfterSkill3LvlList,choosedIsCal,choosedBeforeBreakUPLvlList,choosedAfterBreakUPLvlList,"WRITE");
                calculatorProcess.artifact_setup(artifactChoosedNameList,artifactChoosedBeforeLvlList,artifactChoosedAfterLvlList,artifactChoosedIsCal,artifactChoosedFollowList,artifactChoosedRare,artifactChoosedType,artifactChoosedIdList,"READ");
                calculatorProcess.weapon_setup(weaponChoosedNameList,weaponChoosedBeforeLvlList,weaponChoosedAfterLvlList,weaponChoosedBeforeBreakLvlList,weaponChoosedAfterBreakLvlList,weaponChoosedIsCal,weaponChoosedBeforeBreakUPLvlList,weaponChoosedAfterBreakUPLvlList,weaponChoosedFollowList,weaponChoosedRare,weaponChoosedIdList,"READ");
                calculatorProcess.saveToDB();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                int skill1_before = menu_skill1_before_pb.getProgress();
                int skill1_after = menu_skill1_after_pb.getProgress();
                int skill2_before = menu_skill2_before_pb.getProgress();
                int skill2_after = menu_skill2_after_pb.getProgress();
                int skill3_before = menu_skill3_before_pb.getProgress();
                int skill3_after = menu_skill3_after_pb.getProgress();
                boolean isCal = menu_cal.isChecked();

                addCharIntoListUI(CharName_BASE,before_lvl,after_lvl,before_break,after_break,skill1_before,skill1_after,skill2_before,skill2_after,skill3_before,skill3_after,isCal,menu_break_lvl_before_switch.isChecked(),menu_break_lvl_after_switch.isChecked(),XPR,k);

                calculatorProcess.setDBName(dataSheetName);
                calculatorProcess.setup(context,activity);
                calculatorProcess.char_setup(choosedNameList,choosedBeforeLvlList,choosedAfterLvlList,choosedBeforeBreakLvlList,choosedAfterBreakLvlList,choosedBeforeSkill1LvlList,choosedAfterSkill1LvlList,choosedBeforeSkill2LvlList,choosedAfterSkill2LvlList,choosedBeforeSkill3LvlList,choosedAfterSkill3LvlList,choosedIsCal,choosedBeforeBreakUPLvlList,choosedAfterBreakUPLvlList,"WRITE");
                calculatorProcess.artifact_setup(artifactChoosedNameList,artifactChoosedBeforeLvlList,artifactChoosedAfterLvlList,artifactChoosedIsCal,artifactChoosedFollowList,artifactChoosedRare,artifactChoosedType,artifactChoosedIdList,"READ");
                calculatorProcess.weapon_setup(weaponChoosedNameList,weaponChoosedBeforeLvlList,weaponChoosedAfterLvlList,weaponChoosedBeforeBreakLvlList,weaponChoosedAfterBreakLvlList,weaponChoosedIsCal,weaponChoosedBeforeBreakUPLvlList,weaponChoosedAfterBreakUPLvlList,weaponChoosedFollowList,weaponChoosedRare,weaponChoosedIdList,"READ");
                calculatorProcess.saveToDB();


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        menu_skill1_before_pb.setOnSeekBarChangeListener(seekBarChangeListenerBefore(menu_skill1_before_pb,menu_skill1_after_pb,menu_skill1_before_tv));
        menu_skill1_after_pb.setOnSeekBarChangeListener(seekBarChangeListenerAfter(menu_skill1_after_pb,menu_skill1_before_pb,menu_skill1_after_tv));
        menu_skill2_before_pb.setOnSeekBarChangeListener(seekBarChangeListenerBefore(menu_skill2_before_pb,menu_skill2_after_pb,menu_skill2_before_tv));
        menu_skill2_after_pb.setOnSeekBarChangeListener(seekBarChangeListenerAfter(menu_skill2_after_pb,menu_skill2_before_pb,menu_skill2_after_tv));
        menu_skill3_before_pb.setOnSeekBarChangeListener(seekBarChangeListenerBefore(menu_skill3_before_pb,menu_skill3_after_pb,menu_skill3_before_tv));
        menu_skill3_after_pb.setOnSeekBarChangeListener(seekBarChangeListenerAfter(menu_skill3_after_pb,menu_skill3_before_pb,menu_skill3_after_tv));

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


    public void weaponQuestion (String CharName_BASE, String XPR, int k, int rare){
        normal_name = "XPR";
        element_name = "XPR";
        final_name = "XPR";

        /** Calculator vars -> Might change to int[] which sort by char update time*/
        before_lvl = 1;
        after_lvl = 90;
        if(rare < 3){
            after_lvl = 70;
        }
        before_break = 0;
        after_break = 6;
        if(rare < 3){
            after_break = 4;
        }
        skill1_lvl = 1;
        skill2_lvl = 1;
        skill3_lvl = 1;

        normal_skill_name = getString(R.string.unknown);
        element_skill_name = getString(R.string.unknown);
        final_skill_name = getString(R.string.unknown);

        sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        item_rss = new ItemRss();

        String CharName_BASE_UNDERSCORE = CharName_BASE.replace(" ","_");

        String lang = sharedPreferences.getString("curr_lang","zh-HK");
        AssetManager mg = context.getResources().getAssets();

        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.menu_char_add, null);

        // Function method
        Button cancel = view.findViewById(R.id.menu_cancel);
        Button ok = view.findViewById(R.id.menu_ok);
        Button delete = view.findViewById(R.id.menu_delete);
        TextView menu_title = view.findViewById(R.id.menu_title);
        Button menu_char_lvl_before = view.findViewById(R.id.menu_char_lvl_before);
        Button menu_char_lvl_after = view.findViewById(R.id.menu_char_lvl_after);
        TextView menu_skill1_title = view.findViewById(R.id.menu_skill1_title);
        TextView menu_skill2_title = view.findViewById(R.id.menu_skill2_title);
        TextView menu_skill3_title = view.findViewById(R.id.menu_skill3_title);

        SeekBar menu_skill1_before_pb = view.findViewById(R.id.menu_skill1_before_pb);
        TextView menu_skill1_before_tv = view.findViewById(R.id.menu_skill1_before_tv);
        SeekBar menu_skill1_after_pb = view.findViewById(R.id.menu_skill1_after_pb);
        TextView menu_skill1_after_tv = view.findViewById(R.id.menu_skill1_after_tv);
        SeekBar menu_skill2_before_pb = view.findViewById(R.id.menu_skill2_before_pb);
        TextView menu_skill2_before_tv = view.findViewById(R.id.menu_skill2_before_tv);
        SeekBar menu_skill2_after_pb = view.findViewById(R.id.menu_skill2_after_pb);
        TextView menu_skill2_after_tv = view.findViewById(R.id.menu_skill2_after_tv);
        SeekBar menu_skill3_before_pb = view.findViewById(R.id.menu_skill3_before_pb);
        TextView menu_skill3_before_tv = view.findViewById(R.id.menu_skill3_before_tv);
        SeekBar menu_skill3_after_pb = view.findViewById(R.id.menu_skill3_after_pb);
        TextView menu_skill3_after_tv = view.findViewById(R.id.menu_skill3_after_tv);

        Switch menu_cal = view.findViewById(R.id.menu_cal);
        Switch menu_break_lvl_before_switch = view.findViewById(R.id.menu_break_lvl_before_switch);
        Switch menu_break_lvl_after_switch = view.findViewById(R.id.menu_break_lvl_after_switch);
        View divider = view.findViewById(R.id.divider);

        SharedPreferences sharedPreferences = getSharedPreferences("user_info",MODE_PRIVATE);
        String color_hex = sharedPreferences.getString("theme_color_hex","#FF5A5A"); // Must include #

        ColorStateList myList = new ColorStateList(
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

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        if(color_hex.toUpperCase().equals("#FFFFFFFF")){
            window.setStatusBarColor(Color.parseColor("#000000"));}
        else {
            window.setStatusBarColor(Color.parseColor(color_hex));
            Log.w("WRF",color_hex);
        }

        menu_cal.setThumbTintList(myList);
        menu_cal.setTrackTintList(myList);
        menu_break_lvl_before_switch.setThumbTintList(myList);
        menu_break_lvl_before_switch.setTrackTintList(myList);
        menu_break_lvl_after_switch.setThumbTintList(myList);
        menu_break_lvl_after_switch.setTrackTintList(myList);

        menu_char_lvl_before.setText(getString(R.string.curr_lvl)+String.valueOf(before_lvl));
        menu_char_lvl_after.setText(getString(R.string.aim_lvl)+String.valueOf(after_lvl));
        menu_title.setText(item_rss.getWeaponByName(CharName_BASE,context)[0]);

        divider.setVisibility(View.GONE);
        menu_skill1_title.setVisibility(View.GONE);
        menu_skill2_title.setVisibility(View.GONE);
        menu_skill3_title.setVisibility(View.GONE);
        menu_skill1_before_pb.setVisibility(View.GONE);
        menu_skill1_before_tv.setVisibility(View.GONE);
        menu_skill1_after_pb.setVisibility(View.GONE);
        menu_skill1_after_tv.setVisibility(View.GONE);
        menu_skill2_before_pb.setVisibility(View.GONE);
        menu_skill2_before_tv.setVisibility(View.GONE);
        menu_skill2_after_pb.setVisibility(View.GONE);
        menu_skill2_after_tv.setVisibility(View.GONE);
        menu_skill3_before_pb.setVisibility(View.GONE);
        menu_skill3_before_tv.setVisibility(View.GONE);
        menu_skill3_after_pb.setVisibility(View.GONE);
        menu_skill3_after_tv.setVisibility(View.GONE);

        String[] langList = new String[choosedNameList.size()];
        for (int x = 0 ; x < choosedNameList.size() ; x++){
            langList[x] = item_rss.getCharByName(choosedNameList.get(x),context)[1];
        }

        ArrayAdapter char_aa = new ArrayAdapter(context,R.layout.spinner_item,langList);
        char_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);

        Spinner char_sp = view.findViewById(R.id.menu_char_follow);
        char_sp.setVisibility(View.VISIBLE);
        char_sp.setAdapter(char_aa);
        char_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                follow_char_tmp = choosedNameList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                follow_char_tmp = "NaN";
            }
        });

        if(XPR.equals("EDIT")){
            delete.setVisibility(View.VISIBLE);
            before_lvl = weaponChoosedBeforeLvlList.get(k);
            after_lvl = weaponChoosedAfterLvlList.get(k);
            before_break = weaponChoosedBeforeBreakLvlList.get(k);
            after_break = weaponChoosedAfterBreakLvlList.get(k);
            menu_char_lvl_before.setText(getString(R.string.curr_lvl)+String.valueOf(weaponChoosedBeforeLvlList.get(k)));
            menu_char_lvl_after.setText(getString(R.string.aim_lvl)+String.valueOf(weaponChoosedAfterLvlList.get(k)));
            menu_cal.setChecked(weaponChoosedIsCal.get(k));
            menu_break_lvl_before_switch.setChecked(weaponChoosedBeforeBreakUPLvlList.get(k));
            menu_break_lvl_after_switch.setChecked(weaponChoosedAfterBreakUPLvlList.get(k));

            langList = new String[choosedNameList.size()];
            for (int x = 0 ; x < choosedNameList.size() ; x++){
                langList[x] = item_rss.getCharByName(choosedNameList.get(x),context)[1];
                if(choosedNameList.get(x).equals(weaponChoosedFollowList.get(k))){
                    char_aa = new ArrayAdapter(context,R.layout.spinner_item,langList);
                    char_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    char_sp.setAdapter(char_aa);
                    char_sp.setSelection(x);
                }else {
                    char_sp.setSelection(0);
                }
            }
        }

        menu_char_lvl_before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                npd.setLastValue(before_lvl);
                if(rare < 3){
                    npd.setMaxValue(70);
                }else {
                    npd.setMaxValue(90);
                }
                npd.setMinValue(1);
                npd.showDialog("LVL_BEFORE");
            }
        });

        menu_char_lvl_after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                npd.setLastValue(after_lvl);
                if(rare < 3){
                    npd.setMaxValue(70);
                }else {
                    npd.setMaxValue(90);
                }
                npd.setMinValue(before_lvl);
                npd.showDialog("LVL_AFTER");
            }
        });
        /**這邊取得自己所設置之模組回調*/

        npd.onDialogRespond = new NumberPickerDialog.OnDialogRespond() {
            @Override
            public void onRespond(int value , String XPR) {
                if(XPR.equals("LVL_BEFORE")){
                    before_lvl = value;
                    if(before_lvl > 80 ){before_break =6;}
                    else if(before_lvl > 70 && before_lvl <= 80){before_break =5;}
                    else if(before_lvl > 60 && before_lvl <= 70){before_break =4;}
                    else if(before_lvl > 50 && before_lvl <= 60){before_break =3;}
                    else if(before_lvl > 40 && before_lvl <= 50){before_break =2;}
                    else if(before_lvl > 20 && before_lvl <= 40){before_break =1;}
                    else if(before_lvl <= 20 ){before_break =0;}
                    menu_char_lvl_before.setText(getString(R.string.curr_lvl)+String.valueOf(before_lvl));

                    if(value > after_lvl){
                        after_lvl = (int) value;
                        menu_char_lvl_after.setText(getString(R.string.aim_lvl)+String.valueOf(after_lvl));
                    }

                    if(after_lvl > 80 ){after_break =6;}
                    else if(after_lvl > 70 ){after_break =5;}
                    else if(after_lvl > 60 ){after_break =4;}
                    else if(after_lvl > 50 ){after_break =3;}
                    else if(after_lvl > 40 ){after_break =2;}
                    else if(after_lvl > 20 ){after_break =1;}
                    else if(after_lvl < 20 ){after_break =0;}

                    if(menu_break_lvl_before_switch.isChecked()){
                        if(before_lvl !=20 | before_lvl !=40 | before_lvl !=50 | before_lvl !=60 | before_lvl !=70 | before_lvl !=80){
                            menu_break_lvl_before_switch.setChecked(false);
                        }
                    }

                    if(menu_break_lvl_after_switch.isChecked()){
                        if(after_lvl !=20 | after_lvl !=40 | after_lvl !=50 | after_lvl !=60 | after_lvl !=70 | after_lvl !=80){
                            menu_break_lvl_after_switch.setChecked(false);
                        }
                    }

                }else if(XPR.equals("LVL_AFTER")){
                    after_lvl = value;
                    if(after_lvl > 80 ){after_break =6;}
                    else if(after_lvl > 70 && after_lvl <=80){after_break =5;}
                    else if(after_lvl > 60 && after_lvl <=70){after_break =4;}
                    else if(after_lvl > 50 && after_lvl <=60){after_break =3;}
                    else if(after_lvl > 40 && after_lvl <=50){after_break =2;}
                    else if(after_lvl > 20 && after_lvl <=40){after_break =1;}
                    else if(after_lvl <= 20 ){after_break =0;}
                    menu_char_lvl_after.setText(getString(R.string.aim_lvl)+String.valueOf(after_lvl));

                    if(value < before_lvl){
                        before_lvl = (int) value;
                        menu_char_lvl_before.setText(getString(R.string.curr_lvl)+String.valueOf(before_lvl));
                    }

                    if(before_lvl > 80 ){before_break =6;}
                    else if(before_lvl > 70 ){before_break =5;}
                    else if(before_lvl > 60 ){before_break =4;}
                    else if(before_lvl > 50 ){before_break =3;}
                    else if(before_lvl > 40 ){before_break =2;}
                    else if(before_lvl > 20 ){before_break =1;}
                    else if(before_lvl < 20 ){before_break =0;}

                    if(menu_break_lvl_before_switch.isChecked()){
                        if(before_lvl !=20 | before_lvl !=40 | before_lvl !=50 | before_lvl !=60 | before_lvl !=70 | before_lvl !=80){
                            menu_break_lvl_before_switch.setChecked(false);
                        }
                    }

                    if(menu_break_lvl_after_switch.isChecked()){
                        if(after_lvl !=20 | after_lvl !=40 | after_lvl !=50 | after_lvl !=60 | after_lvl !=70 | after_lvl !=80){
                            menu_break_lvl_after_switch.setChecked(false);
                        }
                    }
                }
            }
        };
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                String name_del = CharName_BASE;
                for (int p = 0 ; p < weaponChoosedNameList.size() ; p ++){
                    if(weaponChoosedNameList.get(p).equals(name_del)){

                        // SQL !

                       /*
                        DataBaseHelper dbHelper = new DataBaseHelper(context);
                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                        // Define 'where' part of query.
                        String selection = "weaponName" + " LIKE ?";
                        // Specify arguments in placeholder order.
                        String[] selectionArgs = { name_del };
                        // Issue SQL statement.
                        db.delete(dataSheetName+"_weapon", selection, selectionArgs);
                        */

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

                        while(cursor.moveToNext()) {
                            weaponChoosedIdList.add(cursor.getInt(cursor.getColumnIndexOrThrow("weaponId")));
                        }

                        //db.execSQL("DELETE FROM "+dataSheetName+"_artifact"+" WHERE artifactName = \""+name_del+"\" AND artifactType = \""+type_del+"\" AND artifactFollow = \""+follow_del+"\"");
                        db.execSQL("DELETE FROM "+dataSheetName+"_weapon"+" WHERE weaponId = "+weaponChoosedIdList.get(k)+";");


                        for (int x = 0 ; x < weaponChoosedNameList.size(); x++){
                            if(weaponChoosedNameList.get(x).equals(weaponChoosedFollowList.get(p))){
                                charHasWeapon.set(x,false);
                            }
                        }

                        weaponChoosedNameList.remove(p);
                        weaponChoosedBeforeLvlList.remove(p);
                        weaponChoosedAfterLvlList.remove(p);
                        weaponChoosedBeforeBreakLvlList.remove(p);
                        weaponChoosedAfterBreakLvlList.remove(p);
                        weaponChoosedBeforeBreakUPLvlList.remove(p);
                        weaponChoosedAfterBreakUPLvlList.remove(p);
                        weaponChoosedRare.remove(p);
                        weaponChoosedIsCal.remove(p);
                        weaponChoosedFollowList.remove(p);

                        LinearLayout cal_choosed_list = findViewById(R.id.cal_weapon_choosed_list);
                        cal_choosed_list.removeAllViews();
                        for (int x = 0 ; x < weaponChoosedNameList.size(); x++){
                            View char_view = LayoutInflater.from(context).inflate(R.layout.item_img, cal_choosed_list, false);
                            ImageView item_img = char_view.findViewById(R.id.item_img);
                            String charName = weaponChoosedNameList.get(x);
                            int finalX = x;
                            item_img.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    weaponQuestion(charName,"EDIT", finalX,rare);
                                }
                            });

                            final int radius = 180;
                            final int margin = 4;
                            final Transformation transformation = new RoundedCornersTransformation(radius, margin);
                            Picasso.get()
                                    .load (FileLoader.loadIMG(item_rss.getWeaponByName(weaponChoosedNameList.get(x),context)[1],context))
                                    .transform(transformation)
                                    .fit()
                                    .error (R.drawable.paimon_full)
                                    .into (item_img);

                            ImageView item_user_img = char_view.findViewById(R.id.item_user_img);
                            if(!weaponChoosedFollowList.get(x).isEmpty() && !weaponChoosedFollowList.get(x).equals("N/A")) {
                                item_user_img.setVisibility(View.VISIBLE);
                                Picasso.get()
                                        .load(FileLoader.loadIMG(item_rss.getCharByName(weaponChoosedFollowList.get(x), context)[3],context))
                                        .transform(transformation)
                                        .resize(36, 36)
                                        .error(R.drawable.paimon_full)
                                        .into(item_user_img);
                            }
                            cal_choosed_list.addView(char_view);
                        }


                    }
                }

                calculatorProcess.setDBName(dataSheetName);
                calculatorProcess.setup(context,activity);
                calculatorProcess.char_setup(choosedNameList,choosedBeforeLvlList,choosedAfterLvlList,choosedBeforeBreakLvlList,choosedAfterBreakLvlList,choosedBeforeSkill1LvlList,choosedAfterSkill1LvlList,choosedBeforeSkill2LvlList,choosedAfterSkill2LvlList,choosedBeforeSkill3LvlList,choosedAfterSkill3LvlList,choosedIsCal,choosedBeforeBreakUPLvlList,choosedAfterBreakUPLvlList,"WRITE");
                calculatorProcess.artifact_setup(artifactChoosedNameList,artifactChoosedBeforeLvlList,artifactChoosedAfterLvlList,artifactChoosedIsCal,artifactChoosedFollowList,artifactChoosedRare,artifactChoosedType,artifactChoosedIdList,"READ");
                calculatorProcess.weapon_setup(weaponChoosedNameList,weaponChoosedBeforeLvlList,weaponChoosedAfterLvlList,weaponChoosedBeforeBreakLvlList,weaponChoosedAfterBreakLvlList,weaponChoosedIsCal,weaponChoosedBeforeBreakUPLvlList,weaponChoosedAfterBreakUPLvlList,weaponChoosedFollowList,weaponChoosedRare,weaponChoosedIdList,"READ");
                calculatorProcess.saveToDB();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                boolean isCal = menu_cal.isChecked();

                DataBaseHelper dbHelper = new DataBaseHelper(context);
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                cursorWeaponIds();

                addWeaponIntoListUI(CharName_BASE,before_lvl,after_lvl,before_break,after_break,isCal,menu_break_lvl_before_switch.isChecked(),menu_break_lvl_after_switch.isChecked(),XPR,k,rare,follow_char_tmp);
                calculatorProcess.setDBName(dataSheetName);
                calculatorProcess.setup(context,activity);
                calculatorProcess.char_setup(choosedNameList,choosedBeforeLvlList,choosedAfterLvlList,choosedBeforeBreakLvlList,choosedAfterBreakLvlList,choosedBeforeSkill1LvlList,choosedAfterSkill1LvlList,choosedBeforeSkill2LvlList,choosedAfterSkill2LvlList,choosedBeforeSkill3LvlList,choosedAfterSkill3LvlList,choosedIsCal,choosedBeforeBreakUPLvlList,choosedAfterBreakUPLvlList,"WRITE");
                calculatorProcess.artifact_setup(artifactChoosedNameList,artifactChoosedBeforeLvlList,artifactChoosedAfterLvlList,artifactChoosedIsCal,artifactChoosedFollowList,artifactChoosedRare,artifactChoosedType,artifactChoosedIdList,"READ");
                calculatorProcess.weapon_setup(weaponChoosedNameList,weaponChoosedBeforeLvlList,weaponChoosedAfterLvlList,weaponChoosedBeforeBreakLvlList,weaponChoosedAfterBreakLvlList,weaponChoosedIsCal,weaponChoosedBeforeBreakUPLvlList,weaponChoosedAfterBreakUPLvlList,weaponChoosedFollowList,weaponChoosedRare,weaponChoosedIdList,"READ");
                calculatorProcess.saveToDB();


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

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


    public void artifactQuestion (String CharName_BASE, String XPR, int k, int rare){

        /** Calculator vars -> Might change to int[] which sort by char update time*/
        before_lvl = 1;
        after_lvl = 20;
        switch (rare){
            case 1 : after_lvl = 4; break;
            case 2 : after_lvl = 4; break;
            case 3 : after_lvl = 12; break;
            case 4 : after_lvl = 16; break;
            case 5 : after_lvl = 20; break;
        }

        sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        item_rss = new ItemRss();

        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.menu_char_add, null);

        // Function method
        Button cancel = view.findViewById(R.id.menu_cancel);
        Button ok = view.findViewById(R.id.menu_ok);
        Button delete = view.findViewById(R.id.menu_delete);
        TextView menu_title = view.findViewById(R.id.menu_title);
        Button menu_char_lvl_before = view.findViewById(R.id.menu_char_lvl_before);
        Button menu_char_lvl_after = view.findViewById(R.id.menu_char_lvl_after);

        TextView menu_skill1_title = view.findViewById(R.id.menu_skill1_title);
        TextView menu_skill2_title = view.findViewById(R.id.menu_skill2_title);
        TextView menu_skill3_title = view.findViewById(R.id.menu_skill3_title);

        SeekBar menu_skill1_before_pb = view.findViewById(R.id.menu_skill1_before_pb);
        TextView menu_skill1_before_tv = view.findViewById(R.id.menu_skill1_before_tv);
        SeekBar menu_skill1_after_pb = view.findViewById(R.id.menu_skill1_after_pb);
        TextView menu_skill1_after_tv = view.findViewById(R.id.menu_skill1_after_tv);
        SeekBar menu_skill2_before_pb = view.findViewById(R.id.menu_skill2_before_pb);
        TextView menu_skill2_before_tv = view.findViewById(R.id.menu_skill2_before_tv);
        SeekBar menu_skill2_after_pb = view.findViewById(R.id.menu_skill2_after_pb);
        TextView menu_skill2_after_tv = view.findViewById(R.id.menu_skill2_after_tv);
        SeekBar menu_skill3_before_pb = view.findViewById(R.id.menu_skill3_before_pb);
        TextView menu_skill3_before_tv = view.findViewById(R.id.menu_skill3_before_tv);
        SeekBar menu_skill3_after_pb = view.findViewById(R.id.menu_skill3_after_pb);
        TextView menu_skill3_after_tv = view.findViewById(R.id.menu_skill3_after_tv);

        Switch menu_cal = view.findViewById(R.id.menu_cal);
        Switch menu_break_lvl_before_switch = view.findViewById(R.id.menu_break_lvl_before_switch);
        Switch menu_break_lvl_after_switch = view.findViewById(R.id.menu_break_lvl_after_switch);
        View divider = view.findViewById(R.id.divider);

        TextView menu_artifact_title_tv = view.findViewById(R.id.menu_artifact_title_tv);
        LinearLayout menu_artifact_title_ll = view.findViewById(R.id.menu_artifact_title_ll);
        ImageView ico_flower = view.findViewById(R.id.ico_flower);
        ImageView ico_plume = view.findViewById(R.id.ico_plume);
        ImageView ico_sand = view.findViewById(R.id.ico_sand);
        ImageView ico_goblet = view.findViewById(R.id.ico_goblet);
        ImageView ico_circlet = view.findViewById(R.id.ico_circlet);

        divider.setVisibility(View.GONE);
        menu_skill1_title.setVisibility(View.GONE);
        menu_skill2_title.setVisibility(View.GONE);
        menu_skill3_title.setVisibility(View.GONE);
        menu_skill1_before_pb.setVisibility(View.GONE);
        menu_skill1_before_tv.setVisibility(View.GONE);
        menu_skill1_after_pb.setVisibility(View.GONE);
        menu_skill1_after_tv.setVisibility(View.GONE);
        menu_skill2_before_pb.setVisibility(View.GONE);
        menu_skill2_before_tv.setVisibility(View.GONE);
        menu_skill2_after_pb.setVisibility(View.GONE);
        menu_skill2_after_tv.setVisibility(View.GONE);
        menu_skill3_before_pb.setVisibility(View.GONE);
        menu_skill3_before_tv.setVisibility(View.GONE);
        menu_skill3_after_pb.setVisibility(View.GONE);
        menu_skill3_after_tv.setVisibility(View.GONE);
        menu_skill3_after_tv.setVisibility(View.GONE);

        menu_artifact_title_tv.setVisibility(View.VISIBLE);
        menu_artifact_title_ll.setVisibility(View.VISIBLE);

        ico_flower.setColorFilter(Color.parseColor("#00000000")); // DEFAULT
        ico_plume.setColorFilter(Color.parseColor("#66313131"));
        ico_sand.setColorFilter(Color.parseColor("#66313131"));
        ico_goblet.setColorFilter(Color.parseColor("#66313131"));
        ico_circlet.setColorFilter(Color.parseColor("#66313131"));

        View v = view;
/*

        for(int x = 0; x< choosedNameList.size() ; x++) {
            for (int y = 0; x < artifactChoosedNameList.size(); y++) {
                if (artifactChoosedFollowList.get(y).equals(choosedNameList.get(x))){
                    switch (artifactChoosedType.get(y)){
                        case "Flower" : charHasFlower.set(x,true);break;
                        case "Plume" : charHasPlume.set(x,true);break;
                        case "Sand" : charHasSand.set(x,true);break;
                        case "Goblet" : charHasGoblet.set(x,true);break;
                        case "Circlet" : charHasCirclet.set(x,true);break;
                    }
                }
            }
        }
 */

        ico_flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ico_flower.setColorFilter(Color.parseColor("#00000000"));
                ico_plume.setColorFilter(Color.parseColor("#66313131"));
                ico_sand.setColorFilter(Color.parseColor("#66313131"));
                ico_goblet.setColorFilter(Color.parseColor("#66313131"));
                ico_circlet.setColorFilter(Color.parseColor("#66313131"));
                tmp_artifact_type = "Flower";

                chooseFollow(v);
            }
        });

        ico_plume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ico_plume.setColorFilter(Color.parseColor("#00000000"));
                ico_flower.setColorFilter(Color.parseColor("#66313131"));
                ico_sand.setColorFilter(Color.parseColor("#66313131"));
                ico_goblet.setColorFilter(Color.parseColor("#66313131"));
                ico_circlet.setColorFilter(Color.parseColor("#66313131"));
                tmp_artifact_type = "Plume";

                chooseFollow(v);
            }
        });

        ico_sand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ico_sand.setColorFilter(Color.parseColor("#00000000"));
                ico_flower.setColorFilter(Color.parseColor("#66313131"));
                ico_plume.setColorFilter(Color.parseColor("#66313131"));
                ico_goblet.setColorFilter(Color.parseColor("#66313131"));
                ico_circlet.setColorFilter(Color.parseColor("#66313131"));
                tmp_artifact_type = "Sand";

                chooseFollow(v);
            }
        });

        ico_goblet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ico_goblet.setColorFilter(Color.parseColor("#00000000"));
                ico_flower.setColorFilter(Color.parseColor("#66313131"));
                ico_sand.setColorFilter(Color.parseColor("#66313131"));
                ico_plume.setColorFilter(Color.parseColor("#66313131"));
                ico_circlet.setColorFilter(Color.parseColor("#66313131"));
                tmp_artifact_type = "Goblet";

                chooseFollow(v);
            }
        });

        ico_circlet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ico_circlet.setColorFilter(Color.parseColor("#00000000"));
                ico_flower.setColorFilter(Color.parseColor("#66313131"));
                ico_sand.setColorFilter(Color.parseColor("#66313131"));
                ico_goblet.setColorFilter(Color.parseColor("#66313131"));
                ico_plume.setColorFilter(Color.parseColor("#66313131"));
                tmp_artifact_type = "Circlet";

                chooseFollow(v);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("user_info",MODE_PRIVATE);
        String color_hex = sharedPreferences.getString("theme_color_hex","#FF5A5A"); // Must include #

        ColorStateList myList = new ColorStateList(
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

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        if(color_hex.toUpperCase().equals("#FFFFFFFF")){
            window.setStatusBarColor(Color.parseColor("#000000"));}
        else {
            window.setStatusBarColor(Color.parseColor(color_hex));
            Log.w("WRF",color_hex);
        }


        menu_char_lvl_before.setText(getString(R.string.curr_lvl)+String.valueOf(before_lvl));
        menu_char_lvl_after.setText(getString(R.string.aim_lvl)+String.valueOf(after_lvl));
        menu_title.setText(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(CharName_BASE),context)[0]);


        ArrayList<String> cList = new ArrayList<String>();

        ArrayList<String> charFlowerList = new ArrayList<String>();
        ArrayList<String> charPlumeList = new ArrayList<String>();
        ArrayList<String> charSandList = new ArrayList<String>();
        ArrayList<String> charGobletList = new ArrayList<String>();
        ArrayList<String> charCircletList = new ArrayList<String>();

        for(int x = 0 ; x< choosedNameList.size() ; x++){
            if(charHasFlower.get(x) == false){
                charFlowerList.add(item_rss.getCharByName(choosedNameList.get(x),context)[1]);
            }
            if(charHasPlume.get(x) == false){
                charPlumeList.add(item_rss.getCharByName(choosedNameList.get(x),context)[1]);
            }
            if(charHasSand.get(x) == false){
                charSandList.add(item_rss.getCharByName(choosedNameList.get(x),context)[1]);
            }
            if(charHasGoblet.get(x) == false){
                charGobletList.add(item_rss.getCharByName(choosedNameList.get(x),context)[1]);
            }
            if(charHasCirclet.get(x) == false){
                charCircletList.add(item_rss.getCharByName(choosedNameList.get(x),context)[1]);
            }
        }
        switch (tmp_artifact_type){
            case "Flower" : cList = charFlowerList;break;
            case "Plume" : cList = charPlumeList;break;
            case "Sand" : cList = charSandList;break;
            case "Goblet" : cList = charGobletList;break;
            case "Circlet" : cList = charCircletList;break;
        }

        /*
        for (int x = 0 ; x < choosedNameList.size() ; x++){
            Log.wtf("TMP1","TMP");
            if(choosedIsCal.get(x) == true ){
                Log.wtf("TMP2","TMP");
                if(artifactChoosedFollowList.size() == 0 && artifactChoosedType.size() == 0){
                    cList.add(getString(item_rss.getCharByName(choosedNameList.get(x),context)[1]));
                }else {
                    for (int y = 0 ; y < artifactChoosedFollowList.size() ; y ++){
                        Log.wtf("TMP3","TMP");
                        if (artifactChoosedType.get(y).equals(tmp_artifact_type) && artifactChoosedFollowList.get(y).equals(choosedNameList.get(x))){
                            Log.wtf("TMP4","TMP");
                        }else if(!cList.contains(choosedNameList.get(x)) && !artifactChoosedType.get(y).equals(tmp_artifact_type) && !artifactChoosedFollowList.get(y).equals(choosedNameList.get(x))){
                            cList.add(getString(item_rss.getCharByName(choosedNameList.get(x),context)[1]));
                        }
                    }
                }
            }
        }
         */


        if(XPR.equals("EDIT")){
            /*
            ArrayAdapter char_aa = new ArrayAdapter(context,R.layout.spinner_item,cList);
            char_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);

            Spinner char_sp = view.findViewById(R.id.menu_char_follow);
            char_sp.setVisibility(View.VISIBLE);
            char_sp.setAdapter(char_aa);
            char_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    follow_char_tmp = choosedNameList.get(position);
                    position_tmp = position;

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    follow_char_tmp = "NaN";
                    position_tmp = -1;
                }
            });
             */
            delete.setVisibility(View.VISIBLE);
            before_lvl = artifactChoosedBeforeLvlList.get(k);
            after_lvl = artifactChoosedAfterLvlList.get(k);
            menu_char_lvl_before.setText(getString(R.string.curr_lvl)+String.valueOf(artifactChoosedBeforeLvlList.get(k)));
            menu_char_lvl_after.setText(getString(R.string.aim_lvl)+String.valueOf(artifactChoosedAfterLvlList.get(k)));
            menu_cal.setChecked(artifactChoosedIsCal.get(k));
            ico_flower.setColorFilter(Color.parseColor("#66313131"));

            switch (artifactChoosedType.get(k)){
                case "Flower" : ico_flower.setColorFilter(Color.parseColor("#00000000")); tmp_artifact_type = "Flower"; break;
                case "Plume" : ico_plume.setColorFilter(Color.parseColor("#00000000")); tmp_artifact_type = "Plume"; break;
                case "Goblet" : ico_goblet.setColorFilter(Color.parseColor("#00000000")); tmp_artifact_type = "Goblet"; break;
                case "Circlet" : ico_circlet.setColorFilter(Color.parseColor("#00000000")); tmp_artifact_type = "Circlet"; break;
                case "Sand" : ico_sand.setColorFilter(Color.parseColor("#00000000")); tmp_artifact_type = "Sand"; break;
            }

            /*
            for (int x = 0 ; x < choosedNameList.size() ; x++){

                cList.add(getString(item_rss.getCharByName(choosedNameList.get(x),context)[1]));
                if(choosedNameList.get(x).equals(artifactChoosedFollowList.get(k))){
                    char_aa = new ArrayAdapter(context,R.layout.spinner_item,cList);
                    char_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    char_sp.setAdapter(char_aa);
                    char_sp.setSelection(x);
                }else {
                    char_sp.setSelection(0);
                }
            }
             */
        }

        menu_char_lvl_before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                npd.setLastValue(before_lvl);
                switch (rare){
                    case 1 : npd.setMaxValue(4); break;
                    case 2 : npd.setMaxValue(8); break;
                    case 3 : npd.setMaxValue(12); break;
                    case 4 : npd.setMaxValue(16); break;
                    case 5 : npd.setMaxValue(20); break;
                }
                npd.setMinValue(0);
                npd.showDialog("LVL_BEFORE");
            }
        });

        menu_char_lvl_after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                npd.setLastValue(after_lvl);
                switch (rare){
                    case 1 : npd.setMaxValue(4); break;
                    case 2 : npd.setMaxValue(8); break;
                    case 3 : npd.setMaxValue(12); break;
                    case 4 : npd.setMaxValue(16); break;
                    case 5 : npd.setMaxValue(20); break;
                }
                npd.setMinValue(before_lvl);
                npd.showDialog("LVL_AFTER");
            }
        });
        /**這邊取得自己所設置之模組回調*/

        npd.onDialogRespond = new NumberPickerDialog.OnDialogRespond() {
            @Override
            public void onRespond(int value , String XPR) {
                if(XPR.equals("LVL_BEFORE")){
                    before_lvl = value;
                }else if(XPR.equals("LVL_AFTER")) {
                    after_lvl = value;
                }
            }
        };
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                String name_del = CharName_BASE;
                String follow_del = artifactChoosedFollowList.get(k);
                String type_del = artifactChoosedType.get(k);

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

                System.out.println("artifactChoosedIdList : "+artifactChoosedIdList);
                System.out.println("artifactChoosedNameList : "+artifactChoosedNameList);
                System.out.println("INT K : "+k);

                //db.execSQL("DELETE FROM "+dataSheetName+"_artifact"+" WHERE artifactName = \""+name_del+"\" AND artifactType = \""+type_del+"\" AND artifactFollow = \""+follow_del+"\"");
                db.execSQL("DELETE FROM "+dataSheetName+"_artifact"+" WHERE artifactId = "+artifactChoosedIdList.get(k)+";");



                int tmp_pos = 0;
                for(int x = 0; x< choosedNameList.size() ; x++) {
                    if (artifactChoosedFollowList.get(k).equals(choosedNameList.get(x)) && artifactChoosedType.get(k).equals(tmp_artifact_type)){
                        tmp_pos = x;
                    }
                }

                switch (tmp_artifact_type){
                    case "Flower" : charHasFlower.set(tmp_pos,false);break;
                    case "Plume" : charHasPlume.set(tmp_pos,false);break;
                    case "Sand" : charHasSand.set(tmp_pos,false);break;
                    case "Goblet" : charHasGoblet.set(tmp_pos,false);break;
                    case "Circlet" : charHasCirclet.set(tmp_pos,false);break;
                }

                artifactChoosedNameList.remove(k);
                artifactChoosedBeforeLvlList.remove(k);
                artifactChoosedAfterLvlList.remove(k);
                artifactChoosedRare.remove(k);
                artifactChoosedIsCal.remove(k);
                artifactChoosedFollowList.remove(k);
                artifactChoosedType.remove(k);

                LinearLayout cal_choosed_list = findViewById(R.id.cal_artifact_choosed_list);
                cal_choosed_list.removeAllViews();
                for (int x = 0 ; x < artifactChoosedNameList.size(); x++){
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_img, cal_choosed_list, false);
                    ImageView item_img = char_view.findViewById(R.id.item_img);
                    String charName = artifactChoosedNameList.get(x);
                    int finalX = x;
                    item_img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            artifactQuestion(charName,"EDIT", finalX,rare);
                        }
                    });

                    final int radius = 180;
                    final int margin = 4;
                    final Transformation transformation = new RoundedCornersTransformation(radius, margin);
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

                    ImageView item_user_img = char_view.findViewById(R.id.item_user_img);
                    if(!artifactChoosedFollowList.get(x).equals("N/A") && !artifactChoosedFollowList.get(x).isEmpty()){
                        item_user_img.setVisibility(View.VISIBLE);
                        Picasso.get()
                                .load (FileLoader.loadIMG(item_rss.getCharByName(item_rss.getCharNameByTranslatedName(artifactChoosedFollowList.get(x),context),context)[3],context))
                                .transform(transformation)
                                .resize(36,36)
                                .error (R.drawable.paimon_full)
                                .into (item_user_img);
                    }

                    cal_choosed_list.addView(char_view);
                }
                calculatorProcess.setDBName(dataSheetName);
                calculatorProcess.setup(context,activity);
                calculatorProcess.char_setup(choosedNameList,choosedBeforeLvlList,choosedAfterLvlList,choosedBeforeBreakLvlList,choosedAfterBreakLvlList,choosedBeforeSkill1LvlList,choosedAfterSkill1LvlList,choosedBeforeSkill2LvlList,choosedAfterSkill2LvlList,choosedBeforeSkill3LvlList,choosedAfterSkill3LvlList,choosedIsCal,choosedBeforeBreakUPLvlList,choosedAfterBreakUPLvlList,"WRITE");
                calculatorProcess.artifact_setup(artifactChoosedNameList,artifactChoosedBeforeLvlList,artifactChoosedAfterLvlList,artifactChoosedIsCal,artifactChoosedFollowList,artifactChoosedRare,artifactChoosedType,artifactChoosedIdList,"READ");
                calculatorProcess.weapon_setup(weaponChoosedNameList,weaponChoosedBeforeLvlList,weaponChoosedAfterLvlList,weaponChoosedBeforeBreakLvlList,weaponChoosedAfterBreakLvlList,weaponChoosedIsCal,weaponChoosedBeforeBreakUPLvlList,weaponChoosedAfterBreakUPLvlList,weaponChoosedFollowList,weaponChoosedRare,weaponChoosedIdList,"READ");
                calculatorProcess.saveToDB();

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                boolean isCal = menu_cal.isChecked();

                cursorArtifactIds();

                addArtifactIntoListUI(CharName_BASE,before_lvl,after_lvl,isCal,XPR,k,rare,follow_char_tmp,tmp_artifact_type,position_tmp);
                calculatorProcess.setDBName(dataSheetName);
                calculatorProcess.setup(context,activity);
                calculatorProcess.char_setup(choosedNameList,choosedBeforeLvlList,choosedAfterLvlList,choosedBeforeBreakLvlList,choosedAfterBreakLvlList,choosedBeforeSkill1LvlList,choosedAfterSkill1LvlList,choosedBeforeSkill2LvlList,choosedAfterSkill2LvlList,choosedBeforeSkill3LvlList,choosedAfterSkill3LvlList,choosedIsCal,choosedBeforeBreakUPLvlList,choosedAfterBreakUPLvlList,"WRITE");
                calculatorProcess.artifact_setup(artifactChoosedNameList,artifactChoosedBeforeLvlList,artifactChoosedAfterLvlList,artifactChoosedIsCal,artifactChoosedFollowList,artifactChoosedRare,artifactChoosedType,artifactChoosedIdList,"READ");
                calculatorProcess.weapon_setup(weaponChoosedNameList,weaponChoosedBeforeLvlList,weaponChoosedAfterLvlList,weaponChoosedBeforeBreakLvlList,weaponChoosedAfterBreakLvlList,weaponChoosedIsCal,weaponChoosedBeforeBreakUPLvlList,weaponChoosedAfterBreakUPLvlList,weaponChoosedFollowList,weaponChoosedRare,weaponChoosedIdList,"READ");
                calculatorProcess.saveToDB();


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

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

    private void chooseFollow(View view) {
        Log.wtf("TMP0","TMP");

        ArrayList<String> cList = new ArrayList<String>();
        ArrayList<String> cTMPList = new ArrayList<String>();

        ArrayList<String> charFlowerList = new ArrayList<String>();
        ArrayList<String> charPlumeList = new ArrayList<String>();
        ArrayList<String> charSandList = new ArrayList<String>();
        ArrayList<String> charGobletList = new ArrayList<String>();
        ArrayList<String> charCircletList = new ArrayList<String>();

        ArrayList<String> charFlowerTMPList = new ArrayList<String>();
        ArrayList<String> charPlumeTMPList = new ArrayList<String>();
        ArrayList<String> charSandTMPList = new ArrayList<String>();
        ArrayList<String> charGobletTMPList = new ArrayList<String>();
        ArrayList<String> charCircletTMPList = new ArrayList<String>();

        for(int x = 0 ; x< choosedNameList.size() ; x++){
            if(charHasFlower.get(x) == false){
                charFlowerList.add(item_rss.getCharByName(choosedNameList.get(x),context)[1]);
                charFlowerTMPList.add(choosedNameList.get(x));
            }
            if(charHasPlume.get(x) == false){
                charPlumeList.add(item_rss.getCharByName(choosedNameList.get(x),context)[1]);
                charPlumeTMPList.add(choosedNameList.get(x));
            }
            if(charHasSand.get(x) == false){
                charSandList.add(item_rss.getCharByName(choosedNameList.get(x),context)[1]);
                charSandTMPList.add(choosedNameList.get(x));
            }
            if(charHasGoblet.get(x) == false){
                charGobletList.add(item_rss.getCharByName(choosedNameList.get(x),context)[1]);
                charGobletTMPList.add(choosedNameList.get(x));
            }
            if(charHasCirclet.get(x) == false){
                charCircletList.add(item_rss.getCharByName(choosedNameList.get(x),context)[1]);
                charCircletTMPList.add(choosedNameList.get(x));
            }
        }
        switch (tmp_artifact_type){
            case "Flower" : cList = charFlowerList;cTMPList = charFlowerTMPList;break;
            case "Plume" : cList = charPlumeList;cTMPList = charPlumeTMPList;break;
            case "Sand" : cList = charSandList; cTMPList = charSandTMPList;break;
            case "Goblet" : cList = charGobletList;cTMPList = charGobletTMPList;break;
            case "Circlet" : cList = charCircletList;cTMPList = charCircletTMPList;break;
        }

        /*

        for (int x = 0 ; x < choosedNameList.size() ; x++){
            Log.wtf("TMP1","TMP");
            if(choosedIsCal.get(x) == true ){
                Log.wtf("TMP2","TMP");
                if(artifactChoosedFollowList.size() == 0 && artifactChoosedType.size() == 0){
                    cList.add(getString(item_rss.getCharByName(choosedNameList.get(x),context)[1]));
                }else {
                    for (int y = 0 ; y < artifactChoosedFollowList.size() ; y ++){
                        Log.wtf("TMP3","TMP");
                        if (artifactChoosedType.get(y).equals(tmp_artifact_type) && artifactChoosedFollowList.get(y).equals(choosedNameList.get(x))){
                            Log.wtf("TMP4","TMP");
                        }else if(!cList.contains(choosedNameList.get(x))){
                            cList.add(getString(item_rss.getCharByName(choosedNameList.get(x),context)[1]));
                        }
                    }
                }

            }
        }

         */

        ArrayAdapter char_aa = new ArrayAdapter(context,R.layout.spinner_item,cList);
        char_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);

        Spinner char_sp = view.findViewById(R.id.menu_char_follow);
        char_sp.setVisibility(View.VISIBLE);
        char_sp.setAdapter(char_aa);
        ArrayList<String> finalCTMPList = cTMPList;
        char_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                follow_char_tmp = finalCTMPList.get(position);
                position_tmp = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                follow_char_tmp = "NaN";
                position_tmp = -1;
            }
        });


    }

    private void addCharIntoListUI(String charName_base, int before_lvl, int after_lvl, int before_break, int after_break, int skill1_before, int skill1_after, int skill2_before, int skill2_after, int skill3_before, int skill3_after, boolean isCal, boolean beforeUP, boolean afterUP, String XPR, int k) {
        LinearLayout cal_choosed_list = findViewById(R.id.cal_choosed_list);
        cal_choosed_list.removeAllViews();
        // THERE WILL USE ON ADD ITEMS INTO EVERY ARRAYLIST *-> LATER ADD MORE VAR*

        if(XPR.equals("ADD")){
            choosedNameList.add(charName_base);
            choosedBeforeLvlList.add(before_lvl);
            choosedAfterLvlList.add(after_lvl);
            choosedBeforeBreakLvlList.add(before_break);
            Log.wtf("WTF",String.valueOf(after_break));
            choosedAfterBreakLvlList.add(after_break);
            choosedBeforeSkill1LvlList.add(skill1_before);
            choosedAfterSkill1LvlList.add(skill1_after);
            choosedBeforeSkill2LvlList.add(skill2_before);
            choosedAfterSkill2LvlList.add(skill2_after);
            choosedBeforeSkill3LvlList.add(skill3_before);
            choosedAfterSkill3LvlList.add(skill3_after);
            choosedIsCal.add(isCal);
            choosedBeforeBreakUPLvlList.add(beforeUP);
            choosedAfterBreakUPLvlList.add(afterUP);
            choosedAfterBreakUPLvlList.add(afterUP);
            charHasFlower.add(false);
            charHasPlume.add(false);
            charHasSand.add(false);
            charHasGoblet.add(false);
            charHasCirclet.add(false);
            charHasWeapon.add(false);
        }else if (XPR.equals("EDIT")){
            choosedNameList.set(k,charName_base);
            choosedBeforeLvlList.set(k,before_lvl);
            choosedAfterLvlList.set(k,after_lvl);
            choosedBeforeBreakLvlList.set(k,before_break);
            choosedAfterBreakLvlList.set(k,after_break);
            choosedBeforeSkill1LvlList.set(k,skill1_before);
            choosedAfterSkill1LvlList.set(k,skill1_after);
            choosedBeforeSkill2LvlList.set(k,skill2_before);
            choosedAfterSkill2LvlList.set(k,skill2_after);
            choosedBeforeSkill3LvlList.set(k,skill3_before);
            choosedAfterSkill3LvlList.set(k,skill3_after);
            choosedIsCal.set(k,isCal);
            choosedBeforeBreakUPLvlList.set(k,beforeUP);
            choosedAfterBreakUPLvlList.set(k,afterUP);
        }

        for (int x = 0 ; x < choosedNameList.size(); x++){
            Log.w("choosedNameList"+String.valueOf(x),choosedNameList.get(x));
            View char_view = LayoutInflater.from(this).inflate(R.layout.item_img, cal_choosed_list, false);
            ImageView item_img = char_view.findViewById(R.id.item_img);
            String charName = choosedNameList.get(x);
            int finalX = x;
            item_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    charQuestion(charName,"EDIT", finalX);
                }
            });

            final int radius = 180;
            final int margin = 4;
            final Transformation transformation = new RoundedCornersTransformation(radius, margin);
            Picasso.get()
                    .load (FileLoader.loadIMG(item_rss.getCharByName(choosedNameList.get(x),context)[3],context))
                    .transform(transformation)
                    .fit()
                    .error (R.drawable.paimon_full)
                    .into (item_img);
            cal_choosed_list.addView(char_view);
        }
    }

    private void addWeaponIntoListUI(String charName_base, int before_lvl, int after_lvl, int before_break, int after_break, boolean isCal, boolean beforeUP, boolean afterUP, String XPR, int k, int rare,String follow_char) {
        LinearLayout cal_choosed_list = findViewById(R.id.cal_weapon_choosed_list);
        cal_choosed_list.removeAllViews();
        // THERE WILL USE ON ADD ITEMS INTO EVERY ARRAYLIST *-> LATER ADD MORE VAR*

        if(XPR.equals("ADD")){
            weaponChoosedNameList.add(charName_base);
            weaponChoosedBeforeLvlList.add(before_lvl);
            weaponChoosedAfterLvlList.add(after_lvl);
            weaponChoosedBeforeBreakLvlList.add(before_break);
            Log.wtf("WTF",String.valueOf(after_break));
            weaponChoosedAfterBreakLvlList.add(after_break);
            weaponChoosedIsCal.add(isCal);
            weaponChoosedBeforeBreakUPLvlList.add(beforeUP);
            weaponChoosedAfterBreakUPLvlList.add(afterUP);
            weaponChoosedRare.add(rare);
            weaponChoosedFollowList.add(follow_char);

            DataBaseHelper dbHelper = new DataBaseHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("weaponName", charName_base);
            values.put("weaponBeforeLvl", before_lvl);
            values.put("weaponAfterLvl", after_lvl);
            values.put("weaponBeforeBreakLvl", before_break);
            values.put("weaponAfterBreakLvl", after_break);
            values.put("weaponBeforeBreakUpLvl", beforeUP ? 1 : 0 );
            values.put("weaponAfterBreakUpLvl", afterUP ? 1 : 0 );
            values.put("weaponRare", rare);
            values.put("weaponFollow", follow_char);
            values.put("weaponIsCal", String.valueOf((isCal) ? 1 : 0 ));

            db.insert(dataSheetName+"_weapon", null, values);


        }else if (XPR.equals("EDIT")){
            weaponChoosedNameList.set(k,charName_base);
            weaponChoosedBeforeLvlList.set(k,before_lvl);
            weaponChoosedAfterLvlList.set(k,after_lvl);
            weaponChoosedBeforeBreakLvlList.set(k,before_break);
            weaponChoosedAfterBreakLvlList.set(k,after_break);
            weaponChoosedIsCal.set(k,isCal);
            weaponChoosedBeforeBreakUPLvlList.set(k,beforeUP);
            weaponChoosedAfterBreakUPLvlList.set(k,afterUP);
            weaponChoosedRare.set(k,rare);
            weaponChoosedFollowList.set(k,follow_char);
        }

        for (int x = 0 ; x < weaponChoosedNameList.size(); x++){
            Log.w("weaponChoosedNameList"+String.valueOf(x),weaponChoosedNameList.get(x));
            View char_view = LayoutInflater.from(this).inflate(R.layout.item_img, cal_choosed_list, false);
            ImageView item_img = char_view.findViewById(R.id.item_img);
            String charName = weaponChoosedNameList.get(x);
            int finalX = x;
            item_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    weaponQuestion(charName,"EDIT", finalX,rare);
                }
            });

            final int radius = 180;
            final int margin = 4;
            final Transformation transformation = new RoundedCornersTransformation(radius, margin);
            Picasso.get()
                    .load (FileLoader.loadIMG(item_rss.getWeaponByName(weaponChoosedNameList.get(x),context)[1],context))
                    .transform(transformation)
                    .fit()
                    .error (R.drawable.paimon_full)
                    .into (item_img);

            ImageView item_user_img = char_view.findViewById(R.id.item_user_img);
            if(!weaponChoosedFollowList.get(x).equals("N/A")){
                item_user_img.setVisibility(View.VISIBLE);

                Picasso.get()
                        .load (FileLoader.loadIMG(item_rss.getCharByName(weaponChoosedFollowList.get(x),context)[3],context))
                        .transform(transformation)
                        .resize(36,36)
                        .error (R.drawable.paimon_full)
                        .into (item_user_img);
                cal_choosed_list.addView(char_view);
            }
        }
    }

    private void addArtifactIntoListUI(String charName_base, int before_lvl, int after_lvl, boolean isCal, String XPR, int k, int rare,String follow_char, String artifact_type,int position) {
        LinearLayout cal_choosed_list = findViewById(R.id.cal_artifact_choosed_list);
        cal_choosed_list.removeAllViews();
        // THERE WILL USE ON ADD ITEMS INTO EVERY ARRAYLIST *-> LATER ADD MORE VAR*

        if(XPR.equals("ADD")){
            artifactChoosedNameList.add(charName_base);
            artifactChoosedBeforeLvlList.add(before_lvl);
            artifactChoosedAfterLvlList.add(after_lvl);
            artifactChoosedIsCal.add(isCal);
            artifactChoosedRare.add(rare);
            artifactChoosedFollowList.add(follow_char);
            artifactChoosedType.add(artifact_type);

            if(position > -1){
                switch (tmp_artifact_type){
                    case "Flower" : charHasFlower.set(position,true);break;
                    case "Plume" : charHasPlume.set(position,true);break;
                    case "Sand" : charHasSand.set(position,true);break;
                    case "Goblet" : charHasGoblet.set(position,true);break;
                    case "Circlet" : charHasCirclet.set(position,true);break;
                    default:break;
                }
            }

            DataBaseHelper dbHelper = new DataBaseHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("artifactName", charName_base);
            values.put("artifactBeforeLvl", before_lvl);
            values.put("artifactAfterLvl", after_lvl);
            values.put("artifactRare", rare);
            values.put("artifactFollow", follow_char);
            values.put("artifactType", artifact_type);
            values.put("artifactIsCal", String.valueOf((isCal) ? 1 : 0 ));

            db.insert(dataSheetName+"_artifact", null, values);

        }else if (XPR.equals("EDIT")){
            artifactChoosedNameList.set(k,charName_base);
            artifactChoosedBeforeLvlList.set(k,before_lvl);
            artifactChoosedAfterLvlList.set(k,after_lvl);
            artifactChoosedIsCal.set(k,isCal);
            artifactChoosedRare.set(k,rare);
            artifactChoosedFollowList.set(k,follow_char);
            artifactChoosedType.set(k,artifact_type);
        }

        for (int x = 0 ; x < artifactChoosedNameList.size(); x++){
            Log.w("artifactChoosedNameList"+String.valueOf(x),artifactChoosedNameList.get(x));
            View char_view = LayoutInflater.from(this).inflate(R.layout.item_img, cal_choosed_list, false);
            ImageView item_img = char_view.findViewById(R.id.item_img);
            ImageView item_user_img = char_view.findViewById(R.id.item_user_img);

            String charName = artifactChoosedNameList.get(x);
            int finalX = x;
            item_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    artifactQuestion(charName,"EDIT", finalX,rare);
                }
            });

            final int radius = 180;
            final int margin = 4;
            final Transformation transformation = new RoundedCornersTransformation(radius, margin);
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

            if(!artifactChoosedFollowList.get(x).isEmpty() && !artifactChoosedFollowList.get(x).equals("N/A")) {
                item_user_img.setVisibility(View.VISIBLE);
                Picasso.get()
                        .load(FileLoader.loadIMG(item_rss.getCharByName(artifactChoosedFollowList.get(x),context)[3],context))
                        .transform(transformation)
                        .resize(36, 36)
                        .error(R.drawable.paimon_full)
                        .into(item_user_img);
            }
            cal_choosed_list.addView(char_view);

        }
    }


    public ArrayList<String> checkNameList () {
        return choosedNameList;
    }

    private void char_list_reload() {
        Log.wtf("DAAM","YEE");
        String name ,element,weapon,nation,sex;
        int rare,isComing;
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
                rare = object.getInt("rare");
                isComing = object.getInt("isComing");

                Characters characters = new Characters();
                characters.setName(name);
                characters.setElement(element);
                characters.setWeapon(weapon);
                characters.setNation(nation);
                characters.setSex(sex);
                characters.setRare(rare);
                characters.setIsComing(isComing);
                charactersList.add(characters);
            }
            mCharAdapter.filterList(charactersList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void weapon_list_reload() {
        Log.wtf("DAAM", "YEE");
        weaponsList.clear();
        String name,weapon,stat_1;
        int rare,isComing;

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
        String name ,img;
        int rare,isComing;

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


    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }


    public class MyViewPagerAdapter extends PagerAdapter
    {
        private List<View> mListViews;

        public MyViewPagerAdapter(List<View> mListViews)
        {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            View view = mListViews.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount()
        {
            return  mListViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1)
        {
            return arg0 == arg1;
        }

    }
    public SeekBar.OnSeekBarChangeListener seekBarChangeListenerBefore (SeekBar seekBarX, SeekBar seekBar_after ,TextView textView){
        SeekBar.OnSeekBarChangeListener sbC = new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                textView.setText(String.valueOf(progress));
                if (progress <1){
                    seekBar.setProgress(1);
                    skill1_lvl = 1 ;
                    textView.setText(String.valueOf(1));
                }
                if (progress > seekBar_after.getProgress()){
                    seekBar_after.setProgress(seekBarX.getProgress());
                }
            }
        };
        return sbC;
    }

    public SeekBar.OnSeekBarChangeListener seekBarChangeListenerAfter (SeekBar seekBarX, SeekBar seekBar_before ,TextView textView){
        SeekBar.OnSeekBarChangeListener sbC = new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                textView.setText(String.valueOf(progress));
                if (progress <1){
                    seekBar.setProgress(1);
                    skill1_lvl = 1 ;
                    textView.setText(String.valueOf(1));
                }
                if (progress < seekBar_before.getProgress()){
                    seekBarX.setProgress(seekBar_before.getProgress());
                }
            }
        };
        return sbC;
    }

    public static void transferData(){

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
}