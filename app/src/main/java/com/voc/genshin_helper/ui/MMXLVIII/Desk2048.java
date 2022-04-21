package com.voc.genshin_helper.ui.MMXLVIII;

import static com.voc.genshin_helper.util.RoundedCornersTransformation.CornerType.ALL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

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
import android.text.PrecomputedText;
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
import com.voc.genshin_helper.ui.AlarmUI;
import com.voc.genshin_helper.ui.BackgroundConfirmActivity;
import com.voc.genshin_helper.ui.CalculatorDBActivity;
import com.voc.genshin_helper.ui.MainActivity;
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

public class Desk2048 extends AppCompatActivity {

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
    Switch other_char_suit ;

    String[] weekdayList ;
    String[] langList ;
    String[] serverList ;
    String[] gridList ;

    private ViewPager viewPager;
    private ArrayList<View> viewPager_List;
    GoSleep gs;

    Activity activity;

    View viewPager0, viewPager1, viewPager2, viewPager3, viewPager4;

    int[] tabItemImageArray = new int[]{R.drawable.ic_2048_tab_desk,R.drawable.ic_2048_tab_char,R.drawable.ic_2048_tab_weapon,R.drawable.ic_2048_tab_art,R.drawable.ic_2048_tab_toolbox};
    int[] tabItemImageSelectedArray = new int[]{R.drawable.ic_2048_tab_desk_selected,R.drawable.ic_2048_tab_char_selected,R.drawable.ic_2048_tab_weapon_selected,R.drawable.ic_2048_tab_art_selected,R.drawable.ic_2048_tab_toolbox_selected};
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

        setContentView(R.layout.activity_desk_2048);

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
        viewPager0 = mInflater.inflate(R.layout.fragment_home_2048, null,false);
        viewPager1 = mInflater.inflate(R.layout.fragment_char_2048, null,false);
        viewPager2 = mInflater.inflate(R.layout.fragment_weapon_2048, null,false);
        viewPager3 = mInflater.inflate(R.layout.fragment_art_2048, null,false);
        viewPager4 = mInflater.inflate(R.layout.fragment_setting_2048, null,false);

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
        TextView home_asc_tv = viewPager0.findViewById(R.id.home_asc_tv);
        ImageView home_switch_btn = viewPager0.findViewById(R.id.home_switch_btn);

        home_asc_tv.setText(context.getString(R.string.char_asc_mater));
        char_ll.setVisibility(View.VISIBLE);
        weapon_ll.setVisibility(View.GONE);
        home_switch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWeaponLLShow == false){
                    weapon_ll.setVisibility(View.VISIBLE);
                    char_ll.setVisibility(View.GONE);
                    isCharLLShow = false;
                    isWeaponLLShow = true;
                    home_asc_tv.setText(context.getString(R.string.weapon_asc_mater));
                }else if (isCharLLShow == false){
                    weapon_ll.setVisibility(View.GONE);
                    char_ll.setVisibility(View.VISIBLE);
                    isCharLLShow = true;
                    isWeaponLLShow = false;
                    home_asc_tv.setText(context.getString(R.string.char_asc_mater));
                }
            }
        });
        viewPager.setAdapter(new MyViewPagerAdapter(viewPager_List));

        for (int x = 0 ; x < 5 ; x++){
            View view1 = getLayoutInflater().inflate(R.layout.item_custom_tab, null);
            ImageView ico_img = view1.findViewById(R.id.icon);
            ico_img.setImageResource(tabItemImageArray[x]);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int pix = displayMetrics.widthPixels;
            if (displayMetrics.heightPixels < displayMetrics.widthPixels){
                pix = displayMetrics.heightPixels;
            }

            if ((int) (((pix-32-5^8)/10)*1.2) <= 200){
                ico_img.setMaxHeight((int) ((pix-32-5^8)/10*1.2));
            }else{
                ico_img.setMaxHeight(200);
            }
            desk_tablayout.addTab(desk_tablayout.newTab().setCustomView(view1).setId(x));
        }

        desk_tablayout.setTabIndicatorFullWidth(false);

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
                mLayoutManager = new GridLayoutManager(context,  width_w/960);
            }else{
                mLayoutManager = new GridLayoutManager(context,  1);
            }
        }
        LinearLayout.LayoutParams  paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
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
                final Dialog dialog = new Dialog(context,R.style.NormalDialogStyle_N);
                View view1 =  View.inflate(context,R.layout.item_search_et,null);

                EditText weapon_et = view1.findViewById(R.id.char_et);
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

                FrameLayout menu_ok = view1.findViewById(R.id.menu_ok);
                menu_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.setContentView(view1);
                dialog.setCanceledOnTouchOutside(true);
                //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();

                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int height = displayMetrics.heightPixels;
                int width = displayMetrics.widthPixels;

                lp.width = (int) (width*0.9);
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;
                dialogWindow.setAttributes(lp);
                dialog.show();
            }
        });

        ImageView weapon_filter = viewPager2.findViewById(R.id.weapon_filter);
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
                                if(ratingBar.getRating() != 0 && item.getRare() == ratingBar.getRating()){
                                    filteredList.add(item);
                                }else if (ratingBar.getRating() == 0){
                                    filteredList.add(item);
                                }
                            }else if(item.getWeapon().toLowerCase().equals("sword") && show_sword||item.getWeapon().toLowerCase().equals("claymore") && show_claymore||item.getWeapon().toLowerCase().equals("polearm") && show_polearm||item.getWeapon().toLowerCase().equals("bow") && show_bow||item.getWeapon().toLowerCase().equals("catalyst") && show_catalyst){
                                if(ratingBar.getRating() != 0 && item.getRare() == ratingBar.getRating()){
                                    filteredList.add(item);
                                }else if (ratingBar.getRating() == 0){
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
                mLayoutManager = new GridLayoutManager(context,  width/960);
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

        ImageView char_search = viewPager1.findViewById(R.id.char_search);
        char_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context,R.style.NormalDialogStyle_N);
                View view1 =  View.inflate(context,R.layout.item_search_et,null);

                EditText char_et = view1.findViewById(R.id.char_et);
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
                            if (css.getCharByName(item.getName(),context)[1].contains(str)||css.getCharByName(item.getName(),context)[1].toLowerCase().contains(str)||item.getName().toLowerCase().contains(str)){ // EN -> ZH
                                filteredList.add(item);
                            }
                            x = x +1;
                        }
                        mAdapter.filterList(filteredList);
                    }
                });

                FrameLayout menu_ok = view1.findViewById(R.id.menu_ok);
                menu_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.setContentView(view1);
                dialog.setCanceledOnTouchOutside(true);
                //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();

                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int height = displayMetrics.heightPixels;
                int width = displayMetrics.widthPixels;

                lp.width = (int) (width*0.9);
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;
                dialogWindow.setAttributes(lp);
                dialog.show();
            }
        });


        ImageView char_filter = viewPager1.findViewById(R.id.char_filter);
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

                        mAdapter.filterList(charactersList);

                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<Characters> filteredList = new ArrayList<>();
                        for (Characters item : charactersList) {
                            if((show_sword == false && show_claymore == false && show_catalyst == false && show_bow == false && show_polearm == false) &&
                                    (show_anemo == false  && show_cryo == false && show_dendor == false && show_electro == false && show_hydro == false && show_geo == false && show_pyro == false)){
                                if(ratingBar.getRating() != 0 && item.getRare() == ratingBar.getRating()){
                                    filteredList.add(item);
                                }else if (ratingBar.getRating() == 0){
                                    filteredList.add(item);
                                }
                            }else if ((item.getElement().toLowerCase().equals("pyro") && show_pyro||item.getElement().toLowerCase().equals("hydro") && show_hydro||item.getElement().toLowerCase().equals("anemo") && show_anemo||item.getElement().toLowerCase().equals("electro") && show_electro||item.getElement().toLowerCase().equals("dendor") && show_dendor||item.getElement().toLowerCase().equals("cryo") && show_cryo||item.getElement().toLowerCase().equals("geo") && show_geo)) {
                                if((show_sword == false && show_claymore == false && show_catalyst == false && show_bow == false && show_polearm == false)){
                                    if(ratingBar.getRating() != 0 && item.getRare() == ratingBar.getRating()){
                                        filteredList.add(item);
                                    }else if (ratingBar.getRating() == 0){
                                        filteredList.add(item);
                                    }
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
                mLayoutManager = new GridLayoutManager(context,  width_a/960);
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
                final Dialog dialog = new Dialog(context,R.style.NormalDialogStyle_N);
                View view1 =  View.inflate(context,R.layout.item_search_et,null);

                EditText artifact_et = view1.findViewById(R.id.char_et);
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

                FrameLayout menu_ok = view1.findViewById(R.id.menu_ok);
                menu_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.setContentView(view1);
                dialog.setCanceledOnTouchOutside(true);
                //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();

                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int height = displayMetrics.heightPixels;
                int width = displayMetrics.widthPixels;

                lp.width = (int) (width*0.9);
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;
                dialogWindow.setAttributes(lp);
                dialog.show();
            }
        });

        ImageView artifact_filter = viewPager3.findViewById(R.id.artifact_filter);
        artifact_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.menu_artifact_filter, null);
                // Rating
                RatingBar ratingBar = view.findViewById(R.id.menu_rating);
                // Function Buttons
                Button cancel = view.findViewById(R.id.menu_cancel);
                Button reset = view.findViewById(R.id.menu_reset);
                Button ok = view.findViewById(R.id.menu_ok);

                ratingBar.setNumStars(5);
                ratingBar.setRating(show_stars);

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

                        mArtifactList.removeAllViews();
                        mArtifactAdapter.filterList(filteredList);
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

        LinearLayout calculator_ll = viewPager4.findViewById(R.id.calculator_ll);
        LinearLayout daily_login_ll = viewPager4.findViewById(R.id.daily_login_ll);
        LinearLayout map_ll = viewPager4.findViewById(R.id.map_ll);
        LinearLayout alarm_ll = viewPager4.findViewById(R.id.alarm_ll);

        calculator_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Desk2048.this, CalculatorDBActivity.class);
                startActivity(i);
            }
        });
        daily_login_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://webstatic-sea.mihoyo.com/ys/event/signin-sea/index.html?act_id=e202102251931481"));
                startActivity(browserIntent);
            }
        });
        map_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://webstatic-sea.mihoyo.com/ys/event/signin-sea/index.html?act_id=e202102251931481"));
                startActivity(browserIntent);
            }
        });

        alarm_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, AlarmUI.class);
                startActivity(i);
            }
        });

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



        // Translate
        //langList = new String[]{getString(R.string.zh_hk),getString(R.string.zh_cn),getString(R.string.en_us),getString(R.string.ru_ru),getString(R.string.ja_jp),getString(R.string.fr_fr),getString(R.string.uk_ua)};
        langList = new String[]{getString(R.string.zh_hk),getString(R.string.zh_cn),getString(R.string.en_us),getString(R.string.ru_ru),getString(R.string.ja_jp),getString(R.string.fr_fr)};
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

        // List_Grid

        gridList = new String[]{context.getString(R.string.rectangle),context.getString(R.string.square),context.getString(R.string.card)};
        ArrayAdapter grid_aa = new ArrayAdapter(context,R.layout.spinner_item,gridList);
        grid_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);

        Spinner grid_sp = viewPager4.findViewById(R.id.ui_box_spinner);
        grid_sp.setAdapter(grid_aa);
        grid_sp.setSelection(sharedPreferences.getInt("curr_ui_grid_pos",0));
        grid_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // https://blog.csdn.net/pigdreams/article/details/81277110
                // https://stackoverflow.com/questions/13397933/android-spinner-avoid-onitemselected-calls-during-initialization
                if(check_spinner >0){
                    if(position == 0){
                        editor.putString("curr_ui_grid","2");
                        editor.putInt("curr_ui_grid_pos",position);
                        editor.apply();
                    }else if(position == 1){
                        editor.putString("curr_ui_grid","3");
                        editor.putInt("curr_ui_grid_pos",position);
                        editor.apply();
                    }else if(position == 2){
                        editor.putString("curr_ui_grid","4");
                        editor.putInt("curr_ui_grid_pos",position);
                        editor.apply();
                    }
                }
                check_spinner = check_spinner +1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Traveler Sex

        String[] travelerList = new String[]{css.getLocaleName("Female",context),css.getLocaleName("Male",context)};
        ArrayAdapter traveler_aa = new ArrayAdapter(context,R.layout.spinner_item,travelerList);
        traveler_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);

        Spinner traveler_sp = viewPager4.findViewById(R.id.traveler_spinner);
        traveler_sp.setAdapter(traveler_aa);
        switch (sharedPreferences.getString("traveler_sex","F")){
            case "F" : traveler_sp.setSelection(0);break;
            case "M" : traveler_sp.setSelection(1);break;
        }



        traveler_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // https://blog.csdn.net/pigdreams/article/details/81277110
                // https://stackoverflow.com/questions/13397933/android-spinner-avoid-onitemselected-calls-during-initialization
                if(check_spinner >0){
                    if(position == 0){
                        editor.putString("traveler_sex","F");
                        editor.apply();
                    }else if(position == 1){
                        editor.putString("traveler_sex","M");
                        editor.apply();
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
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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
                AlertDialog.Builder dialog = new AlertDialog.Builder(Desk2048.this,R.style.AlertDialogCustom);
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
                char_reload(dow);
                weapon_reload(dow);
                cbg();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Other -> Switchs
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
        //Other -> Change Suits
        other_char_suit = viewPager4.findViewById(R.id.other_char_suit);
        boolean isCharChangeEventSuit = sharedPreferences.getBoolean("isCharChangeEventSuit",false);
        other_char_suit.setChecked(isCharChangeEventSuit);
        other_char_suit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(other_char_suit.isChecked() == false){
                    editor.putBoolean("isCharChangeEventSuit",false);
                    editor.apply();
                }else if(other_char_suit.isChecked() == true){
                    editor.putBoolean("isCharChangeEventSuit",true);
                    editor.apply();
                }
            }
        });
    }

    private void showVoteDialog() {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(Desk2048.this,R.style.MyDialogTheme);
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

        TextView home_date_tv = viewPager0.findViewById(R.id.home_date_tv);
        if(dow == 1){home_date_tv.setText("【"+getString(R.string.sunday)+"】");}
        if(dow == 2){home_date_tv.setText("【"+getString(R.string.monday)+"】");}
        if(dow == 3){home_date_tv.setText("【"+getString(R.string.tuesday)+"】");}
        if(dow == 4){home_date_tv.setText("【"+getString(R.string.wednesday)+"】");}
        if(dow == 5){home_date_tv.setText("【"+getString(R.string.thursday)+"】");}
        if(dow == 6){home_date_tv.setText("【"+getString(R.string.friday)+"】");}
        if(dow == 7){home_date_tv.setText("【"+getString(R.string.saturday)+"】");}


        TextView home_f_date_tv = viewPager0.findViewById(R.id.home_f_date_tv);
        home_f_date_tv.setText(serverList[position]);

    }

    public void bday_reload(){
        String char_name = "EMPTY";
        Log.w("MOY",String.valueOf(moy));
        Log.w("DOM",String.valueOf(dom));

        char_name = css.char_birth(moy,dom);

        if(!char_name.equals("EMPTY")){
            // Setting
            CardView birth_card = viewPager0.findViewById(R.id.birth_card);
            ImageView birth_char = viewPager0.findViewById(R.id.birth_char);
            TextView birth_char_tv = viewPager0.findViewById(R.id.birth_char_tv);
            TextView birth_char_date = viewPager0.findViewById(R.id.birth_char_date);

            birth_card.setVisibility(View.VISIBLE);

            final int radius = 180;
            final int margin = 4;
            final Transformation transformation = new RoundedCornersTransformation(radius, margin);
            Picasso.get()
                    .load (FileLoader.loadIMG(css.getCharByName(char_name,context)[3],context))
                    .transform(transformation)
                    .error (R.drawable.paimon_lost)
                    .into (birth_char);
            birth_char_tv.setText(css.getCharByName(char_name,context)[1]);
            birth_char_date.setText(css.getLocaleBirth(String.valueOf(moy+1)+"/"+String.valueOf(dom),context));

        }
    }

    public void char_reload(int dow){
        LinearLayout char_ll = viewPager0.findViewById(R.id.char_ll);
        char_ll.removeAllViews();
        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(360, 0, RoundedCornersTransformation.CornerType.ALL);

        int[] today_IMG = tm.today_char_IMG(dow);
        int[] today_TV = tm.today_char_TV(dow);
        int[] today_Location = tm.today_char_location(dow);
        for (int x = 0;  x < today_IMG.length ; x++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_asc_require_card, char_ll, false);
            ImageView asc_material_ico = view.findViewById(R.id.asc_material_ico);
            ImageView asc_material_tick = view.findViewById(R.id.asc_material_tick);
            TextView asc_material_tv = view.findViewById(R.id.asc_material_tv);
            TextView asc_material_location = view.findViewById(R.id.asc_material_location);
            LinearLayout asc_material_char_ll = view.findViewById(R.id.asc_material_char_ll);

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
            String name,book ;
            int rare;
            int tmp_cnt = 0;
            try {
                JSONArray array = new JSONArray(json_base);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    name = object.getString("name");
                    book = object.getString("book");
                    rare = object.getInt("rare");

                    if (tm.findCharBookByZHName(book) == today_TV[x]){
                        FrameLayout ll = asc_material_char_ll.findViewById(asc_char_ll[tmp_cnt]);
                        ImageView tick = asc_material_char_ll.findViewById(asc_char_tick[tmp_cnt]);
                        ImageView img = asc_material_char_ll.findViewById(asc_char_ico[tmp_cnt]);

                        ll.setVisibility(View.VISIBLE);

                        switch (rare){
                            case 1 : img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                            case 2 : img.setBackgroundResource(R.drawable.item_char_list_bg_circ_2s);break;
                            case 3 : img.setBackgroundResource(R.drawable.item_char_list_bg_circ_3s);break;
                            case 4 : img.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);break;
                            case 5 : img.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);break;
                            default:  img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                        }

                        Picasso.get().load(FileLoader.loadIMG(css.getCharByName(name,context)[3],context)).fit().transform(roundedCornersTransformation).into(img);
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

            char_ll.addView(view);
        }
    }
    public void weapon_reload(int dow){
        LinearLayout weapon_ll = viewPager0.findViewById(R.id.weapon_ll);
        weapon_ll.removeAllViews();
        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(360, 0, RoundedCornersTransformation.CornerType.ALL);

        int[] today_IMG = tm.today_weapon_IMG(dow);
        int[] today_TV = tm.today_weapon_TV(dow);
        int[] today_Location = tm.today_weapon_location(dow);
        for (int x = 0;  x < today_IMG.length ; x++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_asc_require_card, weapon_ll, false);
            ImageView asc_material_ico = view.findViewById(R.id.asc_material_ico);
            ImageView asc_material_tick = view.findViewById(R.id.asc_material_tick);
            TextView asc_material_tv = view.findViewById(R.id.asc_material_tv);
            TextView asc_material_location = view.findViewById(R.id.asc_material_location);
            LinearLayout asc_material_weapon_ll = view.findViewById(R.id.asc_material_char_ll);

            //Set tv and img
            asc_material_ico.getLayoutParams().width = (int) (asc_material_ico.getLayoutParams().width*0.8);
            asc_material_ico.getLayoutParams().height = (int) (asc_material_ico.getLayoutParams().height*0.8);
            asc_material_tv.setText(getString(today_TV[x]));
            Picasso.get().load(today_IMG[x]).into(asc_material_ico);
            asc_material_location.setText(getString(today_Location[x]));

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
                        FrameLayout ll = asc_material_weapon_ll.findViewById(asc_weapon_ll[tmp_cnt]);
                        ImageView tick = asc_material_weapon_ll.findViewById(asc_weapon_tick[tmp_cnt]);
                        ImageView img = asc_material_weapon_ll.findViewById(asc_weapon_ico[tmp_cnt]);


                        Picasso.get().load(css.getWeaponByName(name,context)[1]).fit().into(img);
                        // if weaponacter is exist in list
                        //asc_weapon_tick.setVisibility(View.VISIBLE);

                        ll.setVisibility(View.VISIBLE);

                        switch (rare){
                            case 1 : img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                            case 2 : img.setBackgroundResource(R.drawable.item_char_list_bg_circ_2s);break;
                            case 3 : img.setBackgroundResource(R.drawable.item_char_list_bg_circ_3s);break;
                            case 4 : img.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);break;
                            case 5 : img.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);break;
                            default:  img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
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

    public void startInfo (String name, Activity activity){
        Characters_Info_2048 cif = new Characters_Info_2048();
        cif.setup(String.valueOf(name),context,activity);
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

        //Color.parseColor(color_hex)
        //getResources().getColor(R.color.idname);


        // R.layout.fragment_char
        //EditText char_et = viewPager1.findViewById(R.id.char_et);
        //char_et.setTextColor(Color.parseColor(color_hex));

        // R.layout.fragment_art

        // R.layout.fragment_home
        TextView home_f_date_tv = viewPager0.findViewById(R.id.home_f_date_tv);
        TextView home_title_tv = viewPager0.findViewById(R.id.home_title_tv);
        TextView home_date_tv = viewPager0.findViewById(R.id.home_date_tv);
        TextView char_tv = viewPager0.findViewById(R.id.char_tv);
        TextView weapon_tv = viewPager0.findViewById(R.id.weapon_tv);
        TextView tool_tv = viewPager0.findViewById(R.id.tool_tv);
        TextView calculator_tv = viewPager0.findViewById(R.id.calculator_tv);
        TextView daily_login_tv = viewPager0.findViewById(R.id.daily_login_tv);
        TextView map_tv = viewPager0.findViewById(R.id.map_tv);
        TextView alarm_tv = viewPager0.findViewById(R.id.alarm_tv);

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

        Switch other_exit_confirm = viewPager4.findViewById(R.id.other_exit_confirm);
        Switch other_char_suit = viewPager4.findViewById(R.id.other_char_suit);
        theme_light.setButtonTintList(myList);
        theme_dark.setButtonTintList(myList);
        theme_default.setButtonTintList(myList);

        other_exit_confirm.setThumbTintList(myList);
        other_exit_confirm.setTrackTintList(myList);
        other_char_suit.setThumbTintList(myList);
        other_char_suit.setTrackTintList(myList);

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
            startActivity(new Intent(Desk2048.this, BackgroundConfirmActivity.class));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        cbg();
        BackgroundReload.BackgroundReload(context,activity);

        getDOW();
        //char_reload(dow);
        //weapon_reload(dow);
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
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Desk2048.this,R.style.AlertDialogCustom);
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
}

