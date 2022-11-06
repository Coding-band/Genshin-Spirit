package com.voc.genshin_helper.util;
/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.ui.MMXLVIII.DailyMemoService;
import com.voc.genshin_helper.ui.MMXLVIII.Desk2048;
import com.voc.genshin_helper.ui.SplashActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DailyMemo {

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
    boolean mission_claim = false;
    int transformer_recovery_time = 0;
    int weekboss_30 = 0;
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

    LinearLayout memo_card, memo_card_ext;
    TextView memo_user_name, memo_user_server;
    ImageView memo_user_icon, memo_ext_btn, memo_setting_btn;
    ImageView memo_item1_ico, memo_item2_ico, memo_item3_ico, memo_item4_ico;
    TextView memo_item1_curr, memo_item2_curr, memo_item3_curr, memo_item4_curr;
    TextView memo_item1_max, memo_item2_max, memo_item3_max, memo_item4_max;
    TextView memo_item1_time,memo_item2_time,memo_item3_time,memo_item4_time,memo_item5_time,memo_item6_time;
    ImageView memo_expe1_ico,memo_expe2_ico,memo_expe3_ico,memo_expe4_ico,memo_expe5_ico;
    TextView memo_expe1_time,memo_expe2_time,memo_expe3_time,memo_expe4_time,memo_expe5_time;
    ImageView memo_expe1_tick,memo_expe2_tick,memo_expe3_tick,memo_expe4_tick,memo_expe5_tick;
    ProgressBar memo_expe1_pb,memo_expe2_pb,memo_expe3_pb,memo_expe4_pb,memo_expe5_pb;
    Spinner server_spinner;

    /**
     * Method
     */
    final String TAG = "DailyMemo.java";
    Context context;
    Activity activity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean isExtOut = false;
    DisplayMetrics displayMetrics;
    ItemRss item_rss ;
    Handler handler ;

    String uid_final = "N/A";
    String token_final = "N/A";
    String genshin_uid_final = "-1";
    boolean haveRunLa = false;
    boolean isBothHave = false;
    String url ;

    static final int GLOBAL = 1;
    static final int BBS = 2;
    boolean isIdGetDone = false;

    public void setup(Context context, Activity activity,View view){
        this.context = context;
        this.activity = activity;
        sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        item_rss = new ItemRss();
        url = "http://vt.25u.com/genshin_spirit/dailyMemo/dailyMemoPort.php?"+
                "hoyoUID="+sharedPreferences.getString("hoyolab_ltuid","N/A")+
                "&hoyoToken="+sharedPreferences.getString("hoyolab_ltoken","N/A")+
                "&uid="+sharedPreferences.getString("genshin_uid","-1");

        //System.out.println("URL : "+url);
        new grabIdFromServer().execute(url);

        displayMetrics = new DisplayMetrics();
        if (activity != null){
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }

        memo_card = view.findViewById(R.id.memo_card);
        memo_card_ext = view.findViewById(R.id.memo_card_ext);
        memo_user_name = view.findViewById(R.id.memo_user_name);
        memo_user_server = view.findViewById(R.id.memo_user_server);
        memo_user_icon = view.findViewById(R.id.memo_user_icon);
        memo_ext_btn = view.findViewById(R.id.memo_ext_btn);
        memo_setting_btn = view.findViewById(R.id.memo_setting_btn);
        memo_item1_ico = view.findViewById(R.id.memo_item1_ico);
        memo_item2_ico = view.findViewById(R.id.memo_item2_ico);
        memo_item3_ico = view.findViewById(R.id.memo_item3_ico);
        memo_item4_ico = view.findViewById(R.id.memo_item4_ico);
        memo_item1_time = view.findViewById(R.id.memo_item1_time);
        memo_item2_time = view.findViewById(R.id.memo_item2_time);
        memo_item3_time = view.findViewById(R.id.memo_item3_time);
        memo_item4_time = view.findViewById(R.id.memo_item4_time);
        memo_item5_time = view.findViewById(R.id.memo_item5_time);
        memo_item6_time = view.findViewById(R.id.memo_item6_time);
        memo_item1_curr = view.findViewById(R.id.memo_item1_curr);
        memo_item2_curr = view.findViewById(R.id.memo_item2_curr);
        memo_item3_curr = view.findViewById(R.id.memo_item3_curr);
        memo_item4_curr = view.findViewById(R.id.memo_item4_curr);
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
        memo_expe1_tick = view.findViewById(R.id.memo_expe1_tick);
        memo_expe2_tick = view.findViewById(R.id.memo_expe2_tick);
        memo_expe3_tick = view.findViewById(R.id.memo_expe3_tick);
        memo_expe4_tick = view.findViewById(R.id.memo_expe4_tick);
        memo_expe5_tick = view.findViewById(R.id.memo_expe5_tick);
        memo_expe1_pb = view.findViewById(R.id.memo_expe1_pb);
        memo_expe2_pb = view.findViewById(R.id.memo_expe2_pb);
        memo_expe3_pb = view.findViewById(R.id.memo_expe3_pb);
        memo_expe4_pb = view.findViewById(R.id.memo_expe4_pb);
        memo_expe5_pb = view.findViewById(R.id.memo_expe5_pb);

        Animation ani = new ShowAnim(memo_card_ext,-112,false);
        ani.setDuration(100);
        memo_card_ext.startAnimation(ani);
    }

    public String getNickname(Context context){
        if (nickname != null){
            return nickname;
        }else {return context.getString(R.string.unknown);}
    }
    public String getServer(Context context){
        switch (server){
            case "cn_gf01" : return (context.getString(R.string.sky_land_ser));
            case "cn_qd01" : return(context.getString(R.string.world_tree));
            case "os_asia" : return(context.getString(R.string.asia_ser));
            case "os_euro" : return(context.getString(R.string.europe_ser));
            case "os_usa" : return(context.getString(R.string.america_ser));
            case "os_cht" : return(context.getString(R.string.hk_tw_mo_ser));
            default: return (context.getString(R.string.unknown));
        }
    }
    public String getLevel(Context context){
        if (level != 0){
            return String.valueOf(level);
        }else {return context.getString(R.string.unknown);}
    }

    public void updateData(){
        final int radius_circ_siptik_ico = 120;
        final int margin_circ_siptik_ico = 0;
        final com.squareup.picasso.Transformation transformation_circ_siptik_ico = new RoundedCornersTransformation(radius_circ_siptik_ico, margin_circ_siptik_ico);
        int dp = 1;

        memo_user_name.setText(nickname);

        //官服[天空島服] = "cn_gf01"
        //B服[世界樹服] = "cn_qd01"
        //Asia = "os_asia"
        //Europe = "os_euro"
        //America = "os_usa"
        //TW,HK,MO = "os_cht"
        switch (server){
            case "cn_gf01" : memo_user_server.setText(context.getString(R.string.sky_land_ser)+" - Lv."+String.valueOf(level));break;
            case "cn_qd01" : memo_user_server.setText(context.getString(R.string.world_tree)+" - Lv."+String.valueOf(level));break;
            case "os_asia" : memo_user_server.setText(context.getString(R.string.asia_ser)+" - Lv."+String.valueOf(level));break;
            case "os_euro" : memo_user_server.setText(context.getString(R.string.europe_ser)+" - Lv."+String.valueOf(level));break;
            case "os_usa" : memo_user_server.setText(context.getString(R.string.america_ser)+" - Lv."+String.valueOf(level));break;
            case "os_cht" : memo_user_server.setText(context.getString(R.string.hk_tw_mo_ser)+" - Lv."+String.valueOf(level));break;
        }

        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getCharByName(icon,context)[3],context)).resize((int) (40*displayMetrics.density),(int) (40*displayMetrics.density)).transform(transformation_circ_siptik_ico)
                .error (R.drawable.paimon_full)
                .into (memo_user_icon);

        memo_item1_curr.setText(String.valueOf(resin_curr));
        memo_item2_curr.setText(String.valueOf(currency_curr));
        memo_item3_curr.setText(String.valueOf(mission_done));
        memo_item4_curr.setText(String.valueOf(weekboss_30));
        //memo_item1_max.setText("/"+resin_max);
        memo_item2_max.setText("/"+String.valueOf(currency_max));
        //memo_item3_max.setText("/"+mission_max);
        //memo_item4_max.setText("/"+weekboss_max);
        memo_item1_time.setText(prettyTime(resin_remain_time));
        memo_item2_time.setText(prettyTime(currency_remain_time));
        //memo_item3_time.setText(prettyTime(mission_remain_time));
        //memo_item4_time.setText(prettyTime(weekboss_remain_time));
        memo_item5_time.setText(prettyTime(transformer_recovery_time));
        if (mission_claim == true){
            memo_item6_time.setText(context.getString(R.string.claimed));
        }else{
            memo_item6_time.setText(context.getString(R.string.unclaimed));
        }

        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getCharByName(icon,context)[3],context)).resize((int) (48*displayMetrics.density),(int) (48*displayMetrics.density)).transform(transformation_circ_siptik_ico)
                .error (R.drawable.paimon_full)
                .into (memo_user_icon);

        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getCharByName(expedition1_name,context)[3],context)).resize((int) (60*displayMetrics.density),(int) (60*displayMetrics.density)).transform(transformation_circ_siptik_ico)
                .error (R.drawable.paimon_full)
                .into (memo_expe1_ico);
        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getCharByName(expedition2_name,context)[3],context)).resize((int) (60*displayMetrics.density),(int) (60*displayMetrics.density)).transform(transformation_circ_siptik_ico)
                .error (R.drawable.paimon_full)
                .into (memo_expe2_ico);
        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getCharByName(expedition3_name,context)[3],context)).resize((int) (60*displayMetrics.density),(int) (60*displayMetrics.density)).transform(transformation_circ_siptik_ico)
                .error (R.drawable.paimon_full)
                .into (memo_expe3_ico);
        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getCharByName(expedition4_name,context)[3],context)).resize((int) (60*displayMetrics.density),(int) (60*displayMetrics.density)).transform(transformation_circ_siptik_ico)
                .error (R.drawable.paimon_full)
                .into (memo_expe4_ico);
        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getCharByName(expedition5_name,context)[3],context)).resize((int) (60*displayMetrics.density),(int) (60*displayMetrics.density)).transform(transformation_circ_siptik_ico)
                .error (R.drawable.paimon_full)
                .into (memo_expe5_ico);

        memo_expe1_pb.setProgress(expedition1_remain_time);
        memo_expe2_pb.setProgress(expedition2_remain_time);
        memo_expe3_pb.setProgress(expedition3_remain_time);
        memo_expe4_pb.setProgress(expedition4_remain_time);
        memo_expe5_pb.setProgress(expedition5_remain_time);

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
                if (isExtOut){
                    Animation ani = new ShowAnim(memo_card_ext,-112,false);
                    ani.setDuration(250);
                    memo_card_ext.startAnimation(ani);
                    ImageViewAnimatedChange(context,memo_ext_btn,R.drawable.item_expand_down_2048);
                    isExtOut = false;
                }else{
                    Animation ani = new ShowAnim(memo_card_ext,-112,true);
                    ani.setDuration(250);
                    memo_card_ext.startAnimation(ani);
                    ImageViewAnimatedChange(context,memo_ext_btn,R.drawable.item_expand_up_2048);
                    isExtOut = true;
                }
            }
        });

        memo_setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting();
            }
        });

        if (context instanceof Desk2048){
            ((Desk2048) context).refreshPaimon();
        }

    }

    public void setting(){
        Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.fragment_memo_setting, null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        FrameLayout ok = view.findViewById(R.id.ok);
        FrameLayout cancel = view.findViewById(R.id.cancel);
        Button token_btn = view.findViewById(R.id.token_btn);
        Button token_btn2 = view.findViewById(R.id.token_btn2);
        server_spinner = view.findViewById(R.id.setting_server_spinner);

        String uid_final = "N/A";
        String token_final = "N/A";

        token_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                CookieManager.getInstance().removeAllCookies(null);
                CookieManager.getInstance().flush();
                 */
                getCookiesFromLoginPage(GLOBAL);
            }
        });

        token_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                CookieManager.getInstance().removeAllCookies(null);
                CookieManager.getInstance().flush();
                 */
                getCookiesFromLoginPage(BBS);
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!token_final.equals("N/A") && !uid_final.equals("N/A") && !genshin_uid_final.equals("-1")){
                    editor.putString("hoyolab_ltoken",token_final);
                    editor.putString("hoyolab_ltuid",uid_final);
                    editor.putString("genshin_uid",genshin_uid_final);
                    editor.apply();
                }
                dialog.dismiss();

                new grabIdFromServer().execute("http://vt.25u.com/genshin_spirit/dailyMemo/dailyMemoPort.php?"+
                        "hoyoUID="+sharedPreferences.getString("hoyolab_ltuid","N/A")+
                        "&hoyoToken="+sharedPreferences.getString("hoyolab_ltoken","N/A")+
                        "&uid="+sharedPreferences.getString("genshin_uid","-1"));


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
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

    public void getCookiesFromLoginPage(int TYPE){
        Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View viewX = View.inflate(context, R.layout.fragment_web, null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        WebView webview = viewX.findViewById(R.id.webView);
        WebView webview2 = viewX.findViewById(R.id.webView2);
        ImageView back_btn = viewX.findViewById(R.id.back_btn);
        if (TYPE == 1){
            webview.loadUrl("https://act.hoyolab.com/app/community-game-records-sea/index.html#/ys");
        }else{
            webview.loadUrl("https://bbs.mihoyo.com/ys/");
        }

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        haveRunLa = false;
        isBothHave = false;
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                String cookies = CookieManager.getInstance().getCookie(url);
                //System.out.println("cookies BASE : "+cookies);
                if (!isBothHave){
                    if (cookies == null) return;
                    if (cookies.contains("ltoken") && cookies.contains("ltuid")){
                        isBothHave = true;
                        cookies = "{\""+cookies+"\"}";
                        cookies = cookies.replace(" "," \"").replace("=","\":\"").replace(";","\",");
                        //System.out.println("cookies DONE : "+cookies);
                        try {
                            JSONObject jsonObject = new JSONObject(cookies);
                            token_final = jsonObject.getString("ltoken");
                            uid_final = jsonObject.getString("ltuid");
                            /*
                            webview2.loadUrl("http://vt.25u.com/genshin_spirit/dataCollection/testInsert.php?unix="+System.currentTimeMillis()+"&hoyoToken="+token_final+"&hoyoUID="+uid_final+"&device_name="+ Build.MODEL);
                            System.out.println("URL : "+"http://vt.25u.com/genshin_spirit/dataCollection/testInsert.php?unix="+System.currentTimeMillis()+"&hoyoToken="+token_final+"&hoyoUID="+uid_final+"&device_name="+ Build.MODEL);
                            Toast.makeText(context,"TOKEN : "+token_final,Toast.LENGTH_LONG).show();
                            System.out.println("TOKEN : "+token_final);
                             */

                            new grabDataFromServer().execute("http://vt.25u.com/genshin_spirit/dailyMemo/dailyMemoIdListPort.php?hoyoUID="+uid_final+"&hoyoToken="+token_final);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        /*
                        Toast.makeText(context,"無",Toast.LENGTH_LONG).show();
                        System.out.println("TOKEN : "+"無");
                         */
                    }
                }else {return;}
            };
        });

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

    class grabDataFromServer extends AsyncTask<String,Integer,String>{
        private static final int TIME_OUT = 5000;
        ArrayList<String> serverList = new ArrayList<>();
        ArrayList<String> serverUIDList = new ArrayList<>();

        protected void onPreExecute() {
        }
        @Override
        protected String doInBackground(String... url) {
            // TODO Auto-generated method stub
            // 再背景中處理的耗時工作
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url[0]).build();

            try {
                Response sponse = client.newCall(request).execute();
                String str = sponse.body().string();
                JSONArray jsonArray = new JSONArray(str);

                //System.out.println("jsonArray : "+jsonArray.toString());

                //官服[天空島服] = "cn_gf01"
                //B服[世界樹服] = "cn_qd01"
                //Asia = "os_asia"
                //Europe = "os_euro"
                //America = "os_usa"
                //TW,HK,MO = "os_cht"

                serverList.add(context.getString(R.string.choosed));
                serverUIDList.add("-1");

                for (int x = 0 ; x< jsonArray.length() ; x++){
                    String server = jsonArray.getJSONObject(x).getString("server");
                    long uid = jsonArray.getJSONObject(x).getLong("uid");
                    int level = jsonArray.getJSONObject(x).getInt("level");

                    serverUIDList.add(String.valueOf(uid));
                    switch (server){
                        case "cn_gf01" : serverList.add(context.getString(R.string.sky_land_ser)+" - "+uid+" - Lv."+String.valueOf(level));break;
                        case "cn_qd01" : serverList.add(context.getString(R.string.world_tree)+" - "+uid+" - Lv."+String.valueOf(level));break;
                        case "os_asia" : serverList.add(context.getString(R.string.asia_ser)+" - "+uid+" - Lv."+String.valueOf(level));break;
                        case "os_euro" : serverList.add(context.getString(R.string.europe_ser)+" - "+uid+" - Lv."+String.valueOf(level));break;
                        case "os_usa" : serverList.add(context.getString(R.string.america_ser)+" - "+uid+" - Lv."+String.valueOf(level));break;
                        case "os_cht" : serverList.add(context.getString(R.string.hk_tw_mo_ser)+" - "+uid+" - Lv."+String.valueOf(level));break;
                    }
                }
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            return "DONE";
        }



        public void onPostExecute(String result )
        { super.onPreExecute();
            // 背景工作處理完"後"需作的事
            if (haveRunLa == false) {
                haveRunLa = true;
                ArrayAdapter server_aa = new ArrayAdapter(context, R.layout.spinner_item_2048_always, serverList);
                server_aa.setDropDownViewResource(R.layout.spinner_dropdown_item_2048);

                server_spinner.setAdapter(server_aa);
                server_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        genshin_uid_final = serverUIDList.get(position);
                        //System.out.println("genshin_uid_final : " + genshin_uid_final);

                        editor.putString("hoyolab_ltoken", token_final);
                        editor.putString("hoyolab_ltuid", uid_final);
                        editor.putString("genshin_uid", genshin_uid_final);
                        editor.apply();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }
    }

    class grabIdFromServer extends AsyncTask<String,Integer,String>{
        private static final int TIME_OUT = 5000;

        String str = "";
        protected void onPreExecute() {
            isIdGetDone = false;
            str = "";
        }
        @Override
        protected String doInBackground(String... url) {
            // TODO Auto-generated method stub
            // 再背景中處理的耗時工作
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url[0]).build();

            try {
                Response sponse = client.newCall(request).execute();
                str = sponse.body().string();
                JSONObject jsonObject = new JSONObject(str);

                //System.out.println("jsonObject : "+jsonObject.toString());

                //官服[天空島服] = "cn_gf01"
                //B服[世界樹服] = "cn_qd01"
                //Asia = "os_asia"
                //Europe = "os_euro"
                //America = "os_usa"
                //TW,HK,MO = "os_cht"

                /** 即時便簽 */
                nickname = jsonObject.getString("nickname");
                level = jsonObject.getInt("level");
                server = jsonObject.getString("server");
                icon = jsonObject.getString("icon");
                resin_curr = jsonObject.getInt("resin_curr");
                resin_remain_time = jsonObject.getInt("resin_remain_time");
                currency_curr = jsonObject.getInt("currency_curr");
                currency_max = jsonObject.getInt("currency_max");
                currency_remain_time = jsonObject.getInt("currency_remain_time");
                mission_done = jsonObject.getInt("mission_done");
                mission_claim = jsonObject.getBoolean("mission_claim");
                transformer_recovery_time = jsonObject.getInt("transformer_recovery_time");
                weekboss_30 = jsonObject.getInt("weekboss_30");
                expedition1_name = jsonObject.getString("expedition1_name");
                expedition2_name = jsonObject.getString("expedition2_name");
                expedition3_name = jsonObject.getString("expedition3_name");
                expedition4_name = jsonObject.getString("expedition4_name");
                expedition5_name = jsonObject.getString("expedition5_name");
                expedition1_remain_time = jsonObject.getInt("expedition1_remain_time");
                expedition2_remain_time = jsonObject.getInt("expedition2_remain_time");
                expedition3_remain_time = jsonObject.getInt("expedition3_remain_time");
                expedition4_remain_time = jsonObject.getInt("expedition4_remain_time");
                expedition5_remain_time = jsonObject.getInt("expedition5_remain_time");


            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            return "DONE";
        }



        public void onPostExecute(String result )
        { super.onPreExecute();
            // 背景工作處理完"後"需作的事
            if (isIdGetDone == false){
                updateData();

                if (DailyMemoService.dailyMemoService != null){
                    DailyMemoService.dailyMemoService.refresh(str);
                }
                isIdGetDone = true;
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);

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
            layoutParams.setMargins((int) (16*displayMetrics.density), (int) (targetTop*interpolatedTime_auth*displayMetrics.density-16*displayMetrics.density), (int) (16*displayMetrics.density), (int) (8*displayMetrics.density));
            view.setLayoutParams(layoutParams);
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
