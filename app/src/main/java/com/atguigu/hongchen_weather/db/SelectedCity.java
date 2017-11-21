package com.atguigu.hongchen_weather.db;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2017/10/2 0002.
 */
@Table(name="selectedcity")
public class SelectedCity {
        @Column(name = "id", isId = true, autoGen = true)
        private int id;
        @Column(name = "selectedCityName")
        private String selectedCityName;

        @Column(name = "selectedCityWeatherId")
        private String selectedCityWeatherId;

        public SelectedCity() {
        }

    public void setId(int id) {
            this.id = id;
        }

    public void setSelectedCityName(String selectedCityName) {
        this.selectedCityName = selectedCityName;
    }

    public void setSelectedCityWeatherId(String selectedCityWeatherId) {
        this.selectedCityWeatherId = selectedCityWeatherId;
    }

    public int getId() {

            return id;
        }

    public String getSelectedCityName() {
        return selectedCityName;
    }

    public String getSelectedCityWeatherId() {
        return selectedCityWeatherId;
    }
}
