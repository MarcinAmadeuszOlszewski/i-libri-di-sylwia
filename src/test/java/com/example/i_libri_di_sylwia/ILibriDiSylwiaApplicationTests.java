package com.example.i_libri_di_sylwia;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@SpringBootTest
class ILibriDiSylwiaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@DisplayName("loads application context")
	@Test
	void contextLoads() {
	}

	@DisplayName("returns demo work response")
	@Test
	void returnsDemoWorkResponse() throws Exception {
		mockMvc.perform(get("/v1/work"))
			.andExpect(status().isOk())
			.andExpect(content().string("Its alive"));
	}

	@DisplayName("renders index page with demo endpoint")
	@Test
	void rendersIndexPageWithDemoEndpoint() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("index"))
			.andExpect(content().string(containsString("\\/v1\\/work")));
	}

}
