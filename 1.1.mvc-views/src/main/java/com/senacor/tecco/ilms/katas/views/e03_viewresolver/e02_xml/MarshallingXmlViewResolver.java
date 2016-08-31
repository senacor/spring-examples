package com.senacor.tecco.ilms.katas.views.e03_viewresolver.e02_xml;

/**
 * Created by fsubasi on 19.01.2016.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

import java.util.Locale;

/**
 * View resolver for returning XML in a view-based system.
 */
public class MarshallingXmlViewResolver implements ViewResolver {

    @Autowired
    private Marshaller marshaller;

    public MarshallingXmlViewResolver(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    /**
     * Get the view to use.
     *
     * @return Always returns an instance of {@link MarshallingView}.
     */
    @Override
    public View resolveViewName(String viewName, Locale locale)
            throws Exception {
        // another possibility is to use MappingJackson2XmlView
        MarshallingView view = new MarshallingView();
        view.setMarshaller(marshaller);
        return view;
    }
}
