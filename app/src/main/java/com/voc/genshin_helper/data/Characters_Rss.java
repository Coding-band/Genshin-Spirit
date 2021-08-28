package com.voc.genshin_helper.data;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.voc.genshin_helper.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Package com.voc.genshin_helper.data was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2020 Xectorda 版權所有
 */
public class Characters_Rss {

    public int[] char_names = {R.string.aloy,R.string.kujou_sara,R.string.sangonomiya_kokomi,R.string.raiden_shogun,R.string.sayu,R.string.yoimiya,R.string.kamisato_ayaka,R.string.kaedehara_kazuha,R.string.yanfei,R.string.eula,R.string.rosaria,R.string.xiao,R.string.hu_tao,R.string.ganyu,R.string.albedo,R.string.zhongli,R.string.xinyan,R.string.tartaglia,R.string.diona,R.string.xingqiu,R.string.xiangling,R.string.venti,R.string.sucrose,R.string.razor,R.string.qiqi,R.string.noelle,R.string.ningguang,R.string.mona,R.string.lisa,R.string.klee,R.string.keqing,R.string.kaeya,R.string.jean,R.string.fischl,R.string.diluc,R.string.chongyun,R.string.bennett,R.string.beidou,R.string.barbara, R.string.amber,R.string.traveler_anemo,R.string.traveler_geo,R.string.traveler_electro};

    public String LocaleStr(int x,Context context){
        return context.getString(char_names[x]);
    }

    public String getLocaleName (String str,Context context) {
        /** Area Name */
        if (str.equals("Mondstadt")){return context.getString(R.string.mondstadt);}
        else if (str.equals("Liyue")){return context.getString(R.string.liyue);}
        else if (str.equals("Inazuma")){return context.getString(R.string.inazuma);}
        /** Char's Role Name*/
        else if (str.equals("Main_DPS")){return context.getString(R.string.main_dps);}
        else if (str.equals("Support_DPS")){return context.getString(R.string.support_dps);}
        else if (str.equals("Utility")){return context.getString(R.string.utility);}
        /** Sex Name */
        else if (str.equals("Female")){return context.getString(R.string.female);}
        else if (str.equals("Male")){return context.getString(R.string.male);}

        else {return "null";}
    }

    public int getWeaponTypeIMG (String str){
        if(str.equals("Bow")){return R.drawable.ico_bow;}
        else if(str.equals("Catalyst")){return R.drawable.ico_catalyst;}
        else if(str.equals("Claymore")){return R.drawable.ico_claymore;}
        else if(str.equals("Polearm")){return R.drawable.ico_polearm;}
        else if(str.equals("Sword")){return R.drawable.ico_sword;}
        else {return R.drawable.paimon_lost;}
    }

    /**
     * @param name is Char's Identify Name -> JSON
     * @return IMG_ID , NAME_LOCAL , FULL_IMG_ID , ICO_IMG_ID
     */
    public int[] getCharByName(String name){
        if(name.equals("Aloy")){return new int[] {R.drawable.aloy,R.string.aloy,R.drawable.aloy,R.drawable.aloy_ico};}
        else if(name.equals("Kujou Sara")){return new int[] {R.drawable.kujou_sara_flag,R.string.kujou_sara,R.drawable.kujou_sara_full,R.drawable.kujou_sara_ico};}
        else if(name.equals("Sangonomiya Kokomi")){return new int[] {R.drawable.sangonomiya_kokomi_flag,R.string.sangonomiya_kokomi,R.drawable.sangonomiya_kokomi_full,R.drawable.sangonomiya_kokomi_ico};}
        else if(name.equals("Raiden Shogun")){return new int[] {R.drawable.raiden_shogun_flag,R.string.raiden_shogun,R.drawable.raiden_shogun_full,R.drawable.raiden_shogun_ico};}
        else if(name.equals("Sayu")){return new int[] {R.drawable.sayu_flag,R.string.sayu,R.drawable.sayu_full,R.drawable.sayu_ico};}
        else if(name.equals("Yoimiya")){return new int[] {R.drawable.yoimiya_flag,R.string.yoimiya,R.drawable.yoimiya_full,R.drawable.yoimiya_ico};}
        else if(name.equals("Kamisato Ayaka")){return new int[] {R.drawable.ayaka_flag,R.string.kamisato_ayaka,R.drawable.ayaka_full,R.drawable.ayaka_ico};}
        else if(name.equals("Kaedehara Kazuha")){return new int[] {R.drawable.kazuha_flag,R.string.kaedehara_kazuha,R.drawable.kazuha_full,R.drawable.kazuha_ico};}
        else if(name.equals("Yanfei")){return new int[] {R.drawable.yanfei_flag,R.string.yanfei,R.drawable.yanfei_full,R.drawable.yanfei_ico};}
        else if(name.equals("Eula")){return new int[] {R.drawable.eula_flag,R.string.eula,R.drawable.eula_full,R.drawable.eula_ico};}
        else if(name.equals("Rosaria")){return new int[] {R.drawable.rosaria_flag,R.string.rosaria,R.drawable.rosaria_full,R.drawable.rosaria_ico};}
        else if(name.equals("Xiao")){return new int[] {R.drawable.xiao_flag,R.string.xiao,R.drawable.xiao_full,R.drawable.xiao_ico};}
        else if(name.equals("Hu Tao")){return new int[] {R.drawable.hu_tao_flag,R.string.hu_tao,R.drawable.hu_tao_full,R.drawable.hu_tao_ico};}
        else if(name.equals("Ganyu")){return new int[] {R.drawable.ganyu_flag,R.string.ganyu,R.drawable.ganyu_full,R.drawable.ganyu_ico};}
        else if(name.equals("Albedo")){return new int[] {R.drawable.albedo_flag,R.string.albedo,R.drawable.albedo_full,R.drawable.albedo_ico};}
        else if(name.equals("Zhongli")){return new int[] {R.drawable.zhongli_flag,R.string.zhongli,R.drawable.zhongli_full,R.drawable.zhongli_ico};}
        else if(name.equals("Xinyan")){return new int[] {R.drawable.xinyan_flag,R.string.xinyan,R.drawable.xinyan_full,R.drawable.xinyan_ico};}
        else if(name.equals("Tartaglia")){return new int[] {R.drawable.tartaglia_flag,R.string.tartaglia,R.drawable.tartaglia_full,R.drawable.tartaglia_ico};}
        else if(name.equals("Diona")){return new int[] {R.drawable.diona_flag,R.string.diona,R.drawable.diona_full,R.drawable.diona_ico};}
        else if(name.equals("Xingqiu")){return new int[] {R.drawable.xingqiu_flag,R.string.xingqiu,R.drawable.xingqiu_full,R.drawable.xingqiu_ico};}
        else if(name.equals("Xiangling")){return new int[] {R.drawable.xiangling_flag,R.string.xiangling,R.drawable.xiangling_full,R.drawable.xiangling_ico};}
        else if(name.equals("Venti")){return new int[] {R.drawable.venti_flag,R.string.venti,R.drawable.venti_full,R.drawable.venti_ico};}
        else if(name.equals("Sucrose")){return new int[] {R.drawable.sucrose_flag,R.string.sucrose,R.drawable.sucrose_full,R.drawable.sucrose_ico};}
        else if(name.equals("Razor")){return new int[] {R.drawable.razor_flag,R.string.razor,R.drawable.razor_full,R.drawable.razor_ico};}
        else if(name.equals("Qiqi")){return new int[] {R.drawable.qiqi_flag,R.string.qiqi,R.drawable.qiqi_full,R.drawable.qiqi_ico};}
        else if(name.equals("Noelle")){return new int[] {R.drawable.noelle_flag,R.string.noelle,R.drawable.noelle_full,R.drawable.noelle_ico};}
        else if(name.equals("Ningguang")){return new int[] {R.drawable.ningguang_flag,R.string.ningguang,R.drawable.ningguang_full,R.drawable.ningguang_ico};}
        else if(name.equals("Mona")){return new int[] {R.drawable.mona_flag,R.string.mona,R.drawable.mona_full,R.drawable.mona_ico};}
        else if(name.equals("Lisa")){return new int[] {R.drawable.lisa_flag,R.string.lisa,R.drawable.lisa_full,R.drawable.lisa_ico};}
        else if(name.equals("Klee")){return new int[] {R.drawable.klee_flag,R.string.klee,R.drawable.klee_full,R.drawable.klee_ico};}
        else if(name.equals("Keqing")){return new int[] {R.drawable.keqing_flag,R.string.keqing,R.drawable.keqing_full,R.drawable.keqing_ico};}
        else if(name.equals("Kaeya")){return new int[] {R.drawable.kaeya_flag,R.string.kaeya,R.drawable.kaeya_full,R.drawable.kaeya_ico};}
        else if(name.equals("Jean")){return new int[] {R.drawable.jean_flag,R.string.jean,R.drawable.jean_full,R.drawable.jean_ico};}
        else if(name.equals("Fischl")){return new int[] {R.drawable.fischl_flag,R.string.fischl,R.drawable.fischl_full,R.drawable.fischl_ico};}
        else if(name.equals("Diluc")){return new int[] {R.drawable.diluc_flag,R.string.diluc,R.drawable.diluc_full,R.drawable.diluc_ico};}
        else if(name.equals("Chongyun")){return new int[] {R.drawable.chongyun_flag,R.string.chongyun,R.drawable.chongyun_full,R.drawable.chongyun_ico};}
        else if(name.equals("Bennett")){return new int[] {R.drawable.bennett_flag,R.string.bennett,R.drawable.bennett_full,R.drawable.bennett_ico};}
        else if(name.equals("Beidou")){return new int[] {R.drawable.beidou_flag,R.string.beidou,R.drawable.beidou_full,R.drawable.beidou_ico};}
        else if(name.equals("Barbara")){return new int[] {R.drawable.barbara_flag,R.string.barbara,R.drawable.barbara_full,R.drawable.barbara_ico};}
        else if(name.equals("Amber")){return new int[] {R.drawable.amber_flag,R.string.amber,R.drawable.amber_full,R.drawable.amber_ico};}
        //Add at 20210820
        else if(name.equals("Traveler-Anemo")){return new int[] {R.drawable.traveler_female,R.string.traveler_anemo,R.drawable.traveler_full,R.drawable.traveler_ico};}
        else if(name.equals("Traveler-Geo")){return new int[] {R.drawable.traveler_female,R.string.traveler_geo,R.drawable.traveler_full,R.drawable.traveler_ico};}
        else if(name.equals("Traveler-Electro")){return new int[] {R.drawable.traveler_female,R.string.traveler_electro,R.drawable.traveler_full,R.drawable.traveler_ico};}

        return new int[] {R.drawable.paimon_lost,R.string.unknown,R.drawable.paimon_lost,R.drawable.paimon_lost};
    }

    /**
     * @param element Element Name
     * @return ICON_ID , RAD_BG_ID , BG_COLOR_ID
     */
    public int[] getElementByName (String element) {
        if(element.equals("Anemo")) {return new int[] {R.drawable.anemo,R.drawable.bg_anemo_char,R.color.anemo};}
        else if(element.equals("Cryo")) {return new int[] {R.drawable.cryo,R.drawable.bg_cryo_char,R.color.cryo};}
        else if(element.equals("Dendor")) {return new int[] {R.drawable.dendro,R.drawable.bg_dendro_char,R.color.dendor};}
        else if(element.equals("Electro")) {return new int[] {R.drawable.electro,R.drawable.bg_electro_char,R.color.electro};}
        else if(element.equals("Geo")) {return new int[] {R.drawable.geo,R.drawable.bg_geo_char,R.color.geo};}
        else if(element.equals("Hydro")) {return new int[] {R.drawable.hydro,R.drawable.bg_hydro_char,R.color.hydro};}
        else if(element.equals("Pyro")) {return new int[] {R.drawable.pyro,R.drawable.bg_pyro_char,R.color.pyro};}
        else return new int[] {R.drawable.paimon_lost,R.drawable.paimon_lost,R.color.anemo};
    }

    /*

    public int getTalentByName (String name) {
        return new int[] {0};
    }
     */

    public Drawable getTalentIcoByName (String name, Context context){
        InputStream ims = null;
        try {
            ims = context.getAssets().open("skills/"+name+".png");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            return d;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getItemIcoByName (String name ,Context context){
        if(name.equals("常燃火種")){return R.drawable.everflame_seed;}
        else if(name.equals("淨水之心")){return R.drawable.cleansing_heart;}
        else if(name.equals("雷光棱鏡")){return R.drawable.lightning_prism;}
        else if(name.equals("極寒之核")){return R.drawable.hoarfrost_core;}
        else if(name.equals("颶風之種")){return R.drawable.hurricane_seed;}
        else if(name.equals("玄岩之塔")){return R.drawable.basalt_pillar;}
        else if(name.equals("未熟之玉")){return R.drawable.juvenile_jade;}
        else if(name.equals("晶凝之華")){return R.drawable.crystalline_bloom;}
        else if(name.equals("魔偶機心")){return R.drawable.maguu_kishin;}
        else if(name.equals("恒常機關之心")){return R.drawable.perpetual_heart;}
        else if(name.equals("陰燃之珠")){return R.drawable.smoldering_pearl;}
        else if(name.equals("小燈草")){return R.drawable.small_lamp_grass;}
        else if(name.equals("慕風蘑菇")){return R.drawable.philanemo_mushroom;}
        else if(name.equals("夜泊石")){return R.drawable.noctilous_jade;}
        else if(name.equals("風車菊")){return R.drawable.windwheel_aster;}
        else if(name.equals("石珀")){return R.drawable.cor_lapis;}
        else if(name.equals("蒲公英籽")){return R.drawable.dandelion_seed;}
        else if(name.equals("嘟嘟蓮")){return R.drawable.calla_lily;}
        else if(name.equals("落落莓")){return R.drawable.valberry;}
        else if(name.equals("琉璃百合")){return R.drawable.glaze_lily;}
        else if(name.equals("琉璃袋")){return R.drawable.violetgrass;}
        else if(name.equals("鉤鉤果")){return R.drawable.wolfhook;}
        else if(name.equals("塞西莉亞花")){return R.drawable.cecilia;}
        else if(name.equals("絕雲椒椒")){return R.drawable.jueyun_chili;}
        else if(name.equals("霓裳花")){return R.drawable.silk_flower;}
        else if(name.equals("星螺")){return R.drawable.starconch;}
        else if(name.equals("清心")){return R.drawable.qingxin;}
        else if(name.equals("海靈芝")){return R.drawable.sea_ganoderma;}
        else if(name.equals("緋櫻繡球")){return R.drawable.sakura_bloom;}
        else if(name.equals("鳴草")){return R.drawable.naku_weed;}
        else if(name.equals("晶化骨髓")){return R.drawable.crystal_marrow;}
        else if(name.equals("北風之環")){return R.drawable.ring_of_boreas;}
        else if(name.equals("東風的吐息")){return R.drawable.dvalins_sigh;}
        else if(name.equals("東風之翎")){return R.drawable.dvalins_plume;}
        else if(name.equals("北風的魂匣")){return R.drawable.spirit_locket_of_boreas;}
        else if(name.equals("東風之爪")){return R.drawable.dvalins_claw;}
        else if(name.equals("北風之尾")){return R.drawable.tail_of_boreas;}
        else if(name.equals("魔王之刃·殘片")){return R.drawable.shard_of_foul_legacy;}
        else if(name.equals("吞天之鯨·只角")){return R.drawable.tusk_of_monoceros_caeli;}
        else if(name.equals("武煉之魂·孤影")){return R.drawable.shadow_of_the_warrior;}
        else if(name.equals("龍王之冕")){return R.drawable.dragon_lords_crown;}
        else if(name.equals("血玉之枝")){return R.drawable.bloodjade_branch;}
        else if(name.equals("鎏金之鱗")){return R.drawable.gilded_scale;}

        else if(name.equals("牢固的箭簇")){return R.drawable.firm_arrowhead;}
        else if(name.equals("銳利的箭簇")){return R.drawable.sharp_arrowhead;}
        else if(name.equals("歷戰的箭簇")){return R.drawable.weathered_arrowhead;}
        else if(name.equals("導能繪卷")){return R.drawable.divining_scroll;}
        else if(name.equals("封魔繪卷")){return R.drawable.sealed_scroll;}
        else if(name.equals("禁咒繪卷")){return R.drawable.forbidden_curse_scroll;}
        else if(name.equals("尋寶鴉印")){return R.drawable.treasure_hoarder_insignia;}
        else if(name.equals("藏銀鴉印")){return R.drawable.silver_raven_insignia;}
        else if(name.equals("攫金鴉印")){return R.drawable.golden_raven_insignia;}
        else if(name.equals("破損的面具")){return R.drawable.damaged_mask;}
        else if(name.equals("污穢的面具")){return R.drawable.stained_mask;}
        else if(name.equals("不祥的面具")){return R.drawable.ominous_mask;}
        else if(name.equals("新兵的徽記")){return R.drawable.recruits_insignia;}
        else if(name.equals("士官的徽記")){return R.drawable.sergeants_insignia;}
        else if(name.equals("尉官的徽記")){return R.drawable.lieutenants_insignia;}
        else if(name.equals("騙騙花蜜")){return R.drawable.whopperflower_nectar;}
        else if(name.equals("微光花蜜")){return R.drawable.shimmering_nectar;}
        else if(name.equals("原素花蜜")){return R.drawable.energy_nectar;}
        else if(name.equals("史萊姆凝液")){return R.drawable.slime_condensate;}
        else if(name.equals("史萊姆清")){return R.drawable.slime_secretions;}
        else if(name.equals("史萊姆原漿")){return R.drawable.slime_concentrate;}
        else if(name.equals("破舊的刀鐔")){return R.drawable.old_handguard;}
        else if(name.equals("影打刀鐔")){return R.drawable.kageuchi_handguard;}
        else if(name.equals("名刀鐔")){return R.drawable.famed_handguard;}
        else if(name.equals("「自由」的教導")){return R.drawable.teaching_of_freedom;}
        else if(name.equals("「黃金」的教導")){return R.drawable.teaching_of_gold;}
        else if(name.equals("「抗爭」的教導")){return R.drawable.teaching_of_resistance;}
        else if(name.equals("「勤勞」的教導")){return R.drawable.teaching_of_prosperity;}
        else if(name.equals("「詩文」的教導")){return R.drawable.teaching_of_ballad;}
        else if(name.equals("「繁榮」的教導")){return R.drawable.teaching_of_diligence;}
        else if(name.equals("「風雅」的教導")){return R.drawable.teachings_of_elegance;}
        else if(name.equals("「浮世」的教導")){return R.drawable.teachings_of_transience;}
        else if(name.equals("「天光」的教導")){return R.drawable.teachings_of_light;}
        else if(name.equals("「自由」的指引")){return R.drawable.guide_to_freedom;}
        else if(name.equals("「黃金」的指引")){return R.drawable.guide_to_gold;}
        else if(name.equals("「抗爭」的指引")){return R.drawable.guide_to_resistance;}
        else if(name.equals("「勤勞」的指引")){return R.drawable.guide_to_prosperity;}
        else if(name.equals("「詩文」的指引")){return R.drawable.guide_to_ballad;}
        else if(name.equals("「繁榮」的指引")){return R.drawable.guide_to_diligence;}
        else if(name.equals("「風雅」的指引")){return R.drawable.guide_of_elegance;}
        else if(name.equals("「浮世」的指引")){return R.drawable.guide_of_transience;}
        else if(name.equals("「天光」的指引")){return R.drawable.guide_of_light;}
        else if(name.equals("「自由」的哲學")){return R.drawable.philosophies_of_freedom;}
        else if(name.equals("「黃金」的哲學")){return R.drawable.philosophies_of_gold;}
        else if(name.equals("「抗爭」的哲學")){return R.drawable.philosophies_of_resistance;}
        else if(name.equals("「勤勞」的哲學")){return R.drawable.philosophies_of_prosperity;}
        else if(name.equals("「詩文」的哲學")){return R.drawable.philosophies_of_ballad;}
        else if(name.equals("「繁榮」的哲學")){return R.drawable.philosophies_of_diligence;}
        else if(name.equals("「風雅」的哲學")){return R.drawable.philosophies_of_elegance;}
        else if(name.equals("「浮世」的哲學")){return R.drawable.philosophies_of_transience;}
        else if(name.equals("「天光」的哲學")){return R.drawable.philosophies_of_light;}
        else if(name.equals("燃願瑪瑙碎屑")){return R.drawable.agnidus_agate_sliver;}
        else if(name.equals("燃願瑪瑙斷片")){return R.drawable.agnidus_agate_fragment;}
        else if(name.equals("燃願瑪瑙塊")){return R.drawable.agnidus_agate_chunk;}
        else if(name.equals("燃願瑪瑙")){return R.drawable.agnidus_agate_gemstone;}
        else if(name.equals("滌淨青金碎屑")){return R.drawable.varunada_lazurite_sliver;}
        else if(name.equals("滌淨青金斷片")){return R.drawable.varunada_lazurite_fragment;}
        else if(name.equals("滌淨青金塊")){return R.drawable.varunada_lazurite_chunk;}
        else if(name.equals("滌淨青金")){return R.drawable.varunada_lazurite_gemstone;}
        else if(name.equals("最勝紫晶碎屑")){return R.drawable.vajrada_amethyst_sliver;}
        else if(name.equals("最勝紫晶斷片")){return R.drawable.vajrada_amethyst_fragment;}
        else if(name.equals("最勝紫晶塊")){return R.drawable.vajrada_amethyst_chunk;}
        else if(name.equals("最勝紫晶")){return R.drawable.vajrada_amethyst_gemstone;}
        else if(name.equals("哀敘冰玉碎屑")){return R.drawable.shivada_jade_sliver;}
        else if(name.equals("哀敘冰玉斷片")){return R.drawable.shivada_jade_fragment;}
        else if(name.equals("哀敘冰玉塊")){return R.drawable.shivada_jade_chunk;}
        else if(name.equals("哀敘冰玉")){return R.drawable.shivada_jade_gemstone;}
        else if(name.equals("自在松石碎屑")){return R.drawable.vayuda_turquoise_sliver;}
        else if(name.equals("自在松石斷片")){return R.drawable.vayuda_turquoise_fragment;}
        else if(name.equals("自在松石塊")){return R.drawable.vayuda_turquoise_chunk;}
        else if(name.equals("自在松石")){return R.drawable.vayuda_turquoise_gemstone;}
        else if(name.equals("堅牢黃玉碎屑")){return R.drawable.prithiva_topaz_sliver;}
        else if(name.equals("堅牢黃玉斷片")){return R.drawable.prithiva_topaz_fragment;}
        else if(name.equals("堅牢黃玉塊")){return R.drawable.prithiva_topaz_chunk;}
        else if(name.equals("堅牢黃玉")){return R.drawable.prithiva_topaz_gemstone;}
        else if(name.equals("brilliant_diamond_sliver")){return R.drawable.brilliant_diamond_sliver;}
        else if(name.equals("brilliant_diamond_fragment")){return R.drawable.brilliant_diamond_fragment;}
        else if(name.equals("brilliant_diamond_chunk")){return R.drawable.brilliant_diamond_chunk;}
        else if(name.equals("brilliant_diamond_gemstone")){return R.drawable.brilliant_diamond_gemstone;}
        else if(name.equals("nagadus_emerald_sliver")){return R.drawable.nagadus_emerald_sliver;}
        else if(name.equals("nagadus_emerald_fragment")){return R.drawable.nagadus_emerald_fragment;}
        else if(name.equals("nagadus_emerald_chunk")){return R.drawable.nagadus_emerald_chunk;}
        else if(name.equals("摩拉")){return R.drawable.mora;}
        else if(name.equals("流浪者的經驗")){return R.drawable.wanderers_advice;}
        else if(name.equals("冒險家的經驗")){return R.drawable.adventurers_experience;}
        else if(name.equals("大英雄的經驗")){return R.drawable.heros_wit;}

        else {return R.drawable.paimon_lost;}
    }
}
