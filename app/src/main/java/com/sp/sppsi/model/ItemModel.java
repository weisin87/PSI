package com.sp.sppsi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 2/14/2018.
 */

public class ItemModel {

    private String timestamp;
    private String update_timestamp;

    private Map<String, ReadingModel> readings;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUpdate_timestamp() {
        return update_timestamp;
    }

    public void setUpdate_timestamp(String update_timestamp) {
        this.update_timestamp = update_timestamp;
    }

    public Map<String, ReadingModel> getReadings() {
        return readings;
    }

    public void setReadings(Map<String, ReadingModel> readings) {
        this.readings = readings;
    }
}
