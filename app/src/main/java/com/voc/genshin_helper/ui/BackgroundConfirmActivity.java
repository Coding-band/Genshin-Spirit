package com.voc.genshin_helper.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.voc.genshin_helper.R;
import com.voc.genshin_helper.ui.CalculatorUI;
import com.voc.genshin_helper.ui.MainActivity;
import com.voc.genshin_helper.util.BackgroundReload;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.LangUtils;

import java.io.File;
import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

public class BackgroundConfirmActivity extends AppCompatActivity {

    Context context;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_confirm);

        context = this;
        activity = this;
        BackgroundReload(context,activity);

        Button bg_ok = findViewById(R.id.bg_ok);
        Button bg_cancel = findViewById(R.id.bg_cancel);

        bg_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Turn tmp back to real

                String pathName = sharedPreferences.getString("tmp_pathName","N/A");

                editor.putString("pathName",pathName);
                switch (pathName.split("\\.")[1].toLowerCase()){
                    case "png" : editor.putString("gif/png","png");break;
                    case "jpg" : editor.putString("gif/png","jpg");break;
                    case "jpeg" : editor.putString("gif/png","jpeg");break;
                    case "gif" : editor.putString("gif/png","gif");break;
                }
                editor.apply();
                finish();
                //startActivity(new Intent(BackgroundConfirmActivity.this, MainActivity.class));
            }
        });

        bg_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                //startActivity(new Intent(BackgroundConfirmActivity.this, MainActivity.class));
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            CustomToast.toast(context,activity,"請先按下你的決定");
        }
        return true;
    }

    public void BackgroundReload (Context context, Activity activity){
        this.context = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String gif_png = sharedPreferences.getString("tmp_gif_png", "png");
        String pathName = sharedPreferences.getString("tmp_pathName","N/A");
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
        }

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user_info",MODE_PRIVATE);
        sharedPreferences.getInt("curr_lang_pos",2);
        super.attachBaseContext(LangUtils.getAttachBaseContext(newBase, sharedPreferences.getInt("curr_lang_pos",2)));
    }
}