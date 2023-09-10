package com.voc.genshin_helper.util;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import static com.voc.genshin_helper.util.Dialog2048.MODE_DOWNLOAD_PAUSED_CRASH;
import static com.voc.genshin_helper.util.LogExport.DOWNLOADTASK;
import static com.voc.genshin_helper.util.LogExport.DOWNLOAD_UNZIP_TASK;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import androidx.core.app.NotificationCompat;

import com.voc.genshin_helper.R;
import com.voc.genshin_helper.ui.MMXLVIII.Desk2048;
import com.voc.genshin_helper.ui.SplashActivity;

import org.apache.commons.io.FileUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class DownloadAndUnzipTask extends AsyncTask<Void, Integer, Void> {

    private static final String TAG = "DownloadZipFilesTask";

    private Context context;
    private ArrayList<String> urls;
    private String location;
    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;
    private int notificationId;
    //private ProgressDialog progressDialog;
    private Dialog2048 dialog2048;
    private Activity activity;

    private SharedPreferences sharedPreferences;

    DownloadAndUnzipTask downloadAndUnzipTask;


    private boolean mIsPaused;
    private boolean mIsCancelled;

    boolean isBase = false;

    public static final String baseFileName = "base_webp.zip";

    public int laterestUnix = 0;
    public int lastDownloadSize = 0;

    int checkIPAccessable = 0;
    SSLContext sslContext = null;
    public DownloadAndUnzipTask(Context context, Activity activity, ArrayList<String> urls, String location) {
        this.context = context;
        this.activity = activity;
        this.urls = urls;
        this.location = location;
        notificationId = 1;
        sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        downloadAndUnzipTask = this;
        LogExport.export("DownloadAndUnzipTask","DownloadFileFromURL(...)", "FINISH INIT", context, DOWNLOAD_UNZIP_TASK);

        //sslContext = new InitCA().initCA(context);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // 顯示進度對話框
        dialog2048 = new Dialog2048();
        dialog2048.setup(context, activity);
        dialog2048.mode(Dialog2048.MODE_PROGRESS_DOWNLOAD);
        dialog2048.show();
        LogExport.export("DownloadAndUnzipTask","onPreExecute", "Dialog display ok", context, DOWNLOAD_UNZIP_TASK);

        // 建立 NotificationCompat.Builder
        notificationBuilder = new NotificationCompat.Builder(context, "download_noti")
                .setContentTitle(context.getString(R.string.update_download_downloading))
                .setContentText("0B / 0B"+" (0B/s)")
                .setSmallIcon(android.R.drawable.stat_sys_download)
                .setAutoCancel(false)
                .setProgress(100, 0, false);

        // 建立 NotificationManager 並發送通知
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notificationBuilder.build());
        LogExport.export("DownloadAndUnzipTask","onPreExecute", "Notification ok", context, DOWNLOAD_UNZIP_TASK);

    }

    @Override
    protected Void doInBackground(Void... params) {
        /*
        //加入憑證
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("TLSv1.2");
            sc.init(null,
                    new TrustManager[] {new MyX509TrustManager() },
                    new java.security.SecureRandom());

            SSLSocketFactory ssf = sc.getSocketFactory();

            URL url = new URL("https://vt.25u.com");

            HttpsURLConnection httpsConn =
                    (HttpsURLConnection) url.openConnection();

            HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
                public boolean verify(String s, SSLSession sslsession) {
                    System.out.println(
                            "WARNING: Hostname is not matched for cert."
                    );
                    return true;
                }
            };

            httpsConn.setDefaultHostnameVerifier(ignoreHostnameVerifier);
            httpsConn.setDefaultSSLSocketFactory(ssf);
        } catch (NoSuchAlgorithmException | IOException |
                 KeyManagementException e) {
            System.out.println("NNIK : "+e.getMessage());
            throw new RuntimeException(e);
        }
         */

        for (int i = 0; i < urls.size(); i++) {
            if (!isCancelled()) {
                String urlStr = urls.get(i);
                String zipFileName = urlStr.substring(urlStr.lastIndexOf('/') + 1);
                String downloadFilePath = location + File.separator + zipFileName;
                File downloadFile = new File(downloadFilePath);

                if(zipFileName.equals("base.zip") || zipFileName.equals(baseFileName)){
                    isBase = true;
                    deletePreviousFiles(context,"db",null);
                    deletePreviousFiles(context,"drawable",null);
                    deletePreviousFiles(context,"randomScenery",null);
                    deletePreviousFiles(context,"skills",null);
                }

                // 當檔案已存在，檢查檔案大小，計算已下載的比例
                long existingFileSize = 0;
                if (downloadFile.exists()) {
                    existingFileSize = downloadFile.length();
                }

                LogExport.export("DownloadAndUnzipTask","doInBackground", "existingFileSize = "+String.valueOf(existingFileSize), context, DOWNLOAD_UNZIP_TASK);

                // 下載檔案
                try {
                    URL urlT = new URL(urlStr);
                    HttpsURLConnection connT = (HttpsURLConnection) urlT.openConnection();
                    //connT.setSSLSocketFactory(sslContext.getSocketFactory());
                    connT.setRequestMethod("GET");
                    connT.setConnectTimeout(4000);
                    connT.setReadTimeout(4000);
                    LogExport.export("DownloadAndUnzipTask","doInBackground", "connT.getResponseCode()+"+connT.getResponseCode()+ "("+connT.getResponseMessage()+")", context, DOWNLOAD_UNZIP_TASK);
                    LogExport.export("DownloadAndUnzipTask","doInBackground", "connT.usingProxy() "+ connT.usingProxy(), context, DOWNLOAD_UNZIP_TASK);
                    connT.connect();
                    LogExport.export("DownloadAndUnzipTask","doInBackground", "DOWNLOAD - connT.getContentLength() : "+connT.getContentLength()+" | existingFileSize : "+existingFileSize, context, DOWNLOAD_UNZIP_TASK);

                    if(connT.getContentLength() != existingFileSize && connT.getContentLength() != -1){
                        URL url = new URL(urlStr);
                        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                        //connT.setSSLSocketFactory(sslContext.getSocketFactory());
                        conn.setRequestMethod("GET");
                        conn.setConnectTimeout(4000);
                        conn.setReadTimeout(4000);
                        conn.setRequestProperty("Range", "bytes=" + existingFileSize + "-");
                        conn.connect();

                        LogExport.export("DownloadAndUnzipTask","doInBackground", "DOWNLOAD - conn.getContentLength() : "+conn.getContentLength(), context, DOWNLOAD_UNZIP_TASK);

                        BufferedInputStream inputStream = new BufferedInputStream(conn.getInputStream());
                        FileOutputStream outputStream = new FileOutputStream(downloadFilePath, true);

                        int counter = 0;

                        byte[] buffer = new byte[1024];
                        int count;

                        long fileSourceSize = (conn.getContentLength() == 0 ? 1 : conn.getContentLength());

                        //初始化Dialog
                        dialog2048.updateMax(fileSourceSize+existingFileSize);
                        publishProgress(0, Dialog2048.MODE_PROGRESS_DOWNLOAD, (int) System.currentTimeMillis());
                        LogExport.export("DownloadAndUnzipTask","doInBackground", "DOWNLOAD - Dialog max & mode init : "+"MODE_PROGRESS_DOWNLOAD", context, DOWNLOAD_UNZIP_TASK);
                        laterestUnix = (int) System.currentTimeMillis();
                        lastDownloadSize = (int) existingFileSize;

                        while ((count = inputStream.read(buffer, 0, 1024)) != -1) {
                            existingFileSize += count;
                            outputStream.write(buffer, 0, count);

                            if (counter >= 750) {
                                // 發佈進度
                                publishProgress((int) existingFileSize, Dialog2048.MODE_PROGRESS_DOWNLOAD, (int) System.currentTimeMillis());
                                counter = 0;
                            } else {
                                counter++;
                            }
                        }

                        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        if(isBase){
                            editor.putBoolean("downloadBase",true);
                        }
                        editor.putLong("lastUpdateUnix", System.currentTimeMillis());
                        editor.apply();

                        LogExport.export("DownloadAndUnzipTask","doInBackground", "DOWNLOAD - Finish count++ loop", context, DOWNLOAD_UNZIP_TASK);

                        outputStream.flush();
                        outputStream.close();
                        inputStream.close();
                        LogExport.export("DownloadAndUnzipTask","doInBackground", "DOWNLOAD - steams were closed", context, DOWNLOAD_UNZIP_TASK);

                    }
                } catch (IOException e) {
                    LogExport.export("DownloadAndUnzipTask","doInBackground", "DOWNLOAD - ERROR ! "+ e.getMessage()+" -> \n"+Arrays.toString(e.getStackTrace()), context, DOWNLOAD_UNZIP_TASK);
                    e.printStackTrace();

                }

                // 解壓縮檔案
                int zipSize = 1;
                try {
                    ZipInputStream zipInputStream = new ZipInputStream(FileUtils.openInputStream(downloadFile));
                    ZipEntry zipEntry;
                    byte[] buffer = new byte[1024];

                    ZipFile zipFile = new ZipFile(downloadFilePath);
                    zipSize = zipFile.size();
                    LogExport.export("DownloadAndUnzipTask","doInBackground", "UNZIP - zipSize : "+zipFile.size(), context, DOWNLOAD_UNZIP_TASK);
                    if (zipFile.size() > 0) {
                        //初始化Dialog
                        dialog2048.updateMax((zipFile.size() > 0 ? zipFile.size() : 1));
                        publishProgress(0, Dialog2048.MODE_PROGRESS_UNZIP);
                        LogExport.export("DownloadAndUnzipTask","doInBackground", "UNZIP - Dialog max & mode init : "+"MODE_PROGRESS_UNZIP", context, DOWNLOAD_UNZIP_TASK);
                    }
                    int counter = 0;
                    LogExport.export("DownloadAndUnzipTask","doInBackground", "UNZIP - begin unzipping", context, DOWNLOAD_UNZIP_TASK);

                    while ((zipEntry = zipInputStream.getNextEntry()) != null && !isCancelled()) {
                        String fileName = zipEntry.getName();
                        File outputFile = new File(location, fileName);

                        if (zipEntry.isDirectory()) {
                            outputFile.mkdirs();
                        } else {
                            FileOutputStream outputStream = new FileOutputStream(outputFile);
                            int count;
                            while ((count = zipInputStream.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, count);
                            }
                            outputStream.flush();
                            outputStream.close();

                        }

                        if (counter % 150 == 0) {
                            publishProgress(counter, Dialog2048.MODE_PROGRESS_UNZIP);
                        }
                        counter++;

                        zipInputStream.closeEntry();
                    }
                    LogExport.export("DownloadAndUnzipTask","doInBackground", "UNZIP - finish unzipping", context, DOWNLOAD_UNZIP_TASK);
                    zipInputStream.close();
                    LogExport.export("DownloadAndUnzipTask","doInBackground", "UNZIP - steams were closed", context, DOWNLOAD_UNZIP_TASK);


                    // 確保完全解壓縮後刪除下載的 Zip 檔案
                    if(zipSize == counter){
                        FileUtils.forceDelete(downloadFile);
                    }
                    LogExport.export("DownloadAndUnzipTask","doInBackground", "UNZIP - zip file has been deleted", context, DOWNLOAD_UNZIP_TASK);

                } catch (IOException e) {
                    LogExport.export("DownloadAndUnzipTask","doInBackground", "UNZIP - ERROR ! "+e.getMessage(), context, DOWNLOAD_UNZIP_TASK);
                    e.printStackTrace();
                }
            }else{
                publishProgress(0, MODE_DOWNLOAD_PAUSED_CRASH);
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);

        // 更新進度對話框和通知
        dialog2048.updateProgressWaited(progress[0]);
        dialog2048.mode(progress[1]);

        notificationBuilder.setProgress((int) dialog2048.getMax(), progress[0], false);
        if(progress[1] == Dialog2048.MODE_PROGRESS_DOWNLOAD){
            long speed = (progress[0] - lastDownloadSize) / (progress[2] - laterestUnix) * 1000L;
            notificationBuilder.setContentTitle(context.getString(R.string.update_download_downloading));
            notificationBuilder.setContentText(dialog2048.prettyByteCount(progress[0]) + " / " + dialog2048.prettyByteCount(dialog2048.getMax()) +"("+prettyByteCount(speed)+"/s)");

            laterestUnix = progress[2];
            lastDownloadSize = progress[0];
        } else if (progress[1] == Dialog2048.MODE_PROGRESS_UNZIP) {
            notificationBuilder.setContentTitle(context.getString(R.string.update_download_unzipping));
            notificationBuilder.setContentText(dialog2048.prettyCount(progress[0]) + " / " + dialog2048.prettyCount(dialog2048.getMax()) );

        }



        if(sharedPreferences.getBoolean("appStopped", false) && !isCancelled() || progress[1] == MODE_DOWNLOAD_PAUSED_CRASH){
            notificationBuilder.setContentTitle(context.getString(R.string.download_cancelled));
            notificationBuilder.setSmallIcon(R.drawable.paimon_lost);
            notificationManager.notify(notificationId, notificationBuilder.build());
            downloadAndUnzipTask.cancel(true);
        }

        notificationManager.notify(notificationId, notificationBuilder.build());

    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        // 隱藏進度對話框和通知
        dialog2048.dismiss();
        notificationManager.cancel(notificationId);

        if(isBase){
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {

                    SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("downloadBase",true);
                    editor.putLong("lastUpdateUnix", System.currentTimeMillis());
                    editor.apply();
                }
            }, 10);
        }

        if(context instanceof SplashActivity){
            ((SplashActivity)context).checkStyleUI();
        } else if (context instanceof Desk2048) {
            ((Desk2048)context).refreshUI();
        }
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

        int decimal_num = 2;//sharedPreferences.getInt("decimal_num", 0);
        boolean decimal  = sharedPreferences.getBoolean("decimal", false);
        if (base < suffix.length) {
            return new DecimalFormat("##.##").format(numValue / Math.pow(1024, base)) + suffix[base];
            // Muility
        } else {
            return new DecimalFormat("#,###").format(numValue);
        }
    }

    private void deletePreviousFiles(Context context,String folderName, File childx){
        File folder = null;

        if (!folderName.equals("") && !folderName.equals("NonRoot") && folderName != null){
            folder = new File(context.getFilesDir().getAbsolutePath(),folderName);
        }else{
            folder = childx;
        }

        int failCount = 0;
        int totalCount = 0;
        if (folder.exists() && folder.isDirectory()){
            for (File child : folder.listFiles()) {
                totalCount ++;
                if(child.isDirectory()){
                    deletePreviousFiles(context, "NonRoot",child);
                }else{
                    if (!child.delete()){failCount++;}
                }
            }
            LogExport.export("DownloadAndUnzipTask","deletePreviousFiles", "DELETE FOLDER IN "+(childx != null ? childx.getAbsolutePath() : folderName)+" SUCCESSFUL ? "+folder.delete() + " | FAILED : "+failCount+"/"+totalCount, context, DOWNLOAD_UNZIP_TASK);
        }

    }
}