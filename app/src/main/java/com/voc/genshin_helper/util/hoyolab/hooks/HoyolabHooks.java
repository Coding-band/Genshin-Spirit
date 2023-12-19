package com.voc.genshin_helper.util.hoyolab.hooks;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import static com.voc.genshin_helper.util.hoyolab.HoyolabConstants.HOYOLAB_SERVER_ID;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.voc.genshin_helper.util.hoyolab.HoyolabConstants;
import com.voc.genshin_helper.util.hoyolab.HoyolabCookie;
import com.voc.genshin_helper.util.hoyolab.request.HoyolabRequest;
import com.voc.genshin_helper.util.hoyolab.request.HoyolabRequestType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class HoyolabHooks {
    public JSONObject hoyolabGameRecord(Context context){
        HoyolabCookie hoyolabCookie = new HoyolabCookie(HoyolabCookie.getCookieMap(context, HoyolabCookie.HOYOLAB_V2_KEY_GROUP));
        String hoyolabId = hoyolabCookie.getAccount_id_v2();
        //hoyolab-game-record ?
        HoyolabRequestType.IResponse response = new HoyolabRequest(HoyolabCookie.getCookiePlain(context,HoyolabCookie.HOYOLAB_V2_KEY_GROUP)).send(
                HoyolabConstants.HoyolabGameRecordCardURL(hoyolabId),
                context,
                HoyolabRequestType.Method.GET
        );

        if (response.getRetcode() != 0){
            Toast.makeText(context, "retcode "+response.getRetcode()+" : "+(response.getMessage() == null ? "null" : response.getMessage()), Toast.LENGTH_SHORT).show();
        }

        return response.getData();
    }
    public String genshinUUID(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        HoyolabConstants.GAME_SERVERS userGameServer = HoyolabConstants.GAME_SERVERS.getEnumByIdName(sharedPreferences.getString(HOYOLAB_SERVER_ID,null));

        if (userGameServer == null) return null;

        JSONObject jsonObject = hoyolabGameRecord(context);
        try {
            if (jsonObject != null && jsonObject.has("list")){
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                for (int x = 0 ; x < jsonArray.length() ; x++){
                    if (jsonArray.getJSONObject(x).has("game_id") &&
                            jsonArray.getJSONObject(x).getString("region").equals(userGameServer.getServerIdName()) &&
                            jsonArray.getJSONObject(x).getInt("game_id") == HoyolabConstants.GAME_LIST.GENSHIN_IMPACT.getGameId() //原神
                    ){
                        return jsonArray.getJSONObject(x).getString("game_role_id");
                    }
                }
            }
        } catch (JSONException e) {
            return "-2"; //JSON有錯
        }
        return "-1"; //找不到結果
    }

    public JSONObject genshinFullData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        HoyolabConstants.GAME_SERVERS hsrServerChosen = HoyolabConstants.GAME_SERVERS.getEnumByIdName(sharedPreferences.getString(HOYOLAB_SERVER_ID,null));
        if (hsrServerChosen == null) return null;
        return genshinCommonGetData(context, HoyolabConstants.GenshinFullDataURL(genshinUUID(context), hsrServerChosen.getServerIdName()));
    }
    public JSONObject genshinNoteData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        HoyolabConstants.GAME_SERVERS hsrServerChosen = HoyolabConstants.GAME_SERVERS.getEnumByIdName(sharedPreferences.getString(HOYOLAB_SERVER_ID,null));
        if (hsrServerChosen == null) return null;
        return genshinCommonGetData(context, HoyolabConstants.GenshinNoteDataURL(genshinUUID(context), hsrServerChosen.getServerIdName()));
    }

    public JSONObject genshinCommonGetData(Context context,String url){
        String hoyolabCookie = HoyolabCookie.getCookiePlain(context, HoyolabCookie.HOYOLAB_V2_KEY_GROUP);

        //hsr-full-data
        HoyolabRequestType.IResponse response = new HoyolabRequest(hoyolabCookie).setDs(true).send(
                url,
                context,
                HoyolabRequestType.Method.GET
        );

        if (response.getRetcode() != 0){
            Toast.makeText(context, "retcode "+response.getRetcode()+" : "+(response.getMessage() == null ? "null" : response.getMessage()), Toast.LENGTH_SHORT).show();
        }

        return response.getData();
    }

    /*
    public JSONObject genshinPlayerData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        HoyolabConstants.GAME_SERVERS userGameServer = HoyolabConstants.GAME_SERVERS.getEnumByIdName(sharedPreferences.getString(HOYOLAB_SERVER_ID,null));

        if (userGameServer == null) return null;

        JSONObject jsonObject = hoyolabGameRecord(context);
        try {
            if (jsonObject != null && jsonObject.has("list")){
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                for (int x = 0 ; x < jsonArray.length() ; x++){
                    if (jsonArray.getJSONObject(x).has("game_id") &&
                            jsonArray.getJSONObject(x).getString("region").equals(userGameServer.getServerIdName()) &&
                            jsonArray.getJSONObject(x).getInt("game_id") == HoyolabConstants.GAME_LIST.GENSHIN_IMPACT.getGameId() //原神
                    ){
                        return jsonArray.getJSONObject(x).getString("game_role_id");
                    }
                }
            }
        } catch (JSONException e) {
            return "-2"; //JSON有錯
        }
        return "-1"; //找不到結果

    }

     */
}
