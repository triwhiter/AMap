package com.ctgu.linlinmap;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.MapView;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;
import com.ctgu.linlinmap.utils.Constants;


public class WeatherActivity extends AppCompatActivity implements WeatherSearch.OnWeatherSearchListener {

    private String mcity;
    private LocalWeatherLive weatherlive;
    private TextView reporttime1;
    private TextView weather;
    private TextView Temperature;
    private TextView wind;
    private TextView humidity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initLayout();
        weatherSearch();
    }

    private void initLayout() {
       reporttime1 = (TextView) findViewById(R.id.reporttime1);
        weather = (TextView) findViewById(R.id.weather);
        Temperature = (TextView) findViewById(R.id.Temperature);
        wind = (TextView) findViewById(R.id.wind);
        humidity = (TextView) findViewById(R.id.humidity);
      
    }
    //活动跳转函数
    public static void startActivity(AppCompatActivity activity, String city){
        Intent intent=new Intent(activity, WeatherActivity.class);
        intent.putExtra("city",city);
        activity.startActivity(intent);

    }

    /**
     * 天气查询
     */
    private void weatherSearch() {
        Intent intent = getIntent();
        mcity = intent.getStringExtra("city");
        //检索参数为城市和天气类型，实况天气为WEATHER_TYPE_LIVE、天气预报为WEATHER_TYPE_FORECAST
        WeatherSearchQuery mquery = new WeatherSearchQuery(mcity, WeatherSearchQuery.WEATHER_TYPE_LIVE);
        WeatherSearch mweathersearch = new WeatherSearch(this);
        mweathersearch.setOnWeatherSearchListener(this);
        mweathersearch.setQuery(mquery);
        mweathersearch.searchWeatherAsyn(); //异步搜索
    }



    @Override
    public void onWeatherForecastSearched(LocalWeatherForecastResult localWeatherForecastResult, int i) {

    }

    /**
     * 实时天气查询回调
     */
    @Override
    public void onWeatherLiveSearched(LocalWeatherLiveResult weatherLiveResult ,int rCode) {
        if (rCode == 1000) {
            if (weatherLiveResult != null&&weatherLiveResult.getLiveResult() != null) {
                weatherlive = weatherLiveResult.getLiveResult();
                reporttime1.setText(weatherlive.getCity()+"于"+weatherlive.getReportTime()+"发布");
                weather.setText(weatherlive.getWeather());

                Temperature.setText(weatherlive.getTemperature());
                wind.setText(weatherlive.getWindDirection()+"风     "+weatherlive.getWindPower()+"级");
                humidity.setText("湿度         "+weatherlive.getHumidity()+"%");
            }else {
                Toast.makeText(WeatherActivity.this,R.string.no_result,
                        Toast.LENGTH_SHORT).show();

            }
        }else {
            Toast.makeText(WeatherActivity.this,rCode,
                    Toast.LENGTH_SHORT).show();

        }
    }
}
