package com.atguigu.hongchen_weather.util;

import android.text.TextUtils;
import android.util.Log;

import com.atguigu.hongchen_weather.HongChen_Weather;
import com.atguigu.hongchen_weather.R;
import com.atguigu.hongchen_weather.data.Weather;
import com.atguigu.hongchen_weather.db.City;
import com.atguigu.hongchen_weather.db.County;
import com.atguigu.hongchen_weather.db.Province;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.DbManager;
import org.xutils.x;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public class Utility {

    HongChen_Weather moSahng_weatherApplication =new HongChen_Weather();
    DbManager dbManager = x.getDb(moSahng_weatherApplication.getDaoConfig());

    //解析和处理服务器返回的省级数据
    public boolean handleProvinceResponse(String response)
    {
        if(!TextUtils.isEmpty(response))
        {
            try
            {
                JSONArray allProvinces =new JSONArray(response);
                dbManager.dropTable(Province.class);
                for(int i=0;i<allProvinces.length();i++)
                {
                    JSONObject provinceObject =allProvinces.getJSONObject(i);
                    Province province =new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    dbManager.save(province);
                }
                return true;

            }catch(Exception e)
            {
                Log.e("TAG",e.getMessage());
                e.printStackTrace();
            }
        }
        return false;
    }


    //解析和处理服务器返回的市级数据
    public boolean handleCityResponse(String response,int provinceId)
    {
        if(!TextUtils.isEmpty(response))
        {
            try
            {
                JSONArray allCities =new JSONArray(response);
                dbManager.dropTable(City.class);
                for(int i=0;i<allCities.length();i++)
                {
                    JSONObject cityObject =allCities.getJSONObject(i);
                    City city =new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    dbManager.save(city);
                }
                return true;

            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    //解析和处理服务器返回的县级数据
    public boolean handleCountyResponse(String response,int cityId)
    {
        if(!TextUtils.isEmpty(response))
        {
            try
            {
                JSONArray allCounties =new JSONArray(response);
                dbManager.dropTable(County.class);
                for(int i=0;i<allCounties.length();i++)
                {
                    JSONObject countyObject =allCounties.getJSONObject(i);
                    County county =new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    dbManager.save(county);
                }
                return true;
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    //将返回的JSON数据解析成Weather实体类，用GSON进行解析数据
    public static Weather handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather5");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            Gson gson = new Gson();
            Weather weather = gson.fromJson(weatherContent, Weather.class);
            return weather;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //设置图片资源
    public static int handleImageSource(String imageNum) {
        switch (imageNum) {
            case "100":
                return R.drawable.weather_100;
            case "101":
                return R.drawable.weather_101;
            case "102":
                return R.drawable.weather_102;
            case "103":
                return R.drawable.weather_103;
            case "104":
                return R.drawable.weather_104;
            case "300":
                return R.drawable.weather_300;
            case "301":
                return R.drawable.weather_301;
            case "302":
                return R.drawable.weather_302;
            case "303":
                return R.drawable.weather_303;
            case "304":
                return R.drawable.weather_304;
            case "305":
                return R.drawable.weather_305;
            case "306":
                return R.drawable.weather_306;
            case "307":
                return R.drawable.weather_307;
            case "308":
                return R.drawable.weather_308;
            case "309":
                return R.drawable.weather_309;
            case "310":
                return R.drawable.weather_310;
            case "311":
                return R.drawable.weather_311;
            case "312":
                return R.drawable.weather_312;
            case "313":
                return R.drawable.weather_313;
            case "400":
                return R.drawable.weather_400;
            case "401":
                return R.drawable.weather_401;
            case "402":
                return R.drawable.weather_402;
            case "403":
                return R.drawable.weather_403;
            case "404":
                return R.drawable.weather_404;
            case "405":
                return R.drawable.weather_405;
            case "406":
                return R.drawable.weather_406;
            case "407":
                return R.drawable.weather_407;
            case "500":
                return R.drawable.weather_500;
            case "501":
                return R.drawable.weather_501;
            case "502":
                return R.drawable.weather_502;
            case "503":
                return R.drawable.weather_503;
            case "504":
                return R.drawable.weather_504;
            case "507":
                return R.drawable.weather_507;
            case "508":
                return R.drawable.weather_508;
            default:
                return R.drawable.weather_999;
        }
    }

        //设置图片资源
    public static int handleContentImageSource(String imageNum) {
        switch(imageNum)
        {
            case "100":
                return R.drawable.back1;
            case "101":
                return R.drawable.back2;
            case "102":
                return R.drawable.back3;
            case "103":
                return R.drawable.back4;
            case "104":
                return R.drawable.back5;
            case "300":
                return R.drawable.back8;
            case "301":
                return R.drawable.back14;
            case "302":
                return R.drawable.back14;
            case "303":
                return R.drawable.back13;
            case "304":
                return R.drawable.back13;
            case "305":
                return R.drawable.back7;
            case "306":
                return R.drawable.back9;
            case "307":
                return R.drawable.back11;
            case "308":
                return R.drawable.back7;
            case "309":
                return R.drawable.back6;
            case "310":
                return R.drawable.back11;
            case "311":
                return R.drawable.back12;
            case "312":
                return R.drawable.back12;
            case "313":
                return R.drawable.back10;
            case "400":
                return R.drawable.back16;
            case "401":
                return R.drawable.back16;
            case "402":
                return R.drawable.back17;
            case "403":
                return R.drawable.back17;
            case "404":
                return R.drawable.back15;
            case "405":
                return R.drawable.back15;
            case "406":
                return R.drawable.back18;
            case "407":
                return R.drawable.back18;
            case "500":
                return R.drawable.back20;
            case "501":
                return R.drawable.back20;
            case "502":
                return R.drawable.back19;
            case "503":
                return R.drawable.back21;
            case "504":
                return R.drawable.back21;
            case "507":
                return R.drawable.back22;
            case "508":
                return R.drawable.back22;
            default:
                return R.drawable.back1;
        }
    }

}
