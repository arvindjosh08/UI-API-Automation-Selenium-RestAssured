package com.aarushi.automation.ui.utilities;

import com.aarushi.automation.ui.model.LoginModel;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class TestDataProvider {

    @DataProvider(name="loginData")
    public static Object[][] loginData() throws IOException {
        return JsonDataReader.readData("src/test/resources/ui/testdata/logindata.json", LoginModel.class);

    }

}
