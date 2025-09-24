/*
    1) vo 생성 및 내용 추가
    2) dao 생성 및 내용 추가
    3) mapper에 쿼리 생성
    4) servlet 생성 및 내용 추가
    5) 포워딩된 데이터를 jsp에서 가공
*/

-- 학생 정보 관리 테이블
create sequence seq_sungtb_no;

create table sungtb(
	no Number(3) primary key,
	name varchar2(50),
	kor number(3),
	eng number(3),
	mat number(3)
);

-- 샘플 데이터
insert into sungtb values(
	seq_sungtb_no.nextVal,
	'일길동',
	77, 88, 99
);

commit;

-- 부서 테이블
create table dept(
	deptno number(2) primary key,
	dname varchar2(20),
	loc varchar2(20)
);

-- 일련번호 관리 객체
create sequence seq_member_idx;

-- 회원 테이블
create table member(
    idx number(3) primary key,
    name VARCHAR2(50),
    id VARCHAR2(50),
    pwd VARCHAR2(50),
    email VARCHAR2(100),
    addr VARCHAR2(200)
);

-- 샘플 데이터
insert into member values(seq_member_idx.nextVal, 
    '홍길동', 
    'one', 
    '1234', 
    'one@kor.com', 
    '서울시 은평구'
);

commit;