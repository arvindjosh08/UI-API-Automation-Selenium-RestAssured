package com.aarushi.automation.api.services;

import com.aarushi.automation.api.base.BaseService;
import com.aarushi.automation.api.model.request.DeviceReqModel;
import com.aarushi.automation.common.utilities.ConfigReader;
import io.restassured.response.Response;

public class DeviceApi extends BaseService {
    public DeviceApi() {
        super();
    }
    public Response getDevices() {
        setBaseUri(ConfigReader.get("domain"));
        setBasePath("/objects");
        return get();
    }

    public Response addDevices(DeviceReqModel deviceReqModel) {
        setBaseUri(ConfigReader.get("domain"));
        setBasePath("/objects");
        setBody(deviceReqModel);
        setContentType("application/json");
        return post();
    }
}
