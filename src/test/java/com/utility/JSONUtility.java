package com.utility;

import com.constants.Env;
import com.google.gson.Gson;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONUtility {
    //public static void main(String[] args) throws FileNotFoundException {
    public static Environment readJSON(Env env){


    Gson gson1 = new Gson();
    File jsonFile = new File(System.getProperty("user.dir") + "//config//config.json");
        System.out.println(System.getProperty("user.dir") + "//config//config.json");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(jsonFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Config config = gson1.fromJson(fileReader, Config.class);
    Environment environment = config.getEnvironments().get(env.toString());
    System.out.println(environment.getUrl());
    return environment;
    }
}
