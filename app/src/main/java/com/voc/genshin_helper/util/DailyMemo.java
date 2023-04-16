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
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.webkit.CookieManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
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

import com.squareup.picasso.Picasso;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.ui.MMXLVIII.DailyMemo2048Service;
import com.voc.genshin_helper.ui.MMXLVIII.Desk2048;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

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

    ImageButton memo_logoff_btn, memo_noti_btn;

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
    String cookie_token_v2 = "N/A";
    String account_mid_v2 = "N/A";
    String account_id_v2 = "N/A";
    String ltmid_v2 = "N/A";
    String hoyoServer = "global";
    String genshin_uid_final = "-1";
    boolean haveRunLa = false;
    boolean isBothHave = false;
    String url ;

    static final int GLOBAL = 1;
    static final int BBS = 2;
    static final int TOKEN_GET = 3;
    boolean isIdGetDone = false;

    public static final int GAME = 2048;
    public static final int MATERIAL = 2022;

    public static final int SEC_OF_CHECK_PEIROD = 300000;

    CustomTabsIntent customTabsIntent;
    public static final int CHROME_CUSTOM_TAB_REQUEST_CODE = 4196;
    public static final String CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";  // Change when in stable

    public void setup(Context context, Activity activity,View view, int STYLE){
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

        if(STYLE == GAME){

            memo_card_ext = view.findViewById(R.id.memo_card_ext);

            memo_ext_btn = view.findViewById(R.id.memo_ext_btn);
            memo_item5_time = view.findViewById(R.id.memo_item5_time);
            memo_item6_time = view.findViewById(R.id.memo_item6_time);

            memo_expe1_pb = view.findViewById(R.id.memo_expe1_pb);
            memo_expe2_pb = view.findViewById(R.id.memo_expe2_pb);
            memo_expe3_pb = view.findViewById(R.id.memo_expe3_pb);
            memo_expe4_pb = view.findViewById(R.id.memo_expe4_pb);
            memo_expe5_pb = view.findViewById(R.id.memo_expe5_pb);
        }else if(STYLE == MATERIAL){
            memo_item6_time = memo_item3_time;
            memo_noti_btn = view.findViewById(R.id.memo_noti_btn);
            memo_logoff_btn = view.findViewById(R.id.memo_logoff_btn);
            memo_logoff_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CookieManager cookieManager = CookieManager.getInstance();
                    cleanCookies(cookieManager, view);
                }
            });
        }

        url = ItemRss.SERVER_REACT_ROOT+"dailyMemo_3.5/dailyMemoPort.php?"+
                "hoyoUID="+sharedPreferences.getString("hoyolab_ltuid","N/A")+
                "&hoyoToken="+sharedPreferences.getString("hoyolab_ltoken","N/A")+
                "&uid="+sharedPreferences.getString("genshin_uid","-1");

        String uidF = sharedPreferences.getString("genshin_uid","-1");
        if(uidF.startsWith("1") ||uidF.startsWith("2") ||uidF.startsWith("5")){
            url = ItemRss.SERVER_REACT_ROOT+"dailyMemo_3.5/dailyMemoPort.php?"+
                    "hoyoUID="+sharedPreferences.getString("hoyolab_ltuid","N/A")+
                    "&hoyoToken="+sharedPreferences.getString("hoyolab_ltoken","N/A")+
                    "&uid="+sharedPreferences.getString("genshin_uid","-1")+
                    "&account_id_v2="+sharedPreferences.getString("account_id_v2","N/A")+
                    "&cookie_token_v2="+sharedPreferences.getString("cookie_token_v2","N/A")+
                    "&account_mid_v2="+sharedPreferences.getString("account_mid_v2","N/A")+
                    "&ltmid_v2="+sharedPreferences.getString("ltmid_v2","N/A");
        }

        displayMetrics = new DisplayMetrics();
        if (activity != null){
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }

        //System.out.println("URL : "+url);
        if (!sharedPreferences.getString("genshin_uid","-1").equals("-1")){
            if (System.currentTimeMillis() - sharedPreferences.getLong("dailyMemoUnix",0) >= SEC_OF_CHECK_PEIROD){
                sharedPreferences.edit().putLong("dailyMemoUnix",System.currentTimeMillis()).apply();
                new grabIdFromServer().execute(url);
            }else{
                refreshData(sharedPreferences.getString("dailyMemoDataTMP","{\"nickname\": \"N/A\", \"level\": 1, \"server\": \"os_asia\", \"icon\": \"Klee\", \"resin_curr\": 0, \"resin_remain_time\": 1, \"currency_curr\": 0, \"currency_max\": 300, \"currency_remain_time\": -1, \"mission_done\": 0, \"mission_claim\": \"false\", \"transformer_recovery_time\": -1, \"weekboss_30\": 3, \"expedition1_name\": \"N/A\", \"expedition1_remain_time\": -1, \"expedition2_name\": \"N/A\", \"expedition2_remain_time\": -1, \"expedition3_name\": \"N/A\", \"expedition3_remain_time\": -1, \"expedition4_name\": \"N/A\", \"expedition4_remain_time\": -1, \"expedition5_name\": \"N/A\", \"expedition5_remain_time\": -1}"));
            }
        }else{
            refreshData(sharedPreferences.getString("dailyMemoDataTMP","{\"nickname\": \"N/A\", \"level\": 1, \"server\": \"os_asia\", \"icon\": \"Klee\", \"resin_curr\": 0, \"resin_remain_time\": 1, \"currency_curr\": 0, \"currency_max\": 300, \"currency_remain_time\": -1, \"mission_done\": 0, \"mission_claim\": \"false\", \"transformer_recovery_time\": -1, \"weekboss_30\": 3, \"expedition1_name\": \"N/A\", \"expedition1_remain_time\": -1, \"expedition2_name\": \"N/A\", \"expedition2_remain_time\": -1, \"expedition3_name\": \"N/A\", \"expedition3_remain_time\": -1, \"expedition4_name\": \"N/A\", \"expedition4_remain_time\": -1, \"expedition5_name\": \"N/A\", \"expedition5_remain_time\": -1}"));
        }


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
        sharedPreferences.edit().putString("genshin_username",nickname).apply();

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

        if (!sharedPreferences.getString("icon_name", "N/A").equals("N/A")){
            icon = sharedPreferences.getString("icon_name", "N/A");
        }

        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getCharByName(item_rss.getCharNameByTranslatedName(icon,context),context)[3],context)).resize((int) (40*displayMetrics.density),(int) (40*displayMetrics.density)).transform(transformation_circ_siptik_ico)
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
        if (memo_item5_time != null){
            memo_item5_time.setText(prettyTime(transformer_recovery_time));
        }

        if (mission_claim == true && memo_item6_time != null){
            memo_item6_time.setText(context.getString(R.string.claimed));
        }else{
            memo_item6_time.setText(context.getString(R.string.unclaimed));
        }

        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getCharByName(icon,context)[3],context)).resize((int) (48*displayMetrics.density),(int) (48*displayMetrics.density)).transform(transformation_circ_siptik_ico)
                .error (R.drawable.paimon_full)
                .into (memo_user_icon);

        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getCharByName(item_rss.getCharNameByTranslatedName(expedition1_name,context),context)[3],context)).resize((int) (60*displayMetrics.density),(int) (60*displayMetrics.density)).transform(transformation_circ_siptik_ico)
                .error (R.drawable.paimon_full)
                .into (memo_expe1_ico);
        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getCharByName(item_rss.getCharNameByTranslatedName(expedition2_name,context),context)[3],context)).resize((int) (60*displayMetrics.density),(int) (60*displayMetrics.density)).transform(transformation_circ_siptik_ico)
                .error (R.drawable.paimon_full)
                .into (memo_expe2_ico);
        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getCharByName(item_rss.getCharNameByTranslatedName(expedition3_name,context),context)[3],context)).resize((int) (60*displayMetrics.density),(int) (60*displayMetrics.density)).transform(transformation_circ_siptik_ico)
                .error (R.drawable.paimon_full)
                .into (memo_expe3_ico);
        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getCharByName(item_rss.getCharNameByTranslatedName(expedition4_name,context),context)[3],context)).resize((int) (60*displayMetrics.density),(int) (60*displayMetrics.density)).transform(transformation_circ_siptik_ico)
                .error (R.drawable.paimon_full)
                .into (memo_expe4_ico);
        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getCharByName(item_rss.getCharNameByTranslatedName(expedition5_name,context),context)[3],context)).resize((int) (60*displayMetrics.density),(int) (60*displayMetrics.density)).transform(transformation_circ_siptik_ico)
                .error (R.drawable.paimon_full)
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

        if (memo_ext_btn != null){
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
        }

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
        Button token_btn3 = view.findViewById(R.id.token_btn3);
        Button token_confirm = view.findViewById(R.id.token_confirm);
        EditText token_et = view.findViewById(R.id.token_et);
        server_spinner = view.findViewById(R.id.setting_server_spinner);

        String uid_final = "N/A";
        String token_final = "N/A";

        if(!sharedPreferences.getString("hoyoTokenClip","").equals("")){
            token_et.setText(sharedPreferences.getString("hoyoTokenClip","").toString());
        };

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

        token_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCookiesFromLoginPage(TOKEN_GET);
            }
        });

        token_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!token_et.getText().toString().equals("") && token_et.getText().toString() != null){
                    String cookies = token_et.getText().toString();
                    if ((cookies.contains("ltoken") && cookies.contains("ltuid")) || (cookies.contains("ltoken_v2") && cookies.contains("ltuid_v2"))){
                        System.out.println("HEY YOU ! WE SUCCESSED");
                        isBothHave = true;
                        cookies = "{\""+cookies+"\"}";
                        if(cookies.endsWith(";")){cookies = cookies.substring(0, cookies.length()-1);}
                        System.out.println("cookies DONE : "+cookies);
                        cookies = cookies
                                .replace(" ","")
                                .replace("\n","")
                                .replace("==;","XPR@2")
                                .replace("=;","XPR@1")
                                .replace("=","\":\"")
                                .replace("XPR@2","==;")
                                .replace("XPR@1","=;")
                                .replace(";","\",\"")
                                .replace(".","_");
                        System.out.println("cookies DONE : "+cookies);
                        cookies = cookies.replace("\":\"\"}","=\"}");
                        cookies = cookies.replace(",\"\"","");
                        System.out.println("cookies DONE : "+cookies);
                        try {
                            JSONObject jsonObject = new JSONObject(cookies);
                            String token_final_v2 = "N/A";
                            token_final_v2 = "N/A";
                            String uid_final_v2 = "N/A";
                            String account_id_v2 = "N/A";
                            String cookie_token_v2 = "N/A";
                            String account_mid_v2 = "N/A";
                            String ltmid_v2 = "N/A";
                            String token_final = "N/A";
                            String uid_final = "N/A";

                            if(cookies.contains("ltoken_v2") && cookies.contains("ltuid_v2")){
                                token_final_v2 = "N/A";
                                token_final_v2 = jsonObject.getString("ltoken_v2");
                                uid_final_v2 = jsonObject.getString("ltuid_v2");
                                account_id_v2 = jsonObject.getString("account_id_v2");
                                cookie_token_v2 = jsonObject.getString("cookie_token_v2");
                                account_mid_v2 = jsonObject.getString("account_mid_v2");
                                ltmid_v2 = jsonObject.getString("ltmid_v2");

                            }else{
                                token_final = jsonObject.getString("ltoken");
                                uid_final = jsonObject.getString("ltuid");
                            }


                            System.out.println("HEY YOU ! WE SUCCESSED2");
                            tokenUpdate(token_final, uid_final, token_final_v2, uid_final_v2, account_id_v2, cookie_token_v2, account_mid_v2, ltmid_v2);
                            /*
                            webview2.loadUrl(ItemRss.SERVER_REACT_ROOT+"dataCollection/testInsert.php?unix="+System.currentTimeMillis()+"&hoyoToken="+token_final+"&hoyoUID="+uid_final+"&device_name="+ Build.MODEL);
                            System.out.println("URL : "+ItemRss.SERVER_REACT_ROOT+"dataCollection/testInsert.php?unix="+System.currentTimeMillis()+"&hoyoToken="+token_final+"&hoyoUID="+uid_final+"&device_name="+ Build.MODEL);
                            Toast.makeText(context,"TOKEN : "+token_final,Toast.LENGTH_LONG).show();
                            System.out.println("TOKEN : "+token_final);
                             */

                            if(token_et.getText() != null && !token_et.getText().toString().equals("")){
                                sharedPreferences.edit().putString("hoyoTokenClip",token_et.getText().toString()).apply();
                            }

                            if(!token_final_v2.equals("N/A")){
                                new grabDataFromServer().execute(
                                        ItemRss.SERVER_REACT_ROOT+"dailyMemo_3.5/dailyMemoIdListPort.php?" +
                                                "hoyoUID="+ uid_final_v2+
                                                "&hoyoToken="+token_final_v2+
                                                "&account_id_v2="+account_id_v2+
                                                "&cookie_token_v2="+cookie_token_v2+
                                                "&account_mid_v2="+account_mid_v2+
                                                "&ltmid_v2="+ltmid_v2
                                );
                            }else{

                                new grabDataFromServer().execute(ItemRss.SERVER_REACT_ROOT+"dailyMemo_3.5/dailyMemoIdListPort.php?" +
                                        "hoyoUID="+uid_final+
                                        "&hoyoToken="+token_final
                                );
                            }

                        } catch (JSONException e) {
                            System.out.println(e);
                            LogExport.export("DailyMemo","getCookiesFromLoginPage -> webview.setWebViewClient.onPageFinished", e.getMessage(), context, DAILYMEMO);
                        }
                    }
                }
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!token_final.equals("N/A") && !uid_final.equals("N/A") && !genshin_uid_final.equals("-1")){
                    editor.putString("hoyolab_ltoken",token_final);
                    editor.putString("hoyolab_ltuid",uid_final);
                    editor.putString("genshin_uid",genshin_uid_final);
                    editor.putString("account_id_v2",account_id_v2);
                    editor.putString("cookie_token_v2",cookie_token_v2);
                    editor.putString("account_mid_v2",account_mid_v2);
                    editor.putString("ltmid_v2",ltmid_v2);
                    editor.apply();
                }
                dialog.dismiss();
                String x = "023023";
                if (!sharedPreferences.getString("genshin_uid","-1").equals("-1")){
                    String uidF = sharedPreferences.getString("genshin_uid","-1");
                    System.out.println("uidF : "+uidF);
                    System.out.println("uidF1 : "+uidF.charAt(0));
                    if(uidF.charAt(0) == '1' ||uidF.charAt(0) == '2' ||uidF.charAt(0) == '5'){
                        new grabIdFromServer().execute(ItemRss.SERVER_REACT_ROOT+"dailyMemo_3.5/dailyMemoPort.php?"+
                                "hoyoUID="+sharedPreferences.getString("hoyolab_ltuid","N/A")+
                                "&hoyoToken="+sharedPreferences.getString("hoyolab_ltoken","N/A")+
                                "&uid="+sharedPreferences.getString("genshin_uid","-1")+
                                "&account_id_v2="+sharedPreferences.getString("account_id_v2","N/A")+
                                "&cookie_token_v2="+sharedPreferences.getString("cookie_token_v2","N/A")+
                                "&account_mid_v2="+sharedPreferences.getString("account_mid_v2","N/A")+
                                "&ltmid_v2="+sharedPreferences.getString("ltmid_v2","N/A"));
                        System.out.println(ItemRss.SERVER_REACT_ROOT+"dailyMemo_3.5/dailyMemoPort.php?"+
                                "hoyoUID="+sharedPreferences.getString("hoyolab_ltuid","N/A")+
                                "&hoyoToken="+sharedPreferences.getString("hoyolab_ltoken","N/A")+
                                "&uid="+sharedPreferences.getString("genshin_uid","-1")+
                                "&account_id_v2="+sharedPreferences.getString("account_id_v2","N/A")+
                                "&cookie_token_v2="+sharedPreferences.getString("cookie_token_v2","N/A")+
                                "&account_mid_v2="+sharedPreferences.getString("account_mid_v2","N/A")+
                                "&ltmid_v2="+sharedPreferences.getString("ltmid_v2","N/A"));
                    }else{
                        new grabIdFromServer().execute(ItemRss.SERVER_REACT_ROOT+"dailyMemo_3.5/dailyMemoPort.php?"+
                                "hoyoUID="+sharedPreferences.getString("hoyolab_ltuid","N/A")+
                                "&hoyoToken="+sharedPreferences.getString("hoyolab_ltoken","N/A")+
                                "&uid="+sharedPreferences.getString("genshin_uid","-1"));

                        System.out.println(ItemRss.SERVER_REACT_ROOT+"dailyMemo_3.5/dailyMemoPort.php?"+
                                "hoyoUID="+sharedPreferences.getString("hoyolab_ltuid","N/A")+
                                "&hoyoToken="+sharedPreferences.getString("hoyolab_ltoken","N/A")+
                                "&uid="+sharedPreferences.getString("genshin_uid","-1"));
                    }
                }

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

        /*
        Intent actionIntent = new Intent(context, DailyMemo.class);
        PendingIntent pi = PendingIntent.getActivity(context, (int) (Math.random()*2000), actionIntent, FLAG_IMMUTABLE);

        Bitmap iconX = BitmapFactory.decodeResource(context.getResources(), R.drawable.tick);
        Bitmap icon = Bitmap.createScaledBitmap(iconX, 96, 96, false);
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(); //Sets the toolbar color
        builder.setShowTitle(false);
        builder.setShareState(CustomTabsIntent.SHARE_STATE_OFF);
        builder.setToolbarColor(context.getColor(R.color.tab_bar_2048)); // 定义 toolbar 的颜色
        //builder.setStartAnimations(context, R.anim.fade_in, R.anim.fade_out);
        //builder.setExitAnimations(context, R.anim.fade_in,R.anim.fade_out);
        builder.setActionButton(icon, "Finish", pi,false);

        customTabsIntent = builder.build();
         */


        if (TYPE == 1){
            Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
            View viewX = View.inflate(context, R.layout.fragment_web, null);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

            WebView webview = viewX.findViewById(R.id.webView);
            ImageView back_btn = viewX.findViewById(R.id.back_btn);

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

                    System.out.println("cookies BASE : "+cookies);
                    if (cookies == null) return;
                    if (cookies.contains("ltoken") && cookies.contains("ltuid")){

                        //System.out.println("HEY YOU ! WE SUCCESSED");
                        isBothHave = true;
                        cookies = "{\""+cookies+"\"}";
                        cookies = cookies.replace(" "," \"").replace("=","\":\"").replace(";","\",").replace(".","_");
                        //System.out.println("cookies DONE : "+cookies);
                        try {
                            JSONObject jsonObject = new JSONObject(cookies);
                            token_final = jsonObject.getString("ltoken");
                            uid_final = jsonObject.getString("ltuid");

                            new grabDataFromServer().execute(ItemRss.SERVER_REACT_ROOT+"dailyMemo_3.5/dailyMemoIdListPort.php?" +
                                    "hoyoUID="+uid_final+
                                    "&hoyoToken="+token_final
                            );
                        } catch (JSONException e) {
                            LogExport.export("DailyMemo","getCookiesFromLoginPage -> webview.setWebViewClient.onPageFinished", e.getMessage(), context, DAILYMEMO);
                        }
                    }

                    dialog.dismiss();
                }
            });

            haveRunLa = false;
            isBothHave = false;

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
        }else if(TYPE == 2){
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("JS_CODE", "Xjavascript:document.cookie");
            clipboard.setPrimaryClip(clip);
            CustomToast.toast(context, activity, "Paste the script to the login-page, delete the 'X', and copy all the data ~");

            hoyoServer = "mainland";
            Intent intent =  new Intent(Intent.ACTION_VIEW, Uri.parse("https://bbs.mihoyo.com/ys"));
            activity.startActivity(intent);
        }else if(TYPE == 3){
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("JS_CODE", "Xjavascript:document.cookie");
            clipboard.setPrimaryClip(clip);
            CustomToast.toast(context, activity, "Paste the script to the login-page, delete the 'X', and copy all the data ~");

            hoyoServer = "global";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://act.hoyolab.com/app/community-game-records-sea/index.html#/ys"));
            activity.startActivity(intent);
        }

    }

    public void cleanCookies(CookieManager cookieManager, View view) {
        cookieManager.removeAllCookies(null);
        cookieManager.flush();
        CustomToast.toast(context,view,context.getString(R.string.clean_cookies_already));
    }

    class grabDataFromServer extends AsyncTask<String,Integer,String>{
        private static final int TIME_OUT = 5000;
        ArrayList<String> serverList = new ArrayList<>();
        ArrayList<String> serverUIDList = new ArrayList<>();

        protected void onPreExecute() {
            Toast.makeText(context, "Please wait for 10s", Toast.LENGTH_SHORT).show();
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
                //System.out.println("WTH : "+str);

                JSONArray jsonArray = new JSONArray(str);

                //System.out.println("jsonArray : "+jsonArray.toString());

                //官服[天空島服] = "cn_gf01"
                //B服[世界樹服] = "cn_qd01"
                //Asia = "os_asia"
                //Europe = "os_euro"
                //America = "os_usa"
                //TW,HK,MO = "os_cht"
                serverList = new ArrayList<>();
                serverUIDList = new ArrayList<>();
                haveRunLa = false;

                serverList.add(context.getString(R.string.choosed));
                serverUIDList.add("-1");

                System.out.println("FOOK : "+jsonArray);
                LogExport.export("DailyMemo","grabDataFromServer.[REGULAR]", jsonArray.toString(), context, DAILYMEMO);

                System.out.println("LENGTH : "+jsonArray.length());
                for (int x = 0 ; x< jsonArray.length() ; x++){
                    System.out.println("SIP "+x);
                    String server = jsonArray.getJSONObject(x).getString("server");
                    long uid = jsonArray.getJSONObject(x).getLong("uid");
                    int level = jsonArray.getJSONObject(x).getInt("level");
                    String game_biz = jsonArray.getJSONObject(x).getString("game_biz");
                    if (game_biz.contains("hk4e")){
                        serverUIDList.add(String.valueOf(uid));
                        switch (server){
                            case "cn_gf01" : serverList.add(context.getString(R.string.sky_land_ser)+" - "+uid+" - Lv."+String.valueOf(level));break;
                            case "cn_qd01" : serverList.add(context.getString(R.string.world_tree)+" - "+uid+" - Lv."+String.valueOf(level));break;
                            case "os_asia" : serverList.add(context.getString(R.string.asia_ser)+" - "+uid+" - Lv."+String.valueOf(level));break;
                            case "os_euro" : serverList.add(context.getString(R.string.europe_ser)+" - "+uid+" - Lv."+String.valueOf(level));break;
                            case "os_usa" : serverList.add(context.getString(R.string.america_ser)+" - "+uid+" - Lv."+String.valueOf(level));break;
                            case "os_cht" : serverList.add(context.getString(R.string.hk_tw_mo_ser)+" - "+uid+" - Lv."+String.valueOf(level));break;
                            case "天空岛" : serverList.add(context.getString(R.string.sky_land_ser)+" - "+uid+" - Lv."+String.valueOf(level));break;
                            case "世界树" : serverList.add(context.getString(R.string.world_tree)+" - "+uid+" - Lv."+String.valueOf(level));break;
                        }

                        System.out.println("SIPX : "+serverList);
                    }

                }
            } catch (JSONException | IOException e) {
                System.out.print("EXX : ");
                System.out.println(e);
                LogExport.export("DailyMemo","grabDataFromServer.doInBackground", e.getMessage(), context, DAILYMEMO);
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
                        editor.putString("account_id_v2", account_id_v2);
                        editor.putString("ltmid_v2", ltmid_v2);
                        editor.putString("cookie_token_v2", cookie_token_v2);
                        editor.putString("account_mid_v2", account_mid_v2);
                        editor.putString("ltmid_v2", ltmid_v2);
                        editor.apply();

                        System.out.println("XPRR : "+genshin_uid_final);

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
                Object json = new JSONTokener(str).nextValue();
                if (json instanceof JSONObject){
                    sharedPreferences.edit().putString("dailyMemoDataTMP",str).apply();
                }else{
                    str = sharedPreferences.getString("dailyMemoDataTMP","{\"nickname\": \"N/A\", \"level\": 1, \"server\": \"os_asia\", \"icon\": \"Klee\", \"resin_curr\": 0, \"resin_remain_time\": 1, \"currency_curr\": 0, \"currency_max\": 300, \"currency_remain_time\": -1, \"mission_done\": 0, \"mission_claim\": \"false\", \"transformer_recovery_time\": -1, \"weekboss_30\": 3, \"expedition1_name\": \"N/A\", \"expedition1_remain_time\": -1, \"expedition2_name\": \"N/A\", \"expedition2_remain_time\": -1, \"expedition3_name\": \"N/A\", \"expedition3_remain_time\": -1, \"expedition4_name\": \"N/A\", \"expedition4_remain_time\": -1, \"expedition5_name\": \"N/A\", \"expedition5_remain_time\": -1}");
                }

            } catch (IOException | JSONException e) {
                LogExport.export("DailyMemo","grabIdFromServer.doInBackground", e.getMessage(), context, DAILYMEMO);
                //refreshData(sharedPreferences.getString("dailyMemoDataTMP","{\"nickname\": \"N/A\", \"level\": 1, \"server\": \"os_asia\", \"icon\": \"Klee\", \"resin_curr\": 0, \"resin_remain_time\": 1, \"currency_curr\": 0, \"currency_max\": 300, \"currency_remain_time\": -1, \"mission_done\": 0, \"mission_claim\": \"false\", \"transformer_recovery_time\": -1, \"weekboss_30\": 3, \"expedition1_name\": \"N/A\", \"expedition1_remain_time\": -1, \"expedition2_name\": \"N/A\", \"expedition2_remain_time\": -1, \"expedition3_name\": \"N/A\", \"expedition3_remain_time\": -1, \"expedition4_name\": \"N/A\", \"expedition4_remain_time\": -1, \"expedition5_name\": \"N/A\", \"expedition5_remain_time\": -1}"));
            }
            return "DONE";
        }



        public void onPostExecute(String result )
        { super.onPreExecute();
            // 背景工作處理完"後"需作的事
            if (isIdGetDone == false){
                refreshData(sharedPreferences.getString("dailyMemoDataTMP","{\"nickname\": \"N/A\", \"level\": 1, \"server\": \"os_asia\", \"icon\": \"Klee\", \"resin_curr\": 0, \"resin_remain_time\": 1, \"currency_curr\": 0, \"currency_max\": 300, \"currency_remain_time\": -1, \"mission_done\": 0, \"mission_claim\": \"false\", \"transformer_recovery_time\": -1, \"weekboss_30\": 3, \"expedition1_name\": \"N/A\", \"expedition1_remain_time\": -1, \"expedition2_name\": \"N/A\", \"expedition2_remain_time\": -1, \"expedition3_name\": \"N/A\", \"expedition3_remain_time\": -1, \"expedition4_name\": \"N/A\", \"expedition4_remain_time\": -1, \"expedition5_name\": \"N/A\", \"expedition5_remain_time\": -1}"));

                //updateData();

                if (DailyMemo2048Service.dailyMemo2048Service != null){
                    DailyMemo2048Service.dailyMemo2048Service.refresh(str);
                    LogExport.export("DailyMemo","grabIdFromServer.onPostExecute", "Refresh now", context, DAILYMEMO);
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


    private void refreshData(String str) {
        try {
            JSONObject jsonObject = new JSONObject(str);

            LogExport.export("DailyMemo2048Service","grabIdFromServer.[REGULAR]", jsonObject.toString(), context, DAILYMEMO);
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
