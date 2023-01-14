package com.voc.genshin_helper.data;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */
public class Alarm {
    public String title; //標題
    public String info; //內容
    public int type; //種類
    public long remain_time; //尚餘時間
    public long finish_time; //完成時間
    public int jb_time; //周本提醒時間選擇
    public int ph_time; //派遣時長選擇
    public int lvl; //信任等階
    public int grade; //洞天仙力
    public int count; //洞天仙力
    public long id;

    public Alarm() {

    }

    public Alarm(String title, String info, int type, long finish_time , int jb_time, int ph_time, int lvl, int grade ,long remain_time, int count, long id) {
        this.title = title;
        this.info = info;
        this.type = type;
        this.finish_time = finish_time;
        this.jb_time = jb_time;
        this.ph_time = ph_time;
        this.lvl = lvl;
        this.grade = grade;
        this.remain_time = remain_time;
        this.count = count;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(long finish_time) {
        this.finish_time = finish_time;
    }

    public int getJb_time() {
        return jb_time;
    }

    public void setJb_time(int jb_time) {
        this.jb_time = jb_time;
    }

    public int getPh_time() {
        return ph_time;
    }

    public void setPh_time(int ph_time) {
        this.ph_time = ph_time;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public long getRemain_time() {
        return remain_time;
    }

    public void setRemain_time(long remain_time) {
        this.remain_time = remain_time;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }





}

