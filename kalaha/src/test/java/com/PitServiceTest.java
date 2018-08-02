package com;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bol.interviews.kalaha.model.Board;
import com.bol.interviews.kalaha.model.Pit;
import com.bol.interviews.kalaha.repository.PitRepository;
import com.bol.interviews.kalaha.service.PitService;

@RunWith(SpringJUnit4ClassRunner.class)
public class PitServiceTest {

	@Mock
	private PitRepository pitRepositoryMock;

	private PitService pitService;

	@Before
	public void init() {
		pitService = new PitService(pitRepositoryMock);
	}

	@Test
	public void testCreatePitStore() {
		// Test specific init
		Board boardMock = mock(Board.class);
		
		
		Pit result = pitService.createNewPit(boardMock, 0, 6);
		assertEquals(result.getBoard(), boardMock);

		assertEquals(result.getPosition(), new Integer(0));
		assertEquals(result.getValue(), new Integer(6));
		verify(pitRepositoryMock, times(1)).save(result);
	}
	@Test
	public void testIncrementPitStore () {
		Board boardMock = mock(Board.class);
		Pit pit = new Pit (boardMock, 0, 6);
		
		when(pitRepositoryMock.findByBoardAndPosition(boardMock, 0)).thenReturn(pit);
		
		Pit result = pitService.updatePitIncrement(boardMock, 0);
		assertEquals(new Integer(7), 
				result.getValue());
		verify(pitRepositoryMock, times(1)).save(result);
	}
	
	@Test
	public void testUpdatePitStoreByPit () {
		Board boardMock = mock(Board.class);
		Pit pit = new Pit (boardMock, 0, 6);
		
		when(pitRepositoryMock.findByBoardAndPosition(boardMock, 0)).thenReturn(pit);
		
		Pit result = pitService.updatePitNumberOfStones(pit, 10, false);
		assertEquals(new Integer(10), 
				result.getValue());
		result = pitService.updatePitNumberOfStones(pit, 10, true);
		assertEquals(new Integer(20), 
				result.getValue());
		verify(pitRepositoryMock, times(2)).save(result);		
	}	
	
	
	@Test
	public void testUpdatePitStoreByBoard () {
		Board boardMock = mock(Board.class);
		Pit pit = new Pit (boardMock, 0, 6);
		
		when(pitRepositoryMock.findByBoardAndPosition(boardMock, 0)).thenReturn(pit);
		
		Pit result = pitService.updatePitNumberOfStones(boardMock, 0, 10, false);
		assertEquals(new Integer(10), 
				result.getValue());
		result = pitService.updatePitNumberOfStones(boardMock, 0, 10, true);
		assertEquals(new Integer(20), 
				result.getValue());
		verify(pitRepositoryMock, times(2)).save(result);
		
	}		
	
	@Test
	public void testStealPit () {
		Board boardMock = mock(Board.class);
		Pit pit = new Pit (boardMock, 0, 6);
		Pit pit2 = new Pit (boardMock, 8, 6);
		when(pitRepositoryMock.findByBoardAndPosition(boardMock, 0)).thenReturn(pit);
		when(pitRepositoryMock.findByBoardAndPosition(boardMock, 8)).thenReturn(pit2);
		
		Pit result = pitService.updatePitStealPit(boardMock, 0, 8);
		assertEquals(new Integer(12), 
				result.getValue());
		verify(pitRepositoryMock, times(1)).save(result);
		
	}		
	
	@Test
	public void testGetPit () {
		Board boardMock = mock(Board.class);
		Pit pit = new Pit (boardMock, 0, 6);
		
		when(pitRepositoryMock.findByBoardAndPosition(boardMock, 0)).thenReturn(pit);
		
		Integer value = pitService.getPitNumberOfStonesByBoardAndPosition(boardMock, 0);
		assertEquals(value, new Integer(6));
		
		
	}
	
	@Test
	public void testPit () {
		new PitService();
	}
}
