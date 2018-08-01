package com.bol.interviews.kalaha.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bol.interviews.kalaha.service.PlayerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestPlayerService {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PlayerService playerService;
	
	
	

	public TestPlayerService() {
		this.playerService = new PlayerService (mockMvc);
	}
	
	@Test	
	public void testFindPlayer () throws JsonProcessingException, Exception  {

		mockMvc.perform (get ("/players")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}	

}
