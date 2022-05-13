package com.voc.genshin_helper.util;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.voc.genshin_helper.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapToRGB565 {
    public static  void order(Context context){
        String[] arr = new String[]{"/drawable/about_icon.png","/drawable/abyss_herald_wicked_torrents.png","/drawable/abyss_lector_fathomless_flames.png","/drawable/abyss_lector_violet_lightning.png","/drawable/acquaint_fate.png","/drawable/adventurers_experience.png","/drawable/adventurer_1.png","/drawable/adventurer_2.png","/drawable/adventurer_3.png","/drawable/adventurer_4.png","/drawable/adventurer_5.png","/drawable/aether_flag.png","/drawable/aether_full.png","/drawable/aether_ico.png","/drawable/agents_sacrificial_knife.png","/drawable/agnidus_agate_chunk.png","/drawable/agnidus_agate_fragment.png","/drawable/agnidus_agate_gemstone.png","/drawable/agnidus_agate_sliver.png","/drawable/akuoumaru.png","/drawable/albedo_card.png","/drawable/albedo_flag.png","/drawable/albedo_full.png","/drawable/albedo_gacha_splash.png","/drawable/albedo_ico.png","/drawable/alley_hunter.png","/drawable/aloy.png","/drawable/aloy_card.png","/drawable/aloy_full.png","/drawable/aloy_gacha_splash.png","/drawable/aloy_ico.png","/drawable/amakumo_fruit.png","/drawable/amber_card.png","/drawable/amber_catalyst.png","/drawable/amber_flag.png","/drawable/amber_full.png","/drawable/amber_gacha_splash.png","/drawable/amber_ico.png","/drawable/amber_suit1_flag.png","/drawable/amenoma_kageuchi.png","/drawable/amethyst_lump.png","/drawable/amos_bow.png","/drawable/anemo.png","/drawable/anemo_hypostasis.png","/drawable/anemo_samachurl.png","/drawable/anemo_slime.png","/drawable/anemo_specter.png","/drawable/apprentices_notes.png","/drawable/app_ico.png","/drawable/app_ico_splash.png","/drawable/app_seek_round.png","/drawable/aquila_favonia.png","/drawable/arataki_itto_card.png","/drawable/arataki_itto_flag.png","/drawable/arataki_itto_full.png","/drawable/arataki_itto_gacha_splash.png","/drawable/arataki_itto_ico.png","/drawable/archaic_petra_1.png","/drawable/archaic_petra_2.png","/drawable/archaic_petra_3.png","/drawable/archaic_petra_4.png","/drawable/archaic_petra_5.png","/drawable/art2.png","/drawable/artifacts_ico.png","/drawable/ashen_heart.png","/drawable/ayaka_flag.png","/drawable/ayaka_full.png","/drawable/ayaka_ico.png","/drawable/azhdaha.png","/drawable/barbara_card.png","/drawable/barbara_flag.png","/drawable/barbara_full.png","/drawable/barbara_gacha_splash.png","/drawable/barbara_ico.png","/drawable/barbara_suit1_full.png","/drawable/barbara_suit1_gacha_splash.png","/drawable/barbara_suit1_ico.png","/drawable/basalt_pillar.png","/drawable/beginners_protector.png","/drawable/beidou_card.png","/drawable/beidou_flag.png","/drawable/beidou_full.png","/drawable/beidou_gacha_splash.png","/drawable/beidou_ico.png","/drawable/bennett_card.png","/drawable/bennett_flag.png","/drawable/bennett_full.png","/drawable/bennett_gacha_splash.png","/drawable/bennett_ico.png","/drawable/berserker_1.png","/drawable/berserker_2.png","/drawable/berserker_3.png","/drawable/berserker_4.png","/drawable/berserker_5.png","/drawable/bird_egg.png","/drawable/bit_of_aerosiderite.png","/drawable/blackcliff_agate.png","/drawable/blackcliff_amulet.png","/drawable/blackcliff_longsword.png","/drawable/blackcliff_pole.png","/drawable/blackcliff_slasher.png","/drawable/blackcliff_warbow.png","/drawable/black_bronze_horn.png","/drawable/black_crystal_horn.png","/drawable/black_tassel.png","/drawable/blazing_axe_mitachurl.png","/drawable/blizzard_strayer_1.png","/drawable/blizzard_strayer_2.png","/drawable/blizzard_strayer_3.png","/drawable/blizzard_strayer_4.png","/drawable/blizzard_strayer_5.png","/drawable/bloodjade_branch.png","/drawable/bloodstained_chivalry_1.png","/drawable/bloodstained_chivalry_2.png","/drawable/bloodstained_chivalry_3.png","/drawable/bloodstained_chivalry_4.png","/drawable/bloodstained_chivalry_5.png","/drawable/bloodtainted_greatsword.png","/drawable/bolteater_bathysmal_vishap.png","/drawable/boreal_wolfs_broken_fang.png","/drawable/boreal_wolfs_cracked_tooth.png","/drawable/boreal_wolfs_milk_tooth.png","/drawable/boreal_wolfs_nostalgia.png","/drawable/brave_heart_1.png","/drawable/brave_heart_2.png","/drawable/brave_heart_3.png","/drawable/brave_heart_4.png","/drawable/brave_heart_5.png","/drawable/brilliant_diamond_chunk.png","/drawable/brilliant_diamond_fragment.png","/drawable/brilliant_diamond_gemstone.png","/drawable/brilliant_diamond_sliver.png","/drawable/cake.png","/drawable/calamity_queller.png","/drawable/calla_lily.png","/drawable/carrot.png","/drawable/cecilia.png","/drawable/chains_of_the_dandelion_gladiator.png","/drawable/chaos_axis.png","/drawable/chaos_circuit.png","/drawable/chaos_core.png","/drawable/chaos_device.png","/drawable/chaos_gear.png","/drawable/chaos_oculus.png","/drawable/childe.png","/drawable/chongyun_card.png","/drawable/chongyun_flag.png","/drawable/chongyun_full.png","/drawable/chongyun_gacha_splash.png","/drawable/chongyun_ico.png","/drawable/chunk_of_aerosiderite.png","/drawable/cinnabar_spindle.png","/drawable/cleansing_heart.png","/drawable/compound_bow.png","/drawable/concealed_claw.png","/drawable/concealed_talon.png","/drawable/concealed_unguis.png","/drawable/cool_steel.png","/drawable/coral_branch_of_a_distant_sea.png","/drawable/cor_lapis.png","/drawable/crackling_axe_mitachurl.png","/drawable/crescent_pike.png","/drawable/crimson_witch_of_flames_1.png","/drawable/crimson_witch_of_flames_2.png","/drawable/crimson_witch_of_flames_3.png","/drawable/crimson_witch_of_flames_4.png","/drawable/crimson_witch_of_flames_5.png","/drawable/crown_of_sagehood.png","/drawable/cryo.png","/drawable/cryo_abyss_mage.png","/drawable/cryo_cicin.png","/drawable/cryo_hilichurl_grenadier.png","/drawable/cryo_hilichurl_shooter.png","/drawable/cryo_hypostasis.png","/drawable/cryo_regisvine.png","/drawable/cryo_samachurl.png","/drawable/cryo_slime.png","/drawable/cryo_specter.png","/drawable/cryo_whopperflower.png","/drawable/crystalline_bloom.png","/drawable/crystal_chunk.png","/drawable/crystal_marrow.png","/drawable/crystal_prism.png","/drawable/cyro_bathysmal_vishap.png","/drawable/dainsleif_ico.png","/drawable/damaged_mask.png","/drawable/dandelion_seed.png","/drawable/dark_iron_sword.png","/drawable/dark_statuette.png","/drawable/dead_ley_line_branch.png","/drawable/dead_ley_line_leaves.png","/drawable/deathly_statuette.png","/drawable/deathmatch.png","/drawable/debate_club.png","/drawable/debris_of_decarabians_city.png","/drawable/defenders_will_1.png","/drawable/defenders_will_2.png","/drawable/defenders_will_3.png","/drawable/defenders_will_4.png","/drawable/defenders_will_5.png","/drawable/dendro.png","/drawable/dendrobium.png","/drawable/dendro_samachurl.png","/drawable/dendro_slime.png","/drawable/dew_of_repudiation.png","/drawable/diluc_card.png","/drawable/diluc_flag.png","/drawable/diluc_full.png","/drawable/diluc_gacha_splash.png","/drawable/diluc_ico.png","/drawable/diona_card.png","/drawable/diona_flag.png","/drawable/diona_full.png","/drawable/diona_gacha_splash.png","/drawable/diona_ico.png","/drawable/dismal_prism.png","/drawable/divine_body_from_guyun.png","/drawable/divining_scroll.png","/drawable/dodoco_tales.png","/drawable/dragonheirs_false_fin.png","/drawable/dragonspine_spear.png","/drawable/dragons_bane.png","/drawable/dragon_lords_crown.png","/drawable/dreams_of_dragonfell.png","/drawable/dream_of_the_dandelion_gladiator.png","/drawable/dream_solvent.png","/drawable/dull_blade.png","/drawable/dust_of_azoth.png","/drawable/dvalins_claw.png","/drawable/dvalins_plume.png","/drawable/dvalins_sigh.png","/drawable/ebony_bow.png","/drawable/echoes_of_an_offering_1.png","/drawable/echoes_of_an_offering_2.png","/drawable/echoes_of_an_offering_3.png","/drawable/echoes_of_an_offering_4.png","/drawable/echoes_of_an_offering_5.png","/drawable/electro.png","/drawable/electro_abyss_mage.png","/drawable/electro_bathysmal_vishap.png","/drawable/electro_cicin.png","/drawable/electro_hilichurl_grenadier.png","/drawable/electro_hilichurl_shooter.png","/drawable/electro_hypostasis.png","/drawable/electro_samachurl.png","/drawable/electro_slime.png","/drawable/electro_specter.png","/drawable/electro_whopperflower.png","/drawable/elegy_for_the_end.png","/drawable/emblem_of_severed_fate_1.png","/drawable/emblem_of_severed_fate_2.png","/drawable/emblem_of_severed_fate_3.png","/drawable/emblem_of_severed_fate_4.png","/drawable/emblem_of_severed_fate_5.png","/drawable/emerald_orb.png","/drawable/enchancement_ore.png","/drawable/energy_nectar.png","/drawable/engulfing_lightning.png","/drawable/eula_card.png","/drawable/eula_flag.png","/drawable/eula_full.png","/drawable/eula_gacha_splash.png","/drawable/eula_ico.png","/drawable/everflame_seed.png","/drawable/everfrost_core.png","/drawable/everlasting_moonglow.png","/drawable/eye_of_perception.png","/drawable/eye_of_the_storm.png","/drawable/famed_handguard.png","/drawable/fatui_cryo_cicin_mage.png","/drawable/fatui_electro_cicin_mage.png","/drawable/fatui_pyro_agent.png","/drawable/fatui_skirmisher_anemoboxer_vanguard.png","/drawable/fatui_skirmisher_cryogunner_legionnaire.png","/drawable/fatui_skirmisher_electrohammer_vanguard.png","/drawable/fatui_skirmisher_geochanter_bracer.png","/drawable/fatui_skirmisher_hydrogunner_legionnaire.png","/drawable/fatui_skirmisher_pyroslinger_bracer.png","/drawable/favonius_codex.png","/drawable/favonius_greatsword.png","/drawable/favonius_lance.png","/drawable/favonius_sword.png","/drawable/favonius_warbow.png","/drawable/ferrous_shadow.png","/drawable/festering_desire.png","/drawable/festering_fang.png","/drawable/fetters_of_the_dandelion_gladiator.png","/drawable/fillet_blade.png","/drawable/fine_enchancement_ore.png","/drawable/firm_arrowhead.png","/drawable/fischl_card.png","/drawable/fischl_flag.png","/drawable/fischl_full.png","/drawable/fischl_gacha_splash.png","/drawable/fischl_ico.png","/drawable/fluorescent_fungus.png","/drawable/forbidden_curse_scroll.png","/drawable/fossilized_bone_shard.png","/drawable/fowl.png","/drawable/fragile_bone_shard.png","/drawable/fragile_resin.png","/drawable/fragment_of_decarabians_epic.png","/drawable/freedom_sworn.png","/drawable/frostarm_lawachurl.png","/drawable/frostbearer.png","/drawable/gambler_1.png","/drawable/gambler_2.png","/drawable/gambler_3.png","/drawable/gambler_4.png","/drawable/gambler_5.png","/drawable/ganyu.png","/drawable/ganyu_card.png","/drawable/ganyu_flag.png","/drawable/ganyu_full.png","/drawable/ganyu_gacha_splash.png","/drawable/ganyu_ico.png","/drawable/genesis_crystal.png","/drawable/geo.png","/drawable/geovishap.png","/drawable/geovishap_hatchling.png","/drawable/geo_hypostasis.png","/drawable/geo_samachurl.png","/drawable/geo_slime.png","/drawable/geo_specter.png","/drawable/gilded_scale.png","/drawable/glacier_and_snowfield_1.png","/drawable/glacier_and_snowfield_2.png","/drawable/glacier_and_snowfield_3.png","/drawable/glacier_and_snowfield_4.png","/drawable/glacier_and_snowfield_5.png","/drawable/gladiators_finale_1.png","/drawable/gladiators_finale_2.png","/drawable/gladiators_finale_3.png","/drawable/gladiators_finale_4.png","/drawable/gladiators_finale_5.png","/drawable/glaze_lily.png","/drawable/gloomy_statuette.png","/drawable/golden_branch_of_a_distant_sea.png","/drawable/golden_raven_insignia.png","/drawable/golden_wolflord.png","/drawable/gorou_card.png","/drawable/gorou_flag.png","/drawable/gorou_full.png","/drawable/gorou_gacha_splash.png","/drawable/gorou_ico.png","/drawable/grain_of_aerosiderite.png","/drawable/guide_of_elegance.png","/drawable/guide_of_light.png","/drawable/guide_of_transience.png","/drawable/guide_to_ballad.png","/drawable/guide_to_diligence.png","/drawable/guide_to_freedom.png","/drawable/guide_to_gold.png","/drawable/guide_to_prosperity.png","/drawable/guide_to_resistance.png","/drawable/hakushin_ring.png","/drawable/halberd.png","/drawable/hamayumi.png","/drawable/haran_geppaku_futsu.png","/drawable/harbinger_of_dawn.png","/drawable/heart_of_depth_1.png","/drawable/heart_of_depth_2.png","/drawable/heart_of_depth_3.png","/drawable/heart_of_depth_4.png","/drawable/heart_of_depth_5.png","/drawable/heavy_horn.png","/drawable/hellfire_butterfly.png","/drawable/heros_wit.png","/drawable/hilichurl.png","/drawable/hilichurl_berserker.png","/drawable/hilichurl_fighter.png","/drawable/hilichurl_grenadier.png","/drawable/hilichurl_shooter.png","/drawable/hoarfrost_core.png","/drawable/hunters_bow.png","/drawable/hunters_sacrificial_knife.png","/drawable/hurricane_seed.png","/drawable/husk_of_opulent_dreams_1.png","/drawable/husk_of_opulent_dreams_2.png","/drawable/husk_of_opulent_dreams_3.png","/drawable/husk_of_opulent_dreams_4.png","/drawable/husk_of_opulent_dreams_5.png","/drawable/hu_tao_card.png","/drawable/hu_tao_flag.png","/drawable/hu_tao_full.png","/drawable/hu_tao_gacha_splash.png","/drawable/hu_tao_ico.png","/drawable/hydro.png","/drawable/hydro_abyss_mage.png","/drawable/hydro_cicin.png","/drawable/hydro_hypostasis.png","/drawable/hydro_mimic_boar.png","/drawable/hydro_mimic_crab.png","/drawable/hydro_mimic_crane.png","/drawable/hydro_mimic_ferret.png","/drawable/hydro_mimic_finch.png","/drawable/hydro_mimic_frog.png","/drawable/hydro_mimic_mallard.png","/drawable/hydro_mimic_raptor.png","/drawable/hydro_samachurl.png","/drawable/hydro_slime.png","/drawable/hydro_specter.png","/drawable/ice_shieldwall_mitachurl.png","/drawable/ice_shield_hilichurl_guard.png","/drawable/ico_bow.png","/drawable/ico_catalyst.png","/drawable/ico_circlet.png","/drawable/ico_claymore.png","/drawable/ico_flower.png","/drawable/ico_goblet.png","/drawable/ico_plume.png","/drawable/ico_polearm.png","/drawable/ico_sand.png","/drawable/ico_sword.png","/drawable/inspectors_sacrificial_knife.png","/drawable/instructor_1.png","/drawable/instructor_2.png","/drawable/instructor_3.png","/drawable/instructor_4.png","/drawable/instructor_5.png","/drawable/iron_chunk.png","/drawable/iron_point.png","/drawable/iron_sting.png","/drawable/jade_branch_of_a_distant_sea.png","/drawable/jean_card.png","/drawable/jean_flag.png","/drawable/jean_full.png","/drawable/jean_gacha_splash.png","/drawable/jean_ico.png","/drawable/jean_suit1_flag.png","/drawable/jean_suit1_full.png","/drawable/jean_suit1_ico.png","/drawable/jean_suit2_full.png","/drawable/jean_suit2_gacha_splash.png","/drawable/jean_suit2_ico.png","/drawable/jeweled_branch_of_a_distant_sea.png","/drawable/jueyun_chili.png","/drawable/juvenile_jade.png","/drawable/kaedehara_kazuha_card.png","/drawable/kaedehara_kazuha_gacha_splash.png","/drawable/kaeya_card.png","/drawable/kaeya_flag.png","/drawable/kaeya_full.png","/drawable/kaeya_gacha_splash.png","/drawable/kaeya_ico.png","/drawable/kageuchi_handguard.png","/drawable/kaguras_verity.png","/drawable/kairagi_dancing_thunder.png","/drawable/kairagi_fiery_might.png","/drawable/kamisato_ayaka_card.png","/drawable/kamisato_ayaka_gacha_splash.png","/drawable/kamisato_ayato_card.png","/drawable/kamisato_ayato_flag.png","/drawable/kamisato_ayato_full.png","/drawable/kamisato_ayato_gacha_splash.png","/drawable/kamisato_ayato_ico.png","/drawable/katsuragikiri_nagamasa.png","/drawable/kazuha_flag.png","/drawable/kazuha_full.png","/drawable/kazuha_ico.png","/drawable/keqing_card.png","/drawable/keqing_flag.png","/drawable/keqing_full.png","/drawable/keqing_gacha_splash.png","/drawable/keqing_ico.png","/drawable/keqing_suit1_full.png","/drawable/keqing_suit1_gacha_splash.png","/drawable/keqing_suit1_ico.png","/drawable/kitain_cross_spear.png","/drawable/klee_card.png","/drawable/klee_flag.png","/drawable/klee_full.png","/drawable/klee_gacha_splash.png","/drawable/klee_ico.png","/drawable/kujor_sara_card.png","/drawable/kujor_sara_gacha_splash.png","/drawable/kujou_sara_flag.png","/drawable/kujou_sara_full.png","/drawable/kujou_sara_ico.png","/drawable/kuki_shinobu_card.png","/drawable/kuki_shinobu_full.png","/drawable/kuki_shinobu_gacha_splash.png","/drawable/kuki_shinobu_ico.png","/drawable/large_anemo_slime.png","/drawable/large_cryo_slime.png","/drawable/large_dendro_slime.png","/drawable/large_electro_slime.png","/drawable/large_geo_slime.png","/drawable/large_hydro_slime.png","/drawable/large_pyro_slime.png","/drawable/lavawalker_1.png","/drawable/lavawalker_2.png","/drawable/lavawalker_3.png","/drawable/lavawalker_4.png","/drawable/lavawalker_5.png","/drawable/la_signora.png","/drawable/ley_line_sprout.png","/drawable/lieutenants_insignia.png","/drawable/lightning_prism.png","/drawable/lions_roar.png","/drawable/lisa_card.png","/drawable/lisa_flag.png","/drawable/lisa_full.png","/drawable/lisa_gacha_splash.png","/drawable/lisa_ico.png","/drawable/lithic_blade.png","/drawable/lithic_spear.png","/drawable/lost_prayer_to_the_sacred_winds.png","/drawable/lotus_head.png","/drawable/lucky_dog_1.png","/drawable/lucky_dog_2.png","/drawable/lucky_dog_3.png","/drawable/lucky_dog_4.png","/drawable/lucky_dog_5.png","/drawable/lumine_flag.png","/drawable/lumine_full.png","/drawable/lumine_ico.png","/drawable/luminous_sands_from_guyun.png","/drawable/lupus_boreas.png","/drawable/lustrous_stone_from_guyun.png","/drawable/luxurious_sea_lord.png","/drawable/magatsu_mitake_narukami_no_mikoto.png","/drawable/magical_crystal_chunk.png","/drawable/magic_guide.png","/drawable/maguu_kenki.png","/drawable/maguu_kenki_galloping_frost.png","/drawable/maguu_kenki_lone_gale.png","/drawable/maguu_kenki_mask_of_terror.png","/drawable/maguu_kishin.png","/drawable/maiden_beloved_1.png","/drawable/maiden_beloved_2.png","/drawable/maiden_beloved_3.png","/drawable/maiden_beloved_4.png","/drawable/maiden_beloved_5.png","/drawable/mappa_mare.png","/drawable/martial_artist_1.png","/drawable/martial_artist_2.png","/drawable/martial_artist_3.png","/drawable/martial_artist_4.png","/drawable/martial_artist_5.png","/drawable/mascot.png","/drawable/mascot_card.png","/drawable/mascot_head.png","/drawable/mask_of_the_kijin.png","/drawable/mask_of_the_one_horned.png","/drawable/mask_of_the_tigers_bite.png","/drawable/mask_of_the_wicked_lieutenant.png","/drawable/matsutake.png","/drawable/memory_of_dust.png","/drawable/messenger.png","/drawable/millelith_sergeant.png","/drawable/millelith_soldier.png","/drawable/mirror_maiden.png","/drawable/mistsplitter_reforged.png","/drawable/mist_grass.png","/drawable/mist_grass_pollen.png","/drawable/mist_grass_wick.png","/drawable/mist_veiled_gold_elixir.png","/drawable/mist_veiled_lead_elixir.png","/drawable/mist_veiled_mercury_elixir.png","/drawable/mist_veiled_primo_elixir.png","/drawable/mitternachts_waltz.png","/drawable/molten_moment.png","/drawable/mona_card.png","/drawable/mona_flag.png","/drawable/mona_full.png","/drawable/mona_gacha_splash.png","/drawable/mona_ico.png","/drawable/mona_suit1_flag.png","/drawable/mona_suit1_full.png","/drawable/mona_suit1_ico.png","/drawable/mora.png","/drawable/mouuns_moon.png","/drawable/mudra_of_the_malefic_general.png","/drawable/mutant_electro_slime.png","/drawable/mystic_enchancement_ore.png","/drawable/nagadus_emerald_chunk.png","/drawable/nagadus_emerald_fragment.png","/drawable/nagadus_emerald_gemstone.png","/drawable/nagadus_emerald_sliver.png","/drawable/naku_weed.png","/drawable/narukamis_affection.png","/drawable/narukamis_joy.png","/drawable/narukamis_valor.png","/drawable/narukamis_wisdom.png","/drawable/ndm_508.png","/drawable/ningguang_card.png","/drawable/ningguang_flag.png","/drawable/ningguang_full.png","/drawable/ningguang_gacha_splash.png","/drawable/ningguang_ico.png","/drawable/ningguang_suit1_full.png","/drawable/ningguang_suit1_gacha_splash.png","/drawable/ningguang_suit1_ico.png","/drawable/noblesse_oblige_1.png","/drawable/noblesse_oblige_2.png","/drawable/noblesse_oblige_3.png","/drawable/noblesse_oblige_4.png","/drawable/noblesse_oblige_5.png","/drawable/nobushi_hitsukeban.png","/drawable/nobushi_jintouban.png","/drawable/nobushi_kikouban.png","/drawable/noctilous_jade.png","/drawable/noelle_card.png","/drawable/noelle_flag.png","/drawable/noelle_full.png","/drawable/noelle_gacha_splash.png","/drawable/noelle_ico.png","/drawable/northlander_bow_prototype.png","/drawable/northlander_catalyst_prototype.png","/drawable/northlander_claymore_prototype.png","/drawable/northlander_polearm_prototype.png","/drawable/northlander_sword_prototype.png","/drawable/oathsworn_eye.png","/drawable/oceanid.png","/drawable/ocean_hued_clam_1.png","/drawable/ocean_hued_clam_2.png","/drawable/ocean_hued_clam_3.png","/drawable/ocean_hued_clam_4.png","/drawable/ocean_hued_clam_5.png","/drawable/ochimusha_cankered_flame.png","/drawable/ochimusha_ensorcelled_thunder.png","/drawable/old_handguard.png","/drawable/old_mercs_pal.png","/drawable/ominous_mask.png","/drawable/otherworldly_story.png","/drawable/paimon_full.png","/drawable/paimon_lost.png","/drawable/pale_flame_1.png","/drawable/pale_flame_2.png","/drawable/pale_flame_3.png","/drawable/pale_flame_4.png","/drawable/pale_flame_5.png","/drawable/perpetual_heart.png","/drawable/perpetual_mechanical_array.png","/drawable/philanemo_mushroom.png","/drawable/philosophies_of_ballad.png","/drawable/philosophies_of_diligence.png","/drawable/philosophies_of_elegance.png","/drawable/philosophies_of_freedom.png","/drawable/philosophies_of_gold.png","/drawable/philosophies_of_light.png","/drawable/philosophies_of_prosperity.png","/drawable/philosophies_of_resistance.png","/drawable/philosophies_of_transience.png","/drawable/piece_of_aerosiderite.png","/drawable/pocket_grimoire.png","/drawable/polarizing_prism.png","/drawable/prayers_of_destiny_4.png","/drawable/prayers_of_illumination_4.png","/drawable/prayers_of_springtime_4.png","/drawable/prayers_of_wisdom_4.png","/drawable/predator.png","/drawable/primogem.png","/drawable/primordial_bathysmal_vishap.png","/drawable/primordial_jade_cutter.png","/drawable/primordial_jade_winged_spear.png","/drawable/primo_geovishap.png","/drawable/prithiva_topaz_chunk.png","/drawable/prithiva_topaz_fragment.png","/drawable/prithiva_topaz_gemstone.png","/drawable/prithiva_topaz_sliver.png","/drawable/prototype_amber.png","/drawable/prototype_aminus.png","/drawable/prototype_archaic.png","/drawable/prototype_crescent.png","/drawable/prototype_grudge.png","/drawable/prototype_malice.png","/drawable/prototype_rancour.png","/drawable/prototype_starglitter.png","/drawable/pyro.png","/drawable/pyro_abyss_mage.png","/drawable/pyro_hilichurl_shooter.png","/drawable/pyro_hypostasis.png","/drawable/pyro_regisvine.png","/drawable/pyro_slime.png","/drawable/pyro_specter.png","/drawable/pyro_whopperflower.png","/drawable/qingxin.png","/drawable/qiqi_card.png","/drawable/qiqi_flag.png","/drawable/qiqi_full.png","/drawable/qiqi_gacha_splash.png","/drawable/qiqi_ico.png","/drawable/quartz.png","/drawable/radish.png","/drawable/raiden_shogun_card.png","/drawable/raiden_shogun_flag.png","/drawable/raiden_shogun_full.png","/drawable/raiden_shogun_gacha_splash.png","/drawable/raiden_shogun_ico.png","/drawable/rainslasher.png","/drawable/raven_bow.png","/drawable/raw_meat.png","/drawable/razor_card.png","/drawable/razor_flag.png","/drawable/razor_full.png","/drawable/razor_gacha_splash.png","/drawable/razor_ico.png","/drawable/realm_currency.png","/drawable/recruits_insignia.png","/drawable/recurve_bow.png","/drawable/redhorn_stonethresher.png","/drawable/relic_from_guyun.png","/drawable/resolution_of_sojourner_1.png","/drawable/resolution_of_sojourner_2.png","/drawable/resolution_of_sojourner_3.png","/drawable/resolution_of_sojourner_4.png","/drawable/resolution_of_sojourner_5.png","/drawable/retracing_bolide_1.png","/drawable/retracing_bolide_2.png","/drawable/retracing_bolide_3.png","/drawable/retracing_bolide_4.png","/drawable/retracing_bolide_5.png","/drawable/riftborn_regalia.png","/drawable/rimebiter_bathysmal_vishap.png","/drawable/ring_of_boreas.png","/drawable/rockfond_rifthound.png","/drawable/rockfond_rifthound_whelp.png","/drawable/rock_shieldwall_mitachurl.png","/drawable/rock_shield_hilichurl_guard.png","/drawable/rosaria_card.png","/drawable/rosaria_flag.png","/drawable/rosaria_full.png","/drawable/rosaria_gacha_splash.png","/drawable/rosaria_ico.png","/drawable/rosaria_suit1_flag.png","/drawable/rosaria_suit1_full.png","/drawable/rosaria_suit1_ico.png","/drawable/royal_bow.png","/drawable/royal_greatsword.png","/drawable/royal_grimoire.png","/drawable/royal_longsword.png","/drawable/royal_spear.png","/drawable/ruin_cruiser.png","/drawable/ruin_defender.png","/drawable/ruin_destroyer.png","/drawable/ruin_grader.png","/drawable/ruin_guard.png","/drawable/ruin_hunter.png","/drawable/ruin_scout.png","/drawable/runic_fang.png","/drawable/rust.png","/drawable/sacrificial_bow.png","/drawable/sacrificial_fragments.png","/drawable/sacrificial_greatsword.png","/drawable/sacrificial_sword.png","/drawable/sakura_bloom.png","/drawable/sanctifying_essence.png","/drawable/sanctifying_unction.png","/drawable/sangonomiya_cohort.png","/drawable/sangonomiya_kokomi_card.png","/drawable/sangonomiya_kokomi_flag.png","/drawable/sangonomiya_kokomi_full.png","/drawable/sangonomiya_kokomi_gacha_splash.png","/drawable/sangonomiya_kokomi_ico.png","/drawable/sango_pearl.png","/drawable/sayu_card.png","/drawable/sayu_flag.png","/drawable/sayu_full.png","/drawable/sayu_gacha_splash.png","/drawable/sayu_ico.png","/drawable/scattered_piece_of_decarabianss_dream.png","/drawable/scholar_1.png","/drawable/scholar_2.png","/drawable/scholar_3.png","/drawable/scholar_4.png","/drawable/scholar_5.png","/drawable/sealed_scroll.png","/drawable/seasoned_hunters_bow.png","/drawable/sea_ganoderma.png","/drawable/serene_requiem.png","/drawable/sergeants_insignia.png","/drawable/serpent_spine.png","/drawable/shackles_of_the_dandelion_gladiator.png","/drawable/shadowy_husk_defender.png","/drawable/shadowy_husk_line_breaker.png","/drawable/shadowy_husk_standard_bearer.png","/drawable/shadow_of_the_warrior.png","/drawable/shard_of_foul_legacy.png","/drawable/sharpshooters_oath.png","/drawable/sharp_arrowhead.png","/drawable/shenhe_card.png","/drawable/shenhe_flag.png","/drawable/shenhe_full.png","/drawable/shenhe_gacha_splash.png","/drawable/shenhe_ico.png","/drawable/shimenawas_reminiscence_1.png","/drawable/shimenawas_reminiscence_2.png","/drawable/shimenawas_reminiscence_3.png","/drawable/shimenawas_reminiscence_4.png","/drawable/shimenawas_reminiscence_5.png","/drawable/shimmering_nectar.png","/drawable/shivada_jade_chunk.png","/drawable/shivada_jade_fragment.png","/drawable/shivada_jade_gemstone.png","/drawable/shivada_jade_sliver.png","/drawable/shogunate_infantry.png","/drawable/shogunate_infantry_captain.png","/drawable/silk_flower.png","/drawable/silver_raven_insignia.png","/drawable/silver_sword.png","/drawable/skyrider_greatsword.png","/drawable/skyrider_sword.png","/drawable/skyward_atlas.png","/drawable/skyward_blade.png","/drawable/skyward_harp.png","/drawable/skyward_pride.png","/drawable/skyward_spine.png","/drawable/slime_concentrate.png","/drawable/slime_condensate.png","/drawable/slime_secretions.png","/drawable/slingshot.png","/drawable/small_lamp_grass.png","/drawable/smoldering_pearl.png","/drawable/snow_tombed_starsilver.png","/drawable/solar_pearl.png","/drawable/song_of_broken_pines.png","/drawable/spectral_heart.png","/drawable/spectral_husk.png","/drawable/spectral_nucleus.png","/drawable/spirit_locket_of_boreas.png","/drawable/staff_of_homa.png","/drawable/stained_mask.png","/drawable/starconch.png","/drawable/starsilver.png","/drawable/star_empty.png","/drawable/star_full.png","/drawable/stonehide_lawachurl.png","/drawable/stormterror.png","/drawable/storm_beads.png","/drawable/sturdy_bone_shard.png","/drawable/sucrose_card.png","/drawable/sucrose_flag.png","/drawable/sucrose_full.png","/drawable/sucrose_gacha_splash.png","/drawable/sucrose_ico.png","/drawable/summit_shaper.png","/drawable/sweet_flower.png","/drawable/sword_of_descension.png","/drawable/tail_of_boreas.png","/drawable/tartaglia_card.png","/drawable/tartaglia_flag.png","/drawable/tartaglia_full.png","/drawable/tartaglia_gacha_splash.png","/drawable/tartaglia_ico.png","/drawable/teachings_of_elegance.png","/drawable/teachings_of_light.png","/drawable/teachings_of_transience.png","/drawable/teaching_of_ballad.png","/drawable/teaching_of_diligence.png","/drawable/teaching_of_freedom.png","/drawable/teaching_of_gold.png","/drawable/teaching_of_prosperity.png","/drawable/teaching_of_resistance.png","/drawable/tears_of_the_calamitous_god.png","/drawable/tenacity_of_the_millelith_1.png","/drawable/tenacity_of_the_millelith_2.png","/drawable/tenacity_of_the_millelith_3.png","/drawable/tenacity_of_the_millelith_4.png","/drawable/tenacity_of_the_millelith_5.png","/drawable/the_alley_flash.png","/drawable/the_bell.png","/drawable/the_black_sword.png","/drawable/the_catch.png","/drawable/the_exile_1.png","/drawable/the_exile_2.png","/drawable/the_exile_3.png","/drawable/the_exile_4.png","/drawable/the_exile_5.png","/drawable/the_flute.png","/drawable/the_great_snowboar_king.png","/drawable/the_meaning_of_aeons.png","/drawable/the_stringless.png","/drawable/the_unforged.png","/drawable/the_viridescent_hunt.png","/drawable/the_widsith.png","/drawable/thoma_card.png","/drawable/thoma_flag.png","/drawable/thoma_full.png","/drawable/thoma_gacha_splash.png","/drawable/thoma_ico.png","/drawable/thrilling_tales_of_dragon_slayers.png","/drawable/thundercraven_rifthound.png","/drawable/thundercraven_rifthound_whelp.png","/drawable/thunderhelm_lawachurl.png","/drawable/thundering_fury_1.png","/drawable/thundering_fury_2.png","/drawable/thundering_fury_3.png","/drawable/thundering_fury_4.png","/drawable/thundering_fury_5.png","/drawable/thundering_pulse.png","/drawable/thundersoother_1.png","/drawable/thundersoother_2.png","/drawable/thundersoother_3.png","/drawable/thundersoother_4.png","/drawable/thundersoother_5.png","/drawable/thunder_manifestation.png","/drawable/tile_of_decarabians_tower.png","/drawable/tiny_miracle_1.png","/drawable/tiny_miracle_2.png","/drawable/tiny_miracle_3.png","/drawable/tiny_miracle_4.png","/drawable/tiny_miracle_5.png","/drawable/travelers_handy_sword.png","/drawable/traveler_card.png","/drawable/traveler_ico.png","/drawable/traveling_doctor_1.png","/drawable/traveling_doctor_2.png","/drawable/traveling_doctor_3.png","/drawable/traveling_doctor_4.png","/drawable/traveling_doctor_5.png","/drawable/treasure_hoarders_boss.png","/drawable/treasure_hoarders_carmen.png","/drawable/treasure_hoarders_crusher.png","/drawable/treasure_hoarders_cryo_potioneer.png","/drawable/treasure_hoarders_electro_potioneer.png","/drawable/treasure_hoarders_gravedigger.png","/drawable/treasure_hoarders_handyman.png","/drawable/treasure_hoarders_hydro_potioneer.png","/drawable/treasure_hoarders_liuliu.png","/drawable/treasure_hoarders_marksman.png","/drawable/treasure_hoarders_pugilist.png","/drawable/treasure_hoarders_pyro_potioneer.png","/drawable/treasure_hoarders_raptor.png","/drawable/treasure_hoarders_scout.png","/drawable/treasure_hoarders_seaman.png","/drawable/treasure_hoarder_insignia.png","/drawable/tusk_of_monoceros_caeli.png","/drawable/twin_nephrite.png","/drawable/unknown_card.png","/drawable/unusual_hilichurl.png","/drawable/uprising_satire.png","/drawable/vajrada_amethyst_chunk.png","/drawable/vajrada_amethyst_fragment.png","/drawable/vajrada_amethyst_gemstone.png","/drawable/vajrada_amethyst_sliver.png","/drawable/valberry.png","/drawable/varunada_lazurite_chunk.png","/drawable/varunada_lazurite_fragment.png","/drawable/varunada_lazurite_gemstone.png","/drawable/varunada_lazurite_sliver.png","/drawable/vayuda_turquoise_chunk.png","/drawable/vayuda_turquoise_fragment.png","/drawable/vayuda_turquoise_gemstone.png","/drawable/vayuda_turquoise_sliver.png","/drawable/venti_card.png","/drawable/venti_flag.png","/drawable/venti_full.png","/drawable/venti_gacha_splash.png","/drawable/venti_ico.png","/drawable/vermillion_hereafter_1.png","/drawable/vermillion_hereafter_2.png","/drawable/vermillion_hereafter_3.png","/drawable/vermillion_hereafter_4.png","/drawable/vermillion_hereafter_5.png","/drawable/violetgrass.png","/drawable/viridescent_venerer_1.png","/drawable/viridescent_venerer_2.png","/drawable/viridescent_venerer_3.png","/drawable/viridescent_venerer_4.png","/drawable/viridescent_venerer_5.png","/drawable/vitalized_dragontooth.png","/drawable/vortex_vanquisher.png","/drawable/wanderers_advice.png","/drawable/wanderers_troupe_1.png","/drawable/wanderers_troupe_2.png","/drawable/wanderers_troupe_3.png","/drawable/wanderers_troupe_4.png","/drawable/wanderers_troupe_5.png","/drawable/waster_greatsword.png","/drawable/wavebreakers_fin.png","/drawable/weapons2.png","/drawable/weapons_ico.png","/drawable/weathered_arrowhead.png","/drawable/whiteblind.png","/drawable/white_iron_chunk.png","/drawable/white_iron_greatsword.png","/drawable/white_tassel.png","/drawable/whopperflower_nectar.png","/drawable/windblume_ode.png","/drawable/windwheel_aster.png","/drawable/wine_and_song.png","/drawable/wolfhook.png","/drawable/wolfs_gravestone.png","/drawable/wooden_shieldwall_mitachurl.png","/drawable/wooden_shield_hilichurl_guard.png","/drawable/xectorda_name_color0.png","/drawable/xiangling_card.png","/drawable/xiangling_flag.png","/drawable/xiangling_full.png","/drawable/xiangling_gacha_splash.png","/drawable/xiangling_ico.png","/drawable/xiao_card.png","/drawable/xiao_flag.png","/drawable/xiao_full.png","/drawable/xiao_gacha_splash.png","/drawable/xiao_ico.png","/drawable/xingqiu_card.png","/drawable/xingqiu_flag.png","/drawable/xingqiu_full.png","/drawable/xingqiu_gacha_splash.png","/drawable/xingqiu_ico.png","/drawable/xinyan_card.png","/drawable/xinyan_flag.png","/drawable/xinyan_full.png","/drawable/xinyan_gacha_splash.png","/drawable/xinyan_ico.png","/drawable/yae_miko_card.png","/drawable/yae_miko_flag.png","/drawable/yae_miko_full.png","/drawable/yae_miko_gacha_splash.png","/drawable/yae_miko_ico.png","/drawable/yanfei_card.png","/drawable/yanfei_flag.png","/drawable/yanfei_full.png","/drawable/yanfei_gacha_splash.png","/drawable/yanfei_ico.png","/drawable/yelan_card.png","/drawable/yelan_full.png","/drawable/yelan_gacha_splash.png","/drawable/yelan_ico.png","/drawable/yoimiya_card.png","/drawable/yoimiya_flag.png","/drawable/yoimiya_full.png","/drawable/yoimiya_gacha_splash.png","/drawable/yoimiya_ico.png","/drawable/yoriki_samurai.png","/drawable/yun_jin_card.png","/drawable/yun_jin_flag.png","/drawable/yun_jin_full.png","/drawable/yun_jin_gacha_splash.png","/drawable/yun_jin_ico.png","/drawable/zhongli.png","/drawable/zhongli_card.png","/drawable/zhongli_flag.png","/drawable/zhongli_full.png","/drawable/zhongli_gacha_splash.png","/drawable/zhongli_ico.png"};
        for (int x = 0 ; x < arr.length ; x++ ){
            run(context.getFilesDir()+arr[x],context);
        }
    }
    public static void run(String path, Context context){
        BitmapFactory.Options myOptions = new BitmapFactory.Options();
        myOptions.inDither = true;
        myOptions.inScaled = false;
        myOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//important
        //myOptions.inDither = false;
        myOptions.inPurgeable = true;
        Bitmap tempImage = BitmapFactory.decodeFile(path, myOptions);//important

        //this is important part new scale method created by someone else
        tempImage = CreateScaledBitmap(tempImage,tempImage.getWidth(),tempImage.getHeight(),false);

        try (FileOutputStream out = new FileOutputStream(path)) {
            tempImage.compress(Bitmap.CompressFormat.PNG, 80, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Bitmap CreateScaledBitmap(Bitmap src, int dstWidth, int dstHeight, boolean filter)
    {
        Matrix m = new Matrix();
        m.setScale(dstWidth  / (float)src.getWidth(), dstHeight / (float)src.getHeight());
        Bitmap result = Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);

        Paint paint = new Paint();
        paint.setFilterBitmap(filter);
        canvas.drawBitmap(src, m, paint);

        return result;

    }
}
