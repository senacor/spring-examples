package com.example.e02_value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fsubasi on 13.01.2016.
 * For properties examples a possible order of topics can be
 * 1- @PropertySource annotation and Environment class
 * 2- Spring Boots default property source application.properties/.yml and places where spring boot looks for them (and the order of precendence?)
 + 3- @Value annotation
 * 4- @ConfigurationProperties and @EnableConfigurationProperties annotations, creating a bean from properties data
 * 5- Profiles, @Profile annotation
 * 6- profile-specific .properties/.yml files
 * 7- Multi-profile .yml files
 */
@Controller
@RequestMapping("/value")
public class ValueController {
    @Value("${spring.application.name}")
    String appName;

    @RequestMapping("/appName")
    @ResponseBody
    public String appName(){
        return this.appName;
    }


}
