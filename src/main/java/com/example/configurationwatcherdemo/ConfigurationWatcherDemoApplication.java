package com.example.configurationwatcherdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.ZonedDateTime;
import java.util.Map;

@SpringBootApplication
@EnableScheduling
public class ConfigurationWatcherDemoApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(ConfigurationWatcherDemoApplication.class);

    @Value("${spring.application.name:application}")
    private String appName;
    @Value("${something}")
    private String someThing;

    @Autowired
    private ConnectionProvider connProvider;

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationWatcherDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Application-Name: " + appName);
        LOG.info("some secret value: " + someThing);
    }

    @Scheduled(fixedDelay = 15000L)
    public void logConnections() {
        LOG.info("connections at " + ZonedDateTime.now());
        Map<String, String> storageconnections = connProvider.getStorageconnections();
        if (storageconnections != null && !storageconnections.isEmpty()) {
            storageconnections.entrySet().forEach(e -> LOG.info(e.getKey() + ": " + e.getValue()));
        }
    }
}
