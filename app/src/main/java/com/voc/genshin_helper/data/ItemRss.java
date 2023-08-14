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

import org.json.JSONException;
import org.json.JSONObject;

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
    public static String SERVER_DOWNLOAD_ROOT = "https://voc2048.com/genshin_spirit/";
    public static String SERVER_DATA_ROOT = "https://voc2048.com/genshin_spirit/online_rss/";
    //public static String SERVER_DOWNLOAD_ROOT = "https://github.com/Vocaloid2048/Genshin-Spirit/raw/release-master/rss/";
    public static String SERVER_REACT_ROOT = "https://voc2048.com/genshin_spirit/";

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
            0,0,0,0,0,
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
    public int getCharRare2048ByInt(int i){
        switch (i){
            case 0 : return R.drawable.item_paimon_char_bg_circ_0s;
            case 4 : return R.drawable.item_paimon_char_bg_circ_4s;
            case 5 : return R.drawable.item_paimon_char_bg_circ_5s;
            default : return R.drawable.item_paimon_char_bg_circ_0s;
        }
    }
    public int getTalentBg2048ByElement(String element){
        switch (element){
            case "Anemo" : return R.drawable.bg_colored_talent_anemo;
            case "Cryo" : return R.drawable.bg_colored_talent_cryo;
            case "Dendro" : return R.drawable.bg_colored_talent_dendro;
            case "Electro" : return R.drawable.bg_colored_talent_electro;
            case "Geo" : return R.drawable.bg_colored_talent_geo;
            case "Hydro" : return R.drawable.bg_colored_talent_hydro;
            case "Pyro" : return R.drawable.bg_colored_talent_pyro;
            default: return R.drawable.bg_colored_talent_pyro;
        }
    }
    public int getSOFBg2048ByElement(String element){
        switch (element){
            case "Anemo" : return R.drawable.bg_colored_sof_anemo;
            case "Cryo" : return R.drawable.bg_colored_sof_cryo;
            case "Dendro" : return R.drawable.bg_colored_sof_dendro;
            case "Electro" : return R.drawable.bg_colored_sof_electro;
            case "Geo" : return R.drawable.bg_colored_sof_geo;
            case "Hydro" : return R.drawable.bg_colored_sof_hydro;
            case "Pyro" : return R.drawable.bg_colored_sof_pyro;
            default: return R.drawable.bg_colored_sof_pyro;
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


    public int[] getEnemyByName(String str){
        switch (str){
            case "Pyro Slime" : return new int[]{R.string.pyro_slime,R.drawable.pyro_slime};
            case "Large Pyro Slime" : return new int[]{R.string.large_pyro_slime,R.drawable.large_pyro_slime};
            case "Electro Slime" : return new int[]{R.string.electro_slime,R.drawable.electro_slime};
            case "Large Electro Slime" : return new int[]{R.string.large_electro_slime,R.drawable.large_electro_slime};
            case "Mutant Electro Slime" : return new int[]{R.string.mutant_electro_slime,R.drawable.mutant_electro_slime};
            case "Cryo Slime" : return new int[]{R.string.cryo_slime,R.drawable.cryo_slime};
            case "Large Cryo Slime" : return new int[]{R.string.large_cryo_slime,R.drawable.large_cryo_slime};
            case "Hydro Slime" : return new int[]{R.string.hydro_slime,R.drawable.hydro_slime};
            case "Large Hydro Slime" : return new int[]{R.string.large_hydro_slime,R.drawable.large_hydro_slime};
            case "Anemo Slime" : return new int[]{R.string.anemo_slime,R.drawable.anemo_slime};
            case "Large Anemo Slime" : return new int[]{R.string.large_anemo_slime,R.drawable.large_anemo_slime};
            case "Geo Slime" : return new int[]{R.string.geo_slime,R.drawable.geo_slime};
            case "Large Geo Slime" : return new int[]{R.string.large_geo_slime,R.drawable.large_geo_slime};
            case "Dendro Slime" : return new int[]{R.string.dendro_slime,R.drawable.dendro_slime};
            case "Large Dendro Slime" : return new int[]{R.string.large_dendro_slime,R.drawable.large_dendro_slime};
            case "Eye of the Storm" : return new int[]{R.string.eye_of_the_storm,R.drawable.eye_of_the_storm};
            case "Pyro Hypostasis" : return new int[]{R.string.pyro_hypostasis,R.drawable.pyro_hypostasis};
            case "Electro Hypostasis" : return new int[]{R.string.electro_hypostasis,R.drawable.electro_hypostasis};
            case "Cryo Hypostasis" : return new int[]{R.string.cryo_hypostasis,R.drawable.cryo_hypostasis};
            case "Hydro Hypostasis" : return new int[]{R.string.hydro_hypostasis,R.drawable.hydro_hypostasis};
            case "Anemo Hypostasis" : return new int[]{R.string.anemo_hypostasis,R.drawable.anemo_hypostasis};
            case "Geo Hypostasis" : return new int[]{R.string.geo_hypostasis,R.drawable.geo_hypostasis};
            case "Oceanid" : return new int[]{R.string.oceanid,R.drawable.oceanid};
            case "Hydro Specter" : return new int[]{R.string.hydro_specter,R.drawable.hydro_specter};
            case "Geo Specter" : return new int[]{R.string.geo_specter,R.drawable.geo_specter};
            case "Anemo Specter" : return new int[]{R.string.anemo_specter,R.drawable.anemo_specter};
            case "Cryo Specter" : return new int[]{R.string.cryo_specter,R.drawable.cryo_specter};
            case "Electro Specter" : return new int[]{R.string.electro_specter,R.drawable.electro_specter};
            case "Pyro Specter" : return new int[]{R.string.pyro_specter,R.drawable.pyro_specter};
            case "Thunder Manifestation" : return new int[]{R.string.thunder_manifestation,R.drawable.thunder_manifestation};
            case "Hilichurl" : return new int[]{R.string.hilichurl,R.drawable.hilichurl};
            case "Hilichurl Fighter" : return new int[]{R.string.hilichurl_fighter,R.drawable.hilichurl_fighter};
            case "Wooden Shield Hilichurl Guard" : return new int[]{R.string.wooden_shield_hilichurl_guard,R.drawable.wooden_shield_hilichurl_guard};
            case "Hilichurl Shooter" : return new int[]{R.string.hilichurl_shooter,R.drawable.hilichurl_shooter};
            case "Pyro Hilichurl Shooter" : return new int[]{R.string.pyro_hilichurl_shooter,R.drawable.pyro_hilichurl_shooter};
            case "Hilichurl Grenadier" : return new int[]{R.string.hilichurl_grenadier,R.drawable.hilichurl_grenadier};
            case "Hilichurl Berserker" : return new int[]{R.string.hilichurl_berserker,R.drawable.hilichurl_berserker};
            case "Cryo Hilichurl Shooter" : return new int[]{R.string.cryo_hilichurl_shooter,R.drawable.cryo_hilichurl_shooter};
            case "Electro Hilichurl Shooter" : return new int[]{R.string.electro_hilichurl_shooter,R.drawable.electro_hilichurl_shooter};
            case "Rock Shield Hilichurl Guard" : return new int[]{R.string.rock_shield_hilichurl_guard,R.drawable.rock_shield_hilichurl_guard};
            case "Cryo Hilichurl Grenadier" : return new int[]{R.string.cryo_hilichurl_grenadier,R.drawable.cryo_hilichurl_grenadier};
            case "Ice Shield Hilichurl Guard" : return new int[]{R.string.ice_shield_hilichurl_guard,R.drawable.ice_shield_hilichurl_guard};
            case "Unusual Hilichurl" : return new int[]{R.string.unusual_hilichurl,R.drawable.unusual_hilichurl};
            case "Electro Hilichurl Grenadier" : return new int[]{R.string.electro_hilichurl_grenadier,R.drawable.electro_hilichurl_grenadier};
            case "Wooden Shieldwall Mitachurl" : return new int[]{R.string.wooden_shieldwall_mitachurl,R.drawable.wooden_shieldwall_mitachurl};
            case "Blazing Axe Mitachurl" : return new int[]{R.string.blazing_axe_mitachurl,R.drawable.blazing_axe_mitachurl};
            case "Rock Shieldwall Mitachurl" : return new int[]{R.string.rock_shieldwall_mitachurl,R.drawable.rock_shieldwall_mitachurl};
            case "Frostarm Lawachurl" : return new int[]{R.string.frostarm_lawachurl,R.drawable.frostarm_lawachurl};
            case "Stonehide Lawachurl" : return new int[]{R.string.stonehide_lawachurl,R.drawable.stonehide_lawachurl};
            case "Thunderhelm Lawachurl" : return new int[]{R.string.thunderhelm_lawachurl,R.drawable.thunderhelm_lawachurl};
            case "Ice Shieldwall Mitachurl" : return new int[]{R.string.ice_shieldwall_mitachurl,R.drawable.ice_shieldwall_mitachurl};
            case "Crackling Axe Mitachurl" : return new int[]{R.string.crackling_axe_mitachurl,R.drawable.crackling_axe_mitachurl};
            case "Hydro Samachurl" : return new int[]{R.string.hydro_samachurl,R.drawable.hydro_samachurl};
            case "Dendro Samachurl" : return new int[]{R.string.dendro_samachurl,R.drawable.dendro_samachurl};
            case "Anemo Samachurl" : return new int[]{R.string.anemo_samachurl,R.drawable.anemo_samachurl};
            case "Geo Samachurl" : return new int[]{R.string.geo_samachurl,R.drawable.geo_samachurl};
            case "Cryo Samachurl" : return new int[]{R.string.cryo_samachurl,R.drawable.cryo_samachurl};
            case "Electro Samachurl" : return new int[]{R.string.electro_samachurl,R.drawable.electro_samachurl};
            case "Pyro Abyss Mage" : return new int[]{R.string.pyro_abyss_mage,R.drawable.pyro_abyss_mage};
            case "Cryo Abyss Mage" : return new int[]{R.string.cryo_abyss_mage,R.drawable.cryo_abyss_mage};
            case "Hydro Abyss Mage" : return new int[]{R.string.hydro_abyss_mage,R.drawable.hydro_abyss_mage};
            case "Electro Abyss Mage" : return new int[]{R.string.electro_abyss_mage,R.drawable.electro_abyss_mage};
            case "Abyss Herald: Wicked Torrents" : return new int[]{R.string.abyss_herald_wicked_torrents,R.drawable.abyss_herald_wicked_torrents};
            case "Abyss Lector: Violet Lightning" : return new int[]{R.string.abyss_lector_violet_lightning,R.drawable.abyss_lector_violet_lightning};
            case "Abyss Lector: Fathomless Flames" : return new int[]{R.string.abyss_lector_fathomless_flames,R.drawable.abyss_lector_fathomless_flames};
            case "Rockfond Rifthound Whelp" : return new int[]{R.string.rockfond_rifthound_whelp,R.drawable.rockfond_rifthound_whelp};
            case "Thundercraven Rifthound Whelp" : return new int[]{R.string.thundercraven_rifthound_whelp,R.drawable.thundercraven_rifthound_whelp};
            case "Rockfond Rifthound" : return new int[]{R.string.rockfond_rifthound,R.drawable.rockfond_rifthound};
            case "Thundercraven Rifthound" : return new int[]{R.string.thundercraven_rifthound,R.drawable.thundercraven_rifthound};
            case "Beginning : Golden Wolflord" : return new int[]{R.string.beginning_golden_wolflord,R.drawable.golden_wolflord};
            case "Golden Wolflord" : return new int[]{R.string.golden_wolflord,R.drawable.golden_wolflord};
            case "Head-Broken : Golden Wolflord" : return new int[]{R.string.headbroken_golden_wolflord,R.drawable.golden_wolflord};
            case "Shadowy Husk: Standard Bearer" : return new int[]{R.string.shadowy_husk_standard_bearer,R.drawable.shadowy_husk_standard_bearer};
            case "Shadowy Husk: Line Breaker" : return new int[]{R.string.shadowy_husk_line_breaker,R.drawable.shadowy_husk_line_breaker};
            case "Shadowy Husk: Defender" : return new int[]{R.string.shadowy_husk_defender,R.drawable.shadowy_husk_defender};
            case "Fatui Skirmisher - Cryogunner Legionnaire" : return new int[]{R.string.fatui_skirmisher_cryogunner_legionnaire,R.drawable.fatui_skirmisher_cryogunner_legionnaire};
            case "Fatui Skirmisher - Hydrogunner Legionnaire" : return new int[]{R.string.fatui_skirmisher_hydrogunner_legionnaire,R.drawable.fatui_skirmisher_hydrogunner_legionnaire};
            case "Fatui Skirmisher - Electrohammer Vanguard" : return new int[]{R.string.fatui_skirmisher_electrohammer_vanguard,R.drawable.fatui_skirmisher_electrohammer_vanguard};
            case "Fatui Skirmisher - Geochanter Bracer" : return new int[]{R.string.fatui_skirmisher_geochanter_bracer,R.drawable.fatui_skirmisher_geochanter_bracer};
            case "Fatui Skirmisher - Anemoboxer Vanguard" : return new int[]{R.string.fatui_skirmisher_anemoboxer_vanguard,R.drawable.fatui_skirmisher_anemoboxer_vanguard};
            case "Fatui Skirmisher - Pyroslinger Bracer" : return new int[]{R.string.fatui_skirmisher_pyroslinger_bracer,R.drawable.fatui_skirmisher_pyroslinger_bracer};
            case "Fatui Pyro Agent" : return new int[]{R.string.fatui_pyro_agent,R.drawable.fatui_pyro_agent};
            case "Fatui Electro Cicin Mage" : return new int[]{R.string.fatui_electro_cicin_mage,R.drawable.fatui_electro_cicin_mage};
            case "Fatui Cryo Cicin Mage" : return new int[]{R.string.fatui_cryo_cicin_mage,R.drawable.fatui_cryo_cicin_mage};
            case "Mirror Maiden" : return new int[]{R.string.mirror_maiden,R.drawable.mirror_maiden};
            case "Ruin Guard" : return new int[]{R.string.ruin_guard,R.drawable.ruin_guard};
            case "Ruin Hunter" : return new int[]{R.string.ruin_hunter,R.drawable.ruin_hunter};
            case "Ruin Grader" : return new int[]{R.string.ruin_grader,R.drawable.ruin_grader};
            case "Ruin Cruiser" : return new int[]{R.string.ruin_cruiser,R.drawable.ruin_cruiser};
            case "Ruin Destroyer" : return new int[]{R.string.ruin_destroyer,R.drawable.ruin_destroyer};
            case "Ruin Defender" : return new int[]{R.string.ruin_defender,R.drawable.ruin_defender};
            case "Ruin Scout" : return new int[]{R.string.ruin_scout,R.drawable.ruin_scout};
            case "Perpetual Mechanical Array" : return new int[]{R.string.perpetual_mechanical_array,R.drawable.perpetual_mechanical_array};
            case "Stunned Perpetual Mechanical Array" : return new int[]{R.string.stunned_perpetual_mechanical_array,R.drawable.perpetual_mechanical_array};
            case "Treasure Hoarders - Liuliu" : return new int[]{R.string.treasure_hoarders_liuliu,R.drawable.treasure_hoarders_liuliu};
            case "Treasure Hoarders - Scout" : return new int[]{R.string.treasure_hoarders_scout,R.drawable.treasure_hoarders_scout};
            case "Treasure Hoarders: Pyro Potioneer" : return new int[]{R.string.treasure_hoarders_pyro_potioneer,R.drawable.treasure_hoarders_pyro_potioneer};
            case "Treasure Hoarders: Hydro Potioneer" : return new int[]{R.string.treasure_hoarders_hydro_potioneer,R.drawable.treasure_hoarders_hydro_potioneer};
            case "Treasure Hoarders: Electro Potioneer" : return new int[]{R.string.treasure_hoarders_electro_potioneer,R.drawable.treasure_hoarders_electro_potioneer};
            case "Treasure Hoarders: Cryo Potioneer" : return new int[]{R.string.treasure_hoarders_cryo_potioneer,R.drawable.treasure_hoarders_cryo_potioneer};
            case "Treasure Hoarders: Handyman" : return new int[]{R.string.treasure_hoarders_handyman,R.drawable.treasure_hoarders_handyman};
            case "Treasure Hoarders - Raptor" : return new int[]{R.string.treasure_hoarders_raptor,R.drawable.treasure_hoarders_raptor};
            case "Treasure Hoarders - Marksman" : return new int[]{R.string.treasure_hoarders_marksman,R.drawable.treasure_hoarders_marksman};
            case "Treasure Hoarders - Carmen" : return new int[]{R.string.treasure_hoarders_carmen,R.drawable.treasure_hoarders_carmen};
            case "Treasure Hoarders: Gravedigger" : return new int[]{R.string.treasure_hoarders_gravedigger,R.drawable.treasure_hoarders_gravedigger};
            case "Treasure Hoarders - Seaman" : return new int[]{R.string.treasure_hoarders_seaman,R.drawable.treasure_hoarders_seaman};
            case "Treasure Hoarders - Boss" : return new int[]{R.string.treasure_hoarders_boss,R.drawable.treasure_hoarders_boss};
            case "Millelith Soldier" : return new int[]{R.string.millelith_soldier,R.drawable.millelith_soldier};
            case "Millelith Sergeant" : return new int[]{R.string.millelith_sergeant,R.drawable.millelith_sergeant};
            case "Treasure Hoarders - Pugilist" : return new int[]{R.string.treasure_hoarders_pugilist,R.drawable.treasure_hoarders_pugilist};
            case "Treasure Hoarders - Crusher" : return new int[]{R.string.treasure_hoarders_crusher,R.drawable.treasure_hoarders_crusher};
            case "Nobushi: Jintouban" : return new int[]{R.string.nobushi_jintouban,R.drawable.nobushi_jintouban};
            case "Nobushi: Hitsukeban" : return new int[]{R.string.nobushi_hitsukeban,R.drawable.nobushi_hitsukeban};
            case "Nobushi: Kikouban" : return new int[]{R.string.nobushi_kikouban,R.drawable.nobushi_kikouban};
            case "Maguu Kenki" : return new int[]{R.string.maguu_kenki,R.drawable.maguu_kenki};
            case "Kairagi: Dancing Thunder" : return new int[]{R.string.kairagi_dancing_thunder,R.drawable.kairagi_dancing_thunder};
            case "Kairagi: Fiery Might" : return new int[]{R.string.kairagi_fiery_might,R.drawable.kairagi_fiery_might};
            case "Cryo Whopperflower" : return new int[]{R.string.cryo_whopperflower,R.drawable.cryo_whopperflower};
            case "Pyro Whopperflower" : return new int[]{R.string.pyro_whopperflower,R.drawable.pyro_whopperflower};
            case "Electro Whopperflower" : return new int[]{R.string.electro_whopperflower,R.drawable.electro_whopperflower};
            case "Stunned Pyro Whopperflower" : return new int[]{R.string.stunned_pyro_whopperflower,R.drawable.cryo_whopperflower};
            case "Stunned Electro Whopperflower" : return new int[]{R.string.stunned_electro_whopperflower,R.drawable.pyro_whopperflower};
            case "Stunned Cryo Whopperflower" : return new int[]{R.string.stunned_cryo_whopperflower,R.drawable.electro_whopperflower};
            case "Cryo Regisvine" : return new int[]{R.string.cryo_regisvine,R.drawable.cryo_regisvine};
            case "Pyro Regisvine" : return new int[]{R.string.pyro_regisvine,R.drawable.pyro_regisvine};
            case "Stunned Cryo Regisvine" : return new int[]{R.string.stunned_cryo_regisvine,R.drawable.cryo_regisvine};
            case "Stunned Pyro Regisvine" : return new int[]{R.string.stunned_pyro_regisvine,R.drawable.pyro_regisvine};
            case "Geovishap Hatchling" : return new int[]{R.string.geovishap_hatchling,R.drawable.geovishap_hatchling};
            case "Geovishap" : return new int[]{R.string.geovishap,R.drawable.geovishap};
            case "Pyro-infused Geovishap" : return new int[]{R.string.pyroinfused_geovishap,R.drawable.geovishap};
            case "Electro-infused Geovishap" : return new int[]{R.string.electroinfused_geovishap,R.drawable.geovishap};
            case "Cryo-infused Geovishap" : return new int[]{R.string.cryoinfused_geovishap,R.drawable.geovishap};
            case "Hydro-infused Geovishap" : return new int[]{R.string.hydroinfused_geovishap,R.drawable.geovishap};
            case "Primo Geovishap" : return new int[]{R.string.primo_geovishap,R.drawable.primo_geovishap};
            case "Primordial Bathysmal Vishap" : return new int[]{R.string.primordial_bathysmal_vishap,R.drawable.primordial_bathysmal_vishap};
            case "Rimebiter Bathysmal Vishap" : return new int[]{R.string.rimebiter_bathysmal_vishap,R.drawable.rimebiter_bathysmal_vishap};
            case "Bolteater Bathysmal Vishap" : return new int[]{R.string.bolteater_bathysmal_vishap,R.drawable.bolteater_bathysmal_vishap};
            case "Electro Bathysmal Vishap" : return new int[]{R.string.electro_bathysmal_vishap,R.drawable.electro_bathysmal_vishap};
            case "Cyro Bathysmal Vishap" : return new int[]{R.string.cyro_bathysmal_vishap,R.drawable.cyro_bathysmal_vishap};
            case "Electro Cicin" : return new int[]{R.string.electro_cicin,R.drawable.electro_cicin};
            case "Hydro Cicin" : return new int[]{R.string.hydro_cicin,R.drawable.hydro_cicin};
            case "Cryo Cicin" : return new int[]{R.string.cryo_cicin,R.drawable.cryo_cicin};
            case "Stormterror" : return new int[]{R.string.stormterror,R.drawable.stormterror};
            case "Lupus Boreas" : return new int[]{R.string.lupus_boreas,R.drawable.lupus_boreas};
            case "Phase 1 Childe" : return new int[]{R.string.phase_1_childe,R.drawable.childe};
            case "Stunned Phase 1 Childe " : return new int[]{R.string.stunned_phase_1_childe_,R.drawable.childe};
            case "Phase 2 Childe" : return new int[]{R.string.phase_2_childe,R.drawable.childe};
            case "Stunned Phase 2 Childe" : return new int[]{R.string.stunned_phase_2_childe,R.drawable.childe};
            case "Phase 3 Childe" : return new int[]{R.string.phase_3_childe,R.drawable.childe};
            case "Azhdaha" : return new int[]{R.string.azhdaha,R.drawable.azhdaha};
            case "Pyro Azhdaha" : return new int[]{R.string.pyro_azhdaha,R.drawable.azhdaha};
            case "Hydro Azhdaha" : return new int[]{R.string.hydro_azhdaha,R.drawable.azhdaha};
            case "Fire-Cryo Azhdaha" : return new int[]{R.string.firecryo_azhdaha,R.drawable.azhdaha};
            case "Fire-Electro Azhdaha" : return new int[]{R.string.fireelectro_azhdaha,R.drawable.azhdaha};
            case "Hydro-Cryo Azhdaha" : return new int[]{R.string.hydrocryo_azhdaha,R.drawable.azhdaha};
            case "Hydro-Electro Azhdaha" : return new int[]{R.string.hydroelectro_azhdaha,R.drawable.azhdaha};
            case "Phase 1 La Signora" : return new int[]{R.string.phase_1_la_signora,R.drawable.la_signora};
            case "Phase 2 La Signora" : return new int[]{R.string.phase_2_la_signora,R.drawable.la_signora};
            case "Magatsu Mitake Narukami no Mikoto" : return new int[]{R.string.magatsu_mitake_narukami_no_mikoto,R.drawable.magatsu_mitake_narukami_no_mikoto};
            case "Hydro Mimic Boar" : return new int[]{R.string.hydro_mimic_boar,R.drawable.hydro_mimic_boar};
            case "Hydro Mimic Crane" : return new int[]{R.string.hydro_mimic_crane,R.drawable.hydro_mimic_crane};
            case "Hydro Mimic Crab" : return new int[]{R.string.hydro_mimic_crab,R.drawable.hydro_mimic_crab};
            case "Hydro Mimic Finch" : return new int[]{R.string.hydro_mimic_finch,R.drawable.hydro_mimic_finch};
            case "Hydro Mimic Mallard" : return new int[]{R.string.hydro_mimic_mallard,R.drawable.hydro_mimic_mallard};
            case "Hydro Mimic Ferret" : return new int[]{R.string.hydro_mimic_ferret,R.drawable.hydro_mimic_ferret};
            case "Hydro Mimic Frog" : return new int[]{R.string.hydro_mimic_frog,R.drawable.hydro_mimic_frog};
            case "Hydro Mimic Raptor" : return new int[]{R.string.hydro_mimic_raptor,R.drawable.hydro_mimic_raptor};
            case "Shogunate Infantry" : return new int[]{R.string.shogunate_infantry,R.drawable.shogunate_infantry};
            case "Shogunate Infantry Captain" : return new int[]{R.string.shogunate_infantry_captain,R.drawable.shogunate_infantry_captain};
            case "Sangonomiya Cohort" : return new int[]{R.string.sangonomiya_cohort,R.drawable.sangonomiya_cohort};
            case "Yoriki Samurai" : return new int[]{R.string.yoriki_samurai,R.drawable.yoriki_samurai};
            case "Ochimusha: Ensorcelled Thunder" : return new int[]{R.string.ochimusha_ensorcelled_thunder,R.drawable.ochimusha_ensorcelled_thunder};
            case "Ochimusha: Cankered Flame" : return new int[]{R.string.ochimusha_cankered_flame,R.drawable.ochimusha_cankered_flame};
            case "Maguu Kenki: Lone Gale" : return new int[]{R.string.maguu_kenki_lone_gale,R.drawable.maguu_kenki_lone_gale};
            case "Maguu Kenki: Galloping Frost" : return new int[]{R.string.maguu_kenki_galloping_frost,R.drawable.maguu_kenki_galloping_frost};
            case "Maguu Kenki: Mask of Terror" : return new int[]{R.string.maguu_kenki_mask_of_terror,R.drawable.maguu_kenki_mask_of_terror};
            case "The Great Snowboar King" : return new int[]{R.string.the_great_snowboar_king,R.drawable.the_great_snowboar_king};

            default : return new int[] {R.string.unknown,R.drawable.hu_tao_unknown};
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

    public int[] getWeaponByName(String str) {
        switch (str){
            //Add in 4.0.0
            case "Wolf-Fang" : return new int[] {R.string.wolf_fang,R.drawable.wolf_fang};
            case "Finale of the Deep" : return new int[] {R.string.finale_of_the_deep,R.drawable.finale_of_the_deep};
            case "Crossing of Fleuve Cendre" : return new int[] {R.string.crossing_of_fleuve_cendre,R.drawable.crossing_of_fleuve_cendre};
            case "Talking Stick" : return new int[] {R.string.talking_stick,R.drawable.talking_stick};
            case "Tidal Shadow" : return new int[] {R.string.tidal_shadow,R.drawable.tidal_shadow};
            case "Ballad of the Fjords" : return new int[] {R.string.ballad_of_the_fjords,R.drawable.ballad_of_the_fjords};
            case "Rightful Reward" : return new int[] {R.string.rightful_reward,R.drawable.rightful_reward};
            case "Sacrificial Jade" : return new int[] {R.string.sacrificial_jade,R.drawable.sacrificial_jade};
            case "Flowing Purity" : return new int[] {R.string.flowing_purity,R.drawable.flowing_purity};
            case "Scion of the Blazing Sun" : return new int[] {R.string.scion_of_the_blazing_sun,R.drawable.scion_of_the_blazing_sun};
            case "Song of Stillness" : return new int[] {R.string.song_of_stillness,R.drawable.song_of_stillness};
            case "The First Great Magic" : return new int[] {R.string.the_first_great_magic,R.drawable.the_first_great_magic};


            case "Amenoma Kageuchi" : return new int[] {R.string.amenoma_kageuchi,R.drawable.amenoma_kageuchi};
            case "Aquila Favonia" : return new int[] {R.string.aquila_favonia,R.drawable.aquila_favonia};
            case "Blackcliff Longsword" : return new int[] {R.string.blackcliff_longsword,R.drawable.blackcliff_longsword};
            case "Cool Steel" : return new int[] {R.string.cool_steel,R.drawable.cool_steel};
            case "Dark Iron Sword" : return new int[] {R.string.dark_iron_sword,R.drawable.dark_iron_sword};
            case "Dull Blade" : return new int[] {R.string.dull_blade,R.drawable.dull_blade};
            case "Favonius Sword" : return new int[] {R.string.favonius_sword,R.drawable.favonius_sword};
            case "Festering Desire" : return new int[] {R.string.festering_desire,R.drawable.festering_desire};
            case "Fillet Blade" : return new int[] {R.string.fillet_blade,R.drawable.fillet_blade};
            case "Freedom-Sworn" : return new int[] {R.string.freedom_sworn,R.drawable.freedom_sworn};
            case "Freedom Sworn" : return new int[] {R.string.freedom_sworn,R.drawable.freedom_sworn};
            case "Harbinger of Dawn" : return new int[] {R.string.harbinger_of_dawn,R.drawable.harbinger_of_dawn};
            case "Iron Sting" : return new int[] {R.string.iron_sting,R.drawable.iron_sting};
            case "Lion's Roar" : return new int[] {R.string.lions_roar,R.drawable.lions_roar};
            case "Mistsplitter Reforged" : return new int[] {R.string.mistsplitter_reforged,R.drawable.mistsplitter_reforged};
            case "Primordial Jade Cutter" : return new int[] {R.string.primordial_jade_cutter,R.drawable.primordial_jade_cutter};
            case "Prototype Rancour" : return new int[] {R.string.prototype_rancour,R.drawable.prototype_rancour};
            case "Royal Longsword" : return new int[] {R.string.royal_longsword,R.drawable.royal_longsword};
            case "Sacrificial Sword" : return new int[] {R.string.sacrificial_sword,R.drawable.sacrificial_sword};
            case "Silver Sword" : return new int[] {R.string.silver_sword,R.drawable.silver_sword};
            case "Skyrider Sword" : return new int[] {R.string.skyrider_sword,R.drawable.skyrider_sword};
            case "Skyward Blade" : return new int[] {R.string.skyward_blade,R.drawable.skyward_blade};
            case "Summit Shaper" : return new int[] {R.string.summit_shaper,R.drawable.summit_shaper};
            case "Sword of Descension" : return new int[] {R.string.sword_of_descension,R.drawable.sword_of_descension};
            case "The Alley Flash" : return new int[] {R.string.the_alley_flash,R.drawable.the_alley_flash};
            case "The Black Sword" : return new int[] {R.string.the_black_sword,R.drawable.the_black_sword};
            case "The Flute" : return new int[] {R.string.the_flute,R.drawable.the_flute};
            case "Traveler's Handy Sword" : return new int[] {R.string.travelers_handy_sword,R.drawable.travelers_handy_sword};
            case "Cinnabar Spindle" : return new int[] {R.string.cinnabar_spindle,R.drawable.cinnabar_spindle};

            case "Waster Greatsword" : return new int[] {R.string.waster_greatsword,R.drawable.waster_greatsword};
            case "Old Merc's Pal" : return new int[] {R.string.old_mercs_pal,R.drawable.old_mercs_pal};
            case "Bloodtainted Greatsword" : return new int[] {R.string.bloodtainted_greatsword,R.drawable.bloodtainted_greatsword};
            case "Debate Club" : return new int[] {R.string.debate_club,R.drawable.debate_club};
            case "Quartz" : return new int[] {R.string.quartz,R.drawable.quartz};
            case "Ferrous Shadow" : return new int[] {R.string.ferrous_shadow,R.drawable.ferrous_shadow};
            case "Skyrider Greatsword" : return new int[] {R.string.skyrider_greatsword,R.drawable.skyrider_greatsword};
            case "White Iron Greatsword" : return new int[] {R.string.white_iron_greatsword,R.drawable.white_iron_greatsword};
            case "Blackcliff Slasher" : return new int[] {R.string.blackcliff_slasher,R.drawable.blackcliff_slasher};
            case "Favonius Greatsword" : return new int[] {R.string.favonius_greatsword,R.drawable.favonius_greatsword};
            case "Katsuragikiri Nagamasa" : return new int[] {R.string.katsuragikiri_nagamasa,R.drawable.katsuragikiri_nagamasa};
            case "Lithic Blade" : return new int[] {R.string.lithic_blade,R.drawable.lithic_blade};
            case "Luxurious Sea-Lord" : return new int[] {R.string.luxurious_sea_lord,R.drawable.luxurious_sea_lord};
            case "Prototype Archaic" : return new int[] {R.string.prototype_archaic,R.drawable.prototype_archaic};
            case "Rainslasher" : return new int[] {R.string.rainslasher,R.drawable.rainslasher};
            case "Royal Greatsword" : return new int[] {R.string.royal_greatsword,R.drawable.royal_greatsword};
            case "Sacrificial Greatsword" : return new int[] {R.string.sacrificial_greatsword,R.drawable.sacrificial_greatsword};
            case "Serpent Spine" : return new int[] {R.string.serpent_spine,R.drawable.serpent_spine};
            case "Snow-Tombed Starsilver" : return new int[] {R.string.snow_tombed_starsilver,R.drawable.snow_tombed_starsilver};
            case "The Bell" : return new int[] {R.string.the_bell,R.drawable.the_bell};
            case "Whiteblind" : return new int[] {R.string.whiteblind,R.drawable.whiteblind};
            case "Skyward Pride" : return new int[] {R.string.skyward_pride,R.drawable.skyward_pride};
            case "Song of Broken Pines" : return new int[] {R.string.song_of_broken_pines,R.drawable.song_of_broken_pines};
            case "The Unforged" : return new int[] {R.string.the_unforged,R.drawable.the_unforged};
            case "Wolf's Gravestone" : return new int[] {R.string.wolfs_gravestone,R.drawable.wolfs_gravestone};
            case "Akuoumaru" : return new int[] {R.string.akuoumaru,R.drawable.akuoumaru};
            case "Redhorn Stonethresher" : return new int[] {R.string.redhorn_stonethresher,R.drawable.redhorn_stonethresher};

            case "Beginner's Protector" : return new int[] {R.string.beginners_protector,R.drawable.beginners_protector};
            case "Iron Point" : return new int[] {R.string.iron_point,R.drawable.iron_point};
            case "Black Tassel" : return new int[] {R.string.black_tassel,R.drawable.black_tassel};
            case "Halberd" : return new int[] {R.string.halberd,R.drawable.halberd};
            case "White Tassel" : return new int[] {R.string.white_tassel,R.drawable.white_tassel};
            case "Blackcliff Pole" : return new int[] {R.string.blackcliff_pole,R.drawable.blackcliff_pole};
            case "Crescent Pike" : return new int[] {R.string.crescent_pike,R.drawable.crescent_pike};
            case "Deathmatch" : return new int[] {R.string.deathmatch,R.drawable.deathmatch};
            case "Dragon's Bane" : return new int[] {R.string.dragons_bane,R.drawable.dragons_bane};
            case "Dragonspine Spear" : return new int[] {R.string.dragonspine_spear,R.drawable.dragonspine_spear};
            case "Favonius Lance" : return new int[] {R.string.favonius_lance,R.drawable.favonius_lance};
            case "Kitain Cross Spear" : return new int[] {R.string.kitain_cross_spear,R.drawable.kitain_cross_spear};
            case "Lithic Spear" : return new int[] {R.string.lithic_spear,R.drawable.lithic_spear};
            case "Prototype Starglitter" : return new int[] {R.string.prototype_starglitter,R.drawable.prototype_starglitter};
            case "Royal Spear" : return new int[] {R.string.royal_spear,R.drawable.royal_spear};
            case "The Catch" : return new int[] {R.string.the_catch,R.drawable.the_catch};
            case "Engulfing Lightning" : return new int[] {R.string.engulfing_lightning,R.drawable.engulfing_lightning};
            case "Primordial Jade Winged-Spear" : return new int[] {R.string.primordial_jade_winged_spear,R.drawable.primordial_jade_winged_spear};
            case "Skyward Spine" : return new int[] {R.string.skyward_spine,R.drawable.skyward_spine};
            case "Staff of Homa" : return new int[] {R.string.staff_of_homa,R.drawable.staff_of_homa};
            case "Vortex Vanquisher" : return new int[] {R.string.vortex_vanquisher,R.drawable.vortex_vanquisher};
            case "Wavebreaker's Fin" : return new int[] {R.string.wavebreakers_fin,R.drawable.wavebreakers_fin};
            case "Calamity Queller" : return new int[] {R.string.calamity_queller,R.drawable.calamity_queller};

            case "Apprentice's Notes" : return new int[] {R.string.apprentices_notes,R.drawable.apprentices_notes};
            case "Pocket Grimoire" : return new int[] {R.string.pocket_grimoire,R.drawable.pocket_grimoire};
            case "Amber Catalyst" : return new int[] {R.string.amber_catalyst,R.drawable.amber_catalyst};
            case "Emerald Orb" : return new int[] {R.string.emerald_orb,R.drawable.emerald_orb};
            case "Magic Guide" : return new int[] {R.string.magic_guide,R.drawable.magic_guide};
            case "Otherworldly Story" : return new int[] {R.string.otherworldly_story,R.drawable.otherworldly_story};
            case "Thrilling Tales of Dragon Slayers" : return new int[] {R.string.thrilling_tales_of_dragon_slayers,R.drawable.thrilling_tales_of_dragon_slayers};
            case "Twin Nephrite" : return new int[] {R.string.twin_nephrite,R.drawable.twin_nephrite};
            case "Blackcliff Agate" : return new int[] {R.string.blackcliff_agate,R.drawable.blackcliff_agate};
            case "Dodoco Tales" : return new int[] {R.string.dodoco_tales,R.drawable.dodoco_tales};
            case "Eye of Perception" : return new int[] {R.string.eye_of_perception,R.drawable.eye_of_perception};
            case "Favonius Codex" : return new int[] {R.string.favonius_codex,R.drawable.favonius_codex};
            case "Frostbearer" : return new int[] {R.string.frostbearer,R.drawable.frostbearer};
            case "Hakushin Ring" : return new int[] {R.string.hakushin_ring,R.drawable.hakushin_ring};
            case "Mappa Mare" : return new int[] {R.string.mappa_mare,R.drawable.mappa_mare};
            case "Prototype Amber" : return new int[] {R.string.prototype_amber,R.drawable.prototype_amber};
            case "Royal Grimoire" : return new int[] {R.string.royal_grimoire,R.drawable.royal_grimoire};
            case "Sacrificial Fragments" : return new int[] {R.string.sacrificial_fragments,R.drawable.sacrificial_fragments};
            case "Solar Pearl" : return new int[] {R.string.solar_pearl,R.drawable.solar_pearl};
            case "The Widsith" : return new int[] {R.string.the_widsith,R.drawable.the_widsith};
            case "Wine and Song" : return new int[] {R.string.wine_and_song,R.drawable.wine_and_song};
            case "Everlasting Moonglow" : return new int[] {R.string.everlasting_moonglow,R.drawable.everlasting_moonglow};
            case "Lost Prayer to the Sacred Winds" : return new int[] {R.string.lost_prayer_to_the_sacred_winds,R.drawable.lost_prayer_to_the_sacred_winds};
            case "Memory of Dust" : return new int[] {R.string.memory_of_dust,R.drawable.memory_of_dust};
            case "Skyward Atlas" : return new int[] {R.string.skyward_atlas,R.drawable.skyward_atlas};

            case "Hunter's Bow" : return new int[] {R.string.hunters_bow,R.drawable.hunters_bow};
            case "Seasoned Hunter's Bow" : return new int[] {R.string.seasoned_hunters_bow,R.drawable.seasoned_hunters_bow};
            case "Ebony Bow" : return new int[] {R.string.ebony_bow,R.drawable.ebony_bow};
            case "Messenger" : return new int[] {R.string.messenger,R.drawable.messenger};
            case "Raven Bow" : return new int[] {R.string.raven_bow,R.drawable.raven_bow};
            case "Recurve Bow" : return new int[] {R.string.recurve_bow,R.drawable.recurve_bow};
            case "Sharpshooter's Oath" : return new int[] {R.string.sharpshooters_oath,R.drawable.sharpshooters_oath};
            case "Slingshot" : return new int[] {R.string.slingshot,R.drawable.slingshot};
            case "Alley Hunter" : return new int[] {R.string.alley_hunter,R.drawable.alley_hunter};
            case "Blackcliff Warbow" : return new int[] {R.string.blackcliff_warbow,R.drawable.blackcliff_warbow};
            case "Compound Bow" : return new int[] {R.string.compound_bow,R.drawable.compound_bow};
            case "Favonius Warbow" : return new int[] {R.string.favonius_warbow,R.drawable.favonius_warbow};
            case "Hamayumi" : return new int[] {R.string.hamayumi,R.drawable.hamayumi};
            case "Mitternachts Waltz" : return new int[] {R.string.mitternachts_waltz,R.drawable.mitternachts_waltz};
            case "Predator" : return new int[] {R.string.predator,R.drawable.predator};
            case "Prototype Crescent" : return new int[] {R.string.prototype_crescent,R.drawable.prototype_crescent};
            case "Royal Bow" : return new int[] {R.string.royal_bow,R.drawable.royal_bow};
            case "Rust" : return new int[] {R.string.rust,R.drawable.rust};
            case "Sacrificial Bow" : return new int[] {R.string.sacrificial_bow,R.drawable.sacrificial_bow};
            case "The Stringless" : return new int[] {R.string.the_stringless,R.drawable.the_stringless};
            case "The Viridescent Hunt" : return new int[] {R.string.the_viridescent_hunt,R.drawable.the_viridescent_hunt};
            case "Windblume Ode" : return new int[] {R.string.windblume_ode,R.drawable.windblume_ode};
            case "Amos' Bow" : return new int[] {R.string.amos_bow,R.drawable.amos_bow};
            case "Elegy for the End" : return new int[] {R.string.elegy_for_the_end,R.drawable.elegy_for_the_end};
            case "Skyward Harp" : return new int[] {R.string.skyward_harp,R.drawable.skyward_harp};
            case "Thundering Pulse" : return new int[] {R.string.thundering_pulse,R.drawable.thundering_pulse};
            case "Mouun's Moon" : return new int[] {R.string.mouuns_moon,R.drawable.mouuns_moon};
            //add in 20220126
            case "Kagura's Verity" : return new int[] {R.string.kaguras_verity,R.drawable.kaguras_verity};
            case "Oathsworn Eye" : return new int[] {R.string.oathsworn_eye,R.drawable.oathsworn_eye};
            //add in 20220329
            case "Haran Geppaku Futsu" : return new int[] {R.string.haran_geppaku_futsu,R.drawable.haran_geppaku_futsu};
            //add in 20220521
            case "Aqua Simulacra" : return new int[] {R.string.aqua_simulacra,R.drawable.aqua_simulacra};
            case "Kagotsurube Isshin" : return new int[] {R.string.kagotsurube_isshin,R.drawable.kagotsurube_isshin};
            case "Fading Twilight" : return new int[] {R.string.fading_twilight,R.drawable.fading_twilight};
            //add in 20220714
            case "Sapwood Blade" : return new int[] {R.string.sapwood_blade,R.drawable.timber_blade};
            case "Forest Regalia" : return new int[] {R.string.forest_regalia,R.drawable.forest_regalia};
            case "Moonpiercer" : return new int[] {R.string.moonpiercer,R.drawable.moonpiercer};
            case "King's Squire" : return new int[] {R.string.kings_squire,R.drawable.kings_squire};
            case "End of the Line" : return new int[] {R.string.end_of_the_line,R.drawable.trawler};
            case "Hunter's Path" : return new int[] {R.string.hunters_path,R.drawable.hunters_path};
            case "Fruit of Fulfillment" : return new int[] {R.string.fruit_of_fulfillment,R.drawable.fruit_of_fulfillment};
            //add in 20220924
            case "Xiphos' Moonlight" : return new int[] {R.string.xiphos_moonlight,R.drawable.xiphos_moonlight};
            case "Key of Khaj-Nisut" : return new int[] {R.string.key_of_khaj_nisut,R.drawable.key_of_khaj_nisut};
            case "Makhaira Aquamarine" : return new int[] {R.string.makhaira_aquamarine,R.drawable.makhaira_aquamarine};
            case "Missive Windspear" : return new int[] {R.string.missive_windspear,R.drawable.missive_windspear};
            case "Staff of the Scarlet Sands" : return new int[] {R.string.staff_of_the_scarlet_sands,R.drawable.staff_of_the_scarlet_sands};
            case "Wandering Evenstar" : return new int[] {R.string.wandering_evenstar,R.drawable.wandering_evenstar};
            //add in 20221001
            case "A Thousand Floating Dreams" : return new int[] {R.string.a_thousand_floating_dreams,R.drawable.a_thousand_floating_dreams};
            //add in 20221210
            case "Polar Star" : return new int[] {R.string.polar_star,R.drawable.polar_star};
            case "Toukabou Shigure" : return new int[] {R.string.toukabou_shigure,R.drawable.toukabou_shigure};
            case "Tulaytullah's Remembrance" : return new int[] {R.string.tulaytullahs_remembrance,R.drawable.tulaytullahs_remembrance};
            //add in 20230118
            case "Light of Foliar Incision" : return new int[] {R.string.light_of_foliar_incision,R.drawable.light_of_foliar_incision};
            //add in 20230225
            case "Mailed Flower" : return new int[] {R.string.mailed_flower,R.drawable.mailed_flower};
            case "Beacon of the Reed Sea" : return new int[] {R.string.beacon_of_the_reed_sea,R.drawable.beacon_of_the_reed_sea};
            //add in 20230416
            case "Jadefall's Splendor" : return new int[] {R.string.jadefalls_splendor,R.drawable.jadefalls_splendor};

            default :
                System.out.println("WEAPON+ NAME ERROR : "+str);
                return new int[] {R.string.unknown,R.drawable.hu_tao_unknown};
        }
    }


    public int[] getWeaponGachaByName(String str) {
        switch (str){
            case "Amenoma Kageuchi" : return new int[] {R.string.amenoma_kageuchi,R.drawable.amenoma_kageuchi_gacha};
            case "Aquila Favonia" : return new int[] {R.string.aquila_favonia,R.drawable.aquila_favonia_gacha};
            case "Blackcliff Longsword" : return new int[] {R.string.blackcliff_longsword,R.drawable.blackcliff_longsword_gacha};
            case "Cool Steel" : return new int[] {R.string.cool_steel,R.drawable.cool_steel_gacha};
            case "Dark Iron Sword" : return new int[] {R.string.dark_iron_sword,R.drawable.dark_iron_sword_gacha};
            case "Dull Blade" : return new int[] {R.string.dull_blade,R.drawable.dull_blade_gacha};
            case "Favonius Sword" : return new int[] {R.string.favonius_sword,R.drawable.favonius_sword_gacha};
            case "Festering Desire" : return new int[] {R.string.festering_desire,R.drawable.festering_desire_gacha};
            case "Fillet Blade" : return new int[] {R.string.fillet_blade,R.drawable.fillet_blade_gacha};
            case "Freedom-Sworn" : return new int[] {R.string.freedom_sworn,R.drawable.freedom_sworn_gacha};
            case "Freedom Sworn" : return new int[] {R.string.freedom_sworn,R.drawable.freedom_sworn_gacha};
            case "Harbinger of Dawn" : return new int[] {R.string.harbinger_of_dawn,R.drawable.harbinger_of_dawn_gacha};
            case "Iron Sting" : return new int[] {R.string.iron_sting,R.drawable.iron_sting_gacha};
            case "Lion's Roar" : return new int[] {R.string.lions_roar,R.drawable.lions_roar_gacha};
            case "Mistsplitter Reforged" : return new int[] {R.string.mistsplitter_reforged,R.drawable.mistsplitter_reforged_gacha};
            case "Primordial Jade Cutter" : return new int[] {R.string.primordial_jade_cutter,R.drawable.primordial_jade_cutter_gacha};
            case "Prototype Rancour" : return new int[] {R.string.prototype_rancour,R.drawable.prototype_rancour_gacha};
            case "Royal Longsword" : return new int[] {R.string.royal_longsword,R.drawable.royal_longsword_gacha};
            case "Sacrificial Sword" : return new int[] {R.string.sacrificial_sword,R.drawable.sacrificial_sword_gacha};
            case "Silver Sword" : return new int[] {R.string.silver_sword,R.drawable.silver_sword_gacha};
            case "Skyrider Sword" : return new int[] {R.string.skyrider_sword,R.drawable.skyrider_sword_gacha};
            case "Skyward Blade" : return new int[] {R.string.skyward_blade,R.drawable.skyward_blade_gacha};
            case "Summit Shaper" : return new int[] {R.string.summit_shaper,R.drawable.summit_shaper_gacha};
            case "Sword of Descension" : return new int[] {R.string.sword_of_descension,R.drawable.sword_of_descension_gacha};
            case "The Alley Flash" : return new int[] {R.string.the_alley_flash,R.drawable.the_alley_flash_gacha};
            case "The Black Sword" : return new int[] {R.string.the_black_sword,R.drawable.the_black_sword_gacha};
            case "The Flute" : return new int[] {R.string.the_flute,R.drawable.the_flute_gacha};
            case "Traveler's Handy Sword" : return new int[] {R.string.travelers_handy_sword,R.drawable.travelers_handy_sword_gacha};
            case "Cinnabar Spindle" : return new int[] {R.string.cinnabar_spindle,R.drawable.cinnabar_spindle_gacha};

            case "Waster Greatsword" : return new int[] {R.string.waster_greatsword,R.drawable.waster_greatsword_gacha};
            case "Old Merc's Pal" : return new int[] {R.string.old_mercs_pal,R.drawable.old_mercs_pal_gacha};
            case "Bloodtainted Greatsword" : return new int[] {R.string.bloodtainted_greatsword,R.drawable.bloodtainted_greatsword_gacha};
            case "Debate Club" : return new int[] {R.string.debate_club,R.drawable.debate_club_gacha};
            case "Quartz" : return new int[] {R.string.quartz,R.drawable.quartz_gacha};
            case "Ferrous Shadow" : return new int[] {R.string.ferrous_shadow,R.drawable.ferrous_shadow_gacha};
            case "Skyrider Greatsword" : return new int[] {R.string.skyrider_greatsword,R.drawable.skyrider_greatsword_gacha};
            case "White Iron Greatsword" : return new int[] {R.string.white_iron_greatsword,R.drawable.white_iron_greatsword_gacha};
            case "Blackcliff Slasher" : return new int[] {R.string.blackcliff_slasher,R.drawable.blackcliff_slasher_gacha};
            case "Favonius Greatsword" : return new int[] {R.string.favonius_greatsword,R.drawable.favonius_greatsword_gacha};
            case "Katsuragikiri Nagamasa" : return new int[] {R.string.katsuragikiri_nagamasa,R.drawable.katsuragikiri_nagamasa_gacha};
            case "Lithic Blade" : return new int[] {R.string.lithic_blade,R.drawable.lithic_blade_gacha};
            case "Luxurious Sea-Lord" : return new int[] {R.string.luxurious_sea_lord,R.drawable.luxurious_sea_lord_gacha};
            case "Prototype Archaic" : return new int[] {R.string.prototype_archaic,R.drawable.prototype_archaic_gacha};
            case "Rainslasher" : return new int[] {R.string.rainslasher,R.drawable.rainslasher_gacha};
            case "Royal Greatsword" : return new int[] {R.string.royal_greatsword,R.drawable.royal_greatsword_gacha};
            case "Sacrificial Greatsword" : return new int[] {R.string.sacrificial_greatsword,R.drawable.sacrificial_greatsword_gacha};
            case "Serpent Spine" : return new int[] {R.string.serpent_spine,R.drawable.serpent_spine_gacha};
            case "Snow-Tombed Starsilver" : return new int[] {R.string.snow_tombed_starsilver,R.drawable.snow_tombed_starsilver_gacha};
            case "The Bell" : return new int[] {R.string.the_bell,R.drawable.the_bell_gacha};
            case "Whiteblind" : return new int[] {R.string.whiteblind,R.drawable.whiteblind_gacha};
            case "Skyward Pride" : return new int[] {R.string.skyward_pride,R.drawable.skyward_pride_gacha};
            case "Song of Broken Pines" : return new int[] {R.string.song_of_broken_pines,R.drawable.song_of_broken_pines_gacha};
            case "The Unforged" : return new int[] {R.string.the_unforged,R.drawable.the_unforged_gacha};
            case "Wolf's Gravestone" : return new int[] {R.string.wolfs_gravestone,R.drawable.wolfs_gravestone_gacha};
            case "Akuoumaru" : return new int[] {R.string.akuoumaru,R.drawable.akuoumaru_gacha};
            case "Redhorn Stonethresher" : return new int[] {R.string.redhorn_stonethresher,R.drawable.redhorn_stonethresher_gacha};

            case "Beginner's Protector" : return new int[] {R.string.beginners_protector,R.drawable.beginners_protector_gacha};
            case "Iron Point" : return new int[] {R.string.iron_point,R.drawable.iron_point_gacha};
            case "Black Tassel" : return new int[] {R.string.black_tassel,R.drawable.black_tassel_gacha};
            case "Halberd" : return new int[] {R.string.halberd,R.drawable.halberd_gacha};
            case "White Tassel" : return new int[] {R.string.white_tassel,R.drawable.white_tassel_gacha};
            case "Blackcliff Pole" : return new int[] {R.string.blackcliff_pole,R.drawable.blackcliff_pole_gacha};
            case "Crescent Pike" : return new int[] {R.string.crescent_pike,R.drawable.crescent_pike_gacha};
            case "Deathmatch" : return new int[] {R.string.deathmatch,R.drawable.deathmatch_gacha};
            case "Dragon's Bane" : return new int[] {R.string.dragons_bane,R.drawable.dragons_bane_gacha};
            case "Dragonspine Spear" : return new int[] {R.string.dragonspine_spear,R.drawable.dragonspine_spear_gacha};
            case "Favonius Lance" : return new int[] {R.string.favonius_lance,R.drawable.favonius_lance_gacha};
            case "Kitain Cross Spear" : return new int[] {R.string.kitain_cross_spear,R.drawable.kitain_cross_spear_gacha};
            case "Lithic Spear" : return new int[] {R.string.lithic_spear,R.drawable.lithic_spear_gacha};
            case "Prototype Starglitter" : return new int[] {R.string.prototype_starglitter,R.drawable.prototype_starglitter_gacha};
            case "Royal Spear" : return new int[] {R.string.royal_spear,R.drawable.royal_spear_gacha};
            case "The Catch" : return new int[] {R.string.the_catch,R.drawable.the_catch_gacha};
            case "Engulfing Lightning" : return new int[] {R.string.engulfing_lightning,R.drawable.engulfing_lightning_gacha};
            case "Primordial Jade Winged-Spear" : return new int[] {R.string.primordial_jade_winged_spear,R.drawable.primordial_jade_winged_spear_gacha};
            case "Skyward Spine" : return new int[] {R.string.skyward_spine,R.drawable.skyward_spine_gacha};
            case "Staff of Homa" : return new int[] {R.string.staff_of_homa,R.drawable.staff_of_homa_gacha};
            case "Vortex Vanquisher" : return new int[] {R.string.vortex_vanquisher,R.drawable.vortex_vanquisher_gacha};
            case "Wavebreaker's Fin" : return new int[] {R.string.wavebreakers_fin,R.drawable.wavebreakers_fin_gacha};
            case "Calamity Queller" : return new int[] {R.string.calamity_queller,R.drawable.calamity_queller_gacha};

            case "Apprentice's Notes" : return new int[] {R.string.apprentices_notes,R.drawable.apprentices_notes_gacha};
            case "Pocket Grimoire" : return new int[] {R.string.pocket_grimoire,R.drawable.pocket_grimoire_gacha};
            case "Amber Catalyst" : return new int[] {R.string.amber_catalyst,R.drawable.amber_catalyst};
            case "Emerald Orb" : return new int[] {R.string.emerald_orb,R.drawable.emerald_orb_gacha};
            case "Magic Guide" : return new int[] {R.string.magic_guide,R.drawable.magic_guide_gacha};
            case "Otherworldly Story" : return new int[] {R.string.otherworldly_story,R.drawable.otherworldly_story_gacha};
            case "Thrilling Tales of Dragon Slayers" : return new int[] {R.string.thrilling_tales_of_dragon_slayers,R.drawable.thrilling_tales_of_dragon_slayers_gacha};
            case "Twin Nephrite" : return new int[] {R.string.twin_nephrite,R.drawable.twin_nephrite_gacha};
            case "Blackcliff Agate" : return new int[] {R.string.blackcliff_agate,R.drawable.blackcliff_agate_gacha};
            case "Dodoco Tales" : return new int[] {R.string.dodoco_tales,R.drawable.dodoco_tales_gacha};
            case "Eye of Perception" : return new int[] {R.string.eye_of_perception,R.drawable.eye_of_perception_gacha};
            case "Favonius Codex" : return new int[] {R.string.favonius_codex,R.drawable.favonius_codex_gacha};
            case "Frostbearer" : return new int[] {R.string.frostbearer,R.drawable.frostbearer_gacha};
            case "Hakushin Ring" : return new int[] {R.string.hakushin_ring,R.drawable.hakushin_ring_gacha};
            case "Mappa Mare" : return new int[] {R.string.mappa_mare,R.drawable.mappa_mare_gacha};
            case "Prototype Amber" : return new int[] {R.string.prototype_amber,R.drawable.prototype_amber_gacha};
            case "Royal Grimoire" : return new int[] {R.string.royal_grimoire,R.drawable.royal_grimoire_gacha};
            case "Sacrificial Fragments" : return new int[] {R.string.sacrificial_fragments,R.drawable.sacrificial_fragments_gacha};
            case "Solar Pearl" : return new int[] {R.string.solar_pearl,R.drawable.solar_pearl_gacha};
            case "The Widsith" : return new int[] {R.string.the_widsith,R.drawable.the_widsith_gacha};
            case "Wine and Song" : return new int[] {R.string.wine_and_song,R.drawable.wine_and_song_gacha};
            case "Everlasting Moonglow" : return new int[] {R.string.everlasting_moonglow,R.drawable.everlasting_moonglow_gacha};
            case "Lost Prayer to the Sacred Winds" : return new int[] {R.string.lost_prayer_to_the_sacred_winds,R.drawable.lost_prayer_to_the_sacred_winds_gacha};
            case "Memory of Dust" : return new int[] {R.string.memory_of_dust,R.drawable.memory_of_dust_gacha};
            case "Skyward Atlas" : return new int[] {R.string.skyward_atlas,R.drawable.skyward_atlas_gacha};

            case "Hunter's Bow" : return new int[] {R.string.hunters_bow,R.drawable.hunters_bow_gacha};
            case "Seasoned Hunter's Bow" : return new int[] {R.string.seasoned_hunters_bow,R.drawable.seasoned_hunters_bow_gacha};
            case "Ebony Bow" : return new int[] {R.string.ebony_bow,R.drawable.ebony_bow_gacha};
            case "Messenger" : return new int[] {R.string.messenger,R.drawable.messenger_gacha};
            case "Raven Bow" : return new int[] {R.string.raven_bow,R.drawable.raven_bow_gacha};
            case "Recurve Bow" : return new int[] {R.string.recurve_bow,R.drawable.recurve_bow_gacha};
            case "Sharpshooter's Oath" : return new int[] {R.string.sharpshooters_oath,R.drawable.sharpshooters_oath_gacha};
            case "Slingshot" : return new int[] {R.string.slingshot,R.drawable.slingshot_gacha};
            case "Alley Hunter" : return new int[] {R.string.alley_hunter,R.drawable.alley_hunter_gacha};
            case "Blackcliff Warbow" : return new int[] {R.string.blackcliff_warbow,R.drawable.blackcliff_warbow_gacha};
            case "Compound Bow" : return new int[] {R.string.compound_bow,R.drawable.compound_bow_gacha};
            case "Favonius Warbow" : return new int[] {R.string.favonius_warbow,R.drawable.favonius_warbow_gacha};
            case "Hamayumi" : return new int[] {R.string.hamayumi,R.drawable.hamayumi_gacha};
            case "Mitternachts Waltz" : return new int[] {R.string.mitternachts_waltz,R.drawable.mitternachts_waltz_gacha};
            case "Predator" : return new int[] {R.string.predator,R.drawable.predator_gacha};
            case "Prototype Crescent" : return new int[] {R.string.prototype_crescent,R.drawable.prototype_crescent_gacha};
            case "Royal Bow" : return new int[] {R.string.royal_bow,R.drawable.royal_bow_gacha};
            case "Rust" : return new int[] {R.string.rust,R.drawable.rust_gacha};
            case "Sacrificial Bow" : return new int[] {R.string.sacrificial_bow,R.drawable.sacrificial_bow_gacha};
            case "The Stringless" : return new int[] {R.string.the_stringless,R.drawable.the_stringless_gacha};
            case "The Viridescent Hunt" : return new int[] {R.string.the_viridescent_hunt,R.drawable.the_viridescent_hunt_gacha};
            case "Windblume Ode" : return new int[] {R.string.windblume_ode,R.drawable.windblume_ode_gacha};
            case "Amos' Bow" : return new int[] {R.string.amos_bow,R.drawable.amos_bow_gacha};
            case "Elegy for the End" : return new int[] {R.string.elegy_for_the_end,R.drawable.elegy_for_the_end_gacha};
            case "Skyward Harp" : return new int[] {R.string.skyward_harp,R.drawable.skyward_harp_gacha};
            case "Thundering Pulse" : return new int[] {R.string.thundering_pulse,R.drawable.thundering_pulse_gacha};
            case "Mouun's Moon" : return new int[] {R.string.mouuns_moon,R.drawable.mouuns_moon_gacha};
            //add in 20220126
            case "Kagura's Verity" : return new int[] {R.string.kaguras_verity,R.drawable.kaguras_verity_gacha};
            case "Oathsworn Eye" : return new int[] {R.string.oathsworn_eye,R.drawable.oathsworn_eye_gacha};
            //add in 20220329
            case "Haran Geppaku Futsu" : return new int[] {R.string.haran_geppaku_futsu,R.drawable.haran_geppaku_futsu_gacha};
            // add in 20220521
            case "Aqua Simulacra" : return new int[] {R.string.aqua_simulacra,R.drawable.aqua_simulacra_gacha};
            case "Kagotsurube Isshin" : return new int[] {R.string.kagotsurube_isshin,R.drawable.kagotsurube_isshin_gacha};
            case "Fading Twilight" : return new int[] {R.string.fading_twilight,R.drawable.fading_twilight_gacha};
            //add in 20220714
            case "Sapwood Blade" : return new int[] {R.string.sapwood_blade,R.drawable.timber_blade_gacha};
            case "Forest Regalia" : return new int[] {R.string.forest_regalia,R.drawable.forest_regalia_gacha};
            case "Moonpiercer" : return new int[] {R.string.moonpiercer,R.drawable.moonpiercer_gacha};
            case "King's Squire" : return new int[] {R.string.kings_squire,R.drawable.kings_squire_gacha};
            case "End of the Line" : return new int[] {R.string.end_of_the_line,R.drawable.trawler_gacha};
            case "Hunter's Path" : return new int[] {R.string.hunters_path,R.drawable.hunters_path_gacha};
            case "Fruit of Fulfillment" : return new int[] {R.string.fruit_of_fulfillment,R.drawable.fruit_of_fulfillment_gacha};
            //add in 20220924
            case "Xiphos' Moonlight" : return new int[] {R.string.xiphos_moonlight,R.drawable.xiphos_moonlight_gacha};
            case "Key of Khaj-Nisut" : return new int[] {R.string.key_of_khaj_nisut,R.drawable.key_of_khaj_nisut_gacha};
            case "Makhaira Aquamarine" : return new int[] {R.string.makhaira_aquamarine,R.drawable.makhaira_aquamarine_gacha};
            case "Missive Windspear" : return new int[] {R.string.missive_windspear,R.drawable.missive_windspear_gacha};
            case "Staff of the Scarlet Sands" : return new int[] {R.string.staff_of_the_scarlet_sands,R.drawable.staff_of_the_scarlet_sands_gacha};
            case "Wandering Evenstar" : return new int[] {R.string.wandering_evenstar,R.drawable.wandering_evenstar_gacha};
            //add in 20221003
            case "A Thousand Floating Dreams" : return new int[] {R.string.a_thousand_floating_dreams,R.drawable.a_thousand_floating_dreams_gacha};
            //add in 20221210
            case "Polar Star" : return new int[] {R.string.polar_star,R.drawable.polar_star_gacha};
            case "Toukabou Shigure" : return new int[] {R.string.toukabou_shigure,R.drawable.toukabou_shigure_gacha};
            case "Tulaytullah's Remembrance" : return new int[] {R.string.tulaytullahs_remembrance,R.drawable.tulaytullahs_remembrance_gacha};
            //add in 20230118
            case "Light of Foliar Incision" : return new int[] {R.string.light_of_foliar_incision,R.drawable.light_of_foliar_incision_gacha};
            //add in 20230225
            case "Mailed Flower" : return new int[] {R.string.mailed_flower,R.drawable.mailed_flower_gacha};
            case "Beacon of the Reed Sea" : return new int[] {R.string.beacon_of_the_reed_sea,R.drawable.beacon_of_the_reed_sea_gacha};
            //add in 20230416
            case "Jadefall's Splendor" : return new int[] {R.string.jadefalls_splendor,R.drawable.jadefalls_splendor_gacha};
            //add in 20230810 - 4.0.0
            case "Wolf-Fang" : return new int[] {R.string.wolf_fang,R.drawable.wolf_fang_gacha};
            case "Finale of the Deep" : return new int[] {R.string.finale_of_the_deep,R.drawable.finale_of_the_deep_gacha};
            case "Crossing of Fleuve Cendre" : return new int[] {R.string.crossing_of_fleuve_cendre,R.drawable.crossing_of_fleuve_cendre_gacha};
            case "Talking Stick" : return new int[] {R.string.talking_stick,R.drawable.talking_stick_gacha};
            case "Tidal Shadow" : return new int[] {R.string.tidal_shadow,R.drawable.tidal_shadow_gacha};
            case "Ballad of the Fjords" : return new int[] {R.string.ballad_of_the_fjords,R.drawable.ballad_of_the_fjords_gacha};
            case "Rightful Reward" : return new int[] {R.string.rightful_reward,R.drawable.rightful_reward_gacha};
            case "Sacrificial Jade" : return new int[] {R.string.sacrificial_jade,R.drawable.sacrificial_jade_gacha};
            case "Flowing Purity" : return new int[] {R.string.flowing_purity,R.drawable.flowing_purity_gacha};
            case "Scion of the Blazing Sun" : return new int[] {R.string.scion_of_the_blazing_sun,R.drawable.scion_of_the_blazing_sun_gacha};
            case "Song of Stillness" : return new int[] {R.string.song_of_stillness,R.drawable.song_of_stillness_gacha};
            case "The First Great Magic" : return new int[] {R.string.the_first_great_magic,R.drawable.the_first_great_magic_gacha};

            default :
                System.out.println("WEAPON+ NAME ERROR : "+str);
                return new int[] {R.string.unknown,R.drawable.hu_tao_unknown};
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
            //add in 20230810 - 4.0.0
            case "wolf_fang" : return "Wolf-Fang";
            case "finale_of_the_deep" : return "Finale of the Deep";
            case "crossing_of_fleuve_cendre" : return "Crossing of Fleuve Cendre";
            case "talking_stick" : return "Talking Stick";
            case "tidal_shadow" : return "Tidal Shadow";
            case "ballad_of_the_fjords" : return "Ballad of the Fjords";
            case "rightful_reward" : return "Rightful Reward";
            case "sacrificial_jade" : return "Sacrificial Jade";
            case "flowing_purity" : return "Flowing Purity";
            case "scion_of_the_blazing_sun" : return "Scion of the Blazing Sun";
            case "song_of_stillness" : return "Song of Stillness";
            case "the_first_great_magic" : return "The First Great Magic";

            default: return str;
        }
    }
    public int[] getArtifactByName (String str){
        switch (str){
            case "Adventurer" : return new int[] {R.string.adventurer,R.drawable.adventurer_1,R.drawable.adventurer_2,R.drawable.adventurer_3,R.drawable.adventurer_4,R.drawable.adventurer_5};
            case "Archaic Petra" : return new int[] {R.string.archaic_petra,R.drawable.archaic_petra_1,R.drawable.archaic_petra_2,R.drawable.archaic_petra_3,R.drawable.archaic_petra_4,R.drawable.archaic_petra_5};
            case "Berserker" : return new int[] {R.string.berserker,R.drawable.berserker_1,R.drawable.berserker_2,R.drawable.berserker_3,R.drawable.berserker_4,R.drawable.berserker_5};
            case "Blizzard Strayer" : return new int[] {R.string.blizzard_strayer,R.drawable.blizzard_strayer_1,R.drawable.blizzard_strayer_2,R.drawable.blizzard_strayer_3,R.drawable.blizzard_strayer_4,R.drawable.blizzard_strayer_5};
            case "Bloodstained Chivalry" : return new int[] {R.string.bloodstained_chivalry,R.drawable.bloodstained_chivalry_1,R.drawable.bloodstained_chivalry_2,R.drawable.bloodstained_chivalry_3,R.drawable.bloodstained_chivalry_4,R.drawable.bloodstained_chivalry_5};
            case "Brave Heart" : return new int[] {R.string.brave_heart,R.drawable.brave_heart_1,R.drawable.brave_heart_2,R.drawable.brave_heart_3,R.drawable.brave_heart_4,R.drawable.brave_heart_5};
            case "Crimson Witch of Flames" : return new int[] {R.string.crimson_witch_of_flames,R.drawable.crimson_witch_of_flames_1,R.drawable.crimson_witch_of_flames_2,R.drawable.crimson_witch_of_flames_3,R.drawable.crimson_witch_of_flames_4,R.drawable.crimson_witch_of_flames_5};
            case "Defender's Will" : return new int[] {R.string.defenders_will,R.drawable.defenders_will_1,R.drawable.defenders_will_2,R.drawable.defenders_will_3,R.drawable.defenders_will_4,R.drawable.defenders_will_5};
            case "Emblem of Severed Fate" : return new int[] {R.string.emblem_of_severed_fate,R.drawable.emblem_of_severed_fate_1,R.drawable.emblem_of_severed_fate_2,R.drawable.emblem_of_severed_fate_3,R.drawable.emblem_of_severed_fate_4,R.drawable.emblem_of_severed_fate_5};
            case "Gambler" : return new int[] {R.string.gambler,R.drawable.gambler_1,R.drawable.gambler_2,R.drawable.gambler_3,R.drawable.gambler_4,R.drawable.gambler_5};
            case "Gladiator's Finale" : return new int[] {R.string.gladiators_finale,R.drawable.gladiators_finale_1,R.drawable.gladiators_finale_2,R.drawable.gladiators_finale_3,R.drawable.gladiators_finale_4,R.drawable.gladiators_finale_5};
            case "Heart of Depth" : return new int[] {R.string.heart_of_depth,R.drawable.heart_of_depth_1,R.drawable.heart_of_depth_2,R.drawable.heart_of_depth_3,R.drawable.heart_of_depth_4,R.drawable.heart_of_depth_5};
            case "Instructor" : return new int[] {R.string.instructor,R.drawable.instructor_1,R.drawable.instructor_2,R.drawable.instructor_3,R.drawable.instructor_4,R.drawable.instructor_5};
            case "Lavawalker" : return new int[] {R.string.lavawalker,R.drawable.lavawalker_1,R.drawable.lavawalker_2,R.drawable.lavawalker_3,R.drawable.lavawalker_4,R.drawable.lavawalker_5};
            case "Lucky Dog" : return new int[] {R.string.lucky_dog,R.drawable.lucky_dog_1,R.drawable.lucky_dog_2,R.drawable.lucky_dog_3,R.drawable.lucky_dog_4,R.drawable.lucky_dog_5};
            case "Maiden Beloved" : return new int[] {R.string.maiden_beloved,R.drawable.maiden_beloved_1,R.drawable.maiden_beloved_2,R.drawable.maiden_beloved_3,R.drawable.maiden_beloved_4,R.drawable.maiden_beloved_5};
            case "Martial Artist" : return new int[] {R.string.martial_artist,R.drawable.martial_artist_1,R.drawable.martial_artist_2,R.drawable.martial_artist_3,R.drawable.martial_artist_4,R.drawable.martial_artist_5};
            case "Noblesse Oblige" : return new int[] {R.string.noblesse_oblige,R.drawable.noblesse_oblige_1,R.drawable.noblesse_oblige_2,R.drawable.noblesse_oblige_3,R.drawable.noblesse_oblige_4,R.drawable.noblesse_oblige_5};
            case "Pale Flame" : return new int[] {R.string.pale_flame,R.drawable.pale_flame_1,R.drawable.pale_flame_2,R.drawable.pale_flame_3,R.drawable.pale_flame_4,R.drawable.pale_flame_5};
            case "Prayers of Destiny" : return new int[] {R.string.prayers_of_destiny,R.drawable.prayers_of_destiny_4,R.drawable.prayers_of_destiny_4,R.drawable.prayers_of_destiny_4,R.drawable.prayers_of_destiny_4,R.drawable.prayers_of_destiny_4};
            case "Prayers of Illumination" : return new int[] {R.string.prayers_of_illumination,R.drawable.prayers_of_illumination_4,R.drawable.prayers_of_illumination_4,R.drawable.prayers_of_illumination_4,R.drawable.prayers_of_illumination_4,R.drawable.prayers_of_illumination_4};
            case "Prayers of Wisdom" : return new int[] {R.string.prayers_of_wisdom,R.drawable.prayers_of_wisdom_4,R.drawable.prayers_of_wisdom_4,R.drawable.prayers_of_wisdom_4,R.drawable.prayers_of_wisdom_4,R.drawable.prayers_of_wisdom_4};
            case "Prayers of Springtime" : return new int[] {R.string.prayers_of_springtime,R.drawable.prayers_of_springtime_4,R.drawable.prayers_of_springtime_4,R.drawable.prayers_of_springtime_4,R.drawable.prayers_of_springtime_4,R.drawable.prayers_of_springtime_4};
            case "Resolution of Sojourner" : return new int[] {R.string.resolution_of_sojourner,R.drawable.resolution_of_sojourner_1,R.drawable.resolution_of_sojourner_2,R.drawable.resolution_of_sojourner_3,R.drawable.resolution_of_sojourner_4,R.drawable.resolution_of_sojourner_5};
            case "Retracing Bolide" : return new int[] {R.string.retracing_bolide,R.drawable.retracing_bolide_1,R.drawable.retracing_bolide_2,R.drawable.retracing_bolide_3,R.drawable.retracing_bolide_4,R.drawable.retracing_bolide_5};
            case "Scholar" : return new int[] {R.string.scholar,R.drawable.scholar_1,R.drawable.scholar_2,R.drawable.scholar_3,R.drawable.scholar_4,R.drawable.scholar_5};
            case "Shimenawa's Reminiscence" : return new int[] {R.string.shimenawas_reminiscence,R.drawable.shimenawas_reminiscence_1,R.drawable.shimenawas_reminiscence_2,R.drawable.shimenawas_reminiscence_3,R.drawable.shimenawas_reminiscence_4,R.drawable.shimenawas_reminiscence_5};
            case "Tenacity of the Millelith" : return new int[] {R.string.tenacity_of_the_millelith,R.drawable.tenacity_of_the_millelith_1,R.drawable.tenacity_of_the_millelith_2,R.drawable.tenacity_of_the_millelith_3,R.drawable.tenacity_of_the_millelith_4,R.drawable.tenacity_of_the_millelith_5};
            case "The Exile" : return new int[] {R.string.the_exile,R.drawable.the_exile_1,R.drawable.the_exile_2,R.drawable.the_exile_3,R.drawable.the_exile_4,R.drawable.the_exile_5};
            case "Thundering Fury" : return new int[] {R.string.thundering_fury,R.drawable.thundering_fury_1,R.drawable.thundering_fury_2,R.drawable.thundering_fury_3,R.drawable.thundering_fury_4,R.drawable.thundering_fury_5};
            case "Thunder-soother" : return new int[] {R.string.thundersoother,R.drawable.thundersoother_1,R.drawable.thundersoother_2,R.drawable.thundersoother_3,R.drawable.thundersoother_4,R.drawable.thundersoother_5};
            case "Tiny Miracle" : return new int[] {R.string.tiny_miracle,R.drawable.tiny_miracle_1,R.drawable.tiny_miracle_2,R.drawable.tiny_miracle_3,R.drawable.tiny_miracle_4,R.drawable.tiny_miracle_5};
            case "Traveling Doctor" : return new int[] {R.string.traveling_doctor,R.drawable.traveling_doctor_1,R.drawable.traveling_doctor_2,R.drawable.traveling_doctor_3,R.drawable.traveling_doctor_4,R.drawable.traveling_doctor_5};
            case "Viridescent Venerer" : return new int[] {R.string.viridescent_venerer,R.drawable.viridescent_venerer_1,R.drawable.viridescent_venerer_2,R.drawable.viridescent_venerer_3,R.drawable.viridescent_venerer_4,R.drawable.viridescent_venerer_5};
            case "Wanderer's Troupe" : return new int[] {R.string.wanderers_troupe,R.drawable.wanderers_troupe_1,R.drawable.wanderers_troupe_2,R.drawable.wanderers_troupe_3,R.drawable.wanderers_troupe_4,R.drawable.wanderers_troupe_5};

            // add in 20211127
            case "Husk of Opulent Dreams" : return new int[] {R.string.husk_of_opulent_dreams,R.drawable.husk_of_opulent_dreams_1,R.drawable.husk_of_opulent_dreams_2,R.drawable.husk_of_opulent_dreams_3,R.drawable.husk_of_opulent_dreams_4,R.drawable.husk_of_opulent_dreams_5};
            case "Ocean-Hued Clam" : return new int[] {R.string.ocean_hued_clam,R.drawable.ocean_hued_clam_1,R.drawable.ocean_hued_clam_2,R.drawable.ocean_hued_clam_3,R.drawable.ocean_hued_clam_4,R.drawable.ocean_hued_clam_5};
            // add in 20220329
            case "Echoes of an Offering" : return new int[] {R.string.echoes_of_an_offering,R.drawable.echoes_of_an_offering_1,R.drawable.echoes_of_an_offering_2,R.drawable.echoes_of_an_offering_3,R.drawable.echoes_of_an_offering_4,R.drawable.echoes_of_an_offering_5};
            case "Vermillion Hereafter" : return new int[] {R.string.vermillion_hereafter,R.drawable.vermillion_hereafter_1,R.drawable.vermillion_hereafter_2,R.drawable.vermillion_hereafter_3,R.drawable.vermillion_hereafter_4,R.drawable.vermillion_hereafter_5};
            case "Deepwood Memories" : return new int[] {R.string.deepwood_memories,R.drawable.deepwood_memories_1,R.drawable.deepwood_memories_2,R.drawable.deepwood_memories_3,R.drawable.deepwood_memories_4,R.drawable.deepwood_memories_5};
            case "Gilded Dreams" : return new int[] {R.string.gilded_dreams,R.drawable.gilded_dreams_1,R.drawable.gilded_dreams_2,R.drawable.gilded_dreams_3,R.drawable.gilded_dreams_4,R.drawable.gilded_dreams_5};
            // add in 20221210
            case "Desert Pavilion Chronicle" : return new int[] {R.string.desert_pavilion_chronicle,R.drawable.desert_pavilion_chronicle_1,R.drawable.desert_pavilion_chronicle_2,R.drawable.desert_pavilion_chronicle_3,R.drawable.desert_pavilion_chronicle_4,R.drawable.desert_pavilion_chronicle_5};
            case "Flower of Paradise Lost" : return new int[] {R.string.flower_of_paradise_lost,R.drawable.flower_of_paradise_lost_1,R.drawable.flower_of_paradise_lost_2,R.drawable.flower_of_paradise_lost_3,R.drawable.flower_of_paradise_lost_4,R.drawable.flower_of_paradise_lost_5};
            // add in 20230416
            case "Nymph's Dream" : return new int[] {R.string.nymphs_dream,R.drawable.nymphs_dream_1,R.drawable.nymphs_dream_2,R.drawable.nymphs_dream_3,R.drawable.nymphs_dream_4,R.drawable.nymphs_dream_5};
            case "Vourukasha's Glow" : return new int[] {R.string.vourukashas_glow,R.drawable.vourukashas_glow_1,R.drawable.vourukashas_glow_2,R.drawable.vourukashas_glow_3,R.drawable.vourukashas_glow_4,R.drawable.vourukashas_glow_5};
            // add in 20230810 - 4.0.0
            case "Marechaussee Hunter" : return new int[] {R.string.marechaussee_hunter,R.drawable.marechaussee_hunter_1,R.drawable.marechaussee_hunter_2,R.drawable.marechaussee_hunter_3,R.drawable.marechaussee_hunter_4,R.drawable.marechaussee_hunter_5};
            case "Golden Troupe" : return new int[] {R.string.golden_troupe,R.drawable.golden_troupe_1,R.drawable.golden_troupe_2,R.drawable.golden_troupe_3,R.drawable.golden_troupe_4,R.drawable.golden_troupe_5};

            default:
                String upcoming = str.toLowerCase().replace(" ","_");
                return new int[] {R.string.unknown,R.drawable.hu_tao_unknown,R.drawable.hu_tao_unknown,R.drawable.hu_tao_unknown,R.drawable.hu_tao_unknown,R.drawable.hu_tao_unknown};

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
            // add in 20230810
            case "marechaussee_hunter" : return "Marechaussee Hunter";
            case "golden_troupe" : return "Golden Troupe";

            default: return str;
        }
    }

    // Not continue to use anymore
    public int[] getTCGByName(String name){
        return getTCGByNameBase(name);
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
    public int[] getTCGByNameBase(String name){
        switch (name){
            //3.3.0
            case "Ganyu" : return new int[]{R.drawable.tcg_ganyu, R.string.tcg_ganyu};
            case "Kaeya" : return new int[]{R.drawable.tcg_kaeya, R.string.tcg_kaeya};
            case "Chongyun" : return new int[]{R.drawable.tcg_chongyun, R.string.tcg_chongyun};
            case "Kamisato Ayaka" : return new int[]{R.drawable.tcg_kamisato_ayaka, R.string.tcg_kamisato_ayaka};
            case "Xingqiu" : return new int[]{R.drawable.tcg_xingqiu, R.string.tcg_xingqiu};
            case "Mona" : return new int[]{R.drawable.tcg_mona, R.string.tcg_mona};
            case "Diluc" : return new int[]{R.drawable.tcg_diluc, R.string.tcg_diluc};
            case "Xiangling" : return new int[]{R.drawable.tcg_xiangling, R.string.tcg_xiangling};
            case "Bennett" : return new int[]{R.drawable.tcg_bennett, R.string.tcg_bennett};
            case "Yoimiya" : return new int[]{R.drawable.tcg_yoimiya, R.string.tcg_yoimiya};
            case "Fischl" : return new int[]{R.drawable.tcg_fischl, R.string.tcg_fischl};
            case "Razor" : return new int[]{R.drawable.tcg_razor, R.string.tcg_razor};
            case "Keqing" : return new int[]{R.drawable.tcg_keqing, R.string.tcg_keqing};
            case "Sucrose" : return new int[]{R.drawable.tcg_sucrose, R.string.tcg_sucrose};
            case "Jean" : return new int[]{R.drawable.tcg_jean, R.string.tcg_jean};
            case "Ningguang" : return new int[]{R.drawable.tcg_ningguang, R.string.tcg_ningguang};
            case "Noelle" : return new int[]{R.drawable.tcg_noelle, R.string.tcg_noelle};
            case "Collei" : return new int[]{R.drawable.tcg_collei, R.string.tcg_collei};
            case "Rhodeia of Loch" : return new int[]{R.drawable.tcg_rhodeia_of_loch, R.string.tcg_rhodeia_of_loch};
            case "Fatui Pyro Agent" : return new int[]{R.drawable.tcg_fatui_pyro_agent, R.string.tcg_fatui_pyro_agent};
            case "Maguu Kenki" : return new int[]{R.drawable.tcg_maguu_kenki, R.string.tcg_maguu_kenki};
            case "Stonehide Lawachurl" : return new int[]{R.drawable.tcg_stonehide_lawachurl, R.string.tcg_stonehide_lawachurl};
            case "Diona" : return new int[]{R.drawable.tcg_diona, R.string.tcg_diona};
            case "Cyno" : return new int[]{R.drawable.tcg_cyno, R.string.tcg_cyno};
            case "Barbara" : return new int[]{R.drawable.tcg_barbara, R.string.tcg_barbara};
            case "Mirror Maiden" : return new int[]{R.drawable.tcg_mirror_maiden, R.string.tcg_mirror_maiden};
            case "Jadeplume Terrorshroom" : return new int[]{R.drawable.tcg_jadeplume_terrorshroom, R.string.tcg_jadeplume_terrorshroom};
            case "Undivided Heart" : return new int[]{R.drawable.tcg_undivided_heart, R.string.tcg_undivided_heart};
            case "Cold-Blooded Strike" : return new int[]{R.drawable.tcg_cold_blooded_strike, R.string.tcg_cold_blooded_strike};
            case "Steady Breathing" : return new int[]{R.drawable.tcg_steady_breathing, R.string.tcg_steady_breathing};
            case "Kanten Senmyou Blessing" : return new int[]{R.drawable.tcg_kanten_senmyou_blessing, R.string.tcg_kanten_senmyou_blessing};
            case "The Scent Remained" : return new int[]{R.drawable.tcg_the_scent_remained, R.string.tcg_the_scent_remained};
            case "Prophecy of Submersion" : return new int[]{R.drawable.tcg_prophecy_of_submersion, R.string.tcg_prophecy_of_submersion};
            case "Flowing Flame" : return new int[]{R.drawable.tcg_flowing_flame, R.string.tcg_flowing_flame};
            case "Crossfire" : return new int[]{R.drawable.tcg_crossfire, R.string.tcg_crossfire};
            case "Grand Expectation" : return new int[]{R.drawable.tcg_grand_expectation, R.string.tcg_grand_expectation};
            case "Naganohara Meteor Swarm" : return new int[]{R.drawable.tcg_naganohara_meteor_swarm, R.string.tcg_naganohara_meteor_swarm};
            case "Stellar Predator" : return new int[]{R.drawable.tcg_stellar_predator, R.string.tcg_stellar_predator};
            case "Awakening" : return new int[]{R.drawable.tcg_awakening, R.string.tcg_awakening};
            case "Thundering Penance" : return new int[]{R.drawable.tcg_thundering_penance, R.string.tcg_thundering_penance};
            case "Chaotic Entropy" : return new int[]{R.drawable.tcg_chaotic_entropy, R.string.tcg_chaotic_entropy};
            case "Lands of Dandelion" : return new int[]{R.drawable.tcg_lands_of_dandelion, R.string.tcg_lands_of_dandelion};
            case "Strategic Reserve" : return new int[]{R.drawable.tcg_strategic_reserve, R.string.tcg_strategic_reserve};
            case "I Got Your Back" : return new int[]{R.drawable.tcg_i_got_your_back, R.string.tcg_i_got_your_back};
            case "Floral Sidewinder" : return new int[]{R.drawable.tcg_floral_sidewinder, R.string.tcg_floral_sidewinder};
            case "Streaming Surge" : return new int[]{R.drawable.tcg_streaming_surge, R.string.tcg_streaming_surge};
            case "Paid in Full" : return new int[]{R.drawable.tcg_paid_in_full, R.string.tcg_paid_in_full};
            case "Transcendent Automaton" : return new int[]{R.drawable.tcg_transcendent_automaton, R.string.tcg_transcendent_automaton};
            case "Stonehide Reforged" : return new int[]{R.drawable.tcg_stonehide_reforged, R.string.tcg_stonehide_reforged};
            case "Shaken, Not Purred" : return new int[]{R.drawable.tcg_shaken_not_purred, R.string.tcg_shaken_not_purred};
            case "Featherfall Judgment" : return new int[]{R.drawable.tcg_featherfall_judgment, R.string.tcg_featherfall_judgment};
            case "Glorious Season" : return new int[]{R.drawable.tcg_glorious_season, R.string.tcg_glorious_season};
            case "Mirror Cage" : return new int[]{R.drawable.tcg_mirror_cage, R.string.tcg_mirror_cage};
            case "Proliferating Spores" : return new int[]{R.drawable.tcg_proliferating_spores, R.string.tcg_proliferating_spores};
            case "Magic Guide" : return new int[]{R.drawable.tcg_magic_guide, R.string.tcg_magic_guide};
            case "Sacrificial Fragments" : return new int[]{R.drawable.tcg_sacrificial_fragments, R.string.tcg_sacrificial_fragments};
            case "Skyward Atlas" : return new int[]{R.drawable.tcg_skyward_atlas, R.string.tcg_skyward_atlas};
            case "Raven Bow" : return new int[]{R.drawable.tcg_raven_bow, R.string.tcg_raven_bow};
            case "Sacrificial Bow" : return new int[]{R.drawable.tcg_sacrificial_bow, R.string.tcg_sacrificial_bow};
            case "Skyward Harp" : return new int[]{R.drawable.tcg_skyward_harp, R.string.tcg_skyward_harp};
            case "White Iron Greatsword" : return new int[]{R.drawable.tcg_white_iron_greatsword, R.string.tcg_white_iron_greatsword};
            case "Sacrificial Greatsword" : return new int[]{R.drawable.tcg_sacrificial_greatsword, R.string.tcg_sacrificial_greatsword};
            case "Wolf's Gravestone" : return new int[]{R.drawable.tcg_wolfs_gravestone, R.string.tcg_wolfs_gravestone};
            case "White Tassel" : return new int[]{R.drawable.tcg_white_tassel, R.string.tcg_white_tassel};
            case "Lithic Spear" : return new int[]{R.drawable.tcg_lithic_spear, R.string.tcg_lithic_spear};
            case "Skyward Spine" : return new int[]{R.drawable.tcg_skyward_spine, R.string.tcg_skyward_spine};
            case "Traveler's Handy Sword" : return new int[]{R.drawable.tcg_travelers_handy_sword, R.string.tcg_travelers_handy_sword};
            case "Sacrificial Sword" : return new int[]{R.drawable.tcg_sacrificial_sword, R.string.tcg_sacrificial_sword};
            case "Aquila Favonia" : return new int[]{R.drawable.tcg_aquila_favonia, R.string.tcg_aquila_favonia};
            case "Adventurer's Bandana" : return new int[]{R.drawable.tcg_adventurers_bandana, R.string.tcg_adventurers_bandana};
            case "Lucky Dog's Silver Circlet" : return new int[]{R.drawable.tcg_lucky_dogs_silver_circlet, R.string.tcg_lucky_dogs_silver_circlet};
            case "Traveling Doctor's Handkerchief" : return new int[]{R.drawable.tcg_traveling_doctors_handkerchief, R.string.tcg_traveling_doctors_handkerchief};
            case "Gambler's Earrings" : return new int[]{R.drawable.tcg_gamblers_earrings, R.string.tcg_gamblers_earrings};
            case "Instructor's Cap" : return new int[]{R.drawable.tcg_instructors_cap, R.string.tcg_instructors_cap};
            case "Exile's Circlet" : return new int[]{R.drawable.tcg_exiles_circlet, R.string.tcg_exiles_circlet};
            case "Broken Rime's Echo" : return new int[]{R.drawable.tcg_broken_rimes_echo, R.string.tcg_broken_rimes_echo};
            case "Blizzard Strayer" : return new int[]{R.drawable.tcg_blizzard_strayer, R.string.tcg_blizzard_strayer};
            case "Wine-Stained Tricorne" : return new int[]{R.drawable.tcg_wine_stained_tricorne, R.string.tcg_wine_stained_tricorne};
            case "Heart of Depth" : return new int[]{R.drawable.tcg_heart_of_depth, R.string.tcg_heart_of_depth};
            case "Witch's Scorching Hat" : return new int[]{R.drawable.tcg_witchs_scorching_hat, R.string.tcg_witchs_scorching_hat};
            case "Crimson Witch of Flames" : return new int[]{R.drawable.tcg_crimson_witch_of_flames, R.string.tcg_crimson_witch_of_flames};
            case "Thunder Summoner's Crown" : return new int[]{R.drawable.tcg_thunder_summoners_crown, R.string.tcg_thunder_summoners_crown};
            case "Thundering Fury" : return new int[]{R.drawable.tcg_thundering_fury, R.string.tcg_thundering_fury};
            case "Viridescent Venerer's Diadem" : return new int[]{R.drawable.tcg_viridescent_venerers_diadem, R.string.tcg_viridescent_venerers_diadem};
            case "Viridescent Venerer" : return new int[]{R.drawable.tcg_viridescent_venerer, R.string.tcg_viridescent_venerer};
            case "Mask of Solitude Basalt" : return new int[]{R.drawable.tcg_mask_of_solitude_basalt, R.string.tcg_mask_of_solitude_basalt};
            case "Archaic Petra" : return new int[]{R.drawable.tcg_archaic_petra, R.string.tcg_archaic_petra};
            case "Laurel Coronet" : return new int[]{R.drawable.tcg_laurel_coronet, R.string.tcg_laurel_coronet};
            case "Deepwood Memories" : return new int[]{R.drawable.tcg_deepwood_memories, R.string.tcg_deepwood_memories};
            case "Liyue Harbor Wharf" : return new int[]{R.drawable.tcg_liyue_harbor_wharf, R.string.tcg_liyue_harbor_wharf};
            case "Knights of Favonius Library" : return new int[]{R.drawable.tcg_knights_of_favonius_library, R.string.tcg_knights_of_favonius_library};
            case "Jade Chamber" : return new int[]{R.drawable.tcg_jade_chamber, R.string.tcg_jade_chamber};
            case "Dawn Winery" : return new int[]{R.drawable.tcg_dawn_winery, R.string.tcg_dawn_winery};
            case "Wangshu Inn" : return new int[]{R.drawable.tcg_wangshu_inn, R.string.tcg_wangshu_inn};
            case "Favonius Cathedral" : return new int[]{R.drawable.tcg_favonius_cathedral, R.string.tcg_favonius_cathedral};
            case "Paimon" : return new int[]{R.drawable.tcg_paimon, R.string.tcg_paimon};
            case "Katheryne" : return new int[]{R.drawable.tcg_katheryne, R.string.tcg_katheryne};
            case "Timaeus" : return new int[]{R.drawable.tcg_timaeus, R.string.tcg_timaeus};
            case "Wagner" : return new int[]{R.drawable.tcg_wagner, R.string.tcg_wagner};
            case "Chef Mao" : return new int[]{R.drawable.tcg_chef_mao, R.string.tcg_chef_mao};
            case "Tubby" : return new int[]{R.drawable.tcg_tubby, R.string.tcg_tubby};
            case "Timmie" : return new int[]{R.drawable.tcg_timmie, R.string.tcg_timmie};
            case "Liben" : return new int[]{R.drawable.tcg_liben, R.string.tcg_liben};
            case "Chang the Ninth" : return new int[]{R.drawable.tcg_chang_the_ninth, R.string.tcg_chang_the_ninth};
            case "Ellin" : return new int[]{R.drawable.tcg_ellin, R.string.tcg_ellin};
            case "Iron Tongue Tian" : return new int[]{R.drawable.tcg_iron_tongue_tian, R.string.tcg_iron_tongue_tian};
            case "Liu Su" : return new int[]{R.drawable.tcg_liu_su, R.string.tcg_liu_su};
            case "Parametric Transformer" : return new int[]{R.drawable.tcg_parametric_transformer, R.string.tcg_parametric_transformer};
            case "NRE" : return new int[]{R.drawable.tcg_nre, R.string.tcg_nre};
            case "Elemental Resonance: Woven Ice" : return new int[]{R.drawable.tcg_elemental_resonance_woven_ice, R.string.tcg_elemental_resonance_woven_ice};
            case "Elemental Resonance: Shattering Ice" : return new int[]{R.drawable.tcg_elemental_resonance_shattering_ice, R.string.tcg_elemental_resonance_shattering_ice};
            case "Elemental Resonance: Woven Waters" : return new int[]{R.drawable.tcg_elemental_resonance_woven_waters, R.string.tcg_elemental_resonance_woven_waters};
            case "Elemental Resonance: Soothing Water" : return new int[]{R.drawable.tcg_elemental_resonance_soothing_water, R.string.tcg_elemental_resonance_soothing_water};
            case "Elemental Resonance: Woven Flames" : return new int[]{R.drawable.tcg_elemental_resonance_woven_flames, R.string.tcg_elemental_resonance_woven_flames};
            case "Elemental Resonance: Fervent Flames" : return new int[]{R.drawable.tcg_elemental_resonance_fervent_flames, R.string.tcg_elemental_resonance_fervent_flames};
            case "Elemental Resonance: Woven Thunder" : return new int[]{R.drawable.tcg_elemental_resonance_woven_thunder, R.string.tcg_elemental_resonance_woven_thunder};
            case "Elemental Resonance: High Voltage" : return new int[]{R.drawable.tcg_elemental_resonance_high_voltage, R.string.tcg_elemental_resonance_high_voltage};
            case "Elemental Resonance: Woven Winds" : return new int[]{R.drawable.tcg_elemental_resonance_woven_winds, R.string.tcg_elemental_resonance_woven_winds};
            case "Elemental Resonance: Impetuous Winds" : return new int[]{R.drawable.tcg_elemental_resonance_impetuous_winds, R.string.tcg_elemental_resonance_impetuous_winds};
            case "Elemental Resonance: Woven Stone" : return new int[]{R.drawable.tcg_elemental_resonance_woven_stone, R.string.tcg_elemental_resonance_woven_stone};
            case "Elemental Resonance: Enduring Rock" : return new int[]{R.drawable.tcg_elemental_resonance_enduring_rock, R.string.tcg_elemental_resonance_enduring_rock};
            case "Elemental Resonance: Woven Weeds" : return new int[]{R.drawable.tcg_elemental_resonance_woven_weeds, R.string.tcg_elemental_resonance_woven_weeds};
            case "Elemental Resonance: Sprawling Greenery" : return new int[]{R.drawable.tcg_elemental_resonance_sprawling_greenery, R.string.tcg_elemental_resonance_sprawling_greenery};
            case "The Bestest Travel Companion!" : return new int[]{R.drawable.tcg_the_bestest_travel_companion, R.string.tcg_the_bestest_travel_companion};
            case "Changing Shifts" : return new int[]{R.drawable.tcg_changing_shifts, R.string.tcg_changing_shifts};
            case "Toss-Up" : return new int[]{R.drawable.tcg_toss_up, R.string.tcg_toss_up};
            case "Strategize" : return new int[]{R.drawable.tcg_strategize, R.string.tcg_strategize};
            case "I Haven't Lost Yet!" : return new int[]{R.drawable.tcg_i_havent_lost_yet, R.string.tcg_i_havent_lost_yet};
            case "Leave it to Me!" : return new int[]{R.drawable.tcg_leave_it_to_me, R.string.tcg_leave_it_to_me};
            case "When the Crane Returned" : return new int[]{R.drawable.tcg_when_the_crane_returned, R.string.tcg_when_the_crane_returned};
            case "Starsigns" : return new int[]{R.drawable.tcg_starsigns, R.string.tcg_starsigns};
            case "Calx's Arts" : return new int[]{R.drawable.tcg_calxs_arts, R.string.tcg_calxs_arts};
            case "Master of Weaponry" : return new int[]{R.drawable.tcg_master_of_weaponry, R.string.tcg_master_of_weaponry};
            case "Blessing of the Divine Relic's Installation" : return new int[]{R.drawable.tcg_blessing_of_the_divine_relics_installation, R.string.tcg_blessing_of_the_divine_relics_installation};
            case "Quick Knit" : return new int[]{R.drawable.tcg_quick_knit, R.string.tcg_quick_knit};
            case "Send Off" : return new int[]{R.drawable.tcg_send_off, R.string.tcg_send_off};
            case "Guardian's Oath" : return new int[]{R.drawable.tcg_guardians_oath, R.string.tcg_guardians_oath};
            case "Abyssal Summons" : return new int[]{R.drawable.tcg_abyssal_summons, R.string.tcg_abyssal_summons};
            case "Jueyun Guoba" : return new int[]{R.drawable.tcg_jueyun_guoba, R.string.tcg_jueyun_guoba};
            case "Adeptus' Temptation" : return new int[]{R.drawable.tcg_adeptus_temptation, R.string.tcg_adeptus_temptation};
            case "Lotus Flower Crisp" : return new int[]{R.drawable.tcg_lotus_flower_crisp, R.string.tcg_lotus_flower_crisp};
            case "Northern Smoked Chicken" : return new int[]{R.drawable.tcg_northern_smoked_chicken, R.string.tcg_northern_smoked_chicken};
            case "Sweet Madame" : return new int[]{R.drawable.tcg_sweet_madame, R.string.tcg_sweet_madame};
            case "Mondstadt Hash Brown" : return new int[]{R.drawable.tcg_mondstadt_hash_brown, R.string.tcg_mondstadt_hash_brown};
            case "Mushroom Pizza" : return new int[]{R.drawable.tcg_mushroom_pizza, R.string.tcg_mushroom_pizza};
            case "Minty Meat Rolls" : return new int[]{R.drawable.tcg_minty_meat_rolls, R.string.tcg_minty_meat_rolls};
            case "Origin" : return new int[]{R.drawable.tcg_origin, R.string.tcg_origin};
            case "Mondstadt" : return new int[]{R.drawable.tcg_mondstadt, R.string.tcg_mondstadt};
            case "Liyue" : return new int[]{R.drawable.tcg_liyue, R.string.tcg_liyue};
            case "Inazuma" : return new int[]{R.drawable.tcg_inazuma, R.string.tcg_inazuma};
            case "Sumeru" : return new int[]{R.drawable.tcg_sumeru, R.string.tcg_sumeru};
            case "Legend" : return new int[]{R.drawable.tcg_legend, R.string.tcg_legend};
            case "Adept" : return new int[]{R.drawable.tcg_adept, R.string.tcg_adept};
            case "Hydro Crystal Chunk" : return new int[]{R.drawable.tcg_hydro_crystal_chunk, R.string.tcg_hydro_crystal_chunk};
            case "Magical Crystal Chunk" : return new int[]{R.drawable.tcg_magical_crystal_chunk, R.string.tcg_magical_crystal_chunk};
            case "Amethyst Lump" : return new int[]{R.drawable.tcg_amethyst_lump, R.string.tcg_amethyst_lump};
            case "Flaming Flower Stamen" : return new int[]{R.drawable.tcg_flaming_flower_stamen, R.string.tcg_flaming_flower_stamen};
            case "Mist Flower Corolla" : return new int[]{R.drawable.tcg_mist_flower_corolla, R.string.tcg_mist_flower_corolla};
            case "Electro Crystal" : return new int[]{R.drawable.tcg_electro_crystal, R.string.tcg_electro_crystal};
            case "Dandelion Seed" : return new int[]{R.drawable.tcg_dandelion_seed, R.string.tcg_dandelion_seed};
            case "Cecilia" : return new int[]{R.drawable.tcg_cecilia, R.string.tcg_cecilia};
            case "Glaze Lily" : return new int[]{R.drawable.tcg_glaze_lily, R.string.tcg_glaze_lily};
            case "Dendrobium" : return new int[]{R.drawable.tcg_dendrobium, R.string.tcg_dendrobium};
            //add in 20230118 - 3.4.0
            case "Klee" : return new int[]{R.drawable.tcg_klee, R.string.tcg_klee};
            case "Beidou" : return new int[]{R.drawable.tcg_beidou, R.string.tcg_beidou};
            case "Lightning Storm" : return new int[]{R.drawable.tcg_lightning_storm, R.string.tcg_lightning_storm};
            case "Pounding Surprise" : return new int[]{R.drawable.tcg_pounding_surprise, R.string.tcg_pounding_surprise};
            //add in 20230226 - 3.5.0
            case "Eula" : return new int[]{R.drawable.tcg_eula, R.string.tcg_eula};
            case "Sangonomiya Kokomi" : return new int[]{R.drawable.tcg_sangonomiya_kokomi, R.string.tcg_sangonomiya_kokomi};
            case "Kujou Sara" : return new int[]{R.drawable.tcg_kujou_sara, R.string.tcg_kujou_sara};
            case "Wellspring of War-Lust" : return new int[]{R.drawable.tcg_wellspring_of_war_lust, R.string.tcg_wellspring_of_war_lust};
            case "Tamanooya's Casket" : return new int[]{R.drawable.tcg_tamanooyas_casket, R.string.tcg_tamanooyas_casket};
            case "Sin of Pride" : return new int[]{R.drawable.tcg_sin_of_pride, R.string.tcg_sin_of_pride};
            case "Ornate Kabuto" : return new int[]{R.drawable.tcg_ornate_kabuto, R.string.tcg_ornate_kabuto};
            case "General's Ancient Helm" : return new int[]{R.drawable.tcg_generals_ancient_helm, R.string.tcg_generals_ancient_helm};
            //add in 20230416 - 3.6.0
            case "Kamisato Ayato" : return new int[]{R.drawable.tcg_kamisato_ayato, R.string.tcg_kamisato_ayato};
            case "Arataki Itto" : return new int[]{R.drawable.tcg_arataki_itto, R.string.tcg_arataki_itto};
            case "Tighnari" : return new int[]{R.drawable.tcg_tighnari, R.string.tcg_tighnari};
            case "Keen Sight" : return new int[]{R.drawable.tcg_keen_sight, R.string.tcg_keen_sight};
            case "Kyouka Fuushi" : return new int[]{R.drawable.tcg_kyouka_fuushi, R.string.tcg_kyouka_fuushi};
            case "Arataki Ichiban" : return new int[]{R.drawable.tcg_arataki_ichiban, R.string.tcg_arataki_ichiban};
            case "Favonius Sword" : return new int[]{R.drawable.tcg_favonius_sword, R.string.tcg_favonius_sword};
            case "Grand Narukami Shrine" : return new int[]{R.drawable.tcg_grand_narukami_shrine, R.string.tcg_grand_narukami_shrine};
            //add in 20230811 - 3.7.0
            case "Amber" : return new int[]{R.drawable.tcg_amber, R.string.tcg_amber};
            case "Tartaglia" : return new int[]{R.drawable.tcg_tartaglia, R.string.tcg_tartaglia};
            case "Hu Tao" : return new int[]{R.drawable.tcg_hu_tao, R.string.tcg_hu_tao};
            case "Raiden Shogun" : return new int[]{R.drawable.tcg_raiden_shogun, R.string.tcg_raiden_shogun};
            case "Yae Miko" : return new int[]{R.drawable.tcg_yae_miko, R.string.tcg_yae_miko};
            case "Venti" : return new int[]{R.drawable.tcg_venti, R.string.tcg_venti};
            case "Xiao" : return new int[]{R.drawable.tcg_xiao, R.string.tcg_xiao};
            case "Zhongli" : return new int[]{R.drawable.tcg_zhongli, R.string.tcg_zhongli};
            case "Nahida" : return new int[]{R.drawable.tcg_nahida, R.string.tcg_nahida};
            case "Fatui Cryo Cicin Mage" : return new int[]{R.drawable.tcg_fatui_cryo_cicin_mage, R.string.tcg_fatui_cryo_cicin_mage};
            case "Abyss Lector: Fathomless Flames" : return new int[]{R.drawable.tcg_abyss_lector_fathomless_flames, R.string.tcg_abyss_lector_fathomless_flames};
            case "Electro Hypostasis" : return new int[]{R.drawable.tcg_electro_hypostasis, R.string.tcg_electro_hypostasis};
            case "Shenhe" : return new int[]{R.drawable.tcg_shenhe, R.string.tcg_shenhe};
            case "Bunny Triggered" : return new int[]{R.drawable.tcg_bunny_triggered, R.string.tcg_bunny_triggered};
            case "Embrace of Winds" : return new int[]{R.drawable.tcg_embrace_of_winds, R.string.tcg_embrace_of_winds};
            case "Conqueror of Evil: Guardian Yaksha" : return new int[]{R.drawable.tcg_conqueror_of_evil_guardian_yaksha, R.string.tcg_conqueror_of_evil_guardian_yaksha};
            case "Dominance of Earth" : return new int[]{R.drawable.tcg_dominance_of_earth, R.string.tcg_dominance_of_earth};
            case "Absorbing Prism" : return new int[]{R.drawable.tcg_absorbing_prism, R.string.tcg_absorbing_prism};
            case "Mystical Abandon" : return new int[]{R.drawable.tcg_mystical_abandon, R.string.tcg_mystical_abandon};
            case "Abyssal Mayhem: Hydrospout" : return new int[]{R.drawable.tcg_abyssal_mayhem_hydrospout, R.string.tcg_abyssal_mayhem_hydrospout};
            case "Sanguine Rouge" : return new int[]{R.drawable.tcg_sanguine_rouge, R.string.tcg_sanguine_rouge};
            case "Wishes Unnumbered" : return new int[]{R.drawable.tcg_wishes_unnumbered, R.string.tcg_wishes_unnumbered};
            case "The Shrine's Sacred Shade" : return new int[]{R.drawable.tcg_the_shrines_sacred_shade, R.string.tcg_the_shrines_sacred_shade};
            case "The Seed of Stored Knowledge" : return new int[]{R.drawable.tcg_the_seed_of_stored_knowledge, R.string.tcg_the_seed_of_stored_knowledge};
            case "Cicin's Cold Glare" : return new int[]{R.drawable.tcg_cicins_cold_glare, R.string.tcg_cicins_cold_glare};
            case "Embers Rekindled" : return new int[]{R.drawable.tcg_embers_rekindled, R.string.tcg_embers_rekindled};
            case "Amos' Bow" : return new int[]{R.drawable.tcg_amos_bow, R.string.tcg_amos_bow};
            case "A Thousand Floating Dreams" : return new int[]{R.drawable.tcg_a_thousand_floating_dreams, R.string.tcg_a_thousand_floating_dreams};
            case "Elegy for the End" : return new int[]{R.drawable.tcg_elegy_for_the_end, R.string.tcg_elegy_for_the_end};
            case "Skyward Pride" : return new int[]{R.drawable.tcg_skyward_pride, R.string.tcg_skyward_pride};
            case "The Bell" : return new int[]{R.drawable.tcg_the_bell, R.string.tcg_the_bell};
            case "Vortex Vanquisher" : return new int[]{R.drawable.tcg_vortex_vanquisher, R.string.tcg_vortex_vanquisher};
            case "Engulfing Lightning" : return new int[]{R.drawable.tcg_engulfing_lightning, R.string.tcg_engulfing_lightning};
            case "Skyward Blade" : return new int[]{R.drawable.tcg_skyward_blade, R.string.tcg_skyward_blade};
            case "Emblem of Severed Fate" : return new int[]{R.drawable.tcg_emblem_of_severed_fate, R.string.tcg_emblem_of_severed_fate};
            case "Tenacity of the Millelith" : return new int[]{R.drawable.tcg_tenacity_of_the_millelith, R.string.tcg_tenacity_of_the_millelith};
            case "Thundering Poise" : return new int[]{R.drawable.tcg_thundering_poise, R.string.tcg_thundering_poise};
            case "Vermillion Hereafter" : return new int[]{R.drawable.tcg_vermillion_hereafter, R.string.tcg_vermillion_hereafter};
            case "Capricious Visage" : return new int[]{R.drawable.tcg_capricious_visage, R.string.tcg_capricious_visage};
            case "Shimenawa's Reminiscence" : return new int[]{R.drawable.tcg_shimenawas_reminiscence, R.string.tcg_shimenawas_reminiscence};
            case "Tenshukaku" : return new int[]{R.drawable.tcg_tenshukaku, R.string.tcg_tenshukaku};
            case "Sangonomiya Shrine" : return new int[]{R.drawable.tcg_sangonomiya_shrine, R.string.tcg_sangonomiya_shrine};
            case "Sumeru City" : return new int[]{R.drawable.tcg_sumeru_city, R.string.tcg_sumeru_city};
            case "Vanarana" : return new int[]{R.drawable.tcg_vanarana, R.string.tcg_vanarana};
            case "Chinju Forest" : return new int[]{R.drawable.tcg_chinju_forest, R.string.tcg_chinju_forest};
            case "Hanachirusato" : return new int[]{R.drawable.tcg_hanachirusato, R.string.tcg_hanachirusato};
            case "Kid Kujirai" : return new int[]{R.drawable.tcg_kid_kujirai, R.string.tcg_kid_kujirai};
            case "Xudong" : return new int[]{R.drawable.tcg_xudong, R.string.tcg_xudong};
            case "Dunyarzad" : return new int[]{R.drawable.tcg_dunyarzad, R.string.tcg_dunyarzad};
            case "Rana" : return new int[]{R.drawable.tcg_rana, R.string.tcg_rana};
            case "Red Feather Fan" : return new int[]{R.drawable.tcg_red_feather_fan, R.string.tcg_red_feather_fan};
            case "Treasure-Seeking Seelie" : return new int[]{R.drawable.tcg_treasure_seeking_seelie, R.string.tcg_treasure_seeking_seelie};
            case "Wind and Freedom" : return new int[]{R.drawable.tcg_wind_and_freedom, R.string.tcg_wind_and_freedom};
            case "Stone and Contracts" : return new int[]{R.drawable.tcg_stone_and_contracts, R.string.tcg_stone_and_contracts};
            case "Thunder and Eternity" : return new int[]{R.drawable.tcg_thunder_and_eternity, R.string.tcg_thunder_and_eternity};
            case "Nature and Wisdom" : return new int[]{R.drawable.tcg_nature_and_wisdom, R.string.tcg_nature_and_wisdom};
            case "Fatui Conspiracy" : return new int[]{R.drawable.tcg_fatui_conspiracy, R.string.tcg_fatui_conspiracy};
            case "Plunging Strike" : return new int[]{R.drawable.tcg_plunging_strike, R.string.tcg_plunging_strike};
            case "Heavy Strike" : return new int[]{R.drawable.tcg_heavy_strike, R.string.tcg_heavy_strike};
            case "The Legend of Vennessa" : return new int[]{R.drawable.tcg_the_legend_of_vennessa, R.string.tcg_the_legend_of_vennessa};
            case "Friendship Eternal" : return new int[]{R.drawable.tcg_friendship_eternal, R.string.tcg_friendship_eternal};
            case "Teyvat Fried Egg" : return new int[]{R.drawable.tcg_teyvat_fried_egg, R.string.tcg_teyvat_fried_egg};
            case "Sashimi Platter" : return new int[]{R.drawable.tcg_sashimi_platter, R.string.tcg_sashimi_platter};
            case "Tandoori Roast Chicken" : return new int[]{R.drawable.tcg_tandoori_roast_chicken, R.string.tcg_tandoori_roast_chicken};
            case "Butter Crab" : return new int[]{R.drawable.tcg_butter_crab, R.string.tcg_butter_crab};
            case "Treasures of the Deck" : return new int[]{R.drawable.tcg_treasures_of_the_deck, R.string.tcg_treasures_of_the_deck};
            //add in 20230811 - 3.8.0
            case "Candace" : return new int[]{R.drawable.tcg_candace, R.string.tcg_candace};
            case "Yanfei" : return new int[]{R.drawable.tcg_yanfei, R.string.tcg_yanfei};
            case "Kaedehara Kazuha" : return new int[]{R.drawable.tcg_kaedehara_kazuha, R.string.tcg_kaedehara_kazuha};
            case "The Overflow" : return new int[]{R.drawable.tcg_the_overflow, R.string.tcg_the_overflow};
            case "Right of Final Interpretation" : return new int[]{R.drawable.tcg_right_of_final_interpretation, R.string.tcg_right_of_final_interpretation};
            case "Poetics of Fuubutsu" : return new int[]{R.drawable.tcg_poetics_of_fuubutsu, R.string.tcg_poetics_of_fuubutsu};
            case "Fruit of Fulfillment" : return new int[]{R.drawable.tcg_fruit_of_fulfillment, R.string.tcg_fruit_of_fulfillment};
            case "Master Zhang" : return new int[]{R.drawable.tcg_master_zhang, R.string.tcg_master_zhang};
            case "Ancient Courtyard" : return new int[]{R.drawable.tcg_ancient_courtyard, R.string.tcg_ancient_courtyard};
            case "Covenant of Rock" : return new int[]{R.drawable.tcg_covenant_of_rock, R.string.tcg_covenant_of_rock};
            case "Rhythm of the Great Dream" : return new int[]{R.drawable.tcg_rhythm_of_the_great_dream, R.string.tcg_rhythm_of_the_great_dream};
            //add in 20230811 - 4.0.0
            case "Albedo" : return new int[]{R.drawable.tcg_albedo, R.string.tcg_albedo};
            case "Qiqi" : return new int[]{R.drawable.tcg_qiqi, R.string.tcg_qiqi};
            case "Lisa" : return new int[]{R.drawable.tcg_lisa, R.string.tcg_lisa};
            case "Descent of Divinity" : return new int[]{R.drawable.tcg_descent_of_divinity, R.string.tcg_descent_of_divinity};
            case "Rite of Resurrection" : return new int[]{R.drawable.tcg_rite_of_resurrection, R.string.tcg_rite_of_resurrection};
            case "Pulsating Witch" : return new int[]{R.drawable.tcg_pulsating_witch, R.string.tcg_pulsating_witch};
            case "King's Squire" : return new int[]{R.drawable.tcg_kings_squire, R.string.tcg_kings_squire};
            case "Golden House" : return new int[]{R.drawable.tcg_golden_house, R.string.tcg_golden_house};
            case "Setaria" : return new int[]{R.drawable.tcg_setaria, R.string.tcg_setaria};
            case "Joyous Celebration" : return new int[]{R.drawable.tcg_joyous_celebration, R.string.tcg_joyous_celebration};
            case "Where Is the Unseen Razor?" : return new int[]{R.drawable.tcg_where_is_the_unseen_razor, R.string.tcg_where_is_the_unseen_razor};
            case "Fontaine" : return new int[]{R.drawable.tcg_fontaine, R.string.tcg_fontaine};

            default: return new int[]{R.drawable.hu_tao_unknown, R.string.unknown};
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
    public int[] getCharByName(String name, Context context){
        if(context.getSharedPreferences("user_info",MODE_PRIVATE).getBoolean("isCharChangeEventSuit",false) == true){
            switch (name) {
                case "Ningguang": return new int[]{R.drawable.ningguang_suit1_gacha_splash, R.string.ningguang, R.drawable.ningguang_suit1_gacha_splash, R.drawable.ningguang_suit1_ico, R.drawable.ningguang_card};
                case "Barbara": return new int[]{R.drawable.barbara_suit1_gacha_splash, R.string.barbara, R.drawable.barbara_suit1_gacha_splash, R.drawable.barbara_suit1_ico, R.drawable.barbara_card};
                case "Jean": return new int[]{R.drawable.jean_suit2_gacha_splash, R.string.jean, R.drawable.jean_suit2_gacha_splash, R.drawable.jean_suit2_ico, R.drawable.jean_card};
                case "Keqing": return new int[]{R.drawable.keqing_suit1_gacha_splash, R.string.keqing, R.drawable.keqing_suit1_gacha_splash, R.drawable.keqing_suit1_ico, R.drawable.keqing_card};
                case "Diluc": return new int[]{R.drawable.diluc_suit1_gacha_splash, R.string.diluc, R.drawable.diluc_suit1_gacha_splash, R.drawable.diluc_suit1_ico, R.drawable.diluc_card};
                case "Fischl": return new int[]{R.drawable.fischl_suit1_gacha_splash, R.string.fischl, R.drawable.fischl_suit1_gacha_splash, R.drawable.fischl_suit1_ico, R.drawable.fischl_card};
                case "Kamisato Ayaka": return new int[]{R.drawable.kamisato_ayaka_suit1_gacha_splash, R.string.kamisato_ayaka, R.drawable.kamisato_ayaka_suit1_gacha_splash, R.drawable.kamisato_ayaka_suit1_ico, R.drawable.kamisato_ayaka_card};
                case "Lisa": return new int[]{R.drawable.lisa_suit1_gacha_splash, R.string.lisa, R.drawable.lisa_suit1_gacha_splash, R.drawable.lisa_suit1_ico, R.drawable.lisa_card};
                default: return getCharByName_BASE(name, context);
            }
        }else{
            return getCharByName_BASE(name,context);
        }
    }

    public int[] getCharByName_BASE(String name, Context context){
        switch (name) {
            case "Lynette": return new int[]{R.drawable.lynette_full, R.string.lynette, R.drawable.lynette_gacha_splash, R.drawable.lynette_ico, R.drawable.lynette_card};
            case "Lyney": return new int[]{R.drawable.lyney_full, R.string.lyney, R.drawable.lyney_gacha_splash, R.drawable.lyney_ico, R.drawable.lyney_card};
            case "Freminet": return new int[]{R.drawable.freminet_full, R.string.freminet, R.drawable.freminet_gacha_splash, R.drawable.freminet_ico, R.drawable.freminet_card};

            case "Kirara": return new int[]{R.drawable.kirara_full, R.string.kirara, R.drawable.kirara_gacha_splash, R.drawable.kirara_ico, R.drawable.kirara_card};

            case "Baizhu": return new int[]{R.drawable.baizhu_full, R.string.baizhu, R.drawable.baizhu_gacha_splash, R.drawable.baizhu_ico, R.drawable.baizhu_card};
            case "Kaveh": return new int[]{R.drawable.kaveh_full, R.string.kaveh, R.drawable.kaveh_gacha_splash, R.drawable.kaveh_ico, R.drawable.kaveh_card};

            case "Dehya": return new int[]{R.drawable.dehya_full, R.string.dehya, R.drawable.dehya_gacha_splash, R.drawable.dehya_ico, R.drawable.dehya_card};
            case "Mika": return new int[]{R.drawable.mika_full, R.string.mika, R.drawable.mika_gacha_splash, R.drawable.mika_ico, R.drawable.mika_card};

            case "Alhaitham": return new int[]{R.drawable.alhaitham_full, R.string.alhaitham, R.drawable.alhaitham_gacha_splash, R.drawable.alhaitham_ico, R.drawable.alhaitham_card};
            case "Yaoyao": return new int[]{R.drawable.yaoyao_full, R.string.yaoyao, R.drawable.yaoyao_gacha_splash, R.drawable.yaoyao_ico, R.drawable.yaoyao_card};

            case "Faruzan": return new int[]{R.drawable.faruzan_full, R.string.faruzan, R.drawable.faruzan_gacha_splash, R.drawable.faruzan_ico, R.drawable.faruzan_card};
            case "Wanderer": return new int[]{R.drawable.wanderer_full, R.string.wanderer, R.drawable.wanderer_gacha_splash, R.drawable.wanderer_ico, R.drawable.wanderer_card};

            case "Nahida": return new int[]{R.drawable.nahida_full, R.string.nahida, R.drawable.nahida_gacha_splash, R.drawable.nahida_ico, R.drawable.nahida_card};
            case "Layla": return new int[]{R.drawable.layla_full, R.string.layla, R.drawable.layla_gacha_splash, R.drawable.layla_ico, R.drawable.layla_card};

            case "Nilou": return new int[]{R.drawable.nilou_full, R.string.nilou, R.drawable.nilou_gacha_splash, R.drawable.nilou_ico, R.drawable.nilou_card};
            case "Cyno": return new int[]{R.drawable.cyno_full, R.string.cyno, R.drawable.cyno_gacha_splash, R.drawable.cyno_ico, R.drawable.cyno_card};
            case "Candace": return new int[]{R.drawable.candace_full, R.string.candace, R.drawable.candace_gacha_splash, R.drawable.candace_ico, R.drawable.candace_card};

            case "Collei": return new int[]{R.drawable.collei_full, R.string.collei, R.drawable.collei_gacha_splash, R.drawable.collei_ico, R.drawable.collei_card};
            case "Dori": return new int[]{R.drawable.dori_full, R.string.dori, R.drawable.dori_gacha_splash, R.drawable.dori_ico, R.drawable.dori_card};
            case "Tighnari": return new int[]{R.drawable.tighnari_full, R.string.tighnari, R.drawable.tighnari_gacha_splash, R.drawable.tighnari_ico, R.drawable.tighnari_card};
            case "Shikanoin Heizou": return new int[]{R.drawable.shikanoin_heizou_full, R.string.shikanoin_heizou, R.drawable.shikanoin_heizou_gacha_splash, R.drawable.shikanoin_heizou_ico, R.drawable.shikanoin_heizou_card};
            case "Kuki Shinobu": return new int[]{R.drawable.kuki_shinobu_full, R.string.kuki_shinobu, R.drawable.kuki_shinobu_gacha_splash, R.drawable.kuki_shinobu_ico, R.drawable.kuki_shinobu_card};
            case "Yelan": return new int[]{R.drawable.yelan_full, R.string.yelan, R.drawable.yelan_gacha_splash, R.drawable.yelan_ico, R.drawable.yelan_card};
            case "Kamisato Ayato": return new int[]{R.drawable.kamisato_ayato_full, R.string.kamisato_ayato, R.drawable.kamisato_ayato_gacha_splash, R.drawable.kamisato_ayato_ico, R.drawable.kamisato_ayato_card};
            case "Yae Miko": return new int[]{R.drawable.yae_miko_full, R.string.yae_miko, R.drawable.yae_miko_gacha_splash, R.drawable.yae_miko_ico, R.drawable.yae_miko_card};
            case "Shenhe": return new int[]{R.drawable.shenhe_full, R.string.shenhe, R.drawable.shenhe_gacha_splash, R.drawable.shenhe_ico, R.drawable.shenhe_card};
            case "Yun Jin": return new int[]{R.drawable.yun_jin_full, R.string.yun_jin, R.drawable.yun_jin_gacha_splash, R.drawable.yun_jin_ico, R.drawable.yun_jin_card};
            case "Gorou": return new int[]{R.drawable.gorou_full, R.string.gorou, R.drawable.gorou_gacha_splash, R.drawable.gorou_ico, R.drawable.gorou_card};
            case "Arataki Itto": return new int[]{R.drawable.arataki_itto_full, R.string.arataki_itto, R.drawable.arataki_itto_gacha_splash, R.drawable.arataki_itto_ico, R.drawable.arataki_itto_card};
            case "Thoma": return new int[]{R.drawable.thoma_full, R.string.thoma, R.drawable.thoma_gacha_splash, R.drawable.thoma_ico, R.drawable.thoma_card};
            case "Sangonomiya Kokomi": return new int[]{R.drawable.sangonomiya_kokomi_full, R.string.sangonomiya_kokomi, R.drawable.sangonomiya_kokomi_gacha_splash, R.drawable.sangonomiya_kokomi_ico, R.drawable.sangonomiya_kokomi_card};
            case "Aloy": return new int[]{R.drawable.aloy_full, R.string.aloy, R.drawable.aloy_gacha_splash, R.drawable.aloy_ico, R.drawable.aloy_card};
            case "Kujou Sara": return new int[]{R.drawable.kujou_sara_full, R.string.kujou_sara, R.drawable.kujor_sara_gacha_splash, R.drawable.kujou_sara_ico, R.drawable.kujor_sara_card};
            case "Raiden Shogun": return new int[]{R.drawable.raiden_shogun_full, R.string.raiden_shogun, R.drawable.raiden_shogun_gacha_splash, R.drawable.raiden_shogun_ico, R.drawable.raiden_shogun_card};
            case "Sayu": return new int[]{R.drawable.sayu_full, R.string.sayu, R.drawable.sayu_gacha_splash, R.drawable.sayu_ico, R.drawable.sayu_card};
            case "Yoimiya": return new int[]{R.drawable.yoimiya_full, R.string.yoimiya, R.drawable.yoimiya_gacha_splash, R.drawable.yoimiya_ico, R.drawable.yoimiya_card};
            case "Kamisato Ayaka": return new int[]{R.drawable.ayaka_full, R.string.kamisato_ayaka, R.drawable.kamisato_ayaka_gacha_splash, R.drawable.ayaka_ico, R.drawable.kamisato_ayaka_card};
            case "Kaedehara Kazuha": return new int[]{R.drawable.kazuha_full, R.string.kaedehara_kazuha, R.drawable.kaedehara_kazuha_gacha_splash, R.drawable.kazuha_ico, R.drawable.kaedehara_kazuha_card};
            case "Yanfei": return new int[]{R.drawable.yanfei_full, R.string.yanfei, R.drawable.yanfei_gacha_splash, R.drawable.yanfei_ico, R.drawable.yanfei_card};
            case "Eula": return new int[]{R.drawable.eula_full, R.string.eula, R.drawable.eula_gacha_splash, R.drawable.eula_ico, R.drawable.eula_card};
            case "Rosaria": return new int[]{R.drawable.rosaria_full, R.string.rosaria, R.drawable.rosaria_gacha_splash, R.drawable.rosaria_ico, R.drawable.rosaria_card};
            case "Xiao": return new int[]{R.drawable.xiao_full, R.string.xiao, R.drawable.xiao_gacha_splash, R.drawable.xiao_ico, R.drawable.xiao_card};
            case "Hu Tao": return new int[]{R.drawable.hu_tao_full, R.string.hu_tao, R.drawable.hu_tao_gacha_splash, R.drawable.hu_tao_ico, R.drawable.hu_tao_card};
            case "Ganyu": return new int[]{R.drawable.ganyu_full, R.string.ganyu, R.drawable.ganyu_gacha_splash, R.drawable.ganyu_ico, R.drawable.ganyu_card};
            case "Albedo": return new int[]{R.drawable.albedo_full, R.string.albedo, R.drawable.albedo_gacha_splash, R.drawable.albedo_ico, R.drawable.albedo_card};
            case "Zhongli": return new int[]{R.drawable.zhongli_full, R.string.zhongli, R.drawable.zhongli_gacha_splash, R.drawable.zhongli_ico, R.drawable.zhongli_card};
            case "Xinyan": return new int[]{R.drawable.xinyan_full, R.string.xinyan, R.drawable.xinyan_gacha_splash, R.drawable.xinyan_ico, R.drawable.xinyan_card};
            case "Tartaglia": return new int[]{R.drawable.tartaglia_full, R.string.tartaglia, R.drawable.tartaglia_gacha_splash, R.drawable.tartaglia_ico, R.drawable.tartaglia_card};
            case "Diona": return new int[]{R.drawable.diona_full, R.string.diona, R.drawable.diona_gacha_splash, R.drawable.diona_ico, R.drawable.diona_card};
            case "Xingqiu": return new int[]{R.drawable.xingqiu_full, R.string.xingqiu, R.drawable.xingqiu_gacha_splash, R.drawable.xingqiu_ico, R.drawable.xingqiu_card};
            case "Xiangling": return new int[]{R.drawable.xiangling_full, R.string.xiangling, R.drawable.xiangling_gacha_splash, R.drawable.xiangling_ico, R.drawable.xiangling_card};
            case "Venti": return new int[]{R.drawable.venti_full, R.string.venti, R.drawable.venti_gacha_splash, R.drawable.venti_ico, R.drawable.venti_card};
            case "Sucrose": return new int[]{R.drawable.sucrose_full, R.string.sucrose, R.drawable.sucrose_gacha_splash, R.drawable.sucrose_ico, R.drawable.sucrose_card};
            case "Razor": return new int[]{R.drawable.razor_full, R.string.razor, R.drawable.razor_gacha_splash, R.drawable.razor_ico, R.drawable.razor_card};
            case "Qiqi": return new int[]{R.drawable.qiqi_full, R.string.qiqi, R.drawable.qiqi_gacha_splash, R.drawable.qiqi_ico, R.drawable.qiqi_card};
            case "Noelle": return new int[]{R.drawable.noelle_full, R.string.noelle, R.drawable.noelle_gacha_splash, R.drawable.noelle_ico, R.drawable.noelle_card};
            case "Ningguang": return new int[]{R.drawable.ningguang_full, R.string.ningguang, R.drawable.ningguang_gacha_splash, R.drawable.ningguang_ico, R.drawable.ningguang_card};
            case "Mona": return new int[]{R.drawable.mona_full, R.string.mona, R.drawable.mona_gacha_splash, R.drawable.mona_ico, R.drawable.mona_card};
            case "Lisa": return new int[]{R.drawable.lisa_full, R.string.lisa, R.drawable.lisa_gacha_splash, R.drawable.lisa_ico, R.drawable.lisa_card};
            case "Klee": return new int[]{R.drawable.klee_full, R.string.klee, R.drawable.klee_gacha_splash, R.drawable.klee_ico, R.drawable.klee_card};
            case "Keqing": return new int[]{R.drawable.keqing_full, R.string.keqing, R.drawable.keqing_gacha_splash, R.drawable.keqing_ico, R.drawable.keqing_card};
            case "Kaeya": return new int[]{R.drawable.kaeya_full, R.string.kaeya, R.drawable.kaeya_gacha_splash, R.drawable.kaeya_ico, R.drawable.kaeya_card};
            case "Jean": return new int[]{R.drawable.jean_full, R.string.jean, R.drawable.jean_gacha_splash, R.drawable.jean_ico, R.drawable.jean_card};
            case "Fischl": return new int[]{R.drawable.fischl_full, R.string.fischl, R.drawable.fischl_gacha_splash, R.drawable.fischl_ico, R.drawable.fischl_card};
            case "Diluc": return new int[]{R.drawable.diluc_full, R.string.diluc, R.drawable.diluc_gacha_splash, R.drawable.diluc_ico, R.drawable.diluc_card};
            case "Chongyun": return new int[]{R.drawable.chongyun_full, R.string.chongyun, R.drawable.chongyun_gacha_splash, R.drawable.chongyun_ico, R.drawable.chongyun_card};
            case "Bennett": return new int[]{R.drawable.bennett_full, R.string.bennett, R.drawable.bennett_gacha_splash, R.drawable.bennett_ico, R.drawable.bennett_card};
            case "Beidou": return new int[]{R.drawable.beidou_full, R.string.beidou, R.drawable.beidou_gacha_splash, R.drawable.beidou_ico, R.drawable.beidou_card};
            case "Barbara": return new int[]{R.drawable.barbara_full, R.string.barbara, R.drawable.barbara_gacha_splash, R.drawable.barbara_ico, R.drawable.barbara_card};
            case "Amber": return new int[]{R.drawable.amber_full, R.string.amber, R.drawable.amber_gacha_splash, R.drawable.amber_ico, R.drawable.amber_card};
            //Add at 20210820, update at 20220109, add CARD at 20220411
            case "Traveler-Anemo": {
                sharedPreferences = context.getSharedPreferences("user_info", MODE_PRIVATE);
                String traveler_sex = sharedPreferences.getString("traveler_sex", "F");

                if (traveler_sex.equals("M")) {	return new int[]{R.drawable.aether_full, R.string.traveler_anemo, R.drawable.aether_full, R.drawable.aether_ico, R.drawable.traveler_card};
                } else {
                    return new int[]{R.drawable.lumine_full, R.string.traveler_anemo, R.drawable.lumine_full, R.drawable.lumine_ico, R.drawable.traveler_card};
                }
            }
            //Add at 20210820, update at 20220109, add CARD at 20220411
            case "Traveler-Geo": {
                sharedPreferences = context.getSharedPreferences("user_info", MODE_PRIVATE);
                String traveler_sex = sharedPreferences.getString("traveler_sex", "F");

                if (traveler_sex.equals("M")) {
                    return new int[]{R.drawable.aether_full, R.string.traveler_geo, R.drawable.aether_full, R.drawable.aether_ico, R.drawable.traveler_card};
                } else {
                    return new int[]{R.drawable.lumine_full, R.string.traveler_geo, R.drawable.lumine_full, R.drawable.lumine_ico, R.drawable.traveler_card};
                }
            }
            //Add at 20210820, update at 20220109, add CARD at 20220411
            case "Traveler-Electro": {
                sharedPreferences = context.getSharedPreferences("user_info", MODE_PRIVATE);
                String traveler_sex = sharedPreferences.getString("traveler_sex", "F");

                if (traveler_sex.equals("M")) {
                    return new int[]{R.drawable.aether_full, R.string.traveler_electro, R.drawable.aether_full, R.drawable.aether_ico, R.drawable.traveler_card};
                } else {
                    return new int[]{R.drawable.lumine_full, R.string.traveler_electro, R.drawable.lumine_full, R.drawable.lumine_ico, R.drawable.traveler_card};
                }
            }
            //Add at 20220714
            case "Traveler-Dendro": {
                sharedPreferences = context.getSharedPreferences("user_info", MODE_PRIVATE);
                String traveler_sex = sharedPreferences.getString("traveler_sex", "F");

                if (traveler_sex.equals("M")) {
                    return new int[]{R.drawable.aether_full, R.string.traveler_dendor, R.drawable.aether_full, R.drawable.aether_ico, R.drawable.traveler_card};
                } else {
                    return new int[]{R.drawable.lumine_full, R.string.traveler_dendor, R.drawable.lumine_full, R.drawable.lumine_ico, R.drawable.traveler_card};
                }
            }
            // ???
            case "Voc-夜芷冰": return new int[]{R.drawable.voc_full, R.string.voc, R.drawable.voc_full, R.drawable.voc_ico, R.drawable.voc_card};
            case "朝霧冰瀬": return new int[]{R.drawable.asagiri_korise_full, R.string.asagiri_korise, R.drawable.asagiri_korise_full, R.drawable.asagiri_korise_ico, R.drawable.unknown_card};
            case "N/A" : return new int[]{R.drawable.paimon_full, R.string.unknown, R.drawable.paimon_full,R.drawable.paimon_lost, R.drawable.unknown_card};
        }
        // Suitable for display upcoming characters
        String upcoming = name.toLowerCase().replace(" ","_");
        return new int[]{R.drawable.paimon_full, R.string.unknown, R.drawable.paimon_full,R.drawable.paimon_lost, R.drawable.unknown_card};
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
            InputStream stream = context.getAssets().open("skills/"+name+IMG_FORMAT);
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
    public int getItemIcoByName (String name){
        switch (name) {
            /** Boss*/
            case "常燃火種":
                return R.drawable.everflame_seed;
            case "淨水之心":
                return R.drawable.cleansing_heart;
            case "雷光棱鏡":
                return R.drawable.lightning_prism;
            case "極寒之核":
                return R.drawable.hoarfrost_core;
            case "颶風之種":
                return R.drawable.hurricane_seed;
            case "玄岩之塔":
                return R.drawable.basalt_pillar;
            case "未熟之玉":
                return R.drawable.juvenile_jade;
            case "晶凝之華":
                return R.drawable.crystalline_bloom;
            case "魔偶機心":
                return R.drawable.maguu_kishin;
            case "恒常機關之心":
                return R.drawable.perpetual_heart;
            case "陰燃之珠":
                return R.drawable.smoldering_pearl;
            //add in 20210910
            case "雷霆數珠":
                return R.drawable.storm_beads;
            case "排異之露":
                return R.drawable.dew_of_repudiation;
            //add in 20220105
            case "獸境王器":
                return R.drawable.riftborn_regalia;
            case "龍嗣偽鰭":
                return R.drawable.dragonheirs_false_fin;
            //add in 20220331
            case "符紋之齒":
                return R.drawable.runic_fang;
            //add in 20220716
            case "蕈王鉤喙":
                return R.drawable.majestic_hooked_beak;
            case "藏雷野實":
                return R.drawable.thunderclap_fruitcore;
            //add in 20221001
            case "永續機芯":
                return R.drawable.perpetual_caliber;
            case "導光四面體":
                return R.drawable.light_guiding_tetrahedron;
            case "滅諍草蔓":
                return R.drawable.quelled_creeper;
            //add in 20230115
            case "蒼礫蕊羽":
                return R.drawable.pseudo_stamens;
            //add in 20230416
            case "常暗圓環":
                return R.drawable.evergloom_ring;
            //add in 20230710
            case "奇械發條備件·歌裴莉婭":
                return R.drawable.novel_spare_clockwork_component_geppelia;
            case "帝皇的決斷":
                return R.drawable.emperors_resolution;
            case "奇械發條備件·科培琉司":
                return R.drawable.novel_spare_clockwork_component_coppelius;

            /** Local*/
            case "小燈草":
                return R.drawable.small_lamp_grass;
            case "慕風蘑菇":
                return R.drawable.philanemo_mushroom;
            case "夜泊石":
                return R.drawable.noctilous_jade;
            case "風車菊":
                return R.drawable.windwheel_aster;
            case "石珀":
                return R.drawable.cor_lapis;
            case "蒲公英籽":
                return R.drawable.dandelion_seed;
            case "嘟嘟蓮":
                return R.drawable.calla_lily;
            case "落落莓":
                return R.drawable.valberry;
            case "琉璃百合":
                return R.drawable.glaze_lily;
            case "琉璃袋":
                return R.drawable.violetgrass;
            case "鉤鉤果":
                return R.drawable.wolfhook;
            case "塞西莉亞花":
                return R.drawable.cecilia;
            case "絕雲椒椒":
                return R.drawable.jueyun_chili;
            case "霓裳花":
                return R.drawable.silk_flower;
            case "星螺":
                return R.drawable.starconch;
            case "清心":
                return R.drawable.qingxin;
            case "海靈芝":
                return R.drawable.sea_ganoderma;
            case "緋櫻繡球":
                return R.drawable.sakura_bloom;
            case "鳴草":
                return R.drawable.naku_weed;
            case "晶化骨髓":
                return R.drawable.crystal_marrow;
            //add in 20210910
            case "天雲草實":
                return R.drawable.amakumo_fruit;
            case "血斛":
                return R.drawable.dendrobium;
            case "幽燈蕈":
                return R.drawable.fluorescent_fungus;
            case "珊瑚真珠":
                return R.drawable.sango_pearl;
            //add in 20220517
            case "鬼兜蟲":
                return R.drawable.onikabuto;
            //add in 20220716
            case "樹王聖體菇":
                return R.drawable.onikabuto;
            case "月蓮":
                return R.drawable.lunar_lotus;
            case "劫波蓮":
                return R.drawable.kalpalata;
            //add in 20230115
            case "赤念果":
                return R.drawable.redcrest;
            case "聖金蟲":
                return R.drawable.scarab;
            case "帕蒂沙蘭":
                return R.drawable.padisarah;
            case "沙脂蛹":
                return R.drawable.sand_grease_pupa;
            case "悼靈花":
                return R.drawable.mourning_flower;
            //add in 20230710
            case "柔燈鈴":
                return R.drawable.lumidouce_bell;
            case "虹彩薔薇":
                return R.drawable.rainbow_rose;
            case "海露花":
                return R.drawable.romaritime_flower;

            /** T-Boss*/
            case "北風之環":
                return R.drawable.ring_of_boreas;
            case "東風的吐息":
                return R.drawable.dvalins_sigh;
            case "東風之翎":
                return R.drawable.dvalins_plume;
            case "北風的魂匣":
                return R.drawable.spirit_locket_of_boreas;
            case "東風之爪":
                return R.drawable.dvalins_claw;
            case "北風之尾":
                return R.drawable.tail_of_boreas;
            case "魔王之刃·殘片":
                return R.drawable.shard_of_foul_legacy;
            case "吞天之鯨·只角":
                return R.drawable.tusk_of_monoceros_caeli;
            case "武煉之魂·孤影":
                return R.drawable.shadow_of_the_warrior;
            case "龍王之冕":
                return R.drawable.dragon_lords_crown;
            case "血玉之枝":
                return R.drawable.bloodjade_branch;
            case "鎏金之鱗":
                return R.drawable.gilded_scale;
            //add in 20210910
            case "熔毀之刻":
                return R.drawable.molten_moment;
            case "灰燼之心":
                return R.drawable.ashen_heart;
            case "獄火之蝶":
                return R.drawable.hellfire_butterfly;
            //add in 20220216
            case "萬劫之真意":
                return R.drawable.the_meaning_of_aeons;
            case "凶將之手眼":
                return R.drawable.mudra_of_the_malefic_general;
            case "禍神之禊淚":
                return R.drawable.tears_of_the_calamitous_god;
            //add in 20221102
            case "傀儡的懸絲":
                return R.drawable.puppet_strings;
            case "無心的淵鏡":
                return R.drawable.miirror_of_mushin;
            case "空行的虛鈴":
                return R.drawable.dakas_bell;
            //add in 20230416
            case "原初綠洲之初綻":
                return R.drawable.primordial_greenbloom;
            case "生長天地之蕨草":
                return R.drawable.worldspan_fern;
            case "亙古樹海之一瞬":
                return R.drawable.everamber;

            /** Common*/
            case "牢固的箭簇":
                return R.drawable.firm_arrowhead;
            case "銳利的箭簇":
                return R.drawable.sharp_arrowhead;
            case "歷戰的箭簇":
                return R.drawable.weathered_arrowhead;
            case "導能繪卷":
                return R.drawable.divining_scroll;
            case "封魔繪卷":
                return R.drawable.sealed_scroll;
            case "禁咒繪卷":
                return R.drawable.forbidden_curse_scroll;
            case "尋寶鴉印":
                return R.drawable.treasure_hoarder_insignia;
            case "藏銀鴉印":
                return R.drawable.silver_raven_insignia;
            case "攫金鴉印":
                return R.drawable.golden_raven_insignia;
            case "破損的面具":
                return R.drawable.damaged_mask;
            case "污穢的面具":
                return R.drawable.stained_mask;
            case "不祥的面具":
                return R.drawable.ominous_mask;
            case "新兵的徽記":
                return R.drawable.recruits_insignia;
            case "士官的徽記":
                return R.drawable.sergeants_insignia;
            case "尉官的徽記":
                return R.drawable.lieutenants_insignia;
            case "騙騙花蜜":
                return R.drawable.whopperflower_nectar;
            case "微光花蜜":
                return R.drawable.shimmering_nectar;
            case "原素花蜜":
                return R.drawable.energy_nectar;
            case "史萊姆凝液":
                return R.drawable.slime_condensate;
            case "史萊姆清":
                return R.drawable.slime_secretions;
            case "史萊姆原漿":
                return R.drawable.slime_concentrate;
            case "破舊的刀鐔":
                return R.drawable.old_handguard;
            case "影打刀鐔":
                return R.drawable.kageuchi_handguard;
            case "名刀鐔":
                return R.drawable.famed_handguard;
            //add in 20210910
            case "浮游乾核":
                return R.drawable.spectral_husk;
            case "浮游幽核":
                return R.drawable.spectral_heart;
            case "浮游晶化核":
                return R.drawable.spectral_nucleus;
            //add in 20220716
            case "褪色紅綢":
                return R.drawable.faded_red_satin;
            case "鑲邊紅綢":
                return R.drawable.trimmed_red_silk;
            case "織金紅綢":
                return R.drawable.rich_red_brocade;
            case "蕈獸孢子":
                return R.drawable.fungal_spores;
            case "螢光孢粉":
                return R.drawable.luminescent_pollen;
            case "孢囊晶塵":
                return R.drawable.crystalline_cyst_dust;
            case "晦暗刻像":
                return R.drawable.gloomy_statuette;
            case "夤夜刻像":
                return R.drawable.dark_statuette;
            case "幽邃刻像":
                return R.drawable.deathly_statuette;
            //add in 20230416
            case "來自何處的待放之花":
                return R.drawable.a_flower_yet_to_bloom;
            case "何人所珍藏之花":
                return R.drawable.treasured_flower;
            case "漫遊者的盛放之花":
                return R.drawable.wanderers_blooming_flower;
            //add in 20230710
            case "齧合齒輪":
                return R.drawable.meshing_gear;
            case "機關正齒輪":
                return R.drawable.mechanical_spur_gear;
            case "奇械機芯齒輪":
                return R.drawable.novel_dynamic_gear;
            case "異海凝珠":
                return R.drawable.transoceanic_pearl;
            case "異海之塊":
                return R.drawable.transoceanic_chunk;
            case "異色結晶石":
                return R.drawable.kaleidoscopic_crystal;

            // P.S. There still have Sumeru items not added yet since the name are undefinded or unable to define in there. => 20220716
            // ♪ Added Sumeru items
            //add in 20220823
            case "謐林涓露的銅符":
                return R.drawable.copper_talisman_of_the_forest_dew;
            case "謐林涓露的鐵符":
                return R.drawable.iron_talisman_of_the_forest_dew;
            case "謐林涓露的銀符":
                return R.drawable.silver_talisman_of_the_forest_dew;
            case "謐林涓露的金符":
                return R.drawable.golden_talisman_of_the_forest_dew;
            case "綠洲花園的追憶":
                return R.drawable.oasis_gardens_reminiscence;
            case "綠洲花園的恩惠":
                return R.drawable.oasis_gardens_kindness;
            case "綠洲花園的哀思":
                return R.drawable.oasis_gardens_mourning;
            case "綠洲花園的真諦":
                return R.drawable.oasis_gardens_truth;
            case "烈日威權的殘響":
                return R.drawable.echo_of_scorching_might;
            case "烈日威權的餘光":
                return R.drawable.remnant_glow_of_scorching_might;
            case "烈日威權的夢想":
                return R.drawable.dream_of_scorching_might;
            case "烈日威權的舊日":
                return R.drawable.olden_days_of_scorching_might;

            //add in 20211024 (RE)
            case "漆黑隕鐵的一粒":
                return R.drawable.grain_of_aerosiderite;
            case "漆黑隕鐵的一片":
                return R.drawable.piece_of_aerosiderite;
            case "漆黑隕鐵的一角":
                return R.drawable.bit_of_aerosiderite;
            case "漆黑隕鐵的一塊":
                return R.drawable.chunk_of_aerosiderite;
            case "鳴神御靈的明惠":
                return R.drawable.narukamis_wisdom;
            case "鳴神御靈的歡喜":
                return R.drawable.narukamis_joy;
            case "鳴神御靈的親愛":
                return R.drawable.narukamis_affection;
            case "鳴神御靈的勇武":
                return R.drawable.narukamis_valor;
            case "遠海夷地的瑚枝":
                return R.drawable.coral_branch_of_a_distant_sea;
            case "遠海夷地的玉枝":
                return R.drawable.jeweled_branch_of_a_distant_sea;
            case "遠海夷地的瓊枝":
                return R.drawable.jade_branch_of_a_distant_sea;
            case "遠海夷地的金枝":
                return R.drawable.golden_branch_of_a_distant_sea;
            case "凜風奔狼的始齔":
                return R.drawable.boreal_wolfs_milk_tooth;
            case "凜風奔狼的裂齒":
                return R.drawable.boreal_wolfs_cracked_tooth;
            case "凜風奔狼的斷牙":
                return R.drawable.boreal_wolfs_broken_fang;
            case "凜風奔狼的懷鄉":
                return R.drawable.boreal_wolfs_nostalgia;
            case "高塔孤王的破瓦":
                return R.drawable.tile_of_decarabians_tower;
            case "高塔孤王的殘垣":
                return R.drawable.debris_of_decarabians_city;
            case "高塔孤王的斷片":
                return R.drawable.fragment_of_decarabians_epic;
            case "高塔孤王的碎夢":
                return R.drawable.scattered_piece_of_decarabianss_dream;
            case "霧海雲間的鉛丹":
                return R.drawable.mist_veiled_lead_elixir;
            case "霧海雲間的汞丹":
                return R.drawable.mist_veiled_mercury_elixir;
            case "霧海雲間的金丹":
                return R.drawable.mist_veiled_gold_elixir;
            case "霧海雲間的轉還":
                return R.drawable.mist_veiled_primo_elixir;
            case "獅牙鬥士的枷鎖":
                return R.drawable.fetters_of_the_dandelion_gladiator;
            case "獅牙鬥士的鐵鍊":
                return R.drawable.chains_of_the_dandelion_gladiator;
            case "獅牙鬥士的鐐銬":
                return R.drawable.shackles_of_the_dandelion_gladiator;
            case "獅牙鬥士的理想":
                return R.drawable.dream_of_the_dandelion_gladiator;
            case "孤雲寒林的光砂":
                return R.drawable.luminous_sands_from_guyun;
            case "孤雲寒林的輝岩":
                return R.drawable.lustrous_stone_from_guyun;
            case "孤雲寒林的聖骸":
                return R.drawable.relic_from_guyun;
            case "孤雲寒林的神體":
                return R.drawable.divine_body_from_guyun;
            case "今昔劇畫的惡尉":
                return R.drawable.mask_of_the_wicked_lieutenant;
            case "今昔劇畫的虎囓":
                return R.drawable.mask_of_the_tigers_bite;
            case "今昔劇畫的一角":
                return R.drawable.mask_of_the_one_horned;
            case "今昔劇畫的鬼人":
                return R.drawable.mask_of_the_kijin;
            case "混沌機關":
                return R.drawable.chaos_gear;
            case "混沌樞紐":
                return R.drawable.chaos_axis;
            case "混沌真眼":
                return R.drawable.chaos_oculus;
            case "混沌裝置":
                return R.drawable.chaos_device;
            case "混沌迴路":
                return R.drawable.chaos_circuit;
            case "混沌爐心":
                return R.drawable.chaos_core;
            case "脆弱的骨片":
                return R.drawable.sturdy_bone_shard;
            case "結實的骨片":
                return R.drawable.fragile_bone_shard;
            case "石化的骨片":
                return R.drawable.fossilized_bone_shard;
            case "霧虛花粉":
                return R.drawable.mist_grass_pollen;
            case "霧虛草囊":
                return R.drawable.mist_grass_wick;
            case "霧虛燈芯":
                return R.drawable.mist_grass;
            case "獵兵祭刀":
                return R.drawable.hunters_sacrificial_knife;
            case "特工祭刀":
                return R.drawable.inspectors_sacrificial_knife;
            case "督察長祭刀":
                return R.drawable.agents_sacrificial_knife;
            case "沉重號角":
                return R.drawable.heavy_horn;
            case "黑銅號角":
                return R.drawable.black_bronze_horn;
            case "黑晶號角":
                return R.drawable.black_crystal_horn;
            case "地脈的舊枝":
                return R.drawable.dead_ley_line_branch;
            case "地脈的枯葉":
                return R.drawable.dead_ley_line_leaves;
            case "地脈的新芽":
                return R.drawable.ley_line_sprout;
            case "黯淡棱鏡":
                return R.drawable.dismal_prism;
            case "水晶棱鏡":
                return R.drawable.crystal_prism;
            case "偏光棱鏡":
                return R.drawable.polarizing_prism;
            case "隱獸指爪":
                return R.drawable.concealed_claw;
            case "隱獸利爪":
                return R.drawable.concealed_unguis;
            case "隱獸鬼爪":
                return R.drawable.concealed_talon;
            //add in 20220823
            case "失活菌核":
                return R.drawable.inactivated_fungal_nucleus;
            case "休眠菌核":
                return R.drawable.dormant_fungal_nucleus;
            case "茁壯菌核":
                return R.drawable.robust_fungal_nucleus;
            case "混沌容器":
                return R.drawable.chaos_storage;
            case "混沌模組":
                return R.drawable.chaos_module;
            case "混沌錨栓":
                return R.drawable.chaos_bolt;
            //add in 20220924
            case "破缺稜晶":
                return R.drawable.damaged_prism;
            case "混濁稜晶":
                return R.drawable.turbid_prism;
            case "輝光稜晶":
                return R.drawable.radiant_prism;
            //add in 20230225
            case "殘毀的橫脊":
                return R.drawable.desiccated_shell;
            case "密固的橫脊":
                return R.drawable.sturdy_shell;
            case "鍥紋的橫脊":
                return R.drawable.marked_shell;

            /** T-Book*/
            case "「自由」的教導":
                return R.drawable.teaching_of_freedom;
            case "「黃金」的教導":
                return R.drawable.teaching_of_gold;
            case "「抗爭」的教導":
                return R.drawable.teaching_of_resistance;
            case "「繁榮」的教導":
                return R.drawable.teaching_of_prosperity;
            case "「詩文」的教導":
                return R.drawable.teaching_of_ballad;
            case "「勤勞」的教導":
                return R.drawable.teaching_of_diligence;
            case "「風雅」的教導":
                return R.drawable.teachings_of_elegance;
            case "「浮世」的教導":
                return R.drawable.teachings_of_transience;
            case "「天光」的教導":
                return R.drawable.teachings_of_light;
            case "「自由」的指引":
                return R.drawable.guide_to_freedom;
            case "「黃金」的指引":
                return R.drawable.guide_to_gold;
            case "「抗爭」的指引":
                return R.drawable.guide_to_resistance;
            case "「繁榮」的指引":
                return R.drawable.guide_to_prosperity;
            case "「詩文」的指引":
                return R.drawable.guide_to_ballad;
            case "「勤勞」的指引":
                return R.drawable.guide_to_diligence;
            case "「風雅」的指引":
                return R.drawable.guide_of_elegance;
            case "「浮世」的指引":
                return R.drawable.guide_of_transience;
            case "「天光」的指引":
                return R.drawable.guide_of_light;
            case "「自由」的哲學":
                return R.drawable.philosophies_of_freedom;
            case "「黃金」的哲學":
                return R.drawable.philosophies_of_gold;
            case "「抗爭」的哲學":
                return R.drawable.philosophies_of_resistance;
            case "「繁榮」的哲學":
                return R.drawable.philosophies_of_prosperity;
            case "「詩文」的哲學":
                return R.drawable.philosophies_of_ballad;
            case "「勤勞」的哲學":
                return R.drawable.philosophies_of_diligence;
            case "「風雅」的哲學":
                return R.drawable.philosophies_of_elegance;
            case "「浮世」的哲學":
                return R.drawable.philosophies_of_transience;
            case "「天光」的哲學":
                return R.drawable.philosophies_of_light;
            //add in 20220823
            case "「篤行」的教導":
                return R.drawable.teachings_of_admonition;
            case "「巧思」的教導":
                return R.drawable.teachings_of_ingenuity;
            case "「諍言」的教導":
                return R.drawable.teachings_of_praxis;
            case "「篤行」的指引":
                return R.drawable.guide_to_admonition;
            case "「巧思」的指引":
                return R.drawable.guide_to_ingenuity;
            case "「諍言」的指引":
                return R.drawable.guide_to_praxis;
            case "「篤行」的哲學":
                return R.drawable.philosophies_of_admonition;
            case "「巧思」的哲學":
                return R.drawable.philosophies_of_ingenuity;
            case "「諍言」的哲學":
                return R.drawable.philosophies_of_praxis;
            //add in 20230710
            case "「公平」的教導":
                return R.drawable.teachings_of_fairness;
            case "「正義」的教導":
                return R.drawable.teachings_of_justice;
            case "「秩序」的教導":
                return R.drawable.teachings_of_order;
            case "「公平」的指引":
                return R.drawable.guide_to_fairness;
            case "「正義」的指引":
                return R.drawable.guide_to_justice;
            case "「秩序」的指引":
                return R.drawable.guide_to_order;
            case "「公平」的哲學":
                return R.drawable.philosophies_of_fairness;
            case "「正義」的哲學":
                return R.drawable.philosophies_of_justice;
            case "「秩序」的哲學":
                return R.drawable.philosophies_of_order;

            /** Crystal*/
            case "燃願瑪瑙碎屑":
                return R.drawable.agnidus_agate_sliver;
            case "燃願瑪瑙斷片":
                return R.drawable.agnidus_agate_fragment;
            case "燃願瑪瑙塊":
                return R.drawable.agnidus_agate_chunk;
            case "燃願瑪瑙":
                return R.drawable.agnidus_agate_gemstone;
            case "滌淨青金碎屑":
                return R.drawable.varunada_lazurite_sliver;
            case "滌淨青金斷片":
                return R.drawable.varunada_lazurite_fragment;
            case "滌淨青金塊":
                return R.drawable.varunada_lazurite_chunk;
            case "滌淨青金":
                return R.drawable.varunada_lazurite_gemstone;
            case "最勝紫晶碎屑":
                return R.drawable.vajrada_amethyst_sliver;
            case "最勝紫晶斷片":
                return R.drawable.vajrada_amethyst_fragment;
            case "最勝紫晶塊":
                return R.drawable.vajrada_amethyst_chunk;
            case "最勝紫晶":
                return R.drawable.vajrada_amethyst_gemstone;
            case "哀敘冰玉碎屑":
                return R.drawable.shivada_jade_sliver;
            case "哀敘冰玉斷片":
                return R.drawable.shivada_jade_fragment;
            case "哀敘冰玉塊":
                return R.drawable.shivada_jade_chunk;
            case "哀敘冰玉":
                return R.drawable.shivada_jade_gemstone;
            case "自在松石碎屑":
                return R.drawable.vayuda_turquoise_sliver;
            case "自在松石斷片":
                return R.drawable.vayuda_turquoise_fragment;
            case "自在松石塊":
                return R.drawable.vayuda_turquoise_chunk;
            case "自在松石":
                return R.drawable.vayuda_turquoise_gemstone;
            case "堅牢黃玉碎屑":
                return R.drawable.prithiva_topaz_sliver;
            case "堅牢黃玉斷片":
                return R.drawable.prithiva_topaz_fragment;
            case "堅牢黃玉塊":
                return R.drawable.prithiva_topaz_chunk;
            case "堅牢黃玉":
                return R.drawable.prithiva_topaz_gemstone;
            //add in 20220714
            case "生長碧翡碎屑":
                return R.drawable.nagadus_emerald_sliver;
            case "生長碧翡斷片":
                return R.drawable.nagadus_emerald_fragment;
            case "生長碧翡塊":
                return R.drawable.nagadus_emerald_chunk;
            case "生長碧翡":
                return R.drawable.nagadus_emerald_gemstone;
            case "璀璨原鑽碎屑":
                return R.drawable.brilliant_diamond_sliver;
            case "璀璨原鑽斷片":
                return R.drawable.brilliant_diamond_fragment;
            case "璀璨原鑽塊":
                return R.drawable.brilliant_diamond_chunk;
            case "璀璨原鑽":
                return R.drawable.brilliant_diamond_gemstone;

            /** Others*/
            case "智識之冕":
                return R.drawable.crown_of_sagehood;
            case "摩拉":
                return R.drawable.mora;
            case "流浪者的經驗":
                return R.drawable.wanderers_advice;
            case "冒險家的經驗":
                return R.drawable.adventurers_experience;
            case "大英雄的經驗":
                return R.drawable.heros_wit;
            case "精鍛用雜礦":
                return R.drawable.enchancement_ore;
            case "精鍛用良礦":
                return R.drawable.fine_enchancement_ore;
            case "精鍛用魔礦":
                return R.drawable.mystic_enchancement_ore;
            case "祝聖油膏":
                return R.drawable.sanctifying_unction;
            case "祝聖精華":
                return R.drawable.sanctifying_essence;

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
                return R.drawable.hu_tao_unknown;
            default:
                return R.drawable.hu_tao_unknown;
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


    public static String LoadAssestData (Context context, String inFile){
        String tContents = "";

        try {
            InputStream stream = context.getAssets().open(inFile);

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
    public static String LoadData(Context context, String inFile) {
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
    public static String LoadExtendData(Context context, String inFile) {
        String tContents = "";
        try {
            File file = new File(context.getExternalMediaDirs()[0]+"/"+inFile);
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

    public static String convertToLowerFileName(String str){
        return str
                .toLowerCase()
                .replace(" ", "")
                .replace("_", "")
                .replace("-", "")
                .replace("'", "")
                .replace(":", "")
                .replace("!", "");
    }

    /**
     *
     * @param jsonObject : including KEY `effect`, also maybe include `r1`, `r2`, etc...
     * @return Combined Effect String
     */
    public static String combineEffectStatus(JSONObject jsonObject, String effectKey) {
        try{
            int rMax = 0;
            while (jsonObject.has("r"+String.valueOf(rMax+1))){
                rMax++;
            }

            String effect = jsonObject.getString(effectKey);

            if (rMax >= 1){
                for (int rPerLen = 0 ; rPerLen < jsonObject.getJSONArray("r1").length() ; rPerLen++){
                    String tmpValueCombine = "";
                    for (int x = 1 ;x <= rMax ; x++){
                        tmpValueCombine += (jsonObject.getJSONArray("r"+String.valueOf(x)).get(rPerLen) + ((x < (rMax)) ? "/" : ""));
                    }
                    effect = effect.replace("{"+String.valueOf(rPerLen)+"}", tmpValueCombine);
                }
                return effect;
            }else{
                return jsonObject.getString(jsonObject.getString(effectKey));
            }
        }catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
