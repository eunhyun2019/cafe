package com.enhyun.enhyuntest;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Join_terms_AC extends AppCompatActivity {
    CheckBox checkBox1, checkBox2, checkBox3;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_terms);

        checkBox1=findViewById(R.id.checkBox);
        checkBox2=findViewById(R.id.checkBox2);
        checkBox3=findViewById(R.id.checkBox3);
        button3=findViewById(R.id.button3);

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBox2.setChecked(true);
                    checkBox3.setChecked(true);
                }else{
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox2.isChecked()&&checkBox3.isChecked()){
                    checkBox1.setChecked(true);
                    Toast.makeText(getApplicationContext(), "회원정보 입력 페이지로 이동합니다.", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(Join_terms_AC.this, Join_AC.class );
                    startActivity(intent1);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"약관에 동의해주세요.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}