package demo.intern.properties.e01_basics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fsubasi on 13.01.2016.
 * For properties examples a possible order of topics can be
 * 1- @PropertySource annotation
 * 2- @Value annotation
 * 3- Environment class
 * 4- Spring Boots default property source application.properties/.yml and places where spring boot looks for them (and the order of precendence?)
 * 5- @ConfigurationProperties and @EnableConfigurationProperties annotations, creating a bean from properties data
 * 6- Profiles, @Profile annotation
 * 7- profile-specific .properties/.yml files
 * 8- Multi-profile .yml files
 */
@Controller
@ConfigurationProperties("webmaster") // set methods in this controller will be initialized with external properties starting with 'webmaster'
@RequestMapping("/properties")
public class PropertyController {

    private String webmasterName;

    // This is a @ConfigurationProperties bean initialized with properties in application.properties
    @Autowired
    private Person person;

    @Value("${spring.application.name}")
    String appName;

    // this value is set in application.properties file
    // the property's name is the @ConfigurationProperties value above + "." + property's name
    // which is webmaster.name in this case
    public void setName(String name){
        this.webmasterName = name;
    }

    @RequestMapping("webmaster/name")
    @ResponseBody
    public String webmasterName(){
        return this.webmasterName;
    }

    @RequestMapping("/webmaster")
    @ResponseBody
    public Person webmaster(){
        return person;
    }

    @RequestMapping("/appName")
    @ResponseBody
    public String appName(){
        return this.appName;
    }


}
