DROP TABLE member;
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

SELECT * FROM person WHERE name = 'aaa' AND age = 123 AND addr = 'aaa';
INSERT INTO person(name, age, addr) VALUES('이름', 10, '서울');
SELECT * FROM person WHERE id = 1;
UPDATE person SET addr = '경기도' WHERE id = 1;
DELETE FROM person WHERE id = 1;

-- -----------------------------------
DROP TABLE rent;
DROP TABLE book;
DROP TABLE member;

DELETE FROM book;
DELETE FROM member;
DELETE FROM rent;

CREATE TABLE book(
	book_no INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(50) NOT NULL,
    access_age INT DEFAULT 0
);

CREATE TABLE member(
	id VARCHAR(100) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    pwd VARCHAR(200) NOT NULL,
    age INT
);

CREATE TABLE rent(
	rent_no INT PRIMARY KEY AUTO_INCREMENT,
    id VARCHAR(100),
    book_no INT,
    rent_date DATE DEFAULT (CURRENT_DATE)
);
-- foreign key
ALTER TABLE rent ADD
FOREIGN KEY (id) REFERENCES member(id) ON DELETE CASCADE;
ALTER TABLE rent ADD
FOREIGN KEY (book_no) REFERENCES book(book_no);

SELECT * FROM book;
SELECT * FROM member;
SELECT * FROM rent;

-- 내가 대여한 책 조회 --> rent, book 테이블 조인!
SELECT * FROM rent JOIN book USING(book_no) WHERE id = 'aaa';

INSERT INTO member VALUES('admin', '관리자', '1234', 100); -- 관리자 계정
INSERT INTO member VALUES('aaa', '사용자', 'bbb', 10); -- 사용자
INSERT INTO book(title, author, access_age) VALUES('책 제목', '저자', 1);
INSERT INTO rent(id, book_no) VALUES('aaa', 1);
SELECT id, pwd FROM member WHERE id = 'aaa' AND pwd = 'bbb';
SELECT * FROM book WHERE title = '책 제목' AND author = '저자' AND access_age = 1;
SELECT book_no FROM book WHERE title = 'aaa';
DELETE FROM member WHERE id = 'aaa';
DELETE FROM book WHERE book_no = 1;
DELETE FROM rent WHERE rent_no = 2;

-- -------------------------------------------
DROP TABLE board;
CREATE TABLE board (
    no INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    content TEXT,
    url VARCHAR(100),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
SELECT * FROM board;
SELECT count(*) FROM board;

-- -------------------------------------------
DROP TABLE user;
CREATE TABLE user(
	id VARCHAR(50) PRIMARY KEY,
	pwd VARCHAR(100),
	name VARCHAR(100),
	role VARCHAR(20) DEFAULT 'ROLE_USER'
);
SELECT * FROM user;