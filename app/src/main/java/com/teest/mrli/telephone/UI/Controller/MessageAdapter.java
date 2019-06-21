package com.teest.mrli.telephone.UI.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.teest.mrli.telephone.R;

/**
 * 消息适配器，用来显示消息
 */
public class MessageAdapter extends ArrayAdapter<Message> {
    private int resourceId;

    //使用该构造函数时参数context可以使用getActivity()传入，参数resource为layout中的item_conversation_single
    public MessageAdapter(Context context, int resource, List<Message> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message message = getItem(position); // 获取当前项的Fruit实例
        View view;
        ViewHolder holder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            holder = new ViewHolder();
            holder.iv_headphoto = (ImageView) view.findViewById(R.id.iv_headphoto);
            holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            holder.tv_time = (TextView) view.findViewById(R.id.tv_time);
            holder.tv_state = (TextView) view.findViewById(R.id.tv_state);
            holder.tv_cishu = (TextView) view.findViewById(R.id.tv_cishu);
            view.setTag(holder); // 将ViewHolder存储在View中

        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        holder.tv_name.setText(message.getName());
        holder.tv_time.setText(message.getTime());
        holder.tv_cishu.setText(message.getC());
        holder.tv_state.setText(message.getState());
        /*if (message.getUnread() == "0") {
            holder.tv_unread.setVisibility(View.GONE);
        } else {
            holder.tv_unread.setText(message.getUnread());
        }

        // 发送状态
        holder.iv_avatar.setImageResource(message.getAvatar());*/
        return view;
    }

    private static class ViewHolder {

        ImageView iv_headphoto;//用户头像
        TextView tv_name;   // 和谁的聊天记录
        TextView tv_cishu;//次数
        TextView tv_time;  //消息时间
        TextView tv_state;  //消息内容

    }
}
