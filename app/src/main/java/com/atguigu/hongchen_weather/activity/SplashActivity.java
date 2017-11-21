package com.atguigu.hongchen_weather.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.atguigu.hongchen_weather.R;
import com.atguigu.hongchen_weather.util.CacheUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //背景图和状态栏融合
        if(Build.VERSION.SDK_INT>=21)
        {
            View decorView =getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                String weather = CacheUtil.getString(SplashActivity.this,"weather");
                if(weather!=null)
                {
                    //如果有缓存天气数据，直接跳转天气界面
                    Intent intent =new Intent(SplashActivity.this,WeatherActivity.class);
                    startActivity(intent);
                }else
                {
                    //否则，进入城市选择界面
                    Intent intent =new Intent(SplashActivity.this,ChooseCityActivity.class);
                    startActivity(intent);
                }
                //Toast.makeText(SplashActivity.this, "nihao", Toast.LENGTH_SHORT).show();
                finish();

            }
        },1500);
    }
}
