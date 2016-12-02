package com.example.framgia.iweather.data.model.geocode;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Result {
    @SerializedName("address_components")
    private List<AddressComponent> mAddressComponents = new ArrayList<AddressComponent>();
    @SerializedName("types")
    private List<String> mTypes = new ArrayList<String>();

    public List<AddressComponent> getAddressComponents() {
        return mAddressComponents;
    }

    public void setAddressComponents(
        List<AddressComponent> addressComponents) {
        mAddressComponents = addressComponents;
    }

    public List<String> getTypes() {
        return mTypes;
    }

    public void setTypes(List<String> types) {
        mTypes = types;
    }
}
