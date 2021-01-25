package com.example.weatherapplc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private ArrayList<ExampleItem> mExampleList;

    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        ParseJSON();

        return;
    }




    private void ParseJSON() {
        String url = "https://api.openweathermap.org/data/2.5/group?id=4990729,4393217,5110629,4164138,5188029,5254962,4684888,4347778,4180439,5128581,&units=imperial&appid=c4175f377020bf6f148d220d0683b8a7";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
                try {
                    JSONArray jsonArray = response.getJSONArray("list");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject list = jsonArray.getJSONObject(i);
                        JSONObject mainObject = list.getJSONObject("main");
                        JSONArray weatherArray = list.getJSONArray("weather");
                        JSONObject weatherObject = weatherArray.getJSONObject(0);
                        JSONObject coords = list.getJSONObject("coord");

                        Integer cityKey = list.getInt("id");
                        String cityName = list.getString("name");
                        double lon = coords.getDouble("lon");
                        double lat = coords.getDouble("lat");


                        String currentCondition = weatherObject.getString("main");
                        double currentTemp = mainObject.getDouble("temp");
                        String imageUrl = weatherObject.getString("icon");

                        int temperature = (int) currentTemp;

                        mExampleList.add(new ExampleItem(cityName, temperature, currentCondition, imageUrl, cityKey, lon, lat));


                    }

                    mAdapter = new Adapter(MainActivity.this, mExampleList);
                    mRecyclerView.setAdapter(mAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, Throwable::printStackTrace);

            mRequestQueue.add(request);
    }


}