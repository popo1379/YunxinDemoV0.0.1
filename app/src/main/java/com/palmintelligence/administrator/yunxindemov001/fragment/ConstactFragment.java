package com.palmintelligence.administrator.yunxindemov001.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import com.palmintelligence.administrator.yunxindemov001.R;
import com.palmintelligence.administrator.yunxindemov001.adapter.ConstactAdapter;
import com.palmintelligence.administrator.yunxindemov001.addfriends.AddActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/22 0022.
 */
public class ConstactFragment extends ListFragment {

    private ConstactAdapter constactAdapter;
    private List<NimUserInfo> users;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestConstact(true);

        constactAdapter=new ConstactAdapter(getActivity().getApplicationContext(),users);
    setListAdapter(constactAdapter);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.frame_constacter,container,false);

        ImageView imageView=(ImageView)view.findViewById(R.id.iv_friend);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getContext(), AddActivity.class));
            }
        });



        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.constact_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                requestConstact(false);
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        return view;

    }

    private void requestConstact(boolean delay) {

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                List<String> accounts = NIMClient.getService(FriendService.class).getFriendAccounts(); // 获取所有好友帐号
              users = NIMClient.getService(UserService.class).getUserInfoList(accounts); // 获取所有好友用户资料
                Log.d("ConstactFragment","requestfriend is ok");

                constactAdapter.notifyDataSetChanged();
            }
        }, delay ? 250 : 0);
    }

}







