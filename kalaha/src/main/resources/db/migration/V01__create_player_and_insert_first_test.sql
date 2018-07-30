CREATE TABLE player (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) not null,
	password VARCHAR (100) not null 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO player (name, password) VALUES ('Carl Sagan', '123');
INSERT INTO player (name, password) VALUES ('Richard Feynman', '123');
