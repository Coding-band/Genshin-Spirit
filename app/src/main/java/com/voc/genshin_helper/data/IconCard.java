package com.voc.genshin_helper.data;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class IconCard {

    String itemName; // File name of global
    String itemBaseName; // File name of it

    public int getItemRare() {
        return itemRare;
    }

    public void setItemRare(int itemRare) {
        this.itemRare = itemRare;
    }

    int itemRare = 1;

    public String getItemBaseName() {
        return itemBaseName;
    }

    public void setItemBaseName(String itemBaseName) {
        this.itemBaseName = itemBaseName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }





}
