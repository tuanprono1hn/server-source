package com.tuanla.springserver.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertyValues {
    public String getPropValues(String propertiesName) throws IOException {
        Properties prop = new Properties();
        String propFileName = "activiti_server.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null)
            prop.load(inputStream);
        else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
        return prop.getProperty(propertiesName);
    }

    public String getPropValues(String file, String propertiesName) throws IOException {
        Properties prop = new Properties();
        String propFileName = file + ".properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null)
            prop.load(inputStream);
        else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
        return prop.getProperty(propertiesName);
    }
}
