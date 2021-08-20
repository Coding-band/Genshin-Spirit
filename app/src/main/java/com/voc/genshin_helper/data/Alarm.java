package com.voc.genshin_helper.data;

/**
 * Package com.voc.genshin_helper.data was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2020 Xectorda 版權所有
 */
public class Alarm {
    public String title;
    public String info;
    public int type;
    public long remain_time;
    public long finish_time;
    public int jb_time; // 周本Choice
    public int ph_time; // 派遣Choice
    public int lvl; //信任等階
    public int grade; //洞天仙力
    public int count; //洞天仙力

    public Alarm() {

    }

    public Alarm(String title, String info, int type, long finish_time , int jb_time, int ph_time, int lvl, int grade ,long remain_time, int count) {
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





}

