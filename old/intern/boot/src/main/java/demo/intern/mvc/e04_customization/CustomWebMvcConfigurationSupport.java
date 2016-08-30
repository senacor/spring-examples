package demo.intern.mvc.e04_customization;

/**
 * Created by fsubasi on 24.01.2016.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.intern.mvc.User;
import demo.intern.mvc.e02_view.e03_viewresolver.e01_json.JacksonViewResolver;
import demo.intern.mvc.e02_view.e03_viewresolver.e02_xml.MarshallingXmlViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.AppCacheManifestTransformer;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import java.util.Arrays;
import java.util.List;

/**
 * Spring Boot provides auto-configuration for Spring MVC
 * http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-spring-mvc-auto-configuration
 *
 * In this example, we see how to do a custom Spring MVC configuration
 * Here we extend WebMvcConfigurationSupport, other options would be to extend
 * DelegatingWebMvcConfiguration or WebMvcConfigurerAdapter
 */
//@Configuration  // commenting out to prevent overriding spring boot autoconfig
@ComponentScan
public class CustomWebMvcConfigurationSupport extends WebMvcConfigurationSupport {

    @Autowired
    ObjectMapper objectMapper; // object mapper can be customized too

    /**
     * Customizing HttpMessageConverters,
     * HttpMessageConverters are used for @ResponseBody controller methods
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        converters.add(new FormHttpMessageConverter());
        converters.add(new SourceHttpMessageConverter<>());

        /*
        MappingJackson2XmlHttpMessageConverter xmlConverter =
                new MappingJackson2XmlHttpMessageConverter();
        xmlConverter.setPrettyPrint(true); // browsers pretty print xml anyway
        xmlConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("application", "xml"),
                new MediaType("text", "xml")
        ));
        converters.add(xmlConverter);*/

        MarshallingHttpMessageConverter xmlConverter =
                new MarshallingHttpMessageConverter();
        xmlConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("application", "xml"),
                new MediaType("text", "xml")
        ));
        converters.add(xmlConverter);


        MappingJackson2HttpMessageConverter jsonConverter =
                new MappingJackson2HttpMessageConverter();
        jsonConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("application", "json"),
                new MediaType("text", "json")
        ));

        jsonConverter.setObjectMapper(this.objectMapper);
        jsonConverter.setPrettyPrint(true);
        converters.add(jsonConverter);
    }

    // Configure static resource handling
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String location = "classpath:static/";
        Integer cachePeriod = 0;
        boolean useResourceCache = false;
        String version = "dev";

        AppCacheManifestTransformer appCacheTransformer = new AppCacheManifestTransformer();
        VersionResourceResolver versionResolver = new VersionResourceResolver()
                .addFixedVersionStrategy(version, "/**/*.js", "/**/*.html")
                .addContentVersionStrategy("/**");

        registry.addResourceHandler("/**")
                .addResourceLocations(location)
                .setCachePeriod(cachePeriod)
                .resourceChain(useResourceCache)
                .addResolver(versionResolver)
                .addTransformer(appCacheTransformer);
    }

    // CONFIGURING CONTENT NEGOTIATION
    // Here we customize content negotiation
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .defaultContentType(MediaType.APPLICATION_JSON)
                .favorParameter(true) // use query parameter, default is 'format' can be customized by calling parameterName on configurer
                .favorPathExtension(true) // use path extensions
                .ignoreAcceptHeader(true) // do not use accept header
                .useJaf(false) // do not use JAF mime types
                .mediaType("xml", MediaType.APPLICATION_XML)  // we can serve xml
                .mediaType("json", MediaType.APPLICATION_JSON) // we can serve json
                .mediaType("html", MediaType.TEXT_HTML) // we can serve html
                .mediaType("txt", MediaType.TEXT_PLAIN); // we can serve plain text
        super.configureContentNegotiation(configurer);
    }

    /**
     * If semi colon content is removed(default) @MatrixVariable s do not work
     * @return
     */
    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        final RequestMappingHandlerMapping requestMappingHandlerMapping = super.requestMappingHandlerMapping();
        requestMappingHandlerMapping.setRemoveSemicolonContent(false);
        return requestMappingHandlerMapping;
    }

    // Configure a ContentNegotiatiatingViewResolver which is a ViewResolver
    //  that resolves a view based on the request file name or Accept header.(does not use parameter!)
    @Bean
    public ViewResolver contentNegotiatingViewResolver(
            ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);
        // If setViewResolvers is not called, the view resolver beans will be detected
        // automatically and used to configure ContentNegotiationManager
        /*
        resolver.setViewResolvers(Arrays.asList(
                        viewResolver(templateEngine(templateResolver())),
                        jacksonViewResolver(),
                        getMarshallingXmlViewResolver())
        );*/
        return resolver;
    }

    // This is the optional thymeleaf config
    // Below we are using a ServletContextTemplateResolver
    // The defaultTemplateResolver of spring boot auto configuration
    // uses a SpringResourceResourceResolver.
    // The ViewResolver will search the WEB-INF/templates folder
    // Default thymeleaf view resolver of spring boot can be customized
    // using spring.thymeleaf.* properties in application.properties file
    @Bean
    public ViewResolver viewResolver(
            SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }
    @Bean
    public SpringTemplateEngine templateEngine(
            TemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }
    @Bean
    public TemplateResolver templateResolver() {
        TemplateResolver templateResolver =
                new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        return templateResolver;
    }

    //Jackson View Resolver for JSON
    @Bean
    public JacksonViewResolver jacksonViewResolver(){
        return new JacksonViewResolver();
    }

    // View Resolver for XML
    // I had to add spring boot web services dependency to get
    // spring oxm on the classpath. HttpMessageConverter does this
    // without spring oxm, so there should be an easier way to do this
    @Bean
    public ViewResolver getMarshallingXmlViewResolver() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(User.class);
        return new MarshallingXmlViewResolver(marshaller);
    }
}
