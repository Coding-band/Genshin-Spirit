package com.voc.genshin_helper.data;/*
 * Package com.voc.genshin_helper.data.Artifacts was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

public class Artifacts {
    public String base_name;
    public int isComing;
    public String name;
    public int rare;
    public String stat_1;
    public String stat_2;

    public Artifacts() {
    }

    public Artifacts(String str, int i, String str2, int i2, String str3, String str4, String str5) {
        this.name = str;
        this.rare = i;
        this.isComing = i2;
        this.stat_1 = str3;
        this.stat_2 = str4;
        this.base_name = str5;
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

    public String getStat_2() {
        return this.stat_2;
    }

    public void setStat_2(String str) {
        this.stat_2 = str;
    }

    public String getBaseName() {
        return this.base_name;
    }

    public void setBaseName(String str) {
        this.base_name = str;
    }
}