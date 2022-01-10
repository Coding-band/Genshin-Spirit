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

    int[] Mon_char_IMG =  {R.drawable.teaching_of_freedom,R.drawable.teaching_of_prosperity,R.drawable.teachings_of_transience};
    int[] Tue_char_IMG =  {R.drawable.teaching_of_resistance,R.drawable.teaching_of_diligence,R.drawable.teachings_of_elegance};
    int[] Wed_char_IMG =  {R.drawable.teaching_of_ballad,R.drawable.teaching_of_gold,R.drawable.teachings_of_light};
    int[] Thur_char_IMG =  {R.drawable.teaching_of_freedom,R.drawable.teaching_of_prosperity,R.drawable.teachings_of_transience};
    int[] Fri_char_IMG =  {R.drawable.teaching_of_resistance,R.drawable.teaching_of_diligence,R.drawable.teachings_of_elegance};
    int[] Sat_char_IMG =  {R.drawable.teaching_of_ballad,R.drawable.teaching_of_gold,R.drawable.teachings_of_light};
    int[] Sun_char_IMG =  {R.drawable.teaching_of_freedom,R.drawable.teaching_of_prosperity,R.drawable.teachings_of_transience,R.drawable.teaching_of_resistance,R.drawable.teaching_of_diligence,R.drawable.teachings_of_elegance,R.drawable.teaching_of_ballad,R.drawable.teaching_of_gold,R.drawable.teachings_of_light};

    int[] Mon_char_TV =  {R.string.teaching_of_freedom,R.string.teaching_of_prosperity,R.string.teachings_of_transience};
    int[] Tue_char_TV =  {R.string.teaching_of_resistance,R.string.teaching_of_diligence,R.string.teachings_of_elegance};
    int[] Wed_char_TV =  {R.string.teaching_of_ballad,R.string.teaching_of_gold,R.string.teachings_of_light};
    int[] Thur_char_TV =  {R.string.teaching_of_freedom,R.string.teaching_of_prosperity,R.string.teachings_of_transience};
    int[] Fri_char_TV =  {R.string.teaching_of_resistance,R.string.teaching_of_diligence,R.string.teachings_of_elegance};
    int[] Sat_char_TV =  {R.string.teaching_of_ballad,R.string.teaching_of_gold,R.string.teachings_of_light};
    int[] Sun_char_TV =  {R.string.teaching_of_freedom,R.string.teaching_of_prosperity,R.string.teachings_of_transience,R.string.teaching_of_resistance,R.string.teaching_of_diligence,R.string.teachings_of_elegance,R.string.teaching_of_ballad,R.string.teaching_of_gold,R.string.teachings_of_light};

    int[] Mon_weapon_IMG =  {R.drawable.tile_of_decarabians_tower,R.drawable.luminous_sands_from_guyun,R.drawable.coral_branch_of_a_distant_sea};
    int[] Tue_weapon_IMG =  {R.drawable.mist_veiled_lead_elixir,R.drawable.boreal_wolfs_milk_tooth,R.drawable.narukamis_wisdom};
    int[] Wed_weapon_IMG =  {R.drawable.grain_of_aerosiderite,R.drawable.fetters_of_the_dandelion_gladiator,R.drawable.mask_of_the_wicked_lieutenant};
    int[] Thur_weapon_IMG = {R.drawable.tile_of_decarabians_tower,R.drawable.luminous_sands_from_guyun,R.drawable.coral_branch_of_a_distant_sea};
    int[] Fri_weapon_IMG =  {R.drawable.mist_veiled_lead_elixir,R.drawable.boreal_wolfs_milk_tooth,R.drawable.narukamis_wisdom};
    int[] Sat_weapon_IMG =  {R.drawable.grain_of_aerosiderite,R.drawable.fetters_of_the_dandelion_gladiator,R.drawable.mask_of_the_wicked_lieutenant};
    int[] Sun_weapon_IMG =  {R.drawable.tile_of_decarabians_tower,R.drawable.luminous_sands_from_guyun,R.drawable.coral_branch_of_a_distant_sea,R.drawable.mist_veiled_lead_elixir,R.drawable.boreal_wolfs_milk_tooth,R.drawable.narukamis_wisdom,R.drawable.grain_of_aerosiderite,R.drawable.fetters_of_the_dandelion_gladiator,R.drawable.mask_of_the_wicked_lieutenant};

    int[] Mon_weapon_TV =  {R.string.tile_of_decarabians_tower,R.string.luminous_sands_from_guyun,R.string.coral_branch_of_a_distant_sea};
    int[] Tue_weapon_TV =  {R.string.mist_veiled_lead_elixir,R.string.boreal_wolfs_milk_tooth,R.string.narukamis_wisdom};
    int[] Wed_weapon_TV =  {R.string.grain_of_aerosiderite,R.string.fetters_of_the_dandelion_gladiator,R.string.mask_of_the_wicked_lieutenant};
    int[] Thur_weapon_TV = {R.string.tile_of_decarabians_tower,R.string.luminous_sands_from_guyun,R.string.coral_branch_of_a_distant_sea};
    int[] Fri_weapon_TV =  {R.string.mist_veiled_lead_elixir,R.string.boreal_wolfs_milk_tooth,R.string.narukamis_wisdom};
    int[] Sat_weapon_TV =  {R.string.grain_of_aerosiderite,R.string.fetters_of_the_dandelion_gladiator,R.string.mask_of_the_wicked_lieutenant};
    int[] Sun_weapon_TV =  {R.string.tile_of_decarabians_tower,R.string.luminous_sands_from_guyun,R.string.coral_branch_of_a_distant_sea,R.string.mist_veiled_lead_elixir,R.string.boreal_wolfs_milk_tooth,R.string.narukamis_wisdom,R.string.grain_of_aerosiderite,R.string.fetters_of_the_dandelion_gladiator,R.string.mask_of_the_wicked_lieutenant};


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

}
