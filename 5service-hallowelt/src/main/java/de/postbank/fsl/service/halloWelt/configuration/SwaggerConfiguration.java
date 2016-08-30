package de.postbank.fsl.service.halloWelt.configuration;


import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.paths.SwaggerPathProvider;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import de.postbank.fsl.common.util.ProfileHelper;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.mangofactory.swagger")
public class SwaggerConfiguration {
    final static Log log = LogFactory.getLog(SwaggerConfiguration.class);

    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;

    @Value("${info.maven.version}")
    private String version;

    @Value("${info.fsl.servicename}")
    private String servicename;

    @Value("${info.fsl.servicegroup}")
    private String servicegroup;

    @Value("${info.maven.description}")
    private String servicedescription;


    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                        //erstmal alles, was keinen _ als erstes Zeichen nach dem slash hat
                .includePatterns("^/[^_].*$")
                .apiVersion(version)
                .pathProvider(new PathProvider());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                servicename,
                servicedescription,
                null, //Projekt Webseite
                "PBS-FSL@senacor.com",
                null,//Lizenz Name
                null//Lizenz URL
        );
    }


    private class PathProvider extends SwaggerPathProvider {
        private String basePath = "";

        public PathProvider() {
            if (ProfileHelper.PROFIL_LOKAL.equals(ProfileHelper.getUmgebung())) {
                basePath = "/";
                log.warn("Lokales Profil aktiv, generiere Swagger-URIs mit applicationPath " + basePath);
            } else {
                basePath = "/" + servicegroup + "/" + servicename + "/" + version ;
                log.info("nicht-Lokales Profil aktiv, generiere Swagger-URIs mit applicationPath " + basePath);
            }
        }

        @Override
        protected String applicationPath() {
            return basePath;
        }

        @Override
        protected String getDocumentationPath() {
            return "";
        }
    }
}
