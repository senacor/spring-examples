package com.senacor.tecco.ilms.katas.example.e03_requestmapping;

import org.springframework.web.bind.annotation.*;

/**
 * Created by fsubasi on 18.01.2016.
 * RequestMapping Demo: Mapping requests with @RequestMapping
 *
 */
@RestController
// Request mapping annotation on controller level represents a
// common path prefix for all request mapping methods
// e.g. /mapping/queryParams
@RequestMapping("/mapping")
public class RequestMappingDemoController {

    // path variables with URI Template Pattern in GET Requests:
    // variable parts of the URI are specified with URI templates in the request mapping annotation
    // and passed to the request mapping method
    @RequestMapping(value = "pathVariable/{parameter}", method = RequestMethod.GET)
    public String pathVariableMapping(@PathVariable("parameter") String parameter){
        return "Parameter is " + parameter;
    }

    // URI template pattern with regex in GET Requests:
    // The request is not intercepted if regex does not match
    // GetMapping instead of @RequestMapping simplifies request
    @GetMapping("pathVariableWithRegex/{parameter:\\d+}")
    public String pathVariableMappingWithRegex(@PathVariable("parameter") String parameter){
        return "Numerical parameter is " + parameter;
    }

    // usage of query parameters in GET Requests:
    // request parameters in the url, e.g. '/mapping/queryParam?name=<name>'
    // are mapped to the parameters of the request mapping method that
    // are annotated with @RequestParam
    @GetMapping("queryParam")
    public String queryParamMapping(@RequestParam(value = "name") String name){
        return "Hello " + name;
    }

    // usage of required query parameters in GET Requests:
    // the request to the url '/mapping/queryParams' will be intercepted by this
    // controller method only if the request contains the query parameter 'name'
    // 'required' attribute can be set to false to intercept without it
    @GetMapping("requiredQueryParams")
    public String requiredQueryParamMapping(@RequestParam(value = "name", required = true) String name){
        return "Hello " + name;
    }

    // Complex path variables with @MatrixVariable in GET Requests:
    // RequestMappingHanderMapping must be configured for this to work. See com.senacor.tecco.ilms.katas.MyPostProcessor class
    // An example call with matrix variables /mapping/hotel/34;floor=45;room=3/guest
    @GetMapping("hotel/{hotelId}/guest")
    public String matrixVariableMapping(@PathVariable("hotelId") long hotelId,
                                                          @MatrixVariable(pathVar="hotelId", value="floor") short floorNumber,
                                                          @MatrixVariable(pathVar="hotelId", value="room") short roomNumber){
        return "Hotel ID: " + hotelId + "<br/>Floor Number: " + floorNumber + "<br/>Room Number: " + roomNumber;
    }

}
