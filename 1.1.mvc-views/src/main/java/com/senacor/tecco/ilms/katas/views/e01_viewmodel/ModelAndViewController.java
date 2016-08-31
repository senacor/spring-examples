package com.senacor.tecco.ilms.katas.views.e01_viewmodel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by fsubasi on 17.01.2016.
 *
 * Simple example of ModelAndView usage to generate a model that
 * is rendered by thymeleaf with a specific view
 * The view template "viewTemplate" are stored in resources/templates.
 *
 * This approach is mostly used for serving HTML content, where
 * the view name points to a JSP or Thymeleaf template
 */
@Controller
public class ModelAndViewController {

    //map view generation to /modelAndView
    @RequestMapping("/modelAndView")
    public ModelAndView createModelAndView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", "john");
        modelAndView.addObject("email", "john@example.com");
        modelAndView.setViewName("viewTemplate");
        return modelAndView;
    }
}
