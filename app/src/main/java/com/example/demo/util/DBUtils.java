package com.example.demo.util;

import android.util.Log;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public  class DBUtils  {

    public static HashMap<String, Object> getInfoByName(String dbIP, String dbName,String dbUser ,String dbPassword){

        HashMap<String, Object> map =  new HashMap<>();
        try{
            Class.forName("com.mysql.jdbc.Driver"); //MySQL驱动
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://" + dbIP + ":3306/" + dbName, dbUser, dbPassword); // 尝试建立到给定数据库URL的连接
            if (connection!=null) {
                Log.e("Mysql执行连接jdbc", "Connectd Mysql");


                String sql = "select * from user where username = ? and password = ? ";  // mysql简单的查询语句
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, "user");
                ps.setString(2, "12345");
                ResultSet rs = ps.executeQuery();

                   String user="";
                   String password="";

                if (rs.next()) {  //有值就执行

                    map.put(user, rs.getString(1));
                    map.put(password, rs.getString(2));
                    map.get(1);
                    map.get(2);

                }
                else {map=null;}

                connection.close();
                ps.close();
                return map;
            }
        }catch (Exception e){
        }



        return null;
        }

}

