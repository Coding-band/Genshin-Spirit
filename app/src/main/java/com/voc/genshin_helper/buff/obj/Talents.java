package com.voc.genshin_helper.buff.obj;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import java.io.Serializable;
import java.util.Arrays;

public class Talents implements Serializable {
    double Norm_1_Hit = 0; //普通攻擊_一段傷害
    double Norm_2_Hit = 0; //普通攻擊_二段傷害
    double Norm_3_Hit = 0; //普通攻擊_三段傷害
    double Norm_4_Hit = 0; //普通攻擊_四段傷害
    double Norm_5_Hit = 0; //普通攻擊_五段傷害
    double Norm_6_Hit = 0; //普通攻擊_六段傷害
    double Norm_Plunging = 0; //普通攻擊_下墜期間傷害
    double Norm_Low_Plunge = 0; //普通攻擊_低空墜地衝擊傷害
    double Norm_High_Plunge = 0; //普通攻擊_高空墜地衝擊傷害
    double Norm_Aimed_Shot = 0; //普通攻擊_滿蓄力瞄準射擊
    double Norm_Fully_Charged_Aimed_Shot = 0; //普通攻擊_高空墜地衝擊傷害
    double Norm_Charged_Attack = 0; //普通攻擊_重擊傷害
    double Norm_Charged_Attack_Cyclic = 0; //普通攻擊_重擊循環傷害
    double Norm_Charged_Final_Cyclic = 0; //普通攻擊_重擊終結傷害

    double[][] Element_Value = new double[8][15];
    String[] Element_Str = new String[8];

    double[][] Final_Value = new double[8][15];
    String[] Final_Str = new String[8];

    String[] Special_Talent_Str = new String[4];
    double[] Special_Talent_Value = new double[4];


    public double getNorm_1_Hit() {
        return Norm_1_Hit;
    }

    public void setNorm_1_Hit(double norm_1_Hit) {
        Norm_1_Hit = norm_1_Hit;
    }

    public double getNorm_2_Hit() {
        return Norm_2_Hit;
    }

    public void setNorm_2_Hit(double norm_2_Hit) {
        Norm_2_Hit = norm_2_Hit;
    }

    public double getNorm_3_Hit() {
        return Norm_3_Hit;
    }

    public void setNorm_3_Hit(double norm_3_Hit) {
        Norm_3_Hit = norm_3_Hit;
    }

    public double getNorm_4_Hit() {
        return Norm_4_Hit;
    }

    public void setNorm_4_Hit(double norm_4_Hit) {
        Norm_4_Hit = norm_4_Hit;
    }

    public double getNorm_5_Hit() {
        return Norm_5_Hit;
    }

    public void setNorm_5_Hit(double norm_5_Hit) {
        Norm_5_Hit = norm_5_Hit;
    }

    public double getNorm_6_Hit() {
        return Norm_6_Hit;
    }

    public void setNorm_6_Hit(double norm_6_Hit) {
        Norm_6_Hit = norm_6_Hit;
    }

    public double getNorm_Plunging() {
        return Norm_Plunging;
    }

    public void setNorm_Plunging(double norm_Plunging) {
        Norm_Plunging = norm_Plunging;
    }

    public double getNorm_Low_Plunge() {
        return Norm_Low_Plunge;
    }

    public void setNorm_Low_Plunge(double norm_Low_Plunge) {
        Norm_Low_Plunge = norm_Low_Plunge;
    }

    public double getNorm_High_Plunge() {
        return Norm_High_Plunge;
    }

    public void setNorm_High_Plunge(double norm_High_Plunge) {
        Norm_High_Plunge = norm_High_Plunge;
    }

    public double getNorm_Aimed_Shot() {
        return Norm_Aimed_Shot;
    }

    public void setNorm_Aimed_Shot(double norm_Aimed_Shot) {
        Norm_Aimed_Shot = norm_Aimed_Shot;
    }

    public double getNorm_Fully_Charged_Aimed_Shot() {
        return Norm_Fully_Charged_Aimed_Shot;
    }

    public void setNorm_Fully_Charged_Aimed_Shot(double norm_Fully_Charged_Aimed_Shot) {
        Norm_Fully_Charged_Aimed_Shot = norm_Fully_Charged_Aimed_Shot;
    }

    public double getNorm_Charged_Attack_Cyclic() {
        return Norm_Charged_Attack_Cyclic;
    }

    public void setNorm_Charged_Attack_Cyclic(double norm_Charged_Attack_Cyclic) {
        Norm_Charged_Attack_Cyclic = norm_Charged_Attack_Cyclic;
    }

    public double getNorm_Charged_Final_Cyclic() {
        return Norm_Charged_Final_Cyclic;
    }

    public void setNorm_Charged_Final_Cyclic(double norm_Charged_Final_Cyclic) {
        Norm_Charged_Final_Cyclic = norm_Charged_Final_Cyclic;
    }

    public double[][] getElement_Value() {
        return Element_Value;
    }

    public void setElement_Value(double[][] element_Value) {
        Element_Value = element_Value;
    }

    public String[] getElement_Str() {
        return Element_Str;
    }

    public void setElement_Str(String[] element_Str) {
        Element_Str = element_Str;
    }

    public double[][] getFinal_Value() {
        return Final_Value;
    }

    public void setFinal_Value(double[][] final_Value) {
        Final_Value = final_Value;
    }

    public String[] getFinal_Str() {
        return Final_Str;
    }

    public void setFinal_Str(String[] final_Str) {
        Final_Str = final_Str;
    }

    public String[] getSpecial_Talent_Str() {
        return Special_Talent_Str;
    }

    public void setSpecial_Talent_Str(String[] special_Talent_Str) {
        Special_Talent_Str = special_Talent_Str;
    }

    public double[] getSpecial_Talent_Value() {
        return Special_Talent_Value;
    }

    public void setSpecial_Talent_Value(double[] special_Talent_Value) {
        Special_Talent_Value = special_Talent_Value;
    }

    public double getNorm_Charged_Attack() {
        return Norm_Charged_Attack;
    }

    public void setNorm_Charged_Attack(double norm_Charged_Attack) {
        Norm_Charged_Attack = norm_Charged_Attack;
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

        return "Talent@"+ Integer.toHexString(hashCode())+"\n"+
                padding + "Norm_1_Hit" + equal + getNorm_1_Hit()+"\n"+
                padding + "Norm_2_Hit" + equal + getNorm_2_Hit()+"\n"+
                padding + "Norm_3_Hit" + equal + getNorm_3_Hit()+"\n"+
                padding + "Norm_4_Hit" + equal + getNorm_4_Hit()+"\n"+
                padding + "Norm_5_Hit" + equal + getNorm_5_Hit()+"\n"+
                padding + "Norm_6_Hit" + equal + getNorm_6_Hit()+"\n"+
                padding + "Norm_Plunging" + equal + getNorm_Plunging()+"\n"+
                padding + "Norm_Low_Plunge" + equal + getNorm_Low_Plunge()+"\n"+
                padding + "Norm_High_Plunge" + equal + getNorm_High_Plunge()+"\n"+
                padding + "Norm_Aimed_Shot" + equal + getNorm_Aimed_Shot()+"\n"+
                padding + "Norm_Fully_Charged_Aimed_Shot" + equal + getNorm_Fully_Charged_Aimed_Shot()+"\n"+
                padding + "Norm_Charged_Attack" + equal + getNorm_Charged_Attack()+"\n"+
                padding + "Norm_Charged_Attack_Cyclic" + equal + getNorm_Charged_Attack_Cyclic()+"\n"+
                padding + "Norm_Charged_Final_Cyclic" + equal + getNorm_Charged_Final_Cyclic()+"\n"+
                padding + "Element_Str" + equal + Arrays.toString(getElement_Str())+"\n"+
                padding + "Element_Value" + equal + Arrays.toString(getElement_Value())+"\n"+
                padding + "Final_Str" + equal + Arrays.toString(getFinal_Str())+"\n"+
                padding + "Final_Value" + equal + Arrays.toString(getFinal_Value())+"\n"+
                padding + "Special_Talent_Str" + equal + Arrays.toString(getSpecial_Talent_Str())+"\n"+
                padding + "Special_Talent_Value" + equal + Arrays.toString(getSpecial_Talent_Value())+"\n"
                ;


    }
}
