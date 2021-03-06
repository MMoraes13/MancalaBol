package com.bol.interviews.kalaha.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bol.interviews.kalaha.config.WebSocketResource;
import com.bol.interviews.kalaha.model.Board;
import com.bol.interviews.kalaha.model.Game;
import com.bol.interviews.kalaha.model.Player;
import com.bol.interviews.kalaha.service.BoardService;
import com.bol.interviews.kalaha.service.GameService;
import com.bol.interviews.kalaha.service.PitService;
import com.bol.interviews.kalaha.service.PlayService;

@RestController
@RequestMapping ("/game")
public class GameResource {
	public static final Integer NUMBER_OF_STONES = 6;
	public static final Integer ZERO = 0;
	@Autowired
	private GameService gameService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private PitService pitService;
	@Autowired
    private WebSocketResource webSocketResource;
	
	@PostMapping(value="/create")
	@ResponseStatus(HttpStatus.CREATED)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Game> createNewGame (@RequestBody Player pOne, HttpServletResponse response) {
		//Optional <Player> playerOne = playerService.findById(pOne.getId());
		
		Game createdGame;
		Board board;
		createdGame = gameService.createNewGame(pOne, pOne);
		board = boardService.createNewBoard(createdGame);
		// PITS PLAYER ONE
		for (int i = PlayService.PIT_0_PLAYER_ONE; i < PlayService.KALAHA_PLAYER_ONE; i++) {
			pitService.createNewPit(board, i, NUMBER_OF_STONES);
		}
		// KAHALA PLAYER ONE
		pitService.createNewPit(board, PlayService.KALAHA_PLAYER_ONE, ZERO);
		
		// PITS PLAYER ONE
		for (int i = PlayService.PIT_0_PLAYER_TWO; i < PlayService.KALAHA_PLAYER_TWO; i++) {
			pitService.createNewPit(board, i, NUMBER_OF_STONES);
		}
		// KAHALA PLAYER ONE
		pitService.createNewPit(board, PlayService.KALAHA_PLAYER_TWO, ZERO);
		
		//simpMessagingTemplate.convertAndSend("/update/lobby", "update");
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{gameId}")
				.buildAndExpand (createdGame.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		
		return ResponseEntity.created(uri).body(createdGame);	
		
	}
	
	
	@RequestMapping(value="/{gameId}", method=RequestMethod.GET)	
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Optional<Game>> findGame (@PathVariable Long gameId) {
		Optional<Game> game = gameService.findById(gameId);
		
		if (game.isPresent()) {
			return ResponseEntity.ok(game);
		}
		return ResponseEntity.notFound().build();	
	}
	
	@PatchMapping(value="/join/{gameId}")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Game> joinGame (@PathVariable Long gameId, @RequestBody Player player) {
		Optional<Game> game = gameService.findById(gameId);		
		ResponseEntity <Game> answer = validateJoin(game);
		if (answer == null) {
			Game savedGame = game.get();
			savedGame.setPlayerTwo(player);	
			webSocketResource.publishWebSocket("update");
			return ResponseEntity.ok(gameService.joinGame(savedGame));
		}
		
		return answer;		
	}
	@GetMapping
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseBody
    public List<Game> getGamesToJoin() {
		List <Game> games = gameService.getGamesToJoin();
        return games;
    }
    
	@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/player/{player}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Game> getPlayerGames(Player player) {
    	List<Game> games = gameService.getPlayerGames(player);
        return games;
    }
	
	private ResponseEntity <Game> validateJoin (Optional<Game> game) {
		if (!game.isPresent()) return ResponseEntity.notFound().build();		
		if (!game.get().getPlayerOne().equals(game.get().getPlayerTwo()))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); //if game is already full
		
		return null;
	}
	
}
