package com.https.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {
    String path="http://csapi.dm300.com:21889/android/recom/editorrecomlist?pagesize=4&platform_id=0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        okHttpget();
        okHttppost();
    }
    //post请求
    private void okHttppost() {
        //客户端
        OkHttpClient client=new OkHttpClient();
        /*RequestBody：请求体，携带参数去服务端访问
        FormBody：设置表单参数，用它来设置要携带的参数
        Builder：建造者设计模式：将复杂对象的构建与他的表示分离，使得同样的构建可以创建不同的表示
         */
        RequestBody body=new FormBody.Builder().add("name","xiaoming").add("age","15").build();
        Request request=new Request.Builder().url(path).post(body).build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json=response.body().string();
                Log.i("TAG",json);
            }
        });
    }

    //get请求
    private void okHttpget() {
        //OkHttpClient: 客户端
        OkHttpClient client=new OkHttpClient();
        /*Request：请求服务端
        Request.Builder()：建造者设计模式，作用是将一个复杂对象的构建与他的表示分离，使得同样的构建可以创建不同的表示
        url():请求地址
        build()：建造结束，返回对应的对象
         */
        Request request=new Request.Builder().url(path).build();
        //Call：将你的请求封装成任务
        Call call=client.newCall(request);
        //请求调度
        call.enqueue(new Callback() {
            //请求失败，故障
            @Override
            public void onFailure(Call call, IOException e) {

            }
            //请求成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                     //获得json字符串
                    String json=response.body().string();
                    Log.i("TAG",json);
            }
        });

    }
}
