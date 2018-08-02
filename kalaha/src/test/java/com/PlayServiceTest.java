package com;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bol.interviews.kalaha.model.Game;
import com.bol.interviews.kalaha.model.Player;
import com.bol.interviews.kalaha.service.PlayService;

@RunWith(SpringJUnit4ClassRunner.class)
public class PlayServiceTest {
	
	private PlayService playService;
	
	@Before
	public void init() {
		playService = new PlayService ();
	}

	@Test
	public void testIsMyTurn () {		
		Player player = mock (Player.class);
		Game game = new Game(player, player);
		boolean result = playService.isMyTurn(game, player);
		assertTrue (result);
	}

}
