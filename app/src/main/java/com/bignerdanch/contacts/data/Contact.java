package com.bignerdanch.contacts.data;

import android.util.Log;

import java.util.UUID;

public class Contact {
    private UUID mContactId;
    private String mFirstName;
    private String mLastName;
    private String mMidleName;
    private String mTelNumber;
    private String mPhotoUri;
    private String mAddress;


    public Contact() {
        mContactId = UUID.randomUUID();
    }

    public Contact(UUID contactId) {
        mContactId = contactId;
    }

    public UUID getContactId() {
        Log.i("MY_TAG", "хватаем айдишник");
        return mContactId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getMidleName() {
        return mMidleName;
    }

    public void setMidleName(String companyName) {
        mMidleName= companyName;
    }

    public String getTelNumber() {
        return mTelNumber;
    }

    public void setTelNumber(String telNumber) {
        mTelNumber = telNumber;
    }

    public String getPhotoUri() {
        return mPhotoUri;
    }

    public void setPhotoUri(String photoUri) {
        mPhotoUri = photoUri;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }
}

