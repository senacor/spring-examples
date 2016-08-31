package com.senacor.tecco.ilms.katas.views.e03_viewresolver;

import com.senacor.tecco.ilms.katas.views.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by fsubasi on 18.01.2016.
 *
 * This example shows the view resolvers for thymeleaf, json xml, pdf and xls in action.
 * See CustomMvcConfiguration for content negotiation configuration.
 * This controller serves html, json, xml, pdf and xls representation
 * of an user object.
 *
 * When receiving a new request, Spring will use the “Accept” header, path extension (suffix)
 * or URL parameter "format" to determine the media type that it needs to respond with:
 * - Http-Header: Accept:application/xml
 * - path extension (suffix) in the URL: viewResolver/user.xml
 * - URL parameter "format": viewResolver/user?format=xml
 *
 * It will then try to find an ViewResolver implementation that is capable
 * of handling specific media type – and it will use it to convert the model
 * to generate the service response.
 *
 * json is configured as the default content type in CustomMvcConfiguration.
 * Therefore, .../viewResolver/user returns json
 *
 * Serving json/xml in Spring Boot is easier with HttpMessageConverter's as they come out of box.
 * In order to serve json/xml with views we have to add custom views and custom view resolvers that
 * resolve to these views.
 **/

@Controller
@RequestMapping("/viewResolver")
public class ViewResolverController {

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String viewResolverDemo(Map<String, Object> model){
        model.put("user", User.createUser()); // key not important when serving xml or json
        return "viewResolverTemplate"; // view name not necessary when serving json or xml
    }
}
