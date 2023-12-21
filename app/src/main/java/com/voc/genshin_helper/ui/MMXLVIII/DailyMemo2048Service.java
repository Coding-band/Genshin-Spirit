package com.voc.genshin_helper.ui.MMXLVIII;

import static com.voc.genshin_helper.util.DailyMemo.SEC_OF_CHECK_PEIROD;
import static com.voc.genshin_helper.util.LogExport.DAILYMEMO;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
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
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.squareup.picasso.Picasso;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.LogExport;
import com.voc.genshin_helper.util.RoundedCornersTransformation;
import com.voc.genshin_helper.util.hoyolab.HoyolabConstants;
import com.voc.genshin_helper.util.hoyolab.HoyolabCookie;
import com.voc.genshin_helper.util.hoyolab.hooks.HoyolabHooks;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

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
    int mission_total = 0;
    boolean mission_claim = false;
    int transformer_recovery_time = 0;
    int weekboss_30 = 0;
    int weekboss_30_max = 0;
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
    public int onStartCommand(Intent intent, int flags, int startId) {
        final String CHANNELID = "Foreground Service ID";
        NotificationChannel channel = new NotificationChannel(
                CHANNELID,
                CHANNELID,
                NotificationManager.IMPORTANCE_LOW
        );

        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Notification.Builder notification = new Notification.Builder(this, CHANNELID)
                .setContentText("Service is running")
                .setContentTitle("Service enabled")
                .setSmallIcon(R.drawable.ic_launcher_background);

        startForeground(1001, notification.build());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate:(Service) ");
        dailyMemo2048Service = this;
        context = getApplicationContext();

        String channelId = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            channelId = createNotificationChannel("dailyMemo","DailyMemo");
        }else{
            channelId = "";
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);
        Notification notification = builder.setOngoing(true)
                .setSmallIcon(R.drawable.app_ico)
                .setContentTitle("DailyMemo is running...")
                .setContentInfo("Data will refresh every minutes")
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();

        if (context.getSharedPreferences("user_info", Context.MODE_PRIVATE).getBoolean("isDailyMemoEnabled", true)) {
            startForeground(2, notification);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    update(1);
                }
            }, 100);

            autoLoop();
        }
    }

    public void autoLoop(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                update(2);
            }
        }, SEC_OF_CHECK_PEIROD);
    }

    /**更新時間*/
    private void update(int location){
        System.out.println("UPDATE CALL BY "+location);
        context = getApplicationContext();
        item_rss = new ItemRss();
        sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (!sharedPreferences.getString("genshin_uid","-1").equals("-1")){
            if (System.currentTimeMillis() - sharedPreferences.getLong("dailyMemoUnix",0) >= SEC_OF_CHECK_PEIROD){
                sharedPreferences.edit().putLong("dailyMemoUnix",System.currentTimeMillis()).apply();

                try{
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
                }catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                refresh(new HoyolabHooks().genshinNoteData(context).toString(),nickname,server,icon,level);
                if (location == 2 || location == 3){
                    autoLoop();
                }
            }
        }
    }

    private void updateData(){
        RemoteViews views = new RemoteViews(getPackageName(),R.layout.daily_memo_2048_widget);
        context = getApplicationContext();
        item_rss = new ItemRss();
        sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);

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

        views.setTextViewText(R.id.memo_user_server,sharedPreferences.getString("genshin_uid","-1")+" - Lv."+String.valueOf(level));

        if (icon.equals("N/A")){
            views.setImageViewBitmap(R.id.memo_user_icon,getRoundedCornerBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.paimon_suprise),120, context));
            views.setImageViewBitmap(R.id.memo_expe1_ico,getRoundedCornerBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.paimon_suprise),120, context));
            views.setImageViewBitmap(R.id.memo_expe2_ico,getRoundedCornerBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.paimon_suprise),120, context));
            views.setImageViewBitmap(R.id.memo_expe3_ico,getRoundedCornerBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.paimon_suprise),120, context));
            views.setImageViewBitmap(R.id.memo_expe4_ico,getRoundedCornerBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.paimon_suprise),120, context));
            views.setImageViewBitmap(R.id.memo_expe5_ico,getRoundedCornerBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.paimon_suprise),120, context));

        }else{
            if (!sharedPreferences.getString("icon_name", "N/A").equals("N/A")){
                icon = sharedPreferences.getString("icon_name", "N/A");
            }else{
                icon = item_rss.getCharNameByTranslatedName(icon, context);
            }

            views.setImageViewBitmap(R.id.memo_user_icon,getRoundedCornerBitmap(BitmapFactory.decodeResource(context.getResources(),item_rss.getCharByName(icon,context)[3]),120, context));
            views.setImageViewBitmap(R.id.memo_expe1_ico,getRoundedCornerBitmap(BitmapFactory.decodeResource(context.getResources(),item_rss.getCharByName(expedition1_name,context)[3]),120, context));
            views.setImageViewBitmap(R.id.memo_expe2_ico,getRoundedCornerBitmap(BitmapFactory.decodeResource(context.getResources(),item_rss.getCharByName(expedition2_name,context)[3]),120, context));
            views.setImageViewBitmap(R.id.memo_expe3_ico,getRoundedCornerBitmap(BitmapFactory.decodeResource(context.getResources(),item_rss.getCharByName(expedition3_name,context)[3]),120, context));
            views.setImageViewBitmap(R.id.memo_expe4_ico,getRoundedCornerBitmap(BitmapFactory.decodeResource(context.getResources(),item_rss.getCharByName(expedition4_name,context)[3]),120, context));
            views.setImageViewBitmap(R.id.memo_expe5_ico,getRoundedCornerBitmap(BitmapFactory.decodeResource(context.getResources(),item_rss.getCharByName(expedition5_name,context)[3]),120, context));

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

    @Deprecated
    public void refresh(String str){

    }
    public void refresh(String str, String nickname, String server, String icon, int level){
        try {
            JSONObject jsonObject = new JSONObject(str);

            System.out.println("DailyMemo2048Service REFRESH LA");
            this.nickname = nickname;
            this.server = server;
            this.icon = icon;
            this.level = level;
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
        } catch (JSONException e) {
            LogExport.export("DailyMemo2048Service","refresh", e.getMessage(), context, DAILYMEMO);
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

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels, Context context) {
        if (bitmap == null){
            bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.paimon_suprise);
        }
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

    private String createNotificationChannel(String channelId, String channelName){
        NotificationChannel channel = new NotificationChannel(channelId,channelName, NotificationManager.IMPORTANCE_LOW);
        channel.setLightColor(Color.CYAN);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager service = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        service.createNotificationChannel(channel);
        return channelId;
    }
}