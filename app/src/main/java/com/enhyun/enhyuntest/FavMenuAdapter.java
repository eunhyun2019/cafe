package com.enhyun.enhyuntest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FavMenuAdapter extends BaseAdapter {
    LayoutInflater inflater;
    ArrayList<FavMenuItem> favmenuItems;

    public FavMenuAdapter(LayoutInflater inflater, ArrayList<FavMenuItem> favmenuItems){
        this.inflater=inflater;
        this.favmenuItems=favmenuItems;
    }

    @Override
    public int getCount(){ return favmenuItems.size();}

    @Override
    public Object getItem(int position){ return favmenuItems.get(position);}

    @Override
    public long getItemId(int position){ return position;}

    @Override
    public View getView(int position, View view, ViewGroup viewGroup){

        if(view==null){
            view=inflater.inflate(R.layout.list_fav, viewGroup, false);
        }

        ImageView iv=(ImageView)view.findViewById(R.id.fav_iv);
        TextView coffee_name_txt=(TextView)view.findViewById(R.id.fav_coffee_name_txt);
        TextView coffee_price_txt=(TextView)view.findViewById(R.id.fav_coffee_price_txt);

        FavMenuItem favmenuItem=favmenuItems.get(position);

        coffee_name_txt.setText(favmenuItem.getMenu_name());
        coffee_price_txt.setText(favmenuItem.getMenu_price());
        Glide.with(view).load(favmenuItem.getImgPath()).into(iv);

        return view;
    }
}
