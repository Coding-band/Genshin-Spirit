package com.voc.genshin_helper.data;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */
public class CalculatorDB {
    public String name; //資料表名稱
    public long id; //資料表ID
    public int char_cnt; //資料表中包括之角色數目
    public int weapon_cnt; //資料表中包括之武器數目
    public int artifact_cnt; //資料表中包括之聖遺物
    public String create_unix; //資料表創建時間

    public CalculatorDB() {

    }

    public CalculatorDB(String name, long id, int char_cnt, int weapon_cnt, int artifact_cnt, String create_unix) {
        this.name = name;
        this.id = id;
        this.char_cnt = char_cnt;
        this.weapon_cnt = weapon_cnt;
        this.artifact_cnt = artifact_cnt;
        this.create_unix = create_unix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getChar_cnt() {
        return char_cnt;
    }

    public void setChar_cnt(int char_cnt) {
        this.char_cnt = char_cnt;
    }

    public int getWeapon_cnt() {
        return weapon_cnt;
    }

    public void setWeapon_cnt(int weapon_cnt) {
        this.weapon_cnt = weapon_cnt;
    }

    public int getArtifact_cnt() {
        return artifact_cnt;
    }

    public void setArtifact_cnt(int artifact_cnt) {
        this.artifact_cnt = artifact_cnt;
    }

    public String getCreate_unix() {
        return create_unix;
    }

    public void setCreate_unix(String create_unix) {
        this.create_unix = create_unix;
    }







}

