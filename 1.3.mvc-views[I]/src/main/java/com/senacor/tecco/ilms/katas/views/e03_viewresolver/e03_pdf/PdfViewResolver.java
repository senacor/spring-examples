package com.senacor.tecco.ilms.katas.views.e03_viewresolver.e03_pdf;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * Created by fsubasi on 22.02.2016.
 */
public class PdfViewResolver implements ViewResolver{
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        if(viewName.equalsIgnoreCase("multipleRepresentations")){
            return new UserPdfView();
        }
        return null;
    }
}
