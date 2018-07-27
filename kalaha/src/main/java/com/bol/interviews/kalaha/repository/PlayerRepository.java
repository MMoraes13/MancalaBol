/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bol.interviews.kalaha.repository;

import com.bol.interviews.kalaha.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author mateus
 */
public interface PlayerRepository extends JpaRepository <Player, Long> {
    
}
