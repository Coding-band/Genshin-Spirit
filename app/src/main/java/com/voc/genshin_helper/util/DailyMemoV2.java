package com.voc.genshin_helper.util;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static com.voc.genshin_helper.util.LogExport.DAILYMEMO;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.ui.MMXLVIII.DailyMemo2048Service;
import com.voc.genshin_helper.ui.MMXLVIII.Desk2048;
import com.voc.genshin_helper.util.hoyolab.HoyolabConstants;
import com.voc.genshin_helper.util.hoyolab.HoyolabCookie;
import com.voc.genshin_helper.util.hoyolab.hooks.HoyolabHooks;
import com.voc.genshin_helper.util.hoyolab.hooks.HoyolabUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class DailyMemoV2 {

    /**
     * 即時便簽
     */
    String nickname = "N/A";
    int level = 1;
    String server = "os_cht";
    String icon = "Hu Tao";
    int resin_curr = 0;
    int resin_remain_time = 0;
    int currency_curr = 0;
    int currency_max = 0;
    int currency_remain_time = 0;
    int mission_done = 0;
    int mission_total = 4;
    boolean mission_claim = false;
    int transformer_recovery_time = 0;
    int weekboss_30 = 0;
    int weekboss_30_max = 3;
    String expedition1_name = "N/A";
    String expedition2_name = "N/A";
    String expedition3_name = "N/A";
    String expedition4_name = "N/A";
    String expedition5_name = "N/A";
    int expedition1_remain_time = 0;
    int expedition2_remain_time = 0;
    int expedition3_remain_time = 0;
    int expedition4_remain_time = 0;
    int expedition5_remain_time = 0;

    /**
     * UI
     */

    CardView memo_card, memo_card_ext;
    TextView memo_user_name, memo_user_server;
    ImageView memo_user_icon, memo_ext_btn, memo_setting_btn,memo_logout_btn;
    ImageView memo_item1_ico, memo_item2_ico, memo_item3_ico, memo_item4_ico;
    TextView memo_item1_curr, memo_item2_curr, memo_item3_curr, memo_item4_curr;
    TextView memo_item1_max, memo_item2_max, memo_item3_max, memo_item4_max;
    TextView memo_item1_time,memo_item2_time,memo_item3_time,memo_item4_time;
    ImageView memo_expe1_ico,memo_expe2_ico,memo_expe3_ico,memo_expe4_ico,memo_expe5_ico;
    TextView memo_expe1_time,memo_expe2_time,memo_expe3_time,memo_expe4_time,memo_expe5_time;
    ImageView memo_expe1_tick,memo_expe2_tick,memo_expe3_tick,memo_expe4_tick,memo_expe5_tick;
    ProgressBar memo_expe1_pb,memo_expe2_pb,memo_expe3_pb,memo_expe4_pb,memo_expe5_pb;
    Spinner server_spinner;
    LinearLayout memo_status_ll, memo_exped_ll;

    ImageButton memo_logoff_btn, memo_noti_btn;

    /**
     * Method
     */
    final String TAG = "DailyMemo.java";
    Context context;
    Activity activity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean isExtOut = true;
    boolean isShowingStatus = true;
    DisplayMetrics displayMetrics;
    ItemRss item_rss ;
    Handler handler ;

    String uid_final = "N/A";
    String token_final = "N/A";
    String cookie_token_v2 = "N/A";
    String account_mid_v2 = "N/A";
    String account_id_v2 = "N/A";
    String ltmid_v2 = "N/A";
    String hoyoServer = "global";
    String genshin_uid_final = "-1";

    static final int GLOBAL = 1;
    static final int BBS = 2;
    static final int TOKEN_GET = 3;
    boolean isIdGetDone = false;

    public static final int GAME = 2048;
    public static final int MATERIAL = 2022;

    public static final int SEC_OF_CHECK_PEIROD = 300000; // 5 mins per check
    private Handler refreshRegular = new Handler();
    private Runnable refreshRunnable = null;

    CustomTabsIntent customTabsIntent;
    public static final int CHROME_CUSTOM_TAB_REQUEST_CODE = 4196;
    public static final String CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";  // Change when in stable

    public void setup(Context context, Activity activity,View view){
        this.context = context;
        this.activity = activity;
        sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        item_rss = new ItemRss();

        memo_card = view.findViewById(R.id.memo_card);
        memo_user_name = view.findViewById(R.id.memo_user_name);
        memo_user_server = view.findViewById(R.id.memo_user_server);
        memo_user_icon = view.findViewById(R.id.memo_user_icon);
        memo_setting_btn = view.findViewById(R.id.memo_setting_btn);
        memo_logout_btn = view.findViewById(R.id.memo_logout_btn);
        memo_item1_ico = view.findViewById(R.id.memo_item1_ico);
        memo_item2_ico = view.findViewById(R.id.memo_item2_ico);
        memo_item3_ico = view.findViewById(R.id.memo_item3_ico);
        memo_item4_ico = view.findViewById(R.id.memo_item4_ico);
        memo_item1_time = view.findViewById(R.id.memo_item1_time);
        memo_item2_time = view.findViewById(R.id.memo_item2_time);
        memo_item3_time = view.findViewById(R.id.memo_item3_time);
        memo_item4_time = view.findViewById(R.id.memo_item4_time);
        memo_item1_curr = view.findViewById(R.id.memo_item1_curr);
        memo_item2_curr = view.findViewById(R.id.memo_item2_curr);
        memo_item3_curr = view.findViewById(R.id.memo_item3_curr);
        memo_item4_curr = view.findViewById(R.id.memo_item4_curr);
        memo_status_ll = view.findViewById(R.id.memo_status_ll);
        memo_exped_ll = view.findViewById(R.id.memo_exped_ll);
        memo_item1_max = view.findViewById(R.id.memo_item1_max);
        memo_item2_max = view.findViewById(R.id.memo_item2_max);
        memo_item3_max = view.findViewById(R.id.memo_item3_max);
        memo_item4_max = view.findViewById(R.id.memo_item4_max);
        memo_expe1_ico = view.findViewById(R.id.memo_expe1_ico);
        memo_expe2_ico = view.findViewById(R.id.memo_expe2_ico);
        memo_expe3_ico = view.findViewById(R.id.memo_expe3_ico);
        memo_expe4_ico = view.findViewById(R.id.memo_expe4_ico);
        memo_expe5_ico = view.findViewById(R.id.memo_expe5_ico);
        memo_expe1_time = view.findViewById(R.id.memo_expe1_time);
        memo_expe2_time = view.findViewById(R.id.memo_expe2_time);
        memo_expe3_time = view.findViewById(R.id.memo_expe3_time);
        memo_expe4_time = view.findViewById(R.id.memo_expe4_time);
        memo_expe5_time = view.findViewById(R.id.memo_expe5_time);
        memo_expe1_tick = view.findViewById(R.id.memo_expe1_tick);
        memo_expe2_tick = view.findViewById(R.id.memo_expe2_tick);
        memo_expe3_tick = view.findViewById(R.id.memo_expe3_tick);
        memo_expe4_tick = view.findViewById(R.id.memo_expe4_tick);
        memo_expe5_tick = view.findViewById(R.id.memo_expe5_tick);

        if (memo_card_ext != null){
            Animation ani = new ShowAnim(memo_card_ext,-112,false);
            ani.setDuration(100);
            memo_card_ext.startAnimation(ani);
        }


        memo_card_ext = view.findViewById(R.id.memo_card_ext);
        memo_ext_btn = view.findViewById(R.id.memo_ext_btn);
        memo_expe1_pb = view.findViewById(R.id.memo_expe1_pb);
        memo_expe2_pb = view.findViewById(R.id.memo_expe2_pb);
        memo_expe3_pb = view.findViewById(R.id.memo_expe3_pb);
        memo_expe4_pb = view.findViewById(R.id.memo_expe4_pb);
        memo_expe5_pb = view.findViewById(R.id.memo_expe5_pb);

        displayMetrics = new DisplayMetrics();
        if (activity != null){
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }

        updateData();

        refreshRunnable = new Runnable() {
            @Override
            public void run() {
                if (sharedPreferences.getString("genshin_uid","-1").equals("-1")) return;
                refreshData(new HoyolabHooks().genshinNoteData(context).toString());
                refreshRegular.postDelayed(refreshRunnable, SEC_OF_CHECK_PEIROD);
            }
        };

        if (!sharedPreferences.getString("genshin_uid","-1").equals("-1")){
            if (System.currentTimeMillis() - sharedPreferences.getLong("dailyMemoUnix",0) >= SEC_OF_CHECK_PEIROD){
                sharedPreferences.edit().putLong("dailyMemoUnix",System.currentTimeMillis()).apply();
                refreshData(new HoyolabHooks().genshinNoteData(context).toString());
                refreshRegular.removeCallbacks(refreshRunnable);
                refreshRegular.postDelayed(refreshRunnable,SEC_OF_CHECK_PEIROD);
            }else{
                refreshData(sharedPreferences.getString("dailyMemoDataTMP",HoyolabConstants.HOYOLAB_DAILYMEMO_EMPTY));
            }
        }else{
            refreshData(sharedPreferences.getString("dailyMemoDataTMP",HoyolabConstants.HOYOLAB_DAILYMEMO_EMPTY));
        }
    }

    public String getNickname(Context context){
        if (nickname != null){
            return nickname;
        }else {return context.getString(R.string.unknown);}
    }
    public String getServerIdName(Context context){
        return context.getSharedPreferences("user_info",Context.MODE_PRIVATE).getString(HoyolabConstants.HOYOLAB_SERVER_ID, HoyolabConstants.GAME_SERVERS.UNKNOWN.getServerIdName());
    }
    public String getServerLocaleName(Context context){
        return context.getString(HoyolabConstants.GAME_SERVERS.getEnumByIdName(getServerIdName(context)).getServerTranslateName());
    }
    public String getLevel(Context context){
        if (level != 0){
            return String.valueOf(level);
        }else {return context.getString(R.string.unknown);}
    }
    public String getIcon(Context context){
        return context.getSharedPreferences("user_info",Context.MODE_PRIVATE).getString("icon_name","N/A");
    }

    public void updateData(){
        final int radius_circ_siptik_ico = 120;
        final int margin_circ_siptik_ico = 0;
        final com.squareup.picasso.Transformation transformation_circ_siptik_ico = new RoundedCornersTransformation(radius_circ_siptik_ico, margin_circ_siptik_ico);


        memo_user_name.setText(nickname);
        sharedPreferences.edit().putString("genshin_username",nickname).apply();

        memo_user_server.setText(sharedPreferences.getString("genshin_uid","-1")+" - Lv."+String.valueOf(level));

        memo_item1_curr.setText(String.valueOf(resin_curr));
        memo_item2_curr.setText(String.valueOf(currency_curr));
        memo_item3_curr.setText(String.valueOf(mission_done));
        memo_item4_curr.setText(String.valueOf(weekboss_30));
        memo_item2_max.setText("/"+String.valueOf(currency_max));
        memo_item1_time.setText(prettyTime(resin_remain_time));
        memo_item2_time.setText(prettyTime(currency_remain_time));
        memo_item4_time.setText(context.getString(R.string.memo_available_weekboss_change).replace("{%1}",String.valueOf((weekboss_30))));

        if (mission_claim == true && memo_item3_time != null){
            memo_item3_time.setText(context.getString(R.string.claimed));
        }else{
            memo_item3_time.setText(context.getString(R.string.unclaimed));
        }

        Picasso.get()
                .load (icon)
                .resize((int) (48*displayMetrics.density),(int) (48*displayMetrics.density))
                .transform(transformation_circ_siptik_ico)
                .error (R.drawable.paimon_lost)
                .into (memo_user_icon);

        Picasso.get()
                .load (expedition1_name)
                .resize((int) (60*displayMetrics.density),(int) (60*displayMetrics.density))
                .transform(transformation_circ_siptik_ico)
                .error (R.drawable.paimon_lost)
                .into (memo_expe1_ico);
        Picasso.get()
                .load (expedition2_name)
                .resize((int) (60*displayMetrics.density),(int) (60*displayMetrics.density))
                .transform(transformation_circ_siptik_ico)
                .error (R.drawable.paimon_lost)
                .into (memo_expe2_ico);
        Picasso.get()
                .load (expedition3_name)
                .resize((int) (60*displayMetrics.density),(int) (60*displayMetrics.density))
                .transform(transformation_circ_siptik_ico)
                .error (R.drawable.paimon_lost)
                .into (memo_expe3_ico);
        Picasso.get()
                .load (expedition4_name)
                .resize((int) (60*displayMetrics.density),(int) (60*displayMetrics.density))
                .transform(transformation_circ_siptik_ico)
                .error (R.drawable.paimon_lost)
                .into (memo_expe4_ico);
        Picasso.get()
                .load (expedition5_name)
                .resize((int) (60*displayMetrics.density),(int) (60*displayMetrics.density))
                .transform(transformation_circ_siptik_ico)
                .error (R.drawable.paimon_lost)
                .into (memo_expe5_ico);

        if (memo_expe1_pb != null){
            memo_expe1_pb.setProgress(expedition1_remain_time);
            memo_expe2_pb.setProgress(expedition2_remain_time);
            memo_expe3_pb.setProgress(expedition3_remain_time);
            memo_expe4_pb.setProgress(expedition4_remain_time);
            memo_expe5_pb.setProgress(expedition5_remain_time);
        }

        memo_expe1_time.setText(prettyTime(expedition1_remain_time));
        memo_expe2_time.setText(prettyTime(expedition2_remain_time));
        memo_expe3_time.setText(prettyTime(expedition3_remain_time));
        memo_expe4_time.setText(prettyTime(expedition4_remain_time));
        memo_expe5_time.setText(prettyTime(expedition5_remain_time));

        if (expedition1_remain_time == 0){
            memo_expe1_time.setVisibility(View.GONE);
            memo_expe1_tick.setVisibility(View.VISIBLE);
        }else{
            memo_expe1_time.setVisibility(View.VISIBLE);
            memo_expe1_tick.setVisibility(View.GONE);
        }

        if (expedition2_remain_time == 0){
            memo_expe2_time.setVisibility(View.GONE);
            memo_expe2_tick.setVisibility(View.VISIBLE);
        }else{
            memo_expe2_time.setVisibility(View.VISIBLE);
            memo_expe2_tick.setVisibility(View.GONE);
        }

        if (expedition3_remain_time == 0){
            memo_expe3_time.setVisibility(View.GONE);
            memo_expe3_tick.setVisibility(View.VISIBLE);
        }else{
            memo_expe3_time.setVisibility(View.VISIBLE);
            memo_expe3_tick.setVisibility(View.GONE);
        }

        if (expedition4_remain_time == 0){
            memo_expe4_time.setVisibility(View.GONE);
            memo_expe4_tick.setVisibility(View.VISIBLE);
        }else{
            memo_expe4_time.setVisibility(View.VISIBLE);
            memo_expe4_tick.setVisibility(View.GONE);
        }

        if (expedition5_remain_time == 0){
            memo_expe5_time.setVisibility(View.GONE);
            memo_expe5_tick.setVisibility(View.VISIBLE);
        }else{
            memo_expe5_time.setVisibility(View.VISIBLE);
            memo_expe5_tick.setVisibility(View.GONE);
        }
        memo_ext_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
                fadeIn.setDuration(500);

                Animation fadeOut = new AlphaAnimation(1, 0);
                fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
                fadeOut.setDuration(500);

                if (isShowingStatus){
                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            memo_exped_ll.setVisibility(View.VISIBLE);
                            memo_exped_ll.startAnimation(fadeIn);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            memo_status_ll.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    memo_status_ll.startAnimation(fadeOut);
                }else{
                    fadeIn.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            memo_status_ll.setVisibility(View.VISIBLE);
                            memo_exped_ll.startAnimation(fadeOut);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            memo_exped_ll.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    memo_status_ll.startAnimation(fadeIn);
                }

                isShowingStatus = !isShowingStatus;
            }
        });

        memo_setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting_new();
            }
        });
        memo_logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanCookies(CookieManager.getInstance(),v);
                editor.remove("icon_name").remove("genshin_uid").remove("genshin_username").remove("genshin_level").apply();
                for (String key : HoyolabCookie.HOYOLAB_V2_KEY_GROUP){editor.remove(key).apply();}

                sharedPreferences.edit().putString("dailyMemoDataTMP",HoyolabConstants.HOYOLAB_DAILYMEMO_EMPTY).apply();
                refreshData(HoyolabConstants.HOYOLAB_DAILYMEMO_EMPTY);
                System.out.println("WHERE ARE U ?");
            }
        });

        if (context instanceof Desk2048){
            ((Desk2048) context).refreshPaimon();
        }

    }
    public void setting_new(){
        Dialog dialogS = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.fragment_memo_setting_new, null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        LinearLayout dialog_hoyoverse = view.findViewById(R.id.dialog_hoyoverse);
        LinearLayout dialog_third_party = view.findViewById(R.id.dialog_third_party);
        TextView dialog_miyouse = view.findViewById(R.id.dialog_miyouse);

        dialog_hoyoverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.fragment_memo_hoyoverse_new, null);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                FrameLayout ok = view.findViewById(R.id.ok);
                Button token_btn = view.findViewById(R.id.token_btn);
                server_spinner = view.findViewById(R.id.setting_server_spinner);
                token_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //cleanCookies(CookieManager.getInstance(),v);
                        getCookiesFromLoginPage(GLOBAL);
                        if (dialogS.isShowing()) dialogS.dismiss();
                    }
                });
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Refreshing !", Toast.LENGTH_SHORT).show();
                        refreshData(new HoyolabHooks().genshinNoteData(context).toString());

                        if (!sharedPreferences.getString("genshin_uid","-1").equals("-1")) {
                            refreshRegular.removeCallbacks(refreshRunnable);
                            refreshRegular.postDelayed(refreshRunnable,SEC_OF_CHECK_PEIROD);
                        }

                        if (dialogS.isShowing()) dialogS.dismiss();
                        if (dialog.isShowing()) dialog.dismiss();
                    }
                });

                Window dialogWindowX = activity.getWindow();
                dialogWindowX.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                // 2O48 DESIGN
                dialogWindowX.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                dialogWindowX.setStatusBarColor(context.getColor(R.color.status_bar_2048));
                dialogWindowX.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

                /** Method of dialog */
                dialog.setContentView(view);
                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                // 2O48 DESIGN
                dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_2048));
                dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

                lp.width = MATCH_PARENT;
                lp.height = MATCH_PARENT;
                lp.gravity = Gravity.CENTER;
                dialogWindow.setAttributes(lp);
                dialog.show();
            }
        });

        dialog_third_party.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomToast.toast(context, view, "暫未開放 Not released yet");
            }
        });

        dialog_miyouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomToast.toast(context, view, "暫未開放 Not released yet");
            }
        });


        Window dialogWindowX = activity.getWindow();
        dialogWindowX.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 2O48 DESIGN
        dialogWindowX.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        dialogWindowX.setStatusBarColor(context.getColor(R.color.status_bar_2048));
        dialogWindowX.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

        /** Method of dialog */
        dialogS.setContentView(view);
        Window dialogWindow = dialogS.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        // 2O48 DESIGN
        dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_2048));
        dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

        lp.width = MATCH_PARENT;
        lp.height = MATCH_PARENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        dialogS.show();
    }

    private void tokenUpdate(String token_final, String uid_final, String token_final_v2, String uid_final_v2, String account_id_v2, String cookie_token_v2, String account_mid_v2, String ltmid_v2) {
        this.token_final = token_final;
        this.uid_final = uid_final;
        this.account_id_v2 = account_id_v2;
        this.cookie_token_v2 = cookie_token_v2;
        this.account_mid_v2 = account_mid_v2;
        this.ltmid_v2 = ltmid_v2;

        if(!token_final_v2.equals("N/A")){
            this.token_final = token_final_v2;
            this.uid_final = uid_final_v2;
        }
    }


    public void getCookiesFromLoginPage(int TYPE){
        Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View viewX = View.inflate(context, R.layout.fragment_web, null);
        switch (TYPE) {
            case GLOBAL: {
                viewX = View.inflate(context, R.layout.fragment_web, null);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                WebView webview = viewX.findViewById(R.id.webView);
                ImageView back_btn = viewX.findViewById(R.id.back_btn);

                webview.setWebViewClient(new WebViewClient() {
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });

                WebSettings webSettings = webview.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setSupportMultipleWindows(true);
                webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
                webSettings.setDomStorageEnabled(true);

                webview.loadUrl("https://act.hoyolab.com/app/community-game-records-sea/index.html#/ys");
                hoyoServer = "global";
                back_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CookieManager cookieManager = CookieManager.getInstance();
                        String cookies = cookieManager.getCookie("https://act.hoyolab.com/app/community-game-records-sea/index.html#/ys");
                        Toast.makeText(context, "Please wait for 10s", Toast.LENGTH_SHORT).show();
                        HoyolabCookie.updateCookie(context,cookies);

                        ArrayList<String> tmpList = new ArrayList<>();
                        ArrayList<HoyolabUser> uuidList = new HoyolabHooks().fetchUUIDList(context);
                        uuidList.forEach((user) -> {
                            if (user.getServer().getServerTranslateName() == R.string.unknown) return;
                            tmpList.add(user.getUsername()+" -- "+context.getString(user.getServer().getServerTranslateName())+" "+context.getString(R.string.lvl)+String.valueOf(user.getLevel()));
                        });


                        ArrayAdapter server_aa = new ArrayAdapter(context, R.layout.spinner_item_2048_always, tmpList);
                        server_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_2048);

                        server_spinner.setAdapter(server_aa);
                        server_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                editor.putString("genshin_username",uuidList.get(position).getUsername()).apply();
                                editor.putString("genshin_uid",uuidList.get(position).getUid()).apply();
                                editor.putInt("genshin_level",uuidList.get(position).getLevel()).apply();
                                editor.putString(HoyolabConstants.HOYOLAB_SERVER_ID,uuidList.get(position).getServer().getServerIdName()).apply();
                                editor.putLong("dailyMemoUnix",System.currentTimeMillis()).apply();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                        dialog.dismiss();
                    }
                });
                break;
            }
            case BBS: {
                viewX = View.inflate(context, R.layout.fragment_web, null);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                WebView webview = viewX.findViewById(R.id.webView);
                ImageView back_btn = viewX.findViewById(R.id.back_btn);

                webview.setWebViewClient(new WebViewClient() {
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        // webview自己加载URL，让后通知系统不需要HandleURL
                        view.loadUrl("https://bbs.mihoyo.com/ys");
                        return true;
                    }
                });

                WebSettings webSettings = webview.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setSupportMultipleWindows(true);
                webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
                webSettings.setDomStorageEnabled(true);


                webview.loadUrl("https://bbs.mihoyo.com/ys");
                hoyoServer = "mainland";
                back_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CookieManager cookieManager = CookieManager.getInstance();
                        String cookies = cookieManager.getCookie("https://bbs.mihoyo.com/ys");
                        Toast.makeText(context, "Please wait for 10s", Toast.LENGTH_SHORT).show();

                        System.out.println("cookies BASE : " + cookies);
                        if (cookies == null) return;
                        if (cookies.contains("ltuid_v2")) {

                            //System.out.println("HEY YOU ! WE SUCCESSED");
                            cookies = "{\"" + cookies + "\"}";
                            cookies = cookies.replace(" ", " \"").replace("=", "\":\"").replace(";", "\",").replace(".", "_");
                            //System.out.println("cookies DONE : "+cookies);
                            try {
                                JSONObject jsonObject = new JSONObject(cookies);
                                account_id_v2 = jsonObject.getString("account_id_v2");
                                uid_final = jsonObject.getString("ltuid_v2");
                                account_mid_v2 = jsonObject.getString("account_mid_v2");
                                ltmid_v2 = jsonObject.getString("ltmid_v2");

                            /*
                            new grabDataFromServer().execute(ItemRss.SERVER_REACT_ROOT+"dailyMemo_3.5/dailyMemoIdListPort.php?" +
                                    "hoyoUID="+uid_final+
                                    "&hoyoToken="+token_final+
                                    "&account_mid_v2="+account_mid_v2+
                                    "&ltmid_v2="+ltmid_v2
                            );
                            */
                            } catch (JSONException e) {
                                LogExport.export("DailyMemo", "getCookiesFromLoginPage -> webview.setWebViewClient.onPageFinished", e.getMessage(), context, DAILYMEMO);
                            }
                        }

                        dialog.dismiss();
                    }
                });
                break;
            }
        }

        Window dialogWindowX = activity.getWindow();
        dialogWindowX.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 2O48 DESIGN
        dialogWindowX.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        dialogWindowX.setStatusBarColor(context.getColor(R.color.status_bar_2048));
        dialogWindowX.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

        /** Method of dialog */
        dialog.setContentView(viewX);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        // 2O48 DESIGN
        dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_2048));
        dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

        lp.width = MATCH_PARENT;
        lp.height = MATCH_PARENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        dialog.show();

    }

    public void cleanCookies(CookieManager cookieManager, View view) {
        cookieManager.removeAllCookies(null);
        cookieManager.flush();
        CustomToast.toast(context,view,context.getString(R.string.clean_cookies_already));
    }

    public void refreshData(String str) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            if(!sharedPreferences.getString("genshin_uid","-1").equals("-1")){
                JSONObject playerInfoRoot = new HoyolabHooks().genshinPlayerData(context);

                if (playerInfoRoot != null && playerInfoRoot.has("role")){
                    JSONObject playerInfo = playerInfoRoot.getJSONObject("role");

                    nickname = playerInfo.getString("nickname");
                    level = playerInfo.getInt("level");
                    server = context.getString(
                            HoyolabConstants.GAME_SERVERS.getEnumByIdName(
                                    context.getSharedPreferences("user_info",Context.MODE_PRIVATE).getString(HoyolabConstants.HOYOLAB_SERVER_ID,HoyolabConstants.GAME_SERVERS.UNKNOWN.getServerIdName())
                            ).getServerTranslateName()
                    );
                    icon = playerInfo.getString("game_head_icon");
                }
            }else{

                icon = "N/A";
                nickname = context.getString(R.string.unknown);
                level = -1;
                server = context.getString(HoyolabConstants.GAME_SERVERS.UNKNOWN.getServerTranslateName());
            }

            LogExport.export("DailyMemo2048Service","grabIdFromServer.[REGULAR]", jsonObject.toString(), context, DAILYMEMO);

            editor.putString("dailyMemoDataTMP",jsonObject.toString()).apply();

            /** 即時便簽 */
            sharedPreferences.edit().putString("icon_name",icon).apply();

            resin_curr = jsonObject.getInt("current_resin");
            resin_remain_time = jsonObject.getInt("resin_recovery_time");
            currency_curr = jsonObject.getInt("current_home_coin");
            currency_max = jsonObject.getInt("max_home_coin");
            currency_remain_time = jsonObject.getInt("home_coin_recovery_time");
            mission_done = jsonObject.getInt("finished_task_num");
            mission_total = jsonObject.getInt("total_task_num");
            mission_claim = jsonObject.getBoolean("is_extra_task_reward_received");
            JSONObject transformer = jsonObject.getJSONObject("transformer").getJSONObject("recovery_time");
            transformer_recovery_time = (transformer.getInt("Day") * 86400 + transformer.getInt("Hour") * 3600 + transformer.getInt("Minute") * 60 + transformer.getInt("Second"));
            weekboss_30 = jsonObject.getInt("remain_resin_discount_num");
            weekboss_30_max = jsonObject.getInt("resin_discount_num_limit");
            expedition1_name = jsonObject.getJSONArray("expeditions").getJSONObject(0).getString("avatar_side_icon");
            expedition2_name = jsonObject.getJSONArray("expeditions").getJSONObject(1).getString("avatar_side_icon");
            expedition3_name = jsonObject.getJSONArray("expeditions").getJSONObject(2).getString("avatar_side_icon");
            expedition4_name = jsonObject.getJSONArray("expeditions").getJSONObject(3).getString("avatar_side_icon");
            expedition5_name = jsonObject.getJSONArray("expeditions").getJSONObject(4).getString("avatar_side_icon");
            expedition1_remain_time = jsonObject.getJSONArray("expeditions").getJSONObject(0).getInt("remained_time");
            expedition2_remain_time = jsonObject.getJSONArray("expeditions").getJSONObject(1).getInt("remained_time");
            expedition3_remain_time = jsonObject.getJSONArray("expeditions").getJSONObject(2).getInt("remained_time");
            expedition4_remain_time = jsonObject.getJSONArray("expeditions").getJSONObject(3).getInt("remained_time");
            expedition5_remain_time = jsonObject.getJSONArray("expeditions").getJSONObject(4).getInt("remained_time");

            updateData();
        }catch (JSONException e) {
            LogExport.export("DailyMemo2048Service","grabIdFromServer.doInBackground => refreshData()", e.getMessage(), context, DAILYMEMO);
        }
    }

    public class ShowAnim extends Animation {
        int targetTop;
        View view;
        boolean isPushDownAnim;

        public ShowAnim(View view, int targetTop, boolean isPushDownAnim) {
            this.view = view;
            this.targetTop = targetTop;
            this.isPushDownAnim = isPushDownAnim;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {

            float interpolatedTime_auth = interpolatedTime;
            if (isPushDownAnim){
                interpolatedTime_auth = (1-interpolatedTime);
            }

            ViewGroup.MarginLayoutParams layoutParams =
                    (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            //layoutParams.setMargins((int) (16*displayMetrics.density), (int) (targetTop*interpolatedTime_auth), (int) (16*displayMetrics.density), (int) (8*displayMetrics.density));
            layoutParams.setMargins((int) 0, (int) (targetTop*interpolatedTime_auth*displayMetrics.density), (int) 0, (int) 0);
            view.setLayoutParams(layoutParams);

            System.out.println(targetTop*interpolatedTime_auth);
        }

        @Override
        public void initialize(int width, int height, int parentWidth,
                               int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }

    public String prettyTime(int second){
        if (second == -1 || second == 0){return context.getString(R.string.idle);}
        int day = (int) second/86400;
        int hour = (int) (second%86400)/3600;
        int min = (int) ((second%86400)%3600)/60;

        String hr0 = "";
        String min0 = "";
        if (hour < 10){hr0 = "0";}
        if (min < 10){min0 = "0";}

        if (day>0){
            return String.valueOf(day)+"d "+hr0+String.valueOf(hour)+" : "+min0+String.valueOf(min);
        }else{
            return hr0+String.valueOf(hour)+" : "+min0+String.valueOf(min);
        }
    }

    public void ImageViewAnimatedChange(Context c, final ImageView v, final int new_image) {
        final Animation anim_out = AnimationUtils.loadAnimation(c, R.anim.fade_out);
        final Animation anim_in  = AnimationUtils.loadAnimation(c, R.anim.fade_in);
        anim_out.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation)
            {
                v.setImageResource(new_image);
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override public void onAnimationStart(Animation animation) {}
                    @Override public void onAnimationRepeat(Animation animation) {}
                    @Override public void onAnimationEnd(Animation animation) {}
                });
                v.startAnimation(anim_in);
            }
        });
        v.startAnimation(anim_out);
    }


}
