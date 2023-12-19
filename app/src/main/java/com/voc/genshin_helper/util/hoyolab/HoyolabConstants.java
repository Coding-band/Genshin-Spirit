package com.voc.genshin_helper.util.hoyolab;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import java.util.Objects;

public class HoyolabConstants {

    public enum GAME_SERVERS{
        //官服[天空島服] = "cn_gf01"
        //B服[世界樹服] = "cn_qd01"
        //Asia = "os_asia"
        //Europe = "os_euro"
        //America = "os_usa"
        //TW,HK,MO = "os_cht"

        GENSHIN_MIHOYO("天空島服","cn_gf01","CN"),
        GENSHIN_BILIBILI("世界樹服","cn_qd01","CN"),
        GENSHIN_ASIA("Asia","os_asia","OS"),
        GENSHIN_EUROPE("Europe","os_euro","OS"),
        GENSHIN_AMERICA("America","os_usa","OS"),
        GENSHIN_TW_HK_MO("TW,HK,MO","os_cht","OS"),
        ;

        private final String serverDevName;
        private final String serverIdName;
        private final String serverLocation;

        /**
         *
         * @param serverDevName 方便我對照的...
         * @param serverIdName hoyolabGameRecord() 獲取 JSON data => data.list[x].region
         * @param serverLocation 分辨 國服 (CN) 和 國際服 (OS)
         */
        GAME_SERVERS(String serverDevName, String serverIdName, String serverLocation) {
            this.serverDevName = serverDevName;
            this.serverIdName = serverIdName;
            this.serverLocation = serverLocation;
        }

        public static GAME_SERVERS getEnumByIdName (String serverIdName){
            for (GAME_SERVERS server : GAME_SERVERS.values()){
                if (Objects.equals(server.serverIdName, serverIdName)) return server;
            }
            return null;
        }
        public static GAME_SERVERS getEnumByDevName (String serverDevName){
            for (GAME_SERVERS server : GAME_SERVERS.values()){
                if (Objects.equals(server.serverDevName, serverDevName)) return server;
            }
            return null;
        }

        public String getServerDevName() {
            return serverDevName;
        }

        public String getServerIdName() {
            return serverIdName;
        }

        public String getServerLocation() {
            return serverLocation;
        }
    }
    public enum GAME_LIST{
        HONKAI_3RD("崩壞3rd",1),
        GENSHIN_IMPACT("原神",2),
        HONKAI_2ND("崩壞學院2",3),
        TEARS_OF_THEMIS("未定事件簿",4),
        DA_BIE_YE("大别野",5),
        HONKAI_STAR_RAIL("崩壞．星穹鐵道",6),
        ZZZ("絕區零",8),
        ;

        private final String gameDevName;
        private final int gameId;

        /**
         *
         * @param gameDevName 方便我對照的...
         * @param gameId 在 hoyolabGameRecord() 獲取 JSON data => data.list[x].game_id
         */
        GAME_LIST(String gameDevName, int gameId) {
            this.gameDevName = gameDevName;
            this.gameId = gameId;
        }

        public static GAME_LIST getEnumById (int gameId){
            for (GAME_LIST game : GAME_LIST.values()){
                if (game.gameId == gameId) return game;
            }
            return null;
        }
        public static GAME_LIST getEnumByDevName (String  gameDevName){
            for (GAME_LIST game : GAME_LIST.values()){
                if (Objects.equals(game.gameDevName, gameDevName)) return game;
            }
            return null;
        }

        public String getGameDevName() {
            return gameDevName;
        }

        public int getGameId() {
            return gameId;
        }
    }

    // SharedPreference use
    public static final String HOYOLAB_SERVER_ID = "hoyolabServerId";

    /**
     * 獲取展示用戶Hoyolab帳戶 所有掛鉤的遊戲帳戶
     * @param hoyolabId 用戶Hoyolab帳戶的ID
     * @return 展示所有掛鉤遊戲帳戶的 URL
     */
    public static final String HoyolabGameRecordCardURL(String hoyolabId){
        return "https://bbs-api-os.hoyolab.com/game_record/card/wapi/getGameRecordCard?uid="+hoyolabId;
    }

    /**
     * 獲取指定原神帳戶的所有資訊
     * @param uuid 用戶原神UID
     * @param server 用戶請求的伺服器
     * @return 展示指定原神帳戶所有資訊的 URL
     */
    public static final String GenshinFullDataURL(String uuid, String server){
        return "https://bbs-api-os.hoyolab.com/game_record/genshin/api/index?server="+server+"&role_id="+uuid;
    }
    /**
     * 獲取指定原神帳戶的每日便簽
     * @param uuid 用戶原神UID
     * @param server 用戶請求的伺服器
     * @return 展示指定原神帳戶每日便簽的 URL
     */
    public static final String GenshinNoteDataURL(String uuid, String server){
        return "https://bbs-api-os.hoyolab.com/game_record/genshin/api/dailyNote?server="+server+"&role_id="+uuid;
    }
}
