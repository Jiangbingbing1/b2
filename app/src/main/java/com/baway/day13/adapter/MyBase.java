package com.baway.day13.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.day13.R;
import com.baway.day13.bean.UserBean;
import com.bumptech.glide.Glide;

import java.util.List;

public class MyBase extends BaseAdapter {
    private List<UserBean.Result.Mlss.Mydata> list;
    private Context context;

    public MyBase(List<UserBean.Result.Mlss.Mydata> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
              viewHolder=new ViewHolder();
              convertView=View.inflate(context, R.layout.one,null);
              viewHolder.name=convertView.findViewById(R.id.name);
              viewHolder.img=convertView.findViewById(R.id.img);
              convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).getCommodityName());
        Glide.with(context).load(list.get(position).getMasterPic()).into(viewHolder.img);
        return convertView;
    }
    class ViewHolder{
           TextView name;
           ImageView img;
    }
}
