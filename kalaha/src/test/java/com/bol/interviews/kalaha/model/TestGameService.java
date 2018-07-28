package com.bol.interviews.kalaha.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bol.interviews.kalaha.repository.GameRepository;
@RunWith(SpringJUnit4ClassRunner.class)
public class TestGameService {

    @Mock
    private GameRepository gameRepositoryMock;
    

	public TestGameService() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testCreateGame () {
		Player playerMock = mock(Player.class);
		
		Game result = new Game(playerMock, playerMock);
		
		assertEquals(result.getPlayerOne(), playerMock);
        assertEquals(result.getPlayerTwo(), playerMock);
        assertEquals(result.getTurnOfWithId(), playerMock);
        assertFalse(result.isOver());
	}

}
