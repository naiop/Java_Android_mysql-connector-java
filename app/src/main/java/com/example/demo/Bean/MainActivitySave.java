package com.example.demo.Bean;

import android.app.Application;


public  class MainActivitySave extends Application {  //继承Application后全局能访问 ，Application是继续activity的，其他类要是访问需要基础activity

    private String ed_dbip=null;
    private String ed_dbname=null;
    private String ed_dbuser=null;
    private String ed_dbpassword=null;

    public  String getEd_dbip() {
        return ed_dbip;
    }

    public void setEd_dbip(String ed_dbip) {
        this.ed_dbip = ed_dbip;
    }

    public String getEd_dbname() {
        return ed_dbname;
    }

    public void setEd_dbname(String ed_dbname) {
        this.ed_dbname = ed_dbname;
    }

    public String getEd_dbuser() {
        return ed_dbuser;
    }

    public void setEd_dbuser(String ed_dbuser) {
        this.ed_dbuser = ed_dbuser;
    }

    public String getEd_dbpassword() {
        return ed_dbpassword;
    }

    public void setEd_dbpassword(String ed_dbpassword) {
        this.ed_dbpassword = ed_dbpassword;
    }
}
