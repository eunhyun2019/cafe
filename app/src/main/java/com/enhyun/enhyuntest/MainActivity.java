package com.enhyun.enhyuntest;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    TextView value;
    RadioGroup hot_ice_Group;
    RadioButton hot_btn, ice_btn;
    Button OrderNow_btn,option_btn,hotlist_btn, basket_btn;
    TextView coffee_name, coffee_price, option_intent_txt;
    LinearLayout option_layout;
    TextView option_txt;
    ImageView coffee_image;
    String menuType, cupSize, cupType, add_shot,add_whipping,add_caramel,add_hazelnut, add_vanilla, add_bubble;
    String user_id="admin";
    int menu_id;
    int count_count=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=getIntent();
        coffee_name=(TextView)findViewById(R.id.coffee_name_txt);
        coffee_price=(TextView)findViewById(R.id.coffee_price_txt);
        coffee_image=findViewById(R.id.coffee_image);

        option_layout=findViewById(R.id.option_layout);

        coffee_name.setText(intent.getStringExtra("menu_name"));
        coffee_price.setText(intent.getStringExtra("menu_price"));
        menu_id=intent.getExtras().getInt("menu_id");
        String menu_image=intent.getStringExtra("imagePath");
        sendImageRequest(menu_image);

        option_txt=findViewById(R.id.option_txt);

        option_txt.setOnClickListener(option_txt_clickListener);

        value = (TextView) findViewById(R.id.value);
        hot_ice_Group = (RadioGroup) findViewById(R.id.hot_ice_Group);
        hot_btn = (RadioButton) findViewById(R.id.hot_btn);
        ice_btn = (RadioButton) findViewById(R.id.ice_btn);

        hot_ice_Group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.hot_btn){
                    menuType=hot_btn.getText().toString();
                }else if(checkedId==R.id.ice_btn){
                    menuType=ice_btn.getText().toString();
                }
            }
        });

        //수량 증가, 감소 버튼
        final ImageButton increment_count=findViewById(R.id.increment_count);
        final ImageButton decrement_count=findViewById(R.id.decrement_count);

        increment_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_count++;
                value.setText(""+count_count);
            }
        });

        decrement_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_count<=1) count_count=1;
                else count_count--;
                value.setText(""+count_count);
            }
        });

        basket_btn=findViewById(R.id.btn_basket);
        basket_btn.setOnClickListener(basketBtnListener);
        // 커스텀 다이얼로그를 호출할 버튼을 정의한다.
        Button size_btn = (Button) findViewById(R.id.size_btn);
        Button cup_btn = (Button) findViewById(R.id.cup_btn);
        option_btn = (Button) findViewById(R.id.option_btn);

        option_intent_txt=(TextView) findViewById(R.id.option_intent_txt);

        hotlist_btn=findViewById(R.id.hotlist_btn);
        hotlist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InsertData task=new InsertData();
                task.execute("http://192.168.161.1/InsertHotList.php",user_id, String.valueOf(menu_id));
                Intent intent=new Intent(MainActivity.this, FavActivity.class);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });

        //프로시저 사용해서 한 번에 두 개의 테이블에 값 넣기
        //t_order 테이블과 t_bucket 테이블 수정 봐야함.. 구조 파악을 못 하겠음...
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

        option_btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                option_intent_txt.setText("선택한 옵션: ");
                switch (v.getId()){
                    case R.id.option_btn:
                        OptionDialog optionDialog=new OptionDialog(MainActivity.this);
                        optionDialog.setOptionDialogListener(new OptionDialog.OptionDialogListener() {
                            @Override
                            public void onPositiveClicked(String option_result, String d_add_shot, String d_add_whipping, String d_add_caramel, String d_add_hazelnut, String d_add_vanilla, String d_add_bubble) {
                                //가져온 add_shot을 출력할 부분에 붙여넣기 하면 됨
                                option_intent_txt.append(option_result);
                                option_layout.setVisibility(View.VISIBLE);
                                add_shot=d_add_shot;
                                add_whipping=d_add_whipping;
                                add_caramel=d_add_caramel;
                                add_hazelnut=d_add_hazelnut;
                                add_bubble=d_add_bubble;
                                add_vanilla=d_add_vanilla;
                            }

                            @Override
                            public void onNegativeClicked() {
                            }
                        });
                        optionDialog.show();
                        break;
                }
                if(option_intent_txt.getText().toString()=="선택한 옵션: "){
                    option_layout.setVisibility(View.GONE);

                }else{
                    option_layout.setVisibility(View.VISIBLE);
                }

            }
        });


    }

    View.OnClickListener basketBtnListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            InsertBasket task=new InsertBasket();
            task.execute("http://192.168.161.1/InsertBasket.php",String.valueOf(menu_id),menuType,cupSize,cupType,add_shot,add_whipping,add_caramel,add_hazelnut,add_vanilla,add_bubble);
            Intent intent=new Intent(MainActivity.this, BasketActivity.class);
            intent.putExtra("menuType",menuType);
            intent.putExtra("cupSize",cupSize);
            intent.putExtra("cupType",cupType);
            intent.putExtra("option_shot",add_shot);
            intent.putExtra("option_whippting",add_whipping);
            intent.putExtra("option_caramel",add_caramel);
            intent.putExtra("option_hazelnut",add_hazelnut);
            intent.putExtra("option_vanilla",add_vanilla);
            intent.putExtra("option_bubble",add_bubble);
            startActivity(intent);
        }
    };

    View.OnClickListener option_txt_clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(option_layout.getVisibility()==View.VISIBLE){
                option_layout.setVisibility(View.GONE);
            }else{
                option_layout.setVisibility(View.VISIBLE);
            }
        }
    };

    public void sendImageRequest(String menu_image){
        ImageLoadTask task=new ImageLoadTask(menu_image,coffee_image);
        task.execute();
    }

    class InsertData extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... params) {

            String user_id=(String)params[1];
            int menu_id=Integer.parseInt(params[2]);

            String serverURL=params[0];
            String postParameters="user_id=" + user_id+"&menu_id="+menu_id;

            try{

                URL url=new URL(serverURL);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream=httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStateCode=httpURLConnection.getResponseCode();

                InputStream inputStream;
                if(responseStateCode==HttpURLConnection.HTTP_OK){
                    inputStream=httpURLConnection.getInputStream();
                }else{
                    inputStream=httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader=new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                StringBuilder sb=new StringBuilder();
                String line=null;

                while((line=bufferedReader.readLine())!=null){
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString();

            }catch (Exception e){
                return new String("Error"+e.getMessage());
            }
        }
    }

    class InsertBasket extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... params) {

            String user_id=(String)params[1];
            int menu_id=Integer.parseInt(params[2]);

            String serverURL=params[0];
            String postParameters="user_id=" + user_id+"&menu_id="+menu_id;

            try{

                URL url=new URL(serverURL);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream=httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStateCode=httpURLConnection.getResponseCode();

                InputStream inputStream;
                if(responseStateCode==HttpURLConnection.HTTP_OK){
                    inputStream=httpURLConnection.getInputStream();
                }else{
                    inputStream=httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader=new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                StringBuilder sb=new StringBuilder();
                String line=null;

                while((line=bufferedReader.readLine())!=null){
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString();

            }catch (Exception e){
                return new String("Error"+e.getMessage());
            }
        }
    }



}