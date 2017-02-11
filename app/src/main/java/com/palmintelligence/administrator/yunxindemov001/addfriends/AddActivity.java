package com.palmintelligence.administrator.yunxindemov001.addfriends;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.friend.constant.VerifyType;
import com.netease.nimlib.sdk.friend.model.AddFriendData;
import com.palmintelligence.administrator.yunxindemov001.R;
import com.palmintelligence.administrator.yunxindemov001.chars.CharActivity;

/**
 * Created by Administrator on 2017/2/6 0006.
 */
public class AddActivity extends AppCompatActivity {

    private EditText editText_add,editText_told;
    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editText_add=(EditText)findViewById(R.id.edit_add);
        editText_told=(EditText) findViewById(R.id.edit_addtold);
        button=(Button)findViewById(R.id.button_add);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText_add.getText()==null){

                    Toast.makeText(getApplicationContext(),"对方帐号不能为空！",Toast.LENGTH_SHORT);

                }

                final VerifyType verifyType = VerifyType.VERIFY_REQUEST; // 发起好友验证请求
                String msg = "好友请求附言";
                NIMClient.getService(FriendService.class).addFriend(new AddFriendData(editText_add.getText().toString(), verifyType, editText_told.getText().toString()))
                        .setCallback(new RequestCallback<Void>() {
                                public void onSuccess(Void param){
                                    Intent intent = new Intent(getApplicationContext(), AddSuccess.class);
                                    intent.putExtra("ID",editText_add.getText().toString());
                                    startActivity(intent);

                                }

                            public void onFailed(int code){
                                Toast.makeText(getApplicationContext(),"请检查对方帐号是否正确",Toast.LENGTH_SHORT);
                            }
                            public void onException(Throwable exception){
                                Log.i("AddActivity","Exception of addfriend");
                            }

                        });




            }
        });











    }




}
