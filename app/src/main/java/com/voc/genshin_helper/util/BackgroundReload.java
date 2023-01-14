package com.voc.genshin_helper.util;/*
 * Package com.voc.genshin_helper.util.BackgroundReload was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.voc.genshin_helper.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class BackgroundReload {

    public static void BackgroundReload (Context context, Activity activity){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String gif_png = sharedPreferences.getString("gif/png", "png");
        String pathName = context.getFilesDir()+"/background."+gif_png;
        GifImageView gifImageView1 = (GifImageView) activity.findViewById(R.id.global_bg);

        Resources res = context.getResources();

        if(!pathName.equals("N/A")){
            Bitmap bitmap = BitmapFactory.decodeFile(pathName);
            BitmapDrawable bd = new BitmapDrawable(res, bitmap);

            Bitmap bitmap2 = BitmapFactory.decodeFile(pathName);
            BitmapDrawable bd2 = new BitmapDrawable(res, bitmap2);

            File gifFile = new File(pathName);
            GifDrawable gifFromFile = null;
            try { gifFromFile = new GifDrawable(gifFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (gif_png.matches("gif")){gifImageView1.setImageDrawable(gifFromFile);}
            if (gif_png.matches("png")){gifImageView1.setImageDrawable(bd);}
            if (gif_png.matches("jpg")||gif_png.matches("jpeg")){gifImageView1.setImageDrawable(bd2);}
        }else{
            gifImageView1.setImageResource(R.color.base_page_bg);
        }

    }

    public static void BackgroundReload (Context context, View view){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String gif_png = sharedPreferences.getString("gif/png", "png");
        String pathName = context.getFilesDir()+"/background."+gif_png;
        GifImageView gifImageView1 = (GifImageView) view.findViewById(R.id.global_bg);

        Resources res = context.getResources();

        if(!pathName.equals("N/A")){
            File file = new File(pathName);
            if(file.isFile() && file.exists()){
                Bitmap bitmap = BitmapFactory.decodeFile(pathName);
                BitmapDrawable bd = new BitmapDrawable(res, bitmap);

                Bitmap bitmap2 = BitmapFactory.decodeFile(pathName);
                BitmapDrawable bd2 = new BitmapDrawable(res, bitmap2);

                File gifFile = new File(pathName);
                GifDrawable gifFromFile = null;
                try { gifFromFile = new GifDrawable(gifFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (gif_png.matches("gif")){gifImageView1.setImageDrawable(gifFromFile);}
                if (gif_png.matches("png")){gifImageView1.setImageDrawable(bd);}
                if (gif_png.matches("jpg")||gif_png.matches("jpeg")){gifImageView1.setImageDrawable(bd2);}
            }
            else{
                gifImageView1.setImageResource(R.color.base_page_bg);
            }
        }else{
            gifImageView1.setImageResource(R.color.base_page_bg);
        }

    }

    // OLD

    public static void BackgroundReload_OLD (Context context, Activity activity){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String gif_png = sharedPreferences.getString("gif/png", "png");
        String pathName = sharedPreferences.getString("pathName","N/A");
        GifImageView gifImageView1 = (GifImageView) activity.findViewById(R.id.global_bg);

        Resources res = context.getResources();

        if(!pathName.equals("N/A")){
            Bitmap bitmap = BitmapFactory.decodeFile(pathName);
            BitmapDrawable bd = new BitmapDrawable(res, bitmap);

            Bitmap bitmap2 = BitmapFactory.decodeFile(pathName);
            BitmapDrawable bd2 = new BitmapDrawable(res, bitmap2);

            File gifFile = new File(pathName);
            GifDrawable gifFromFile = null;
            try { gifFromFile = new GifDrawable(gifFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (gif_png.matches("gif")){gifImageView1.setImageDrawable(gifFromFile);}
            if (gif_png.matches("png")){gifImageView1.setImageDrawable(bd);}
            if (gif_png.matches("jpg")||gif_png.matches("jpeg")){gifImageView1.setImageDrawable(bd2);}
        }else{
            gifImageView1.setImageResource(R.color.base_page_bg);
        }

    }

    public static void BackgroundReload_OLD (Context context, View view){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String gif_png = sharedPreferences.getString("gif/png", "png");
        String pathName = sharedPreferences.getString("pathName","N/A");
        GifImageView gifImageView1 = (GifImageView) view.findViewById(R.id.global_bg);

        Resources res = context.getResources();

        if(!pathName.equals("N/A")){
            File file = new File(pathName);
            if(file.isFile() && file.exists()){
                Bitmap bitmap = BitmapFactory.decodeFile(pathName);
                BitmapDrawable bd = new BitmapDrawable(res, bitmap);

                Bitmap bitmap2 = BitmapFactory.decodeFile(pathName);
                BitmapDrawable bd2 = new BitmapDrawable(res, bitmap2);

                File gifFile = new File(pathName);
                GifDrawable gifFromFile = null;
                try { gifFromFile = new GifDrawable(gifFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (gif_png.matches("gif")){gifImageView1.setImageDrawable(gifFromFile);}
                if (gif_png.matches("png")){gifImageView1.setImageDrawable(bd);}
                if (gif_png.matches("jpg")||gif_png.matches("jpeg")){gifImageView1.setImageDrawable(bd2);}
            }
            else{
                gifImageView1.setImageResource(R.color.base_page_bg);
            }
        }else{
            gifImageView1.setImageResource(R.color.base_page_bg);
        }

    }

}
