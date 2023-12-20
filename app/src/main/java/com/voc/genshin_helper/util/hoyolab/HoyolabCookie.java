package com.voc.genshin_helper.util.hoyolab;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.webkit.CookieManager;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
 *  hoyolab.HoyolabCookie was refer from Dalufishe.
 */

public class HoyolabCookie {

    public static final String[] HOYOLAB_V2_KEY_GROUP = {
            "cookie_token_v2",
            "account_mid_v2",
            "account_id_v2",
            "ltoken_v2",
            "ltmid_v2",
            "ltuid_v2"
    };

    String cookie_token_v2;
    String account_mid_v2;
    String account_id_v2;
    String ltoken_v2;
    String ltmid_v2;
    String ltuid_v2;

    public HoyolabCookie(String cookie_token_v2, String account_mid_v2, String account_id_v2, String ltoken_v2, String ltmid_v2, String ltuid_v2) {
        this.cookie_token_v2 = cookie_token_v2;
        this.account_mid_v2 = account_mid_v2;
        this.account_id_v2 = account_id_v2;
        this.ltoken_v2 = ltoken_v2;
        this.ltmid_v2 = ltmid_v2;
        this.ltuid_v2 = ltuid_v2;
    }

    public HoyolabCookie(Map<String, Object> cookieMap){
        cookieMap.forEach((key, obj) -> {
            if (Objects.equals(key, "cookie_token_v2")){cookie_token_v2 = (String) obj;}
            else if (Objects.equals(key, "account_mid_v2")){account_mid_v2 = (String) obj;}
            else if (Objects.equals(key, "account_id_v2")){account_id_v2 = (String) obj;}
            else if (Objects.equals(key, "ltoken_v2")){ltoken_v2 = (String) obj;}
            else if (Objects.equals(key, "ltmid_v2")){ltmid_v2 = (String) obj;}
            else if (Objects.equals(key, "ltuid_v2")){ltuid_v2 = (String) obj;}
            System.out.println(key+" JAVA : "+obj);
        });
    }

    public String getCookie_token_v2() {
        return cookie_token_v2;
    }

    public String getAccount_mid_v2() {
        return account_mid_v2;
    }

    public String getAccount_id_v2() {
        return account_id_v2;
    }

    public String getLtoken_v2() {
        return ltoken_v2;
    }

    public String getLtmid_v2() {
        return ltmid_v2;
    }

    public String getLtuid_v2() {
        return ltuid_v2;
    }
//  獲取 & 儲存 & 調用 Cookies 必要項目

    public static String grabCookie(){
        CookieManager cookieManager = CookieManager.getInstance();
        String cookies = cookieManager.getCookie("https://act.hoyolab.com/app/community-game-records-sea/index.html#/ys");
        cookies = "{\""+cookies+"\"}".replace(" "," \"").replace("=","\":\"").replace(";","\",").replace(".","_");
        return cookies;
    }
    public static boolean updateCookie(Context context, Map<String, Object> values){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        values.forEach((key, obj) -> {
            editor.putString(key, String.valueOf(obj));
        });
        editor.apply();
        return true;
    }

    public static boolean updateCookie(Context context, String cookies){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String[] values = cookies.split(";");
        for (String str : values){
            String[] keyValueArr = str.split("=");
            editor.putString(keyValueArr[0].trim(),keyValueArr[1].trim()).apply();
            System.out.println(keyValueArr[0].trim()+" : "+keyValueArr[1].trim());
        }
        return true;
    }

    public static Map<String, Object> getCookieMap(Context context, String[] keys){
        Map<String, Object> cookies = new HashMap<>();
        keys = (keys == null ? HOYOLAB_V2_KEY_GROUP : keys);
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        for (String key : keys){
            cookies.put(key,sharedPreferences.getString(key,null));
        }
        return cookies;
    }
    public static String getCookiePlain(Context context, String[] keys){
        keys = (keys == null ? HOYOLAB_V2_KEY_GROUP : keys);
        String plain = "";
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        for (String key : keys){
            plain += key+"="+sharedPreferences.getString(key,null)+";";
        }
        return plain;
    }
}
