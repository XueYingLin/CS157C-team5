package com.cs157c.subway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:custom.properties")
public class EnvironmentPropertiesConfig {
    @Autowired
    private Environment env;

    public String getConfigValue(String configKey) {
        return env.getProperty(configKey);
    }
}
