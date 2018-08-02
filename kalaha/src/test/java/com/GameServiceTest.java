package com;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bol.interviews.kalaha.model.Game;
import com.bol.interviews.kalaha.model.Player;
import com.bol.interviews.kalaha.repository.GameRepository;
import com.bol.interviews.kalaha.service.GameService;

@RunWith(SpringJUnit4ClassRunner.class)
public class GameServiceTest {
	@Mock
    private GameRepository gameRepositoryMock;
    
    private GameService gameService;

	@Before
	public void init () {
		gameService = new GameService(gameRepositoryMock);
	}
    
	public GameServiceTest() {
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
		
		assertEquals(game.getTurnOfWithId(), playerTwoMock);
		game = gameService.changeTurn(game);
		assertEquals(game.getTurnOfWithId(), playerOneMock);
		
	}
	@Test
	public void testGamesToJoin () {
		gameService.getGamesToJoin();

	}
	@Test
	public void testJoinGame () {
		Player playerOneMock = mock (Player.class);
		Player playerTwoMock = mock (Player.class);
		
		Game game = new Game (playerOneMock, playerOneMock);
		assertEquals (game.getPlayerOne(), game.getPlayerTwo());
		game.setPlayerTwo(playerTwoMock);
		gameService.joinGame(game);
		assertNotEquals (game.getPlayerOne(), game.getPlayerTwo());
		verify(gameRepositoryMock, times(1)).save(game);
		
	}
	@Test
	public void testFinishGame () {
		Player playerOneMock = mock (Player.class);
		Player playerTwoMock = mock (Player.class);
		
		Game game = gameService.createNewGame (playerOneMock, playerTwoMock);
		
		assertFalse (game.isOver());
		
		gameService.finishGame(game);
		
		assertTrue (game.isOver());
		
		verify(gameRepositoryMock, times(2)).save(game);
		
	}	
	@Test
	public void testGetPlayerGames () {
		Player playerOneMock = mock (Player.class);
		gameService.getPlayerGames(playerOneMock);
		
		verify(gameRepositoryMock, times(1)).findByPlayerOne(playerOneMock);
		verify(gameRepositoryMock, times(1)).findByPlayerTwo(playerOneMock);
	}
	
}
