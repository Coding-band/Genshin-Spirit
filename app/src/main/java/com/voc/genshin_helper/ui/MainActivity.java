package com.voc.genshin_helper.ui;

//https://stackoverflow.com/questions/27128425/add-multiple-custom-views-to-layout-programmatically

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.BuildConfig;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.buff_old.SipTikCal;
import com.voc.genshin_helper.data.Artifacts;
import com.voc.genshin_helper.data.ArtifactsAdapter;
import com.voc.genshin_helper.data.Characters;
import com.voc.genshin_helper.data.CharactersAdapter;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.Today_Material;
import com.voc.genshin_helper.data.Weapons;
import com.voc.genshin_helper.data.WeaponsAdapter;
import com.voc.genshin_helper.kidding.GoSleep;
import com.voc.genshin_helper.tutorial.TutorialUI;
import com.voc.genshin_helper.ui.MMXLVIII.Desk2048;
import com.voc.genshin_helper.ui.SipTik.DeskSipTik;
import com.voc.genshin_helper.util.BackgroundReload;
import com.voc.genshin_helper.util.ChangeLog;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.Dialog2048;
import com.voc.genshin_helper.util.DownloadTask;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.LangUtils;
import com.voc.genshin_helper.util.LocaleHelper;
import com.voc.genshin_helper.util.NumberPickerDialog;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static com.voc.genshin_helper.util.RoundedCornersTransformation.CornerType.ALL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class MainActivity extends AppCompatActivity {
    public static int IMAGE = 1;

    ViewGroup char_ll;
    ViewGroup weapon_ll;
    BottomNavigationView nav_view;
    Today_Material tm;
    ItemRss css;
    //Char Page
    CharactersAdapter mAdapter;
    ArtifactsAdapter mArtifactAdapter;
    RecyclerView mArtifactList;
    RecyclerView mList;
    WeaponsAdapter mWeaponAdapter;
    RecyclerView mWeaponList;

    LocaleHelper localeHelper;
    NumberPickerDialog npd;


    int dow = 0; // Day of Week
    int dom = 0; // Day of Month
    int moy = 0; // Month of Yeat
    int exit = 0;
    int app_started = 0;
    int check_spinner = 0;

    Context context;
    Resources resources;
    Configuration configuration ;
    LangUtils langUtils;

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

    public int show_stars = 0;

    public SharedPreferences sharedPreferences;
    public SharedPreferences sharedPreferences_version;
    public SharedPreferences.Editor editor;
    public SharedPreferences.Editor editor2;

    public List<Characters> charactersList = new ArrayList<>();
    public List<Weapons> weaponsList = new ArrayList();
    public List<Artifacts> artifactsList = new ArrayList();

    boolean first = true;

    String lang = "en-US";

    RadioButton theme_light;
    RadioButton theme_night;
    RadioButton theme_default;

    Switch other_exit_confirm ;

    RadioButton style_Voc_rb;
    RadioButton style_2O48_rb;
    RadioButton style_SipTik_rb;

    RadioButton outfit_standard_rb;
    RadioButton outfit_event_rb;

    RadioButton traveler_female_rb;
    RadioButton traveler_male_rb;

    RadioButton grid_2_rb;
    RadioButton grid_3_rb;
    RadioButton grid_4_rb;
    RadioButton grid_5_rb;

    String[] langList ;
    String[] serverList ;
    String[] gridList ;

    WebView webView;

    private ViewPager viewPager;
    private ArrayList<View> viewPager_List;
    GoSleep gs;

    Activity activity;

    View viewPager0, viewPager1, viewPager2, viewPager3, viewPager4;
    ChangeLog changeLog = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPreferences = getSharedPreferences("user_info",MODE_PRIVATE);
        sharedPreferences_version = getSharedPreferences("changelog_version",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (sharedPreferences.getBoolean("theme_light",true) == true){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }else if (sharedPreferences.getBoolean("theme_night",false) == true){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else if (sharedPreferences.getBoolean("theme_default",false) == true){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        tm = new Today_Material();
        css = new ItemRss();
        localeHelper = new LocaleHelper();
        langUtils = new LangUtils();

        context = this;
        activity = this;
        serverList = new String[]{getString(R.string.america_ser),getString(R.string.europe_ser),getString(R.string.asia_ser),getString(R.string.hk_tw_mo_ser),getString(R.string.sky_land_ser),getString(R.string.world_tree)};

        viewPager = (ViewPager) findViewById(R.id.vp);
        nav_view = findViewById(R.id.nav_view);
        npd = new NumberPickerDialog(this);

        //忘憂喵
        //gs = new GoSleep();
        //gs.sleep(context);

        changeLog = new ChangeLog();

        // Check Is First Time Open
        if(sharedPreferences_version.getBoolean(BuildConfig.VERSION_NAME,false) == false){
            changeLog.show(context,activity);
            editor2 = sharedPreferences_version.edit();
            editor2.putBoolean(BuildConfig.VERSION_NAME,true);
            editor2.apply();
        }

        final LayoutInflater mInflater = getLayoutInflater().from(this);
        viewPager0 = mInflater.inflate(R.layout.fragment_char, null,false);
        viewPager1 = mInflater.inflate(R.layout.fragment_art, null,false);
        viewPager2 = mInflater.inflate(R.layout.fragment_home, null,false);
        viewPager3 = mInflater.inflate(R.layout.fragment_weapon, null,false);
        viewPager4 = mInflater.inflate(R.layout.fragment_setting, null,false);

        viewPager_List = new ArrayList<View>();
        viewPager_List.add(viewPager0);
        viewPager_List.add(viewPager1);
        viewPager_List.add(viewPager2);
        viewPager_List.add(viewPager3);
        viewPager_List.add(viewPager4);

        check_spinner = 0;


        lang_setup();
        home();
        getDOW();
        bday_reload();
        char_reload();
        weapon_reload();
        cbg();
        setColorBk();
        check_updates();
        setup_home();
        setup_char();
        setup_weapon();
        setup_art();
        setup_setting();
        BackgroundReload.BackgroundReload(context,activity);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){

            @Override
            public void run() {
                getDOW();
            }}, 60000);

        viewPager.setAdapter(new MyViewPagerAdapter(viewPager_List));

        app_started = sharedPreferences.getInt("app_started",1);
        boolean voted = sharedPreferences.getBoolean("voted",false);
        if(voted == false && app_started >= 5){
            showVoteDialog();
        }
        if(sharedPreferences.getBoolean("PASS_JUST_CHANGED_THEME",false) == false){
            editor.putInt("app_started",app_started+1);
            editor.apply();
        }

        String versionName = BuildConfig.VERSION_NAME;

        TextView info_app_version = viewPager4.findViewById(R.id.info_app_version);
        info_app_version.setText(versionName);

        theme_light = viewPager4.findViewById(R.id.theme_light);
        theme_night = viewPager4.findViewById(R.id.theme_dark);
        theme_default = viewPager4.findViewById(R.id.theme_default);



        if (sharedPreferences.getBoolean("theme_light",true) == true){
            theme_light.setChecked(true);
            theme_night.setChecked(false);
            theme_default.setChecked(false);
        }
        if (sharedPreferences.getBoolean("theme_night",false) == true){
            theme_night.setChecked(true);
            theme_light.setChecked(false);
            theme_default.setChecked(false);
        }
        if (sharedPreferences.getBoolean("theme_default",false) == true){
            theme_default.setChecked(true);
            theme_night.setChecked(false);
            theme_light.setChecked(false);
        }

        if (sharedPreferences.getBoolean("PASS_JUST_CHANGED_THEME",false) == true) {
            editor.putBoolean("PASS_JUST_CHANGED_THEME", false);
            editor.apply();
            nav_view.setSelectedItemId(R.id.navigation_settings);
            viewPager.setCurrentItem(4);

        }else{
            nav_view.setSelectedItemId(R.id.navigation_home);
            viewPager.setCurrentItem(2);
        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        nav_view.setSelectedItemId(R.id.navigation_char);
                        break;

                    case 1:
                        nav_view.setSelectedItemId(R.id.navigation_artifacts);
                        break;

                    case 2:
                        nav_view.setSelectedItemId(R.id.navigation_home);
                        break;

                    case 3:
                        nav_view.setSelectedItemId(R.id.navigation_weapons);
                        break;

                    case 4:
                        nav_view.setSelectedItemId(R.id.navigation_settings);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_char) {
                    viewPager.setCurrentItem(0);
                    return true;
                }
                else if (item.getItemId() == R.id.navigation_artifacts){
                    viewPager.setCurrentItem(1);
                    return true;
                }
                else if (item.getItemId() == R.id.navigation_home){
                    viewPager.setCurrentItem(2);
                    return true;
                }
                else if (item.getItemId() == R.id.navigation_weapons){
                    viewPager.setCurrentItem(3);
                    return true;
                }
                else if (item.getItemId() == R.id.navigation_settings){
                    viewPager.setCurrentItem(4);
                    //shortcutAdd("Klee",1);
                    return true;
                }
                return false;
            }
        });

        TutorialUI tutorialUI = new TutorialUI();
        tutorialUI.deskSetPosArray(2,0,3,1,4);
        tutorialUI.setup(context,activity,viewPager2,viewPager0,viewPager3,viewPager1,viewPager4,null,nav_view);
    }
    private void setup_weapon() {
        check_spinner = 0;
        mWeaponList = viewPager3.findViewById(R.id.weapon_list);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        DisplayMetrics displayMetrics_w = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics_w);
        int height_w = displayMetrics_w.heightPixels;
        int width_w = displayMetrics_w.widthPixels;
        mWeaponAdapter = new WeaponsAdapter(context,weaponsList,activity,sharedPreferences);

        if (sharedPreferences.getString("curr_ui_grid", "2").equals("2")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                mLayoutManager = new GridLayoutManager(context,  width_w/480+1);
            }else{
                mLayoutManager = new GridLayoutManager(context,  2);
            }
        }else if (sharedPreferences.getString("curr_ui_grid", "2").equals("3")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                mLayoutManager = new GridLayoutManager(context,  width_w/400+1);
            }else{
                mLayoutManager = new GridLayoutManager(context,  3);
            }
        }else if (sharedPreferences.getString("curr_ui_grid", "2").equals("4")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                int tmp_cnt = (int) width_w/960;
                if (tmp_cnt < 1){tmp_cnt = 1;}
                mLayoutManager = new GridLayoutManager(context,  tmp_cnt);
            }else{
                mLayoutManager = new GridLayoutManager(context,  1);
            }
        }else if (sharedPreferences.getString("curr_ui_grid", "2").equals("5")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                int tmp_cnt = (int) width_w/960;
                if (tmp_cnt < 1){tmp_cnt = 1;}
                mLayoutManager = new GridLayoutManager(context,  tmp_cnt);
            }else{
                mLayoutManager = new GridLayoutManager(context,  1);
            }
        }
        LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsMsg.gravity = Gravity.CENTER;
        mWeaponList.setLayoutManager(mLayoutManager);
        mWeaponList.setLayoutParams(paramsMsg);
        mWeaponList.setAdapter(mWeaponAdapter);
        mWeaponList.removeAllViewsInLayout();
        weapon_list_reload();


        EditText weapon_et = viewPager3.findViewById(R.id.char_et);
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
                    if (context.getString(css.getWeaponByName(item.getName())[0]).contains(str)||context.getString(css.getWeaponByName(item.getName())[0]).toLowerCase().contains(str)||context.getString(css.getWeaponByName(item.getName())[0]).toUpperCase().contains(str)||item.getName().toLowerCase().contains(str)){ // EN -> ZH
                        filteredList.add(item);
                    }
                    x = x +1;
                }
                mWeaponAdapter.filterList(filteredList);
            }
        });



        ImageView weapon_filter = viewPager3.findViewById(R.id.weapon_filter);
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
    }

    private void setup_home() {
        check_spinner = 0;
        getDOW();
        home();
        //char_reload(dow);
        //weapon_reload(dow);
        cbg();
        setColorBk();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){

            @Override
            public void run() {
                getDOW();
                //char_reload(dow);
                //weapon_reload(dow);
            }}, 60000);
    }

    private void setup_char() {
        check_spinner = 0;
        mList = viewPager0.findViewById(R.id.main_list);
        mAdapter = new CharactersAdapter(context,charactersList,activity,sharedPreferences);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        if (sharedPreferences.getString("curr_ui_grid", "2").equals("2")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                mLayoutManager = new GridLayoutManager(context,  width/480+1);
            }else{
                mLayoutManager = new GridLayoutManager(context,  2);
            }
        }else if (sharedPreferences.getString("curr_ui_grid", "2").equals("3")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                mLayoutManager = new GridLayoutManager(context,  width/400+1);
            }else{
                mLayoutManager = new GridLayoutManager(context,  3);
            }
        }else if (sharedPreferences.getString("curr_ui_grid", "2").equals("4")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                int tmp_cnt = (int) width/960;
                if (tmp_cnt < 1){tmp_cnt = 1;}
                mLayoutManager = new GridLayoutManager(context,  tmp_cnt);
            }else{
                mLayoutManager = new GridLayoutManager(context,  1);
            }
        }else if (sharedPreferences.getString("curr_ui_grid", "2").equals("5")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                int tmp_cnt = (int) width/960;
                if (tmp_cnt < 1){tmp_cnt = 1;}
                mLayoutManager = new GridLayoutManager(context,  tmp_cnt);
            }else{
                mLayoutManager = new GridLayoutManager(context,  1);
            }
        }
        LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsMsg.gravity = Gravity.CENTER;
        mList.setLayoutManager(mLayoutManager);
        mList.setLayoutParams(paramsMsg);
        mList.setAdapter(mAdapter);
        mList.removeAllViewsInLayout();
        char_list_reload();


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
                    if (context.getString(css.getCharByName(item.getName(),context)[1]).contains(str)||context.getString(css.getCharByName(item.getName(),context)[1]).toLowerCase().contains(str)||item.getName().toLowerCase().contains(str)){ // EN -> ZH
                        filteredList.add(item);
                    }
                    x = x +1;
                }
                mAdapter.filterList(filteredList);
            }
        });

        ImageView char_filter = viewPager0.findViewById(R.id.char_filter);
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

                        mAdapter.filterList(charactersList);

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
    }

    private void setup_art(){
        mArtifactList = viewPager1.findViewById(R.id.artifact_list);
        mArtifactAdapter = new ArtifactsAdapter(context,artifactsList,activity,sharedPreferences);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);

        DisplayMetrics displayMetrics_a = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics_a);
        int height_a = displayMetrics_a.heightPixels;
        int width_a = displayMetrics_a.widthPixels;

        if (sharedPreferences.getString("curr_ui_grid", "2").equals("2")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                mLayoutManager = new GridLayoutManager(context,  width_a/480+1);
            }else{
                mLayoutManager = new GridLayoutManager(context,  2);
            }
        }else if (sharedPreferences.getString("curr_ui_grid", "2").equals("3")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                mLayoutManager = new GridLayoutManager(context,  width_a/400+1);
            }else{
                mLayoutManager = new GridLayoutManager(context,  3);
            }
        }else if (sharedPreferences.getString("curr_ui_grid", "2").equals("4")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                int tmp_cnt = (int) width_a/960;
                if (tmp_cnt < 1){tmp_cnt = 1;}
                mLayoutManager = new GridLayoutManager(context,  tmp_cnt);
            }else{
                mLayoutManager = new GridLayoutManager(context,  1);
            }
        }else if (sharedPreferences.getString("curr_ui_grid", "2").equals("5")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                int tmp_cnt = (int) width_a/960;
                if (tmp_cnt < 1){tmp_cnt = 1;}
                mLayoutManager = new GridLayoutManager(context,  tmp_cnt);
            }else{
                mLayoutManager = new GridLayoutManager(context,  1);
            }
        }
        LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsMsg.gravity = Gravity.CENTER;
        mArtifactList.setLayoutManager(mLayoutManager);
        mArtifactList.setLayoutParams(paramsMsg);
        mArtifactList.setAdapter(mArtifactAdapter);
        mArtifactList.removeAllViewsInLayout();
        artifact_list_reload();


        EditText artifacts_et = viewPager1.findViewById(R.id.char_et);
        artifacts_et.addTextChangedListener(new TextWatcher() {
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
                    if (context.getString(css.getArtifactByName(item.getName())[0]).contains(str)||context.getString(css.getArtifactByName(item.getName())[0]).toLowerCase().contains(str)||context.getString(css.getArtifactByName(item.getName())[0]).toUpperCase().contains(str)||item.getName().toLowerCase().contains(str)){ // EN -> ZH
                        filteredList.add(item);
                    }
                    x = x +1;
                }
                mArtifactAdapter.filterList(filteredList);
            }
        });


        ImageView artifact_filter = viewPager1.findViewById(R.id.artifact_filter);
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
    }

    private void setup_setting() {
        check_spinner = 0;

        // THEME

        theme_light = viewPager4.findViewById(R.id.theme_light);
        theme_night = viewPager4.findViewById(R.id.theme_dark);
        theme_default = viewPager4.findViewById(R.id.theme_default);

        theme_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theme_light.setChecked(true);
                theme_night.setChecked(false);
                theme_default.setChecked(false);

                editor.putBoolean("theme_light",true);
                editor.putBoolean("theme_night",false);
                editor.putBoolean("theme_default",false);
                editor.putBoolean("PASS_JUST_CHANGED_THEME",true);
                editor.apply();

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
        theme_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theme_light.setChecked(false);
                theme_night.setChecked(true);
                theme_default.setChecked(false);

                editor.putBoolean("theme_light",false);
                editor.putBoolean("theme_night",true);
                editor.putBoolean("theme_default",false);
                editor.putBoolean("PASS_JUST_CHANGED_THEME",true);
                editor.apply();

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });
        theme_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theme_light.setChecked(false);
                theme_night.setChecked(false);
                theme_default.setChecked(true);

                editor.putBoolean("theme_light",false);
                editor.putBoolean("theme_night",false);
                editor.putBoolean("theme_default",true);
                editor.putBoolean("PASS_JUST_CHANGED_THEME",true);
                editor.apply();

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            }
        });
        // Color
        ImageView color_bk1,	color_bk2,	color_bk3,	color_bk4,	color_bk5,	color_bk6,	color_bk7,	color_bk8,	color_bk9,	color_bk10,	color_bk11,	color_bk12,	color_bk13,	color_bk14,	color_bk15,	color_bk16,	color_bk17,	color_bk18;
        ImageView color_bk19,color_bk20,color_bk21,color_bk22,color_bk23;

        color_bk1	 = viewPager4.findViewById ( R.id.	color_block1);
        color_bk2	 = viewPager4.findViewById ( R.id.	color_block2);
        color_bk3	 = viewPager4.findViewById ( R.id.	color_block3);
        color_bk4	 = viewPager4.findViewById ( R.id.	color_block4);
        color_bk5	 = viewPager4.findViewById ( R.id.	color_block5);
        color_bk6	 = viewPager4.findViewById ( R.id.	color_block6);
        color_bk7	 = viewPager4.findViewById ( R.id.	color_block7);
        color_bk8	 = viewPager4.findViewById ( R.id.	color_block8);
        color_bk9	 = viewPager4.findViewById ( R.id.	color_block9);
        color_bk10	 = viewPager4.findViewById ( R.id.	color_block10);
        color_bk11	 = viewPager4.findViewById ( R.id.	color_block11);
        color_bk12	 = viewPager4.findViewById ( R.id.	color_block12);
        color_bk13	 = viewPager4.findViewById ( R.id.	color_block13);
        color_bk14	 = viewPager4.findViewById ( R.id.	color_block14);
        color_bk15	 = viewPager4.findViewById ( R.id.	color_block15);
        color_bk16	 = viewPager4.findViewById ( R.id.	color_block16);
        color_bk17	 = viewPager4.findViewById ( R.id.	color_block17);
        color_bk18	 = viewPager4.findViewById ( R.id.	color_block18);
        color_bk19	 = viewPager4.findViewById ( R.id.	color_block19);
        color_bk20	 = viewPager4.findViewById ( R.id.	color_block20);
        color_bk21	 = viewPager4.findViewById ( R.id.	color_block21);
        color_bk22	 = viewPager4.findViewById ( R.id.	color_block22);
        color_bk23	 = viewPager4.findViewById ( R.id.	color_block23);

        color_bk1.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk1,0); }});
        color_bk2.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk2,1); }});
        color_bk3.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk3,2); }});
        color_bk4.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk4,3); }});
        color_bk5.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk5,4); }});
        color_bk6.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk6,5); }});
        color_bk7.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk7,6); }});
        color_bk8.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk8,7); }});
        color_bk9.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk9,8); }});
        color_bk10.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk10,9); }});
        color_bk11.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk11,10); }});
        color_bk12.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk12,11); }});
        color_bk13.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk13,12); }});
        color_bk14.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk14,13); }});
        color_bk15.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk15,14); }});
        color_bk16.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk16,15); }});
        color_bk17.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk17,16); }});
        color_bk18.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk18,17); }});
        color_bk19.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk19,18); }});
        color_bk20.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk20,19); }});
        color_bk21.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk21,20); }});
        color_bk22.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk22,21); }});
        color_bk23.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { giveTickById(color_bk23,22); }});



        // Translate -- U MUST NOT DELETE ANYTHING
        langList = new String[]{getString(R.string.zh_hk),getString(R.string.zh_cn),getString(R.string.en_us),getString(R.string.ru_ru),getString(R.string.ja_jp),getString(R.string.fr_fr),getString(R.string.uk_ua)};
        ArrayAdapter lang_aa = new ArrayAdapter(context,R.layout.spinner_item,langList);
        lang_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_2048);

        Spinner lang_sp = viewPager4.findViewById(R.id.lang_spinner);
        lang_sp.setAdapter(lang_aa);
        lang_sp.setSelection(sharedPreferences.getInt("curr_lang_pos",2));
        lang_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // https://blog.csdn.net/pigdreams/article/details/81277110
                // https://stackoverflow.com/questions/13397933/android-spinner-avoid-onitemselected-calls-during-initialization
                if(check_spinner >0){
                    if(position == 0){
                        editor.putString("curr_lang","zh-HK");
                        editor.putInt("curr_lang_pos",position);
                        editor.putBoolean("PASS_JUST_CHANGED_THEME",true);
                        editor.apply();
                        LangUtils.getAttachBaseContext(context,position);
                        recreate();
                    }else if(position == 1){
                        editor.putString("curr_lang","zh-CN");
                        editor.putInt("curr_lang_pos",position);
                        editor.putBoolean("PASS_JUST_CHANGED_THEME",true);
                        editor.apply();
                        LangUtils.getAttachBaseContext(context,position);
                        recreate();
                    }else if(position == 2){
                        editor.putString("curr_lang","en-US");
                        editor.putInt("curr_lang_pos",position);
                        editor.putBoolean("PASS_JUST_CHANGED_THEME",true);
                        editor.apply();
                        LangUtils.getAttachBaseContext(context,position);
                        recreate();
                    }else if(position == 3){
                        editor.putString("curr_lang","ru-RU");
                        editor.putInt("curr_lang_pos",position);
                        editor.putBoolean("PASS_JUST_CHANGED_THEME",true);
                        editor.apply();
                        LangUtils.getAttachBaseContext(context,position);
                        recreate();
                    }else if(position == 4){
                        editor.putString("curr_lang","ja-JP");
                        editor.putInt("curr_lang_pos",position);
                        editor.putBoolean("PASS_JUST_CHANGED_THEME",true);
                        editor.apply();
                        LangUtils.getAttachBaseContext(context,position);
                        recreate();
                    }else if(position == 5){
                        editor.putString("curr_lang","fr-FR");
                        editor.putInt("curr_lang_pos",position);
                        editor.putBoolean("PASS_JUST_CHANGED_THEME",true);
                        editor.apply();
                        LangUtils.getAttachBaseContext(context,position);
                        recreate();
                    }else if(position == 6){
                        editor.putString("curr_lang","uk-UA");
                        editor.putInt("curr_lang_pos",position);
                        editor.putBoolean("PASS_JUST_CHANGED_THEME",true);
                        editor.apply();
                        LangUtils.getAttachBaseContext(context,position);
                        recreate();
                    }
                }
                check_spinner = check_spinner +1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Background
        Button bg_setting_btn = viewPager4.findViewById(R.id.bg_setting_btn);
        bg_setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);
            }
        });
        Button bg_setting_btn_reset = viewPager4.findViewById(R.id.bg_setting_btn_reset);
        bg_setting_btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gif_png = sharedPreferences.getString("gif/png", "png");
                File file = new File(context.getFilesDir()+"/background."+gif_png);
                file.delete();
                CustomToast.toast(context,activity,context.getString(R.string.img_reset_finish));
                BackgroundReload.BackgroundReload(context,activity);
            }
        });

        // Resource Download
        Button bg_download_base = viewPager4.findViewById(R.id.bg_download_base);
        Button bg_download_update = viewPager4.findViewById(R.id.bg_download_update);

        bg_download_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_updates();
            }
        });

        bg_download_base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this,R.style.AlertDialogCustom);
                dialog.setCancelable(false);
                dialog.setTitle(context.getString(R.string.update_download_update_base));
                dialog.setMessage(context.getString(R.string.update_download_advice)+"\n"+context.getString(R.string.update_download_base_file_size)+" "+prettyByteCount(getRemoteFileSize("https://vt.25u.com/genshin_spirit/base.zip")));
                dialog.setNegativeButton(context.getString(R.string.update_download_later),new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        Log.wtf("NOTHING","NOTHING");
                    }

                });
                dialog.setPositiveButton(context.getString(R.string.update_download_now),new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        DownloadTask downloadTask = new DownloadTask();
                        downloadTask.start("https://vt.25u.com/genshin_spirit/base.zip","base.zip","/base.zip",context,activity);
                    }

                });
                dialog.show();
            }
        });

        // About
        TextView contact_link3 = viewPager4.findViewById(R.id.contact_link3);
        TextView contact_link4 = viewPager4.findViewById(R.id.contact_link4);
        TextView contact_link5 = viewPager4.findViewById(R.id.contact_link5);
        TextView link1 = viewPager4.findViewById(R.id.textView12);
        TextView link2 = viewPager4.findViewById(R.id.textView13);
        TextView link3 = viewPager4.findViewById(R.id.textView10);
        TextView link4 = viewPager4.findViewById(R.id.textView11);
        TextView link5 = viewPager4.findViewById(R.id.textView39);
        TextView link6 = viewPager4.findViewById(R.id.textView43);
        TextView link7 = viewPager4.findViewById(R.id.textView40);
        TextView link8 = viewPager4.findViewById(R.id.textView41);
        TextView link9 = viewPager4.findViewById(R.id.textView44);

        link1.setMovementMethod(LinkMovementMethod.getInstance());
        link2.setMovementMethod(LinkMovementMethod.getInstance());
        link3.setMovementMethod(LinkMovementMethod.getInstance());
        link4.setMovementMethod(LinkMovementMethod.getInstance());
        link5.setMovementMethod(LinkMovementMethod.getInstance());
        link6.setMovementMethod(LinkMovementMethod.getInstance());
        link7.setMovementMethod(LinkMovementMethod.getInstance());
        link8.setMovementMethod(LinkMovementMethod.getInstance());
        link9.setMovementMethod(LinkMovementMethod.getInstance());

        contact_link3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Voc-夜芷冰#2512
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Discord ID", "Voc-夜芷冰#2512");
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context, getString(R.string.copied), Toast.LENGTH_SHORT).show();
            }
        });
        contact_link4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","voc.app.programmer@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_TITLE, "Advice of Genshin Helper");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi, ");
                startActivity(Intent.createChooser(emailIntent, "Advice of Genshin Helper"));
                //voc.app.programmer@gmail.com
            }
        });
        contact_link5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("QQ UID", "822001886");
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context, getString(R.string.copied), Toast.LENGTH_SHORT).show();
            }
        });

        ImageView discord_ico = viewPager4.findViewById(R.id.discord_ico);
        discord_ico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://discord.gg/uXatcbWKv2"); // missing 'https://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        // Other -> Server Location

        ArrayAdapter server_aa = new ArrayAdapter(context,R.layout.spinner_item,serverList);
        server_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_2048);

        Spinner server_spinner = viewPager4.findViewById(R.id.server_spinner);
        server_spinner.setAdapter(server_aa);
        server_spinner.setSelection(sharedPreferences.getInt("serverPos",0));

        server_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    editor.putString("serverLocation","America");
                    editor.apply();
                }else if (position == 1){
                    editor.putString("serverLocation","Europe");
                    editor.apply();
                }else if (position == 2){
                    editor.putString("serverLocation","Asia");
                    editor.apply();
                }else if (position == 3){
                    editor.putString("serverLocation","HK_TW_MO");
                    editor.apply();
                }else if (position == 4){
                    editor.putString("serverLocation","天空島");
                    editor.apply();
                }else if (position == 5){
                    editor.putString("serverLocation","世界樹");
                    editor.apply();
                }

                editor.putInt("serverPos",position);
                editor.apply();
                getDOW();
                char_reload();
                weapon_reload();
                cbg();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Other -> Exit Confirm
        other_exit_confirm = viewPager4.findViewById(R.id.other_exit_confirm);
        SharedPreferences sharedPreferences = getSharedPreferences("user_info",MODE_PRIVATE);
        boolean isExitConfirmEnable = sharedPreferences.getBoolean("isExitConfirmEnable",true);
        other_exit_confirm.setChecked(isExitConfirmEnable);
        other_exit_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(other_exit_confirm.isChecked() == false){
                    editor.putBoolean("isExitConfirmEnable",false);
                    editor.apply();
                }else if(other_exit_confirm.isChecked() == true){
                    editor.putBoolean("isExitConfirmEnable",true);
                    editor.apply();
                }
            }
        });

        //Other -> Style
        style_Voc_rb = viewPager4.findViewById(R.id.ui_Voc_rb);
        style_2O48_rb = viewPager4.findViewById(R.id.ui_2O48_rb);
        style_SipTik_rb = viewPager4.findViewById(R.id.ui_SipTik_rb);
        webView = viewPager4.findViewById(R.id.webView);
        String styleUI = sharedPreferences.getString("styleUI","Voc");
        // StyleCode : "Voc" , "2O48" , "SipTik"

        if (styleUI.equals("Voc")){
            style_Voc_rb.setChecked(true);
            style_2O48_rb.setChecked(false);
            style_SipTik_rb.setChecked(false);
        }else if (styleUI.equals("2O48")){
            style_Voc_rb.setChecked(false);
            style_2O48_rb.setChecked(true);
            style_SipTik_rb.setChecked(false);
        }else if (styleUI.equals("SipTik")) {
            style_Voc_rb.setChecked(false);
            style_2O48_rb.setChecked(false);
            style_SipTik_rb.setChecked(true);
        }

        style_Voc_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("styleUI","Voc");
                editor.apply();
                style_Voc_rb.setChecked(true);
                style_2O48_rb.setChecked(false);
                style_SipTik_rb.setChecked(false);
                startActivity(new Intent(context,MainActivity.class));
                finish();
                //CustomToast.toast(context,activity,context.getString(R.string.pls_restart_app));
            }
        });

        style_2O48_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("styleUI","2O48");
                editor.apply();
                style_Voc_rb.setChecked(false);
                style_2O48_rb.setChecked(true);
                style_SipTik_rb.setChecked(false);
                startActivity(new Intent(context,Desk2048.class));
                finish();
                //CustomToast.toast(context,activity,context.getString(R.string.pls_restart_app));
            }
        });

        style_SipTik_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("styleUI","SipTik");
                editor.apply();
                style_Voc_rb.setChecked(false);
                style_2O48_rb.setChecked(false);
                style_SipTik_rb.setChecked(true);
                startActivity(new Intent(context, DeskSipTik.class));
                finish();
                //CustomToast.toast(context,activity,context.getString(R.string.pls_restart_app));
            }
        });

        //Other -> Change Suits
        outfit_standard_rb = viewPager4.findViewById(R.id.outfit_standard_rb);
        outfit_event_rb = viewPager4.findViewById(R.id.outfit_event_rb);
        boolean isCharChangeEventSuit = sharedPreferences.getBoolean("isCharChangeEventSuit",false);

        if (isCharChangeEventSuit){
            outfit_event_rb.setChecked(true);
            outfit_standard_rb.setChecked(false);
        }else{
            outfit_event_rb.setChecked(false);
            outfit_standard_rb.setChecked(true);
        }

        outfit_standard_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("isCharChangeEventSuit",false);
                editor.apply();
                outfit_event_rb.setChecked(false);
                outfit_standard_rb.setChecked(true);
                setup_char();
            }
        });

        outfit_event_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("isCharChangeEventSuit",true);
                editor.apply();
                outfit_event_rb.setChecked(true);
                outfit_standard_rb.setChecked(false);
                setup_char();
            }
        });

        //Other -> List_Grid
        grid_2_rb = viewPager4.findViewById(R.id.grid_2);
        grid_3_rb = viewPager4.findViewById(R.id.grid_3);
        grid_4_rb = viewPager4.findViewById(R.id.grid_4);
        grid_5_rb = viewPager4.findViewById(R.id.grid_5);
        String currUiGrid = sharedPreferences.getString("curr_ui_grid","2");

        if (currUiGrid.equals("2")){
            grid_2_rb.setChecked(true);
            grid_3_rb.setChecked(false);
            grid_4_rb.setChecked(false);
            grid_5_rb.setChecked(false);
        }else if(currUiGrid.equals("3")){
            grid_2_rb.setChecked(false);
            grid_3_rb.setChecked(true);
            grid_4_rb.setChecked(false);
            grid_5_rb.setChecked(false);
        }else if(currUiGrid.equals("4")){
            grid_2_rb.setChecked(false);
            grid_3_rb.setChecked(false);
            grid_4_rb.setChecked(true);
            grid_5_rb.setChecked(false);
        }else{
            grid_2_rb.setChecked(false);
            grid_3_rb.setChecked(false);
            grid_4_rb.setChecked(false);
            grid_5_rb.setChecked(true);
        }

        grid_2_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("curr_ui_grid","2");
                editor.apply();
                grid_2_rb.setChecked(true);
                grid_3_rb.setChecked(false);
                grid_4_rb.setChecked(false);

                setup_home();
                setup_char();
                setup_weapon();
                setup_art();
            }
        });
        grid_3_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("curr_ui_grid","3");
                editor.apply();
                grid_2_rb.setChecked(false);
                grid_3_rb.setChecked(true);
                grid_4_rb.setChecked(false);

                setup_home();
                setup_char();
                setup_weapon();
                setup_art();
            }
        });
        grid_4_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("curr_ui_grid","4");
                editor.apply();
                grid_2_rb.setChecked(false);
                grid_3_rb.setChecked(false);
                grid_4_rb.setChecked(true);

                setup_home();
                setup_char();
                setup_weapon();
                setup_art();
            }
        });
        grid_5_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("curr_ui_grid","4");
                editor.apply();
                grid_2_rb.setChecked(false);
                grid_3_rb.setChecked(false);
                grid_4_rb.setChecked(false);
                grid_5_rb.setChecked(true);

                setup_home();
                setup_char();
                setup_weapon();
                setup_art();
            }
        });

        //Other -> Traveler Sex
        traveler_female_rb = viewPager4.findViewById(R.id.traveler_female_rb);
        traveler_male_rb = viewPager4.findViewById(R.id.traveler_male_rb);
        String travelerSex = sharedPreferences.getString("traveler_sex","F");

        if (travelerSex.equals("F")){
            traveler_female_rb.setChecked(true);
            traveler_male_rb.setChecked(false);
        }else{
            traveler_female_rb.setChecked(false);
            traveler_male_rb.setChecked(true);
        }

        traveler_male_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("traveler_sex","M");
                editor.apply();
                traveler_female_rb.setChecked(false);
                traveler_male_rb.setChecked(true);
                setup_char();
            }
        });

        traveler_female_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("traveler_sex","F");
                editor.apply();
                traveler_female_rb.setChecked(true);
                traveler_male_rb.setChecked(false);
                setup_char();
            }
        });
    }

    private void showVoteDialog() {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MainActivity.this,R.style.MyDialogTheme);
        normalDialog.setIcon(R.drawable.app_ico);
        normalDialog.setTitle(getString(R.string.vote_title));
        normalDialog.setMessage(getString(R.string.vote_info));
        normalDialog.setPositiveButton(getString(R.string.vote_help),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.putBoolean("voted",true);
                        editor.apply();
                        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.voc.genshin_spirit_gp")));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.voc.genshin_spirit_gp")));
                        }
                    }
                });
        normalDialog.setNegativeButton(getString(R.string.vote_no),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.putBoolean("voted",true);
                        editor.apply();
                    }
                });
        normalDialog.setNeutralButton(getString(R.string.vote_later),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.w("OK","NVM");
                    }
                });
        // 显示
        normalDialog.show();
    }

    private void setColorBk() {
        // Method of Setting Color Blocks

        SharedPreferences sharedPreferences = getSharedPreferences("user_info",MODE_PRIVATE);
        int pos = sharedPreferences.getInt("theme_color_pos",0)+1;
        ImageView color_bk = viewPager4.findViewById(R.id.color_block1); // TEMP

        if(pos == 1){color_bk = viewPager4.findViewById(R.id.color_block1);}
        else if(pos == 2){color_bk =	 viewPager4.findViewById(R.id.color_block2);}
        else if(pos == 3){color_bk =	 viewPager4.findViewById(R.id.color_block3);}
        else if(pos == 4){color_bk =	 viewPager4.findViewById(R.id.color_block4);}
        else if(pos == 5){color_bk =	 viewPager4.findViewById(R.id.color_block5);}
        else if(pos == 6){color_bk =	 viewPager4.findViewById(R.id.color_block6);}
        else if(pos == 7){color_bk =	 viewPager4.findViewById(R.id.color_block7);}
        else if(pos == 8){color_bk =	 viewPager4.findViewById(R.id.color_block8);}
        else if(pos == 9){color_bk =	 viewPager4.findViewById(R.id.color_block9);}
        else if(pos == 10){color_bk =	 viewPager4.findViewById(R.id.color_block10);}
        else if(pos == 11){color_bk =	 viewPager4.findViewById(R.id.color_block11);}
        else if(pos == 12){color_bk =	 viewPager4.findViewById(R.id.color_block12);}
        else if(pos == 13){color_bk =	 viewPager4.findViewById(R.id.color_block13);}
        else if(pos == 14){color_bk =	 viewPager4.findViewById(R.id.color_block14);}
        else if(pos == 15){color_bk =	 viewPager4.findViewById(R.id.color_block15);}
        else if(pos == 16){color_bk =	 viewPager4.findViewById(R.id.color_block16);}
        else if(pos == 17){color_bk =	 viewPager4.findViewById(R.id.color_block17);}
        else if(pos == 18){color_bk =	 viewPager4.findViewById(R.id.color_block18);}
        else if(pos == 19){color_bk =	 viewPager4.findViewById(R.id.color_block19);}
        else if(pos == 20){color_bk =	 viewPager4.findViewById(R.id.color_block20);}
        else if(pos == 21){color_bk =	 viewPager4.findViewById(R.id.color_block21);}
        else if(pos == 22){color_bk =	 viewPager4.findViewById(R.id.color_block22);}
        else if(pos == 23){color_bk =	 viewPager4.findViewById(R.id.color_block23);}

        giveTickById(color_bk,pos-1);
    }

    private void giveTickById(ImageView color_bk,int pos) {
        ImageView color_bk1,	color_bk2,	color_bk3,	color_bk4,	color_bk5,	color_bk6,	color_bk7,	color_bk8,	color_bk9,	color_bk10,	color_bk11,	color_bk12,	color_bk13,	color_bk14,	color_bk15,	color_bk16,	color_bk17,	color_bk18;
        ImageView color_bk19,color_bk20,color_bk21,color_bk22,color_bk23;
        color_bk1	 = viewPager4.findViewById ( R.id.	color_block1);
        color_bk2	 = viewPager4.findViewById ( R.id.	color_block2);
        color_bk3	 = viewPager4.findViewById ( R.id.	color_block3);
        color_bk4	 = viewPager4.findViewById ( R.id.	color_block4);
        color_bk5	 = viewPager4.findViewById ( R.id.	color_block5);
        color_bk6	 = viewPager4.findViewById ( R.id.	color_block6);
        color_bk7	 = viewPager4.findViewById ( R.id.	color_block7);
        color_bk8	 = viewPager4.findViewById ( R.id.	color_block8);
        color_bk9	 = viewPager4.findViewById ( R.id.	color_block9);
        color_bk10	 = viewPager4.findViewById ( R.id.	color_block10);
        color_bk11	 = viewPager4.findViewById ( R.id.	color_block11);
        color_bk12	 = viewPager4.findViewById ( R.id.	color_block12);
        color_bk13	 = viewPager4.findViewById ( R.id.	color_block13);
        color_bk14	 = viewPager4.findViewById ( R.id.	color_block14);
        color_bk15	 = viewPager4.findViewById ( R.id.	color_block15);
        color_bk16	 = viewPager4.findViewById ( R.id.	color_block16);
        color_bk17	 = viewPager4.findViewById ( R.id.	color_block17);
        color_bk18	 = viewPager4.findViewById ( R.id.	color_block18);
        color_bk19	 = viewPager4.findViewById ( R.id.	color_block19);
        color_bk20	 = viewPager4.findViewById ( R.id.	color_block20);
        color_bk21	 = viewPager4.findViewById ( R.id.	color_block21);
        color_bk22	 = viewPager4.findViewById ( R.id.	color_block22);
        color_bk23	 = viewPager4.findViewById ( R.id.	color_block23);

        color_bk1.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk2.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk3.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk4.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk5.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk6.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk7.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk8.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk9.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk10.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk11.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk12.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk13.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk14.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk15.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk16.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk17.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk18.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk19.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk20.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk21.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk22.setForeground(getResources().getDrawable(android.R.color.transparent));
        color_bk23.setForeground(getResources().getDrawable(android.R.color.transparent));

        color_bk.setForeground(getResources().getDrawable(R.drawable.ic_tick_img));

        int[] colorList = new int[]{R.color.color_theme_1,	R.color.color_theme_2,	R.color.color_theme_3,	R.color.color_theme_4,	R.color.color_theme_5,	R.color.color_theme_6,	R.color.color_theme_7,	R.color.color_theme_8,	R.color.color_theme_9,	R.color.color_theme_10,	R.color.color_theme_11,	R.color.color_theme_12,	R.color.color_theme_13,	R.color.color_theme_14,	R.color.color_theme_15,	R.color.color_theme_16,	R.color.color_theme_17,	R.color.color_theme_18};

        switch (pos + 1) {
            case 19:
                editor.putString("start_color", "#AEFEFF");
                editor.putString("end_color", "#35858B");
                editor.putBoolean("theme_color_gradient", true);
                break;
            case 20:
                editor.putString("start_color", "#FEE3EC");
                editor.putString("end_color", "#F2789F");
                editor.putBoolean("theme_color_gradient", true);
                break;
            case 21:
                editor.putString("start_color", "#AEFEFF");
                editor.putString("end_color", "#F2789F");
                editor.putBoolean("theme_color_gradient", true);
                break;
            case 22:
                editor.putString("start_color", "#5994ce");
                editor.putString("end_color", "#b957ce");
                editor.putBoolean("theme_color_gradient", true);
                break;
            case 23:
                editor.putString("start_color", "#FFE3E3");
                editor.putString("end_color", "#93B5C6");
                editor.putBoolean("theme_color_gradient", true);
                break;
            default:
                editor.putString("theme_color_hex", getResources().getString(colorList[pos]));
                editor.putBoolean("theme_color_gradient", false);
                break;
        }

        editor.putInt("theme_color_pos",pos);
        editor.apply();
        cbg();
    }

    public void setLocale(Locale locale) {
        resources = context.getResources();
        configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());

        Intent i = new Intent(context,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    public void home (){
        LinearLayout calculator_ll = viewPager2.findViewById(R.id.calculator_ll);
        LinearLayout daily_login_ll = viewPager2.findViewById(R.id.daily_login_ll);
        LinearLayout map_ll = viewPager2.findViewById(R.id.map_ll);
        LinearLayout alarm_ll = viewPager2.findViewById(R.id.alarm_ll);

        calculator_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CalculatorDBActivity.class);
                startActivity(i);
            }
        });
        daily_login_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String lang = "en-us";
                switch (sharedPreferences.getString("curr_lang","en-US")){
                    case "zh-HK" : lang = "zh-tw";break;
                    default: lang = sharedPreferences.getString("curr_lang","en-US").toLowerCase();break;
                }
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://webstatic-sea.mihoyo.com/ys/event/signin-sea/index.html?act_id=e202102251931481&lang="+lang));
                startActivity(browserIntent);

                /*
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.fragment_web, null);
                dialog.setContentView(view);
                dialog.setCanceledOnTouchOutside(true);

                WebView webview = view.findViewById(R.id.webView);
                ImageView back_btn = view.findViewById(R.id.back_btn);

                WebSettings webSettings = webview.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webview.requestFocus();
                webview.getSettings().setLightTouchEnabled(true);
                webview.getSettings().setJavaScriptEnabled(true);
                webview.getSettings().setGeolocationEnabled(true);
                webview.setSoundEffectsEnabled(true);
                webview.getSettings().setAppCacheEnabled(true);
                webview.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url)
                    {
                        // https://stackoverflow.com/questions/51822019/how-to-hide-a-websites-element-in-webview
                        // hide element by id
                        //webview.loadUrl("javascript:(function() { " +
                        //        "document.getElementsById('your_class_name')[0].style.display='none'; })()");
                        // hide element by class name
                        webview.loadUrl("javascript:(function() { " +
                                "document.getElementsByClassName('mhy-hoyolab-app-header')[0].style.display='none'; })()");
                        webview.loadUrl("javascript:(function() { " +
                                "document.getElementsByClassName('components-m-assets-__navbar_---back---1xSANa')[0].style.display='none'; })()");
                        webview.loadUrl("javascript:(function() { " +
                                "document.getElementsByClassName('components-m-assets-__navbar_---share---13xyQq')[0].style.display='none'; })()");

                    };
                });
                webview.loadUrl("https://webstatic-sea.mihoyo.com/ys/event/signin-sea/index.html?act_id=e202102251931481");

                back_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.width = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth());
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                lp.gravity = Gravity.CENTER;
                dialogWindow.setAttributes(lp);
                dialog.show();
                 */
            }
        });
        map_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lang = "en-us";
                switch (sharedPreferences.getString("curr_lang","en-US")){
                    case "zh-HK" : lang = "zh-tw";break;
                    default: lang = sharedPreferences.getString("curr_lang","en-US").toLowerCase();break;
                }
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://act.hoyolab.com/ys/app/interactive-map/index.html?lang="+lang+"#/map/2"));
                startActivity(browserIntent);
                /*
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.fragment_web, null);
                dialog.setContentView(view);
                dialog.setCanceledOnTouchOutside(true);

                WebView webview = view.findViewById(R.id.webView);
                ImageView back_btn = view.findViewById(R.id.back_btn);

                WebSettings webSettings = webview.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setDomStorageEnabled(true);
                webview.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url)
                    {
                        // https://stackoverflow.com/questions/51822019/how-to-hide-a-websites-element-in-webview
                        // hide element by id
                        //webview.loadUrl("javascript:(function() { " +
                        //        "document.getElementsById('your_class_name')[0].style.display='none'; })()");
                        // hide element by class name
                        webview.loadUrl("javascript:(function() { " +
                                "document.getElementsByClassName('mhy-hoyolab-app-header')[0].style.display='none'; })()");
                        webview.loadUrl("javascript:(function() { " +
                                "document.getElementsByClassName('announcement')[0].value='announcement--hidden'; })()");

                    };
                });
                webview.loadUrl("https://webstatic-sea.mihoyo.com/app/ys-map-sea/index.html?utm_source=hoyolab&lang=en-us#/map/2");

                back_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.width = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth());
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                lp.gravity = Gravity.CENTER;
                dialogWindow.setAttributes(lp);
                dialog.show();

                 */
            }
        });

        alarm_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,AlarmUI.class);
                startActivity(i);
            }
        });

    };

    private void char_list_reload() {
        Log.wtf("DAAM","YEE");
        String name ,element,weapon,nation,sex,mainStat;
        int rare,isComing;
        charactersList.clear();

        String json_base = ItemRss.LoadAssestData(context,"db/char/char_list.json");
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
                mainStat = object.getString("mainStat");
                rare = object.getInt("rare");
                isComing = object.getInt("isComing");

                Characters characters = new Characters();
                characters.setName(name);
                characters.setElement(element);
                characters.setWeapon(weapon);
                characters.setNation(nation);
                characters.setSex(sex);
                characters.setRare(rare);
                characters.setMainStat(mainStat);
                characters.setIsComing(isComing);
                charactersList.add(characters);
            }
            mAdapter.filterList(charactersList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void weapon_list_reload() {
        Log.wtf("DAAM", "YEE");
        weaponsList.clear();
        String name,weapon,stat_1;
        int rare,isComing;

        String json_base = ItemRss.LoadAssestData(context,"db/weapons/weapon_list.json");
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

        String json_base = ItemRss.LoadAssestData(context,"db/artifacts/artifact_list.json");
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


    public void showCharDetail (String name){

    }

    public void getDOW (){
        Calendar c = Calendar.getInstance();
        int position = 0;
        String location = sharedPreferences.getString("serverLocation","HK_TW_MO");
        if(location.equals("America")){
            c.setTimeZone(TimeZone.getTimeZone("GMT-5"));
            position = 0;
        }else if(location.equals("Europe")){
            c.setTimeZone(TimeZone.getTimeZone("GMT+1"));
            position = 1;
        }else if(location.equals("Asia")){
            c.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            position = 2;
        }else if(location.equals("HK_TW_MO")){
            c.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            position = 3;
        }else if(location.equals("天空島")){
            c.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            position = 4;
        }else if(location.equals("世界樹")){
            c.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            position = 5;
        }
        dow = c.get(Calendar.DAY_OF_WEEK);
        dom = c.get(Calendar.DAY_OF_MONTH);
        moy = c.get(Calendar.MONTH);

        TextView home_date_tv = viewPager2.findViewById(R.id.home_date_tv);
        if(dow == 1){home_date_tv.setText("【"+getString(R.string.sunday)+"】");}
        if(dow == 2){home_date_tv.setText("【"+getString(R.string.monday)+"】");}
        if(dow == 3){home_date_tv.setText("【"+getString(R.string.tuesday)+"】");}
        if(dow == 4){home_date_tv.setText("【"+getString(R.string.wednesday)+"】");}
        if(dow == 5){home_date_tv.setText("【"+getString(R.string.thursday)+"】");}
        if(dow == 6){home_date_tv.setText("【"+getString(R.string.friday)+"】");}
        if(dow == 7){home_date_tv.setText("【"+getString(R.string.saturday)+"】");}


        TextView home_f_date_tv = viewPager2.findViewById(R.id.home_f_date_tv);
        home_f_date_tv.setText(serverList[position]);

    }

    public void bday_reload(){
        String char_name = "EMPTY";
        Log.w("MOY",String.valueOf(moy));
        Log.w("DOM",String.valueOf(dom));

        char_name = css.char_birth(moy,dom);

        // Setting
        CardView birth_card = viewPager2.findViewById(R.id.birth_card);
        ImageView birth_char = viewPager2.findViewById(R.id.birth_char);
        TextView birth_char_tv = viewPager2.findViewById(R.id.birth_char_tv);
        TextView birth_char_date = viewPager2.findViewById(R.id.birth_char_date);
        LinearLayout birth_celebrate = viewPager2.findViewById(R.id.birth_celebrate);

        // Big Icon
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int pix = (int) ((displayMetrics.widthPixels-16)/6-8);

        final int radius = 180;
        final int margin = 4;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);

        if(!char_name.equals("EMPTY")){
            birth_card.setVisibility(View.VISIBLE);
            Picasso.get()
                    .load (css.getCharByName(char_name,context)[3])
                    .transform(transformation)
                    .error (R.drawable.paimon_lost)
                    .into (birth_char);
            birth_char_tv.setText(css.getCharByName(char_name,context)[1]);
            birth_char_date.setText(css.getLocaleBirth(String.valueOf(moy+1)+"/"+String.valueOf(dom),context,true));
            birth_celebrate.setVisibility(View.VISIBLE);

        }else{
            birth_celebrate.setVisibility(View.GONE);
        }
        // List

        int dom_TMP = dom;
        int moy_TMP = moy;
        while (char_name.equals("EMPTY")){
            dom_TMP++;
            if (moy_TMP > 13){moy_TMP = 0;}
            if (dom_TMP > 32){dom_TMP = 0;dom_TMP++;}
            char_name = css.char_birth(moy_TMP,dom_TMP);
        }

        int index = Arrays.asList(css.charBirthName).indexOf(char_name);
        int[] imageArray = {R.id.bday_next1,R.id.bday_next2,R.id.bday_next3,R.id.bday_next4,R.id.bday_next5,R.id.bday_next6};
        int[] tvArray = {R.id.bday_next_tv1,R.id.bday_next_tv2,R.id.bday_next_tv3,R.id.bday_next_tv4,R.id.bday_next_tv5,R.id.bday_next_tv6};

        for (int x = 0 ; x < 6; x++ , index++){
            if (index >= css.charBirthName.length){
                index = 0;
            }
            String nextBirthCharName = css.charBirthName[index];
            int nextBirthCharMonth =  css.charBirthMonth[index];
            int nextBirthCharDay = css.charBirthDay[index];

            ImageView img = viewPager2.findViewById(imageArray[x]);
            TextView tv = viewPager2.findViewById(tvArray[x]);
            Picasso.get()
                    .load (css.getCharByName(nextBirthCharName,context)[3])
                    .transform(transformation)
                    .resize((int) (pix*2), (int) (pix*2))
                    .error (R.drawable.paimon_lost)
                    .into (img);

            tv.setText(css.getLocaleBirth(String.valueOf(nextBirthCharMonth+1)+"/"+String.valueOf(nextBirthCharDay),context,true));

            img.getLayoutParams().width = pix;
            img.getLayoutParams().height = pix;
        }
    }

    public void char_reload(){
        GridLayout gridLayout = new GridLayout(context);
        gridLayout = viewPager2.findViewById(R.id.char_ll);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        int[] today_IMG = tm.today_char_IMG(dow);
        int[] today_TV = tm.today_char_TV(dow);
        for (int x = 0, c = 0, r = 0;  x < today_IMG.length ; x++, c++) {
            if(c == 3) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_today_material, null);

            LinearLayout item_bg = view.findViewById(R.id.item_bg);
            ImageView item_img = view.findViewById(R.id.item_img);
            TextView item_name = view.findViewById(R.id.item_name);
            TextView item_dow = view.findViewById(R.id.item_dow);
            //Set tv and img
            item_name.setText(getString(today_TV[x]));
            item_dow.setText(getString(tm.today_is(today_IMG[x])));
            item_img.setImageResource(today_IMG[x]);
            gridLayout.setVisibility(View.VISIBLE);
            gridLayout.addView(view);

            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.setMargins(8,8,8,8);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            param.width = (width-8*6)/3;
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }
    }
    public void weapon_reload(){
        GridLayout gridLayout = new GridLayout(context);
        gridLayout = viewPager2.findViewById(R.id.weapon_ll);
        gridLayout.removeAllViewsInLayout();
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        int[] today_IMG = tm.today_weapon_IMG(dow);
        int[] today_TV = tm.today_weapon_TV(dow);
        for (int x = 0, c = 0, r = 0;  x < today_IMG.length ; x++, c++) {
            if(c == 3) { c = 0;r++; }
            View view = View.inflate(context, R.layout.item_today_material, null);

            LinearLayout item_bg = view.findViewById(R.id.item_bg);
            ImageView item_img = view.findViewById(R.id.item_img);
            TextView item_name = view.findViewById(R.id.item_name);
            TextView item_dow = view.findViewById(R.id.item_dow);
            //Set tv and img
            item_name.setText(getString(today_TV[x]));
            item_dow.setText(getString(tm.today_is(today_IMG[x])));
            item_img.setImageResource(today_IMG[x]);
            gridLayout.setVisibility(View.VISIBLE);
            gridLayout.addView(view);

            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.setMargins(8,8,8,8);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            param.width = (width-8*6)/3;
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.CENTER);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            view.setLayoutParams (param);
        }
    }
    public void startInfo (String name, Activity activity){
        Characters_Info cif = new Characters_Info();
        cif.setup(String.valueOf(name),context,activity);
    }
    public void runSipTikCal (Characters characters, Activity activity){
        Intent intent = new Intent(this,SipTikCal.class);
        intent.putExtra("characters",characters);
        startActivity(intent);
    }

    public void cbg() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_info",MODE_PRIVATE);
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

        //Color.parseColor(color_hex)
        //getResources().getColor(R.color.idname);


        // R.layout.fragment_char
        EditText char_et = viewPager0.findViewById(R.id.char_et);
        char_et.setTextColor(Color.parseColor(color_hex));

        // R.layout.fragment_art

        // R.layout.fragment_home
        TextView home_f_date_tv = viewPager2.findViewById(R.id.home_f_date_tv);
        TextView home_title_tv = viewPager2.findViewById(R.id.home_title_tv);
        TextView home_date_tv = viewPager2.findViewById(R.id.home_date_tv);
        TextView char_tv = viewPager2.findViewById(R.id.char_tv);
        TextView weapon_tv = viewPager2.findViewById(R.id.weapon_tv);
        TextView tool_tv = viewPager2.findViewById(R.id.tool_tv);
        TextView calculator_tv = viewPager2.findViewById(R.id.calculator_tv);
        TextView daily_login_tv = viewPager2.findViewById(R.id.daily_login_tv);
        TextView map_tv = viewPager2.findViewById(R.id.map_tv);
        TextView alarm_tv = viewPager2.findViewById(R.id.alarm_tv);
        BottomNavigationView nav_view = findViewById(R.id.nav_view);

        colorGradient(calculator_tv,start_color,end_color,isColorGradient,color_hex);
        colorGradient(daily_login_tv,start_color,end_color,isColorGradient,color_hex);
        colorGradient(map_tv,start_color,end_color,isColorGradient,color_hex);
        colorGradient(alarm_tv,start_color,end_color,isColorGradient,color_hex);
        nav_view.setItemIconTintList(myList);
        nav_view.setItemTextColor(myList);

        // R.layout.fragment_weapon

        // R.layout.fragment_setting
        RadioButton theme_light = viewPager4.findViewById(R.id.theme_light);
        RadioButton theme_dark = viewPager4.findViewById(R.id.theme_dark);
        RadioButton theme_default = viewPager4.findViewById(R.id.theme_default);
        Button bg_changelog_btn = viewPager4.findViewById(R.id.bg_changelog_btn);

        Switch other_exit_confirm = viewPager4.findViewById(R.id.other_exit_confirm);
        theme_light.setButtonTintList(myList);
        theme_dark.setButtonTintList(myList);
        theme_default.setButtonTintList(myList);

        other_exit_confirm.setThumbTintList(myList);
        other_exit_confirm.setTrackTintList(myList);

        bg_changelog_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLog.show(context,activity);
            }
        });
    }

    public void colorGradient(TextView textView,String start_color, String end_color, boolean isColorGradient , String color){
        if(isColorGradient){
            Shader shader = new LinearGradient(0, 0, textView.getLineCount(), textView.getLineHeight(),
                    Color.parseColor(start_color),  Color.parseColor(end_color), Shader.TileMode.CLAMP);
            textView.getPaint().setShader(shader);
        }else{
            textView.setTextColor(Color.parseColor(color));
        }
    }

    @Override
    public void recreate() {
        finish();
        overridePendingTransition(R.anim.fade_in,
                R.anim.fade_in);
        startActivity(getIntent());
        overridePendingTransition(R.anim.fade_out,
                R.anim.fade_out);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            SharedPreferences sharedPreferences = getSharedPreferences("user_info",MODE_PRIVATE);
            boolean isExitConfirmEnable = sharedPreferences.getBoolean("isExitConfirmEnable",true);
            if(exit == 0 && isExitConfirmEnable == true){
                CustomToast.toast(context,this,getString(R.string.press_exit));
                exit = exit +1;
            }else {
                exit = 0;
                finish();
            }
        }
        return true;
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user_info",MODE_PRIVATE);
        sharedPreferences.getInt("curr_lang_pos",2);
        super.attachBaseContext(LangUtils.getAttachBaseContext(newBase, sharedPreferences.getInt("curr_lang_pos",2)));
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

    //https://stackoverflow.com/questions/1103027/how-to-change-an-application-icon-programmatically-in-android
    private void shortcutAdd(String name, int number) {
        // Intent to be send, when shortcut is pressed by user ("launched")
        Intent shortcutIntent = new Intent(getApplicationContext(), MainActivity.class);

        final int radius = 25;
        final int margin = 0;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin,ALL);

        Bitmap b = BitmapFactory.decodeResource(context.getResources(),R.drawable.paimon_lost);
        Bitmap bitmap = Bitmap.createScaledBitmap(b, 128, 128, false);

        // Decorate the shortcut
        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, bitmap);

        // Inform launcher to create shortcut
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(addIntent);
    }

    private void shortcutDel(String name) {
        // Intent to be send, when shortcut is pressed by user ("launched")
        Intent shortcutIntent = new Intent(getApplicationContext(), MainActivity.class);
        // shortcutIntent.setAction(Constants.ACTION_PLAY);

        // Decorate the shortcut
        Intent delIntent = new Intent();
        delIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        delIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        // Inform launcher to remove shortcut
        delIntent.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(delIntent);
    }

    public void lang_setup(){
        sharedPreferences = getSharedPreferences("user_info",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        int x = 2;
        if(sharedPreferences.getString("curr_lang","").isEmpty()){
            Log.wtf("FFF","TTT");
            String tag = Locale.getDefault().toLanguageTag();
            if(tag.contains("zh-")){
                if(tag.equals("zh-CN")){
                    editor.putString("curr_lang","zh-CN"); editor.putInt("curr_lang_pos",1);x=1;
                }else{
                    editor.putString("curr_lang","zh-HK"); editor.putInt("curr_lang_pos",0);x=0;
                }
            }else if(tag.contains("en-")){
                editor.putString("curr_lang","en-US"); editor.putInt("curr_lang_pos",2);x=2;
            }else if(tag.contains("ru-")){
                editor.putString("curr_lang","ru-RU"); editor.putInt("curr_lang_pos",3);x=3;
            }else if(tag.contains("ja-")){
                editor.putString("curr_lang","ja-JP"); editor.putInt("curr_lang_pos",4);x=4;
            }else if(tag.contains("fr-")){
                editor.putString("curr_lang","ft-FR"); editor.putInt("curr_lang_pos",5);x=5;
            }else if(tag.contains("uk-")){
                editor.putString("curr_lang","uk-UA"); editor.putInt("curr_lang_pos",6);x=6;
            }else{
                editor.putString("curr_lang","en-US"); editor.putInt("curr_lang_pos",2);x=2;
            }
            editor.apply();
            LangUtils.getAttachBaseContext(context,x);
            recreate();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);

            // FROMPATH = imagePath;
            SharedPreferences sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("tmp_pathName",imagePath);

            switch (imagePath.split("\\.")[1].toLowerCase()){
                case "png" : editor.putString("tmp_gif_png","png");break;
                case "jpg" : editor.putString("tmp_gif_png","jpg");break;
                case "jpeg" : editor.putString("tmp_gif_png","jpeg");break;
                case "gif" : editor.putString("tmp_gif_png","gif");break;
            }

            editor.apply();
            c.close();

            File file = new File(imagePath);
            try (InputStream in = new FileInputStream(file)) {
                try (OutputStream out = new FileOutputStream(context.getFilesDir()+"/tmp_background."+sharedPreferences.getString("tmp_gif_png","png"))) {
                    // Transfer bytes from in to out
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(MainActivity.this, BackgroundConfirmActivity.class));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        cbg();
        BackgroundReload.BackgroundReload(context,activity);

        getDOW();
        char_reload();
        weapon_reload();
        bday_reload();
    }

    public String prettyByteCount(Number number) {
        String[] suffix = {"B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};
        long numValue = ((long) number.longValue());
        int base = 0 ;
        double tmp_val = numValue;
        while (tmp_val> 1024){
            tmp_val = tmp_val/1024;
            base = base +1;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE);
        int decimal_num = 2;//sharedPreferences.getInt("decimal_num", 0);
        boolean decimal  = sharedPreferences.getBoolean("decimal", false);
        if (base < suffix.length) {
            return new DecimalFormat("##.##").format(numValue / Math.pow(1024, base)) + suffix[base];
            // Muility
        } else {
            return new DecimalFormat("#,###").format(numValue);
        }
    }
    public static long getRemoteFileSize(String urlSTR) {
        URL url = null;
        try {
            url = new URL(urlSTR.replace("http://vt.25u.com","https://vt.25u.com"));
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            return urlConnection.getContentLength();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }


    public static long getRemoteFileSizeA (ArrayList<String> urlSTR) {
        URL url = null;
        long size = 0;
        for (int x = 0 ;x< urlSTR.size() ; x++){
            System.out.println(urlSTR.get(x));
            try {
                url = new URL(urlSTR.get(x));
                URLConnection urlConnection = url.openConnection();
                urlConnection.connect();
                size = size+urlConnection.getContentLength();
                System.out.println("getR : "+size);
                System.out.println("getRX : "+urlConnection.getContentLength());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return size;
    }

    public void check_updates(){
        OkHttpClient client = new OkHttpClient();
        String url = "https://vt.25u.com/genshin_spirit/update.json";
        if (BuildConfig.FLAVOR.equals("dev")){
            //if (BuildConfig.FLAVOR.equals("dev") || BuildConfig.FLAVOR.equals("beta")){
            url = "https://vt.25u.com/genshin_spirit/update_dev.json";
        }
        Request request = new Request.Builder().url(url).build();

        long lastUnix = System.currentTimeMillis();

        try {
            Response sponse = client.newCall(request).execute();
            String str = sponse.body().string();
            JSONArray array = new JSONArray(str);
            ArrayList<String> array_download = new ArrayList<String>();
            ArrayList<String> array_fileName = new ArrayList<String>();
            ArrayList<String> array_SfileName = new ArrayList<String>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                long release_unix = object.getLong("release_unix");
                String fileName = object.getString("fileName");

                if (i == 0) {
                    lastUnix = release_unix;
                }

                if (release_unix > sharedPreferences.getLong("lastUpdateUnix", 1)) {
                    array_download.add("https://vt.25u.com/genshin_spirit/" + fileName);
                    array_fileName.add(fileName);
                    array_SfileName.add("/" + fileName);
                }
            }
            if(array_download.size()>0){
                Dialog2048 dialog2048 = new Dialog2048();
                dialog2048.setup(context,activity);
                dialog2048.updateMax(getRemoteFileSizeA(array_download));
                dialog2048.mode(Dialog2048.MODE_DOWNLOAD_UPDATE);
                dialog2048.show();

                dialog2048.getPositiveBtn().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog2048.dismiss();
                        DownloadTask downloadTask = new DownloadTask();
                        downloadTask.startA(array_download,array_fileName,array_SfileName,context,activity);
                        editor.apply();
                    }
                });

                dialog2048.getNegativeBtn().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2048.dismiss();
                    }
                });

            }else{
                CustomToast.toast(context,this,context.getString(R.string.update_download_not_found_update));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void filterCharAlgothm(){
        ArrayList<Characters> filteredList = new ArrayList<>();
        for (Characters item : charactersList) {
            // DEFAULT
            if((show_sword == false && show_claymore == false && show_catalyst == false && show_bow == false && show_polearm == false) &&
                    (show_anemo == false  && show_cryo == false && show_dendor == false && show_electro == false && show_hydro == false && show_geo == false && show_pyro == false) &&
                    (show_released == false  && show_unreleased == false) &&
                    (show_rare1 == false && show_rare2 == false && show_rare3 == false && show_rare4 == false && show_rare5 == false) &&
                    (show_dps == false && show_sub_dps == false && show_util == false)) {
                filteredList.add(item);
            }else{
                boolean isAllTrue = true;
                int isSingleElement = 0;
                int isSingleWeapon = 0;
                int isSingleRare = 0;
                int isSingleRelease = 0;
                int isSingleRole = 0;

                if (show_pyro){isSingleElement = isSingleElement+1;}
                if (show_hydro){isSingleElement = isSingleElement+1;}
                if (show_anemo){isSingleElement = isSingleElement+1;}
                if (show_dendor){isSingleElement = isSingleElement+1;}
                if (show_electro){isSingleElement = isSingleElement+1;}
                if (show_cryo){isSingleElement = isSingleElement+1;}
                if (show_geo){isSingleElement = isSingleElement+1;}

                if (show_sword){isSingleWeapon = isSingleWeapon+1;}
                if (show_claymore){isSingleWeapon = isSingleWeapon+1;}
                if (show_polearm){isSingleWeapon = isSingleWeapon+1;}
                if (show_bow){isSingleWeapon = isSingleWeapon+1;}
                if (show_catalyst){isSingleWeapon = isSingleWeapon+1;}

                if (show_rare4){isSingleRare = isSingleRare+1;}
                if (show_rare5){isSingleRare = isSingleRare+1;}
                if (show_released){isSingleRelease = isSingleRelease+1;}
                if (show_unreleased){isSingleRelease = isSingleRelease+1;}
                if (show_dps){isSingleRelease = isSingleRelease+1;}
                if (show_sub_dps){isSingleRelease = isSingleRelease+1;}
                if (show_util){isSingleRelease = isSingleRelease+1;}

                if (isSingleElement == 1){
                    if(show_pyro && !item.getElement().toLowerCase().equals("pyro") ){isAllTrue = false;}
                    if(show_hydro && !item.getElement().toLowerCase().equals("hydro") ){isAllTrue = false;}
                    if(show_anemo && !item.getElement().toLowerCase().equals("anemo") ){isAllTrue = false;}
                    if(show_dendor && !item.getElement().toLowerCase().equals("dendro")){isAllTrue = false;}
                    if(show_electro && !item.getElement().toLowerCase().equals("electro")){isAllTrue = false;}
                    if(show_cryo && !item.getElement().toLowerCase().equals("cryo")){isAllTrue = false;}
                    if(show_geo && !item.getElement().toLowerCase().equals("geo") ){isAllTrue = false;}
                }else if ((show_anemo == false  && show_cryo == false && show_dendor == false && show_electro == false && show_hydro == false && show_geo == false && show_pyro == false) == false){
                    if(!show_pyro && item.getElement().toLowerCase().equals("pyro") ){isAllTrue = false;}
                    if(!show_hydro && item.getElement().toLowerCase().equals("hydro") ){isAllTrue = false;}
                    if(!show_anemo && item.getElement().toLowerCase().equals("anemo") ){isAllTrue = false;}
                    if(!show_dendor && item.getElement().toLowerCase().equals("dendro")){isAllTrue = false;}
                    if(!show_electro && item.getElement().toLowerCase().equals("electro")){isAllTrue = false;}
                    if(!show_cryo && item.getElement().toLowerCase().equals("cryo")){isAllTrue = false;}
                    if(!show_geo && item.getElement().toLowerCase().equals("geo") ){isAllTrue = false;}
                }

                if(isSingleWeapon == 1){
                    if(show_sword && !item.getWeapon().toLowerCase().equals("sword")){isAllTrue = false;}
                    if(show_claymore && !item.getWeapon().toLowerCase().equals("claymore")){isAllTrue = false;}
                    if(show_polearm && !item.getWeapon().toLowerCase().equals("polearm")){isAllTrue = false;}
                    if(show_bow && !item.getWeapon().toLowerCase().equals("bow") ){isAllTrue = false;}
                    if(show_catalyst && !item.getWeapon().toLowerCase().equals("catalyst") ){isAllTrue = false;}
                }else if ((show_sword == false && show_claymore == false && show_catalyst == false && show_bow == false && show_polearm == false) == false){
                    if(!show_sword && item.getWeapon().toLowerCase().equals("sword")){isAllTrue = false;}
                    if(!show_claymore && item.getWeapon().toLowerCase().equals("claymore")){isAllTrue = false;}
                    if(!show_polearm && item.getWeapon().toLowerCase().equals("polearm")){isAllTrue = false;}
                    if(!show_bow && item.getWeapon().toLowerCase().equals("bow") ){isAllTrue = false;}
                    if(!show_catalyst && item.getWeapon().toLowerCase().equals("catalyst") ){isAllTrue = false;}
                }

                if(isSingleRare == 1){
                    if(show_rare4 && item.getRare() !=4 ){isAllTrue = false;}
                    if(show_rare5 && item.getRare() !=5 ){isAllTrue = false;}
                }else if ((show_rare4 == false && show_rare5 == false ) == false){
                    if(!show_rare4 && item.getRare() ==4 ){isAllTrue = false;}
                    if(!show_rare5 && item.getRare() ==5 ){isAllTrue = false;}
                }

                if (isSingleRelease == 1){
                    if(show_released && item.getIsComing() !=0){isAllTrue = false;}
                    if(show_unreleased && item.getIsComing() !=1 ){isAllTrue = false;}
                }else if ((show_released == false && show_unreleased == false ) == false){
                    if(!show_released && item.getIsComing() ==0){isAllTrue = false;}
                    if(!show_unreleased && item.getIsComing() ==1 ){isAllTrue = false;}
                }

                /*
                if (isSingleRole == 1){
                    if(show_dps && !item.getRole().equals("Main_DPS") ){isAllTrue = false;}
                    if(show_sub_dps && !item.getRole().equals("Support_DPS") ){isAllTrue = false;}
                    if(show_util && !item.getRole().equals("Utility")){isAllTrue = false;}
                }else if ((show_dps == false && show_sub_dps == false && show_util == false) == false){
                    if(!show_dps && item.getRole().equals("Main_DPS") ){isAllTrue = false;}
                    if(!show_sub_dps && item.getRole().equals("Support_DPS") ){isAllTrue = false;}
                    if(!show_util && item.getRole().equals("Utility")){isAllTrue = false;}
                }
                 */

                if (isAllTrue == true){
                    filteredList.add(item);
                }
            }
        }

        mList.removeAllViews();
        mAdapter.filterList(filteredList);
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
    }

    public void filterWeaponAlgothm(int star){
        switch (star){
            case 0: show_rare1 = false; show_rare2 = false ; show_rare3 = false;show_rare4 = false ; show_rare5 = false;break;
            case 1: show_rare1 = true;break;
            case 2: show_rare2 = true;break;
            case 3: show_rare3 = true;break;
            case 4: show_rare4 = true;break;
            case 5: show_rare5 = true;break;
        }

        ArrayList<Weapons> filteredList = new ArrayList<>();
        for (Weapons item : weaponsList) {
            // DEFAULT
            if((show_sword == false && show_claymore == false && show_catalyst == false && show_bow == false && show_polearm == false) &&
                    (show_released == false  && show_unreleased == false) &&
                    (show_rare1 == false && show_rare2 == false && show_rare3 == false && show_rare4 == false && show_rare5 == false)) {
                filteredList.add(item);
            }else{
                boolean isAllTrue = true;
                int isSingleWeapon = 0;
                int isSingleRare = 0;
                int isSingleRelease = 0;

                if (show_sword){isSingleWeapon = isSingleWeapon+1;}
                if (show_claymore){isSingleWeapon = isSingleWeapon+1;}
                if (show_polearm){isSingleWeapon = isSingleWeapon+1;}
                if (show_bow){isSingleWeapon = isSingleWeapon+1;}
                if (show_catalyst){isSingleWeapon = isSingleWeapon+1;}

                if (show_rare1){isSingleRare = isSingleRare+1;}
                if (show_rare2){isSingleRare = isSingleRare+1;}
                if (show_rare3){isSingleRare = isSingleRare+1;}
                if (show_rare4){isSingleRare = isSingleRare+1;}
                if (show_rare5){isSingleRare = isSingleRare+1;}
                if (show_released){isSingleRelease = isSingleRelease+1;}
                if (show_unreleased){isSingleRelease = isSingleRelease+1;}

                if(isSingleWeapon == 1){
                    if(show_sword && !item.getWeapon().toLowerCase().equals("sword")){isAllTrue = false;}
                    if(show_claymore && !item.getWeapon().toLowerCase().equals("claymore")){isAllTrue = false;}
                    if(show_polearm && !item.getWeapon().toLowerCase().equals("polearm")){isAllTrue = false;}
                    if(show_bow && !item.getWeapon().toLowerCase().equals("bow") ){isAllTrue = false;}
                    if(show_catalyst && !item.getWeapon().toLowerCase().equals("catalyst") ){isAllTrue = false;}
                }else if ((show_sword == false && show_claymore == false && show_catalyst == false && show_bow == false && show_polearm == false) == false){
                    if(!show_sword && item.getWeapon().toLowerCase().equals("sword")){isAllTrue = false;}
                    if(!show_claymore && item.getWeapon().toLowerCase().equals("claymore")){isAllTrue = false;}
                    if(!show_polearm && item.getWeapon().toLowerCase().equals("polearm")){isAllTrue = false;}
                    if(!show_bow && item.getWeapon().toLowerCase().equals("bow") ){isAllTrue = false;}
                    if(!show_catalyst && item.getWeapon().toLowerCase().equals("catalyst") ){isAllTrue = false;}
                }

                if(isSingleRare == 1){
                    if(show_rare1 && item.getRare() !=1 ){isAllTrue = false;}
                    if(show_rare2 && item.getRare() !=2 ){isAllTrue = false;}
                    if(show_rare3 && item.getRare() !=3 ){isAllTrue = false;}
                    if(show_rare4 && item.getRare() !=4 ){isAllTrue = false;}
                    if(show_rare5 && item.getRare() !=5 ){isAllTrue = false;}
                }else if ((show_rare1 == false &&show_rare2 == false &&show_rare3 == false &&show_rare4 == false && show_rare5 == false ) == false){
                    if(!show_rare1 && item.getRare() ==1 ){isAllTrue = false;}
                    if(!show_rare2 && item.getRare() ==2 ){isAllTrue = false;}
                    if(!show_rare3 && item.getRare() ==3 ){isAllTrue = false;}
                    if(!show_rare4 && item.getRare() ==4 ){isAllTrue = false;}
                    if(!show_rare5 && item.getRare() ==5 ){isAllTrue = false;}
                }

                if (isSingleRelease == 1){
                    if(show_released && item.getIsComing() !=0){isAllTrue = false;}
                    if(show_unreleased && item.getIsComing() !=1 ){isAllTrue = false;}
                }else if ((show_released == false && show_unreleased == false ) == false){
                    if(!show_released && item.getIsComing() ==0){isAllTrue = false;}
                    if(!show_unreleased && item.getIsComing() ==1 ){isAllTrue = false;}
                }

                if (isAllTrue == true){
                    filteredList.add(item);
                }
            }
        }
        mWeaponList.removeAllViews();
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
    }

    public void filterArtifactAlgothm(int star){
        switch (star){
            case 0: show_rare1 = false; show_rare2 = false ; show_rare3 = false;show_rare4 = false ; show_rare5 = false;break;
            case 1: show_rare1 = true;break;
            case 2: show_rare2 = true;break;
            case 3: show_rare3 = true;break;
            case 4: show_rare4 = true;break;
            case 5: show_rare5 = true;break;
        }
        ArrayList<Artifacts> filteredList = new ArrayList<>();
        for (Artifacts item : artifactsList) {
            // DEFAULT
            if((show_released == false  && show_unreleased == false) &&
                    (show_rare1 == false && show_rare2 == false && show_rare3 == false && show_rare4 == false && show_rare5 == false)) {
                filteredList.add(item);
            }else{
                boolean isAllTrue = true;
                int isSingleRare = 0;
                int isSingleRelease = 0;

                if (show_rare1){isSingleRare = isSingleRare+1;}
                if (show_rare2){isSingleRare = isSingleRare+1;}
                if (show_rare3){isSingleRare = isSingleRare+1;}
                if (show_rare4){isSingleRare = isSingleRare+1;}
                if (show_rare5){isSingleRare = isSingleRare+1;}
                if (show_released){isSingleRelease = isSingleRelease+1;}
                if (show_unreleased){isSingleRelease = isSingleRelease+1;}


                if(isSingleRare == 1){
                    if(show_rare1 && item.getRare() !=1 ){isAllTrue = false;}
                    if(show_rare2 && item.getRare() !=2 ){isAllTrue = false;}
                    if(show_rare3 && item.getRare() !=3 ){isAllTrue = false;}
                    if(show_rare4 && item.getRare() !=4 ){isAllTrue = false;}
                    if(show_rare5 && item.getRare() !=5 ){isAllTrue = false;}
                }else if ((show_rare1 == false &&show_rare2 == false &&show_rare3 == false &&show_rare4 == false && show_rare5 == false ) == false){
                    if(!show_rare1 && item.getRare() ==1 ){isAllTrue = false;}
                    if(!show_rare2 && item.getRare() ==2 ){isAllTrue = false;}
                    if(!show_rare3 && item.getRare() ==3 ){isAllTrue = false;}
                    if(!show_rare4 && item.getRare() ==4 ){isAllTrue = false;}
                    if(!show_rare5 && item.getRare() ==5 ){isAllTrue = false;}
                }

                if (isSingleRelease == 1){
                    if(show_released && item.getIsComing() !=0){isAllTrue = false;}
                    if(show_unreleased && item.getIsComing() !=1 ){isAllTrue = false;}
                }else if ((show_released == false && show_unreleased == false ) == false){
                    if(!show_released && item.getIsComing() ==0){isAllTrue = false;}
                    if(!show_unreleased && item.getIsComing() ==1 ){isAllTrue = false;}
                }

                if (isAllTrue == true){
                    filteredList.add(item);
                }
            }
        }
        mArtifactList.removeAllViews();
        mArtifactAdapter.filterList(filteredList);
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
    }

    @Override
    protected void onDestroy(){
        changeLog.dismiss();
        super.onDestroy();
    }
}