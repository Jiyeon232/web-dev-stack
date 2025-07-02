CREATE TABLE member(
	id VARCHAR(100) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL
);
SELECT * FROM member;

-- -----------------------------------
DROP TABLE bank;
CREATE TABLE bank(
	name VARCHAR(100),
    balance INT
);
INSERT INTO bank VALUES('지은', 100000);
INSERT INTO bank VALUES('지연', 0);
SELECT * FROM bank;

UPDATE bank SET balance = balance - 30000 WHERE name = '지은';
UPDATE bank SET balance = balance + 30000 WHERE name = '지연';
SELECT balance FROM bank WHERE name = '지은' ;

-- -----------------------------------
DROP TABLE person;
CREATE TABLE person(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    age INT,
    addr VARCHAR(200)
);
SELECT * FROM person;

INSERT INTO person(name, age, addr) VALUES('이름', 10, '서울');
SELECT * FROM person WHERE id = 1;
UPDATE person SET addr = '경기도' WHERE id = 1;
DELETE FROM person WHERE id = 1;