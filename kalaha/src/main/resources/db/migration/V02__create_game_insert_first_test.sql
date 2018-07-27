CREATE TABLE game (
	id BIGINT (20) PRIMARY KEY AUTO_INCREMENT,
	player_one_id BIGINT (20) NOT NULL,
	player_two_id BIGINT (20) NOT NULL,
	turn_of_with_id BIGINT (20) NOT NULL,
	is_over BOOL,
	FOREIGN KEY (player_one_id) REFERENCES player(id),
	FOREIGN KEY (player_two_id) REFERENCES player(id),
	FOREIGN KEY (turn_of_with_id) REFERENCES player(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;
