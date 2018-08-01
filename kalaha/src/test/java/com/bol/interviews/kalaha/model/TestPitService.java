package com.bol.interviews.kalaha.model;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bol.interviews.kalaha.repository.PitRepository;
import com.bol.interviews.kalaha.service.PitService;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestPitService {

	@Mock
	private PitRepository pitRepositoryMock;

	private PitService pitService;

	@Before
	public void init() {
		pitService = new PitService(pitRepositoryMock);
	}

	@Test
	public <S> void testCreatePitStore() {
		// Test specific init
		Board boardMock = mock(Board.class);
		
		
		Pit result = pitService.createNewPit(boardMock, 0, 6);
		assertEquals(result.getBoard(), boardMock);

		assertEquals(result.getPosition(), new Integer(0));
		assertEquals(result.getValue(), new Integer(6));
		verify(pitRepositoryMock, times(1)).save(result);
	}

	/*@Test
	public void testUpdateNumberOfStones() {
		Board boardMock = mock(Board.class);
		int position = 6;
		int value = 10;
		Pit pit = new Pit(boardMock, position, 100);
		pit.setId(1L);
		// Test rules
		
		Pit result = pitService.updatePitNumberOfStones(pit, value, true);

		assertEquals(result.getBoard(), boardMock);
		assertEquals(result.getPosition(), new Integer(position));
		assertEquals(result.getValue(), new Integer(110)); // Correct amount set
		verify(pitRepositoryMock, times(1)).save (pit); 
	}*/
}
