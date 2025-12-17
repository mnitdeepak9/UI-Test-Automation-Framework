package com.utility;

import com.constants.Env;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    public static String readProperty(Env env, String propertyName) {
        System.out.println(System.getProperty("user.dir") + "//config//QA.properties");
        File propFile = new File(System.getProperty("user.dir") + "//config//"+env+".properties");


        Properties properties = new Properties();
        try {
            FileReader fileReader = new FileReader(propFile);
            properties.load(fileReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String url = properties.getProperty(propertyName);
        System.out.println(url);
        return url;
    }
}
