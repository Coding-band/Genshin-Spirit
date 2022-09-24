package com.voc.genshin_helper.data;

import android.util.Log;

import com.voc.genshin_helper.R;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */
public class Today_Material {

    int[] Mon_char_IMG =  {R.drawable.teaching_of_freedom,R.drawable.teaching_of_prosperity,R.drawable.teachings_of_transience,R.drawable.teachings_of_admonition};
    int[] Tue_char_IMG =  {R.drawable.teaching_of_resistance,R.drawable.teaching_of_diligence,R.drawable.teachings_of_elegance,R.drawable.teachings_of_ingenuity};
    int[] Wed_char_IMG =  {R.drawable.teaching_of_ballad,R.drawable.teaching_of_gold,R.drawable.teachings_of_light,R.drawable.teachings_of_praxis};
    int[] Thur_char_IMG =  {R.drawable.teaching_of_freedom,R.drawable.teaching_of_prosperity,R.drawable.teachings_of_transience,R.drawable.teachings_of_admonition};
    int[] Fri_char_IMG =  {R.drawable.teaching_of_resistance,R.drawable.teaching_of_diligence,R.drawable.teachings_of_elegance,R.drawable.teachings_of_ingenuity};
    int[] Sat_char_IMG =  {R.drawable.teaching_of_ballad,R.drawable.teaching_of_gold,R.drawable.teachings_of_light,R.drawable.teachings_of_praxis};
    int[] Sun_char_IMG =  {R.drawable.teaching_of_freedom,R.drawable.teaching_of_prosperity,R.drawable.teachings_of_transience,R.drawable.teaching_of_resistance,R.drawable.teaching_of_diligence,R.drawable.teachings_of_elegance,R.drawable.teaching_of_ballad,R.drawable.teaching_of_gold,R.drawable.teachings_of_light,R.drawable.teachings_of_admonition,R.drawable.teachings_of_ingenuity,R.drawable.teachings_of_praxis};

    int[] Mon_char_TV =  {R.string.teaching_of_freedom,R.string.teaching_of_prosperity,R.string.teachings_of_transience,R.string.teachings_of_admonition};
    int[] Tue_char_TV =  {R.string.teaching_of_resistance,R.string.teaching_of_diligence,R.string.teachings_of_elegance,R.string.teachings_of_ingenuity};
    int[] Wed_char_TV =  {R.string.teaching_of_ballad,R.string.teaching_of_gold,R.string.teachings_of_light,R.string.teachings_of_praxis};
    int[] Thur_char_TV =  {R.string.teaching_of_freedom,R.string.teaching_of_prosperity,R.string.teachings_of_transience,R.string.teachings_of_admonition};
    int[] Fri_char_TV =  {R.string.teaching_of_resistance,R.string.teaching_of_diligence,R.string.teachings_of_elegance,R.string.teachings_of_ingenuity};
    int[] Sat_char_TV =  {R.string.teaching_of_ballad,R.string.teaching_of_gold,R.string.teachings_of_light,R.string.teachings_of_praxis};
    int[] Sun_char_TV =  {R.string.teaching_of_freedom,R.string.teaching_of_prosperity,R.string.teachings_of_transience,R.string.teaching_of_resistance,R.string.teaching_of_diligence,R.string.teachings_of_elegance,R.string.teaching_of_ballad,R.string.teaching_of_gold,R.string.teachings_of_light,R.string.teachings_of_admonition,R.string.teachings_of_ingenuity,R.string.teachings_of_praxis};

    int[] Sun_char_Location =  {R.string.forsaken_rift,R.string.taishan_mansion,R.string.violet_court,R.string.steeple_of_ignorance,R.string.forsaken_rift,R.string.taishan_mansion,R.string.violet_court,R.string.steeple_of_ignorance,R.string.forsaken_rift,R.string.taishan_mansion,R.string.violet_court,R.string.steeple_of_ignorance};
    int[] Weekday_char_Location =  {R.string.forsaken_rift,R.string.taishan_mansion,R.string.violet_court,R.string.steeple_of_ignorance};

    int[] NullX = {1,2,3,4,5,6,7,8,9};

    //https://webstatic-sea.hoyolab.com/ys/app/interactive-map/index.html?lang=zh-tw#/map/2?shown_types=3,154&center={X,Y}&zoom=0.50
    String[] Sun_char_Location_URL = {"656.47,94.08","1665.33,-1944.69","5532.32,3176.36","0,0","656.47,94.08","1665.33,-1944.69","0,0","5532.32,3176.36","656.47,94.08","1665.33,-1944.69","5532.32,3176.36","0,0"};
    String[] Weekday_char_Location_URL = {"656.47,94.08","1665.33,-1944.69","5532.32,3176.36","0,0"};

    int[] Mon_weapon_IMG =  {R.drawable.tile_of_decarabians_tower,R.drawable.luminous_sands_from_guyun,R.drawable.coral_branch_of_a_distant_sea,R.drawable.copper_talisman_of_the_forest_dew};
    int[] Tue_weapon_IMG =  {R.drawable.mist_veiled_lead_elixir,R.drawable.boreal_wolfs_milk_tooth,R.drawable.narukamis_wisdom,R.drawable.oasis_gardens_reminiscence};
    int[] Wed_weapon_IMG =  {R.drawable.grain_of_aerosiderite,R.drawable.fetters_of_the_dandelion_gladiator,R.drawable.mask_of_the_wicked_lieutenant,R.drawable.echo_of_scorching_might};
    int[] Thur_weapon_IMG = {R.drawable.tile_of_decarabians_tower,R.drawable.luminous_sands_from_guyun,R.drawable.coral_branch_of_a_distant_sea,R.drawable.copper_talisman_of_the_forest_dew};
    int[] Fri_weapon_IMG =  {R.drawable.mist_veiled_lead_elixir,R.drawable.boreal_wolfs_milk_tooth,R.drawable.narukamis_wisdom,R.drawable.oasis_gardens_reminiscence};
    int[] Sat_weapon_IMG =  {R.drawable.grain_of_aerosiderite,R.drawable.fetters_of_the_dandelion_gladiator,R.drawable.mask_of_the_wicked_lieutenant,R.drawable.echo_of_scorching_might};
    int[] Sun_weapon_IMG =  {R.drawable.tile_of_decarabians_tower,R.drawable.luminous_sands_from_guyun,R.drawable.coral_branch_of_a_distant_sea,R.drawable.mist_veiled_lead_elixir,R.drawable.boreal_wolfs_milk_tooth,R.drawable.narukamis_wisdom,R.drawable.grain_of_aerosiderite,R.drawable.fetters_of_the_dandelion_gladiator,R.drawable.mask_of_the_wicked_lieutenant,R.drawable.copper_talisman_of_the_forest_dew,R.drawable.oasis_gardens_reminiscence,R.drawable.echo_of_scorching_might};

    int[] Mon_weapon_TV =  {R.string.tile_of_decarabians_tower,R.string.luminous_sands_from_guyun,R.string.coral_branch_of_a_distant_sea,R.string.copper_talisman_of_the_forest_dew};
    int[] Tue_weapon_TV =  {R.string.mist_veiled_lead_elixir,R.string.boreal_wolfs_milk_tooth,R.string.narukamis_wisdom,R.string.oasis_gardens_reminiscence};
    int[] Wed_weapon_TV =  {R.string.grain_of_aerosiderite,R.string.fetters_of_the_dandelion_gladiator,R.string.mask_of_the_wicked_lieutenant,R.string.echo_of_scorching_might};
    int[] Thur_weapon_TV = {R.string.tile_of_decarabians_tower,R.string.luminous_sands_from_guyun,R.string.coral_branch_of_a_distant_sea,R.string.copper_talisman_of_the_forest_dew};
    int[] Fri_weapon_TV =  {R.string.mist_veiled_lead_elixir,R.string.boreal_wolfs_milk_tooth,R.string.narukamis_wisdom,R.string.oasis_gardens_reminiscence};
    int[] Sat_weapon_TV =  {R.string.grain_of_aerosiderite,R.string.fetters_of_the_dandelion_gladiator,R.string.mask_of_the_wicked_lieutenant,R.string.echo_of_scorching_might};
    int[] Sun_weapon_TV =  {R.string.tile_of_decarabians_tower,R.string.luminous_sands_from_guyun,R.string.coral_branch_of_a_distant_sea,R.string.mist_veiled_lead_elixir,R.string.boreal_wolfs_milk_tooth,R.string.narukamis_wisdom,R.string.grain_of_aerosiderite,R.string.fetters_of_the_dandelion_gladiator,R.string.mask_of_the_wicked_lieutenant,R.string.copper_talisman_of_the_forest_dew,R.string.oasis_gardens_reminiscence,R.string.echo_of_scorching_might};

    int[] Sun_weapon_Location =  {R.string.cecilia_garden,R.string.hidden_palace_of_lianshan_formula,R.string.court_of_flowing_sand,R.string.tower_of_abject_pride,R.string.cecilia_garden,R.string.hidden_palace_of_lianshan_formula,R.string.court_of_flowing_sand,R.string.tower_of_abject_pride,R.string.cecilia_garden,R.string.hidden_palace_of_lianshan_formula,R.string.court_of_flowing_sand,R.string.tower_of_abject_pride};
    int[] Weekday_weapon_Location =  {R.string.cecilia_garden,R.string.hidden_palace_of_lianshan_formula,R.string.court_of_flowing_sand,R.string.tower_of_abject_pride};

    //https://webstatic-sea.hoyolab.com/ys/app/interactive-map/index.html?lang=zh-tw#/map/2?shown_types=3,154&center={X,Y}&zoom=0.50
    String[] Sun_weapon_Location_URL = {"47.65,-564.24","1582.07,-432.71","4714.55,3606.99","0,0","47.65,-564.24","1582.07,-432.71","4714.55,3606.99","0,0","47.65,-564.24","1582.07,-432.71","4714.55,3606.99","0,0"};
    String[] Weekday_weapon_Location_URL = {"47.65,-564.24","1582.07,-432.71","4714.55,3606.99","0,0"};

    public int[] today_char_IMG (int dow){
        if(dow == 2){return Mon_char_IMG;}
        else if(dow == 3){return Tue_char_IMG;}
        else if(dow == 4){return Wed_char_IMG;}
        else if(dow == 5){return Thur_char_IMG;}
        else if(dow == 6){return Fri_char_IMG;}
        else if(dow == 7){return Sat_char_IMG;}
        else if(dow == 1){return Sun_char_IMG;}
        return Mon_char_IMG;
    }

    public int[] today_char_location (int dow){
        if(dow == 1){return Sun_char_Location;}
        else return Weekday_char_Location;
    }

    public int[] today_weapon_IMG (int dow){
        if(dow == 2){return Mon_weapon_IMG;}
        else if(dow == 3){return Tue_weapon_IMG;}
        else if(dow == 4){return Wed_weapon_IMG;}
        else if(dow == 5){return Thur_weapon_IMG;}
        else if(dow == 6){return Fri_weapon_IMG;}
        else if(dow == 7){return Sat_weapon_IMG;}
        else if(dow == 1){return Sun_weapon_IMG;}
        return Mon_weapon_IMG;
    }

    public int[] today_char_TV (int dow){
        if(dow == 2){return Mon_char_TV;}
        else if(dow == 3){return Tue_char_TV;}
        else if(dow == 4){return Wed_char_TV;}
        else if(dow == 5){return Thur_char_TV;}
        else if(dow == 6){return Fri_char_TV;}
        else if(dow == 7){return Sat_char_TV;}
        else if(dow == 1){return Sun_char_TV;}
        return Mon_char_TV;
    }

    public int[] today_weapon_TV (int dow){
        if(dow == 2){return Mon_weapon_TV;}
        else if(dow == 3){return Tue_weapon_TV;}
        else if(dow == 4){return Wed_weapon_TV;}
        else if(dow == 5){return Thur_weapon_TV;}
        else if(dow == 6){return Fri_weapon_TV;}
        else if(dow == 7){return Sat_weapon_TV;}
        else if(dow == 1){return Sun_weapon_TV;}
        return Mon_weapon_TV;
    }

    public int[] today_weapon_location (int dow){
        if(dow == 1){return Sun_weapon_Location;}
        else return Weekday_weapon_Location;
    }

    public String[] today_weapon_location_url (int dow){
        if(dow == 1){return Sun_weapon_Location_URL;}
        else return Weekday_weapon_Location_URL;
    }
    public String[] today_char_location_url (int dow){
        if(dow == 1){return Sun_char_Location_URL;}
        else return Weekday_char_Location_URL;
    }

    public int today_is (int ids){
        if(ArrayUtils.contains( Mon_char_IMG, ids)||ArrayUtils.contains( Thur_char_IMG, ids)||ArrayUtils.contains( Mon_weapon_IMG,ids)||ArrayUtils.contains( Thur_weapon_IMG,ids)){
            return R.string.dow_set1;
        }
        else if(ArrayUtils.contains( Tue_char_IMG,ids)||ArrayUtils.contains( Fri_char_IMG,ids)||ArrayUtils.contains( Tue_weapon_IMG,ids)||ArrayUtils.contains( Fri_weapon_IMG,ids)){
            return R.string.dow_set2;
        }
        else if(ArrayUtils.contains( Wed_char_IMG,ids)||ArrayUtils.contains( Sat_char_IMG,ids)||ArrayUtils.contains( Wed_weapon_IMG,ids)||ArrayUtils.contains( Sat_weapon_IMG,ids)){
            return R.string.dow_set3;
        }
        else return R.string.unknown;
    }

    public int findCharBookByZHName(String str){
        switch (str){
            case "「自由」的哲學" : return R.string.teaching_of_freedom;
            case "「抗爭」的哲學" : return R.string.teaching_of_resistance;
            case "「詩文」的哲學" : return R.string.teaching_of_ballad;
            case "「黃金」的哲學" : return R.string.teaching_of_gold;
            case "「勤勞」的哲學" : return R.string.teaching_of_diligence;
            case "「繁榮」的哲學" : return R.string.teaching_of_prosperity;
            case "「風雅」的哲學" : return R.string.teachings_of_elegance;
            case "「天光」的哲學" : return R.string.teachings_of_light;
            case "「浮世」的哲學" : return R.string.teachings_of_transience;
            case "「諍言」的哲學" : return R.string.teachings_of_admonition;
            case "「巧思」的哲學" : return R.string.teachings_of_ingenuity;
            case "「篤行」的哲學" : return R.string.teachings_of_praxis;
            default: return R.string.unknown;
        }
    }

    public int findWeaponMaterialByZHName(String str){
        switch (str){
            case "高塔孤王的碎夢" : return R.string.tile_of_decarabians_tower;
            case "孤雲寒林的神體" : return R.string.luminous_sands_from_guyun;
            case "遠海夷地的金枝" : return R.string.coral_branch_of_a_distant_sea;
            case "霧海雲間的轉還" : return R.string.mist_veiled_lead_elixir;
            case "凜風奔狼的懷鄉" : return R.string.boreal_wolfs_milk_tooth;
            case "鳴神御靈的勇武" : return R.string.narukamis_wisdom;
            case "漆黑隕鐵的一塊" : return R.string.grain_of_aerosiderite;
            case "獅牙鬥士的理想" : return R.string.fetters_of_the_dandelion_gladiator;
            case "今昔劇畫的鬼人" : return R.string.mask_of_the_wicked_lieutenant;
            case "謐林涓露的金符" : return R.string.copper_talisman_of_the_forest_dew;
            case "綠洲花園的真諦" : return R.string.oasis_gardens_reminiscence;
            case "烈日威權的舊日" : return R.string.echo_of_scorching_might;
            default: return R.string.unknown;
        }
    }

}
