package com.enhyun.enhyuntest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class ListViewAdapter  extends BaseAdapter {
    private ImageView imageView;

    private ArrayList<ListViewItem> listViewItemList=new ArrayList<ListViewItem>();

    public ListViewAdapter(){

    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos=position;
        final Context context=parent.getContext();

        if(convertView ==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.menu_sub,parent,false);
        }
        imageView=convertView.findViewById(R.id.imageView1);

        ListViewItem listViewItem=listViewItemList.get(position);

        imageView.setImageResource(listViewItem.getImage());

        return convertView;
    }

    public void addItem(int image){
        ListViewItem item=new ListViewItem();

        item.setImage(image);

        listViewItemList.add(item);
    }
}
