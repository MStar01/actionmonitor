package com.mpirv.actionmonitor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ActionMonitorApplication.class)
@PropertySource("classpath:application.properties")
public class ActionMonitorTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Value("${application.version}")
	private String appVersion;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testStatusOK() throws Exception {
		mockMvc.perform(get("/status"))
				.andExpect(status().isOk())
				.andExpect(content().string("UP"));
	}

	@Test
	public void testGetVersion() throws Exception {
		mockMvc.perform(get("/version"))
				.andExpect(status().isOk())
				.andExpect(content().string(appVersion));
	}
}
