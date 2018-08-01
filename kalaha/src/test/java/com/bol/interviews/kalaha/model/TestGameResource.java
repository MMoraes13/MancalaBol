package com.bol.interviews.kalaha.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bol.interviews.kalaha.resource.GameResource;
import com.bol.interviews.kalaha.service.BoardService;
import com.bol.interviews.kalaha.service.GameService;
import com.bol.interviews.kalaha.service.PitService;
import com.bol.interviews.kalaha.service.PlayService;
import com.bol.interviews.kalaha.service.PlayerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@RunWith(SpringRunner.class)
@WebMvcTest(GameResource.class)
public class TestGameResource {

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
	
	
	public TestGameResource() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Test	
	public void createNewGame () throws JsonProcessingException, Exception  {
		ObjectMapper objMapper = new ObjectMapper();
		Player player = new Player ("Mateus Fernandez");
		Game game = new Game (player, player);
		Board boardMock = mock(Board.class);
		
		when(gameService.createNewGame(player, player)).thenReturn(game);
		when(boardService.createNewBoard(game)).thenReturn(boardMock);
		
		
		mockMvc.perform (post ("/game/create")		
				.contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(player))
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content()
				.string(containsString(objMapper.writeValueAsString(game))));
		
	}
	@Test	
	public void testJoinGame () throws JsonProcessingException, Exception  {
		ObjectMapper objMapper = new ObjectMapper ();
		Player playerOne = new Player ("Mateus");
		Player playerTwo = new Player ("Jan");
		Long id = 1000000L;
		Game game = new Game(id, playerOne, playerOne, playerOne, false);
		
		when(gameService.createNewGame(playerOne, playerOne)).thenReturn(game);
		
		MockHttpServletRequestBuilder builder = 
				MockMvcRequestBuilders.patch("/game/join/"+game.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(playerTwo));
		this.mockMvc.perform(builder)
		.andExpect(status().isNotFound());

	}
	
}
