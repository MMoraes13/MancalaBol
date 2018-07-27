package com.bol.interviews.kalaha.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bol.interviews.kalaha.model.Board;
import com.bol.interviews.kalaha.model.Game;
import com.bol.interviews.kalaha.repository.BoardRepository;
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	public BoardService() {
		// TODO Auto-generated constructor stub
	}
	
	public Board createNewBoard (Game game) {
		return boardRepository.save(new Board (game));
		
	}
	public Optional <Board> getBoardById (Long id) {
		return boardRepository.findById(id);
	}
	
	public Optional <Board> getBoardByGame (Game game) {
		return boardRepository.findByGame(game);
	}

}
