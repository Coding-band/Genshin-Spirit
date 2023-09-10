package com.voc.genshin_helper.data;/*
 * Package com.voc.genshin_helper.data.Artifacts was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class Enemys {
    public String base_name;
    public String name;
    public int type_id;
    public int element_id;

    public Enemys() {
    }

    public Enemys(String str,String base_name, int type_id, int element_id) {
        this.name = str;
        this.type_id = type_id;
        this.element_id = element_id;
        this.base_name = base_name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getBaseName() {
        return this.base_name;
    }

    public void setBaseName(String str) {
        this.base_name = str;
    }

    public int getTypeId() {
        return this.type_id;
    }

    public void setTypeId(int type_id) {
        this.type_id = type_id;
    }

    public int getElementId() {
        return this.element_id;
    }

    public void setElementId(int element_id) {
        this.element_id = element_id;
    }
}