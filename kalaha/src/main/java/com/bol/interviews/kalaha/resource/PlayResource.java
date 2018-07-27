package com.bol.interviews.kalaha.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bol.interviews.kalaha.repository.BoardRepository;
import com.bol.interviews.kalaha.repository.GameRepository;
import com.bol.interviews.kalaha.repository.PlayerRepository;
import com.bol.interviews.kalaha.model.Board;
import com.bol.interviews.kalaha.model.Game;
import com.bol.interviews.kalaha.model.Player;

@RestController
@RequestMapping ("/play")
public class PlayResource {
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	
	@RequestMapping(value="/{gameId}/{playerId}/{position}", method = RequestMethod.POST)
	public ResponseEntity<Optional<Board>> movePlay (@PathVariable Long gameId, @PathVariable Long playerId, @PathVariable Long position) {
		Optional<Player> player = playerRepository.findById(playerId);
		if (player.isPresent())  {
			Optional<Game> game = gameRepository.findById(gameId);
			if (game.isPresent()) {
				Optional <Board> board = boardRepository.findByGame(game.get());
				
				return ResponseEntity.ok(board);
			}
		}
		return ResponseEntity.notFound().build();	
		
	}
}

