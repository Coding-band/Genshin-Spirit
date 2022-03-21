package com.voc.genshin_helper.data;

import java.io.Serializable;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

@SuppressWarnings("serial")
public class Materials implements Serializable {
    public String name = "N/A"; //本地化名稱
    public String inside_name = "N/A"; //非本地化名稱
    public int type ; //父類別名稱
    public int count; //數量
    public boolean isCal; //會被計算?

    public Materials() {

    }

    public Materials(String name, String inside_name, int type, int count,boolean isCal) {
        this.name = name;
        this.inside_name = inside_name;
        this.type = type;
        this.count = count;
        this.isCal = isCal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInside_name() {
        return inside_name;
    }

    public void setInside_name(String inside_name) {
        this.inside_name = inside_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean getIsCal() {
        return isCal;
    }

    public void setIsCal(boolean isCal) {
        this.isCal = isCal;
    }




}

