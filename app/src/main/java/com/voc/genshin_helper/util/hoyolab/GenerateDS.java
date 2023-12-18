package com.voc.genshin_helper.util.hoyolab;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
            int randomChar = characters.charAt(randomIndex);
            random += randomChar;
        }

        String hash = DigestUtils.md5Hex("salt="+salt+"&t="+time+"&r="+random);

        return String.valueOf(time)+","+random+","+hash;
    }
}
