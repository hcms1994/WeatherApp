package com.atguigu.hongchen_weather.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.hongchen_weather.R;
import com.atguigu.hongchen_weather.activity.WeatherActivity;
import com.atguigu.hongchen_weather.data.Weather;
import com.atguigu.hongchen_weather.util.CacheUtil;
import com.atguigu.hongchen_weather.util.Constants;
import com.atguigu.hongchen_weather.util.Utility;
import com.bumptech.glide.Glide;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/9/30 0030.
 */

public class ContentFragment extends Fragment implements View.OnClickListener{

    private WeatherActivity weatherActivity;

    public RelativeLayout title_layout;
    public LinearLayout now_layout;
    public LinearLayout pre_layout;
    public LinearLayout wind_layout;
    public LinearLayout aqi_layout;
    public LinearLayout suggestion_layout;
    public FrameLayout frame_layout;


    private Button title_select_city;
    private Button title_setting;
    TextView title_city;

    ImageView now_weather_condimage;
    TextView now_degree;
    TextView now_weather_info;
    TextView now_weather_maxmin;
    TextView now_weather_quality;

    HorizontalScrollView horizontal_view;

    LinearLayout forecast_layout;
    TextView forecast_item_date;
    TextView forecast_item_info;
    TextView forecast_item_max;
    TextView forecast_item_min;
    ImageView weather_predict_image;

    //风力、风向
    ImageView wind_picture;
    TextView wind_orientation;
    TextView wind_visable;
    TextView wind_level;
    TextView wind_speed;

    TextView aqi_text;
    TextView aqi_range;
    TextView aqi_pm10;
    TextView aqi_pm25;
    TextView aqi_no2;
    TextView aqi_so2;
    TextView aqi_co;
    TextView aqi_o3;


    TextView suggestion_comfort;
    TextView suggestion_wash;
    TextView suggestion_sport;
    TextView suggestion_air;
    TextView suggestion_trav;
    TextView suggestion_flu;
    TextView suggestion_uv;
    ScrollView weather_layout;
    SwipeRefreshLayout swipe_refresh;
    ImageView bing_pic_img;

    String WeatherId;//有缓存数据时，天气的weatherId,用于下拉刷新


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_content,container,false);

        frame_layout =(FrameLayout) view.findViewById(R.id.frame_layout);
        title_layout =(RelativeLayout) view.findViewById(R.id.title_layout);
        now_layout =(LinearLayout) view.findViewById(R.id.now_layout);
        pre_layout =(LinearLayout) view.findViewById(R.id.pre_layout);
        wind_layout =(LinearLayout) view.findViewById(R.id.wind_layout);
        aqi_layout =(LinearLayout) view.findViewById(R.id.aqi_layout);
        suggestion_layout =(LinearLayout) view.findViewById(R.id.suggestion_layout);


        title_select_city =(Button) view.findViewById(R.id.title_select_city);
        title_setting =(Button) view.findViewById(R.id.title_setting);
        title_city =(TextView) view.findViewById(R.id.title_city);

        now_weather_condimage =(ImageView) view.findViewById(R.id.now_weather_condimage);
        now_degree =(TextView) view.findViewById(R.id.now_degree);
        now_weather_info =(TextView) view.findViewById(R.id.now_weather_info);
        now_weather_maxmin =(TextView) view.findViewById(R.id.now_weather_maxmin);
        now_weather_quality =(TextView) view.findViewById(R.id.now_weather_quality);

        forecast_layout =(LinearLayout) view.findViewById(R.id.forecast_layout);
        horizontal_view =(HorizontalScrollView) view.findViewById(R.id.horizontal_view);

        wind_picture =(ImageView) view.findViewById(R.id.wind_picture);
        wind_orientation =(TextView) view.findViewById(R.id.wind_orientation);
        wind_visable =(TextView) view.findViewById(R.id.wind_visable);
        wind_level =(TextView) view.findViewById(R.id.wind_level);
        wind_speed =(TextView) view.findViewById(R.id.wind_speed);

        //设置旋转动画
        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.windanim);
        LinearInterpolator in =new LinearInterpolator();
        animation.setInterpolator(in);
        wind_picture.startAnimation(animation);

        aqi_text =(TextView) view.findViewById(R.id.aqi_text);
        aqi_range =(TextView) view.findViewById(R.id.aqi_range);
        aqi_pm25 =(TextView) view.findViewById(R.id.aqi_pm25);
        aqi_pm10 =(TextView) view.findViewById(R.id.aqi_pm10);
        aqi_co =(TextView) view.findViewById(R.id.aqi_co);
        aqi_no2 =(TextView) view.findViewById(R.id.aqi_no2);
        aqi_o3 =(TextView) view.findViewById(R.id.aqi_o3);
        aqi_so2 =(TextView) view.findViewById(R.id.aqi_so2);
        suggestion_comfort =(TextView) view.findViewById(R.id.suggestion_comfort);
        suggestion_wash =(TextView) view.findViewById(R.id.suggestion_wash);
        suggestion_sport =(TextView) view.findViewById(R.id.suggestion_sport);
        suggestion_air =(TextView) view.findViewById(R.id.suggestion_air);
        suggestion_trav =(TextView) view.findViewById(R.id.suggestion_trav);
        suggestion_flu =(TextView) view.findViewById(R.id.suggestion_flu);
        suggestion_uv =(TextView) view.findViewById(R.id.suggestion_uv);
        weather_layout =(ScrollView) view.findViewById(R.id.weather_layout);
        swipe_refresh =(SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        bing_pic_img =(ImageView) view.findViewById(R.id.bing_pic_img);
        //初始化点击事件
        initClick();

        //设置下拉刷新
        swipe_refresh.setColorSchemeResources(R.color.colorPrimary);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh() {
                //重新请求数据
                Weather weather = Utility.handleWeatherResponse(CacheUtil.getString(getContext(),"weather"));
                String weatherId =weather.getBasic().getId();
                requestWeather(weatherId);
    }
});

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //先判断是否缓存过天气信息，有缓存时直接解析天气数据，没有缓存数据时去服务器查询数据
        String weatherData = CacheUtil.getString(getContext(),"weather");
        if(weatherData!=null)
        {
            //有缓存天气数据
            Weather weather = Utility.handleWeatherResponse(weatherData);
            WeatherId =weather.getBasic().getId();
            showWeatherInformation(weather);
        }//无缓存天气数据
        else
        {
            String weatherId =getActivity().getIntent().getStringExtra("Weather_Id");
            requestWeather(weatherId);
        }
    }


    private void initClick() {
        title_select_city.setOnClickListener(this);
        title_setting.setOnClickListener(this);
    }

    //按钮点击事件
    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.title_select_city:
                if(!weatherActivity.getSlidingMenu().getSecondaryMenu().isShown())
                {
                    weatherActivity.getSlidingMenu().showSecondaryMenu();
                }else
                {
                    weatherActivity.getSlidingMenu().getSecondaryMenu().showContextMenu();
                }
                break;
            case R.id.title_setting:
                weatherActivity.getSlidingMenu().toggle();
                break;
            default:
                break;
        }
    }

    //根据天气id请求城市天气信息
    public void requestWeather(String weatherId) {

        String dataAddress = Constants.BASE_WEATHER_ADDRESS+weatherId+Constants.MY_KEY;

        RequestParams params =new RequestParams(dataAddress);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Weather weather =Utility.handleWeatherResponse(result);
                //如果请求成功，把天气数据缓存，显示天气信息
                if(weather!=null&&"ok".equals(weather.getStatus()))
                {
                    CacheUtil.putString(getContext(),"weather",result);
                    showWeatherInformation(weather);
                    Toast.makeText(weatherActivity,"数据请求成功！",Toast.LENGTH_SHORT).show();

                }
                //关闭下拉刷新
                swipe_refresh.setRefreshing(false);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.e("失败原因：",ex.getMessage());

                Toast.makeText(weatherActivity,"数据请求失败！",Toast.LENGTH_SHORT).show();
                //关闭下拉刷新
                swipe_refresh.setRefreshing(false);
            }
            @Override
            public void onCancelled(CancelledException cex) {

            }
            @Override
            public void onFinished() {

            }
        });
    }

    //处理并展示Weather实体类中的数据
    public void showWeatherInformation(Weather weather) {

        Log.e("成功3：",weather.getNow().getCond().getTxt());
        Log.e("成功3：",weather.toString());
        title_city.setText(weather.getBasic().getCity());

        //缓存天气代码
        CacheUtil.putString(getContext(),"imageCode",weather.getNow().getCond().getCode());
        //加载背景图片
        if(CacheUtil.getInt(getContext(),"back_style")==1)
        {
            weatherActivity.getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
            bing_pic_img.setImageResource(Utility.handleContentImageSource(weather.getNow().getCond().getCode()));
            title_layout.setBackgroundColor(getResources().getColor(R.color.trans_color));
            now_layout.setBackgroundColor(getResources().getColor(R.color.trans_color));
            wind_layout.setBackgroundColor(getResources().getColor(R.color.trans_color));
            aqi_layout.setBackgroundColor(getResources().getColor(R.color.trans_color));
            suggestion_layout.setBackgroundColor(getResources().getColor(R.color.trans_color));
            pre_layout.setBackgroundColor(getResources().getColor(R.color.trans_color));
        }else
        {
            weatherActivity.getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
            bing_pic_img.setImageResource(R.drawable.bing_back);
            frame_layout.setBackgroundColor(getResources().getColor(R.color.title_color));
            title_layout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            now_layout.setBackgroundColor(getResources().getColor(R.color.now_color));
            wind_layout.setBackgroundColor(getResources().getColor(R.color.wind_color));
            aqi_layout.setBackgroundColor(getResources().getColor(R.color.aqi_color));
            suggestion_layout.setBackgroundColor(getResources().getColor(R.color.suggestion_color));
            pre_layout.setBackgroundColor(getResources().getColor(R.color.pre_color));
        }
        now_weather_condimage.setImageResource(Utility.handleImageSource(weather.getNow().getCond().getCode()));
        now_degree.setText(weather.getNow().getTmp()+"℃");
        now_weather_info.setText(weather.getNow().getCond().getTxt());
        now_weather_maxmin.setText(weather.getDaily_forecast().get(0).getTmp().getMin()+"/"+weather.getDaily_forecast().get(0).getTmp().getMax()+"℃");
        now_weather_quality.setText("空气"+weather.getAqi().getCity().getQlty());


        forecast_layout.removeAllViews();
        for(Weather.DailyForecastData dailyForecastData:weather.getDaily_forecast())
        {
            View view =LayoutInflater.from(getContext()).inflate(R.layout.forecast_item,forecast_layout,false);
            forecast_item_date =(TextView) view.findViewById(R.id.forecast_item_date);
            weather_predict_image =(ImageView) view.findViewById(R.id.weather_predict_image);
            forecast_item_info =(TextView) view.findViewById(R.id.forecast_item_info);
            forecast_item_max =(TextView) view.findViewById(R.id.forecast_item_max);
            forecast_item_min =(TextView) view.findViewById(R.id.forecast_item_min);
            forecast_item_date.setText(dailyForecastData.getDate());
            weather_predict_image.setImageResource(Utility.handleImageSource(dailyForecastData.getCond().getCode_d()));
            forecast_item_info.setText(dailyForecastData.getCond().getTxt_d());
            forecast_item_max.setText(dailyForecastData.getTmp().getMax()+"℃");
            forecast_item_min.setText(dailyForecastData.getTmp().getMin()+"℃");
            forecast_layout.addView(view);
        }

        wind_orientation.setText(weather.getNow().getWind().getDir());
        wind_visable.setText(weather.getNow().getVis()+" km");
        wind_level.setText(weather.getNow().getWind().getSc());
        wind_speed.setText(weather.getNow().getWind().getSpd()+" kmph");

        if(weather.getAqi()!=null)
        {
            aqi_text.setText(weather.getAqi().getCity().getAqi());
            aqi_range.setText(weather.getAqi().getCity().getQlty());
            aqi_pm25.setText(weather.getAqi().getCity().getPm25());
            aqi_pm10.setText(weather.getAqi().getCity().getPm10());
            aqi_so2.setText(weather.getAqi().getCity().getSo2());
            aqi_o3.setText(weather.getAqi().getCity().getO3());
            aqi_no2.setText(weather.getAqi().getCity().getNo2());
            aqi_co.setText(weather.getAqi().getCity().getCo());
        }

        String air ="空气质量："+weather.getSuggestion().getAir().getBrf()+"，"
                +weather.getSuggestion().getAir().getTxt();

        String comfort ="舒适度："+weather.getSuggestion().getComf().getBrf()+"，"
                +weather.getSuggestion().getComf().getTxt();

        String carWash ="洗车指数："+weather.getSuggestion().getCw().getBrf()+"，"
                +weather.getSuggestion().getCw().getTxt();

        String sport ="运动指数："+weather.getSuggestion().getSport().getBrf()+"，"
                +weather.getSuggestion().getSport().getTxt();

        String trav ="旅游指数："+weather.getSuggestion().getTrav().getBrf()+"，"
                +weather.getSuggestion().getTrav().getTxt();

        String flu ="感冒指数："+weather.getSuggestion().getTrav().getBrf()+"，"
                +weather.getSuggestion().getFlu().getTxt();

        String uv ="紫外线指数："+weather.getSuggestion().getTrav().getBrf()+"，"
                +weather.getSuggestion().getUv().getTxt();

        suggestion_air.setText(air);
        suggestion_comfort.setText(comfort);
        suggestion_wash.setText(carWash);
        suggestion_sport.setText(sport);
        suggestion_trav.setText(trav);
        suggestion_flu.setText(flu);
        suggestion_uv.setText(uv);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        weatherActivity =(WeatherActivity) context;
    }


}
