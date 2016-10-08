package com.https.okhttpdemo;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wuwei on 16/9/13.
 */

public class httputils {
    //单例
    public httputils() {
    }

    static httputils ok = new httputils();

    public static httputils getintest() {
        return ok;
    }
    OkHttpClient client=new OkHttpClient();
    //官方
    String run(String url) throws IOException {
        Request request=new Request.Builder().url(url).build();
        Call call=client.newCall(request);
        //通过call.execute()获得response;
        Response response=call.execute();
        return response.body().string();
    }
    //非官方
    Call runs(String url)throws IOException{
        Request request=new Request.Builder().url(url).build();
        Call call=client.newCall(request);
        return call;
    }

}
