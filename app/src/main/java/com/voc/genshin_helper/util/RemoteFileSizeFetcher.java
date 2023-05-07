package com.voc.genshin_helper.util;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import android.os.AsyncTask;

import com.voc.genshin_helper.ui.SplashActivity;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.FutureTask;

public class RemoteFileSizeFetcher extends AsyncTask<Void, Void, Long> {
    public ArrayList<String> urls;
    private FutureTask<Long> futureTask;

    public RemoteFileSizeFetcher(FutureTask<Long> futureTask, ArrayList<String> urls){
        this.futureTask = futureTask;
        this.urls = urls;
    }

    @Override
    protected Long doInBackground(Void... params) {
        long fileSize = 0;
        try {
            for (int x = 0 ; x < urls.size() ; x++){
                URL url = new URL(urls.get(x));
                URLConnection conn = url.openConnection();
                conn.connect();
                fileSize = conn.getContentLengthLong();
                conn.getInputStream().close();

                System.out.println("HI THERE : "+fileSize);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileSize;
    }

    @Override
    protected void onPostExecute(Long fileSize) {
        System.out.println("HI THERE : "+"DONE 1");
        super.onPostExecute(fileSize);
    }
}


