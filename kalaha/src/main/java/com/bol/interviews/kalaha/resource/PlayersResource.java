package com.bol.interviews.kalaha.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bol.interviews.kalaha.model.Player;
import com.bol.interviews.kalaha.repository.PlayerRepository;

@RestController
@RequestMapping ("/players")


public class PlayersResource {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@GetMapping
	public List <Player> listAllPlayers () {
		return playerRepository.findAll();
	}

}
