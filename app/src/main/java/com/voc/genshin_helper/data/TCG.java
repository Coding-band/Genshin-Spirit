package com.voc.genshin_helper.data;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import java.io.Serializable;

@SuppressWarnings("serial")
public class TCG implements Serializable {
    public String name; //名稱
    public String type; //卡牌類型
    public String subType; //卡牌類型--EXT
    public int recharge; //充能
    public int diceCost; //花費骰子
    public int HP; //HP
    public String diceType; //花費骰子
    public int rare; //稀有度
    public int isComing; //是否即將推出

    public TCG(){}

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


