package com.senacor.tecco.ilms.katas.e02_hystrix;

import com.senacor.tecco.ilms.katas.HystrixApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HystrixApplication.class)
class HystrixApplicationTests {

    private MockMvc mockMvc;

    @BeforeEach
    void setupMockMvc(WebApplicationContext webContext) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webContext)
                .build();
    }

    @Test
    void hystrixDemoExceptionTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hystrixDemoException"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Our servers are overloaded right now. Please try again later"));
    }

    @Test
    void hystrixDemoTimeoutTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hystrixDemoTimeout"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Our servers are overloaded right now. Please try again later"));
    }
/*
	@Test
	 void hystrixOpenCircuit() throws Exception {
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
