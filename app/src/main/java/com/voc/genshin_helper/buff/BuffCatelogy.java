package com.voc.genshin_helper.buff;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

import com.voc.genshin_helper.R;
import com.voc.genshin_helper.buff.db.BuffDBHelper;
import com.voc.genshin_helper.buff.obj.Artifact;
import com.voc.genshin_helper.buff.obj.BuffObject;
import com.voc.genshin_helper.buff.obj.Character;
import com.voc.genshin_helper.buff.obj.Weapon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class BuffCatelogy {

    public static final String RESERVED = "RESERVED";

    public static String[] lvlListChar = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "20+", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "40+", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "50+", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "60+", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "70+", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "80+", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90"};
    public static String[] lvlListCharCon = new String[]{"0","1", "2", "3", "4", "5", "6"};
    public static String[] lvlListWeaponAffix = new String[]{"1", "2", "3", "4", "5", "6"};
    public static String[] lvlListWeapon70 = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "20+", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "40+", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "50+", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "60+", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70"};
    public static String[] lvlListWeapon90 = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "20+", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "40+", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "50+", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "60+", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "70+", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "80+", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90"};
    public static String[] lvlListSkill = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    public static String[] lvlListArt4 = new String[]{"1", "2", "3", "4"};
    public static String[] lvlListArt8 = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};
    public static String[] lvlListArt12 = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    public static String[] lvlListArt16 = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
    public static String[] lvlListArt20 = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
    public static String[] lvlListArtRare = new String[]{"1","2","3","4","5"};

    public static Integer[] charLvlBreak = {0,20,40,50,60,70,80,90,100}; //100 is for avoiding OutOfIndex
    public static Integer[] weaponLvlBreak = {0,20,40,50,60,70,80,90,100}; //100 is for avoiding OutOfIndex


    /**
     artifactStatusValue [rare][type][value]
     rare : Artifact's rare (1...5)
     type : sub status (HP, ATK, ENRECH...)
     value : double value ...
     */
    public static double[][][] artifactStatusValue = new double[][][]{
            {
                    {0.0, 24.0, 30.0},  //HP
                    {0.0, 2.0, 2.0},    //ATK
                    {0.0, 2.0, 2.0},    //DEF
                    {0.0 ,1.2 ,1.5 },   //ATKP
                    {0.0 ,1.2 ,1.5 },   //HPP
                    {0.0 ,1.5 ,1.8 },   //DEFP
                    {0.0 ,4.7 ,5.8 },   //EnRech
                    {0.0 ,1.3 ,1.6 },   //EleMas
                    {0.0 ,0.8 ,1.0 },   //CritRate
                    {0.0 ,1.6 ,1.9 },    //CritDMG
            },
            {
                    {0.0, 50.0, 61.0, 72.0},
                    {0.0, 3.0, 4.0, 5.0},
                    {0.0, 4.0, 5.0, 6.0},
                    {0.0, 1.6, 2.0, 2.3},
                    {0.0, 1.6, 2.0, 2.3},
                    {0.0, 2.0, 2.5, 2.9},
                    {0.0, 6.5, 7.9, 9.3},
                    {0.0, 1.8, 2.2, 2.6},
                    {0.0, 1.1, 1.3, 1.6},
                    {0.0, 2.2, 2.6, 3.1}
            },
            {
                    {0.0, 100.0, 115.0, 129.0, 143.0, 200.0, 215.0, 229.0, 230.0, 243.0, 244.0, 258.0, 272.0, 286.0},
                    {0.0, 7.0, 7.0, 8.0, 9.0, 14.0, 15.0, 16.0, 17.0, 18.0},
                    {0.0, 8.0, 9.0, 10.0, 11.0, 16.0, 17.0, 18.0, 19.0, 20.0, 21.0, 22.0},
                    {0.0, 2.5, 2.8, 3.2, 3.5, 5.0, 5.3, 5.6, 5.7, 6.0, 6.3, 6.4, 6.7, 7.0},
                    {0.0, 2.5, 2.8, 3.2, 3.5, 5.0, 5.3, 5.6, 5.7, 6.0, 6.3, 6.4, 6.7, 7.0},
                    {0.0, 3.1, 3.5, 3.9, 4.4, 6.2, 6.6, 7.0, 7.4, 7.5, 7.8, 7.9, 8.3, 8.8},
                    {0.0, 9.8, 11.2, 12.6, 14.0, 19.6, 21.0, 22.4, 23.8, 25.2, 26.6, 28.0},
                    {0.0, 2.7, 3.1, 3.5, 3.9, 5.4, 5.8, 6.2, 6.6, 7.0, 7.4, 7.8},
                    {0.0, 1.6, 1.9, 2.1, 2.3, 3.2, 3.5, 3.7, 3.8, 3.9, 4.0, 4.2, 4.4, 4.6},
                    {0.0, 3.3, 3.7, 4.2, 4.7, 6.6, 7.0, 7.4, 7.5, 7.9, 8.0, 8.4, 8.9, 9.4}
            },
            {
                    {0.0, 167.0, 191.0, 215.0, 239.0, 334.0, 358.0, 382.0, 406.0, 430.0, 454.0, 478.0, 501.0, 525.0, 549.0, 573.0, 597.0, 621.0, 645.0, 669.0, 693.0, 717.0},
                    {0.0, 11.0, 12.0, 14.0, 16.0, 22.0, 23.0, 24.0, 25.0, 26.0, 27.0, 28.0, 30.0, 32.0, 33.0, 34.0, 35.0, 36.0, 37.0, 38.0, 39.0, 40.0, 41.0, 42.0, 43.0, 44.0, 46.0, 48.0},
                    {0.0, 13.0, 15.0, 17.0, 19.0, 26.0, 28.0, 30.0, 32.0, 34.0, 36.0, 38.0, 39.0, 41.0, 43.0, 45.0, 47.0, 49.0, 51.0, 53.0, 55.0, 57.0},
                    {0.0, 3.3, 3.7, 4.2, 4.7, 6.6, 7.0, 7.4, 7.5, 7.9, 8.0, 8.4, 8.9, 9.4, 9.9, 10.3, 10.7, 10.8, 11.1, 11.2, 11.3, 11.6, 11.7, 12.1, 12.2, 12.6, 12.7, 13.1, 13.6, 14.1},
                    {0.0, 3.3, 3.7, 4.2, 4.7, 6.6, 7.0, 7.4, 7.5, 7.9, 8.0, 8.4, 8.9, 9.4, 9.9, 10.3, 10.7, 10.8, 11.1, 11.2, 11.3, 11.6, 11.7, 12.1, 12.2, 12.6, 12.7, 13.1, 13.6, 14.1},
                    {0.0, 4.1, 4.7, 5.3, 5.8, 8.2, 8.8, 9.4, 9.9, 10.0, 10.5, 10.6, 11.1, 11.6, 12.3, 12.9, 13.5, 14.0, 14.1, 14.6, 14.7, 15.2, 15.3, 15.7, 15.8, 15.9, 16.3, 16.4, 16.9, 17.4},
                    {0.0, 13.1, 14.9, 16.8, 18.6, 26.2, 28.0, 29.8, 29.9, 31.7, 33.5, 33.6, 35.4, 37.2, 39.3, 41.1, 42.9, 43.0, 44.7, 44.8, 46.6, 46.7, 48.4, 48.5, 50.3, 50.4, 52.1, 52.2, 54.0, 55.8},
                    {0.0, 3.6, 4.1, 4.7, 5.2, 7.2, 7.7, 8.2, 8.3, 8.8, 9.3, 9.4, 9.9, 10.4, 10.8, 11.3, 11.8, 11.9, 12.3, 12.4, 12.9, 13.0, 13.4, 13.5, 14.0, 14.1, 14.5, 14.6, 15.1, 15.6},
                    {0.0, 2.2, 2.5, 2.8, 3.1, 4.4, 4.7, 5.0, 5.3, 5.6, 5.9, 6.2, 6.6, 6.9, 7.2, 7.5, 7.8, 8.1, 8.4, 8.7, 9.0, 9.3},
                    {0.0, 4.4, 5.0, 5.6, 6.2, 8.8, 9.4, 10.0, 10.6, 11.2, 11.8, 12.4, 13.2, 13.8, 14.4, 15.0, 15.6, 16.2, 16.8, 17.4, 18.0, 18.6}
            },
            {
                    {0.0, 209.0, 239.0, 269.0, 299.0, 418.0, 448.0, 478.0, 508.0, 538.0, 568.0, 598.0, 627.0, 657.0, 687.0, 717.0, 747.0, 777.0, 807.0, 836.0, 837.0, 866.0, 867.0, 896.0, 897.0, 926.0, 956.0, 986.0, 1016.0, 1045.0, 1046.0, 1075.0, 1076.0, 1105.0, 1106.0, 1135.0, 1136.0, 1165.0, 1166.0, 1195.0, 1196.0, 1225.0, 1255.0, 1285.0, 1315.0, 1345.0, 1375.0, 1405.0, 1435.0, 1465.0, 1495.0},
                    {0.0, 14.0, 16.0, 18.0, 19.0, 28.0, 30.0, 32.0, 33.0, 34.0, 35.0, 36.0, 37.0, 38.0, 42.0, 44.0, 46.0, 47.0, 48.0, 49.0, 50.0, 51.0, 52.0, 53.0, 54.0, 55.0, 56.0, 57.0, 58.0, 60.0, 61.0, 62.0, 63.0, 64.0, 65.0, 66.0, 67.0, 68.0, 69.0, 70.0, 71.0, 72.0, 73.0, 74.0, 75.0, 76.0, 77.0, 78.0, 79.0, 80.0, 81.0, 82.0, 83.0, 84.0, 85.0, 86.0, 87.0, 88.0, 89.0, 90.0, 91.0, 92.0, 93.0, 94.0, 95.0},
                    {0.0, 16.0, 19.0, 21.0, 23.0, 32.0, 35.0, 37.0, 38.0, 39.0, 40.0, 42.0, 44.0, 46.0, 48.0, 51.0, 53.0, 54.0, 55.0, 56.0, 57.0, 58.0, 59.0, 60.0, 61.0, 62.0, 63.0, 64.0, 65.0, 67.0, 69.0, 70.0, 71.0, 72.0, 73.0, 74.0, 75.0, 76.0, 77.0, 78.0, 79.0, 80.0, 81.0, 82.0, 83.0, 84.0, 85.0, 86.0, 87.0, 88.0, 89.0, 90.0, 91.0, 92.0, 93.0, 94.0, 95.0, 96.0, 97.0, 98.0, 99.0, 100.0, 101.0, 102.0, 103.0, 104.0, 105.0, 106.0, 107.0, 108.0, 109.0, 111.0, 113.0, 115.0},
                    {0.0, 4.1, 4.7, 5.3, 5.8, 8.2, 8.8, 9.4, 9.9, 10.0, 10.5, 10.6, 11.1, 11.6, 12.3, 12.9, 13.5, 14.0, 14.1, 14.6, 14.7, 15.2, 15.3, 15.7, 15.8, 15.9, 16.3, 16.4, 16.9, 17.0, 17.4, 17.6, 18.1, 18.2, 18.7, 18.8, 19.3, 19.4, 19.8, 19.9, 20.0, 20.4, 20.5, 20.6, 21.0, 21.1, 21.2, 21.5, 21.6, 21.7, 22.1, 22.2, 22.3, 22.7, 22.8, 22.9, 23.2, 23.4, 23.5, 23.9, 24.0, 24.1, 24.5, 24.6, 24.7, 25.1, 25.2, 25.3, 25.6, 25.7, 25.8, 25.9, 26.2, 26.3, 26.4, 26.5, 26.8, 26.9, 27.0, 27.3, 27.4, 27.5, 27.9, 28.0, 28.5, 29.0},
                    {0.0, 4.1, 4.7, 5.3, 5.8, 8.2, 8.8, 9.4, 9.9, 10.0, 10.5, 10.6, 11.1, 11.6, 12.3, 12.9, 13.5, 14.0, 14.1, 14.6, 14.7, 15.2, 15.3, 15.7, 15.8, 15.9, 16.3, 16.4, 16.9, 17.0, 17.4, 17.6, 18.1, 18.2, 18.7, 18.8, 19.3, 19.4, 19.8, 19.9, 20.0, 20.4, 20.5, 20.6, 21.0, 21.1, 21.2, 21.5, 21.6, 21.7, 22.1, 22.2, 22.3, 22.7, 22.8, 22.9, 23.2, 23.4, 23.5, 23.9, 24.0, 24.1, 24.5, 24.6, 24.7, 25.1, 25.2, 25.3, 25.6, 25.7, 25.8, 25.9, 26.2, 26.3, 26.4, 26.5, 26.8, 26.9, 27.0, 27.3, 27.4, 27.5, 27.9, 28.0, 28.5, 29.0},
                    {0.0, 5.1, 5.8, 6.6, 7.3, 10.2, 10.9, 11.6, 11.7, 12.4, 13.1, 13.2, 13.9, 14.6, 15.3, 16.0, 16.7, 16.8, 17.4, 17.5, 18.2, 18.3, 18.9, 19.0, 19.7, 19.8, 20.4, 20.5, 21.1, 21.2, 21.8, 21.9, 22.5, 22.6, 23.2, 23.3, 23.4, 24.0, 24.1, 24.7, 24.8, 24.9, 25.5, 25.6, 26.2, 26.3, 26.4, 26.9, 27.0, 27.1, 27.6, 27.7, 27.8, 28.3, 28.4, 28.5, 29.0, 29.1, 29.2, 29.8, 29.9, 30.0, 30.5, 30.6, 30.7, 31.3, 31.4, 31.5, 32.0, 32.1, 32.2, 32.8, 32.9, 33.0, 33.5, 33.6, 33.7, 34.3, 34.4, 35.0, 35.1, 35.8, 36.5},
                    {0.0, 16.3, 18.7, 21.0, 23.3, 32.6, 35.0, 37.3, 37.4, 39.6, 39.7, 42.0, 44.3, 46.6, 48.9, 51.3, 53.6, 53.7, 55.9, 56.0, 56.1, 58.3, 58.4, 60.6, 60.7, 62.9, 63.0, 65.2, 65.3, 67.6, 69.9, 70.0, 72.2, 72.3, 72.4, 74.6, 74.7, 74.8, 76.9, 77.0, 77.1, 79.2, 79.3, 79.4, 81.5, 81.6, 81.7, 83.9, 84.0, 86.2, 86.3, 88.5, 88.6, 88.7, 90.9, 91.0, 91.1, 93.2, 93.3, 93.4, 93.5, 95.5, 95.6, 95.7, 95.8, 97.9, 98.0, 98.1, 100.2, 100.3, 100.4, 102.5, 102.6, 102.7, 104.9, 105.0, 107.2, 107.3, 109.5, 109.6, 111.9, 114.2, 116.5},
                    {0.0, 4.5, 5.2, 5.8, 6.5, 9.0, 9.7, 10.3, 10.4, 11.0, 11.6, 11.7, 12.3, 13.0, 13.5, 14.2, 14.8, 14.9, 15.5, 15.6, 16.1, 16.2, 16.8, 16.9, 17.4, 17.5, 18.0, 18.1, 18.2, 18.7, 18.8, 19.3, 19.4, 19.5, 20.0, 20.1, 20.6, 20.7, 20.8, 21.3, 21.4, 21.9, 22.0, 22.1, 22.5, 22.6, 22.7, 23.2, 23.3, 23.4, 23.8, 23.9, 24.0, 24.5, 24.6, 24.7, 25.1, 25.2, 25.3, 25.8, 25.9, 26.0, 26.4, 26.5, 26.6, 27.1, 27.2, 27.3, 27.7, 27.8, 27.9, 28.4, 28.5, 28.6, 29.0, 29.1, 29.2, 29.7, 29.8, 29.9, 30.4, 30.5, 31.1, 31.2, 31.8, 32.5},
                    {0.0, 2.7, 3.1, 3.5, 3.9, 5.4, 5.8, 6.2, 6.6, 7.0, 7.4, 7.8, 8.1, 8.5, 8.9, 9.3, 9.7, 10.1, 10.5, 10.8, 10.9, 11.2, 11.3, 11.6, 11.7, 12.0, 12.4, 12.8, 13.2, 13.5, 13.6, 13.9, 14.0, 14.3, 14.4, 14.7, 14.8, 15.1, 15.2, 15.5, 15.6, 15.9, 16.3, 16.7, 17.1, 17.5, 17.9, 18.3, 18.7, 19.1, 19.5},
                    {0.0, 5.4, 6.2, 7.0, 7.8, 10.8, 11.6, 12.4, 13.2, 14.0, 14.8, 15.6, 16.2, 17.0, 17.8, 18.6, 19.4, 20.2, 21.0, 21.6, 21.8, 22.4, 22.6, 23.2, 23.4, 24.0, 24.8, 25.6, 26.4, 27.0, 27.2, 27.8, 28.0, 28.6, 28.8, 29.4, 29.6, 30.2, 30.4, 31.0, 31.2, 31.8, 32.6, 33.4, 34.2, 35.0, 35.8, 36.6, 37.4, 38.2, 39.0}
            }

    };




    //get from https://ambr.top/cht/archive/avatar
    public String getCharNameByID(int id){
        switch (id){
            case -1 : return "N/A";
            case 10000002 : return "Kamisato Ayaka";
            case 10000003 : return "Jean";
            case 10000005 : return "Aether";
            case 10000006 : return "Lisa";
            case 10000007 : return "Lumine";
            case 10000014 : return "Barbara";
            case 10000015 : return "Kaeya";
            case 10000016 : return "Diluc";
            case 10000020 : return "Razor";
            case 10000021 : return "Amber";
            case 10000022 : return "Venti";
            case 10000023 : return "Xiangling";
            case 10000024 : return "Beidou";
            case 10000025 : return "Xingqiu";
            case 10000026 : return "Xiao";
            case 10000027 : return "Ningguang";
            case 10000029 : return "Klee";
            case 10000030 : return "Zhongli";
            case 10000031 : return "Fischl";
            case 10000032 : return "Bennett";
            case 10000033 : return "Tartaglia";
            case 10000034 : return "Noelle";
            case 10000035 : return "Qiqi";
            case 10000036 : return "Chongyun";
            case 10000037 : return "Ganyu";
            case 10000038 : return "Albedo";
            case 10000039 : return "Diona";
            case 10000041 : return "Mona";
            case 10000042 : return "Keqing";
            case 10000043 : return "Sucrose";
            case 10000044 : return "Xinyan";
            case 10000045 : return "Rosaria";
            case 10000046 : return "Hu Tao";
            case 10000047 : return "Kaedehara Kazuha";
            case 10000048 : return "Yanfei";
            case 10000049 : return "Yoimiya";
            case 10000050 : return "Thoma";
            case 10000051 : return "Eula";
            case 10000052 : return "Raiden Shogun";
            case 10000053 : return "Sayu";
            case 10000054 : return "Sangonomiya Kokomi";
            case 10000055 : return "Gorou";
            case 10000056 : return "Kujou Sara";
            case 10000057 : return "Arataki Itto";
            case 10000058 : return "Yae Miko";
            case 10000059 : return "Shikanoin Heizou";
            case 10000060 : return "Yelan";
            case 10000062 : return "Aloy";
            case 10000063 : return "Shenhe";
            case 10000064 : return "Yun Jin";
            case 10000065 : return "Kuki Shinobu";
            case 10000066 : return "Kamisato Ayato";
            case 10000067 : return "Collei";
            case 10000068 : return "Dori";
            case 10000069 : return "Tighnari";
            case 10000070 : return "Nilou";
            case 10000071 : return "Cyno";
            case 10000072 : return "Candace";
            case 10000073 : return "Nahida";
            case 10000074 : return "Layla";
            case 10000075 : return "Wanderer";
            case 10000076 : return "Faruzan";
            case 10000077 : return "Yaoyao";
            case 10000078 : return "Alhaitham";
            case 10000079 : return "Dehya";
            case 10000080 : return "Mika";
            case 10000081 : return "Kaveh";
            case 10000082 : return "Baizhu";

            default: return "unknown";
        }
    }

    public int getIdByCharName(String name){
        switch (name){
            case "N/A" : return -1;
            case "Kamisato Ayaka" : return 10000002;
            case "Jean" : return 10000003;
            case "Aether" : return 10000005;
            case "Lisa" : return 10000006;
            case "Lumine" : return 10000007;
            case "Barbara" : return 10000014;
            case "Kaeya" : return 10000015;
            case "Diluc" : return 10000016;
            case "Razor" : return 10000020;
            case "Amber" : return 10000021;
            case "Venti" : return 10000022;
            case "Xiangling" : return 10000023;
            case "Beidou" : return 10000024;
            case "Xingqiu" : return 10000025;
            case "Xiao" : return 10000026;
            case "Ningguang" : return 10000027;
            case "Klee" : return 10000029;
            case "Zhongli" : return 10000030;
            case "Fischl" : return 10000031;
            case "Bennett" : return 10000032;
            case "Tartaglia" : return 10000033;
            case "Noelle" : return 10000034;
            case "Qiqi" : return 10000035;
            case "Chongyun" : return 10000036;
            case "Ganyu" : return 10000037;
            case "Albedo" : return 10000038;
            case "Diona" : return 10000039;
            case "Mona" : return 10000041;
            case "Keqing" : return 10000042;
            case "Sucrose" : return 10000043;
            case "Xinyan" : return 10000044;
            case "Rosaria" : return 10000045;
            case "Hu Tao" : return 10000046;
            case "Kaedehara Kazuha" : return 10000047;
            case "Yanfei" : return 10000048;
            case "Yoimiya" : return 10000049;
            case "Thoma" : return 10000050;
            case "Eula" : return 10000051;
            case "Raiden Shogun" : return 10000052;
            case "Sayu" : return 10000053;
            case "Sangonomiya Kokomi" : return 10000054;
            case "Gorou" : return 10000055;
            case "Kujou Sara" : return 10000056;
            case "Arataki Itto" : return 10000057;
            case "Yae Miko" : return 10000058;
            case "Shikanoin Heizou" : return 10000059;
            case "Yelan" : return 10000060;
            case "Aloy" : return 10000062;
            case "Shenhe" : return 10000063;
            case "Yun Jin" : return 10000064;
            case "Kuki Shinobu" : return 10000065;
            case "Kamisato Ayato" : return 10000066;
            case "Collei" : return 10000067;
            case "Dori" : return 10000068;
            case "Tighnari" : return 10000069;
            case "Nilou" : return 10000070;
            case "Cyno" : return 10000071;
            case "Candace" : return 10000072;
            case "Nahida" : return 10000073;
            case "Layla" : return 10000074;
            case "Wanderer" : return 10000075;
            case "Faruzan" : return 10000076;
            case "Yaoyao" : return 10000077;
            case "Alhaitham" : return 10000078;
            case "Dehya" : return 10000079;
            case "Mika" : return 10000080;
            case "Kaveh" : return 10000081;
            case "Baizhu" : return 10000082;

            default: return 0;
        }
    }

    //get from https://ambr.top/cht/archive/weapon
    public String getWeaponById (int id){
        switch (id){
            case -1 : return "N/A";
            case 11101 : return "Dull Blade";
            case 11201 : return "Silver Sword";
            case 11301 : return "Cool Steel";
            case 11302 : return "Harbinger of Dawn";
            case 11303 : return "Traveler's Handy Sword";
            case 11304 : return "Dark Iron Sword";
            case 11305 : return "Fillet Blade";
            case 11306 : return "Skyrider Sword";
            case 11401 : return "Favonius Sword";
            case 11402 : return "The Flute";
            case 11403 : return "Sacrificial Sword";
            case 11404 : return "Royal Longsword";
            case 11405 : return "Lion's Roar";
            case 11406 : return "Prototype Rancour";
            case 11407 : return "Iron Sting";
            case 11408 : return "Blackcliff Longsword";
            case 11409 : return "The Black Sword";
            case 11410 : return "The Alley Flash";
            case 11412 : return "Sword of Descension";
            case 11413 : return "Festering Desire";
            case 11414 : return "Amenoma Kageuchi";
            case 11415 : return "Cinnabar Spindle";
            case 11416 : return "Kagotsurube Iisshin";
            case 11417 : return "Sapwood Blade";
            case 11418 : return "Xiphos' Moonlight";
            case 11422 : return "Toukabou Shigure";
            case 11501 : return "Aquila Favonia";
            case 11502 : return "Skyward Blade";
            case 11503 : return "Freedom Sworn";
            case 11504 : return "Summit Shaper";
            case 11505 : return "Primordial Jade Cutter";
            case 11509 : return "Mistsplitter Reforged";
            case 11510 : return "Haran Geppaku Futsu";
            case 11511 : return "Key of Khaj-Nisut";
            case 11512 : return "Light of Foliar Incision";
            case 12101 : return "Waster Greatsword";
            case 12201 : return "Old Merc's Pal";
            case 12301 : return "Ferrous Shadow";
            case 12302 : return "Bloodtainted Greatsword";
            case 12303 : return "White Iron Greatsword";
            case 12305 : return "Debate Club";
            case 12306 : return "Skyrider Greatsword";
            case 12401 : return "Favonius Greatsword";
            case 12402 : return "The Bell";
            case 12403 : return "Sacrificial Greatsword";
            case 12404 : return "Royal Greatsword";
            case 12405 : return "Rainslasher";
            case 12406 : return "Prototype Archaic";
            case 12407 : return "Whiteblind";
            case 12408 : return "Blackcliff Slasher";
            case 12409 : return "Serpent Spine";
            case 12410 : return "Lithic Blade";
            case 12411 : return "Snow-Tombed Starsilver";
            case 12412 : return "Luxurious Sea-Lord";
            case 12414 : return "Katsuragikiri Nagamasa";
            case 12415 : return "Makhaira Aquamarine";
            case 12416 : return "Akuoumaru";
            case 12417 : return "Forest Regalia";
            case 12418 : return "Mailed Flower";
            case 12501 : return "Skyward Pride";
            case 12502 : return "Wolf's Gravestone";
            case 12503 : return "Song of Broken Pines";
            case 12504 : return "The Unforged";
            case 12505 : return "primordial_jade_greatsword";
            case 12510 : return "Redhorn Stonethresher";
            case 12511 : return "Beacon of the Reed Sea";
            case 13101 : return "Beginner's Protector";
            case 13201 : return "Iron Point";
            case 13301 : return "White Tassel";
            case 13302 : return "Halberd";
            case 13303 : return "Black Tassel";
            case 13401 : return "Dragon's Bane";
            case 13402 : return "Prototype Starglitter";
            case 13403 : return "Crescent Pike";
            case 13404 : return "Blackcliff Pole";
            case 13405 : return "Deathmatch";
            case 13406 : return "Lithic Spear";
            case 13407 : return "Favonius Lance";
            case 13408 : return "Royal Spear";
            case 13409 : return "Dragonspine Spear";
            case 13414 : return "Kitain Cross Spear";
            case 13415 : return "The Catch";
            case 13416 : return "Wavebreaker's Fin";
            case 13417 : return "Moonpiercer";
            case 13419 : return "Missive Windspear";
            case 13501 : return "Staff of Homa";
            case 13502 : return "Skyward Spine";
            case 13504 : return "Vortex Vanquisher";
            case 13505 : return "Primordial Jade Winged-Spear";
            case 13507 : return "Calamity Queller";
            case 13509 : return "Engulfing Lightning";
            case 13511 : return "Staff of the Scarlet Sands";
            case 14101 : return "Apprentice's Notes";
            case 14201 : return "Pocket Grimoire";
            case 14301 : return "Magic Guide";
            case 14302 : return "Thrilling Tales of Dragon Slayers";
            case 14303 : return "Otherworldly Story";
            case 14304 : return "Emerald Orb";
            case 14305 : return "Twin Nephrite";
            case 14401 : return "Favonius Codex";
            case 14402 : return "The Widsith";
            case 14403 : return "Sacrificial Fragments";
            case 14404 : return "Royal Grimoire";
            case 14405 : return "Solar Pearl";
            case 14406 : return "Prototype Amber";
            case 14407 : return "Mappa Mare";
            case 14408 : return "Blackcliff Agate";
            case 14409 : return "Eye of Perception";
            case 14410 : return "Wine and Song";
            case 14412 : return "Frostbearer";
            case 14413 : return "Dodoco Tales";
            case 14414 : return "Hakushin Ring";
            case 14415 : return "Oathsworn Eye";
            case 14416 : return "Wandering Evenstar";
            case 14417 : return "Fruit of Fulfillment";
            case 14501 : return "Skyward Atlas";
            case 14502 : return "Lost Prayer to the Sacred Winds";
            case 14504 : return "Memory of Dust";
            case 14505 : return "Jadefall's Splendor";
            case 14506 : return "Everlasting Moonglow";
            case 14509 : return "Kagura's Verity";
            case 14511 : return "A Thousand Floating Dreams";
            case 14512 : return "Tulaytullah's Remembrance";
            case 15101 : return "Hunter's Bow";
            case 15201 : return "Seasoned Hunter's Bow";
            case 15301 : return "Raven Bow";
            case 15302 : return "Sharpshooter's Oath";
            case 15303 : return "Recurve Bow";
            case 15304 : return "Slingshot";
            case 15305 : return "Messenger";
            case 15401 : return "Favonius Warbow";
            case 15402 : return "The Stringless";
            case 15403 : return "Sacrificial Bow";
            case 15404 : return "Royal Bow";
            case 15405 : return "Rust";
            case 15406 : return "Prototype Crescent";
            case 15407 : return "Compound Bow";
            case 15408 : return "Blackcliff Warbow";
            case 15409 : return "The Viridescent Hunt";
            case 15410 : return "Alley Hunter";
            case 15411 : return "Fading Twilight";
            case 15412 : return "Mitternachts Waltz";
            case 15413 : return "Windblume Ode";
            case 15414 : return "Hamayumi";
            case 15415 : return "Predator";
            case 15416 : return "Mouun's Moon";
            case 15417 : return "King's Squire";
            case 15418 : return "End of the Line";
            case 15501 : return "Skyward Harp";
            case 15502 : return "Amos' Bow";
            case 15503 : return "Elegy for the End";
            case 15507 : return "Polar Star";
            case 15508 : return "Aqua Simulacra";
            case 15509 : return "Thundering Pulse";
            case 15511 : return "Hunter's Path";

            default: return "unknown";
        }
    }
    public int getIdByWeaponName(String name){
        switch (name){
            case "N/A" : return -1;
            case "Dull Blade" : return 11101;
            case "Silver Sword" : return 11201;
            case "Cool Steel" : return 11301;
            case "Harbinger of Dawn" : return 11302;
            case "Traveler's Handy Sword" : return 11303;
            case "Dark Iron Sword" : return 11304;
            case "Fillet Blade" : return 11305;
            case "Skyrider Sword" : return 11306;
            case "Favonius Sword" : return 11401;
            case "The Flute" : return 11402;
            case "Sacrificial Sword" : return 11403;
            case "Royal Longsword" : return 11404;
            case "Lion's Roar" : return 11405;
            case "Prototype Rancour" : return 11406;
            case "Iron Sting" : return 11407;
            case "Blackcliff Longsword" : return 11408;
            case "The Black Sword" : return 11409;
            case "The Alley Flash" : return 11410;
            case "Sword of Descension" : return 11412;
            case "Festering Desire" : return 11413;
            case "Amenoma Kageuchi" : return 11414;
            case "Cinnabar Spindle" : return 11415;
            case "Kagotsurube Iisshin" : return 11416;
            case "Sapwood Blade" : return 11417;
            case "Xiphos' Moonlight" : return 11418;
            case "Toukabou Shigure" : return 11422;
            case "Aquila Favonia" : return 11501;
            case "Skyward Blade" : return 11502;
            case "Freedom Sworn" : return 11503;
            case "Summit Shaper" : return 11504;
            case "Primordial Jade Cutter" : return 11505;
            case "Mistsplitter Reforged" : return 11509;
            case "Haran Geppaku Futsu" : return 11510;
            case "Key of Khaj-Nisut" : return 11511;
            case "Light of Foliar Incision" : return 11512;
            case "Waster Greatsword" : return 12101;
            case "Old Merc's Pal" : return 12201;
            case "Ferrous Shadow" : return 12301;
            case "Bloodtainted Greatsword" : return 12302;
            case "White Iron Greatsword" : return 12303;
            case "Debate Club" : return 12305;
            case "Skyrider Greatsword" : return 12306;
            case "Favonius Greatsword" : return 12401;
            case "The Bell" : return 12402;
            case "Sacrificial Greatsword" : return 12403;
            case "Royal Greatsword" : return 12404;
            case "Rainslasher" : return 12405;
            case "Prototype Archaic" : return 12406;
            case "Whiteblind" : return 12407;
            case "Blackcliff Slasher" : return 12408;
            case "Serpent Spine" : return 12409;
            case "Lithic Blade" : return 12410;
            case "Snow-Tombed Starsilver" : return 12411;
            case "Luxurious Sea-Lord" : return 12412;
            case "Katsuragikiri Nagamasa" : return 12414;
            case "Makhaira Aquamarine" : return 12415;
            case "Akuoumaru" : return 12416;
            case "Forest Regalia" : return 12417;
            case "Mailed Flower" : return 12418;
            case "Skyward Pride" : return 12501;
            case "Wolf's Gravestone" : return 12502;
            case "Song of Broken Pines" : return 12503;
            case "The Unforged" : return 12504;
            case "primordial_jade_greatsword" : return 12505;
            case "Redhorn Stonethresher" : return 12510;
            case "Beacon of the Reed Sea" : return 12511;
            case "Beginner's Protector" : return 13101;
            case "Iron Point" : return 13201;
            case "White Tassel" : return 13301;
            case "Halberd" : return 13302;
            case "Black Tassel" : return 13303;
            case "Dragon's Bane" : return 13401;
            case "Prototype Starglitter" : return 13402;
            case "Crescent Pike" : return 13403;
            case "Blackcliff Pole" : return 13404;
            case "Deathmatch" : return 13405;
            case "Lithic Spear" : return 13406;
            case "Favonius Lance" : return 13407;
            case "Royal Spear" : return 13408;
            case "Dragonspine Spear" : return 13409;
            case "Kitain Cross Spear" : return 13414;
            case "The Catch" : return 13415;
            case "Wavebreaker's Fin" : return 13416;
            case "Moonpiercer" : return 13417;
            case "Missive Windspear" : return 13419;
            case "Staff of Homa" : return 13501;
            case "Skyward Spine" : return 13502;
            case "Vortex Vanquisher" : return 13504;
            case "Primordial Jade Winged-Spear" : return 13505;
            case "Calamity Queller" : return 13507;
            case "Engulfing Lightning" : return 13509;
            case "Staff of the Scarlet Sands" : return 13511;
            case "Apprentice's Notes" : return 14101;
            case "Pocket Grimoire" : return 14201;
            case "Magic Guide" : return 14301;
            case "Thrilling Tales of Dragon Slayers" : return 14302;
            case "Otherworldly Story" : return 14303;
            case "Emerald Orb" : return 14304;
            case "Twin Nephrite" : return 14305;
            case "Favonius Codex" : return 14401;
            case "The Widsith" : return 14402;
            case "Sacrificial Fragments" : return 14403;
            case "Royal Grimoire" : return 14404;
            case "Solar Pearl" : return 14405;
            case "Prototype Amber" : return 14406;
            case "Mappa Mare" : return 14407;
            case "Blackcliff Agate" : return 14408;
            case "Eye of Perception" : return 14409;
            case "Wine and Song" : return 14410;
            case "Frostbearer" : return 14412;
            case "Dodoco Tales" : return 14413;
            case "Hakushin Ring" : return 14414;
            case "Oathsworn Eye" : return 14415;
            case "Wandering Evenstar" : return 14416;
            case "Fruit of Fulfillment" : return 14417;
            case "Skyward Atlas" : return 14501;
            case "Lost Prayer to the Sacred Winds" : return 14502;
            case "Memory of Dust" : return 14504;
            case "Jadefall's Splendor" : return 14505;
            case "Everlasting Moonglow" : return 14506;
            case "Kagura's Verity" : return 14509;
            case "A Thousand Floating Dreams" : return 14511;
            case "Tulaytullah's Remembrance" : return 14512;
            case "Hunter's Bow" : return 15101;
            case "Seasoned Hunter's Bow" : return 15201;
            case "Raven Bow" : return 15301;
            case "Sharpshooter's Oath" : return 15302;
            case "Recurve Bow" : return 15303;
            case "Slingshot" : return 15304;
            case "Messenger" : return 15305;
            case "Favonius Warbow" : return 15401;
            case "The Stringless" : return 15402;
            case "Sacrificial Bow" : return 15403;
            case "Royal Bow" : return 15404;
            case "Rust" : return 15405;
            case "Prototype Crescent" : return 15406;
            case "Compound Bow" : return 15407;
            case "Blackcliff Warbow" : return 15408;
            case "The Viridescent Hunt" : return 15409;
            case "Alley Hunter" : return 15410;
            case "Fading Twilight" : return 15411;
            case "Mitternachts Waltz" : return 15412;
            case "Windblume Ode" : return 15413;
            case "Hamayumi" : return 15414;
            case "Predator" : return 15415;
            case "Mouun's Moon" : return 15416;
            case "King's Squire" : return 15417;
            case "End of the Line" : return 15418;
            case "Skyward Harp" : return 15501;
            case "Amos' Bow" : return 15502;
            case "Elegy for the End" : return 15503;
            case "Polar Star" : return 15507;
            case "Aqua Simulacra" : return 15508;
            case "Thundering Pulse" : return 15509;
            case "Hunter's Path" : return 15511;


            default: return 0;
        }
    }

    //get from https://ambr.top/cht/archive/reliquary
    public String getArtifactById (int id){
        switch (id){
            case -1 : return "N/A";
            case 10010: return "Adventurer";
            case 10011: return "Lucky Dog";
            case 10013: return "Traveling Doctor";
            case 10001: return "Resolution of Sojourner";
            case 10004: return "Tiny Miracle";
            case 10005: return "Berserker";
            case 10007: return "Instructor";
            case 10009: return "The Exile";
            case 10003: return "Defender's Will";
            case 10002: return "Brave Heart";
            case 10006: return "Martial Artist";
            case 10008: return "Gambler";
            case 10012: return "Scholar";
            case 15009: return "prayers_for_illumination";
            case 15010: return "prayers_for_destiny";
            case 15011: return "prayers_for_wisdom";
            case 15013: return "prayers_to_springtime";
            case 14001: return "Blizzard Strayer";
            case 14002: return "Thunder-soother";
            case 14003: return "Lavawalker";
            case 14004: return "Maiden Beloved";
            case 15001: return "Gladiator's Finale";
            case 15002: return "Viridescent Venerer";
            case 15003: return "Wanderer's Troupe";
            case 15005: return "Thundering Fury";
            case 15006: return "Crimson Witch of Flames";
            case 15007: return "Noblesse Oblige";
            case 15008: return "Bloodstained Chivalry";
            case 15014: return "Archaic Petra";
            case 15015: return "Retracing Bolide";
            case 15016: return "Heart of Depth";
            case 15017: return "Tenacity of the Millelith";
            case 15018: return "Pale Flame";
            case 15019: return "Shimenawa's Reminiscence";
            case 15020: return "Emblem of Severed Fate";
            case 15021: return "Husk of Opulent Dreams";
            case 15022: return "Ocean-Hued Clam";
            case 15023: return "Vermillion Hereafter";
            case 15024: return "Echoes of an Offering";
            case 15025: return "Deepwood Memories";
            case 15026: return "Gilded Dreams";
            case 15027: return "Desert Pavilion Chronicle";
            case 15028: return "Flower of Paradise Lost";
            case 15029: return RESERVED;
            case 15030: return RESERVED;

            default:return "unknown";

        }
    }
    public int getIdByArtifactName(String name){
        switch (name){
            case "N/A" : return -1;
            case "Adventurer" : return 10010;
            case "Lucky Dog" : return 10011;
            case "Traveling Doctor" : return 10013;
            case "Resolution of Sojourner" : return 10001;
            case "Tiny Miracle" : return 10004;
            case "Berserker" : return 10005;
            case "Instructor" : return 10007;
            case "The Exile" : return 10009;
            case "Defender's Will" : return 10003;
            case "Brave Heart" : return 10002;
            case "Martial Artist" : return 10006;
            case "Gambler" : return 10008;
            case "Scholar" : return 10012;
            case "prayers_for_illumination" : return 15009;
            case "prayers_for_destiny" : return 15010;
            case "prayers_for_wisdom" : return 15011;
            case "prayers_to_springtime" : return 15013;
            case "Blizzard Strayer" : return 14001;
            case "Thunder-soother" : return 14002;
            case "Lavawalker" : return 14003;
            case "Maiden Beloved" : return 14004;
            case "Gladiator's Finale" : return 15001;
            case "Viridescent Venerer" : return 15002;
            case "Wanderer's Troupe" : return 15003;
            case "Thundering Fury" : return 15005;
            case "Crimson Witch of Flames" : return 15006;
            case "Noblesse Oblige" : return 15007;
            case "Bloodstained Chivalry" : return 15008;
            case "Archaic Petra" : return 15014;
            case "Retracing Bolide" : return 15015;
            case "Heart of Depth" : return 15016;
            case "Tenacity of the Millelith" : return 15017;
            case "Pale Flame" : return 15018;
            case "Shimenawa's Reminiscence" : return 15019;
            case "Emblem of Severed Fate" : return 15020;
            case "Husk of Opulent Dreams" : return 15021;
            case "Ocean-Hued Clam" : return 15022;
            case "Vermillion Hereafter" : return 15023;
            case "Echoes of an Offering" : return 15024;
            case "Deepwood Memories" : return 15025;
            case "Gilded Dreams" : return 15026;
            case "Desert Pavilion Chronicle" : return 15027;
            case "Flower of Paradise Lost" : return 15028;
            case "RESERVED1" : return 15029;
            case "RESERVED2" : return 15030;


            default: return 0;
        }
    }

    public String getWeaponTypeByName (String name_local, Context context){
        String name, weapon;
        String json_base = LoadData("db/weapons/weapon_list.json",context);
        //Get data from JSON
        try {
            JSONArray array = new JSONArray(json_base);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                name = object.getString("name");
                weapon = object.getString("weapon");

                if(name_local.equals(name)){
                    switch (weapon){
                        case "Sword" : return Weapon.SWORD;
                        case "Claymore" : return Weapon.CLAYMORE;
                        case "Catalyst" : return Weapon.CATALYST;
                        case "Bow" : return Weapon.BOW;
                        case "Polearm" : return Weapon.POLEARM;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Weapon.SWORD;
    }
    public int getCharRareByName(String name_local, Context context) {
        String name;
        int rare;
        String json_base = LoadData("db/char/char_list.json",context);
        //Get data from JSON
        try {
            JSONArray array = new JSONArray(json_base);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                name = object.getString("name");
                rare = object.getInt("rare");

                if(name_local.equals(name)){
                    return rare;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getCharElementByName(String name_local, Context context) {
        String name;
        String element;
        String json_base = LoadData("db/char/char_list.json",context);
        //Get data from JSON
        try {
            JSONArray array = new JSONArray(json_base);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                name = object.getString("name");
                element = object.getString("element");

                if(name_local.equals(name)){
                    switch (element){
                        case "Anemo" : return Character.ANEMO;
                        case "Cryo" : return Character.CRYO;
                        case "Dendro" : return Character.DENDRO;
                        case "Electro" : return Character.ELECTRO;
                        case "Geo" : return Character.GEO;
                        case "Hydro" : return Character.HYDRO;
                        case "Pyro" : return Character.PYRO;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Character.PYRO;
    }

    public int getStatusIcoByName(String name){
        switch (name){
            case BuffObject.FIGHT_PROP_BASE_ATK : return R.drawable.ic_buff_atk_2048;
            case BuffObject.FIGHT_PROP_ATK : return R.drawable.ic_buff_atk_2048;
            case BuffObject.FIGHT_PROP_DEF : return R.drawable.ic_buff_def_2048;
            case BuffObject.FIGHT_PROP_HP : return R.drawable.ic_buff_hp_2048;
            case BuffObject.FIGHT_PROP_HP_P : return R.drawable.ic_buff_hp_2048;
            case BuffObject.FIGHT_PROP_ATK_P : return R.drawable.ic_buff_atk_2048;
            case BuffObject.FIGHT_PROP_DEF_P : return R.drawable.ic_buff_def_2048;
            case BuffObject.FIGHT_PROP_CRIT_RATE : return R.drawable.ic_buff_crit_rate_2048;
            case BuffObject.FIGHT_PROP_CRIT_DMG : return R.drawable.ic_buff_crit_dmg_2048;
            case BuffObject.FIGHT_PROP_EN_RECH : return R.drawable.ic_buff_enrech_2048;
            case BuffObject.FIGHT_PROP_HEAL_P : return R.drawable.ic_buff_hp_2048;
            case BuffObject.FIGHT_PROP_ELE_MAS : return R.drawable.ic_buff_elemas_2048;
            case BuffObject.FIGHT_PROP_PHY_DMG : return R.drawable.ic_buff_atk_2048;
            case BuffObject.FIGHT_PROP_PYRO_DMG : return R.drawable.ic_buff_pyro_2048;
            case BuffObject.FIGHT_PROP_ELECTRO_DMG : return R.drawable.ic_buff_electro_2048;
            case BuffObject.FIGHT_PROP_HYDRO_DMG : return R.drawable.ic_buff_hydro_2048;
            case BuffObject.FIGHT_PROP_ANEMO_DMG : return R.drawable.ic_buff_anemo_2048;
            case BuffObject.FIGHT_PROP_CRYO_DMG : return R.drawable.ic_buff_cryo_2048;
            case BuffObject.FIGHT_PROP_GEO_DMG : return R.drawable.ic_buff_geo_2048;
            case BuffObject.FIGHT_PROP_DENDRO_DMG : return R.drawable.ic_buff_dendro_2048;
            default: return R.drawable.paimon_sleep;
        }
    }

    public String getStatusNameByLocaleName(String name, Context context) {
        if ("基礎攻擊力".equals(name) || context.getString(R.string.weapon_stat_atk_base).equals(name)) {
            return BuffObject.FIGHT_PROP_BASE_ATK;
        } else if ("生命值".equals(name) || context.getString(R.string.weapon_stat_HP).equals(name)) {
            return BuffObject.FIGHT_PROP_HP;
        } else if ("攻擊力".equals(name) || context.getString(R.string.weapon_stat_atk).equals(name)) {
            return BuffObject.FIGHT_PROP_ATK;
        } else if ("防禦力".equals(name) || context.getString(R.string.weapon_stat_DEF).equals(name)) {
            return BuffObject.FIGHT_PROP_DEF;
        } else if ("生命值百分比".equals(name) || context.getString(R.string.weapon_stat_HPP).equals(name)) {
            return BuffObject.FIGHT_PROP_HP_P;
        } else if ("攻擊力百分比".equals(name) || context.getString(R.string.weapon_stat_atkP).equals(name)) {
            return BuffObject.FIGHT_PROP_ATK_P;
        } else if ("防禦力百分比".equals(name) || context.getString(R.string.weapon_stat_DEFP).equals(name)) {
            return BuffObject.FIGHT_PROP_DEF_P;
        } else if ("暴擊率".equals(name) || context.getString(R.string.weapon_stat_CritRateP).equals(name)) {
            return BuffObject.FIGHT_PROP_CRIT_RATE;
        } else if ("暴擊傷害".equals(name) || context.getString(R.string.weapon_stat_CritDMGP).equals(name)) {
            return BuffObject.FIGHT_PROP_CRIT_DMG;
        } else if ("元素充能效率".equals(name) || context.getString(R.string.weapon_stat_EnRechP).equals(name)) {
            return BuffObject.FIGHT_PROP_EN_RECH;
        } else if ("治療加成".equals(name) || context.getString(R.string.weapon_stat_HealingP).equals(name)) {
            return BuffObject.FIGHT_PROP_HEAL_P;
        } else if ("元素精通".equals(name) || context.getString(R.string.weapon_stat_EleMas).equals(name)) {
            return BuffObject.FIGHT_PROP_ELE_MAS;
        } else if ("物理傷害加成".equals(name) || context.getString(R.string.weapon_stat_PhyDMGP).equals(name)) {
            return BuffObject.FIGHT_PROP_PHY_DMG;
        } else if ("火元素傷害加成".equals(name) || context.getString(R.string.weapon_stat_EleDMGP_Pyro).equals(name)) {
            return BuffObject.FIGHT_PROP_PYRO_DMG;
        } else if ("雷元素傷害加成".equals(name) || context.getString(R.string.weapon_stat_EleDMGP_Electro).equals(name)) {
            return BuffObject.FIGHT_PROP_ELECTRO_DMG;
        } else if ("水元素傷害加成".equals(name) || context.getString(R.string.weapon_stat_EleDMGP_Hydro).equals(name)) {
            return BuffObject.FIGHT_PROP_HYDRO_DMG;
        } else if ("風元素傷害加成".equals(name) || context.getString(R.string.weapon_stat_EleDMGP_Anemo).equals(name)) {
            return BuffObject.FIGHT_PROP_ANEMO_DMG;
        } else if ("冰元素傷害加成".equals(name) || context.getString(R.string.weapon_stat_EleDMGP_Cryo).equals(name)) {
            return BuffObject.FIGHT_PROP_CRYO_DMG;
        } else if ("岩元素傷害加成".equals(name) || context.getString(R.string.weapon_stat_EleDMGP_Geo).equals(name)) {
            return BuffObject.FIGHT_PROP_GEO_DMG;
        } else if ("草元素傷害加成".equals(name) || context.getString(R.string.weapon_stat_EleDMGP_Dendor).equals(name)) {
            return BuffObject.FIGHT_PROP_DENDRO_DMG;
        }
        return name;
    }

    public double[] getArtifactStatusValue(int rare, String statusStr){
        System.out.println("rare : "+rare);
        switch (statusStr){
            case BuffObject.FIGHT_PROP_HP:{return artifactStatusValue[rare-1][0];}
            case BuffObject.FIGHT_PROP_ATK:{return artifactStatusValue[rare-1][1];}
            case BuffObject.FIGHT_PROP_DEF:{return artifactStatusValue[rare-1][2];}
            case BuffObject.FIGHT_PROP_ATK_P:{return artifactStatusValue[rare-1][3];}
            case BuffObject.FIGHT_PROP_HP_P:{return artifactStatusValue[rare-1][4];}
            case BuffObject.FIGHT_PROP_DEF_P:{return artifactStatusValue[rare-1][5];}
            case BuffObject.FIGHT_PROP_EN_RECH:{return artifactStatusValue[rare-1][6];}
            case BuffObject.FIGHT_PROP_ELE_MAS:{return artifactStatusValue[rare-1][7];}
            case BuffObject.FIGHT_PROP_CRIT_RATE:{return artifactStatusValue[rare-1][8];}
            case BuffObject.FIGHT_PROP_CRIT_DMG:{return artifactStatusValue[rare-1][9];}
            default: return new double[]{0};
        }
    }

    public int getLocaleNameByStatusName(String TAG){
        switch (TAG){
            case BuffObject.FIGHT_PROP_HP: return R.string.weapon_stat_HP;
            case BuffObject.FIGHT_PROP_ATK: return R.string.weapon_stat_atk;
            case BuffObject.FIGHT_PROP_DEF: return R.string.weapon_stat_DEF;
            case BuffObject.FIGHT_PROP_HP_P: return R.string.weapon_stat_HPP;
            case BuffObject.FIGHT_PROP_ATK_P: return R.string.weapon_stat_atkP;
            case BuffObject.FIGHT_PROP_DEF_P: return R.string.weapon_stat_DEFP;
            case BuffObject.FIGHT_PROP_EN_RECH: return R.string.weapon_stat_EnRechP;
            case BuffObject.FIGHT_PROP_ELE_MAS: return R.string.weapon_stat_EleMas;
            case BuffObject.FIGHT_PROP_CRIT_RATE: return R.string.weapon_stat_CritRateP;
            case BuffObject.FIGHT_PROP_CRIT_DMG: return R.string.weapon_stat_CritDMGP;
            case BuffObject.FIGHT_PROP_ANEMO_DMG: return R.string.weapon_stat_EleDMGP_Anemo;
            case BuffObject.FIGHT_PROP_CRYO_DMG: return R.string.weapon_stat_EleDMGP_Cryo;
            case BuffObject.FIGHT_PROP_DENDRO_DMG: return R.string.weapon_stat_EleDMGP_Dendor;
            case BuffObject.FIGHT_PROP_ELECTRO_DMG: return R.string.weapon_stat_EleDMGP_Electro;
            case BuffObject.FIGHT_PROP_GEO_DMG: return R.string.weapon_stat_EleDMGP_Geo;
            case BuffObject.FIGHT_PROP_HYDRO_DMG: return R.string.weapon_stat_EleDMGP_Hydro;
            case BuffObject.FIGHT_PROP_PYRO_DMG: return R.string.weapon_stat_EleDMGP_Pyro;
            case BuffObject.FIGHT_PROP_PHY_DMG: return R.string.weapon_stat_PhyDMGP;
            default:return R.string.unknown;
        }
    }

    public String LoadData(String inFile, Context context) {
        String tContents = "";
        try {
            File file = new File(context.getFilesDir()+"/"+inFile);
            InputStream stream = new FileInputStream(file);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }

        return tContents;

    }
}
