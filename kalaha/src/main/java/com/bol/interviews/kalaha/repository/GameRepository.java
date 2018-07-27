package com.bol.interviews.kalaha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bol.interviews.kalaha.model.Game;

public interface GameRepository extends JpaRepository <Game, Long> {

}
