package com.aarushi.automation.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceDataResModel {

    private String generation;
    private String price;
    private String capacity;

    private String screenSize;   // API sometimes returns string values
    private String description;

    @JsonProperty("Color")
    private String color;

    @JsonProperty("Strap Colour")
    private String strapColour;

    @JsonProperty("Case Size")
    private String caseSize;

    private int year;

    @JsonProperty("CPU model")
    private String cpuModel;

    @JsonProperty("Hard disk size")
    private String hardDiskSize;

    @JsonProperty("capacity GB")
    private int capacityGB;

    private String createdAt;

    // ---------- GETTERS ----------

    public String getGeneration() {
        return generation;
    }

    public String getPrice() {
        return price;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public String getDescription() {
        return description;
    }

    public String getColor() {
        return color;
    }

    public String getStrapColour() {
        return strapColour;
    }

    public String getCaseSize() {
        return caseSize;
    }

    public int getYear() {
        return year;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public String getHardDiskSize() {
        return hardDiskSize;
    }

    public int getCapacityGB() {
        return capacityGB;
    }

}