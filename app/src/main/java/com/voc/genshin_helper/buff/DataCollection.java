package com.voc.genshin_helper.buff;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */


import static com.voc.genshin_helper.util.LogExport.DAILYMEMO;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.voc.genshin_helper.ui.MMXLVIII.DailyMemo2048Service;
import com.voc.genshin_helper.util.LogExport;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataCollection {
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String genshinUID;
    String enkaData = "N/A";

    public void init(Context context){
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
        genshinUID = sharedPreferences.getString("genshin_uid","-1");

        enkaData = sharedPreferences.getString("enka_data","N/A");
        if(enkaData.equals("N/A")){

        }

    }


    class grabDataFromEnka extends AsyncTask<String,Integer,String> {
        private static final int TIME_OUT = 5000;

        String str = "";
        protected void onPreExecute() {
            str = "";
        }
        @Override
        protected String doInBackground(String... url) {
            // TODO Auto-generated method stub
            // 再背景中處理的耗時工作
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url[0]).build();

            try {
                Response sponse = client.newCall(request).execute();
                str = sponse.body().string();
                Object json = new JSONTokener(str).nextValue();
                if (json instanceof JSONObject){
                    sharedPreferences.edit().putString("enka_data",str).apply();
                }else{
                    System.out.println("Enka Read Error");
                }

            } catch (IOException | JSONException e) {
                LogExport.export("DataCollection","grabDataFromEnka.doInBackground", e.getMessage(), context, DAILYMEMO);
            }
            return "DONE";
        }



        public void onPostExecute(String result )
        { super.onPreExecute();
            // 背景工作處理完"後"需作的事
            
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);

        }
    }
}
