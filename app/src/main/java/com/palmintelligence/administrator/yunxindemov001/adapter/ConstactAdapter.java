package com.palmintelligence.administrator.yunxindemov001.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import com.palmintelligence.administrator.yunxindemov001.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Administrator on 2017/2/1 0001.
 */
public class ConstactAdapter extends BaseAdapter {
    private Context context;

  private List<NimUserInfo> users;
    NimUserInfo userInfo;

    public ConstactAdapter(Context context,List<NimUserInfo> users){
  this.context=context;
        this.users=users;
    }
    @Override
    public int getCount() {
        if (users==null){
            return 0;
        }


        return users.size();
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
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
                convertView= LayoutInflater.from(context).inflate(R.layout.constacter_item,null);
                holder.imageView=(ImageView)convertView.findViewById(R.id.constact_imageView);
                holder.text_name=(TextView)convertView.findViewById(R.id.constact_name);
            holder.text_sex=(TextView)convertView.findViewById(R.id.constact_online);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


            if (position == -1) {

                return convertView;
                //无消息逻辑
            }

                userInfo=users.get(position);
                 holder.text_sex.setText(""+userInfo.getGenderEnum());
                holder.text_name.setText(""+userInfo.getEmail());
                holder.imageView.setImageResource(R.drawable.login_default_avatar);


            return convertView;
    }



    public final class ViewHolder{
   public ImageView imageView;
        public TextView text_name;
        public TextView text_sex;

    }




}
