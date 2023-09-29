package com.voc.genshin_helper.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import java.util.Locale;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class LangUtils {
    private static final int FOLLOW_SYSTEM = -1;
    private static final int zh_CH = 1;
    private static final int zh_HK = 0;
    private static final int en_US = 2;
    private static final int ru_RU = 3;
    private static final int ja_JP = 4;
    private static final int fr_FR = 5;
    private static final int uk_UA = 6;
    private static final int pt_PT = 7;
    private static final int de_DE = 8;
    private static final int sp_SP = 9;
    private static final int vi_VI = 10;

    public static Locale getCurrentLang(int i) {
        switch (i) {
            case -1:
                return Locale.getDefault();
            case 0:
                return Locale.TRADITIONAL_CHINESE;
            case 1:
                return Locale.SIMPLIFIED_CHINESE;
            case 2:
                return Locale.US;
            case 3:
                return new Locale("ru", "RU");
            case 4:
                return Locale.JAPAN;
            case 5:
                return Locale.FRANCE;
            case 6:
                return new Locale("uk", "UA");
            case 7:
                return new Locale("pt", "PT");
            case 8:
                return Locale.GERMANY;
            case 9:
                return new Locale("es", "ES");
            case 10:
                return new Locale("vi", "VI");
            default:
                return Locale.US;
        }
    }

    public static Context getAttachBaseContext(Context context, int i) {
        Log.d("pigdreams", "配置语言...默认locale=" + Locale.getDefault() + ";用户设置的为=" + getCurrentLang(i));
        if (Build.VERSION.SDK_INT >= 24) {
            return updateResources(context, i);
        }
        changeResLanguage(context, i);
        return context;
    }

    private static Context updateResources(Context context, int i) {
        Resources resources = context.getResources();
        Locale currentLang = getCurrentLang(i);
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(currentLang);
        return context.createConfigurationContext(configuration);
    }

    private static void changeResLanguage(Context context, int i) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(getCurrentLang(i));
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}
