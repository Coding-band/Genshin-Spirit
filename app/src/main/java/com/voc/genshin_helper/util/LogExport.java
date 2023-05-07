package com.voc.genshin_helper.util;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import static android.widget.Toast.LENGTH_LONG;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.voc.genshin_helper.BuildConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class LogExport {

    public static String DAILYMEMO = "dailyMemo.txt";
    public static String DOWNLOADTASK = "downloadTask.txt";
    public static String UNZIPMANAGER = "unzipManager.txt";
    public static String ENKADATACOLLECT = "enkaDataCollect.txt";
    public static String DOWNLOAD_UNZIP_TASK = "downloadUnzipTask.txt";
    public static String FETCH_FILE_FUTURE = "fetchFileFuture.txt";
    public static String BETA_TESTING = "betaTesting.txt";

    public static String[] list = {DAILYMEMO,DOWNLOADTASK,UNZIPMANAGER,ENKADATACOLLECT, DOWNLOAD_UNZIP_TASK,BETA_TESTING, FETCH_FILE_FUTURE};

    public static void export (String className, String functionName, String data, Context context, String fileName) {
        //if (BuildConfig.FLAVOR.equals("dev") || BuildConfig.FLAVOR.equals("beta")) {
            String dataFinal =
                    "--------\n" +
                            "Class : " + className + "\n" +
                            "Function : " + functionName + "\n" +
                            "Time : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + "\n" +
                            "UnixTimeStamp : " + System.currentTimeMillis() + "\n\n";
            String fileHead =
                    "This file was created in " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + "\n";
            try {
                dataFinal = dataFinal + data + "\n\n";
                File ext = context.getExternalMediaDirs()[0];
                Files.write(Paths.get(ext + "/" + "genshin_spirit/" + fileName), dataFinal.getBytes(), new StandardOpenOption[]{StandardOpenOption.APPEND});
            } catch (IOException e) {
                Log.i("LogExport", e.getMessage());
            }
        //}
    }

    public static void init(Context context) {
        //if (BuildConfig.FLAVOR.equals("dev") || BuildConfig.FLAVOR.equals("beta")) {
            String fileHead = "This file was created in " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + "\n";
            try {
                File ext = context.getExternalMediaDirs()[0];
                if (!Files.exists(Paths.get(ext + "/" + "genshin_spirit/"))) {
                    Files.createDirectory(Paths.get(ext + "/" + "genshin_spirit/"));
                }
                for (int x = 0; x < list.length; x++) {
                    if (!Files.exists(Paths.get(ext + "/" + "genshin_spirit/" + list[x]))) {
                        Files.createFile(Paths.get(ext + "/" + "genshin_spirit/" + list[x]));
                        Log.i("Voc", ext + "/" + "genshin_spirit/" + list[x]);
                        Files.write(Paths.get(ext + "/" + "genshin_spirit/" + list[x]), fileHead.getBytes(), new StandardOpenOption[]{StandardOpenOption.APPEND});
                    }
                }
            } catch (IOException e) {
                Log.i("LogExport", e.getMessage());
                e.printStackTrace();
            }
        }
    //}
}
