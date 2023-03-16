package com.voc.genshin_helper.buff.obj;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import java.io.Serializable;

public class BuffObject implements Serializable {
    /**
     * FIGHT_PROP_BASE_ATTACK       基礎攻擊力
     * FIGHT_PROP_HP	            生命值
     * FIGHT_PROP_ATTACK	        攻擊力
     * FIGHT_PROP_DEFENSE	        防禦力
     * FIGHT_PROP_HP_PERCENT	    生命值百分比
     * FIGHT_PROP_ATTACK_PERCENT	攻擊力百分比
     * FIGHT_PROP_DEFENSE_PERCENT	防禦力百分比
     * FIGHT_PROP_CRITICAL	        暴擊率
     * FIGHT_PROP_CRITICAL_HURT	    暴擊傷害
     * FIGHT_PROP_CHARGE_EFFICIENCY	元素充能效率
     * FIGHT_PROP_HEAL_ADD	        治療加成
     * FIGHT_PROP_ELEMENT_MASTERY	元素精通
     * FIGHT_PROP_PHYSICAL_ADD_HURT	物理傷害加成
     * FIGHT_PROP_FIRE_ADD_HURT	    火元素傷害加成
     * FIGHT_PROP_ELEC_ADD_HURT	    雷元素傷害加成
     * FIGHT_PROP_WATER_ADD_HURT	水元素傷害加成
     * FIGHT_PROP_WIND_ADD_HURT	    風元素傷害加成
     * FIGHT_PROP_ICE_ADD_HURT	    冰元素傷害加成
     * FIGHT_PROP_ROCK_ADD_HURT	    岩元素傷害加成
     * FIGHT_PROP_GRASS_ADD_HURT	草元素傷害加成
     */

    public static final String FIGHT_PROP_BASE_ATK = "FIGHT_PROP_BASE_ATTACK";
    public static final String FIGHT_PROP_HP = "FIGHT_PROP_HP";
    public static final String FIGHT_PROP_ATK = "FIGHT_PROP_ATTACK";
    public static final String FIGHT_PROP_DEF = "FIGHT_PROP_DEFENSE";
    public static final String FIGHT_PROP_HP_P = "FIGHT_PROP_HP_PERCENT";
    public static final String FIGHT_PROP_ATK_P = "FIGHT_PROP_ATTACK_PERCENT";
    public static final String FIGHT_PROP_DEF_P = "FIGHT_PROP_DEFENSE_PERCENT";
    public static final String FIGHT_PROP_CRIT_RATE = "FIGHT_PROP_CRITICAL";
    public static final String FIGHT_PROP_CRIT_DMG = "FIGHT_PROP_CRITICAL_HURT";
    public static final String FIGHT_PROP_EN_RECH = "FIGHT_PROP_CHARGE_EFFICIENCY";
    public static final String FIGHT_PROP_HEAL_P = "FIGHT_PROP_HEAL_ADD";
    public static final String FIGHT_PROP_ELE_MAS = "FIGHT_PROP_ELEMENT_MASTERY";
    public static final String FIGHT_PROP_PHY_DMG = "FIGHT_PROP_PHYSICAL_ADD_HURT";
    public static final String FIGHT_PROP_PYRO_DMG = "FIGHT_PROP_FIRE_ADD_HURT";
    public static final String FIGHT_PROP_ELECTRO_DMG = "FIGHT_PROP_ELEC_ADD_HURT";
    public static final String FIGHT_PROP_HYDRO_DMG = "FIGHT_PROP_WATER_ADD_HURT";
    public static final String FIGHT_PROP_ANEMO_DMG = "FIGHT_PROP_WIND_ADD_HURT";
    public static final String FIGHT_PROP_CRYO_DMG = "FIGHT_PROP_ICE_ADD_HURT";
    public static final String FIGHT_PROP_GEO_DMG = "FIGHT_PROP_ROCK_ADD_HURT";
    public static final String FIGHT_PROP_DENDRO_DMG = "FIGHT_PROP_GRASS_ADD_HURT";
    public static final String NONE = "N/A";

    Artifact artifactFlower = null;
    Artifact artifactPlume = null;
    Artifact artifactSand = null;
    Artifact artifactCirclet = null;
    Artifact artifactGoblet = null;
    Weapon weapon = null;
    Character character = null;

    // How about DataBase type ?

    public Artifact getArtifactFlower() {
        return artifactFlower;
    }

    public void setArtifactFlower(Artifact artifactFlower) {
        this.artifactFlower = artifactFlower;
    }

    public Artifact getArtifactPlume() {
        return artifactPlume;
    }

    public void setArtifactPlume(Artifact artifactPlume) {
        this.artifactPlume = artifactPlume;
    }

    public Artifact getArtifactSand() {
        return artifactSand;
    }

    public void setArtifactSand(Artifact artifactSand) {
        this.artifactSand = artifactSand;
    }

    public Artifact getArtifactCirclet() {
        return artifactCirclet;
    }

    public void setArtifactCirclet(Artifact artifactCirclet) {
        this.artifactCirclet = artifactCirclet;
    }

    public Artifact getArtifactGoblet() {
        return artifactGoblet;
    }

    public void setArtifactGoblet(Artifact artifactGoblet) {
        this.artifactGoblet = artifactGoblet;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
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

        return "BuffObject@"+ Integer.toHexString(hashCode())+"\n"+
                padding+"character"+ equal + getCharacter().toString(nextLvl)+"\n"+
                padding+"weapon"+ equal +getWeapon().toString(nextLvl)+"\n"+
                padding+"artifactFlower"+ equal +(getArtifactFlower() == null ? "NULL" : getArtifactFlower().toString(nextLvl))+"\n"+
                padding+"artifactPlume"+ equal +(getArtifactPlume() == null ? "NULL" : getArtifactPlume().toString(nextLvl))+"\n"+
                padding+"artifactSand"+ equal +(getArtifactSand() == null ? "NULL" : getArtifactSand().toString(nextLvl))+"\n"+
                padding+"artifactCirclet"+ equal +(getArtifactCirclet() == null ? "NULL" : getArtifactCirclet().toString(nextLvl))+"\n"+
                padding+"artifactGoblet"+ equal +(getArtifactGoblet() == null ? "NULL" : getArtifactGoblet().toString(nextLvl))+"\n"
                +"\n"

                ;

    }
}
