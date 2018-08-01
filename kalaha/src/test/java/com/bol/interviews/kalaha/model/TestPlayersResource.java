package com.bol.interviews.kalaha.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bol.interviews.kalaha.repository.GameRepository;
import com.bol.interviews.kalaha.resource.PlayersResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestPlayersResource {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PlayersResource playersResource;
	
	
	public TestPlayersResource() {
		super();
		
	}

	@Test	
	public void testFindPlayer () throws JsonProcessingException, Exception  {

	}	

}
