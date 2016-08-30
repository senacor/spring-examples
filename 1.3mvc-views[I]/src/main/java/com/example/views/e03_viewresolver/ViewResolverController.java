package com.example.views.e03_viewresolver;

import com.example.views.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by fsubasi on 18.01.2016.
 * We see the configured view resolvers for thymeleaf, json xml, pdf and xls in action
 * This controller serves html, json, xml, pdf and xls representation of a User object
 * Since we enabled path extensions and path parameter for content negotiation
 * to get html, url should be .../views/viewResolver/user.html or .../views/viewResolver/user?format=html
 * to get xml,  url should be .../views/viewResolver/user.xml or .../views/viewResolver/user?format=xml
 * to get json, the same procedure as above, also when there is neither path extension nor query parameter
 * it serves json since the default content type is configured to be json in CustomViewConfig class
 * Serving json/xml in Spring Boot is easier with HttpMessageConverter's as they come out of box.
 * In order to serve json/xml with views we have to add custom views and custom view resolvers that
 * resolve to these views.
 **/

@Controller
@RequestMapping("/viewResolver")
public class ViewResolverController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String viewResolverDemo(Map<String, Object> model){
        model.put("user", User.createUser()); // key not important when serving xml or json
        return "multipleRepresentations"; // view name not necessary when serving json or xml
    }
}
