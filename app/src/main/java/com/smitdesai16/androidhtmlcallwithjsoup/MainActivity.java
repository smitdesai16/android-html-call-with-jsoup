package com.smitdesai16.androidhtmlcallwithjsoup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private ImageView ivMain;
    private TextView tvMain;
    private ResponseModel responseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivMain = findViewById(R.id.ivMain);
        tvMain = findViewById(R.id.tvMain);

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                ivMain.setImageBitmap(responseModel.bitmap);
                tvMain.setText(responseModel.title);
            }
        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    ContentService contentService = new ContentService();
                    responseModel = contentService.execute().get();
                    handler.sendEmptyMessage(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}