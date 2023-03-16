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
import com.voc.genshin_helper.ui.SplashActivity;

import org.apache.commons.io.FileUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

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

    int checkIPAccessable = 0;
    public DownloadAndUnzipTask(Context context, Activity activity, ArrayList<String> urls, String location) {
        this.context = context;
        this.activity = activity;
        this.urls = urls;
        this.location = location;
        notificationId = 1;
        sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        downloadAndUnzipTask = this;LogExport.export("DownloadAndUnzipTask","DownloadFileFromURL(...)", "FINISH INIT", context, DOWNLOAD_UNZIP_TASK);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // 顯示進度對話框
        dialog2048 = new Dialog2048();
        dialog2048.setup(context, activity);
        dialog2048.mode(Dialog2048.MODE_PROGRESS_DOWNLOAD);
        dialog2048.show();
        downloadAndUnzipTask = this;LogExport.export("DownloadAndUnzipTask","onPreExecute", "Dialog display ok", context, DOWNLOAD_UNZIP_TASK);

        // 建立 NotificationCompat.Builder
        notificationBuilder = new NotificationCompat.Builder(context, "download_noti")
                .setContentTitle(context.getString(R.string.update_download_downloading))
                .setContentText("0B / 0B")
                .setSmallIcon(android.R.drawable.stat_sys_download)
                .setAutoCancel(false)
                .setProgress(100, 0, false);

        // 建立 NotificationManager 並發送通知
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notificationBuilder.build());
        downloadAndUnzipTask = this;LogExport.export("DownloadAndUnzipTask","onPreExecute", "Notification ok", context, DOWNLOAD_UNZIP_TASK);

    }

    @Override
    protected Void doInBackground(Void... params) {

        for (int i = 0; i < urls.size(); i++) {
            if (!isCancelled()) {
                String urlStr = urls.get(i);
                String zipFileName = urlStr.substring(urlStr.lastIndexOf('/') + 1);
                String downloadFilePath = location + File.separator + zipFileName;
                File downloadFile = new File(downloadFilePath);

                if(zipFileName.equals("base.zip")){
                    isBase = true;
                }

                // 當檔案已存在，檢查檔案大小，計算已下載的比例
                long existingFileSize = 0;
                if (downloadFile.exists()) {
                    existingFileSize = downloadFile.length();
                }

                downloadAndUnzipTask = this;LogExport.export("DownloadAndUnzipTask","doInBackground", "existingFileSize = "+String.valueOf(existingFileSize), context, DOWNLOAD_UNZIP_TASK);

                // 下載檔案
                try {
                    URL urlT = new URL(urlStr);
                    HttpURLConnection connT = (HttpURLConnection) urlT.openConnection();
                    connT.setRequestMethod("GET");
                    connT.setConnectTimeout(10000);
                    connT.setReadTimeout(10000);
                    connT.connect();

                    downloadAndUnzipTask = this;LogExport.export("DownloadAndUnzipTask","doInBackground", "DOWNLOAD - connT.getContentLength() : "+connT.getContentLength()+" | existingFileSize : "+existingFileSize, context, DOWNLOAD_UNZIP_TASK);

                    if(connT.getContentLength() != existingFileSize){
                        URL url = new URL(urlStr);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setConnectTimeout(10000);
                        conn.setReadTimeout(10000);
                        conn.setRequestProperty("Range", "bytes=" + existingFileSize + "-");
                        conn.connect();

                        downloadAndUnzipTask = this;LogExport.export("DownloadAndUnzipTask","doInBackground", "DOWNLOAD - conn.getContentLength() : "+conn.getContentLength(), context, DOWNLOAD_UNZIP_TASK);

                        BufferedInputStream inputStream = new BufferedInputStream(conn.getInputStream());
                        FileOutputStream outputStream = new FileOutputStream(downloadFilePath, true);

                        int counter = 0;

                        byte[] buffer = new byte[1024];
                        int count;

                        long fileSourceSize = (conn.getContentLength() == 0 ? 1 : conn.getContentLength());

                        //初始化Dialog
                        dialog2048.updateMax(fileSourceSize+existingFileSize);
                        publishProgress(0, Dialog2048.MODE_PROGRESS_DOWNLOAD);
                        downloadAndUnzipTask = this;LogExport.export("DownloadAndUnzipTask","doInBackground", "DOWNLOAD - Dialog max & mode init : "+"MODE_PROGRESS_DOWNLOAD", context, DOWNLOAD_UNZIP_TASK);

                        while ((count = inputStream.read(buffer, 0, 1024)) != -1) {
                            existingFileSize += count;
                            outputStream.write(buffer, 0, count);

                            if (counter >= 750) {
                                // 發佈進度
                                publishProgress((int) existingFileSize, Dialog2048.MODE_PROGRESS_DOWNLOAD);
                                counter = 0;
                            } else {
                                counter++;
                            }
                        }
                        downloadAndUnzipTask = this;LogExport.export("DownloadAndUnzipTask","doInBackground", "DOWNLOAD - Finish count++ loop", context, DOWNLOAD_UNZIP_TASK);

                        outputStream.flush();
                        outputStream.close();
                        inputStream.close();
                        downloadAndUnzipTask = this;LogExport.export("DownloadAndUnzipTask","doInBackground", "DOWNLOAD - steams were closed", context, DOWNLOAD_UNZIP_TASK);

                    }
                } catch (IOException e) {
                    downloadAndUnzipTask = this;LogExport.export("DownloadAndUnzipTask","doInBackground", "DOWNLOAD - ERROR ! "+e.getMessage(), context, DOWNLOAD_UNZIP_TASK);
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
                    downloadAndUnzipTask = this;LogExport.export("DownloadAndUnzipTask","doInBackground", "UNZIP - zipSize : "+zipFile.size(), context, DOWNLOAD_UNZIP_TASK);
                    if (zipFile.size() > 0) {
                        //初始化Dialog
                        dialog2048.updateMax((zipFile.size() > 0 ? zipFile.size() : 1));
                        publishProgress(0, Dialog2048.MODE_PROGRESS_UNZIP);
                        downloadAndUnzipTask = this;LogExport.export("DownloadAndUnzipTask","doInBackground", "UNZIP - Dialog max & mode init : "+"MODE_PROGRESS_UNZIP", context, DOWNLOAD_UNZIP_TASK);
                    }
                    int counter = 0;
                    downloadAndUnzipTask = this;LogExport.export("DownloadAndUnzipTask","doInBackground", "UNZIP - begin unzipping", context, DOWNLOAD_UNZIP_TASK);

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
                    downloadAndUnzipTask = this;LogExport.export("DownloadAndUnzipTask","doInBackground", "UNZIP - finish unzipping", context, DOWNLOAD_UNZIP_TASK);
                    zipInputStream.close();
                    downloadAndUnzipTask = this;LogExport.export("DownloadAndUnzipTask","doInBackground", "UNZIP - steams were closed", context, DOWNLOAD_UNZIP_TASK);


                    // 確保完全解壓縮後刪除下載的 Zip 檔案
                    if(zipSize == counter){
                        FileUtils.forceDelete(downloadFile);
                    }
                    downloadAndUnzipTask = this;LogExport.export("DownloadAndUnzipTask","doInBackground", "UNZIP - zip file has been deleted", context, DOWNLOAD_UNZIP_TASK);

                } catch (IOException e) {
                    downloadAndUnzipTask = this;LogExport.export("DownloadAndUnzipTask","doInBackground", "UNZIP - ERROR ! "+e.getMessage(), context, DOWNLOAD_UNZIP_TASK);
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
            notificationBuilder.setContentTitle(context.getString(R.string.update_download_downloading));
            notificationBuilder.setContentText(dialog2048.prettyByteCount(progress[0]) + " / " + dialog2048.prettyByteCount(dialog2048.getMax()) );

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
        }
    }

}