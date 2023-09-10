package com.voc.genshin_helper.util;

import static com.voc.genshin_helper.util.LogExport.DAILYMEMO;
import static com.voc.genshin_helper.util.LogExport.DOWNLOADTASK;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.voc.genshin_helper.R;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

//https://stackoverflow.com/questions/29188557/download-file-with-asynctask
public class DownloadTask {

    String urlS,fileName,savePath;
    Context context;
    Activity activity;
    //ProgressDialog pDialog;
    Dialog2048 dialog2048;
    boolean runStyleUI = false;


    ArrayList<String> urls,fileNames,savePaths = new ArrayList<String>();

    public void start(String url, String fileName, String savePath, Context context,Activity activity){
        this.urlS = url;
        this.fileName = fileName;
        this.savePath = savePath;
        this.context = context;
        this.activity = activity;
        new DownloadFileFromURL().execute();
    }

    public class  DownloadFileFromURL extends AsyncTask<String, Integer, String> {

        /**
         * Before starting background thread
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("Starting download");

            /*
            pDialog = new ProgressDialog(context, R.style.AlertDialogCustom);
            pDialog.setMessage(context.getString(R.string.update_download_downloading));
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pDialog.show();
             */

            dialog2048 = new Dialog2048();
            dialog2048.setup(context,activity);
            dialog2048.mode(Dialog2048.MODE_PROGRESS_DOWNLOAD);
            dialog2048.show();
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                String ipAddress = "vt.25u.com";
                InetAddress inet = InetAddress.getByName(ipAddress);
                LogExport.export("DownloadTask","DownloadFileFromURL.doInBackground", "InetAddress : "+"Sending Ping Request to " + ipAddress+" and wait for 500ms "+(inet.isReachable(500) ? " and host is reachable" : "but host is NOT reachable"), context, DOWNLOADTASK);
                LogExport.export("DownloadTask","DownloadFileFromURL.doInBackground", "InetAddress : "+"Sending Ping Request to " + ipAddress+" and wait for 1000ms "+(inet.isReachable(1000) ? "and host is reachable" : "but host NOT reachable"), context, DOWNLOADTASK);
                LogExport.export("DownloadTask","DownloadFileFromURL.doInBackground", "InetAddress : "+"Sending Ping Request to " + ipAddress+" and wait for 2000ms "+(inet.isReachable(2000) ? "and host is reachable" : "but host NOT reachable"), context, DOWNLOADTASK);

                String root = Environment.getExternalStorageDirectory().toString();

                System.out.println("Downloading");
                URL url = new URL(urlS);

                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                long lenghtOfFile = conection.getContentLengthLong();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file

                OutputStream output = new FileOutputStream(context.getFilesDir()+savePath);
                byte data[] = new byte[8192];
                dialog2048.updateMax(lenghtOfFile);
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;

                    //pDialog.setProgress((int) total);
                    dialog2048.updateProgress(total);
                    // writing data to file
                    output.write(data, 0, count);

                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
                LogExport.export("DownloadTask","DownloadFileFromURL.doInBackground", e.getMessage(), context, DOWNLOADTASK);
            }

            return null;
        }

        /**
         * After completing background task
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            System.out.println("Downloaded");
            //pDialog.dismiss();
            dialog2048.dismiss();
            UnzipManager unzipManager = new UnzipManager();
            unzipManager.pbShow(String.valueOf(context.getFilesDir()),fileName,context,activity);
        }

    }

    // ARRAY DOWNLOAD

    public void startA(ArrayList<String> url, ArrayList<String> fileName, ArrayList<String> savePath, Context context, Activity activity){
        this.urls = url;
        this.fileNames = fileName;
        this.savePaths = savePath;
        this.context = context;
        this.activity = activity;
        new DownloadFileFromURLArray().execute();
    }
    public void startAWithRun(ArrayList<String> url, ArrayList<String> fileName, ArrayList<String> savePath, Context context, Activity activity, boolean runStyleUI){
        this.runStyleUI = runStyleUI;
        this.urls = url;
        this.fileNames = fileName;
        this.savePaths = savePath;
        this.context = context;
        this.activity = activity;
        new DownloadFileFromURLArray().execute();
    }

    public class DownloadFileFromURLArray extends AsyncTask<String, Integer, String> {

        /**
         * Before starting background thread
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("Starting download");


            dialog2048 = new Dialog2048();
            dialog2048.setup(context,activity);
            dialog2048.mode(Dialog2048.MODE_PROGRESS_DOWNLOAD);
            dialog2048.show();
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            int countProgress = 0;
            try {
                String ipAddress = "vt.25u.com";
                InetAddress inet = InetAddress.getByName(ipAddress);
                LogExport.export("DownloadTask","DownloadFileFromURL.doInBackground", "InetAddress : "+"Sending Ping Request to " + ipAddress+" and wait for 500ms "+(inet.isReachable(500) ? " and host is reachable" : "but host is NOT reachable"), context, DOWNLOADTASK);
                LogExport.export("DownloadTask","DownloadFileFromURL.doInBackground", "InetAddress : "+"Sending Ping Request to " + ipAddress+" and wait for 1000ms "+(inet.isReachable(1000) ? "and host is reachable" : "but host NOT reachable"), context, DOWNLOADTASK);
                LogExport.export("DownloadTask","DownloadFileFromURL.doInBackground", "InetAddress : "+"Sending Ping Request to " + ipAddress+" and wait for 2000ms "+(inet.isReachable(2000) ? "and host is reachable" : "but host NOT reachable"), context, DOWNLOADTASK);

                String root = Environment.getExternalStorageDirectory().toString();

                long countMax = 0;
                for(int x = 0 ; x < urls.size() ; x++){
                    try {
                        URL url = new URL(urls.get(x));
                        URLConnection conection = url.openConnection();
                        conection.connect();
                        countMax += conection.getContentLength();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // getting file length
                }

                dialog2048.updateMax(countMax);

                for(int x = 0 ; x < urls.size() ; x++){
                    //pDialog.setMessage(context.getString(R.string.update_download_downloading)+" (" + String.valueOf(x+1) + "/" + String.valueOf(urls.size())+")");
                    System.out.println("Downloading");
                    URL url = new URL(urls.get(x));

                    URLConnection conection = url.openConnection();
                    conection.connect();
                    // getting file length
                    int lenghtOfFile = conection.getContentLength();

                    // input stream to read file - with 8k buffer
                    InputStream input = new BufferedInputStream(url.openStream(), 8192);

                    // Output stream to write file

                    OutputStream output = new FileOutputStream(context.getFilesDir()+savePaths.get(x));
                    byte data[] = new byte[8192];

                    long total = 0;
                    while ((count = input.read(data)) != -1) {
                        countProgress += count;

                        dialog2048.updateProgress(countProgress);
                        // writing data to file
                        output.write(data, 0, count);

                    }

                    // flushing output
                    output.flush();

                    // closing streams
                    output.close();
                    input.close();

                }

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
                LogExport.export("DownloadTask","DownloadFileFromURLArray.doInBackground", e.getMessage(), context, DOWNLOADTASK);
            }

            return null;
        }

        /**
         * After completing background task
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            System.out.println("Downloaded");
            dialog2048.dismiss();
            UnzipManager unzipManager = new UnzipManager();
            if (runStyleUI){
                unzipManager.pbShowAWithRun(String.valueOf(context.getFilesDir()),fileNames,context,activity,runStyleUI);
            }else{
                unzipManager.pbShowA(String.valueOf(context.getFilesDir()),fileNames,context,activity);
            }
        }

    }
}