package com.https.okhttpdemo;

import android.os.Handler;
import android.os.Message;
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

public class MainActivity extends AppCompatActivity {
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case  1:

                    break;
                case 2:

                    break;
            }
        }
    };

    String path="http://csapi.dm300.com:21889/android/recom/editorrecomlist?pagesize=4&platform_id=0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        httpget();
        httppost();
        httpGF();
        httpFGF();
    }

    private void httpFGF() {
        try {
            httputils.getintest().runs(path).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json=response.body().string();
                    Log.i("TAG","----->非官方"+json);

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void httpGF() {
        //
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json= httputils.getintest().run(path);
                    handler.sendEmptyMessage(1);
                    handler.sendEmptyMessageDelayed(1,2000);
                    Log.i("TAG","----->官方"+json);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //post
    private void httppost() {
        //okhttpclient :okthttp主要类
        OkHttpClient client=new OkHttpClient();
        /*
        RequestBody:请求体,携带参数去服务端访问
        FormBody:设置表单参数,用它来设置要携带的参数
        FormBody.Builder():设计模式:将一个复杂的对象的构建与他的表示分离,使得同样的构建过程可以创建不同的表示
        build:结束,返回一个RequestBody
         */
        RequestBody body=new FormBody.Builder().add("name","xiaoming").add("age","18").build();
        //创建Request
        Request request=new Request.Builder().url(path).post(body).build();
        //Call 将你的请求封装成任务
        Call call=client.newCall(request);
        //请求调度
        call.enqueue(new Callback() {
            //失败
            @Override
            public void onFailure(Call call, IOException e) {

            }
            //成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                   String  json= response.body().string();
                Log.i("TAG","----->post"+json);

                response.body().byteStream();
                       response.body().bytes();
            }
        });
    }

    //get
    private void httpget() {
        //okhttpclient :okthttp主要类，客户端
        OkHttpClient   client=new OkHttpClient();
        /*
        Request:请求服务端
        Request.Builder():建造者设计模式:将一个复杂的对象的构建与他的表示分离,使得同样的构建过程可以创建不同的表示
        url（path）：输入你的接口地址
        build:结束,返回Request对象
         */
        Request request=new Request.Builder().url(path).build();
        //Call 将你的请求封装成任务
        Call call=client.newCall(request);
        //请求调度
        call.enqueue(new Callback() {
            //失败
            @Override
            public void onFailure(Call call, IOException e) {

            }
            //成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //返回String
                String json=response.body().string();
                //返回byte[]
                            response.body().bytes();
                //返回流
                            response.body().byteStream();
            }
        });
    }


}
