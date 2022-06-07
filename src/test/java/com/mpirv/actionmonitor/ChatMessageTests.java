package com.mpirv.actionmonitor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpirv.actionmonitor.model.ChatMessage;
import com.mpirv.actionmonitor.model.MessageType;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ActionMonitorApplication.class)
public class ChatMessageTests {
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	private ChatMessage msg;
	private ObjectMapper mapper

	@BeforeClass
    public static void setup() {
        msg = ChatMessage.builder()
				.type(MessageType.CHAT)
				.sender("Test")
				.content("Test Content")
				.time("Test Time")
				.build();
		mapper = new ObjectMapper();
    }

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testSendMessage() throws Exception {
		MvcResult result = mockMvc.perform(
						post("/message")
								.content(getJson(msg))
								.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		assertTrue(result.getResponse().getContentAsString().contains(msg.getContent()));
	}

	@Test
	public void testGetAllMessages() throws Exception {
		this.mockMvc.perform(
						get("/all")
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
	}

	@Test
	public void testAddMessageEmptyMessage() throws Exception {
		MvcResult result = mockMvc.perform(
						post("/message")
								.content(getJson(msg))
								.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		assertTrue(result.getResponse().getContentAsString().contains(msg.getContent()));
	}

	@Test
	public void testAddMessageWrongJSON() throws Exception {
		this.mockMvc.perform(
						post("/message")
								.content("{ message:\"new message\" ")
								.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isBadRequest());
	}

	private static String getJson(Object obj) {
		return objectMapper.writeValueAsString(obj);
	}
}