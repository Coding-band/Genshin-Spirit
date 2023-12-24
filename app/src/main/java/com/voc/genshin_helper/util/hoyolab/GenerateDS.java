package com.voc.genshin_helper.util.hoyolab;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
/*
 *  hoyolab.GenerateDS was refer from Dalufishe.
 */
public class GenerateDS {
    public static String generate()  {
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

        return String.valueOf(time)+","+random+","+hash;
    }


    /**
     *
     E.g. body = "{\"role\": \"108289390\"}";
     E.g. queryFromURL = "uid=16299869";
     * @param body
     * @param queryFromURL
     * @return
     */
    public static String generate2(String body, String queryFromURL)  {
        final String salt = "xV8v4Qu54lUKrEYFZkJhB8cuOh9Asafs";
        Date date = new Date();
        long time = (long) Math.floor(date.getTime()/1000);
        String[] preQuery = queryFromURL.split("&");
        Arrays.sort(preQuery);
        String query = "";
        for (int x = 0 ; x < preQuery.length ; x++){
            query += preQuery[x] += (x+1 < preQuery.length ? "&" : "");
        }

        int random = (int) Math.floor(Math.random() * 100001 + 100000);
        if(random == 100000) {
            random = 642367;
        }

        String main = DigestUtils.md5Hex(new String(("salt="+salt+"&t="+time+"&r="+random+"&b="+body+"&q="+query).getBytes(), StandardCharsets.UTF_8));
        return (time +","+random+","+main);

    }
}
