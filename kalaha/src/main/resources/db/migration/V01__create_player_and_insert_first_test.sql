CREATE TABLE player (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) not null 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO player (name) VALUES ('Carl Sagan');
INSERT INTO player (name) VALUES ('Richard Feynman');
