package com.example.configurationwatcherdemo;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@ConfigurationProperties
public class ConnectionProvider {

    private Map<String, String> storageconnections;

    public Map<String, String> getStorageconnections() {
        return storageconnections;
    }

    public void setStorageconnections(Map<String, String> storageconnections) {
        this.storageconnections = storageconnections;
    }

    public String getConnectionById(String organization) {
        return this.storageconnections.get(organization);
    }

}