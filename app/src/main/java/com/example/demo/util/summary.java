package com.example.demo.util;

public class summary {

    //静态方法的不需要实例化就能访问，不是静态的就需要new
    //getApplication()是用来获取Application实例的，但是该方法只在Activity和Service中才能调用；在一些其他的地方，比如说当我们在BroadcastReceiver中也想获取Application实例，这时就需要使用getApplicationContext()方法
  //其他类 访问 getSharedPreferences   也要继承 Activity
    //https://blog.csdn.net/geduo_83/article/details/86561559
    //android:theme="@style/Theme.AppCompat.NoActionBar"\
    //menu设置https://blog.csdn.net/yinzhijiezhan/article/details/80997554



}
