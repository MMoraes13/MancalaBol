package com.bol.interviews.kalaha.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bol.interviews.kalaha.model.Player;
import com.bol.interviews.kalaha.repository.PlayerRepository;
@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepository playerRepository;

	public PlayerService() {
		// TODO Auto-generated constructor stub
	}
	
	public Player createPlayer (Player player) {
		playerRepository.save(player);
		return player;
	}
	
	public Optional<Player> findById (Long id) {
		return playerRepository.findById(id);
	}

}
