package com.bol.interviews.kalaha.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bol.interviews.kalaha.resource.PlayerResource;
import com.bol.interviews.kalaha.service.BoardService;
import com.bol.interviews.kalaha.service.GameService;
import com.bol.interviews.kalaha.service.PitService;
import com.bol.interviews.kalaha.service.PlayService;
import com.bol.interviews.kalaha.service.PlayerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(PlayerResource.class)
public class TestPlayerResource {
	
	@Autowired
	private MockMvc mockMvc;
	
    @MockBean
    private GameService gameService;

    @MockBean
    private BoardService boardService;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private PitService pitService;

    @MockBean
    private PlayService playService;

  
	public TestPlayerResource() {
		// TODO Auto-generated constructor stub
	}

	
	@Test	
	public void createSingleUser () throws JsonProcessingException, Exception  {
		ObjectMapper objMapper = new ObjectMapper();
		Player player = new Player ("Mateus Fernandez");
		when(playerService.createPlayer(player)).thenReturn(player);
		
		mockMvc.perform (post ("/player/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(player))
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(content()
				.string(containsString(objMapper.writeValueAsString(player))));
		
	}
}