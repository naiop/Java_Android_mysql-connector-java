package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.demo.Bean.MainActivitySave;
import com.example.demo.MainActivity;
import com.example.demo.R;
import com.example.demo.util.DBUtils;
import com.example.demo.util.HttpUrl;
import com.mysql.jdbc.Connection;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;


public class CoreActivity extends AppCompatActivity {
    private Button btnT , ip ,getsettest,getbtn;
    private TextView tx ,tv1 ,gettv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);

        btnT=findViewById(R.id.vbtn);
        ip=findViewById(R.id.ip);
        tx=findViewById(R.id.vtext);
        tv1=findViewById(R.id.tv11);
        getsettest=findViewById(R.id.getset);
        getbtn=findViewById(R.id.getbtn);
        gettv=findViewById(R.id.gettv);



        setListener();
        getIP();
    //    getset();
    //    getposttext();

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings1) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    private void getset(){
        getsettest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MainActivitySave Data1=(MainActivitySave) getApplication();
                tv1.setText(Data1.getEd_dbip());

            }
        });
    }
    /**
     * 设置监听
     */
    private  void setListener() {

        // 按钮点击事件
        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个线程来连接数据库并获取数据库中对应表的数据
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        SharedPreferences sp= getSharedPreferences("save_txt",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sp.edit();
                        String ip = sp.getString("dbip", "47.112.230.135");
                        String dbName = sp.getString("dbname", "user_login");
                        String user = sp.getString("dbuser", "root");
                        String password = sp.getString("dbpassword", "124578");


                        HashMap<String, Object> map = DBUtils.getInfoByName(ip,dbName,user,password);
                       //------------------

                        Message message = handler.obtainMessage();  //Message

                        if(map !=null){
                            message.what = 0x11;
                            message.obj = "登录成功";
                        }else {
                            message.what = 0x12;
                            message.obj = "服务器未获取到数据";
                        }

                        handler.sendMessage(message); // 发消息通知主线程更新UI
                       //-----------------
                    }
                }).start();

                //-----------------

            }
        });
    }



    /**
     * 线程 ;安卓中的网络访问要在子线程中访问 UI只能在主线程程中更新
     */
    @SuppressLint("HandlerLeak") //线程
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x11:
                    String s = (String) msg.obj;
                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                    break;
                case 0x12:
                    String ss = (String) msg.obj;
                    Toast.makeText(getApplicationContext(),ss,Toast.LENGTH_SHORT).show();
                    break;
                case 0x13:
                    String sss = (String) msg.obj;
                    tx.setText(sss);
                    break;
                case 0x14:
                    String ssss = (String) msg.obj;
                   // Toast.makeText(getApplicationContext(),ssss,Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };



    private void getIP(){
        ip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String ip= HttpUrl.GetNetIp();

                        Message message = handler.obtainMessage();
                        if(ip !=null){
                            message.what = 0x13;
                            message.obj = ip; //获取成功
                        }else {
                            message.what = 0x14;
                            message.obj = "获取失败";
                        }
                        // 发消息通知主线程更新UI
                        handler.sendMessage(message);

                    }
                }).start();

            }
        });
    }


    private void getposttext(){
        getbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/**
 * 异步get请求
 */
                //第一步获取okHttpClient对象
                OkHttpClient client = new OkHttpClient.Builder()
                        .build();
                //第二步构建Request对象
                Request request = new Request.Builder()
                        .url("http://c.y.qq.com/fav/fcgi-bin/fcg_get_profile_order_asset.fcg?format=json&ct=20&cid=205360956&userid=1596782257&reqtype=1&sin=0&ein=1000")
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
                                gettv.setText(result);

                            }
                        });

                    }
                });

            }
        });
    }


}
