package com.example.framgia.iweather.data.model.geocode;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AddressComponent {
    @SerializedName("short_name")
    private String mShortName;
    @SerializedName("types")
    private List<String> mTypes = new ArrayList<String>();

    public String getShortName() {
        return mShortName;
    }

    public void setShortName(String shortName) {
        mShortName = shortName;
    }

    public List<String> getTypes() {
        return mTypes;
    }

    public void setTypes(List<String> types) {
        mTypes = types;
    }
}
