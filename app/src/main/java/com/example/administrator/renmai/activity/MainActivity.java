package com.example.administrator.renmai.activity;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chatuidemo.activity.ChatActivity;
import com.example.administrator.renmai.utils.PermissionsUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        PermissionsUtils.getInstance().chekPermissions(this, permissions, permissionsResult);

        //调用sdk登陆方法登陆聊天服务器
        EMChatManager.getInstance().login("qijiuyu79", "q790097", new EMCallBack() {
            public void onSuccess() {
                Intent intent=new Intent(MainActivity.this, ChatActivity.class);
                intent.putExtra("userId","qijiuyu77");
                startActivity(intent);
            }
            public void onError(int arg0, String arg1) {
            }
            public void onProgress(int arg0, String arg1) {
            }
        });
    }


    //创建监听权限的接口对象
    PermissionsUtils.IPermissionsResult permissionsResult = new PermissionsUtils.IPermissionsResult() {
        public void passPermissons() {
        }

        public void forbitPermissons() {
            String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
            PermissionsUtils.getInstance().chekPermissions(MainActivity.this, permissions, permissionsResult);
        }
    };


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //就多一个参数this
        PermissionsUtils.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
}
