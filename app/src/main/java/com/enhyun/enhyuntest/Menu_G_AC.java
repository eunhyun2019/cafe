package com.enhyun.enhyuntest;


import android.app.TabActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

        btnCoffee = findViewById(R.id.btn_coffe);
        btnLatte = findViewById(R.id.btn_latte);
        btnTeaLatte = findViewById(R.id.btn_teaLatte);
        btnBubble = findViewById(R.id.btn_bubble);
        btnSmoodie = findViewById(R.id.btn_smoodie);
        btnAde = findViewById(R.id.btn_ade);
        btnDessert = findViewById(R.id.btn_dessert);

        //메뉴 리스트 적용, 리니어 레이아웃에 리스트를 붙이는 역할
        listMenuView = findViewById(R.id.listMenuView);
        menuAdapter=new MenuAdapter(getLayoutInflater(),menuItems);
        listMenuView.setAdapter(menuAdapter);

        listFavView = findViewById(R.id.listFavView);

    }

    void loadMenuItem(){
        new Thread(){
            @Override
            public void run(){

                String serverURi="http://192.168.161.1/loadDB_test.php";

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
                        if(datas.length!=5){
                            continue;
                        }

                        int menu_image_id=Integer.parseInt(datas[0]);
                        int cafe_id=Integer.parseInt(datas[1]);
                        String category=datas[2];
                        String imgPath="http://192.168.161.1/"+datas[3];
                        String date=datas[4];

                        menuItems.add(new MenuItem(menu_image_id, cafe_id, category, imgPath, date));

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