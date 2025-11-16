package com.aarushi.automation.api.utilities;

import com.aarushi.automation.api.model.request.DeviceReqModel;
import com.aarushi.automation.common.utilities.ConfigReader;
import com.aarushi.automation.common.utilities.JsonDataReader;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class TestDataProvider {

    @DataProvider(name="devices")
    public static Object[][] deviceData() throws IOException {
        return JsonDataReader.readData(ConfigReader.getDataFilePath("DeviceDetails.json"), DeviceReqModel.class);
    }

}
