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
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
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
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.LogExport;
import com.voc.genshin_helper.util.RemoteFileSizeFetcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.net.ssl.HttpsURLConnection;

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

        if (BuildConfig.FLAVOR.equals("dev")){
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
        }

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

        if (sharedPreferences.getBoolean("isRandomTheme",true) == true && sharedPreferences.getBoolean("downloadBase", false) == true){
            ((ConstraintLayout) findViewById(R.id.splash_rand_cons)).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.splash_random_bg)).setVisibility(View.VISIBLE);
            ((ConstraintLayout) findViewById(R.id.splash_base_cons)).setVisibility(View.GONE);

            String suffix = ItemRss.IMG_FORMAT;

            if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                suffix = "_crop"+ ItemRss.IMG_FORMAT;
            }

            String json_base = LoadData("randomScenery/rss_bg.json");
            //Get data from JSON
            try {
                JSONArray array = new JSONArray(json_base);
                randNum = (int) (Math.random()*array.length()) ;
                JSONObject object = array.getJSONObject(randNum);

                System.out.println("randNum : "+randNum);

                System.out.println("IMG NAME : "+"/randomScenery/"+object.getString("img_name")+suffix);

                Picasso.get()
                        .load (FileLoader.loadIMG("/randomScenery/"+object.getString("img_name")+suffix,context))
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
            }
        }else{
            ((ConstraintLayout) findViewById(R.id.splash_rand_cons)).setVisibility(View.GONE);
            ((ConstraintLayout) findViewById(R.id.splash_base_cons)).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.splash_random_bg)).setVisibility(View.GONE);

            //((TextView) findViewById(R.id.splash_base_title)).setText("");
            //((TextView) findViewById(R.id.splash_base_subtitle)).setText("");
        }


        PackageManager manager=getPackageManager();
        manager.setComponentEnabledSetting(new ComponentName(SplashActivity.this,"com.voc.genshin_helper.ui.SplashActivity")
                ,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP);
        manager.setComponentEnabledSetting(new ComponentName(SplashActivity.this,"com.voc.genshin_helper.ui.SplashActivityAlias")
                ,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
        manager.setComponentEnabledSetting(new ComponentName(SplashActivity.this,"com.voc.genshin_helper.ui.SplashActivityUkraine")
                ,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);

        if(sharedPreferences.getBoolean("supportUkraine", false) == true) {
            manager.setComponentEnabledSetting(new ComponentName(SplashActivity.this,"com.voc.genshin_helper.ui.SplashActivityUkraine")
                    ,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP);
            manager.setComponentEnabledSetting(new ComponentName(SplashActivity.this,"com.voc.genshin_helper.ui.SplashActivity")
                    ,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
            manager.setComponentEnabledSetting(new ComponentName(SplashActivity.this,"com.voc.genshin_helper.ui.SplashActivityAlias")
                    ,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
        }

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        NotificationChannel channel = new NotificationChannel("download_noti", "Download Notification", NotificationManager.IMPORTANCE_LOW);
        channel.setDescription("Download Notification");
        notificationManager.createNotificationChannel(channel);

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
        sharedPreferences = getSharedPreferences("user_info", 0);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(ItemRss.SERVER_DOWNLOAD_ROOT+baseFileName);
        long remoteFileSize = getRemoteFileSize(arrayList);
        if(remoteFileSize > 10000000){
            if (sharedPreferences.getBoolean("downloadBase", false) == false) {
                /**
                 * Build a class in util -> Dialog2048
                 */

                Dialog2048 dialog2048 = new Dialog2048();
                dialog2048.setup(context,activity);
                dialog2048.updateMax(remoteFileSize);
                dialog2048.mode(Dialog2048.MODE_DOWNLOAD_BASE);
                dialog2048.show();

                dialog2048.getPositiveBtn().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2048.dismiss();
                        //DownloadTask downloadTask = new DownloadTask();
                        //downloadTask.start("https://vt.25u.com/genshin_spirit/base.zip", "base.zip", "/base.zip", context, activity);
                        ArrayList<String> downloadList = new ArrayList<>();
                        downloadList.add(ItemRss.SERVER_DOWNLOAD_ROOT+baseFileName);

                        new DownloadAndUnzipTask(context,activity,downloadList,context.getFilesDir().getAbsolutePath()).execute();
                    }
                });

                dialog2048.getNegativeBtn().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2048.dismiss();
                        finish();
                    }
                });

            } else {
                check_updates(remoteFileSize);
            }
        }else{
            runDesk(sharedPreferences);
        }

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

    public String prettyByteCount(Number number) {
        String[] suffix = {"B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};
        long numValue = ((long) number.longValue());
        int base = 0;
        double tmp_val = numValue;
        while (tmp_val > 1024) {
            tmp_val = tmp_val / 1024;
            base = base + 1;
        }

        sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE);
        int decimal_num = 2;//sharedPreferences.getInt("decimal_num", 0);
        boolean decimal = sharedPreferences.getBoolean("decimal", false);
        if (base < suffix.length) {
            return new DecimalFormat("##.##").format(numValue / Math.pow(1024, base)) + suffix[base];
            // Muility
        } else {
            return new DecimalFormat("#,###").format(numValue);
        }
    }

    public long getRemoteFileSize(ArrayList<String> urlSTR){
        CompletableFuture<Long> completableFuture = new CompletableFuture<>();

        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... params) {
                long fileSize = 0;
                try {
                    for (int x = 0 ; x < urlSTR.size() ; x++){
                        URL url = new URL(urlSTR.get(x));
                        URLConnection conn = url.openConnection();
                        conn.connect();
                        fileSize = conn.getContentLengthLong();
                        conn.getInputStream().close();

                        System.out.println("HI THERE : "+fileSize);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                completableFuture.complete(fileSize);
                return fileSize;
            }
        }.execute();

        try {
            return completableFuture.get(5000, TimeUnit.MILLISECONDS);
        } catch (ExecutionException  e) {
            LogExport.export("SplashActivity", "getRemoteFileSize()", "ExecutionException : "+e.getMessage(), context, LogExport.FETCH_FILE_FUTURE);
            runDesk(sharedPreferences);
            return -2;
        } catch (InterruptedException e) {
            LogExport.export("SplashActivity", "getRemoteFileSize()", "InterruptedException : "+e.getMessage(), context, LogExport.FETCH_FILE_FUTURE);
            runDesk(sharedPreferences);
            return -3;
        } catch (TimeoutException e){
            LogExport.export("SplashActivity", "getRemoteFileSize()", "TimeoutException : "+e.getMessage(), context, LogExport.FETCH_FILE_FUTURE);
            return -4;
        }
    }


    public void check_updates(long remoteFileSize) {
        sharedPreferences = context.getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(remoteFileSize > 10000000){
            OkHttpClient client = new OkHttpClient();
            String url = ItemRss.SERVER_DOWNLOAD_ROOT+"update.json";
            if (BuildConfig.FLAVOR.equals("dev")){
                url = ItemRss.SERVER_DOWNLOAD_ROOT+"update_dev.json";
            }

            String finalUrl = url;
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        Request request = new Request.Builder().url(finalUrl).build();
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

                            long lastUnix = System.currentTimeMillis();
                            if (i == 0) {
                                lastUnix = release_unix;
                            }

                            if (release_unix > sharedPreferences.getLong("lastUpdateUnix", 1)) {
                                array_download.add(ItemRss.SERVER_DOWNLOAD_ROOT + fileName);
                                array_fileName.add(fileName);
                                array_SfileName.add("/" + fileName);
                            }
                        }
                        if (array_download.size() > 0) {
                            ArrayList<String> arrayList = new ArrayList<>();
                            arrayList.add(ItemRss.SERVER_DOWNLOAD_ROOT + baseFileName);
                            if (getRemoteFileSize(arrayList) > getRemoteFileSize(array_download)) {

                                Dialog2048 dialog2048 = new Dialog2048();
                                dialog2048.setup(context, activity);
                                dialog2048.updateMax(getRemoteFileSize(array_download));
                                dialog2048.mode(Dialog2048.MODE_DOWNLOAD_UPDATE);
                                dialog2048.show();

                                dialog2048.getPositiveBtn().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        dialog2048.dismiss();
                                        //DownloadTask downloadTask = new DownloadTask();
                                        //downloadTask.startAWithRun(array_download, array_fileName, array_SfileName, context, activity, true);

                                        new DownloadAndUnzipTask(context, activity, array_download, context.getFilesDir().getAbsolutePath()).execute();
                                        editor.apply();
                                    }
                                });
                                dialog2048.getNegativeBtn().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        dialog2048.dismiss();
                                        runDesk(sharedPreferences);
                                    }
                                });


                            } else {
                                Dialog2048 dialog2048 = new Dialog2048();
                                dialog2048.setup(context, activity);

                                dialog2048.updateMax(getRemoteFileSize(arrayList));
                                dialog2048.mode(Dialog2048.MODE_DOWNLOAD_BASE);
                                dialog2048.show();

                                dialog2048.getPositiveBtn().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //DownloadTask downloadTask = new DownloadTask();
                                        //downloadTask.start("https://vt.25u.com/genshin_spirit/base.zip", "base.zip", "/base.zip", context, activity);

                                        ArrayList<String> downloadList = new ArrayList<>();
                                        downloadList.add(ItemRss.SERVER_DOWNLOAD_ROOT + baseFileName);

                                        new DownloadAndUnzipTask(context, activity, downloadList, context.getFilesDir().getAbsolutePath()).execute();
                                    }
                                });
                                dialog2048.getNegativeBtn().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        runDesk(sharedPreferences);
                                    }
                                });
                            }

                        } else {
                            //CustomToast.toast(context, this, context.getString(R.string.update_download_not_found_update));
                            checkStyleUI();
                        }
                    }catch (JSONException e) {
                        LogExport.export("SplashActivity", "getRemoteFileSize()", "JSONException : "+e.getMessage(), context, LogExport.FETCH_FILE_FUTURE);
                    }catch (IOException e) {
                        LogExport.export("SplashActivity", "getRemoteFileSize()", "IOException : "+e.getMessage(), context, LogExport.FETCH_FILE_FUTURE);
                    }
                    return null;
                }
            }.execute();
        }else{
            runDesk(sharedPreferences);
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
        }, 2000);
    }

    public void checkStyleUI() {
        if (sharedPreferences.getString("styleUI", "N/A").equals("N/A")) {

            sharedPreferences.edit().putString("styleUI", choice).apply();
            runDesk(sharedPreferences);
            /*
            final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
            View view = View.inflate(context, R.layout.fragment_choose_style_ui, null);
            WebView webView = view.findViewById(R.id.webView);
            RadioButton style_Voc_rb = view.findViewById(R.id.ui_Voc_rb);
            RadioButton style_2O48_rb = view.findViewById(R.id.ui_2O48_rb);
            RadioButton style_SipTik_rb = view.findViewById(R.id.ui_SipTik_rb);
            FrameLayout menu_ok = view.findViewById(R.id.menu_ok);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            style_Voc_rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    style_Voc_rb.setChecked(true);
                    style_2O48_rb.setChecked(false);
                    style_SipTik_rb.setChecked(false);
                    choice = "Voc";
                }
            });
            style_2O48_rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    style_Voc_rb.setChecked(false);
                    style_2O48_rb.setChecked(true);
                    style_SipTik_rb.setChecked(false);
                    choice = "2O48";
                }
            });
            style_SipTik_rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("styleUI", "SipTik");
                    editor.apply();
                    style_Voc_rb.setChecked(false);
                    style_2O48_rb.setChecked(false);
                    style_SipTik_rb.setChecked(true);
                    choice = "SipTik";
                }
            });
            menu_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!choice.equals("N/A")) {
                        editor.putString("styleUI", choice);
                        //webView.loadUrl("https://vt.25u.com/genshin_spirit/dataCollection/styleInsert.php?unix="+System.currentTimeMillis()+"&style="+choice+"&record_location="+"Splash"+"&device_name="+Build.MODEL+"&app_version="+BuildConfig.VERSION_NAME+"&android_api="+String.valueOf(android.os.Build.VERSION.SDK_INT));
                        //editor.putBoolean("firstCheck",true);
                        editor.apply();
                        dialog.dismiss();
                        runDesk(sharedPreferences);
                    }
                }
            });
            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(true);
            Window dialogWindow = dialog.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            lp.width = (int) (width * 0.9);
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.CENTER;
            dialogWindow.setAttributes(lp);
            dialog.show();
             */
        } else {
            runDesk(sharedPreferences);
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}