package com.example.views.e03_viewresolver.e04_xls;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * Created by fsubasi on 22.02.2016.
 */
public class ExcelViewResolver implements ViewResolver{
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        if(viewName.equalsIgnoreCase("multipleRepresentations")){
            return new UserExcelView();
        }
        return null;
    }
}
