package com.example.administrator.renmai.application;

import android.app.Application;

import com.example.administrator.renmai.utils.SPUtil;
import com.google.gson.Gson;

public class MyApplication extends Application {

    public static Gson gson;
    public static MyApplication application;
    public static SPUtil spUtil;
    public void onCreate() {
        super.onCreate();
        application = this;
        gson = new Gson();
        spUtil = SPUtil.getInstance(this);
    }
}
