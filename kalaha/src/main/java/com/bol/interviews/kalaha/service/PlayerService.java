package com.bol.interviews.kalaha.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;

import com.bol.interviews.kalaha.model.Player;
import com.bol.interviews.kalaha.repository.PlayerRepository;
@Service
public class PlayerService {
	
	
	@Autowired
	private PlayerRepository playerRepository;

	public PlayerService() {
		// TODO Auto-generated constructor stub
	}
	
	public PlayerService(MockMvc mockMvc) {
		// TODO Auto-generated constructor stub
	}

	public Player createPlayer (Player player) {		
		return playerRepository.save(player);
	}
	
	public Optional<Player> findById (Long id) {
		return playerRepository.findById(id);
	}

}
