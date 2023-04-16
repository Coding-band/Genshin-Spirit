package com.voc.genshin_helper.data;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;

import com.voc.genshin_helper.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Locale;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */
public class ItemRss {

    /** IMPORTANT !
     * 九條裟羅 = Kujou Sara
     */

    SharedPreferences sharedPreferences;

    public static String IMG_FORMAT = ".webp";
    public static String SERVER_DOWNLOAD_ROOT = "https://github.com/Vocaloid2048/Genshin-Spirit/raw/release-master/rss/";
    public static String SERVER_REACT_ROOT = "https://vt.25u.com/genshin_spirit/";

    /**EDIT WHEN ADD NEW ITEMS*/
    // INCLUDED 3.0 CHAR. => 20221003
    // INCLUDED 3.1 CHAR. => 20221003
    // INCLUDED 3.2 CHAR. => 20221210
    // INCLUDED 3.3 CHAR. => 20221210
    // INCLUDED 3.4 CHAR. => 20230117
    // INCLUDED 3.5 CHAR. => 20230225
    public String[] charBirthName = {
            "Wanderer","Thoma","Diona","Rosaria","朝霧冰瀬",
            "Alhaitham","Beidou","Sangonomiya Kokomi","Bennett",
            "Qiqi","Yaoyao","Shenhe","Jean","Noelle","Kamisato Ayato",
            "Aloy","Dehya","Xiao","Yelan","Baizhu","Diluc",
            "Candace","Collei","Gorou","Yun Jin","Fischl",
            "Arataki Itto","Lisa","Venti","Yoimiya","Cyno","Raiden Shogun","Yae Miko","Kuki Shinobu",
            "Barbara","Kaveh","Kujou Sara","Hu Tao","Voc-夜芷冰","Tartaglia","Shikanoin Heizou","Klee","Yanfei",
            "Amber","Mika","Faruzan","Ningguang","Mona",
            "Diona","Razor","Albedo","Kamisato Ayaka",
            "Xingqiu","Xinyan","Sayu","Eula","Nahida","Kaedehara Kazuha",
            "Xiangling","Keqing","Sucrose","Kaeya",
            "Ganyu","Nilou","Layla","Dori","Tighnari","Zhongli"};
    public int[] charBirthDay = {
            3,9,18,24,26,
            11,14,22,29,
            3,6,10,14,21,26,
            4,7,17,20,25,30,
            3,8,18,21,27,
            1,6,9,21,23,26,27,28,
            5,9,14,15,19,20,24,27,28,
            10,11,20,26,31,
            7,9,13,28,
            9,16,19,25,27,29,
            2,20,26,30,
            2,3,19,21,29,31};
    public int[] charBirthMonth = {
            0,0,0,0,
            1,1,1,1,
            2,2,2,2,2,2,
            3,3,3,3,3,3,
            4,4,4,4,4,
            5,5,5,5,5,5,5,5,
            6,6,6,6,6,6,6,6,6,
            7,7,7,7,7,
            8,8,8,8,
            9,9,9,9,9,9,
            10,10,10,10,
            11,11,11,11,11,11};


    public String getLocaleName (String str,Context context) {
        /** Area Name */
        if (str.equals("Mondstadt")){return context.getString(R.string.mondstadt);}
        else if (str.equals("Liyue")){return context.getString(R.string.liyue);}
        else if (str.equals("Inazuma")){return context.getString(R.string.inazuma);}
        else if (str.equals("Nora Fortis")){return context.getString(R.string.nora_fortis);}
        else if (str.equals("Snezhnaya")){return context.getString(R.string.snezhnaya);}
        else if (str.equals("Sumeru")){return context.getString(R.string.sumeru);}
        else if (str.equals("Fontaine")){return context.getString(R.string.fontaine);}
        else if (str.equals("Natlan")){return context.getString(R.string.natlan);}
        else if (str.equals("Another World")){return context.getString(R.string.another_world);}
        /** Char's Role Name*/
        else if (str.equals("Main_DPS")){return context.getString(R.string.main_dps);}
        else if (str.equals("Support_DPS")){return context.getString(R.string.support_dps);}
        else if (str.equals("Utility")){return context.getString(R.string.utility);}
        // add in 20220411
        else if (str.equals("N/A")){return context.getString(R.string.unknown);}
        /** Sex Name */
        else if (str.equals("Female")){return context.getString(R.string.female);}
        else if (str.equals("Male")){return context.getString(R.string.male);}
        else if (str.equals("SET_BY_PLAYER")){return context.getString(R.string.set_by_player);}

        else {return str;}
    }

    public String getObtainCode(String str, Context context){
        switch (str){
            case "N/A" : return context.getString(R.string.obtain_no);
            case "FORGE_MISSION_NMDBJ" : return context.getString(R.string.obtain_forge_mission_nmdbj);
            case "LIMITED_WISH" : return context.getString(R.string.obtain_limited_wish);
            case "WISH" : return context.getString(R.string.obtain_wish);
            case "FIRST_GIFT" : return context.getString(R.string.obtain_first_gift);
            case "SHOP" : return context.getString(R.string.obtain_shop);
            case "CHEST_SHOP" : return context.getString(R.string.obtain_chest_shop);
            case "FORGE" : return context.getString(R.string.obtain_forge);
            case "CHAT_YMDJY" : return context.getString(R.string.obtain_chat_ymdjy);
            case "BATTLEPASS" : return context.getString(R.string.obtain_battlepass);
            case "WISH_CHEST" : return context.getString(R.string.obtain_wish_chest);
            case "EVENT" : return context.getString(R.string.obtain_event);
            case "FORGE_EVENT" : return context.getString(R.string.obtain_forge_event);
            case "WISH_EVENT_GLYJYJG" : return context.getString(R.string.obtain_wish_event_glyjyjg);
            case "FORGE_YHBH" : return context.getString(R.string.obtain_forge_yhbh);
            case "CHEST" : return context.getString(R.string.obtain_chest);
            case "FORGE_HLDW" : return context.getString(R.string.obtain_forge_hldw);
            case "WISH_EVENT_HDBBHJYJJ" : return context.getString(R.string.obtain_wish_event_hdbbhjyjj);
            case "FORGE_TBMY" : return context.getString(R.string.obtain_forge_tbmy);
            case "FORGE_YLYSWS" : return context.getString(R.string.obtain_forge_ylysws);
            case "CHESR_MD" : return context.getString(R.string.obtain_chesr_md);
            case "PLAYSTATION_LOGIN" : return context.getString(R.string.obtain_playstation_login);
            case "FORGE_LVL10_SJGW" : return context.getString(R.string.obtain_forge_lvl10_sjgw);
            case "PLAYSTATION_SP_GIFT" : return context.getString(R.string.obtain_playstation_sp_gift);
            case "NPC_DCDYHW" : return context.getString(R.string.obtain_npc_dcdyhw);
            case "CHESR_LY" : return context.getString(R.string.obtain_chesr_ly);
            case "CHEST_GWK" : return context.getString(R.string.obtain_chest_gwk);
            default: return str;

        }
    }


    public int[] getRareColorByName(int i) {
        switch (i){
            case 1 : return new int[]{R.drawable.bg_rare1_bg, R.drawable.bg_rare1_char, R.color.rare1,R.drawable.rare1_800x800};
            case 2 : return new int[]{R.drawable.bg_rare2_bg, R.drawable.bg_rare2_char, R.color.rare2,R.drawable.rare2_800x800};
            case 3 : return new int[]{R.drawable.bg_rare3_bg, R.drawable.bg_rare3_char, R.color.rare3,R.drawable.rare3_800x800};
            case 4 : return new int[]{R.drawable.bg_rare4_bg, R.drawable.bg_rare4_char, R.color.rare4,R.drawable.rare4_800x800};
            case 5 : return new int[]{R.drawable.bg_rare5_bg, R.drawable.bg_rare5_char, R.color.rare5,R.drawable.rare5_800x800};
            default: return new int[]{R.drawable.hu_tao_unknown, R.drawable.hu_tao_unknown, R.color.rare1,R.drawable.rare1_800x800};
        }
    }

    public int getRare2048ByInt(int i){
        switch (i){
            case 0 : return R.drawable.item_char_list_bg_circ_0s;
            case 1 : return R.drawable.item_char_list_bg_circ_1s;
            case 2 : return R.drawable.item_char_list_bg_circ_2s;
            case 3 : return R.drawable.item_char_list_bg_circ_3s;
            case 4 : return R.drawable.item_char_list_bg_circ_4s;
            case 5 : return R.drawable.item_char_list_bg_circ_5s;
            default : return R.drawable.item_char_list_bg_circ_0s;
        }
    }


    public int getWeaponTypeIMG (String str, Context context){
        if(str.equals("Bow") || str.equals(context.getString(R.string.bow))){return R.drawable.ico_bow;}
        else if(str.equals("Catalyst") || str.equals(context.getString(R.string.catalyst))){return R.drawable.ico_catalyst;}
        else if(str.equals("Claymore") || str.equals(context.getString(R.string.claymore))){return R.drawable.ico_claymore;}
        else if(str.equals("Polearm") || str.equals(context.getString(R.string.polearm))){return R.drawable.ico_polearm;}
        else if(str.equals("Sword") || str.equals(context.getString(R.string.sword))){return R.drawable.ico_sword;}
        else {return R.drawable.hu_tao_unknown;}
    }
    public int getDistrictIMG (String str){
        if(str.equals("Mondstadt")){return R.drawable.mondstadt_ico;}
        else if(str.equals("Liyue")){return R.drawable.liyue_ico;}
        else if(str.equals("Inazuma")){return R.drawable.inazuma_ico;}
        else if(str.equals("Sumeru")){return R.drawable.sumeru_ico;}
        else {return R.drawable.unknown;}
    }

    // add in 20220207
    public String[] getEnemyByName(String str, Context context){
        switch (str){
            case "Pyro Slime" : return new String[]{context.getString(R.string.pyro_slime),"/drawable/pyro_slime"+IMG_FORMAT};
            case "Large Pyro Slime" : return new String[]{context.getString(R.string.large_pyro_slime),"/drawable/large_pyro_slime"+IMG_FORMAT};
            case "Electro Slime" : return new String[]{context.getString(R.string.electro_slime),"/drawable/electro_slime"+IMG_FORMAT};
            case "Large Electro Slime" : return new String[]{context.getString(R.string.large_electro_slime),"/drawable/large_electro_slime"+IMG_FORMAT};
            case "Mutant Electro Slime" : return new String[]{context.getString(R.string.mutant_electro_slime),"/drawable/mutant_electro_slime"+IMG_FORMAT};
            case "Cryo Slime" : return new String[]{context.getString(R.string.cryo_slime),"/drawable/cryo_slime"+IMG_FORMAT};
            case "Large Cryo Slime" : return new String[]{context.getString(R.string.large_cryo_slime),"/drawable/large_cryo_slime"+IMG_FORMAT};
            case "Hydro Slime" : return new String[]{context.getString(R.string.hydro_slime),"/drawable/hydro_slime"+IMG_FORMAT};
            case "Large Hydro Slime" : return new String[]{context.getString(R.string.large_hydro_slime),"/drawable/large_hydro_slime"+IMG_FORMAT};
            case "Anemo Slime" : return new String[]{context.getString(R.string.anemo_slime),"/drawable/anemo_slime"+IMG_FORMAT};
            case "Large Anemo Slime" : return new String[]{context.getString(R.string.large_anemo_slime),"/drawable/large_anemo_slime"+IMG_FORMAT};
            case "Geo Slime" : return new String[]{context.getString(R.string.geo_slime),"/drawable/geo_slime"+IMG_FORMAT};
            case "Large Geo Slime" : return new String[]{context.getString(R.string.large_geo_slime),"/drawable/large_geo_slime"+IMG_FORMAT};
            case "Dendro Slime" : return new String[]{context.getString(R.string.dendro_slime),"/drawable/dendro_slime"+IMG_FORMAT};
            case "Large Dendro Slime" : return new String[]{context.getString(R.string.large_dendro_slime),"/drawable/large_dendro_slime"+IMG_FORMAT};
            case "Eye of the Storm" : return new String[]{context.getString(R.string.eye_of_the_storm),"/drawable/eye_of_the_storm"+IMG_FORMAT};
            case "Pyro Hypostasis" : return new String[]{context.getString(R.string.pyro_hypostasis),"/drawable/pyro_hypostasis"+IMG_FORMAT};
            case "Electro Hypostasis" : return new String[]{context.getString(R.string.electro_hypostasis),"/drawable/electro_hypostasis"+IMG_FORMAT};
            case "Cryo Hypostasis" : return new String[]{context.getString(R.string.cryo_hypostasis),"/drawable/cryo_hypostasis"+IMG_FORMAT};
            case "Hydro Hypostasis" : return new String[]{context.getString(R.string.hydro_hypostasis),"/drawable/hydro_hypostasis"+IMG_FORMAT};
            case "Anemo Hypostasis" : return new String[]{context.getString(R.string.anemo_hypostasis),"/drawable/anemo_hypostasis"+IMG_FORMAT};
            case "Geo Hypostasis" : return new String[]{context.getString(R.string.geo_hypostasis),"/drawable/geo_hypostasis"+IMG_FORMAT};
            case "Oceanid" : return new String[]{context.getString(R.string.oceanid),"/drawable/oceanid"+IMG_FORMAT};
            case "Hydro Specter" : return new String[]{context.getString(R.string.hydro_specter),"/drawable/hydro_specter"+IMG_FORMAT};
            case "Geo Specter" : return new String[]{context.getString(R.string.geo_specter),"/drawable/geo_specter"+IMG_FORMAT};
            case "Anemo Specter" : return new String[]{context.getString(R.string.anemo_specter),"/drawable/anemo_specter"+IMG_FORMAT};
            case "Cryo Specter" : return new String[]{context.getString(R.string.cryo_specter),"/drawable/cryo_specter"+IMG_FORMAT};
            case "Electro Specter" : return new String[]{context.getString(R.string.electro_specter),"/drawable/electro_specter"+IMG_FORMAT};
            case "Pyro Specter" : return new String[]{context.getString(R.string.pyro_specter),"/drawable/pyro_specter"+IMG_FORMAT};
            case "Thunder Manifestation" : return new String[]{context.getString(R.string.thunder_manifestation),"/drawable/thunder_manifestation"+IMG_FORMAT};
            case "Hilichurl" : return new String[]{context.getString(R.string.hilichurl),"/drawable/hilichurl"+IMG_FORMAT};
            case "Hilichurl Fighter" : return new String[]{context.getString(R.string.hilichurl_fighter),"/drawable/hilichurl_fighter"+IMG_FORMAT};
            case "Wooden Shield Hilichurl Guard" : return new String[]{context.getString(R.string.wooden_shield_hilichurl_guard),"/drawable/wooden_shield_hilichurl_guard"+IMG_FORMAT};
            case "Hilichurl Shooter" : return new String[]{context.getString(R.string.hilichurl_shooter),"/drawable/hilichurl_shooter"+IMG_FORMAT};
            case "Pyro Hilichurl Shooter" : return new String[]{context.getString(R.string.pyro_hilichurl_shooter),"/drawable/pyro_hilichurl_shooter"+IMG_FORMAT};
            case "Hilichurl Grenadier" : return new String[]{context.getString(R.string.hilichurl_grenadier),"/drawable/hilichurl_grenadier"+IMG_FORMAT};
            case "Hilichurl Berserker" : return new String[]{context.getString(R.string.hilichurl_berserker),"/drawable/hilichurl_berserker"+IMG_FORMAT};
            case "Cryo Hilichurl Shooter" : return new String[]{context.getString(R.string.cryo_hilichurl_shooter),"/drawable/cryo_hilichurl_shooter"+IMG_FORMAT};
            case "Electro Hilichurl Shooter" : return new String[]{context.getString(R.string.electro_hilichurl_shooter),"/drawable/electro_hilichurl_shooter"+IMG_FORMAT};
            case "Rock Shield Hilichurl Guard" : return new String[]{context.getString(R.string.rock_shield_hilichurl_guard),"/drawable/rock_shield_hilichurl_guard"+IMG_FORMAT};
            case "Cryo Hilichurl Grenadier" : return new String[]{context.getString(R.string.cryo_hilichurl_grenadier),"/drawable/cryo_hilichurl_grenadier"+IMG_FORMAT};
            case "Ice Shield Hilichurl Guard" : return new String[]{context.getString(R.string.ice_shield_hilichurl_guard),"/drawable/ice_shield_hilichurl_guard"+IMG_FORMAT};
            case "Unusual Hilichurl" : return new String[]{context.getString(R.string.unusual_hilichurl),"/drawable/unusual_hilichurl"+IMG_FORMAT};
            case "Electro Hilichurl Grenadier" : return new String[]{context.getString(R.string.electro_hilichurl_grenadier),"/drawable/electro_hilichurl_grenadier"+IMG_FORMAT};
            case "Wooden Shieldwall Mitachurl" : return new String[]{context.getString(R.string.wooden_shieldwall_mitachurl),"/drawable/wooden_shieldwall_mitachurl"+IMG_FORMAT};
            case "Blazing Axe Mitachurl" : return new String[]{context.getString(R.string.blazing_axe_mitachurl),"/drawable/blazing_axe_mitachurl"+IMG_FORMAT};
            case "Rock Shieldwall Mitachurl" : return new String[]{context.getString(R.string.rock_shieldwall_mitachurl),"/drawable/rock_shieldwall_mitachurl"+IMG_FORMAT};
            case "Frostarm Lawachurl" : return new String[]{context.getString(R.string.frostarm_lawachurl),"/drawable/frostarm_lawachurl"+IMG_FORMAT};
            case "Stonehide Lawachurl" : return new String[]{context.getString(R.string.stonehide_lawachurl),"/drawable/stonehide_lawachurl"+IMG_FORMAT};
            case "Thunderhelm Lawachurl" : return new String[]{context.getString(R.string.thunderhelm_lawachurl),"/drawable/thunderhelm_lawachurl"+IMG_FORMAT};
            case "Ice Shieldwall Mitachurl" : return new String[]{context.getString(R.string.ice_shieldwall_mitachurl),"/drawable/ice_shieldwall_mitachurl"+IMG_FORMAT};
            case "Crackling Axe Mitachurl" : return new String[]{context.getString(R.string.crackling_axe_mitachurl),"/drawable/crackling_axe_mitachurl"+IMG_FORMAT};
            case "Hydro Samachurl" : return new String[]{context.getString(R.string.hydro_samachurl),"/drawable/hydro_samachurl"+IMG_FORMAT};
            case "Dendro Samachurl" : return new String[]{context.getString(R.string.dendro_samachurl),"/drawable/dendro_samachurl"+IMG_FORMAT};
            case "Anemo Samachurl" : return new String[]{context.getString(R.string.anemo_samachurl),"/drawable/anemo_samachurl"+IMG_FORMAT};
            case "Geo Samachurl" : return new String[]{context.getString(R.string.geo_samachurl),"/drawable/geo_samachurl"+IMG_FORMAT};
            case "Cryo Samachurl" : return new String[]{context.getString(R.string.cryo_samachurl),"/drawable/cryo_samachurl"+IMG_FORMAT};
            case "Electro Samachurl" : return new String[]{context.getString(R.string.electro_samachurl),"/drawable/electro_samachurl"+IMG_FORMAT};
            case "Pyro Abyss Mage" : return new String[]{context.getString(R.string.pyro_abyss_mage),"/drawable/pyro_abyss_mage"+IMG_FORMAT};
            case "Cryo Abyss Mage" : return new String[]{context.getString(R.string.cryo_abyss_mage),"/drawable/cryo_abyss_mage"+IMG_FORMAT};
            case "Hydro Abyss Mage" : return new String[]{context.getString(R.string.hydro_abyss_mage),"/drawable/hydro_abyss_mage"+IMG_FORMAT};
            case "Electro Abyss Mage" : return new String[]{context.getString(R.string.electro_abyss_mage),"/drawable/electro_abyss_mage"+IMG_FORMAT};
            case "Abyss Herald: Wicked Torrents" : return new String[]{context.getString(R.string.abyss_herald_wicked_torrents),"/drawable/abyss_herald_wicked_torrents"+IMG_FORMAT};
            case "Abyss Lector: Violet Lightning" : return new String[]{context.getString(R.string.abyss_lector_violet_lightning),"/drawable/abyss_lector_violet_lightning"+IMG_FORMAT};
            case "Abyss Lector: Fathomless Flames" : return new String[]{context.getString(R.string.abyss_lector_fathomless_flames),"/drawable/abyss_lector_fathomless_flames"+IMG_FORMAT};
            case "Rockfond Rifthound Whelp" : return new String[]{context.getString(R.string.rockfond_rifthound_whelp),"/drawable/rockfond_rifthound_whelp"+IMG_FORMAT};
            case "Thundercraven Rifthound Whelp" : return new String[]{context.getString(R.string.thundercraven_rifthound_whelp),"/drawable/thundercraven_rifthound_whelp"+IMG_FORMAT};
            case "Rockfond Rifthound" : return new String[]{context.getString(R.string.rockfond_rifthound),"/drawable/rockfond_rifthound"+IMG_FORMAT};
            case "Thundercraven Rifthound" : return new String[]{context.getString(R.string.thundercraven_rifthound),"/drawable/thundercraven_rifthound"+IMG_FORMAT};
            case "Beginning : Golden Wolflord" : return new String[]{context.getString(R.string.beginning_golden_wolflord),"/drawable/golden_wolflord"+IMG_FORMAT};
            case "Golden Wolflord" : return new String[]{context.getString(R.string.golden_wolflord),"/drawable/golden_wolflord"+IMG_FORMAT};
            case "Head-Broken : Golden Wolflord" : return new String[]{context.getString(R.string.headbroken_golden_wolflord),"/drawable/golden_wolflord"+IMG_FORMAT};
            case "Shadowy Husk: Standard Bearer" : return new String[]{context.getString(R.string.shadowy_husk_standard_bearer),"/drawable/shadowy_husk_standard_bearer"+IMG_FORMAT};
            case "Shadowy Husk: Line Breaker" : return new String[]{context.getString(R.string.shadowy_husk_line_breaker),"/drawable/shadowy_husk_line_breaker"+IMG_FORMAT};
            case "Shadowy Husk: Defender" : return new String[]{context.getString(R.string.shadowy_husk_defender),"/drawable/shadowy_husk_defender"+IMG_FORMAT};
            case "Fatui Skirmisher - Cryogunner Legionnaire" : return new String[]{context.getString(R.string.fatui_skirmisher_cryogunner_legionnaire),"/drawable/fatui_skirmisher_cryogunner_legionnaire"+IMG_FORMAT};
            case "Fatui Skirmisher - Hydrogunner Legionnaire" : return new String[]{context.getString(R.string.fatui_skirmisher_hydrogunner_legionnaire),"/drawable/fatui_skirmisher_hydrogunner_legionnaire"+IMG_FORMAT};
            case "Fatui Skirmisher - Electrohammer Vanguard" : return new String[]{context.getString(R.string.fatui_skirmisher_electrohammer_vanguard),"/drawable/fatui_skirmisher_electrohammer_vanguard"+IMG_FORMAT};
            case "Fatui Skirmisher - Geochanter Bracer" : return new String[]{context.getString(R.string.fatui_skirmisher_geochanter_bracer),"/drawable/fatui_skirmisher_geochanter_bracer"+IMG_FORMAT};
            case "Fatui Skirmisher - Anemoboxer Vanguard" : return new String[]{context.getString(R.string.fatui_skirmisher_anemoboxer_vanguard),"/drawable/fatui_skirmisher_anemoboxer_vanguard"+IMG_FORMAT};
            case "Fatui Skirmisher - Pyroslinger Bracer" : return new String[]{context.getString(R.string.fatui_skirmisher_pyroslinger_bracer),"/drawable/fatui_skirmisher_pyroslinger_bracer"+IMG_FORMAT};
            case "Fatui Pyro Agent" : return new String[]{context.getString(R.string.fatui_pyro_agent),"/drawable/fatui_pyro_agent"+IMG_FORMAT};
            case "Fatui Electro Cicin Mage" : return new String[]{context.getString(R.string.fatui_electro_cicin_mage),"/drawable/fatui_electro_cicin_mage"+IMG_FORMAT};
            case "Fatui Cryo Cicin Mage" : return new String[]{context.getString(R.string.fatui_cryo_cicin_mage),"/drawable/fatui_cryo_cicin_mage"+IMG_FORMAT};
            case "Mirror Maiden" : return new String[]{context.getString(R.string.mirror_maiden),"/drawable/mirror_maiden"+IMG_FORMAT};
            case "Ruin Guard" : return new String[]{context.getString(R.string.ruin_guard),"/drawable/ruin_guard"+IMG_FORMAT};
            case "Ruin Hunter" : return new String[]{context.getString(R.string.ruin_hunter),"/drawable/ruin_hunter"+IMG_FORMAT};
            case "Ruin Grader" : return new String[]{context.getString(R.string.ruin_grader),"/drawable/ruin_grader"+IMG_FORMAT};
            case "Ruin Cruiser" : return new String[]{context.getString(R.string.ruin_cruiser),"/drawable/ruin_cruiser"+IMG_FORMAT};
            case "Ruin Destroyer" : return new String[]{context.getString(R.string.ruin_destroyer),"/drawable/ruin_destroyer"+IMG_FORMAT};
            case "Ruin Defender" : return new String[]{context.getString(R.string.ruin_defender),"/drawable/ruin_defender"+IMG_FORMAT};
            case "Ruin Scout" : return new String[]{context.getString(R.string.ruin_scout),"/drawable/ruin_scout"+IMG_FORMAT};
            case "Perpetual Mechanical Array" : return new String[]{context.getString(R.string.perpetual_mechanical_array),"/drawable/perpetual_mechanical_array"+IMG_FORMAT};
            case "Stunned Perpetual Mechanical Array" : return new String[]{context.getString(R.string.stunned_perpetual_mechanical_array),"/drawable/perpetual_mechanical_array"+IMG_FORMAT};
            case "Treasure Hoarders - Liuliu" : return new String[]{context.getString(R.string.treasure_hoarders_liuliu),"/drawable/treasure_hoarders_liuliu"+IMG_FORMAT};
            case "Treasure Hoarders - Scout" : return new String[]{context.getString(R.string.treasure_hoarders_scout),"/drawable/treasure_hoarders_scout"+IMG_FORMAT};
            case "Treasure Hoarders: Pyro Potioneer" : return new String[]{context.getString(R.string.treasure_hoarders_pyro_potioneer),"/drawable/treasure_hoarders_pyro_potioneer"+IMG_FORMAT};
            case "Treasure Hoarders: Hydro Potioneer" : return new String[]{context.getString(R.string.treasure_hoarders_hydro_potioneer),"/drawable/treasure_hoarders_hydro_potioneer"+IMG_FORMAT};
            case "Treasure Hoarders: Electro Potioneer" : return new String[]{context.getString(R.string.treasure_hoarders_electro_potioneer),"/drawable/treasure_hoarders_electro_potioneer"+IMG_FORMAT};
            case "Treasure Hoarders: Cryo Potioneer" : return new String[]{context.getString(R.string.treasure_hoarders_cryo_potioneer),"/drawable/treasure_hoarders_cryo_potioneer"+IMG_FORMAT};
            case "Treasure Hoarders: Handyman" : return new String[]{context.getString(R.string.treasure_hoarders_handyman),"/drawable/treasure_hoarders_handyman"+IMG_FORMAT};
            case "Treasure Hoarders - Raptor" : return new String[]{context.getString(R.string.treasure_hoarders_raptor),"/drawable/treasure_hoarders_raptor"+IMG_FORMAT};
            case "Treasure Hoarders - Marksman" : return new String[]{context.getString(R.string.treasure_hoarders_marksman),"/drawable/treasure_hoarders_marksman"+IMG_FORMAT};
            case "Treasure Hoarders - Carmen" : return new String[]{context.getString(R.string.treasure_hoarders_carmen),"/drawable/treasure_hoarders_carmen"+IMG_FORMAT};
            case "Treasure Hoarders: Gravedigger" : return new String[]{context.getString(R.string.treasure_hoarders_gravedigger),"/drawable/treasure_hoarders_gravedigger"+IMG_FORMAT};
            case "Treasure Hoarders - Seaman" : return new String[]{context.getString(R.string.treasure_hoarders_seaman),"/drawable/treasure_hoarders_seaman"+IMG_FORMAT};
            case "Treasure Hoarders - Boss" : return new String[]{context.getString(R.string.treasure_hoarders_boss),"/drawable/treasure_hoarders_boss"+IMG_FORMAT};
            case "Millelith Soldier" : return new String[]{context.getString(R.string.millelith_soldier),"/drawable/millelith_soldier"+IMG_FORMAT};
            case "Millelith Sergeant" : return new String[]{context.getString(R.string.millelith_sergeant),"/drawable/millelith_sergeant"+IMG_FORMAT};
            case "Treasure Hoarders - Pugilist" : return new String[]{context.getString(R.string.treasure_hoarders_pugilist),"/drawable/treasure_hoarders_pugilist"+IMG_FORMAT};
            case "Treasure Hoarders - Crusher" : return new String[]{context.getString(R.string.treasure_hoarders_crusher),"/drawable/treasure_hoarders_crusher"+IMG_FORMAT};
            case "Nobushi: Jintouban" : return new String[]{context.getString(R.string.nobushi_jintouban),"/drawable/nobushi_jintouban"+IMG_FORMAT};
            case "Nobushi: Hitsukeban" : return new String[]{context.getString(R.string.nobushi_hitsukeban),"/drawable/nobushi_hitsukeban"+IMG_FORMAT};
            case "Nobushi: Kikouban" : return new String[]{context.getString(R.string.nobushi_kikouban),"/drawable/nobushi_kikouban"+IMG_FORMAT};
            case "Maguu Kenki" : return new String[]{context.getString(R.string.maguu_kenki),"/drawable/maguu_kenki"+IMG_FORMAT};
            case "Kairagi: Dancing Thunder" : return new String[]{context.getString(R.string.kairagi_dancing_thunder),"/drawable/kairagi_dancing_thunder"+IMG_FORMAT};
            case "Kairagi: Fiery Might" : return new String[]{context.getString(R.string.kairagi_fiery_might),"/drawable/kairagi_fiery_might"+IMG_FORMAT};
            case "Cryo Whopperflower" : return new String[]{context.getString(R.string.cryo_whopperflower),"/drawable/cryo_whopperflower"+IMG_FORMAT};
            case "Pyro Whopperflower" : return new String[]{context.getString(R.string.pyro_whopperflower),"/drawable/pyro_whopperflower"+IMG_FORMAT};
            case "Electro Whopperflower" : return new String[]{context.getString(R.string.electro_whopperflower),"/drawable/electro_whopperflower"+IMG_FORMAT};
            case "Stunned Pyro Whopperflower" : return new String[]{context.getString(R.string.stunned_pyro_whopperflower),"/drawable/cryo_whopperflower"+IMG_FORMAT};
            case "Stunned Electro Whopperflower" : return new String[]{context.getString(R.string.stunned_electro_whopperflower),"/drawable/pyro_whopperflower"+IMG_FORMAT};
            case "Stunned Cryo Whopperflower" : return new String[]{context.getString(R.string.stunned_cryo_whopperflower),"/drawable/electro_whopperflower"+IMG_FORMAT};
            case "Cryo Regisvine" : return new String[]{context.getString(R.string.cryo_regisvine),"/drawable/cryo_regisvine"+IMG_FORMAT};
            case "Pyro Regisvine" : return new String[]{context.getString(R.string.pyro_regisvine),"/drawable/pyro_regisvine"+IMG_FORMAT};
            case "Stunned Cryo Regisvine" : return new String[]{context.getString(R.string.stunned_cryo_regisvine),"/drawable/cryo_regisvine"+IMG_FORMAT};
            case "Stunned Pyro Regisvine" : return new String[]{context.getString(R.string.stunned_pyro_regisvine),"/drawable/pyro_regisvine"+IMG_FORMAT};
            case "Geovishap Hatchling" : return new String[]{context.getString(R.string.geovishap_hatchling),"/drawable/geovishap_hatchling"+IMG_FORMAT};
            case "Geovishap" : return new String[]{context.getString(R.string.geovishap),"/drawable/geovishap"+IMG_FORMAT};
            case "Pyro-infused Geovishap" : return new String[]{context.getString(R.string.pyroinfused_geovishap),"/drawable/geovishap"+IMG_FORMAT};
            case "Electro-infused Geovishap" : return new String[]{context.getString(R.string.electroinfused_geovishap),"/drawable/geovishap"+IMG_FORMAT};
            case "Cryo-infused Geovishap" : return new String[]{context.getString(R.string.cryoinfused_geovishap),"/drawable/geovishap"+IMG_FORMAT};
            case "Hydro-infused Geovishap" : return new String[]{context.getString(R.string.hydroinfused_geovishap),"/drawable/geovishap"+IMG_FORMAT};
            case "Primo Geovishap" : return new String[]{context.getString(R.string.primo_geovishap),"/drawable/primo_geovishap"+IMG_FORMAT};
            case "Primordial Bathysmal Vishap" : return new String[]{context.getString(R.string.primordial_bathysmal_vishap),"/drawable/primordial_bathysmal_vishap"+IMG_FORMAT};
            case "Rimebiter Bathysmal Vishap" : return new String[]{context.getString(R.string.rimebiter_bathysmal_vishap),"/drawable/rimebiter_bathysmal_vishap"+IMG_FORMAT};
            case "Bolteater Bathysmal Vishap" : return new String[]{context.getString(R.string.bolteater_bathysmal_vishap),"/drawable/bolteater_bathysmal_vishap"+IMG_FORMAT};
            case "Electro Bathysmal Vishap" : return new String[]{context.getString(R.string.electro_bathysmal_vishap),"/drawable/electro_bathysmal_vishap"+IMG_FORMAT};
            case "Cyro Bathysmal Vishap" : return new String[]{context.getString(R.string.cyro_bathysmal_vishap),"/drawable/cyro_bathysmal_vishap"+IMG_FORMAT};
            case "Electro Cicin" : return new String[]{context.getString(R.string.electro_cicin),"/drawable/electro_cicin"+IMG_FORMAT};
            case "Hydro Cicin" : return new String[]{context.getString(R.string.hydro_cicin),"/drawable/hydro_cicin"+IMG_FORMAT};
            case "Cryo Cicin" : return new String[]{context.getString(R.string.cryo_cicin),"/drawable/cryo_cicin"+IMG_FORMAT};
            case "Stormterror" : return new String[]{context.getString(R.string.stormterror),"/drawable/stormterror"+IMG_FORMAT};
            case "Lupus Boreas" : return new String[]{context.getString(R.string.lupus_boreas),"/drawable/lupus_boreas"+IMG_FORMAT};
            case "Phase 1 Childe" : return new String[]{context.getString(R.string.phase_1_childe),"/drawable/childe"+IMG_FORMAT};
            case "Stunned Phase 1 Childe " : return new String[]{context.getString(R.string.stunned_phase_1_childe_),"/drawable/childe"+IMG_FORMAT};
            case "Phase 2 Childe" : return new String[]{context.getString(R.string.phase_2_childe),"/drawable/childe"+IMG_FORMAT};
            case "Stunned Phase 2 Childe" : return new String[]{context.getString(R.string.stunned_phase_2_childe),"/drawable/childe"+IMG_FORMAT};
            case "Phase 3 Childe" : return new String[]{context.getString(R.string.phase_3_childe),"/drawable/childe"+IMG_FORMAT};
            case "Azhdaha" : return new String[]{context.getString(R.string.azhdaha),"/drawable/azhdaha"+IMG_FORMAT};
            case "Pyro Azhdaha" : return new String[]{context.getString(R.string.pyro_azhdaha),"/drawable/azhdaha"+IMG_FORMAT};
            case "Hydro Azhdaha" : return new String[]{context.getString(R.string.hydro_azhdaha),"/drawable/azhdaha"+IMG_FORMAT};
            case "Fire-Cryo Azhdaha" : return new String[]{context.getString(R.string.firecryo_azhdaha),"/drawable/azhdaha"+IMG_FORMAT};
            case "Fire-Electro Azhdaha" : return new String[]{context.getString(R.string.fireelectro_azhdaha),"/drawable/azhdaha"+IMG_FORMAT};
            case "Hydro-Cryo Azhdaha" : return new String[]{context.getString(R.string.hydrocryo_azhdaha),"/drawable/azhdaha"+IMG_FORMAT};
            case "Hydro-Electro Azhdaha" : return new String[]{context.getString(R.string.hydroelectro_azhdaha),"/drawable/azhdaha"+IMG_FORMAT};
            case "Phase 1 La Signora" : return new String[]{context.getString(R.string.phase_1_la_signora),"/drawable/la_signora"+IMG_FORMAT};
            case "Phase 2 La Signora" : return new String[]{context.getString(R.string.phase_2_la_signora),"/drawable/la_signora"+IMG_FORMAT};
            case "Magatsu Mitake Narukami no Mikoto" : return new String[]{context.getString(R.string.magatsu_mitake_narukami_no_mikoto),"/drawable/magatsu_mitake_narukami_no_mikoto"+IMG_FORMAT};
            case "Hydro Mimic Boar" : return new String[]{context.getString(R.string.hydro_mimic_boar),"/drawable/hydro_mimic_boar"+IMG_FORMAT};
            case "Hydro Mimic Crane" : return new String[]{context.getString(R.string.hydro_mimic_crane),"/drawable/hydro_mimic_crane"+IMG_FORMAT};
            case "Hydro Mimic Crab" : return new String[]{context.getString(R.string.hydro_mimic_crab),"/drawable/hydro_mimic_crab"+IMG_FORMAT};
            case "Hydro Mimic Finch" : return new String[]{context.getString(R.string.hydro_mimic_finch),"/drawable/hydro_mimic_finch"+IMG_FORMAT};
            case "Hydro Mimic Mallard" : return new String[]{context.getString(R.string.hydro_mimic_mallard),"/drawable/hydro_mimic_mallard"+IMG_FORMAT};
            case "Hydro Mimic Ferret" : return new String[]{context.getString(R.string.hydro_mimic_ferret),"/drawable/hydro_mimic_ferret"+IMG_FORMAT};
            case "Hydro Mimic Frog" : return new String[]{context.getString(R.string.hydro_mimic_frog),"/drawable/hydro_mimic_frog"+IMG_FORMAT};
            case "Hydro Mimic Raptor" : return new String[]{context.getString(R.string.hydro_mimic_raptor),"/drawable/hydro_mimic_raptor"+IMG_FORMAT};
            case "Shogunate Infantry" : return new String[]{context.getString(R.string.shogunate_infantry),"/drawable/shogunate_infantry"+IMG_FORMAT};
            case "Shogunate Infantry Captain" : return new String[]{context.getString(R.string.shogunate_infantry_captain),"/drawable/shogunate_infantry_captain"+IMG_FORMAT};
            case "Sangonomiya Cohort" : return new String[]{context.getString(R.string.sangonomiya_cohort),"/drawable/sangonomiya_cohort"+IMG_FORMAT};
            case "Yoriki Samurai" : return new String[]{context.getString(R.string.yoriki_samurai),"/drawable/yoriki_samurai"+IMG_FORMAT};
            case "Ochimusha: Ensorcelled Thunder" : return new String[]{context.getString(R.string.ochimusha_ensorcelled_thunder),"/drawable/ochimusha_ensorcelled_thunder"+IMG_FORMAT};
            case "Ochimusha: Cankered Flame" : return new String[]{context.getString(R.string.ochimusha_cankered_flame),"/drawable/ochimusha_cankered_flame"+IMG_FORMAT};
            case "Maguu Kenki: Lone Gale" : return new String[]{context.getString(R.string.maguu_kenki_lone_gale),"/drawable/maguu_kenki_lone_gale"+IMG_FORMAT};
            case "Maguu Kenki: Galloping Frost" : return new String[]{context.getString(R.string.maguu_kenki_galloping_frost),"/drawable/maguu_kenki_galloping_frost"+IMG_FORMAT};
            case "Maguu Kenki: Mask of Terror" : return new String[]{context.getString(R.string.maguu_kenki_mask_of_terror),"/drawable/maguu_kenki_mask_of_terror"+IMG_FORMAT};
            case "The Great Snowboar King" : return new String[]{context.getString(R.string.the_great_snowboar_king),"/drawable/the_great_snowboar_king"+IMG_FORMAT};

            default : return new String[] {context.getString(R.string.unknown),"/drawable/hu_tao_unknown"+IMG_FORMAT};
        }
    }

    // add in 20220207
    public String getEnemyByFileName(String str){
        switch (str){
            case "pyro_slime" : return "Pyro Slime";
            case "large_pyro_slime" : return "Large Pyro Slime";
            case "electro_slime" : return "Electro Slime";
            case "large_electro_slime" : return "Large Electro Slime";
            case "mutant_electro_slime" : return "Mutant Electro Slime";
            case "cryo_slime" : return "Cryo Slime";
            case "large_cryo_slime" : return "Large Cryo Slime";
            case "hydro_slime" : return "Hydro Slime";
            case "large_hydro_slime" : return "Large Hydro Slime";
            case "anemo_slime" : return "Anemo Slime";
            case "large_anemo_slime" : return "Large Anemo Slime";
            case "geo_slime" : return "Geo Slime";
            case "large_geo_slime" : return "Large Geo Slime";
            case "dendro_slime" : return "Dendro Slime";
            case "large_dendro_slime" : return "Large Dendro Slime";
            case "eye_of_the_storm" : return "Eye of the Storm";
            case "pyro_hypostasis" : return "Pyro Hypostasis";
            case "electro_hypostasis" : return "Electro Hypostasis";
            case "cryo_hypostasis" : return "Cryo Hypostasis";
            case "hydro_hypostasis" : return "Hydro Hypostasis";
            case "anemo_hypostasis" : return "Anemo Hypostasis";
            case "geo_hypostasis" : return "Geo Hypostasis";
            case "oceanid" : return "Oceanid";
            case "hydro_specter" : return "Hydro Specter";
            case "geo_specter" : return "Geo Specter";
            case "anemo_specter" : return "Anemo Specter";
            case "cryo_specter" : return "Cryo Specter";
            case "electro_specter" : return "Electro Specter";
            case "pyro_specter" : return "Pyro Specter";
            case "thunder_manifestation" : return "Thunder Manifestation";
            case "hilichurl" : return "Hilichurl";
            case "hilichurl_fighter" : return "Hilichurl Fighter";
            case "wooden_shield_hilichurl_guard" : return "Wooden Shield Hilichurl Guard";
            case "hilichurl_shooter" : return "Hilichurl Shooter";
            case "pyro_hilichurl_shooter" : return "Pyro Hilichurl Shooter";
            case "hilichurl_grenadier" : return "Hilichurl Grenadier";
            case "hilichurl_berserker" : return "Hilichurl Berserker";
            case "cryo_hilichurl_shooter" : return "Cryo Hilichurl Shooter";
            case "electro_hilichurl_shooter" : return "Electro Hilichurl Shooter";
            case "rock_shield_hilichurl_guard" : return "Rock Shield Hilichurl Guard";
            case "cryo_hilichurl_grenadier" : return "Cryo Hilichurl Grenadier";
            case "ice_shield_hilichurl_guard" : return "Ice Shield Hilichurl Guard";
            case "unusual_hilichurl" : return "Unusual Hilichurl";
            case "electro_hilichurl_grenadier" : return "Electro Hilichurl Grenadier";
            case "wooden_shieldwall_mitachurl" : return "Wooden Shieldwall Mitachurl";
            case "blazing_axe_mitachurl" : return "Blazing Axe Mitachurl";
            case "rock_shieldwall_mitachurl" : return "Rock Shieldwall Mitachurl";
            case "frostarm_lawachurl" : return "Frostarm Lawachurl";
            case "stonehide_lawachurl" : return "Stonehide Lawachurl";
            case "thunderhelm_lawachurl" : return "Thunderhelm Lawachurl";
            case "ice_shieldwall_mitachurl" : return "Ice Shieldwall Mitachurl";
            case "crackling_axe_mitachurl" : return "Crackling Axe Mitachurl";
            case "hydro_samachurl" : return "Hydro Samachurl";
            case "dendro_samachurl" : return "Dendro Samachurl";
            case "anemo_samachurl" : return "Anemo Samachurl";
            case "geo_samachurl" : return "Geo Samachurl";
            case "cryo_samachurl" : return "Cryo Samachurl";
            case "electro_samachurl" : return "Electro Samachurl";
            case "pyro_abyss_mage" : return "Pyro Abyss Mage";
            case "cryo_abyss_mage" : return "Cryo Abyss Mage";
            case "hydro_abyss_mage" : return "Hydro Abyss Mage";
            case "electro_abyss_mage" : return "Electro Abyss Mage";
            case "abyss_herald_wicked_torrents" : return "Abyss Herald: Wicked Torrents";
            case "abyss_lector_violet_lightning" : return "Abyss Lector: Violet Lightning";
            case "abyss_lector_fathomless_flames" : return "Abyss Lector: Fathomless Flames";
            case "rockfond_rifthound_whelp" : return "Rockfond Rifthound Whelp";
            case "thundercraven_rifthound_whelp" : return "Thundercraven Rifthound Whelp";
            case "rockfond_rifthound" : return "Rockfond Rifthound";
            case "thundercraven_rifthound" : return "Thundercraven Rifthound";
            case "beginning_golden_wolflord" : return "Beginning : Golden Wolflord";
            case "golden_wolflord" : return "Golden Wolflord";
            case "headbroken_golden_wolflord" : return "Head-Broken : Golden Wolflord";
            case "shadowy_husk_standard_bearer" : return "Shadowy Husk: Standard Bearer";
            case "shadowy_husk_line_breaker" : return "Shadowy Husk: Line Breaker";
            case "shadowy_husk_defender" : return "Shadowy Husk: Defender";
            case "fatui_skirmisher_cryogunner_legionnaire" : return "Fatui Skirmisher - Cryogunner Legionnaire";
            case "fatui_skirmisher_hydrogunner_legionnaire" : return "Fatui Skirmisher - Hydrogunner Legionnaire";
            case "fatui_skirmisher_electrohammer_vanguard" : return "Fatui Skirmisher - Electrohammer Vanguard";
            case "fatui_skirmisher_geochanter_bracer" : return "Fatui Skirmisher - Geochanter Bracer";
            case "fatui_skirmisher_anemoboxer_vanguard" : return "Fatui Skirmisher - Anemoboxer Vanguard";
            case "fatui_skirmisher_pyroslinger_bracer" : return "Fatui Skirmisher - Pyroslinger Bracer";
            case "fatui_pyro_agent" : return "Fatui Pyro Agent";
            case "fatui_electro_cicin_mage" : return "Fatui Electro Cicin Mage";
            case "fatui_cryo_cicin_mage" : return "Fatui Cryo Cicin Mage";
            case "mirror_maiden" : return "Mirror Maiden";
            case "ruin_guard" : return "Ruin Guard";
            case "ruin_hunter" : return "Ruin Hunter";
            case "ruin_grader" : return "Ruin Grader";
            case "ruin_cruiser" : return "Ruin Cruiser";
            case "ruin_destroyer" : return "Ruin Destroyer";
            case "ruin_defender" : return "Ruin Defender";
            case "ruin_scout" : return "Ruin Scout";
            case "perpetual_mechanical_array" : return "Perpetual Mechanical Array";
            case "stunned_perpetual_mechanical_array" : return "Stunned Perpetual Mechanical Array";
            case "treasure_hoarders_liuliu" : return "Treasure Hoarders - Liuliu";
            case "treasure_hoarders_scout" : return "Treasure Hoarders - Scout";
            case "treasure_hoarders_pyro_potioneer" : return "Treasure Hoarders: Pyro Potioneer";
            case "treasure_hoarders_hydro_potioneer" : return "Treasure Hoarders: Hydro Potioneer";
            case "treasure_hoarders_electro_potioneer" : return "Treasure Hoarders: Electro Potioneer";
            case "treasure_hoarders_cryo_potioneer" : return "Treasure Hoarders: Cryo Potioneer";
            case "treasure_hoarders_handyman" : return "Treasure Hoarders: Handyman";
            case "treasure_hoarders_raptor" : return "Treasure Hoarders - Raptor";
            case "treasure_hoarders_marksman" : return "Treasure Hoarders - Marksman";
            case "treasure_hoarders_carmen" : return "Treasure Hoarders - Carmen";
            case "treasure_hoarders_gravedigger" : return "Treasure Hoarders: Gravedigger";
            case "treasure_hoarders_seaman" : return "Treasure Hoarders - Seaman";
            case "treasure_hoarders_boss" : return "Treasure Hoarders - Boss";
            case "millelith_soldier" : return "Millelith Soldier";
            case "millelith_sergeant" : return "Millelith Sergeant";
            case "treasure_hoarders_pugilist" : return "Treasure Hoarders - Pugilist";
            case "treasure_hoarders_crusher" : return "Treasure Hoarders - Crusher";
            case "nobushi_jintouban" : return "Nobushi: Jintouban";
            case "nobushi_hitsukeban" : return "Nobushi: Hitsukeban";
            case "nobushi_kikouban" : return "Nobushi: Kikouban";
            case "maguu_kenki" : return "Maguu Kenki";
            case "kairagi_dancing_thunder" : return "Kairagi: Dancing Thunder";
            case "kairagi_fiery_might" : return "Kairagi: Fiery Might";
            case "cryo_whopperflower" : return "Cryo Whopperflower";
            case "pyro_whopperflower" : return "Pyro Whopperflower";
            case "electro_whopperflower" : return "Electro Whopperflower";
            case "stunned_pyro_whopperflower" : return "Stunned Pyro Whopperflower";
            case "stunned_electro_whopperflower" : return "Stunned Electro Whopperflower";
            case "stunned_cryo_whopperflower" : return "Stunned Cryo Whopperflower";
            case "cryo_regisvine" : return "Cryo Regisvine";
            case "pyro_regisvine" : return "Pyro Regisvine";
            case "stunned_cryo_regisvine" : return "Stunned Cryo Regisvine";
            case "stunned_pyro_regisvine" : return "Stunned Pyro Regisvine";
            case "geovishap_hatchling" : return "Geovishap Hatchling";
            case "geovishap" : return "Geovishap";
            case "pyroinfused_geovishap" : return "Pyro-infused Geovishap";
            case "electroinfused_geovishap" : return "Electro-infused Geovishap";
            case "cryoinfused_geovishap" : return "Cryo-infused Geovishap";
            case "hydroinfused_geovishap" : return "Hydro-infused Geovishap";
            case "primo_geovishap" : return "Primo Geovishap";
            case "primordial_bathysmal_vishap" : return "Primordial Bathysmal Vishap";
            case "rimebiter_bathysmal_vishap" : return "Rimebiter Bathysmal Vishap";
            case "bolteater_bathysmal_vishap" : return "Bolteater Bathysmal Vishap";
            case "electro_bathysmal_vishap" : return "Electro Bathysmal Vishap";
            case "cyro_bathysmal_vishap" : return "Cyro Bathysmal Vishap";
            case "electro_cicin" : return "Electro Cicin";
            case "hydro_cicin" : return "Hydro Cicin";
            case "cryo_cicin" : return "Cryo Cicin";
            case "stormterror" : return "Stormterror";
            case "lupus_boreas" : return "Lupus Boreas";
            case "phase_1_childe" : return "Phase 1 Childe";
            case "stunned_phase_1_childe_" : return "Stunned Phase 1 Childe ";
            case "phase_2_childe" : return "Phase 2 Childe";
            case "stunned_phase_2_childe" : return "Stunned Phase 2 Childe";
            case "phase_3_childe" : return "Phase 3 Childe";
            case "azhdaha" : return "Azhdaha";
            case "pyro_azhdaha" : return "Pyro Azhdaha";
            case "hydro_azhdaha" : return "Hydro Azhdaha";
            case "firecryo_azhdaha" : return "Fire-Cryo Azhdaha";
            case "fireelectro_azhdaha" : return "Fire-Electro Azhdaha";
            case "hydrocryo_azhdaha" : return "Hydro-Cryo Azhdaha";
            case "hydroelectro_azhdaha" : return "Hydro-Electro Azhdaha";
            case "phase_1_la_signora" : return "Phase 1 La Signora";
            case "phase_2_la_signora" : return "Phase 2 La Signora";
            case "magatsu_mitake_narukami_no_mikoto" : return "Magatsu Mitake Narukami no Mikoto";
            case "hydro_mimic_boar" : return "Hydro Mimic Boar";
            case "hydro_mimic_crane" : return "Hydro Mimic Crane";
            case "hydro_mimic_crab" : return "Hydro Mimic Crab";
            case "hydro_mimic_finch" : return "Hydro Mimic Finch";
            case "hydro_mimic_mallard" : return "Hydro Mimic Mallard";
            case "hydro_mimic_ferret" : return "Hydro Mimic Ferret";
            case "hydro_mimic_frog" : return "Hydro Mimic Frog";
            case "hydro_mimic_raptor" : return "Hydro Mimic Raptor";
            case "shogunate_infantry" : return "Shogunate Infantry";
            case "shogunate_infantry_captain" : return "Shogunate Infantry Captain";
            case "sangonomiya_cohort" : return "Sangonomiya Cohort";
            case "yoriki_samurai" : return "Yoriki Samurai";
            case "ochimusha_ensorcelled_thunder" : return "Ochimusha: Ensorcelled Thunder";
            case "ochimusha_cankered_flame" : return "Ochimusha: Cankered Flame";
            case "maguu_kenki_lone_gale" : return "Maguu Kenki: Lone Gale";
            case "maguu_kenki_galloping_frost" : return "Maguu Kenki: Galloping Frost";
            case "maguu_kenki_mask_of_terror" : return "Maguu Kenki: Mask of Terror";
            case "the_great_snowboar_king" : return "The Great Snowboar King";

            default: return  "PAIMON_ATE";
        }
    }

    public String[] getWeaponByName(String str,Context context) {
        switch (str){
            case "Amenoma Kageuchi" : return new String[] {context.getString(R.string.amenoma_kageuchi),"/drawable/amenoma_kageuchi"+IMG_FORMAT};
            case "Aquila Favonia" : return new String[] {context.getString(R.string.aquila_favonia),"/drawable/aquila_favonia"+IMG_FORMAT};
            case "Blackcliff Longsword" : return new String[] {context.getString(R.string.blackcliff_longsword),"/drawable/blackcliff_longsword"+IMG_FORMAT};
            case "Cool Steel" : return new String[] {context.getString(R.string.cool_steel),"/drawable/cool_steel"+IMG_FORMAT};
            case "Dark Iron Sword" : return new String[] {context.getString(R.string.dark_iron_sword),"/drawable/dark_iron_sword"+IMG_FORMAT};
            case "Dull Blade" : return new String[] {context.getString(R.string.dull_blade),"/drawable/dull_blade"+IMG_FORMAT};
            case "Favonius Sword" : return new String[] {context.getString(R.string.favonius_sword),"/drawable/favonius_sword"+IMG_FORMAT};
            case "Festering Desire" : return new String[] {context.getString(R.string.festering_desire),"/drawable/festering_desire"+IMG_FORMAT};
            case "Fillet Blade" : return new String[] {context.getString(R.string.fillet_blade),"/drawable/fillet_blade"+IMG_FORMAT};
            case "Freedom-Sworn" : return new String[] {context.getString(R.string.freedom_sworn),"/drawable/freedom_sworn"+IMG_FORMAT};
            case "Freedom Sworn" : return new String[] {context.getString(R.string.freedom_sworn),"/drawable/freedom_sworn"+IMG_FORMAT};
            case "Harbinger of Dawn" : return new String[] {context.getString(R.string.harbinger_of_dawn),"/drawable/harbinger_of_dawn"+IMG_FORMAT};
            case "Iron Sting" : return new String[] {context.getString(R.string.iron_sting),"/drawable/iron_sting"+IMG_FORMAT};
            case "Lion's Roar" : return new String[] {context.getString(R.string.lions_roar),"/drawable/lions_roar"+IMG_FORMAT};
            case "Mistsplitter Reforged" : return new String[] {context.getString(R.string.mistsplitter_reforged),"/drawable/mistsplitter_reforged"+IMG_FORMAT};
            case "Primordial Jade Cutter" : return new String[] {context.getString(R.string.primordial_jade_cutter),"/drawable/primordial_jade_cutter"+IMG_FORMAT};
            case "Prototype Rancour" : return new String[] {context.getString(R.string.prototype_rancour),"/drawable/prototype_rancour"+IMG_FORMAT};
            case "Royal Longsword" : return new String[] {context.getString(R.string.royal_longsword),"/drawable/royal_longsword"+IMG_FORMAT};
            case "Sacrificial Sword" : return new String[] {context.getString(R.string.sacrificial_sword),"/drawable/sacrificial_sword"+IMG_FORMAT};
            case "Silver Sword" : return new String[] {context.getString(R.string.silver_sword),"/drawable/silver_sword"+IMG_FORMAT};
            case "Skyrider Sword" : return new String[] {context.getString(R.string.skyrider_sword),"/drawable/skyrider_sword"+IMG_FORMAT};
            case "Skyward Blade" : return new String[] {context.getString(R.string.skyward_blade),"/drawable/skyward_blade"+IMG_FORMAT};
            case "Summit Shaper" : return new String[] {context.getString(R.string.summit_shaper),"/drawable/summit_shaper"+IMG_FORMAT};
            case "Sword of Descension" : return new String[] {context.getString(R.string.sword_of_descension),"/drawable/sword_of_descension"+IMG_FORMAT};
            case "The Alley Flash" : return new String[] {context.getString(R.string.the_alley_flash),"/drawable/the_alley_flash"+IMG_FORMAT};
            case "The Black Sword" : return new String[] {context.getString(R.string.the_black_sword),"/drawable/the_black_sword"+IMG_FORMAT};
            case "The Flute" : return new String[] {context.getString(R.string.the_flute),"/drawable/the_flute"+IMG_FORMAT};
            case "Traveler's Handy Sword" : return new String[] {context.getString(R.string.travelers_handy_sword),"/drawable/travelers_handy_sword"+IMG_FORMAT};
            case "Cinnabar Spindle" : return new String[] {context.getString(R.string.cinnabar_spindle),"/drawable/cinnabar_spindle"+IMG_FORMAT};

            case "Waster Greatsword" : return new String[] {context.getString(R.string.waster_greatsword),"/drawable/waster_greatsword"+IMG_FORMAT};
            case "Old Merc's Pal" : return new String[] {context.getString(R.string.old_mercs_pal),"/drawable/old_mercs_pal"+IMG_FORMAT};
            case "Bloodtainted Greatsword" : return new String[] {context.getString(R.string.bloodtainted_greatsword),"/drawable/bloodtainted_greatsword"+IMG_FORMAT};
            case "Debate Club" : return new String[] {context.getString(R.string.debate_club),"/drawable/debate_club"+IMG_FORMAT};
            case "Quartz" : return new String[] {context.getString(R.string.quartz),"/drawable/quartz"+IMG_FORMAT};
            case "Ferrous Shadow" : return new String[] {context.getString(R.string.ferrous_shadow),"/drawable/ferrous_shadow"+IMG_FORMAT};
            case "Skyrider Greatsword" : return new String[] {context.getString(R.string.skyrider_greatsword),"/drawable/skyrider_greatsword"+IMG_FORMAT};
            case "White Iron Greatsword" : return new String[] {context.getString(R.string.white_iron_greatsword),"/drawable/white_iron_greatsword"+IMG_FORMAT};
            case "Blackcliff Slasher" : return new String[] {context.getString(R.string.blackcliff_slasher),"/drawable/blackcliff_slasher"+IMG_FORMAT};
            case "Favonius Greatsword" : return new String[] {context.getString(R.string.favonius_greatsword),"/drawable/favonius_greatsword"+IMG_FORMAT};
            case "Katsuragikiri Nagamasa" : return new String[] {context.getString(R.string.katsuragikiri_nagamasa),"/drawable/katsuragikiri_nagamasa"+IMG_FORMAT};
            case "Lithic Blade" : return new String[] {context.getString(R.string.lithic_blade),"/drawable/lithic_blade"+IMG_FORMAT};
            case "Luxurious Sea-Lord" : return new String[] {context.getString(R.string.luxurious_sea_lord),"/drawable/luxurious_sea_lord"+IMG_FORMAT};
            case "Prototype Archaic" : return new String[] {context.getString(R.string.prototype_archaic),"/drawable/prototype_archaic"+IMG_FORMAT};
            case "Rainslasher" : return new String[] {context.getString(R.string.rainslasher),"/drawable/rainslasher"+IMG_FORMAT};
            case "Royal Greatsword" : return new String[] {context.getString(R.string.royal_greatsword),"/drawable/royal_greatsword"+IMG_FORMAT};
            case "Sacrificial Greatsword" : return new String[] {context.getString(R.string.sacrificial_greatsword),"/drawable/sacrificial_greatsword"+IMG_FORMAT};
            case "Serpent Spine" : return new String[] {context.getString(R.string.serpent_spine),"/drawable/serpent_spine"+IMG_FORMAT};
            case "Snow-Tombed Starsilver" : return new String[] {context.getString(R.string.snow_tombed_starsilver),"/drawable/snow_tombed_starsilver"+IMG_FORMAT};
            case "The Bell" : return new String[] {context.getString(R.string.the_bell),"/drawable/the_bell"+IMG_FORMAT};
            case "Whiteblind" : return new String[] {context.getString(R.string.whiteblind),"/drawable/whiteblind"+IMG_FORMAT};
            case "Skyward Pride" : return new String[] {context.getString(R.string.skyward_pride),"/drawable/skyward_pride"+IMG_FORMAT};
            case "Song of Broken Pines" : return new String[] {context.getString(R.string.song_of_broken_pines),"/drawable/song_of_broken_pines"+IMG_FORMAT};
            case "The Unforged" : return new String[] {context.getString(R.string.the_unforged),"/drawable/the_unforged"+IMG_FORMAT};
            case "Wolf's Gravestone" : return new String[] {context.getString(R.string.wolfs_gravestone),"/drawable/wolfs_gravestone"+IMG_FORMAT};
            case "Akuoumaru" : return new String[] {context.getString(R.string.akuoumaru),"/drawable/akuoumaru"+IMG_FORMAT};
            case "Redhorn Stonethresher" : return new String[] {context.getString(R.string.redhorn_stonethresher),"/drawable/redhorn_stonethresher"+IMG_FORMAT};

            case "Beginner's Protector" : return new String[] {context.getString(R.string.beginners_protector),"/drawable/beginners_protector"+IMG_FORMAT};
            case "Iron Point" : return new String[] {context.getString(R.string.iron_point),"/drawable/iron_point"+IMG_FORMAT};
            case "Black Tassel" : return new String[] {context.getString(R.string.black_tassel),"/drawable/black_tassel"+IMG_FORMAT};
            case "Halberd" : return new String[] {context.getString(R.string.halberd),"/drawable/halberd"+IMG_FORMAT};
            case "White Tassel" : return new String[] {context.getString(R.string.white_tassel),"/drawable/white_tassel"+IMG_FORMAT};
            case "Blackcliff Pole" : return new String[] {context.getString(R.string.blackcliff_pole),"/drawable/blackcliff_pole"+IMG_FORMAT};
            case "Crescent Pike" : return new String[] {context.getString(R.string.crescent_pike),"/drawable/crescent_pike"+IMG_FORMAT};
            case "Deathmatch" : return new String[] {context.getString(R.string.deathmatch),"/drawable/deathmatch"+IMG_FORMAT};
            case "Dragon's Bane" : return new String[] {context.getString(R.string.dragons_bane),"/drawable/dragons_bane"+IMG_FORMAT};
            case "Dragonspine Spear" : return new String[] {context.getString(R.string.dragonspine_spear),"/drawable/dragonspine_spear"+IMG_FORMAT};
            case "Favonius Lance" : return new String[] {context.getString(R.string.favonius_lance),"/drawable/favonius_lance"+IMG_FORMAT};
            case "Kitain Cross Spear" : return new String[] {context.getString(R.string.kitain_cross_spear),"/drawable/kitain_cross_spear"+IMG_FORMAT};
            case "Lithic Spear" : return new String[] {context.getString(R.string.lithic_spear),"/drawable/lithic_spear"+IMG_FORMAT};
            case "Prototype Starglitter" : return new String[] {context.getString(R.string.prototype_starglitter),"/drawable/prototype_starglitter"+IMG_FORMAT};
            case "Royal Spear" : return new String[] {context.getString(R.string.royal_spear),"/drawable/royal_spear"+IMG_FORMAT};
            case "The Catch" : return new String[] {context.getString(R.string.the_catch),"/drawable/the_catch"+IMG_FORMAT};
            case "Engulfing Lightning" : return new String[] {context.getString(R.string.engulfing_lightning),"/drawable/engulfing_lightning"+IMG_FORMAT};
            case "Primordial Jade Winged-Spear" : return new String[] {context.getString(R.string.primordial_jade_winged_spear),"/drawable/primordial_jade_winged_spear"+IMG_FORMAT};
            case "Skyward Spine" : return new String[] {context.getString(R.string.skyward_spine),"/drawable/skyward_spine"+IMG_FORMAT};
            case "Staff of Homa" : return new String[] {context.getString(R.string.staff_of_homa),"/drawable/staff_of_homa"+IMG_FORMAT};
            case "Vortex Vanquisher" : return new String[] {context.getString(R.string.vortex_vanquisher),"/drawable/vortex_vanquisher"+IMG_FORMAT};
            case "Wavebreaker's Fin" : return new String[] {context.getString(R.string.wavebreakers_fin),"/drawable/wavebreakers_fin"+IMG_FORMAT};
            case "Calamity Queller" : return new String[] {context.getString(R.string.calamity_queller),"/drawable/calamity_queller"+IMG_FORMAT};

            case "Apprentice's Notes" : return new String[] {context.getString(R.string.apprentices_notes),"/drawable/apprentices_notes"+IMG_FORMAT};
            case "Pocket Grimoire" : return new String[] {context.getString(R.string.pocket_grimoire),"/drawable/pocket_grimoire"+IMG_FORMAT};
            case "Amber Catalyst" : return new String[] {context.getString(R.string.amber_catalyst),"/drawable/amber_catalyst"+IMG_FORMAT};
            case "Emerald Orb" : return new String[] {context.getString(R.string.emerald_orb),"/drawable/emerald_orb"+IMG_FORMAT};
            case "Magic Guide" : return new String[] {context.getString(R.string.magic_guide),"/drawable/magic_guide"+IMG_FORMAT};
            case "Otherworldly Story" : return new String[] {context.getString(R.string.otherworldly_story),"/drawable/otherworldly_story"+IMG_FORMAT};
            case "Thrilling Tales of Dragon Slayers" : return new String[] {context.getString(R.string.thrilling_tales_of_dragon_slayers),"/drawable/thrilling_tales_of_dragon_slayers"+IMG_FORMAT};
            case "Twin Nephrite" : return new String[] {context.getString(R.string.twin_nephrite),"/drawable/twin_nephrite"+IMG_FORMAT};
            case "Blackcliff Agate" : return new String[] {context.getString(R.string.blackcliff_agate),"/drawable/blackcliff_agate"+IMG_FORMAT};
            case "Dodoco Tales" : return new String[] {context.getString(R.string.dodoco_tales),"/drawable/dodoco_tales"+IMG_FORMAT};
            case "Eye of Perception" : return new String[] {context.getString(R.string.eye_of_perception),"/drawable/eye_of_perception"+IMG_FORMAT};
            case "Favonius Codex" : return new String[] {context.getString(R.string.favonius_codex),"/drawable/favonius_codex"+IMG_FORMAT};
            case "Frostbearer" : return new String[] {context.getString(R.string.frostbearer),"/drawable/frostbearer"+IMG_FORMAT};
            case "Hakushin Ring" : return new String[] {context.getString(R.string.hakushin_ring),"/drawable/hakushin_ring"+IMG_FORMAT};
            case "Mappa Mare" : return new String[] {context.getString(R.string.mappa_mare),"/drawable/mappa_mare"+IMG_FORMAT};
            case "Prototype Amber" : return new String[] {context.getString(R.string.prototype_amber),"/drawable/prototype_amber"+IMG_FORMAT};
            case "Royal Grimoire" : return new String[] {context.getString(R.string.royal_grimoire),"/drawable/royal_grimoire"+IMG_FORMAT};
            case "Sacrificial Fragments" : return new String[] {context.getString(R.string.sacrificial_fragments),"/drawable/sacrificial_fragments"+IMG_FORMAT};
            case "Solar Pearl" : return new String[] {context.getString(R.string.solar_pearl),"/drawable/solar_pearl"+IMG_FORMAT};
            case "The Widsith" : return new String[] {context.getString(R.string.the_widsith),"/drawable/the_widsith"+IMG_FORMAT};
            case "Wine and Song" : return new String[] {context.getString(R.string.wine_and_song),"/drawable/wine_and_song"+IMG_FORMAT};
            case "Everlasting Moonglow" : return new String[] {context.getString(R.string.everlasting_moonglow),"/drawable/everlasting_moonglow"+IMG_FORMAT};
            case "Lost Prayer to the Sacred Winds" : return new String[] {context.getString(R.string.lost_prayer_to_the_sacred_winds),"/drawable/lost_prayer_to_the_sacred_winds"+IMG_FORMAT};
            case "Memory of Dust" : return new String[] {context.getString(R.string.memory_of_dust),"/drawable/memory_of_dust"+IMG_FORMAT};
            case "Skyward Atlas" : return new String[] {context.getString(R.string.skyward_atlas),"/drawable/skyward_atlas"+IMG_FORMAT};

            case "Hunter's Bow" : return new String[] {context.getString(R.string.hunters_bow),"/drawable/hunters_bow"+IMG_FORMAT};
            case "Seasoned Hunter's Bow" : return new String[] {context.getString(R.string.seasoned_hunters_bow),"/drawable/seasoned_hunters_bow"+IMG_FORMAT};
            case "Ebony Bow" : return new String[] {context.getString(R.string.ebony_bow),"/drawable/ebony_bow"+IMG_FORMAT};
            case "Messenger" : return new String[] {context.getString(R.string.messenger),"/drawable/messenger"+IMG_FORMAT};
            case "Raven Bow" : return new String[] {context.getString(R.string.raven_bow),"/drawable/raven_bow"+IMG_FORMAT};
            case "Recurve Bow" : return new String[] {context.getString(R.string.recurve_bow),"/drawable/recurve_bow"+IMG_FORMAT};
            case "Sharpshooter's Oath" : return new String[] {context.getString(R.string.sharpshooters_oath),"/drawable/sharpshooters_oath"+IMG_FORMAT};
            case "Slingshot" : return new String[] {context.getString(R.string.slingshot),"/drawable/slingshot"+IMG_FORMAT};
            case "Alley Hunter" : return new String[] {context.getString(R.string.alley_hunter),"/drawable/alley_hunter"+IMG_FORMAT};
            case "Blackcliff Warbow" : return new String[] {context.getString(R.string.blackcliff_warbow),"/drawable/blackcliff_warbow"+IMG_FORMAT};
            case "Compound Bow" : return new String[] {context.getString(R.string.compound_bow),"/drawable/compound_bow"+IMG_FORMAT};
            case "Favonius Warbow" : return new String[] {context.getString(R.string.favonius_warbow),"/drawable/favonius_warbow"+IMG_FORMAT};
            case "Hamayumi" : return new String[] {context.getString(R.string.hamayumi),"/drawable/hamayumi"+IMG_FORMAT};
            case "Mitternachts Waltz" : return new String[] {context.getString(R.string.mitternachts_waltz),"/drawable/mitternachts_waltz"+IMG_FORMAT};
            case "Predator" : return new String[] {context.getString(R.string.predator),"/drawable/predator"+IMG_FORMAT};
            case "Prototype Crescent" : return new String[] {context.getString(R.string.prototype_crescent),"/drawable/prototype_crescent"+IMG_FORMAT};
            case "Royal Bow" : return new String[] {context.getString(R.string.royal_bow),"/drawable/royal_bow"+IMG_FORMAT};
            case "Rust" : return new String[] {context.getString(R.string.rust),"/drawable/rust"+IMG_FORMAT};
            case "Sacrificial Bow" : return new String[] {context.getString(R.string.sacrificial_bow),"/drawable/sacrificial_bow"+IMG_FORMAT};
            case "The Stringless" : return new String[] {context.getString(R.string.the_stringless),"/drawable/the_stringless"+IMG_FORMAT};
            case "The Viridescent Hunt" : return new String[] {context.getString(R.string.the_viridescent_hunt),"/drawable/the_viridescent_hunt"+IMG_FORMAT};
            case "Windblume Ode" : return new String[] {context.getString(R.string.windblume_ode),"/drawable/windblume_ode"+IMG_FORMAT};
            case "Amos' Bow" : return new String[] {context.getString(R.string.amos_bow),"/drawable/amos_bow"+IMG_FORMAT};
            case "Elegy for the End" : return new String[] {context.getString(R.string.elegy_for_the_end),"/drawable/elegy_for_the_end"+IMG_FORMAT};
            case "Skyward Harp" : return new String[] {context.getString(R.string.skyward_harp),"/drawable/skyward_harp"+IMG_FORMAT};
            case "Thundering Pulse" : return new String[] {context.getString(R.string.thundering_pulse),"/drawable/thundering_pulse"+IMG_FORMAT};
            case "Mouun's Moon" : return new String[] {context.getString(R.string.mouuns_moon),"/drawable/mouuns_moon"+IMG_FORMAT};
            //add in 20220126
            case "Kagura's Verity" : return new String[] {context.getString(R.string.kaguras_verity),"/drawable/kaguras_verity"+IMG_FORMAT};
            case "Oathsworn Eye" : return new String[] {context.getString(R.string.oathsworn_eye),"/drawable/oathsworn_eye"+IMG_FORMAT};
            //add in 20220329
            case "Haran Geppaku Futsu" : return new String[] {context.getString(R.string.haran_geppaku_futsu),"/drawable/haran_geppaku_futsu"+IMG_FORMAT};
            //add in 20220521
            case "Aqua Simulacra" : return new String[] {context.getString(R.string.aqua_simulacra),"/drawable/aqua_simulacra"+IMG_FORMAT};
            case "Kagotsurube Isshin" : return new String[] {context.getString(R.string.kagotsurube_isshin),"/drawable/kagotsurube_isshin"+IMG_FORMAT};
            case "Fading Twilight" : return new String[] {context.getString(R.string.fading_twilight),"/drawable/fading_twilight"+IMG_FORMAT};
            //add in 20220714
            case "Sapwood Blade" : return new String[] {context.getString(R.string.sapwood_blade),"/drawable/timber_blade"+IMG_FORMAT};
            case "Forest Regalia" : return new String[] {context.getString(R.string.forest_regalia),"/drawable/forest_regalia"+IMG_FORMAT};
            case "Moonpiercer" : return new String[] {context.getString(R.string.moonpiercer),"/drawable/moonpiercer"+IMG_FORMAT};
            case "King's Squire" : return new String[] {context.getString(R.string.kings_squire),"/drawable/kings_squire"+IMG_FORMAT};
            case "End of the Line" : return new String[] {context.getString(R.string.end_of_the_line),"/drawable/trawler"+IMG_FORMAT};
            case "Hunter's Path" : return new String[] {context.getString(R.string.hunters_path),"/drawable/hunters_path"+IMG_FORMAT};
            case "Fruit of Fulfillment" : return new String[] {context.getString(R.string.fruit_of_fulfillment),"/drawable/fruit_of_fulfillment"+IMG_FORMAT};
            //add in 20220924
            case "Xiphos' Moonlight" : return new String[] {context.getString(R.string.xiphos_moonlight),"/drawable/xiphos_moonlight"+IMG_FORMAT};
            case "Key of Khaj-Nisut" : return new String[] {context.getString(R.string.key_of_khaj_nisut),"/drawable/key_of_khaj_nisut"+IMG_FORMAT};
            case "Makhaira Aquamarine" : return new String[] {context.getString(R.string.makhaira_aquamarine),"/drawable/makhaira_aquamarine"+IMG_FORMAT};
            case "Missive Windspear" : return new String[] {context.getString(R.string.missive_windspear),"/drawable/missive_windspear"+IMG_FORMAT};
            case "Staff of the Scarlet Sands" : return new String[] {context.getString(R.string.staff_of_the_scarlet_sands),"/drawable/staff_of_the_scarlet_sands"+IMG_FORMAT};
            case "Wandering Evenstar" : return new String[] {context.getString(R.string.wandering_evenstar),"/drawable/wandering_evenstar"+IMG_FORMAT};
            //add in 20221001
            case "A Thousand Floating Dreams" : return new String[] {context.getString(R.string.a_thousand_floating_dreams),"/drawable/a_thousand_floating_dreams"+IMG_FORMAT};
            //add in 20221210
            case "Polar Star" : return new String[] {context.getString(R.string.polar_star),"/drawable/polar_star"+IMG_FORMAT};
            case "Toukabou Shigure" : return new String[] {context.getString(R.string.toukabou_shigure),"/drawable/toukabou_shigure"+IMG_FORMAT};
            case "Tulaytullah's Remembrance" : return new String[] {context.getString(R.string.tulaytullahs_remembrance),"/drawable/tulaytullahs_remembrance"+IMG_FORMAT};
            //add in 20230118
            case "Light of Foliar Incision" : return new String[] {context.getString(R.string.light_of_foliar_incision),"/drawable/light_of_foliar_incision"+IMG_FORMAT};
            //add in 20230225
            case "Mailed Flower" : return new String[] {context.getString(R.string.mailed_flower),"/drawable/mailed_flower"+IMG_FORMAT};
            case "Beacon of the Reed Sea" : return new String[] {context.getString(R.string.beacon_of_the_reed_sea),"/drawable/beacon_of_the_reed_sea"+IMG_FORMAT};
            //add in 20230416
            case "Jadefall's Splendor" : return new String[] {context.getString(R.string.jadefalls_splendor),"/drawable/jadefalls_splendor"+IMG_FORMAT};

            default :
                System.out.println("WEAPON+ NAME ERROR : "+str);
                return new String[] {str,"/drawable/"+str.toLowerCase().replace("'","").replace(" ","_")+""+IMG_FORMAT};
        }
    }


    public String[] getWeaponGachaByName(String str,Context context) {
        switch (str){
            case "Amenoma Kageuchi" : return new String[] {context.getString(R.string.amenoma_kageuchi),"/drawable/amenoma_kageuchi_gacha"+IMG_FORMAT};
            case "Aquila Favonia" : return new String[] {context.getString(R.string.aquila_favonia),"/drawable/aquila_favonia_gacha"+IMG_FORMAT};
            case "Blackcliff Longsword" : return new String[] {context.getString(R.string.blackcliff_longsword),"/drawable/blackcliff_longsword_gacha"+IMG_FORMAT};
            case "Cool Steel" : return new String[] {context.getString(R.string.cool_steel),"/drawable/cool_steel_gacha"+IMG_FORMAT};
            case "Dark Iron Sword" : return new String[] {context.getString(R.string.dark_iron_sword),"/drawable/dark_iron_sword_gacha"+IMG_FORMAT};
            case "Dull Blade" : return new String[] {context.getString(R.string.dull_blade),"/drawable/dull_blade_gacha"+IMG_FORMAT};
            case "Favonius Sword" : return new String[] {context.getString(R.string.favonius_sword),"/drawable/favonius_sword_gacha"+IMG_FORMAT};
            case "Festering Desire" : return new String[] {context.getString(R.string.festering_desire),"/drawable/festering_desire_gacha"+IMG_FORMAT};
            case "Fillet Blade" : return new String[] {context.getString(R.string.fillet_blade),"/drawable/fillet_blade_gacha"+IMG_FORMAT};
            case "Freedom-Sworn" : return new String[] {context.getString(R.string.freedom_sworn),"/drawable/freedom_sworn_gacha"+IMG_FORMAT};
            case "Freedom Sworn" : return new String[] {context.getString(R.string.freedom_sworn),"/drawable/freedom_sworn_gacha"+IMG_FORMAT};
            case "Harbinger of Dawn" : return new String[] {context.getString(R.string.harbinger_of_dawn),"/drawable/harbinger_of_dawn_gacha"+IMG_FORMAT};
            case "Iron Sting" : return new String[] {context.getString(R.string.iron_sting),"/drawable/iron_sting_gacha"+IMG_FORMAT};
            case "Lion's Roar" : return new String[] {context.getString(R.string.lions_roar),"/drawable/lions_roar_gacha"+IMG_FORMAT};
            case "Mistsplitter Reforged" : return new String[] {context.getString(R.string.mistsplitter_reforged),"/drawable/mistsplitter_reforged_gacha"+IMG_FORMAT};
            case "Primordial Jade Cutter" : return new String[] {context.getString(R.string.primordial_jade_cutter),"/drawable/primordial_jade_cutter_gacha"+IMG_FORMAT};
            case "Prototype Rancour" : return new String[] {context.getString(R.string.prototype_rancour),"/drawable/prototype_rancour_gacha"+IMG_FORMAT};
            case "Royal Longsword" : return new String[] {context.getString(R.string.royal_longsword),"/drawable/royal_longsword_gacha"+IMG_FORMAT};
            case "Sacrificial Sword" : return new String[] {context.getString(R.string.sacrificial_sword),"/drawable/sacrificial_sword_gacha"+IMG_FORMAT};
            case "Silver Sword" : return new String[] {context.getString(R.string.silver_sword),"/drawable/silver_sword_gacha"+IMG_FORMAT};
            case "Skyrider Sword" : return new String[] {context.getString(R.string.skyrider_sword),"/drawable/skyrider_sword_gacha"+IMG_FORMAT};
            case "Skyward Blade" : return new String[] {context.getString(R.string.skyward_blade),"/drawable/skyward_blade_gacha"+IMG_FORMAT};
            case "Summit Shaper" : return new String[] {context.getString(R.string.summit_shaper),"/drawable/summit_shaper_gacha"+IMG_FORMAT};
            case "Sword of Descension" : return new String[] {context.getString(R.string.sword_of_descension),"/drawable/sword_of_descension_gacha"+IMG_FORMAT};
            case "The Alley Flash" : return new String[] {context.getString(R.string.the_alley_flash),"/drawable/the_alley_flash_gacha"+IMG_FORMAT};
            case "The Black Sword" : return new String[] {context.getString(R.string.the_black_sword),"/drawable/the_black_sword_gacha"+IMG_FORMAT};
            case "The Flute" : return new String[] {context.getString(R.string.the_flute),"/drawable/the_flute_gacha"+IMG_FORMAT};
            case "Traveler's Handy Sword" : return new String[] {context.getString(R.string.travelers_handy_sword),"/drawable/travelers_handy_sword_gacha"+IMG_FORMAT};
            case "Cinnabar Spindle" : return new String[] {context.getString(R.string.cinnabar_spindle),"/drawable/cinnabar_spindle_gacha"+IMG_FORMAT};

            case "Waster Greatsword" : return new String[] {context.getString(R.string.waster_greatsword),"/drawable/waster_greatsword_gacha"+IMG_FORMAT};
            case "Old Merc's Pal" : return new String[] {context.getString(R.string.old_mercs_pal),"/drawable/old_mercs_pal_gacha"+IMG_FORMAT};
            case "Bloodtainted Greatsword" : return new String[] {context.getString(R.string.bloodtainted_greatsword),"/drawable/bloodtainted_greatsword_gacha"+IMG_FORMAT};
            case "Debate Club" : return new String[] {context.getString(R.string.debate_club),"/drawable/debate_club_gacha"+IMG_FORMAT};
            case "Quartz" : return new String[] {context.getString(R.string.quartz),"/drawable/quartz_gacha"+IMG_FORMAT};
            case "Ferrous Shadow" : return new String[] {context.getString(R.string.ferrous_shadow),"/drawable/ferrous_shadow_gacha"+IMG_FORMAT};
            case "Skyrider Greatsword" : return new String[] {context.getString(R.string.skyrider_greatsword),"/drawable/skyrider_greatsword_gacha"+IMG_FORMAT};
            case "White Iron Greatsword" : return new String[] {context.getString(R.string.white_iron_greatsword),"/drawable/white_iron_greatsword_gacha"+IMG_FORMAT};
            case "Blackcliff Slasher" : return new String[] {context.getString(R.string.blackcliff_slasher),"/drawable/blackcliff_slasher_gacha"+IMG_FORMAT};
            case "Favonius Greatsword" : return new String[] {context.getString(R.string.favonius_greatsword),"/drawable/favonius_greatsword_gacha"+IMG_FORMAT};
            case "Katsuragikiri Nagamasa" : return new String[] {context.getString(R.string.katsuragikiri_nagamasa),"/drawable/katsuragikiri_nagamasa_gacha"+IMG_FORMAT};
            case "Lithic Blade" : return new String[] {context.getString(R.string.lithic_blade),"/drawable/lithic_blade_gacha"+IMG_FORMAT};
            case "Luxurious Sea-Lord" : return new String[] {context.getString(R.string.luxurious_sea_lord),"/drawable/luxurious_sea_lord_gacha"+IMG_FORMAT};
            case "Prototype Archaic" : return new String[] {context.getString(R.string.prototype_archaic),"/drawable/prototype_archaic_gacha"+IMG_FORMAT};
            case "Rainslasher" : return new String[] {context.getString(R.string.rainslasher),"/drawable/rainslasher_gacha"+IMG_FORMAT};
            case "Royal Greatsword" : return new String[] {context.getString(R.string.royal_greatsword),"/drawable/royal_greatsword_gacha"+IMG_FORMAT};
            case "Sacrificial Greatsword" : return new String[] {context.getString(R.string.sacrificial_greatsword),"/drawable/sacrificial_greatsword_gacha"+IMG_FORMAT};
            case "Serpent Spine" : return new String[] {context.getString(R.string.serpent_spine),"/drawable/serpent_spine_gacha"+IMG_FORMAT};
            case "Snow-Tombed Starsilver" : return new String[] {context.getString(R.string.snow_tombed_starsilver),"/drawable/snow_tombed_starsilver_gacha"+IMG_FORMAT};
            case "The Bell" : return new String[] {context.getString(R.string.the_bell),"/drawable/the_bell_gacha"+IMG_FORMAT};
            case "Whiteblind" : return new String[] {context.getString(R.string.whiteblind),"/drawable/whiteblind_gacha"+IMG_FORMAT};
            case "Skyward Pride" : return new String[] {context.getString(R.string.skyward_pride),"/drawable/skyward_pride_gacha"+IMG_FORMAT};
            case "Song of Broken Pines" : return new String[] {context.getString(R.string.song_of_broken_pines),"/drawable/song_of_broken_pines_gacha"+IMG_FORMAT};
            case "The Unforged" : return new String[] {context.getString(R.string.the_unforged),"/drawable/the_unforged_gacha"+IMG_FORMAT};
            case "Wolf's Gravestone" : return new String[] {context.getString(R.string.wolfs_gravestone),"/drawable/wolfs_gravestone_gacha"+IMG_FORMAT};
            case "Akuoumaru" : return new String[] {context.getString(R.string.akuoumaru),"/drawable/akuoumaru_gacha"+IMG_FORMAT};
            case "Redhorn Stonethresher" : return new String[] {context.getString(R.string.redhorn_stonethresher),"/drawable/redhorn_stonethresher_gacha"+IMG_FORMAT};

            case "Beginner's Protector" : return new String[] {context.getString(R.string.beginners_protector),"/drawable/beginners_protector_gacha"+IMG_FORMAT};
            case "Iron Point" : return new String[] {context.getString(R.string.iron_point),"/drawable/iron_point_gacha"+IMG_FORMAT};
            case "Black Tassel" : return new String[] {context.getString(R.string.black_tassel),"/drawable/black_tassel_gacha"+IMG_FORMAT};
            case "Halberd" : return new String[] {context.getString(R.string.halberd),"/drawable/halberd_gacha"+IMG_FORMAT};
            case "White Tassel" : return new String[] {context.getString(R.string.white_tassel),"/drawable/white_tassel_gacha"+IMG_FORMAT};
            case "Blackcliff Pole" : return new String[] {context.getString(R.string.blackcliff_pole),"/drawable/blackcliff_pole_gacha"+IMG_FORMAT};
            case "Crescent Pike" : return new String[] {context.getString(R.string.crescent_pike),"/drawable/crescent_pike_gacha"+IMG_FORMAT};
            case "Deathmatch" : return new String[] {context.getString(R.string.deathmatch),"/drawable/deathmatch_gacha"+IMG_FORMAT};
            case "Dragon's Bane" : return new String[] {context.getString(R.string.dragons_bane),"/drawable/dragons_bane_gacha"+IMG_FORMAT};
            case "Dragonspine Spear" : return new String[] {context.getString(R.string.dragonspine_spear),"/drawable/dragonspine_spear_gacha"+IMG_FORMAT};
            case "Favonius Lance" : return new String[] {context.getString(R.string.favonius_lance),"/drawable/favonius_lance_gacha"+IMG_FORMAT};
            case "Kitain Cross Spear" : return new String[] {context.getString(R.string.kitain_cross_spear),"/drawable/kitain_cross_spear_gacha"+IMG_FORMAT};
            case "Lithic Spear" : return new String[] {context.getString(R.string.lithic_spear),"/drawable/lithic_spear_gacha"+IMG_FORMAT};
            case "Prototype Starglitter" : return new String[] {context.getString(R.string.prototype_starglitter),"/drawable/prototype_starglitter_gacha"+IMG_FORMAT};
            case "Royal Spear" : return new String[] {context.getString(R.string.royal_spear),"/drawable/royal_spear_gacha"+IMG_FORMAT};
            case "The Catch" : return new String[] {context.getString(R.string.the_catch),"/drawable/the_catch_gacha"+IMG_FORMAT};
            case "Engulfing Lightning" : return new String[] {context.getString(R.string.engulfing_lightning),"/drawable/engulfing_lightning_gacha"+IMG_FORMAT};
            case "Primordial Jade Winged-Spear" : return new String[] {context.getString(R.string.primordial_jade_winged_spear),"/drawable/primordial_jade_winged_spear_gacha"+IMG_FORMAT};
            case "Skyward Spine" : return new String[] {context.getString(R.string.skyward_spine),"/drawable/skyward_spine_gacha"+IMG_FORMAT};
            case "Staff of Homa" : return new String[] {context.getString(R.string.staff_of_homa),"/drawable/staff_of_homa_gacha"+IMG_FORMAT};
            case "Vortex Vanquisher" : return new String[] {context.getString(R.string.vortex_vanquisher),"/drawable/vortex_vanquisher_gacha"+IMG_FORMAT};
            case "Wavebreaker's Fin" : return new String[] {context.getString(R.string.wavebreakers_fin),"/drawable/wavebreakers_fin_gacha"+IMG_FORMAT};
            case "Calamity Queller" : return new String[] {context.getString(R.string.calamity_queller),"/drawable/calamity_queller_gacha"+IMG_FORMAT};

            case "Apprentice's Notes" : return new String[] {context.getString(R.string.apprentices_notes),"/drawable/apprentices_notes_gacha"+IMG_FORMAT};
            case "Pocket Grimoire" : return new String[] {context.getString(R.string.pocket_grimoire),"/drawable/pocket_grimoire_gacha"+IMG_FORMAT};
            case "Amber Catalyst" : return new String[] {context.getString(R.string.amber_catalyst),"/drawable/amber_catalyst_gacha"+IMG_FORMAT};
            case "Emerald Orb" : return new String[] {context.getString(R.string.emerald_orb),"/drawable/emerald_orb_gacha"+IMG_FORMAT};
            case "Magic Guide" : return new String[] {context.getString(R.string.magic_guide),"/drawable/magic_guide_gacha"+IMG_FORMAT};
            case "Otherworldly Story" : return new String[] {context.getString(R.string.otherworldly_story),"/drawable/otherworldly_story_gacha"+IMG_FORMAT};
            case "Thrilling Tales of Dragon Slayers" : return new String[] {context.getString(R.string.thrilling_tales_of_dragon_slayers),"/drawable/thrilling_tales_of_dragon_slayers_gacha"+IMG_FORMAT};
            case "Twin Nephrite" : return new String[] {context.getString(R.string.twin_nephrite),"/drawable/twin_nephrite_gacha"+IMG_FORMAT};
            case "Blackcliff Agate" : return new String[] {context.getString(R.string.blackcliff_agate),"/drawable/blackcliff_agate_gacha"+IMG_FORMAT};
            case "Dodoco Tales" : return new String[] {context.getString(R.string.dodoco_tales),"/drawable/dodoco_tales_gacha"+IMG_FORMAT};
            case "Eye of Perception" : return new String[] {context.getString(R.string.eye_of_perception),"/drawable/eye_of_perception_gacha"+IMG_FORMAT};
            case "Favonius Codex" : return new String[] {context.getString(R.string.favonius_codex),"/drawable/favonius_codex_gacha"+IMG_FORMAT};
            case "Frostbearer" : return new String[] {context.getString(R.string.frostbearer),"/drawable/frostbearer_gacha"+IMG_FORMAT};
            case "Hakushin Ring" : return new String[] {context.getString(R.string.hakushin_ring),"/drawable/hakushin_ring_gacha"+IMG_FORMAT};
            case "Mappa Mare" : return new String[] {context.getString(R.string.mappa_mare),"/drawable/mappa_mare_gacha"+IMG_FORMAT};
            case "Prototype Amber" : return new String[] {context.getString(R.string.prototype_amber),"/drawable/prototype_amber_gacha"+IMG_FORMAT};
            case "Royal Grimoire" : return new String[] {context.getString(R.string.royal_grimoire),"/drawable/royal_grimoire_gacha"+IMG_FORMAT};
            case "Sacrificial Fragments" : return new String[] {context.getString(R.string.sacrificial_fragments),"/drawable/sacrificial_fragments_gacha"+IMG_FORMAT};
            case "Solar Pearl" : return new String[] {context.getString(R.string.solar_pearl),"/drawable/solar_pearl_gacha"+IMG_FORMAT};
            case "The Widsith" : return new String[] {context.getString(R.string.the_widsith),"/drawable/the_widsith_gacha"+IMG_FORMAT};
            case "Wine and Song" : return new String[] {context.getString(R.string.wine_and_song),"/drawable/wine_and_song_gacha"+IMG_FORMAT};
            case "Everlasting Moonglow" : return new String[] {context.getString(R.string.everlasting_moonglow),"/drawable/everlasting_moonglow_gacha"+IMG_FORMAT};
            case "Lost Prayer to the Sacred Winds" : return new String[] {context.getString(R.string.lost_prayer_to_the_sacred_winds),"/drawable/lost_prayer_to_the_sacred_winds_gacha"+IMG_FORMAT};
            case "Memory of Dust" : return new String[] {context.getString(R.string.memory_of_dust),"/drawable/memory_of_dust_gacha"+IMG_FORMAT};
            case "Skyward Atlas" : return new String[] {context.getString(R.string.skyward_atlas),"/drawable/skyward_atlas_gacha"+IMG_FORMAT};

            case "Hunter's Bow" : return new String[] {context.getString(R.string.hunters_bow),"/drawable/hunters_bow_gacha"+IMG_FORMAT};
            case "Seasoned Hunter's Bow" : return new String[] {context.getString(R.string.seasoned_hunters_bow),"/drawable/seasoned_hunters_bow_gacha"+IMG_FORMAT};
            case "Ebony Bow" : return new String[] {context.getString(R.string.ebony_bow),"/drawable/ebony_bow_gacha"+IMG_FORMAT};
            case "Messenger" : return new String[] {context.getString(R.string.messenger),"/drawable/messenger_gacha"+IMG_FORMAT};
            case "Raven Bow" : return new String[] {context.getString(R.string.raven_bow),"/drawable/raven_bow_gacha"+IMG_FORMAT};
            case "Recurve Bow" : return new String[] {context.getString(R.string.recurve_bow),"/drawable/recurve_bow_gacha"+IMG_FORMAT};
            case "Sharpshooter's Oath" : return new String[] {context.getString(R.string.sharpshooters_oath),"/drawable/sharpshooters_oath_gacha"+IMG_FORMAT};
            case "Slingshot" : return new String[] {context.getString(R.string.slingshot),"/drawable/slingshot_gacha"+IMG_FORMAT};
            case "Alley Hunter" : return new String[] {context.getString(R.string.alley_hunter),"/drawable/alley_hunter_gacha"+IMG_FORMAT};
            case "Blackcliff Warbow" : return new String[] {context.getString(R.string.blackcliff_warbow),"/drawable/blackcliff_warbow_gacha"+IMG_FORMAT};
            case "Compound Bow" : return new String[] {context.getString(R.string.compound_bow),"/drawable/compound_bow_gacha"+IMG_FORMAT};
            case "Favonius Warbow" : return new String[] {context.getString(R.string.favonius_warbow),"/drawable/favonius_warbow_gacha"+IMG_FORMAT};
            case "Hamayumi" : return new String[] {context.getString(R.string.hamayumi),"/drawable/hamayumi_gacha"+IMG_FORMAT};
            case "Mitternachts Waltz" : return new String[] {context.getString(R.string.mitternachts_waltz),"/drawable/mitternachts_waltz_gacha"+IMG_FORMAT};
            case "Predator" : return new String[] {context.getString(R.string.predator),"/drawable/predator_gacha"+IMG_FORMAT};
            case "Prototype Crescent" : return new String[] {context.getString(R.string.prototype_crescent),"/drawable/prototype_crescent_gacha"+IMG_FORMAT};
            case "Royal Bow" : return new String[] {context.getString(R.string.royal_bow),"/drawable/royal_bow_gacha"+IMG_FORMAT};
            case "Rust" : return new String[] {context.getString(R.string.rust),"/drawable/rust_gacha"+IMG_FORMAT};
            case "Sacrificial Bow" : return new String[] {context.getString(R.string.sacrificial_bow),"/drawable/sacrificial_bow_gacha"+IMG_FORMAT};
            case "The Stringless" : return new String[] {context.getString(R.string.the_stringless),"/drawable/the_stringless_gacha"+IMG_FORMAT};
            case "The Viridescent Hunt" : return new String[] {context.getString(R.string.the_viridescent_hunt),"/drawable/the_viridescent_hunt_gacha"+IMG_FORMAT};
            case "Windblume Ode" : return new String[] {context.getString(R.string.windblume_ode),"/drawable/windblume_ode_gacha"+IMG_FORMAT};
            case "Amos' Bow" : return new String[] {context.getString(R.string.amos_bow),"/drawable/amos_bow_gacha"+IMG_FORMAT};
            case "Elegy for the End" : return new String[] {context.getString(R.string.elegy_for_the_end),"/drawable/elegy_for_the_end_gacha"+IMG_FORMAT};
            case "Skyward Harp" : return new String[] {context.getString(R.string.skyward_harp),"/drawable/skyward_harp_gacha"+IMG_FORMAT};
            case "Thundering Pulse" : return new String[] {context.getString(R.string.thundering_pulse),"/drawable/thundering_pulse_gacha"+IMG_FORMAT};
            case "Mouun's Moon" : return new String[] {context.getString(R.string.mouuns_moon),"/drawable/mouuns_moon_gacha"+IMG_FORMAT};
            //add in 20220126
            case "Kagura's Verity" : return new String[] {context.getString(R.string.kaguras_verity),"/drawable/kaguras_verity_gacha"+IMG_FORMAT};
            case "Oathsworn Eye" : return new String[] {context.getString(R.string.oathsworn_eye),"/drawable/oathsworn_eye_gacha"+IMG_FORMAT};
            //add in 20220329
            case "Haran Geppaku Futsu" : return new String[] {context.getString(R.string.haran_geppaku_futsu),"/drawable/haran_geppaku_futsu_gacha"+IMG_FORMAT};
            // add in 20220521
            case "Aqua Simulacra" : return new String[] {context.getString(R.string.aqua_simulacra),"/drawable/aqua_simulacra_gacha"+IMG_FORMAT};
            case "Kagotsurube Isshin" : return new String[] {context.getString(R.string.kagotsurube_isshin),"/drawable/kagotsurube_isshin_gacha"+IMG_FORMAT};
            case "Fading Twilight" : return new String[] {context.getString(R.string.fading_twilight),"/drawable/fading_twilight_gacha"+IMG_FORMAT};
            //add in 20220714
            case "Sapwood Blade" : return new String[] {context.getString(R.string.sapwood_blade),"/drawable/timber_blade_gacha"+IMG_FORMAT};
            case "Forest Regalia" : return new String[] {context.getString(R.string.forest_regalia),"/drawable/forest_regalia_gacha"+IMG_FORMAT};
            case "Moonpiercer" : return new String[] {context.getString(R.string.moonpiercer),"/drawable/moonpiercer_gacha"+IMG_FORMAT};
            case "King's Squire" : return new String[] {context.getString(R.string.kings_squire),"/drawable/kings_squire_gacha"+IMG_FORMAT};
            case "End of the Line" : return new String[] {context.getString(R.string.end_of_the_line),"/drawable/trawler_gacha"+IMG_FORMAT};
            case "Hunter's Path" : return new String[] {context.getString(R.string.hunters_path),"/drawable/hunters_path_gacha"+IMG_FORMAT};
            case "Fruit of Fulfillment" : return new String[] {context.getString(R.string.fruit_of_fulfillment),"/drawable/fruit_of_fulfillment_gacha"+IMG_FORMAT};
            //add in 20220924
            case "Xiphos' Moonlight" : return new String[] {context.getString(R.string.xiphos_moonlight),"/drawable/xiphos_moonlight_gacha"+IMG_FORMAT};
            case "Key of Khaj-Nisut" : return new String[] {context.getString(R.string.key_of_khaj_nisut),"/drawable/key_of_khaj_nisut_gacha"+IMG_FORMAT};
            case "Makhaira Aquamarine" : return new String[] {context.getString(R.string.makhaira_aquamarine),"/drawable/makhaira_aquamarine_gacha"+IMG_FORMAT};
            case "Missive Windspear" : return new String[] {context.getString(R.string.missive_windspear),"/drawable/missive_windspear_gacha"+IMG_FORMAT};
            case "Staff of the Scarlet Sands" : return new String[] {context.getString(R.string.staff_of_the_scarlet_sands),"/drawable/staff_of_the_scarlet_sands_gacha"+IMG_FORMAT};
            case "Wandering Evenstar" : return new String[] {context.getString(R.string.wandering_evenstar),"/drawable/wandering_evenstar_gacha"+IMG_FORMAT};
            //add in 20221003
            case "A Thousand Floating Dreams" : return new String[] {context.getString(R.string.a_thousand_floating_dreams),"/drawable/a_thousand_floating_dreams_gacha"+IMG_FORMAT};
            //add in 20221210
            case "Polar Star" : return new String[] {context.getString(R.string.polar_star),"/drawable/polar_star_gacha"+IMG_FORMAT};
            case "Toukabou Shigure" : return new String[] {context.getString(R.string.toukabou_shigure),"/drawable/toukabou_shigure_gacha"+IMG_FORMAT};
            case "Tulaytullah's Remembrance" : return new String[] {context.getString(R.string.tulaytullahs_remembrance),"/drawable/tulaytullahs_remembrance_gacha"+IMG_FORMAT};
            //add in 20230118
            case "Light of Foliar Incision" : return new String[] {context.getString(R.string.light_of_foliar_incision),"/drawable/light_of_foliar_incision_gacha"+IMG_FORMAT};
            //add in 20230225
            case "Mailed Flower" : return new String[] {context.getString(R.string.mailed_flower),"/drawable/mailed_flower_gacha"+IMG_FORMAT};
            case "Beacon of the Reed Sea" : return new String[] {context.getString(R.string.beacon_of_the_reed_sea),"/drawable/beacon_of_the_reed_sea_gacha"+IMG_FORMAT};
            //add in 20230416
            case "Jadefall's Splendor" : return new String[] {context.getString(R.string.jadefalls_splendor),"/drawable/jadefalls_splendor_gacha"+IMG_FORMAT};


            default :
                System.out.println("WEAPON+ NAME ERROR : "+str);
                return new String[] {str,"/drawable/"+str.toLowerCase().replace("'","").replace(" ","_")+"_gacha"+IMG_FORMAT};
        }
    }

    public String getWeaponNameByFileName (String str){
        switch (str){
            case "amenoma_kageuchi" : return "Amenoma Kageuchi";
            case "aquila_favonia" : return "Aquila Favonia";
            case "blackcliff_longsword" : return "Blackcliff Longsword";
            case "cool_steel" : return "Cool Steel";
            case "dark_iron_sword" : return "Dark Iron Sword";
            case "dull_blade" : return "Dull Blade";
            case "favonius_sword" : return "Favonius Sword";
            case "festering_desire" : return "Festering Desire";
            case "fillet_blade" : return "Fillet Blade";
            case "freedom_sworn" : return "Freedom Sworn";
            case "harbinger_of_dawn" : return "Harbinger of Dawn";
            case "iron_sting" : return "Iron Sting";
            case "lions_roar" : return "Lion's Roar";
            case "mistsplitter_reforged" : return "Mistsplitter Reforged";
            case "primordial_jade_cutter" : return "Primordial Jade Cutter";
            case "prototype_rancour" : return "Prototype Rancour";
            case "royal_longsword" : return "Royal Longsword";
            case "sacrificial_sword" : return "Sacrificial Sword";
            case "silver_sword" : return "Silver Sword";
            case "skyrider_sword" : return "Skyrider Sword";
            case "skyward_blade" : return "Skyward Blade";
            case "summit_shaper" : return "Summit Shaper";
            case "sword_of_descension" : return "Sword of Descension";
            case "the_alley_flash" : return "The Alley Flash";
            case "the_black_sword" : return "The Black Sword";
            case "the_flute" : return "The Flute";
            case "travelers_handy_sword" : return "Traveler's Handy Sword";

            case "waster_greatsword" : return "Waster Greatsword";
            case "old_mercs_pal" : return "Old Merc's Pal";
            case "bloodtainted_greatsword" : return "Bloodtainted Greatsword";
            case "debate_club" : return "Debate Club";
            case "quartz" : return "Quartz";
            case "ferrous_shadow" : return "Ferrous Shadow";
            case "skyrider_greatsword" : return "Skyrider Greatsword";
            case "white_iron_greatsword" : return "White Iron Greatsword";
            case "blackcliff_slasher" : return "Blackcliff Slasher";
            case "favonius_greatsword" : return "Favonius Greatsword";
            case "katsuragikiri_nagamasa" : return "Katsuragikiri Nagamasa";
            case "lithic_blade" : return "Lithic Blade";
            case "luxurious_sea_lord" : return "Luxurious Sea-Lord";
            case "prototype_archaic" : return "Prototype Archaic";
            case "rainslasher" : return "Rainslasher";
            case "royal_greatsword" : return "Royal Greatsword";
            case "sacrificial_greatsword" : return "Sacrificial Greatsword";
            case "serpent_spine" : return "Serpent Spine";
            case "snow_tombed_starsilver" : return "Snow-Tombed Starsilver";
            case "the_bell" : return "The Bell";
            case "whiteblind" : return "Whiteblind";
            case "skyward_pride" : return "Skyward Pride";
            case "song_of_broken_pines" : return "Song of Broken Pines";
            case "the_unforged" : return "The Unforged";
            case "wolfs_gravestone" : return "Wolf's Gravestone";

            case "beginners_protector" : return "Beginner's Protector";
            case "iron_point" : return "Iron Point";
            case "black_tassel" : return "Black Tassel";
            case "halberd" : return "Halberd";
            case "white_tassel" : return "White Tassel";
            case "blackcliff_pole" : return "Blackcliff Pole";
            case "crescent_pike" : return "Crescent Pike";
            case "deathmatch" : return "Deathmatch";
            case "dragons_bane" : return "Dragon's Bane";
            case "dragonspine_spear" : return "Dragonspine Spear";
            case "favonius_lance" : return "Favonius Lance";
            case "kitain_cross_spear" : return "Kitain Cross Spear";
            case "lithic_spear" : return "Lithic Spear";
            case "prototype_starglitter" : return "Prototype Starglitter";
            case "royal_spear" : return "Royal Spear";
            case "the_catch" : return "The Catch";
            case "engulfing_lightning" : return "Engulfing Lightning";
            case "primordial_jade_winged_spear" : return "Primordial Jade Winged-Spear";
            case "skyward_spine" : return "Skyward Spine";
            case "staff_of_homa" : return "Staff of Homa";
            case "vortex_vanquisher" : return "Vortex Vanquisher";

            case "apprentices_notes" : return "Apprentice's Notes";
            case "pocket_grimoire" : return "Pocket Grimoire";
            case "amber_catalyst" : return "Amber Catalyst";
            case "emerald_orb" : return "Emerald Orb";
            case "magic_guide" : return "Magic Guide";
            case "otherworldly_story" : return "Otherworldly Story";
            case "thrilling_tales_of_dragon_slayers" : return "Thrilling Tales of Dragon Slayers";
            case "twin_nephrite" : return "Twin Nephrite";
            case "blackcliff_agate" : return "Blackcliff Agate";
            case "dodoco_tales" : return "Dodoco Tales";
            case "eye_of_perception" : return "Eye of Perception";
            case "favonius_codex" : return "Favonius Codex";
            case "frostbearer" : return "Frostbearer";
            case "hakushin_ring" : return "Hakushin Ring";
            case "mappa_mare" : return "Mappa Mare";
            case "prototype_amber" : return "Prototype Amber";
            case "royal_grimoire" : return "Royal Grimoire";
            case "sacrificial_fragments" : return "Sacrificial Fragments";
            case "solar_pearl" : return "Solar Pearl";
            case "the_widsith" : return "The Widsith";
            case "wine_and_song" : return "Wine and Song";
            case "everlasting_moonglow" : return "Everlasting Moonglow";
            case "lost_prayer_to_the_sacred_winds" : return "Lost Prayer to the Sacred Winds";
            case "memory_of_dust" : return "Memory of Dust";
            case "skyward_atlas" : return "Skyward Atlas";

            case "hunters_bow" : return "Hunter's Bow";
            case "seasoned_hunters_bow" : return "Seasoned Hunter's Bow";
            case "ebony_bow" : return "Ebony Bow";
            case "messenger" : return "Messenger";
            case "raven_bow" : return "Raven Bow";
            case "recurve_bow" : return "Recurve Bow";
            case "sharpshooters_oath" : return "Sharpshooter's Oath";
            case "slingshot" : return "Slingshot";
            case "alley_hunter" : return "Alley Hunter";
            case "blackcliff_warbow" : return "Blackcliff Warbow";
            case "compound_bow" : return "Compound Bow";
            case "favonius_warbow" : return "Favonius Warbow";
            case "hamayumi" : return "Hamayumi";
            case "mitternachts_waltz" : return "Mitternachts Waltz";
            case "predator" : return "Predator";
            case "prototype_crescent" : return "Prototype Crescent";
            case "royal_bow" : return "Royal Bow";
            case "rust" : return "Rust";
            case "sacrificial_bow" : return "Sacrificial Bow";
            case "the_stringless" : return "The Stringless";
            case "the_viridescent_hunt" : return "The Viridescent Hunt";
            case "windblume_ode" : return "Windblume Ode";
            case "amos_bow" : return "Amos' Bow";
            case "elegy_for_the_end" : return "Elegy for the End";
            case "skyward_harp" : return "Skyward Harp";
            case "thundering_pulse" : return "Thundering Pulse";

            // add in 20211030
            case "akuoumaru" : return "Akuoumaru";
            case "wavebreakers_fin" : return "Wavebreaker's Fin";
            case "mouuns_moon" : return "Mouun's Moon";
            // add in 20211127
            case "cinnabar_spindle" : return "Cinnabar Spindle";
            // add in 20220104
            case "redhorn_stonethresher" : return "Redhorn Stonethresher";
            case "calamity_queller" : return "Calamity Queller";
            // add in 20220126
            case "kaguras_verity" : return "Kagura's Verity";
            case "oathsworn_eye" : return "Oathsworn Eye";
            // add in 20220329
            case "haran_geppaku_futsu" : return "Haran Geppaku Futsu";
            // add in 20220521
            case "aqua_simulacra" : return "Aqua Simulacra";
            case "kagotsurube_isshin" : return "Kagotsurube Iisshin";
            case "fading_twilight" : return "Fading Twilight";
            //add in 20220714
            case "sapwood_blade" : return "Sapwood Blade";
            case "forest_regalia" : return "Forest Regalia";
            case "moonpiercer" : return "Moonpiercer";
            case "kings_squire" : return "King's Squire";
            case "end_of_the_line" : return "End of the Line";
            case "hunters_path" : return "Hunter's Path";
            case "fruit_of_fulfillment" : return "Fruit of Fulfillment" ;
            //add in 20220924
            case "xiphos_moonlight" : return "Xiphos' Moonlight" ;
            case "key_of_khaj_nisut" : return "Key of Khaj-Nisut" ;
            case "makhaira_aquamarine" : return "Makhaira Aquamarine" ;
            case "missive_windspear" : return "Missive Windspear" ;
            case "staff_of_the_scarlet_sands" : return "Staff of the Scarlet Sands" ;
            case "wandering_evenstar" : return "Wandering Evenstar" ;
            case "a_thousand_floating_dreams" : return "A Thousand Floating Dreams";
            //add in 20221210
            case "polar_star" : return "Polar Star" ;
            case "toukabou_shigure" : return "Toukabou Shigure" ;
            case "tulaytullahs_remembrance" : return "Tulaytullah's Remembrance";
            //add in 20230118
            case "light_of_foliar_incision" : return "Light of Foliar Incision";
            //add in 20230225
            case "mailed_flower" : return "Mailed Flower";
            case "beacon_of_the_reed_sea" : return "Beacon of the Reed Sea";
            //add in 20230416
            case "jadefalls_splendor" : return "Jadefall's Splendor";

            default: return str;
        }
    }

    public String[] getArtifactByName (String str, Context context){
        switch (str){
            case "Adventurer" : return new String[] {context.getString(R.string.adventurer),"/drawable/adventurer_1"+IMG_FORMAT,"/drawable/adventurer_2"+IMG_FORMAT,"/drawable/adventurer_3"+IMG_FORMAT,"/drawable/adventurer_4"+IMG_FORMAT,"/drawable/adventurer_5"+IMG_FORMAT};
            case "Archaic Petra" : return new String[] {context.getString(R.string.archaic_petra),"/drawable/archaic_petra_1"+IMG_FORMAT,"/drawable/archaic_petra_2"+IMG_FORMAT,"/drawable/archaic_petra_3"+IMG_FORMAT,"/drawable/archaic_petra_4"+IMG_FORMAT,"/drawable/archaic_petra_5"+IMG_FORMAT};
            case "Berserker" : return new String[] {context.getString(R.string.berserker),"/drawable/berserker_1"+IMG_FORMAT,"/drawable/berserker_2"+IMG_FORMAT,"/drawable/berserker_3"+IMG_FORMAT,"/drawable/berserker_4"+IMG_FORMAT,"/drawable/berserker_5"+IMG_FORMAT};
            case "Blizzard Strayer" : return new String[] {context.getString(R.string.blizzard_strayer),"/drawable/blizzard_strayer_1"+IMG_FORMAT,"/drawable/blizzard_strayer_2"+IMG_FORMAT,"/drawable/blizzard_strayer_3"+IMG_FORMAT,"/drawable/blizzard_strayer_4"+IMG_FORMAT,"/drawable/blizzard_strayer_5"+IMG_FORMAT};
            case "Bloodstained Chivalry" : return new String[] {context.getString(R.string.bloodstained_chivalry),"/drawable/bloodstained_chivalry_1"+IMG_FORMAT,"/drawable/bloodstained_chivalry_2"+IMG_FORMAT,"/drawable/bloodstained_chivalry_3"+IMG_FORMAT,"/drawable/bloodstained_chivalry_4"+IMG_FORMAT,"/drawable/bloodstained_chivalry_5"+IMG_FORMAT};
            case "Brave Heart" : return new String[] {context.getString(R.string.brave_heart),"/drawable/brave_heart_1"+IMG_FORMAT,"/drawable/brave_heart_2"+IMG_FORMAT,"/drawable/brave_heart_3"+IMG_FORMAT,"/drawable/brave_heart_4"+IMG_FORMAT,"/drawable/brave_heart_5"+IMG_FORMAT};
            case "Crimson Witch of Flames" : return new String[] {context.getString(R.string.crimson_witch_of_flames),"/drawable/crimson_witch_of_flames_1"+IMG_FORMAT,"/drawable/crimson_witch_of_flames_2"+IMG_FORMAT,"/drawable/crimson_witch_of_flames_3"+IMG_FORMAT,"/drawable/crimson_witch_of_flames_4"+IMG_FORMAT,"/drawable/crimson_witch_of_flames_5"+IMG_FORMAT};
            case "Defender's Will" : return new String[] {context.getString(R.string.defenders_will),"/drawable/defenders_will_1"+IMG_FORMAT,"/drawable/defenders_will_2"+IMG_FORMAT,"/drawable/defenders_will_3"+IMG_FORMAT,"/drawable/defenders_will_4"+IMG_FORMAT,"/drawable/defenders_will_5"+IMG_FORMAT};
            case "Emblem of Severed Fate" : return new String[] {context.getString(R.string.emblem_of_severed_fate),"/drawable/emblem_of_severed_fate_1"+IMG_FORMAT,"/drawable/emblem_of_severed_fate_2"+IMG_FORMAT,"/drawable/emblem_of_severed_fate_3"+IMG_FORMAT,"/drawable/emblem_of_severed_fate_4"+IMG_FORMAT,"/drawable/emblem_of_severed_fate_5"+IMG_FORMAT};
            case "Gambler" : return new String[] {context.getString(R.string.gambler),"/drawable/gambler_1"+IMG_FORMAT,"/drawable/gambler_2"+IMG_FORMAT,"/drawable/gambler_3"+IMG_FORMAT,"/drawable/gambler_4"+IMG_FORMAT,"/drawable/gambler_5"+IMG_FORMAT};
            case "Gladiator's Finale" : return new String[] {context.getString(R.string.gladiators_finale),"/drawable/gladiators_finale_1"+IMG_FORMAT,"/drawable/gladiators_finale_2"+IMG_FORMAT,"/drawable/gladiators_finale_3"+IMG_FORMAT,"/drawable/gladiators_finale_4"+IMG_FORMAT,"/drawable/gladiators_finale_5"+IMG_FORMAT};
            case "Heart of Depth" : return new String[] {context.getString(R.string.heart_of_depth),"/drawable/heart_of_depth_1"+IMG_FORMAT,"/drawable/heart_of_depth_2"+IMG_FORMAT,"/drawable/heart_of_depth_3"+IMG_FORMAT,"/drawable/heart_of_depth_4"+IMG_FORMAT,"/drawable/heart_of_depth_5"+IMG_FORMAT};
            case "Instructor" : return new String[] {context.getString(R.string.instructor),"/drawable/instructor_1"+IMG_FORMAT,"/drawable/instructor_2"+IMG_FORMAT,"/drawable/instructor_3"+IMG_FORMAT,"/drawable/instructor_4"+IMG_FORMAT,"/drawable/instructor_5"+IMG_FORMAT};
            case "Lavawalker" : return new String[] {context.getString(R.string.lavawalker),"/drawable/lavawalker_1"+IMG_FORMAT,"/drawable/lavawalker_2"+IMG_FORMAT,"/drawable/lavawalker_3"+IMG_FORMAT,"/drawable/lavawalker_4"+IMG_FORMAT,"/drawable/lavawalker_5"+IMG_FORMAT};
            case "Lucky Dog" : return new String[] {context.getString(R.string.lucky_dog),"/drawable/lucky_dog_1"+IMG_FORMAT,"/drawable/lucky_dog_2"+IMG_FORMAT,"/drawable/lucky_dog_3"+IMG_FORMAT,"/drawable/lucky_dog_4"+IMG_FORMAT,"/drawable/lucky_dog_5"+IMG_FORMAT};
            case "Maiden Beloved" : return new String[] {context.getString(R.string.maiden_beloved),"/drawable/maiden_beloved_1"+IMG_FORMAT,"/drawable/maiden_beloved_2"+IMG_FORMAT,"/drawable/maiden_beloved_3"+IMG_FORMAT,"/drawable/maiden_beloved_4"+IMG_FORMAT,"/drawable/maiden_beloved_5"+IMG_FORMAT};
            case "Martial Artist" : return new String[] {context.getString(R.string.martial_artist),"/drawable/martial_artist_1"+IMG_FORMAT,"/drawable/martial_artist_2"+IMG_FORMAT,"/drawable/martial_artist_3"+IMG_FORMAT,"/drawable/martial_artist_4"+IMG_FORMAT,"/drawable/martial_artist_5"+IMG_FORMAT};
            case "Noblesse Oblige" : return new String[] {context.getString(R.string.noblesse_oblige),"/drawable/noblesse_oblige_1"+IMG_FORMAT,"/drawable/noblesse_oblige_2"+IMG_FORMAT,"/drawable/noblesse_oblige_3"+IMG_FORMAT,"/drawable/noblesse_oblige_4"+IMG_FORMAT,"/drawable/noblesse_oblige_5"+IMG_FORMAT};
            case "Pale Flame" : return new String[] {context.getString(R.string.pale_flame),"/drawable/pale_flame_1"+IMG_FORMAT,"/drawable/pale_flame_2"+IMG_FORMAT,"/drawable/pale_flame_3"+IMG_FORMAT,"/drawable/pale_flame_4"+IMG_FORMAT,"/drawable/pale_flame_5"+IMG_FORMAT};
            case "Prayers of Destiny" : return new String[] {context.getString(R.string.prayers_of_destiny),"/drawable/prayers_of_destiny_4"+IMG_FORMAT,"/drawable/prayers_of_destiny_4"+IMG_FORMAT,"/drawable/prayers_of_destiny_4"+IMG_FORMAT,"/drawable/prayers_of_destiny_4"+IMG_FORMAT,"/drawable/prayers_of_destiny_4"+IMG_FORMAT};
            case "Prayers of Illumination" : return new String[] {context.getString(R.string.prayers_of_illumination),"/drawable/prayers_of_illumination_4"+IMG_FORMAT,"/drawable/prayers_of_illumination_4"+IMG_FORMAT,"/drawable/prayers_of_illumination_4"+IMG_FORMAT,"/drawable/prayers_of_illumination_4"+IMG_FORMAT,"/drawable/prayers_of_illumination_4"+IMG_FORMAT};
            case "Prayers of Wisdom" : return new String[] {context.getString(R.string.prayers_of_wisdom),"/drawable/prayers_of_wisdom_4"+IMG_FORMAT,"/drawable/prayers_of_wisdom_4"+IMG_FORMAT,"/drawable/prayers_of_wisdom_4"+IMG_FORMAT,"/drawable/prayers_of_wisdom_4"+IMG_FORMAT,"/drawable/prayers_of_wisdom_4"+IMG_FORMAT};
            case "Prayers of Springtime" : return new String[] {context.getString(R.string.prayers_of_springtime),"/drawable/prayers_of_springtime_4"+IMG_FORMAT,"/drawable/prayers_of_springtime_4"+IMG_FORMAT,"/drawable/prayers_of_springtime_4"+IMG_FORMAT,"/drawable/prayers_of_springtime_4"+IMG_FORMAT,"/drawable/prayers_of_springtime_4"+IMG_FORMAT};
            case "Resolution of Sojourner" : return new String[] {context.getString(R.string.resolution_of_sojourner),"/drawable/resolution_of_sojourner_1"+IMG_FORMAT,"/drawable/resolution_of_sojourner_2"+IMG_FORMAT,"/drawable/resolution_of_sojourner_3"+IMG_FORMAT,"/drawable/resolution_of_sojourner_4"+IMG_FORMAT,"/drawable/resolution_of_sojourner_5"+IMG_FORMAT};
            case "Retracing Bolide" : return new String[] {context.getString(R.string.retracing_bolide),"/drawable/retracing_bolide_1"+IMG_FORMAT,"/drawable/retracing_bolide_2"+IMG_FORMAT,"/drawable/retracing_bolide_3"+IMG_FORMAT,"/drawable/retracing_bolide_4"+IMG_FORMAT,"/drawable/retracing_bolide_5"+IMG_FORMAT};
            case "Scholar" : return new String[] {context.getString(R.string.scholar),"/drawable/scholar_1"+IMG_FORMAT,"/drawable/scholar_2"+IMG_FORMAT,"/drawable/scholar_3"+IMG_FORMAT,"/drawable/scholar_4"+IMG_FORMAT,"/drawable/scholar_5"+IMG_FORMAT};
            case "Shimenawa's Reminiscence" : return new String[] {context.getString(R.string.shimenawas_reminiscence),"/drawable/shimenawas_reminiscence_1"+IMG_FORMAT,"/drawable/shimenawas_reminiscence_2"+IMG_FORMAT,"/drawable/shimenawas_reminiscence_3"+IMG_FORMAT,"/drawable/shimenawas_reminiscence_4"+IMG_FORMAT,"/drawable/shimenawas_reminiscence_5"+IMG_FORMAT};
            case "Tenacity of the Millelith" : return new String[] {context.getString(R.string.tenacity_of_the_millelith),"/drawable/tenacity_of_the_millelith_1"+IMG_FORMAT,"/drawable/tenacity_of_the_millelith_2"+IMG_FORMAT,"/drawable/tenacity_of_the_millelith_3"+IMG_FORMAT,"/drawable/tenacity_of_the_millelith_4"+IMG_FORMAT,"/drawable/tenacity_of_the_millelith_5"+IMG_FORMAT};
            case "The Exile" : return new String[] {context.getString(R.string.the_exile),"/drawable/the_exile_1"+IMG_FORMAT,"/drawable/the_exile_2"+IMG_FORMAT,"/drawable/the_exile_3"+IMG_FORMAT,"/drawable/the_exile_4"+IMG_FORMAT,"/drawable/the_exile_5"+IMG_FORMAT};
            case "Thundering Fury" : return new String[] {context.getString(R.string.thundering_fury),"/drawable/thundering_fury_1"+IMG_FORMAT,"/drawable/thundering_fury_2"+IMG_FORMAT,"/drawable/thundering_fury_3"+IMG_FORMAT,"/drawable/thundering_fury_4"+IMG_FORMAT,"/drawable/thundering_fury_5"+IMG_FORMAT};
            case "Thunder-soother" : return new String[] {context.getString(R.string.thundersoother),"/drawable/thundersoother_1"+IMG_FORMAT,"/drawable/thundersoother_2"+IMG_FORMAT,"/drawable/thundersoother_3"+IMG_FORMAT,"/drawable/thundersoother_4"+IMG_FORMAT,"/drawable/thundersoother_5"+IMG_FORMAT};
            case "Tiny Miracle" : return new String[] {context.getString(R.string.tiny_miracle),"/drawable/tiny_miracle_1"+IMG_FORMAT,"/drawable/tiny_miracle_2"+IMG_FORMAT,"/drawable/tiny_miracle_3"+IMG_FORMAT,"/drawable/tiny_miracle_4"+IMG_FORMAT,"/drawable/tiny_miracle_5"+IMG_FORMAT};
            case "Traveling Doctor" : return new String[] {context.getString(R.string.traveling_doctor),"/drawable/traveling_doctor_1"+IMG_FORMAT,"/drawable/traveling_doctor_2"+IMG_FORMAT,"/drawable/traveling_doctor_3"+IMG_FORMAT,"/drawable/traveling_doctor_4"+IMG_FORMAT,"/drawable/traveling_doctor_5"+IMG_FORMAT};
            case "Viridescent Venerer" : return new String[] {context.getString(R.string.viridescent_venerer),"/drawable/viridescent_venerer_1"+IMG_FORMAT,"/drawable/viridescent_venerer_2"+IMG_FORMAT,"/drawable/viridescent_venerer_3"+IMG_FORMAT,"/drawable/viridescent_venerer_4"+IMG_FORMAT,"/drawable/viridescent_venerer_5"+IMG_FORMAT};
            case "Wanderer's Troupe" : return new String[] {context.getString(R.string.wanderers_troupe),"/drawable/wanderers_troupe_1"+IMG_FORMAT,"/drawable/wanderers_troupe_2"+IMG_FORMAT,"/drawable/wanderers_troupe_3"+IMG_FORMAT,"/drawable/wanderers_troupe_4"+IMG_FORMAT,"/drawable/wanderers_troupe_5"+IMG_FORMAT};

            // add in 20211127
            case "Husk of Opulent Dreams" : return new String[] {context.getString(R.string.husk_of_opulent_dreams),"/drawable/husk_of_opulent_dreams_1"+IMG_FORMAT,"/drawable/husk_of_opulent_dreams_2"+IMG_FORMAT,"/drawable/husk_of_opulent_dreams_3"+IMG_FORMAT,"/drawable/husk_of_opulent_dreams_4"+IMG_FORMAT,"/drawable/husk_of_opulent_dreams_5"+IMG_FORMAT};
            case "Ocean-Hued Clam" : return new String[] {context.getString(R.string.ocean_hued_clam),"/drawable/ocean_hued_clam_1"+IMG_FORMAT,"/drawable/ocean_hued_clam_2"+IMG_FORMAT,"/drawable/ocean_hued_clam_3"+IMG_FORMAT,"/drawable/ocean_hued_clam_4"+IMG_FORMAT,"/drawable/ocean_hued_clam_5"+IMG_FORMAT};
            // add in 20220329
            case "Echoes of an Offering" : return new String[] {context.getString(R.string.echoes_of_an_offering),"/drawable/echoes_of_an_offering_1"+IMG_FORMAT,"/drawable/echoes_of_an_offering_2"+IMG_FORMAT,"/drawable/echoes_of_an_offering_3"+IMG_FORMAT,"/drawable/echoes_of_an_offering_4"+IMG_FORMAT,"/drawable/echoes_of_an_offering_5"+IMG_FORMAT};
            case "Vermillion Hereafter" : return new String[] {context.getString(R.string.vermillion_hereafter),"/drawable/vermillion_hereafter_1"+IMG_FORMAT,"/drawable/vermillion_hereafter_2"+IMG_FORMAT,"/drawable/vermillion_hereafter_3"+IMG_FORMAT,"/drawable/vermillion_hereafter_4"+IMG_FORMAT,"/drawable/vermillion_hereafter_5"+IMG_FORMAT};
            case "Deepwood Memories" : return new String[] {context.getString(R.string.deepwood_memories),"/drawable/deepwood_memories_1"+IMG_FORMAT,"/drawable/deepwood_memories_2"+IMG_FORMAT,"/drawable/deepwood_memories_3"+IMG_FORMAT,"/drawable/deepwood_memories_4"+IMG_FORMAT,"/drawable/deepwood_memories_5"+IMG_FORMAT};
            case "Gilded Dreams" : return new String[] {context.getString(R.string.gilded_dreams),"/drawable/gilded_dreams_1"+IMG_FORMAT,"/drawable/gilded_dreams_2"+IMG_FORMAT,"/drawable/gilded_dreams_3"+IMG_FORMAT,"/drawable/gilded_dreams_4"+IMG_FORMAT,"/drawable/gilded_dreams_5"+IMG_FORMAT};
            // add in 20221210
            case "Desert Pavilion Chronicle" : return new String[] {context.getString(R.string.desert_pavilion_chronicle),"/drawable/desert_pavilion_chronicle_1"+IMG_FORMAT,"/drawable/desert_pavilion_chronicle_2"+IMG_FORMAT,"/drawable/desert_pavilion_chronicle_3"+IMG_FORMAT,"/drawable/desert_pavilion_chronicle_4"+IMG_FORMAT,"/drawable/desert_pavilion_chronicle_5"+IMG_FORMAT};
            case "Flower of Paradise Lost" : return new String[] {context.getString(R.string.flower_of_paradise_lost),"/drawable/flower_of_paradise_lost_1"+IMG_FORMAT,"/drawable/flower_of_paradise_lost_2"+IMG_FORMAT,"/drawable/flower_of_paradise_lost_3"+IMG_FORMAT,"/drawable/flower_of_paradise_lost_4"+IMG_FORMAT,"/drawable/flower_of_paradise_lost_5"+IMG_FORMAT};
            // add in 20230416
            case "Nymph's Dream" : return new String[] {context.getString(R.string.nymphs_dream),"/drawable/nymphs_dream_1"+IMG_FORMAT,"/drawable/nymphs_dream_2"+IMG_FORMAT,"/drawable/nymphs_dream_3"+IMG_FORMAT,"/drawable/nymphs_dream_4"+IMG_FORMAT,"/drawable/nymphs_dream_5"+IMG_FORMAT};
            case "Vourukasha's Glow" : return new String[] {context.getString(R.string.vourukashas_glow),"/drawable/vourukashas_glow_1"+IMG_FORMAT,"/drawable/vourukashas_glow_2"+IMG_FORMAT,"/drawable/vourukashas_glow_3"+IMG_FORMAT,"/drawable/vourukashas_glow_4"+IMG_FORMAT,"/drawable/vourukashas_glow_5"+IMG_FORMAT};

            default:
                String upcoming = str.toLowerCase().replace(" ","_");
                return new String[] {str,"/drawable/"+upcoming+"1"+IMG_FORMAT,"/drawable/"+upcoming+"2"+IMG_FORMAT,"/drawable/"+upcoming+"3"+IMG_FORMAT,"/drawable/"+upcoming+"4"+IMG_FORMAT,"/drawable/"+upcoming+"5"+IMG_FORMAT,};

        }
    }

    public String getArtifactNameByFileName (String str){
        switch (str){
            case "adventurer" : return "Adventurer";
            case "archaic_petra" : return "Archaic Petra";
            case "berserker" : return "Berserker";
            case "blizzard_strayer" : return "Blizzard Strayer";
            case "bloodstained_chivalry" : return "Bloodstained Chivalry";
            case "brave_heart" : return "Brave Heart";
            case "crimson_witch_of_flames" : return "Crimson Witch of Flames";
            case "defenders_will" : return "Defender's Will";
            case "emblem_of_severed_fate" : return "Emblem of Severed Fate";
            case "gambler" : return "Gambler";
            case "gladiators_finale" : return "Gladiator's Finale";
            case "heart_of_depth" : return "Heart of Depth";
            case "instructor" : return "Instructor";
            case "lavawalker" : return "Lavawalker";
            case "lucky_dog" : return "Lucky Dog";
            case "maiden_beloved" : return "Maiden Beloved";
            case "martial_artist" : return "Martial Artist";
            case "noblesse_oblige" : return "Noblesse Oblige";
            case "pale_flame" : return "Pale Flame";
            case "prayers_of_destiny" : return "Prayers of Destiny";
            case "prayers_of_illumination" : return "Prayers of Illumination";
            case "prayers_of_wisdom" : return "Prayers of Wisdom";
            case "prayers_of_springtime" : return "Prayers of Springtime";
            case "resolution_of_sojourner" : return "Resolution of Sojourner";
            case "retracing_bolide" : return "Retracing Bolide";
            case "scholar" : return "Scholar";
            case "shimenawas_reminiscence" : return "Shimenawa's Reminiscence";
            case "tenacity_of_the_millelith" : return "Tenacity of the Millelith";
            case "the_exile" : return "The Exile";
            case "thundering_fury" : return "Thundering Fury";
            case "thundersoother" : return "Thunder-soother";
            case "tiny_miracle" : return "Tiny Miracle";
            case "traveling_doctor" : return "Traveling Doctor";
            case "viridescent_venerer" : return "Viridescent Venerer";
            case "wanderers_troupe" : return "Wanderer's Troupe";
            // add in 20211127
            case "husk_of_opulent_dreams" : return "Husk of Opulent Dreams";
            case "ocean_hued_clam" : return "Ocean-Hued Clam";
            // add in 20220329
            case "echoes_of_an_offering" : return "Echoes of an Offering";
            case "vermillion_hereafter" : return "Vermillion Hereafter";
            // add in 20220821
            case "deepwood_memories" : return "Deepwood Memories";
            case "gilded_dreams" : return "Gilded Dreams";
            // add in 20221210
            case "desert_pavilion_chronicle" : return "Desert Pavilion Chronicle";
            case "flower_of_paradise_lost" : return "Flower of Paradise Lost";
            // add in 20230416
            case "nymphs_dream" : return "Nymph's Dream";
            case "vourukashas_glow" : return "Vourukasha's Glow";

            default: return  str;
        }
    }

    // Not continue to use anymore
    public String[] getTCGByName(String name, Context context){
        return getTCGByNameBase(name,context);
        /*
        switch (name){
            case "Barbara" : return new String[]{"/anim/tcg_anim_barbara.gif", context.getString(R.string.tcg_barbara)};
            case "Bennett" : return new String[]{"/anim/tcg_anim_bennett.gif", context.getString(R.string.tcg_bennett)};
            case "Chongyun" : return new String[]{"/anim/tcg_anim_chongyun.gif", context.getString(R.string.tcg_chongyun)};
            case "Collei" : return new String[]{"/anim/tcg_anim_collei.gif", context.getString(R.string.tcg_collei)};
            case "Cyno" : return new String[]{"/anim/tcg_anim_cyno.gif", context.getString(R.string.tcg_cyno)};
            case "Diluc" : return new String[]{"/anim/tcg_anim_diluc.gif", context.getString(R.string.tcg_diluc)};
            case "Diona" : return new String[]{"/anim/tcg_anim_diona.gif", context.getString(R.string.tcg_diona)};
            case "Fatui Pyro Agent" : return new String[]{"/anim/tcg_anim_fatui_pyro_agent.gif", context.getString(R.string.tcg_fatui_pyro_agent)};
            case "Fischl" : return new String[]{"/anim/tcg_anim_fischl.gif", context.getString(R.string.tcg_fischl)};
            case "Ganyu" : return new String[]{"/anim/tcg_anim_ganyu.gif", context.getString(R.string.tcg_ganyu)};
            case "Jadeplume Terrorshroom" : return new String[]{"/anim/tcg_anim_jadeplume_terrorshroom.gif", context.getString(R.string.tcg_jadeplume_terrorshroom)};
            case "Jean" : return new String[]{"/anim/tcg_anim_jean.gif", context.getString(R.string.tcg_jean)};
            case "Kaeya" : return new String[]{"/anim/tcg_anim_kaeya.gif", context.getString(R.string.tcg_kaeya)};
            case "Kamisato Ayaka" : return new String[]{"/anim/tcg_anim_kamisato_ayaka.gif", context.getString(R.string.tcg_kamisato_ayaka)};
            case "Keqing" : return new String[]{"/anim/tcg_anim_keqing.gif", context.getString(R.string.tcg_keqing)};
            case "Maguu Kenki" : return new String[]{"/anim/tcg_anim_maguu_kenki.gif", context.getString(R.string.tcg_maguu_kenki)};
            case "Mirror Maiden" : return new String[]{"/anim/tcg_anim_mirror_maiden.gif", context.getString(R.string.tcg_mirror_maiden)};
            case "Mona" : return new String[]{"/anim/tcg_anim_mona.gif", context.getString(R.string.tcg_mona)};
            case "Ningguang" : return new String[]{"/anim/tcg_anim_ningguang.gif", context.getString(R.string.tcg_ningguang)};
            case "Noelle" : return new String[]{"/anim/tcg_anim_noelle.gif", context.getString(R.string.tcg_noelle)};
            case "Razor" : return new String[]{"/anim/tcg_anim_razor.gif", context.getString(R.string.tcg_razor)};
            case "Rhodeia of Loch" : return new String[]{"/anim/tcg_anim_rhodeia_of_loch.gif", context.getString(R.string.tcg_rhodeia_of_loch)};
            case "Stonehide Lawachurl" : return new String[]{"/anim/tcg_anim_stonehide_lawachurl.gif", context.getString(R.string.tcg_stonehide_lawachurl)};
            case "Sucrose" : return new String[]{"/anim/tcg_anim_sucrose.gif", context.getString(R.string.tcg_sucrose)};
            case "Xiangling" : return new String[]{"/anim/tcg_anim_xiangling.gif", context.getString(R.string.tcg_xiangling)};
            case "Xingqiu" : return new String[]{"/anim/tcg_anim_xingqiu.gif", context.getString(R.string.tcg_xingqiu)};
            case "Yoimiya" : return new String[]{"/anim/tcg_anim_yoimiya.gif", context.getString(R.string.tcg_yoimiya)};
            //add in 20230118
            case "Klee" : return new String[]{"/anim/tcg_anim_klee.gif", context.getString(R.string.tcg_klee)};
            case "Beidou" : return new String[]{"/anim/tcg_anim_beidou.gif", context.getString(R.string.tcg_beidou)};

            default: return getTCGByNameBase(name,context);
        }
         */
    }

    // In Fact, we still haven't made enemy card.
    public String[] getTCGByNameBase(String name, Context context){
        switch (name){
            case "Ganyu" : return new String[]{"/drawable/tcg_ganyu"+IMG_FORMAT, context.getString(R.string.tcg_ganyu)};
            case "Kaeya" : return new String[]{"/drawable/tcg_kaeya"+IMG_FORMAT, context.getString(R.string.tcg_kaeya)};
            case "Chongyun" : return new String[]{"/drawable/tcg_chongyun"+IMG_FORMAT, context.getString(R.string.tcg_chongyun)};
            case "Kamisato Ayaka" : return new String[]{"/drawable/tcg_kamisato_ayaka"+IMG_FORMAT, context.getString(R.string.tcg_kamisato_ayaka)};
            case "Xingqiu" : return new String[]{"/drawable/tcg_xingqiu"+IMG_FORMAT, context.getString(R.string.tcg_xingqiu)};
            case "Mona" : return new String[]{"/drawable/tcg_mona"+IMG_FORMAT, context.getString(R.string.tcg_mona)};
            case "Diluc" : return new String[]{"/drawable/tcg_diluc"+IMG_FORMAT, context.getString(R.string.tcg_diluc)};
            case "Xiangling" : return new String[]{"/drawable/tcg_xiangling"+IMG_FORMAT, context.getString(R.string.tcg_xiangling)};
            case "Bennett" : return new String[]{"/drawable/tcg_bennett"+IMG_FORMAT, context.getString(R.string.tcg_bennett)};
            case "Yoimiya" : return new String[]{"/drawable/tcg_yoimiya"+IMG_FORMAT, context.getString(R.string.tcg_yoimiya)};
            case "Fischl" : return new String[]{"/drawable/tcg_fischl"+IMG_FORMAT, context.getString(R.string.tcg_fischl)};
            case "Razor" : return new String[]{"/drawable/tcg_razor"+IMG_FORMAT, context.getString(R.string.tcg_razor)};
            case "Keqing" : return new String[]{"/drawable/tcg_keqing"+IMG_FORMAT, context.getString(R.string.tcg_keqing)};
            case "Sucrose" : return new String[]{"/drawable/tcg_sucrose"+IMG_FORMAT, context.getString(R.string.tcg_sucrose)};
            case "Jean" : return new String[]{"/drawable/tcg_jean"+IMG_FORMAT, context.getString(R.string.tcg_jean)};
            case "Ningguang" : return new String[]{"/drawable/tcg_ningguang"+IMG_FORMAT, context.getString(R.string.tcg_ningguang)};
            case "Noelle" : return new String[]{"/drawable/tcg_noelle"+IMG_FORMAT, context.getString(R.string.tcg_noelle)};
            case "Collei" : return new String[]{"/drawable/tcg_collei"+IMG_FORMAT, context.getString(R.string.tcg_collei)};
            case "Rhodeia of Loch" : return new String[]{"/drawable/tcg_rhodeia_of_loch"+IMG_FORMAT, context.getString(R.string.tcg_rhodeia_of_loch)};
            case "Fatui Pyro Agent" : return new String[]{"/drawable/tcg_fatui_pyro_agent"+IMG_FORMAT, context.getString(R.string.tcg_fatui_pyro_agent)};
            case "Maguu Kenki" : return new String[]{"/drawable/tcg_maguu_kenki"+IMG_FORMAT, context.getString(R.string.tcg_maguu_kenki)};
            case "Stonehide Lawachurl" : return new String[]{"/drawable/tcg_stonehide_lawachurl"+IMG_FORMAT, context.getString(R.string.tcg_stonehide_lawachurl)};
            case "Diona" : return new String[]{"/drawable/tcg_diona"+IMG_FORMAT, context.getString(R.string.tcg_diona)};
            case "Cyno" : return new String[]{"/drawable/tcg_cyno"+IMG_FORMAT, context.getString(R.string.tcg_cyno)};
            case "Barbara" : return new String[]{"/drawable/tcg_barbara"+IMG_FORMAT, context.getString(R.string.tcg_barbara)};
            case "Mirror Maiden" : return new String[]{"/drawable/tcg_mirror_maiden"+IMG_FORMAT, context.getString(R.string.tcg_mirror_maiden)};
            case "Jadeplume Terrorshroom" : return new String[]{"/drawable/tcg_jadeplume_terrorshroom"+IMG_FORMAT, context.getString(R.string.tcg_jadeplume_terrorshroom)};
            case "Undivided Heart" : return new String[]{"/drawable/tcg_undivided_heart"+IMG_FORMAT, context.getString(R.string.tcg_undivided_heart)};
            case "Cold-Blooded Strike" : return new String[]{"/drawable/tcg_cold_blooded_strike"+IMG_FORMAT, context.getString(R.string.tcg_cold_blooded_strike)};
            case "Steady Breathing" : return new String[]{"/drawable/tcg_steady_breathing"+IMG_FORMAT, context.getString(R.string.tcg_steady_breathing)};
            case "Kanten Senmyou Blessing" : return new String[]{"/drawable/tcg_kanten_senmyou_blessing"+IMG_FORMAT, context.getString(R.string.tcg_kanten_senmyou_blessing)};
            case "The Scent Remained" : return new String[]{"/drawable/tcg_the_scent_remained"+IMG_FORMAT, context.getString(R.string.tcg_the_scent_remained)};
            case "Prophecy of Submersion" : return new String[]{"/drawable/tcg_prophecy_of_submersion"+IMG_FORMAT, context.getString(R.string.tcg_prophecy_of_submersion)};
            case "Flowing Flame" : return new String[]{"/drawable/tcg_flowing_flame"+IMG_FORMAT, context.getString(R.string.tcg_flowing_flame)};
            case "Crossfire" : return new String[]{"/drawable/tcg_crossfire"+IMG_FORMAT, context.getString(R.string.tcg_crossfire)};
            case "Grand Expectation" : return new String[]{"/drawable/tcg_grand_expectation"+IMG_FORMAT, context.getString(R.string.tcg_grand_expectation)};
            case "Naganohara Meteor Swarm" : return new String[]{"/drawable/tcg_naganohara_meteor_swarm"+IMG_FORMAT, context.getString(R.string.tcg_naganohara_meteor_swarm)};
            case "Stellar Predator" : return new String[]{"/drawable/tcg_stellar_predator"+IMG_FORMAT, context.getString(R.string.tcg_stellar_predator)};
            case "Awakening" : return new String[]{"/drawable/tcg_awakening"+IMG_FORMAT, context.getString(R.string.tcg_awakening)};
            case "Thundering Penance" : return new String[]{"/drawable/tcg_thundering_penance"+IMG_FORMAT, context.getString(R.string.tcg_thundering_penance)};
            case "Chaotic Entropy" : return new String[]{"/drawable/tcg_chaotic_entropy"+IMG_FORMAT, context.getString(R.string.tcg_chaotic_entropy)};
            case "Lands of Dandelion" : return new String[]{"/drawable/tcg_lands_of_dandelion"+IMG_FORMAT, context.getString(R.string.tcg_lands_of_dandelion)};
            case "Strategic Reserve" : return new String[]{"/drawable/tcg_strategic_reserve"+IMG_FORMAT, context.getString(R.string.tcg_strategic_reserve)};
            case "I Got Your Back" : return new String[]{"/drawable/tcg_i_got_your_back"+IMG_FORMAT, context.getString(R.string.tcg_i_got_your_back)};
            case "Floral Sidewinder" : return new String[]{"/drawable/tcg_floral_sidewinder"+IMG_FORMAT, context.getString(R.string.tcg_floral_sidewinder)};
            case "Streaming Surge" : return new String[]{"/drawable/tcg_streaming_surge"+IMG_FORMAT, context.getString(R.string.tcg_streaming_surge)};
            case "Paid in Full" : return new String[]{"/drawable/tcg_paid_in_full"+IMG_FORMAT, context.getString(R.string.tcg_paid_in_full)};
            case "Transcendent Automaton" : return new String[]{"/drawable/tcg_transcendent_automaton"+IMG_FORMAT, context.getString(R.string.tcg_transcendent_automaton)};
            case "Stonehide Reforged" : return new String[]{"/drawable/tcg_stonehide_reforged"+IMG_FORMAT, context.getString(R.string.tcg_stonehide_reforged)};
            case "Shaken, Not Purred" : return new String[]{"/drawable/tcg_shaken_not_purred"+IMG_FORMAT, context.getString(R.string.tcg_shaken_not_purred)};
            case "Featherfall Judgment" : return new String[]{"/drawable/tcg_featherfall_judgment"+IMG_FORMAT, context.getString(R.string.tcg_featherfall_judgment)};
            case "Glorious Season" : return new String[]{"/drawable/tcg_glorious_season"+IMG_FORMAT, context.getString(R.string.tcg_glorious_season)};
            case "Mirror Cage" : return new String[]{"/drawable/tcg_mirror_cage"+IMG_FORMAT, context.getString(R.string.tcg_mirror_cage)};
            case "Proliferating Spores" : return new String[]{"/drawable/tcg_proliferating_spores"+IMG_FORMAT, context.getString(R.string.tcg_proliferating_spores)};
            case "Magic Guide" : return new String[]{"/drawable/tcg_magic_guide"+IMG_FORMAT, context.getString(R.string.tcg_magic_guide)};
            case "Sacrificial Fragments" : return new String[]{"/drawable/tcg_sacrificial_fragments"+IMG_FORMAT, context.getString(R.string.tcg_sacrificial_fragments)};
            case "Skyward Atlas" : return new String[]{"/drawable/tcg_skyward_atlas"+IMG_FORMAT, context.getString(R.string.tcg_skyward_atlas)};
            case "Raven Bow" : return new String[]{"/drawable/tcg_raven_bow"+IMG_FORMAT, context.getString(R.string.tcg_raven_bow)};
            case "Sacrificial Bow" : return new String[]{"/drawable/tcg_sacrificial_bow"+IMG_FORMAT, context.getString(R.string.tcg_sacrificial_bow)};
            case "Skyward Harp" : return new String[]{"/drawable/tcg_skyward_harp"+IMG_FORMAT, context.getString(R.string.tcg_skyward_harp)};
            case "White Iron Greatsword" : return new String[]{"/drawable/tcg_white_iron_greatsword"+IMG_FORMAT, context.getString(R.string.tcg_white_iron_greatsword)};
            case "Sacrificial Greatsword" : return new String[]{"/drawable/tcg_sacrificial_greatsword"+IMG_FORMAT, context.getString(R.string.tcg_sacrificial_greatsword)};
            case "Wolf's Gravestone" : return new String[]{"/drawable/tcg_wolfs_gravestone"+IMG_FORMAT, context.getString(R.string.tcg_wolfs_gravestone)};
            case "White Tassel" : return new String[]{"/drawable/tcg_white_tassel"+IMG_FORMAT, context.getString(R.string.tcg_white_tassel)};
            case "Lithic Spear" : return new String[]{"/drawable/tcg_lithic_spear"+IMG_FORMAT, context.getString(R.string.tcg_lithic_spear)};
            case "Skyward Spine" : return new String[]{"/drawable/tcg_skyward_spine"+IMG_FORMAT, context.getString(R.string.tcg_skyward_spine)};
            case "Traveler's Handy Sword" : return new String[]{"/drawable/tcg_travelers_handy_sword"+IMG_FORMAT, context.getString(R.string.tcg_travelers_handy_sword)};
            case "Sacrificial Sword" : return new String[]{"/drawable/tcg_sacrificial_sword"+IMG_FORMAT, context.getString(R.string.tcg_sacrificial_sword)};
            case "Aquila Favonia" : return new String[]{"/drawable/tcg_aquila_favonia"+IMG_FORMAT, context.getString(R.string.tcg_aquila_favonia)};
            case "Adventurer's Bandana" : return new String[]{"/drawable/tcg_adventurers_bandana"+IMG_FORMAT, context.getString(R.string.tcg_adventurers_bandana)};
            case "Lucky Dog's Silver Circlet" : return new String[]{"/drawable/tcg_lucky_dogs_silver_circlet"+IMG_FORMAT, context.getString(R.string.tcg_lucky_dogs_silver_circlet)};
            case "Traveling Doctor's Handkerchief" : return new String[]{"/drawable/tcg_traveling_doctors_handkerchief"+IMG_FORMAT, context.getString(R.string.tcg_traveling_doctors_handkerchief)};
            case "Gambler's Earrings" : return new String[]{"/drawable/tcg_gamblers_earrings"+IMG_FORMAT, context.getString(R.string.tcg_gamblers_earrings)};
            case "Instructor's Cap" : return new String[]{"/drawable/tcg_instructors_cap"+IMG_FORMAT, context.getString(R.string.tcg_instructors_cap)};
            case "Exile's Circlet" : return new String[]{"/drawable/tcg_exiles_circlet"+IMG_FORMAT, context.getString(R.string.tcg_exiles_circlet)};
            case "Broken Rime's Echo" : return new String[]{"/drawable/tcg_broken_rimes_echo"+IMG_FORMAT, context.getString(R.string.tcg_broken_rimes_echo)};
            case "Blizzard Strayer" : return new String[]{"/drawable/tcg_blizzard_strayer"+IMG_FORMAT, context.getString(R.string.tcg_blizzard_strayer)};
            case "Wine-Stained Tricorne" : return new String[]{"/drawable/tcg_wine_stained_tricorne"+IMG_FORMAT, context.getString(R.string.tcg_wine_stained_tricorne)};
            case "Heart of Depth" : return new String[]{"/drawable/tcg_heart_of_depth"+IMG_FORMAT, context.getString(R.string.tcg_heart_of_depth)};
            case "Witch's Scorching Hat" : return new String[]{"/drawable/tcg_witchs_scorching_hat"+IMG_FORMAT, context.getString(R.string.tcg_witchs_scorching_hat)};
            case "Crimson Witch of Flames" : return new String[]{"/drawable/tcg_crimson_witch_of_flames"+IMG_FORMAT, context.getString(R.string.tcg_crimson_witch_of_flames)};
            case "Thunder Summoner's Crown" : return new String[]{"/drawable/tcg_thunder_summoners_crown"+IMG_FORMAT, context.getString(R.string.tcg_thunder_summoners_crown)};
            case "Thundering Fury" : return new String[]{"/drawable/tcg_thundering_fury"+IMG_FORMAT, context.getString(R.string.tcg_thundering_fury)};
            case "Viridescent Venerer's Diadem" : return new String[]{"/drawable/tcg_viridescent_venerers_diadem"+IMG_FORMAT, context.getString(R.string.tcg_viridescent_venerers_diadem)};
            case "Viridescent Venerer" : return new String[]{"/drawable/tcg_viridescent_venerer"+IMG_FORMAT, context.getString(R.string.tcg_viridescent_venerer)};
            case "Mask of Solitude Basalt" : return new String[]{"/drawable/tcg_mask_of_solitude_basalt"+IMG_FORMAT, context.getString(R.string.tcg_mask_of_solitude_basalt)};
            case "Archaic Petra" : return new String[]{"/drawable/tcg_archaic_petra"+IMG_FORMAT, context.getString(R.string.tcg_archaic_petra)};
            case "Laurel Coronet" : return new String[]{"/drawable/tcg_laurel_coronet"+IMG_FORMAT, context.getString(R.string.tcg_laurel_coronet)};
            case "Deepwood Memories" : return new String[]{"/drawable/tcg_deepwood_memories"+IMG_FORMAT, context.getString(R.string.tcg_deepwood_memories)};
            case "Liyue Harbor Wharf" : return new String[]{"/drawable/tcg_liyue_harbor_wharf"+IMG_FORMAT, context.getString(R.string.tcg_liyue_harbor_wharf)};
            case "Knights of Favonius Library" : return new String[]{"/drawable/tcg_knights_of_favonius_library"+IMG_FORMAT, context.getString(R.string.tcg_knights_of_favonius_library)};
            case "Jade Chamber" : return new String[]{"/drawable/tcg_jade_chamber"+IMG_FORMAT, context.getString(R.string.tcg_jade_chamber)};
            case "Dawn Winery" : return new String[]{"/drawable/tcg_dawn_winery"+IMG_FORMAT, context.getString(R.string.tcg_dawn_winery)};
            case "Wangshu Inn" : return new String[]{"/drawable/tcg_wangshu_inn"+IMG_FORMAT, context.getString(R.string.tcg_wangshu_inn)};
            case "Favonius Cathedral" : return new String[]{"/drawable/tcg_favonius_cathedral"+IMG_FORMAT, context.getString(R.string.tcg_favonius_cathedral)};
            case "Paimon" : return new String[]{"/drawable/tcg_paimon"+IMG_FORMAT, context.getString(R.string.tcg_paimon)};
            case "Katheryne" : return new String[]{"/drawable/tcg_katheryne"+IMG_FORMAT, context.getString(R.string.tcg_katheryne)};
            case "Timaeus" : return new String[]{"/drawable/tcg_timaeus"+IMG_FORMAT, context.getString(R.string.tcg_timaeus)};
            case "Wagner" : return new String[]{"/drawable/tcg_wagner"+IMG_FORMAT, context.getString(R.string.tcg_wagner)};
            case "Chef Mao" : return new String[]{"/drawable/tcg_chef_mao"+IMG_FORMAT, context.getString(R.string.tcg_chef_mao)};
            case "Tubby" : return new String[]{"/drawable/tcg_tubby"+IMG_FORMAT, context.getString(R.string.tcg_tubby)};
            case "Timmie" : return new String[]{"/drawable/tcg_timmie"+IMG_FORMAT, context.getString(R.string.tcg_timmie)};
            case "Liben" : return new String[]{"/drawable/tcg_liben"+IMG_FORMAT, context.getString(R.string.tcg_liben)};
            case "Chang the Ninth" : return new String[]{"/drawable/tcg_chang_the_ninth"+IMG_FORMAT, context.getString(R.string.tcg_chang_the_ninth)};
            case "Ellin" : return new String[]{"/drawable/tcg_ellin"+IMG_FORMAT, context.getString(R.string.tcg_ellin)};
            case "Iron Tongue Tian" : return new String[]{"/drawable/tcg_iron_tongue_tian"+IMG_FORMAT, context.getString(R.string.tcg_iron_tongue_tian)};
            case "Liu Su" : return new String[]{"/drawable/tcg_liu_su"+IMG_FORMAT, context.getString(R.string.tcg_liu_su)};
            case "Parametric Transformer" : return new String[]{"/drawable/tcg_parametric_transformer"+IMG_FORMAT, context.getString(R.string.tcg_parametric_transformer)};
            case "NRE" : return new String[]{"/drawable/tcg_nre"+IMG_FORMAT, context.getString(R.string.tcg_nre)};
            case "Elemental Resonance: Woven Ice" : return new String[]{"/drawable/tcg_elemental_resonance_woven_ice"+IMG_FORMAT, context.getString(R.string.tcg_elemental_resonance_woven_ice)};
            case "Elemental Resonance: Shattering Ice" : return new String[]{"/drawable/tcg_elemental_resonance_shattering_ice"+IMG_FORMAT, context.getString(R.string.tcg_elemental_resonance_shattering_ice)};
            case "Elemental Resonance: Woven Waters" : return new String[]{"/drawable/tcg_elemental_resonance_woven_waters"+IMG_FORMAT, context.getString(R.string.tcg_elemental_resonance_woven_waters)};
            case "Elemental Resonance: Soothing Water" : return new String[]{"/drawable/tcg_elemental_resonance_soothing_water"+IMG_FORMAT, context.getString(R.string.tcg_elemental_resonance_soothing_water)};
            case "Elemental Resonance: Woven Flames" : return new String[]{"/drawable/tcg_elemental_resonance_woven_flames"+IMG_FORMAT, context.getString(R.string.tcg_elemental_resonance_woven_flames)};
            case "Elemental Resonance: Fervent Flames" : return new String[]{"/drawable/tcg_elemental_resonance_fervent_flames"+IMG_FORMAT, context.getString(R.string.tcg_elemental_resonance_fervent_flames)};
            case "Elemental Resonance: Woven Thunder" : return new String[]{"/drawable/tcg_elemental_resonance_woven_thunder"+IMG_FORMAT, context.getString(R.string.tcg_elemental_resonance_woven_thunder)};
            case "Elemental Resonance: High Voltage" : return new String[]{"/drawable/tcg_elemental_resonance_high_voltage"+IMG_FORMAT, context.getString(R.string.tcg_elemental_resonance_high_voltage)};
            case "Elemental Resonance: Woven Winds" : return new String[]{"/drawable/tcg_elemental_resonance_woven_winds"+IMG_FORMAT, context.getString(R.string.tcg_elemental_resonance_woven_winds)};
            case "Elemental Resonance: Impetuous Winds" : return new String[]{"/drawable/tcg_elemental_resonance_impetuous_winds"+IMG_FORMAT, context.getString(R.string.tcg_elemental_resonance_impetuous_winds)};
            case "Elemental Resonance: Woven Stone" : return new String[]{"/drawable/tcg_elemental_resonance_woven_stone"+IMG_FORMAT, context.getString(R.string.tcg_elemental_resonance_woven_stone)};
            case "Elemental Resonance: Enduring Rock" : return new String[]{"/drawable/tcg_elemental_resonance_enduring_rock"+IMG_FORMAT, context.getString(R.string.tcg_elemental_resonance_enduring_rock)};
            case "Elemental Resonance: Woven Weeds" : return new String[]{"/drawable/tcg_elemental_resonance_woven_weeds"+IMG_FORMAT, context.getString(R.string.tcg_elemental_resonance_woven_weeds)};
            case "Elemental Resonance: Sprawling Greenery" : return new String[]{"/drawable/tcg_elemental_resonance_sprawling_greenery"+IMG_FORMAT, context.getString(R.string.tcg_elemental_resonance_sprawling_greenery)};
            case "The Bestest Travel Companion!" : return new String[]{"/drawable/tcg_the_bestest_travel_companion"+IMG_FORMAT, context.getString(R.string.tcg_the_bestest_travel_companion)};
            case "Changing Shifts" : return new String[]{"/drawable/tcg_changing_shifts"+IMG_FORMAT, context.getString(R.string.tcg_changing_shifts)};
            case "Toss-Up" : return new String[]{"/drawable/tcg_toss_up"+IMG_FORMAT, context.getString(R.string.tcg_toss_up)};
            case "Strategize" : return new String[]{"/drawable/tcg_strategize"+IMG_FORMAT, context.getString(R.string.tcg_strategize)};
            case "I Haven't Lost Yet!" : return new String[]{"/drawable/tcg_i_havent_lost_yet"+IMG_FORMAT, context.getString(R.string.tcg_i_havent_lost_yet)};
            case "Leave it to Me!" : return new String[]{"/drawable/tcg_leave_it_to_me"+IMG_FORMAT, context.getString(R.string.tcg_leave_it_to_me)};
            case "When the Crane Returned" : return new String[]{"/drawable/tcg_when_the_crane_returned"+IMG_FORMAT, context.getString(R.string.tcg_when_the_crane_returned)};
            case "Starsigns" : return new String[]{"/drawable/tcg_starsigns"+IMG_FORMAT, context.getString(R.string.tcg_starsigns)};
            case "Calx's Arts" : return new String[]{"/drawable/tcg_calxs_arts"+IMG_FORMAT, context.getString(R.string.tcg_calxs_arts)};
            case "Master of Weaponry" : return new String[]{"/drawable/tcg_master_of_weaponry"+IMG_FORMAT, context.getString(R.string.tcg_master_of_weaponry)};
            case "Blessing of the Divine Relic's Installation" : return new String[]{"/drawable/tcg_blessing_of_the_divine_relics_installation"+IMG_FORMAT, context.getString(R.string.tcg_blessing_of_the_divine_relics_installation)};
            case "Quick Knit" : return new String[]{"/drawable/tcg_quick_knit"+IMG_FORMAT, context.getString(R.string.tcg_quick_knit)};
            case "Send Off" : return new String[]{"/drawable/tcg_send_off"+IMG_FORMAT, context.getString(R.string.tcg_send_off)};
            case "Guardian's Oath" : return new String[]{"/drawable/tcg_guardians_oath"+IMG_FORMAT, context.getString(R.string.tcg_guardians_oath)};
            case "Abyssal Summons" : return new String[]{"/drawable/tcg_abyssal_summons"+IMG_FORMAT, context.getString(R.string.tcg_abyssal_summons)};
            case "Jueyun Guoba" : return new String[]{"/drawable/tcg_jueyun_guoba"+IMG_FORMAT, context.getString(R.string.tcg_jueyun_guoba)};
            case "Adeptus' Temptation" : return new String[]{"/drawable/tcg_adeptus_temptation"+IMG_FORMAT, context.getString(R.string.tcg_adeptus_temptation)};
            case "Lotus Flower Crisp" : return new String[]{"/drawable/tcg_lotus_flower_crisp"+IMG_FORMAT, context.getString(R.string.tcg_lotus_flower_crisp)};
            case "Northern Smoked Chicken" : return new String[]{"/drawable/tcg_northern_smoked_chicken"+IMG_FORMAT, context.getString(R.string.tcg_northern_smoked_chicken)};
            case "Sweet Madame" : return new String[]{"/drawable/tcg_sweet_madame"+IMG_FORMAT, context.getString(R.string.tcg_sweet_madame)};
            case "Mondstadt Hash Brown" : return new String[]{"/drawable/tcg_mondstadt_hash_brown"+IMG_FORMAT, context.getString(R.string.tcg_mondstadt_hash_brown)};
            case "Mushroom Pizza" : return new String[]{"/drawable/tcg_mushroom_pizza"+IMG_FORMAT, context.getString(R.string.tcg_mushroom_pizza)};
            case "Minty Meat Rolls" : return new String[]{"/drawable/tcg_minty_meat_rolls"+IMG_FORMAT, context.getString(R.string.tcg_minty_meat_rolls)};
            case "Origin" : return new String[]{"/drawable/tcg_origin"+IMG_FORMAT, context.getString(R.string.tcg_origin)};
            case "Mondstadt" : return new String[]{"/drawable/tcg_mondstadt"+IMG_FORMAT, context.getString(R.string.tcg_mondstadt)};
            case "Liyue" : return new String[]{"/drawable/tcg_liyue"+IMG_FORMAT, context.getString(R.string.tcg_liyue)};
            case "Inazuma" : return new String[]{"/drawable/tcg_inazuma"+IMG_FORMAT, context.getString(R.string.tcg_inazuma)};
            case "Sumeru" : return new String[]{"/drawable/tcg_sumeru"+IMG_FORMAT, context.getString(R.string.tcg_sumeru)};
            case "Legend" : return new String[]{"/drawable/tcg_legend"+IMG_FORMAT, context.getString(R.string.tcg_legend)};
            case "Adept" : return new String[]{"/drawable/tcg_adept"+IMG_FORMAT, context.getString(R.string.tcg_adept)};
            case "Hydro Crystal Chunk" : return new String[]{"/drawable/tcg_hydro_crystal_chunk"+IMG_FORMAT, context.getString(R.string.tcg_hydro_crystal_chunk)};
            case "Magical Crystal Chunk" : return new String[]{"/drawable/tcg_magical_crystal_chunk"+IMG_FORMAT, context.getString(R.string.tcg_magical_crystal_chunk)};
            case "Amethyst Lump" : return new String[]{"/drawable/tcg_amethyst_lump"+IMG_FORMAT, context.getString(R.string.tcg_amethyst_lump)};
            case "Flaming Flower Stamen" : return new String[]{"/drawable/tcg_flaming_flower_stamen"+IMG_FORMAT, context.getString(R.string.tcg_flaming_flower_stamen)};
            case "Mist Flower Corolla" : return new String[]{"/drawable/tcg_mist_flower_corolla"+IMG_FORMAT, context.getString(R.string.tcg_mist_flower_corolla)};
            case "Electro Crystal" : return new String[]{"/drawable/tcg_electro_crystal"+IMG_FORMAT, context.getString(R.string.tcg_electro_crystal)};
            case "Dandelion Seed" : return new String[]{"/drawable/tcg_dandelion_seed"+IMG_FORMAT, context.getString(R.string.tcg_dandelion_seed)};
            case "Cecilia" : return new String[]{"/drawable/tcg_cecilia"+IMG_FORMAT, context.getString(R.string.tcg_cecilia)};
            case "Glaze Lily" : return new String[]{"/drawable/tcg_glaze_lily"+IMG_FORMAT, context.getString(R.string.tcg_glaze_lily)};
            case "Dendrobium" : return new String[]{"/drawable/tcg_dendrobium"+IMG_FORMAT, context.getString(R.string.tcg_dendrobium)};
            //add in 20230118
            case "Klee" : return new String[]{"/drawable/tcg_klee"+IMG_FORMAT, context.getString(R.string.tcg_klee)};
            case "Beidou" : return new String[]{"/drawable/tcg_beidou"+IMG_FORMAT, context.getString(R.string.tcg_beidou)};
            case "Lightning Storm" : return new String[]{"/drawable/tcg_lightning_storm"+IMG_FORMAT, context.getString(R.string.tcg_lightning_storm)};
            case "Pounding Surprise" : return new String[]{"/drawable/tcg_pounding_surprise"+IMG_FORMAT, context.getString(R.string.tcg_pounding_surprise)};
            //add in 20230226
            case "Eula" : return new String[]{"/drawable/tcg_eula"+IMG_FORMAT, context.getString(R.string.tcg_eula)};
            case "Sangonomiya Kokomi" : return new String[]{"/drawable/tcg_sangonomiya_kokomi"+IMG_FORMAT, context.getString(R.string.tcg_sangonomiya_kokomi)};
            case "Kujou Sara" : return new String[]{"/drawable/tcg_kujou_sara"+IMG_FORMAT, context.getString(R.string.tcg_kujou_sara)};
            case "Wellspring of War-Lust" : return new String[]{"/drawable/tcg_wellspring_of_war_lust"+IMG_FORMAT, context.getString(R.string.tcg_wellspring_of_war_lust)};
            case "Tamanooya's Casket" : return new String[]{"/drawable/tcg_tamanooyas_casket"+IMG_FORMAT, context.getString(R.string.tcg_tamanooyas_casket)};
            case "Sin of Pride" : return new String[]{"/drawable/tcg_sin_of_pride"+IMG_FORMAT, context.getString(R.string.tcg_sin_of_pride)};
            case "Ornate Kabuto" : return new String[]{"/drawable/tcg_ornate_kabuto"+IMG_FORMAT, context.getString(R.string.tcg_ornate_kabuto)};
            case "General's Ancient Helm" : return new String[]{"/drawable/tcg_generals_ancient_helm"+IMG_FORMAT, context.getString(R.string.tcg_generals_ancient_helm)};
            //add in 20230416
            case "Kamisato Ayato" : return new String[]{"/drawable/tcg_kamisato_ayato"+IMG_FORMAT, context.getString(R.string.tcg_kamisato_ayato)};
            case "Arataki Itto" : return new String[]{"/drawable/tcg_arataki_itto"+IMG_FORMAT, context.getString(R.string.tcg_arataki_itto)};
            case "Tighnari" : return new String[]{"/drawable/tcg_tighnari"+IMG_FORMAT, context.getString(R.string.tcg_tighnari)};
            case "Keen Sight" : return new String[]{"/drawable/tcg_keen_sight"+IMG_FORMAT, context.getString(R.string.tcg_keen_sight)};
            case "Kyouka Fuushi" : return new String[]{"/drawable/tcg_kyouka_fuushi"+IMG_FORMAT, context.getString(R.string.tcg_kyouka_fuushi)};
            case "Arataki Ichiban" : return new String[]{"/drawable/tcg_arataki_ichiban"+IMG_FORMAT, context.getString(R.string.tcg_arataki_ichiban)};
            case "Favonius Sword" : return new String[]{"/drawable/tcg_favonius_sword"+IMG_FORMAT, context.getString(R.string.tcg_favonius_sword)};
            case "Grand Narukami Shrine" : return new String[]{"/drawable/tcg_grand_narukami_shrine"+IMG_FORMAT, context.getString(R.string.tcg_grand_narukami_shrine)};


            default: return new String[]{
                    "/drawable/tcg"+name.toLowerCase()
                            .replace("'","")
                            .replace(":","")
                            .replace(" ","_")
                            .replace(",","")
                            .replace("-","_")
                            +""+IMG_FORMAT, name};

        }
    }

    // Since it isn't in use, so 3.4 wont update it.. 20230118
    public String getTCGByFileName(String name){
        switch (name){
            case "tcg_ganyu" : return "Ganyu";
            case "tcg_kaeya" : return "Kaeya";
            case "tcg_chongyun" : return "Chongyun";
            case "tcg_kamisato_ayaka" : return "Kamisato Ayaka";
            case "tcg_xingqiu" : return "Xingqiu";
            case "tcg_mona" : return "Mona";
            case "tcg_diluc" : return "Diluc";
            case "tcg_xiangling" : return "Xiangling";
            case "tcg_bennett" : return "Bennett";
            case "tcg_yoimiya" : return "Yoimiya";
            case "tcg_fischl" : return "Fischl";
            case "tcg_razor" : return "Razor";
            case "tcg_keqing" : return "Keqing";
            case "tcg_sucrose" : return "Sucrose";
            case "tcg_jean" : return "Jean";
            case "tcg_ningguang" : return "Ningguang";
            case "tcg_noelle" : return "Noelle";
            case "tcg_collei" : return "Collei";
            case "tcg_rhodeia_of_loch" : return "Rhodeia of Loch";
            case "tcg_fatui_pyro_agent" : return "Fatui Pyro Agent";
            case "tcg_maguu_kenki" : return "Maguu Kenki";
            case "tcg_stonehide_lawachurl" : return "Stonehide Lawachurl";
            case "tcg_diona" : return "Diona";
            case "tcg_cyno" : return "Cyno";
            case "tcg_barbara" : return "Barbara";
            case "tcg_mirror_maiden" : return "Mirror Maiden";
            case "tcg_jadeplume_terrorshroom" : return "Jadeplume Terrorshroom";
            case "tcg_undivided_heart" : return "Undivided Heart";
            case "tcg_cold_blooded_strike" : return "Cold-Blooded Strike";
            case "tcg_steady_breathing" : return "Steady Breathing";
            case "tcg_kanten_senmyou_blessing" : return "Kanten Senmyou Blessing";
            case "tcg_the_scent_remained" : return "The Scent Remained";
            case "tcg_prophecy_of_submersion" : return "Prophecy of Submersion";
            case "tcg_flowing_flame" : return "Flowing Flame";
            case "tcg_crossfire" : return "Crossfire";
            case "tcg_grand_expectation" : return "Grand Expectation";
            case "tcg_naganohara_meteor_swarm" : return "Naganohara Meteor Swarm";
            case "tcg_stellar_predator" : return "Stellar Predator";
            case "tcg_awakening" : return "Awakening";
            case "tcg_thundering_penance" : return "Thundering Penance";
            case "tcg_chaotic_entropy" : return "Chaotic Entropy";
            case "tcg_lands_of_dandelion" : return "Lands of Dandelion";
            case "tcg_strategic_reserve" : return "Strategic Reserve";
            case "tcg_i_got_your_back" : return "I Got Your Back";
            case "tcg_floral_sidewinder" : return "Floral Sidewinder";
            case "tcg_streaming_surge" : return "Streaming Surge";
            case "tcg_paid_in_full" : return "Paid in Full";
            case "tcg_transcendent_automaton" : return "Transcendent Automaton";
            case "tcg_stonehide_reforged" : return "Stonehide Reforged";
            case "tcg_shaken_not_purred" : return "Shaken, Not Purred";
            case "tcg_featherfall_judgment" : return "Featherfall Judgment";
            case "tcg_glorious_season" : return "Glorious Season";
            case "tcg_mirror_cage" : return "Mirror Cage";
            case "tcg_proliferating_spores" : return "Proliferating Spores";
            case "tcg_magic_guide" : return "Magic Guide";
            case "tcg_sacrificial_fragments" : return "Sacrificial Fragments";
            case "tcg_skyward_atlas" : return "Skyward Atlas";
            case "tcg_raven_bow" : return "Raven Bow";
            case "tcg_sacrificial_bow" : return "Sacrificial Bow";
            case "tcg_skyward_harp" : return "Skyward Harp";
            case "tcg_white_iron_greatsword" : return "White Iron Greatsword";
            case "tcg_sacrificial_greatsword" : return "Sacrificial Greatsword";
            case "tcg_wolfs_gravestone" : return "Wolf's Gravestone";
            case "tcg_white_tassel" : return "White Tassel";
            case "tcg_lithic_spear" : return "Lithic Spear";
            case "tcg_skyward_spine" : return "Skyward Spine";
            case "tcg_travelers_handy_sword" : return "Traveler's Handy Sword";
            case "tcg_sacrificial_sword" : return "Sacrificial Sword";
            case "tcg_aquila_favonia" : return "Aquila Favonia";
            case "tcg_adventurers_bandana" : return "Adventurer's Bandana";
            case "tcg_lucky_dogs_silver_circlet" : return "Lucky Dog's Silver Circlet";
            case "tcg_traveling_doctors_handkerchief" : return "Traveling Doctor's Handkerchief";
            case "tcg_gamblers_earrings" : return "Gambler's Earrings";
            case "tcg_instructors_cap" : return "Instructor's Cap";
            case "tcg_exiles_circlet" : return "Exile's Circlet";
            case "tcg_broken_rimes_echo" : return "Broken Rime's Echo";
            case "tcg_blizzard_strayer" : return "Blizzard Strayer";
            case "tcg_wine_stained_tricorne" : return "Wine-Stained Tricorne";
            case "tcg_heart_of_depth" : return "Heart of Depth";
            case "tcg_witchs_scorching_hat" : return "Witch's Scorching Hat";
            case "tcg_crimson_witch_of_flames" : return "Crimson Witch of Flames";
            case "tcg_thunder_summoners_crown" : return "Thunder Summoner's Crown";
            case "tcg_thundering_fury" : return "Thundering Fury";
            case "tcg_viridescent_venerers_diadem" : return "Viridescent Venerer's Diadem";
            case "tcg_viridescent_venerer" : return "Viridescent Venerer";
            case "tcg_mask_of_solitude_basalt" : return "Mask of Solitude Basalt";
            case "tcg_archaic_petra" : return "Archaic Petra";
            case "tcg_laurel_coronet" : return "Laurel Coronet";
            case "tcg_deepwood_memories" : return "Deepwood Memories";
            case "tcg_liyue_harbor_wharf" : return "Liyue Harbor Wharf";
            case "tcg_knights_of_favonius_library" : return "Knights of Favonius Library";
            case "tcg_jade_chamber" : return "Jade Chamber";
            case "tcg_dawn_winery" : return "Dawn Winery";
            case "tcg_wangshu_inn" : return "Wangshu Inn";
            case "tcg_favonius_cathedral" : return "Favonius Cathedral";
            case "tcg_paimon" : return "Paimon";
            case "tcg_katheryne" : return "Katheryne";
            case "tcg_timaeus" : return "Timaeus";
            case "tcg_wagner" : return "Wagner";
            case "tcg_chef_mao" : return "Chef Mao";
            case "tcg_tubby" : return "Tubby";
            case "tcg_timmie" : return "Timmie";
            case "tcg_liben" : return "Liben";
            case "tcg_chang_the_ninth" : return "Chang the Ninth";
            case "tcg_ellin" : return "Ellin";
            case "tcg_iron_tongue_tian" : return "Iron Tongue Tian";
            case "tcg_liu_su" : return "Liu Su";
            case "tcg_parametric_transformer" : return "Parametric Transformer";
            case "tcg_nre" : return "NRE";
            case "tcg_elemental_resonance_woven_ice" : return "Elemental Resonance: Woven Ice";
            case "tcg_elemental_resonance_shattering_ice" : return "Elemental Resonance: Shattering Ice";
            case "tcg_elemental_resonance_woven_waters" : return "Elemental Resonance: Woven Waters";
            case "tcg_elemental_resonance_soothing_water" : return "Elemental Resonance: Soothing Water";
            case "tcg_elemental_resonance_woven_flames" : return "Elemental Resonance: Woven Flames";
            case "tcg_elemental_resonance_fervent_flames" : return "Elemental Resonance: Fervent Flames";
            case "tcg_elemental_resonance_woven_thunder" : return "Elemental Resonance: Woven Thunder";
            case "tcg_elemental_resonance_high_voltage" : return "Elemental Resonance: High Voltage";
            case "tcg_elemental_resonance_woven_winds" : return "Elemental Resonance: Woven Winds";
            case "tcg_elemental_resonance_impetuous_winds" : return "Elemental Resonance: Impetuous Winds";
            case "tcg_elemental_resonance_woven_stone" : return "Elemental Resonance: Woven Stone";
            case "tcg_elemental_resonance_enduring_rock" : return "Elemental Resonance: Enduring Rock";
            case "tcg_elemental_resonance_woven_weeds" : return "Elemental Resonance: Woven Weeds";
            case "tcg_elemental_resonance_sprawling_greenery" : return "Elemental Resonance: Sprawling Greenery";
            case "tcg_the_bestest_travel_companion" : return "The Bestest Travel Companion!";
            case "tcg_changing_shifts" : return "Changing Shifts";
            case "tcg_toss_up" : return "Toss-Up";
            case "tcg_strategize" : return "Strategize";
            case "tcg_i_havent_lost_yet" : return "I Haven't Lost Yet!";
            case "tcg_leave_it_to_me" : return "Leave it to Me!";
            case "tcg_when_the_crane_returned" : return "When the Crane Returned";
            case "tcg_starsigns" : return "Starsigns";
            case "tcg_calxs_arts" : return "Calx's Arts";
            case "tcg_master_of_weaponry" : return "Master of Weaponry";
            case "tcg_blessing_of_the_divine_relics_installation" : return "Blessing of the Divine Relic's Installation";
            case "tcg_quick_knit" : return "Quick Knit";
            case "tcg_send_off" : return "Send Off";
            case "tcg_guardians_oath" : return "Guardian's Oath";
            case "tcg_abyssal_summons" : return "Abyssal Summons";
            case "tcg_jueyun_guoba" : return "Jueyun Guoba";
            case "tcg_adeptus_temptation" : return "Adeptus' Temptation";
            case "tcg_lotus_flower_crisp" : return "Lotus Flower Crisp";
            case "tcg_northern_smoked_chicken" : return "Northern Smoked Chicken";
            case "tcg_sweet_madame" : return "Sweet Madame";
            case "tcg_mondstadt_hash_brown" : return "Mondstadt Hash Brown";
            case "tcg_mushroom_pizza" : return "Mushroom Pizza";
            case "tcg_minty_meat_rolls" : return "Minty Meat Rolls";
            case "tcg_origin" : return "Origin";
            case "tcg_mondstadt" : return "Mondstadt";
            case "tcg_liyue" : return "Liyue";
            case "tcg_inazuma" : return "Inazuma";
            case "tcg_sumeru" : return "Sumeru";
            case "tcg_legend" : return "Legend";
            case "tcg_adept" : return "Adept";
            case "tcg_hydro_crystal_chunk" : return "Hydro Crystal Chunk";
            case "tcg_magical_crystal_chunk" : return "Magical Crystal Chunk";
            case "tcg_amethyst_lump" : return "Amethyst Lump";
            case "tcg_flaming_flower_stamen" : return "Flaming Flower Stamen";
            case "tcg_mist_flower_corolla" : return "Mist Flower Corolla";
            case "tcg_electro_crystal" : return "Electro Crystal";
            case "tcg_dandelion_seed" : return "Dandelion Seed";
            case "tcg_cecilia" : return "Cecilia";
            case "tcg_glaze_lily" : return "Glaze Lily";
            case "tcg_dendrobium" : return "Dendrobium";
            default: return name;
        }
    }

    public String getTypeLocaleByName(String type, Context context){
        switch (type){
            case TCG.CHAR : return context.getString(R.string.tcg_type_char);
            case TCG.EQUIP: return context.getString(R.string.tcg_type_equip);
            case TCG.SUPPORT: return context.getString(R.string.tcg_type_support);
            case TCG.EVENT: return context.getString(R.string.tcg_type_event);
            case TCG.BACKSIDE: return context.getString(R.string.tcg_type_backside);
            default: return context.getString(R.string.unknown);
        }
    }

    /**
     * @param name is Char's Identify Name -> JSON
     * @return IMG_ID , NAME_LOCAL , FULL_IMG_ID , ICO_IMG_ID , CARD_ID
     */

    /**EDIT WHEN ADD NEW ITEMS*/
    public String[] getCharByName(String name, Context context){
        if(context.getSharedPreferences("user_info",MODE_PRIVATE).getBoolean("isCharChangeEventSuit",false) == true){
            switch (name) {
                case "Ningguang": return new String[]{"/drawable/ningguang_suit1_gacha_splash"+IMG_FORMAT, context.getString(R.string.ningguang), "/drawable/ningguang_suit1_gacha_splash"+IMG_FORMAT, "/drawable/ningguang_suit1_ico"+IMG_FORMAT, "/drawable/ningguang_card"+IMG_FORMAT};
                case "Barbara": return new String[]{"/drawable/barbara_suit1_gacha_splash"+IMG_FORMAT, context.getString(R.string.barbara), "/drawable/barbara_suit1_gacha_splash"+IMG_FORMAT, "/drawable/barbara_suit1_ico"+IMG_FORMAT, "/drawable/barbara_card"+IMG_FORMAT};
                case "Jean": return new String[]{"/drawable/jean_suit2_gacha_splash"+IMG_FORMAT, context.getString(R.string.jean), "/drawable/jean_suit2_gacha_splash"+IMG_FORMAT, "/drawable/jean_suit2_ico"+IMG_FORMAT, "/drawable/jean_card"+IMG_FORMAT};
                case "Keqing": return new String[]{"/drawable/keqing_suit1_gacha_splash"+IMG_FORMAT, context.getString(R.string.keqing), "/drawable/keqing_suit1_gacha_splash"+IMG_FORMAT, "/drawable/keqing_suit1_ico"+IMG_FORMAT, "/drawable/keqing_card"+IMG_FORMAT};
                case "Diluc": return new String[]{"/drawable/diluc_suit1_gacha_splash"+IMG_FORMAT, context.getString(R.string.diluc), "/drawable/diluc_suit1_gacha_splash"+IMG_FORMAT, "/drawable/diluc_suit1_ico"+IMG_FORMAT, "/drawable/diluc_card"+IMG_FORMAT};
                case "Fischl": return new String[]{"/drawable/fischl_suit1_gacha_splash"+IMG_FORMAT, context.getString(R.string.fischl), "/drawable/fischl_suit1_gacha_splash"+IMG_FORMAT, "/drawable/fischl_suit1_ico"+IMG_FORMAT, "/drawable/fischl_card"+IMG_FORMAT};
                case "Kamisato Ayaka": return new String[]{"/drawable/kamisato_ayaka_suit1_gacha_splash"+IMG_FORMAT, context.getString(R.string.kamisato_ayaka), "/drawable/kamisato_ayaka_suit1_gacha_splash"+IMG_FORMAT, "/drawable/kamisato_ayaka_suit1_ico"+IMG_FORMAT, "/drawable/kamisato_ayaka_card"+IMG_FORMAT};
                case "Lisa": return new String[]{"/drawable/lisa_suit1_gacha_splash"+IMG_FORMAT, context.getString(R.string.lisa), "/drawable/lisa_suit1_gacha_splash"+IMG_FORMAT, "/drawable/lisa_suit1_ico"+IMG_FORMAT, "/drawable/lisa_card"+IMG_FORMAT};
                default: return getCharByName_BASE(name, context);
            }
        }else{
            return getCharByName_BASE(name,context);
        }
    }

    public String[] getCharByName_BASE(String name, Context context){
        switch (name) {
            case "Kirara": return new String[]{"/drawable/kirara_full"+IMG_FORMAT, context.getString(R.string.kirara), "/drawable/kirara_gacha_splash"+IMG_FORMAT, "/drawable/kirara_ico"+IMG_FORMAT, "/drawable/kirara_card"+IMG_FORMAT};

            case "Baizhu": return new String[]{"/drawable/baizhu_full"+IMG_FORMAT, context.getString(R.string.baizhu), "/drawable/baizhu_gacha_splash"+IMG_FORMAT, "/drawable/baizhu_ico"+IMG_FORMAT, "/drawable/baizhu_card"+IMG_FORMAT};
            case "Kaveh": return new String[]{"/drawable/kaveh_full"+IMG_FORMAT, context.getString(R.string.kaveh), "/drawable/kaveh_gacha_splash"+IMG_FORMAT, "/drawable/kaveh_ico"+IMG_FORMAT, "/drawable/kaveh_card"+IMG_FORMAT};

            case "Dehya": return new String[]{"/drawable/dehya_full"+IMG_FORMAT, context.getString(R.string.dehya), "/drawable/dehya_gacha_splash"+IMG_FORMAT, "/drawable/dehya_ico"+IMG_FORMAT, "/drawable/dehya_card"+IMG_FORMAT};
            case "Mika": return new String[]{"/drawable/mika_full"+IMG_FORMAT, context.getString(R.string.mika), "/drawable/mika_gacha_splash"+IMG_FORMAT, "/drawable/mika_ico"+IMG_FORMAT, "/drawable/mika_card"+IMG_FORMAT};

            case "Alhaitham": return new String[]{"/drawable/alhaitham_full"+IMG_FORMAT, context.getString(R.string.alhaitham), "/drawable/alhaitham_gacha_splash"+IMG_FORMAT, "/drawable/alhaitham_ico"+IMG_FORMAT, "/drawable/alhaitham_card"+IMG_FORMAT};
            case "Yaoyao": return new String[]{"/drawable/yaoyao_full"+IMG_FORMAT, context.getString(R.string.yaoyao), "/drawable/yaoyao_gacha_splash"+IMG_FORMAT, "/drawable/yaoyao_ico"+IMG_FORMAT, "/drawable/yaoyao_card"+IMG_FORMAT};

            case "Faruzan": return new String[]{"/drawable/faruzan_full"+IMG_FORMAT, context.getString(R.string.faruzan), "/drawable/faruzan_gacha_splash"+IMG_FORMAT, "/drawable/faruzan_ico"+IMG_FORMAT, "/drawable/faruzan_card"+IMG_FORMAT};
            case "Wanderer": return new String[]{"/drawable/wanderer_full"+IMG_FORMAT, context.getString(R.string.wanderer), "/drawable/wanderer_gacha_splash"+IMG_FORMAT, "/drawable/wanderer_ico"+IMG_FORMAT, "/drawable/wanderer_card"+IMG_FORMAT};

            case "Nahida": return new String[]{"/drawable/nahida_full"+IMG_FORMAT, context.getString(R.string.nahida), "/drawable/nahida_gacha_splash"+IMG_FORMAT, "/drawable/nahida_ico"+IMG_FORMAT, "/drawable/nahida_card"+IMG_FORMAT};
            case "Layla": return new String[]{"/drawable/layla_full"+IMG_FORMAT, context.getString(R.string.layla), "/drawable/layla_gacha_splash"+IMG_FORMAT, "/drawable/layla_ico"+IMG_FORMAT, "/drawable/layla_card"+IMG_FORMAT};

            case "Nilou": return new String[]{"/drawable/nilou_full"+IMG_FORMAT, context.getString(R.string.nilou), "/drawable/nilou_gacha_splash"+IMG_FORMAT, "/drawable/nilou_ico"+IMG_FORMAT, "/drawable/nilou_card"+IMG_FORMAT};
            case "Cyno": return new String[]{"/drawable/cyno_full"+IMG_FORMAT, context.getString(R.string.cyno), "/drawable/cyno_gacha_splash"+IMG_FORMAT, "/drawable/cyno_ico"+IMG_FORMAT, "/drawable/cyno_card"+IMG_FORMAT};
            case "Candace": return new String[]{"/drawable/candace_full"+IMG_FORMAT, context.getString(R.string.candace), "/drawable/candace_gacha_splash"+IMG_FORMAT, "/drawable/candace_ico"+IMG_FORMAT, "/drawable/candace_card"+IMG_FORMAT};

            case "Collei": return new String[]{"/drawable/collei_full"+IMG_FORMAT, context.getString(R.string.collei), "/drawable/collei_gacha_splash"+IMG_FORMAT, "/drawable/collei_ico"+IMG_FORMAT, "/drawable/collei_card"+IMG_FORMAT};
            case "Dori": return new String[]{"/drawable/dori_full"+IMG_FORMAT, context.getString(R.string.dori), "/drawable/dori_gacha_splash"+IMG_FORMAT, "/drawable/dori_ico"+IMG_FORMAT, "/drawable/dori_card"+IMG_FORMAT};
            case "Tighnari": return new String[]{"/drawable/tighnari_full"+IMG_FORMAT, context.getString(R.string.tighnari), "/drawable/tighnari_gacha_splash"+IMG_FORMAT, "/drawable/tighnari_ico"+IMG_FORMAT, "/drawable/tighnari_card"+IMG_FORMAT};
            case "Shikanoin Heizou": return new String[]{"/drawable/shikanoin_heizou_full"+IMG_FORMAT, context.getString(R.string.shikanoin_heizou), "/drawable/shikanoin_heizou_gacha_splash"+IMG_FORMAT, "/drawable/shikanoin_heizou_ico"+IMG_FORMAT, "/drawable/shikanoin_heizou_card"+IMG_FORMAT};
            case "Kuki Shinobu": return new String[]{"/drawable/kuki_shinobu_full"+IMG_FORMAT, context.getString(R.string.kuki_shinobu), "/drawable/kuki_shinobu_gacha_splash"+IMG_FORMAT, "/drawable/kuki_shinobu_ico"+IMG_FORMAT, "/drawable/kuki_shinobu_card"+IMG_FORMAT};
            case "Yelan": return new String[]{"/drawable/yelan_full"+IMG_FORMAT, context.getString(R.string.yelan), "/drawable/yelan_gacha_splash"+IMG_FORMAT, "/drawable/yelan_ico"+IMG_FORMAT, "/drawable/yelan_card"+IMG_FORMAT};
            case "Kamisato Ayato": return new String[]{"/drawable/kamisato_ayato_full"+IMG_FORMAT, context.getString(R.string.kamisato_ayato), "/drawable/kamisato_ayato_gacha_splash"+IMG_FORMAT, "/drawable/kamisato_ayato_ico"+IMG_FORMAT, "/drawable/kamisato_ayato_card"+IMG_FORMAT};
            case "Yae Miko": return new String[]{"/drawable/yae_miko_full"+IMG_FORMAT, context.getString(R.string.yae_miko), "/drawable/yae_miko_gacha_splash"+IMG_FORMAT, "/drawable/yae_miko_ico"+IMG_FORMAT, "/drawable/yae_miko_card"+IMG_FORMAT};
            case "Shenhe": return new String[]{"/drawable/shenhe_full"+IMG_FORMAT, context.getString(R.string.shenhe), "/drawable/shenhe_gacha_splash"+IMG_FORMAT, "/drawable/shenhe_ico"+IMG_FORMAT, "/drawable/shenhe_card"+IMG_FORMAT};
            case "Yun Jin": return new String[]{"/drawable/yun_jin_full"+IMG_FORMAT, context.getString(R.string.yun_jin), "/drawable/yun_jin_gacha_splash"+IMG_FORMAT, "/drawable/yun_jin_ico"+IMG_FORMAT, "/drawable/yun_jin_card"+IMG_FORMAT};
            case "Gorou": return new String[]{"/drawable/gorou_full"+IMG_FORMAT, context.getString(R.string.gorou), "/drawable/gorou_gacha_splash"+IMG_FORMAT, "/drawable/gorou_ico"+IMG_FORMAT, "/drawable/gorou_card"+IMG_FORMAT};
            case "Arataki Itto": return new String[]{"/drawable/arataki_itto_full"+IMG_FORMAT, context.getString(R.string.arataki_itto), "/drawable/arataki_itto_gacha_splash"+IMG_FORMAT, "/drawable/arataki_itto_ico"+IMG_FORMAT, "/drawable/arataki_itto_card"+IMG_FORMAT};
            case "Thoma": return new String[]{"/drawable/thoma_full"+IMG_FORMAT, context.getString(R.string.thoma), "/drawable/thoma_gacha_splash"+IMG_FORMAT, "/drawable/thoma_ico"+IMG_FORMAT, "/drawable/thoma_card"+IMG_FORMAT};
            case "Sangonomiya Kokomi": return new String[]{"/drawable/sangonomiya_kokomi_full"+IMG_FORMAT, context.getString(R.string.sangonomiya_kokomi), "/drawable/sangonomiya_kokomi_gacha_splash"+IMG_FORMAT, "/drawable/sangonomiya_kokomi_ico"+IMG_FORMAT, "/drawable/sangonomiya_kokomi_card"+IMG_FORMAT};
            case "Aloy": return new String[]{"/drawable/aloy_full"+IMG_FORMAT, context.getString(R.string.aloy), "/drawable/aloy_gacha_splash"+IMG_FORMAT, "/drawable/aloy_ico"+IMG_FORMAT, "/drawable/aloy_card"+IMG_FORMAT};
            case "Kujou Sara": return new String[]{"/drawable/kujou_sara_full"+IMG_FORMAT, context.getString(R.string.kujou_sara), "/drawable/kujor_sara_gacha_splash"+IMG_FORMAT, "/drawable/kujou_sara_ico"+IMG_FORMAT, "/drawable/kujor_sara_card"+IMG_FORMAT};
            case "Raiden Shogun": return new String[]{"/drawable/raiden_shogun_full"+IMG_FORMAT, context.getString(R.string.raiden_shogun), "/drawable/raiden_shogun_gacha_splash"+IMG_FORMAT, "/drawable/raiden_shogun_ico"+IMG_FORMAT, "/drawable/raiden_shogun_card"+IMG_FORMAT};
            case "Sayu": return new String[]{"/drawable/sayu_full"+IMG_FORMAT, context.getString(R.string.sayu), "/drawable/sayu_gacha_splash"+IMG_FORMAT, "/drawable/sayu_ico"+IMG_FORMAT, "/drawable/sayu_card"+IMG_FORMAT};
            case "Yoimiya": return new String[]{"/drawable/yoimiya_full"+IMG_FORMAT, context.getString(R.string.yoimiya), "/drawable/yoimiya_gacha_splash"+IMG_FORMAT, "/drawable/yoimiya_ico"+IMG_FORMAT, "/drawable/yoimiya_card"+IMG_FORMAT};
            case "Kamisato Ayaka": return new String[]{"/drawable/ayaka_full"+IMG_FORMAT, context.getString(R.string.kamisato_ayaka), "/drawable/kamisato_ayaka_gacha_splash"+IMG_FORMAT, "/drawable/ayaka_ico"+IMG_FORMAT, "/drawable/kamisato_ayaka_card"+IMG_FORMAT};
            case "Kaedehara Kazuha": return new String[]{"/drawable/kazuha_full"+IMG_FORMAT, context.getString(R.string.kaedehara_kazuha), "/drawable/kaedehara_kazuha_gacha_splash"+IMG_FORMAT, "/drawable/kazuha_ico"+IMG_FORMAT, "/drawable/kaedehara_kazuha_card"+IMG_FORMAT};
            case "Yanfei": return new String[]{"/drawable/yanfei_full"+IMG_FORMAT, context.getString(R.string.yanfei), "/drawable/yanfei_gacha_splash"+IMG_FORMAT, "/drawable/yanfei_ico"+IMG_FORMAT, "/drawable/yanfei_card"+IMG_FORMAT};
            case "Eula": return new String[]{"/drawable/eula_full"+IMG_FORMAT, context.getString(R.string.eula), "/drawable/eula_gacha_splash"+IMG_FORMAT, "/drawable/eula_ico"+IMG_FORMAT, "/drawable/eula_card"+IMG_FORMAT};
            case "Rosaria": return new String[]{"/drawable/rosaria_full"+IMG_FORMAT, context.getString(R.string.rosaria), "/drawable/rosaria_gacha_splash"+IMG_FORMAT, "/drawable/rosaria_ico"+IMG_FORMAT, "/drawable/rosaria_card"+IMG_FORMAT};
            case "Xiao": return new String[]{"/drawable/xiao_full"+IMG_FORMAT, context.getString(R.string.xiao), "/drawable/xiao_gacha_splash"+IMG_FORMAT, "/drawable/xiao_ico"+IMG_FORMAT, "/drawable/xiao_card"+IMG_FORMAT};
            case "Hu Tao": return new String[]{"/drawable/hu_tao_full"+IMG_FORMAT, context.getString(R.string.hu_tao), "/drawable/hu_tao_gacha_splash"+IMG_FORMAT, "/drawable/hu_tao_ico"+IMG_FORMAT, "/drawable/hu_tao_card"+IMG_FORMAT};
            case "Ganyu": return new String[]{"/drawable/ganyu_full"+IMG_FORMAT, context.getString(R.string.ganyu), "/drawable/ganyu_gacha_splash"+IMG_FORMAT, "/drawable/ganyu_ico"+IMG_FORMAT, "/drawable/ganyu_card"+IMG_FORMAT};
            case "Albedo": return new String[]{"/drawable/albedo_full"+IMG_FORMAT, context.getString(R.string.albedo), "/drawable/albedo_gacha_splash"+IMG_FORMAT, "/drawable/albedo_ico"+IMG_FORMAT, "/drawable/albedo_card"+IMG_FORMAT};
            case "Zhongli": return new String[]{"/drawable/zhongli_full"+IMG_FORMAT, context.getString(R.string.zhongli), "/drawable/zhongli_gacha_splash"+IMG_FORMAT, "/drawable/zhongli_ico"+IMG_FORMAT, "/drawable/zhongli_card"+IMG_FORMAT};
            case "Xinyan": return new String[]{"/drawable/xinyan_full"+IMG_FORMAT, context.getString(R.string.xinyan), "/drawable/xinyan_gacha_splash"+IMG_FORMAT, "/drawable/xinyan_ico"+IMG_FORMAT, "/drawable/xinyan_card"+IMG_FORMAT};
            case "Tartaglia": return new String[]{"/drawable/tartaglia_full"+IMG_FORMAT, context.getString(R.string.tartaglia), "/drawable/tartaglia_gacha_splash"+IMG_FORMAT, "/drawable/tartaglia_ico"+IMG_FORMAT, "/drawable/tartaglia_card"+IMG_FORMAT};
            case "Diona": return new String[]{"/drawable/diona_full"+IMG_FORMAT, context.getString(R.string.diona), "/drawable/diona_gacha_splash"+IMG_FORMAT, "/drawable/diona_ico"+IMG_FORMAT, "/drawable/diona_card"+IMG_FORMAT};
            case "Xingqiu": return new String[]{"/drawable/xingqiu_full"+IMG_FORMAT, context.getString(R.string.xingqiu), "/drawable/xingqiu_gacha_splash"+IMG_FORMAT, "/drawable/xingqiu_ico"+IMG_FORMAT, "/drawable/xingqiu_card"+IMG_FORMAT};
            case "Xiangling": return new String[]{"/drawable/xiangling_full"+IMG_FORMAT, context.getString(R.string.xiangling), "/drawable/xiangling_gacha_splash"+IMG_FORMAT, "/drawable/xiangling_ico"+IMG_FORMAT, "/drawable/xiangling_card"+IMG_FORMAT};
            case "Venti": return new String[]{"/drawable/venti_full"+IMG_FORMAT, context.getString(R.string.venti), "/drawable/venti_gacha_splash"+IMG_FORMAT, "/drawable/venti_ico"+IMG_FORMAT, "/drawable/venti_card"+IMG_FORMAT};
            case "Sucrose": return new String[]{"/drawable/sucrose_full"+IMG_FORMAT, context.getString(R.string.sucrose), "/drawable/sucrose_gacha_splash"+IMG_FORMAT, "/drawable/sucrose_ico"+IMG_FORMAT, "/drawable/sucrose_card"+IMG_FORMAT};
            case "Razor": return new String[]{"/drawable/razor_full"+IMG_FORMAT, context.getString(R.string.razor), "/drawable/razor_gacha_splash"+IMG_FORMAT, "/drawable/razor_ico"+IMG_FORMAT, "/drawable/razor_card"+IMG_FORMAT};
            case "Qiqi": return new String[]{"/drawable/qiqi_full"+IMG_FORMAT, context.getString(R.string.qiqi), "/drawable/qiqi_gacha_splash"+IMG_FORMAT, "/drawable/qiqi_ico"+IMG_FORMAT, "/drawable/qiqi_card"+IMG_FORMAT};
            case "Noelle": return new String[]{"/drawable/noelle_full"+IMG_FORMAT, context.getString(R.string.noelle), "/drawable/noelle_gacha_splash"+IMG_FORMAT, "/drawable/noelle_ico"+IMG_FORMAT, "/drawable/noelle_card"+IMG_FORMAT};
            case "Ningguang": return new String[]{"/drawable/ningguang_full"+IMG_FORMAT, context.getString(R.string.ningguang), "/drawable/ningguang_gacha_splash"+IMG_FORMAT, "/drawable/ningguang_ico"+IMG_FORMAT, "/drawable/ningguang_card"+IMG_FORMAT};
            case "Mona": return new String[]{"/drawable/mona_full"+IMG_FORMAT, context.getString(R.string.mona), "/drawable/mona_gacha_splash"+IMG_FORMAT, "/drawable/mona_ico"+IMG_FORMAT, "/drawable/mona_card"+IMG_FORMAT};
            case "Lisa": return new String[]{"/drawable/lisa_full"+IMG_FORMAT, context.getString(R.string.lisa), "/drawable/lisa_gacha_splash"+IMG_FORMAT, "/drawable/lisa_ico"+IMG_FORMAT, "/drawable/lisa_card"+IMG_FORMAT};
            case "Klee": return new String[]{"/drawable/klee_full"+IMG_FORMAT, context.getString(R.string.klee), "/drawable/klee_gacha_splash"+IMG_FORMAT, "/drawable/klee_ico"+IMG_FORMAT, "/drawable/klee_card"+IMG_FORMAT};
            case "Keqing": return new String[]{"/drawable/keqing_full"+IMG_FORMAT, context.getString(R.string.keqing), "/drawable/keqing_gacha_splash"+IMG_FORMAT, "/drawable/keqing_ico"+IMG_FORMAT, "/drawable/keqing_card"+IMG_FORMAT};
            case "Kaeya": return new String[]{"/drawable/kaeya_full"+IMG_FORMAT, context.getString(R.string.kaeya), "/drawable/kaeya_gacha_splash"+IMG_FORMAT, "/drawable/kaeya_ico"+IMG_FORMAT, "/drawable/kaeya_card"+IMG_FORMAT};
            case "Jean": return new String[]{"/drawable/jean_full"+IMG_FORMAT, context.getString(R.string.jean), "/drawable/jean_gacha_splash"+IMG_FORMAT, "/drawable/jean_ico"+IMG_FORMAT, "/drawable/jean_card"+IMG_FORMAT};
            case "Fischl": return new String[]{"/drawable/fischl_full"+IMG_FORMAT, context.getString(R.string.fischl), "/drawable/fischl_gacha_splash"+IMG_FORMAT, "/drawable/fischl_ico"+IMG_FORMAT, "/drawable/fischl_card"+IMG_FORMAT};
            case "Diluc": return new String[]{"/drawable/diluc_full"+IMG_FORMAT, context.getString(R.string.diluc), "/drawable/diluc_gacha_splash"+IMG_FORMAT, "/drawable/diluc_ico"+IMG_FORMAT, "/drawable/diluc_card"+IMG_FORMAT};
            case "Chongyun": return new String[]{"/drawable/chongyun_full"+IMG_FORMAT, context.getString(R.string.chongyun), "/drawable/chongyun_gacha_splash"+IMG_FORMAT, "/drawable/chongyun_ico"+IMG_FORMAT, "/drawable/chongyun_card"+IMG_FORMAT};
            case "Bennett": return new String[]{"/drawable/bennett_full"+IMG_FORMAT, context.getString(R.string.bennett), "/drawable/bennett_gacha_splash"+IMG_FORMAT, "/drawable/bennett_ico"+IMG_FORMAT, "/drawable/bennett_card"+IMG_FORMAT};
            case "Beidou": return new String[]{"/drawable/beidou_full"+IMG_FORMAT, context.getString(R.string.beidou), "/drawable/beidou_gacha_splash"+IMG_FORMAT, "/drawable/beidou_ico"+IMG_FORMAT, "/drawable/beidou_card"+IMG_FORMAT};
            case "Barbara": return new String[]{"/drawable/barbara_full"+IMG_FORMAT, context.getString(R.string.barbara), "/drawable/barbara_gacha_splash"+IMG_FORMAT, "/drawable/barbara_ico"+IMG_FORMAT, "/drawable/barbara_card"+IMG_FORMAT};
            case "Amber": return new String[]{"/drawable/amber_full"+IMG_FORMAT, context.getString(R.string.amber), "/drawable/amber_gacha_splash"+IMG_FORMAT, "/drawable/amber_ico"+IMG_FORMAT, "/drawable/amber_card"+IMG_FORMAT};
            //Add at 20210820, update at 20220109, add CARD at 20220411
            case "Traveler-Anemo": {
                sharedPreferences = context.getSharedPreferences("user_info", MODE_PRIVATE);
                String traveler_sex = sharedPreferences.getString("traveler_sex", "F");

                if (traveler_sex.equals("M")) {	return new String[]{"/drawable/aether_full"+IMG_FORMAT, context.getString(R.string.traveler_anemo), "/drawable/aether_full"+IMG_FORMAT, "/drawable/aether_ico"+IMG_FORMAT, "/drawable/traveler_card"+IMG_FORMAT};
                } else {
                    return new String[]{"/drawable/lumine_full"+IMG_FORMAT, context.getString(R.string.traveler_anemo), "/drawable/lumine_full"+IMG_FORMAT, "/drawable/lumine_ico"+IMG_FORMAT, "/drawable/traveler_card"+IMG_FORMAT};
                }
            }
            //Add at 20210820, update at 20220109, add CARD at 20220411
            case "Traveler-Geo": {
                sharedPreferences = context.getSharedPreferences("user_info", MODE_PRIVATE);
                String traveler_sex = sharedPreferences.getString("traveler_sex", "F");

                if (traveler_sex.equals("M")) {
                    return new String[]{"/drawable/aether_full"+IMG_FORMAT, context.getString(R.string.traveler_geo), "/drawable/aether_full"+IMG_FORMAT, "/drawable/aether_ico"+IMG_FORMAT, "/drawable/traveler_card"+IMG_FORMAT};
                } else {
                    return new String[]{"/drawable/lumine_full"+IMG_FORMAT, context.getString(R.string.traveler_geo), "/drawable/lumine_full"+IMG_FORMAT, "/drawable/lumine_ico"+IMG_FORMAT, "/drawable/traveler_card"+IMG_FORMAT};
                }
            }
            //Add at 20210820, update at 20220109, add CARD at 20220411
            case "Traveler-Electro": {
                sharedPreferences = context.getSharedPreferences("user_info", MODE_PRIVATE);
                String traveler_sex = sharedPreferences.getString("traveler_sex", "F");

                if (traveler_sex.equals("M")) {
                    return new String[]{"/drawable/aether_full"+IMG_FORMAT, context.getString(R.string.traveler_electro), "/drawable/aether_full"+IMG_FORMAT, "/drawable/aether_ico"+IMG_FORMAT, "/drawable/traveler_card"+IMG_FORMAT};
                } else {
                    return new String[]{"/drawable/lumine_full"+IMG_FORMAT, context.getString(R.string.traveler_electro), "/drawable/lumine_full"+IMG_FORMAT, "/drawable/lumine_ico"+IMG_FORMAT, "/drawable/traveler_card"+IMG_FORMAT};
                }
            }
            //Add at 20220714
            case "Traveler-Dendro": {
                sharedPreferences = context.getSharedPreferences("user_info", MODE_PRIVATE);
                String traveler_sex = sharedPreferences.getString("traveler_sex", "F");

                if (traveler_sex.equals("M")) {
                    return new String[]{"/drawable/aether_full"+IMG_FORMAT, context.getString(R.string.traveler_dendor), "/drawable/aether_full"+IMG_FORMAT, "/drawable/aether_ico"+IMG_FORMAT, "/drawable/traveler_card"+IMG_FORMAT};
                } else {
                    return new String[]{"/drawable/lumine_full"+IMG_FORMAT, context.getString(R.string.traveler_dendor), "/drawable/lumine_full"+IMG_FORMAT, "/drawable/lumine_ico"+IMG_FORMAT, "/drawable/traveler_card"+IMG_FORMAT};
                }
            }
            // ???
            case "Voc-夜芷冰": return new String[]{"/drawable/voc_full"+IMG_FORMAT, context.getString(R.string.voc), "/drawable/voc_full"+IMG_FORMAT, "/drawable/voc_ico"+IMG_FORMAT, "/drawable/mascot_card"+IMG_FORMAT};
            case "朝霧冰瀬": return new String[]{"/drawable/asagiri_korise_full"+IMG_FORMAT, context.getString(R.string.asagiri_korise), "/drawable/asagiri_korise_full"+IMG_FORMAT, "/drawable/asagiri_korise_ico"+IMG_FORMAT, "/drawable/mascot_card"+IMG_FORMAT};
            case "N/A" : return new String[]{"/drawable/paimon_full"+IMG_FORMAT, context.getString(R.string.unknown), "/drawable/paimon_full"+IMG_FORMAT,"/drawable/paimon_lost"+IMG_FORMAT, "/drawable/unknown_card"+IMG_FORMAT};
        }
        // Suitable for display upcoming characters
        String upcoming = name.toLowerCase().replace(" ","_");
        return new String[]{"/drawable/"+upcoming+"_full"+IMG_FORMAT, name, "/drawable/"+upcoming+"_gacha_splash"+IMG_FORMAT, "/drawable/"+upcoming+"_ico"+IMG_FORMAT, "/drawable/"+upcoming+"_card"+IMG_FORMAT};
    }

    public String getCharNameByTranslatedName(String name, Context context){
        if(name.equals("Albedo") || name.equals("阿貝多") || name.equals("阿贝多") || name.equals("アルベド") || name.equals("Альбедо")){ return "Albedo";}
        else if(name.equals("Aloy") || name.equals("亞蘿伊") || name.equals("亚萝伊") || name.equals("アーロイ") || name.equals("Элой")){ return "Aloy";}
        else if(name.equals("Amber") || name.equals("安柏") || name.equals("安柏") || name.equals("アンバー") || name.equals("Эмбер")){ return "Amber";}
        else if(name.equals("Barbara") || name.equals("芭芭拉") || name.equals("芭芭拉") || name.equals("バーバラ") || name.equals("Барбара")){ return "Barbara";}
        else if(name.equals("Beidou") || name.equals("北斗") || name.equals("北斗") || name.equals("北斗") || name.equals("Бэй Доу")){ return "Beidou";}
        else if(name.equals("Bennett") || name.equals("班尼特") || name.equals("班尼特") || name.equals("ベネット") || name.equals("Беннет")){ return "Bennett";}
        else if(name.equals("Chongyun") || name.equals("重雲") || name.equals("重云") || name.equals("重雲") || name.equals("Чун Юнь")){ return "Chongyun";}
        else if(name.equals("Diluc") || name.equals("迪盧克") || name.equals("迪卢克") || name.equals("ディルック") || name.equals("Дилюк")){ return "Diluc";}
        else if(name.equals("Diona") || name.equals("迪奧娜") || name.equals("迪奥娜") || name.equals("ディオナ") || name.equals("Диона")){ return "Diona";}
        else if(name.equals("Eula") || name.equals("優菈") || name.equals("优菈") || name.equals("エウルア") || name.equals("Эола")){ return "Eula";}
        else if(name.equals("Fischl") || name.equals("菲謝爾") || name.equals("菲谢尔") || name.equals("フィッシュル") || name.equals("Фишль")){ return "Fischl";}
        else if(name.equals("Ganyu") || name.equals("甘雨") || name.equals("甘雨") || name.equals("甘雨") || name.equals("Гань Юй")){ return "Ganyu";}
        else if(name.equals("Hu Tao") || name.equals("胡桃") || name.equals("胡桃") || name.equals("胡桃") || name.equals("Ху Тао")){ return "Hu Tao";}
        else if(name.equals("Jean") || name.equals("琴") || name.equals("琴") || name.equals("ジン") || name.equals("Джинн")){ return "Jean";}
        else if(name.equals("Kaedehara Kazuha") || name.equals("楓原萬葉") || name.equals("枫原万叶") || name.equals("楓原万葉") || name.equals("Каэдэхара Кадзуха")){ return "Kaedehara Kazuha";}
        else if(name.equals("Kaeya") || name.equals("凱亞") || name.equals("凯亚") || name.equals("ガイア") || name.equals("Кэйа")){ return "Kaeya";}
        else if(name.equals("Kamisato Ayaka") || name.equals("神里綾華") || name.equals("神里绫华") || name.equals("神里綾華") || name.equals("Камисато Аяка")){ return "Kamisato Ayaka";}
        else if(name.equals("Keqing") || name.equals("刻晴") || name.equals("刻晴") || name.equals("刻晴") || name.equals("Кэ Цин")){ return "Keqing";}
        else if(name.equals("Klee") || name.equals("可莉") || name.equals("可莉") || name.equals("クレー") || name.equals("Кли")){ return "Klee";}
        else if(name.equals("Kujou Sara") || name.equals("九條娑羅") || name.equals("九条娑罗") || name.equals("九条裟羅") || name.equals("Кудзё Сара")){ return "Kujou Sara";}
        else if(name.equals("Lisa") || name.equals("麗莎") || name.equals("莉萨") || name.equals("リサ") || name.equals("Лиза")){ return "Lisa";}
        else if(name.equals("Mona") || name.equals("莫娜") || name.equals("莫娜") || name.equals("モナ") || name.equals("Мона")){ return "Mona";}
        else if(name.equals("Ningguang") || name.equals("凝光") || name.equals("凝光") || name.equals("凝光") || name.equals("Нин Гуан")){ return "Ningguang";}
        else if(name.equals("Noelle") || name.equals("諾艾爾") || name.equals("诺艾尔") || name.equals("ノエル") || name.equals("Ноэль")){ return "Noelle";}
        else if(name.equals("Qiqi") || name.equals("七七") || name.equals("七七") || name.equals("七七") || name.equals("Ци Ци")){ return "Qiqi";}
        else if(name.equals("Raiden Shogun") || name.equals("雷電將軍") || name.equals("雷电将军") || name.equals("雷電将軍") || name.equals("Сёгун Райдэн")){ return "Raiden Shogun";}
        else if(name.equals("Razor") || name.equals("雷澤") || name.equals("雷泽") || name.equals("レザー") || name.equals("Рэйзор")){ return "Razor";}
        else if(name.equals("Rosaria") || name.equals("羅莎莉亞") || name.equals("罗莎莉亚") || name.equals("ロサリア") || name.equals("Розария")){ return "Rosaria";}
        else if(name.equals("Sangonomiya Kokomi") || name.equals("珊瑚宮心海") || name.equals("珊瑚宫心海") || name.equals("珊瑚宮心海") || name.equals("Сангономия Кокоми")){ return "Sangonomiya Kokomi";}
        else if(name.equals("Sayu") || name.equals("早柚") || name.equals("早柚") || name.equals("早柚") || name.equals("Саю")){ return "Sayu";}
        else if(name.equals("Sucrose") || name.equals("砂糖") || name.equals("砂糖") || name.equals("スクロース") || name.equals("Сахароза")){ return "Sucrose";}
        else if(name.equals("Tartaglia") || name.equals("達達利亞") || name.equals("达达利亚") || name.equals("タルタリヤ") || name.equals("Тарталья")){ return "Tartaglia";}
        else if(name.equals("Venti") || name.equals("溫迪") || name.equals("温迪") || name.equals("ウェンティ") || name.equals("Венти")){ return "Venti";}
        else if(name.equals("Xiangling") || name.equals("香菱") || name.equals("香菱") || name.equals("香菱") || name.equals("Сян Лин")){ return "Xiangling";}
        else if(name.equals("Xiao") || name.equals("魈") || name.equals("魈") || name.equals("魈") || name.equals("Сяо")){ return "Xiao";}
        else if(name.equals("Xingqiu") || name.equals("行秋") || name.equals("行秋") || name.equals("行秋") || name.equals("Син Цю")){ return "Xingqiu";}
        else if(name.equals("Xinyan") || name.equals("辛焱") || name.equals("辛焱") || name.equals("辛炎") || name.equals("Синь Янь")){ return "Xinyan";}
        else if(name.equals("Yanfei") || name.equals("煙緋") || name.equals("烟绯") || name.equals("煙緋") || name.equals("Янь Фэй")){ return "Yanfei";}
        else if(name.equals("Yoimiya") || name.equals("宵宮") || name.equals("宵宫") || name.equals("宵宮") || name.equals("Ёимия")){ return "Yoimiya";}
        else if(name.equals("Zhongli") || name.equals("鍾離") || name.equals("钟离") || name.equals("鍾離") || name.equals("Чжун Ли")){ return "Zhongli";}
        else if(name.equals("Arataki Itto") || name.equals("荒瀧一斗") || name.equals("荒泷一斗") || name.equals("荒瀧一斗") || name.equals("Аратаки Итто")){ return "Arataki Itto";}
        else if(name.equals("Gorou") || name.equals("五郎") || name.equals("五郎") || name.equals("ゴロー") || name.equals("Горо")){ return "Gorou";}
        else if(name.equals("Yun Jin") || name.equals("雲菫") || name.equals("云菫") || name.equals("雲菫") || name.equals("Юнь Цзинь")){ return "Yun Jin";}
        else if(name.equals("Shenhe") || name.equals("申鶴") || name.equals("申鹤") || name.equals("申鶴") || name.equals("Шэнь Хэ")){ return "Shenhe";}
        else if(name.equals("Yae Miko") || name.equals("八重神子") || name.equals("八重神子") || name.equals("八重神子") || name.equals("Яэ Мико")){ return "Yae Miko";}
        else if(name.equals("Thoma") || name.equals("托馬") || name.equals("托马") || name.equals("トーマ") || name.equals("Тома")){ return "Thoma";}
        else if(name.equals("Kamisato Ayato") || name.equals("神里綾人") || name.equals("神里绫人") || name.equals("神里綾人") || name.equals("Камисато Аято")){ return "Kamisato Ayato";}
        else if(name.equals("Kuki Shinobu") || name.equals("久岐忍") || name.equals("久岐忍") || name.equals("久岐忍") || name.equals("Куки Синобу")){ return "Kuki Shinobu";}
        else if(name.equals("Yelan") || name.equals("夜蘭") || name.equals("夜兰") || name.equals("夜蘭") || name.equals("Е Лань")){ return "Yelan";}
        else if(name.equals("Shikanoin Heizou") || name.equals("鹿野院平藏") || name.equals("鹿野院平藏") || name.equals("鹿野院平蔵") || name.equals("Сиканоин Хэйдзо")){ return "Shikanoin Heizou";}
        else if(name.equals("Collei") || name.equals("柯萊") || name.equals("柯莱") || name.equals("コレイ") || name.equals("Коллеи")){ return "Collei";}
        else if(name.equals("Dori") || name.equals("多莉") || name.equals("多莉") || name.equals("ドリー") || name.equals("Дори")){ return "Dori";}
        else if(name.equals("Tighnari") || name.equals("提納里") || name.equals("提纳里") || name.equals("ティナリ") || name.equals("Тигнари")){ return "Tighnari";}
        else if(name.equals("Cyno") || name.equals("赛诺") || name.equals("賽諾") || name.equals("セノ") || name.equals("Сайно")){ return "Cyno";}
        else if(name.equals("Nilou") || name.equals("坎蒂丝") || name.equals("坎蒂絲") || name.equals("ニィロウ") || name.equals("Нилу")){ return "Nilou";}
        else if(name.equals("Candace") || name.equals("提納里") || name.equals("提纳里") || name.equals("キャンディス") || name.equals("Кандакия")){ return "Candace";}
        else if(name.equals("Nahida") || name.equals("纳西妲") || name.equals("納西妲") || name.equals("ナヒーダ") || name.equals("Нахида")){ return "Nahida";}
        else if(name.equals("Layla") || name.equals("莱依拉") || name.equals("萊依拉") || name.equals("レイラ") || name.equals("Лайла")){ return "Layla";}
        else if(name.equals("Faruzan") || name.equals("珐露珊") || name.equals("琺露珊") || name.equals("ファルザン") || name.equals("Фарузан")){ return "Faruzan";}
        else if(name.equals("Wanderer") || name.equals("流浪者") || name.equals("流浪者") || name.equals("放浪者") || name.equals("Странник")){ return "Wanderer";}
        else if(name.equals("Yaoyao") || name.equals("瑤瑤") || name.equals("瑶瑶") || name.equals("ヨォーヨ") || name.equals("Яо Яо")){ return "Yaoyao";}
        else if(name.equals("Alhaitham") || name.equals("艾爾海森") || name.equals("艾尔海森") || name.equals("アルハイゼン") || name.equals("Аль-Хайтам")){ return "Alhaitham";}
        else if(name.equals("Dehya") || name.equals("迪希雅") || name.equals("迪希雅") || name.equals("ディシア") || name.equals("Дэхья")){ return "Dehya";}
        else if(name.equals("Mika") || name.equals("米卡") || name.equals("米卡") || name.equals("ミカ") || name.equals("Мика")){ return "Mika";}

        else if(name.equals("Traveler-Anemo") || name.equals("旅行者(風)") || name.equals("旅行者(风)") || name.equals("旅人 (風)") || name.equals("Путешественник(Анемо)")){ return "Traveler-Anemo";}
        else if(name.equals("Traveler-Electro") || name.equals("旅行者(岩)") || name.equals("旅行者(岩)") || name.equals("旅人 (岩)") || name.equals("Путешественник(Гео)")){ return "Traveler-Electro";}
        else if(name.equals("Traveler-Geo") || name.equals("旅行者(雷)") || name.equals("旅行者(雷)") || name.equals("旅人 (雷)") || name.equals("Путешественник(Электро)")){ return "Traveler-Geo";}
        else if(name.equals("Traveler-Dendro") || name.equals("旅行者(草)") || name.equals("旅行者(草)") || name.equals("旅人 (草)") || name.equals("Путешественник(Элементы)")){ return "Traveler-Dendro";}
        else if(name.equals("N/A")){ return "N/A";}
        else if(name.equals("Voc-夜芷冰")){ return "Voc-夜芷冰";}
        else if(name.equals("朝霧冰瀬") || name.equals("朝霧 冰瀬") || name.equals("朝雾冰瀬") || name.equals("Asagiri Korise")){ return "朝霧冰瀬";}

        else return context.getString(R.string.unknown);
    }

    /**
     * @param element Element Name
     * @return ICON_ID , RAD_BG_ID , BG_COLOR_ID
     */
    public int[] getElementByName (String element) {
        switch (element) {
            case "Anemo":
                return new int[]{R.drawable.siptik_anemo_ico, R.drawable.bg_anemo_char, R.color.anemo};
            case "Cryo":
                return new int[]{R.drawable.siptik_cryo_ico, R.drawable.bg_cryo_char, R.color.cryo};
            case "Dendro":
                return new int[]{R.drawable.siptik_dendor_ico, R.drawable.bg_dendro_char, R.color.dendor};
            case "Electro":
                return new int[]{R.drawable.siptik_electro_ico, R.drawable.bg_electro_char, R.color.electro};
            case "Geo":
                return new int[]{R.drawable.siptik_geo_ico, R.drawable.bg_geo_char, R.color.geo};
            case "Hydro":
                return new int[]{R.drawable.siptik_hydro_ico, R.drawable.bg_hydro_char, R.color.hydro};
            case "Pyro":
                return new int[]{R.drawable.siptik_pyro_ico, R.drawable.bg_pyro_char, R.color.pyro};
            default:
                return new int[]{R.drawable.hu_tao_unknown, R.drawable.hu_tao_unknown, R.color.anemo};
        }
    }

    public int[] getElementByNameTCG (String element, Context context) {
        if (context.getString(R.string.element_Anemo).equals(element)) {
            return new int[]{R.drawable.anemo_ico, R.drawable.bg_tcg_dice_anemo, R.color.anemo};
        } else if (context.getString(R.string.element_Cryo).equals(element)) {
            return new int[]{R.drawable.cryo_ico, R.drawable.bg_tcg_dice_cryo, R.color.cryo};
        } else if (context.getString(R.string.element_Dendor).equals(element)) {
            return new int[]{R.drawable.dendro_ico, R.drawable.bg_tcg_dice_dendro, R.color.dendor};
        } else if (context.getString(R.string.element_Electro).equals(element)) {
            return new int[]{R.drawable.electro_ico, R.drawable.bg_tcg_dice_electro, R.color.electro};
        } else if (context.getString(R.string.element_Geo).equals(element)) {
            return new int[]{R.drawable.geo_ico, R.drawable.bg_tcg_dice_geo, R.color.geo};
        } else if (context.getString(R.string.element_Hydro).equals(element)) {
            return new int[]{R.drawable.hydro_ico, R.drawable.bg_tcg_dice_hydro, R.color.hydro};
        } else if (context.getString(R.string.element_Pyro).equals(element)) {
            return new int[]{R.drawable.pyro_ico, R.drawable.bg_tcg_dice_pyro, R.color.pyro};
        }
        return new int[]{R.drawable.hu_tao_unknown, R.drawable.hu_tao_unknown, R.color.anemo};
    }

    public Drawable getTalentIcoByName (String name, Context context){
        try {
            File file = new File(context.getFilesDir()+"/skills/"+name+""+IMG_FORMAT);
            InputStream stream = new FileInputStream(file);
            // load image as Drawable
            Drawable d = Drawable.createFromStream(stream, null);
            // set image to ImageView
            return d;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable myIcon = context.getResources().getDrawable( R.drawable.hu_tao_unknown );
        return myIcon;
    }

    public int getSecAttr(String str) {
        switch (str){
            case "CritRate%" : return R.string.weapon_stat_CritRateP;
            case "ATK" : return R.string.weapon_stat_atk;
            case "HP%" : return R.string.weapon_stat_HPP;
            case "ATK%" : return R.string.weapon_stat_atkP;
            case "DEF%" : return R.string.weapon_stat_DEFP;
            case "EnRech%" : return R.string.weapon_stat_EnRechP;
            case "PhyDMG%" : return R.string.weapon_stat_PhyDMGP;
            case "CritDMG%" : return R.string.weapon_stat_CritDMGP;
            case "EleMas" : return R.string.weapon_stat_EleMas;
            default: return  R.string.weapon_stat_no;
        }
    }

    /**EDIT WHEN ADD NEW ITEMS*/
    public String getItemIcoByName (String name ,Context context){
        switch (name) {
            /** Boss*/
            case "常燃火種":
                return "/drawable/everflame_seed"+IMG_FORMAT;
            case "淨水之心":
                return "/drawable/cleansing_heart"+IMG_FORMAT;
            case "雷光棱鏡":
                return "/drawable/lightning_prism"+IMG_FORMAT;
            case "極寒之核":
                return "/drawable/hoarfrost_core"+IMG_FORMAT;
            case "颶風之種":
                return "/drawable/hurricane_seed"+IMG_FORMAT;
            case "玄岩之塔":
                return "/drawable/basalt_pillar"+IMG_FORMAT;
            case "未熟之玉":
                return "/drawable/juvenile_jade"+IMG_FORMAT;
            case "晶凝之華":
                return "/drawable/crystalline_bloom"+IMG_FORMAT;
            case "魔偶機心":
                return "/drawable/maguu_kishin"+IMG_FORMAT;
            case "恒常機關之心":
                return "/drawable/perpetual_heart"+IMG_FORMAT;
            case "陰燃之珠":
                return "/drawable/smoldering_pearl"+IMG_FORMAT;
            //add in 20210910
            case "雷霆數珠":
                return "/drawable/storm_beads"+IMG_FORMAT;
            case "排異之露":
                return "/drawable/dew_of_repudiation"+IMG_FORMAT;
            //add in 20220105
            case "獸境王器":
                return "/drawable/riftborn_regalia"+IMG_FORMAT;
            case "龍嗣偽鰭":
                return "/drawable/dragonheirs_false_fin"+IMG_FORMAT;
            //add in 20220331
            case "符紋之齒":
                return "/drawable/runic_fang"+IMG_FORMAT;
            //add in 20220716
            case "蕈王鉤喙":
                return "/drawable/majestic_hooked_beak"+IMG_FORMAT;
            case "藏雷野實":
                return "/drawable/thunderclap_fruitcore"+IMG_FORMAT;
            //add in 20221001
            case "永續機芯":
                return "/drawable/perpetual_caliber"+IMG_FORMAT;
            case "導光四面體":
                return "/drawable/light_guiding_tetrahedron"+IMG_FORMAT;
            case "滅諍草蔓":
                return "/drawable/quelled_creeper"+IMG_FORMAT;
            //add in 20230115
            case "蒼礫蕊羽":
                return "/drawable/pseudo_stamens"+IMG_FORMAT;
            //add in 20230416
            case "常暗圓環":
                return "/drawable/evergloom_ring"+IMG_FORMAT;

            /** Local*/
            case "小燈草":
                return "/drawable/small_lamp_grass"+IMG_FORMAT;
            case "慕風蘑菇":
                return "/drawable/philanemo_mushroom"+IMG_FORMAT;
            case "夜泊石":
                return "/drawable/noctilous_jade"+IMG_FORMAT;
            case "風車菊":
                return "/drawable/windwheel_aster"+IMG_FORMAT;
            case "石珀":
                return "/drawable/cor_lapis"+IMG_FORMAT;
            case "蒲公英籽":
                return "/drawable/dandelion_seed"+IMG_FORMAT;
            case "嘟嘟蓮":
                return "/drawable/calla_lily"+IMG_FORMAT;
            case "落落莓":
                return "/drawable/valberry"+IMG_FORMAT;
            case "琉璃百合":
                return "/drawable/glaze_lily"+IMG_FORMAT;
            case "琉璃袋":
                return "/drawable/violetgrass"+IMG_FORMAT;
            case "鉤鉤果":
                return "/drawable/wolfhook"+IMG_FORMAT;
            case "塞西莉亞花":
                return "/drawable/cecilia"+IMG_FORMAT;
            case "絕雲椒椒":
                return "/drawable/jueyun_chili"+IMG_FORMAT;
            case "霓裳花":
                return "/drawable/silk_flower"+IMG_FORMAT;
            case "星螺":
                return "/drawable/starconch"+IMG_FORMAT;
            case "清心":
                return "/drawable/qingxin"+IMG_FORMAT;
            case "海靈芝":
                return "/drawable/sea_ganoderma"+IMG_FORMAT;
            case "緋櫻繡球":
                return "/drawable/sakura_bloom"+IMG_FORMAT;
            case "鳴草":
                return "/drawable/naku_weed"+IMG_FORMAT;
            case "晶化骨髓":
                return "/drawable/crystal_marrow"+IMG_FORMAT;
            //add in 20210910
            case "天雲草實":
                return "/drawable/amakumo_fruit"+IMG_FORMAT;
            case "血斛":
                return "/drawable/dendrobium"+IMG_FORMAT;
            case "幽燈蕈":
                return "/drawable/fluorescent_fungus"+IMG_FORMAT;
            case "珊瑚真珠":
                return "/drawable/sango_pearl"+IMG_FORMAT;
            //add in 20220517
            case "鬼兜蟲":
                return "/drawable/onikabuto"+IMG_FORMAT;
            //add in 20220716
            case "樹王聖體菇":
                return "/drawable/onikabuto"+IMG_FORMAT;
            case "月蓮":
                return "/drawable/lunar_lotus"+IMG_FORMAT;
            case "劫波蓮":
                return "/drawable/kalpalata"+IMG_FORMAT;
            //add in 20230115
            case "赤念果":
                return "/drawable/redcrest"+IMG_FORMAT;
            case "聖金蟲":
                return "/drawable/scarab"+IMG_FORMAT;
            case "帕蒂沙蘭":
                return "/drawable/padisarah"+IMG_FORMAT;
            case "沙脂蛹":
                return "/drawable/sand_grease_pupa"+IMG_FORMAT;
            case "悼靈花":
                return "/drawable/mourning_flower"+IMG_FORMAT;

            /** T-Boss*/
            case "北風之環":
                return "/drawable/ring_of_boreas"+IMG_FORMAT;
            case "東風的吐息":
                return "/drawable/dvalins_sigh"+IMG_FORMAT;
            case "東風之翎":
                return "/drawable/dvalins_plume"+IMG_FORMAT;
            case "北風的魂匣":
                return "/drawable/spirit_locket_of_boreas"+IMG_FORMAT;
            case "東風之爪":
                return "/drawable/dvalins_claw"+IMG_FORMAT;
            case "北風之尾":
                return "/drawable/tail_of_boreas"+IMG_FORMAT;
            case "魔王之刃·殘片":
                return "/drawable/shard_of_foul_legacy"+IMG_FORMAT;
            case "吞天之鯨·只角":
                return "/drawable/tusk_of_monoceros_caeli"+IMG_FORMAT;
            case "武煉之魂·孤影":
                return "/drawable/shadow_of_the_warrior"+IMG_FORMAT;
            case "龍王之冕":
                return "/drawable/dragon_lords_crown"+IMG_FORMAT;
            case "血玉之枝":
                return "/drawable/bloodjade_branch"+IMG_FORMAT;
            case "鎏金之鱗":
                return "/drawable/gilded_scale"+IMG_FORMAT;
            //add in 20210910
            case "熔毀之刻":
                return "/drawable/molten_moment"+IMG_FORMAT;
            case "灰燼之心":
                return "/drawable/ashen_heart"+IMG_FORMAT;
            case "獄火之蝶":
                return "/drawable/hellfire_butterfly"+IMG_FORMAT;
            //add in 20220216
            case "萬劫之真意":
                return "/drawable/the_meaning_of_aeons"+IMG_FORMAT;
            case "凶將之手眼":
                return "/drawable/mudra_of_the_malefic_general"+IMG_FORMAT;
            case "禍神之禊淚":
                return "/drawable/tears_of_the_calamitous_god"+IMG_FORMAT;
            //add in 20221102
            case "傀儡的懸絲":
                return "/drawable/puppet_strings"+IMG_FORMAT;
            case "無心的淵鏡":
                return "/drawable/miirror_of_mushin"+IMG_FORMAT;
            case "空行的虛鈴":
                return "/drawable/dakas_bell"+IMG_FORMAT;
            //add in 20230416
            case "原初綠洲之初綻":
                return "/drawable/primordial_greenbloom"+IMG_FORMAT;
            case "生長天地之蕨草":
                return "/drawable/worldspan_fern"+IMG_FORMAT;
            case "亙古樹海之一瞬":
                return "/drawable/everamber"+IMG_FORMAT;

            /** Common*/
            case "牢固的箭簇":
                return "/drawable/firm_arrowhead"+IMG_FORMAT;
            case "銳利的箭簇":
                return "/drawable/sharp_arrowhead"+IMG_FORMAT;
            case "歷戰的箭簇":
                return "/drawable/weathered_arrowhead"+IMG_FORMAT;
            case "導能繪卷":
                return "/drawable/divining_scroll"+IMG_FORMAT;
            case "封魔繪卷":
                return "/drawable/sealed_scroll"+IMG_FORMAT;
            case "禁咒繪卷":
                return "/drawable/forbidden_curse_scroll"+IMG_FORMAT;
            case "尋寶鴉印":
                return "/drawable/treasure_hoarder_insignia"+IMG_FORMAT;
            case "藏銀鴉印":
                return "/drawable/silver_raven_insignia"+IMG_FORMAT;
            case "攫金鴉印":
                return "/drawable/golden_raven_insignia"+IMG_FORMAT;
            case "破損的面具":
                return "/drawable/damaged_mask"+IMG_FORMAT;
            case "污穢的面具":
                return "/drawable/stained_mask"+IMG_FORMAT;
            case "不祥的面具":
                return "/drawable/ominous_mask"+IMG_FORMAT;
            case "新兵的徽記":
                return "/drawable/recruits_insignia"+IMG_FORMAT;
            case "士官的徽記":
                return "/drawable/sergeants_insignia"+IMG_FORMAT;
            case "尉官的徽記":
                return "/drawable/lieutenants_insignia"+IMG_FORMAT;
            case "騙騙花蜜":
                return "/drawable/whopperflower_nectar"+IMG_FORMAT;
            case "微光花蜜":
                return "/drawable/shimmering_nectar"+IMG_FORMAT;
            case "原素花蜜":
                return "/drawable/energy_nectar"+IMG_FORMAT;
            case "史萊姆凝液":
                return "/drawable/slime_condensate"+IMG_FORMAT;
            case "史萊姆清":
                return "/drawable/slime_secretions"+IMG_FORMAT;
            case "史萊姆原漿":
                return "/drawable/slime_concentrate"+IMG_FORMAT;
            case "破舊的刀鐔":
                return "/drawable/old_handguard"+IMG_FORMAT;
            case "影打刀鐔":
                return "/drawable/kageuchi_handguard"+IMG_FORMAT;
            case "名刀鐔":
                return "/drawable/famed_handguard"+IMG_FORMAT;
            //add in 20210910
            case "浮游乾核":
                return "/drawable/spectral_husk"+IMG_FORMAT;
            case "浮游幽核":
                return "/drawable/spectral_heart"+IMG_FORMAT;
            case "浮游晶化核":
                return "/drawable/spectral_nucleus"+IMG_FORMAT;
            //add in 20220716
            case "褪色紅綢":
                return "/drawable/faded_red_satin"+IMG_FORMAT;
            case "鑲邊紅綢":
                return "/drawable/trimmed_red_silk"+IMG_FORMAT;
            case "織金紅綢":
                return "/drawable/rich_red_brocade"+IMG_FORMAT;
            case "蕈獸孢子":
                return "/drawable/fungal_spores"+IMG_FORMAT;
            case "螢光孢粉":
                return "/drawable/luminescent_pollen"+IMG_FORMAT;
            case "孢囊晶塵":
                return "/drawable/crystalline_cyst_dust"+IMG_FORMAT;
            case "晦暗刻像":
                return "/drawable/gloomy_statuette"+IMG_FORMAT;
            case "夤夜刻像":
                return "/drawable/dark_statuette"+IMG_FORMAT;
            case "幽邃刻像":
                return "/drawable/deathly_statuette"+IMG_FORMAT;
            //add in 20230416
            case "來自何處的待放之花":
                return "/drawable/a_flower_yet_to_bloom"+IMG_FORMAT;
            case "何人所珍藏之花":
                return "/drawable/treasured_flower"+IMG_FORMAT;
            case "漫遊者的盛放之花":
                return "/drawable/wanderers_blooming_flower"+IMG_FORMAT;

            // P.S. There still have Sumeru items not added yet since the name are undefinded or unable to define in there. => 20220716
            // ♪ Added Sumeru items
            //add in 20220823
            case "謐林涓露的銅符":
                return "/drawable/copper_talisman_of_the_forest_dew"+IMG_FORMAT;
            case "謐林涓露的鐵符":
                return "/drawable/iron_talisman_of_the_forest_dew"+IMG_FORMAT;
            case "謐林涓露的銀符":
                return "/drawable/silver_talisman_of_the_forest_dew"+IMG_FORMAT;
            case "謐林涓露的金符":
                return "/drawable/golden_talisman_of_the_forest_dew"+IMG_FORMAT;
            case "綠洲花園的追憶":
                return "/drawable/oasis_gardens_reminiscence"+IMG_FORMAT;
            case "綠洲花園的恩惠":
                return "/drawable/oasis_gardens_kindness"+IMG_FORMAT;
            case "綠洲花園的哀思":
                return "/drawable/oasis_gardens_mourning"+IMG_FORMAT;
            case "綠洲花園的真諦":
                return "/drawable/oasis_gardens_truth"+IMG_FORMAT;
            case "烈日威權的殘響":
                return "/drawable/echo_of_scorching_might"+IMG_FORMAT;
            case "烈日威權的餘光":
                return "/drawable/remnant_glow_of_scorching_might"+IMG_FORMAT;
            case "烈日威權的夢想":
                return "/drawable/dream_of_scorching_might"+IMG_FORMAT;
            case "烈日威權的舊日":
                return "/drawable/olden_days_of_scorching_might"+IMG_FORMAT;

            //add in 20211024 (RE)
            case "漆黑隕鐵的一粒":
                return "/drawable/grain_of_aerosiderite"+IMG_FORMAT;
            case "漆黑隕鐵的一片":
                return "/drawable/piece_of_aerosiderite"+IMG_FORMAT;
            case "漆黑隕鐵的一角":
                return "/drawable/bit_of_aerosiderite"+IMG_FORMAT;
            case "漆黑隕鐵的一塊":
                return "/drawable/chunk_of_aerosiderite"+IMG_FORMAT;
            case "鳴神御靈的明惠":
                return "/drawable/narukamis_wisdom"+IMG_FORMAT;
            case "鳴神御靈的歡喜":
                return "/drawable/narukamis_joy"+IMG_FORMAT;
            case "鳴神御靈的親愛":
                return "/drawable/narukamis_affection"+IMG_FORMAT;
            case "鳴神御靈的勇武":
                return "/drawable/narukamis_valor"+IMG_FORMAT;
            case "遠海夷地的瑚枝":
                return "/drawable/coral_branch_of_a_distant_sea"+IMG_FORMAT;
            case "遠海夷地的玉枝":
                return "/drawable/jeweled_branch_of_a_distant_sea"+IMG_FORMAT;
            case "遠海夷地的瓊枝":
                return "/drawable/jade_branch_of_a_distant_sea"+IMG_FORMAT;
            case "遠海夷地的金枝":
                return "/drawable/golden_branch_of_a_distant_sea"+IMG_FORMAT;
            case "凜風奔狼的始齔":
                return "/drawable/boreal_wolfs_milk_tooth"+IMG_FORMAT;
            case "凜風奔狼的裂齒":
                return "/drawable/boreal_wolfs_cracked_tooth"+IMG_FORMAT;
            case "凜風奔狼的斷牙":
                return "/drawable/boreal_wolfs_broken_fang"+IMG_FORMAT;
            case "凜風奔狼的懷鄉":
                return "/drawable/boreal_wolfs_nostalgia"+IMG_FORMAT;
            case "高塔孤王的破瓦":
                return "/drawable/tile_of_decarabians_tower"+IMG_FORMAT;
            case "高塔孤王的殘垣":
                return "/drawable/debris_of_decarabians_city"+IMG_FORMAT;
            case "高塔孤王的斷片":
                return "/drawable/fragment_of_decarabians_epic"+IMG_FORMAT;
            case "高塔孤王的碎夢":
                return "/drawable/scattered_piece_of_decarabianss_dream"+IMG_FORMAT;
            case "霧海雲間的鉛丹":
                return "/drawable/mist_veiled_lead_elixir"+IMG_FORMAT;
            case "霧海雲間的汞丹":
                return "/drawable/mist_veiled_mercury_elixir"+IMG_FORMAT;
            case "霧海雲間的金丹":
                return "/drawable/mist_veiled_gold_elixir"+IMG_FORMAT;
            case "霧海雲間的轉還":
                return "/drawable/mist_veiled_primo_elixir"+IMG_FORMAT;
            case "獅牙鬥士的枷鎖":
                return "/drawable/fetters_of_the_dandelion_gladiator"+IMG_FORMAT;
            case "獅牙鬥士的鐵鍊":
                return "/drawable/chains_of_the_dandelion_gladiator"+IMG_FORMAT;
            case "獅牙鬥士的鐐銬":
                return "/drawable/shackles_of_the_dandelion_gladiator"+IMG_FORMAT;
            case "獅牙鬥士的理想":
                return "/drawable/dream_of_the_dandelion_gladiator"+IMG_FORMAT;
            case "孤雲寒林的光砂":
                return "/drawable/luminous_sands_from_guyun"+IMG_FORMAT;
            case "孤雲寒林的輝岩":
                return "/drawable/lustrous_stone_from_guyun"+IMG_FORMAT;
            case "孤雲寒林的聖骸":
                return "/drawable/relic_from_guyun"+IMG_FORMAT;
            case "孤雲寒林的神體":
                return "/drawable/divine_body_from_guyun"+IMG_FORMAT;
            case "今昔劇畫的惡尉":
                return "/drawable/mask_of_the_wicked_lieutenant"+IMG_FORMAT;
            case "今昔劇畫的虎囓":
                return "/drawable/mask_of_the_tigers_bite"+IMG_FORMAT;
            case "今昔劇畫的一角":
                return "/drawable/mask_of_the_one_horned"+IMG_FORMAT;
            case "今昔劇畫的鬼人":
                return "/drawable/mask_of_the_kijin"+IMG_FORMAT;
            case "混沌機關":
                return "/drawable/chaos_gear"+IMG_FORMAT;
            case "混沌樞紐":
                return "/drawable/chaos_axis"+IMG_FORMAT;
            case "混沌真眼":
                return "/drawable/chaos_oculus"+IMG_FORMAT;
            case "混沌裝置":
                return "/drawable/chaos_device"+IMG_FORMAT;
            case "混沌迴路":
                return "/drawable/chaos_circuit"+IMG_FORMAT;
            case "混沌爐心":
                return "/drawable/chaos_core"+IMG_FORMAT;
            case "脆弱的骨片":
                return "/drawable/sturdy_bone_shard"+IMG_FORMAT;
            case "結實的骨片":
                return "/drawable/fragile_bone_shard"+IMG_FORMAT;
            case "石化的骨片":
                return "/drawable/fossilized_bone_shard"+IMG_FORMAT;
            case "霧虛花粉":
                return "/drawable/mist_grass_pollen"+IMG_FORMAT;
            case "霧虛草囊":
                return "/drawable/mist_grass_wick"+IMG_FORMAT;
            case "霧虛燈芯":
                return "/drawable/mist_grass"+IMG_FORMAT;
            case "獵兵祭刀":
                return "/drawable/hunters_sacrificial_knife"+IMG_FORMAT;
            case "特工祭刀":
                return "/drawable/inspectors_sacrificial_knife"+IMG_FORMAT;
            case "督察長祭刀":
                return "/drawable/agents_sacrificial_knife"+IMG_FORMAT;
            case "沉重號角":
                return "/drawable/heavy_horn"+IMG_FORMAT;
            case "黑銅號角":
                return "/drawable/black_bronze_horn"+IMG_FORMAT;
            case "黑晶號角":
                return "/drawable/black_crystal_horn"+IMG_FORMAT;
            case "地脈的舊枝":
                return "/drawable/dead_ley_line_branch"+IMG_FORMAT;
            case "地脈的枯葉":
                return "/drawable/dead_ley_line_leaves"+IMG_FORMAT;
            case "地脈的新芽":
                return "/drawable/ley_line_sprout"+IMG_FORMAT;
            case "黯淡棱鏡":
                return "/drawable/dismal_prism"+IMG_FORMAT;
            case "水晶棱鏡":
                return "/drawable/crystal_prism"+IMG_FORMAT;
            case "偏光棱鏡":
                return "/drawable/polarizing_prism"+IMG_FORMAT;
            case "隱獸指爪":
                return "/drawable/concealed_claw"+IMG_FORMAT;
            case "隱獸利爪":
                return "/drawable/concealed_unguis"+IMG_FORMAT;
            case "隱獸鬼爪":
                return "/drawable/concealed_talon"+IMG_FORMAT;
            //add in 20220823
            case "失活菌核":
                return "/drawable/inactivated_fungal_nucleus"+IMG_FORMAT;
            case "休眠菌核":
                return "/drawable/dormant_fungal_nucleus"+IMG_FORMAT;
            case "茁壯菌核":
                return "/drawable/robust_fungal_nucleus"+IMG_FORMAT;
            case "混沌容器":
                return "/drawable/chaos_storage"+IMG_FORMAT;
            case "混沌模組":
                return "/drawable/chaos_module"+IMG_FORMAT;
            case "混沌錨栓":
                return "/drawable/chaos_bolt"+IMG_FORMAT;
            //add in 20220924
            case "破缺稜晶":
                return "/drawable/damaged_prism"+IMG_FORMAT;
            case "混濁稜晶":
                return "/drawable/turbid_prism"+IMG_FORMAT;
            case "輝光稜晶":
                return "/drawable/radiant_prism"+IMG_FORMAT;
            //add in 20230225
            case "殘毀的橫脊":
                return "/drawable/desiccated_shell"+IMG_FORMAT;
            case "密固的橫脊":
                return "/drawable/sturdy_shell"+IMG_FORMAT;
            case "鍥紋的橫脊":
                return "/drawable/marked_shell"+IMG_FORMAT;

            /** T-Book*/
            case "「自由」的教導":
                return "/drawable/teaching_of_freedom"+IMG_FORMAT;
            case "「黃金」的教導":
                return "/drawable/teaching_of_gold"+IMG_FORMAT;
            case "「抗爭」的教導":
                return "/drawable/teaching_of_resistance"+IMG_FORMAT;
            case "「繁榮」的教導":
                return "/drawable/teaching_of_prosperity"+IMG_FORMAT;
            case "「詩文」的教導":
                return "/drawable/teaching_of_ballad"+IMG_FORMAT;
            case "「勤勞」的教導":
                return "/drawable/teaching_of_diligence"+IMG_FORMAT;
            case "「風雅」的教導":
                return "/drawable/teachings_of_elegance"+IMG_FORMAT;
            case "「浮世」的教導":
                return "/drawable/teachings_of_transience"+IMG_FORMAT;
            case "「天光」的教導":
                return "/drawable/teachings_of_light"+IMG_FORMAT;
            case "「自由」的指引":
                return "/drawable/guide_to_freedom"+IMG_FORMAT;
            case "「黃金」的指引":
                return "/drawable/guide_to_gold"+IMG_FORMAT;
            case "「抗爭」的指引":
                return "/drawable/guide_to_resistance"+IMG_FORMAT;
            case "「繁榮」的指引":
                return "/drawable/guide_to_prosperity"+IMG_FORMAT;
            case "「詩文」的指引":
                return "/drawable/guide_to_ballad"+IMG_FORMAT;
            case "「勤勞」的指引":
                return "/drawable/guide_to_diligence"+IMG_FORMAT;
            case "「風雅」的指引":
                return "/drawable/guide_of_elegance"+IMG_FORMAT;
            case "「浮世」的指引":
                return "/drawable/guide_of_transience"+IMG_FORMAT;
            case "「天光」的指引":
                return "/drawable/guide_of_light"+IMG_FORMAT;
            case "「自由」的哲學":
                return "/drawable/philosophies_of_freedom"+IMG_FORMAT;
            case "「黃金」的哲學":
                return "/drawable/philosophies_of_gold"+IMG_FORMAT;
            case "「抗爭」的哲學":
                return "/drawable/philosophies_of_resistance"+IMG_FORMAT;
            case "「繁榮」的哲學":
                return "/drawable/philosophies_of_prosperity"+IMG_FORMAT;
            case "「詩文」的哲學":
                return "/drawable/philosophies_of_ballad"+IMG_FORMAT;
            case "「勤勞」的哲學":
                return "/drawable/philosophies_of_diligence"+IMG_FORMAT;
            case "「風雅」的哲學":
                return "/drawable/philosophies_of_elegance"+IMG_FORMAT;
            case "「浮世」的哲學":
                return "/drawable/philosophies_of_transience"+IMG_FORMAT;
            case "「天光」的哲學":
                return "/drawable/philosophies_of_light"+IMG_FORMAT;
            //add in 20220823
            case "「篤行」的教導":
                return "/drawable/teachings_of_admonition"+IMG_FORMAT;
            case "「巧思」的教導":
                return "/drawable/teachings_of_ingenuity"+IMG_FORMAT;
            case "「諍言」的教導":
                return "/drawable/teachings_of_praxis"+IMG_FORMAT;
            case "「篤行」的指引":
                return "/drawable/guide_to_admonition"+IMG_FORMAT;
            case "「巧思」的指引":
                return "/drawable/guide_to_ingenuity"+IMG_FORMAT;
            case "「諍言」的指引":
                return "/drawable/guide_to_praxis"+IMG_FORMAT;
            case "「篤行」的哲學":
                return "/drawable/philosophies_of_admonition"+IMG_FORMAT;
            case "「巧思」的哲學":
                return "/drawable/philosophies_of_ingenuity"+IMG_FORMAT;
            case "「諍言」的哲學":
                return "/drawable/philosophies_of_praxis"+IMG_FORMAT;

            /** Crystal*/
            case "燃願瑪瑙碎屑":
                return "/drawable/agnidus_agate_sliver"+IMG_FORMAT;
            case "燃願瑪瑙斷片":
                return "/drawable/agnidus_agate_fragment"+IMG_FORMAT;
            case "燃願瑪瑙塊":
                return "/drawable/agnidus_agate_chunk"+IMG_FORMAT;
            case "燃願瑪瑙":
                return "/drawable/agnidus_agate_gemstone"+IMG_FORMAT;
            case "滌淨青金碎屑":
                return "/drawable/varunada_lazurite_sliver"+IMG_FORMAT;
            case "滌淨青金斷片":
                return "/drawable/varunada_lazurite_fragment"+IMG_FORMAT;
            case "滌淨青金塊":
                return "/drawable/varunada_lazurite_chunk"+IMG_FORMAT;
            case "滌淨青金":
                return "/drawable/varunada_lazurite_gemstone"+IMG_FORMAT;
            case "最勝紫晶碎屑":
                return "/drawable/vajrada_amethyst_sliver"+IMG_FORMAT;
            case "最勝紫晶斷片":
                return "/drawable/vajrada_amethyst_fragment"+IMG_FORMAT;
            case "最勝紫晶塊":
                return "/drawable/vajrada_amethyst_chunk"+IMG_FORMAT;
            case "最勝紫晶":
                return "/drawable/vajrada_amethyst_gemstone"+IMG_FORMAT;
            case "哀敘冰玉碎屑":
                return "/drawable/shivada_jade_sliver"+IMG_FORMAT;
            case "哀敘冰玉斷片":
                return "/drawable/shivada_jade_fragment"+IMG_FORMAT;
            case "哀敘冰玉塊":
                return "/drawable/shivada_jade_chunk"+IMG_FORMAT;
            case "哀敘冰玉":
                return "/drawable/shivada_jade_gemstone"+IMG_FORMAT;
            case "自在松石碎屑":
                return "/drawable/vayuda_turquoise_sliver"+IMG_FORMAT;
            case "自在松石斷片":
                return "/drawable/vayuda_turquoise_fragment"+IMG_FORMAT;
            case "自在松石塊":
                return "/drawable/vayuda_turquoise_chunk"+IMG_FORMAT;
            case "自在松石":
                return "/drawable/vayuda_turquoise_gemstone"+IMG_FORMAT;
            case "堅牢黃玉碎屑":
                return "/drawable/prithiva_topaz_sliver"+IMG_FORMAT;
            case "堅牢黃玉斷片":
                return "/drawable/prithiva_topaz_fragment"+IMG_FORMAT;
            case "堅牢黃玉塊":
                return "/drawable/prithiva_topaz_chunk"+IMG_FORMAT;
            case "堅牢黃玉":
                return "/drawable/prithiva_topaz_gemstone"+IMG_FORMAT;
            //add in 20220714
            case "生長碧翡碎屑":
                return "/drawable/nagadus_emerald_sliver"+IMG_FORMAT;
            case "生長碧翡斷片":
                return "/drawable/nagadus_emerald_fragment"+IMG_FORMAT;
            case "生長碧翡塊":
                return "/drawable/nagadus_emerald_chunk"+IMG_FORMAT;
            case "生長碧翡":
                return "/drawable/nagadus_emerald_gemstone"+IMG_FORMAT;
            case "璀璨原鑽碎屑":
                return "/drawable/brilliant_diamond_sliver"+IMG_FORMAT;
            case "璀璨原鑽斷片":
                return "/drawable/brilliant_diamond_fragment"+IMG_FORMAT;
            case "璀璨原鑽塊":
                return "/drawable/brilliant_diamond_chunk"+IMG_FORMAT;
            case "璀璨原鑽":
                return "/drawable/brilliant_diamond_gemstone"+IMG_FORMAT;

            /** Others*/
            case "智識之冕":
                return "/drawable/crown_of_sagehood"+IMG_FORMAT;
            case "摩拉":
                return "/drawable/mora"+IMG_FORMAT;
            case "流浪者的經驗":
                return "/drawable/wanderers_advice"+IMG_FORMAT;
            case "冒險家的經驗":
                return "/drawable/adventurers_experience"+IMG_FORMAT;
            case "大英雄的經驗":
                return "/drawable/heros_wit"+IMG_FORMAT;
            case "精鍛用雜礦":
                return "/drawable/enchancement_ore"+IMG_FORMAT;
            case "精鍛用良礦":
                return "/drawable/fine_enchancement_ore"+IMG_FORMAT;
            case "精鍛用魔礦":
                return "/drawable/mystic_enchancement_ore"+IMG_FORMAT;
            case "祝聖油膏":
                return "/drawable/sanctifying_unction"+IMG_FORMAT;
            case "祝聖精華":
                return "/drawable/sanctifying_essence"+IMG_FORMAT;

            /** Un-released*/
            case "「未知1」的教導":
            case "「未知1」的指引":
            case "「未知1」的哲學":
            case "「未知2」的教導":
            case "「未知2」的指引":
            case "「未知2」的哲學":
            case "「未知3」的教導":
            case "「未知3」的指引":
            case "「未知3」的哲學":
            case "未知周本BOSS跌落物1":
            case "未知周本BOSS跌落物2":
            case "未知周本BOSS跌落物3":
            case "未知常駐BOSS跌落物1":
            case "未知常駐BOSS跌落物2":
            case "未知常駐BOSS跌落物3":
                return "/drawable/hu_tao_unknown"+IMG_FORMAT;
            default:
                return "/drawable/hu_tao_unknown"+IMG_FORMAT;
        }
    }

    // TMP notice : Collei => 「未知1」的哲學 || Dori => 「未知2」的哲學 || Tighnari => 「未知3」的哲學
    // Ok get it and added. (From Voc in 20220823)
    public String getLocaleTeaches (String name, Context context){
        if(name.equals("「自由」的教導")){return context.getString(R.string.teaching_of_freedom);}
        else if(name.equals("「黃金」的教導")){return context.getString(R.string.teaching_of_gold);}
        else if(name.equals("「抗爭」的教導")){return context.getString(R.string.teaching_of_resistance);}
        else if(name.equals("「繁榮」的教導")){return context.getString(R.string.teaching_of_prosperity);}
        else if(name.equals("「詩文」的教導")){return context.getString(R.string.teaching_of_ballad);}
        else if(name.equals("「勤勞」的教導")){return context.getString(R.string.teaching_of_diligence);}
        else if(name.equals("「風雅」的教導")){return context.getString(R.string.teachings_of_elegance);}
        else if(name.equals("「浮世」的教導")){return context.getString(R.string.teachings_of_transience);}
        else if(name.equals("「天光」的教導")){return context.getString(R.string.teachings_of_light);}
        else if(name.equals("「諍言」的教導")){return context.getString(R.string.teachings_of_admonition);}
        else if(name.equals("「巧思」的教導")){return context.getString(R.string.teachings_of_ingenuity);}
        else if(name.equals("「篤行」的教導")){return context.getString(R.string.teachings_of_praxis);}

        else if(name.equals("「自由」的指引")){return context.getString(R.string.guide_of_freedom);}
        else if(name.equals("「黃金」的指引")){return context.getString(R.string.guide_of_gold);}
        else if(name.equals("「抗爭」的指引")){return context.getString(R.string.guide_of_resistance);}
        else if(name.equals("「繁榮」的指引")){return context.getString(R.string.guide_of_prosperity);}
        else if(name.equals("「詩文」的指引")){return context.getString(R.string.guide_of_ballad);}
        else if(name.equals("「勤勞」的指引")){return context.getString(R.string.guide_of_diligence);}
        else if(name.equals("「風雅」的指引")){return context.getString(R.string.guides_of_elegance);}
        else if(name.equals("「浮世」的指引")){return context.getString(R.string.guides_of_transience);}
        else if(name.equals("「天光」的指引")){return context.getString(R.string.guides_of_light);}
        else if(name.equals("「諍言」的指引")){return context.getString(R.string.guides_of_admonition);}
        else if(name.equals("「巧思」的指引")){return context.getString(R.string.guides_of_ingenuity);}
        else if(name.equals("「篤行」的指引")){return context.getString(R.string.guides_of_praxis);}

        else if(name.equals("「自由」的哲學")){return context.getString(R.string.philosophies_of_freedom);}
        else if(name.equals("「黃金」的哲學")){return context.getString(R.string.philosophies_of_gold);}
        else if(name.equals("「抗爭」的哲學")){return context.getString(R.string.philosophies_of_resistance);}
        else if(name.equals("「繁榮」的哲學")){return context.getString(R.string.philosophies_of_prosperity);}
        else if(name.equals("「詩文」的哲學")){return context.getString(R.string.philosophies_of_ballad);}
        else if(name.equals("「勤勞」的哲學")){return context.getString(R.string.philosophies_of_diligence);}
        else if(name.equals("「風雅」的哲學")){return context.getString(R.string.philosophiess_of_elegance);}
        else if(name.equals("「浮世」的哲學")){return context.getString(R.string.philosophiess_of_transience);}
        else if(name.equals("「天光」的哲學")){return context.getString(R.string.philosophiess_of_light);}
        else if(name.equals("「諍言」的哲學")){return context.getString(R.string.philosophies_of_admonition);}
        else if(name.equals("「巧思」的哲學")){return context.getString(R.string.philosophies_of_ingenuity);}
        else if(name.equals("「篤行」的哲學")){return context.getString(R.string.philosophies_of_praxis);}

        else {return context.getString(R.string.unknown);}

    }

    public String getLocaleBirth (String str,Context context,boolean isThinSize) {
        if(!str.equals("SET_BY_PLAYER") && !str.equals("N/A")){
            String[] date = str.split("/");
            int month = Integer.parseInt(date[0]);
            int day = Integer.parseInt(date[1]);

            if(isThinSize){
                String monthS = String.valueOf(month);
                String dayS = String.valueOf(day);
                if(month < 10){monthS = "0"+String.valueOf(month);}
                if(day < 10){dayS = "0"+String.valueOf(day);}
                return monthS+"-"+dayS;
            }else{
                String returns = "Jan 1st";
                if(month == 1){returns = context.getString(R.string.date_jan);}
                else if(month == 2){returns = context.getString(R.string.date_feb);}
                else if(month == 3){returns = context.getString(R.string.date_mar);}
                else if(month == 4){returns = context.getString(R.string.date_apr);}
                else if(month == 5){returns = context.getString(R.string.date_may);}
                else if(month == 6){returns = context.getString(R.string.date_jun);}
                else if(month == 7){returns = context.getString(R.string.date_jul);}
                else if(month == 8){returns = context.getString(R.string.date_aug);}
                else if(month == 9){returns = context.getString(R.string.date_sep);}
                else if(month == 10){returns = context.getString(R.string.date_oct);}
                else if(month == 11){returns = context.getString(R.string.date_nov);}
                else if(month == 12){returns = context.getString(R.string.date_dec);}

                if(day == 1){returns = returns + context.getString(R.string.date_1st);}
                else if(day == 2){returns = returns + context.getString(R.string.date_2nd);}
                else if(day == 3){returns = returns + context.getString(R.string.date_3rd);}
                else if(day == 4){returns = returns + context.getString(R.string.date_4th);}
                else if(day == 5){returns = returns + context.getString(R.string.date_5th);}
                else if(day == 6){returns = returns + context.getString(R.string.date_6th);}
                else if(day == 7){returns = returns + context.getString(R.string.date_7th);}
                else if(day == 8){returns = returns + context.getString(R.string.date_8th);}
                else if(day == 9){returns = returns + context.getString(R.string.date_9th);}
                else if(day == 10){returns = returns + context.getString(R.string.date_10th);}
                else if(day == 11){returns = returns + context.getString(R.string.date_11th);}
                else if(day == 12){returns = returns + context.getString(R.string.date_12th);}
                else if(day == 13){returns = returns + context.getString(R.string.date_13th);}
                else if(day == 14){returns = returns + context.getString(R.string.date_14th);}
                else if(day == 15){returns = returns + context.getString(R.string.date_15th);}
                else if(day == 16){returns = returns + context.getString(R.string.date_16th);}
                else if(day == 17){returns = returns + context.getString(R.string.date_17th);}
                else if(day == 18){returns = returns + context.getString(R.string.date_18th);}
                else if(day == 19){returns = returns + context.getString(R.string.date_19th);}
                else if(day == 20){returns = returns + context.getString(R.string.date_20th);}
                else if(day == 21){returns = returns + context.getString(R.string.date_21st);}
                else if(day == 22){returns = returns + context.getString(R.string.date_22nd);}
                else if(day == 23){returns = returns + context.getString(R.string.date_23rd);}
                else if(day == 24){returns = returns + context.getString(R.string.date_24th);}
                else if(day == 25){returns = returns + context.getString(R.string.date_25th);}
                else if(day == 26){returns = returns + context.getString(R.string.date_26th);}
                else if(day == 27){returns = returns + context.getString(R.string.date_27th);}
                else if(day == 28){returns = returns + context.getString(R.string.date_28th);}
                else if(day == 29){returns = returns + context.getString(R.string.date_29th);}
                else if(day == 30){returns = returns + context.getString(R.string.date_30th);}
                else if(day == 31){returns = returns + context.getString(R.string.date_31st);}
                return returns;
            }
        }else if(str.equals("SET_BY_PLAYER")){
            return context.getString(R.string.set_by_player);
        }else if(str.equals("N/A")){
            return context.getString(R.string.obtain_no);
        }else {return context.getString(R.string.obtain_no);}
    }

    public String char_birth(int moy, int dom){
        String char_name = "EMPTY";
        switch (moy){
            case Calendar.JANUARY: {
                switch (dom) {
                    case 9 : char_name = "Thoma"; break;
                    case 18 : char_name = "Diona"; break;
                    case 24 : char_name = "Rosaria"; break;
                    case 26 : char_name = "朝霧冰瀬"; break;
                }
                break;
            }
            case Calendar.FEBRUARY: {
                switch (dom) {
                    case 14 : char_name = "Beidou"; break;
                    case 22 : char_name = "Sangonomiya Kokomi"; break;
                    case 29 : char_name = "Bennett"; break;
                }
                break;
            }
            case Calendar.MARCH: {
                switch (dom) {
                    case 3 : char_name = "Qiqi"; break;
                    case 10 : char_name = "Shenhe"; break;
                    case 14 : char_name = "Jean"; break;
                    case 21 : char_name = "Noelle"; break;
                    case 26 : char_name = "Kamisato Ayato"; break;
                }
                break;
            }
            case Calendar.APRIL: {
                switch (dom) {
                    case 4 : char_name = "Aloy"; break;
                    case 17 : char_name = "Xiao"; break;
                    case 20 : char_name = "Yelan"; break;
                    case 30 : char_name = "Diluc"; break;
                }
                break;
            }
            case Calendar.MAY: {
                switch (dom) {
                    case 3 : char_name = "Candace"; break;
                    case 8 : char_name = "Collei"; break;
                    case 18 : char_name = "Gorou"; break;
                    case 21 : char_name = "Yun Jin"; break;
                    case 27 : char_name = "Fischl"; break;
                }
                break;
            }
            case Calendar.JUNE: {
                switch (dom) {
                    case 1 : char_name = "Arataki Itto"; break;
                    case 6 : char_name = "Lisa"; break;
                    case 9 : char_name = "Venti"; break;
                    case 21 : char_name = "Yoimiya"; break;
                    case 23 : char_name = "Cyno"; break;
                    case 26 : char_name = "Raiden Shogun"; break;
                    case 27 : char_name = "Yae Miko"; break;
                    case 28 : char_name = "Kuki Shinobu"; break;
                }
                break;
            }
            case Calendar.JULY: {
                switch (dom) {
                    case 5 : char_name = "Barbara"; break;
                    case 14 : char_name = "Kujou Sara"; break;
                    case 15 : char_name = "Hu Tao"; break;
                    case 19 : char_name = "Voc-夜芷冰"; break;
                    case 20 : char_name = "Tartaglia"; break;
                    case 24 : char_name = "Shikanoin Heizou"; break;
                    case 27 : char_name = "Klee"; break;
                    case 28 : char_name = "Yanfei"; break;
                }
                break;
            }
            case Calendar.AUGUST: {
                switch (dom) {
                    case 10 : char_name = "Amber"; break;
                    case 26 : char_name = "Ningguang"; break;
                    case 31 : char_name = "Mona"; break;
                }
                break;
            }
            case Calendar.SEPTEMBER: {
                switch (dom) {
                    case 7 : char_name = "Diona"; break;
                    case 9 : char_name = "Razor"; break;
                    case 13 : char_name = "Albedo"; break;
                    case 28 : char_name = "Kamisato Ayaka"; break;
                }
                break;
            }
            case Calendar.OCTOBER: {
                switch (dom) {
                    case 9 : char_name = "Xingqiu"; break;
                    case 16 : char_name = "Xinyan"; break;
                    case 19 : char_name = "Sayu"; break;
                    case 25 : char_name = "Eula"; break;
                    case 29 : char_name = "Kaedehara Kazuha"; break;
                }
                break;
            }
            case Calendar.NOVEMBER: {
                switch (dom) {
                    case 2 : char_name = "Xiangling"; break;
                    case 20 : char_name = "Keqing"; break;
                    case 26 : char_name = "Sucrose"; break;
                    case 30 : char_name = "Kaeya"; break;
                }
                break;
            }
            case Calendar.DECEMBER: {
                switch (dom) {
                    case 2 : char_name = "Ganyu"; break;
                    case 3 : char_name = "Nilou"; break;
                    case 21 : char_name = "Dori"; break;
                    case 29 : char_name = "Tighnari"; break;
                    case 31 : char_name = "Zhongli"; break;
                }
                break;
            }

            // 3.2
            // 納西妲 , 萊依拉

        }
        return char_name;
    }

    public String getArtifactBuffName (String nickname,Context context){
        switch (nickname){
            case "baseHP" : return context.getString(R.string.weapon_stat_HP);
            case "baseATK" : return context.getString(R.string.weapon_stat_atk);
            case "baseDEF" : return context.getString(R.string.weapon_stat_DEF);
            case "HP" : return context.getString(R.string.weapon_stat_HPP);
            case "ATK" : return context.getString(R.string.weapon_stat_atkP);
            case "DEF" : return context.getString(R.string.weapon_stat_DEFP);
            case "EleMas" : return context.getString(R.string.weapon_stat_EleMas);
            case "EnRech" : return context.getString(R.string.weapon_stat_EnRechP);
            case "PhyDMG" : return context.getString(R.string.weapon_stat_PhyDMGP);
            case "EleDMG_Electro" : return context.getString(R.string.weapon_stat_EleDMGP_Electro);
            case "EleDMG_Pyro" : return context.getString(R.string.weapon_stat_EleDMGP_Pyro);
            case "EleDMG_Hydro" : return context.getString(R.string.weapon_stat_EleDMGP_Hydro);
            case "EleDMG_Dendro" : return context.getString(R.string.weapon_stat_EleDMGP_Dendor);
            case "EleDMG_Cryo" : return context.getString(R.string.weapon_stat_EleDMGP_Cryo);
            case "EleDMG_Anemo" : return context.getString(R.string.weapon_stat_EleDMGP_Anemo);
            case "EleDMG_Geo" : return context.getString(R.string.weapon_stat_EleDMGP_Geo);
            case "CritRate" : return context.getString(R.string.weapon_stat_CritRateP);
            case "CritDMG" : return context.getString(R.string.weapon_stat_CritDMGP);
            case "Healing" : return context.getString(R.string.weapon_stat_HealingP);

            default: return context.getString(R.string.unknown);
        }
    }

    public String getSkillNameByCustomName(String str, Context context){
        switch (str){
            case "技能傷害" : return context.getString(R.string.skill_dmg);
            case "剎那之花傷害" : return context.getString(R.string.transient_blossom_dmg);
            case "急凍炸彈傷害" : return context.getString(R.string.freeze_bomb_dmg);
            case "冷凍炸彈傷害" : return context.getString(R.string.chillwater_bomblets);
            case "爆炸傷害" : return context.getString(R.string.explosion_dmg);
            case "水珠傷害" : return context.getString(R.string.droplet_dmg);
            case "命中治療量" : return context.getString(R.string.hp_regeneration_per_hit);
            case "持續治療量" : return context.getString(R.string.continuous_regeneration);
            case "命中治療量_BASE" : return context.getString(R.string.hp_regeneration_per_hit_base);
            case "持續治療量_BASE" : return context.getString(R.string.continuous_regeneration_base);
            case "基礎傷害" : return context.getString(R.string.base_dmg);
            case "受擊時傷害提升" : return context.getString(R.string.dmg_bonus_on_hit_taken);
            case "護盾吸收量_BASE" : return context.getString(R.string.base_dmg_base);
            case "點按傷害" : return context.getString(R.string.press_dmg);
            case "一段蓄力傷害" : return context.getString(R.string.charge_level_1_dmg);
            case "二段蓄力傷害" : return context.getString(R.string.charge_level_2_dmg);
            case "一段傷害" : return context.getString(R.string.dmg_1_hit);
            case "二段傷害" : return context.getString(R.string.dmg_2_hit);
            case "三段傷害" : return context.getString(R.string.dmg_3_hit);
            case "貓爪傷害(@1)" : return context.getString(R.string.icy_paw_dmg_per1);
            case "護盾基礎吸收量" : return context.getString(R.string.base_shield_dmg_absorption);
            case "護盾基礎吸收量_BASE" : return context.getString(R.string.base_shield_dmg_absorption_base);
            case "長按傷害" : return context.getString(R.string.hold_dmg);
            case "冰渦之劍傷害" : return context.getString(R.string.icewhirl_brand_dmg);
            case "奧茲攻擊傷害" : return context.getString(R.string.ozs_atk_dmg);
            case "召喚傷害" : return context.getString(R.string.summoning_dmg);
            case "血梅香傷害" : return context.getString(R.string.blood_blossom_dmg);
            case "點按技能傷害" : return context.getString(R.string.press_skill_dmg);
            case "長按技能傷害" : return context.getString(R.string.hold_skill_dmg);
            case "雷楔傷害" : return context.getString(R.string.lightning_stiletto_dmg);
            case "斬擊傷害" : return context.getString(R.string.slashing_dmg);
            case "雷爆連斬傷害" : return context.getString(R.string.thunderclap_slash_dmg);
            case "蹦蹦炸彈傷害" : return context.getString(R.string.jumpy_dumpty_dmg);
            case "詭雷傷害" : return context.getString(R.string.mine_dmg);
            case "天狗咒雷·伏 傷害" : return context.getString(R.string.tengu_juurai_ambush_dmg);
            case "無引雷長按傷害" : return context.getString(R.string.non_conductive_hold_dmg);
            case "一層引雷長按傷害" : return context.getString(R.string.stack_1_conductive_hold_dmg);
            case "二層引雷長按傷害" : return context.getString(R.string.stack_2_conductive_hold_dmg);
            case "三層引雷長按傷害" : return context.getString(R.string.stack_3_conductive_hold_dmg);
            case "持續傷害" : return context.getString(R.string.dot);
            case "吸收量" : return context.getString(R.string.dmg_absorption);
            case "治療量" : return context.getString(R.string.healing);
            case "吸收量_BASE" : return context.getString(R.string.dmg_absorption_base);
            case "治療量_BASE" : return context.getString(R.string.healing_base);
            case "寒病鬼差傷害" : return context.getString(R.string.herald_of_frost_dmg);
            case "協同攻擊傷害" : return context.getString(R.string.coordinated_atk_dmg);
            case "波紋傷害" : return context.getString(R.string.ripple_dmg);
            case "風風輪傷害" : return context.getString(R.string.fuufuu_windwheel_dmg);
            case "風風輪舞踢點按傷害" : return context.getString(R.string.pressfuufuu_whirlwind_kick_dmg);
            case "風風輪舞踢長按傷害" : return context.getString(R.string.fuufuu_whirlwind_kick_hold_dmg);
            case "風風輪附帶元素傷害" : return context.getString(R.string.fuufuu_windwheel_elemental_dmg);
            case "風風輪舞踢長按附帶元素傷害" : return context.getString(R.string.fuufuu_whirlwind_kick_elemental_dmg);
            case "狀態激發傷害" : return context.getString(R.string.stance_change_dmg);
            case "四段傷害" : return context.getString(R.string.dmg_4_hit);
            case "五段傷害" : return context.getString(R.string.dmg_5_hit);
            case "六段傷害" : return context.getString(R.string.dmg_6_hit);
            case "重擊傷害" : return context.getString(R.string.charged_attack_dmg);
            case "斷流·斬 傷害" : return context.getString(R.string.riptide_slash);
            case "護盾吸收量上限" : return context.getString(R.string.max_shield_dmg_absorption);
            case "護盾吸收量上限_BASE" : return context.getString(R.string.max_shield_dmg_absorption_base);
            case "初始切割傷害" : return context.getString(R.string.initial_cutting_dmg);
            case "最大切割傷害" : return context.getString(R.string.max_cutting_dmg);
            case "初始爆風傷害" : return context.getString(R.string.initial_storm_dmg);
            case "最大爆風傷害" : return context.getString(R.string.max_storm_dmg);
            case "噴火傷害" : return context.getString(R.string.flame_dmg);
            case "揮舞傷害" : return context.getString(R.string.swing_dmg);
            case "一級護盾吸收量" : return context.getString(R.string.shield_level_1_dmg_absorption);
            case "二級護盾吸收量" : return context.getString(R.string.shield_level_2_dmg_absorption);
            case "三級護盾吸收量" : return context.getString(R.string.shield_level_3_dmg_absorption);
            case "一級護盾吸收量_BASE" : return context.getString(R.string.shield_level_1_dmg_absorption_base);
            case "二級護盾吸收量_BASE" : return context.getString(R.string.shield_level_2_dmg_absorption_base);
            case "三級護盾吸收量_BASE" : return context.getString(R.string.shield_level_3_dmg_absorption_base);
            case "熾焰箭傷害" : return context.getString(R.string.blazing_arrow_dmg);
            case "岩脊傷害" : return context.getString(R.string.stone_stele_dmg);
            case "共鳴傷害" : return context.getString(R.string.stone_resonance_dmg);
            case "護盾附加吸收量" : return context.getString(R.string.additional_shield_absorption);
            case "爆發傷害" : return context.getString(R.string.elemental_burst_dmg);
            case "生滅之花傷害(@1)" : return context.getString(R.string.fatal_blossom_dmg);
            case "箭雨單次傷害" : return context.getString(R.string.fiery_rain_dmg_per_wave);
            case "箭雨總傷害" : return context.getString(R.string.total_fiery_rain_dmg);
            case "攻擊力提高" : return context.getString(R.string.atk_bonus);
            case "閃雷傷害" : return context.getString(R.string.lightning_dmg);
            case "持續治療" : return context.getString(R.string.continuous_regeneration_per_sec);
            case "持續治療_BASE" : return context.getString(R.string.continuous_regeneration_per_sec_base);
            case "領域持續傷害" : return context.getString(R.string.continuous_field_dmg);
            case "光降之劍基礎傷害" : return context.getString(R.string.lightfall_sword_base_dmg);
            case "落雷傷害" : return context.getString(R.string.falling_thunder_dmg);
            case "冰凌傷害" : return context.getString(R.string.ice_shard_dmg);
            case "岩晶崩破傷害" : return context.getString(R.string.crystal_collapse_dmg);
            case "低血量時技能傷害" : return context.getString(R.string.low_hp_skill_dmg);
            case "技能治療量" : return context.getString(R.string.skill_hp_regeneration);
            case "低血量時技能治療量" : return context.getString(R.string.low_hp_skill_regeneration);
            case "出入領域傷害" : return context.getString(R.string.field_entering_exiting_dmg);
            case "領域發動治療量" : return context.getString(R.string.field_activation_healing);
            case "領域發動治療量_BASE" : return context.getString(R.string.field_activation_healing_base);
            case "附加元素傷害" : return context.getString(R.string.additional_elemental_dmg);
            case "切割傷害" : return context.getString(R.string.cutting_dmg);
            case "綻放傷害" : return context.getString(R.string.bloom_dmg);
            case "連斬傷害" : return context.getString(R.string.consecutive_slash_dmg);
            case "最後一擊傷害" : return context.getString(R.string.last_attack_dmg);
            case "轟轟火花傷害" : return context.getString(R.string.sparks_n_splash_dmg);
            case "天狗咒雷·金剛壞 傷害" : return context.getString(R.string.tengu_juurai_titanbreaker_dmg);
            case "天狗咒雷·雷礫 傷害" : return context.getString(R.string.tengu_juurai_stormcluster_dmg);
            case "放電傷害" : return context.getString(R.string.discharge_dmg);
            case "泡影破裂傷害" : return context.getString(R.string.illusory_bubble_explosion_dmg);
            case "夢想一刀基礎傷害" : return context.getString(R.string.musou_no_hitotachi_base_dmg);
            case "下墜期間傷害" : return context.getString(R.string.plunge_dmg);
            case "低空墜地衝擊傷害" : return context.getString(R.string.low_plunge_dmg);
            case "高空墜地衝擊傷害" : return context.getString(R.string.high_plunge_dmg);
            case "狼魂傷害" : return context.getString(R.string.soul_companion_dmg);
            case "普通攻擊速度提升" : return context.getString(R.string.normal_atk_spd_bonus);
            case "冰槍持續傷害" : return context.getString(R.string.ice_lance_dot);
            case "普通攻擊傷害提升" : return context.getString(R.string.normal_attack_dmg_bonus);
            case "重擊傷害提升" : return context.getString(R.string.charged_attack_dmg_bonus);
            case "化海月傷害提升" : return context.getString(R.string.bake_kurage_dmg_bonus);
            case "技能發動傷害" : return context.getString(R.string.skill_activation_dmg);
            case "技能發動治療量" : return context.getString(R.string.skill_activation_healing);
            case "不倒貉貉傷害" : return context.getString(R.string.muji_muji_daruma_dmg);
            case "不倒貉貉治療量" : return context.getString(R.string.muji_muji_daruma_healing);
            case "不倒貉貉治療量_BASE" : return context.getString(R.string.muji_muji_daruma_healing_base);
            case "技能傷害·近戰" : return context.getString(R.string.skill_dmg_melee);
            case "技能傷害·遠程" : return context.getString(R.string.skill_dmg_ranged);
            case "斷流·爆 傷害" : return context.getString(R.string.riptide_blast_dmg);
            case "熾火崩破傷害" : return context.getString(R.string.fiery_collapse_dmg);
            case "護盾吸收量" : return context.getString(R.string.shield_dmg_absorption);
            case "龍捲風傷害" : return context.getString(R.string.tornado_dmg);
            case "地震波單次傷害" : return context.getString(R.string.dmg_per_shockwave);
            case "一段揮舞傷害" : return context.getString(R.string.dmg_1_hit_swing);
            case "二段揮舞傷害" : return context.getString(R.string.dmg_2_hit_swing);
            case "三段揮舞傷害" : return context.getString(R.string.dmg_3_hit_swing);
            case "旋火輪傷害" : return context.getString(R.string.pyronado_dmg);
            case "箭雨傷害" : return context.getString(R.string.sword_rain_dmg);
            case "琉金火光爆炸傷害" : return context.getString(R.string.aurous_blaze_explosion_dmg);
            // add in 20220219
            case "殺生櫻傷害·壹階" : return context.getString(R.string.sesshou_sakura_dmg_level_1);
            case "殺生櫻傷害·貳階" : return context.getString(R.string.sesshou_sakura_dmg_level_2);
            case "殺生櫻傷害·參階" : return context.getString(R.string.sesshou_sakura_dmg_level_3);
            case "殺生櫻傷害·肆階" : return context.getString(R.string.sesshou_sakura_dmg_level_4);
            case "天狐霆雷傷害" : return context.getString(R.string.tenko_thunderbolt_dmg);

            default: return context.getString(R.string.unknown);
        }
    }


}
