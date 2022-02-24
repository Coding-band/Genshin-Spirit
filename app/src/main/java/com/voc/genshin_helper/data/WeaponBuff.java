package com.voc.genshin_helper.data;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

public class WeaponBuff {

    WeaponBuffItem weaponBuffItem;

    public WeaponBuffItem returnWeaponBuff (String weaponName, int weaponRefineLvl){
        // init weaponBuffItem
        weaponBuffItem.setBaseName(weaponName);
        weaponBuffItem.setRefineLvl(weaponRefineLvl);
        weaponBuffItem.setBuff1Type("N/A");
        weaponBuffItem.setBuff2Type("N/A");
        weaponBuffItem.setBuff3Type("N/A");
        weaponBuffItem.setBuff4Type("N/A");
        weaponBuffItem.setBuff5Type("N/A");
        weaponBuffItem.setBuff1Value(0.0);
        weaponBuffItem.setBuff2Value(0.0);
        weaponBuffItem.setBuff3Value(0.0);
        weaponBuffItem.setBuff4Value(0.0);
        weaponBuffItem.setBuff5Value(0.0);

        switch (weaponName){
            case "Amenoma Kageuchi" : weaponBuffItem.setBuff1Type("HP_PLUS");
            default: return weaponBuffItem;
        }
    }


}
