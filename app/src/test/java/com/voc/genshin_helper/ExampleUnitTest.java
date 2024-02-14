package com.voc.genshin_helper;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /*
    For Hoyolab only
     */
    @Test
    public void generate()  {
        final String salt = "6s25p5ox5y14umn1p61aqyyvbvvl3lrt";
        Date date = new Date();
        long time = (long) Math.floor(date.getTime()/1000);

        String random = "";
        final String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int x = 0 ; x < 6 ; x++){
            int randomIndex = (int) Math.floor(Math.random() * characters.length());
            char randomChar = characters.charAt(randomIndex);
            random += randomChar;
        }

        String hash = DigestUtils.md5Hex("salt="+salt+"&t="+time+"&r="+random);

        System.out.println(String.valueOf(time)+","+random+","+hash);
    }

    /*
    米遊社!
     */
    @Test
    public void generateDS1()  {
        //final String salt = "BIPaooxbWZW02fGHZL1If26mYCljPgst"; //2.63.1
        final String salt = "Za8pSfshqZn9URWnG2UoIA6X978y5lIK"; //2.66.1
        Date date = new Date();
        long time = (long) Math.floor(date.getTime()/1000);

        String random = "";
        final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for(int x = 0 ; x < 6 ; x++){
            int randomIndex = (int) Math.floor(Math.random() * characters.length());
            char randomChar = characters.charAt(randomIndex);
            random += randomChar;
        }

        //random = "ab7njk"; //Successful case
        //String target = "7aa04b477f4fb48a10cb92df462f6e8a";

        String hash = DigestUtils.md5Hex("salt="+salt+"&t="+time+"&r="+random);

        System.out.println(time+","+random+","+hash);
    }
    /*
    ref :
    https://blog.starudream.cn/
    https://uigf.org/zh/mihoyo-api-collection/other/authentication.html#%E8%AF%B7%E6%B1%82%E5%A4%B4
    https://github.com/UIGF-org/mihoyo-api-collect/issues/1
     */
    @Test
    public void generate2()  {
        final String salt = "xV8v4Qu54lUKrEYFZkJhB8cuOh9Asafs";
        Date date = new Date();
        long time = (long) Math.floor(date.getTime()/1000);

        String body = "{\"role\": \"108289390\"}";
        //String[] preQuery = "uid=16299869".split("&"); //[IMPORTANT - NO DELETE gameRecordCard!]
        String[] preQuery = "server=prod_official_cht&role_id=900033852".split("&"); //[IMPORTANT - NO DELETE STARRAIL!]
        //String[] preQuery = "server=cn_gf01&role_id=170422041".split("&"); //[IMPORTANT - NO DELETE GENSHIN!]
        //String[] preQuery = "server=cn_gf01&role_id=170422041".split("&");
        Arrays.sort(preQuery);
        String query = "";
        for (int x = 0 ; x < preQuery.length ; x++){
            query += preQuery[x] += (x+1 < preQuery.length ? "&" : "");
        }
        System.out.println(query);
        //query = "";

        body = "";

        int random = (int) Math.floor(Math.random() * 100001 + 100000);
        if(random == 100000) {
            random = 642367;
        }

        String main = DigestUtils.md5Hex(new String(("salt="+salt+"&t="+time+"&r="+random+"&b="+body+"&q="+query).getBytes(), StandardCharsets.UTF_8));
        System.out.println(time +","+random+","+main);

    }

    @Test
    public void generateK2Salt(){
        //Za8pSfshqZn9URWnG2UoIA6X978y5lIK
        int[] f298361k = {126, -98, -4782969, 192, -84, 162, -116, 168, -114, 126, 186, -14348907, -86, 102, -88, 186, -72, -6561, -86, -112, -74, -66, -531441, 120, -14348907, -1594323, -4782969, -122, -177147, 180, -74, -76};
        System.out.println(f1(f298361k));
    }
    public static String f1(int[] iArr) {
        int i11;
        StringBuilder sb2 = new StringBuilder();
        ArrayList<Number> arrayList = new ArrayList(iArr.length);
        for (int i12 : iArr) {
            if (i12 < 0) {
                i11 = ((double) (-i12)) >= Math.pow(3.0d, 6.0d) ? (int) (((Math.log(-i12) / Math.log(3.0d)) - 6) + 48) : ~i12;
            } else {
                i11 = (i12 / 3) + 48;
            }
            arrayList.add(Integer.valueOf(i11));
        }
        ArrayList arrayList2 = new ArrayList(Z(arrayList, 10));
        for (Number number : arrayList) {
            sb2.append((char) number.intValue());
            arrayList2.add(sb2);
        }
        String sb3 = sb2.toString();
        return sb3;
    }

    public static final <T> int Z(Iterable<? extends T> iterable, int i11) {
        return iterable instanceof Collection ? ((Collection) iterable).size() : i11;
    }
}