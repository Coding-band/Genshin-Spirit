package com.voc.genshin_helper.util;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import java.io.File;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class FileLoader {
    public static File loadIMG(String path, Context context){
        return new File(context.getFilesDir()+path);
    }

    public static Drawable loadIMG2Drawable(String path, Context context){
        Drawable d = Drawable.createFromPath(context.getFilesDir()+path);
        return d;
    }

    public static Bitmap loadIMG2Bitmap(String path, Context context){
        Bitmap bitmap = BitmapFactory.decodeFile(context.getFilesDir()+path);
        return bitmap;
    }
}
