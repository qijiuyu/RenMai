package com.example.administrator.renmai.activity;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.administrator.renmai.utils.PermissionsUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        PermissionsUtils.getInstance().chekPermissions(this, permissions, permissionsResult);
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
