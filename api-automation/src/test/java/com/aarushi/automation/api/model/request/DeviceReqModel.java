package com.aarushi.automation.api.model.request;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceReqModel {

    private String name;
    private DeviceDataReqModel data;

    public String getName() {
        return name;
    }

    public DeviceDataReqModel getData() {
        return data;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setData(DeviceDataReqModel data) {
        this.data = data;
    }
}
