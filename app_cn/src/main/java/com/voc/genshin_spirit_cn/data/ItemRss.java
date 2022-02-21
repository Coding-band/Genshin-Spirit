package com.voc.genshin_spirit_cn.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;

import com.voc.genshin_spirit_cn.R;

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

    public int[] getWeaponByName(String str) {
        switch (str){
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
            //add in 20220126
            case "Kagura's Verity" : return new int[] {R.string.kaguras_verity,R.drawable.kaguras_verity};
            case "Oathsworn Eye" : return new int[] {R.string.oathsworn_eye,R.drawable.oathsworn_eye};

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

            default : return new int[] {R.string.unknown,R.drawable.paimon_lost};
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
            case "Prayers of Destiny" : return new int[] {R.string.prayers_of_destiny,R.drawable.prayers_of_destiny_4};
            case "Prayers of Illumination" : return new int[] {R.string.prayers_of_illumination,R.drawable.prayers_of_illumination_4};
            case "Prayers of Wisdom" : return new int[] {R.string.prayers_of_wisdom,R.drawable.prayers_of_wisdom_4};
            case "Prayers of Springtime" : return new int[] {R.string.prayers_of_springtime,R.drawable.prayers_of_springtime_4};
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

            default: return new int[] {R.string.unknown,R.drawable.paimon_lost,R.drawable.paimon_lost,R.drawable.paimon_lost,R.drawable.paimon_lost,R.drawable.paimon_lost};

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
    public int[]  getCharByName(String name, Context context){
        if(name.equals("Kamisato Ayato")){return new int[] {R.drawable.kamisato_ayato_flag,R.string.kamisato_ayato,R.drawable.kamisato_ayato_full,R.drawable.kamisato_ayato_ico};}
        else if(name.equals("Yae Miko")){return new int[] {R.drawable.yae_miko_flag,R.string.yae_miko,R.drawable.yae_miko_full,R.drawable.yae_miko_ico};}
        else if(name.equals("Shenhe")){return new int[] {R.drawable.shenhe_flag,R.string.shenhe,R.drawable.shenhe_full,R.drawable.shenhe_ico};}
        else if(name.equals("Yun Jin")){return new int[] {R.drawable.yun_jin_flag,R.string.yun_jin,R.drawable.yun_jin_full,R.drawable.yun_jin_ico};}
        else if(name.equals("Gorou")){return new int[] {R.drawable.gorou_flag,R.string.gorou,R.drawable.gorou_full,R.drawable.gorou_ico};}
        else if(name.equals("Arataki Itto")){return new int[] {R.drawable.arataki_itto_flag,R.string.arataki_itto,R.drawable.arataki_itto_full,R.drawable.arataki_itto_ico};}
        else if(name.equals("Thoma")){return new int[] {R.drawable.thoma_flag,R.string.thoma,R.drawable.thoma_full,R.drawable.thoma_ico};}
        else if(name.equals("Sangonomiya Kokomi")){return new int[] {R.drawable.sangonomiya_kokomi_flag,R.string.sangonomiya_kokomi,R.drawable.sangonomiya_kokomi_full,R.drawable.sangonomiya_kokomi_ico};}
        else if(name.equals("Aloy")){return new int[] {R.drawable.aloy,R.string.aloy,R.drawable.aloy,R.drawable.aloy_ico};}
        else if(name.equals("Kujou Sara")){return new int[] {R.drawable.kujou_sara_flag,R.string.kujou_sara,R.drawable.kujou_sara_full,R.drawable.kujou_sara_ico};}
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
        //Add at 20210820, update at 20220109
        else if(name.equals("Traveler-Anemo")){
            sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
            String traveler_sex = sharedPreferences.getString("traveler_sex","F");

            if(traveler_sex.equals("M")){
                return new int[] {R.drawable.aether_flag,R.string.traveler_anemo,R.drawable.aether_full,R.drawable.aether_ico};
            }else{
                return new int[] {R.drawable.lumine_flag,R.string.traveler_anemo,R.drawable.lumine_full,R.drawable.lumine_ico};
            }
        }
        //Add at 20210820, update at 20220109
        else if(name.equals("Traveler-Geo")){
            sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
            String traveler_sex = sharedPreferences.getString("traveler_sex","F");

            if(traveler_sex.equals("M")){
                return new int[] {R.drawable.aether_flag,R.string.traveler_geo,R.drawable.aether_full,R.drawable.aether_ico};
            }else{
                return new int[] {R.drawable.lumine_flag,R.string.traveler_geo,R.drawable.lumine_full,R.drawable.lumine_ico};
            }
        }
        //Add at 20210820, update at 20220109
        else if(name.equals("Traveler-Electro")){
            sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
            String traveler_sex = sharedPreferences.getString("traveler_sex","F");

            if(traveler_sex.equals("M")){
                return new int[] {R.drawable.aether_flag,R.string.traveler_electro,R.drawable.aether_full,R.drawable.aether_ico};
            }else{
                return new int[] {R.drawable.lumine_flag,R.string.traveler_electro,R.drawable.lumine_full,R.drawable.lumine_ico};
            }
        }
        // ???
        else if(name.equals("Voc-夜芷冰")){return new int[] {R.drawable.mascot,R.string.voc,R.drawable.mascot,R.drawable.mascot_head};}

        return new int[] {R.drawable.paimon_lost,R.string.unknown,R.drawable.paimon_lost,R.drawable.paimon_lost};
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
    public int getItemIcoByName (String name ,Context context){
        /** Boss*/
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
        //add in 20210910
        else if(name.equals("雷霆數珠")){return R.drawable.storm_beads;}
        else if(name.equals("排異之露")){return R.drawable.dew_of_repudiation;}

        /** Local*/
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
        //add in 20210910
        else if(name.equals("天雲草實")){return R.drawable.amakumo_fruit;}
        else if(name.equals("血斛")){return R.drawable.dendrobium;}
        else if(name.equals("幽燈蕈")){return R.drawable.fluorescent_fungus;}
        else if(name.equals("珊瑚真珠")){return R.drawable.sango_pearl;}


        /** T-Boss*/
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
        //add in 20210910
        else if(name.equals("熔毀之刻")){return R.drawable.molten_moment;}
        else if(name.equals("灰燼之心")){return R.drawable.ashen_heart;}
        else if(name.equals("獄火之蝶")){return R.drawable.hellfire_butterfly;}
        //add in 20220216
        else if(name.equals("萬劫之真意")){return R.drawable.the_meaning_of_aeons;}
        else if(name.equals("凶將之手眼")){return R.drawable.mudra_of_the_malefic_general;}
        else if(name.equals("禍神之禊淚")){return R.drawable.tears_of_the_calamitous_god;}

        /** Common*/
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
        //add in 20210910
        else if(name.equals("浮游乾核")){return R.drawable.spectral_husk;}
        else if(name.equals("浮游幽核")){return R.drawable.spectral_heart;}
        else if(name.equals("浮游晶化核")){return R.drawable.spectral_nucleus;}
        //add in 20211024 (RE)
        else if(name.equals("漆黑隕鐵的一粒")){return R.drawable.grain_of_aerosiderite;}
        else if(name.equals("漆黑隕鐵的一片")){return R.drawable.piece_of_aerosiderite;}
        else if(name.equals("漆黑隕鐵的一角")){return R.drawable.bit_of_aerosiderite;}
        else if(name.equals("漆黑隕鐵的一塊")){return R.drawable.chunk_of_aerosiderite;}
        else if(name.equals("鳴神御靈的明惠")){return R.drawable.narukamis_wisdom;}
        else if(name.equals("鳴神御靈的歡喜")){return R.drawable.narukamis_joy;}
        else if(name.equals("鳴神御靈的親愛")){return R.drawable.narukamis_affection;}
        else if(name.equals("鳴神御靈的勇武")){return R.drawable.narukamis_valor;}
        else if(name.equals("遠海夷地的瑚枝")){return R.drawable.coral_branch_of_a_distant_sea;}
        else if(name.equals("遠海夷地的玉枝")){return R.drawable.jeweled_branch_of_a_distant_sea;}
        else if(name.equals("遠海夷地的瓊枝")){return R.drawable.jade_branch_of_a_distant_sea;}
        else if(name.equals("遠海夷地的金枝")){return R.drawable.golden_branch_of_a_distant_sea;}
        else if(name.equals("凜風奔狼的始齔")){return R.drawable.boreal_wolfs_milk_tooth;}
        else if(name.equals("凜風奔狼的裂齒")){return R.drawable.boreal_wolfs_cracked_tooth;}
        else if(name.equals("凜風奔狼的斷牙")){return R.drawable.boreal_wolfs_broken_fang;}
        else if(name.equals("凜風奔狼的懷鄉")){return R.drawable.boreal_wolfs_nostalgia;}
        else if(name.equals("高塔孤王的破瓦")){return R.drawable.tile_of_decarabians_tower;}
        else if(name.equals("高塔孤王的殘垣")){return R.drawable.debris_of_decarabians_city;}
        else if(name.equals("高塔孤王的斷片")){return R.drawable.fragment_of_decarabians_epic;}
        else if(name.equals("高塔孤王的碎夢")){return R.drawable.scattered_piece_of_decarabianss_dream;}
        else if(name.equals("霧海雲間的鉛丹")){return R.drawable.mist_veiled_lead_elixir;}
        else if(name.equals("霧海雲間的汞丹")){return R.drawable.mist_veiled_mercury_elixir;}
        else if(name.equals("霧海雲間的金丹")){return R.drawable.mist_veiled_gold_elixir;}
        else if(name.equals("霧海雲間的轉還")){return R.drawable.mist_veiled_primo_elixir;}
        else if(name.equals("獅牙鬥士的枷鎖")){return R.drawable.fetters_of_the_dandelion_gladiator;}
        else if(name.equals("獅牙鬥士的鐵鍊")){return R.drawable.chains_of_the_dandelion_gladiator;}
        else if(name.equals("獅牙鬥士的鐐銬")){return R.drawable.shackles_of_the_dandelion_gladiator;}
        else if(name.equals("獅牙鬥士的理想")){return R.drawable.dream_of_the_dandelion_gladiator;}
        else if(name.equals("孤雲寒林的光砂")){return R.drawable.luminous_sands_from_guyun;}
        else if(name.equals("孤雲寒林的輝岩")){return R.drawable.lustrous_stone_from_guyun;}
        else if(name.equals("孤雲寒林的聖骸")){return R.drawable.relic_from_guyun;}
        else if(name.equals("孤雲寒林的神體")){return R.drawable.divine_body_from_guyun;}
        else if(name.equals("今昔劇畫的惡尉")){return R.drawable.mask_of_the_wicked_lieutenant;}
        else if(name.equals("今昔劇畫的虎囓")){return R.drawable.mask_of_the_tigers_bite;}
        else if(name.equals("今昔劇畫的一角")){return R.drawable.mask_of_the_one_horned;}
        else if(name.equals("今昔劇畫的鬼人")){return R.drawable.mask_of_the_kijin;}

        else if(name.equals("混沌機關")){return R.drawable.chaos_gear;}
        else if(name.equals("混沌樞紐")){return R.drawable.chaos_axis;}
        else if(name.equals("混沌真眼")){return R.drawable.chaos_oculus;}
        else if(name.equals("混沌裝置")){return R.drawable.chaos_device;}
        else if(name.equals("混沌迴路")){return R.drawable.chaos_circuit;}
        else if(name.equals("混沌爐心")){return R.drawable.chaos_core;}
        else if(name.equals("脆弱的骨片")){return R.drawable.sturdy_bone_shard;}
        else if(name.equals("結實的骨片")){return R.drawable.fragile_bone_shard;}
        else if(name.equals("石化的骨片")){return R.drawable.fossilized_bone_shard;}
        else if(name.equals("霧虛花粉")){return R.drawable.mist_grass_pollen;}
        else if(name.equals("霧虛草囊")){return R.drawable.mist_grass_wick;}
        else if(name.equals("霧虛燈芯")){return R.drawable.mist_grass;}
        else if(name.equals("獵兵祭刀")){return R.drawable.hunters_sacrificial_knife;}
        else if(name.equals("特工祭刀")){return R.drawable.inspectors_sacrificial_knife;}
        else if(name.equals("督察長祭刀")){return R.drawable.agents_sacrificial_knife;}
        else if(name.equals("沉重號角")){return R.drawable.heavy_horn;}
        else if(name.equals("黑銅號角")){return R.drawable.black_bronze_horn;}
        else if(name.equals("黑晶號角")){return R.drawable.black_crystal_horn;}
        else if(name.equals("地脈的舊枝")){return R.drawable.dead_ley_line_branch;}
        else if(name.equals("地脈的枯葉")){return R.drawable.dead_ley_line_leaves;}
        else if(name.equals("地脈的新芽")){return R.drawable.ley_line_sprout;}
        else if(name.equals("黯淡棱鏡")){return R.drawable.dismal_prism;}
        else if(name.equals("水晶棱鏡")){return R.drawable.crystal_prism;}
        else if(name.equals("偏光棱鏡")){return R.drawable.polarizing_prism;}
        else if(name.equals("隱獸指爪")){return R.drawable.concealed_claw;}
        else if(name.equals("隱獸利爪")){return R.drawable.concealed_unguis;}
        else if(name.equals("隱獸鬼爪")){return R.drawable.concealed_talon;}
        //add in 20220105
        else if(name.equals("獸境王器")){return R.drawable.riftborn_regalia;}
        else if(name.equals("龍嗣偽鰭")){return R.drawable.dragonheirs_false_fin;}


        /** T-Book*/
        else if(name.equals("「自由」的教導")){return R.drawable.teaching_of_freedom;}
        else if(name.equals("「黃金」的教導")){return R.drawable.teaching_of_gold;}
        else if(name.equals("「抗爭」的教導")){return R.drawable.teaching_of_resistance;}
        else if(name.equals("「繁榮」的教導")){return R.drawable.teaching_of_prosperity;}
        else if(name.equals("「詩文」的教導")){return R.drawable.teaching_of_ballad;}
        else if(name.equals("「勤勞」的教導")){return R.drawable.teaching_of_diligence;}
        else if(name.equals("「風雅」的教導")){return R.drawable.teachings_of_elegance;}
        else if(name.equals("「浮世」的教導")){return R.drawable.teachings_of_transience;}
        else if(name.equals("「天光」的教導")){return R.drawable.teachings_of_light;}
        else if(name.equals("「自由」的指引")){return R.drawable.guide_to_freedom;}
        else if(name.equals("「黃金」的指引")){return R.drawable.guide_to_gold;}
        else if(name.equals("「抗爭」的指引")){return R.drawable.guide_to_resistance;}
        else if(name.equals("「繁榮」的指引")){return R.drawable.guide_to_prosperity;}
        else if(name.equals("「詩文」的指引")){return R.drawable.guide_to_ballad;}
        else if(name.equals("「勤勞」的指引")){return R.drawable.guide_to_diligence;}
        else if(name.equals("「風雅」的指引")){return R.drawable.guide_of_elegance;}
        else if(name.equals("「浮世」的指引")){return R.drawable.guide_of_transience;}
        else if(name.equals("「天光」的指引")){return R.drawable.guide_of_light;}
        else if(name.equals("「自由」的哲學")){return R.drawable.philosophies_of_freedom;}
        else if(name.equals("「黃金」的哲學")){return R.drawable.philosophies_of_gold;}
        else if(name.equals("「抗爭」的哲學")){return R.drawable.philosophies_of_resistance;}
        else if(name.equals("「繁榮」的哲學")){return R.drawable.philosophies_of_prosperity;}
        else if(name.equals("「詩文」的哲學")){return R.drawable.philosophies_of_ballad;}
        else if(name.equals("「勤勞」的哲學")){return R.drawable.philosophies_of_diligence;}
        else if(name.equals("「風雅」的哲學")){return R.drawable.philosophies_of_elegance;}
        else if(name.equals("「浮世」的哲學")){return R.drawable.philosophies_of_transience;}
        else if(name.equals("「天光」的哲學")){return R.drawable.philosophies_of_light;}

        /** Crystal*/
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
        else if(name.equals("nagadus_emerald_gemstone")){return R.drawable.nagadus_emerald_gemstone;}

        /** Others*/
        else if(name.equals("智識之冕")){return R.drawable.crown_of_sagehood;}
        else if(name.equals("摩拉")){return R.drawable.mora;}
        else if(name.equals("流浪者的經驗")){return R.drawable.wanderers_advice;}
        else if(name.equals("冒險家的經驗")){return R.drawable.adventurers_experience;}
        else if(name.equals("大英雄的經驗")){return R.drawable.heros_wit;}
        else if(name.equals("精鍛用雜礦")){return R.drawable.enchancement_ore;}
        else if(name.equals("精鍛用良礦")){return R.drawable.fine_enchancement_ore;}
        else if(name.equals("精鍛用魔礦")){return R.drawable.mystic_enchancement_ore;}
        else if(name.equals("祝聖油膏")){return R.drawable.sanctifying_unction;}
        else if(name.equals("祝聖精華")){return R.drawable.sanctifying_essence;}

        else {return R.drawable.paimon_lost;}
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

    public int getArtifactBuffName (String nickname){
        switch (nickname){
            case "baseHP" : return R.string.weapon_stat_HP;
            case "baseATK" : return R.string.weapon_stat_atk;
            case "baseDEF" : return R.string.weapon_stat_DEF;
            case "HP" : return R.string.weapon_stat_HPP;
            case "ATK" : return R.string.weapon_stat_atkP;
            case "DEF" : return R.string.weapon_stat_DEFP;
            case "EleMas" : return R.string.weapon_stat_EleMas;
            case "EnRech" : return R.string.weapon_stat_EnRechP;
            case "PhyDMG" : return R.string.weapon_stat_PhyDMGP;
            case "EleDMG_Electro" : return R.string.weapon_stat_EleDMGP_Electro;
            case "EleDMG_Pyro" : return R.string.weapon_stat_EleDMGP_Pyro;
            case "EleDMG_Hydro" : return R.string.weapon_stat_EleDMGP_Hydro;
            case "EleDMG_Dendor" : return R.string.weapon_stat_EleDMGP_Dendor;
            case "EleDMG_Cryo" : return R.string.weapon_stat_EleDMGP_Cryo;
            case "EleDMG_Anemo" : return R.string.weapon_stat_EleDMGP_Anemo;
            case "EleDMG_Geo" : return R.string.weapon_stat_EleDMGP_Geo;
            case "CritRate" : return R.string.weapon_stat_CritRateP;
            case "CritDMG" : return R.string.weapon_stat_CritDMGP;
            case "Healing" : return R.string.weapon_stat_HealingP;

            default: return R.string.unknown;
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
