package com.atguigu.hongchen_weather.data;

import java.util.List;

/**
 * Created by Administrator on 2017/8/27 0027.
 */

public class Weather {

    /**
     * aqi : {"city":{"aqi":"35","co":"0","no2":"27","o3":"52","pm10":"35","pm25":"13","qlty":"优","so2":"2"}}
     * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.90498734","lon":"116.40528870","update":{"loc":"2017-10-03 16:46","utc":"2017-10-03 08:46"}}
     * daily_forecast : [{"astro":{"mr":"16:46","ms":"03:08","sr":"06:12","ss":"17:54"},"cond":{"code_d":"101","code_n":"100","txt_d":"多云","txt_n":"晴"},"date":"2017-10-03","hum":"38","pcpn":"0.0","pop":"0","pres":"1029","tmp":{"max":"20","min":"10"},"uv":"4","vis":"16","wind":{"deg":"211","dir":"西南风","sc":"微风","spd":"7"}},{"astro":{"mr":"17:20","ms":"04:11","sr":"06:13","ss":"17:53"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2017-10-04","hum":"36","pcpn":"0.0","pop":"0","pres":"1026","tmp":{"max":"22","min":"10"},"uv":"5","vis":"20","wind":{"deg":"238","dir":"西南风","sc":"微风","spd":"12"}},{"astro":{"mr":"17:53","ms":"05:16","sr":"06:14","ss":"17:51"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2017-10-05","hum":"41","pcpn":"0.0","pop":"0","pres":"1021","tmp":{"max":"22","min":"12"},"uv":"5","vis":"20","wind":{"deg":"235","dir":"西南风","sc":"微风","spd":"5"}}]
     * hourly_forecast : [{"cond":{"code":"100","txt":"晴"},"date":"2017-10-03 19:00","hum":"45","pop":"0","pres":"1025","tmp":"18","wind":{"deg":"195","dir":"西南风","sc":"微风","spd":"8"}},{"cond":{"code":"100","txt":"晴"},"date":"2017-10-03 22:00","hum":"50","pop":"0","pres":"1029","tmp":"17","wind":{"deg":"176","dir":"南风","sc":"微风","spd":"3"}}]
     * now : {"cond":{"code":"101","txt":"多云"},"fl":"14","hum":"33","pcpn":"0","pres":"1026","tmp":"16","vis":"7","wind":{"deg":"204","dir":"西南风","sc":"微风","spd":"7"}}
     * status : ok
     * suggestion : {"air":{"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"},"comf":{"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"较舒适","txt":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"},"flu":{"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"},"sport":{"brf":"适宜","txt":"天气较好，赶快投身大自然参与户外运动，尽情感受运动的快乐吧。"},"trav":{"brf":"适宜","txt":"天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。"},"uv":{"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}}
     */

    private AqiData aqi;
    private BasicData basic;
    private NowData now;
    private String status;
    private SuggestionData suggestion;
    private List<DailyForecastData> daily_forecast;
    private List<HourlyForecastData> hourly_forecast;

    public AqiData getAqi() {
        return aqi;
    }

    public void setAqi(AqiData aqi) {
        this.aqi = aqi;
    }

    public BasicData getBasic() {
        return basic;
    }

    public void setBasic(BasicData basic) {
        this.basic = basic;
    }

    public NowData getNow() {
        return now;
    }

    public void setNow(NowData now) {
        this.now = now;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SuggestionData getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(SuggestionData suggestion) {
        this.suggestion = suggestion;
    }

    public List<DailyForecastData> getDaily_forecast() {
        return daily_forecast;
    }

    public void setDaily_forecast(List<DailyForecastData> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }

    public List<HourlyForecastData> getHourly_forecast() {
        return hourly_forecast;
    }

    public void setHourly_forecast(List<HourlyForecastData> hourly_forecast) {
        this.hourly_forecast = hourly_forecast;
    }

    public static class AqiData {
        /**
         * city : {"aqi":"35","co":"0","no2":"27","o3":"52","pm10":"35","pm25":"13","qlty":"优","so2":"2"}
         */

        private CityData city;

        public CityData getCity() {
            return city;
        }

        public void setCity(CityData city) {
            this.city = city;
        }

        public static class CityData {
            /**
             * aqi : 35
             * co : 0
             * no2 : 27
             * o3 : 52
             * pm10 : 35
             * pm25 : 13
             * qlty : 优
             * so2 : 2
             */

            private String aqi;
            private String co;
            private String no2;
            private String o3;
            private String pm10;
            private String pm25;
            private String qlty;
            private String so2;

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

            public String getCo() {
                return co;
            }

            public void setCo(String co) {
                this.co = co;
            }

            public String getNo2() {
                return no2;
            }

            public void setNo2(String no2) {
                this.no2 = no2;
            }

            public String getO3() {
                return o3;
            }

            public void setO3(String o3) {
                this.o3 = o3;
            }

            public String getPm10() {
                return pm10;
            }

            public void setPm10(String pm10) {
                this.pm10 = pm10;
            }

            public String getPm25() {
                return pm25;
            }

            public void setPm25(String pm25) {
                this.pm25 = pm25;
            }

            public String getQlty() {
                return qlty;
            }

            public void setQlty(String qlty) {
                this.qlty = qlty;
            }

            public String getSo2() {
                return so2;
            }

            public void setSo2(String so2) {
                this.so2 = so2;
            }
        }
    }

    public static class BasicData {
        /**
         * city : 北京
         * cnty : 中国
         * id : CN101010100
         * lat : 39.90498734
         * lon : 116.40528870
         * update : {"loc":"2017-10-03 16:46","utc":"2017-10-03 08:46"}
         */

        private String city;
        private String cnty;
        private String id;
        private String lat;
        private String lon;
        private UpdateData update;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCnty() {
            return cnty;
        }

        public void setCnty(String cnty) {
            this.cnty = cnty;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public UpdateData getUpdate() {
            return update;
        }

        public void setUpdate(UpdateData update) {
            this.update = update;
        }

        public static class UpdateData {
            /**
             * loc : 2017-10-03 16:46
             * utc : 2017-10-03 08:46
             */

            private String loc;
            private String utc;

            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }
    }

    public static class NowData {
        /**
         * cond : {"code":"101","txt":"多云"}
         * fl : 14
         * hum : 33
         * pcpn : 0
         * pres : 1026
         * tmp : 16
         * vis : 7
         * wind : {"deg":"204","dir":"西南风","sc":"微风","spd":"7"}
         */

        private CondData cond;
        private String fl;
        private String hum;
        private String pcpn;
        private String pres;
        private String tmp;
        private String vis;
        private WindData wind;

        public CondData getCond() {
            return cond;
        }

        public void setCond(CondData cond) {
            this.cond = cond;
        }

        public String getFl() {
            return fl;
        }

        public void setFl(String fl) {
            this.fl = fl;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPcpn() {
            return pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getTmp() {
            return tmp;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public WindData getWind() {
            return wind;
        }

        public void setWind(WindData wind) {
            this.wind = wind;
        }

        public static class CondData {
            /**
             * code : 101
             * txt : 多云
             */

            private String code;
            private String txt;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class WindData {
            /**
             * deg : 204
             * dir : 西南风
             * sc : 微风
             * spd : 7
             */

            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public String getDeg() {
                return deg;
            }

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getSpd() {
                return spd;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }
        }
    }

    public static class SuggestionData {
        /**
         * air : {"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"}
         * comf : {"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"}
         * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
         * drsg : {"brf":"较舒适","txt":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"}
         * flu : {"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"}
         * sport : {"brf":"适宜","txt":"天气较好，赶快投身大自然参与户外运动，尽情感受运动的快乐吧。"}
         * trav : {"brf":"适宜","txt":"天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。"}
         * uv : {"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}
         */

        private AirData air;
        private ComfData comf;
        private CwData cw;
        private DrsgData drsg;
        private FluData flu;
        private SportData sport;
        private TravData trav;
        private UvData uv;

        public AirData getAir() {
            return air;
        }

        public void setAir(AirData air) {
            this.air = air;
        }

        public ComfData getComf() {
            return comf;
        }

        public void setComf(ComfData comf) {
            this.comf = comf;
        }

        public CwData getCw() {
            return cw;
        }

        public void setCw(CwData cw) {
            this.cw = cw;
        }

        public DrsgData getDrsg() {
            return drsg;
        }

        public void setDrsg(DrsgData drsg) {
            this.drsg = drsg;
        }

        public FluData getFlu() {
            return flu;
        }

        public void setFlu(FluData flu) {
            this.flu = flu;
        }

        public SportData getSport() {
            return sport;
        }

        public void setSport(SportData sport) {
            this.sport = sport;
        }

        public TravData getTrav() {
            return trav;
        }

        public void setTrav(TravData trav) {
            this.trav = trav;
        }

        public UvData getUv() {
            return uv;
        }

        public void setUv(UvData uv) {
            this.uv = uv;
        }

        public static class AirData {
            /**
             * brf : 中
             * txt : 气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class ComfData {
            /**
             * brf : 舒适
             * txt : 白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class CwData {
            /**
             * brf : 较适宜
             * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class DrsgData {
            /**
             * brf : 较舒适
             * txt : 建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class FluData {
            /**
             * brf : 少发
             * txt : 各项气象条件适宜，无明显降温过程，发生感冒机率较低。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class SportData {
            /**
             * brf : 适宜
             * txt : 天气较好，赶快投身大自然参与户外运动，尽情感受运动的快乐吧。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class TravData {
            /**
             * brf : 适宜
             * txt : 天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class UvData {
            /**
             * brf : 弱
             * txt : 紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }
    }

    public static class DailyForecastData {
        /**
         * astro : {"mr":"16:46","ms":"03:08","sr":"06:12","ss":"17:54"}
         * cond : {"code_d":"101","code_n":"100","txt_d":"多云","txt_n":"晴"}
         * date : 2017-10-03
         * hum : 38
         * pcpn : 0.0
         * pop : 0
         * pres : 1029
         * tmp : {"max":"20","min":"10"}
         * uv : 4
         * vis : 16
         * wind : {"deg":"211","dir":"西南风","sc":"微风","spd":"7"}
         */

        private AstroData astro;
        private CondDataX cond;
        private String date;
        private String hum;
        private String pcpn;
        private String pop;
        private String pres;
        private TmpData tmp;
        private String uv;
        private String vis;
        private WindDataX wind;

        public AstroData getAstro() {
            return astro;
        }

        public void setAstro(AstroData astro) {
            this.astro = astro;
        }

        public CondDataX getCond() {
            return cond;
        }

        public void setCond(CondDataX cond) {
            this.cond = cond;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPcpn() {
            return pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public TmpData getTmp() {
            return tmp;
        }

        public void setTmp(TmpData tmp) {
            this.tmp = tmp;
        }

        public String getUv() {
            return uv;
        }

        public void setUv(String uv) {
            this.uv = uv;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public WindDataX getWind() {
            return wind;
        }

        public void setWind(WindDataX wind) {
            this.wind = wind;
        }

        public static class AstroData {
            /**
             * mr : 16:46
             * ms : 03:08
             * sr : 06:12
             * ss : 17:54
             */

            private String mr;
            private String ms;
            private String sr;
            private String ss;

            public String getMr() {
                return mr;
            }

            public void setMr(String mr) {
                this.mr = mr;
            }

            public String getMs() {
                return ms;
            }

            public void setMs(String ms) {
                this.ms = ms;
            }

            public String getSr() {
                return sr;
            }

            public void setSr(String sr) {
                this.sr = sr;
            }

            public String getSs() {
                return ss;
            }

            public void setSs(String ss) {
                this.ss = ss;
            }
        }

        public static class CondDataX {
            /**
             * code_d : 101
             * code_n : 100
             * txt_d : 多云
             * txt_n : 晴
             */

            private String code_d;
            private String code_n;
            private String txt_d;
            private String txt_n;

            public String getCode_d() {
                return code_d;
            }

            public void setCode_d(String code_d) {
                this.code_d = code_d;
            }

            public String getCode_n() {
                return code_n;
            }

            public void setCode_n(String code_n) {
                this.code_n = code_n;
            }

            public String getTxt_d() {
                return txt_d;
            }

            public void setTxt_d(String txt_d) {
                this.txt_d = txt_d;
            }

            public String getTxt_n() {
                return txt_n;
            }

            public void setTxt_n(String txt_n) {
                this.txt_n = txt_n;
            }
        }

        public static class TmpData {
            /**
             * max : 20
             * min : 10
             */

            private String max;
            private String min;

            public String getMax() {
                return max;
            }

            public void setMax(String max) {
                this.max = max;
            }

            public String getMin() {
                return min;
            }

            public void setMin(String min) {
                this.min = min;
            }
        }

        public static class WindDataX {
            /**
             * deg : 211
             * dir : 西南风
             * sc : 微风
             * spd : 7
             */

            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public String getDeg() {
                return deg;
            }

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getSpd() {
                return spd;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }
        }
    }

    public static class HourlyForecastData {
        /**
         * cond : {"code":"100","txt":"晴"}
         * date : 2017-10-03 19:00
         * hum : 45
         * pop : 0
         * pres : 1025
         * tmp : 18
         * wind : {"deg":"195","dir":"西南风","sc":"微风","spd":"8"}
         */

        private CondDataXX cond;
        private String date;
        private String hum;
        private String pop;
        private String pres;
        private String tmp;
        private WindDataXX wind;

        public CondDataXX getCond() {
            return cond;
        }

        public void setCond(CondDataXX cond) {
            this.cond = cond;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getTmp() {
            return tmp;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public WindDataXX getWind() {
            return wind;
        }

        public void setWind(WindDataXX wind) {
            this.wind = wind;
        }

        public static class CondDataXX {
            /**
             * code : 100
             * txt : 晴
             */

            private String code;
            private String txt;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class WindDataXX {
            /**
             * deg : 195
             * dir : 西南风
             * sc : 微风
             * spd : 8
             */

            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public String getDeg() {
                return deg;
            }

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getSpd() {
                return spd;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }
        }
    }
}
