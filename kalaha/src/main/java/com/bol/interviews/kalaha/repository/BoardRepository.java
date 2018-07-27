package com.bol.interviews.kalaha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bol.interviews.kalaha.model.Board;
import com.bol.interviews.kalaha.model.Game;

public interface BoardRepository extends JpaRepository <Board, Long> {
	
	public Optional<Board> findByGame(Game game);

}
