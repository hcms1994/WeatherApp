package com.atguigu.hongchen_weather.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.atguigu.hongchen_weather.R;
import com.atguigu.hongchen_weather.util.CacheUtil;

public class ChooseCityActivity extends FragmentActivity {

    public static int FLAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //状态栏和背景融合
        if(Build.VERSION.SDK_INT>=21)
        {
            View decorView =getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_choose_city);

        String weather = CacheUtil.getString(ChooseCityActivity.this,"weather");
        if(weather!=null)
        {
            //如果有缓存天气数据，直接跳转天气界面
            Intent intent =new Intent(ChooseCityActivity.this,WeatherActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
