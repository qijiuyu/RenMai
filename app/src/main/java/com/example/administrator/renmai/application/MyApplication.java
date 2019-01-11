package com.example.administrator.renmai.application;

import android.app.Application;

import com.example.administrator.renmai.utils.SPUtil;
import com.example.administrator.renmai.utils.Utils;
import com.google.gson.Gson;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
public class MyApplication extends Application {

    public static Gson gson;
    public static MyApplication application;
    public static SPUtil spUtil;
    public void onCreate() {
        super.onCreate();
        application = this;
        gson = new Gson();
        spUtil = SPUtil.getInstance(this);

        String processAppName = Utils.getAppName();
        if (processAppName == null ||!processAppName.equalsIgnoreCase(this.getPackageName())) {
            return;
        }

        //初始化环信
        initIM();
    }


    /**
     * 初始化环信
     */
    private void initIM(){
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        //初始化
        EMClient.getInstance().init(this, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }
}
