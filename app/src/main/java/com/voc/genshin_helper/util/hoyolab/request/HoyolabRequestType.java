package com.voc.genshin_helper.util.hoyolab.request;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import org.json.JSONObject;

import java.util.Map;

/*
 *  hoyolab.request.HoyolabRequestType was refer from Dalufishe.
 */
public class HoyolabRequestType {

    public enum Method{
        GET,
        POST
    }

    public static class IResponse{
        int retcode;
        String message;
        JSONObject data;

        public IResponse(int retcode, String message, JSONObject data) {
            this.retcode = retcode;
            this.message = message;
            this.data = data;
        }

        public int getRetcode() {
            return retcode;
        }

        public String getMessage() {
            return message;
        }

        public JSONObject getData() {
            return data;
        }

        public String toString(){
            return "{retcode:"+getRetcode()+", message:"+message+", data:"+getData()+"}";
        }
    }
}
