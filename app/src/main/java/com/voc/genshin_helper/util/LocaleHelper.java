package com.voc.genshin_helper.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

//https://medium.com/工程師求生指南-sofware-engineer-survival-guide/android多語系在地化-i18n-支援7-0-繁中-3ac0fc6ffdbb

/*
 * Package com.voc.genshin_helper.util.LocaleHelper was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

public class LocaleHelper {
    public SharedPreferences sharedPreferences;
    public String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";
    public String COUNTRY = "country";
    public String LANG = "lang";

    public Context onAttach (Context context){
        saveSettingLang(context, Locale.getDefault().getLanguage(), Locale.getDefault().getCountry());
        return initLocale(context, readSettingLang(), readSettingCountry());
    }

    public Context initLocale(Context context, String lang, String country) {
        saveSettingLang(context, lang, country);

        if (android.os.Build.VERSION.SDK_INT > 24) {
            return updateResources(context, lang, country);
        }
        return updateResourcesLegacy(context, lang, country);
    }

    //region 因應 API Level 取得相對應context更新方法
    //updateResources為7.0後
    @TargetApi(Build.VERSION_CODES.N)
    private Context updateResources(Context context, String lang, String country){
        Locale locale = new Locale(lang, country);
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        if (configuration != null) {
            configuration.setLocale(locale);
            configuration.setLayoutDirection(locale);
        }

        return context.createConfigurationContext(configuration);
    }

    //updateResourcesLegacy為7.0前
    private Context updateResourcesLegacy(Context context, String lang, String country){
        Locale locale = new Locale(lang, country);
        Locale.setDefault(locale);

        Resources resource = context.getResources();

        Configuration configuration = resource.getConfiguration();
        if (configuration != null) {
            configuration.locale = locale;
            if (android.os.Build.VERSION.SDK_INT > 11)
                configuration.setLayoutDirection(locale);
            resource.updateConfiguration(configuration, resource.getDisplayMetrics());
        }

        return context;
    }
    //endregion

    //region 讀寫設定語言及國家
    private String readSettingLang(){
        return sharedPreferences.getString(LANG, "");
    }

    private String readSettingCountry(){
        return sharedPreferences.getString(COUNTRY, "");
    }

    private void saveSettingLang(Context context, String lang, String country) {
        if (context != null) {
            sharedPreferences = context.getSharedPreferences(SELECTED_LANGUAGE, 0);
        }
        sharedPreferences.edit()
                .putString(LANG, lang)
                .putString(COUNTRY, country)
                .apply();
    }
}
