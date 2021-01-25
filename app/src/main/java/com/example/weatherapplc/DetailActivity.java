package com.example.weatherapplc;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private RecyclerView dRecyclerView;
    private DailyAdapter dAdapter;
    private ArrayList<DailyItem> dItemList;

    String completedForecastUrl;
    TextView cityNameNew;
    TextView detailCurrentTemp;
    ImageView iconSymbol;
    Double detailCurrentTempNumber;
    Integer detailCityKey;
    Double lon;
    Double lat;

    private RequestQueue dRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        dItemList = new ArrayList<>();

        dRecyclerView = findViewById(R.id.dailyRecycler);

        LinearLayoutManager dLayoutManager = new LinearLayoutManager(this);
        dLayoutManager.setOrientation(RecyclerView.VERTICAL);
        dRecyclerView.setLayoutManager(dLayoutManager);
        dRecyclerView.setHasFixedSize(true);


        iconSymbol=findViewById (R.id.detailIconView);
        cityNameNew = findViewById(R.id.detailCity);
        detailCurrentTemp = findViewById(R.id.detailTemp);

        cityNameNew.setText(getIntent().getStringExtra("Name"));
        detailCurrentTempNumber = getIntent().getDoubleExtra("temp", 0.00);
        int detailCityKey = getIntent().getIntExtra("CityID", 0);
        Picasso.get().load("https://openweathermap.org/img/wn/" + getIntent().getExtras().getString("icon") + "@2x.png").fit().centerInside().into(iconSymbol);
        detailCurrentTemp.setText(detailCurrentTempNumber.toString());
        double lon = getIntent().getDoubleExtra("Lon", 0);
        double lat = getIntent().getDoubleExtra("Lat", 0);

        String lat2 = String.valueOf(lat);
        String lon2 = String.valueOf(lon);

        completedForecastUrl = "https://api.openweathermap.org/data/2.5/onecall?lat=" + lat2 + "&lon=" + lon2 + "&exclude=current,minutely,hourly,alerts&units=imperial&appid=c4175f377020bf6f148d220d0683b8a7";


        dRequestQueue = Volley.newRequestQueue(this);
        ParseJSON();


        return;

        }






    private void ParseJSON(){

        String cityName = getIntent().getStringExtra("Name");

        JsonObjectRequest dailyRequest = new JsonObjectRequest(Request.Method.GET, completedForecastUrl, null,
                response -> {
                    try {
                        JSONArray dailyArray = response.getJSONArray("daily");

                        for (int i = 0; i < dailyArray.length(); i++) {
                            JSONObject dayList = dailyArray.getJSONObject(i);

                            JSONObject dMainObject = dayList.getJSONObject("temp");
                            JSONArray dWeather = dayList.getJSONArray("weather");
                            JSONObject dWeatherObject = dWeather.getJSONObject(0);

                            String dailyConditions = dWeatherObject.getString("main");
                            String dailyicon = dWeatherObject.getString("icon");
                            double dailyTemp = dMainObject.getDouble("day");
                            String decription = dWeatherObject.getString("description");

                            int dTemperature = (int) dailyTemp;

                            dItemList.add(new DailyItem(dailyConditions, dailyicon , decription, dTemperature, cityName));

                        }

                        dAdapter = new DailyAdapter(DetailActivity.this, dItemList);
                        dRecyclerView.setAdapter(dAdapter);
                        dAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }, error -> error.printStackTrace());
        dRequestQueue.add(dailyRequest);
    }

}

