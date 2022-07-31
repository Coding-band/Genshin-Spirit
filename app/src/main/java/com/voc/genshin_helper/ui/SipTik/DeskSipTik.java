package com.voc.genshin_helper.ui.SipTik;

import static com.voc.genshin_helper.util.RoundedCornersTransformation.CornerType.ALL;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.BuildConfig;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.buff.SipTikCal;
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
import com.voc.genshin_helper.ui.AlarmUI;
import com.voc.genshin_helper.ui.BackgroundConfirmActivity;
import com.voc.genshin_helper.ui.CalculatorDBActivity;
import com.voc.genshin_helper.ui.MMXLVIII.Desk2048;
import com.voc.genshin_helper.ui.MainActivity;
import com.voc.genshin_helper.ui.NotificationActivity;
import com.voc.genshin_helper.util.BackgroundReload;
import com.voc.genshin_helper.util.ChangeLog;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.DownloadTask;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.LangUtils;
import com.voc.genshin_helper.util.LocaleHelper;
import com.voc.genshin_helper.util.MyViewPagerAdapter;
import com.voc.genshin_helper.util.NumberPickerDialog;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

public class DeskSipTik extends AppCompatActivity {

    public static int IMAGE = 1;

    boolean isCharLLShow = true;
    boolean isWeaponLLShow = false;

    LinearLayout char_ll;
    LinearLayout weapon_ll;
    TabLayout desk_tablayout;
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

    String[] weekdayList ;
    String[] langList ;
    String[] serverList ;
    String[] gridList ;

    private ViewPager viewPager;
    private ArrayList<View> viewPager_List;
    GoSleep gs;

    Activity activity;

    View viewPager0, viewPager1, viewPager2, viewPager3, viewPager4;

    int[] tabItemNameArray = new int[]{R.string.title_home,R.string.title_char,R.string.title_weapons,R.string.title_artifacts,R.string.title_settings};
    int[] tabItemImageArray = new int[]{R.drawable.ic_siptik_tab_desk,R.drawable.ic_siptik_tab_char,R.drawable.ic_siptik_tab_weapon,R.drawable.ic_siptik_tab_art,R.drawable.ic_siptik_tab_toolbox};
    int[] tabItemImageSelectedArray = new int[]{R.drawable.ic_siptik_tab_desk_selected,R.drawable.ic_siptik_tab_char_selected,R.drawable.ic_siptik_tab_weapon_selected,R.drawable.ic_siptik_tab_art_selected,R.drawable.ic_siptik_tab_toolbox_selected};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("user_info",MODE_PRIVATE);
        sharedPreferences_version = getSharedPreferences("changelog_version",MODE_PRIVATE);
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

        setContentView(R.layout.activity_desk_siptik);

        //init
        tm = new Today_Material();
        css = new ItemRss();
        localeHelper = new LocaleHelper();
        langUtils = new LangUtils();

        context = this;
        activity = this;
        serverList = new String[]{getString(R.string.america_ser),getString(R.string.europe_ser),getString(R.string.asia_ser),getString(R.string.hk_tw_mo_ser),getString(R.string.sky_land_ser),getString(R.string.world_tree)};

        viewPager = (ViewPager) findViewById(R.id.vp);
        desk_tablayout = findViewById(R.id.desk_tablayout);
        npd = new NumberPickerDialog(this);


        // Check Is First Time Open
        if(sharedPreferences_version.getBoolean(BuildConfig.VERSION_NAME,false) == false){
            ChangeLog.show(context,activity);
            editor2 = sharedPreferences_version.edit();
            editor2.putBoolean(BuildConfig.VERSION_NAME,true);
            editor2.apply();
        }

        final LayoutInflater mInflater = getLayoutInflater().from(this);
        viewPager0 = mInflater.inflate(R.layout.fragment_home_siptik, null,false);
        viewPager1 = mInflater.inflate(R.layout.fragment_char_siptik, null,false);
        viewPager2 = mInflater.inflate(R.layout.fragment_weapon_siptik, null,false);
        viewPager3 = mInflater.inflate(R.layout.fragment_art_siptik, null,false);
        viewPager4 = mInflater.inflate(R.layout.fragment_setting_siptik, null,false);

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
        cbg();
        setColorBk();
        char_reload(dow);
        weapon_reload(dow);
        setup_home();
        setup_char();
        setup_weapon();
        setup_art();
        setup_setting();
        BackgroundReload.BackgroundReload(context,activity);

        LinearLayout char_ll = viewPager0.findViewById(R.id.char_ll);
        LinearLayout weapon_ll = viewPager0.findViewById(R.id.weapon_ll);

        char_ll.setVisibility(View.VISIBLE);
        weapon_ll.setVisibility(View.VISIBLE);
        viewPager.setAdapter(new MyViewPagerAdapter(viewPager_List));

        for (int x = 0 ; x < 5 ; x++){
            View view1 = getLayoutInflater().inflate(R.layout.item_custom_tab, null);
            ImageView ico_img = view1.findViewById(R.id.icon);
            TextView name = view1.findViewById(R.id.name);
            ico_img.setImageResource(tabItemImageArray[x]);
            name.setText(context.getString(tabItemNameArray[x]));
            name.setVisibility(View.VISIBLE);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int pix = displayMetrics.widthPixels;
            if (displayMetrics.heightPixels < displayMetrics.widthPixels){
                pix = displayMetrics.heightPixels;
            }

            if ((int) (((pix-32-5*8)/10)*1.2-16) <= 200){
                ico_img.setMaxHeight((int) ((pix-32-5*8)/10*1.2-16));
            }else{
                ico_img.setMaxHeight(200);
            }
            LinearLayout ll = view1.findViewById(R.id.ll);
            desk_tablayout.setMinimumHeight(ll.getMeasuredHeight());
            desk_tablayout.addTab(desk_tablayout.newTab().setCustomView(view1).setId(x));
        }

        desk_tablayout.setTabIndicatorFullWidth(false);

        if (sharedPreferences.getString("SipTikNavi","Hover").equals("Hover")){
            desk_tablayout.setBackgroundResource(R.drawable.bg_siptik_tab);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) desk_tablayout.getLayoutParams();
            layoutParams.setMargins(8,8,8,8);
            layoutParams.verticalBias = 1f;
            desk_tablayout.setLayoutParams(layoutParams);
        }else if (sharedPreferences.getString("SipTikNavi","Hover").equals("Solid")){
            desk_tablayout.setBackgroundResource(R.drawable.bg_siptik_tab_choice2);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) desk_tablayout.getLayoutParams();
            layoutParams.setMargins(0,0,0,0);
            layoutParams.verticalBias = 1f;
            desk_tablayout.setLayoutParams(layoutParams);
        }

        app_started = sharedPreferences.getInt("app_started",1);
        boolean voted = sharedPreferences.getBoolean("voted",false);
        if(voted == false && app_started >= 5){
            showVoteDialog();
        }
        if(sharedPreferences.getBoolean("PASS_JUST_CHANGED_THEME",false) == false){
            editor.putInt("app_started",app_started+1);
            editor.apply();
        }

        if (sharedPreferences.getBoolean("PASS_JUST_CHANGED_THEME",false) == true) {
            editor.putBoolean("PASS_JUST_CHANGED_THEME", false);
            editor.apply();

            viewPager.setCurrentItem(4);
            desk_tablayout.selectTab(desk_tablayout.getTabAt(4));

            View view1 = desk_tablayout.getTabAt(4).getCustomView();
            ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
            tab_icon.setImageResource(tabItemImageSelectedArray[4]);

        }else{
            desk_tablayout.selectTab(desk_tablayout.getTabAt(0));
            viewPager.setCurrentItem(0);

            View view1 = desk_tablayout.getTabAt(0).getCustomView();
            ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
            tab_icon.setImageResource(tabItemImageSelectedArray[0]);
        }



        desk_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
                tab_icon.setImageResource(tabItemImageSelectedArray[tab.getPosition()]);
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
                tab_icon.setImageResource(tabItemImageArray[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                desk_tablayout.selectTab(desk_tablayout.getTabAt(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        TutorialUI tutorialUI = new TutorialUI();
        tutorialUI.deskSetPosArray(0,1,2,3,4);
        tutorialUI.setup(context,activity,viewPager0,viewPager1,viewPager2,viewPager3,viewPager4,desk_tablayout,null);
    }

    private void setup_weapon() {
        check_spinner = 0;
        mWeaponList = viewPager2.findViewById(R.id.weapon_list);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        DisplayMetrics displayMetrics_w = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics_w);
        int height_w = displayMetrics_w.heightPixels;
        int width_w = displayMetrics_w.widthPixels;
        mWeaponAdapter = new WeaponsAdapter(context,weaponsList,activity);

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
        LinearLayout.LayoutParams  paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsMsg.gravity = Gravity.CENTER;
        mWeaponList.setLayoutManager(mLayoutManager);
        mWeaponList.setLayoutParams(paramsMsg);
        mWeaponList.setAdapter(mWeaponAdapter);
        mWeaponList.removeAllViewsInLayout();
        weapon_list_reload();


                        /*
                        EditText weapon_et = viewPager2.findViewById(R.id.char_et);
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
                         */

        ImageView weapon_search = viewPager2.findViewById(R.id.weapon_search);
        weapon_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View header_search = viewPager2.findViewById(R.id.header_search);
                EditText header_search_et = viewPager2.findViewById(R.id.header_search_et);
                Button menu_search_cancel = viewPager2.findViewById(R.id.menu_search_cancel);
                ImageView header_search_reset = viewPager2.findViewById(R.id.header_search_reset);

                header_search.animate()
                        .alpha(1.0f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                            }
                        });
                header_search.setVisibility(View.VISIBLE);

                header_search_et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (header_search_et.getText() != null){
                            String request = header_search_et.getText().toString();
                            if (!request.equals("")){
                                ArrayList<Weapons> filteredList = new ArrayList<>();
                                int x = 0;
                                for (Weapons item : weaponsList) {
                                    String str = request.toLowerCase();
                                    if (css.getWeaponByName(item.getName(),context)[0].contains(str)||css.getWeaponByName(item.getName(),context)[0].toLowerCase().contains(str)||css.getWeaponByName(item.getName(),context)[0].toUpperCase().contains(str)||item.getName().toLowerCase().contains(str)){ // EN -> ZH
                                        filteredList.add(item);
                                    }
                                    x = x +1;
                                }
                                mWeaponAdapter.filterList(filteredList);
                            }else{
                                mWeaponAdapter.filterList(weaponsList);
                            }
                        }else{
                            mWeaponAdapter.filterList(weaponsList);
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

            };
        });

        ImageView weapon_filter = viewPager2.findViewById(R.id.weapon_filter);
        weapon_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.menu_char_filter_siptik, null);
                // Elements
                ImageView pyro_kwang = view.findViewById(R.id.pyro_kwang);
                ImageView hydro_kwang = view.findViewById(R.id.hydro_kwang);
                ImageView anemo_kwang = view.findViewById(R.id.anemo_kwang);
                ImageView electro_kwang = view.findViewById(R.id.electro_kwang);
                ImageView dendor_kwang = view.findViewById(R.id.dendor_kwang);
                ImageView cryo_kwang = view.findViewById(R.id.cryo_kwang);
                ImageView geo_kwang = view.findViewById(R.id.geo_kwang);

                CardView pyro_bg = view.findViewById(R.id.pyro_bg);
                CardView hydro_bg = view.findViewById(R.id.hydro_bg);
                CardView anemo_bg = view.findViewById(R.id.anemo_bg);
                CardView electro_bg = view.findViewById(R.id.electro_bg);
                CardView dendor_bg = view.findViewById(R.id.dendor_bg);
                CardView cryo_bg = view.findViewById(R.id.cryo_bg);
                CardView geo_bg = view.findViewById(R.id.geo_bg);
                // Weapons
                ImageView ico_sword = view.findViewById(R.id.ico_sword);
                ImageView ico_claymore = view.findViewById(R.id.ico_claymore);
                ImageView ico_polearm = view.findViewById(R.id.ico_polearm);
                ImageView ico_bow = view.findViewById(R.id.ico_bow);
                ImageView ico_catalyst = view.findViewById(R.id.ico_catalyst);
                // Role
                Spinner role_spinner = view.findViewById(R.id.role_spinner);
                // Rarity
                Spinner rare_spinner = view.findViewById(R.id.rare_spinner);
                // Release
                CheckBox menu_release_0 = view.findViewById(R.id.menu_release_0);
                CheckBox menu_release_1 = view.findViewById(R.id.menu_release_1);

                // Function Buttons
                FrameLayout cancel = view.findViewById(R.id.menu_cancel);
                FrameLayout ok = view.findViewById(R.id.menu_ok);

                TextView menu_elements_title_tv = view.findViewById(R.id.menu_elements_title_tv);
                LinearLayout menu_elements_ll = view.findViewById(R.id.menu_elements_ll);
                LinearLayout menu_role_ll = view.findViewById(R.id.menu_role_ll);

                menu_elements_title_tv.setVisibility(View.GONE);
                menu_elements_ll.setVisibility(View.GONE);
                menu_role_ll.setVisibility(View.GONE);

                if (show_rare1){role_spinner.setSelection(1);}
                if (show_rare2){role_spinner.setSelection(2);}
                if (show_rare3){role_spinner.setSelection(3);}
                if (show_rare4){role_spinner.setSelection(4);}
                if (show_rare5){role_spinner.setSelection(5);}

                if (show_dps){ rare_spinner.setSelection(0); }
                if (show_sub_dps){rare_spinner.setSelection(1); }
                if (show_util){rare_spinner.setSelection(2);}


                String[] roleList = new String[]{"ALL",context.getString(R.string.main_dps),context.getString(R.string.support_dps),context.getString(R.string.utility)};
                String[] rareList = new String[]{"ALL","1","2","3","4","5"};

                ArrayAdapter rare_aa = new ArrayAdapter(context,R.layout.spinner_item,rareList);
                rare_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_2048);

                rare_spinner.setAdapter(rare_aa);
                rare_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0: {show_rare1 = false; show_rare2 = false; show_rare3 = false; show_rare4 = false; show_rare5 = false;break;}
                            case 1: {show_rare1 = true; show_rare2 = false; show_rare3 = false; show_rare4 = false; show_rare5 = false;break;}
                            case 2: {show_rare1 = false; show_rare2 = true; show_rare3 = false; show_rare4 = false; show_rare5 = false;break;}
                            case 3: {show_rare1 = false; show_rare2 = false; show_rare3 = true; show_rare4 = false; show_rare5 = false;break;}
                            case 4: {show_rare1 = false; show_rare2 = false; show_rare3 = false; show_rare4 = true; show_rare5 = false;break;}
                            case 5: {show_rare1 = false; show_rare2 = false; show_rare3 = false; show_rare4 = false; show_rare5 = true;break;}
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter role_aa = new ArrayAdapter(context,R.layout.spinner_item,roleList);
                role_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_2048);

                role_spinner.setAdapter(role_aa);
                role_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0: {show_dps = false; show_sub_dps = false; show_util = false;break;}
                            case 1: {show_dps = true; show_sub_dps = false; show_util = false;break;}
                            case 2: {show_dps = false; show_sub_dps = true; show_util = false;break;}
                            case 3: {show_dps = false; show_sub_dps = true; show_util = true;break;}
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

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

                if(show_pyro){show_pyro = true;pyro_kwang.setVisibility(View.VISIBLE);pyro_bg.setAlpha(1);}else{show_pyro = false;pyro_kwang.setVisibility(View.GONE);pyro_bg.setAlpha(0.5f);}
                if(show_hydro){show_hydro = true;hydro_kwang.setVisibility(View.VISIBLE);hydro_bg.setAlpha(1);}else{show_hydro = false;hydro_kwang.setVisibility(View.GONE);hydro_bg.setAlpha(0.5f);}
                if(show_anemo){show_anemo = true;anemo_kwang.setVisibility(View.VISIBLE);anemo_bg.setAlpha(1);}else{show_anemo = false;anemo_kwang.setVisibility(View.GONE);anemo_bg.setAlpha(0.5f);}
                if(show_electro){show_electro = true;electro_kwang.setVisibility(View.VISIBLE);electro_bg.setAlpha(1);}else{show_electro = false;electro_kwang.setVisibility(View.GONE);electro_bg.setAlpha(0.5f);}
                if(show_dendor){show_dendor = true;dendor_kwang.setVisibility(View.VISIBLE);dendor_bg.setAlpha(1);}else{show_dendor = false;dendor_kwang.setVisibility(View.GONE);dendor_bg.setAlpha(0.5f);}
                if(show_cryo){show_cryo = true;cryo_kwang.setVisibility(View.VISIBLE);cryo_bg.setAlpha(1);}else{show_cryo = false;cryo_kwang.setVisibility(View.GONE);cryo_bg.setAlpha(0.5f);}
                if(show_geo){show_geo = true;geo_kwang.setVisibility(View.VISIBLE);geo_bg.setAlpha(1);}else{show_geo = false;geo_kwang.setVisibility(View.GONE);geo_bg.setAlpha(0.5f);}
                if(show_sword){show_sword = true;ico_sword.setAlpha(1f);}else{show_sword = false;ico_sword.setAlpha(0.5f);}
                if(show_claymore){show_claymore = true;ico_claymore.setAlpha(1f);}else{show_claymore = false;ico_claymore.setAlpha(0.5f);}
                if(show_polearm){show_polearm = true;ico_polearm.setAlpha(1f);}else{show_polearm = false;ico_polearm.setAlpha(0.5f);}
                if(show_bow){show_bow = true;ico_bow.setAlpha(1f);}else{show_bow = false;ico_bow.setAlpha(0.5f);}
                if(show_catalyst){show_catalyst = true;ico_catalyst.setAlpha(1f);}else{show_catalyst = false;ico_catalyst.setAlpha(0.5f);}

                if (show_released){ menu_release_0.setChecked(true); }
                if (show_unreleased){ menu_release_1.setChecked(true); }

                pyro_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_pyro){show_pyro = false;pyro_kwang.setVisibility(View.GONE);pyro_bg.setAlpha(0.5f);}else{show_pyro = true;pyro_kwang.setVisibility(View.VISIBLE);pyro_bg.setAlpha(1);}}});
                hydro_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_hydro){show_hydro = false;hydro_kwang.setVisibility(View.GONE);hydro_bg.setAlpha(0.5f);}else{show_hydro = true;hydro_kwang.setVisibility(View.VISIBLE);hydro_bg.setAlpha(1);}}});
                anemo_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_anemo){show_anemo = false;anemo_kwang.setVisibility(View.GONE);anemo_bg.setAlpha(0.5f);}else{show_anemo = true;anemo_kwang.setVisibility(View.VISIBLE);anemo_bg.setAlpha(1);}}});
                electro_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_electro){show_electro = false;electro_kwang.setVisibility(View.GONE);electro_bg.setAlpha(0.5f);}else{show_electro = true;electro_kwang.setVisibility(View.VISIBLE);electro_bg.setAlpha(1);}}});
                dendor_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_dendor){show_dendor = false;dendor_kwang.setVisibility(View.GONE);dendor_bg.setAlpha(0.5f);}else{show_dendor = true;dendor_kwang.setVisibility(View.VISIBLE);dendor_bg.setAlpha(1);}}});
                cryo_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_cryo){show_cryo = false;cryo_kwang.setVisibility(View.GONE);cryo_bg.setAlpha(0.5f);}else{show_cryo = true;cryo_kwang.setVisibility(View.VISIBLE);cryo_bg.setAlpha(1);}}});
                geo_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_geo){show_geo = false;geo_kwang.setVisibility(View.GONE);geo_bg.setAlpha(0.5f);}else{show_geo = true;geo_kwang.setVisibility(View.VISIBLE);geo_bg.setAlpha(1);}}});
                ico_sword.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_sword){show_sword = false;ico_sword.setAlpha(0.5f);}else{show_sword = true;ico_sword.setAlpha(1f);}}});
                ico_claymore.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_claymore){show_claymore = false;ico_claymore.setAlpha(0.5f);}else{show_claymore = true;ico_claymore.setAlpha(1f);}}});
                ico_polearm.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_polearm){show_polearm = false;ico_polearm.setAlpha(0.5f);}else{show_polearm = true;ico_polearm.setAlpha(1f);}}});
                ico_bow.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_bow){show_bow = false;ico_bow.setAlpha(0.5f);}else{show_bow = true;ico_bow.setAlpha(1f);}}});
                ico_catalyst.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_catalyst){show_catalyst = false;ico_catalyst.setAlpha(0.5f);}else{show_catalyst = true;ico_catalyst.setAlpha(1f);}}});

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                /*
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
                 */
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (menu_release_0.isChecked()){show_released = true;}else{show_released = false;}
                        if (menu_release_1.isChecked()){show_unreleased = true;}else{show_unreleased = false;}
                        filterWeaponAlgothm();
                        dialog.dismiss();
                    }
                });

                dialog.setContentView(view);
                dialog.setCanceledOnTouchOutside(true);
                //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                // 2O48 DESIGN
                //dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_siptik));
                //dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_siptik));

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

        mList = viewPager1.findViewById(R.id.main_list);
        mAdapter = new CharactersAdapter(context,charactersList,activity);
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
        LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsMsg.gravity = Gravity.CENTER;
        mList.setLayoutManager(mLayoutManager);
        mList.setLayoutParams(paramsMsg);
        mList.setAdapter(mAdapter);
        mList.removeAllViewsInLayout();
        char_list_reload();

        ImageView char_search = viewPager1.findViewById(R.id.char_search);
        char_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View header_search = viewPager1.findViewById(R.id.header_search);
                EditText header_search_et = viewPager1.findViewById(R.id.header_search_et);
                Button menu_search_cancel = viewPager1.findViewById(R.id.menu_search_cancel);
                ImageView header_search_reset = viewPager1.findViewById(R.id.header_search_reset);

                header_search.animate()
                        .alpha(1.0f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                            }
                        });
                header_search.setVisibility(View.VISIBLE);

                header_search_et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (header_search_et.getText() != null){
                            String request = header_search_et.getText().toString();
                            if (!request.equals("")){
                                ArrayList<Characters> filteredList = new ArrayList<>();
                                int x = 0;
                                for (Characters item : charactersList) {
                                    String str = request.toLowerCase();
                                    if (css.getCharByName(item.getName(),context)[1].contains(str)||css.getCharByName(item.getName(),context)[1].toLowerCase().contains(str)||item.getName().toLowerCase().contains(str)){ // EN -> ZH
                                        filteredList.add(item);
                                    }
                                    x = x +1;
                                }
                                mAdapter.filterList(filteredList);
                            }else{
                                mAdapter.filterList(charactersList);
                            }
                        }else{
                            mAdapter.filterList(charactersList);
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

            };
        });


        ImageView char_filter = viewPager1.findViewById(R.id.char_filter);
        char_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.menu_char_filter_siptik, null);
                // Elements
                ImageView pyro_kwang = view.findViewById(R.id.pyro_kwang);
                ImageView hydro_kwang = view.findViewById(R.id.hydro_kwang);
                ImageView anemo_kwang = view.findViewById(R.id.anemo_kwang);
                ImageView electro_kwang = view.findViewById(R.id.electro_kwang);
                ImageView dendor_kwang = view.findViewById(R.id.dendor_kwang);
                ImageView cryo_kwang = view.findViewById(R.id.cryo_kwang);
                ImageView geo_kwang = view.findViewById(R.id.geo_kwang);

                CardView pyro_bg = view.findViewById(R.id.pyro_bg);
                CardView hydro_bg = view.findViewById(R.id.hydro_bg);
                CardView anemo_bg = view.findViewById(R.id.anemo_bg);
                CardView electro_bg = view.findViewById(R.id.electro_bg);
                CardView dendor_bg = view.findViewById(R.id.dendor_bg);
                CardView cryo_bg = view.findViewById(R.id.cryo_bg);
                CardView geo_bg = view.findViewById(R.id.geo_bg);
                // Weapons
                ImageView ico_sword = view.findViewById(R.id.ico_sword);
                ImageView ico_claymore = view.findViewById(R.id.ico_claymore);
                ImageView ico_polearm = view.findViewById(R.id.ico_polearm);
                ImageView ico_bow = view.findViewById(R.id.ico_bow);
                ImageView ico_catalyst = view.findViewById(R.id.ico_catalyst);
                // Role
                Spinner role_spinner = view.findViewById(R.id.role_spinner);
                // Rarity
                Spinner rare_spinner = view.findViewById(R.id.rare_spinner);
                // Release
                CheckBox menu_release_0 = view.findViewById(R.id.menu_release_0);
                CheckBox menu_release_1 = view.findViewById(R.id.menu_release_1);

                // Function Buttons
                FrameLayout cancel = view.findViewById(R.id.menu_cancel);
                FrameLayout ok = view.findViewById(R.id.menu_ok);

                if (show_rare1){role_spinner.setSelection(1);}
                if (show_rare2){role_spinner.setSelection(2);}
                if (show_rare3){role_spinner.setSelection(3);}
                if (show_rare4){role_spinner.setSelection(4);}
                if (show_rare5){role_spinner.setSelection(5);}

                if (show_dps){ rare_spinner.setSelection(0); }
                if (show_sub_dps){rare_spinner.setSelection(1); }
                if (show_util){rare_spinner.setSelection(2);}

                String[] roleList = new String[]{context.getString(R.string.all),context.getString(R.string.main_dps),context.getString(R.string.support_dps),context.getString(R.string.utility)};
                String[] rareList = new String[]{context.getString(R.string.all),"1","2","3","4","5"};

                ArrayAdapter rare_aa = new ArrayAdapter(context,R.layout.spinner_item,rareList);
                rare_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_2048);

                rare_spinner.setAdapter(rare_aa);
                rare_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0: {show_rare1 = false; show_rare2 = false; show_rare3 = false; show_rare4 = false; show_rare5 = false;break;}
                            case 1: {show_rare1 = true; show_rare2 = false; show_rare3 = false; show_rare4 = false; show_rare5 = false;break;}
                            case 2: {show_rare1 = false; show_rare2 = true; show_rare3 = false; show_rare4 = false; show_rare5 = false;break;}
                            case 3: {show_rare1 = false; show_rare2 = false; show_rare3 = true; show_rare4 = false; show_rare5 = false;break;}
                            case 4: {show_rare1 = false; show_rare2 = false; show_rare3 = false; show_rare4 = true; show_rare5 = false;break;}
                            case 5: {show_rare1 = false; show_rare2 = false; show_rare3 = false; show_rare4 = false; show_rare5 = true;break;}
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter role_aa = new ArrayAdapter(context,R.layout.spinner_item,roleList);
                role_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_2048);

                role_spinner.setAdapter(role_aa);
                role_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0: {show_dps = false; show_sub_dps = false; show_util = false;break;}
                            case 1: {show_dps = true; show_sub_dps = false; show_util = false;break;}
                            case 2: {show_dps = false; show_sub_dps = true; show_util = false;break;}
                            case 3: {show_dps = false; show_sub_dps = true; show_util = true;break;}
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

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
                show_rare1 = sharedPreferences.getBoolean("show_rare1",false);
                show_rare2 = sharedPreferences.getBoolean("show_rare2",false);
                show_rare3 = sharedPreferences.getBoolean("show_rare3",false);
                show_rare4 = sharedPreferences.getBoolean("show_rare4",false);
                show_rare5 = sharedPreferences.getBoolean("show_rare5",false);
                show_released = sharedPreferences.getBoolean("show_released",false);
                show_unreleased = sharedPreferences.getBoolean("show_unreleased",false);
                show_dps = sharedPreferences.getBoolean("show_dps",false);
                show_sub_dps = sharedPreferences.getBoolean("show_sub_dps",false);
                show_util = sharedPreferences.getBoolean("show_util",false);

                if(show_pyro){show_pyro = true;pyro_kwang.setVisibility(View.VISIBLE);pyro_bg.setAlpha(1);}else{show_pyro = false;pyro_kwang.setVisibility(View.GONE);pyro_bg.setAlpha(0.5f);}
                if(show_hydro){show_hydro = true;hydro_kwang.setVisibility(View.VISIBLE);hydro_bg.setAlpha(1);}else{show_hydro = false;hydro_kwang.setVisibility(View.GONE);hydro_bg.setAlpha(0.5f);}
                if(show_anemo){show_anemo = true;anemo_kwang.setVisibility(View.VISIBLE);anemo_bg.setAlpha(1);}else{show_anemo = false;anemo_kwang.setVisibility(View.GONE);anemo_bg.setAlpha(0.5f);}
                if(show_electro){show_electro = true;electro_kwang.setVisibility(View.VISIBLE);electro_bg.setAlpha(1);}else{show_electro = false;electro_kwang.setVisibility(View.GONE);electro_bg.setAlpha(0.5f);}
                if(show_dendor){show_dendor = true;dendor_kwang.setVisibility(View.VISIBLE);dendor_bg.setAlpha(1);}else{show_dendor = false;dendor_kwang.setVisibility(View.GONE);dendor_bg.setAlpha(0.5f);}
                if(show_cryo){show_cryo = true;cryo_kwang.setVisibility(View.VISIBLE);cryo_bg.setAlpha(1);}else{show_cryo = false;cryo_kwang.setVisibility(View.GONE);cryo_bg.setAlpha(0.5f);}
                if(show_geo){show_geo = true;geo_kwang.setVisibility(View.VISIBLE);geo_bg.setAlpha(1);}else{show_geo = false;geo_kwang.setVisibility(View.GONE);geo_bg.setAlpha(0.5f);}
                if(show_sword){show_sword = true;ico_sword.setAlpha(1f);}else{show_sword = false;ico_sword.setAlpha(0.5f);}
                if(show_claymore){show_claymore = true;ico_claymore.setAlpha(1f);}else{show_claymore = false;ico_claymore.setAlpha(0.5f);}
                if(show_polearm){show_polearm = true;ico_polearm.setAlpha(1f);}else{show_polearm = false;ico_polearm.setAlpha(0.5f);}
                if(show_bow){show_bow = true;ico_bow.setAlpha(1f);}else{show_bow = false;ico_bow.setAlpha(0.5f);}
                if(show_catalyst){show_catalyst = true;ico_catalyst.setAlpha(1f);}else{show_catalyst = false;ico_catalyst.setAlpha(0.5f);}

                if (show_released){ menu_release_0.setChecked(true); }
                if (show_unreleased){ menu_release_1.setChecked(true); }

                pyro_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_pyro){show_pyro = false;pyro_kwang.setVisibility(View.GONE);pyro_bg.setAlpha(0.5f);}else{show_pyro = true;pyro_kwang.setVisibility(View.VISIBLE);pyro_bg.setAlpha(1);}}});
                hydro_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_hydro){show_hydro = false;hydro_kwang.setVisibility(View.GONE);hydro_bg.setAlpha(0.5f);}else{show_hydro = true;hydro_kwang.setVisibility(View.VISIBLE);hydro_bg.setAlpha(1);}}});
                anemo_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_anemo){show_anemo = false;anemo_kwang.setVisibility(View.GONE);anemo_bg.setAlpha(0.5f);}else{show_anemo = true;anemo_kwang.setVisibility(View.VISIBLE);anemo_bg.setAlpha(1);}}});
                electro_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_electro){show_electro = false;electro_kwang.setVisibility(View.GONE);electro_bg.setAlpha(0.5f);}else{show_electro = true;electro_kwang.setVisibility(View.VISIBLE);electro_bg.setAlpha(1);}}});
                dendor_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_dendor){show_dendor = false;dendor_kwang.setVisibility(View.GONE);dendor_bg.setAlpha(0.5f);}else{show_dendor = true;dendor_kwang.setVisibility(View.VISIBLE);dendor_bg.setAlpha(1);}}});
                cryo_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_cryo){show_cryo = false;cryo_kwang.setVisibility(View.GONE);cryo_bg.setAlpha(0.5f);}else{show_cryo = true;cryo_kwang.setVisibility(View.VISIBLE);cryo_bg.setAlpha(1);}}});
                geo_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_geo){show_geo = false;geo_kwang.setVisibility(View.GONE);geo_bg.setAlpha(0.5f);}else{show_geo = true;geo_kwang.setVisibility(View.VISIBLE);geo_bg.setAlpha(1);}}});
                    ico_sword.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_sword){show_sword = false;ico_sword.setAlpha(0.5f);}else{show_sword = true;ico_sword.setAlpha(1f);}}});
                    ico_claymore.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_claymore){show_claymore = false;ico_claymore.setAlpha(0.5f);}else{show_claymore = true;ico_claymore.setAlpha(1f);}}});
                    ico_polearm.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_polearm){show_polearm = false;ico_polearm.setAlpha(0.5f);}else{show_polearm = true;ico_polearm.setAlpha(1f);}}});
                    ico_bow.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_bow){show_bow = false;ico_bow.setAlpha(0.5f);}else{show_bow = true;ico_bow.setAlpha(1f);}}});
                    ico_catalyst.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_catalyst){show_catalyst = false;ico_catalyst.setAlpha(0.5f);}else{show_catalyst = true;ico_catalyst.setAlpha(1f);}}});

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                /*
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
                 */
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (menu_release_0.isChecked()){show_released = true;}else{show_released = false;}
                        if (menu_release_1.isChecked()){show_unreleased = true;}else{show_unreleased = false;}
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
                //dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_siptik));
                //dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_siptik));

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
        mArtifactList = viewPager3.findViewById(R.id.artifact_list);
        mArtifactAdapter = new ArtifactsAdapter(context,artifactsList,activity);
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
        LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsMsg.gravity = Gravity.CENTER;
        mArtifactList.setLayoutManager(mLayoutManager);
        mArtifactList.setLayoutParams(paramsMsg);
        mArtifactList.setAdapter(mArtifactAdapter);
        mArtifactList.removeAllViewsInLayout();
        artifact_list_reload();


                        /*
                        EditText artifacts_et = viewPager3.findViewById(R.id.char_et);
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
                                    if (css.getArtifactByName(item.getName(),context)[0].contains(str)||css.getArtifactByName(item.getName(),context)[0].toLowerCase().contains(str)||css.getArtifactByName(item.getName(),context)[0].toUpperCase().contains(str)||item.getName().toLowerCase().contains(str)){ // EN -> ZH
                                        filteredList.add(item);
                                    }
                                    x = x +1;
                                }
                                mArtifactAdapter.filterList(filteredList);
                            }
                        });

                         */

        ImageView artifact_search = viewPager3.findViewById(R.id.artifact_search);
        artifact_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View header_search = viewPager3.findViewById(R.id.header_search);
                EditText header_search_et = viewPager3.findViewById(R.id.header_search_et);
                Button menu_search_cancel = viewPager3.findViewById(R.id.menu_search_cancel);
                ImageView header_search_reset = viewPager3.findViewById(R.id.header_search_reset);

                header_search.animate()
                        .alpha(1.0f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                            }
                        });
                header_search.setVisibility(View.VISIBLE);

                header_search_et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (header_search_et.getText() != null){
                            String request = header_search_et.getText().toString();
                            if (!request.equals("")){
                                ArrayList<Artifacts> filteredList = new ArrayList<>();
                                int x = 0;
                                for (Artifacts item : artifactsList) {
                                    String str = request.toLowerCase();
                                    if (css.getArtifactByName(item.getName(),context)[0].contains(str)||css.getArtifactByName(item.getName(),context)[0].toLowerCase().contains(str)||css.getArtifactByName(item.getName(),context)[0].toUpperCase().contains(str)||item.getName().toLowerCase().contains(str)){ // EN -> ZH
                                        filteredList.add(item);
                                    }
                                    x = x +1;
                                }
                                mArtifactAdapter.filterList(filteredList);
                            }else{
                                mArtifactAdapter.filterList(artifactsList);
                            }
                        }else{
                            mArtifactAdapter.filterList(artifactsList);
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

            };
        });

        ImageView artifact_filter = viewPager3.findViewById(R.id.artifact_filter);
        artifact_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.menu_char_filter_siptik, null);
                // Elements
                ImageView pyro_kwang = view.findViewById(R.id.pyro_kwang);
                ImageView hydro_kwang = view.findViewById(R.id.hydro_kwang);
                ImageView anemo_kwang = view.findViewById(R.id.anemo_kwang);
                ImageView electro_kwang = view.findViewById(R.id.electro_kwang);
                ImageView dendor_kwang = view.findViewById(R.id.dendor_kwang);
                ImageView cryo_kwang = view.findViewById(R.id.cryo_kwang);
                ImageView geo_kwang = view.findViewById(R.id.geo_kwang);

                CardView pyro_bg = view.findViewById(R.id.pyro_bg);
                CardView hydro_bg = view.findViewById(R.id.hydro_bg);
                CardView anemo_bg = view.findViewById(R.id.anemo_bg);
                CardView electro_bg = view.findViewById(R.id.electro_bg);
                CardView dendor_bg = view.findViewById(R.id.dendor_bg);
                CardView cryo_bg = view.findViewById(R.id.cryo_bg);
                CardView geo_bg = view.findViewById(R.id.geo_bg);
                // Weapons
                ImageView ico_sword = view.findViewById(R.id.ico_sword);
                ImageView ico_claymore = view.findViewById(R.id.ico_claymore);
                ImageView ico_polearm = view.findViewById(R.id.ico_polearm);
                ImageView ico_bow = view.findViewById(R.id.ico_bow);
                ImageView ico_catalyst = view.findViewById(R.id.ico_catalyst);
                // Role
                Spinner role_spinner = view.findViewById(R.id.role_spinner);
                // Rarity
                Spinner rare_spinner = view.findViewById(R.id.rare_spinner);
                // Release
                CheckBox menu_release_0 = view.findViewById(R.id.menu_release_0);
                CheckBox menu_release_1 = view.findViewById(R.id.menu_release_1);

                // Function Buttons
                FrameLayout cancel = view.findViewById(R.id.menu_cancel);
                FrameLayout ok = view.findViewById(R.id.menu_ok);

                TextView menu_elements_title_tv = view.findViewById(R.id.menu_elements_title_tv);
                LinearLayout menu_elements_ll = view.findViewById(R.id.menu_elements_ll);
                TextView menu_weapons_title_tv = view.findViewById(R.id.menu_weapons_title_tv);
                LinearLayout menu_weapons_ll = view.findViewById(R.id.menu_weapons_ll);
                LinearLayout menu_role_ll = view.findViewById(R.id.menu_role_ll);

                menu_elements_title_tv.setVisibility(View.GONE);
                menu_elements_ll.setVisibility(View.GONE);
                menu_weapons_title_tv.setVisibility(View.GONE);
                menu_weapons_ll.setVisibility(View.GONE);
                menu_role_ll.setVisibility(View.GONE);

                if (show_rare1){role_spinner.setSelection(1);}
                if (show_rare2){role_spinner.setSelection(2);}
                if (show_rare3){role_spinner.setSelection(3);}
                if (show_rare4){role_spinner.setSelection(4);}
                if (show_rare5){role_spinner.setSelection(5);}

                if (show_dps){ rare_spinner.setSelection(0); }
                if (show_sub_dps){rare_spinner.setSelection(1); }
                if (show_util){rare_spinner.setSelection(2);}


                String[] roleList = new String[]{"ALL",context.getString(R.string.main_dps),context.getString(R.string.support_dps),context.getString(R.string.utility)};
                String[] rareList = new String[]{"ALL","1","2","3","4","5"};

                ArrayAdapter rare_aa = new ArrayAdapter(context,R.layout.spinner_item,rareList);
                rare_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_2048);

                rare_spinner.setAdapter(rare_aa);
                rare_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0: {show_rare1 = false; show_rare2 = false; show_rare3 = false; show_rare4 = false; show_rare5 = false;break;}
                            case 1: {show_rare1 = true; show_rare2 = false; show_rare3 = false; show_rare4 = false; show_rare5 = false;break;}
                            case 2: {show_rare1 = false; show_rare2 = true; show_rare3 = false; show_rare4 = false; show_rare5 = false;break;}
                            case 3: {show_rare1 = false; show_rare2 = false; show_rare3 = true; show_rare4 = false; show_rare5 = false;break;}
                            case 4: {show_rare1 = false; show_rare2 = false; show_rare3 = false; show_rare4 = true; show_rare5 = false;break;}
                            case 5: {show_rare1 = false; show_rare2 = false; show_rare3 = false; show_rare4 = false; show_rare5 = true;break;}
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter role_aa = new ArrayAdapter(context,R.layout.spinner_item,roleList);
                role_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_2048);

                role_spinner.setAdapter(role_aa);
                role_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0: {show_dps = false; show_sub_dps = false; show_util = false;break;}
                            case 1: {show_dps = true; show_sub_dps = false; show_util = false;break;}
                            case 2: {show_dps = false; show_sub_dps = true; show_util = false;break;}
                            case 3: {show_dps = false; show_sub_dps = true; show_util = true;break;}
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

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
                show_rare1 = sharedPreferences.getBoolean("show_rare1",false);
                show_rare2 = sharedPreferences.getBoolean("show_rare2",false);
                show_rare3 = sharedPreferences.getBoolean("show_rare3",false);
                show_rare4 = sharedPreferences.getBoolean("show_rare4",false);
                show_rare5 = sharedPreferences.getBoolean("show_rare5",false);
                show_released = sharedPreferences.getBoolean("show_released",false);
                show_unreleased = sharedPreferences.getBoolean("show_unreleased",false);
                show_dps = sharedPreferences.getBoolean("show_dps",false);
                show_sub_dps = sharedPreferences.getBoolean("show_sub_dps",false);
                show_util = sharedPreferences.getBoolean("show_util",false);

                if(show_pyro){show_pyro = true;pyro_kwang.setVisibility(View.VISIBLE);pyro_bg.setAlpha(1);}else{show_pyro = false;pyro_kwang.setVisibility(View.GONE);pyro_bg.setAlpha(0.5f);}
                if(show_hydro){show_hydro = true;hydro_kwang.setVisibility(View.VISIBLE);hydro_bg.setAlpha(1);}else{show_hydro = false;hydro_kwang.setVisibility(View.GONE);hydro_bg.setAlpha(0.5f);}
                if(show_anemo){show_anemo = true;anemo_kwang.setVisibility(View.VISIBLE);anemo_bg.setAlpha(1);}else{show_anemo = false;anemo_kwang.setVisibility(View.GONE);anemo_bg.setAlpha(0.5f);}
                if(show_electro){show_electro = true;electro_kwang.setVisibility(View.VISIBLE);electro_bg.setAlpha(1);}else{show_electro = false;electro_kwang.setVisibility(View.GONE);electro_bg.setAlpha(0.5f);}
                if(show_dendor){show_dendor = true;dendor_kwang.setVisibility(View.VISIBLE);dendor_bg.setAlpha(1);}else{show_dendor = false;dendor_kwang.setVisibility(View.GONE);dendor_bg.setAlpha(0.5f);}
                if(show_cryo){show_cryo = true;cryo_kwang.setVisibility(View.VISIBLE);cryo_bg.setAlpha(1);}else{show_cryo = false;cryo_kwang.setVisibility(View.GONE);cryo_bg.setAlpha(0.5f);}
                if(show_geo){show_geo = true;geo_kwang.setVisibility(View.VISIBLE);geo_bg.setAlpha(1);}else{show_geo = false;geo_kwang.setVisibility(View.GONE);geo_bg.setAlpha(0.5f);}
                if(show_sword){show_sword = true;ico_sword.setAlpha(1f);}else{show_sword = false;ico_sword.setAlpha(0.5f);}
                if(show_claymore){show_claymore = true;ico_claymore.setAlpha(1f);}else{show_claymore = false;ico_claymore.setAlpha(0.5f);}
                if(show_polearm){show_polearm = true;ico_polearm.setAlpha(1f);}else{show_polearm = false;ico_polearm.setAlpha(0.5f);}
                if(show_bow){show_bow = true;ico_bow.setAlpha(1f);}else{show_bow = false;ico_bow.setAlpha(0.5f);}
                if(show_catalyst){show_catalyst = true;ico_catalyst.setAlpha(1f);}else{show_catalyst = false;ico_catalyst.setAlpha(0.5f);}

                if (show_released){ menu_release_0.setChecked(true); }
                if (show_unreleased){ menu_release_1.setChecked(true); }

                pyro_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_pyro){show_pyro = false;pyro_kwang.setVisibility(View.GONE);pyro_bg.setAlpha(0.5f);}else{show_pyro = true;pyro_kwang.setVisibility(View.VISIBLE);pyro_bg.setAlpha(1);}}});
                hydro_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_hydro){show_hydro = false;hydro_kwang.setVisibility(View.GONE);hydro_bg.setAlpha(0.5f);}else{show_hydro = true;hydro_kwang.setVisibility(View.VISIBLE);hydro_bg.setAlpha(1);}}});
                anemo_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_anemo){show_anemo = false;anemo_kwang.setVisibility(View.GONE);anemo_bg.setAlpha(0.5f);}else{show_anemo = true;anemo_kwang.setVisibility(View.VISIBLE);anemo_bg.setAlpha(1);}}});
                electro_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_electro){show_electro = false;electro_kwang.setVisibility(View.GONE);electro_bg.setAlpha(0.5f);}else{show_electro = true;electro_kwang.setVisibility(View.VISIBLE);electro_bg.setAlpha(1);}}});
                dendor_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_dendor){show_dendor = false;dendor_kwang.setVisibility(View.GONE);dendor_bg.setAlpha(0.5f);}else{show_dendor = true;dendor_kwang.setVisibility(View.VISIBLE);dendor_bg.setAlpha(1);}}});
                cryo_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_cryo){show_cryo = false;cryo_kwang.setVisibility(View.GONE);cryo_bg.setAlpha(0.5f);}else{show_cryo = true;cryo_kwang.setVisibility(View.VISIBLE);cryo_bg.setAlpha(1);}}});
                geo_bg.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_geo){show_geo = false;geo_kwang.setVisibility(View.GONE);geo_bg.setAlpha(0.5f);}else{show_geo = true;geo_kwang.setVisibility(View.VISIBLE);geo_bg.setAlpha(1);}}});
                ico_sword.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_sword){show_sword = false;ico_sword.setAlpha(0.5f);}else{show_sword = true;ico_sword.setAlpha(1f);}}});
                ico_claymore.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_claymore){show_claymore = false;ico_claymore.setAlpha(0.5f);}else{show_claymore = true;ico_claymore.setAlpha(1f);}}});
                ico_polearm.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_polearm){show_polearm = false;ico_polearm.setAlpha(0.5f);}else{show_polearm = true;ico_polearm.setAlpha(1f);}}});
                ico_bow.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_bow){show_bow = false;ico_bow.setAlpha(0.5f);}else{show_bow = true;ico_bow.setAlpha(1f);}}});
                ico_catalyst.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_catalyst){show_catalyst = false;ico_catalyst.setAlpha(0.5f);}else{show_catalyst = true;ico_catalyst.setAlpha(1f);}}});

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                /*
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
                 */
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (menu_release_0.isChecked()){show_released = true;}else{show_released = false;}
                        if (menu_release_1.isChecked()){show_unreleased = true;}else{show_unreleased = false;}
                        filterArtifactAlgothm();
                        dialog.dismiss();
                    }
                });

                dialog.setContentView(view);
                dialog.setCanceledOnTouchOutside(true);
                //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                // 2O48 DESIGN
                //dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_siptik));
                //dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_siptik));

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

        ImageView discord_ico = viewPager4.findViewById(R.id.discord_ico);
        discord_ico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://discord.gg/uXatcbWKv2"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        // Navigation Bar
        TabLayout desk_tablayout_hover = viewPager4.findViewById(R.id.desk_tablayout_hover);
        TabLayout desk_tablayout_solid = viewPager4.findViewById(R.id.desk_tablayout_solid);
        RadioButton navigation_hover = viewPager4.findViewById(R.id.navigation_hover);
        RadioButton navigation_solid = viewPager4.findViewById(R.id.navigation_solid);

        if (sharedPreferences.getString("SipTikNavi","Hover").equals("Hover")){
            navigation_hover.setChecked(true);
            navigation_solid.setChecked(false);
        }else if (sharedPreferences.getString("SipTikNavi","Hover").equals("Solid")){
            navigation_hover.setChecked(false);
            navigation_solid.setChecked(true);
        }

        for (int x = 0 ; x < 5 ; x++){
            View view1 = getLayoutInflater().inflate(R.layout.item_custom_tab, null);
            ImageView ico_img = view1.findViewById(R.id.icon);
            TextView name = view1.findViewById(R.id.name);
            ico_img.setImageResource(tabItemImageArray[x]);
            name.setText(context.getString(tabItemNameArray[x]));
            name.setVisibility(View.VISIBLE);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int pix = displayMetrics.widthPixels;
            if (displayMetrics.heightPixels < displayMetrics.widthPixels){
                pix = displayMetrics.heightPixels;
            }

            if ((int) (((pix-32-5*8)/10)*1.2-16) <= 200){
                ico_img.setMaxHeight((int) ((pix-32-5*8)/10*1.2-16));
            }else{
                ico_img.setMaxHeight(200);
            }

            LinearLayout ll = view1.findViewById(R.id.ll);
            desk_tablayout_hover.setMinimumHeight(ll.getMeasuredHeight());

            desk_tablayout_hover.addTab(desk_tablayout_hover.newTab().setCustomView(view1).setId(x));
            desk_tablayout_hover.setTabIndicatorFullWidth(false);
        }


        for (int x = 0 ; x < 5 ; x++){
            View view1 = getLayoutInflater().inflate(R.layout.item_custom_tab, null);
            ImageView ico_img = view1.findViewById(R.id.icon);
            TextView name = view1.findViewById(R.id.name);
            ico_img.setImageResource(tabItemImageArray[x]);
            name.setText(context.getString(tabItemNameArray[x]));
            name.setVisibility(View.VISIBLE);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int pix = displayMetrics.widthPixels;
            if (displayMetrics.heightPixels < displayMetrics.widthPixels){
                pix = displayMetrics.heightPixels;
            }

            if ((int) (((pix-32-5*8)/10)*1.2-16) <= 200){
                ico_img.setMaxHeight((int) ((pix-32-5*8)/10*1.2-16));
            }else{
                ico_img.setMaxHeight(200);
            }

            LinearLayout ll = view1.findViewById(R.id.ll);
            desk_tablayout_solid.setMinimumHeight(ll.getMeasuredHeight());

            desk_tablayout_solid.addTab(desk_tablayout_solid.newTab().setCustomView(view1).setId(x));
            desk_tablayout_solid.setTabIndicatorFullWidth(false);
        }

        navigation_hover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigation_hover.setChecked(true);
                navigation_solid.setChecked(false);

                editor.putString("SipTikNavi","Hover");
                editor.apply();

                desk_tablayout.setBackgroundResource(R.drawable.bg_siptik_tab);
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) desk_tablayout.getLayoutParams();
                layoutParams.setMargins(8,8,8,8);
                layoutParams.verticalBias = 1f;
                desk_tablayout.setLayoutParams(layoutParams);
            }
        });

        navigation_solid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigation_hover.setChecked(false);
                navigation_solid.setChecked(true);

                editor.putString("SipTikNavi","Solid");
                editor.apply();

                desk_tablayout.setBackgroundResource(R.drawable.bg_siptik_tab_choice2);
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) desk_tablayout.getLayoutParams();
                layoutParams.setMargins(0,0,0,0);
                layoutParams.verticalBias = 1f;
                desk_tablayout.setLayoutParams(layoutParams);
            }
        });

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
                editor.putString("pathName","N/A");
                editor.apply();
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
                AlertDialog.Builder dialog = new AlertDialog.Builder(DeskSipTik.this,R.style.AlertDialogCustom);
                dialog.setCancelable(false);
                dialog.setTitle(context.getString(R.string.update_download_update_base));
                dialog.setMessage(context.getString(R.string.update_download_advice)+"\n"+context.getString(R.string.update_download_base_file_size)+" "+prettyByteCount(getRemoteFileSize("http://113.254.213.196/genshin_spirit/base.zip")));
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
                        downloadTask.start("http://113.254.213.196/genshin_spirit/base.zip","base.zip","/base.zip",context,activity);
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
                char_reload(dow);
                weapon_reload(dow);
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

        /*
        //Other -> EasterEgg_LYS
        ImageView app_ico = viewPager4.findViewById(R.id.app_ico);

        app_ico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EasterEgg_LYS.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
         */

        //Other -> Style
        style_Voc_rb = viewPager4.findViewById(R.id.ui_Voc_rb);
        style_2O48_rb = viewPager4.findViewById(R.id.ui_2O48_rb);
        style_SipTik_rb = viewPager4.findViewById(R.id.ui_SipTik_rb);
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
                startActivity(new Intent(context, Desk2048.class));
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
                startActivity(new Intent(context,DeskSipTik.class));
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
                grid_5_rb.setChecked(false);

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
                grid_5_rb.setChecked(false);

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
                grid_5_rb.setChecked(false);

                setup_home();
                setup_char();
                setup_weapon();
                setup_art();
            }
        });
        grid_5_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("curr_ui_grid","5");
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
                new AlertDialog.Builder(DeskSipTik.this,R.style.MyDialogTheme);
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

        LinearLayout daily_login_ll = viewPager0.findViewById(R.id.daily_login_ll);
        LinearLayout map_ll = viewPager0.findViewById(R.id.map_ll);
        LinearLayout calculator_ll = viewPager0.findViewById(R.id.calculator_ll);
        LinearLayout alarm_ll = viewPager0.findViewById(R.id.alarm_ll);

        calculator_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DeskSipTik.this, CalculatorDBActivity.class);
                startActivity(i);
            }
        });


        alarm_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, AlarmUI.class);
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
            }
        });

        weekdayList = new String[]{getString(R.string.sunday),getString(R.string.monday),getString(R.string.tuesday),getString(R.string.wednesday),getString(R.string.thursday),getString(R.string.friday),getString(R.string.saturday)};
        ArrayAdapter lang_aa = new ArrayAdapter(context,R.layout.spinner_item_2048,weekdayList);
        lang_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_2048);

        Spinner lang_sp = viewPager0.findViewById(R.id.home_weekday_spinner);
        lang_sp.setAdapter(lang_aa);

        Calendar c = Calendar.getInstance();
        lang_sp.setSelection(c.get(Calendar.DAY_OF_WEEK)-1);
        lang_sp.setDropDownWidth(lang_sp.getLayoutParams().width);
        lang_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // https://blog.csdn.net/pigdreams/article/details/81277110
                // https://stackoverflow.com/questions/13397933/android-spinner-avoid-onitemselected-calls-during-initialization
                char_reload(position+1);
                weapon_reload(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    };

    private void char_list_reload() {
        Log.wtf("DAAM","YEE");
        String name ,element,weapon,nation,sex,mainStat,role;
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


    public void showCharDetail (String name){

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

    }

    public void bday_reload(){
        String char_name = "EMPTY";
        Log.w("MOY",String.valueOf(moy));
        Log.w("DOM",String.valueOf(dom));

        char_name = css.char_birth(moy,dom);

        // Setting
        CardView birth_card = viewPager0.findViewById(R.id.birth_card);
        LinearLayout birth_celebrate = viewPager0.findViewById(R.id.birth_celebrate);
        ImageView birth_char = viewPager0.findViewById(R.id.birth_char);
        TextView birth_title_char = viewPager0.findViewById(R.id.birth_title_char);
        TextView birth_title_tv = viewPager0.findViewById(R.id.birth_title_tv);
        birth_card.setVisibility(View.VISIBLE);
        birth_title_tv.setVisibility(View.VISIBLE);

        // Big Icon
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int pix = (int) ((displayMetrics.widthPixels-16)/6-8);

        final int radius = 180;
        final int margin = 4;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);

        if(!char_name.equals("EMPTY")){
            Picasso.get()
                    .load (FileLoader.loadIMG(css.getCharByName(char_name,context)[3],context))
                    .transform(transformation)
                    .resize((int) (pix*1.2), (int) (pix*1.2))
                    .error (R.drawable.paimon_lost)
                    .into (birth_char);

            birth_char.getLayoutParams().width = (int) (pix*1.2);
            birth_char.getLayoutParams().height = (int) (pix*1.2);
            birth_title_char.setText(css.getCharByName(char_name,context)[1]);
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

            ImageView img = viewPager0.findViewById(imageArray[x]);
            TextView tv = viewPager0.findViewById(tvArray[x]);
            Picasso.get()
                    .load (FileLoader.loadIMG(css.getCharByName(nextBirthCharName,context)[3],context))
                    .transform(transformation)
                    .resize((int) (pix*2), (int) (pix*2))
                    .error (R.drawable.paimon_lost)
                    .into (img);

            tv.setText(css.getLocaleBirth(String.valueOf(nextBirthCharMonth+1)+"/"+String.valueOf(nextBirthCharDay),context));

            img.getLayoutParams().width = pix;
            img.getLayoutParams().height = pix;
        }
    }

    public void char_reload(int dow){
        LinearLayout char_ll = viewPager0.findViewById(R.id.char_ll);
        char_ll.removeAllViews();
        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(60, 0, RoundedCornersTransformation.CornerType.ALL);

        int[] today_IMG = tm.today_char_IMG(dow);
        int[] today_TV = tm.today_char_TV(dow);
        int[] today_Location = tm.today_char_location(dow);

        for (int x = 0;  x < today_IMG.length ; x++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_asc_require_card_siptik, char_ll, false);
            ImageView asc_material_ico = view.findViewById(R.id.asc_material_ico);
            ImageView asc_material_tick = view.findViewById(R.id.asc_material_tick);
            TextView asc_material_tv = view.findViewById(R.id.asc_material_tv);
            TextView asc_material_location = view.findViewById(R.id.asc_material_location);
            CardView asc_card = view.findViewById(R.id.asc_card);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int pix = displayMetrics.widthPixels-16;

            //Set tv and img
            asc_material_ico.getLayoutParams().width = (int) (asc_material_ico.getLayoutParams().width*0.8);
            asc_material_ico.getLayoutParams().height = (int) (asc_material_ico.getLayoutParams().height*0.8);
            asc_material_tv.setText(getString(today_TV[x]));
            Picasso.get().load(today_IMG[x]).into(asc_material_ico);
            asc_material_location.setText(getString(today_Location[x]));

            int[] asc_char_ico = new int[]{R.id.asc_char_ico1,R.id.asc_char_ico2,R.id.asc_char_ico3,R.id.asc_char_ico4,R.id.asc_char_ico5,R.id.asc_char_ico6,R.id.asc_char_ico7,R.id.asc_char_ico8,R.id.asc_char_ico9,R.id.asc_char_ico10,R.id.asc_char_ico11,R.id.asc_char_ico12,R.id.asc_char_ico13,R.id.asc_char_ico14,R.id.asc_char_ico15,R.id.asc_char_ico16,R.id.asc_char_ico17,R.id.asc_char_ico18,R.id.asc_char_ico19,R.id.asc_char_ico20,R.id.asc_char_ico21,R.id.asc_char_ico22,R.id.asc_char_ico23,R.id.asc_char_ico24,R.id.asc_char_ico25,R.id.asc_char_ico26,R.id.asc_char_ico27,R.id.asc_char_ico28,R.id.asc_char_ico29,R.id.asc_char_ico30,R.id.asc_char_ico31,R.id.asc_char_ico32,R.id.asc_char_ico33,R.id.asc_char_ico34,R.id.asc_char_ico35,R.id.asc_char_ico36,R.id.asc_char_ico37,R.id.asc_char_ico38,R.id.asc_char_ico39,R.id.asc_char_ico40,R.id.asc_char_ico41,R.id.asc_char_ico42,R.id.asc_char_ico43,R.id.asc_char_ico44,R.id.asc_char_ico45};
            int[] asc_char_tick = new int[]{R.id.asc_char_tick1,R.id.asc_char_tick2,R.id.asc_char_tick3,R.id.asc_char_tick4,R.id.asc_char_tick5,R.id.asc_char_tick6,R.id.asc_char_tick7,R.id.asc_char_tick8,R.id.asc_char_tick9,R.id.asc_char_tick10,R.id.asc_char_tick11,R.id.asc_char_tick12,R.id.asc_char_tick13,R.id.asc_char_tick14,R.id.asc_char_tick15,R.id.asc_char_tick16,R.id.asc_char_tick17,R.id.asc_char_tick18,R.id.asc_char_tick19,R.id.asc_char_tick20,R.id.asc_char_tick21,R.id.asc_char_tick22,R.id.asc_char_tick23,R.id.asc_char_tick24,R.id.asc_char_tick25,R.id.asc_char_tick26,R.id.asc_char_tick27,R.id.asc_char_tick28,R.id.asc_char_tick29,R.id.asc_char_tick30,R.id.asc_char_tick31,R.id.asc_char_tick32,R.id.asc_char_tick33,R.id.asc_char_tick34,R.id.asc_char_tick35,R.id.asc_char_tick36,R.id.asc_char_tick37,R.id.asc_char_tick38,R.id.asc_char_tick39,R.id.asc_char_tick40,R.id.asc_char_tick41,R.id.asc_char_tick42,R.id.asc_char_tick43,R.id.asc_char_tick44,R.id.asc_char_tick45};
            int[] asc_char_ll = new int[]{R.id.asc_char_ll1,R.id.asc_char_ll2,R.id.asc_char_ll3,R.id.asc_char_ll4,R.id.asc_char_ll5,R.id.asc_char_ll6,R.id.asc_char_ll7,R.id.asc_char_ll8,R.id.asc_char_ll9,R.id.asc_char_ll10,R.id.asc_char_ll11,R.id.asc_char_ll12,R.id.asc_char_ll13,R.id.asc_char_ll14,R.id.asc_char_ll15,R.id.asc_char_ll16,R.id.asc_char_ll17,R.id.asc_char_ll18,R.id.asc_char_ll19,R.id.asc_char_ll20,R.id.asc_char_ll21,R.id.asc_char_ll22,R.id.asc_char_ll23,R.id.asc_char_ll24,R.id.asc_char_ll25,R.id.asc_char_ll26,R.id.asc_char_ll27,R.id.asc_char_ll28,R.id.asc_char_ll29,R.id.asc_char_ll30,R.id.asc_char_ll31,R.id.asc_char_ll32,R.id.asc_char_ll33,R.id.asc_char_ll34,R.id.asc_char_ll35,R.id.asc_char_ll36,R.id.asc_char_ll37,R.id.asc_char_ll38,R.id.asc_char_ll39,R.id.asc_char_ll40,R.id.asc_char_ll41,R.id.asc_char_ll42,R.id.asc_char_ll43,R.id.asc_char_ll44,R.id.asc_char_ll45};
            String json_base = LoadData("db/char/char_require_asc_skill.json");
            String name = null,book ;
            int rare;
            int tmp_cnt = 0;
            try {
                JSONArray array = new JSONArray(json_base);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    name = object.getString("name");
                    book = object.getString("book");
                    rare = object.getInt("rare");

                    String real_name = "XPR";

                    if (tm.findCharBookByZHName(book) == today_TV[x]){
                        FrameLayout ll = view.findViewById(asc_char_ll[tmp_cnt]);
                        ImageView tick = view.findViewById(asc_char_tick[tmp_cnt]);
                        ImageView img = view.findViewById(asc_char_ico[tmp_cnt]);
                        real_name = name;
                        ll.setVisibility(View.VISIBLE);

                        switch (rare){
                            case 1 : img.setBackgroundResource(R.drawable.bg_rare1_char_siptik);break;
                            case 2 : img.setBackgroundResource(R.drawable.bg_rare2_char_siptik);break;
                            case 3 : img.setBackgroundResource(R.drawable.bg_rare3_char_siptik);break;
                            case 4 : img.setBackgroundResource(R.drawable.bg_rare4_char_siptik);break;
                            case 5 : img.setBackgroundResource(R.drawable.bg_rare5_char_siptik);break;
                            default:  img.setBackgroundResource(R.drawable.bg_rare1_char_siptik);break;
                        }

                        Picasso.get().load(FileLoader.loadIMG(css.getCharByName(name,context)[3],context)).fit().transform(roundedCornersTransformation).into(img);
                        tmp_cnt = tmp_cnt +1;
                        // if character is exist in list
                        //tick.setVisibility(View.VISIBLE);
                        String finalReal_name = real_name;
                        String finalName = name;
                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Characters_Info_SipTik cif = new Characters_Info_SipTik();
                                Log.wtf("FINAL_NAME","RR"+finalReal_name);
                                cif.setup(finalName,context,activity);
                            }
                        });
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Setup later
            asc_material_tick.setVisibility(View.GONE);
            char_ll.addView(view);
        }
    }
    public void weapon_reload(int dow){
        LinearLayout weapon_ll = viewPager0.findViewById(R.id.weapon_ll);
        weapon_ll.removeAllViews();
        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(60, 0, RoundedCornersTransformation.CornerType.ALL);

        int[] today_IMG = tm.today_weapon_IMG(dow);
        int[] today_TV = tm.today_weapon_TV(dow);
        int[] today_Location = tm.today_weapon_location(dow);
        for (int x = 0;  x < today_IMG.length ; x++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_asc_require_card_siptik, weapon_ll, false);
            ImageView asc_material_ico = view.findViewById(R.id.asc_material_ico);
            ImageView asc_material_tick = view.findViewById(R.id.asc_material_tick);
            TextView asc_material_tv = view.findViewById(R.id.asc_material_tv);
            TextView asc_material_location = view.findViewById(R.id.asc_material_location);

            //Set tv and img
            asc_material_ico.getLayoutParams().width = (int) (asc_material_ico.getLayoutParams().width*0.8);
            asc_material_ico.getLayoutParams().height = (int) (asc_material_ico.getLayoutParams().height*0.8);
            asc_material_tv.setText(getString(today_TV[x]));
            Picasso.get().load(today_IMG[x]).into(asc_material_ico);
            asc_material_location.setText(getString(today_Location[x]));
            CardView asc_card = view.findViewById(R.id.asc_card);


            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int pix = displayMetrics.widthPixels-16;

            int[] asc_weapon_ico = new int[]{R.id.asc_char_ico1,R.id.asc_char_ico2,R.id.asc_char_ico3,R.id.asc_char_ico4,R.id.asc_char_ico5,R.id.asc_char_ico6,R.id.asc_char_ico7,R.id.asc_char_ico8,R.id.asc_char_ico9,R.id.asc_char_ico10,R.id.asc_char_ico11,R.id.asc_char_ico12,R.id.asc_char_ico13,R.id.asc_char_ico14,R.id.asc_char_ico15,R.id.asc_char_ico16,R.id.asc_char_ico17,R.id.asc_char_ico18,R.id.asc_char_ico19,R.id.asc_char_ico20,R.id.asc_char_ico21,R.id.asc_char_ico22,R.id.asc_char_ico23,R.id.asc_char_ico24,R.id.asc_char_ico25,R.id.asc_char_ico26,R.id.asc_char_ico27,R.id.asc_char_ico28,R.id.asc_char_ico29,R.id.asc_char_ico30,R.id.asc_char_ico31,R.id.asc_char_ico32,R.id.asc_char_ico33,R.id.asc_char_ico34,R.id.asc_char_ico35,R.id.asc_char_ico36,R.id.asc_char_ico37,R.id.asc_char_ico38,R.id.asc_char_ico39,R.id.asc_char_ico40,R.id.asc_char_ico41,R.id.asc_char_ico42,R.id.asc_char_ico43,R.id.asc_char_ico44,R.id.asc_char_ico45};
            int[] asc_weapon_tick = new int[]{R.id.asc_char_tick1,R.id.asc_char_tick2,R.id.asc_char_tick3,R.id.asc_char_tick4,R.id.asc_char_tick5,R.id.asc_char_tick6,R.id.asc_char_tick7,R.id.asc_char_tick8,R.id.asc_char_tick9,R.id.asc_char_tick10,R.id.asc_char_tick11,R.id.asc_char_tick12,R.id.asc_char_tick13,R.id.asc_char_tick14,R.id.asc_char_tick15,R.id.asc_char_tick16,R.id.asc_char_tick17,R.id.asc_char_tick18,R.id.asc_char_tick19,R.id.asc_char_tick20,R.id.asc_char_tick21,R.id.asc_char_tick22,R.id.asc_char_tick23,R.id.asc_char_tick24,R.id.asc_char_tick25,R.id.asc_char_tick26,R.id.asc_char_tick27,R.id.asc_char_tick28,R.id.asc_char_tick29,R.id.asc_char_tick30,R.id.asc_char_tick31,R.id.asc_char_tick32,R.id.asc_char_tick33,R.id.asc_char_tick34,R.id.asc_char_tick35,R.id.asc_char_tick36,R.id.asc_char_tick37,R.id.asc_char_tick38,R.id.asc_char_tick39,R.id.asc_char_tick40,R.id.asc_char_tick41,R.id.asc_char_tick42,R.id.asc_char_tick43,R.id.asc_char_tick44,R.id.asc_char_tick45};
            int[] asc_weapon_ll = new int[]{R.id.asc_char_ll1,R.id.asc_char_ll2,R.id.asc_char_ll3,R.id.asc_char_ll4,R.id.asc_char_ll5,R.id.asc_char_ll6,R.id.asc_char_ll7,R.id.asc_char_ll8,R.id.asc_char_ll9,R.id.asc_char_ll10,R.id.asc_char_ll11,R.id.asc_char_ll12,R.id.asc_char_ll13,R.id.asc_char_ll14,R.id.asc_char_ll15,R.id.asc_char_ll16,R.id.asc_char_ll17,R.id.asc_char_ll18,R.id.asc_char_ll19,R.id.asc_char_ll20,R.id.asc_char_ll21,R.id.asc_char_ll22,R.id.asc_char_ll23,R.id.asc_char_ll24,R.id.asc_char_ll25,R.id.asc_char_ll26,R.id.asc_char_ll27,R.id.asc_char_ll28,R.id.asc_char_ll29,R.id.asc_char_ll30,R.id.asc_char_ll31,R.id.asc_char_ll32,R.id.asc_char_ll33,R.id.asc_char_ll34,R.id.asc_char_ll35,R.id.asc_char_ll36,R.id.asc_char_ll37,R.id.asc_char_ll38,R.id.asc_char_ll39,R.id.asc_char_ll40,R.id.asc_char_ll41,R.id.asc_char_ll42,R.id.asc_char_ll43,R.id.asc_char_ll44,R.id.asc_char_ll45};
            int tmp_cnt = 0;
            String json_base = LoadData("db/weapons/weapon_require_asc.json");
            String name,copy1 ;
            int rare;
            try {
                JSONArray array = new JSONArray(json_base);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    name = object.getString("name");
                    copy1 = object.getString("copy1");
                    rare = object.getInt("rare");

                    if (tm.findWeaponMaterialByZHName(copy1) == today_TV[x]){
                        FrameLayout ll = view.findViewById(asc_weapon_ll[tmp_cnt]);
                        ImageView tick = view.findViewById(asc_weapon_tick[tmp_cnt]);
                        ImageView img = view.findViewById(asc_weapon_ico[tmp_cnt]);

                        Picasso.get().load(css.getWeaponByName(name,context)[1]).fit().into(img);

                        String finalName = name;
                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Weapon_Info_Siptik wif = new Weapon_Info_Siptik();
                                wif.setup(finalName,context,activity);
                            }
                        });
                        // if weaponacter is exist in list
                        //asc_weapon_tick.setVisibility(View.VISIBLE);

                        ll.setVisibility(View.VISIBLE);

                        switch (rare){
                            case 1 : img.setBackgroundResource(R.drawable.bg_rare1_char_siptik);break;
                            case 2 : img.setBackgroundResource(R.drawable.bg_rare2_char_siptik);break;
                            case 3 : img.setBackgroundResource(R.drawable.bg_rare3_char_siptik);break;
                            case 4 : img.setBackgroundResource(R.drawable.bg_rare4_char_siptik);break;
                            case 5 : img.setBackgroundResource(R.drawable.bg_rare5_char_siptik);break;
                            default:  img.setBackgroundResource(R.drawable.bg_rare1_char_siptik);break;
                        }

                        Picasso.get().load(FileLoader.loadIMG(css.getWeaponByName(name,context)[1],context)).fit().transform(roundedCornersTransformation).into(img);
                        tmp_cnt = tmp_cnt +1;
                        // if character is exist in list
                        //tick.setVisibility(View.VISIBLE);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Setup later
            asc_material_tick.setVisibility(View.GONE);

            weapon_ll.addView(view);
        }
    }

    public void startCharInfo (String name, Activity activity){
        Characters_Info_SipTik cif = new Characters_Info_SipTik();
        cif.setup(String.valueOf(name),context,activity);
    }
    public void startWeaponInfo (String name, Activity activity){
        Weapon_Info_Siptik cif = new Weapon_Info_Siptik();
        cif.setup(String.valueOf(name),context,activity);
    }
    public void startArtifactInfo (String name, Activity activity){
        Artifact_Info_SipTik aif = new Artifact_Info_SipTik();
        aif.setup(String.valueOf(name),context,activity);
    }
    public void runSipTikCal (Characters characters, Activity activity){
        Intent intent = new Intent(this, SipTikCal.class);
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

        // 2O48 DESIGN
        //window.setStatusBarColor(context.getColor(R.color.status_bar_siptik));
        //window.setNavigationBarColor(context.getColor(R.color.tab_bar_siptik));

        //Color.parseColor(color_hex)
        //getResources().getColor(R.color.idname);


        // R.layout.fragment_char
        //EditText char_et = viewPager1.findViewById(R.id.char_et);
        //char_et.setTextColor(Color.parseColor(color_hex));

        // R.layout.fragment_art

        // R.layout.fragment_home
        TextView daily_login_tv = viewPager0.findViewById(R.id.daily_login_tv);
        TextView map_tv = viewPager0.findViewById(R.id.map_tv);

        //colorGradient(calculator_tv,start_color,end_color,isColorGradient,color_hex);
        //colorGradient(daily_login_tv,start_color,end_color,isColorGradient,color_hex);
        //colorGradient(map_tv,start_color,end_color,isColorGradient,color_hex);
        //colorGradient(alarm_tv,start_color,end_color,isColorGradient,color_hex);

        // R.layout.fragment_weapon

        // R.layout.fragment_setting
        RadioButton theme_light = viewPager4.findViewById(R.id.theme_light);
        RadioButton theme_dark = viewPager4.findViewById(R.id.theme_dark);
        RadioButton theme_default = viewPager4.findViewById(R.id.theme_default);
        Button bg_changelog_btn = viewPager4.findViewById(R.id.bg_changelog_btn);

        theme_light.setButtonTintList(myList);
        theme_dark.setButtonTintList(myList);
        theme_default.setButtonTintList(myList);
        Switch other_exit_confirm = viewPager4.findViewById(R.id.other_exit_confirm);

        bg_changelog_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeLog.show(context,activity);
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
            startActivity(new Intent(DeskSipTik.this, BackgroundConfirmActivity.class));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        cbg();
        BackgroundReload.BackgroundReload(context,activity);

        getDOW();
        char_reload(dow);
        weapon_reload(dow);
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
            url = new URL(urlSTR);
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
        {
            OkHttpClient client = new OkHttpClient();
            String url = "http://113.254.213.196/genshin_spirit/update.json";
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
                        array_download.add("http://113.254.213.196/genshin_spirit/" + fileName);
                        array_fileName.add(fileName);
                        array_SfileName.add("/" + fileName);
                    }
                }
                if(array_download.size()>0){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(DeskSipTik.this,R.style.AlertDialogCustom);
                    dialog.setCancelable(false);
                    dialog.setTitle(context.getString(R.string.update_download_update_curr));
                    dialog.setMessage(context.getString(R.string.update_download_found_update)+context.getString(R.string.update_download_advice)+"\n"+context.getString(R.string.update_download_base_file_size)+" "+prettyByteCount(getRemoteFileSizeA(array_download)));
                    dialog.setNegativeButton(context.getString(R.string.update_download_later),new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            // TODO Auto-generated method stub
                            Log.wtf("NOTHING","NOTHING");
                        }

                    });
                    long finalLastUnix = lastUnix;
                    dialog.setPositiveButton(context.getString(R.string.update_download_now),new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            // TODO Auto-generated method stub
                            DownloadTask downloadTask = new DownloadTask();
                            downloadTask.startA(array_download,array_fileName,array_SfileName,context,activity);
                            editor.putLong("lastUpdateUnix", finalLastUnix);
                            editor.apply();
                        }

                    });
                    dialog.show();
                }else{
                    CustomToast.toast(context,this,context.getString(R.string.update_download_not_found_update));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

                if (isSingleRole == 1){
                    if(show_dps && !item.getRole().equals("Main_DPS") ){isAllTrue = false;}
                    if(show_sub_dps && !item.getRole().equals("Support_DPS") ){isAllTrue = false;}
                    if(show_util && !item.getRole().equals("Utility")){isAllTrue = false;}
                }else if ((show_dps == false && show_sub_dps == false && show_util == false) == false){
                    if(!show_dps && item.getRole().equals("Main_DPS") ){isAllTrue = false;}
                    if(!show_sub_dps && item.getRole().equals("Support_DPS") ){isAllTrue = false;}
                    if(!show_util && item.getRole().equals("Utility")){isAllTrue = false;}
                }

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

    public void filterWeaponAlgothm(){

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

    public void filterArtifactAlgothm(){
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
}