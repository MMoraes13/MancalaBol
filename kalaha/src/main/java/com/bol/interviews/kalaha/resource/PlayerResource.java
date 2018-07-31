package com.bol.interviews.kalaha.resource;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bol.interviews.kalaha.model.Player;
import com.bol.interviews.kalaha.service.PlayerService;

@RestController
@RequestMapping ("/player")
public class PlayerResource {
	
	@Autowired
	private PlayerService playerService;
	
	@RequestMapping(value="/{playerId}", method=RequestMethod.GET)	
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseBody
	public ResponseEntity<Optional<Player>> findPlayer (@PathVariable Long playerId) {
		Optional<Player> player = playerService.findById(playerId);	
		if (player.isPresent()) {
			return ResponseEntity.ok(player);
		}
		return ResponseEntity.notFound().build();		
	}
	
	@PostMapping(value="/create")
	@ResponseStatus(HttpStatus.CREATED)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Player> createNewPlayer (@Valid @RequestBody Player player, HttpServletResponse response) {
		
		Player savedPlayer = playerService.createPlayer(player);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{player}")
				.buildAndExpand(savedPlayer.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(savedPlayer);
	}



}
