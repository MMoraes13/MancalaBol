package com.bol.interviews.kalaha.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bol.interviews.kalaha.service.PlayerService;

public class TestPlayerService {
	
	@Autowired
	private PlayerService playerService;
	
	public TestPlayerService() {
		// TODO Auto-generated constructor stub
	}
	

	@Test
	public void testCreatePlayer () {
		Player createdPlayer = new Player ("Jan"); 
		assertEquals (createdPlayer.getName(), "Jan");
	}
	@Test(expected=NullPointerException.class)
	public void testNullInput() {		
		playerService.createPlayer(new Player (null));
	}	
	@Test
	public void testMinSizeOfName () {
		Player player = new Player ("a");
		assertFalse (player.getName().length() > 2);
		assertTrue (new Player ("Mateus Moraes").getName().length() > 3);
	}
}
