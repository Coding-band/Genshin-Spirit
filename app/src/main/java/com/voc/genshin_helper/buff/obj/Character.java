package com.voc.genshin_helper.buff.obj;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */


import java.io.Serializable;
import java.util.Arrays;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class Character implements Serializable {
    public static final String ANEMO = "CHAR_ANEMO";
    public static final String CRYO = "CHAR_CRYO";
    public static final String DENDRO = "CHAR_DENDRO";
    public static final String ELECTRO = "CHAR_ELECTRO";
    public static final String GEO = "CHAR_GEO";
    public static final String HYDRO = "CHAR_HYDRO";
    public static final String PYRO = "CHAR_PYRO";
    /**
     * <h3>Character Bio</h3>
     * Example : Lvl 90 C2 Hu Tao
     *
     * charName : Character's Name
     * charEXP : Character's Experience count
     * charASC : Character's Ascension
     * charLvl : Character's Level
     * charCon : Character's Constellation Level
     */

    String charName = "N/A";
    int charId = 0; //avatarId - 10000000
    int charEXP = 0;
    int charASC = 0;
    int charLvl = 1;
    int charCon = 0;
    int charRare = 5;
    int[] charTalentLvl = new int[]{10,10,10};

    String charElement = PYRO;

    Talents talents = null;

    /**
     * <h3>Character Value</h3>
     *
     * <b>P.S. [Solid] : Status in same constellation level's weapon that won't affect by other variable (E.g. Staff of Homa +20% HP)</b>
     *
     * charBaseHP       [0001] (Ascension) 基礎生命值 <br>
     * charHP           [0002] (Artifacts) 生命值 <br>
     * charHPP          [0003] (Artifacts & Weapons[Solid]) 生命值百分比 <br>
     * charBaseATK      [0004] (Ascension) 基礎攻擊力 <br>
     * charATK          [0005] (Artifacts) 攻擊力 <br>
     * charATKP         [0006] (Artifacts & Weapons[Solid]) 攻擊力百分比 <br>
     * charBaseDEF      [0007] (Ascension) 基礎防禦力 <br>
     * charDEF          [0008] (Artifacts) 防禦力 <br>
     * charDEFP         [0009] (Artifacts & Weapons[Solid]) 防禦力百分比 <br>
     * charCritRate     [0020] (ALL) 暴擊率 <br>
     * charCritDMG      [0022] (ALL) 暴擊傷害 <br>
     * charEnRech       [0023] (ALL) 元素充能效率 <br>
     * charHealP        [0026] (ALL) 治療加成 <br>
     * charGotHealP     [0027] (SPECIAL) 受治療加成 <br>
     * charEleMas       [0028] (ALL) 元素精通 <br>
     * charPhyRes       [0029] (ALL) 物理抗性 <br>
     * charPhyDMGP      [0030] (ALL) 物理傷害加成 <br>
     * charPyroDMGP     [0040] (ALL) 火元傷害加成 <br>
     * charElectroDMGP  [0041] (ALL) 雷元素傷害加成 <br>
     * charHydroDMGP    [0042] (ALL) 水元素傷害加成 <br>
     * charDendroDMGP   [0043] (ALL) 草元素傷害加成 <br>
     * charAnemoDMGP    [0044] (ALL) 風元素傷害加成 <br>
     * charGeoDMGP      [0045] (ALL) 岩元傷害加成 <br>
     * charCryoDMGP     [0046] (ALL) 冰元傷害加成 <br>
     * charPyroResP     [0050] (ALL) 火元素抗性 <br>
     * charElectroResP  [0051] (ALL) 雷元素抗性 <br>
     * charHydroResP    [0052] (ALL) 水元素抗性 <br>
     * charDendroResP   [0053] (ALL) 草元素抗性 <br>
     * charAnemoResP    [0054] (ALL) 風元素抗性 <br>
     * charGeoResP      [0055] (ALL) 岩元素抗性 <br>
     * charCryoResP     [0056] (ALL) 冰元素抗性 <br>
     * charMaxHP        [2000] (ALL) 生命值上限 <br>
     * charMaxATK       [2001] (ALL) 攻擊力 <br>
     * charMaxDEF       [2002] (ALL) 防禦力 <br>

     */

    public static final int CHAR_BASE_HP = 1;
    public static final int CHAR_HP = 2;
    public static final int CHAR_HP_P = 3;
    public static final int CHAR_BASE_ATK = 4;
    public static final int CHAR_ATK = 5;
    public static final int CHAR_ATK_P = 6;
    public static final int CHAR_BASE_DEF = 7;
    public static final int CHAR_DEF = 8;
    public static final int CHAR_DEF_P = 9;
    public static final int CHAR_CRIT_RATE = 20;
    public static final int CHAR_CRIT_DMG = 22;
    public static final int CHAR_EN_RECH = 23;
    public static final int CHAR_HEAL_P = 26;
    public static final int CHAR_GOT_HEAL_P = 27;
    public static final int CHAR_ELE_MAS = 28;
    public static final int CHAR_PHY_RES = 29;
    public static final int CHAR_PHY_DMG_P = 30;
    public static final int CHAR_PYRO_DMG_P = 40;
    public static final int CHAR_ELECTRO_DMG_P = 41;
    public static final int CHAR_HYDRO_DMG_P = 42;
    public static final int CHAR_DENDRO_DMG_P = 43;
    public static final int CHAR_ANEMO_DMG_P = 44;
    public static final int CHAR_GEO_DMG_P = 45;
    public static final int CHAR_CRYO_DMG_P = 46;
    public static final int CHAR_PYRO_RES_P = 50;
    public static final int CHAR_ELECTRO_RES_P = 51;
    public static final int CHAR_HYDRO_RES_P = 52;
    public static final int CHAR_DENDRO_RES_P = 53;
    public static final int CHAR_ANEMO_RES_P = 54;
    public static final int CHAR_GEO_RES_P = 55;
    public static final int CHAR_CRYO_RES_P = 56;
    public static final int CHAR_MAX_HP = 2000;
    public static final int CHAR_MAX_ATK = 2001;
    public static final int CHAR_MAX_DEF = 2002;

    public static final int[] CHAR_FIGHT_PROP_LIST = new int[]{1,2,3,4,5,6,7,8,9,20,22,23,26,27,28,29,30,40,41,42,43,44,45,46,50,51,52,53,54,55,56,2000,2001,2002};

    double charBaseHP = 0;
    double charHP = 0;
    double charHPP = 0;
    double charBaseATK = 0;
    double charATK = 0;
    double charATKP = 0;
    double charBaseDEF = 0;
    double charDEF = 0;
    double charDEFP = 0;
    double charSpdP = 0;
    double charCritRate = 0;
    double charCritDMG = 0;
    double charEnRech = 0;
    double charHealP = 0;
    double charGotHealP = 0;
    double charEleMas = 0 ;
    double charPhyRes = 0 ;
    double charPhyDMGP = 0 ;
    double charPyroDMGP = 0 ;
    double charElectroDMGP = 0 ;
    double charHydroDMGP = 0 ;
    double charDendroDMGP = 0 ;
    double charAnemoDMGP = 0 ;
    double charGeoDMGP = 0 ;
    double charCryoDMGP = 0 ;
    double charPyroResP = 0 ;
    double charElectroResP = 0 ;
    double charHydroResP = 0 ;
    double charDendroResP = 0 ;
    double charAnemoResP = 0 ;
    double charGeoResP = 0 ;
    double charCryoResP = 0 ;
    double charMaxHP = 0 ;
    double charMaxATK = 0 ;
    double charMaxDEF = 0 ;

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public int getCharId() {
        return charId;
    }

    public void setCharId(int charId) {
        this.charId = charId;
    }

    public int getCharEXP() {
        return charEXP;
    }

    public void setCharEXP(int charEXP) {
        this.charEXP = charEXP;
    }

    public int getCharASC() {
        return charASC;
    }

    public void setCharASC(int charASC) {
        this.charASC = charASC;
    }

    public int getCharLvl() {
        return charLvl;
    }

    public void setCharLvl(int charLvl) {
        this.charLvl = charLvl;
    }

    public int getCharCon() {
        return charCon;
    }

    public void setCharCon(int charCon) {
        this.charCon = charCon;
    }

    public int[] getCharTalentLvl() {
        return charTalentLvl;
    }

    public void setCharTalentLvl(int[] charTalentLvl) {
        this.charTalentLvl = charTalentLvl;
    }

    public double getCharBaseHP() {
        return charBaseHP;
    }

    public void setCharBaseHP(double charBaseHP) {
        this.charBaseHP = charBaseHP;
    }

    public double getCharHP() {
        return charHP;
    }

    public void setCharHP(double charHP) {
        this.charHP = charHP;
    }

    public double getCharHPP() {
        return charHPP;
    }

    public void setCharHPP(double charHPP) {
        this.charHPP = charHPP;
    }

    public double getCharBaseATK() {
        return charBaseATK;
    }

    public void setCharBaseATK(double charBaseATK) {
        this.charBaseATK = charBaseATK;
    }

    public double getCharATK() {
        return charATK;
    }

    public void setCharATK(double charATK) {
        this.charATK = charATK;
    }

    public double getCharATKP() {
        return charATKP;
    }

    public void setCharATKP(double charATKP) {
        this.charATKP = charATKP;
    }

    public double getCharBaseDEF() {
        return charBaseDEF;
    }

    public void setCharBaseDEF(double charBaseDEF) {
        this.charBaseDEF = charBaseDEF;
    }

    public double getCharDEF() {
        return charDEF;
    }

    public void setCharDEF(double charDEF) {
        this.charDEF = charDEF;
    }

    public double getCharDEFP() {
        return charDEFP;
    }

    public void setCharDEFP(double charDEFP) {
        this.charDEFP = charDEFP;
    }

    public double getCharSpdP() {
        return charSpdP;
    }

    public void setCharSpdP(double charSpdP) {
        this.charSpdP = charSpdP;
    }

    public double getCharCritRate() {
        return charCritRate;
    }

    public void setCharCritRate(double charCritRate) {
        this.charCritRate = charCritRate;
    }

    public double getCharCritDMG() {
        return charCritDMG;
    }

    public void setCharCritDMG(double charCritDMG) {
        this.charCritDMG = charCritDMG;
    }

    public double getCharEnRech() {
        return charEnRech;
    }

    public void setCharEnRech(double charEnRech) {
        this.charEnRech = charEnRech;
    }

    public double getCharHealP() {
        return charHealP;
    }

    public void setCharHealP(double charHealP) {
        this.charHealP = charHealP;
    }

    public double getCharGotHealP() {
        return charGotHealP;
    }

    public void setCharGotHealP(double charGotHealP) {
        this.charGotHealP = charGotHealP;
    }

    public double getCharEleMas() {
        return charEleMas;
    }

    public void setCharEleMas(double charEleMas) {
        this.charEleMas = charEleMas;
    }

    public double getCharPhyRes() {
        return charPhyRes;
    }

    public void setCharPhyRes(double charPhyRes) {
        this.charPhyRes = charPhyRes;
    }

    public double getCharPhyDMGP() {
        return charPhyDMGP;
    }

    public void setCharPhyDMGP(double charPhyDMGP) {
        this.charPhyDMGP = charPhyDMGP;
    }

    public double getCharPyroDMGP() {
        return charPyroDMGP;
    }

    public void setCharPyroDMGP(double charPyroDMGP) {
        this.charPyroDMGP = charPyroDMGP;
    }

    public double getCharElectroDMGP() {
        return charElectroDMGP;
    }

    public void setCharElectroDMGP(double charElectroDMGP) {
        this.charElectroDMGP = charElectroDMGP;
    }

    public double getCharHydroDMGP() {
        return charHydroDMGP;
    }

    public void setCharHydroDMGP(double charHydroDMGP) {
        this.charHydroDMGP = charHydroDMGP;
    }

    public double getCharDendroDMGP() {
        return charDendroDMGP;
    }

    public void setCharDendroDMGP(double charDendroDMGP) {
        this.charDendroDMGP = charDendroDMGP;
    }

    public double getCharAnemoDMGP() {
        return charAnemoDMGP;
    }

    public void setCharAnemoDMGP(double charAnemoDMGP) {
        this.charAnemoDMGP = charAnemoDMGP;
    }

    public double getCharGeoDMGP() {
        return charGeoDMGP;
    }

    public void setCharGeoDMGP(double charGeoDMGP) {
        this.charGeoDMGP = charGeoDMGP;
    }

    public double getCharCryoDMGP() {
        return charCryoDMGP;
    }

    public void setCharCryoDMGP(double charCryoDMGP) {
        this.charCryoDMGP = charCryoDMGP;
    }

    public double getCharPyroResP() {
        return charPyroResP;
    }

    public void setCharPyroResP(double charPyroResP) {
        this.charPyroResP = charPyroResP;
    }

    public double getCharElectroResP() {
        return charElectroResP;
    }

    public void setCharElectroResP(double charElectroResP) {
        this.charElectroResP = charElectroResP;
    }

    public double getCharHydroResP() {
        return charHydroResP;
    }

    public void setCharHydroResP(double charHydroResP) {
        this.charHydroResP = charHydroResP;
    }

    public double getCharDendroResP() {
        return charDendroResP;
    }

    public void setCharDendroResP(double charDendroResP) {
        this.charDendroResP = charDendroResP;
    }

    public double getCharAnemoResP() {
        return charAnemoResP;
    }

    public void setCharAnemoResP(double charAnemoResP) {
        this.charAnemoResP = charAnemoResP;
    }

    public double getCharGeoResP() {
        return charGeoResP;
    }

    public void setCharGeoResP(double charGeoResP) {
        this.charGeoResP = charGeoResP;
    }

    public double getCharCryoResP() {
        return charCryoResP;
    }

    public void setCharCryoResP(double charCryoResP) {
        this.charCryoResP = charCryoResP;
    }

    public double getCharMaxHP() {
        return charMaxHP;
    }

    public void setCharMaxHP(double charMaxHP) {
        this.charMaxHP = charMaxHP;
    }

    public double getCharMaxATK() {
        return charMaxATK;
    }

    public void setCharMaxATK(double charMaxATK) {
        this.charMaxATK = charMaxATK;
    }

    public double getCharMaxDEF() {
        return charMaxDEF;
    }

    public void setCharMaxDEF(double charMaxDEF) {
        this.charMaxDEF = charMaxDEF;
    }

    public int getCharRare() {
        return charRare;
    }

    public void setCharRare(int charRare) {
        this.charRare = charRare;
    }

    public String getCharElement() {
        return charElement;
    }

    public void setCharElement(String charElement) {
        this.charElement = charElement;
    }

    public Talents getTalents() {
        return talents;
    }

    public void setTalents(Talents talents) {
        this.talents = talents;
    }

    /**
     *
     * @param level : Root is 0, subItem is 1...n
     * @return object's information
     */
    public String toString(int level){
        int nextLvl = level+1;
        String padding = "┣ ";
        String equal = " = ";
        for (int x = 0 ; x < level ; x++){
            padding = "\t"+padding;
        }

        return "Character@"+ Integer.toHexString(hashCode())+"\n"+
                padding + "charName" + equal + getCharName()+"\n"+
                padding + "charId" + equal + getCharId()+"\n"+
                padding + "charEXP" + equal + getCharEXP()+"\n"+
                padding + "charASC" + equal + getCharASC()+"\n"+
                padding + "charLvl" + equal + getCharLvl()+"\n"+
                padding + "charCon" + equal + getCharCon()+"\n"+
                padding + "charRare" + equal + getCharRare()+"\n"+
                padding + "charElement" + equal + getCharElement()+"\n"+
                padding + "charTalentLvl" + equal + Arrays.toString(getCharTalentLvl())+"\n"+
                padding + "charBaseHP" + equal + getCharBaseHP()+"\n"+
                padding + "charHP" + equal + getCharHP()+"\n"+
                padding + "charHPP" + equal + getCharHPP()+"\n"+
                padding + "charBaseATK" + equal + getCharBaseATK()+"\n"+
                padding + "charATK" + equal + getCharATK()+"\n"+
                padding + "charATKP" + equal + getCharATKP()+"\n"+
                padding + "charBaseDEF" + equal + getCharBaseDEF()+"\n"+
                padding + "charDEF" + equal + getCharDEF()+"\n"+
                padding + "charDEFP" + equal + getCharDEFP()+"\n"+
                padding + "charSpdP" + equal + getCharSpdP()+"\n"+
                padding + "charCritRate" + equal + getCharCritRate()+"\n"+
                padding + "charCritDMG" + equal + getCharCritDMG()+"\n"+
                padding + "charEnRech" + equal + getCharEnRech()+"\n"+
                padding + "charHealP" + equal + getCharHealP()+"\n"+
                padding + "charGotHealP" + equal + getCharGotHealP()+"\n"+
                padding + "charEleMas" + equal + getCharEleMas()+"\n"+
                padding + "charPhyRes" + equal + getCharPhyRes()+"\n"+
                padding + "charPhyDMGP" + equal + getCharPhyDMGP()+"\n"+
                padding + "charPyroDMGP" + equal + getCharPyroDMGP()+"\n"+
                padding + "charElectroDMGP" + equal + getCharElectroDMGP()+"\n"+
                padding + "charHydroDMGP" + equal + getCharHydroDMGP()+"\n"+
                padding + "charDendroDMGP" + equal + getCharDendroDMGP()+"\n"+
                padding + "charAnemoDMGP" + equal + getCharAnemoDMGP()+"\n"+
                padding + "charGeoDMGP" + equal + getCharGeoDMGP()+"\n"+
                padding + "charCryoDMGP" + equal + getCharCryoDMGP()+"\n"+
                padding + "charPyroResP" + equal + getCharPyroResP()+"\n"+
                padding + "charElectroResP" + equal + getCharElectroResP()+"\n"+
                padding + "charHydroResP" + equal + getCharHydroResP()+"\n"+
                padding + "charDendroResP" + equal + getCharDendroResP()+"\n"+
                padding + "charAnemoResP" + equal + getCharAnemoResP()+"\n"+
                padding + "charGeoResP" + equal + getCharGeoResP()+"\n"+
                padding + "charCryoResP" + equal + getCharCryoResP()+"\n"+
                padding + "charMaxHP" + equal + getCharMaxHP()+"\n"+
                padding + "charMaxATK" + equal + getCharMaxATK()+"\n"+
                padding + "charMaxDEF" + equal + getCharMaxDEF()+"\n"+
                padding + "Talents"+ equal +(getTalents() == null ? "NULL" : getTalents().toString(nextLvl))+"\n"
                ;


    }
}
