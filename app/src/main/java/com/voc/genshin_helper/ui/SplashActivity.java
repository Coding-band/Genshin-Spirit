package com.voc.genshin_helper.ui;/*
 * Package com.voc.genshin_helper.ui.SplashActivity was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

import static android.Manifest.permission.POST_NOTIFICATIONS;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_MEDIA_IMAGES;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;
import static com.voc.genshin_helper.util.DownloadAndUnzipTask.baseFileName;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import com.squareup.picasso.Picasso;
import com.voc.genshin_helper.BuildConfig;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.ui.MMXLVIII.Desk2048;
import com.voc.genshin_helper.util.Dialog2048;
import com.voc.genshin_helper.util.DownloadAndUnzipTask;
import com.voc.genshin_helper.util.LogExport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class SplashActivity extends AppCompatActivity {

    public int REQUEST_CODE = 2296;
    Context context;
    Activity activity;
    String choice = "N/A";
    SharedPreferences sharedPreferences;

    int randNum = 0;
    int randMax = 0;
    ArrayList<String> randBgTitleZH = new ArrayList<>();
    ArrayList<String> randBgTitleEN = new ArrayList<>();
    ArrayList<String> randBgAuthor = new ArrayList<>();
    ArrayList<String> randBgFileName = new ArrayList<>();

    private ActivityResultContracts.RequestMultiplePermissions multiplePermissionsContract;
    private ActivityResultLauncher<String[]> multiplePermissionLauncher;

    final String[] PERMISSIONS_API33 = {
            READ_MEDIA_IMAGES,
            POST_NOTIFICATIONS
    };

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTheme(R.style.SplashTheme);
        setContentView(R.layout.activity_splash_new);

        context = this;
        activity = this;

        sharedPreferences = getSharedPreferences("user_info", 0);
        if (sharedPreferences.getBoolean("theme_light", true) == true) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }else if (sharedPreferences.getBoolean("theme_night", false) == true) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else if (sharedPreferences.getBoolean("theme_default", false) == true) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }

        sharedPreferences.edit().putBoolean("appStopped",false).apply();
        
        LogExport.init(context);
        ((TextView) findViewById(R.id.splash_version)).setText(BuildConfig.VERSION_NAME);

        if (sharedPreferences.getBoolean("isRandomTheme",true) == true){
            ((ConstraintLayout) findViewById(R.id.splash_rand_cons)).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.splash_random_bg)).setVisibility(View.VISIBLE);
            ((ConstraintLayout) findViewById(R.id.splash_base_cons)).setVisibility(View.GONE);

            String suffix = ItemRss.IMG_FORMAT;

            if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                suffix = "_crop"+ ItemRss.IMG_FORMAT;
            }

            String json_base = ItemRss.LoadAssestData(context,"randomScenery/rss_bg.json");
            //Get data from JSON
            try {
                JSONArray array = new JSONArray(json_base);
                randNum = (int) (Math.random()*array.length()) ;
                JSONObject object = array.getJSONObject(randNum);

                String url = "file:///android_asset/randomScenery/"+object.getString("img_name")+suffix;
                URLEncoder.encode(url, "UTF-8");

                Picasso.get()
                        .load (url)
                        .fit()
                        .error (R.drawable.demo_random_sencery)
                        .into ((ImageView) findViewById(R.id.splash_random_bg));

                ((TextView) findViewById(R.id.splash_rand_title)).setText(object.getString("title_en"));
                switch (sharedPreferences.getString("curr_lang","en-US")){
                    case "zh-HK" :
                    case "zh-tw" :
                    case "zh-cn" :
                        ((TextView) findViewById(R.id.splash_rand_title)).setText(object.getString("title_zh"));
                        break;
                    default:
                        ((TextView) findViewById(R.id.splash_rand_title)).setText(object.getString("title_en"));
                        break;
                }

                ((TextView) findViewById(R.id.splash_rand_author)).setText(object.getString("author"));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }else{
            ((ConstraintLayout) findViewById(R.id.splash_rand_cons)).setVisibility(View.GONE);
            ((ConstraintLayout) findViewById(R.id.splash_base_cons)).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.splash_random_bg)).setVisibility(View.GONE);

            //((TextView) findViewById(R.id.splash_base_title)).setText("");
            //((TextView) findViewById(R.id.splash_base_subtitle)).setText("");
        }

        String[] checkList = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, Manifest.permission.EXPAND_STATUS_BAR};
        List<String> needRequestList = checkPermission(activity, checkList);

        if (SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(hasPermissions(PERMISSIONS_API33)){
                goMain();
            }else{
                //https://stackoverflow.com/questions/66475027/activityresultlauncher-with-requestmultiplepermissions-contract-doesnt-show-per
                multiplePermissionsContract = new ActivityResultContracts.RequestMultiplePermissions();
                multiplePermissionLauncher = registerForActivityResult(multiplePermissionsContract, isGranted -> {
                    if (isGranted.containsValue(false)) {
                        multiplePermissionLauncher.launch(PERMISSIONS_API33);
                    }
                    if(hasPermissions(PERMISSIONS_API33)){
                        goMain();
                    }
                });
                multiplePermissionLauncher.launch(PERMISSIONS_API33);
            }

        }else{
            if (!needRequestList.isEmpty()) {
                requestPermission(activity, needRequestList.toArray(new String[needRequestList.size()]));
            } else {
                goMain();
            }
        }

    }

    private boolean hasPermissions(String[] permissions) {
        if (permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    Log.d("PERMISSIONS", "Permission is not granted: " + permission);
                    return false;
                }
                Log.d("PERMISSIONS", "Permission already granted: " + permission);
            }
            return true;
        }
        return false;
    }

    // Must let it be AsyncTask !
    private void goMain() {
        runDesk(sharedPreferences);
    }

    //https://blog.csdn.net/fitaotao/article/details/119700579

    private List<String> checkPermission(Context context, String[] checkList) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < checkList.length; i++) {
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(context, checkList[i])) {
                list.add(checkList[i]);
            }
        }
        return list;
    }

    //申請許可權
    private void requestPermission(Activity activity, String requestPermissionList[]) {
        ActivityCompat.requestPermissions(activity, requestPermissionList, 100);
    }


    //用戶作出選擇後，返回申請的結果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            boolean isREADABLE = false;
            boolean isWRITEABLE = false;
            System.out.println(Arrays.asList(permissions));
            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equals(READ_EXTERNAL_STORAGE)) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Log.w("存儲許可權", "申請成功");
                        isREADABLE = true;
                    } else {
                        Log.w("存儲許可權", "申請失敗");
                        isREADABLE = false;
                    }
                }else if (permissions[i].equals(WRITE_EXTERNAL_STORAGE)) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Log.w("寫錄許可權", "申請成功");
                        isWRITEABLE = true;
                    } else {
                        Log.w("寫錄許可權", "申請失敗");
                        isWRITEABLE = false;
                    }
                }
            }

            if (isREADABLE && isWRITEABLE){
                goMain();
            }else{
                finish();
            }

        }
    }

    private void runDesk(SharedPreferences sharedPreferences) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent();
                intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
                sendBroadcast(intent);

                /**
                 * After Genshin 3.6, Material & Classic theme will not be longer support
                 * Also, it's not possible to change the theme (both in UI and editing value.) anymore.
                 */

                sharedPreferences.edit().putString("styleUI","2O48").apply();
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, Desk2048.class));

                /*
                if (sharedPreferences.getString("styleUI", "N/A").equals("2O48")) {
                    sharedPreferences.edit().putString("curr_ui_grid","2").apply();
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, Desk2048.class));
                } else if (sharedPreferences.getString("styleUI", "N/A").equals("SipTik")) {
                    sharedPreferences.edit().putString("curr_ui_grid","5").apply();
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, DeskSipTik.class));
                } else if (sharedPreferences.getString("styleUI", "N/A").equals("Voc")) {
                    sharedPreferences.edit().putString("curr_ui_grid","4").apply();
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {
                    sharedPreferences.edit().putString("curr_ui_grid","4").apply();
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
                 */
                SplashActivity.this.finish();

            }
        }, 500);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}