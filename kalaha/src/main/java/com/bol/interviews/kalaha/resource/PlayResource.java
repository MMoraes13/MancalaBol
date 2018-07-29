package com.bol.interviews.kalaha.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bol.interviews.kalaha.repository.BoardRepository;
import com.bol.interviews.kalaha.repository.GameRepository;
import com.bol.interviews.kalaha.repository.PlayerRepository;
import com.bol.interviews.kalaha.service.PlayService;
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

	@Autowired
	private PlayService playService;
	
	@Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
	
	
	
	@RequestMapping(value="/{gameId}/{playerId}/{position}", method = RequestMethod.POST)
	public ResponseEntity<Board> movePlay (@PathVariable Long gameId, @PathVariable Long playerId, @PathVariable Integer position) {
		Optional<Player> player = playerRepository.findById(playerId);
		Optional<Game> game = gameRepository.findById(gameId);
		Optional <Board> board = boardRepository.findByGame(game.get());
		Board resultBoard;
		if (player.isPresent() && game.isPresent() && board.isPresent())  {
			if (!playService.checkGameOver(board.get())) {
				if (game.get().getPlayerOne().equals(game.get().getTurnOfWithId()) && position < PlayService.PIT_0_PLAYER_ONE && position >= PlayService.KALAHA_PLAYER_ONE)
					return ResponseEntity.badRequest().build();
				else if (game.get().getPlayerTwo().equals(game.get().getTurnOfWithId()) && position < PlayService.PIT_0_PLAYER_TWO && position >= PlayService.KALAHA_PLAYER_TWO)
					return ResponseEntity.badRequest().build();
				
				resultBoard = playService.movePlay(board.get(), player.get(), position);
				simpMessagingTemplate.convertAndSend("/update/game/"+resultBoard.getGame().getId(), "moved");
				if (playService.checkGameOver(board.get())) {
					playService.finishGame(board.get().getGame());
					simpMessagingTemplate.convertAndSend("/update/gameslist/"+resultBoard.getGame(), "update");
				}
				
				return ResponseEntity.ok(resultBoard);
			}			
		}
		return ResponseEntity.badRequest().build();
	}
}

