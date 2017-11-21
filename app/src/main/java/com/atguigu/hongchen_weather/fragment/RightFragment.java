package com.atguigu.hongchen_weather.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.atguigu.hongchen_weather.HongChen_Weather;
import com.atguigu.hongchen_weather.R;
import com.atguigu.hongchen_weather.activity.WeatherActivity;
import com.atguigu.hongchen_weather.db.SelectedCity;
import com.atguigu.hongchen_weather.util.CacheUtil;

import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static com.atguigu.hongchen_weather.activity.WeatherActivity.CHOOSECITY_TAG;

/**
 * Created by Administrator on 2017/10/1 0001.
 */

public class RightFragment extends Fragment{

    private WeatherActivity weatherActivity;
    private ListView city_listview;
    private ImageButton add_city;
    LinearLayout city_selected_fragment;

    public ArrayAdapter<String> adapter;
    ArrayList<String> city_dataList =new ArrayList<>();

    HongChen_Weather moSahng_weatherApplication =new HongChen_Weather();
    DbManager db = x.getDb(moSahng_weatherApplication.getDaoConfig());

    String deletedCityName=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_right,container,false);
        city_selected_fragment =(LinearLayout) view.findViewById(R.id.city_selected_fragment);
        city_listview =(ListView) view.findViewById(R.id.city_listview);
        city_listview.addFooterView(new View(getContext()));
        add_city =(ImageButton) view.findViewById(R.id.add_city);

        //从数据库SelectedCity表中读取数据
        readSelecedCityTable();
        adapter =new ArrayAdapter<String>(weatherActivity,R.layout.listitem_layout,city_dataList);
        city_listview.setAdapter(adapter);

        //为添加城市按钮设置点击事件
        add_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherActivity.getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.activity_right,new ChooseCityFragment(),CHOOSECITY_TAG)
                        .commit();
            }
        });

        //为listview设置点击事件，跳转到相应天气界面
        city_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //关闭右侧菜单，跳转主页面,显示选中的城市的天气信息
                String weatherName =city_dataList.get(position);
                weatherActivity.getContentFragment().requestWeather(weatherName);
                weatherActivity.getSlidingMenu().toggle();
            }
        });

        //为listview设置长按点击事件，删除当前数据
        city_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder =new AlertDialog.Builder(getContext())
                        .setMessage("确定要删除选中的城市吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(city_dataList.size()>1)
                                {
                                    WhereBuilder b=WhereBuilder.b();
                                    b.and("selectedCityName","=",city_dataList.get(position));
                                    try {
                                        //删除的城市
                                        deletedCityName =city_dataList.get(position);

                                        db.delete(SelectedCity.class,b);
                                        readSelecedCityTable();
                                        adapter.notifyDataSetChanged();

                                        //如果删除的是当前显示的天气信息，那么主页显示列表中第一个城市
                                        if(weatherActivity.getContentFragment().title_city.getText().equals(deletedCityName))
                                        {
                                            weatherActivity.getContentFragment().requestWeather(city_dataList.get(0));
                                        }

                                    } catch (DbException e) {
                                        e.printStackTrace();
                                    }
                                }else
                                {
                                    Toast.makeText(getContext(),"保证至少选择一个城市，请先添加您要选择的城市！",Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.create().show();
                return true;
            }
        });

        return view;
    }

    //从数据库SelectedCity表中读取数据
    private void readSelecedCityTable() {
        try {
            List<SelectedCity> allSelectedCity =db.selector(SelectedCity.class).findAll();
            if(allSelectedCity.size()>0)
            {
                Log.e("数据",Integer.toString(allSelectedCity.size()));
                city_dataList.clear();
                for(SelectedCity selectedCity:allSelectedCity)
                {
                    city_dataList.add(selectedCity.getSelectedCityName());
                }
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        if(CacheUtil.getInt(getContext(),"back_style")==1)
        {
            city_selected_fragment.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }else
        {
            city_selected_fragment.setBackgroundColor(getResources().getColor(R.color.title_color));
        }
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        weatherActivity =(WeatherActivity)context;
    }

}
