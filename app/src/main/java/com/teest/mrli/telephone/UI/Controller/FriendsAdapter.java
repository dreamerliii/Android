package com.teest.mrli.telephone.UI.Controller;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.teest.mrli.telephone.R;

import java.util.List;

/**
 * Created by Mr.Li on 2018/11/13.
 */

public class FriendsAdapter extends ArrayAdapter<Friends> {
    private List<Friends> list = null;
    private int resourceId;
    public FriendsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Friends> list) {
        super(context, resource, list);
        this.list = list;
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Friends friends = getItem(position);
        View view = null;
        ViewHolder viewHolder;
        //判断convertView是否为空
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.avatar = (ImageView) view.findViewById(R.id.friend_img);
            viewHolder.name =(TextView) view.findViewById(R.id.friend_name);
            viewHolder.catalog = (TextView) view.findViewById(R.id.friend_word);
            /*viewHolder.name =(TextView) view.findViewById(R.id.friend_name);
            viewHolder.telephone =(TextView) view.findViewById(R.id.friend_name);
            viewHolder.bells =(TextView) view.findViewById(R.id.friend_name);
            viewHolder.birthday =(TextView) view.findViewById(R.id.friend_name);
            viewHolder.remark =(TextView) view.findViewById(R.id.friend_name);*/
            view.setTag(viewHolder);
        }
        else
        {
            view = convertView;
            viewHolder =(ViewHolder) view.getTag();
        }
        //根据position获取首字母作为目录catalog
        String catalog =  list.get(position).getFirstLetter();

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if(position == getPositionForSection(catalog)){
            viewHolder.catalog.setVisibility(View.VISIBLE);
            viewHolder.catalog.setText(friends.getFirstLetter().toUpperCase());
        }else{
            viewHolder.catalog.setVisibility(View.GONE);
        }

        viewHolder.name.setText(this.list.get(position).getName());

        return view;
    }

    //自定义ViewHolder类，属性包含所用到的控件
    private static class ViewHolder{
        public TextView catalog;
        public ImageView avatar;//头像
        public TextView name;//名
/*        public TextView telephone;//电话号
        public TextView bells;//铃声
        public TextView birthday;//生日
        public TextView remark;//备注*/
    }

    /**
     * 获取catalog首次出现位置
     */
    public int getPositionForSection(String catalog) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getFirstLetter();
            if (catalog.equalsIgnoreCase(sortStr)) {//不考虑大小写的比较
                return i;
            }
        }
        return -1;
    }
}
