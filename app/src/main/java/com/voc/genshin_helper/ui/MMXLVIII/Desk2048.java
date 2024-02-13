package com.voc.genshin_helper.ui.MMXLVIII;

import static com.voc.genshin_helper.util.DownloadAndUnzipTask.baseFileName;
import static com.voc.genshin_helper.util.RoundedCornersTransformation.CornerType.ALL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
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
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.BaseColumns;
import android.provider.MediaStore;
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
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.BuildConfig;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.buff.BuffDatabaseUI;
import com.voc.genshin_helper.buff.EnkaDataCollect;
import com.voc.genshin_helper.data.buff_old.SipTikCal;
import com.voc.genshin_helper.data.Artifacts;
import com.voc.genshin_helper.data.Characters;
import com.voc.genshin_helper.data.IconCard;
import com.voc.genshin_helper.data.IconCardAdapter;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.TCG;
import com.voc.genshin_helper.data.Today_Material;
import com.voc.genshin_helper.data.Weapons;
import com.voc.genshin_helper.database.DataBaseContract;
import com.voc.genshin_helper.database.DataBaseHelper;
import com.voc.genshin_helper.kidding.GoSleep;
import com.voc.genshin_helper.tutorial.TutorialUI;
import com.voc.genshin_helper.ui.AlarmUI;
import com.voc.genshin_helper.ui.BackgroundConfirmActivity;
import com.voc.genshin_helper.ui.MainActivity;
import com.voc.genshin_helper.ui.SipTik.DeskSipTik;
import com.voc.genshin_helper.util.BackgroundReload;
import com.voc.genshin_helper.util.ChangeLog;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.CustomViewPager;
import com.voc.genshin_helper.util.DailyMemo;
import com.voc.genshin_helper.util.DailyMemoV2;
import com.voc.genshin_helper.util.Dialog2048;
import com.voc.genshin_helper.util.DownloadAndUnzipTask;
import com.voc.genshin_helper.util.EventUtil;
import com.voc.genshin_helper.util.LangUtils;
import com.voc.genshin_helper.util.LocaleHelper;
import com.voc.genshin_helper.util.MyViewPagerAdapter;
import com.voc.genshin_helper.util.NumberPickerDialog;
import com.voc.genshin_helper.util.RoundedCornersTransformation;
import com.voc.genshin_helper.util.hoyolab.HoyolabConstants;
import com.voc.genshin_helper.util.hoyolab.HoyolabCookie;
import com.voc.genshin_helper.util.hoyolab.hooks.HoyolabHooks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
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

    boolean firstSelect = false;
    boolean firstSelectASC = false;
    int id = 0;
    long period = 0;


    LinearLayout char_ll;
    LinearLayout weapon_ll;
    TabLayout desk_tablayout;
    TabLayout tcg_tablayout;
    ImageView desk_tablayout_bg;
    Today_Material tm;
    ItemRss css;
    //Char Page
    //CharactersAdapter mAdapter;
    //ArtifactsAdapter mArtifactAdapter;
    //RecyclerView mArtifactList;
    //RecyclerView mList;
    //WeaponsAdapter mWeaponAdapter;
    //RecyclerView mWeaponList;
    IconCardAdapter mIconCardAdapter;
    RecyclerView mIconCardList;

    LocaleHelper localeHelper;
    NumberPickerDialog npd;

    String uid_final = "N/A";
    String token_final = "N/A";
    String genshin_uid_final = "-1";

    int dow = 0; // Day of Week
    int dom = 0; // Day of Month
    int moy = 0; // Month of Year
    int exit = 0;
    int app_started = 0;
    int check_spinner = 0;

    Context context;
    Resources resources;
    Configuration configuration ;
    LangUtils langUtils;
    EventUtil eventUtil;

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
    int indicatorWidth = 0;

    public SharedPreferences sharedPreferences;
    public SharedPreferences sharedPreferences_version;
    public SharedPreferences.Editor editor;
    public SharedPreferences.Editor editor2;

    //public List<Characters> charactersList = new ArrayList<>();
    //public List<Weapons> weaponsList = new ArrayList();
    //public List<Artifacts> artifactsList = new ArrayList();
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
    Switch other_dailymemo_enabled ;
    Switch supportUkraine ;

    Button bg_download_delete;
    Button bg_download_reset;

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

    DailyMemoV2 dailyMemo;

    private ViewPager viewPager;
    private ArrayList<View> viewPager_List;
    private ArrayList<View> viewPager_ASC_List;
    GoSleep gs;
    ColorStateList myList;

    TCG2048 tcg2048;
    Team2048 team2048;

    final int radius_circ = 360;
    final int margin_circ = 0;
    final Transformation transformation_circ = new RoundedCornersTransformation(radius_circ, margin_circ);

    final int radius_card = 24;
    final int margin_card = 0;
    final Transformation transformation_card = new RoundedCornersTransformation(radius_card, margin_card);

    Activity activity;

    //View viewPager1, viewPager2, viewPager3;
    View viewPager0, viewPager4, viewPager5, viewPager6;
    View viewPagerChar, viewPagerWeapon;

    int[] tabItemImageArray = new int[]{R.drawable.ic_2048_tab_desk,R.drawable.ic_2048_tab_team,R.drawable.ic_2048_tab_tcg,R.drawable.ic_2048_tab_toolbox};
    int[] tabItemImageSelectedArray = new int[]{R.drawable.ic_2048_tab_desk_selected,R.drawable.ic_2048_tab_team_selected,R.drawable.ic_2048_tab_tcg_selected,R.drawable.ic_2048_tab_toolbox_selected};


    //int[] tabItemImageArray = new int[]{R.drawable.ic_2048_tab_desk,R.drawable.ic_2048_tab_char,R.drawable.ic_2048_tab_weapon,R.drawable.ic_2048_tab_art,R.drawable.ic_2048_tab_tcg,R.drawable.ic_2048_tab_toolbox};
    //int[] tabItemImageSelectedArray = new int[]{R.drawable.ic_2048_tab_desk_selected,R.drawable.ic_2048_tab_char_selected,R.drawable.ic_2048_tab_weapon_selected,R.drawable.ic_2048_tab_art_selected,R.drawable.ic_2048_tab_tcg_selected,R.drawable.ic_2048_tab_toolbox_selected};

    int[] tabTCGItemImageArray = new int[]{R.drawable.ic_2048_tcg_char,R.drawable.ic_2048_tcg_equip,R.drawable.ic_2048_tcg_support,R.drawable.ic_2048_tcg_event};
    int[] tabTCGItemImageSelectedArray = new int[]{R.drawable.ic_2048_tcg_char_selected,R.drawable.ic_2048_tcg_equip_selected,R.drawable.ic_2048_tcg_support_selected,R.drawable.ic_2048_tcg_event_selected};

    ChangeLog changeLog ;

    TabLayout asc_tablayout ;
    CustomViewPager viewPagerASC;
    SwitchCompat asc_switch;
    View mIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("user_info",MODE_PRIVATE);
        sharedPreferences_version = getSharedPreferences("changelog_version",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        setContentView(R.layout.activity_desk_2048);

        /*
        Intent serviceIntent = new Intent(this, DailyMemo2048Service.class);
        startForegroundService(serviceIntent);
         */

        //init
        tm = new Today_Material();
        css = new ItemRss();
        localeHelper = new LocaleHelper();
        langUtils = new LangUtils();
        eventUtil = new EventUtil();


        context = this;
        activity = this;
        serverList = new String[]{getString(R.string.america_ser),getString(R.string.europe_ser),getString(R.string.asia_ser),getString(R.string.hk_tw_mo_ser),getString(R.string.sky_land_ser),getString(R.string.world_tree)};

        //System.out.println("UUID : "+HoyolabHooks.getDeviceId(context));

        viewPager = (ViewPager) findViewById(R.id.vp);
        desk_tablayout = findViewById(R.id.desk_tablayout);
        desk_tablayout_bg = findViewById(R.id.desk_tablayout_bg);

        if (context.getString(R.string.mode).equals("Night")){
            desk_tablayout_bg.setImageResource(R.drawable.mask_navi_bg_night);
        }else{
            desk_tablayout_bg.setImageResource(R.drawable.mask_navi_bg_day);
        }

        npd = new NumberPickerDialog(this);
        changeLog = new ChangeLog();

        // Check Is First Time Open -- Disabled
        /*
        if(sharedPreferences_version.getBoolean(BuildConfig.VERSION_NAME,false) == false){
            changeLog.show(context,activity);

            editor2 = sharedPreferences_version.edit();
            editor2.putBoolean(BuildConfig.VERSION_NAME,true);
            editor2.apply();
        }
         */

        final LayoutInflater mInflater = getLayoutInflater().from(this);
        viewPager0 = mInflater.inflate(R.layout.fragment_home_2048, null,false);
        //viewPager1 = mInflater.inflate(R.layout.fragment_char_2048, null,false);
        //viewPager2 = mInflater.inflate(R.layout.fragment_weapon_2048, null,false);
        //viewPager3 = mInflater.inflate(R.layout.fragment_art_2048, null,false);
        viewPager4 = mInflater.inflate(R.layout.fragment_paimon_2048, null,false);
        viewPager5 = mInflater.inflate(R.layout.fragment_tcg_2048, null,false);
        viewPager6 = mInflater.inflate(R.layout.fragment_team_2048, null,false);
        viewPagerWeapon = mInflater.inflate(R.layout.fragment_asc_2048, null,false);
        viewPagerChar = mInflater.inflate(R.layout.fragment_asc_2048, null,false);

        viewPager_List = new ArrayList<View>();
        viewPager_List.add(viewPager0);
        //viewPager_List.add(viewPager1);
        //viewPager_List.add(viewPager2);
        //viewPager_List.add(viewPager3);
        viewPager_List.add(viewPager6);
        viewPager_List.add(viewPager5);
        viewPager_List.add(viewPager4);

        viewPager_ASC_List = new ArrayList<View>();
        viewPager_ASC_List.add(viewPagerChar);
        viewPager_ASC_List.add(viewPagerWeapon);

        check_spinner = 0;

        dailyMemo = new DailyMemoV2();
        dailyMemo.setup(context,activity,viewPager0);
        viewPager0.findViewById(R.id.home_dailymemo).setVisibility(View.VISIBLE);

        //eventUtil.init(viewPager0, context,activity);

        tcg2048 = new TCG2048();
        team2048 = new Team2048();

        lang_setup();
        home();
        getDOW();
        //bday_reload();  The tear of last era
        cbg();
        dbChar_reload();
        char_reload(dow);
        weapon_reload(dow);
        setup_home();
        setup_team();
        setup_paimon();
        tcg2048.setup(viewPager5,context,activity,sharedPreferences);
        team2048.setup(viewPager6,context,activity,sharedPreferences);

        //EnkaDataCollect enkaDataCollect = new EnkaDataCollect();
        //enkaDataCollect.init(context);
        char_list_reload();

        if(sharedPreferences.getInt("app_started",0) > 10 && sharedPreferences.getBoolean("review_given",false) == false){
            LinearLayout review_request_ll = findViewById(R.id.review_request_ll);
            review_request_ll.setVisibility(View.VISIBLE);

            TextView desk_dismiss_tv = findViewById(R.id.desk_dismiss_tv);
            TextView desk_yes_tv = findViewById(R.id.desk_yes_tv);
            TextView desk_feedback_tv = findViewById(R.id.desk_feedback_tv);

            desk_dismiss_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    review_request_ll.setVisibility(View.GONE);
                }
            });
            desk_yes_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putBoolean("review_given", true).apply();
                    review_request_ll.setVisibility(View.GONE);
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.voc.genshin_spirit_gp")));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.voc.genshin_spirit_gp")));
                    }

                    //Uri uri = Uri.parse("https://discord.gg/uXatcbWKv2"); // missing 'https://' will cause crashed
                    //                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    //                startActivity(intent);

                }
            });
            desk_feedback_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putBoolean("review_given", true).apply();
                    review_request_ll.setVisibility(View.GONE);

                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto","voc.app.programmer@gmail.com", null));
                    emailIntent.putExtra(Intent.EXTRA_TITLE, "Advice of Genshin Helper");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi, ");
                    startActivity(Intent.createChooser(emailIntent, "Advice of Genshin Helper"));

                }
            });
        }

        BackgroundReload.BackgroundReload(context,activity);

        viewPager.setAdapter(new MyViewPagerAdapter(viewPager_List));

        for (int x = 0 ; x < 4 ; x++){
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
        //if(!voted && app_started >= 5){
        //    showVoteDialog();
        //}

        if(sharedPreferences.getBoolean("PASS_JUST_CHANGED_THEME",false) == false){
            editor.putInt("app_started",app_started+1);
            editor.apply();
        }

        if (sharedPreferences.getBoolean("PASS_JUST_CHANGED_THEME",false) == true) {
            editor.putBoolean("PASS_JUST_CHANGED_THEME", false);
            editor.apply();


            viewPager.setCurrentItem(4);
            desk_tablayout.selectTab(desk_tablayout.getTabAt(3));

            View view1 = desk_tablayout.getTabAt(3).getCustomView();
            ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
            tab_icon.setImageResource(tabItemImageSelectedArray[3]);

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
            if(!dialog.isShowing()){
                dialog.show();
            }

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

                if (!firstSelect || (System.currentTimeMillis() - period > 3000)){
                    id = tab.getId();
                    period = System.currentTimeMillis();
                    firstSelect = true;
                }else if(firstSelect && tab.getId() != id){
                    firstSelect = false;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
                tab_icon.setImageResource(tabItemImageArray[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (!firstSelect || (System.currentTimeMillis() - period > 3000)){
                    id = tab.getId();
                    period = System.currentTimeMillis();
                    firstSelect = true;
                }else if(firstSelect && tab.getId() != id){
                    firstSelect = false;
                }else if (firstSelect && (tab.getId() == id) && (System.currentTimeMillis() - period < 3000)){
                    RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(context) {
                        @Override protected int getVerticalSnapPreference() {
                            return LinearSmoothScroller.SNAP_TO_START;
                        }
                    };
                    smoothScroller.setTargetPosition(0);
                    switch (tab.getPosition()){
                        case 0 : ((NestedScrollView) viewPager0.findViewById(R.id.sc_root)).smoothScrollTo(0,0);break;
                        case 1 : ((RecyclerView) team2048.getCurrList()).getLayoutManager().startSmoothScroll(smoothScroller);break;
                        case 2 : ((RecyclerView) tcg2048.getCurrList()).getLayoutManager().startSmoothScroll(smoothScroller);break;
                        //case 3 : ((RecyclerView) viewPager4.findViewById(R.id.artifact_list)).getLayoutManager().startSmoothScroll(smoothScroller);break;
                    }
                    firstSelect = false;
                }
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

        viewPager0.findViewById(R.id.tut_char_card).setVisibility(View.GONE);
        viewPager4.findViewById(R.id.tut_char_card).setVisibility(View.GONE);
        viewPager6.findViewById(R.id.tut_char_card).setVisibility(View.GONE);

        TutorialUI tutorialUI = new TutorialUI();
        //tutorialUI.deskSetPosArray(0,1,2,3,4);
        //tutorialUI.setup(context,activity,viewPager0,viewPager1,viewPager2,viewPager3,viewPager4,desk_tablayout,null);
    }

    public void refreshUI(){
        lang_setup();
        home();
        getDOW();
        //EventUtil eventUtil = new EventUtil();
        //eventUtil.init(viewPager0, context,activity);
        //bday_reload();
        cbg();
        dbChar_reload();
        char_reload(dow);
        weapon_reload(dow);
        setup_home();
        setup_team();
        setup_paimon();
        tcg2048.setup(viewPager5,context,activity,sharedPreferences);
        team2048.setup(viewPager6,context,activity,sharedPreferences);

        EnkaDataCollect enkaDataCollect = new EnkaDataCollect();
        enkaDataCollect.init(context);

        char_list_reload();
    }

    public void setCheckSpinner(int check_spinner){
        this.check_spinner = check_spinner;
    }

    public int getCheckSpinner(){
        return check_spinner;
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

    public void refreshPaimon(){
        TextView card_userstat = viewPager4.findViewById(R.id.card_userstat);
        TextView card_username = viewPager4.findViewById(R.id.card_username);
        TextView card_lvl_tv = viewPager4.findViewById(R.id.card_lvl_tv);

        card_username.setText(dailyMemo.getNickname(context));
        card_userstat.setText(dailyMemo.getServerLocaleName(context)+" -- "+sharedPreferences.getString("genshin_uid","-1"));
        card_lvl_tv.setText(dailyMemo.getLevel(context));
    }

    private void setup_paimon() {
        //ConstraintLayout paimon_cal = viewPager4.findViewById(R.id.paimon_cal);
        //ConstraintLayout paimon_buff_cal = viewPager4.findViewById(R.id.paimon_buff_cal);
        //ConstraintLayout paimon_daily = viewPager4.findViewById(R.id.paimon_daily);
        //ConstraintLayout paimon_map = viewPager4.findViewById(R.id.paimon_map);
        //ConstraintLayout paimon_alarm = viewPager4.findViewById(R.id.paimon_alarm);
        //ConstraintLayout paimon_setting = viewPager4.findViewById(R.id.paimon_setting);
        //ConstraintLayout paimon_about = viewPager4.findViewById(R.id.paimon_about);

        //Unlock in 3.7 or 3.8 -> 4.0 -> 4.2 -> NEVER QQ
        //paimon_buff_cal.setVisibility(View.INVISIBLE);

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

        viewPager4.findViewById(R.id.card_bg).setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int) (displayMetrics.density*240)));

        ImageView card_bg = viewPager4.findViewById(R.id.card_bg);
        card_bg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        card_bg.setImageResource(css.getCharByName(sharedPreferences.getString("card_name","Klee"),context)[4]);

        Picasso.get()
                .load (dailyMemo.getIcon(context))
                .transform(transformation)
                .fit()
                .error (R.drawable.paimon_lost)
                .into ((ImageView) viewPager4.findViewById(R.id.card_char_ico));

        ImageView card_char_bg = viewPager4.findViewById(R.id.card_char_bg);
        card_char_bg.setImageResource(css.getCharRare2048ByInt(sharedPreferences.getInt("icon_rare",5)));

        int countPerRow = (int) ((width_curr - 8*displayMetrics.density) / (300));
        int itemWidth = (int) (300 + ((width_curr - 8*displayMetrics.density) % (300)) / countPerRow);

        LinearLayout paimon_ll = viewPager4.findViewById(R.id.paimon_ll);
        paimon_ll.removeAllViews();
        int[] btnStr = new int[]{R.string.calculator, R.string.daily_login, R.string.map, R.string.title_settings, R.string.about};//, R.string.buff_calcluator};
        int[] btnIMG = new int[]{R.drawable.ic_2048_cal, R.drawable.ic_2048_daily, R.drawable.ic_2048_map, R.drawable.ic_2048_setting, R.drawable.ic_2048_about};//, R.drawable.ic_2048_cal};
        View.OnClickListener[] btnOnClick = new View.OnClickListener[]{paimon_cal, paimon_daily, paimon_map, paimon_setting, paimon_about};//, paimon_buff_cal};

        LinearLayout ll_main = new LinearLayout(context);
        for (int x = 0; x < btnStr.length ; x++){
            if (x % countPerRow == 0){
                LinearLayout ll = new LinearLayout(context);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                ll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                ll_main = ll;
                paimon_ll.addView(ll_main);
            }
            View view = View.inflate(context, R.layout.item_paimon_btn_2048, null);

            ImageView paimon_bg = view.findViewById(R.id.paimon_bg);
            TextView paimon_tv = view.findViewById(R.id.paimon_tv);
            ImageView paimon_ico = view.findViewById(R.id.paimon_ico);
            FrameLayout paimon_fl = view.findViewById(R.id.paimon_fl);

            paimon_tv.setText(context.getString(btnStr[x]));
            paimon_ico.setImageResource(btnIMG[x]);
            paimon_bg.setOnClickListener(btnOnClick[x]);
            paimon_fl.getLayoutParams().width = itemWidth - (int) (8*displayMetrics.density);

            ll_main.addView(view);
        }
    }

    public View.OnClickListener paimon_cal = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Desk2048.this, CalculatorDB_2048.class);
            startActivity(i);
        }
    };
    public View.OnClickListener paimon_buff_cal = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CustomToast.toast(context, activity, "80% done !");
            if(BuildConfig.FLAVOR.equals("beta") || BuildConfig.FLAVOR.equals("dev")){
                Intent i = new Intent(Desk2048.this, BuffDatabaseUI.class);
                startActivity(i);
            }
        }
    };
    public View.OnClickListener paimon_daily = new View.OnClickListener()  {
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
    };
    public View.OnClickListener paimon_map = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, AlarmUI.class);
            startActivity(i);
        }
    };
    public View.OnClickListener paimon_alarm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, AlarmUI.class);
            startActivity(i);
        }
    };
    public View.OnClickListener paimon_setting = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Dialog dialog = new Dialog(context, R.style.PageDialogStyle_P);
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

            if (!dialog.isShowing()){
                dialog.show();
            }
        }
    };
    public View.OnClickListener paimon_about = new View.OnClickListener() {
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
    };

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

    private void setup_team(){
        check_spinner = 0;
    }

    public void setup_setting(View view, Dialog dialog) {
        // Top Bar
        check_spinner = 0;
        BackgroundReload.BackgroundReload(context,view);

        ImageView info_back_btn = view.findViewById(R.id.info_back_btn);
        info_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ImageView discord_ico = view.findViewById(R.id.discord_ico);
        discord_ico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://discord.gg/uXatcbWKv2"); // missing 'https://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        // Account
        Button custom_cookie_btn = view.findViewById(R.id.custom_cookie_btn);
        Button custom_ico_btn = view.findViewById(R.id.custom_ico_btn);
        Button custom_card_btn = view.findViewById(R.id.custom_card_btn);

        icon_name_final = sharedPreferences.getString("icon_name","N/A");
        card_name_final = sharedPreferences.getString("card_name","Traveler");
        icon_rare_final = sharedPreferences.getInt("icon_rare",5);

        custom_cookie_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CookieManager cookieManager = CookieManager.getInstance();
                dailyMemo.cleanCookies(cookieManager, view);
                editor.remove("icon_name").remove("genshin_uid").remove("genshin_username").remove("genshin_level").apply();
                for (String key : HoyolabCookie.HOYOLAB_V2_KEY_GROUP){editor.remove(key).apply();}

                sharedPreferences.edit().putString("dailyMemoDataTMP", HoyolabConstants.HOYOLAB_DAILYMEMO_EMPTY).apply();
                dailyMemo.refreshData(HoyolabConstants.HOYOLAB_DAILYMEMO_EMPTY);
                System.out.println("WHERE ARE U ?");
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

        theme_light = view.findViewById(R.id.theme_light);
        theme_night = view.findViewById(R.id.theme_dark);
        theme_default = view.findViewById(R.id.theme_default);

        //Server choice
        ArrayAdapter server_aa = new ArrayAdapter(context,R.layout.spinner_item,serverList);
        server_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_2048);
        Spinner server_spinner = view.findViewById(R.id.server_spinner);
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

        other_exit_confirm = view.findViewById(R.id.other_exit_confirm);

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

        //Other -> List_Grid
        grid_2_rb = view.findViewById(R.id.grid_2);
        grid_3_rb = view.findViewById(R.id.grid_3);
        grid_4_rb = view.findViewById(R.id.grid_4);
        grid_5_rb = view.findViewById(R.id.grid_5);
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
        }else if(currUiGrid.equals("5")){
            grid_2_rb.setChecked(false);
            grid_3_rb.setChecked(false);
            grid_4_rb.setChecked(false);
            grid_5_rb.setChecked(true);
        }else{
            grid_2_rb.setChecked(true);
            grid_3_rb.setChecked(false);
            grid_4_rb.setChecked(false);
            grid_5_rb.setChecked(false);
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
                //setup_char();
                //setup_weapon();
                //setup_art();
                tcg2048.setup(viewPager5, context, activity, sharedPreferences);
                team2048.setup(viewPager6, context, activity,sharedPreferences);
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
                //setup_char();
                //setup_weapon();
                //setup_art();
                tcg2048.setup(viewPager5, context, activity, sharedPreferences);
                team2048.setup(viewPager6, context, activity,sharedPreferences);
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
                //setup_char();
                //setup_weapon();
                //setup_art();
                tcg2048.setup(viewPager5, context, activity, sharedPreferences);
                team2048.setup(viewPager6, context, activity,sharedPreferences);
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
                //setup_char();
                //setup_weapon();
                //setup_art();
                tcg2048.setup(viewPager5, context, activity, sharedPreferences);
                team2048.setup(viewPager6, context, activity,sharedPreferences);
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
                //setup_char();
                //setup_weapon();
                //setup_art();
                tcg2048.setup(viewPager5, context, activity, sharedPreferences);
                team2048.setup(viewPager6, context, activity,sharedPreferences);
            }
        });

        //Other -> Style
        style_Voc_rb = view.findViewById(R.id.ui_Voc_rb);
        style_2O48_rb = view.findViewById(R.id.ui_2O48_rb);
        style_SipTik_rb = view.findViewById(R.id.ui_SipTik_rb);
        webView = view.findViewById(R.id.webView);
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

        //Other -> Traveler Sex
        traveler_female_rb = view.findViewById(R.id.traveler_female_rb);
        traveler_male_rb = view.findViewById(R.id.traveler_male_rb);
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
                //setup_char();
                tcg2048.setup(viewPager5, context, activity, sharedPreferences);
                team2048.setup(viewPager6, context, activity,sharedPreferences);
            }
        });

        traveler_female_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("traveler_sex","F");
                editor.apply();
                traveler_female_rb.setChecked(true);
                traveler_male_rb.setChecked(false);
                //setup_char();
                tcg2048.setup(viewPager5, context, activity, sharedPreferences);
                team2048.setup(viewPager6, context, activity,sharedPreferences);
            }
        });

        //Other -> Change Suits
        outfit_standard_rb = view.findViewById(R.id.outfit_standard_rb);
        outfit_event_rb = view.findViewById(R.id.outfit_event_rb);
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
                //setup_char();
                tcg2048.setup(viewPager5, context, activity, sharedPreferences);
                team2048.setup(viewPager6, context, activity,sharedPreferences);
            }
        });

        outfit_event_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("isCharChangeEventSuit",true);
                editor.apply();
                outfit_event_rb.setChecked(true);
                outfit_standard_rb.setChecked(false);
                //setup_char();
                tcg2048.setup(viewPager5, context, activity, sharedPreferences);
                team2048.setup(viewPager6, context, activity,sharedPreferences);
            }
        });

        // Background
        Button bg_setting_btn = view.findViewById(R.id.bg_setting_btn);
        bg_setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);
            }
        });
        Button bg_setting_btn_reset = view.findViewById(R.id.bg_setting_btn_reset);
        bg_setting_btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gif_png = sharedPreferences.getString("gif/png", "png");
                File file = new File(context.getFilesDir()+"/background."+gif_png);
                file.delete();
                CustomToast.toast(context,activity,context.getString(R.string.img_reset_finish));
                BackgroundReload.BackgroundReload(context,activity);
                BackgroundReload.BackgroundReload(context,view);
            }
        });

        // Translate -- U MUST NOT DELETE ANYTHING
        langList = new String[]{getString(R.string.zh_hk),getString(R.string.zh_cn),getString(R.string.en_us),getString(R.string.ru_ru),getString(R.string.ja_jp),getString(R.string.fr_fr),getString(R.string.uk_ua),getString(R.string.pt_pt),getString(R.string.de_de),getString(R.string.sp_sp),getString(R.string.vi_vi)};
        ArrayAdapter lang_aa = new ArrayAdapter(context,R.layout.spinner_item,langList);
        lang_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_2048);

        Spinner lang_sp = view.findViewById(R.id.lang_spinner);
        lang_sp.setAdapter(lang_aa);
        lang_sp.setSelection(sharedPreferences.getInt("curr_lang_pos",2));
        lang_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // https://blog.csdn.net/pigdreams/article/details/81277110
                // https://stackoverflow.com/questions/13397933/android-spinner-avoid-onitemselected-calls-during-initialization
                if(check_spinner >0){
                    switch (position) {
                        case 0:
                            editor.putString("curr_lang", "zh-HK");
                            break;
                        case 1:
                            editor.putString("curr_lang", "zh-CN");
                            break;
                        case 2:
                            editor.putString("curr_lang", "en-US");
                            break;
                        case 3:
                            editor.putString("curr_lang", "ru-RU");
                            break;
                        case 4:
                            editor.putString("curr_lang", "ja-JP");
                            break;
                        case 5:
                            editor.putString("curr_lang", "fr-FR");
                            break;
                        case 6:
                            editor.putString("curr_lang", "uk-UA");
                            break;
                        case 7:
                            editor.putString("curr_lang", "pt-PT");
                            break;
                        case 8:
                            editor.putString("curr_lang", "de-DE");
                            break;
                        case 9:
                            editor.putString("curr_lang", "sp-SP");
                            break;
                        case 10:
                            editor.putString("curr_lang", "vi-VI");
                            break;
                    }

                    editor.putInt("curr_lang_pos",position);
                    editor.putBoolean("PASS_JUST_CHANGED_THEME",true);
                    editor.apply();
                    LangUtils.getAttachBaseContext(context,position);
                    recreate();
                }
                check_spinner = check_spinner +1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        other_exit_confirm = view.findViewById(R.id.other_exit_confirm);
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

        //Other -> Exit Confirm
        other_random_theme_confirm = view.findViewById(R.id.other_random_theme_confirm);
        boolean other_random_theme = sharedPreferences.getBoolean("isRandomTheme",true);
        other_random_theme_confirm.setChecked(other_random_theme);
        other_random_theme_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(other_random_theme_confirm.isChecked() == false){
                    editor.putBoolean("isRandomTheme",false);
                    editor.apply();
                }else if(other_random_theme_confirm.isChecked() == true){
                    editor.putBoolean("isRandomTheme",true);
                    editor.apply();
                }
            }
        });

        other_dailymemo_enabled = view.findViewById(R.id.other_dailymemo_enabled);
        other_dailymemo_enabled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DailyMemo2048Widget dailyMemo2048Widget = new DailyMemo2048Widget();
                if(other_dailymemo_enabled.isChecked() == false){
                    editor.putBoolean("isDailyMemoEnabled",false);
                    editor.apply();
                    dailyMemo2048Widget.onDisabled(context);
                }else if(other_dailymemo_enabled.isChecked() == true){
                    editor.putBoolean("isDailyMemoEnabled",true);
                    editor.apply();
                    dailyMemo2048Widget.onEnabled(context);
                }
            }
        });

        //Other -> Item Base Name -> DEBUG ONLY
        other_item_eng_name = view.findViewById(R.id.other_item_eng_name);
        if (BuildConfig.FLAVOR.equals("dev") || BuildConfig.FLAVOR.equals("beta")){
            other_item_eng_name.setVisibility(View.VISIBLE);
        }
        boolean isBaseNameDisplay = sharedPreferences.getBoolean("isBaseNameDisplay",false);
        other_item_eng_name.setChecked(isBaseNameDisplay);
        other_item_eng_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(other_item_eng_name.isChecked() == false){
                    editor.putBoolean("isBaseNameDisplay",false);
                    editor.apply();
                }else if(other_item_eng_name.isChecked() == true){
                    editor.putBoolean("isBaseNameDisplay",true);
                    editor.apply();
                }
            }
        });

        // Resource Download
        Button bg_download_base = view.findViewById(R.id.bg_download_base);
        Button bg_download_update = view.findViewById(R.id.bg_download_update);
        Button bg_download_reset = view.findViewById(R.id.bg_download_reset);
        Button bg_download_delete = view.findViewById(R.id.bg_download_delete);


        if (BuildConfig.FLAVOR.equals("dev") || BuildConfig.FLAVOR.equals("beta")){
            bg_download_reset.setVisibility(View.VISIBLE);
            //bg_download_delete.setVisibility(View.VISIBLE);
        }
        bg_download_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().remove("lastUpdateUnix").apply();
                CustomToast.toast(context,activity,"Cleaned Download Unix Timestamp");
            }
        });

        bg_download_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context.getFilesDir().isDirectory()){
                    context.getFilesDir().delete();
                    sharedPreferences.edit().remove("downloadBase").apply();
                    CustomToast.toast(context,activity,"Deleted All Resources");
                }
            }
        });

        bg_download_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {

                        check_updates();
                        return null;
                    }
                }.execute();
            }
        });

        bg_download_base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> downloadList = new ArrayList<>();
                downloadList.add(ItemRss.SERVER_DOWNLOAD_ROOT+baseFileName);

                Dialog2048 dialog2048 = new Dialog2048();
                dialog2048.setup(context,activity);
                dialog2048.updateMax(getRemoteFileSizeA(downloadList));
                dialog2048.mode(Dialog2048.MODE_DOWNLOAD_BASE_DESK);
                dialog2048.show();

                dialog2048.getPositiveBtn().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2048.dismiss();

                        new DownloadAndUnzipTask(context,activity,downloadList,context.getFilesDir().getAbsolutePath()).execute();
                    }
                });

                dialog2048.getNegativeBtn().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2048.dismiss();
                    }
                });
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
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                final int radius_circ = 360;
                final int margin_circ = 0;
                final Transformation transformation_circ = new RoundedCornersTransformation(radius_circ, margin_circ);

                Picasso.get()
                        .load (item_rss.getCharByName(icon_name_final,context)[3]).resize((int) (40*displayMetrics.density),(int) (40*displayMetrics.density)).transform(transformation_circ)
                        .error (R.drawable.paimon_full)
                        .into ((ImageView) viewPager0.findViewById(R.id.memo_user_icon));
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

        TextView card_userstat = viewX.findViewById(R.id.card_userstat);
        TextView card_username = viewX.findViewById(R.id.card_username);
        TextView card_lvl_tv = viewPager4.findViewById(R.id.card_lvl_tv);

        card_username.setText(dailyMemo.getNickname(context));
        card_userstat.setText(dailyMemo.getServerLocaleName(context)+" -- "+sharedPreferences.getString("genshin_uid","-1"));
        card_lvl_tv.setText(dailyMemo.getLevel(context));
        card_userstat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Voc-夜芷冰#2512
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Genshin UID", sharedPreferences.getString("genshin_uid","-1"));
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context, getString(R.string.copied), Toast.LENGTH_SHORT).show();
            }
        });

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            height = (int) height/4;
        }
        Picasso.get()
                .load (item_rss.getCharByName(sharedPreferences.getString("card_name","Klee"), context)[4])
                .resize(width, height)
                .error (R.drawable.unknown_card)
                .into (card_bg);

        card_bg.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Picasso.get()
                .load (dailyMemo.getIcon(context))
                .transform(transformation_circ)
                .fit()
                .error (R.drawable.paimon_lost)
                .into (card_char_ico);

        ImageView card_char_bg = viewX.findViewById(R.id.card_char_bg);
        card_char_bg.setImageResource(item_rss.getCharRare2048ByInt(sharedPreferences.getInt("icon_rare",5)));

        ConstraintLayout card_cl = viewX.findViewById(R.id.card_cl);
        card_cl.setMinHeight(height);

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
                    .load (item_rss.getCharByName(baseName, context)[4])
                    .fit()
                    .error (R.drawable.unknown_card)
                    .into (card_bg);
            card_name_final = baseName;
        }else{
            Picasso.get()
                    .load (dailyMemo.getIcon(context))
                    .transform(transformation_circ)
                    .fit()
                    .error (R.drawable.paimon_lost)
                    .into (card_char_ico);
            icon_name_final = baseName;
            icon_rare_final = rare;
        }

        card_char_bg.setImageResource(item_rss.getCharRare2048ByInt(icon_rare_final));

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
                        .load (css.getCharByName(sharedPreferences.getString("card_name","Klee"),context)[4])
                        .resize(width_curr,height)
                        .error (R.drawable.paimon_lost)
                        .into ((ImageView) viewPager4.findViewById(R.id.card_bg));

                ((ImageView) viewPager4.findViewById(R.id.card_bg)).setScaleType(ImageView.ScaleType.CENTER_CROP);

                Picasso.get()
                        .load (dailyMemo.getIcon(context))
                        .transform(transformation_circ)
                        .fit()
                        .error (R.drawable.paimon_lost)
                        .into ((ImageView) viewPager4.findViewById(R.id.card_char_ico));

                ImageView card_char_bg = viewPager4.findViewById(R.id.card_char_bg);
                card_char_bg.setImageResource(css.getCharRare2048ByInt(sharedPreferences.getInt("icon_rare",5)));

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
                changeLog.show(context,activity);
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

        NestedScrollView sc_root = (NestedScrollView) viewPager0.findViewById (R.id.sc_root);
        sc_root.setFillViewport (true);

        asc_tablayout = viewPager0.findViewById(R.id.asc_tablayout);
        mIndicator = viewPager0.findViewById(R.id.indicator);
        int itemNum = 2;
        viewPagerASC = viewPager0.findViewById(R.id.asc_vp);
        viewPagerASC.setAdapter(new MyViewPagerAdapter(viewPager_ASC_List));
        viewPagerASC.setIsLinearLayout(true);
        asc_switch = viewPager0.findViewById(R.id.asc_switch);
        ImageView asc_switch_char = viewPager0.findViewById(R.id.asc_switch_char);
        ImageView asc_switch_weapon = viewPager0.findViewById(R.id.asc_switch_weapon);

        asc_tablayout.removeAllTabs();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        /*
        int[] itemImageArray = new int[]{R.drawable.ic_2048_team_char,R.drawable.ic_2048_team_weapon};
        int[] itemImageSelectedArray = new int[]{R.drawable.ic_2048_team_char_selected,R.drawable.ic_2048_team_weapon_selected};

        for (int x = 0 ; x < itemNum ; x++){
            View view1 = activity.getLayoutInflater().inflate(R.layout.item_custom_tab, null);
            ImageView ico_img = view1.findViewById(R.id.icon);
            ico_img.setImageResource(itemImageArray[x]);
            TextView ico_tv = view1.findViewById(R.id.name);
            ico_tv.setVisibility(View.GONE);
            ico_img.setPadding((int) (-displayMetrics.density*6),(int) (-displayMetrics.density*6),(int) (-displayMetrics.density*6),(int) (-displayMetrics.density*6));
            ico_img.getLayoutParams().width = ico_img.getLayoutParams().height;
            asc_tablayout.addTab(asc_tablayout.newTab().setCustomView(view1).setId(x));
        }

        asc_tablayout.getLayoutParams().width = (int) (displayMetrics.density*(52*2));

        asc_tablayout.post(new Runnable() {
            @Override
            public void run() {
                indicatorWidth = asc_tablayout.getWidth() / asc_tablayout.getTabCount();

                //Assign new width
                FrameLayout.LayoutParams indicatorParams = (FrameLayout.LayoutParams) mIndicator.getLayoutParams();
                indicatorParams.width = indicatorWidth;
                mIndicator.setLayoutParams(indicatorParams);
            }
        });

        asc_tablayout.setTabIndicatorFullWidth(false);
        viewPagerASC.setCurrentItem(0);
        asc_tablayout.selectTab(asc_tablayout.getTabAt(0));

        View view1 = asc_tablayout.getTabAt(0).getCustomView();
        ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
        tab_icon.setImageResource(itemImageSelectedArray[0]);

        asc_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
                tab_icon.setImageResource(itemImageSelectedArray[tab.getPosition()]);
                viewPagerASC.setCurrentItem(tab.getPosition());

                if (!firstSelectASC || (System.currentTimeMillis() - period > 3000)){
                    id = tab.getId();
                    period = System.currentTimeMillis();
                    firstSelectASC = true;
                }else if(firstSelectASC && tab.getId() != id){
                    firstSelectASC = false;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
                tab_icon.setImageResource(itemImageArray[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (!firstSelectASC || (System.currentTimeMillis() - period > 3000)){
                    id = tab.getId();
                    period = System.currentTimeMillis();
                    firstSelectASC = true;
                }else if(firstSelectASC && tab.getId() != id){
                    firstSelectASC = false;
                }else if (firstSelectASC && (tab.getId() == id) && (System.currentTimeMillis() - period < 3000)){
                    ((NestedScrollView) viewPager0.findViewById(R.id.sc_root)).smoothScrollTo(0,0);
                    firstSelectASC = false;
                }
            }
        });
         */

        asc_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    asc_switch_char.setImageResource(R.drawable.ic_2048_team_char);
                    asc_switch_weapon.setImageResource(R.drawable.ic_2048_team_weapon_selected);
                    viewPagerASC.setCurrentItem(1);
                }else{
                    asc_switch_char.setImageResource(R.drawable.ic_2048_team_char_selected);
                    asc_switch_weapon.setImageResource(R.drawable.ic_2048_team_weapon);
                    viewPagerASC.setCurrentItem(0);
                }
            }
        });

        viewPagerASC.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float positionOffset, int positionOffsetPx) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mIndicator.getLayoutParams();
                //Multiply positionOffset with indicatorWidth to get translation
                float translationOffset =  (positionOffset+i) * (indicatorWidth) ;
                params.leftMargin = (int) translationOffset;
                mIndicator.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                asc_tablayout.selectTab(asc_tablayout.getTabAt(position));
                asc_switch.setChecked(position==1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    };

    private void char_list_reload() {
        Log.wtf("DAAM","YEE");
        String name ,element,weapon,nation,sex,mainStat;
        int rare,isComing;
        //charactersList.clear();

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
                //charactersList.add(characters);

                IconCard iconCard = new IconCard();
                iconCard.setItemName(name);
                iconCard.setItemRare(rare);
                iconCard.setItemBaseName(name);
                iconCardList_ICON.add(iconCard);
                iconCardList_CARD.add(iconCard);
            }
            //mAdapter.filterList(charactersList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void weapon_list_reload() {
        Log.wtf("DAAM", "YEE");
        //weaponsList.clear();
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
                //weaponsList.add(weapons);
            }
            //mWeaponAdapter.filterList(weaponsList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void artifact_list_reload() {
        Log.wtf("DAAM", "YEE");
        //artifactsList.clear();
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
                //artifactsList.add(artifacts);
            }
            //mArtifactAdapter.filterList(artifactsList);
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
        switch (location) {
            case "America":
                c.setTimeZone(TimeZone.getTimeZone("GMT-5"));
                position = 0;
                break;
            case "Europe":
                c.setTimeZone(TimeZone.getTimeZone("GMT+1"));
                position = 1;
                break;
            case "Asia":
                c.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                position = 2;
                break;
            case "HK_TW_MO":
                c.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                position = 3;
                break;
            case "天空島":
                c.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                position = 4;
                break;
            case "世界樹":
                c.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                position = 5;
                break;
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

        bday_reload(c);

    }

    public void bday_reload(Calendar c){
        ItemRss.Birthday birthday = new ItemRss.Birthday();
        birthday.birthInit(context);
        ArrayList<ItemRss.Birthday> birthdayList = birthday.upcomingBirthday(c);
        String char_name = "EMPTY";

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
        final int margin = 0;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);

        //System.out.println(birthday.upcomingBirthday(c).get(0).getSumOfBirth() + " : "+ ((moy+1)*100 + dom));

        if(birthdayList.get(0).getSumOfBirth() == ((moy+1)*100 + dom)){
            char_name = birthdayList.get(0).getCharName();
            birth_title_normal.setVisibility(View.GONE);
            birth_title_special.setVisibility(View.VISIBLE);
            birth_title_special.setText(context.getString(R.string.happy_birthday)+" "+context.getString(css.getCharByName(char_name,context)[1]));
        }else{
            birth_title_special.setVisibility(View.GONE);
            birth_title_normal.setVisibility(View.VISIBLE);
        }

        int[] imageArray = {R.id.bday_next1,R.id.bday_next2,R.id.bday_next3,R.id.bday_next4,R.id.bday_next5};
        int[] tvArray = {R.id.bday_next_tv1,R.id.bday_next_tv2,R.id.bday_next_tv3,R.id.bday_next_tv4,R.id.bday_next_tv5};

        for (int x = 0 ; x < 5; x++){
            String nextBirthCharName = birthdayList.get(x).getCharName();
            int nextBirthCharMonth =  birthdayList.get(x).getMonthOfBirth();
            int nextBirthCharDay = birthdayList.get(x).getDayOfBirth();
            int nextBirthCharRare = birthdayList.get(x).getRare();

            ImageView img = viewPager0.findViewById(imageArray[x]);
            TextView tv = viewPager0.findViewById(tvArray[x]);
            Picasso.get()
                    .load (css.getCharByName(nextBirthCharName,context)[3])
                    .transform(transformation)
                    .resize((int) (64*displayMetrics.density), (int) (64*displayMetrics.density))
                    .error (R.drawable.paimon_lost)
                    .into (img);

            img.setBackgroundResource(css.getRare2048ByInt(nextBirthCharRare));
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Characters_Info_2048().setup(nextBirthCharName,context, activity);
                }
            });

            if ((moy+1 == nextBirthCharMonth) && (dom == nextBirthCharDay)){
                tv.setText(context.getString(R.string.today));
            }else{
                tv.setText(css.getLocaleBirth(String.valueOf(nextBirthCharMonth)+"/"+String.valueOf(nextBirthCharDay),context,true));
            }
        }
    }

    public void char_reload(int dow){
        LinearLayout char_ll = viewPagerChar.findViewById(R.id.asc_list);
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
            String json_base = ItemRss.LoadAssestData(context,"db/char/char_require_asc_skill.json");
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

                        Picasso.get().load(css.getCharByName(name,context)[3]).fit().transform(roundedCornersTransformation).into(img);
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
            viewPagerASC.getLayoutParams().height = char_ll.getLayoutParams().height;
        }
    }
    public void weapon_reload(int dow){
        LinearLayout weapon_ll = viewPagerWeapon.findViewById(R.id.asc_list);
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
            String json_base = ItemRss.LoadAssestData(context,"db/weapons/weapon_require_asc.json");
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

                        Picasso.get().load(css.getWeaponByName(name)[1]).fit().into(img);

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

                        Picasso.get().load(css.getWeaponByName(name)[1]).fit().transform(roundedCornersTransformation).into(img);
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
            viewPagerASC.getLayoutParams().height = weapon_ll.getLayoutParams().height;
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
    public void startTCGInfo(FrameLayout tcg_card,TCG tcg, int tcg_width, Activity activity, int[] screenPos){
        TCG_Info_2048_OLD tcgI = new TCG_Info_2048_OLD();
        tcgI.setup(tcg_card,tcg,tcg_width,context,activity,sharedPreferences,editor, screenPos);
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
            }else if(tag.contains("pt-")){
                editor.putString("curr_lang","pt-PT"); editor.putInt("curr_lang_pos",7);x=7;
            }else if(tag.contains("de-")){
                editor.putString("curr_lang","de-DE"); editor.putInt("curr_lang_pos",8);x=8;
            }else if(tag.contains("sp-")){
                editor.putString("curr_lang","sp-SP"); editor.putInt("curr_lang_pos",9);x=9;
            }else if(tag.contains("vi-")){
                editor.putString("curr_lang","vi-VI"); editor.putInt("curr_lang_pos",10);x=10;
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
        //bday_reload();
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


    public static long getRemoteFileSizeB (String[] urlSTR) {
        URL url = null;
        long size = 0;
        for (int x = 0 ;x< urlSTR.length ; x++){
            System.out.println(urlSTR[x]);
            try {
                url = new URL(urlSTR[x]);
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
        String url = ItemRss.SERVER_DOWNLOAD_ROOT+"update.json";
        if (BuildConfig.FLAVOR.equals("dev")){
            //if (BuildConfig.FLAVOR.equals("dev") || BuildConfig.FLAVOR.equals("beta")){
            url = ItemRss.SERVER_DOWNLOAD_ROOT+"update_dev.json";
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
                    array_download.add(ItemRss.SERVER_DOWNLOAD_ROOT + fileName);
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
                        //DownloadTask downloadTask = new DownloadTask();
                        //downloadTask.startA(array_download,array_fileName,array_SfileName,context,activity);
                        new DownloadAndUnzipTask(context,activity,array_download,context.getFilesDir().getAbsolutePath()).execute();
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
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        sharedPreferences.edit().putBoolean("appStopped",true).apply();
        changeLog.dismiss();
        super.onDestroy();
    }
}
