package com.example.administrator.renmai.utils;

import android.app.ActivityManager;
import android.content.pm.PackageManager;

import com.example.administrator.renmai.application.MyApplication;

import java.util.Iterator;
import java.util.List;

public class Utils {

    /**
     * 获取当前进程的名称
     * @return
     */
    public static String getAppName() {
        String processName = null;
        ActivityManager am = (ActivityManager) MyApplication.application.getSystemService(MyApplication.application.ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = MyApplication.application.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                int pid = android.os.Process.myPid();
                if (info.pid == pid) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
            }
        }
        return processName;
    }
}
