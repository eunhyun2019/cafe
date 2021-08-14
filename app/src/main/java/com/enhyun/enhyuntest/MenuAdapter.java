package com.enhyun.enhyuntest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {
    LayoutInflater inflater;
    ArrayList<MenuItem> menuItems;

    public MenuAdapter(LayoutInflater inflater, ArrayList<MenuItem> menuItems){
        this.inflater=inflater;
        this.menuItems=menuItems;
    }

    @Override
    public int getCount(){ return menuItems.size();}

    @Override
    public Object getItem(int position){ return menuItems.get(position);}

    @Override
    public long getItemId(int position){ return position;}

    @Override
    public View getView(int position, View view, ViewGroup viewGroup){

        if(view==null){
            view=inflater.inflate(R.layout.list_item, viewGroup, false);
        }

        ImageView iv=view.findViewById(R.id.iv);

        MenuItem menuItem=menuItems.get(position);

        Glide.with(view).load(menuItem.getImgPath()).into(iv);

        return view;
    }
}
