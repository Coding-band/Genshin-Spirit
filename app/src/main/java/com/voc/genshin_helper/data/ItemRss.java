package com.voc.genshin_helper.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;

import com.voc.genshin_helper.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */
public class ItemRss {

    SharedPreferences sharedPreferences;

    /**EDIT WHEN ADD NEW ITEMS*/
    public int[] char_names = {R.string.yae_miko,R.string.shenhe,R.string.yun_jin,R.string.gorou, R.string.arataki_itto, R.string.thoma, R.string.sangonomiya_kokomi, R.string.aloy, R.string.kujou_sara, R.string.raiden_shogun, R.string.sayu, R.string.yoimiya, R.string.kamisato_ayaka, R.string.kaedehara_kazuha, R.string.yanfei, R.string.eula, R.string.rosaria, R.string.xiao, R.string.hu_tao, R.string.ganyu, R.string.albedo, R.string.zhongli, R.string.xinyan, R.string.tartaglia, R.string.diona, R.string.xingqiu, R.string.xiangling, R.string.venti, R.string.sucrose, R.string.razor, R.string.qiqi, R.string.noelle, R.string.ningguang, R.string.mona, R.string.lisa, R.string.klee, R.string.keqing, R.string.kaeya, R.string.jean, R.string.fischl, R.string.diluc, R.string.chongyun, R.string.bennett, R.string.beidou, R.string.barbara, R.string.amber, R.string.traveler_anemo, R.string.traveler_geo, R.string.traveler_electro};
    public int[] weapons_name = {R.string.calamity_queller,R.string.redhorn_stonethresher,R.string.akuoumaru,R.string.akuoumaru,R.string.mouuns_moon,R.string.wavebreakers_fin,R.string.amenoma_kageuchi, R.string.aquila_favonia, R.string.blackcliff_longsword, R.string.cool_steel, R.string.dark_iron_sword, R.string.dull_blade, R.string.favonius_sword, R.string.festering_desire, R.string.fillet_blade, R.string.freedom_sworn, R.string.harbinger_of_dawn, R.string.iron_sting, R.string.lions_roar, R.string.mistsplitter_reforged, R.string.primordial_jade_cutter, R.string.prototype_rancour, R.string.royal_longsword, R.string.sacrificial_sword, R.string.silver_sword, R.string.skyrider_sword, R.string.skyward_blade, R.string.summit_shaper, R.string.sword_of_descension, R.string.the_alley_flash, R.string.the_black_sword, R.string.the_flute, R.string.travelers_handy_sword, R.string.waster_greatsword, R.string.old_mercs_pal, R.string.bloodtainted_greatsword, R.string.debate_club, R.string.quartz, R.string.ferrous_shadow, R.string.skyrider_greatsword, R.string.white_iron_greatsword, R.string.blackcliff_slasher, R.string.favonius_greatsword, R.string.katsuragikiri_nagamasa, R.string.lithic_blade, R.string.luxurious_sea_lord, R.string.prototype_archaic, R.string.rainslasher, R.string.royal_greatsword, R.string.sacrificial_greatsword, R.string.serpent_spine, R.string.snow_tombed_starsilver, R.string.the_bell, R.string.whiteblind, R.string.skyward_pride, R.string.song_of_broken_pines, R.string.the_unforged, R.string.wolfs_gravestone, R.string.beginners_protector, R.string.iron_point, R.string.black_tassel, R.string.halberd, R.string.white_tassel, R.string.blackcliff_pole, R.string.crescent_pike, R.string.deathmatch, R.string.dragons_bane, R.string.dragonspine_spear, R.string.favonius_lance, R.string.kitain_cross_spear, R.string.lithic_spear, R.string.prototype_starglitter, R.string.royal_spear, R.string.the_catch, R.string.engulfing_lightning, R.string.primordial_jade_winged_spear, R.string.skyward_spine, R.string.staff_of_homa, R.string.vortex_vanquisher, R.string.apprentices_notes, R.string.pocket_grimoire, R.string.amber_catalyst, R.string.emerald_orb, R.string.magic_guide, R.string.otherworldly_story, R.string.thrilling_tales_of_dragon_slayers, R.string.twin_nephrite, R.string.blackcliff_agate, R.string.dodoco_tales, R.string.eye_of_perception, R.string.favonius_codex, R.string.frostbearer, R.string.hakushin_ring, R.string.mappa_mare, R.string.prototype_amber, R.string.royal_grimoire, R.string.sacrificial_fragments, R.string.solar_pearl, R.string.the_widsith, R.string.wine_and_song, R.string.everlasting_moonglow, R.string.lost_prayer_to_the_sacred_winds, R.string.memory_of_dust, R.string.skyward_atlas, R.string.hunters_bow, R.string.seasoned_hunters_bow, R.string.ebony_bow, R.string.messenger, R.string.raven_bow, R.string.recurve_bow, R.string.sharpshooters_oath, R.string.slingshot, R.string.alley_hunter, R.string.blackcliff_warbow, R.string.compound_bow, R.string.favonius_warbow, R.string.hamayumi, R.string.mitternachts_waltz, R.string.predator, R.string.prototype_crescent, R.string.royal_bow, R.string.rust, R.string.sacrificial_bow, R.string.the_stringless, R.string.the_viridescent_hunt, R.string.windblume_ode, R.string.amos_bow, R.string.elegy_for_the_end, R.string.skyward_harp, R.string.thundering_pulse, R.string.cinnabar_spindle};
    public int[] artifact_name = {R.string.adventurer, R.string.archaic_petra, R.string.berserker, R.string.blizzard_strayer, R.string.bloodstained_chivalry, R.string.brave_heart, R.string.crimson_witch_of_flames, R.string.defenders_will, R.string.emblem_of_severed_fate, R.string.gambler, R.string.gladiators_finale, R.string.heart_of_depth, R.string.instructor, R.string.lavawalker, R.string.lucky_dog, R.string.maiden_beloved, R.string.martial_artist, R.string.noblesse_oblige, R.string.pale_flame, R.string.prayers_of_destiny, R.string.prayers_of_illumination, R.string.prayers_of_wisdom, R.string.prayers_of_springtime, R.string.resolution_of_sojourner, R.string.retracing_bolide, R.string.scholar, R.string.shimenawas_reminiscence, R.string.tenacity_of_the_millelith, R.string.the_exile, R.string.thundering_fury, R.string.thundersoother, R.string.tiny_miracle, R.string.traveling_doctor, R.string.viridescent_venerer, R.string.wanderers_troupe,R.string.husk_of_opulent_dreams,R.string.ocean_hued_clam};

    public String getLocaleName (String str,Context context) {
        /** Area Name */
        if (str.equals("Mondstadt")){return context.getString(R.string.mondstadt);}
        else if (str.equals("Liyue")){return context.getString(R.string.liyue);}
        else if (str.equals("Inazuma")){return context.getString(R.string.inazuma);}
        else if (str.equals("Nora Fortis")){return context.getString(R.string.nora_fortis);}
        else if (str.equals("Snezhnaya")){return context.getString(R.string.snezhnaya);}
        else if (str.equals("Another World")){return context.getString(R.string.another_world);}
        /** Char's Role Name*/
        else if (str.equals("Main_DPS")){return context.getString(R.string.main_dps);}
        else if (str.equals("Support_DPS")){return context.getString(R.string.support_dps);}
        else if (str.equals("Utility")){return context.getString(R.string.utility);}
        /** Sex Name */
        else if (str.equals("Female")){return context.getString(R.string.female);}
        else if (str.equals("Male")){return context.getString(R.string.male);}
        else if (str.equals("SET_BY_PLAYER")){return context.getString(R.string.set_by_player);}

        else {return str;}
    }
    public String LocaleCharStr(int i, Context context) {
        return context.getString(this.char_names[i]);
    }

    public String LocaleArtifactStr(int i, Context context) {
        return context.getString(this.artifact_name[i]);
    }

    public String LocaleWeaponStr(int i, Context context) {
        return context.getString(this.weapons_name[i]);
    }


    public int[] getRareColorByName(int i) {
        switch (i){
            case 1 : return new int[]{R.drawable.bg_rare1_bg, R.drawable.bg_rare1_char, R.color.rare1};
            case 2 : return new int[]{R.drawable.bg_rare2_bg, R.drawable.bg_rare2_char, R.color.rare2};
            case 3 : return new int[]{R.drawable.bg_rare3_bg, R.drawable.bg_rare3_char, R.color.rare3};
            case 4 : return new int[]{R.drawable.bg_rare4_bg, R.drawable.bg_rare4_char, R.color.rare4};
            case 5 : return new int[]{R.drawable.bg_rare5_bg, R.drawable.bg_rare5_char, R.color.rare5};
            default: return new int[]{R.drawable.paimon_lost, R.drawable.paimon_lost, R.color.rare1};
        }
    }


    public int getWeaponTypeIMG (String str){
        if(str.equals("Bow")){return R.drawable.ico_bow;}
        else if(str.equals("Catalyst")){return R.drawable.ico_catalyst;}
        else if(str.equals("Claymore")){return R.drawable.ico_claymore;}
        else if(str.equals("Polearm")){return R.drawable.ico_polearm;}
        else if(str.equals("Sword")){return R.drawable.ico_sword;}
        else {return R.drawable.paimon_lost;}
    }
    public int getDistrictIMG (String str){
        if(str.equals("Mondstadt")){return R.drawable.mondstadt_ico;}
        else if(str.equals("Liyue")){return R.drawable.liyue_ico;}
        else if(str.equals("Inazuma")){return R.drawable.inazuma_ico;}
        else {return R.drawable.unknown;}
    }

    // add in 20220207
    public String[] getEnemyByName(String str, Context context){
        switch (str){
            case "Pyro Slime" : return new String[]{context.getString(R.string.pyro_slime),"/drawable/pyro_slime.png"};
            case "Large Pyro Slime" : return new String[]{context.getString(R.string.large_pyro_slime),"/drawable/large_pyro_slime.png"};
            case "Electro Slime" : return new String[]{context.getString(R.string.electro_slime),"/drawable/electro_slime.png"};
            case "Large Electro Slime" : return new String[]{context.getString(R.string.large_electro_slime),"/drawable/large_electro_slime.png"};
            case "Mutant Electro Slime" : return new String[]{context.getString(R.string.mutant_electro_slime),"/drawable/mutant_electro_slime.png"};
            case "Cryo Slime" : return new String[]{context.getString(R.string.cryo_slime),"/drawable/cryo_slime.png"};
            case "Large Cryo Slime" : return new String[]{context.getString(R.string.large_cryo_slime),"/drawable/large_cryo_slime.png"};
            case "Hydro Slime" : return new String[]{context.getString(R.string.hydro_slime),"/drawable/hydro_slime.png"};
            case "Large Hydro Slime" : return new String[]{context.getString(R.string.large_hydro_slime),"/drawable/large_hydro_slime.png"};
            case "Anemo Slime" : return new String[]{context.getString(R.string.anemo_slime),"/drawable/anemo_slime.png"};
            case "Large Anemo Slime" : return new String[]{context.getString(R.string.large_anemo_slime),"/drawable/large_anemo_slime.png"};
            case "Geo Slime" : return new String[]{context.getString(R.string.geo_slime),"/drawable/geo_slime.png"};
            case "Large Geo Slime" : return new String[]{context.getString(R.string.large_geo_slime),"/drawable/large_geo_slime.png"};
            case "Dendro Slime" : return new String[]{context.getString(R.string.dendro_slime),"/drawable/dendro_slime.png"};
            case "Large Dendro Slime" : return new String[]{context.getString(R.string.large_dendro_slime),"/drawable/large_dendro_slime.png"};
            case "Eye of the Storm" : return new String[]{context.getString(R.string.eye_of_the_storm),"/drawable/eye_of_the_storm.png"};
            case "Pyro Hypostasis" : return new String[]{context.getString(R.string.pyro_hypostasis),"/drawable/pyro_hypostasis.png"};
            case "Electro Hypostasis" : return new String[]{context.getString(R.string.electro_hypostasis),"/drawable/electro_hypostasis.png"};
            case "Cryo Hypostasis" : return new String[]{context.getString(R.string.cryo_hypostasis),"/drawable/cryo_hypostasis.png"};
            case "Hydro Hypostasis" : return new String[]{context.getString(R.string.hydro_hypostasis),"/drawable/hydro_hypostasis.png"};
            case "Anemo Hypostasis" : return new String[]{context.getString(R.string.anemo_hypostasis),"/drawable/anemo_hypostasis.png"};
            case "Geo Hypostasis" : return new String[]{context.getString(R.string.geo_hypostasis),"/drawable/geo_hypostasis.png"};
            case "Oceanid" : return new String[]{context.getString(R.string.oceanid),"/drawable/oceanid.png"};
            case "Hydro Specter" : return new String[]{context.getString(R.string.hydro_specter),"/drawable/hydro_specter.png"};
            case "Geo Specter" : return new String[]{context.getString(R.string.geo_specter),"/drawable/geo_specter.png"};
            case "Anemo Specter" : return new String[]{context.getString(R.string.anemo_specter),"/drawable/anemo_specter.png"};
            case "Cryo Specter" : return new String[]{context.getString(R.string.cryo_specter),"/drawable/cryo_specter.png"};
            case "Electro Specter" : return new String[]{context.getString(R.string.electro_specter),"/drawable/electro_specter.png"};
            case "Pyro Specter" : return new String[]{context.getString(R.string.pyro_specter),"/drawable/pyro_specter.png"};
            case "Thunder Manifestation" : return new String[]{context.getString(R.string.thunder_manifestation),"/drawable/thunder_manifestation.png"};
            case "Hilichurl" : return new String[]{context.getString(R.string.hilichurl),"/drawable/hilichurl.png"};
            case "Hilichurl Fighter" : return new String[]{context.getString(R.string.hilichurl_fighter),"/drawable/hilichurl_fighter.png"};
            case "Wooden Shield Hilichurl Guard" : return new String[]{context.getString(R.string.wooden_shield_hilichurl_guard),"/drawable/wooden_shield_hilichurl_guard.png"};
            case "Hilichurl Shooter" : return new String[]{context.getString(R.string.hilichurl_shooter),"/drawable/hilichurl_shooter.png"};
            case "Pyro Hilichurl Shooter" : return new String[]{context.getString(R.string.pyro_hilichurl_shooter),"/drawable/pyro_hilichurl_shooter.png"};
            case "Hilichurl Grenadier" : return new String[]{context.getString(R.string.hilichurl_grenadier),"/drawable/hilichurl_grenadier.png"};
            case "Hilichurl Berserker" : return new String[]{context.getString(R.string.hilichurl_berserker),"/drawable/hilichurl_berserker.png"};
            case "Cryo Hilichurl Shooter" : return new String[]{context.getString(R.string.cryo_hilichurl_shooter),"/drawable/cryo_hilichurl_shooter.png"};
            case "Electro Hilichurl Shooter" : return new String[]{context.getString(R.string.electro_hilichurl_shooter),"/drawable/electro_hilichurl_shooter.png"};
            case "Rock Shield Hilichurl Guard" : return new String[]{context.getString(R.string.rock_shield_hilichurl_guard),"/drawable/rock_shield_hilichurl_guard.png"};
            case "Cryo Hilichurl Grenadier" : return new String[]{context.getString(R.string.cryo_hilichurl_grenadier),"/drawable/cryo_hilichurl_grenadier.png"};
            case "Ice Shield Hilichurl Guard" : return new String[]{context.getString(R.string.ice_shield_hilichurl_guard),"/drawable/ice_shield_hilichurl_guard.png"};
            case "Unusual Hilichurl" : return new String[]{context.getString(R.string.unusual_hilichurl),"/drawable/unusual_hilichurl.png"};
            case "Electro Hilichurl Grenadier" : return new String[]{context.getString(R.string.electro_hilichurl_grenadier),"/drawable/electro_hilichurl_grenadier.png"};
            case "Wooden Shieldwall Mitachurl" : return new String[]{context.getString(R.string.wooden_shieldwall_mitachurl),"/drawable/wooden_shieldwall_mitachurl.png"};
            case "Blazing Axe Mitachurl" : return new String[]{context.getString(R.string.blazing_axe_mitachurl),"/drawable/blazing_axe_mitachurl.png"};
            case "Rock Shieldwall Mitachurl" : return new String[]{context.getString(R.string.rock_shieldwall_mitachurl),"/drawable/rock_shieldwall_mitachurl.png"};
            case "Frostarm Lawachurl" : return new String[]{context.getString(R.string.frostarm_lawachurl),"/drawable/frostarm_lawachurl.png"};
            case "Stonehide Lawachurl" : return new String[]{context.getString(R.string.stonehide_lawachurl),"/drawable/stonehide_lawachurl.png"};
            case "Thunderhelm Lawachurl" : return new String[]{context.getString(R.string.thunderhelm_lawachurl),"/drawable/thunderhelm_lawachurl.png"};
            case "Ice Shieldwall Mitachurl" : return new String[]{context.getString(R.string.ice_shieldwall_mitachurl),"/drawable/ice_shieldwall_mitachurl.png"};
            case "Crackling Axe Mitachurl" : return new String[]{context.getString(R.string.crackling_axe_mitachurl),"/drawable/crackling_axe_mitachurl.png"};
            case "Hydro Samachurl" : return new String[]{context.getString(R.string.hydro_samachurl),"/drawable/hydro_samachurl.png"};
            case "Dendro Samachurl" : return new String[]{context.getString(R.string.dendro_samachurl),"/drawable/dendro_samachurl.png"};
            case "Anemo Samachurl" : return new String[]{context.getString(R.string.anemo_samachurl),"/drawable/anemo_samachurl.png"};
            case "Geo Samachurl" : return new String[]{context.getString(R.string.geo_samachurl),"/drawable/geo_samachurl.png"};
            case "Cryo Samachurl" : return new String[]{context.getString(R.string.cryo_samachurl),"/drawable/cryo_samachurl.png"};
            case "Electro Samachurl" : return new String[]{context.getString(R.string.electro_samachurl),"/drawable/electro_samachurl.png"};
            case "Pyro Abyss Mage" : return new String[]{context.getString(R.string.pyro_abyss_mage),"/drawable/pyro_abyss_mage.png"};
            case "Cryo Abyss Mage" : return new String[]{context.getString(R.string.cryo_abyss_mage),"/drawable/cryo_abyss_mage.png"};
            case "Hydro Abyss Mage" : return new String[]{context.getString(R.string.hydro_abyss_mage),"/drawable/hydro_abyss_mage.png"};
            case "Electro Abyss Mage" : return new String[]{context.getString(R.string.electro_abyss_mage),"/drawable/electro_abyss_mage.png"};
            case "Abyss Herald: Wicked Torrents" : return new String[]{context.getString(R.string.abyss_herald_wicked_torrents),"/drawable/abyss_herald_wicked_torrents.png"};
            case "Abyss Lector: Violet Lightning" : return new String[]{context.getString(R.string.abyss_lector_violet_lightning),"/drawable/abyss_lector_violet_lightning.png"};
            case "Abyss Lector: Fathomless Flames" : return new String[]{context.getString(R.string.abyss_lector_fathomless_flames),"/drawable/abyss_lector_fathomless_flames.png"};
            case "Rockfond Rifthound Whelp" : return new String[]{context.getString(R.string.rockfond_rifthound_whelp),"/drawable/rockfond_rifthound_whelp.png"};
            case "Thundercraven Rifthound Whelp" : return new String[]{context.getString(R.string.thundercraven_rifthound_whelp),"/drawable/thundercraven_rifthound_whelp.png"};
            case "Rockfond Rifthound" : return new String[]{context.getString(R.string.rockfond_rifthound),"/drawable/rockfond_rifthound.png"};
            case "Thundercraven Rifthound" : return new String[]{context.getString(R.string.thundercraven_rifthound),"/drawable/thundercraven_rifthound.png"};
            case "Beginning : Golden Wolflord" : return new String[]{context.getString(R.string.beginning_golden_wolflord),"/drawable/golden_wolflord.png"};
            case "Golden Wolflord" : return new String[]{context.getString(R.string.golden_wolflord),"/drawable/golden_wolflord.png"};
            case "Head-Broken : Golden Wolflord" : return new String[]{context.getString(R.string.headbroken_golden_wolflord),"/drawable/golden_wolflord.png"};
            case "Shadowy Husk: Standard Bearer" : return new String[]{context.getString(R.string.shadowy_husk_standard_bearer),"/drawable/shadowy_husk_standard_bearer.png"};
            case "Shadowy Husk: Line Breaker" : return new String[]{context.getString(R.string.shadowy_husk_line_breaker),"/drawable/shadowy_husk_line_breaker.png"};
            case "Shadowy Husk: Defender" : return new String[]{context.getString(R.string.shadowy_husk_defender),"/drawable/shadowy_husk_defender.png"};
            case "Fatui Skirmisher - Cryogunner Legionnaire" : return new String[]{context.getString(R.string.fatui_skirmisher_cryogunner_legionnaire),"/drawable/fatui_skirmisher_cryogunner_legionnaire.png"};
            case "Fatui Skirmisher - Hydrogunner Legionnaire" : return new String[]{context.getString(R.string.fatui_skirmisher_hydrogunner_legionnaire),"/drawable/fatui_skirmisher_hydrogunner_legionnaire.png"};
            case "Fatui Skirmisher - Electrohammer Vanguard" : return new String[]{context.getString(R.string.fatui_skirmisher_electrohammer_vanguard),"/drawable/fatui_skirmisher_electrohammer_vanguard.png"};
            case "Fatui Skirmisher - Geochanter Bracer" : return new String[]{context.getString(R.string.fatui_skirmisher_geochanter_bracer),"/drawable/fatui_skirmisher_geochanter_bracer.png"};
            case "Fatui Skirmisher - Anemoboxer Vanguard" : return new String[]{context.getString(R.string.fatui_skirmisher_anemoboxer_vanguard),"/drawable/fatui_skirmisher_anemoboxer_vanguard.png"};
            case "Fatui Skirmisher - Pyroslinger Bracer" : return new String[]{context.getString(R.string.fatui_skirmisher_pyroslinger_bracer),"/drawable/fatui_skirmisher_pyroslinger_bracer.png"};
            case "Fatui Pyro Agent" : return new String[]{context.getString(R.string.fatui_pyro_agent),"/drawable/fatui_pyro_agent.png"};
            case "Fatui Electro Cicin Mage" : return new String[]{context.getString(R.string.fatui_electro_cicin_mage),"/drawable/fatui_electro_cicin_mage.png"};
            case "Fatui Cryo Cicin Mage" : return new String[]{context.getString(R.string.fatui_cryo_cicin_mage),"/drawable/fatui_cryo_cicin_mage.png"};
            case "Mirror Maiden" : return new String[]{context.getString(R.string.mirror_maiden),"/drawable/mirror_maiden.png"};
            case "Ruin Guard" : return new String[]{context.getString(R.string.ruin_guard),"/drawable/ruin_guard.png"};
            case "Ruin Hunter" : return new String[]{context.getString(R.string.ruin_hunter),"/drawable/ruin_hunter.png"};
            case "Ruin Grader" : return new String[]{context.getString(R.string.ruin_grader),"/drawable/ruin_grader.png"};
            case "Ruin Cruiser" : return new String[]{context.getString(R.string.ruin_cruiser),"/drawable/ruin_cruiser.png"};
            case "Ruin Destroyer" : return new String[]{context.getString(R.string.ruin_destroyer),"/drawable/ruin_destroyer.png"};
            case "Ruin Defender" : return new String[]{context.getString(R.string.ruin_defender),"/drawable/ruin_defender.png"};
            case "Ruin Scout" : return new String[]{context.getString(R.string.ruin_scout),"/drawable/ruin_scout.png"};
            case "Perpetual Mechanical Array" : return new String[]{context.getString(R.string.perpetual_mechanical_array),"/drawable/perpetual_mechanical_array.png"};
            case "Stunned Perpetual Mechanical Array" : return new String[]{context.getString(R.string.stunned_perpetual_mechanical_array),"/drawable/perpetual_mechanical_array.png"};
            case "Treasure Hoarders - Liuliu" : return new String[]{context.getString(R.string.treasure_hoarders_liuliu),"/drawable/treasure_hoarders_liuliu.png"};
            case "Treasure Hoarders - Scout" : return new String[]{context.getString(R.string.treasure_hoarders_scout),"/drawable/treasure_hoarders_scout.png"};
            case "Treasure Hoarders: Pyro Potioneer" : return new String[]{context.getString(R.string.treasure_hoarders_pyro_potioneer),"/drawable/treasure_hoarders_pyro_potioneer.png"};
            case "Treasure Hoarders: Hydro Potioneer" : return new String[]{context.getString(R.string.treasure_hoarders_hydro_potioneer),"/drawable/treasure_hoarders_hydro_potioneer.png"};
            case "Treasure Hoarders: Electro Potioneer" : return new String[]{context.getString(R.string.treasure_hoarders_electro_potioneer),"/drawable/treasure_hoarders_electro_potioneer.png"};
            case "Treasure Hoarders: Cryo Potioneer" : return new String[]{context.getString(R.string.treasure_hoarders_cryo_potioneer),"/drawable/treasure_hoarders_cryo_potioneer.png"};
            case "Treasure Hoarders: Handyman" : return new String[]{context.getString(R.string.treasure_hoarders_handyman),"/drawable/treasure_hoarders_handyman.png"};
            case "Treasure Hoarders - Raptor" : return new String[]{context.getString(R.string.treasure_hoarders_raptor),"/drawable/treasure_hoarders_raptor.png"};
            case "Treasure Hoarders - Marksman" : return new String[]{context.getString(R.string.treasure_hoarders_marksman),"/drawable/treasure_hoarders_marksman.png"};
            case "Treasure Hoarders - Carmen" : return new String[]{context.getString(R.string.treasure_hoarders_carmen),"/drawable/treasure_hoarders_carmen.png"};
            case "Treasure Hoarders: Gravedigger" : return new String[]{context.getString(R.string.treasure_hoarders_gravedigger),"/drawable/treasure_hoarders_gravedigger.png"};
            case "Treasure Hoarders - Seaman" : return new String[]{context.getString(R.string.treasure_hoarders_seaman),"/drawable/treasure_hoarders_seaman.png"};
            case "Treasure Hoarders - Boss" : return new String[]{context.getString(R.string.treasure_hoarders_boss),"/drawable/treasure_hoarders_boss.png"};
            case "Millelith Soldier" : return new String[]{context.getString(R.string.millelith_soldier),"/drawable/millelith_soldier.png"};
            case "Millelith Sergeant" : return new String[]{context.getString(R.string.millelith_sergeant),"/drawable/millelith_sergeant.png"};
            case "Treasure Hoarders - Pugilist" : return new String[]{context.getString(R.string.treasure_hoarders_pugilist),"/drawable/treasure_hoarders_pugilist.png"};
            case "Treasure Hoarders - Crusher" : return new String[]{context.getString(R.string.treasure_hoarders_crusher),"/drawable/treasure_hoarders_crusher.png"};
            case "Nobushi: Jintouban" : return new String[]{context.getString(R.string.nobushi_jintouban),"/drawable/nobushi_jintouban.png"};
            case "Nobushi: Hitsukeban" : return new String[]{context.getString(R.string.nobushi_hitsukeban),"/drawable/nobushi_hitsukeban.png"};
            case "Nobushi: Kikouban" : return new String[]{context.getString(R.string.nobushi_kikouban),"/drawable/nobushi_kikouban.png"};
            case "Maguu Kenki" : return new String[]{context.getString(R.string.maguu_kenki),"/drawable/maguu_kenki.png"};
            case "Kairagi: Dancing Thunder" : return new String[]{context.getString(R.string.kairagi_dancing_thunder),"/drawable/kairagi_dancing_thunder.png"};
            case "Kairagi: Fiery Might" : return new String[]{context.getString(R.string.kairagi_fiery_might),"/drawable/kairagi_fiery_might.png"};
            case "Cryo Whopperflower" : return new String[]{context.getString(R.string.cryo_whopperflower),"/drawable/cryo_whopperflower.png"};
            case "Pyro Whopperflower" : return new String[]{context.getString(R.string.pyro_whopperflower),"/drawable/pyro_whopperflower.png"};
            case "Electro Whopperflower" : return new String[]{context.getString(R.string.electro_whopperflower),"/drawable/electro_whopperflower.png"};
            case "Stunned Pyro Whopperflower" : return new String[]{context.getString(R.string.stunned_pyro_whopperflower),"/drawable/cryo_whopperflower.png"};
            case "Stunned Electro Whopperflower" : return new String[]{context.getString(R.string.stunned_electro_whopperflower),"/drawable/pyro_whopperflower.png"};
            case "Stunned Cryo Whopperflower" : return new String[]{context.getString(R.string.stunned_cryo_whopperflower),"/drawable/electro_whopperflower.png"};
            case "Cryo Regisvine" : return new String[]{context.getString(R.string.cryo_regisvine),"/drawable/cryo_regisvine.png"};
            case "Pyro Regisvine" : return new String[]{context.getString(R.string.pyro_regisvine),"/drawable/pyro_regisvine.png"};
            case "Stunned Cryo Regisvine" : return new String[]{context.getString(R.string.stunned_cryo_regisvine),"/drawable/cryo_regisvine.png"};
            case "Stunned Pyro Regisvine" : return new String[]{context.getString(R.string.stunned_pyro_regisvine),"/drawable/pyro_regisvine.png"};
            case "Geovishap Hatchling" : return new String[]{context.getString(R.string.geovishap_hatchling),"/drawable/geovishap_hatchling.png"};
            case "Geovishap" : return new String[]{context.getString(R.string.geovishap),"/drawable/geovishap.png"};
            case "Pyro-infused Geovishap" : return new String[]{context.getString(R.string.pyroinfused_geovishap),"/drawable/geovishap.png"};
            case "Electro-infused Geovishap" : return new String[]{context.getString(R.string.electroinfused_geovishap),"/drawable/geovishap.png"};
            case "Cryo-infused Geovishap" : return new String[]{context.getString(R.string.cryoinfused_geovishap),"/drawable/geovishap.png"};
            case "Hydro-infused Geovishap" : return new String[]{context.getString(R.string.hydroinfused_geovishap),"/drawable/geovishap.png"};
            case "Primo Geovishap" : return new String[]{context.getString(R.string.primo_geovishap),"/drawable/primo_geovishap.png"};
            case "Primordial Bathysmal Vishap" : return new String[]{context.getString(R.string.primordial_bathysmal_vishap),"/drawable/primordial_bathysmal_vishap.png"};
            case "Rimebiter Bathysmal Vishap" : return new String[]{context.getString(R.string.rimebiter_bathysmal_vishap),"/drawable/rimebiter_bathysmal_vishap.png"};
            case "Bolteater Bathysmal Vishap" : return new String[]{context.getString(R.string.bolteater_bathysmal_vishap),"/drawable/bolteater_bathysmal_vishap.png"};
            case "Electro Bathysmal Vishap" : return new String[]{context.getString(R.string.electro_bathysmal_vishap),"/drawable/electro_bathysmal_vishap.png"};
            case "Cyro Bathysmal Vishap" : return new String[]{context.getString(R.string.cyro_bathysmal_vishap),"/drawable/cyro_bathysmal_vishap.png"};
            case "Electro Cicin" : return new String[]{context.getString(R.string.electro_cicin),"/drawable/electro_cicin.png"};
            case "Hydro Cicin" : return new String[]{context.getString(R.string.hydro_cicin),"/drawable/hydro_cicin.png"};
            case "Cryo Cicin" : return new String[]{context.getString(R.string.cryo_cicin),"/drawable/cryo_cicin.png"};
            case "Stormterror" : return new String[]{context.getString(R.string.stormterror),"/drawable/stormterror.png"};
            case "Lupus Boreas" : return new String[]{context.getString(R.string.lupus_boreas),"/drawable/lupus_boreas.png"};
            case "Phase 1 Childe" : return new String[]{context.getString(R.string.phase_1_childe),"/drawable/childe.png"};
            case "Stunned Phase 1 Childe " : return new String[]{context.getString(R.string.stunned_phase_1_childe_),"/drawable/childe.png"};
            case "Phase 2 Childe" : return new String[]{context.getString(R.string.phase_2_childe),"/drawable/childe.png"};
            case "Stunned Phase 2 Childe" : return new String[]{context.getString(R.string.stunned_phase_2_childe),"/drawable/childe.png"};
            case "Phase 3 Childe" : return new String[]{context.getString(R.string.phase_3_childe),"/drawable/childe.png"};
            case "Azhdaha" : return new String[]{context.getString(R.string.azhdaha),"/drawable/azhdaha.png"};
            case "Pyro Azhdaha" : return new String[]{context.getString(R.string.pyro_azhdaha),"/drawable/azhdaha.png"};
            case "Hydro Azhdaha" : return new String[]{context.getString(R.string.hydro_azhdaha),"/drawable/azhdaha.png"};
            case "Fire-Cryo Azhdaha" : return new String[]{context.getString(R.string.firecryo_azhdaha),"/drawable/azhdaha.png"};
            case "Fire-Electro Azhdaha" : return new String[]{context.getString(R.string.fireelectro_azhdaha),"/drawable/azhdaha.png"};
            case "Hydro-Cryo Azhdaha" : return new String[]{context.getString(R.string.hydrocryo_azhdaha),"/drawable/azhdaha.png"};
            case "Hydro-Electro Azhdaha" : return new String[]{context.getString(R.string.hydroelectro_azhdaha),"/drawable/azhdaha.png"};
            case "Phase 1 La Signora" : return new String[]{context.getString(R.string.phase_1_la_signora),"/drawable/la_signora.png"};
            case "Phase 2 La Signora" : return new String[]{context.getString(R.string.phase_2_la_signora),"/drawable/la_signora.png"};
            case "Magatsu Mitake Narukami no Mikoto" : return new String[]{context.getString(R.string.magatsu_mitake_narukami_no_mikoto),"/drawable/magatsu_mitake_narukami_no_mikoto.png"};
            case "Hydro Mimic Boar" : return new String[]{context.getString(R.string.hydro_mimic_boar),"/drawable/hydro_mimic_boar.png"};
            case "Hydro Mimic Crane" : return new String[]{context.getString(R.string.hydro_mimic_crane),"/drawable/hydro_mimic_crane.png"};
            case "Hydro Mimic Crab" : return new String[]{context.getString(R.string.hydro_mimic_crab),"/drawable/hydro_mimic_crab.png"};
            case "Hydro Mimic Finch" : return new String[]{context.getString(R.string.hydro_mimic_finch),"/drawable/hydro_mimic_finch.png"};
            case "Hydro Mimic Mallard" : return new String[]{context.getString(R.string.hydro_mimic_mallard),"/drawable/hydro_mimic_mallard.png"};
            case "Hydro Mimic Ferret" : return new String[]{context.getString(R.string.hydro_mimic_ferret),"/drawable/hydro_mimic_ferret.png"};
            case "Hydro Mimic Frog" : return new String[]{context.getString(R.string.hydro_mimic_frog),"/drawable/hydro_mimic_frog.png"};
            case "Hydro Mimic Raptor" : return new String[]{context.getString(R.string.hydro_mimic_raptor),"/drawable/hydro_mimic_raptor.png"};
            case "Shogunate Infantry" : return new String[]{context.getString(R.string.shogunate_infantry),"/drawable/shogunate_infantry.png"};
            case "Shogunate Infantry Captain" : return new String[]{context.getString(R.string.shogunate_infantry_captain),"/drawable/shogunate_infantry_captain.png"};
            case "Sangonomiya Cohort" : return new String[]{context.getString(R.string.sangonomiya_cohort),"/drawable/sangonomiya_cohort.png"};
            case "Yoriki Samurai" : return new String[]{context.getString(R.string.yoriki_samurai),"/drawable/yoriki_samurai.png"};
            case "Ochimusha: Ensorcelled Thunder" : return new String[]{context.getString(R.string.ochimusha_ensorcelled_thunder),"/drawable/ochimusha_ensorcelled_thunder.png"};
            case "Ochimusha: Cankered Flame" : return new String[]{context.getString(R.string.ochimusha_cankered_flame),"/drawable/ochimusha_cankered_flame.png"};
            case "Maguu Kenki: Lone Gale" : return new String[]{context.getString(R.string.maguu_kenki_lone_gale),"/drawable/maguu_kenki_lone_gale.png"};
            case "Maguu Kenki: Galloping Frost" : return new String[]{context.getString(R.string.maguu_kenki_galloping_frost),"/drawable/maguu_kenki_galloping_frost.png"};
            case "Maguu Kenki: Mask of Terror" : return new String[]{context.getString(R.string.maguu_kenki_mask_of_terror),"/drawable/maguu_kenki_mask_of_terror.png"};
            case "The Great Snowboar King" : return new String[]{context.getString(R.string.the_great_snowboar_king),"/drawable/the_great_snowboar_king.png"};

            default : return new String[] {context.getString(R.string.unknown),"/drawable/paimon_lost.png"};
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
            case "Amenoma Kageuchi" : return new String[] {context.getString(R.string.amenoma_kageuchi),"/drawable/amenoma_kageuchi.png"};
            case "Aquila Favonia" : return new String[] {context.getString(R.string.aquila_favonia),"/drawable/aquila_favonia.png"};
            case "Blackcliff Longsword" : return new String[] {context.getString(R.string.blackcliff_longsword),"/drawable/blackcliff_longsword.png"};
            case "Cool Steel" : return new String[] {context.getString(R.string.cool_steel),"/drawable/cool_steel.png"};
            case "Dark Iron Sword" : return new String[] {context.getString(R.string.dark_iron_sword),"/drawable/dark_iron_sword.png"};
            case "Dull Blade" : return new String[] {context.getString(R.string.dull_blade),"/drawable/dull_blade.png"};
            case "Favonius Sword" : return new String[] {context.getString(R.string.favonius_sword),"/drawable/favonius_sword.png"};
            case "Festering Desire" : return new String[] {context.getString(R.string.festering_desire),"/drawable/festering_desire.png"};
            case "Fillet Blade" : return new String[] {context.getString(R.string.fillet_blade),"/drawable/fillet_blade.png"};
            case "Freedom-Sworn" : return new String[] {context.getString(R.string.freedom_sworn),"/drawable/freedom_sworn.png"};
            case "Harbinger of Dawn" : return new String[] {context.getString(R.string.harbinger_of_dawn),"/drawable/harbinger_of_dawn.png"};
            case "Iron Sting" : return new String[] {context.getString(R.string.iron_sting),"/drawable/iron_sting.png"};
            case "Lion's Roar" : return new String[] {context.getString(R.string.lions_roar),"/drawable/lions_roar.png"};
            case "Mistsplitter Reforged" : return new String[] {context.getString(R.string.mistsplitter_reforged),"/drawable/mistsplitter_reforged.png"};
            case "Primordial Jade Cutter" : return new String[] {context.getString(R.string.primordial_jade_cutter),"/drawable/primordial_jade_cutter.png"};
            case "Prototype Rancour" : return new String[] {context.getString(R.string.prototype_rancour),"/drawable/prototype_rancour.png"};
            case "Royal Longsword" : return new String[] {context.getString(R.string.royal_longsword),"/drawable/royal_longsword.png"};
            case "Sacrificial Sword" : return new String[] {context.getString(R.string.sacrificial_sword),"/drawable/sacrificial_sword.png"};
            case "Silver Sword" : return new String[] {context.getString(R.string.silver_sword),"/drawable/silver_sword.png"};
            case "Skyrider Sword" : return new String[] {context.getString(R.string.skyrider_sword),"/drawable/skyrider_sword.png"};
            case "Skyward Blade" : return new String[] {context.getString(R.string.skyward_blade),"/drawable/skyward_blade.png"};
            case "Summit Shaper" : return new String[] {context.getString(R.string.summit_shaper),"/drawable/summit_shaper.png"};
            case "Sword of Descension" : return new String[] {context.getString(R.string.sword_of_descension),"/drawable/sword_of_descension.png"};
            case "The Alley Flash" : return new String[] {context.getString(R.string.the_alley_flash),"/drawable/the_alley_flash.png"};
            case "The Black Sword" : return new String[] {context.getString(R.string.the_black_sword),"/drawable/the_black_sword.png"};
            case "The Flute" : return new String[] {context.getString(R.string.the_flute),"/drawable/the_flute.png"};
            case "Traveler's Handy Sword" : return new String[] {context.getString(R.string.travelers_handy_sword),"/drawable/travelers_handy_sword.png"};
            case "Cinnabar Spindle" : return new String[] {context.getString(R.string.cinnabar_spindle),"/drawable/cinnabar_spindle.png"};

            case "Waster Greatsword" : return new String[] {context.getString(R.string.waster_greatsword),"/drawable/waster_greatsword.png"};
            case "Old Merc's Pal" : return new String[] {context.getString(R.string.old_mercs_pal),"/drawable/old_mercs_pal.png"};
            case "Bloodtainted Greatsword" : return new String[] {context.getString(R.string.bloodtainted_greatsword),"/drawable/bloodtainted_greatsword.png"};
            case "Debate Club" : return new String[] {context.getString(R.string.debate_club),"/drawable/debate_club.png"};
            case "Quartz" : return new String[] {context.getString(R.string.quartz),"/drawable/quartz.png"};
            case "Ferrous Shadow" : return new String[] {context.getString(R.string.ferrous_shadow),"/drawable/ferrous_shadow.png"};
            case "Skyrider Greatsword" : return new String[] {context.getString(R.string.skyrider_greatsword),"/drawable/skyrider_greatsword.png"};
            case "White Iron Greatsword" : return new String[] {context.getString(R.string.white_iron_greatsword),"/drawable/white_iron_greatsword.png"};
            case "Blackcliff Slasher" : return new String[] {context.getString(R.string.blackcliff_slasher),"/drawable/blackcliff_slasher.png"};
            case "Favonius Greatsword" : return new String[] {context.getString(R.string.favonius_greatsword),"/drawable/favonius_greatsword.png"};
            case "Katsuragikiri Nagamasa" : return new String[] {context.getString(R.string.katsuragikiri_nagamasa),"/drawable/katsuragikiri_nagamasa.png"};
            case "Lithic Blade" : return new String[] {context.getString(R.string.lithic_blade),"/drawable/lithic_blade.png"};
            case "Luxurious Sea-Lord" : return new String[] {context.getString(R.string.luxurious_sea_lord),"/drawable/luxurious_sea_lord.png"};
            case "Prototype Archaic" : return new String[] {context.getString(R.string.prototype_archaic),"/drawable/prototype_archaic.png"};
            case "Rainslasher" : return new String[] {context.getString(R.string.rainslasher),"/drawable/rainslasher.png"};
            case "Royal Greatsword" : return new String[] {context.getString(R.string.royal_greatsword),"/drawable/royal_greatsword.png"};
            case "Sacrificial Greatsword" : return new String[] {context.getString(R.string.sacrificial_greatsword),"/drawable/sacrificial_greatsword.png"};
            case "Serpent Spine" : return new String[] {context.getString(R.string.serpent_spine),"/drawable/serpent_spine.png"};
            case "Snow-Tombed Starsilver" : return new String[] {context.getString(R.string.snow_tombed_starsilver),"/drawable/snow_tombed_starsilver.png"};
            case "The Bell" : return new String[] {context.getString(R.string.the_bell),"/drawable/the_bell.png"};
            case "Whiteblind" : return new String[] {context.getString(R.string.whiteblind),"/drawable/whiteblind.png"};
            case "Skyward Pride" : return new String[] {context.getString(R.string.skyward_pride),"/drawable/skyward_pride.png"};
            case "Song of Broken Pines" : return new String[] {context.getString(R.string.song_of_broken_pines),"/drawable/song_of_broken_pines.png"};
            case "The Unforged" : return new String[] {context.getString(R.string.the_unforged),"/drawable/the_unforged.png"};
            case "Wolf's Gravestone" : return new String[] {context.getString(R.string.wolfs_gravestone),"/drawable/wolfs_gravestone.png"};
            case "Akuoumaru" : return new String[] {context.getString(R.string.akuoumaru),"/drawable/akuoumaru.png"};
            case "Redhorn Stonethresher" : return new String[] {context.getString(R.string.redhorn_stonethresher),"/drawable/redhorn_stonethresher.png"};

            case "Beginner's Protector" : return new String[] {context.getString(R.string.beginners_protector),"/drawable/beginners_protector.png"};
            case "Iron Point" : return new String[] {context.getString(R.string.iron_point),"/drawable/iron_point.png"};
            case "Black Tassel" : return new String[] {context.getString(R.string.black_tassel),"/drawable/black_tassel.png"};
            case "Halberd" : return new String[] {context.getString(R.string.halberd),"/drawable/halberd.png"};
            case "White Tassel" : return new String[] {context.getString(R.string.white_tassel),"/drawable/white_tassel.png"};
            case "Blackcliff Pole" : return new String[] {context.getString(R.string.blackcliff_pole),"/drawable/blackcliff_pole.png"};
            case "Crescent Pike" : return new String[] {context.getString(R.string.crescent_pike),"/drawable/crescent_pike.png"};
            case "Deathmatch" : return new String[] {context.getString(R.string.deathmatch),"/drawable/deathmatch.png"};
            case "Dragon's Bane" : return new String[] {context.getString(R.string.dragons_bane),"/drawable/dragons_bane.png"};
            case "Dragonspine Spear" : return new String[] {context.getString(R.string.dragonspine_spear),"/drawable/dragonspine_spear.png"};
            case "Favonius Lance" : return new String[] {context.getString(R.string.favonius_lance),"/drawable/favonius_lance.png"};
            case "Kitain Cross Spear" : return new String[] {context.getString(R.string.kitain_cross_spear),"/drawable/kitain_cross_spear.png"};
            case "Lithic Spear" : return new String[] {context.getString(R.string.lithic_spear),"/drawable/lithic_spear.png"};
            case "Prototype Starglitter" : return new String[] {context.getString(R.string.prototype_starglitter),"/drawable/prototype_starglitter.png"};
            case "Royal Spear" : return new String[] {context.getString(R.string.royal_spear),"/drawable/royal_spear.png"};
            case "The Catch" : return new String[] {context.getString(R.string.the_catch),"/drawable/the_catch.png"};
            case "Engulfing Lightning" : return new String[] {context.getString(R.string.engulfing_lightning),"/drawable/engulfing_lightning.png"};
            case "Primordial Jade Winged-Spear" : return new String[] {context.getString(R.string.primordial_jade_winged_spear),"/drawable/primordial_jade_winged_spear.png"};
            case "Skyward Spine" : return new String[] {context.getString(R.string.skyward_spine),"/drawable/skyward_spine.png"};
            case "Staff of Homa" : return new String[] {context.getString(R.string.staff_of_homa),"/drawable/staff_of_homa.png"};
            case "Vortex Vanquisher" : return new String[] {context.getString(R.string.vortex_vanquisher),"/drawable/vortex_vanquisher.png"};
            case "Wavebreaker's Fin" : return new String[] {context.getString(R.string.wavebreakers_fin),"/drawable/wavebreakers_fin.png"};
            case "Calamity Queller" : return new String[] {context.getString(R.string.calamity_queller),"/drawable/calamity_queller.png"};

            case "Apprentice's Notes" : return new String[] {context.getString(R.string.apprentices_notes),"/drawable/apprentices_notes.png"};
            case "Pocket Grimoire" : return new String[] {context.getString(R.string.pocket_grimoire),"/drawable/pocket_grimoire.png"};
            case "Amber Catalyst" : return new String[] {context.getString(R.string.amber_catalyst),"/drawable/amber_catalyst.png"};
            case "Emerald Orb" : return new String[] {context.getString(R.string.emerald_orb),"/drawable/emerald_orb.png"};
            case "Magic Guide" : return new String[] {context.getString(R.string.magic_guide),"/drawable/magic_guide.png"};
            case "Otherworldly Story" : return new String[] {context.getString(R.string.otherworldly_story),"/drawable/otherworldly_story.png"};
            case "Thrilling Tales of Dragon Slayers" : return new String[] {context.getString(R.string.thrilling_tales_of_dragon_slayers),"/drawable/thrilling_tales_of_dragon_slayers.png"};
            case "Twin Nephrite" : return new String[] {context.getString(R.string.twin_nephrite),"/drawable/twin_nephrite.png"};
            case "Blackcliff Agate" : return new String[] {context.getString(R.string.blackcliff_agate),"/drawable/blackcliff_agate.png"};
            case "Dodoco Tales" : return new String[] {context.getString(R.string.dodoco_tales),"/drawable/dodoco_tales.png"};
            case "Eye of Perception" : return new String[] {context.getString(R.string.eye_of_perception),"/drawable/eye_of_perception.png"};
            case "Favonius Codex" : return new String[] {context.getString(R.string.favonius_codex),"/drawable/favonius_codex.png"};
            case "Frostbearer" : return new String[] {context.getString(R.string.frostbearer),"/drawable/frostbearer.png"};
            case "Hakushin Ring" : return new String[] {context.getString(R.string.hakushin_ring),"/drawable/hakushin_ring.png"};
            case "Mappa Mare" : return new String[] {context.getString(R.string.mappa_mare),"/drawable/mappa_mare.png"};
            case "Prototype Amber" : return new String[] {context.getString(R.string.prototype_amber),"/drawable/prototype_amber.png"};
            case "Royal Grimoire" : return new String[] {context.getString(R.string.royal_grimoire),"/drawable/royal_grimoire.png"};
            case "Sacrificial Fragments" : return new String[] {context.getString(R.string.sacrificial_fragments),"/drawable/sacrificial_fragments.png"};
            case "Solar Pearl" : return new String[] {context.getString(R.string.solar_pearl),"/drawable/solar_pearl.png"};
            case "The Widsith" : return new String[] {context.getString(R.string.the_widsith),"/drawable/the_widsith.png"};
            case "Wine and Song" : return new String[] {context.getString(R.string.wine_and_song),"/drawable/wine_and_song.png"};
            case "Everlasting Moonglow" : return new String[] {context.getString(R.string.everlasting_moonglow),"/drawable/everlasting_moonglow.png"};
            case "Lost Prayer to the Sacred Winds" : return new String[] {context.getString(R.string.lost_prayer_to_the_sacred_winds),"/drawable/lost_prayer_to_the_sacred_winds.png"};
            case "Memory of Dust" : return new String[] {context.getString(R.string.memory_of_dust),"/drawable/memory_of_dust.png"};
            case "Skyward Atlas" : return new String[] {context.getString(R.string.skyward_atlas),"/drawable/skyward_atlas.png"};
            //add in 20220126
            case "Kagura's Verity" : return new String[] {context.getString(R.string.kaguras_verity),"/drawable/kaguras_verity.png"};
            case "Oathsworn Eye" : return new String[] {context.getString(R.string.oathsworn_eye),"/drawable/oathsworn_eye.png"};

            case "Hunter's Bow" : return new String[] {context.getString(R.string.hunters_bow),"/drawable/hunters_bow.png"};
            case "Seasoned Hunter's Bow" : return new String[] {context.getString(R.string.seasoned_hunters_bow),"/drawable/seasoned_hunters_bow.png"};
            case "Ebony Bow" : return new String[] {context.getString(R.string.ebony_bow),"/drawable/ebony_bow.png"};
            case "Messenger" : return new String[] {context.getString(R.string.messenger),"/drawable/messenger.png"};
            case "Raven Bow" : return new String[] {context.getString(R.string.raven_bow),"/drawable/raven_bow.png"};
            case "Recurve Bow" : return new String[] {context.getString(R.string.recurve_bow),"/drawable/recurve_bow.png"};
            case "Sharpshooter's Oath" : return new String[] {context.getString(R.string.sharpshooters_oath),"/drawable/sharpshooters_oath.png"};
            case "Slingshot" : return new String[] {context.getString(R.string.slingshot),"/drawable/slingshot.png"};
            case "Alley Hunter" : return new String[] {context.getString(R.string.alley_hunter),"/drawable/alley_hunter.png"};
            case "Blackcliff Warbow" : return new String[] {context.getString(R.string.blackcliff_warbow),"/drawable/blackcliff_warbow.png"};
            case "Compound Bow" : return new String[] {context.getString(R.string.compound_bow),"/drawable/compound_bow.png"};
            case "Favonius Warbow" : return new String[] {context.getString(R.string.favonius_warbow),"/drawable/favonius_warbow.png"};
            case "Hamayumi" : return new String[] {context.getString(R.string.hamayumi),"/drawable/hamayumi.png"};
            case "Mitternachts Waltz" : return new String[] {context.getString(R.string.mitternachts_waltz),"/drawable/mitternachts_waltz.png"};
            case "Predator" : return new String[] {context.getString(R.string.predator),"/drawable/predator.png"};
            case "Prototype Crescent" : return new String[] {context.getString(R.string.prototype_crescent),"/drawable/prototype_crescent.png"};
            case "Royal Bow" : return new String[] {context.getString(R.string.royal_bow),"/drawable/royal_bow.png"};
            case "Rust" : return new String[] {context.getString(R.string.rust),"/drawable/rust.png"};
            case "Sacrificial Bow" : return new String[] {context.getString(R.string.sacrificial_bow),"/drawable/sacrificial_bow.png"};
            case "The Stringless" : return new String[] {context.getString(R.string.the_stringless),"/drawable/the_stringless.png"};
            case "The Viridescent Hunt" : return new String[] {context.getString(R.string.the_viridescent_hunt),"/drawable/the_viridescent_hunt.png"};
            case "Windblume Ode" : return new String[] {context.getString(R.string.windblume_ode),"/drawable/windblume_ode.png"};
            case "Amos' Bow" : return new String[] {context.getString(R.string.amos_bow),"/drawable/amos_bow.png"};
            case "Elegy for the End" : return new String[] {context.getString(R.string.elegy_for_the_end),"/drawable/elegy_for_the_end.png"};
            case "Skyward Harp" : return new String[] {context.getString(R.string.skyward_harp),"/drawable/skyward_harp.png"};
            case "Thundering Pulse" : return new String[] {context.getString(R.string.thundering_pulse),"/drawable/thundering_pulse.png"};
            case "Mouun's Moon" : return new String[] {context.getString(R.string.mouuns_moon),"/drawable/mouuns_moon.png"};

            default : return new String[] {context.getString(R.string.unknown),"/drawable/paimon_lost.png"};
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
            case "freedom_sworn" : return "Freedom-Sworn";
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

            default: return  "PAIMON_ATE";
        }
    }

    public String[] getArtifactByName (String str, Context context){
        switch (str){
            case "Adventurer" : return new String[] {context.getString(R.string.adventurer),"/drawable/adventurer_1.png","/drawable/adventurer_2.png","/drawable/adventurer_3.png","/drawable/adventurer_4.png","/drawable/adventurer_5.png"};
            case "Archaic Petra" : return new String[] {context.getString(R.string.archaic_petra),"/drawable/archaic_petra_1.png","/drawable/archaic_petra_2.png","/drawable/archaic_petra_3.png","/drawable/archaic_petra_4.png","/drawable/archaic_petra_5.png"};
            case "Berserker" : return new String[] {context.getString(R.string.berserker),"/drawable/berserker_1.png","/drawable/berserker_2.png","/drawable/berserker_3.png","/drawable/berserker_4.png","/drawable/berserker_5.png"};
            case "Blizzard Strayer" : return new String[] {context.getString(R.string.blizzard_strayer),"/drawable/blizzard_strayer_1.png","/drawable/blizzard_strayer_2.png","/drawable/blizzard_strayer_3.png","/drawable/blizzard_strayer_4.png","/drawable/blizzard_strayer_5.png"};
            case "Bloodstained Chivalry" : return new String[] {context.getString(R.string.bloodstained_chivalry),"/drawable/bloodstained_chivalry_1.png","/drawable/bloodstained_chivalry_2.png","/drawable/bloodstained_chivalry_3.png","/drawable/bloodstained_chivalry_4.png","/drawable/bloodstained_chivalry_5.png"};
            case "Brave Heart" : return new String[] {context.getString(R.string.brave_heart),"/drawable/brave_heart_1.png","/drawable/brave_heart_2.png","/drawable/brave_heart_3.png","/drawable/brave_heart_4.png","/drawable/brave_heart_5.png"};
            case "Crimson Witch of Flames" : return new String[] {context.getString(R.string.crimson_witch_of_flames),"/drawable/crimson_witch_of_flames_1.png","/drawable/crimson_witch_of_flames_2.png","/drawable/crimson_witch_of_flames_3.png","/drawable/crimson_witch_of_flames_4.png","/drawable/crimson_witch_of_flames_5.png"};
            case "Defender's Will" : return new String[] {context.getString(R.string.defenders_will),"/drawable/defenders_will_1.png","/drawable/defenders_will_2.png","/drawable/defenders_will_3.png","/drawable/defenders_will_4.png","/drawable/defenders_will_5.png"};
            case "Emblem of Severed Fate" : return new String[] {context.getString(R.string.emblem_of_severed_fate),"/drawable/emblem_of_severed_fate_1.png","/drawable/emblem_of_severed_fate_2.png","/drawable/emblem_of_severed_fate_3.png","/drawable/emblem_of_severed_fate_4.png","/drawable/emblem_of_severed_fate_5.png"};
            case "Gambler" : return new String[] {context.getString(R.string.gambler),"/drawable/gambler_1.png","/drawable/gambler_2.png","/drawable/gambler_3.png","/drawable/gambler_4.png","/drawable/gambler_5.png"};
            case "Gladiator's Finale" : return new String[] {context.getString(R.string.gladiators_finale),"/drawable/gladiators_finale_1.png","/drawable/gladiators_finale_2.png","/drawable/gladiators_finale_3.png","/drawable/gladiators_finale_4.png","/drawable/gladiators_finale_5.png"};
            case "Heart of Depth" : return new String[] {context.getString(R.string.heart_of_depth),"/drawable/heart_of_depth_1.png","/drawable/heart_of_depth_2.png","/drawable/heart_of_depth_3.png","/drawable/heart_of_depth_4.png","/drawable/heart_of_depth_5.png"};
            case "Instructor" : return new String[] {context.getString(R.string.instructor),"/drawable/instructor_1.png","/drawable/instructor_2.png","/drawable/instructor_3.png","/drawable/instructor_4.png","/drawable/instructor_5.png"};
            case "Lavawalker" : return new String[] {context.getString(R.string.lavawalker),"/drawable/lavawalker_1.png","/drawable/lavawalker_2.png","/drawable/lavawalker_3.png","/drawable/lavawalker_4.png","/drawable/lavawalker_5.png"};
            case "Lucky Dog" : return new String[] {context.getString(R.string.lucky_dog),"/drawable/lucky_dog_1.png","/drawable/lucky_dog_2.png","/drawable/lucky_dog_3.png","/drawable/lucky_dog_4.png","/drawable/lucky_dog_5.png"};
            case "Maiden Beloved" : return new String[] {context.getString(R.string.maiden_beloved),"/drawable/maiden_beloved_1.png","/drawable/maiden_beloved_2.png","/drawable/maiden_beloved_3.png","/drawable/maiden_beloved_4.png","/drawable/maiden_beloved_5.png"};
            case "Martial Artist" : return new String[] {context.getString(R.string.martial_artist),"/drawable/martial_artist_1.png","/drawable/martial_artist_2.png","/drawable/martial_artist_3.png","/drawable/martial_artist_4.png","/drawable/martial_artist_5.png"};
            case "Noblesse Oblige" : return new String[] {context.getString(R.string.noblesse_oblige),"/drawable/noblesse_oblige_1.png","/drawable/noblesse_oblige_2.png","/drawable/noblesse_oblige_3.png","/drawable/noblesse_oblige_4.png","/drawable/noblesse_oblige_5.png"};
            case "Pale Flame" : return new String[] {context.getString(R.string.pale_flame),"/drawable/pale_flame_1.png","/drawable/pale_flame_2.png","/drawable/pale_flame_3.png","/drawable/pale_flame_4.png","/drawable/pale_flame_5.png"};
            case "Prayers of Destiny" : return new String[] {context.getString(R.string.prayers_of_destiny),"/drawable/prayers_of_destiny_4.png"};
            case "Prayers of Illumination" : return new String[] {context.getString(R.string.prayers_of_illumination),"/drawable/prayers_of_illumination_4.png"};
            case "Prayers of Wisdom" : return new String[] {context.getString(R.string.prayers_of_wisdom),"/drawable/prayers_of_wisdom_4.png"};
            case "Prayers of Springtime" : return new String[] {context.getString(R.string.prayers_of_springtime),"/drawable/prayers_of_springtime_4.png"};
            case "Resolution of Sojourner" : return new String[] {context.getString(R.string.resolution_of_sojourner),"/drawable/resolution_of_sojourner_1.png","/drawable/resolution_of_sojourner_2.png","/drawable/resolution_of_sojourner_3.png","/drawable/resolution_of_sojourner_4.png","/drawable/resolution_of_sojourner_5.png"};
            case "Retracing Bolide" : return new String[] {context.getString(R.string.retracing_bolide),"/drawable/retracing_bolide_1.png","/drawable/retracing_bolide_2.png","/drawable/retracing_bolide_3.png","/drawable/retracing_bolide_4.png","/drawable/retracing_bolide_5.png"};
            case "Scholar" : return new String[] {context.getString(R.string.scholar),"/drawable/scholar_1.png","/drawable/scholar_2.png","/drawable/scholar_3.png","/drawable/scholar_4.png","/drawable/scholar_5.png"};
            case "Shimenawa's Reminiscence" : return new String[] {context.getString(R.string.shimenawas_reminiscence),"/drawable/shimenawas_reminiscence_1.png","/drawable/shimenawas_reminiscence_2.png","/drawable/shimenawas_reminiscence_3.png","/drawable/shimenawas_reminiscence_4.png","/drawable/shimenawas_reminiscence_5.png"};
            case "Tenacity of the Millelith" : return new String[] {context.getString(R.string.tenacity_of_the_millelith),"/drawable/tenacity_of_the_millelith_1.png","/drawable/tenacity_of_the_millelith_2.png","/drawable/tenacity_of_the_millelith_3.png","/drawable/tenacity_of_the_millelith_4.png","/drawable/tenacity_of_the_millelith_5.png"};
            case "The Exile" : return new String[] {context.getString(R.string.the_exile),"/drawable/the_exile_1.png","/drawable/the_exile_2.png","/drawable/the_exile_3.png","/drawable/the_exile_4.png","/drawable/the_exile_5.png"};
            case "Thundering Fury" : return new String[] {context.getString(R.string.thundering_fury),"/drawable/thundering_fury_1.png","/drawable/thundering_fury_2.png","/drawable/thundering_fury_3.png","/drawable/thundering_fury_4.png","/drawable/thundering_fury_5.png"};
            case "Thunder-soother" : return new String[] {context.getString(R.string.thundersoother),"/drawable/thundersoother_1.png","/drawable/thundersoother_2.png","/drawable/thundersoother_3.png","/drawable/thundersoother_4.png","/drawable/thundersoother_5.png"};
            case "Tiny Miracle" : return new String[] {context.getString(R.string.tiny_miracle),"/drawable/tiny_miracle_1.png","/drawable/tiny_miracle_2.png","/drawable/tiny_miracle_3.png","/drawable/tiny_miracle_4.png","/drawable/tiny_miracle_5.png"};
            case "Traveling Doctor" : return new String[] {context.getString(R.string.traveling_doctor),"/drawable/traveling_doctor_1.png","/drawable/traveling_doctor_2.png","/drawable/traveling_doctor_3.png","/drawable/traveling_doctor_4.png","/drawable/traveling_doctor_5.png"};
            case "Viridescent Venerer" : return new String[] {context.getString(R.string.viridescent_venerer),"/drawable/viridescent_venerer_1.png","/drawable/viridescent_venerer_2.png","/drawable/viridescent_venerer_3.png","/drawable/viridescent_venerer_4.png","/drawable/viridescent_venerer_5.png"};
            case "Wanderer's Troupe" : return new String[] {context.getString(R.string.wanderers_troupe),"/drawable/wanderers_troupe_1.png","/drawable/wanderers_troupe_2.png","/drawable/wanderers_troupe_3.png","/drawable/wanderers_troupe_4.png","/drawable/wanderers_troupe_5.png"};

            // add in 20211127
            case "Husk of Opulent Dreams" : return new String[] {context.getString(R.string.husk_of_opulent_dreams),"/drawable/husk_of_opulent_dreams_1.png","/drawable/husk_of_opulent_dreams_2.png","/drawable/husk_of_opulent_dreams_3.png","/drawable/husk_of_opulent_dreams_4.png","/drawable/husk_of_opulent_dreams_5.png"};
            case "Ocean-Hued Clam" : return new String[] {context.getString(R.string.ocean_hued_clam),"/drawable/ocean_hued_clam_1.png","/drawable/ocean_hued_clam_2.png","/drawable/ocean_hued_clam_3.png","/drawable/ocean_hued_clam_4.png","/drawable/ocean_hued_clam_5.png"};

            default: return new String[] {context.getString(R.string.unknown),"/drawable/paimon_lost.png","/drawable/paimon_lost.png","/drawable/paimon_lost.png","/drawable/paimon_lost.png","/drawable/paimon_lost.png"};

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

            default: return  "PAIMON_ATE";
        }
    }
        /**
         * @param name is Char's Identify Name -> JSON
         * @return IMG_ID , NAME_LOCAL , FULL_IMG_ID , ICO_IMG_ID
         */

    /**EDIT WHEN ADD NEW ITEMS*/
    public String[] getCharByName(String name, Context context){
        if(name.equals("Kamisato Ayato")){return new String[] {"/drawable/kamisato_ayato_flag.png",context.getString(R.string.kamisato_ayato),"/drawable/kamisato_ayato_full.png","/drawable/kamisato_ayato_ico.png"};}
        else if(name.equals("Yae Miko")){return new String[] {"/drawable/yae_miko_flag.png",context.getString(R.string.yae_miko),"/drawable/yae_miko_full.png","/drawable/yae_miko_ico.png"};}
        else if(name.equals("Shenhe")){return new String[] {"/drawable/shenhe_flag.png",context.getString(R.string.shenhe),"/drawable/shenhe_full.png","/drawable/shenhe_ico.png"};}
        else if(name.equals("Yun Jin")){return new String[] {"/drawable/yun_jin_flag.png",context.getString(R.string.yun_jin),"/drawable/yun_jin_full.png","/drawable/yun_jin_ico.png"};}
        else if(name.equals("Gorou")){return new String[] {"/drawable/gorou_flag.png",context.getString(R.string.gorou),"/drawable/gorou_full.png","/drawable/gorou_ico.png"};}
        else if(name.equals("Arataki Itto")){return new String[] {"/drawable/arataki_itto_flag.png",context.getString(R.string.arataki_itto),"/drawable/arataki_itto_full.png","/drawable/arataki_itto_ico.png"};}
        else if(name.equals("Thoma")){return new String[] {"/drawable/thoma_flag.png",context.getString(R.string.thoma),"/drawable/thoma_full.png","/drawable/thoma_ico.png"};}
        else if(name.equals("Sangonomiya Kokomi")){return new String[] {"/drawable/sangonomiya_kokomi_flag.png",context.getString(R.string.sangonomiya_kokomi),"/drawable/sangonomiya_kokomi_full.png","/drawable/sangonomiya_kokomi_ico.png"};}
        else if(name.equals("Aloy")){return new String[] {"/drawable/aloy.png",context.getString(R.string.aloy),"/drawable/aloy.png","/drawable/aloy_ico.png"};}
        else if(name.equals("Kujou Sara")){return new String[] {"/drawable/kujou_sara_flag.png",context.getString(R.string.kujou_sara),"/drawable/kujou_sara_full.png","/drawable/kujou_sara_ico.png"};}
        else if(name.equals("Raiden Shogun")){return new String[] {"/drawable/raiden_shogun_flag.png",context.getString(R.string.raiden_shogun),"/drawable/raiden_shogun_full.png","/drawable/raiden_shogun_ico.png"};}
        else if(name.equals("Sayu")){return new String[] {"/drawable/sayu_flag.png",context.getString(R.string.sayu),"/drawable/sayu_full.png","/drawable/sayu_ico.png"};}
        else if(name.equals("Yoimiya")){return new String[] {"/drawable/yoimiya_flag.png",context.getString(R.string.yoimiya),"/drawable/yoimiya_full.png","/drawable/yoimiya_ico.png"};}
        else if(name.equals("Kamisato Ayaka")){return new String[] {"/drawable/ayaka_flag.png",context.getString(R.string.kamisato_ayaka),"/drawable/ayaka_full.png","/drawable/ayaka_ico.png"};}
        else if(name.equals("Kaedehara Kazuha")){return new String[] {"/drawable/kazuha_flag.png",context.getString(R.string.kaedehara_kazuha),"/drawable/kazuha_full.png","/drawable/kazuha_ico.png"};}
        else if(name.equals("Yanfei")){return new String[] {"/drawable/yanfei_flag.png",context.getString(R.string.yanfei),"/drawable/yanfei_full.png","/drawable/yanfei_ico.png"};}
        else if(name.equals("Eula")){return new String[] {"/drawable/eula_flag.png",context.getString(R.string.eula),"/drawable/eula_full.png","/drawable/eula_ico.png"};}
        else if(name.equals("Rosaria")){return new String[] {"/drawable/rosaria_flag.png",context.getString(R.string.rosaria),"/drawable/rosaria_full.png","/drawable/rosaria_ico.png"};}
        else if(name.equals("Xiao")){return new String[] {"/drawable/xiao_flag.png",context.getString(R.string.xiao),"/drawable/xiao_full.png","/drawable/xiao_ico.png"};}
        else if(name.equals("Hu Tao")){return new String[] {"/drawable/hu_tao_flag.png",context.getString(R.string.hu_tao),"/drawable/hu_tao_full.png","/drawable/hu_tao_ico.png"};}
        else if(name.equals("Ganyu")){return new String[] {"/drawable/ganyu_flag.png",context.getString(R.string.ganyu),"/drawable/ganyu_full.png","/drawable/ganyu_ico.png"};}
        else if(name.equals("Albedo")){return new String[] {"/drawable/albedo_flag.png",context.getString(R.string.albedo),"/drawable/albedo_full.png","/drawable/albedo_ico.png"};}
        else if(name.equals("Zhongli")){return new String[] {"/drawable/zhongli_flag.png",context.getString(R.string.zhongli),"/drawable/zhongli_full.png","/drawable/zhongli_ico.png"};}
        else if(name.equals("Xinyan")){return new String[] {"/drawable/xinyan_flag.png",context.getString(R.string.xinyan),"/drawable/xinyan_full.png","/drawable/xinyan_ico.png"};}
        else if(name.equals("Tartaglia")){return new String[] {"/drawable/tartaglia_flag.png",context.getString(R.string.tartaglia),"/drawable/tartaglia_full.png","/drawable/tartaglia_ico.png"};}
        else if(name.equals("Diona")){return new String[] {"/drawable/diona_flag.png",context.getString(R.string.diona),"/drawable/diona_full.png","/drawable/diona_ico.png"};}
        else if(name.equals("Xingqiu")){return new String[] {"/drawable/xingqiu_flag.png",context.getString(R.string.xingqiu),"/drawable/xingqiu_full.png","/drawable/xingqiu_ico.png"};}
        else if(name.equals("Xiangling")){return new String[] {"/drawable/xiangling_flag.png",context.getString(R.string.xiangling),"/drawable/xiangling_full.png","/drawable/xiangling_ico.png"};}
        else if(name.equals("Venti")){return new String[] {"/drawable/venti_flag.png",context.getString(R.string.venti),"/drawable/venti_full.png","/drawable/venti_ico.png"};}
        else if(name.equals("Sucrose")){return new String[] {"/drawable/sucrose_flag.png",context.getString(R.string.sucrose),"/drawable/sucrose_full.png","/drawable/sucrose_ico.png"};}
        else if(name.equals("Razor")){return new String[] {"/drawable/razor_flag.png",context.getString(R.string.razor),"/drawable/razor_full.png","/drawable/razor_ico.png"};}
        else if(name.equals("Qiqi")){return new String[] {"/drawable/qiqi_flag.png",context.getString(R.string.qiqi),"/drawable/qiqi_full.png","/drawable/qiqi_ico.png"};}
        else if(name.equals("Noelle")){return new String[] {"/drawable/noelle_flag.png",context.getString(R.string.noelle),"/drawable/noelle_full.png","/drawable/noelle_ico.png"};}
        else if(name.equals("Ningguang")){return new String[] {"/drawable/ningguang_flag.png",context.getString(R.string.ningguang),"/drawable/ningguang_full.png","/drawable/ningguang_ico.png"};}
        else if(name.equals("Mona")){return new String[] {"/drawable/mona_flag.png",context.getString(R.string.mona),"/drawable/mona_full.png","/drawable/mona_ico.png"};}
        else if(name.equals("Lisa")){return new String[] {"/drawable/lisa_flag.png",context.getString(R.string.lisa),"/drawable/lisa_full.png","/drawable/lisa_ico.png"};}
        else if(name.equals("Klee")){return new String[] {"/drawable/klee_flag.png",context.getString(R.string.klee),"/drawable/klee_full.png","/drawable/klee_ico.png"};}
        else if(name.equals("Keqing")){return new String[] {"/drawable/keqing_flag.png",context.getString(R.string.keqing),"/drawable/keqing_full.png","/drawable/keqing_ico.png"};}
        else if(name.equals("Kaeya")){return new String[] {"/drawable/kaeya_flag.png",context.getString(R.string.kaeya),"/drawable/kaeya_full.png","/drawable/kaeya_ico.png"};}
        else if(name.equals("Jean")){return new String[] {"/drawable/jean_flag.png",context.getString(R.string.jean),"/drawable/jean_full.png","/drawable/jean_ico.png"};}
        else if(name.equals("Fischl")){return new String[] {"/drawable/fischl_flag.png",context.getString(R.string.fischl),"/drawable/fischl_full.png","/drawable/fischl_ico.png"};}
        else if(name.equals("Diluc")){return new String[] {"/drawable/diluc_flag.png",context.getString(R.string.diluc),"/drawable/diluc_full.png","/drawable/diluc_ico.png"};}
        else if(name.equals("Chongyun")){return new String[] {"/drawable/chongyun_flag.png",context.getString(R.string.chongyun),"/drawable/chongyun_full.png","/drawable/chongyun_ico.png"};}
        else if(name.equals("Bennett")){return new String[] {"/drawable/bennett_flag.png",context.getString(R.string.bennett),"/drawable/bennett_full.png","/drawable/bennett_ico.png"};}
        else if(name.equals("Beidou")){return new String[] {"/drawable/beidou_flag.png",context.getString(R.string.beidou),"/drawable/beidou_full.png","/drawable/beidou_ico.png"};}
        else if(name.equals("Barbara")){return new String[] {"/drawable/barbara_flag.png",context.getString(R.string.barbara),"/drawable/barbara_full.png","/drawable/barbara_ico.png"};}
        else if(name.equals("Amber")){return new String[] {"/drawable/amber_flag.png",context.getString(R.string.amber),"/drawable/amber_full.png","/drawable/amber_ico.png"};}
        //Add at 20210820, update at 20220109
        else if(name.equals("Traveler-Anemo")){
            sharedPreferences = context.getSharedPreferences("user_info.png",context.MODE_PRIVATE);
            String traveler_sex = sharedPreferences.getString("traveler_sex.png","F");

            if(traveler_sex.equals("M")){
                return new String[] {"/drawable/aether_flag.png",context.getString(R.string.traveler_anemo),"/drawable/aether_full.png","/drawable/aether_ico.png"};
            }else{
                return new String[] {"/drawable/lumine_flag.png",context.getString(R.string.traveler_anemo),"/drawable/lumine_full.png","/drawable/lumine_ico.png"};
            }
        }
        //Add at 20210820, update at 20220109
        else if(name.equals("Traveler-Geo")){
            sharedPreferences = context.getSharedPreferences("user_info.png",context.MODE_PRIVATE);
            String traveler_sex = sharedPreferences.getString("traveler_sex.png","F");

            if(traveler_sex.equals("M")){
                return new String[] {"/drawable/aether_flag.png",context.getString(R.string.traveler_geo),"/drawable/aether_full.png","/drawable/aether_ico.png"};
            }else{
                return new String[] {"/drawable/lumine_flag.png",context.getString(R.string.traveler_geo),"/drawable/lumine_full.png","/drawable/lumine_ico.png"};
            }
        }
        //Add at 20210820, update at 20220109
        else if(name.equals("Traveler-Electro")){
            sharedPreferences = context.getSharedPreferences("user_info.png",context.MODE_PRIVATE);
            String traveler_sex = sharedPreferences.getString("traveler_sex.png","F");

            if(traveler_sex.equals("M")){
                return new String[] {"/drawable/aether_flag.png",context.getString(R.string.traveler_electro),"/drawable/aether_full.png","/drawable/aether_ico.png"};
            }else{
                return new String[] {"/drawable/lumine_flag.png",context.getString(R.string.traveler_electro),"/drawable/lumine_full.png","/drawable/lumine_ico.png"};
            }
        }
        // ???
        else if(name.equals("Voc-夜芷冰")){return new String[] {"/drawable/mascot.png",context.getString(R.string.voc),"/drawable/mascot.png","/drawable/mascot_head.png"};}

        return new String[] {"/drawable/paimon_lost.png",context.getString(R.string.unknown),"/drawable/paimon_lost.png","/drawable/paimon_lost.png"};
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
        else if(name.equals("Raudeb Shougun") || name.equals("雷電將軍") || name.equals("雷电将军") || name.equals("雷電将軍") || name.equals("Сёгун Райдэн")){ return "Raiden Shogun";}
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
        else if(name.equals("Kamisato Ayato") || name.equals("神里綾人") || name.equals("神里绫人") || name.equals("神里綾人") || name.equals("Камисато Аято")){ return "Kamisato Ayato";}
        else if(name.equals("Traveler-Anemo") || name.equals("旅行者(風)") || name.equals("旅行者(风)") || name.equals("旅人 (風)") || name.equals("Путешественник(Анемо)")){ return "Traveler-Anemo";}
        else if(name.equals("Traveler-Electro") || name.equals("旅行者(岩)") || name.equals("旅行者(岩)") || name.equals("旅人 (岩)") || name.equals("Путешественник(Гео)")){ return "Traveler-Electro";}
        else if(name.equals("Traveler-Geo") || name.equals("旅行者(雷)") || name.equals("旅行者(雷)") || name.equals("旅人 (雷)") || name.equals("Путешественник(Электро)")){ return "Traveler-Geo";}
        else if(name.equals("Thoma") || name.equals("托馬") || name.equals("托马") || name.equals("トーマ") || name.equals("Тома")){ return "Thoma";}


        else return context.getString(R.string.unknown);
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

    public Drawable getTalentIcoByName (String name, Context context){
        try {
            File file = new File(context.getFilesDir()+"/skills/"+name+".png");
            InputStream stream = new FileInputStream(file);
            // load image as Drawable
            Drawable d = Drawable.createFromStream(stream, null);
            // set image to ImageView
            return d;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable myIcon = context.getResources().getDrawable( R.drawable.paimon_lost );
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
        /** Boss*/
        if(name.equals("常燃火種")){return "/drawable/everflame_seed.png";}
        else if(name.equals("淨水之心")){return "/drawable/cleansing_heart.png";}
        else if(name.equals("雷光棱鏡")){return "/drawable/lightning_prism.png";}
        else if(name.equals("極寒之核")){return "/drawable/hoarfrost_core.png";}
        else if(name.equals("颶風之種")){return "/drawable/hurricane_seed.png";}
        else if(name.equals("玄岩之塔")){return "/drawable/basalt_pillar.png";}
        else if(name.equals("未熟之玉")){return "/drawable/juvenile_jade.png";}
        else if(name.equals("晶凝之華")){return "/drawable/crystalline_bloom.png";}
        else if(name.equals("魔偶機心")){return "/drawable/maguu_kishin.png";}
        else if(name.equals("恒常機關之心")){return "/drawable/perpetual_heart.png";}
        else if(name.equals("陰燃之珠")){return "/drawable/smoldering_pearl.png";}
        //add in 20210910
        else if(name.equals("雷霆數珠")){return "/drawable/storm_beads.png";}
        else if(name.equals("排異之露")){return "/drawable/dew_of_repudiation.png";}

        /** Local*/
        else if(name.equals("小燈草")){return "/drawable/small_lamp_grass.png";}
        else if(name.equals("慕風蘑菇")){return "/drawable/philanemo_mushroom.png";}
        else if(name.equals("夜泊石")){return "/drawable/noctilous_jade.png";}
        else if(name.equals("風車菊")){return "/drawable/windwheel_aster.png";}
        else if(name.equals("石珀")){return "/drawable/cor_lapis.png";}
        else if(name.equals("蒲公英籽")){return "/drawable/dandelion_seed.png";}
        else if(name.equals("嘟嘟蓮")){return "/drawable/calla_lily.png";}
        else if(name.equals("落落莓")){return "/drawable/valberry.png";}
        else if(name.equals("琉璃百合")){return "/drawable/glaze_lily.png";}
        else if(name.equals("琉璃袋")){return "/drawable/violetgrass.png";}
        else if(name.equals("鉤鉤果")){return "/drawable/wolfhook.png";}
        else if(name.equals("塞西莉亞花")){return "/drawable/cecilia.png";}
        else if(name.equals("絕雲椒椒")){return "/drawable/jueyun_chili.png";}
        else if(name.equals("霓裳花")){return "/drawable/silk_flower.png";}
        else if(name.equals("星螺")){return "/drawable/starconch.png";}
        else if(name.equals("清心")){return "/drawable/qingxin.png";}
        else if(name.equals("海靈芝")){return "/drawable/sea_ganoderma.png";}
        else if(name.equals("緋櫻繡球")){return "/drawable/sakura_bloom.png";}
        else if(name.equals("鳴草")){return "/drawable/naku_weed.png";}
        else if(name.equals("晶化骨髓")){return "/drawable/crystal_marrow.png";}
        //add in 20210910
        else if(name.equals("天雲草實")){return "/drawable/amakumo_fruit.png";}
        else if(name.equals("血斛")){return "/drawable/dendrobium.png";}
        else if(name.equals("幽燈蕈")){return "/drawable/fluorescent_fungus.png";}
        else if(name.equals("珊瑚真珠")){return "/drawable/sango_pearl.png";}


        /** T-Boss*/
        else if(name.equals("北風之環")){return "/drawable/ring_of_boreas.png";}
        else if(name.equals("東風的吐息")){return "/drawable/dvalins_sigh.png";}
        else if(name.equals("東風之翎")){return "/drawable/dvalins_plume.png";}
        else if(name.equals("北風的魂匣")){return "/drawable/spirit_locket_of_boreas.png";}
        else if(name.equals("東風之爪")){return "/drawable/dvalins_claw.png";}
        else if(name.equals("北風之尾")){return "/drawable/tail_of_boreas.png";}
        else if(name.equals("魔王之刃·殘片")){return "/drawable/shard_of_foul_legacy.png";}
        else if(name.equals("吞天之鯨·只角")){return "/drawable/tusk_of_monoceros_caeli.png";}
        else if(name.equals("武煉之魂·孤影")){return "/drawable/shadow_of_the_warrior.png";}
        else if(name.equals("龍王之冕")){return "/drawable/dragon_lords_crown.png";}
        else if(name.equals("血玉之枝")){return "/drawable/bloodjade_branch.png";}
        else if(name.equals("鎏金之鱗")){return "/drawable/gilded_scale.png";}
        //add in 20210910
        else if(name.equals("熔毀之刻")){return "/drawable/molten_moment.png";}
        else if(name.equals("灰燼之心")){return "/drawable/ashen_heart.png";}
        else if(name.equals("獄火之蝶")){return "/drawable/hellfire_butterfly.png";}
        //add in 20220216
        else if(name.equals("萬劫之真意")){return "/drawable/the_meaning_of_aeons.png";}
        else if(name.equals("凶將之手眼")){return "/drawable/mudra_of_the_malefic_general.png";}
        else if(name.equals("禍神之禊淚")){return "/drawable/tears_of_the_calamitous_god.png";}

        /** Common*/
        else if(name.equals("牢固的箭簇")){return "/drawable/firm_arrowhead.png";}
        else if(name.equals("銳利的箭簇")){return "/drawable/sharp_arrowhead.png";}
        else if(name.equals("歷戰的箭簇")){return "/drawable/weathered_arrowhead.png";}
        else if(name.equals("導能繪卷")){return "/drawable/divining_scroll.png";}
        else if(name.equals("封魔繪卷")){return "/drawable/sealed_scroll.png";}
        else if(name.equals("禁咒繪卷")){return "/drawable/forbidden_curse_scroll.png";}
        else if(name.equals("尋寶鴉印")){return "/drawable/treasure_hoarder_insignia.png";}
        else if(name.equals("藏銀鴉印")){return "/drawable/silver_raven_insignia.png";}
        else if(name.equals("攫金鴉印")){return "/drawable/golden_raven_insignia.png";}
        else if(name.equals("破損的面具")){return "/drawable/damaged_mask.png";}
        else if(name.equals("污穢的面具")){return "/drawable/stained_mask.png";}
        else if(name.equals("不祥的面具")){return "/drawable/ominous_mask.png";}
        else if(name.equals("新兵的徽記")){return "/drawable/recruits_insignia.png";}
        else if(name.equals("士官的徽記")){return "/drawable/sergeants_insignia.png";}
        else if(name.equals("尉官的徽記")){return "/drawable/lieutenants_insignia.png";}
        else if(name.equals("騙騙花蜜")){return "/drawable/whopperflower_nectar.png";}
        else if(name.equals("微光花蜜")){return "/drawable/shimmering_nectar.png";}
        else if(name.equals("原素花蜜")){return "/drawable/energy_nectar.png";}
        else if(name.equals("史萊姆凝液")){return "/drawable/slime_condensate.png";}
        else if(name.equals("史萊姆清")){return "/drawable/slime_secretions.png";}
        else if(name.equals("史萊姆原漿")){return "/drawable/slime_concentrate.png";}
        else if(name.equals("破舊的刀鐔")){return "/drawable/old_handguard.png";}
        else if(name.equals("影打刀鐔")){return "/drawable/kageuchi_handguard.png";}
        else if(name.equals("名刀鐔")){return "/drawable/famed_handguard.png";}
        //add in 20210910
        else if(name.equals("浮游乾核")){return "/drawable/spectral_husk.png";}
        else if(name.equals("浮游幽核")){return "/drawable/spectral_heart.png";}
        else if(name.equals("浮游晶化核")){return "/drawable/spectral_nucleus.png";}
        //add in 20211024 (RE)
        else if(name.equals("漆黑隕鐵的一粒")){return "/drawable/grain_of_aerosiderite.png";}
        else if(name.equals("漆黑隕鐵的一片")){return "/drawable/piece_of_aerosiderite.png";}
        else if(name.equals("漆黑隕鐵的一角")){return "/drawable/bit_of_aerosiderite.png";}
        else if(name.equals("漆黑隕鐵的一塊")){return "/drawable/chunk_of_aerosiderite.png";}
        else if(name.equals("鳴神御靈的明惠")){return "/drawable/narukamis_wisdom.png";}
        else if(name.equals("鳴神御靈的歡喜")){return "/drawable/narukamis_joy.png";}
        else if(name.equals("鳴神御靈的親愛")){return "/drawable/narukamis_affection.png";}
        else if(name.equals("鳴神御靈的勇武")){return "/drawable/narukamis_valor.png";}
        else if(name.equals("遠海夷地的瑚枝")){return "/drawable/coral_branch_of_a_distant_sea.png";}
        else if(name.equals("遠海夷地的玉枝")){return "/drawable/jeweled_branch_of_a_distant_sea.png";}
        else if(name.equals("遠海夷地的瓊枝")){return "/drawable/jade_branch_of_a_distant_sea.png";}
        else if(name.equals("遠海夷地的金枝")){return "/drawable/golden_branch_of_a_distant_sea.png";}
        else if(name.equals("凜風奔狼的始齔")){return "/drawable/boreal_wolfs_milk_tooth.png";}
        else if(name.equals("凜風奔狼的裂齒")){return "/drawable/boreal_wolfs_cracked_tooth.png";}
        else if(name.equals("凜風奔狼的斷牙")){return "/drawable/boreal_wolfs_broken_fang.png";}
        else if(name.equals("凜風奔狼的懷鄉")){return "/drawable/boreal_wolfs_nostalgia.png";}
        else if(name.equals("高塔孤王的破瓦")){return "/drawable/tile_of_decarabians_tower.png";}
        else if(name.equals("高塔孤王的殘垣")){return "/drawable/debris_of_decarabians_city.png";}
        else if(name.equals("高塔孤王的斷片")){return "/drawable/fragment_of_decarabians_epic.png";}
        else if(name.equals("高塔孤王的碎夢")){return "/drawable/scattered_piece_of_decarabianss_dream.png";}
        else if(name.equals("霧海雲間的鉛丹")){return "/drawable/mist_veiled_lead_elixir.png";}
        else if(name.equals("霧海雲間的汞丹")){return "/drawable/mist_veiled_mercury_elixir.png";}
        else if(name.equals("霧海雲間的金丹")){return "/drawable/mist_veiled_gold_elixir.png";}
        else if(name.equals("霧海雲間的轉還")){return "/drawable/mist_veiled_primo_elixir.png";}
        else if(name.equals("獅牙鬥士的枷鎖")){return "/drawable/fetters_of_the_dandelion_gladiator.png";}
        else if(name.equals("獅牙鬥士的鐵鍊")){return "/drawable/chains_of_the_dandelion_gladiator.png";}
        else if(name.equals("獅牙鬥士的鐐銬")){return "/drawable/shackles_of_the_dandelion_gladiator.png";}
        else if(name.equals("獅牙鬥士的理想")){return "/drawable/dream_of_the_dandelion_gladiator.png";}
        else if(name.equals("孤雲寒林的光砂")){return "/drawable/luminous_sands_from_guyun.png";}
        else if(name.equals("孤雲寒林的輝岩")){return "/drawable/lustrous_stone_from_guyun.png";}
        else if(name.equals("孤雲寒林的聖骸")){return "/drawable/relic_from_guyun.png";}
        else if(name.equals("孤雲寒林的神體")){return "/drawable/divine_body_from_guyun.png";}
        else if(name.equals("今昔劇畫的惡尉")){return "/drawable/mask_of_the_wicked_lieutenant.png";}
        else if(name.equals("今昔劇畫的虎囓")){return "/drawable/mask_of_the_tigers_bite.png";}
        else if(name.equals("今昔劇畫的一角")){return "/drawable/mask_of_the_one_horned.png";}
        else if(name.equals("今昔劇畫的鬼人")){return "/drawable/mask_of_the_kijin.png";}

        else if(name.equals("混沌機關")){return "/drawable/chaos_gear.png";}
        else if(name.equals("混沌樞紐")){return "/drawable/chaos_axis.png";}
        else if(name.equals("混沌真眼")){return "/drawable/chaos_oculus.png";}
        else if(name.equals("混沌裝置")){return "/drawable/chaos_device.png";}
        else if(name.equals("混沌迴路")){return "/drawable/chaos_circuit.png";}
        else if(name.equals("混沌爐心")){return "/drawable/chaos_core.png";}
        else if(name.equals("脆弱的骨片")){return "/drawable/sturdy_bone_shard.png";}
        else if(name.equals("結實的骨片")){return "/drawable/fragile_bone_shard.png";}
        else if(name.equals("石化的骨片")){return "/drawable/fossilized_bone_shard.png";}
        else if(name.equals("霧虛花粉")){return "/drawable/mist_grass_pollen.png";}
        else if(name.equals("霧虛草囊")){return "/drawable/mist_grass_wick.png";}
        else if(name.equals("霧虛燈芯")){return "/drawable/mist_grass.png";}
        else if(name.equals("獵兵祭刀")){return "/drawable/hunters_sacrificial_knife.png";}
        else if(name.equals("特工祭刀")){return "/drawable/inspectors_sacrificial_knife.png";}
        else if(name.equals("督察長祭刀")){return "/drawable/agents_sacrificial_knife.png";}
        else if(name.equals("沉重號角")){return "/drawable/heavy_horn.png";}
        else if(name.equals("黑銅號角")){return "/drawable/black_bronze_horn.png";}
        else if(name.equals("黑晶號角")){return "/drawable/black_crystal_horn.png";}
        else if(name.equals("地脈的舊枝")){return "/drawable/dead_ley_line_branch.png";}
        else if(name.equals("地脈的枯葉")){return "/drawable/dead_ley_line_leaves.png";}
        else if(name.equals("地脈的新芽")){return "/drawable/ley_line_sprout.png";}
        else if(name.equals("黯淡棱鏡")){return "/drawable/dismal_prism.png";}
        else if(name.equals("水晶棱鏡")){return "/drawable/crystal_prism.png";}
        else if(name.equals("偏光棱鏡")){return "/drawable/polarizing_prism.png";}
        else if(name.equals("隱獸指爪")){return "/drawable/concealed_claw.png";}
        else if(name.equals("隱獸利爪")){return "/drawable/concealed_unguis.png";}
        else if(name.equals("隱獸鬼爪")){return "/drawable/concealed_talon.png";}
        //add in 20220105
        else if(name.equals("獸境王器")){return "/drawable/riftborn_regalia.png";}
        else if(name.equals("龍嗣偽鰭")){return "/drawable/dragonheirs_false_fin.png";}


        /** T-Book*/
        else if(name.equals("「自由」的教導")){return "/drawable/teaching_of_freedom.png";}
        else if(name.equals("「黃金」的教導")){return "/drawable/teaching_of_gold.png";}
        else if(name.equals("「抗爭」的教導")){return "/drawable/teaching_of_resistance.png";}
        else if(name.equals("「繁榮」的教導")){return "/drawable/teaching_of_prosperity.png";}
        else if(name.equals("「詩文」的教導")){return "/drawable/teaching_of_ballad.png";}
        else if(name.equals("「勤勞」的教導")){return "/drawable/teaching_of_diligence.png";}
        else if(name.equals("「風雅」的教導")){return "/drawable/teachings_of_elegance.png";}
        else if(name.equals("「浮世」的教導")){return "/drawable/teachings_of_transience.png";}
        else if(name.equals("「天光」的教導")){return "/drawable/teachings_of_light.png";}
        else if(name.equals("「自由」的指引")){return "/drawable/guide_to_freedom.png";}
        else if(name.equals("「黃金」的指引")){return "/drawable/guide_to_gold.png";}
        else if(name.equals("「抗爭」的指引")){return "/drawable/guide_to_resistance.png";}
        else if(name.equals("「繁榮」的指引")){return "/drawable/guide_to_prosperity.png";}
        else if(name.equals("「詩文」的指引")){return "/drawable/guide_to_ballad.png";}
        else if(name.equals("「勤勞」的指引")){return "/drawable/guide_to_diligence.png";}
        else if(name.equals("「風雅」的指引")){return "/drawable/guide_of_elegance.png";}
        else if(name.equals("「浮世」的指引")){return "/drawable/guide_of_transience.png";}
        else if(name.equals("「天光」的指引")){return "/drawable/guide_of_light.png";}
        else if(name.equals("「自由」的哲學")){return "/drawable/philosophies_of_freedom.png";}
        else if(name.equals("「黃金」的哲學")){return "/drawable/philosophies_of_gold.png";}
        else if(name.equals("「抗爭」的哲學")){return "/drawable/philosophies_of_resistance.png";}
        else if(name.equals("「繁榮」的哲學")){return "/drawable/philosophies_of_prosperity.png";}
        else if(name.equals("「詩文」的哲學")){return "/drawable/philosophies_of_ballad.png";}
        else if(name.equals("「勤勞」的哲學")){return "/drawable/philosophies_of_diligence.png";}
        else if(name.equals("「風雅」的哲學")){return "/drawable/philosophies_of_elegance.png";}
        else if(name.equals("「浮世」的哲學")){return "/drawable/philosophies_of_transience.png";}
        else if(name.equals("「天光」的哲學")){return "/drawable/philosophies_of_light.png";}

        /** Crystal*/
        else if(name.equals("燃願瑪瑙碎屑")){return "/drawable/agnidus_agate_sliver.png";}
        else if(name.equals("燃願瑪瑙斷片")){return "/drawable/agnidus_agate_fragment.png";}
        else if(name.equals("燃願瑪瑙塊")){return "/drawable/agnidus_agate_chunk.png";}
        else if(name.equals("燃願瑪瑙")){return "/drawable/agnidus_agate_gemstone.png";}
        else if(name.equals("滌淨青金碎屑")){return "/drawable/varunada_lazurite_sliver.png";}
        else if(name.equals("滌淨青金斷片")){return "/drawable/varunada_lazurite_fragment.png";}
        else if(name.equals("滌淨青金塊")){return "/drawable/varunada_lazurite_chunk.png";}
        else if(name.equals("滌淨青金")){return "/drawable/varunada_lazurite_gemstone.png";}
        else if(name.equals("最勝紫晶碎屑")){return "/drawable/vajrada_amethyst_sliver.png";}
        else if(name.equals("最勝紫晶斷片")){return "/drawable/vajrada_amethyst_fragment.png";}
        else if(name.equals("最勝紫晶塊")){return "/drawable/vajrada_amethyst_chunk.png";}
        else if(name.equals("最勝紫晶")){return "/drawable/vajrada_amethyst_gemstone.png";}
        else if(name.equals("哀敘冰玉碎屑")){return "/drawable/shivada_jade_sliver.png";}
        else if(name.equals("哀敘冰玉斷片")){return "/drawable/shivada_jade_fragment.png";}
        else if(name.equals("哀敘冰玉塊")){return "/drawable/shivada_jade_chunk.png";}
        else if(name.equals("哀敘冰玉")){return "/drawable/shivada_jade_gemstone.png";}
        else if(name.equals("自在松石碎屑")){return "/drawable/vayuda_turquoise_sliver.png";}
        else if(name.equals("自在松石斷片")){return "/drawable/vayuda_turquoise_fragment.png";}
        else if(name.equals("自在松石塊")){return "/drawable/vayuda_turquoise_chunk.png";}
        else if(name.equals("自在松石")){return "/drawable/vayuda_turquoise_gemstone.png";}
        else if(name.equals("堅牢黃玉碎屑")){return "/drawable/prithiva_topaz_sliver.png";}
        else if(name.equals("堅牢黃玉斷片")){return "/drawable/prithiva_topaz_fragment.png";}
        else if(name.equals("堅牢黃玉塊")){return "/drawable/prithiva_topaz_chunk.png";}
        else if(name.equals("堅牢黃玉")){return "/drawable/prithiva_topaz_gemstone.png";}
        else if(name.equals("brilliant_diamond_sliver")){return "/drawable/brilliant_diamond_sliver.png";}
        else if(name.equals("brilliant_diamond_fragment")){return "/drawable/brilliant_diamond_fragment.png";}
        else if(name.equals("brilliant_diamond_chunk")){return "/drawable/brilliant_diamond_chunk.png";}
        else if(name.equals("brilliant_diamond_gemstone")){return "/drawable/brilliant_diamond_gemstone.png";}
        else if(name.equals("nagadus_emerald_sliver")){return "/drawable/nagadus_emerald_sliver.png";}
        else if(name.equals("nagadus_emerald_fragment")){return "/drawable/nagadus_emerald_fragment.png";}
        else if(name.equals("nagadus_emerald_chunk")){return "/drawable/nagadus_emerald_chunk.png";}
        else if(name.equals("nagadus_emerald_gemstone")){return "/drawable/nagadus_emerald_gemstone.png";}

        /** Others*/
        else if(name.equals("智識之冕")){return "/drawable/crown_of_sagehood.png";}
        else if(name.equals("摩拉")){return "/drawable/mora.png";}
        else if(name.equals("流浪者的經驗")){return "/drawable/wanderers_advice.png";}
        else if(name.equals("冒險家的經驗")){return "/drawable/adventurers_experience.png";}
        else if(name.equals("大英雄的經驗")){return "/drawable/heros_wit.png";}
        else if(name.equals("精鍛用雜礦")){return "/drawable/enchancement_ore.png";}
        else if(name.equals("精鍛用良礦")){return "/drawable/fine_enchancement_ore.png";}
        else if(name.equals("精鍛用魔礦")){return "/drawable/mystic_enchancement_ore.png";}
        else if(name.equals("祝聖油膏")){return "/drawable/sanctifying_unction.png";}
        else if(name.equals("祝聖精華")){return "/drawable/sanctifying_essence.png";}

        else {return "/drawable/paimon_lost.png";}
    }

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
        else if(name.equals("「自由」的指引")){return context.getString(R.string.guide_of_freedom);}
        else if(name.equals("「黃金」的指引")){return context.getString(R.string.guide_of_gold);}
        else if(name.equals("「抗爭」的指引")){return context.getString(R.string.guide_of_resistance);}
        else if(name.equals("「繁榮」的指引")){return context.getString(R.string.guide_of_prosperity);}
        else if(name.equals("「詩文」的指引")){return context.getString(R.string.guide_of_ballad);}
        else if(name.equals("「勤勞」的指引")){return context.getString(R.string.guide_of_diligence);}
        else if(name.equals("「風雅」的指引")){return context.getString(R.string.guides_of_elegance);}
        else if(name.equals("「浮世」的指引")){return context.getString(R.string.guides_of_transience);}
        else if(name.equals("「天光」的指引")){return context.getString(R.string.guides_of_light);}
        else if(name.equals("「自由」的哲學")){return context.getString(R.string.philosophies_of_freedom);}
        else if(name.equals("「黃金」的哲學")){return context.getString(R.string.philosophies_of_gold);}
        else if(name.equals("「抗爭」的哲學")){return context.getString(R.string.philosophies_of_resistance);}
        else if(name.equals("「繁榮」的哲學")){return context.getString(R.string.philosophies_of_prosperity);}
        else if(name.equals("「詩文」的哲學")){return context.getString(R.string.philosophies_of_ballad);}
        else if(name.equals("「勤勞」的哲學")){return context.getString(R.string.philosophies_of_diligence);}
        else if(name.equals("「風雅」的哲學")){return context.getString(R.string.philosophiess_of_elegance);}
        else if(name.equals("「浮世」的哲學")){return context.getString(R.string.philosophiess_of_transience);}
        else if(name.equals("「天光」的哲學")){return context.getString(R.string.philosophiess_of_light);}

        else {return context.getString(R.string.unknown);}

    }

    public String getLocaleBirth (String str,Context context) {
        if(!str.equals("SET_BY_PLAYER")){
        String[] date = str.split("/");
        int month = Integer.parseInt(date[0]);
        int day = Integer.parseInt(date[1]);

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
        }else return context.getString(R.string.set_by_player);
    }

    public String char_birth(int moy, int dom){
        String char_name = "EMPTY";
        switch (moy){
            case Calendar.JANUARY: {
                switch (dom) {
                    case 9 : char_name = "Thoma"; break;
                    case 18 : char_name = "Diona"; break;
                    case 24 : char_name = "Rosaria"; break;
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
                }
                break;
            }
            case Calendar.APRIL: {
                switch (dom) {
                    case 4 : char_name = "Aloy"; break;
                    case 17 : char_name = "Xiao"; break;
                    case 30 : char_name = "Diluc"; break;
                }
                break;
            }
            case Calendar.MAY: {
                switch (dom) {
                    case 18 : char_name = "Gorou"; break;
                    case 21 : char_name = "Yun Jin"; break;
                    case 27 : char_name = "Fischl"; break;
                }
                break;
            }
            case Calendar.JUNE: {
                switch (dom) {
                    case 1 : char_name = "Arataki Itto"; break;
                    case 9 : char_name = "Lisa"; break;
                    case 16 : char_name = "Venti"; break;
                    case 21 : char_name = "Yoimiya"; break;
                    case 26 : char_name = "Raiden Shogun"; break;
                    case 27 : char_name = "Yae Miko"; break;
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
                    case 31 : char_name = "Zhongli"; break;
                }
                break;
            }

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
            case "EleDMG_Dendor" : return context.getString(R.string.weapon_stat_EleDMGP_Dendor);
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
