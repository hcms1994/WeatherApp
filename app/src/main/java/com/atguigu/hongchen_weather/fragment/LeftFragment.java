package com.atguigu.hongchen_weather.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.hongchen_weather.R;
import com.atguigu.hongchen_weather.activity.WeatherActivity;
import com.atguigu.hongchen_weather.data.Weather;
import com.atguigu.hongchen_weather.service.AutoUpdateService;
import com.atguigu.hongchen_weather.util.CacheUtil;
import com.atguigu.hongchen_weather.util.Utility;

/**
 * Created by Administrator on 2017/9/30 0030.
 */

public class LeftFragment extends Fragment implements View.OnClickListener {

    LinearLayout left_layout;
    RelativeLayout layout_aotoupdate;
    Switch time_switcher;
    RelativeLayout layout_updatetime;
    TextView update_color;
    TextView time;
    RelativeLayout layout_updateimage;
    Switch image_switcher;

    private WeatherActivity weatherActivity;

    public int time_position =1;
    String[] item =new String[]{"30分钟","1小时","2小时","5小时","12小时","24小时"};
    public double[] times =new double[]{0.5,1,2,5,12,24};

    public int BACKGROUND_IMAGE_RESOURCE =0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_left,container,false);
        left_layout =(LinearLayout) view.findViewById(R.id.left_layout);
        layout_aotoupdate =(RelativeLayout) view.findViewById(R.id.layout_aotoupdate);
        time_switcher =(Switch) view.findViewById(R.id.time_switcher);
        layout_updatetime =(RelativeLayout) view.findViewById(R.id.layout_updatetime);
        update_color =(TextView) view.findViewById(R.id.update_color);
        time =(TextView) view.findViewById(R.id.time);
        layout_updateimage =(RelativeLayout) view.findViewById(R.id.layout_updateimage);
        image_switcher =(Switch) view.findViewById(R.id.image_switcher);

        if(!time_switcher.isChecked())
        {
            layout_updatetime.setEnabled(false);
        }

        //设置自动更新switcher点击事件
        time_switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(time_switcher.isChecked())
                {
                    time_switcher.setChecked(false);
                    CacheUtil.putBoolean(getContext(),"time_switch",false);
                }else
                {
                    time_switcher.setChecked(true);
                    CacheUtil.putBoolean(getContext(),"time_switch",true);
                }
            }
        });

        //设置背景图片switcher点击事件
        image_switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image_switcher.isChecked())
                {
                    image_switcher.setChecked(false);
                    CacheUtil.putBoolean(getContext(),"image_switch",false);
                }else
                {
                    image_switcher.setChecked(true);
                    CacheUtil.putBoolean(getContext(),"image_switch",true);
                }
            }
        });

        //设置自动更新时间开关监听器
        time_switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    layout_updatetime.setEnabled(true);
                    update_color.setTextColor(Color.WHITE);
                    time.setTextColor(Color.WHITE);
                    weatherActivity.start_Service();

                }else
                {
                    layout_updatetime.setEnabled(false);
                    update_color.setTextColor(Color.GRAY);
                    time.setTextColor(Color.GRAY);
                    weatherActivity.close_Service();
                }
            }
        });

        //设置背景图片开关监听器
        image_switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    CacheUtil.putInt(getContext(),"back_style",1);
                    //weatherActivity.getContentFragment().requestBackgroundPicture();
                    BACKGROUND_IMAGE_RESOURCE =1;
                   // weatherActivity.getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
           //         left_layout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                    weatherActivity.getRightFragment().city_selected_fragment.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    weatherActivity.getContentFragment().title_layout.setBackgroundColor(getResources().getColor(R.color.trans_color));
                    weatherActivity.getContentFragment().now_layout.setBackgroundColor(getResources().getColor(R.color.trans_color));
                    weatherActivity.getContentFragment().wind_layout.setBackgroundColor(getResources().getColor(R.color.trans_color));
                    weatherActivity.getContentFragment().aqi_layout.setBackgroundColor(getResources().getColor(R.color.trans_color));
                    weatherActivity.getContentFragment().suggestion_layout.setBackgroundColor(getResources().getColor(R.color.trans_color));
                    weatherActivity.getContentFragment().pre_layout.setBackgroundColor(getResources().getColor(R.color.trans_color));
                    weatherActivity.getContentFragment().bing_pic_img.setImageResource(Utility.handleContentImageSource(CacheUtil.getString(getContext(),"imageCode")));

                }else
                {
                    CacheUtil.putInt(getContext(),"back_style",0);
                    BACKGROUND_IMAGE_RESOURCE =0;
                   // weatherActivity.getWindow().setStatusBarColor(getResources().getColor(R.color.title_color));
                  //  left_layout.setBackgroundColor(getResources().getColor(R.color.title_color));
  //                  weatherActivity.getRightFragment().city_selected_fragment.setBackgroundColor(getResources().getColor(R.color.title_color));
                    weatherActivity.getContentFragment().bing_pic_img.setImageResource(R.drawable.bing_back);
                    weatherActivity.getContentFragment().frame_layout.setBackgroundColor(getResources().getColor(R.color.title_color));
                    weatherActivity.getContentFragment().title_layout.setBackgroundColor(getResources().getColor(R.color.title_color));
                    weatherActivity.getContentFragment().now_layout.setBackgroundColor(getResources().getColor(R.color.now_color));
                    weatherActivity.getContentFragment().wind_layout.setBackgroundColor(getResources().getColor(R.color.wind_color));
                    weatherActivity.getContentFragment().aqi_layout.setBackgroundColor(getResources().getColor(R.color.aqi_color));
                    weatherActivity.getContentFragment().suggestion_layout.setBackgroundColor(getResources().getColor(R.color.suggestion_color));
                    weatherActivity.getContentFragment().pre_layout.setBackgroundColor(getResources().getColor(R.color.pre_color));
                }
            }
        });

        //初始化点击事件
        initLeftOnClick();

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        time_switcher.setChecked(CacheUtil.getBoolean(getContext(),"time_switch"));
        if(CacheUtil.getString(getContext(),"update_time")==null)
        {
            time.setText("1小时");
        }else
        {
            time.setText(CacheUtil.getString(getContext(),"update_time"));
        }
        image_switcher.setChecked(CacheUtil.getBoolean(getContext(),"image_switch"));
        layout_updatetime.setEnabled(CacheUtil.getBoolean(getContext(),"time_switch_Clicked"));
    }

    private void initLeftOnClick() {

        layout_aotoupdate.setOnClickListener(this);
        layout_updatetime.setOnClickListener(this);
        layout_updateimage.setOnClickListener(this);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        weatherActivity =(WeatherActivity)context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.layout_aotoupdate:
                if(time_switcher.isChecked())
                {
                    time_switcher.setChecked(false);
                    CacheUtil.putBoolean(getContext(),"time_switch",false);

                }else
                {
                    time_switcher.setChecked(true);
                    CacheUtil.putBoolean(getContext(),"time_switch",true);
                }
                break;

            case R.id.layout_updatetime:
                if(layout_updatetime.isEnabled())
                {
                    AlertDialog.Builder builder =new AlertDialog.Builder(getContext())
                            .setTitle("更新间隔")
                            .setSingleChoiceItems(R.array.items, 1, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    time_position =which;
                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    CacheUtil.putInt(getContext(),"time_value",time_position);
                                    weatherActivity.start_Service();
                                    time.setText(item[time_position]);
                                    CacheUtil.putString(getContext(),"update_time",item[time_position]);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    builder.create().show();
                }else
                {
                    Toast.makeText(weatherActivity,"需要允许后台自动更新",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.layout_updateimage:
                if(image_switcher.isChecked())
                {
                    image_switcher.setChecked(false);
                    CacheUtil.putBoolean(getContext(),"image_switch",false);
                }else
                {
                    image_switcher.setChecked(true);
                    CacheUtil.putBoolean(getContext(),"image_switch",true);
                }
                break;

            default:
                break;
        }
    }
}
