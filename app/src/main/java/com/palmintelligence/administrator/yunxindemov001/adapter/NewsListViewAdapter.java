package com.palmintelligence.administrator.yunxindemov001.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.palmintelligence.administrator.yunxindemov001.R;

import java.util.List;
import java.util.logging.StreamHandler;

/**
 * Created by Administrator on 2017/1/19 0019.
 */
public class NewsListViewAdapter extends BaseAdapter {
    private Context context;
    private List<RecentContact> loadedRecents;
    private  RecentContact loadedRecent;
    public NewsListViewAdapter(Context context,List<RecentContact> loadedRecents){
        this.context=context;
        this.loadedRecents=loadedRecents;
    }
    @Override
    public int getCount() {
        if (loadedRecents==null){
            return 0;
        }

        return loadedRecents.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.news_item, null);
            holder.news_constact = (TextView) convertView.findViewById(R.id.news_constact);
            holder.news_content = (TextView) convertView.findViewById(R.id.news_content);
            holder.news_time = (TextView) convertView.findViewById(R.id.news_time);
            holder.constact_img = (ImageView) convertView.findViewById(R.id.news_imageview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(position== -1){
            //无消息逻辑
            holder.news_time.setText("NO");
            holder.news_content.setText("NO");
            holder.news_constact.setText("NO");
            return convertView;
        }
            loadedRecent=loadedRecents.get(position);
            holder.news_time.setText(""+loadedRecent.getTime());
            holder.news_content.setText(loadedRecent.getContent());
            holder.news_constact.setText(loadedRecent.getContactId());

        return convertView;
    }



    public final class ViewHolder{
        public TextView news_constact;
        public TextView news_content;
        public ImageView constact_img;
        public TextView news_time;

    }


}
