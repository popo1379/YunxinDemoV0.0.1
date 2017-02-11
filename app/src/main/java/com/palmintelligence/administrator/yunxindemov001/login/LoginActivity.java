package com.palmintelligence.administrator.yunxindemov001.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.palmintelligence.administrator.yunxindemov001.MainActivity;
import com.palmintelligence.administrator.yunxindemov001.R;

import java.util.prefs.Preferences;

/**
 * Created by Administrator on 2017/1/16 0016.
 */
public class LoginActivity extends AppCompatActivity {
    private EditText login_account;
    private EditText login_password;
    private Button login_btn;
    private static final String TAG = LoginActivity.class.getSimpleName();

    private AbortableFuture<LoginInfo> login_Request;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        login_account = (EditText) findViewById(R.id.account);
        login_password = (EditText) findViewById(R.id.password);
        login_btn = (Button) findViewById(R.id.login);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();

            }
        });

    }

    /**
     *登录
     */


    public void doLogin() {
        Log.d("doLogin","Login going");
        final String account =   login_account.getEditableText().toString().toLowerCase();
        final String token =  login_password.getEditableText().toString().toLowerCase();


        LoginInfo info = new LoginInfo(account,token); // config...
        RequestCallback<LoginInfo> callback = new RequestCallback<LoginInfo>() {
@Override
public void onSuccess(LoginInfo param) {
    Log.i(TAG, "login success");
    saveLogin(account, token);
    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
    startActivity(intent);
    finish();

}
            @Override
            public void onFailed(int code) {
                Log.i(TAG,"login fail");
            //    onLoginDone();
                if (code == 302 || code == 404) {
                    Toast.makeText(LoginActivity.this, "帐号或密码错误", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败: " + code, Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onException(Throwable exception) {
Log.i(TAG,"lgoin onException");
            }





        };
        NIMClient.getService(AuthService.class).login(info)
                .setCallback(callback);
    }
    private static String readAppKey(Context context) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo != null) {
                return appInfo.metaData.getString("com.netease.nim.appKey");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveLogin(String account,String token){
        SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
        editor.putString("account",account);
        editor.putString("token",token);
        editor.apply();

        }
    }



