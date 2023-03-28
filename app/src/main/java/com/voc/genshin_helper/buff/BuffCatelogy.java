package com.voc.genshin_helper.buff;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.voc.genshin_helper.R;
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

    public static Integer[] charLvlBreak = {0,20,40,50,60,70,80,90,100}; //100 is for avoiding OutOfIndex
    public static Integer[] weaponLvlBreak = {0,20,40,50,60,70,80,90,100}; //100 is for avoiding OutOfIndex

    //get from https://ambr.top/cht/archive/avatar
    public String getCharNameByID(int id){
        switch (id){
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

    //get from https://ambr.top/cht/archive/weapon
    public String getWeaponById (int id){
        switch (id){
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
            case 14505 : return "jadefalls_splendor";
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
            case 15504 : return "kunwus_wyrmbane";
            case 15505 : return "primordial_jade_vista";
            case 15507 : return "Polar Star";
            case 15508 : return "Aqua Simulacra";
            case 15509 : return "Thundering Pulse";
            case 15511 : return "Hunter's Path";

            default: return "unknown";
        }
    }

    //get from https://ambr.top/cht/archive/reliquary
    public String getArtifactById (int id){
        switch (id){
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

    public String getStatusNameByLocaleName(String name){
        switch (name){
            case "基礎攻擊力" : return BuffObject.FIGHT_PROP_BASE_ATK;
            case "生命值" : return BuffObject.FIGHT_PROP_HP;
            case "攻擊力" : return BuffObject.FIGHT_PROP_ATK;
            case "防禦力" : return BuffObject.FIGHT_PROP_DEF;
            case "生命值百分比" : return BuffObject.FIGHT_PROP_HP_P;
            case "攻擊力百分比" : return BuffObject.FIGHT_PROP_ATK_P;
            case "防禦力百分比" : return BuffObject.FIGHT_PROP_DEF_P;
            case "暴擊率" : return BuffObject.FIGHT_PROP_CRIT_RATE;
            case "暴擊傷害" : return BuffObject.FIGHT_PROP_CRIT_DMG;
            case "元素充能效率" : return BuffObject.FIGHT_PROP_EN_RECH;
            case "治療加成" : return BuffObject.FIGHT_PROP_HEAL_P;
            case "元素精通" : return BuffObject.FIGHT_PROP_ELE_MAS;
            case "物理傷害加成" : return BuffObject.FIGHT_PROP_PHY_DMG;
            case "火元素傷害加成" : return BuffObject.FIGHT_PROP_PYRO_DMG;
            case "雷元素傷害加成" : return BuffObject.FIGHT_PROP_ELECTRO_DMG;
            case "水元素傷害加成" : return BuffObject.FIGHT_PROP_HYDRO_DMG;
            case "風元素傷害加成" : return BuffObject.FIGHT_PROP_ANEMO_DMG;
            case "冰元素傷害加成" : return BuffObject.FIGHT_PROP_CRYO_DMG;
            case "岩元素傷害加成" : return BuffObject.FIGHT_PROP_GEO_DMG;
            case "草元素傷害加成" : return BuffObject.FIGHT_PROP_DENDRO_DMG;
            default: return name;
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
