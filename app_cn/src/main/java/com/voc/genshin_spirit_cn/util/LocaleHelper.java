package com.voc.genshin_spirit_cn.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import java.util.Locale;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

public class LocaleHelper {
    public String COUNTRY = "country";
    public String LANG = "lang";
    public String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";
    public SharedPreferences sharedPreferences;

    public Context onAttach(Context context) {
        saveSettingLang(context, Locale.getDefault().getLanguage(), Locale.getDefault().getCountry());
        return initLocale(context, readSettingLang(), readSettingCountry());
    }

    public Context initLocale(Context context, String str, String str2) {
        saveSettingLang(context, str, str2);
        if (Build.VERSION.SDK_INT > 24) {
            return updateResources(context, str, str2);
        }
        return updateResourcesLegacy(context, str, str2);
    }

    private Context updateResources(Context context, String str, String str2) {
        Locale locale = new Locale(str, str2);
        Locale.setDefault(locale);
        Configuration configuration = context.getResources().getConfiguration();
        if (configuration != null) {
            configuration.setLocale(locale);
            configuration.setLayoutDirection(locale);
        }
        return context.createConfigurationContext(configuration);
    }

    private Context updateResourcesLegacy(Context context, String str, String str2) {
        Locale locale = new Locale(str, str2);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        if (configuration != null) {
            configuration.locale = locale;
            if (Build.VERSION.SDK_INT > 11) {
                configuration.setLayoutDirection(locale);
            }
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
        return context;
    }

    private String readSettingLang() {
        return this.sharedPreferences.getString(this.LANG, "");
    }

    private String readSettingCountry() {
        return this.sharedPreferences.getString(this.COUNTRY, "");
    }

    private void saveSettingLang(Context context, String str, String str2) {
        if (context != null) {
            this.sharedPreferences = context.getSharedPreferences(this.SELECTED_LANGUAGE, 0);
        }
        this.sharedPreferences.edit().putString(this.LANG, str).putString(this.COUNTRY, str2).apply();
    }
}
