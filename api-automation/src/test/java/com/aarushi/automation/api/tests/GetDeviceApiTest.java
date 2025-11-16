package com.aarushi.automation.api.tests;

import com.aarushi.automation.api.base.BaseApiTest;
import com.aarushi.automation.api.model.response.DeviceResModel;
import com.aarushi.automation.api.services.DeviceApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class GetDeviceApiTest extends BaseApiTest {

    @Test(groups = "api")
    public void getDeviceDetails() throws JsonProcessingException {
        DeviceApi service = new DeviceApi();
        Response response = service.getDevices();
        Assert.assertEquals(response.getStatusCode(), 200);
        List<DeviceResModel> devices = response.as(new TypeRef<List<DeviceResModel>>() {});
        for (DeviceResModel device : devices) {
            if ("Apple AirPods".equals(device.getName())) {
                assertEquals( "3rd",device.getData().getGeneration());
            }
        }
    }

}
