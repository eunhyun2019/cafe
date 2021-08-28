package com.enhyun.enhyuntest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class FavActivity extends AppCompatActivity {

    ListView listfavView;
    FavMenuAdapter favAdpater;

    ArrayList<FavMenuItem> favmenuItems=new ArrayList<>();
    String user_id;
    int menu_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);


        Intent intent=getIntent();

        user_id=intent.getExtras().getString("user_id");
        menu_id=intent.getExtras().getInt("menu_id");

        listfavView=findViewById(R.id.listFavView);
        favAdpater=new FavMenuAdapter(getLayoutInflater(),favmenuItems);
        listfavView.setAdapter(favAdpater);
        favmenuItems.clear();
        loadHotListItem(user_id);

    }
    void loadHotListItem(String user_id){
        new Thread(){
            @Override
            public void run(){

                String serverURi="http://192.168.161.1/GetHotList.php";
                String postParameters="user_id="+user_id;

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

                    favmenuItems.clear();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            favAdpater.notifyDataSetChanged();
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


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                favmenuItems.add(new FavMenuItem(menu_image_id, cafe_id, category, imgPath, date,menu_id, menu_name, menu_price));
                                favAdpater.notifyDataSetChanged();
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