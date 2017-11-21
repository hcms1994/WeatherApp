package com.atguigu.hongchen_weather.db;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

@Table(name="province")
public class Province {
    @Column(name="id",isId =true,autoGen =true)
    private int id;

    @Column(name="provinceName")
    private String provinceName;

    @Column(name="provinceCode")
    private int provinceCode;

    public Province()
    {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public int getId() {

        return id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }
}
