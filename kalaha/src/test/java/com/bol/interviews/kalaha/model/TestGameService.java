package com.bol.interviews.kalaha.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bol.interviews.kalaha.repository.GameRepository;
import com.bol.interviews.kalaha.service.GameService;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestGameService {
	@Mock
    private GameRepository gameRepositoryMock;
    
    private GameService gameService;

	@Before
	public void init () {
		gameService = new GameService(gameRepositoryMock);
	}
    
	public TestGameService() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testCreateGame () {
		Player playerMock = mock(Player.class);
		
		
		Game result = gameService.createNewGame(playerMock, playerMock);
		assertEquals(result.getPlayerOne(), playerMock);
        assertEquals(result.getPlayerTwo(), playerMock);
        assertEquals(result.getTurnOfWithId(), playerMock);
        assertFalse(result.isOver());      
        
	}
	
	@Test
	public void testChangeTurn () {
		Player playerOneMock = mock (Player.class);
		Player playerTwoMock = mock (Player.class);
		
		Game game = new Game (playerOneMock, playerTwoMock);
		
		assertEquals(game.getTurnOfWithId(), playerOneMock);
		game = gameService.changeTurn(game);
		assertEquals(game.getTurnOfWithId(), playerTwoMock);
	}
	@Test
	public void testJoinGame () {
		Player playerOneMock = mock (Player.class);
		Player playerTwoMock = mock (Player.class);		
		
		Game gameToJoin = gameService.createNewGame(playerOneMock, playerOneMock);
		assertNotEquals(gameToJoin.getPlayerTwo(), playerTwoMock);
		gameToJoin.setPlayerTwo(playerTwoMock);
		Game result = gameService.joinGame(gameToJoin);
		assertEquals(result.getPlayerTwo(), playerTwoMock);
	}
	@Test
	public void testFinishGame () {
		Player playerOneMock = mock (Player.class);
		Player playerTwoMock = mock (Player.class);
		
		Game game = gameService.createNewGame (playerOneMock, playerTwoMock);
		
		assertFalse (game.isOver());
		
		gameService.finishGame(game);
		
		assertTrue (game.isOver());
		
	}
	
}
