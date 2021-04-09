package com.example.demo.util;

import com.example.demo.Bean.MainActivitySave;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

//http://guolin.tech/api/china/"+provinceCode;//获取被选取地区的网络地址
public class HttpUrl {

    /**
     * 获取IP地址 -->通过自带的HTTP访问网络 url 再通过自带json 解析URl ;url是Http://不能访问 ， 27以上版本限制了明文流量的网络请求
     * https://www.jianshu.com/p/02956bdddd2d
     * @return
     */
    public static String GetNetIp() {
        URL infoUrl = null;
        InputStream inStream = null;
        String line = "";
        try {
            infoUrl = new URL("http://msearchcdn.kugou.com/new/app/i/search.php?cmd=302&keyword=mo");
            URLConnection connection = infoUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                StringBuilder strber = new StringBuilder();
                while ((line = reader.readLine()) != null)
                    strber.append(line + "\n");
                inStream.close();
                // 从反馈的结果中提取出IP地址
               // int start = strber.indexOf("{");
             //   int end = strber.indexOf("}");
               // String json = strber.substring(start, end + 1);

                String json = strber.toString();
                if (json != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                    //    line = jsonObject.optString("cip")+jsonObject.optString("cname");
                        line =jsonObject.optString("keyword");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
