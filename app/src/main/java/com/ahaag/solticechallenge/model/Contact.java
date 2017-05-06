package com.ahaag.solticechallenge.model;

/**
 * Model class for data representing a 'contact' and user's related information
 */
public class Contact {

    private String mName;
    private boolean mFavorite;
    private String mSmallImageURL;
    private String mLargeImageURL;
    private String mEmail;
    private String mWebsite;
    private Long mBirthdate;
    private Phone mPhone;
    private Address mAddress;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public boolean ismFavorite() {
        return mFavorite;
    }

    public void setmFavorite(boolean mFavorite) {
        this.mFavorite = mFavorite;
    }

    public String getmSmallImageURL() {
        return mSmallImageURL;
    }

    public void setmSmallImageURL(String mSmallImageURL) {
        this.mSmallImageURL = mSmallImageURL;
    }

    public String getmLargeImageURL() {
        return mLargeImageURL;
    }

    public void setmLargeImageURL(String mLargeImageURL) {
        this.mLargeImageURL = mLargeImageURL;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmWebsite() {
        return mWebsite;
    }

    public void setmWebsite(String mWebsite) {
        this.mWebsite = mWebsite;
    }

    public Long getmBirthdate() {
        return mBirthdate;
    }

    public void setmBirthdate(Long mBirthdate) {
        this.mBirthdate = mBirthdate;
    }

    public Phone getmPhone() {
        return mPhone;
    }

    public void setmPhone(Phone mPhone) {
        this.mPhone = mPhone;
    }

    public Address getmAddress() {
        return mAddress;
    }

    public void setmAddress(Address mAddress) {
        this.mAddress = mAddress;
    }
}
