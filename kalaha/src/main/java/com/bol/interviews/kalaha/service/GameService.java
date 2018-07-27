package com.bol.interviews.kalaha.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bol.interviews.kalaha.model.Board;
import com.bol.interviews.kalaha.model.Game;
import com.bol.interviews.kalaha.model.Player;
import com.bol.interviews.kalaha.repository.BoardRepository;
import com.bol.interviews.kalaha.repository.GameRepository;
import com.bol.interviews.kalaha.repository.PlayerRepository;
@Service
public class GameService {

	
	@Autowired
	private GameRepository gameRepository;
	
	public GameService() {

	}
	
	public Game createNewGame (Player playerOne, Player playerTwo) {		
		return gameRepository.save(new Game(playerOne, playerTwo)); 
	}

	public Optional<Game> findById(Long gameId) {
		return gameRepository.findById(gameId);
	}
	
	public Game changeTurn (Game game) {
		if (game.getTurnOfWithId().equals(game.getPlayerOne())) game.setTurnOfWithId(game.getPlayerTwo());
		else game.setTurnOfWithId(game.getPlayerOne());		
		
		return game;
	}

	public Game joinGame(Game game) {

		return gameRepository.save(game);
	}

}
