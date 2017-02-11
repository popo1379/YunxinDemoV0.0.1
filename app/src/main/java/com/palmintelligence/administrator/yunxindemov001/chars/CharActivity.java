package com.palmintelligence.administrator.yunxindemov001.chars;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.palmintelligence.administrator.yunxindemov001.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/24 0024.
 */
public class CharActivity extends AppCompatActivity {
    private ListView mListView;
    private EditText mEditText;
    private List<IMMessage> msgInfos = new ArrayList<IMMessage>();
    private Button charbtn;
    private String msgContent;
    private MsgAdapter mAdapter;
    private static String mStrMSG = "";
    private String content;
    private IMMessage message;
    private String sessionId;
    private SessionTypeEnum sessionType;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_char);
        mListView = (ListView) findViewById(R.id.char_listView);
        mAdapter = new MsgAdapter(this, R.layout.char_item, msgInfos);
        mListView.setAdapter(mAdapter);
        sessionType=SessionTypeEnum.P2P;
        Intent intent = this.getIntent();
        sessionId = intent.getStringExtra("ID");

        Thread mThread = new Thread(mRunnable);
        mThread.start();

        charbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content = mEditText.getText().toString();
                if (!"".equals(content)) {
                     message = MessageBuilder.createTextMessage(
                            sessionId, // 聊天对象的 ID，如果是单聊，为用户帐号，如果是群聊，为群组 ID
                            sessionType, // 聊天类型，单聊或群组
                            content // 文本内容
                    );
                    sendMessage(message);
                    //动态刷新listview
                    mAdapter.notifyDataSetChanged();
                    //将Listview定位到最后一行
                    mListView.setSelection(msgInfos.size());
                    mEditText.setText("");

                }
            }
        });


    }

    // 线程:监听服务器发来的消息
    private Runnable mRunnable = new Runnable() {
        public void run() {
            while (true) {

                Observer<List<IMMessage>> incomingMessageObserver =
                        new Observer<List<IMMessage>>() {
                            @Override
                            public void onEvent(List<IMMessage> messages) {

                                if (messages == null || messages.isEmpty()) {
                                    return;
                                }
                                msgInfos = messages;

                                //动态刷新listview
                                mAdapter.notifyDataSetChanged();
                                //将Listview定位到最后一行
                                mListView.setSelection(msgInfos.size());

                            }
                        };
                NIMClient.getService(MsgServiceObserve.class)
                        .observeReceiveMessage(incomingMessageObserver, true);

            }
        }
    };

// 发送消息。如果需要关心发送结果，可设置回调函数。发送完成时，会收到回调。如果失败，会有具体的错误码。
  ;


    public boolean sendMessage(IMMessage message) {

        NIMClient.getService(MsgService.class).sendMessage(message, false);

        return true;
    }

}