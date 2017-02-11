package com.palmintelligence.administrator.yunxindemov001;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.friend.model.AddFriendNotify;
import com.netease.nimlib.sdk.msg.SystemMessageObserver;
import com.netease.nimlib.sdk.msg.constant.SystemMessageType;

/**
 * Created by Administrator on 2017/2/11 0011.
 */
public class SystemMessage extends AppCompatActivity {

    private ListView listView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_layout);

        listView=(ListView)findViewById(R.id.system_listview);


        NIMClient.getService(SystemMessageObserver.class)
                .observeReceiveSystemMsg(new Observer<com.netease.nimlib.sdk.msg.model.SystemMessage>() {
                    @Override
                    public void onEvent(com.netease.nimlib.sdk.msg.model.SystemMessage message) {
                        if (message.getType() == SystemMessageType.AddFriend) {
                            AddFriendNotify attachData = (AddFriendNotify) message.getAttachObject();
                            if (attachData != null) {
                                // 针对不同的事件做处理
                                if (attachData.getEvent() == AddFriendNotify.Event.RECV_ADD_FRIEND_DIRECT) {
                                    // 对方直接添加你为好友
                                } else if (attachData.getEvent() == AddFriendNotify.Event.RECV_AGREE_ADD_FRIEND) {
                                    // 对方通过了你的好友验证请求
                                } else if (attachData.getEvent() == AddFriendNotify.Event.RECV_REJECT_ADD_FRIEND) {
                                    // 对方拒绝了你的好友验证请求
                                } else if (attachData.getEvent() == AddFriendNotify.Event.RECV_ADD_FRIEND_VERIFY_REQUEST) {
                                    // 对方请求添加好友，一般场景会让用户选择同意或拒绝对方的好友请求。
                                    // 通过message.getContent()获取好友验证请求的附言
                                }
                            }
                        }


                    }


                }, true);










    }
}
