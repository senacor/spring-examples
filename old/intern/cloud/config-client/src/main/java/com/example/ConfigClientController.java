package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fsubasi on 26.01.2016.
 */

@RestController
//@RefreshScope
public class ConfigClientController {

    @Value("${configuration.projectName}")
    private String projectName;

    @RequestMapping("/project-name")
    String projectName() {
        return this.projectName;
    }
}
