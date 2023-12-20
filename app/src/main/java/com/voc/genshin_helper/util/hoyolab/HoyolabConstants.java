package com.voc.genshin_helper.util.hoyolab;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import com.voc.genshin_helper.R;

import java.util.Objects;

public class HoyolabConstants {

    public enum GAME_SERVERS{
        GENSHIN_MIHOYO("天空島服", R.string.sky_land_ser,"cn_gf01","CN"),
        GENSHIN_BILIBILI("世界樹服", R.string.world_tree,"cn_qd01","CN"),
        GENSHIN_ASIA("Asia", R.string.asia_ser,"os_asia","OS"),
        GENSHIN_EUROPE("Europe", R.string.europe_ser,"os_euro","OS"),
        GENSHIN_AMERICA("America", R.string.america_ser,"os_usa","OS"),
        GENSHIN_TW_HK_MO("TW,HK,MO", R.string.hk_tw_mo_ser,"os_cht","OS"),
        UNKNOWN("UNKNOWN", R.string.unknown,"UNKNOWN","UNKNOWN"),
        ;

        private final String serverDevName;
        private final int serverTranslateName;
        private final String serverIdName;
        private final String serverLocation;

        /**
         *
         * @param serverDevName 方便我對照的...
         * @param serverIdName hoyolabGameRecord() 獲取 JSON data => data.list[x].region
         * @param serverLocation 分辨 國服 (CN) 和 國際服 (OS)
         */
        GAME_SERVERS(String serverDevName, int serverTranslateName, String serverIdName, String serverLocation) {
            this.serverDevName = serverDevName;
            this.serverIdName = serverIdName;
            this.serverTranslateName = serverTranslateName;
            this.serverLocation = serverLocation;
        }

        public static GAME_SERVERS getEnumByIdName (String serverIdName){
            for (GAME_SERVERS server : GAME_SERVERS.values()){
                if (Objects.equals(server.serverIdName, serverIdName)) return server;
            }
            return UNKNOWN;
        }
        public static GAME_SERVERS getEnumByDevName (String serverDevName){
            for (GAME_SERVERS server : GAME_SERVERS.values()){
                if (Objects.equals(server.serverDevName, serverDevName)) return server;
            }
            return UNKNOWN;
        }

        public int getServerTranslateName() {
            return serverTranslateName;
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
    public static final String HOYOLAB_DAILYMEMO_EMPTY = "{\"current_resin\":0,\"max_resin\":160,\"resin_recovery_time\":\"0\",\"finished_task_num\":0,\"total_task_num\":4,\"is_extra_task_reward_received\":false,\"remain_resin_discount_num\":0,\"resin_discount_num_limit\":3,\"current_expedition_num\":0,\"max_expedition_num\":5,\"expeditions\":[{\"avatar_side_icon\":\"N/A\",\"status\":\"Idle\",\"remained_time\":\"-1\"},{\"avatar_side_icon\":\"N/A\",\"status\":\"Idle\",\"remained_time\":\"-1\"},{\"avatar_side_icon\":\"N/A\",\"status\":\"Idle\",\"remained_time\":\"-1\"},{\"avatar_side_icon\":\"N/A\",\"status\":\"Idle\",\"remained_time\":\"-1\"},{\"avatar_side_icon\":\"N/A\",\"status\":\"Idle\",\"remained_time\":\"-1\"}],\"current_home_coin\":0,\"max_home_coin\":300,\"home_coin_recovery_time\":\"0\",\"calendar_url\":\"\",\"transformer\":{\"obtained\":true,\"recovery_time\":{\"Day\":0,\"Hour\":0,\"Minute\":0,\"Second\":0,\"reached\":true},\"wiki\":\"\",\"noticed\":false,\"latest_job_id\":\"0\"},\"daily_task\":{\"total_num\":4,\"finished_num\":0,\"is_extra_task_reward_received\":false,\"task_rewards\":[{\"status\":\"N/A\"},{\"status\":\"N/A\"},{\"status\":\"N/A\"},{\"status\":\"N/A\"}],\"attendance_rewards\":[{\"status\":\"N/A\",\"progress\":0},{\"status\":\"N/A\",\"progress\":0},{\"status\":\"N/A\",\"progress\":0},{\"status\":\"N/A\",\"progress\":0}],\"attendance_visible\":true},\"archon_quest_progress\":{\"list\":[],\"is_open_archon_quest\":true,\"is_finish_all_mainline\":true,\"is_finish_all_interchapter\":true,\"wiki_url\":\"\"}}";
    /**
     * 獲取展示用戶Hoyolab帳戶 所有掛鉤的遊戲帳戶
     * @param hoyolabId 用戶Hoyolab帳戶的ID
     * @return 展示所有掛鉤遊戲帳戶的 URL
     */
    public static final String HoyolabGameRecordCardURL(String hoyolabId){
        return "https://bbs-api-os.hoyolab.com/game_record/card/wapi/getGameRecordCard?uid="+hoyolabId;
    }

    /**
     * 獲取指定原神帳戶的所有資訊 (戰績頁面)
     * @param uuid 用戶原神UID
     * @param server 用戶請求的伺服器
     * @return 展示指定原神帳戶所有資訊的 URL
     */
    public static final String GenshinPlayerDataURL(String uuid, String server){
        return "https://bbs-api-os.hoyolab.com/game_record/genshin/api/index?server="+server+"&role_id="+uuid;
    }
    /**
     * 獲取指定原神帳戶的每日便簽 (便簽頁面)
     * @param uuid 用戶原神UID
     * @param server 用戶請求的伺服器
     * @return 展示指定原神帳戶每日便簽的 URL
     */
    public static final String GenshinNoteDataURL(String uuid, String server){
        return "https://bbs-api-os.hoyolab.com/game_record/genshin/api/dailyNote?server="+server+"&role_id="+uuid;
    }
    /**
     * 獲取原神官方活動資訊**内容** (活動頁面)
     * @param language 語言 (zh-tw, ja-jp, zh-cn, etc...)
     * @param server 用戶請求的伺服器
     * @return 展示原神官方活動資訊**内容**的 URL
     */
    public static final String GenshinEventContentURL(String language, String server){
        return "https://sg-hk4e-api.hoyoverse.com/common/hk4e_global/announcement/api/getAnnContent?bundle_id=hk4e_global&game=hk4e&game_biz=hk4e_global&lang="+language+"&level=43&platform=pc&region="+server+"&uid=100000000";
    }
    /**
     * 獲取原神官方活動資訊**列表** (活動頁面)
     * @param language 語言 (zh-tw, ja-jp, zh-cn, etc...)
     * @param server 用戶請求的伺服器
     * @return 展示原神官方活動資訊**列表**的 URL
     */
    public static final String GenshinEventListURL(String language, String server){
        return "https://sg-hk4e-api.hoyoverse.com/common/hk4e_global/announcement/api/getAnnList?bundle_id=hk4e_global&game=hk4e&game_biz=hk4e_global&lang="+language+"&level=43&platform=pc&region="+server+"&uid=100000000";
    }
}
