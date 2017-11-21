package com.atguigu.hongchen_weather.db;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

@Table(name="city")
public class City {
    @Column(name="id",isId =true,autoGen =true)
    private int id;

    @Column(name="cityName")
    private String cityName;

    @Column(name="cityCode")
    private int cityCode;

    @Column(name="provinceId")
    private int provinceId;

    public City()
    {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getId() {

        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }
}
