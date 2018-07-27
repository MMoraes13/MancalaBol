INSERT INTO game (player_one_id, player_two_id, turn_of_with_id, is_over) VALUES (1, 2, 1, false);


CREATE TABLE pit (
	id BIGINT (20) PRIMARY KEY AUTO_INCREMENT, 
	board_id BIGINT (20) NOT NULL,
	pit_number_in_game INT NOT NULL,
	value INT NOT NULL,
	FOREIGN KEY (board_id) REFERENCES board (id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;
