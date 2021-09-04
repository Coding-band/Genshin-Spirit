package com.voc.genshin_helper.ui;

//https://stackoverflow.com/questions/27128425/add-multiple-custom-views-to-layout-programmatically

import android.animation.ObjectAnimator;
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
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.voc.genshin_helper.BuildConfig;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.Characters;
import com.voc.genshin_helper.data.CharactersAdapter;
import com.voc.genshin_helper.data.Characters_Rss;
import com.voc.genshin_helper.data.ScreenSizeUtils;
import com.voc.genshin_helper.data.Today_Material;
import com.voc.genshin_helper.util.CalculatorProcess;
import com.voc.genshin_helper.util.LangUtils;
import com.voc.genshin_helper.util.LocaleHelper;
import com.voc.genshin_helper.util.NumberPickerDialog;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


/**
 * Package com.voc.genshin_helper.ui was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2020 Xectorda 版權所有
 *
 * SINCE 20210731 09:52:28 UTC+8
 */

public class MainActivity extends AppCompatActivity {

    ViewGroup char_ll;
    ViewGroup weapon_ll;
    BottomNavigationView nav_view;
    Today_Material tm;
    Characters_Rss css;
    //Char Page
    RecyclerView mList;
    CharactersAdapter mAdapter;
    LocaleHelper localeHelper;
    NumberPickerDialog npd;


    int dow = 0;
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

    public int show_stars = 0;

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    public List<Characters> charactersList = new ArrayList<>();
    boolean first = true;

    String lang = "en-US";

    RadioButton theme_light;
    RadioButton theme_night;
    RadioButton theme_default;

    String[] langList ;
    String[] serverList ;

    private ViewPager viewPager;
    private ArrayList<View> viewPager_List;

    View viewPager0, viewPager1, viewPager2, viewPager3, viewPager4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPreferences = getSharedPreferences("user_info",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (sharedPreferences.getBoolean("theme_light",true) == true){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        if (sharedPreferences.getBoolean("theme_night",false) == true){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        if (sharedPreferences.getBoolean("theme_default",false) == true){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init
        tm = new Today_Material();
        css = new Characters_Rss();
        localeHelper = new LocaleHelper();
        langUtils = new LangUtils();

        context = this;
        serverList = new String[]{getString(R.string.america_ser),getString(R.string.europe_ser),getString(R.string.asia_ser),getString(R.string.hk_tw_mo_ser),getString(R.string.sky_land_ser),getString(R.string.world_tree)};

        viewPager = (ViewPager) findViewById(R.id.vp);
        nav_view = findViewById(R.id.nav_view);

        css = new Characters_Rss();
        npd = new NumberPickerDialog(this);
        context = this;

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
        home();
        getDOW();
        char_reload();
        weapon_reload();
        cbg();
        setColorBk();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){

            @Override
            public void run() {
                getDOW();
                char_reload();
                weapon_reload();
            }}, 60000);

        viewPager.setAdapter(new MyViewPagerAdapter(viewPager_List));

        app_started = sharedPreferences.getInt("app_started",1);
        boolean voted = sharedPreferences.getBoolean("voted",false);
        if(voted == false && app_started >= 5){
            showVoteDialog();
        }
        editor.putInt("app_started",app_started+1);
        editor.apply();

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

        nav_view.setSelectedItemId(R.id.navigation_home);
        viewPager.setCurrentItem(2);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        check_spinner = 0;
                        mList = viewPager0.findViewById(R.id.main_list);
                        mAdapter = new CharactersAdapter(context,charactersList);
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
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
                                    if (item.getName().toLowerCase().contains(String.valueOf(str))||css.LocaleStr(x,context).contains(String.valueOf(s))||css.LocaleStr(x,context).toLowerCase().contains(String.valueOf(s).toLowerCase())) {
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

                                show_pyro = sharedPreferences.getBoolean("show_pyro",true);
                                show_hydro = sharedPreferences.getBoolean("show_hydro",true);
                                show_anemo = sharedPreferences.getBoolean("show_anemo",true);
                                show_electro = sharedPreferences.getBoolean("show_electro",true);
                                show_dendor = sharedPreferences.getBoolean("show_dendor",true);
                                show_cryo = sharedPreferences.getBoolean("show_cryo",true);
                                show_geo = sharedPreferences.getBoolean("show_geo",true);
                                show_sword = sharedPreferences.getBoolean("show_sword",true);
                                show_claymore = sharedPreferences.getBoolean("show_claymore",true);
                                show_polearm = sharedPreferences.getBoolean("show_polearm",true);
                                show_bow = sharedPreferences.getBoolean("show_bow",true);
                                show_catalyst = sharedPreferences.getBoolean("show_catalyst",true);
                                show_catalyst = sharedPreferences.getBoolean("show_catalyst",true);
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
                                        show_pyro = true;
                                        show_hydro = true;
                                        show_anemo = true;
                                        show_dendor = true;
                                        show_electro = true;
                                        show_cryo = true;
                                        show_geo = true;

                                        show_sword = true;
                                        show_claymore = true;
                                        show_polearm = true;
                                        show_bow = true;
                                        show_catalyst = true;

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

                                        mAdapter.filterList(charactersList);

                                    }
                                });

                                ok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ArrayList<Characters> filteredList = new ArrayList<>();
                                        for (Characters item : charactersList) {
                                            if (item.getElement().toLowerCase().equals("pyro") && show_pyro||item.getElement().toLowerCase().equals("hydro") && show_hydro||item.getElement().toLowerCase().equals("anemo") && show_anemo||item.getElement().toLowerCase().equals("electro") && show_electro||item.getElement().toLowerCase().equals("dendor") && show_dendor||item.getElement().toLowerCase().equals("cryo") && show_cryo||item.getElement().toLowerCase().equals("geo") && show_geo) {
                                                if(item.getWeapon().toLowerCase().equals("sword") && show_sword||item.getWeapon().toLowerCase().equals("claymore") && show_claymore||item.getWeapon().toLowerCase().equals("polearm") && show_polearm||item.getWeapon().toLowerCase().equals("bow") && show_bow||item.getWeapon().toLowerCase().equals("catalyst") && show_catalyst){
                                                    if(ratingBar.getRating() != 0 && item.getRare() == ratingBar.getRating()){
                                                        filteredList.add(item);
                                                    }else if (ratingBar.getRating() == 0){
                                                        filteredList.add(item);
                                                    }
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
                        break;

                    case 1:
                        check_spinner = 0;
                        break;

                    case 2:
                        check_spinner = 0;
                        home();
                        getDOW();
                        char_reload();
                        weapon_reload();
                        cbg();
                        setColorBk();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable(){

                            @Override
                            public void run() {
                                getDOW();
                                char_reload();
                                weapon_reload();
                            }}, 60000);
                        break;

                    case 3:
                        check_spinner = 0;
                        break;

                    case 4:
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
                                editor.apply();

                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                            }
                        });
                        // Color
                        ImageView color_bk1,	color_bk2,	color_bk3,	color_bk4,	color_bk5,	color_bk6,	color_bk7,	color_bk8,	color_bk9,	color_bk10,	color_bk11,	color_bk12,	color_bk13,	color_bk14,	color_bk15,	color_bk16,	color_bk17,	color_bk18;
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



                        // Translate
                        langList = new String[]{getString(R.string.zh_hk),getString(R.string.zh_cn),getString(R.string.en_us),getString(R.string.ru)};
                        ArrayAdapter lang_aa = new ArrayAdapter(context,R.layout.spinner_item,langList);
                        lang_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);

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
                                        editor.putInt("curr_lang_pos",0);
                                        editor.apply();
                                        LangUtils.getAttachBaseContext(context,0);
                                        Toast.makeText(context, context.getString(R.string.pls_restart_app), Toast.LENGTH_SHORT).show();
                                    }else if(position == 1){
                                        editor.putString("curr_lang","zh-CN");
                                        editor.putInt("curr_lang_pos",1);
                                        editor.apply();
                                        LangUtils.getAttachBaseContext(context,1);
                                        Toast.makeText(context, context.getString(R.string.pls_restart_app), Toast.LENGTH_SHORT).show();
                                    }else if(position == 2){
                                        editor.putString("curr_lang","en-US");
                                        editor.putInt("curr_lang_pos",2);
                                        editor.apply();
                                        LangUtils.getAttachBaseContext(context,2);
                                        Toast.makeText(context, context.getString(R.string.pls_restart_app), Toast.LENGTH_SHORT).show();
                                    }else if(position == 3){
                                        editor.putString("curr_lang","ru-RU");
                                        editor.putInt("curr_lang_pos",3);
                                        editor.apply();
                                        LangUtils.getAttachBaseContext(context,3);
                                        Toast.makeText(context, context.getString(R.string.pls_restart_app), Toast.LENGTH_SHORT).show();
                                    }
                                }
                                check_spinner = check_spinner +1;
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        TextView contact_link1 = viewPager4.findViewById(R.id.contact_link1);
                        TextView contact_link2 = viewPager4.findViewById(R.id.contact_link2);
                        TextView contact_link3 = viewPager4.findViewById(R.id.contact_link3);
                        TextView contact_link4 = viewPager4.findViewById(R.id.contact_link4);
                        TextView contact_link5 = viewPager4.findViewById(R.id.contact_link5);

                        contact_link1.setMovementMethod(LinkMovementMethod.getInstance());
                        contact_link2.setMovementMethod(LinkMovementMethod.getInstance());
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

                        // Other -> Server Location

                        ArrayAdapter server_aa = new ArrayAdapter(context,R.layout.spinner_item,serverList);
                        server_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);

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
                    return true;
                }
                return false;
            }
        });
    }

    private void showVoteDialog() {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MainActivity.this,R.style.MyDialogTheme);
        normalDialog.setIcon(R.drawable.app_ico);
        normalDialog.setTitle(getString(R.string.vote_title));
        normalDialog.setMessage(getString(R.string.vote_info));
        normalDialog.setPositiveButton("幫忙評分",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.putBoolean("voted",true);
                        editor.apply();
                        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }
                    }
                });
        normalDialog.setNegativeButton("不用了",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.putBoolean("voted",true);
                        editor.apply();
                    }
                });
        normalDialog.setNeutralButton("以後再說",
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

        giveTickById(color_bk,pos-1);
    }

    private void giveTickById(ImageView color_bk,int pos) {
        ImageView color_bk1,	color_bk2,	color_bk3,	color_bk4,	color_bk5,	color_bk6,	color_bk7,	color_bk8,	color_bk9,	color_bk10,	color_bk11,	color_bk12,	color_bk13,	color_bk14,	color_bk15,	color_bk16,	color_bk17,	color_bk18;
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
        color_bk.setForeground(getResources().getDrawable(R.drawable.ic_tick_img));

        int[] colorList = new int[]{R.color.color_theme_1,	R.color.color_theme_2,	R.color.color_theme_3,	R.color.color_theme_4,	R.color.color_theme_5,	R.color.color_theme_6,	R.color.color_theme_7,	R.color.color_theme_8,	R.color.color_theme_9,	R.color.color_theme_10,	R.color.color_theme_11,	R.color.color_theme_12,	R.color.color_theme_13,	R.color.color_theme_14,	R.color.color_theme_15,	R.color.color_theme_16,	R.color.color_theme_17,	R.color.color_theme_18};
        editor.putString("theme_color_hex",getResources().getString(colorList[pos]));
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
                Intent i = new Intent(MainActivity.this,CalculatorUI.class);
                startActivity(i);
            }
        });
        daily_login_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.fragment_web, null);
                dialog.setContentView(view);
                dialog.setCanceledOnTouchOutside(true);

                WebView webview = view.findViewById(R.id.webView);
                ImageView back_btn = view.findViewById(R.id.back_btn);

                WebSettings webSettings = webview.getSettings();
                webSettings.setJavaScriptEnabled(true);
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
            }
        });
        map_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.fragment_web, null);
                dialog.setContentView(view);
                dialog.setCanceledOnTouchOutside(true);

                WebView webview = view.findViewById(R.id.webView);
                ImageView back_btn = view.findViewById(R.id.back_btn);

                WebSettings webSettings = webview.getSettings();
                webSettings.setJavaScriptEnabled(true);
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
                webview.loadUrl("https://webstatic-sea.mihoyo.com/app/ys-map-sea/index.html?utm_source=hoyolab&lang=zh-tw#/map/2");

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
            mAdapter.filterList(charactersList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showCharDetail (String name){

    }

    public String LoadData(String inFile) {
        String tContents = "";

        try {
            InputStream stream = getAssets().open(inFile);

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

    public void char_reload(){
        char_ll = viewPager2.findViewById(R.id.char_ll);
        char_ll.removeAllViews();
        //Setup item_today_material
        int[] today_IMG = tm.today_char_IMG(dow);
        int[] today_TV = tm.today_char_TV(dow);
        for (int x = 0 ; x < today_IMG.length; x++){
            View char_view = LayoutInflater.from(this).inflate(R.layout.item_today_material, char_ll, false);
            ImageView item_img = char_view.findViewById(R.id.item_img);
            TextView item_name = char_view.findViewById(R.id.item_name);
            TextView item_dow = char_view.findViewById(R.id.item_dow);
            item_name.setText(getString(today_TV[x]));
            item_dow.setText(getString(tm.today_is(today_IMG[x])));
            item_img.setImageResource(today_IMG[x]);
            char_ll.addView(char_view);
        }
    }
    public void weapon_reload(){
        weapon_ll = viewPager2.findViewById(R.id.weapon_ll);
        weapon_ll.removeAllViews();
        //Setup item_today_material
        int[] today_IMG = tm.today_weapon_IMG(dow);
        int[] today_TV = tm.today_weapon_TV(dow);
        for (int x = 0 ; x < today_IMG.length; x++){
            View char_view = LayoutInflater.from(this).inflate(R.layout.item_today_material, weapon_ll, false);
            ImageView item_img = char_view.findViewById(R.id.item_img);
            TextView item_name = char_view.findViewById(R.id.item_name);
            TextView item_dow = char_view.findViewById(R.id.item_dow);
            item_name.setText(getString(today_TV[x]));
            item_dow.setText(getString(tm.today_is(today_IMG[x])));
            item_img.setImageResource(today_IMG[x]);
            weapon_ll.addView(char_view);
        }
    }
    public void startInfo (String name){
        Characters_Info cif = new Characters_Info();
        Log.wtf("YES","IT's two");
        cif.setup(String.valueOf(name),context);
    }

    public void cbg() {
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
        TextView x_tv = viewPager2.findViewById(R.id.x_tv);
        TextView y_tv = viewPager2.findViewById(R.id.y_tv);
        BottomNavigationView nav_view = findViewById(R.id.nav_view);

        calculator_tv.setTextColor(Color.parseColor(color_hex));
        daily_login_tv.setTextColor(Color.parseColor(color_hex));
        map_tv.setTextColor(Color.parseColor(color_hex));
        alarm_tv.setTextColor(Color.parseColor(color_hex));
        x_tv.setTextColor(Color.parseColor(color_hex));
        y_tv.setTextColor(Color.parseColor(color_hex));
        nav_view.setItemIconTintList(myList);
        nav_view.setItemTextColor(myList);

        // R.layout.fragment_weapon

        // R.layout.fragment_setting
        RadioButton theme_light = viewPager4.findViewById(R.id.theme_light);
        RadioButton theme_dark = viewPager4.findViewById(R.id.theme_dark);
        RadioButton theme_default = viewPager4.findViewById(R.id.theme_default);

        theme_light.setButtonTintList(myList);
        theme_dark.setButtonTintList(myList);
        theme_default.setButtonTintList(myList);

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
            if(exit == 0){
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.item_toast,findViewById(R.id.toast_frame));
                SharedPreferences sharedPreferences = getSharedPreferences("user_info",MODE_PRIVATE);

                String color_hex = sharedPreferences.getString("theme_color_hex","#FF5A5A"); // Must include #
                ColorStateList myList = new ColorStateList(
                        new int[][]{
                                new int[]{android.R.attr.state_pressed},
                                new int[]{-android.R.attr.state_checked},
                                new int[]{android.R.attr.state_checked},
                        },
                        new int[] {
                                Color.parseColor(color_hex),
                                Color.parseColor(color_hex),
                                Color.parseColor(color_hex)
                        }
                );
                ColorStateList myListD = new ColorStateList(
                        new int[][]{
                                new int[]{android.R.attr.state_pressed},
                                new int[]{-android.R.attr.state_checked},
                                new int[]{android.R.attr.state_checked},
                        },
                        new int[] {
                                getResources().getColor(R.color.tv_anti_color),
                                getResources().getColor(R.color.tv_anti_color),
                                getResources().getColor(R.color.tv_anti_color)
                        }
                );

                if(color_hex.toUpperCase().equals("#FFFFFFFF")){
                    color_hex = "#000000";
                }

                TextView text = (TextView) layout.findViewById(R.id.toast_tv);
                text.setText(getString(R.string.press_exit));
                text.setText(getString(R.string.press_exit));
                text.setTextColor(getResources().getColor(R.color.tv_anti_color));
                text.setBackgroundTintList(myList);
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.BOTTOM, 0, 150);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
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

}