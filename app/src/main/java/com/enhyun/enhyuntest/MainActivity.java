package com.enhyun.enhyuntest;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView value;
    RadioGroup hot_ice_Group;
    RadioButton hot_btn, ice_btn;
    Button OrderNow_btn;
    Button option_btn;
    TextView coffee_name, coffee_price;

    int menu_id;

    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=getIntent();
        coffee_name=(TextView)findViewById(R.id.coffee_name_txt);
        coffee_price=(TextView)findViewById(R.id.coffee_price_txt);
        ImageView coffee_image=(ImageView)findViewById(R.id.coffee_image);

        coffee_name.setText(intent.getStringExtra("menu_name"));
        coffee_price.setText(intent.getStringExtra("menu_price"));
        Glide.with(getApplicationContext()).load(intent.getStringExtra("imagePath")).into(coffee_image);

        value = (TextView) findViewById(R.id.value);
        hot_ice_Group = (RadioGroup) findViewById(R.id.hot_ice_Group);
        hot_btn = (RadioButton) findViewById(R.id.hot_btn);
        ice_btn = (RadioButton) findViewById(R.id.ice_btn);

        // 커스텀 다이얼로그를 호출할 버튼을 정의한다.
        Button size_btn = (Button) findViewById(R.id.size_btn);
        Button cup_btn = (Button) findViewById(R.id.cup_btn);
        option_btn = (Button) findViewById(R.id.option_btn);

        OrderNow_btn=findViewById(R.id.OrderNow_btn);
        OrderNow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Payment_AC.class);
                startActivity(intent);
            }
        });

        // 커스텀 다이얼로그 호출할 클릭 이벤트 리스너 정의
        size_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
                CustomDialog customDialog = new CustomDialog(MainActivity.this);

                // 커스텀 다이얼로그를 호출한다.
                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                customDialog.callFunction(size_btn);
            }
        });


        cup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
                CupChoiceDialog cupChoiceDialog = new CupChoiceDialog(MainActivity.this);

                // 커스텀 다이얼로그를 호출한다.
                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                cupChoiceDialog.cupChoiceFunction(cup_btn);
            }
        });

        option_btn.setOnClickListener(option_btn_Listener);
    }

    View.OnClickListener option_btn_Listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.option_btn:
                    OptionDialog optionDialog=new OptionDialog(MainActivity.this);
                    optionDialog.setOptionDialogListener(new OptionDialog.OptionDialogListener() {
                        @Override
                        public void onPositiveClicked(String add_shot, String add_whipping, String add_caramel, String add_hazelnut, String add_vanilla, String add_bubble) {
                            //가져온 add_shot을 출력할 부분에 붙여넣기 하면 됨
                            option_btn.setText(add_shot+add_whipping);
                        }

                        @Override
                        public void onNegativeClicked() {

                        }
                    });
                    optionDialog.show();
                    break;
            }
        }
    };

}