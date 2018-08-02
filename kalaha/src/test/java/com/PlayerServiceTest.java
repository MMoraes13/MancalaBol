package com;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bol.interviews.kalaha.model.Player;
import com.bol.interviews.kalaha.repository.PlayerRepository;
import com.bol.interviews.kalaha.service.PlayerService;

@RunWith(SpringJUnit4ClassRunner.class)
public class PlayerServiceTest {
	@Mock
	private PlayerRepository playerRepositoryMock;
	
	private PlayerService playerService;
	
	@Before
	public void init () {
		playerService = new PlayerService (playerRepositoryMock);
	}
	@Test
	public void testCreatePlayer () {
		Player player = mock (Player.class);
		playerService.createPlayer(player);
		verify(playerRepositoryMock, times(1)).save(player);
	}

}
