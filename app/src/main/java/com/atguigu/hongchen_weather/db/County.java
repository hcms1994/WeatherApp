package com.atguigu.hongchen_weather.db;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

@Table(name="county")
public class County {
    @Column(name="id",isId =true,autoGen =true)
    private int id;

    @Column(name="countyName")
    private String countyName;

    @Column(name="weatherId")
    private String weatherId;

    @Column(name="cityId")
    private int cityId;

    public County()
    {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getId() {

        return id;
    }

    public String getCountyName() {
        return countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public int getCityId() {
        return cityId;
    }
}
