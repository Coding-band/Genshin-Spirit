package com.voc.genshin_helper.buff;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import java.util.Arrays;

public class Weapon {

    public static final int SWORD = 0;
    public static final int CLAYMORE = 1;
    public static final int POLEARM = 2;
    public static final int BOW = 3;
    public static final int CATALYST = 4;

    String weaponName = "Staff of Homa";
    int weaponLvl = 90; // need to source-1
    int weaponRare = 5;
    int weaponASCLvl = 5; // 武器突破等級
    int weaponAffixLvl = 0; // 武器精煉等級
    int weaponType = POLEARM;

    int weaponId = 13501;

    double[] weaponStatValue = new double[]{0,0}; // [MainStatus, SubStatus]
    String[] weaponStatStr = new String[]{"N/A","N/A"}; // [MainStatus, SubStatus]

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public int getWeaponLvl() {
        return weaponLvl;
    }

    public void setWeaponLvl(int weaponLvl) {
        this.weaponLvl = weaponLvl;
    }

    public int getWeaponRare() {
        return weaponRare;
    }

    public void setWeaponRare(int weaponRare) {
        this.weaponRare = weaponRare;
    }

    public int getWeaponASCLvl() {
        return weaponASCLvl;
    }

    public void setWeaponASCLvl(int weaponASCLvl) {
        this.weaponASCLvl = weaponASCLvl;
    }

    public int getWeaponAffixLvl() {
        return weaponAffixLvl;
    }

    public void setWeaponAffixLvl(int weaponAffixLvl) {
        this.weaponAffixLvl = weaponAffixLvl;
    }

    public int getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(int weaponType) {
        this.weaponType = weaponType;
    }

    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }

    public double[] getWeaponStatValue() {
        return weaponStatValue;
    }

    public void setWeaponStatValue(double[] weaponStatValue) {
        this.weaponStatValue = weaponStatValue;
    }

    public String[] getWeaponStatStr() {
        return weaponStatStr;
    }

    public void setWeaponStatStr(String[] weaponStatStr) {
        this.weaponStatStr = weaponStatStr;
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

        return "Weapon@"+ Integer.toHexString(hashCode())+"\n"+
                padding + "weaponName" + equal + getWeaponName()+"\n"+
                padding + "weaponId" + equal + getWeaponId()+"\n"+
                padding + "weaponRare" + equal + getWeaponRare()+"\n"+
                padding + "weaponLvl" + equal + getWeaponLvl()+"\n"+
                padding + "weaponASCLvl" + equal + getWeaponASCLvl()+"\n"+
                padding + "weaponAffixLvl" + equal + getWeaponAffixLvl()+"\n"+
                padding + "weaponType" + equal + getTypeStrByType(getWeaponType())+"\n"+
                padding + "weaponStatStr" + equal + Arrays.toString(getWeaponStatStr())+"\n"+
                padding + "weaponStatValue" + equal + Arrays.toString(getWeaponStatValue())

                ;
    }

    public String getTypeStrByType(int TYPE){
      switch (TYPE){
          case SWORD: return "SWORD"+"_"+String.valueOf(TYPE);
          case CLAYMORE: return "CLAYMORE"+"_"+String.valueOf(TYPE);
          case POLEARM: return "POLEARM"+"_"+String.valueOf(TYPE);
          case BOW: return "BOW"+"_"+String.valueOf(TYPE);
          case CATALYST: return "CATALYST"+"_"+String.valueOf(TYPE);
          default: return "UNDEFINED"+"_"+String.valueOf(TYPE);
      }
    };
}
