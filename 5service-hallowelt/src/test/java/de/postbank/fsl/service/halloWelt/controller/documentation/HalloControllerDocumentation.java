package de.postbank.fsl.service.halloWelt.controller.documentation;

import de.postbank.fsl.service.halloWelt.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.restdocs.RestDocumentation.document;
import static org.springframework.restdocs.RestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@PropertySource("classpath:application.yml")
@ActiveProfiles("lokal")
public class HalloControllerDocumentation {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Value("${info.maven.version}")
    private String version;

    @Value("${info.maven.artifact}")
    private String servicename;


    @Before
    public void setUp() {
        int firstDot = version.indexOf(".");
        String major = version.substring(0, firstDot);
        String minor = version.substring(firstDot + 1, version.indexOf(".", firstDot + 1));
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).apply(
                documentationConfiguration().uris().withScheme("http").withHost("fsl-dev.frankfurt.dez.postbank.de").withPort(80).withContextPath("/min" + major + "." + minor + "/" + servicename)).build();
    }

    @Test
    public void hallo_name_get() throws Exception {


        this.mockMvc.perform(get("/hallo/Welt")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is("Welt")))
                .andDo(document("hallo_name_get").
                        withResponseFields(
                                fieldWithPath("name").description("Der Neme, der übergeben wurde")
                        ));
    }
}
