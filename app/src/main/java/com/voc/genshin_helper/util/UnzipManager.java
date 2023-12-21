package com.voc.genshin_helper.util;

import static com.voc.genshin_helper.util.LogExport.DOWNLOADTASK;
import static com.voc.genshin_helper.util.LogExport.UNZIPMANAGER;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.voc.genshin_helper.R;
import com.voc.genshin_helper.ui.CalculatorUI;
import com.voc.genshin_helper.ui.MainActivity;
import com.voc.genshin_helper.ui.SplashActivity;
import com.voc.genshin_helper.ui.SplashOldActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */
public class UnzipManager {

    Context context;
    Activity activity;
    String path;
    String zipname;
    ArrayList<String> zipnames = new ArrayList<String>();
    int total = 0;
    int curr = 0;
    boolean runStyleUI = false;

    ZipInputStream zis = null;
    Dialog2048 dialog2048 ;

    public void pbShow(String path, String zipname, Context context,Activity activity){
        this.context = context;
        this.path = path;
        this.zipname = zipname;
        this.activity = activity;
        new unzip().execute();
    }

    public void pbShowA(String path, ArrayList<String> zipnames, Context context, Activity activity){
        this.context = context;
        this.path = path;
        this.zipnames = zipnames;
        this.activity = activity;
        new unzipA().execute();
    }

    public void pbShowAWithRun(String path, ArrayList<String> zipnames, Context context, Activity activity, boolean runStyleUI){
        this.runStyleUI = runStyleUI;
        this.context = context;
        this.path = path;
        this.zipnames = zipnames;
        this.activity = activity;
        new unzipA().execute();
    }


    public class unzip extends AsyncTask<String, Integer, String> {

        /**
         * Before starting background thread
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("Starting download");

            dialog2048 = new Dialog2048();
            dialog2048.setup(context,activity);
            dialog2048.mode(Dialog2048.MODE_PROGRESS_UNZIP);
            dialog2048.show();
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            File zipFile = new File(path+"/"+zipname);
            try {
                zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));
                System.out.println("RIVER");
                ZipEntry ze;
                int count;
                byte[] buffer = new byte[8192];

                dialog2048.updateMax(new ZipFile(zipFile).size());

                while ((ze = zis.getNextEntry()) != null) {
                    File file = new File(path, ze.getName());
                    File dir = ze.isDirectory() ? file : file.getParentFile();
                    if (!dir.isDirectory() && !dir.mkdirs()){
                        LogExport.export("UnzipManager","unzip.doInBackground", "Failed to ensure directory: " +dir.getAbsolutePath(), context, UNZIPMANAGER);
                        throw new FileNotFoundException("Failed to ensure directory: " +dir.getAbsolutePath());
                    }
                    if (ze.isDirectory())
                        continue;
                    FileOutputStream fout = new FileOutputStream(file);
                    try {
                        while ((count = zis.read(buffer)) != -1){
                            fout.write(buffer, 0, count);
                        };
                    } finally {
                        fout.close();
                    }
                    curr = curr +1;
                    dialog2048.updateProgress(curr);
                }
            } catch (IOException e) {
                e.printStackTrace();
                LogExport.export("UnzipManager","unzip.doInBackground", e.getMessage(), context, UNZIPMANAGER);
            }
            return null;
        }

        /**
         * After completing background task
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            System.out.println("Downloaded");
            if(zis != null){
                try {
                    zis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            dialog2048.dismiss();
            File file = new File(path+"/"+zipname);
            if (file.exists()){
                file.delete();
            }
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {

                    SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("downloadBase",true);
                    editor.putLong("lastUpdateUnix", System.currentTimeMillis());
                    editor.apply();

                    if(context instanceof SplashActivity){
                        /*
                        ((SplashActivity) context).startActivity(new Intent(context, MainActivity.class));
                        ((SplashActivity) context).finish();
                         */
                        ((SplashOldActivity)context).checkStyleUI();
                    }
                }
            }, 10);
        }

    }

    public class unzipA extends AsyncTask<String, Integer, String> {

        /**
         * Before starting background thread
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("Starting download");

            dialog2048 = new Dialog2048();
            dialog2048.setup(context,activity);
            dialog2048.mode(Dialog2048.MODE_PROGRESS_UNZIP);
            dialog2048.show();
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {

            long zipSize = 0;
            for (int x = 0 ; x < zipnames.size() ; x++){
                File zipFile = new File(path+"/"+zipnames.get(x));
                try {
                    zipSize += new ZipFile(zipFile).size();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            dialog2048.updateMax(zipSize);

            for (int x = 0 ; x < zipnames.size() ; x++){
                File zipFile = new File(path+"/"+zipnames.get(x));
                ZipFile zipFile1 = null;
                try {
                    zipFile1 = new ZipFile(zipFile);
                    zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));
                    System.out.println("RIVER");
                    ZipEntry ze;
                    int count;
                    byte[] buffer = new byte[8192];

                    while ((ze = zis.getNextEntry()) != null) {
                        File file = new File(path, ze.getName());
                        File dir = ze.isDirectory() ? file : file.getParentFile();
                        if (!dir.isDirectory() && !dir.mkdirs()){
                            LogExport.export("UnzipManager","unzip.doInBackground", "Failed to ensure directory: " +dir.getAbsolutePath(), context, DOWNLOADTASK);
                            throw new FileNotFoundException("Failed to ensure directory: " +dir.getAbsolutePath());
                        }
                        if (ze.isDirectory())
                            continue;
                        FileOutputStream fout = new FileOutputStream(file);
                        try {
                            while ((count = zis.read(buffer)) != -1){
                                fout.write(buffer, 0, count);
                            };
                        } finally {
                            fout.close();
                        }
                        curr = curr +1;
                        dialog2048.updateProgress(curr);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    LogExport.export("DownloadTask","DownloadFileFromURLArray.doInBackground", e.getMessage(), context, UNZIPMANAGER);
                }
            }
            return null;
        }

        /**
         * After completing background task
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            System.out.println("Downloaded");
            try {
                zis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dialog2048.dismiss();
            for(int x = 0 ; x< zipnames.size() ; x++){
                File file = new File(path+"/"+zipnames.get(x));
                if (file.exists()){
                    file.delete();
                }
            }
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {

                    SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("downloadBase",true);
                    editor.putLong("lastUpdateUnix", System.currentTimeMillis());
                    editor.apply();

                    if(context instanceof SplashActivity && runStyleUI){
                        /*
                        ((SplashActivity) context).startActivity(new Intent(context, MainActivity.class));
                        ((SplashActivity) context).finish();
                         */
                        ((SplashOldActivity)context).checkStyleUI();
                    }
                }
            }, 10);
            //CustomToast.toast(context,activity,context.getString(R.string.update_download_done));

        }

    }
}
