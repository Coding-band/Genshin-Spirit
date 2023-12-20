package com.voc.genshin_helper.util.hoyolab.hooks;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import com.voc.genshin_helper.util.hoyolab.HoyolabConstants;

public class HoyolabUser {
    String uid = "-1";
    String username = "未知";
    HoyolabConstants.GAME_SERVERS server;
    int level = -1;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HoyolabConstants.GAME_SERVERS getServer() {
        return server;
    }

    public void setServer(HoyolabConstants.GAME_SERVERS server) {
        this.server = server;
    }
    public void setServer(String serverId) {
        this.server = HoyolabConstants.GAME_SERVERS.getEnumByIdName(serverId);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public HoyolabUser(String uid, String username, HoyolabConstants.GAME_SERVERS server, int level) {
        this.uid = uid;
        this.username = username;
        this.server = server;
        this.level = level;
    }
    public HoyolabUser(String uid, String username, String serverId, int level) {
        this.uid = uid;
        this.username = username;
        this.server = HoyolabConstants.GAME_SERVERS.getEnumByIdName(serverId);
        this.level = level;
    }

    public String printOut(){
        return "{uid="+getUid()+", username="+getUsername()+", server="+getServer().name()+", level="+getLevel()+"}";
    }
}
