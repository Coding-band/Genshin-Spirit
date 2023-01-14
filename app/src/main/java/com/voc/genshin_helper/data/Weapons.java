package com.voc.genshin_helper.data;/*
 * Package com.voc.genshin_helper.data.Weapons was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class Weapons {
    public int isComing;
    public String name;
    public int rare;
    public String stat_1;
    public String weapon;

    public Weapons() {
    }

    public Weapons(String str, int i, String str2, int i2, String str3) {
        this.name = str;
        this.rare = i;
        this.weapon = str2;
        this.isComing = i2;
        this.stat_1 = str3;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getRare() {
        return this.rare;
    }

    public void setRare(int i) {
        this.rare = i;
    }

    public String getWeapon() {
        return this.weapon;
    }

    public void setWeapon(String str) {
        this.weapon = str;
    }

    public int getIsComing() {
        return this.isComing;
    }

    public void setIsComing(int i) {
        this.isComing = i;
    }

    public String getStat_1() {
        return this.stat_1;
    }

    public void setStat_1(String str) {
        this.stat_1 = str;
    }
}