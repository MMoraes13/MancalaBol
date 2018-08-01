package com.bol.interviews.kalaha.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bol.interviews.kalaha.model.Player;
import com.bol.interviews.kalaha.repository.PlayerRepository;

@Service
@Transactional
public class PlayersService {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	
	
	public List<Player> findAll () {
		return playerRepository.findAll();
	}

}
