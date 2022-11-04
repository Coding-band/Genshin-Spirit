package com.voc.genshin_helper.ui.MMXLVIII;

import static com.voc.genshin_helper.util.RoundedCornersTransformation.CornerType.ALL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.BaseColumns;
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
import android.webkit.CookieManager;
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
import com.voc.genshin_helper.data.IconCard;
import com.voc.genshin_helper.data.IconCardAdapter;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.Today_Material;
import com.voc.genshin_helper.data.Weapons;
import com.voc.genshin_helper.data.WeaponsAdapter;
import com.voc.genshin_helper.database.DataBaseContract;
import com.voc.genshin_helper.database.DataBaseHelper;
import com.voc.genshin_helper.kidding.GoSleep;
import com.voc.genshin_helper.tutorial.TutorialUI;
import com.voc.genshin_helper.ui.AlarmUI;
import com.voc.genshin_helper.ui.BackgroundConfirmActivity;
import com.voc.genshin_helper.ui.MainActivity;
import com.voc.genshin_helper.util.BackgroundReload;
import com.voc.genshin_helper.util.ChangeLog;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.DailyMemo;
import com.voc.genshin_helper.util.Dialog2048;
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
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
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

    private boolean isReadPermissionGranted = false;
    private boolean isWritePermissionGranted = false;
    private static final int CAMERA = 100;
    private int CARD = 200;
    private int ICON = 201;

    String icon_name_final = "Klee";
    String card_name_final = "Klee";
    int icon_rare_final = 5;

    boolean isCharLLShow = true;
    boolean isWeaponLLShow = false;

    LinearLayout char_ll;
    LinearLayout weapon_ll;
    TabLayout desk_tablayout;
    ImageView desk_tablayout_bg;
    Today_Material tm;
    ItemRss css;
    //Char Page
    CharactersAdapter mAdapter;
    ArtifactsAdapter mArtifactAdapter;
    RecyclerView mArtifactList;
    RecyclerView mList;
    WeaponsAdapter mWeaponAdapter;
    RecyclerView mWeaponList;
    IconCardAdapter mIconCardAdapter;
    RecyclerView mIconCardList;

    LocaleHelper localeHelper;
    NumberPickerDialog npd;

    String uid_final = "N/A";
    String token_final = "N/A";
    String genshin_uid_final = "-1";

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
    public List<IconCard> iconCardList_ICON = new ArrayList();
    public List<IconCard> iconCardList_CARD = new ArrayList();
    public List<String> dbIsCalCharName = new ArrayList();
    public List<String> dbIsCalWeaponName = new ArrayList();

    boolean first = true;

    String lang = "en-US";

    RadioButton theme_light;
    RadioButton theme_night;
    RadioButton theme_default;

    Switch other_exit_confirm ;
    Switch other_item_eng_name ;
    Switch other_random_theme_confirm ;
    Switch other_app_ico_use_default ;

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

    WebView webView;

    String[] weekdayList ;
    String[] langList ;
    String[] serverList ;
    String[] gridList ;

    private ViewPager viewPager;
    private ArrayList<View> viewPager_List;
    GoSleep gs;
    ColorStateList myList;

    final int radius_circ = 360;
    final int margin_circ = 0;
    final Transformation transformation_circ = new RoundedCornersTransformation(radius_circ, margin_circ);

    final int radius_card = 24;
    final int margin_card = 0;
    final Transformation transformation_card = new RoundedCornersTransformation(radius_card, margin_card);

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
        if (sharedPreferences.getBoolean("theme_light", true) == true) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }else if (sharedPreferences.getBoolean("theme_night", false) == true) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else if (sharedPreferences.getBoolean("theme_default", false) == true) {
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
        desk_tablayout_bg = findViewById(R.id.desk_tablayout_bg);

        if (context.getString(R.string.mode).equals("Night")){
            desk_tablayout_bg.setImageResource(R.drawable.mask_navi_bg_night);
        }else{
            desk_tablayout_bg.setImageResource(R.drawable.mask_navi_bg_day);
        }

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
        viewPager4 = mInflater.inflate(R.layout.fragment_paimon_2048, null,false);

        viewPager_List = new ArrayList<View>();
        viewPager_List.add(viewPager0);
        viewPager_List.add(viewPager1);
        viewPager_List.add(viewPager2);
        viewPager_List.add(viewPager3);
        viewPager_List.add(viewPager4);

        check_spinner = 0;

        DailyMemo dailyMemo = new DailyMemo();
        dailyMemo.setup(context,activity,viewPager0);
        viewPager0.findViewById(R.id.home_dailymemo).setVisibility(View.VISIBLE);

        lang_setup();
        home();
        getDOW();
        bday_reload();
        cbg();
        dbChar_reload();
        char_reload(dow);
        weapon_reload(dow);
        setup_home();
        setup_char();
        setup_weapon();
        setup_art();
        setup_paimon();

        //viewPager4.findViewById(R.id.card_char_bg)


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
            TextView ico_tv = view1.findViewById(R.id.name);
            ico_tv.setVisibility(View.GONE);
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

            final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
            View view = View.inflate(context, R.layout.fragment_setting_2048_new, null);
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

            setup_setting(view,dialog);
            //setColorBk(view);

            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            lp.gravity = Gravity.CENTER;
            dialogWindow.setAttributes(lp);
            dialog.show();

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

    private void dbChar_reload() {
        dbIsCalCharName.clear();
        dbIsCalWeaponName.clear();
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Level 0

        String[] projection = {
                BaseColumns._ID,
                DataBaseContract.DataBase.COLUMN_NAME_NAME
        };
        String sortOrder =
                DataBaseContract.DataBase.COLUMN_NAME_CREATE_UNIX + " DESC";

        Cursor cursor = db.query(
                DataBaseContract.DataBase.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        while(cursor.moveToNext()) {
            // Level 1
            String[] projectionX = {"charName","charIsCal"};

            Cursor cursorX = db.query(
                    cursor.getString(cursor.getColumnIndexOrThrow("name"))+"_char",   // The table to query
                    projectionX,             // The array of columns to return (pass null to get all)
                    null,              // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );
            while (cursorX.moveToNext()){
                if(getBooleanByInt(cursorX.getInt(cursorX.getColumnIndexOrThrow("charIsCal"))) == true  && !dbIsCalWeaponName.contains(cursorX.getString(cursorX.getColumnIndexOrThrow("charName")))){
                    dbIsCalCharName.add(cursorX.getString(cursorX.getColumnIndexOrThrow("charName")));
                }
            }

            String[] projectionY = {"weaponName","weaponIsCal"};

            Cursor cursorY = db.query(
                    cursor.getString(cursor.getColumnIndexOrThrow("name"))+"_weapon",   // The table to query
                    projectionY,             // The array of columns to return (pass null to get all)
                    null,              // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );
            while (cursorY.moveToNext()){
                if(getBooleanByInt(cursorY.getInt(cursorY.getColumnIndexOrThrow("weaponIsCal"))) == true && !dbIsCalWeaponName.contains(cursorY.getString(cursorY.getColumnIndexOrThrow("weaponName")))){
                    dbIsCalWeaponName.add(cursorY.getString(cursorY.getColumnIndexOrThrow("weaponName")));
                }
            }
        }
    }

    private void setup_paimon() {
        ConstraintLayout paimon_cal = viewPager4.findViewById(R.id.paimon_cal);
        ConstraintLayout paimon_daily = viewPager4.findViewById(R.id.paimon_daily);
        ConstraintLayout paimon_map = viewPager4.findViewById(R.id.paimon_map);
        ConstraintLayout paimon_alarm = viewPager4.findViewById(R.id.paimon_alarm);
        ConstraintLayout paimon_setting = viewPager4.findViewById(R.id.paimon_setting);
        ConstraintLayout paimon_about = viewPager4.findViewById(R.id.paimon_about);

        final int radius = 360;
        final int margin = 0;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin,ALL);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width_curr = displayMetrics.widthPixels;

        int height = (int) (width_curr/2.1);


        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            height = (int) height/4;
        }

        Picasso.get()
                .load (FileLoader.loadIMG(css.getCharByName(sharedPreferences.getString("card_name","Klee"),context)[4],context))
                .resize(width_curr, (int) height)
                .error (R.drawable.paimon_lost)
                .into ((ImageView) viewPager4.findViewById(R.id.card_bg));

        ((ImageView) viewPager4.findViewById(R.id.card_bg)).setScaleType(ImageView.ScaleType.CENTER_CROP);

        Picasso.get()
                .load (FileLoader.loadIMG(css.getCharByName(sharedPreferences.getString("icon_name","Klee"),context)[3],context))
                .transform(transformation)
                .fit()
                .error (R.drawable.paimon_lost)
                .into ((ImageView) viewPager4.findViewById(R.id.card_char_ico));

        ImageView card_char_bg = viewPager4.findViewById(R.id.card_char_bg);
        card_char_bg.setImageResource(css.getRare2048ByInt(sharedPreferences.getInt("icon_rare",5)));


        // Btn of paimon page
        paimon_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Desk2048.this, CalculatorDB_2048.class);
                startActivity(i);
            }
        });
        paimon_daily.setOnClickListener(new View.OnClickListener() {
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
        paimon_map.setOnClickListener(new View.OnClickListener() {
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

        paimon_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, AlarmUI.class);
                startActivity(i);
            }
        });


        paimon_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.fragment_setting_2048_new, null);
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

                setup_setting(view,dialog);
                //setColorBk(view);

                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                lp.gravity = Gravity.CENTER;
                dialogWindow.setAttributes(lp);
                dialog.show();
            }
        });

        /*
        paimon_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.fragment_setting_2048_new, null);
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

                //setup_setting(view,dialog);
                //setColorBk(view);

                ImageView result_camera = view.findViewById(R.id.discord_ico);
                result_camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ScrollView scrollview = view.findViewById(R.id.setting_sc);

                        Bitmap bitmap = getBitmapFromView(scrollview, scrollview.getChildAt(0).getHeight(), scrollview.getChildAt(0).getWidth());
                        requestPermission();
                        if (isWritePermissionGranted){
                            if (saveImageToExternalStorage("Voc_Setting_Page",bitmap)){
                                CustomToast.toast(context,activity,context.getString(R.string.screenshot_saved));
                            }
                        }else {
                            CustomToast.toast(context,activity,context.getString(R.string.screenshot_not_saved_permission));
                        }
                    }
                });

                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                lp.gravity = Gravity.CENTER;
                dialogWindow.setAttributes(lp);
                dialog.show();
            }
        });
         */
        paimon_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.fragment_about_2048, null);
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

                setup_about(view,dialog);

                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                lp.gravity = Gravity.CENTER;
                dialogWindow.setAttributes(lp);
                dialog.show();
            }
        });
    }

    private Bitmap getBitmapFromView(View view, int height, int width) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.TRANSPARENT);
        view.draw(canvas);
        return bitmap;
    }

    private boolean saveImageToExternalStorage(String imgName, Bitmap bmp){
        Uri ImageCollection = null;
        ContentResolver resolver = context.getContentResolver();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            ImageCollection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
        }else {
            ImageCollection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, imgName + ".png");
        contentValues.put(MediaStore.Images.Media.DATE_ADDED, new Date().getTime());
        contentValues.put(MediaStore.Images.Media.MIME_TYPE,"image/png");
        Uri imageUri = resolver.insert(ImageCollection, contentValues);
        System.out.println("imageUri "+imageUri);
        try {
            OutputStream outputStream = resolver.openOutputStream(Objects.requireNonNull(imageUri));
            bmp.compress(Bitmap.CompressFormat.PNG,100,outputStream);
            Objects.requireNonNull(outputStream);
            return true;
        }
        catch (Exception e){
            CustomToast.toast(context,activity,context.getString(R.string.screenshot_not_saved));
            e.printStackTrace();
        }
        return false;

    }

    private void requestPermission() {
        boolean minSDK = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;
        isReadPermissionGranted = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED;
        isWritePermissionGranted = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED;
        isWritePermissionGranted = isWritePermissionGranted || minSDK;
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
                ConstraintLayout header_con = viewPager2.findViewById(R.id.header_con);
                View header_search = viewPager2.findViewById(R.id.header_search);
                EditText header_search_et = viewPager2.findViewById(R.id.header_search_et);
                Button menu_search_cancel = viewPager2.findViewById(R.id.menu_search_cancel);
                ImageView header_search_reset = viewPager2.findViewById(R.id.header_search_reset);

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

            };
        });

        ImageView weapon_filter = viewPager2.findViewById(R.id.weapon_filter);
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
                ConstraintLayout header_con = viewPager1.findViewById(R.id.header_con);
                View header_search = viewPager1.findViewById(R.id.header_search);
                EditText header_search_et = viewPager1.findViewById(R.id.header_search_et);
                Button menu_search_cancel = viewPager1.findViewById(R.id.menu_search_cancel);
                ImageView header_search_reset = viewPager1.findViewById(R.id.header_search_reset);

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

            };
        });


        ImageView char_filter = viewPager1.findViewById(R.id.char_filter);
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
                ConstraintLayout header_con = viewPager3.findViewById(R.id.header_con);
                View header_search = viewPager3.findViewById(R.id.header_search);
                EditText header_search_et = viewPager3.findViewById(R.id.header_search_et);
                Button menu_search_cancel = viewPager3.findViewById(R.id.menu_search_cancel);
                ImageView header_search_reset = viewPager3.findViewById(R.id.header_search_reset);

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

            };
        });

        ImageView artifact_filter = viewPager3.findViewById(R.id.artifact_filter);
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

    public void setup_setting(View view, Dialog dialog) {
        // Top Bar
        check_spinner = 0;
        BackgroundReload.BackgroundReload(context,view);
        DailyMemo dailyMemo = new DailyMemo();

        ImageView info_back_btn = view.findViewById(R.id.info_back_btn);
        info_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // Account
        Button custom_cookie_btn = view.findViewById(R.id.custom_cookie_btn);
        Button custom_ico_btn = view.findViewById(R.id.custom_ico_btn);
        Button custom_card_btn = view.findViewById(R.id.custom_card_btn);

        icon_name_final = sharedPreferences.getString("icon_name","Klee");
        card_name_final = sharedPreferences.getString("card_name","Klee");
        icon_rare_final = sharedPreferences.getInt("icon_rare",5);

        custom_cookie_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CookieManager.getInstance().removeAllCookies(null);
                CookieManager.getInstance().flush();
                CustomToast.toast(context,view,"Already clean all cookies");
            }
        });

        custom_card_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memo_setting(CARD);
            }
        });
        custom_ico_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memo_setting(ICON);
            }
        });
    }

    public void memo_setting(int TYPE){
        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View viewX = View.inflate(context, R.layout.fragment_icon_card_edit, null);
        dialog.setContentView(viewX);
        dialog.setCanceledOnTouchOutside(true);

        List<IconCard> iconCardList = iconCardList_ICON;
        if (TYPE == CARD){
            iconCardList = iconCardList_CARD;
        }

        ItemRss item_rss = new ItemRss();

        mIconCardList = viewX.findViewById(R.id.icon_card_list);
        mIconCardAdapter = new IconCardAdapter(context,iconCardList,activity,TYPE, viewX, dialog);
        ImageView card_bg = viewX.findViewById(R.id.card_bg);
        ImageView card_char_ico = viewX.findViewById(R.id.card_char_ico);
        FrameLayout dialog_cancel = viewX.findViewById(R.id.dialog_cancel);
        FrameLayout dialog_ok = viewX.findViewById(R.id.dialog_ok);

        dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        final int radius_circ = 360;
        final int margin_circ = 0;
        final Transformation transformation_circ = new RoundedCornersTransformation(radius_circ, margin_circ);

        final int radius_card = 24;
        final int margin_card = 0;
        final Transformation transformation_card = new RoundedCornersTransformation(radius_card, margin_card);
        int width_curr = displayMetrics.widthPixels;

        int height = (int) (width_curr/2.1);


        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            height = (int) height/4;
        }
        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getCharByName(sharedPreferences.getString("card_name","Klee"), context)[4],context))
                .resize(width, height)
                .error (R.drawable.unknown_card)
                .into (card_bg);

        card_bg.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getCharByName(sharedPreferences.getString("icon_name","Klee"), context)[3],context))
                .transform(transformation_circ)
                .fit()
                .error (R.drawable.paimon_lost)
                .into (card_char_ico);

        ImageView card_char_bg = viewX.findViewById(R.id.card_char_bg);
        card_char_bg.setImageResource(item_rss.getRare2048ByInt(sharedPreferences.getInt("icon_rare",5)));

        if (TYPE == CARD){
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                mLayoutManager = new GridLayoutManager(context,  3);
            }else{
                mLayoutManager = new GridLayoutManager(context,  2);
            }
        }else{
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                mLayoutManager = new GridLayoutManager(context,  7);
            }else{
                mLayoutManager = new GridLayoutManager(context,  4);
            }
        }

        LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsMsg.gravity = Gravity.CENTER;
        mIconCardList.setLayoutManager(mLayoutManager);
        mIconCardList.setAdapter(mIconCardAdapter);
        mIconCardList.removeAllViewsInLayout();

        BackgroundReload.BackgroundReload(context,viewX);
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

    public void update_memo(Dialog dialog, View viewX, int type, String baseName, int rare){
        ImageView card_bg = viewX.findViewById(R.id.card_bg);
        ImageView card_char_ico = viewX.findViewById(R.id.card_char_ico);
        ImageView card_char_bg = viewX.findViewById(R.id.card_char_bg);
        ItemRss item_rss = new ItemRss();

        if (type == CARD){
            Picasso.get()
                    .load (FileLoader.loadIMG(item_rss.getCharByName(baseName, context)[4],context))
                    .fit()
                    .error (R.drawable.unknown_card)
                    .into (card_bg);
            card_name_final = baseName;
        }else{
            Picasso.get()
                    .load (FileLoader.loadIMG(item_rss.getCharByName(baseName, context)[3],context))
                    .transform(transformation_circ)
                    .fit()
                    .error (R.drawable.paimon_lost)
                    .into (card_char_ico);
            icon_name_final = baseName;
            icon_rare_final = rare;
        }

        card_char_bg.setImageResource(item_rss.getRare2048ByInt(icon_rare_final));

        FrameLayout dialog_ok = viewX.findViewById(R.id.dialog_ok);
        dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putString("icon_name",icon_name_final).putString("card_name",card_name_final).putInt("icon_rare",icon_rare_final).apply();

                DisplayMetrics displayMetrics = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int width_curr = displayMetrics.widthPixels;

                int height = (int) (width_curr/2.1);


                if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                    height = (int) height/4;
                }
                Picasso.get()
                        .load (FileLoader.loadIMG(css.getCharByName(sharedPreferences.getString("card_name","Klee"),context)[4],context))
                        .resize(width_curr,height)
                        .error (R.drawable.paimon_lost)
                        .into ((ImageView) viewPager4.findViewById(R.id.card_bg));

                ((ImageView) viewPager4.findViewById(R.id.card_bg)).setScaleType(ImageView.ScaleType.CENTER_CROP);

                Picasso.get()
                        .load (FileLoader.loadIMG(css.getCharByName(sharedPreferences.getString("icon_name","Klee"),context)[3],context))
                        .transform(transformation_circ)
                        .fit()
                        .error (R.drawable.paimon_lost)
                        .into ((ImageView) viewPager4.findViewById(R.id.card_char_ico));

                ImageView card_char_bg = viewPager4.findViewById(R.id.card_char_bg);
                card_char_bg.setImageResource(css.getRare2048ByInt(sharedPreferences.getInt("icon_rare",5)));

                dialog.dismiss();
            }
        });
    }

    public void setup_about(View viewPager4, Dialog dialog){
        check_spinner = 0;

        BackgroundReload.BackgroundReload(context,viewPager4);

        ImageView info_back_btn = viewPager4.findViewById(R.id.info_back_btn);
        info_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // About
        Button bg_changelog_btn = viewPager4.findViewById(R.id.bg_changelog_btn);
        bg_changelog_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeLog.show(context,activity);
            }
        });

        TextView info_app_version = viewPager4.findViewById(R.id.info_app_version);
        String versionName = BuildConfig.VERSION_NAME;
        info_app_version.setText(versionName);

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

    private void setColorBk(View viewPager4) {
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

        giveTickById(color_bk,pos-1,viewPager4);
    }

    private void giveTickById(ImageView color_bk,int pos, View viewPager4) {
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

                IconCard iconCard = new IconCard();
                iconCard.setItemName(name);
                iconCard.setItemRare(rare);
                iconCard.setItemBaseName(name);
                iconCardList_ICON.add(iconCard);
                iconCardList_CARD.add(iconCard);
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

        // Setting
        //ConstraintLayout birth_celebrate = viewPager0.findViewById(R.id.birth_celebrate);
        //ImageView birth_char = viewPager0.findViewById(R.id.birth_char);
        //TextView birth_char_tv = viewPager0.findViewById(R.id.birth_char_tv);
        //TextView birth_char_date = viewPager0.findViewById(R.id.birth_char_date);
        TextView birth_title_normal = viewPager0.findViewById(R.id.birth_title_normal);
        TextView birth_title_special = viewPager0.findViewById(R.id.birth_title_special);

        // Big Icon
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int pix = (int) ((displayMetrics.widthPixels-16)/6-8);

        final int radius = 180;
        final int margin = 4;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);

        if(!char_name.equals("EMPTY")){
            birth_title_normal.setVisibility(View.GONE);
            birth_title_special.setVisibility(View.VISIBLE);
            birth_title_special.setText(context.getString(R.string.happy_birthday)+" "+css.getCharByName(char_name,context)[1]);
        }else{
            birth_title_special.setVisibility(View.GONE);
            birth_title_normal.setVisibility(View.VISIBLE);
        }
        // List

        //System.out.println("XPR1 char_name : "+char_name);

        int dom_TMP = dom;
        int moy_TMP = moy;
        while (char_name.equals("EMPTY") || !Arrays.asList(css.charBirthName).contains(char_name)){
            dom_TMP++;
            if (dom_TMP > 32){dom_TMP = 1;moy_TMP++;}
            if (moy_TMP > 13){moy_TMP = 0;}
            char_name = css.char_birth(moy_TMP,dom_TMP);

            //System.out.println("XPR2 char_name : "+char_name);
        }

        //System.out.println("XPR3 char_name : "+char_name);

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

            if ((moy == nextBirthCharMonth) && (dom == nextBirthCharDay)){
                tv.setText(context.getString(R.string.today));
            }else{
                tv.setText(css.getLocaleBirth(String.valueOf(nextBirthCharMonth+1)+"/"+String.valueOf(nextBirthCharDay),context,true));
            }
            img.getLayoutParams().width = pix;
            img.getLayoutParams().height = pix;
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
            View view = LayoutInflater.from(context).inflate(R.layout.item_asc_require_card_2048, char_ll, false);
            ImageView asc_material_ico = view.findViewById(R.id.asc_material_ico);
            ImageView asc_material_tick = view.findViewById(R.id.asc_material_tick);
            ImageView asc_material_locate = view.findViewById(R.id.asc_material_locate);
            TextView asc_material_tv = view.findViewById(R.id.asc_material_tv);
            TextView asc_material_location = view.findViewById(R.id.asc_material_location);
            LinearLayout asc_material_char_ll = view.findViewById(R.id.asc_material_char_ll);

            //Set tv and img
            asc_material_ico.getLayoutParams().width = (int) (asc_material_ico.getLayoutParams().width*0.8);
            asc_material_ico.getLayoutParams().height = (int) (asc_material_ico.getLayoutParams().height*0.8);
            asc_material_tv.setText(getString(today_TV[x]));
            Picasso.get().load(today_IMG[x]).into(asc_material_ico);
            asc_material_location.setText(getString(today_Location[x]));

            int finalX = x;
            asc_material_locate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String lang = "en-us";
                    switch (sharedPreferences.getString("curr_lang","en-US")){
                        case "zh-HK" : lang = "zh-tw";break;
                        default: lang = sharedPreferences.getString("curr_lang","en-US").toLowerCase();break;
                    }
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://webstatic-sea.hoyolab.com/ys/app/interactive-map/index.html?lang="+lang+"#/map/2?shown_types=3,154&center="+tm.today_char_location_url(dow)[finalX]+"&zoom=0.50"));
                    startActivity(browserIntent);
                }
            });

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
                        FrameLayout ll = asc_material_char_ll.findViewById(asc_char_ll[tmp_cnt]);
                        ImageView tick = asc_material_char_ll.findViewById(asc_char_tick[tmp_cnt]);
                        ImageView img = asc_material_char_ll.findViewById(asc_char_ico[tmp_cnt]);
                        real_name = name;
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

                        if (dbIsCalCharName.contains(name)){
                            tick.setVisibility(View.VISIBLE);
                        }


                        String finalReal_name = real_name;
                        String finalName = name;
                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Characters_Info_2048 cif = new Characters_Info_2048();
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
        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(360, 0, RoundedCornersTransformation.CornerType.ALL);

        int[] today_IMG = tm.today_weapon_IMG(dow);
        int[] today_TV = tm.today_weapon_TV(dow);
        int[] today_Location = tm.today_weapon_location(dow);
        for (int x = 0;  x < today_IMG.length ; x++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_asc_require_card_2048, weapon_ll, false);
            ImageView asc_material_ico = view.findViewById(R.id.asc_material_ico);
            ImageView asc_material_tick = view.findViewById(R.id.asc_material_tick);
            TextView asc_material_tv = view.findViewById(R.id.asc_material_tv);
            TextView asc_material_location = view.findViewById(R.id.asc_material_location);
            ImageView asc_material_locate = view.findViewById(R.id.asc_material_locate);
            LinearLayout asc_material_weapon_ll = view.findViewById(R.id.asc_material_char_ll);

            //Set tv and img
            asc_material_ico.getLayoutParams().width = (int) (asc_material_ico.getLayoutParams().width*0.8);
            asc_material_ico.getLayoutParams().height = (int) (asc_material_ico.getLayoutParams().height*0.8);
            asc_material_tv.setText(getString(today_TV[x]));
            Picasso.get().load(today_IMG[x]).into(asc_material_ico);
            asc_material_location.setText(getString(today_Location[x]));

            int finalX = x;
            asc_material_locate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String lang = "en-us";
                    switch (sharedPreferences.getString("curr_lang","en-US")){
                        case "zh-HK" : lang = "zh-tw";break;
                        default: lang = sharedPreferences.getString("curr_lang","en-US").toLowerCase();break;
                    }
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://webstatic-sea.hoyolab.com/ys/app/interactive-map/index.html?lang="+lang+"#/map/2?shown_types=3,154&center="+tm.today_weapon_location_url(dow)[finalX]+"&zoom=0.50"));
                    startActivity(browserIntent);
                }
            });

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

                        String finalName = name;
                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Weapon_Info_2048 wif = new Weapon_Info_2048();
                                wif.setup(finalName,context,activity);
                            }
                        });

                        if (dbIsCalWeaponName.contains(name)){
                            tick.setVisibility(View.VISIBLE);
                        }

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

    public void startCharInfo (String name, Activity activity){
        Characters_Info_2048 cif = new Characters_Info_2048();
        cif.setup(String.valueOf(name),context,activity);
    }
    public void startWeaponInfo (String name, Activity activity){
        Weapon_Info_2048 cif = new Weapon_Info_2048();
        cif.setup(String.valueOf(name),context,activity);
    }
    public void startArtifactInfo (String name, Activity activity){
        Artifact_Info_2048 aif = new Artifact_Info_2048();
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
        window.setStatusBarColor(context.getColor(R.color.status_bar_2048));
        window.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

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
                    Dialog2048 dialog2048 = new Dialog2048();
                    dialog2048.setup(context,activity);
                    dialog2048.updateMax(getRemoteFileSizeA(array_download));
                    dialog2048.mode(Dialog2048.MODE_DOWNLOAD_UPDATE);
                    dialog2048.show();

                    long finalLastUnix = lastUnix;
                    dialog2048.getPositiveBtn().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog2048.dismiss();
                            DownloadTask downloadTask = new DownloadTask();
                            downloadTask.startA(array_download,array_fileName,array_SfileName,context,activity);
                            editor.putLong("lastUpdateUnix", finalLastUnix);
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

    public void filterWeaponAlgothm(int star){
        switch (star){
            case 0: show_rare1 = false; show_rare2 = false ; show_rare3 = false;show_rare4 = false ; show_rare5 = false;break;
            case 1: show_rare1 = true; show_rare2 = false ; show_rare3 = false;show_rare4 = false ; show_rare5 = false;break;
            case 2: show_rare2 = true; show_rare1 = false ; show_rare3 = false;show_rare4 = false ; show_rare5 = false;break;
            case 3: show_rare3 = true;show_rare1 = false; show_rare2 = false ;show_rare4 = false ; show_rare5 = false;break;
            case 4: show_rare4 = true;show_rare1 = false; show_rare2 = false ; show_rare3 = false ; show_rare5 = false;break;
            case 5: show_rare5 = true;show_rare1 = false; show_rare2 = false ; show_rare3 = false;show_rare4 = false ;break;
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
            case 1: show_rare1 = true; show_rare2 = false ; show_rare3 = false;show_rare4 = false ; show_rare5 = false;break;
            case 2: show_rare2 = true; show_rare1 = false ; show_rare3 = false;show_rare4 = false ; show_rare5 = false;break;
            case 3: show_rare3 = true;show_rare1 = false; show_rare2 = false ;show_rare4 = false ; show_rare5 = false;break;
            case 4: show_rare4 = true;show_rare1 = false; show_rare2 = false ; show_rare3 = false ; show_rare5 = false;break;
            case 5: show_rare5 = true;show_rare1 = false; show_rare2 = false ; show_rare3 = false;show_rare4 = false ;break;
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

    private boolean getBooleanByInt (int x){
        if(x == 0){
            return false;
        }else if(x == 1){
            return true;
        }else{
            return false;
        }

    }
}

