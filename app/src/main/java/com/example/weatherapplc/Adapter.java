package com.example.weatherapplc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {

    private Context mContext;
    private ArrayList<ExampleItem> mExampleList;

    public Adapter(Context context, ArrayList<ExampleItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.city_card, parent, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        ExampleItem currentItem = mExampleList.get(position);

        Integer cityKey = currentItem.getmCityKey();
        String cityName = currentItem.getmCity();
        double currentTemp = currentItem.getmTemp();
        String currentCondition = currentItem.getmCondition();
        String imageUrl = currentItem.getmImageUrl();
        double longit = currentItem.getmLon();
        double lattit = currentItem.getmLat();

        holder.mTextViewCity.setText(cityName);
        holder.mCurrentTemp.setText("" + currentTemp);
        holder.mCurrentCondition.setText(currentCondition);
        Picasso.get().load("https://openweathermap.org/img/wn/" + imageUrl + "@2x.png").fit().centerInside().into(holder.mCurrentWeatherIcon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("CityID", cityKey);
                intent.putExtra("Name", cityName);
                intent.putExtra("icon", imageUrl);
                intent.putExtra("temp", currentTemp);
                intent.putExtra("Lon", longit);
                intent.putExtra("Lat", lattit);
                mContext.startActivity(intent);

            }
        })

        ;


    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewCity;
        public TextView mCurrentTemp;
        public TextView mCurrentCondition;
        public ImageView mCurrentWeatherIcon;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            mCurrentWeatherIcon = itemView.findViewById(R.id.condition_symbol);
            mTextViewCity = itemView.findViewById(R.id.tv_forecastCity);
            mCurrentTemp = itemView.findViewById(R.id.current_temp);
            mCurrentCondition = itemView.findViewById(R.id.tv_next_condition);


        }

    }
}
