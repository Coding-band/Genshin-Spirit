package com.voc.genshin_spirit_cn.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.voc.genshin_spirit_cn.R;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Project "Genshin Spirit" (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

//https://stackoverflow.com/questions/29188557/download-file-with-asynctask
public class DownloadTask {

    String urlS,fileName,savePath;
    Context context;
    Activity activity;
    ProgressDialog pDialog;


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

            pDialog = new ProgressDialog(context, R.style.AlertDialogCustom);
            pDialog.setMessage(context.getString(R.string.update_download_downloading));
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
            int count;
            try {
                String root = Environment.getExternalStorageDirectory().toString();

                System.out.println("Downloading");
                URL url = new URL(urlS);

                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file

                OutputStream output = new FileOutputStream(context.getFilesDir()+savePath);
                byte data[] = new byte[1024];
                pDialog.setMax(lenghtOfFile);
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;

                    pDialog.setProgress((int) total);
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
            }

            return null;
        }

        /**
         * After completing background task
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            System.out.println("Downloaded");
            pDialog.dismiss();
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

    public class DownloadFileFromURLArray extends AsyncTask<String, Integer, String> {

        /**
         * Before starting background thread
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("Starting download");

            pDialog = new ProgressDialog(context, R.style.AlertDialogCustom);
            pDialog.setMessage(context.getString(R.string.update_download_downloading) + String.valueOf(1) + "/" + String.valueOf(urls.size()));
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
            int count;
            try {
                String root = Environment.getExternalStorageDirectory().toString();

                for(int x = 0 ; x < urls.size() ; x++){
                    pDialog.setMessage(context.getString(R.string.update_download_downloading)+" (" + String.valueOf(x+1) + "/" + String.valueOf(urls.size())+")");
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
                    byte data[] = new byte[1024];

                    pDialog.setProgress(0);
                    pDialog.setMax(lenghtOfFile);
                    long total = 0;
                    while ((count = input.read(data)) != -1) {
                        total += count;

                        pDialog.setProgress((int) total);
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
            }

            return null;
        }

        /**
         * After completing background task
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            System.out.println("Downloaded");
            pDialog.dismiss();
            UnzipManager unzipManager = new UnzipManager();
            unzipManager.pbShowA(String.valueOf(context.getFilesDir()),fileNames,context,activity);
        }

    }
}
