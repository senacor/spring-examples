package demo.intern.mvc.e02_view.e03_viewresolver;

import demo.intern.mvc.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by fsubasi on 18.01.2016.
 * We see the configured view resolvers for thymeleaf, json and xml in action
 * This controller serves html, xml or json representation of a User object
 * Since we enabled path extensions and path parameter for content negotiation
 * to get html, url should be .../views/viewResolver/user.html or .../views/viewResolver/user?format=html
 * to get xml,  url should be .../views/viewResolver/user.xml or .../views/viewResolver/user?format=xml
 * to get json, the same procedure as above, also when there is neither path extension nor query parameter
 * it serves json since the default content type is configured to be json in CustomViewConfig class
 * SPRING BOOT AS A DEFAULT DOES NOT USE VIEW RESOLVERS FOR SERVING JSON/XML. WE NEED TO OVERRIDE DEFAULT CONFIG WITH
 * demo.intern.mvc.e04_customization.CustomWebMvcConfigurationSupport TO BE ABLE TO GET THE EXPECTED RESULT
 **/

@Controller
@RequestMapping("/views/viewResolver/user")
public class ViewResolverController {

    @RequestMapping(value = "")
    public String viewResolverDemo(Map<String, Object> model){
        model.put("user", User.createUser()); // key not important when serving xml or json
        return "multipleRepresentations"; // view name not necessary when serving json or xml
    }
}
