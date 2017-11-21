package com.atguigu.hongchen_weather.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/8/27 0027.
 */

//缓存数据
public class CacheUtil {

    //获取缓存的文本数据
    public static String getString(Context context,String key)
    {
        SharedPreferences sp =context.getSharedPreferences("weatherData",Context.MODE_PRIVATE);

        return sp.getString(key,null);
    }

    //缓存文本数据
    public static void putString(Context context,String key,String value)
    {
        SharedPreferences sp =context.getSharedPreferences("weatherData",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    };

    public static Boolean getBoolean(Context context,String key)
    {
        SharedPreferences sp =context.getSharedPreferences("weatherData",Context.MODE_PRIVATE);

        return sp.getBoolean(key,false);
    }

    public static void putBoolean(Context context,String key,Boolean value)
    {
        SharedPreferences sp =context.getSharedPreferences("weatherData",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    };

    public static int getInt(Context context,String key)
    {
        SharedPreferences sp =context.getSharedPreferences("weatherData",Context.MODE_PRIVATE);
        return sp.getInt(key,1);
    }

    //缓存文本数据
    public static void putInt(Context context,String key,int value)
    {
        SharedPreferences sp =context.getSharedPreferences("weatherData",Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    };
}
