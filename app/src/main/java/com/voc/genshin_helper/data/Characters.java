package com.voc.genshin_helper.data;

import java.io.Serializable;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

@SuppressWarnings("serial")
public class Characters implements Serializable {
    public String name; //名稱
    public String element; //元素
    public int rare; //稀有度
    public String weapon; //武器類型
    public int isComing; //是否即將推出
    public String nation; //地區
    public String sex; //性別
    public String mainStat; //性別

    public Characters() {

    }

    public Characters(String name, String element, int rare, String weapon, int isComing, String nation, String sex, String role, String mainStat) {
        this.name = name;
        this.element = element;
        this.rare = rare;
        this.weapon = weapon;
        this.isComing = isComing;
        this.nation = nation;
        this.sex = sex;
        this.mainStat = mainStat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public int getRare() {
        return rare;
    }

    public void setRare(int rare) {
        this.rare = rare;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getIsComing() {
        return isComing;
    }

    public void setIsComing(int isComing) {
        this.isComing = isComing;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMainStat() {
        return mainStat;
    }

    public void setMainStat(String mainStat) {
        this.mainStat = mainStat;
    }
}

