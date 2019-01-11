package com.example.administrator.renmai.wxapi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.administrator.renmai.http.HandlerConstant;
import com.example.administrator.renmai.http.HttpConstant;
import com.example.administrator.renmai.http.base.Http;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI mWeixinAPI;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeixinAPI = WXAPIFactory.createWXAPI(this, HttpConstant.WX_APPID, true);
        mWeixinAPI.handleIntent(this.getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mWeixinAPI.handleIntent(intent, this);//必须调用此句话
    }

    //微信发送的请求将回调到onReq方法
    @Override
    public void onReq(BaseReq req) {
    }

    //发送到微信请求的响应结果
    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                //发送成功
                SendAuth.Resp sendResp = (SendAuth.Resp) resp;
                if (sendResp != null) {
                    String code = sendResp.code;
                    getAccess_token(code);
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //发送取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                //发送被拒绝
                break;
            default:
                //发送返回
                break;
        }

    }


    private String openId;
    @SuppressLint("HandlerLeak")
    private Handler mHandler=new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //获取微信的openId
            if(msg.what==HandlerConstant.GET_WX_ACCESS_TOKEN_SUCCESS){
                final String message=msg.obj.toString();
                try {
                    JSONObject jsonObject = new JSONObject(message);
                    openId = jsonObject.getString("openid").toString().trim();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    /**
     * 获取openid accessToken值用于后期操作
     * @param code
     */
    private void getAccess_token(final String code) {
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+HttpConstant.WX_APPID+"&secret="+HttpConstant.WX_APPSECRET+"&code="+code+"&grant_type=authorization_code";
        Http.getMonth(url,mHandler,HandlerConstant.GET_WX_ACCESS_TOKEN_SUCCESS);
    }

}
