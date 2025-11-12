package com.aarushi.automation.ui.utilities;

import com.aarushi.automation.ui.model.SignUpModel;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class TestDataProvider {

    @DataProvider(name="signup")
    public static Object[][] signUpData() throws IOException {
        return JsonDataReader.readData(ConfigReader.getDataFilePath("SignUpData.json"), SignUpModel.class);
    }

}
