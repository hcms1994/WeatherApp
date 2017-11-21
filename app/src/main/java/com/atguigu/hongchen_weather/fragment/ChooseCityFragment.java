package com.atguigu.hongchen_weather.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.hongchen_weather.HongChen_Weather;
import com.atguigu.hongchen_weather.R;
import com.atguigu.hongchen_weather.activity.ChooseCityActivity;
import com.atguigu.hongchen_weather.activity.WeatherActivity;
import com.atguigu.hongchen_weather.db.City;
import com.atguigu.hongchen_weather.db.County;
import com.atguigu.hongchen_weather.db.Province;
import com.atguigu.hongchen_weather.db.SelectedCity;
import com.atguigu.hongchen_weather.util.Constants;
import com.atguigu.hongchen_weather.util.Utility;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/1 0001.
 */

public class ChooseCityFragment extends Fragment{

   // private ChooseCityTwoActivity chooseCityActivity;

    HongChen_Weather moSahng_weatherApplication =new HongChen_Weather();
    DbManager dbManager = x.getDb(moSahng_weatherApplication.getDaoConfig());
    Utility utility;

    private int m=0;
    private int n=0;
    private int k=0;

    public static final int LEVEL_PROVINCE = 1;
    public static final int LEVEL_CITY = 2;
    public static final int LEVEL_COUNTY = 3;

    private ProgressDialog progressDialog; //进度条

    private int currentLevel;

    private Province selectedProvince; //选中的省份
    private List<Province> provinceList;

    private City selectedCity; //选中的城市
    private List<City> cityList;

    private County selectedCounty; //选中的县
    private List<County> countyList;

    private TextView title_name;
    private Button back_button;
    private ListView listview;

    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<>();

    boolean resultStatus = false; //数据解析和处理后标志，true为成功，false为失败


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_city, container, false);
        title_name = (TextView) view.findViewById(R.id.title_name);
        back_button = (Button) view.findViewById(R.id.back_button);
        listview = (ListView) view.findViewById(R.id.listview);

        //设置ListView的适配器
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
        listview.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        utility =new Utility();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //当前级别为省份，下一步查询市
                if(currentLevel==LEVEL_PROVINCE)
                {
                    selectedProvince =provinceList.get(position);
                    queryCities();
                }
                //当前级别为市，下一步查询县
                else if(currentLevel ==LEVEL_CITY)
                {
                    selectedCity =cityList.get(position);
                    queryCounties();
                }
                //当前级别为县，下一步跳转到相应天气界面
                else if(currentLevel==LEVEL_COUNTY)
                {
                    String weatherId =countyList.get(position).getWeatherId();
                    String countyName =countyList.get(position).getCountyName();
                    //instanceof判断一个对象是否属于某个类的实例
                    if(getActivity() instanceof ChooseCityActivity)
                    {
                        //先向数据库中SelectedCity表插入数据，保存选择的城市
                        SelectedCity selectedCity =new SelectedCity();
                        selectedCity.setSelectedCityName(countyName);
                        selectedCity.setSelectedCityWeatherId(weatherId);
                        try {
                            dbManager.dropTable(SelectedCity.class);
                            dbManager.save(selectedCity);
                        } catch (DbException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(getActivity(), WeatherActivity.class);
                        intent.putExtra("Weather_Id", weatherId);
                        intent.putExtra("County_Name", countyName);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    //当前choosecityfragment属于weatheractivity时，出栈
                    else if (getActivity() instanceof WeatherActivity) {
                        //先保存数据
                        SelectedCity selectedCity =new SelectedCity();
                        selectedCity.setSelectedCityName(countyName);
                        selectedCity.setSelectedCityWeatherId(weatherId);
                        try {
                            dbManager.save(selectedCity);
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                       getFragmentManager().popBackStack();
                       WeatherActivity weatherActivity =(WeatherActivity) getActivity();
                       weatherActivity.getRightFragment().adapter.notifyDataSetChanged();
                        }
                }
            }
        });
        //地址后退按钮设置监听器
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentLevel==LEVEL_PROVINCE)
                {
                    getFragmentManager().popBackStack();
                }
                if(currentLevel==LEVEL_COUNTY)
                {
                    queryCities();
                }else if(currentLevel==LEVEL_CITY)
                {
                    queryProvinces();
                }
            }
        });
        queryProvinces();
    }



    //查询全国所有到的省，优先从数据库查询，如果没有查询到再去服务器上查询
    private void queryProvinces()
    {
        title_name.setText("中国");
        back_button.setVisibility(View.GONE);
        if(getActivity() instanceof WeatherActivity)
        {
            back_button.setVisibility(View.VISIBLE);
        }
        if(m==0)
        {
            m=1;
            String address = Constants.BASE_ADDRESS;
            queryFromServer(address,"province");
        }
        try
        {
            provinceList =dbManager.selector(Province.class).findAll();
            if(provinceList.size()>0)
            {
                dataList.clear();
                for(Province province:provinceList)
                {
                    dataList.add(province.getProvinceName());
                }
                currentLevel =LEVEL_PROVINCE;
                adapter.notifyDataSetChanged();
                listview.setSelector(0);
            }
            else
            {
                String address = Constants.BASE_ADDRESS;
                queryFromServer(address,"province");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    //查询全国所有的市，优先从数据库查询，如果没有查询到再去服务器上查询
    private void queryCities() {
        title_name.setText(selectedProvince.getProvinceName());
        back_button.setVisibility(View.VISIBLE);
        if(n==0)
        {
            n=1;
            String address =Constants.BASE_ADDRESS+"/"+selectedProvince.getProvinceCode();
            queryFromServer(address,"city");
        }
        try
        {
            cityList =dbManager.selector(City.class).where("provinceId","==",selectedProvince.getId()).findAll();
            if(cityList.size()>0)
            {
                dataList.clear();
                for(City city:cityList)
                {
                    dataList.add(city.getCityName());
                }
                currentLevel =LEVEL_CITY;
                adapter.notifyDataSetChanged();
                listview.setSelector(0);
            }else
            {
                String address =Constants.BASE_ADDRESS+"/"+selectedProvince.getProvinceCode();
                queryFromServer(address,"city");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    //查询全国所有的县，优先从数据库查询，如果没有查询到再去服务器上查询
    private void queryCounties() {
        title_name.setText(selectedCity.getCityName());
        back_button.setVisibility(View.VISIBLE);
        if(k==0)
        {
            k=1;
            String address =Constants.BASE_ADDRESS+"/"+selectedProvince.getProvinceCode()+"/"+selectedCity.getCityCode();
            queryFromServer(address,"county");
        }
        try
        {
            countyList =dbManager.selector(County.class).where("cityId","==",selectedCity.getId()).findAll();
            if(countyList.size()>0)
            {
                dataList.clear();
                for(County county:countyList)
                {
                    dataList.add(county.getCountyName());
                }
                currentLevel =LEVEL_COUNTY;
                adapter.notifyDataSetChanged();
                listview.setSelector(0);
            }else
            {
                String address =Constants.BASE_ADDRESS+"/"+selectedProvince.getProvinceCode()+"/"+selectedCity.getCityCode();
                queryFromServer(address,"county");
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    //根据传入的地址和类型从服务器上查询省市县数据
    private void queryFromServer(String address, final String type) {
        Log.e("数据","到此00");

        //请求数据
        RequestParams params =new RequestParams(address);
        x.http().get(params, new Callback.CommonCallback<String>() {

            //当数据请求成功后
            @Override
            public void onSuccess(String result) {
                Log.e("数据","到此01");
                if("province".equals(type))
                {
                    resultStatus =utility.handleProvinceResponse(result);

                }else if("city".equals(type))
                {
                    resultStatus =utility.handleCityResponse(result,selectedProvince.getId());
                }else if("county".equals(type))
                {
                    resultStatus =utility.handleCountyResponse(result,selectedCity.getId());
                }

                if(resultStatus)
                {
                    if("province".equals(type))
                    {
                        queryProvinces();
                    }else if("city".equals(type))
                    {
                        queryCities();

                    }else if("county".equals(type))
                    {
                        queryCounties();
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.e("异常原因：","isOnCallback:"+isOnCallback+","+ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
