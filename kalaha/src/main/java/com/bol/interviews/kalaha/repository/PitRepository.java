package com.bol.interviews.kalaha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bol.interviews.kalaha.model.Board;
import com.bol.interviews.kalaha.model.Pit;

public interface PitRepository extends JpaRepository <Pit, Long>{
	
	public Pit findByBoardAndPosition (Board board, Integer position);

}
