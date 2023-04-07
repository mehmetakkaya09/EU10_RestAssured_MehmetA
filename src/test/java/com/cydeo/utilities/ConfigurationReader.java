package com.cydeo.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties props= new Properties();
    static {
        try {

            FileInputStream fis = new FileInputStream("config.properties");
            props.load(fis);
            fis.close();
        } catch (IOException e) {
        }
    }
    public static String getProperty(String key){
        return props.getProperty(key);
    }


}
