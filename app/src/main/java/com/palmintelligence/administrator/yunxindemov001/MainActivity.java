package com.palmintelligence.administrator.yunxindemov001;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.friend.model.AddFriendNotify;
import com.netease.nimlib.sdk.msg.SystemMessageObserver;
import com.netease.nimlib.sdk.msg.constant.SystemMessageType;
import com.netease.nimlib.sdk.msg.model.SystemMessage;
import com.palmintelligence.administrator.yunxindemov001.addfriends.AddActivity;
import com.palmintelligence.administrator.yunxindemov001.fragment.ConstactFragment;
import com.palmintelligence.administrator.yunxindemov001.fragment.NewsFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private NewsFragment newsFragment;
    private ConstactFragment constactFragment=new ConstactFragment();
    private Toolbar toolbar;
private ImageButton newsButton,constactButton,deynaimicButton,settingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    newsButton=(ImageButton)findViewById(R.id.buttom_news);
        constactButton=(ImageButton)findViewById(R.id.buttom_constact);
        deynaimicButton=(ImageButton)findViewById(R.id.buttom_deynaimic);
        settingButton=(ImageButton)findViewById(R.id.buttom_setting);

        newsButton.setOnClickListener(this);
        constactButton.setOnClickListener(this);
        deynaimicButton.setOnClickListener(this);
        settingButton.setOnClickListener(this);

        toolbar=(Toolbar)findViewById(R.id.main_toolbar) ;
        setSupportActionBar(toolbar);
        toolbar.setTitle("悦信");
        toolbar.inflateMenu(R.menu.menu_main);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.action_adding:
                             startActivity(new Intent(MainActivity.this, AddActivity.class));

                                break;

                        }



                return false;
            }
        });





        setDefaultFragment();


        NIMClient.getService(SystemMessageObserver.class)
                .observeReceiveSystemMsg(new Observer<SystemMessage>() {
                    @Override
                    public void onEvent(SystemMessage message) {
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

    private void setDefaultFragment()
    {

        newsFragment = new NewsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, newsFragment).commit();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttom_news:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, newsFragment).commit();
                break;

            case R.id.buttom_constact:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, constactFragment).commit();
                break;
            case R.id.buttom_deynaimic:

                break;


            case R.id.buttom_setting:


                break;
          default:break;
        }

    }


}





