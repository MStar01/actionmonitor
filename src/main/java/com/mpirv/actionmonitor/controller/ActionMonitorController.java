package com.mpirv.actionmonitor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@PropertySource(value = { "classpath:application.properties" })
@Slf4j
public class ActionMonitorController {
    private final Environment environment;

    public ActionMonitorController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/status")
    public String status() {
        log.info("System is UP");
        return "UP";
    }

    @GetMapping("/version")
    public String version() {
        log.info("System version is: " + environment.getProperty("application.version"));
        return environment.getProperty("application.version");
    }
}
