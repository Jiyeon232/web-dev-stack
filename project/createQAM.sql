
-- 제품 테이블
CREATE TABLE product( -- 
product_no INT AUTO_INCREMENT PRIMARY KEY,
production_date DATE NOT NULL, -- 생산일
-- sale_date DATE, -- 판매일
product_code INT -- 제품명 외래키
);

-- 제품명 테이블(제품에 대한 모든 정보)
CREATE TABLE product_name(
product_code INT AUTO_INCREMENT PRIMARY KEY,
-- 제품명product_size VARCHAR(10) NOT NULL, -- 제품 사이즈
product_color VARCHAR(10) NOT NULL, -- 제품 색깔(제품명에 들어가야할지?)
product_name VARCHAR(20) NOT NULL, -- 제품명
product_price INT NOT NULL, -- 제품 가격
product_cost INT NOT NULL, -- 단가
product_category VARCHAR(20) NOT NULL -- 카테고리
-- 생산 브랜드(거래사 테이블과 연관?)

);

-- 제품 종류 테이블
-- CREATE TABLE category(
-- category_code INT AUTO_INCREMENT PRIMARY KEY,
-- category_name VARCHAR(20) -- 카테고리명
-- );

-- 품질검사 테이블
CREATE TABLE qc( 
qc_code INT AUTO_INCREMENT PRIMARY KEY,
check_material VARCHAR(30) , -- 부자재 검사
check_color VARCHAR(30) , -- 색깔 검사
check_damage VARCHAR(30) , -- 손상 검사
qc_desc text, -- 평가 결과 특이사항
-- qc_category VARCHAR(30) NOT NULL, -- 품질 검사 종류(입고, 보관, 출고)
qc_date DATE, -- 품질 검사 날짜
emp_no INT, -- 직원 외래키 (검사자)
product_no INT NOT NULL -- 제품 외래키 

);

-- 판매 테이블 불량 테이블 따로 만들어야하는지? 

-- 판매 테이블
CREATE TABLE sale(
sale_no INT AUTO_INCREMENT PRIMARY KEY,
sale_date DATE, -- 판매일
product_no INT NOT NULL -- 상품 외래키
);

-- 불량품 테이블
CREATE TABLE defective(
defective_no INT AUTO_INCREMENT PRIMARY KEY,

product_no INT NOT NULL -- 상품 외래키
);

ALTER TABLE product ADD
FOREIGN KEY (product_code) REFERENCES product_name(product_code);
ALTER TABLE qc ADD
FOREIGN KEY (emp_no) REFERENCES employee_info(emp_no);
ALTER TABLE qc ADD
FOREIGN KEY (product_no) REFERENCES product(product_no);
ALTER TABLE sale ADD
FOREIGN KEY (product_no) REFERENCES product(product_no);
ALTER TABLE defective ADD
FOREIGN KEY (product_no) REFERENCES product(product_no);

-- 불량품 처리 테이블
-- CREATE TABLE defective_handle(
-- dh_no INT AUTO_INCREMENT PRIMARY KEY,
-- dh_method VARCHAR(20) NOT NULL, -- 불량품 처리 방식
-- dh_check BOOLEAN, -- 처리 결과
-- product_no INT NOT NULL -- 상품 외래키 
-- );