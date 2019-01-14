package com.example.administrator.renmai.application;

import android.app.Application;

import com.easemob.chatuidemo.DemoHXSDKHelper;
import com.example.administrator.renmai.R;
import com.example.administrator.renmai.utils.SPUtil;
import com.example.administrator.renmai.utils.Utils;
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

        //初始化环信
        DemoHXSDKHelper hxSDKHelper = new DemoHXSDKHelper();
        hxSDKHelper.onInit(getApplicationContext());
    }


}
