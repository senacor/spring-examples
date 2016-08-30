package com.example.views.e02_viewString;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by fsubasi on 18.01.2016.
 * This example has the same effect as example 1, but in this one
 * we return the view name as a string from the method. Even though we do not
 * return model from the method, it is forwarded to the view all the same.
 */
@Controller
@RequestMapping("/viewName")
public class ViewNameController {
    @RequestMapping("")
    public String stringViewNameDemo(Map<String, String> model){
        model.put("user", "john");
        model.put("email", "john@example.com");
        return "viewDemo";
    }
}
