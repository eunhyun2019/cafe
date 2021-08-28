package com.enhyun.enhyuntest;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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
    Button OrderNow_btn,option_btn,hotlist_btn;
    TextView coffee_name, coffee_price, option_intent_txt;
    LinearLayout option_layout;
    TextView option_txt;
    ImageView coffee_image;
    String menuType, cupSize, cupType, add_shot,add_whipping,add_caramel,add_hazelnut, add_vanilla, add_bubble;
    String user_id="admin";
    int menu_id;


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
                            public void onPositiveClicked(String option_result, String d_add_shot, String add_whipping, String add_caramel, String add_hazelnut, String add_vanilla, String add_bubble) {
                                //가져온 add_shot을 출력할 부분에 붙여넣기 하면 됨
                                option_intent_txt.append(option_result);
                                option_layout.setVisibility(View.VISIBLE);
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



}