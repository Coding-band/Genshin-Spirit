package com.voc.genshin_helper.util;

/**
 * Package com.voc.genshin_helper.util was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2020 Xectorda 版權所有
 */
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.voc.genshin_helper.R;
import com.voc.genshin_helper.ui.MainActivity;

import java.util.Locale;
// https://github.com/hapzhu/BlogDemo/blob/master/app/src/main/java/cn/pigdreams/blogdemo/LangUtils.java
/**
 * Create by pigdreams on 2018-07-26
 * website:pigdreams.cn
 * 语言自适应工具
 * 数组资源中设定语言,现为4个类型
 * 1.跟随系统
 * 2.简体中文
 * 3.繁体中文
 * 4.English
 * 然后每次选择的语言都会存入SP中,根据SP中保存的语言类型进行资源语言设置
 * 根据语言的整数值来匹配对应的Locale对象，却省值为简体中文Locale.SIMPLIFIED_CHINESE
 */
public class LangUtils {
    private static final int zh_HK = 0;
    private static final int zh_CH = 1;
    private static final int en_US = 2;
    private static final int ru_RU = 3;
    private static final int ja_JP = 4;
    private static final int FOLLOW_SYSTEM = -1;

    public static Locale getCurrentLang(int userLang) {
        switch (userLang) {
            case FOLLOW_SYSTEM:
                return Locale.getDefault();
            case zh_HK:
                return Locale.TRADITIONAL_CHINESE;
            case zh_CH:
                return Locale.SIMPLIFIED_CHINESE;
            case en_US:
                return Locale.US;
            case ru_RU:
                return new Locale("ru","RU");
            case ja_JP:
                return Locale.JAPAN;
            default:
                return Locale.US;

        }
    }

    public static Context getAttachBaseContext(Context context, int lang) {
        Log.d("pigdreams", "配置语言...默认locale=" + Locale.getDefault() + ";用户设置的为=" + getCurrentLang(lang));
        //Android 7.0之后需要用另一种方式更改res语言,即配置进context中

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, lang);
        } else {
            //7.0之前的更新语言资源方式
            changeResLanguage(context, lang);
            return context;
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, int lang) {
        Resources resources = context.getResources();
        Locale locale = getCurrentLang(lang);
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    private static void changeResLanguage(Context context, int lang) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = getCurrentLang(lang);
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

}