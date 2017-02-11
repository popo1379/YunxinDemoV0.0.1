package com.palmintelligence.administrator.yunxindemov001.chars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.QueryDirectionEnum;
import com.palmintelligence.administrator.yunxindemov001.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/24 0024.
 */
public class MsgAdapter extends ArrayAdapter<IMMessage> {
    private int resourceId;
    private List<IMMessage> msgInfos=new ArrayList<IMMessage>();

    public MsgAdapter(Context context, int textViewsourceId, List<IMMessage> msgInfos){
        super(context,textViewsourceId,msgInfos);
        resourceId=textViewsourceId;
        this.msgInfos=msgInfos;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        IMMessage msgInfo=msgInfos.get(position);
        View view;
        ViewHolder viewHolder;
        if (convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder =new ViewHolder();
            viewHolder.leftLayout=(LinearLayout)view.findViewById(R.id.char_left);
            viewHolder.rightLayout=(LinearLayout)view.findViewById(R.id.char_right);

            viewHolder.leftMsg=(TextView)view.findViewById(R.id.char_left_msg);
            viewHolder.rightMsg=(TextView)view.findViewById(R.id.char_right_msg);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        if (msgInfo.getDirect()== MsgDirectionEnum.In){
            //接收消息。显示左边，隐藏右边
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.leftMsg.setText(msgInfo.getContent());
        }else if (msgInfo.getDirect()==MsgDirectionEnum.Out){
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightMsg.setText(msgInfo.getContent());
        }
        return view;

    }
    class ViewHolder{
        LinearLayout leftLayout,rightLayout;
        TextView rightMsg,leftMsg;
    }




}