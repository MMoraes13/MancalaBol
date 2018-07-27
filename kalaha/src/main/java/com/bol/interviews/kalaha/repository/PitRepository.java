package com.bol.interviews.kalaha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bol.interviews.kalaha.model.Board;
import com.bol.interviews.kalaha.model.Pit;

public interface PitRepository extends JpaRepository <Pit, Long>{
	
	public Optional <Pit> findByBoardAndPosition (Board board, Integer position);

}
