package com.enhyun.enhyuntest;

import android.app.Dialog;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import static kr.co.bootpay.Bootpay.dismiss;
import static kr.co.bootpay.Bootpay.finish;


public class OptionDialog extends Dialog{

    private Button option_ok_btn;
    private Button option_cancel_btn;
    static TextView add_shot, add_whipping, add_caramel, add_hazelnut, add_vanilla, add_bubble;
    private Context context;
    TextView option_result_txt;

    int count_shot;
    int count_whip;
    int count_caramel;
    int count_hazelnut;
    int count_vanilla;
    int count_bubble;

    private OptionDialogListener optionDialogListener;

    public OptionDialog(Context context){
        super(context);
        this.context=context;
    }

    interface OptionDialogListener{
        void onPositiveClicked(String option_result, String add_shot, String add_whipping, String add_caramel, String add_hazelnut, String add_vanilla, String add_bubble);
        void onNegativeClicked();
    }

    public void setOptionDialogListener(OptionDialogListener optionDialogListener){
        this.optionDialogListener=optionDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_dialog);


        option_ok_btn=findViewById(R.id.option_ok_btn);
        option_cancel_btn=findViewById(R.id.option_cancel_btn);
        add_shot=findViewById(R.id.add_shot);
        add_whipping=findViewById(R.id.add_whipping);
        add_caramel=findViewById(R.id.add_caramel);
        add_hazelnut=findViewById(R.id.add_hazelnut);
        add_vanilla=findViewById(R.id.add_vanilla);
        add_bubble=findViewById(R.id.add_bubble);
        option_result_txt=findViewById(R.id.option_intent_txt);


        final ImageButton increment_shot=findViewById(R.id.increment_shot);
        final ImageButton decrement_shot=findViewById(R.id.decrement_shot);
        final ImageButton increment_whip=findViewById(R.id.increment_whip);
        final ImageButton decrement_whip=findViewById(R.id.decrement_whip);
        final ImageButton increment_caramel=findViewById(R.id.increment_caramel);
        final ImageButton decrement_caramel=findViewById(R.id.decrement_caramel);
        final ImageButton increment_hazelnut=findViewById(R.id.increment_hazelnut);
        final ImageButton decrement_hazelnut=findViewById(R.id.decrement_hazelnut);
        final ImageButton increment_vanilla=findViewById(R.id.increment_vanilla);
        final ImageButton decrement_vanilla=findViewById(R.id.decrement_vanilla);
        final ImageButton increment_bubble=findViewById(R.id.increment_bubble);
        final ImageButton decrement_bubble=findViewById(R.id.decrement_bubble);


        //Intent intent1= intent1.getIntent();
        //add_shot.setText(intent1.getStringExtra("add_shot"));


        increment_shot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_shot++;
                add_shot.setText(""+count_shot);
            }
        });

        decrement_shot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_shot<=0) count_shot=0;
                else count_shot--;
                add_shot.setText(""+count_shot);
            }
        });

        increment_whip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_whip++;
                add_whipping.setText(""+count_whip);
            }
        });

        decrement_whip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_whip<=0) count_whip=0;
                else count_whip--;
                add_whipping.setText(""+count_whip);
            }
        });
        increment_caramel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_caramel++;
                add_caramel.setText(""+count_caramel);
            }
        });

        decrement_caramel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_caramel<=0) count_caramel=0;
                else count_caramel--;
                add_caramel.setText(""+count_caramel);
            }
        });

        increment_hazelnut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_hazelnut++;
                add_hazelnut.setText(""+count_hazelnut);
            }
        });

        decrement_hazelnut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_hazelnut<=0) count_hazelnut=0;
                else count_hazelnut--;
                add_hazelnut.setText(""+count_hazelnut);
            }
        });
        increment_vanilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_vanilla++;
                add_vanilla.setText(""+count_vanilla);
            }
        });

        decrement_vanilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_vanilla<=0) count_vanilla=0;
                else count_vanilla--;
                add_vanilla.setText(""+count_vanilla);
            }
        });

        increment_bubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_bubble++;
                add_bubble.setText(""+count_bubble);
            }
        });

        decrement_bubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_bubble<=0) count_bubble=0;
                else count_bubble--;
                add_bubble.setText(""+count_bubble);
            }
        });

        option_ok_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String option_result;
                //앞에 선언한 변수명이랑 뒤에서 사용한 변수 명이 같으면 오류가 나니까 다르게 정해서 사용하는 게 좋아요!
                //예를 들면 String esp랑 add_shot.getText()....에 add_shot이랑 같으면 오류나요!
                String esp, whip,cara,hazel,van,bub;


                esp=add_shot.getText().toString();
                if(!esp.equals("0")){esp=" 에스프레소: "+add_shot.getText().toString() ;}else{esp="";}

                whip=add_whipping.getText().toString();
                if(!whip.equals("0")){whip=" 휘핑크림: "+add_whipping.getText().toString();}else{whip="";}

                cara=add_caramel.getText().toString();
                if(!cara.equals("0")){cara=" 카라멜: "+add_caramel.getText().toString();}else{cara="";}

                hazel=add_hazelnut.getText().toString();
                if(!hazel.equals("0")){hazel=" 헤이즐넛: "+add_hazelnut.getText().toString();}else{hazel="";}

                van=add_vanilla.getText().toString();
                if(!van.equals("0")){van=" 바닐라: "+add_vanilla.getText().toString();}else{van="";}

                bub=add_bubble.getText().toString();
                if(!bub.equals("0")){bub=" 펄: "+add_bubble.getText().toString();}else{bub="";}

                option_result=esp+whip+cara+hazel+van+bub;
                optionDialogListener.onPositiveClicked(option_result, add_shot.getText().toString(),add_whipping.getText().toString(),add_caramel.getText().toString(),add_hazelnut.getText().toString(),add_vanilla.getText().toString(),add_bubble.getText().toString());
                dismiss();
            }
        });

        option_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cancel();

            }
        });
    }



}







