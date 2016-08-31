package com.senacor.tecco.ilms.katas.views;

import com.senacor.tecco.ilms.katas.views.User;
import com.senacor.tecco.ilms.katas.views.e03_viewresolver.e01_json.JacksonViewResolver;
import com.senacor.tecco.ilms.katas.views.e03_viewresolver.e02_xml.MarshallingXmlViewResolver;
import com.senacor.tecco.ilms.katas.views.e03_viewresolver.e03_pdf.PdfViewResolver;
import com.senacor.tecco.ilms.katas.views.e03_viewresolver.e04_xls.ExcelViewResolver;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * Created by fsubasi on 22.02.2016.
 * Spring Boot comes with a ContentNegotiatingViewResolver, which does not itself resolve view names to views
 * but uses other view resolver beans to find a matching View. ContentNegotiatingViewResolver as its name
 * indicates tries to find the View that is most suitable to the requested content type.
 */
@Configuration
public class CustomMvcConfiguration extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter{

    @Bean
    public Marshaller marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(User.class);
        return marshaller;
    }

    @Bean
    public ViewResolver jsonViewResolver(){
        return new JacksonViewResolver();
    }

    @Bean
    public ViewResolver xmlViewResolver(){
        return new MarshallingXmlViewResolver(marshaller());
    }

    @Bean
    public ViewResolver pdfViewResolver(){
        return new PdfViewResolver();
    }

    @Bean
    public ViewResolver excelViewResolver(){
        return new ExcelViewResolver();
    }

    // CUSTOM THYMELEAF CONFIGURATION
    // This is a custom thymeleaf configuration, most of the time it is unnecessary since spring boot thymeleaf configuration
    // can be customized using external configuration(i.e. using  spring.thymeleaf.* properties). This configuration is
    // for demonstration purposes only. It uses a ServletContextTemplateResolver, as opposed to the defaultTemplateResolver bean
    // of spring boot auto configuration which uses a SpringResourceResourceResolver.
    // The ViewResolver will search the WEB-INF/templates folder for thymeleaf templates
    @Bean
    public ViewResolver thymeleafViewResolver(SpringTemplateEngine customTemplateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(customTemplateEngine);
        return viewResolver;
    }
    @Bean
    public SpringTemplateEngine customTemplateEngine(TemplateResolver customTemplateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(customTemplateResolver);
        return templateEngine;
    }
    @Bean
    public TemplateResolver customTemplateResolver() {
        TemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        return templateResolver;
    }

    // CONFIGURING CONTENT NEGOTIATION
    // Here we customize content negotiation
    // Spring uses PPA strategy which is Path extension, Parameter, Accept header
    // If all three are enabled Path Extension > Parameter > Accept Header
    // Spring boot auto configuration only uses path extension and accept header
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .defaultContentType(MediaType.APPLICATION_JSON)
                .favorParameter(true) // use query parameter, default is 'format' can be customized by calling parameterName on configurer
                .favorPathExtension(true) // use path extensions
                .ignoreAcceptHeader(false) // use accept header
                .useJaf(false) // do not use JAF mime types
                .mediaType("xml", MediaType.APPLICATION_XML)  // we can serve xml
                .mediaType("json", MediaType.APPLICATION_JSON) // we can serve json
                .mediaType("html", MediaType.TEXT_HTML) // we can serve html
                .mediaType("pdf", new MediaType("application", "pdf"))
                .mediaType("xls", new MediaType("application", "vnd.ms-excel"));
        super.configureContentNegotiation(configurer);
    }
}