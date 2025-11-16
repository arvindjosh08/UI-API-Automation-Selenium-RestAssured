package com.aarushi.automation.api.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceResModel {
	private DeviceDataResModel data;
	private String name;
	private String id;

	public DeviceDataResModel getData(){
		return data;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}
}
