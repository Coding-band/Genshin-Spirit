package com.voc.genshin_helper.util.hoyolab.request;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import com.voc.genshin_helper.util.hoyolab.GenerateDS;
import com.voc.genshin_helper.util.hoyolab.language.Language;

import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/*
 *  hoyolab.request.HoyolabRequest was refer from Dalufishe.
 */
public class HoyolabRequest {
    /* Headers for the request. */
    private Map<String, Object> headers;

    /* Body of the request. */
    private Map<String, Object> body;

    /* Query parameters for the request. */
    private Map<String, Object> params;

    /* Flag indicating whether Dynamic Security is used. */
    private boolean ds;

    public HoyolabRequest(String cookies){
        this.headers = new HashMap<>();
        this.headers.put("Content-Type", "application/json");
        this.headers.put("Host", "bbs-api-os.hoyolab.com");
        this.headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");
        this.headers.put("x-rpc-app_version", "4.3.0");
        this.headers.put("x-rpc-client_type", "5");
        this.body = new HashMap<>();
        this.params = new HashMap<>();
        this.ds = false;
        if (cookies != null) {
            this.headers.put("Cookie", cookies);
        }
    }

    /**
     * Set Referer Headers
     *
     * @param url - The URL string of referer
     * @returns The updated Request instance.
     */
    public HoyolabRequest setReferer(String url){
        this.headers.put("Referer",url);
        this.headers.put("Origin",url);

        return this;
    }

    /**
     * Set Body Parameter
     *
     * @param body - RequestBodyType as object containing the body parameters.
     * @returns This instance of Request object.
     */
    public HoyolabRequest setBody(Map<String, Object> body){
        this.body.putAll(body);
        return this;
    }

    /**
     * Sets search parameters or query parameter.
     *
     * @param params - An object of query parameter to be set.
     * @returns {Request} - Returns this Request object.
     */
    public HoyolabRequest setParams(Map<String, Object> params){
        this.body.putAll(params);
        return this;
    }

    /**
     * Set to used Dynamic Security or not
     *
     * @param flag boolean Flag indicating whether to use dynamic security or not (default: true).
     * @returns {this} The current Request instance.
     */
    public HoyolabRequest setDs(boolean flag){
        this.ds = flag;
        return this;
    }

    /**
     * Set Language
     *
     * @param lang Language Language that used for return of API (default: Language.ENGLISH).
     * @returns {this}
     */
    public HoyolabRequest setLang(Language lang){
        this.headers.put("x-rpc-language",(lang == null ? Language.ENGLISH : lang));
        return this;
    }

    public HoyolabRequestType.IResponse send(String url, HoyolabRequestType.Method method){
        if (this.ds){
            this.headers.put("DS", GenerateDS.generate());
        }
        //這邊用 HttpsURLConnection
        return null;
    }
}
