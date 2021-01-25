package com.example.weatherapplc;

import android.os.Parcel;
import android.os.Parcelable;

public class ExampleItem implements Parcelable {
    private String mCity;
    private String mImageUrl;
    private double mTemp;
    private String mCondition;
    private Integer mCityKey;
    private double mLat;
    private double mLon;

    public ExampleItem(String city, double temp, String condition, String imageUrl, Integer cityKey, double longitude, double latitude) {
        mCityKey = cityKey;
        mCity = city;
        mTemp = temp;
        mCondition = condition;
        mImageUrl = imageUrl;
        mLon = longitude;
        mLat = latitude;
    }

    protected ExampleItem(Parcel in) {
        mCity = in.readString();
        mImageUrl = in.readString();
        mTemp = in.readDouble();
        mCondition = in.readString();
        mCityKey = in.readInt();
        mLat = in.readDouble();
        mLon = in.readDouble();
    }

    public static final Creator<ExampleItem> CREATOR = new Creator<ExampleItem>() {
        @Override
        public ExampleItem createFromParcel(Parcel in) {
            return new ExampleItem(in);
        }

        @Override
        public ExampleItem[] newArray(int size) {
            return new ExampleItem[size];
        }
    };

    public String getmCity(){
        return mCity;
    }
    public double getmTemp() {
        return  mTemp;
    }
    public double getmLat() {return mLat;}
    public double getmLon() {return mLon;}
    public String getmCondition(){
        return mCondition;
    }
    public String getmImageUrl() {
        return mImageUrl;
    }
    public Integer getmCityKey() {return mCityKey; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCity);
        dest.writeString(mImageUrl);
        dest.writeDouble(mTemp);
        dest.writeString(mCondition);
        dest.writeInt(mCityKey);
        dest.writeDouble(mLon);
        dest.writeDouble(mLat);

    }
}
