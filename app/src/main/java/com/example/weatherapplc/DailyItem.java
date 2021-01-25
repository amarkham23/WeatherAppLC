package com.example.weatherapplc;

public class DailyItem {

    private String mDailyCity;
    private String mDescription;
    private String mIconURL;
    private String mDate;
    private int mDailyTemp;

    public DailyItem(String description, String iconURL, String date, int dailyTemp, String mCity){
        mDescription = description;
        mIconURL = iconURL;
        mDate = date;
        mDailyCity = mCity;
        mDailyTemp = dailyTemp;

    }

    public String getmDailyCity() {
        return mDailyCity;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmIconURL() {
        return mIconURL;
    }

    public String getmDate() {
        return mDate;
    }

    public int getmDailyTemp() {
        return mDailyTemp;
    }

}
