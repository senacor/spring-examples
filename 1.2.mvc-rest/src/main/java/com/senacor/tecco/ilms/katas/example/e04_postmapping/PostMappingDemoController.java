package com.senacor.tecco.ilms.katas.example.e04_postmapping;

import com.senacor.tecco.ilms.katas.common.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by fsubasi on 18.01.2016.
 * RequestMapping Demo: Mapping requests with @RequestMapping
 *
 */
@RestController
// Request mapping annotation on controller level represents a
// common path prefix for all request mapping methods
// e.g. /mapping/queryParams
@RequestMapping("/postmapping")
public class PostMappingDemoController {

    // Simple POST example
    @PostMapping("")
    public String requestMappingPOST(@RequestBody String requestBody){
        return requestBody;
    }

    // In response to a form submit in 'application/x-www-form-urlencoded' format
    // requestParameters contain all data passed in the form
    @PostMapping("form")
    public String requestMappingDemoPOSTForm(@RequestParam Map<String, String> requestParameters){
        String str = "";
        for(Map.Entry<String, String> entry : requestParameters.entrySet()){
            str += "key: " + entry.getKey() + ", value: " + entry.getValue() + "<br/>";
        }
        return str;
    }

    // this request handler method consumes json
    // @RequestBody binds the posted data to the method parameter
    // Client-side ajax requests must include 'content-type: application/json' header
    @PostMapping(value = "consumes", consumes = "application/json")
    public User echoUser(@RequestBody User user){
        return user;
    }


}
