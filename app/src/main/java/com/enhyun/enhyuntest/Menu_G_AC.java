package com.enhyun.enhyuntest;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Menu_G_AC extends TabActivity {
    Button btnCoffee, btnLatte, btnTeaLatte, btnBubble, btnSmoodie, btnAde, btnDessert;
    ListView listFavView;
    ImageView imageView1;
    ListView listMenuView;
    private ListViewAdapter adapter;



    /*ArrayList<아마db이름> listMenu;
    ArrayList<아마db이름> listFav;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_grazie);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabSpecSong = tabHost.newTabSpec("menu").setIndicator("메뉴");
        tabSpecSong.setContent(R.id.tab_menu);
        tabHost.addTab(tabSpecSong);

        TabHost.TabSpec tabSpecArtist = tabHost.newTabSpec("fav").setIndicator("즐겨찾기");
        tabSpecArtist.setContent(R.id.tab_fav);
        tabHost.addTab(tabSpecArtist);

        tabHost.setCurrentTab(0);

        btnCoffee = findViewById(R.id.btn_coffe);
        btnLatte = findViewById(R.id.btn_latte);
        btnTeaLatte = findViewById(R.id.btn_teaLatte);
        btnBubble = findViewById(R.id.btn_bubble);
        btnSmoodie = findViewById(R.id.btn_smoodie);
        btnAde = findViewById(R.id.btn_ade);
        btnDessert = findViewById(R.id.btn_dessert);
        listMenuView = findViewById(R.id.listMenuView);
        listFavView = findViewById(R.id.listFavView);

        adapter=new ListViewAdapter();

        listMenuView.setAdapter(adapter);

        adapter.addItem(R.drawable.menu_image_btn_1);
        adapter.addItem(R.drawable.menu_image_btn_2);
        adapter.addItem(R.drawable.menu_image_btn_3);
        adapter.addItem(R.drawable.menu_image_btn_4);
        adapter.addItem(R.drawable.menu_image_btn_5);
        adapter.addItem(R.drawable.menu_image_btn_6);
        adapter.addItem(R.drawable.menu_image_btn_7);
        adapter.addItem(R.drawable.menu_image_btn_8);
        adapter.addItem(R.drawable.menu_image_btn_9);

        adapter.notifyDataSetChanged();

        listMenuView.setOnItemClickListener(listener);

    }

    AdapterView.OnItemClickListener listener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position){
                case 0:
                    Intent intent1=new Intent(Menu_G_AC.this, MainActivity.class);
                    startActivity(intent1);
                    break;

            }
        }
    };

}