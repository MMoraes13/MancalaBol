package com.bol.interviews.kalaha.model;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    public void testCreatePitStore() {
        // Test specific init
        Board boardMock = mock(Board.class);
        
        Pit result = pitService.createNewPit(boardMock, 0, 6);
        assertEquals (result.getBoard(), boardMock);
        
        assertEquals (result.getPosition(), new Integer(0));
        assertEquals (result.getValue(), new Integer(6));

    }
    
    @Test
    public void testUpdateNumberOfStones() {
        // Test specific init
        Board boardMock = mock(Board.class);
        int position = 10;
        int amount = 6;

        Pit pit = pitService.createNewPit(boardMock, position, amount);
        
        // Call to SUT
        Pit result = pitService.updatePitNumberOfStones(boardMock, position, amount, true);   
        //assertNotNull (result.getValue());
        
    }
}
