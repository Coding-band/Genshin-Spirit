package com.voc.genshin_helper.ui.MMXLVIII;
/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DailyMemo2048Service extends Service {
    public static final String TAG = "DailyMemo2048Service";
    public static final String CLICK_EVENT = "android.appwidget.action.Click";
    private static final String PRESS_EXT = "PRESS_EXT";

    public static DailyMemo2048Service dailyMemo2048Service;

    String nickname = "N/A";
    int level = 1;
    String server = "os_cht";
    String icon = "N/A";
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

    Context context;
    ItemRss item_rss;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate:(Service) ");
        dailyMemo2048Service = this;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                update(1);
            }
        }, 100);

        autoLoop();
    }

    public void autoLoop(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                update(2);
            }
        }, 60000);
    }

    /**更新時間*/
    private void update(int location){
        System.out.println("UPDATE CALL BY "+location);
        context = getApplicationContext();
        item_rss = new ItemRss();
        sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String url = "http://vt.25u.com/genshin_spirit/dailyMemo/dailyMemoPort.php?"+
                "hoyoUID="+sharedPreferences.getString("hoyolab_ltuid","N/A")+
                "&hoyoToken="+sharedPreferences.getString("hoyolab_ltoken","N/A")+
                "&uid="+sharedPreferences.getString("genshin_uid","-1");

        //System.out.println("URL : "+url);
        System.out.println("LOGS : "+url);
        if (!sharedPreferences.getString("genshin_uid","-1").equals("-1")){
            new grabIdFromServer().execute(url);
        }
        if (location == 2){
            autoLoop();
        }
    }

    private void updateData(){
        RemoteViews views = new RemoteViews(getPackageName(),R.layout.daily_memo_2048_widget);

        final int radius_circ_siptik_ico = 120;
        final int margin_circ_siptik_ico = 0;
        final com.squareup.picasso.Transformation transformation_circ_siptik_ico = new RoundedCornersTransformation(radius_circ_siptik_ico, margin_circ_siptik_ico);

        views.setTextViewText(R.id.memo_user_name,nickname);
        views.setImageViewResource(R.id.memo_ico_btn,R.drawable.app_ico);

        /*
        views.setImageViewResource(R.id.memo_ext_btn,R.drawable.item_expand_down_2048);
        views.setImageViewResource(R.id.memo_ext_bg,R.drawable.item_expand_bg_2048);
        views.setOnClickPendingIntent(R.id.memo_ext_btn,
                getPendingSelfIntent(context, PRESS_EXT));
         */

        views.setImageViewResource(R.id.memo_blank1,R.drawable.item_blank_2048);
        views.setImageViewResource(R.id.memo_blank2,R.drawable.item_blank_2048);
        views.setImageViewResource(R.id.memo_blank3,R.drawable.item_blank_2048);
        views.setImageViewResource(R.id.memo_blank4,R.drawable.item_blank_2048);

        //官服[天空島服] = "cn_gf01"
        //B服[世界樹服] = "cn_qd01"
        //Asia = "os_asia"
        //Europe = "os_euro"
        //America = "os_usa"
        //TW,HK,MO = "os_cht"

        switch (server){
            case "cn_gf01" : views.setTextViewText(R.id.memo_user_server,context.getString(R.string.sky_land_ser)+" - Lv."+String.valueOf(level));break;
            case "cn_qd01" : views.setTextViewText(R.id.memo_user_server,context.getString(R.string.world_tree)+" - Lv."+String.valueOf(level));break;
            case "os_asia" : views.setTextViewText(R.id.memo_user_server,context.getString(R.string.asia_ser)+" - Lv."+String.valueOf(level));break;
            case "os_euro" : views.setTextViewText(R.id.memo_user_server,context.getString(R.string.europe_ser)+" - Lv."+String.valueOf(level));break;
            case "os_usa" : views.setTextViewText(R.id.memo_user_server,context.getString(R.string.america_ser)+" - Lv."+String.valueOf(level));break;
            case "os_cht" : views.setTextViewText(R.id.memo_user_server,context.getString(R.string.hk_tw_mo_ser)+" - Lv."+String.valueOf(level));break;
        }

        if (icon.equals("N/A")){
            views.setImageViewBitmap(R.id.memo_user_icon,getRoundedCornerBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.paimon_suprise),120));
            views.setImageViewBitmap(R.id.memo_expe1_ico,getRoundedCornerBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.paimon_suprise),120));
            views.setImageViewBitmap(R.id.memo_expe2_ico,getRoundedCornerBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.paimon_suprise),120));
            views.setImageViewBitmap(R.id.memo_expe3_ico,getRoundedCornerBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.paimon_suprise),120));
            views.setImageViewBitmap(R.id.memo_expe4_ico,getRoundedCornerBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.paimon_suprise),120));
            views.setImageViewBitmap(R.id.memo_expe5_ico,getRoundedCornerBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.paimon_suprise),120));

        }else{
            if (!sharedPreferences.getString("icon_name", "N/A").equals("N/A")){
                icon = sharedPreferences.getString("icon_name", "N/A");
            }else{
                icon = item_rss.getCharNameByTranslatedName(icon, context);
            }
            views.setImageViewBitmap(R.id.memo_user_icon,getRoundedCornerBitmap(FileLoader.loadIMG2Bitmap(item_rss.getCharByName(icon,context)[3],context),120));
            views.setImageViewBitmap(R.id.memo_expe1_ico,getRoundedCornerBitmap(FileLoader.loadIMG2Bitmap(item_rss.getCharByName(expedition1_name,context)[3],context),120));
            views.setImageViewBitmap(R.id.memo_expe2_ico,getRoundedCornerBitmap(FileLoader.loadIMG2Bitmap(item_rss.getCharByName(expedition2_name,context)[3],context),120));
            views.setImageViewBitmap(R.id.memo_expe3_ico,getRoundedCornerBitmap(FileLoader.loadIMG2Bitmap(item_rss.getCharByName(expedition3_name,context)[3],context),120));
            views.setImageViewBitmap(R.id.memo_expe4_ico,getRoundedCornerBitmap(FileLoader.loadIMG2Bitmap(item_rss.getCharByName(expedition4_name,context)[3],context),120));
            views.setImageViewBitmap(R.id.memo_expe5_ico,getRoundedCornerBitmap(FileLoader.loadIMG2Bitmap(item_rss.getCharByName(expedition5_name,context)[3],context),120));

        }

        views.setImageViewResource(R.id.memo_item1_ico,R.drawable.fragile_resin);
        views.setImageViewResource(R.id.memo_item2_ico,R.drawable.realm_currency);
        views.setImageViewResource(R.id.memo_item3_ico,R.drawable.ico_mission);
        views.setImageViewResource(R.id.memo_item4_ico,R.drawable.ico_weekboss);
        views.setImageViewResource(R.id.memo_item5_ico,R.drawable.parametric_transformer);
        views.setImageViewResource(R.id.memo_item6_ico,R.drawable.adventurers_guild);

        views.setTextViewText(R.id.memo_item1_curr,String.valueOf(resin_curr));
        views.setTextViewText(R.id.memo_item2_curr,String.valueOf(currency_curr));
        views.setTextViewText(R.id.memo_item3_curr,String.valueOf(mission_done));
        views.setTextViewText(R.id.memo_item4_curr,String.valueOf(weekboss_30));
        //memo_item1_max.setText("/"+resin_max);
        views.setTextViewText(R.id.memo_item2_max,"/"+String.valueOf(currency_max));
        //memo_item3_max.setText("/"+mission_max);
        //memo_item4_max.setText("/"+weekboss_max);
        views.setTextViewText(R.id.memo_item1_time,prettyTime(resin_remain_time));
        views.setTextViewText(R.id.memo_item2_time,prettyTime(currency_remain_time));
        //memo_item3_time.setText(prettyTime(mission_remain_time));
        //memo_item4_time.setText(prettyTime(weekboss_remain_time));
        views.setTextViewText(R.id.memo_item5_time,prettyTime(transformer_recovery_time));
        if (mission_claim == true){
            views.setTextViewText(R.id.memo_item6_time,context.getString(R.string.claimed));
        }else{
            views.setTextViewText(R.id.memo_item6_time,context.getString(R.string.unclaimed));
        }

        views.setProgressBar(R.id.memo_expe1_pb,72000,expedition1_remain_time,false);
        views.setProgressBar(R.id.memo_expe2_pb,72000,expedition2_remain_time,false);
        views.setProgressBar(R.id.memo_expe3_pb,72000,expedition3_remain_time,false);
        views.setProgressBar(R.id.memo_expe4_pb,72000,expedition4_remain_time,false);
        views.setProgressBar(R.id.memo_expe5_pb,72000,expedition5_remain_time,false);

        views.setImageViewResource(R.id.memo_expe1_tick,R.drawable.ic_2048_need_tick);
        views.setImageViewResource(R.id.memo_expe2_tick,R.drawable.ic_2048_need_tick);
        views.setImageViewResource(R.id.memo_expe3_tick,R.drawable.ic_2048_need_tick);
        views.setImageViewResource(R.id.memo_expe4_tick,R.drawable.ic_2048_need_tick);
        views.setImageViewResource(R.id.memo_expe5_tick,R.drawable.ic_2048_need_tick);

        views.setTextViewText(R.id.memo_expe1_time,prettyTime(expedition1_remain_time));
        views.setTextViewText(R.id.memo_expe2_time,prettyTime(expedition2_remain_time));
        views.setTextViewText(R.id.memo_expe3_time,prettyTime(expedition3_remain_time));
        views.setTextViewText(R.id.memo_expe4_time,prettyTime(expedition4_remain_time));
        views.setTextViewText(R.id.memo_expe5_time,prettyTime(expedition5_remain_time));

        if (expedition1_remain_time == 0){
            views.setViewVisibility(R.id.memo_expe1_time,View.GONE);
            views.setViewVisibility(R.id.memo_expe1_tick,View.VISIBLE);
        }else{
            views.setViewVisibility(R.id.memo_expe1_time,View.VISIBLE);
            views.setViewVisibility(R.id.memo_expe1_tick,View.GONE);
        }
        if (expedition2_remain_time == 0){
            views.setViewVisibility(R.id.memo_expe2_time,View.GONE);
            views.setViewVisibility(R.id.memo_expe2_tick,View.VISIBLE);
        }else{
            views.setViewVisibility(R.id.memo_expe2_time,View.VISIBLE);
            views.setViewVisibility(R.id.memo_expe2_tick,View.GONE);
        }
        if (expedition3_remain_time == 0){
            views.setViewVisibility(R.id.memo_expe3_time,View.GONE);
            views.setViewVisibility(R.id.memo_expe3_tick,View.VISIBLE);
        }else{
            views.setViewVisibility(R.id.memo_expe3_time,View.VISIBLE);
            views.setViewVisibility(R.id.memo_expe3_tick,View.GONE);
        }
        if (expedition4_remain_time == 0){
            views.setViewVisibility(R.id.memo_expe4_time,View.GONE);
            views.setViewVisibility(R.id.memo_expe4_tick,View.VISIBLE);
        }else{
            views.setViewVisibility(R.id.memo_expe4_time,View.VISIBLE);
            views.setViewVisibility(R.id.memo_expe4_tick,View.GONE);
        }
        if (expedition5_remain_time == 0){
            views.setViewVisibility(R.id.memo_expe5_time,View.GONE);
            views.setViewVisibility(R.id.memo_expe5_tick,View.VISIBLE);
        }else{
            views.setViewVisibility(R.id.memo_expe5_time,View.VISIBLE);
            views.setViewVisibility(R.id.memo_expe5_tick,View.GONE);
        }

        AppWidgetManager manager = AppWidgetManager.getInstance(getApplicationContext());
        ComponentName componentName = new ComponentName(getApplicationContext(), DailyMemo2048Widget.class);
        manager.updateAppWidget(componentName,views);
    }

    public void refresh(String str){
        try {
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

            updateData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    class grabIdFromServer extends AsyncTask<String,Integer,String> {
        private static final int TIME_OUT = 5000;

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
            updateData();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);

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

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
}