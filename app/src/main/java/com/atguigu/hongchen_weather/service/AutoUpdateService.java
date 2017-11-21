package com.atguigu.hongchen_weather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.atguigu.hongchen_weather.data.Weather;
import com.atguigu.hongchen_weather.util.CacheUtil;
import com.atguigu.hongchen_weather.util.Constants;
import com.atguigu.hongchen_weather.util.Utility;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/8/29 0029.
 */

public class AutoUpdateService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        double time =intent.getDoubleExtra("time",1);
        Log.e("数据0000",Double.toString(time));

        updateWeather();
        //设置更新时间间隔
        AlarmManager manager =(AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour =(int) time*60*60*1000;  //默认1小时毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime()+anHour;
        Intent i =new Intent(this,AutoUpdateService.class);
        PendingIntent pi =PendingIntent.getService(this,0,i,0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        return super.onStartCommand(intent, flags, startId);
    }


    //自动更新天气信息
    private void updateWeather() {
        String weatherData = CacheUtil.getString(this,"weather");
        if(weatherData!=null)
        {
            Weather weather = Utility.handleWeatherResponse(weatherData);
            String weatherId =weather.getBasic().getId();

            String dataAddress = Constants.BASE_WEATHER_ADDRESS+weatherId+Constants.MY_KEY;
            RequestParams params =new RequestParams(dataAddress);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Weather weather =Utility.handleWeatherResponse(result);
                    if(weather!=null&&"ok".equals(weather.getStatus()))
                    {
                        //缓存数据
                        CacheUtil.putString(AutoUpdateService.this,"weather",result);
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }
    }
}
