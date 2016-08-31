package com.senacor.tecco.ilms.katas.example.e02_contentnegotiation;

import com.senacor.tecco.ilms.katas.common.model.User;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Dr. Michael Menzel on 30.08.2016.
 * Content Negotiation demo: Specifying the resource representation
 *
 * When receiving a new request, Spring will use of the “Accept” header
 * to determine the media type that it needs to respond with.
 *
 * It will then try to find an httpMessageConverter implementation that is capable
 * of handling specific media type – and it will use it to convert the entity
 * and send back the response.
 *
 * Let’s clarify this with a quick example:
 * - the Client sends a GET request to /negotiation/user
 *   with the Accept header set to application/json
 * – the ContentNegotiationDemoController Spring Controller is hit and
 *   returns the corresponding Java entity
 * - Spring then uses one of the Jackson message converters to marshall the entities to json
 *
 * There are three possibilities to define the required response type
 * - Http-Header: Accept:application/xml
 * - path extension (suffix) in the URL: negotiation/user.xml
 * - URL parameter format: negotiation/user?format=xml (disabled by default)
 *
 * See e04_customization to change default behavior
 */
@RestController
@RequestMapping("/negotiation")
public class ContentNegotiationDemoController {

    // map /negotiation/user to getUser.
    // The represention of the User object depend on the content negotiation
    // use .xml/.json to retrieve different representations
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public User getUser(){
        return new User("Michael", "Menzel", "michael.menzel@senacor.com");
    }

    //the use of produce parameter in @RequestMapping restricts to XML
    @RequestMapping(value = "userAsXML", method = RequestMethod.GET, produces = "application/xml")
    public User getUserAsXML(){
        return new User("Michael", "Menzel", "michael.menzel@senacor.com");
    }

}
