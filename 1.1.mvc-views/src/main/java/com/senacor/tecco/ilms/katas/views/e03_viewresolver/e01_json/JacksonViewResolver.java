package com.senacor.tecco.ilms.katas.views.e03_viewresolver.e01_json;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Locale;

/**
 * Created by fsubasi on 18.01.2016.
 * View resolver for returning JSON in a view-based system.
 */
public class JacksonViewResolver implements ViewResolver {
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setPrettyPrint(true);
        view.setExtractValueFromSingleKeyModel(true);

        return view;
    }
}
