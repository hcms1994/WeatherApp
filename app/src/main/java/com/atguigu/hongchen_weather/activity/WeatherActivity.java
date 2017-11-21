package com.atguigu.hongchen_weather.activity;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.atguigu.hongchen_weather.R;
import com.atguigu.hongchen_weather.fragment.ChooseCityFragment;
import com.atguigu.hongchen_weather.fragment.ContentFragment;
import com.atguigu.hongchen_weather.fragment.LeftFragment;
import com.atguigu.hongchen_weather.fragment.RightFragment;
import com.atguigu.hongchen_weather.service.AutoUpdateService;
import com.atguigu.hongchen_weather.util.CacheUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import java.util.ArrayList;

public class WeatherActivity extends SlidingFragmentActivity  {
    public ArrayList<String> selectedCityData =new ArrayList<>();;
    String County_Name;
    String Weather_Id;

    public static final String MAIN_CONTENT_TAG ="main_content_tag";
    public static final String LEFTMENU_TAG ="leftmenu_tag";
    public static final String RIGHTMENU_TAG ="rightmenu_tag";
    public static final String CHOOSECITY_TAG ="choosecity_tag";

    private Intent intent;

    private ChooseCityFragment selectedFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        //背景图和状态栏融合
//        if(Build.VERSION.SDK_INT>=21)
//        {
//            View decorView =getWindow().getDecorView();
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }

        //初始化城市选择数据
        initSelectedCityData();

        //1.设置主页面
        setContentView(R.layout.activity_main);
        //2.设置左侧页面
        setBehindContentView(R.layout.activity_left);
        //3.获得菜单
        SlidingMenu slidingMenu =getSlidingMenu();
        //4.设置显示模式：左侧菜单+主页面
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        //设置右侧界面
        slidingMenu.setSecondaryMenu(R.layout.activity_right);
        //5.设置滑动的模式：全屏滑动
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        //设置主页面占据的宽度
        slidingMenu.setBehindOffset(0);
        //初始化碎片
        initFragment();

    }

    private void initSelectedCityData() {
        County_Name= getIntent().getStringExtra("County_Name");
        Weather_Id= getIntent().getStringExtra("Weather_Id");
        selectedCityData.add(County_Name);
    }

    private void initFragment() {
        FragmentManager fm =getSupportFragmentManager();
        FragmentTransaction ft =fm.beginTransaction();
        ft.replace(R.id.activity_main,new ContentFragment(),MAIN_CONTENT_TAG);
        ft.replace(R.id.activity_left,new LeftFragment(),LEFTMENU_TAG);
        ft.replace(R.id.activity_right,new RightFragment(),RIGHTMENU_TAG);
        ft.commit();
    }

    //得到左侧菜单Fragment
    public LeftFragment getLeftMenuFragment() {
        FragmentManager fm =getSupportFragmentManager();
        LeftFragment leftFragment =(LeftFragment) fm.findFragmentByTag(LEFTMENU_TAG);
        return leftFragment;
    }

    //得到正文Fragment
    public ContentFragment getContentFragment() {
        FragmentManager fm =getSupportFragmentManager();
        ContentFragment contentFragment =(ContentFragment) fm.findFragmentByTag(MAIN_CONTENT_TAG);
        return contentFragment;
    }

    //得到右侧Fragment
    public RightFragment getRightFragment() {
        FragmentManager fm =getSupportFragmentManager();
        RightFragment rightFragment =(RightFragment) fm.findFragmentByTag(RIGHTMENU_TAG);
        return rightFragment;
    }

    //得到选择城市Fragment
    public ChooseCityFragment getChooseCityFragment() {
        FragmentManager fm =getSupportFragmentManager();
        ChooseCityFragment chooseCityFragment =(ChooseCityFragment) fm.findFragmentByTag(CHOOSECITY_TAG);
        return chooseCityFragment;
    }


    public ArrayList<String> getArrayListData()
    {
        return selectedCityData;
    }

    //打开服务
    public void start_Service()
    {
        intent =new Intent(WeatherActivity.this, AutoUpdateService.class);
        intent.putExtra("time",getLeftMenuFragment().times[CacheUtil.getInt(this,"time_value")]);
        startService(intent);
    }

    //关闭服务
    public void close_Service()
    {
        intent =new Intent(WeatherActivity.this, AutoUpdateService.class);
        stopService(intent);
    }



    //系统回退健
    @Override
    public void onBackPressed() {
        Log.e("我去","55");
        super.onBackPressed();
    }


}
