package com.example.views.e04_modelattribute;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by fsubasi on 22.02.2016.
 */
@Controller
@SessionAttributes("country")
public class ModelAttributeController {

    // not a session attribute
    @ModelAttribute("foo")
    String getFoo() {
        return "bar";
    }

    // no view name returned here so the view name will be the same as in @RequestMapping
    // i.e. 'modelAttribute1'
    @RequestMapping(value = "/modelAttribute1", produces = "text/html")
    public void modelAttribute1(Map model, HttpSession session){}

    // Add flash attributes and make a redirect, these attributes will be available inside
    // the redirected url handler method, this is not a session attribute
    @RequestMapping("/from")
    ModelAndView redirectFrom(RedirectAttributes redirectAttributes, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView();
        redirectAttributes.addFlashAttribute("attr", "demo");
        modelAndView.setViewName("redirect:/to");
        // session.setAttribute("firstName", "John");
        model.put("country", "germany"); // this becomes a session attribute
        modelAndView.addObject("firstName", "John"); // during a redirect this becomes query parameter
        return modelAndView;
    }

    @RequestMapping("/to")
    String redirectTo(@ModelAttribute("attr") String attribute,
                      @ModelAttribute("firstName") String firstName, HttpSession session)
    {
        // Do sth with the attribute
        return "modelAttribute2";
    }

    @RequestMapping("/test")
    String test() // check if session attrs are still there
    {
        return "modelAttribute2";
    }

}
