package com.aarushi.automation.api.tests;

import com.aarushi.automation.api.base.BaseApiTest;
import com.aarushi.automation.api.model.request.DeviceDataReqModel;
import com.aarushi.automation.api.model.request.DeviceReqModel;
import com.aarushi.automation.api.model.response.DeviceResModel;
import com.aarushi.automation.api.services.DeviceApi;
import com.aarushi.automation.api.utilities.TestDataProvider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class AddDeviceApiTest extends BaseApiTest {

    @Test(groups = "api", dataProvider = "devices", dataProviderClass = TestDataProvider.class)
    public void AddDeviceDetails(DeviceReqModel deviceReqModel){
        DeviceApi service = new DeviceApi();
        Response response = service.addDevices(deviceReqModel);
        DeviceResModel resObj=response.as(DeviceResModel.class);
        Assert.assertEquals(resObj.getName(),deviceReqModel.getName(),"Device name is not matching");

    }

}
