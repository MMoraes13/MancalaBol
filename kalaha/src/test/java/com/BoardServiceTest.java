package com;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bol.interviews.kalaha.model.Board;
import com.bol.interviews.kalaha.model.Game;
import com.bol.interviews.kalaha.repository.BoardRepository;
import com.bol.interviews.kalaha.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
public class BoardServiceTest {
	@Mock
    private BoardRepository boardRepositoryMock;
    
    private BoardService boardService;

	
	@Before
	public void init () {
		boardService = new BoardService(boardRepositoryMock);
	}
    
	
	@Test
	public void testCreateBoard () {
		Game game = mock (Game.class);
		Board board = boardService.createNewBoard(game);
		
		verify (boardRepositoryMock, times(1)).save(board);
	}
}
