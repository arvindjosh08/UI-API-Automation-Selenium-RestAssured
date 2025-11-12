package com.aarushi.automation.ui.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.SystemProperties;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonDataReader {

public static  <T> Object[][] readData(String jsonPath,Class<T> clazz) throws IOException {

    //Read file

    File filePath = new File(jsonPath);
    ObjectMapper mapper = new ObjectMapper();
    List<T> list = mapper.readValue(filePath, mapper.getTypeFactory().constructCollectionType(List.class, clazz));

    //Convert List into 2-D array object for data provider
    Object[][] dataList=new Object[list.size()][1];
    for(int i=0;i<list.size();i++){
        dataList[i][0]=list.get(i);
    }
    return dataList;

}

}
