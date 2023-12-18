package com.voc.genshin_helper.util.hoyolab.request;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import java.util.Map;

/*
 *  hoyolab.request.HoyolabRequestType was refer from Dalufishe.
 */
public class HoyolabRequestType {

    public enum Method{
        GET,
        POST
    }
    public class BaseType{
        Map<String, Object> data;

        public BaseType(Map<String, Object> data) {
            this.data = data;
        }
    }

    public class IResponse{
        int retcode;
        String message;
        Object data;

        public IResponse(int retcode, String message, Object data) {
            this.retcode = retcode;
            this.message = message;
            this.data = data;
        }
    }
}
