package com.palmintelligence.administrator.yunxindemov001.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.palmintelligence.administrator.yunxindemov001.R;
import com.palmintelligence.administrator.yunxindemov001.YXApplication;
import com.palmintelligence.administrator.yunxindemov001.adapter.NewsListViewAdapter;
import com.palmintelligence.administrator.yunxindemov001.chars.CharActivity;

import java.util.List;

import search.SearchActivity;

/**
 * Created by Administrator on 2017/1/21 0021.
 */
public class NewsFragment extends ListFragment {
    private NewsListViewAdapter newsListViewAdapter;
    private List<RecentContact> loadedRecents;
    private SwipeRefreshLayout swipeRefreshLayout;
    private  RecentContact loadedRecent;
    private ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newsListViewAdapter  = new NewsListViewAdapter(YXApplication.getContext(),loadedRecents);
        setListAdapter(newsListViewAdapter);


        requestMessages(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frrame_news, container, false);



        newsListViewAdapter  = new NewsListViewAdapter(YXApplication.getContext(),loadedRecents);
      setListAdapter(newsListViewAdapter);

        View layout = getLayoutInflater(savedInstanceState).inflate(R.layout.cpt_search, null);
        LinearLayout linearLayout=(LinearLayout)v.findViewById(R.id.ll_constact_serach);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), SearchActivity.class);
startActivity(intent);

            }
        });

        swipeRefreshLayout=(SwipeRefreshLayout)v.findViewById(R.id.news_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadedRecents.clear();
                requestMessages(false);
                swipeRefreshLayout.setRefreshing(false);
            }
        });




        return v;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
            if (loadedRecents!=null) {
                loadedRecent=loadedRecents.get(position);
                Intent intent = new Intent(this.getContext(), CharActivity.class);
                intent.putExtra("ID",loadedRecent.getContactId());
                startActivity(intent);

            }


    }
    private void requestMessages(boolean delay) {

   Handler handler = new Handler();

handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // 查询最近联系人列表数据
                NIMClient.getService(MsgService.class).queryRecentContacts().setCallback(new RequestCallbackWrapper<List<RecentContact>>() {

                    @Override
                    public void onResult(int code, List<RecentContact> recents, Throwable exception) {
                        loadedRecents=recents;
                        Log.d("NewsFragment","requestMessages is ok");
                    }
                });

                newsListViewAdapter.notifyDataSetChanged();
            }
        }, delay ? 250 : 0);
    }

}
