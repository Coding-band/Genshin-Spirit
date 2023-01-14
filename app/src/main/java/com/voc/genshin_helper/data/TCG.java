package com.voc.genshin_helper.data;

import java.io.Serializable;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

@SuppressWarnings("serial")
public class TCG implements Serializable {
    public String name; //名稱
    public String localeName; //名稱
    public String fileName; //檔案名稱
    public String type; //卡牌類型
    public String subType; //卡牌類型--EXT
    public int recharge; //充能
    public int diceCost; //花費骰子
    public int HP; //HP
    public String diceType; //花費骰子
    public int rare; //稀有度
    public int isComing; //是否即將推出

    /*
    Category
     */
    // TYPE
    public static final String CHAR = "CHAR";
    public static final String EQUIP = "EQUIP";
    public static final String SUPPORT = "SUPPORT";
    public static final String EVENT = "EVENT";
    public static final String BACKSIDE = "BACKSIDE";

    // SubType
    public static final String Mondstadt = "蒙德";
    public static final String Liyue = "璃月";
    public static final String Inazuma = "稻妻";
    public static final String Sumeru = "須彌";
    public static final String Fatui = "愚人眾";
    public static final String Monster = "魔物";
    public static final String Talent = "天賦";
    public static final String Weapon = "武器";
    public static final String Artifact = "聖遺物";
    public static final String Environment = "場地";
    public static final String Partner = "夥伴";
    public static final String Tool = "道具";
    public static final String ElementalResonance = "元素共鳴";
    public static final String Special = "特殊牌";
    public static final String Food = "料理";
    public static final String CardBack = "卡背";

    //DiceType
    public static final String Anemo = "Anemo";
    public static final String Cryo = "Cryo";
    public static final String Dendro = "Dendro";
    public static final String Electro = "Electro";
    public static final String Geo = "Geo";
    public static final String Hydro = "Hydro";
    public static final String Pyro = "Pyro";
    public static final String SPEC = "SPEC";
    public static final String RAND = "RAND";


    public TCG(){}

    public String getLocaleName() {
        return localeName;
    }

    public void setLocaleName(String localeName) {
        this.localeName = localeName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public int getRecharge() {
        return recharge;
    }

    public void setRecharge(int recharge) {
        this.recharge = recharge;
    }

    public int getDiceCost() {
        return diceCost;
    }

    public void setDiceCost(int diceCost) {
        this.diceCost = diceCost;
    }

    public String getDiceType() {
        return diceType;
    }

    public void setDiceType(String diceType) {
        this.diceType = diceType;
    }

    public int getRare() {
        return rare;
    }

    public void setRare(int rare) {
        this.rare = rare;
    }

    public int getIsComing() {
        return isComing;
    }

    public void setIsComing(int isComing) {
        this.isComing = isComing;
    }
}


