package com.enhyun.enhyuntest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

public class Menu_G_AC extends TabActivity {
    Button btnCoffee, btnLatte, btnTeaLatte, btnBubble, btnSmoodie, btnAde, btnDessert;
    ListView listMenuView, listFavView;

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

        /*listMenu = new ArrayList<>();
        listFav = new ArrayList<>();*/

        btnCoffee.setOnClickListener(btnCoffeeListener);
        btnLatte.setOnClickListener(btnCoffeeListener);
        btnTeaLatte.setOnClickListener(btnCoffeeListener);
        btnBubble.setOnClickListener(btnCoffeeListener);
        btnSmoodie.setOnClickListener(btnCoffeeListener);
        btnAde.setOnClickListener(btnCoffeeListener);
        btnDessert.setOnClickListener(btnCoffeeListener);
    }

    View.OnClickListener btnCoffeeListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}