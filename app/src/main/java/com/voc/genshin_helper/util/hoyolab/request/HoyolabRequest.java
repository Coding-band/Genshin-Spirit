package com.voc.genshin_helper.util.hoyolab.request;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.voc.genshin_helper.util.hoyolab.GenerateDS;
import com.voc.genshin_helper.util.hoyolab.language.Language;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.internal.http.HttpMethod;

/*
 *  hoyolab.request.HoyolabRequest was refer from Dalufishe.
 */
public class HoyolabRequest {
    public enum HttpMethod{
        GET, POST, PUT, DELETE
    }
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
        this.headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Mobile Safari/537.36");
        this.headers.put("x-rpc-app_version", "4.3.0");
        this.headers.put("x-rpc-client_type", "5");
        this.headers.put("x-rpc-lang", "zh-tw");
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

    public HoyolabRequestType.IResponse send(String url, Context context, HoyolabRequestType.Method method){
        if (this.ds){
            this.headers.put("DS", GenerateDS.generate());
            System.out.println("DXS : "+headers.get("DS"));
        }

        //這邊用 HttpsURLConnection
        HttpsCallable callable = new HttpsCallable(url,context,headers,method);
        FutureTask<HoyolabRequestType.IResponse> futureTask = new FutureTask<>(callable);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(futureTask);

        try {
            return futureTask.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    class HttpsCallable implements Callable<HoyolabRequestType.IResponse> {
        private String url;
        private Context context;
        private HoyolabRequestType.Method method;
        private SharedPreferences sharedPreferences;
        private Map<String,Object> headers;

        public HttpsCallable(String url, Context context, Map<String,Object> headers, HoyolabRequestType.Method method) {
            this.url = url;
            this.context = context;
            this.sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
            this.method = method;
            this.headers = headers;
        }

        @Override
        public HoyolabRequestType.IResponse call() {
            try {
                URL urlObj = new URL(url);
                HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
                connection.setRequestMethod(method.name());
                connection.setConnectTimeout(1000);
                connection.setReadTimeout(1000);
                connection.setRequestProperty("Content-Type", "application/json");
                headers.forEach((key,obj) -> {connection.setRequestProperty(key,String.valueOf(obj));});

                int responseCode = connection.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK && responseCode != HttpURLConnection.HTTP_CREATED) {
                    return new HoyolabRequestType.IResponse(
                            responseCode,
                            "[HTTPS] : "+connection.getResponseMessage(),
                            null);
                }else{
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    System.out.println(url+" -> response.toString() : "+response.toString());
                    JSONObject jsonObject = new JSONObject(response.toString());

                    //轉譯
                    return new HoyolabRequestType.IResponse(
                            jsonObject.getInt("retcode"),
                            jsonObject.getString("message"),
                            jsonObject.isNull("data") ? null : jsonObject.getJSONObject("data")
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new HoyolabRequestType.IResponse(-9999,"",null);
        }
    }
}
