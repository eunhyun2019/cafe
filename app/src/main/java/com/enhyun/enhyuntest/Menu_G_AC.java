package com.enhyun.enhyuntest;


import android.app.TabActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Menu_G_AC extends TabActivity {
    Button btnCoffee, btnLatte, btnTeaLatte, btnBubble, btnSmoodie, btnAde, btnDessert;
    ListView listFavView;
    ListView listMenuView;
    MenuAdapter menuAdapter;

    ArrayList<MenuItem> menuItems=new ArrayList<>();



    /*ArrayList<아마db이름> listMenu;
    ArrayList<아마db이름> listFav;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_grazie);

        loadMenuItem();

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabSpecSong = tabHost.newTabSpec("menu").setIndicator("메뉴");
        tabSpecSong.setContent(R.id.tab_menu);
        tabHost.addTab(tabSpecSong);

        TabHost.TabSpec tabSpecArtist = tabHost.newTabSpec("fav").setIndicator("즐겨찾기");
        tabSpecArtist.setContent(R.id.tab_fav);
        tabHost.addTab(tabSpecArtist);

        tabHost.setCurrentTab(0);

        btnCoffee=findViewById(R.id.btn_coffee);
        btnLatte=findViewById(R.id.btn_latte);
        btnTeaLatte=findViewById(R.id.btn_teaLatte);
        btnBubble=findViewById(R.id.btn_bubble);
        btnSmoodie=findViewById(R.id.btn_smoodie);
        btnAde=findViewById(R.id.btn_ade);
        btnDessert=findViewById(R.id.btn_dessert);

        btnCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCategoryItem("COFFEE");
            }
        });
        btnLatte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCategoryItem("LATTE");
            }
        });

        btnTeaLatte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCategoryItem("TEALATTE");
            }
        });
        btnBubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCategoryItem("BUBBLE");
            }
        });
        btnSmoodie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCategoryItem("SMOODIE");
            }
        });
        btnAde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCategoryItem("ADE");
            }
        });
        btnDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCategoryItem("DESSERT");
            }
        });


        //메뉴 리스트 적용, 리니어 레이아웃에 리스트를 붙이는 역할
        listMenuView = findViewById(R.id.listMenuView);
        menuAdapter=new MenuAdapter(getLayoutInflater(),menuItems);
        listMenuView.setAdapter(menuAdapter);


        listFavView = findViewById(R.id.listFavView);

        listMenuView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("menu_id",menuItems.get(position).getMenu_id());
                intent.putExtra("imagePath",menuItems.get(position).getImgPath());
                intent.putExtra("menu_name",menuItems.get(position).getMenu_name());
                intent.putExtra("menu_price",menuItems.get(position).getMenu_price());
                startActivity(intent);

            }
        });



    }



    void loadMenuItem(){
        new Thread(){
            @Override
            public void run(){

                String serverURi="http://192.168.161.1/loadMenu_All.php";

                try{
                    URL url=new URL(serverURi);

                    HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.setUseCaches(false);

                    InputStream is=connection.getInputStream();
                    InputStreamReader isr=new InputStreamReader(is);
                    BufferedReader reader=new BufferedReader(isr);

                    final StringBuffer buffer=new StringBuffer();
                    String line=reader.readLine();
                    while(line != null){
                        buffer.append(line+"\n");
                        line=reader.readLine();
                    }

                    String[] rows=buffer.toString().split(";");

                    menuItems.clear();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            menuAdapter.notifyDataSetChanged();
                        }
                    });

                    for(String row:rows){
                        String[] datas=row.split("&");
                        if(datas.length!=8){
                            continue;
                        }

                        int menu_image_id=Integer.parseInt(datas[0]);
                        int cafe_id=Integer.parseInt(datas[1]);
                        String category=datas[2];
                        String imgPath="http://192.168.161.1/"+datas[3];
                        String date=datas[4];
                        int menu_id=Integer.parseInt(datas[5]);
                        String menu_name=datas[6];
                        String menu_price=datas[7];

                        menuItems.add(new MenuItem(menu_image_id, cafe_id, category, imgPath, date,menu_id, menu_name, menu_price));

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                menuAdapter.notifyDataSetChanged();
                            }
                        });


                    }

                }catch (MalformedURLException e){

                    e.printStackTrace();

                }catch (IOException e){

                    e.printStackTrace();

                }
            }
        }.start();


    }

    void loadCategoryItem(String category){
        new Thread(){
            @Override
            public void run(){

                String serverURi="http://192.168.161.1/loadMenu_image_Category.php";
                String postParameters="category="+category;

                try{
                    URL url=new URL(serverURi);

                    HttpURLConnection connection=(HttpURLConnection)url.openConnection();

                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    connection.setRequestMethod("POST");
                    connection.setDoInput(true);
                    connection.connect();

                    OutputStream outputStream=connection.getOutputStream();
                    outputStream.write(postParameters.getBytes("UTF-8"));
                    outputStream.flush();
                    outputStream.close();

                    int responseStatusCode=connection.getResponseCode();

                    InputStream inputStream;
                    if(responseStatusCode==connection.HTTP_OK){
                        inputStream=connection.getInputStream();
                    }else{
                        inputStream=connection.getErrorStream();
                    }

                    InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"UTF-8");
                    BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                    BufferedReader reader=new BufferedReader(bufferedReader);

                    final StringBuffer buffer=new StringBuffer();
                    String line=reader.readLine();
                    while(line != null){
                        buffer.append(line+"\n");
                        line=reader.readLine();
                    }

                    String[] rows=buffer.toString().split(";");

                    menuItems.clear();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            menuAdapter.notifyDataSetChanged();
                        }
                    });

                    for(String row:rows){
                        String[] datas=row.split("&");
                        if(datas.length!=8){
                            continue;
                        }

                        int menu_image_id=Integer.parseInt(datas[0]);
                        int cafe_id=Integer.parseInt(datas[1]);
                        String category=datas[2];
                        String imgPath="http://192.168.161.1/"+datas[3];
                        String date=datas[4];
                        int menu_id=Integer.parseInt(datas[5]);
                        String menu_name=datas[6];
                        String menu_price=datas[7];

                        menuItems.add(new MenuItem(menu_image_id, cafe_id, category, imgPath, date,menu_id, menu_name, menu_price));

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                menuAdapter.notifyDataSetChanged();
                            }
                        });


                    }


                }catch (MalformedURLException e){

                    e.printStackTrace();

                }catch (IOException e){

                    e.printStackTrace();

                }
            }
        }.start();


    }

}