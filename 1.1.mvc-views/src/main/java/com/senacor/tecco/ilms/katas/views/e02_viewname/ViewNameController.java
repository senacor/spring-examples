package com.senacor.tecco.ilms.katas.views.e02_viewname;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by fsubasi on 18.01.2016.
 *
 * In this example, the request mapping function adds data to the model
 * and returns the view name as a string. This an alternative approach
 * to bind a model to a view.
 */
@Controller
public class ViewNameController {
    @RequestMapping("/modelWithViewName")
    public String createModelWithViewName(Map<String, String> model){
        model.put("user", "john");
        model.put("email", "john@example.com");
        return "viewTemplate";
    }
}
