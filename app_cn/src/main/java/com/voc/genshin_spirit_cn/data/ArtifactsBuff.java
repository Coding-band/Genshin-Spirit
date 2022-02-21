package com.voc.genshin_spirit_cn.data;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import android.content.Context;

import com.voc.genshin_spirit_cn.R;

public class ArtifactsBuff {

    Context context;

    public void setup(Context context){
        this.context = context;
    }

    //baseHP + 1000
    public double 冒險家P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.adventurer)) || artifactList[2].equals(context.getString(R.string.adventurer))){ return 1000;} else return 0;}

    //Healing + 20%
    public double 遊醫P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.traveling_doctor)) || artifactList[2].equals(context.getString(R.string.traveling_doctor))){ return 0.2;} else return 0;}

    //ATK + 18%
    public double 行者之心P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.resolution_of_sojourner)) || artifactList[2].equals(context.getString(R.string.resolution_of_sojourner))){ return 0.18;} else return 0;}

    //CritRate + 15%
    public double 行者之心P4(String[] artifactList){ if(artifactList[1].equals(context.getString(R.string.resolution_of_sojourner))){ return 0.15;} else return 0;}

    //ATK + 18%
    public double 勇士之心P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.brave_heart)) || artifactList[2].equals(context.getString(R.string.brave_heart))){ return 0.18;} else return 0;}

    //ATK + 30%
    public double 勇士之心P4(String[] artifactList){ if(artifactList[1].equals(context.getString(R.string.brave_heart))){ return 0.3;} else return 0;}

    //ATK + 15%
    public double 武人P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.martial_artist)) || artifactList[2].equals(context.getString(R.string.martial_artist))){ return 0.15;} else return 0;}

    //ATK + 25%
    public double 武人P4(String[] artifactList){ if(artifactList[1].equals(context.getString(R.string.martial_artist))){ return 0.25;} else return 0;}

    //CritRate + 12%
    public double 戰狂P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.berserker)) || artifactList[2].equals(context.getString(R.string.berserker))){ return 0.12;} else return 0;}

    //CritRate + 24%
    public double 戰狂P4(String[] artifactList){ if(artifactList[1].equals(context.getString(R.string.berserker))){ return 0.24;} else return 0;}

    //PhyDMG + 25%
    public double 染血的騎士道P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.bloodstained_chivalry)) || artifactList[2].equals(context.getString(R.string.bloodstained_chivalry))){ return 0.25;} else return 0;}

    //ATK + 50%
    public double 染血的騎士道P4(String[] artifactList){ if(artifactList[1].equals(context.getString(R.string.bloodstained_chivalry))){ return 0.5;} else return 0;}

    //ATK + 18%
    public double 角鬥士的終幕禮P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.gladiators_finale)) || artifactList[2].equals(context.getString(R.string.gladiators_finale))){ return 0.18;} else return 0;}

    //ATK + 35%
    public double 角鬥士的終幕禮P4(String[] artifactList){ if(artifactList[1].equals(context.getString(R.string.gladiators_finale))){if(artifactList[3].equals("Sword")||artifactList[3].equals("Claymore")||artifactList[3].equals("Polearm")) {return 0.35;}else return 0;} else return 0;}

    //EleMas + 80
    public double 流浪大地的樂團P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.wanderers_troupe)) || artifactList[2].equals(context.getString(R.string.wanderers_troupe))){ return 80;} else return 0;}

    //ATK + 35%
    public double 流浪大地的樂團P4(String[] artifactList){ if(artifactList[1].equals(context.getString(R.string.wanderers_troupe))){if(artifactList[3].equals("Catalyst")||artifactList[3].equals("Bow")) {return 0.35;}else return 0;} else return 0;}

    //PhyDMG + 25%
    public double 蒼白之火P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.pale_flame)) || artifactList[2].equals(context.getString(R.string.pale_flame))){ return 0.25;} else return 0;}

    //ATK + 9%
    public double 蒼白之火P4(String[] artifactList){ if(artifactList[1].equals(context.getString(R.string.pale_flame))){ return 0.09;} else return 0;}

    //ATK + 20%
    public double 賭徒P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.gambler)) || artifactList[2].equals(context.getString(R.string.gambler))){ return 0.2;} else return 0;}

    //EleDMG_Electro + 15%
    public double 如雷的盛怒P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.thundering_fury)) || artifactList[2].equals(context.getString(R.string.thundering_fury))){ return 0.15;} else return 0;}

    //ResElectro + 40%
    public double 如雷的盛怒P4(String[] artifactList){ if(artifactList[1].equals(context.getString(R.string.thundering_fury))){ return 0.4;} else return 0;}

    //EleDMG_Anemo + 15%
    public double 翠綠之影P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.viridescent_venerer)) || artifactList[2].equals(context.getString(R.string.viridescent_venerer))){ return 0.15;} else return 0;}

    //ResAnemo + 40%
    public double 翠綠之影P4(String[] artifactList){ if(artifactList[1].equals(context.getString(R.string.viridescent_venerer))){ return 0.4;} else return 0;}

    //ResAnemo2 + 40%
    public double 翠綠之影P4_2(String[] artifactList){ if(artifactList[1].equals(context.getString(R.string.viridescent_venerer))){ return 0.4;} else return 0;}

    //EleDMG_Geo + 15%
    public double 悠古的磐岩P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.archaic_petra)) || artifactList[2].equals(context.getString(R.string.archaic_petra))){ return 0.15;} else return 0;}

    //ResGeo + 35%
    public double 悠古的磐岩P4(String[] artifactList){ if(artifactList[1].equals(context.getString(R.string.archaic_petra))){ return 0.35;} else return 0;}

    //EleDMG_Pyro + 15%
    public double 熾烈的炎之魔女P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.crimson_witch_of_flames)) || artifactList[2].equals(context.getString(R.string.crimson_witch_of_flames))){ return 0.15;} else return 0;}

    //ResPyro + 40%
    public double 熾烈的炎之魔女P4(String[] artifactList){ if(artifactList[1].equals(context.getString(R.string.crimson_witch_of_flames))){ return 0.4;} else return 0;}

    //ResPyro2 + 15%
    public double 熾烈的炎之魔女P4_2(String[] artifactList){ if(artifactList[1].equals(context.getString(R.string.crimson_witch_of_flames))){ return 0.15;} else return 0;}

    //ATK + 20%
    public double 昔日宗室之儀P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.noblesse_oblige)) || artifactList[2].equals(context.getString(R.string.noblesse_oblige))){ return 0.2;} else return 0;}

    //ATK + 20%
    public double 昔日宗室之儀P4(String[] artifactList){ if(artifactList[1].equals(context.getString(R.string.noblesse_oblige))){ return 0.2;} else return 0;}

    //EleDMG_Cryo + 15%
    public double 冰風迷途的勇士P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.blizzard_strayer)) || artifactList[2].equals(context.getString(R.string.blizzard_strayer))){ return 0.15;} else return 0;}

    //EleDMG_Hydro + 15%
    public double 沉淪之心P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.heart_of_depth)) || artifactList[2].equals(context.getString(R.string.heart_of_depth))){ return 0.15;} else return 0;}

    //ATK + 30%
    public double 沉淪之心P4(String[] artifactList){ if(artifactList[1].equals(context.getString(R.string.heart_of_depth))){ return 0.3;} else return 0;}

    //ATK + 18%
    public double 追憶之注連P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.shimenawas_reminiscence)) || artifactList[2].equals(context.getString(R.string.shimenawas_reminiscence))){ return 0.18;} else return 0;}

    //baseDEF + 100
    public double 幸運兒P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.lucky_dog)) || artifactList[2].equals(context.getString(R.string.lucky_dog))){ return 100;} else return 0;}

    //DEF + 30%
    public double 守護之心P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.defenders_will)) || artifactList[2].equals(context.getString(R.string.defenders_will))){ return 0.3;} else return 0;}

    //SHIELD + 35%
    public double 逆飛的流星P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.retracing_bolide)) || artifactList[2].equals(context.getString(R.string.retracing_bolide))){ return 0.35;} else return 0;}

    //ATK + 40%
    public double 逆飛的流星P4(String[] artifactList){ if(artifactList[1].equals(context.getString(R.string.retracing_bolide))){ return 0.4;} else return 0;}

    //EnRech + 20%
    public double 學士P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.scholar)) || artifactList[2].equals(context.getString(R.string.scholar))){ return 0.2;} else return 0;}

    //EleMas + 80
    public double 教官P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.instructor)) || artifactList[2].equals(context.getString(R.string.instructor))){ return 80;} else return 0;}

    //EleMas + 120
    public double 教官P4(String[] artifactList){ if(artifactList[1].equals(context.getString(R.string.instructor))){ return 120;} else return 0;}

    //EnRech + 20%
    public double 流放者P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.the_exile)) || artifactList[2].equals(context.getString(R.string.the_exile))){ return 0.2;} else return 0;}

    //Healing + 15%
    public double 被憐愛的少女P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.maiden_beloved)) || artifactList[2].equals(context.getString(R.string.maiden_beloved))){ return 0.15;} else return 0;}

    //HP + 20%
    public double 千岩牢固P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.tenacity_of_the_millelith)) || artifactList[2].equals(context.getString(R.string.tenacity_of_the_millelith))){ return 0.2;} else return 0;}

    //ATK + 20%
    public double 千岩牢固P4(String[] artifactList){ if(artifactList[1].equals(context.getString(R.string.tenacity_of_the_millelith))){ return 0.2;} else return 0;}

    //EnRech + 20%
    public double 絕緣之旗印P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.emblem_of_severed_fate)) || artifactList[2].equals(context.getString(R.string.emblem_of_severed_fate))){ return 0.2;} else return 0;}

    //DEF + 30%
    public double 華館夢醒形骸記P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.husk_of_opulent_dreams)) || artifactList[2].equals(context.getString(R.string.husk_of_opulent_dreams))){ return 0.3;} else return 0;}

    //Healing + 15%
    public double 海染硨磲P2(String[] artifactList){ if(artifactList[0].equals(context.getString(R.string.ocean_hued_clam)) || artifactList[2].equals(context.getString(R.string.ocean_hued_clam))){ return 0.15;} else return 0;}

}
