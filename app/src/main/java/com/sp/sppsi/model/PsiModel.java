package com.sp.sppsi.model;

/**
 * Created by User on 2/14/2018.
 */

public class PsiModel {

    private String regionName;
    private String psiNumber;

    private LocationModel location;

    public PsiModel(String name, String psiNumber, LocationModel location ){

        this.regionName = name;
        this.psiNumber = psiNumber;
        this.location = location;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getPsiNumber() {
        return psiNumber;
    }

    public void setPsiNumber(String psiNumber) {
        this.psiNumber = psiNumber;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }
}
