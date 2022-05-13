package com.voc.genshin_helper.util;

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
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Project "Genshin Spirit" (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */
public class UnzipManager {

    ProgressDialog pDialog;
    Context context;
    Activity activity;
    String path;
    String zipname;
    ArrayList<String> zipnames = new ArrayList<String>();
    int total = 0;
    int curr = 0;
    boolean runStyleUI = false;

    ZipInputStream zis = null;

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

            pDialog = new ProgressDialog(context, R.style.AlertDialogCustom);
            pDialog.setMessage(context.getString(R.string.update_download_unzipping));
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pDialog.show();
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

                ZipFile zipFile1 = new ZipFile(zipFile);
                pDialog.setMax( zipFile1.size());

                while ((ze = zis.getNextEntry()) != null) {
                    File file = new File(path, ze.getName());
                    File dir = ze.isDirectory() ? file : file.getParentFile();
                    if (!dir.isDirectory() && !dir.mkdirs())
                        throw new FileNotFoundException("Failed to ensure directory: " +dir.getAbsolutePath());
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
                    pDialog.setProgress(curr);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
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
            pDialog.dismiss();
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
                    editor.apply();
                    if(context instanceof SplashActivity){
                        /*
                        ((SplashActivity) context).startActivity(new Intent(context, MainActivity.class));
                        ((SplashActivity) context).finish();
                         */
                        ((SplashActivity)context).checkStyleUI();
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

            pDialog = new ProgressDialog(context, R.style.AlertDialogCustom);
            pDialog.setMessage(context.getString(R.string.update_download_unzipping)+" (" + String.valueOf(1) + "/" + String.valueOf(zipnames.size())+")");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pDialog.show();
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {

            for (int x = 0 ; x < zipnames.size() ; x++){
                File zipFile = new File(path+"/"+zipnames.get(x));
                ZipFile zipFile1 = null;
                try {
                    zipFile1 = new ZipFile(zipFile);
                    System.out.println(pDialog.getProgress());
                    pDialog.setMessage("Unzipping  Please wait... (" + String.valueOf(x+1) + "/" + String.valueOf(zipnames.size())+")");
                    pDialog.setMax( zipFile1.size());
                    pDialog.setProgress(0);
                    pDialog.create();
                    zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));
                    System.out.println("RIVER");
                    ZipEntry ze;
                    int count;
                    byte[] buffer = new byte[8192];


                    while ((ze = zis.getNextEntry()) != null) {
                        File file = new File(path, ze.getName());
                        File dir = ze.isDirectory() ? file : file.getParentFile();
                        if (!dir.isDirectory() && !dir.mkdirs())
                            throw new FileNotFoundException("Failed to ensure directory: " +dir.getAbsolutePath());
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
                        pDialog.setProgress(curr);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
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
            pDialog.dismiss();
            for(int x = 0 ; x< zipnames.size() ; x++){
                File file = new File(path+"/"+zipnames.get(x));
                if (file.exists()){
                    file.delete();
                }
            }
            CustomToast.toast(context,activity,context.getString(R.string.update_download_done));
            if (runStyleUI){
                ((SplashActivity) context).checkStyleUI();
            }
        }

    }
}
