package com.example.weatherapplc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.myViewHolder> {

    private Context dContext;
    private ArrayList<DailyItem> dailyList;


    public DailyAdapter(Context d_context, ArrayList<DailyItem> d_list){
        dContext = d_context;
        dailyList = d_list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View dailyView = LayoutInflater.from(dContext).inflate(R.layout.hourly_forecast_card, parent, false);
        return new myViewHolder(dailyView);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        DailyItem currentDailyItem = dailyList.get(position);

        String forecastedCity = currentDailyItem.getmDailyCity();
        String dailySymbol = currentDailyItem.getmIconURL();
        String futureCondition = currentDailyItem.getmDescription();
        double dailyTemp = currentDailyItem.getmDailyTemp();
        String date = currentDailyItem.getmDate();

        holder.tv_forecastedCity.setText(forecastedCity);
        holder.tv_futureCondition.setText(futureCondition);
        holder.tv_date.setText(date);
        holder.tv_dailyTemp.setText(""+ dailyTemp);

        Picasso.get().load("https://openweathermap.org/img/wn/" + dailySymbol + "@2x.png").fit().centerInside().into(holder.iv_dailySymbol);



    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_forecastedCity;
        public TextView tv_dailyTemp;
        public TextView tv_futureCondition;
        public TextView tv_date;
        public ImageView iv_dailySymbol;



        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_dailyTemp = itemView.findViewById(R.id.next_day_temp);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_forecastedCity = itemView.findViewById(R.id.daily_forecastCity);
            tv_futureCondition = itemView.findViewById(R.id.tv_future_condition);
            iv_dailySymbol = itemView.findViewById(R.id.forecast_condition_symbol);

        }
    }
}