package com.bol.interviews.kalaha.resource;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bol.interviews.kalaha.model.Board;
import com.bol.interviews.kalaha.model.Game;
import com.bol.interviews.kalaha.model.Player;
import com.bol.interviews.kalaha.repository.BoardRepository;
import com.bol.interviews.kalaha.repository.GameRepository;
import com.bol.interviews.kalaha.repository.PlayerRepository;
import com.bol.interviews.kalaha.service.BoardService;
import com.bol.interviews.kalaha.service.GameService;
import com.bol.interviews.kalaha.service.PitService;
import com.bol.interviews.kalaha.service.PlayerService;

@RestController
@RequestMapping ("/game")

public class GameResource {
	public static final Integer NUMBER_OF_STONES = 6;
	public static final Integer MAX_POSITIONS_BOARD = 14;
	
	@Autowired
	private GameService gameService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private PitService pitService;
	@Autowired
	private PlayerService playerService;
	
	
	
	@PostMapping(value="/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Game> createNewGame (@RequestBody Player pOne, HttpServletResponse response) {
		Optional <Player> playerOne = playerService.findById(pOne.getId());
		
		Game createdGame;
		Board board;
		createdGame = gameService.createNewGame(playerOne.get(), playerOne.get());
		board = boardService.createNewBoard(createdGame);
		
		for (int i = 0; i < MAX_POSITIONS_BOARD; i++) {
			pitService.createNewPit(board, i, NUMBER_OF_STONES);
		}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{gameId}")
				.buildAndExpand (createdGame.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(createdGame);	
		
	}
	
	
	@RequestMapping(value="/{gameId}", method=RequestMethod.GET)	
	@ResponseBody
	public ResponseEntity<Optional<Game>> findGame (@PathVariable Long gameId) {
		Optional<Game> game = gameService.findById(gameId);	
		if (game.isPresent()) {
			return ResponseEntity.ok(game);
		}
		return ResponseEntity.notFound().build();	
	}
	
	@RequestMapping(value="/join/{gameId}/{playerId}",  method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Game> joinGame (@PathVariable Long gameId, @PathVariable Long playerId) {
		Optional<Game> game = gameService.findById(gameId);		
		Optional<Player> player = playerService.findById(playerId);
		
		if (!game.isPresent()) return ResponseEntity.notFound().build();
		if (!player.isPresent()) return ResponseEntity.notFound().build();
		if (!game.get().getPlayerOne().equals(game.get().getPlayerTwo()))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); //if game is already full
		
		
		Game savedGame = gameService.joinGame(game.get());
		savedGame.setPlayerTwo(player.get());
		
		return ResponseEntity.ok(gameService.joinGame(savedGame));
		
	}

	
}
