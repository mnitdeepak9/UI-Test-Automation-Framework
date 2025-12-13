package com.ui.pojo;

import java.util.Map;

public class Config {
    Map<String,Environment> environments ;


    public Map<String, Environment> getEnvironments() {
        return this.environments;
    }
    public void setEnvironments(Map<String, Environment> environment) {
        this.environments = environment;
    }
}
