package com.example.e02_requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.common.response.BaseResponse;

import java.util.Map;

/**
 * Created by fsubasi on 18.01.2016.
 * RequestMapping Demo: Mapping requests with @RequestMapping
 */
@Controller
@RequestMapping("/requestMapping")
public class RequestMappingDemoController {

    // narrow the primary mapping by specifying a list of producible media types
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "text/plain")// narrowing to GET requests, RequestMapping maps all HTTP methods by default
    @ResponseBody
    public String requestMappingDemoGET(){
        return "Hello World!";
    }

    // @RequestParam example:
    // the request to the url '/requestMapping/queryParams' will be intercepted by this
    // controller method only if the request contains the query parameter 'name'
    // 'required' attribute can be set to false to intercept without it
    @RequestMapping(value = "queryParams", method = RequestMethod.GET)
    @ResponseBody
    public String requestMappingDemoGETWithQueryParams(@RequestParam(value = "name", required = true) String name){
        return "Hello " + name;
    }

    // URI Template Pattern example
    @RequestMapping(value = "templateParams/{parameter}", method = RequestMethod.GET)
    @ResponseBody
    public String requestMappingDemoGETWithTemplateParams(@PathVariable("parameter") String parameter){
        return "Parameter is " + parameter;
    }

    // URI Template Pattern with Regex example
    // The request is not intercepted if regex does not match
    @RequestMapping(value = "templateParams/regex/{parameter:\\d+}", method = RequestMethod.GET)
    @ResponseBody
    public String requestMappingDemoGETWithTemplateParamsRegex(@PathVariable("parameter") String parameter){
        return "Numerical parameter is " + parameter;
    }

    // @MatrixVariable example
    // RequestMappingHanderMapping must be configured for this to work. See com.example.MyPostProcessor class
    // An example call with matrix variables /requestMapping/hotel/34;floor=45;room=3/guest
    @RequestMapping(value="hotel/{hotelId}/guest", method = RequestMethod.GET)
    @ResponseBody
    public String requestMappingDemoGETWithMatrixVariable(@PathVariable("hotelId") long hotelId,
                                                          @MatrixVariable(pathVar="hotelId", value="floor") short floorNumber,
                                                          @MatrixVariable(pathVar="hotelId", value="room") short roomNumber){
        return "Hotel ID: " + hotelId + "<br/>Floor Number: " + floorNumber + "<br/>Room Number: " + roomNumber;
    }

    // Simple POST example
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public String requestMappingDemoPOST(@RequestBody String requestBody){
        return requestBody;
    }

    // In response to a form submit in 'application/x-www-form-urlencoded' format
    // requestParameters contain all data passed in the form
    @RequestMapping(value = "form", method = RequestMethod.POST)
    @ResponseBody
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
    @RequestMapping(value = "consumes", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public BaseResponse echoUser(@RequestBody BaseResponse response){
        return response;
    }


}
