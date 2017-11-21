package com.atguigu.hongchen_weather.util;

import android.content.Context;

/**
 * Created by Administrator on 2017/7/6 0006.
 */

public class DenistyUtil {

    //根据手机分辨率从dp的单位转到px(像素)
    public static int dip2px(Context context,float dpValue)
    {
        final float scale =context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }

    //根据手机分辨率从px(像素)的单位转到dp
    public static int px2dip(Context context,float pxValue)
    {
        final float scale =context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }

}
