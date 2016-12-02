package com.example.framgia.iweather.data.model.geocode;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duong on 11/28/2016.
 */
public class GeoCode {
    @SerializedName("results")
    private List<Result> mResults = new ArrayList<Result>();

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }
}
