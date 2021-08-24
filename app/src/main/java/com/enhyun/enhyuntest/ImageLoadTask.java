package com.enhyun.enhyuntest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.URL;
import java.util.HashMap;

public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

    String urlStr;
    ImageView coffee_image;
    private static HashMap<String, Bitmap> bitmapHashMap=new HashMap<String, Bitmap>();

    public ImageLoadTask(String urlStr,ImageView coffee_image){
        this.urlStr=urlStr;
        this.coffee_image=coffee_image;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        coffee_image.setImageBitmap(bitmap);
        coffee_image.invalidate();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap bitmap=null;
        try{
            if(bitmapHashMap.containsKey(urlStr)){
                Bitmap oldbitmap= bitmapHashMap.remove(urlStr);
                if(oldbitmap!=null){
                    oldbitmap.recycle();
                }
            }
            URL url=new URL(urlStr);
            bitmap= BitmapFactory.decodeStream(url.openConnection().getInputStream());

            bitmapHashMap.put(urlStr,bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }


}
