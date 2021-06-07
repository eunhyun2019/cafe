package com.enhyun.enhyuntest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Store_AC extends AppCompatActivity {

    ImageButton btn_store_g, btn_store_p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        btn_store_g=findViewById(R.id.btn_store_g);
        btn_store_p=findViewById(R.id.btn_store_p);

        btn_store_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Store_AC.this, Menu_G_AC.class);
                startActivity(intent);
            }
        });

        btn_store_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Store_AC.this, Menu_P_AC.class);
                startActivity(intent);
            }
        });
    }
}
