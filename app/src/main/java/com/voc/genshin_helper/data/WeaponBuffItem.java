package com.voc.genshin_helper.data;/*
 * Package com.voc.genshin_spirit_cn.data.Artifacts was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class WeaponBuffItem {
    public String base_name;
    public int refine_lvl;
    public String buff1_type = "N/A";
    public String buff2_type = "N/A";
    public String buff3_type = "N/A";
    public String buff4_type = "N/A";
    public String buff5_type = "N/A";
    public double buff1_value = 0.0;
    public double buff2_value = 0.0;
    public double buff3_value = 0.0;
    public double buff4_value = 0.0;
    public double buff5_value = 0.0;

    public WeaponBuffItem() {
    }

    public WeaponBuffItem(String base_name, int refine_lvl, String buff1_type, String buff2_type, String buff3_type, String buff4_type, String buff5_type, double buff1_value, double buff2_value, double buff3_value, double buff4_value, double buff5_value) {
        this.base_name = base_name;
        this.refine_lvl = refine_lvl;
        this.buff1_type = buff1_type;
        this.buff2_type = buff2_type;
        this.buff3_type = buff3_type;
        this.buff4_type = buff4_type;
        this.buff5_type = buff5_type;
        this.buff1_value = buff1_value;
        this.buff2_value = buff2_value;
        this.buff3_value = buff3_value;
        this.buff4_value = buff4_value;
        this.buff5_value = buff5_value;
    }

    public String getBaseName() {
        return this.base_name;
    }

    public void setBaseName(String base_name) {
        this.base_name = base_name;
    }

    public int getRefineLvl() {
        return this.refine_lvl;
    }

    public void setRefineLvl(int refine_lvl) {
        this.refine_lvl = refine_lvl;
    }

    public String getBuff1Type() {
        return this.buff1_type;
    }

    public void setBuff1Type(String buff1_type) {
        this.buff1_type = buff1_type;
    }


    public double getBuff1Value() {
        return this.buff1_value;
    }

    public void setBuff1Value(double buff1_value){
        this.buff1_value = buff1_value;
    }

    public String getBuff2Type() {
        return this.buff2_type;
    }

    public void setBuff2Type(String buff2_type) {
        this.buff2_type = buff2_type;
    }


    public double getBuff2Value() {
        return this.buff2_value;
    }

    public void setBuff2Value(double buff2_value){
        this.buff2_value = buff2_value;
    }

    public String getBuff3Type() {
        return this.buff3_type;
    }

    public void setBuff3Type(String buff3_type) {
        this.buff3_type = buff3_type;
    }


    public double getBuff3Value() {
        return this.buff3_value;
    }

    public void setBuff3Value(double buff3_value){
        this.buff3_value = buff3_value;
    }

    public String getBuff4Type() {
        return this.buff4_type;
    }

    public void setBuff4Type(String buff4_type) {
        this.buff4_type = buff4_type;
    }


    public double getBuff4Value() {
        return this.buff4_value;
    }

    public void setBuff4Value(double buff4_value){
        this.buff4_value = buff4_value;
    }

    public String getBuff5Type() {
        return this.buff5_type;
    }

    public void setBuff5Type(String buff5_type) {
        this.buff5_type = buff5_type;
    }


    public double getBuff5Value() {
        return this.buff5_value;
    }

    public void setBuff5Value(double buff5_value){
        this.buff5_value = buff5_value;
    }
}