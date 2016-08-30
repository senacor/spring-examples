package demo.fsl.restdocs;

import demo.fsl.Application;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class AppDocumentation {

	// We specify where the generated snippets will be stored
	@Rule
	public RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");

	private RestDocumentationResultHandler document;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.document = MockMvcRestDocumentation
				//  {method-name} => The generated snippets will be kept under a folder with the test
				// method's name i.e. the folder name will be 'get-user' for the getUser method below,
				// the generated snippet location is later specified in the .asciidoc file
				.document("{method-name}",
						// pretty print json or xml
						Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
						Preprocessors.preprocessResponse(Preprocessors.prettyPrint()));

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation)
						.uris()
						// Specify service URI so that http requests can be documented correctly
							.withScheme("http")
							.withHost("localhost")
							.withPort(8080))
				.build();
	}

	@Test
	public void getUser() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.get("/restDocs/23434").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(document
						.snippets(PayloadDocumentation.responseFields( // We are documenting the response
								// firstName attribute and its description followed by other attributes and their descriptions
								PayloadDocumentation.fieldWithPath("firstName").description("The user's first name"),
								PayloadDocumentation.fieldWithPath("lastName").description("The user's last name"),
								PayloadDocumentation.fieldWithPath("emailAddress").description("The user's email address"))));
	}

}
