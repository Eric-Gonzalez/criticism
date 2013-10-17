package com.ericrgon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;

import com.ericrgon.criticism.FeedbackActivity;

import java.io.ByteArrayOutputStream;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void snap(View view){
        Intent feedbackIntent = new Intent(this,FeedbackActivity.class);
        feedbackIntent.putExtra("bitmap",compressBitmap(getBitmapFromView(this)));
        startActivity(feedbackIntent);
    }

    private byte[] compressBitmap(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private Bitmap getBitmapFromView(Activity activity){
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        Bitmap bitmap = Bitmap.createBitmap(displayMetrics.widthPixels,displayMetrics.heightPixels, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        activity.getWindow().getDecorView().draw(canvas);
        return bitmap;
    }

}