package com.ahaag.solticechallenge.model;

/**
 * Model class for data describing user's address
 */
public class Address {

    private String mStreet;
    private String mCity;
    private String mState;
    private String mCountry;
    private int mZip;
    private float mLatitude;
    private float mLongitude;

    public String getmStreet() {
        return mStreet;
    }

    public void setmStreet(String mStreet) {
        this.mStreet = mStreet;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public String getmState() {
        return mState;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    public String getmCountry() {
        return mCountry;
    }

    public void setmCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public int getmZip() {
        return mZip;
    }

    public void setmZip(int mZip) {
        this.mZip = mZip;
    }

    public float getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(float mLatitude) {
        this.mLatitude = mLatitude;
    }

    public float getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(float mLongitude) {
        this.mLongitude = mLongitude;
    }
}
