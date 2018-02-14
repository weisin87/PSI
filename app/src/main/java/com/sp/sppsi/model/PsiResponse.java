package com.sp.sppsi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/14/2018.
 */

public class PsiResponse {


    private List<RegionModel> region_metadata = new ArrayList<RegionModel>();

    private List<ItemModel> items = new ArrayList<ItemModel>();

    public List<RegionModel> getRegion_metadata() {
        return region_metadata;
    }

    public void setRegion_metadata(List<RegionModel> region_metadata) {
        this.region_metadata = region_metadata;
    }

    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
    }
}
