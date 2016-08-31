package com.senacor.tecco.ilms.katas.views.e01_modelAndView;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by fsubasi on 17.01.2016.
 *
 * Here we see an example of ModelAndView with which we can return both the model and view name
 * This is mostly used for serving HTML content, where the view name points to a JSP or Thymeleaf template
 */
@Controller
@RequestMapping("/modelAndView")
public class ModelAndViewController {

    @RequestMapping("")
    public ModelAndView modelAndViewDemo(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", "john");
        modelAndView.addObject("email", "john@example.com");
        modelAndView.setViewName("viewDemo");
        return modelAndView;
    }
}
