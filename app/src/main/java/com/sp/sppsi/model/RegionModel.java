package com.sp.sppsi.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 2/14/2018.
 */

public class RegionModel {

    private String name;

    @SerializedName("label_location")
    private LocationModel regionLocation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationModel getRegionLocation() {
        return regionLocation;
    }

    public void setRegionLocation(LocationModel regionLocation) {
        this.regionLocation = regionLocation;
    }
}
