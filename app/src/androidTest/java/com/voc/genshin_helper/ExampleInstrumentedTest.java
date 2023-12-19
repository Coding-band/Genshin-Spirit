package com.voc.genshin_helper;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.voc.genshin_helper.util.hoyolab.HoyolabCookie;
import com.voc.genshin_helper.util.hoyolab.hooks.HoyolabHooks;

import java.util.HashMap;
import java.util.Map;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.voc.genshin_helper", appContext.getPackageName());
    }

    @Test
    public void hoyolabAndroidTest(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Map<String, Object> cookieTemp = new HashMap<>();
        cookieTemp.put("_MHYUUID","8d2d4cd0-4b95-4c5a-9596-9252ac591a41");
        cookieTemp.put("HYV_LOGIN_PLATFORM_OPTIONAL_AGREEMENT","{%22content%22:[]}");
        cookieTemp.put("DEVICEFP_SEED_ID","b08334622d049e38");
        cookieTemp.put("DEVICEFP_SEED_TIME","1702949307961");
        cookieTemp.put("DEVICEFP","38d7f0007c549");
        cookieTemp.put("_gid","GA1.2.1043661682.1702949309");
        cookieTemp.put("cookie_token_v2","v2_CAQSDGNlMXRidXdiMDB6axokOGQyZDRjZDAtNGI5NS00YzVhLTk1OTYtOTI1MmFjNTkxYTQxIMHjg6wGKOuTyuYCMLjY-T5CC2hrNGVfZ2xvYmFs");
        cookieTemp.put("account_mid_v2","1ixu9u26jj_hy");
        cookieTemp.put("account_id_v2","132017208");
        cookieTemp.put("ltoken_v2","v2_CAISDGNlMXRidXdiMDB6axokOGQyZDRjZDAtNGI5NS00YzVhLTk1OTYtOTI1MmFjNTkxYTQxIMHjg6wGKL2L4pkDMLjY-T5CC2hrNGVfZ2xvYmFs");
        cookieTemp.put("ltmid_v2","1ixu9u26jj_hy");
        cookieTemp.put("ltuid_v2","132017208");
        cookieTemp.put("HYV_LOGIN_PLATFORM_LOAD_TIMEOUT","{}");
        cookieTemp.put("mi18nLang","zh-tw");
        cookieTemp.put("HYV_LOGIN_PLATFORM_TRACKING_MAP","{%22sourceValue%22:%22121%22}");
        cookieTemp.put("_gat_gtag_UA_206868027_11","1");
        cookieTemp.put("HYV_LOGIN_PLATFORM_LIFECYCLE_ID","{%22value%22:%22c87aeca8-a3a2-49cf-8bea-0a7efac14f84%22}");
        cookieTemp.put("_ga_JTLS2F53NR","GS1.1.1702949309.1.1.1702950383.0.0.0");
        cookieTemp.put("_ga_GFC5HN79FG","GS1.1.1702949309.1.1.1702950383.0.0.0");
        cookieTemp.put("_ga","GA1.2.819091040.1702949309");
        HoyolabCookie.updateCookie(appContext, cookieTemp);
        System.out.println(new HoyolabHooks().genshinNoteData(appContext));
    }
}