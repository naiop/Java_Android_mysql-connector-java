package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.Bean.MainActivitySave;
import com.example.demo.activity.CoreActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_start;
    private Button btn_save;
    public EditText ed_dbip;
    private EditText ed_dbname;
    private EditText ed_dbuser;
    private EditText ed_dbpassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBarTransparent();
        save();
        start();

    }

    public void start(){
        btn_start=findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, CoreActivity.class);
                startActivity(in);
            }
        });
    }


    public void save(){
        btn_save=findViewById(R.id.btn_save);
        ed_dbip=findViewById(R.id.edip);
        ed_dbname=findViewById(R.id.edname);
        ed_dbuser=findViewById(R.id.eduser);
        ed_dbpassword=findViewById(R.id.edpassword);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dbip=ed_dbip.getText().toString().trim();
                String dbname=ed_dbname.getText().toString().trim();
                String dbuser=ed_dbuser.getText().toString().trim();
                String dbpassword=ed_dbpassword.getText().toString().trim();

                MainActivitySave Data1=(MainActivitySave) getApplication(); //实例化 MainActivitySave
                Data1.setEd_dbip(dbip);
                Data1.setEd_dbname(dbname);
                Data1.setEd_dbuser(dbuser);
                Data1.setEd_dbpassword(dbpassword);
                Log.e("savemsg",dbip+dbname+dbuser+dbpassword);


                SharedPreferences sp=getSharedPreferences("save_txt",Context.MODE_PRIVATE);

                SharedPreferences.Editor editor=sp.edit();
                editor.putString("dbip",dbip);
                editor.putString("dbname",dbname);
                editor.putString("dbuser",dbuser);
                editor.putString("dbpassword",dbpassword);

            //    String s = shp.getString("name", "默认");读取

                boolean isSaveSuccess = editor.commit();
                if (isSaveSuccess) {
                    Toast.makeText(MainActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    //   StatusBarUtil.darkMode(mContext,true);黑色 false白色
    private void setStatusBarTransparent(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){//版本判断
            Window window = getWindow();  // Translucent status bar
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//设置statusbar应用所占的屏幕扩大到全屏，但是最顶上会有背景透明的状态栏，它的文字可能会盖着你的应用的标题栏
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

    }


}