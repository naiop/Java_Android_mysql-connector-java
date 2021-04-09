package com.example.demo.util;

import android.app.Activity;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class getRequest extends Activity {
    /**
     * 异步get请求
     */
    public  void get() {
        //第一步获取okHttpClient对象
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        //第二步构建Request对象
        Request request = new Request.Builder()
                .url("https://pv.sohu.com/cityjson?ie=utf-8")
                .get()
                .build();
        //第三步构建Call对象
        Call call = client.newCall(request);
        //第四步:异步get请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("ttit", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //得到的子线程
                final String result = response.body().string();
                Log.e("ttit", result);

                runOnUiThread(new Runnable() { //线程
                    @Override
                    public void run() {
                        //主线程操作

                    }
                });

            }
        });
    }

}
