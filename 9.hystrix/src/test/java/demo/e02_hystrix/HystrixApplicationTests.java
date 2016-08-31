package demo.e02_hystrix;

import demo.HystrixApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HystrixApplication.class)
@WebAppConfiguration
public class HystrixApplicationTests {
	@Autowired
	private WebApplicationContext webContext;
	private MockMvc mockMvc;

	@Before
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webContext)
				.build();
	}

	@Test
	public void hystrixDemoExceptionTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/hystrixDemoException"))
				.andExpect(MockMvcResultMatchers.content()
						.string("Our servers are overloaded right now. Please try again later"));
	}

	@Test
	public void hystrixDemoTimeoutTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/hystrixDemoTimeout"))
				.andExpect(MockMvcResultMatchers.content()
						.string("Our servers are overloaded right now. Please try again later"));
	}
/*
	@Test
	public void hystrixOpenCircuit() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/hystrixOpenCircuit"))
				.andExpect(MockMvcResultMatchers.content()
						.string("Standard response"));

		System.setProperty("hystrix.command.demoOpenCircuit.circuitBreaker.forceOpen", "true");

		RefreshScope refreshScope = webContext.getBean(RefreshScope.class);
		refreshScope.

		mockMvc.perform(MockMvcRequestBuilders.get("/hystrixOpenCircuit"))
				.andExpect(MockMvcResultMatchers.content()
						.string("Standard response"));
	}*/

}
