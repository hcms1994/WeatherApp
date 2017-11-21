package com.atguigu.hongchen_weather;

import android.app.Application;

import org.xutils.DbManager;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public class HongChen_Weather extends Application {

    public static ArrayList<String> arrayList =new ArrayList<>();

    private DbManager.DaoConfig daoConfig;
    public  DbManager.DaoConfig getDaoConfig()
    {
        return daoConfig;
    }
    //public DbManager dbManager;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.setDebug(true);
        x.Ext.init(this);


        daoConfig=new DbManager.DaoConfig()
                //数据库名字
                .setDbName("moshang_weather")
                .setDbVersion(1)
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                    }
                });
    }
}
