package com.enhyun.enhyuntest;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView value;
    RadioGroup hot_ice_Group;
    RadioButton hot_btn, ice_btn;


    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value = (TextView) findViewById(R.id.value);
        hot_ice_Group = (RadioGroup) findViewById(R.id.hot_ice_Group);
        hot_btn = (RadioButton) findViewById(R.id.hot_btn);
        ice_btn = (RadioButton) findViewById(R.id.ice_btn);

        // 커스텀 다이얼로그를 호출할 버튼을 정의한다.
        Button size_btn = (Button) findViewById(R.id.size_btn);
        Button cup_btn = (Button) findViewById(R.id.cup_btn);
        Button option_btn = (Button) findViewById(R.id.option_btn);


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

        option_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
                OptionDialog optionDialog = new OptionDialog(MainActivity.this);

                // 커스텀 다이얼로그를 호출한다.
                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                optionDialog.optionChoiceFunction(option_btn);
            }
        });

    }


    public void increment (View v) {
        count++;
        value.setText(""+count);



    }
    public void decrement (View v){
        if (count <= 1) count = 1;
        else count--;
        value.setText(""+count);

    }







}