package com.voc.genshin_helper.util.hoyolab;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import android.content.Context;
import android.webkit.CookieManager;
import android.widget.Toast;

/*
 *  hoyolab.HoyolabCookie was refer from Dalufishe.
 */

public class HoyolabCookie {
    public static String hoyolabCookie(){
        CookieManager cookieManager = CookieManager.getInstance();
        String cookies = cookieManager.getCookie("https://act.hoyolab.com/app/community-game-records-sea/index.html#/ys");
        cookies = "{\""+cookies+"\"}".replace(" "," \"").replace("=","\":\"").replace(";","\",").replace(".","_");
        return cookies;
    }
}
