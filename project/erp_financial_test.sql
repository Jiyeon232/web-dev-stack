-- 재무관리
-- 급여 관리
CREATE TABLE salary(
	salary_no INT AUTO_INCREMENT PRIMARY KEY, -- 급여 번호
    salary_date DATE, -- 지급일
    base_salary INT, -- 기본급
    bonus INT, -- 보너스
    -- overtime INT, -- 초과근무 수당 (OT)
    deduction INT, -- 공제 금액
    tax INT, -- 세금
    
    emp_no INT, -- 사원 번호
    bonus_payment_no INT -- 보너스 수당 번호
);
INSERT INTO salary(salary_date, base_salary, emp_no)
VALUES('2025-07-25', 20000000, 1);
INSERT INTO salary(salary_date, base_salary, emp_no)
VALUES('2025-07-25', 25000000, 2);
INSERT INTO salary(salary_date, base_salary, emp_no)
VALUES('2025-07-25', 35000000, 3);
INSERT INTO salary(salary_date, base_salary, emp_no)
VALUES('2025-07-25', 40000000, 4);
INSERT INTO salary(salary_date, base_salary, emp_no)
VALUES('2025-07-25', 28000000, 5);
INSERT INTO salary(salary_date, base_salary, bonus, emp_no)
VALUES('2025-07-25', 28000000, 400000, 6);
INSERT INTO salary(salary_date, base_salary, bonus, deduction, emp_no)
VALUES('2025-07-25', 30000000, 300000, 150000, 7);
SELECT * FROM salary;
SELECT dept_name, emp_name, salary_date, base_salary, bonus, deduction, tax , payment
FROM salary 
JOIN employee_info USING(emp_no)
JOIN department USING(dept_no)
JOIN bonus_payment USING(bonus_payment_no);

-- 예산 계획
CREATE TABLE budget(
	budget_no INT AUTO_INCREMENT PRIMARY KEY, -- 예산 번호
    period_type VARCHAR(2) CHECK (period_type IN ('Y', 'Q', 'M')), --  (연/분기/월: Y/Q/M)
    period_value VARCHAR(10), -- 적용 기간 값 (예 : 2025, 2025-Q1 등)
    annual_budget INT, -- 예산 금액
    -- target_sales INT, -- 목표 매출
    plan TEXT, -- 계획 상세
	-- achieved VARCHAR(2) CHECK (achieved IN ('T', 'F')), -- 목표 달성 여부	
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성일시
    dept_no INT -- 부서 번호
);
INSERT INTO budget(period_type, period_value, annual_budget, plan, created_at, dept_no) 
VALUES('Y', '2025-Y1 ', 200000, 'plan', '2025-01-01', 1);
INSERT INTO budget(period_type, period_value, annual_budget, plan, created_at, dept_no) 
VALUES('Q', '2025-Q2 ', 100000, 'plan', '2025-01-01', 1);
INSERT INTO budget(period_type, period_value, annual_budget, plan, created_at, dept_no) 
VALUES('Q', '2025-Q3 ', 100000, 'plan', '2025-01-01', 2);
INSERT INTO budget(period_type, period_value, annual_budget, plan, created_at, dept_no) 
VALUES('Y', '2025-Y1 ', 200000, 'plan', '2025-01-01', 5);
SELECT * FROM budget;
DROP TABLE budget;

SELECT CONCAT(YEAR(created_at), '-', period_type, budget_no) FROM budget;


-- 수입/지출 관리
CREATE TABLE transaction(
	trans_no INT AUTO_INCREMENT PRIMARY KEY, -- 거래 번호
    trans_type VARCHAR(10) CHECK (trans_type IN ('수입', '지출')), -- 수입/지출
    trans_amount INT, -- 금액
    category VARCHAR(50), -- 분류
    trans_desc TEXT, -- 수입/지출 내역 상세
    trans_date DATE, -- 수입/지출 발생 일자
    -- emp_no INT, -- 직원 번호
    dept_no INT -- 부서 번호 (통계 처리 하려면) 
);
INSERT INTO transaction(trans_type, trans_amount, category, trans_desc, trans_date, dept_no) 
VALUES('수입', 100000, '판매대금', 'desc', '2025-07-22', 1);
INSERT INTO transaction(trans_type, trans_amount, category, trans_desc, trans_date, dept_no) 
VALUES('지출', 100000, '매입대금', 'desc', '2025-07-22', 2);
INSERT INTO transaction(trans_type, trans_amount, category, trans_desc, trans_date, dept_no) 
VALUES('지출', 200000, '매입대금', 'desc', '2025-07-22', 3);
INSERT INTO transaction(trans_type, trans_amount, category, trans_desc, trans_date, dept_no) 
VALUES('수입', 150000, '판매대금', 'desc', '2025-08-22', 5);
INSERT INTO transaction(trans_type, trans_amount, category, trans_desc, trans_date, dept_no) 
VALUES('지출', 3000000, '매입대금', 'desc', '2025-08-23', 4);
SELECT * FROM transaction;
DROP TABLE transaction;


-- 의류 ERP (매입 내역 관리용) -> 외부에서 구매한 내역
-- 상품/자재 , 매입일, 단가/수랑, 부가세, 공급업체, 부서
CREATE TABLE purchase(
	purchase_no INT AUTO_INCREMENT PRIMARY KEY, -- 매입 번호
    -- vendor VARCHAR(100), -- 공급업체
    unit_price INT, -- 단가
    quantity INT, -- 수량
    var_amount INT, -- 부가세 총액
    total_amount INT, -- 총액 unit_price * quantity 
    purchase_date DATE, -- 매입일
    product_code INT NOT NULL -- 상품 번호 외래키
);
INSERT INTO purchase(unit_price, quantity, var_amount, total_amount, purchase_date, product_code) 
VALUES(1000, 200, 100000, 5000000, '2025-07-21', 1);
INSERT INTO purchase(unit_price, quantity, var_amount, total_amount, purchase_date, product_code) 
VALUES(2000, 500, 300000, 10000000, '2025-07-23', 4);
SELECT * FROM purchase;
DROP TABLE purchase;


CREATE TABLE sale_manage(
	sm_no INT AUTO_INCREMENT PRIMARY KEY, -- 매출 번호
    sale_date DATE, -- 매출 발생일자
    quantity INT, -- 수량
    var_amount INT, -- 부가세
    total_amount INT, -- 총액
    product_code INT NOT NULL -- 품목 번호 외래키
);
INSERT INTO sale_manage(sale_date, quantity, var_amount, total_amount, product_code) 
VALUES('2025-07-21', 10, 10000, 2000000, 1);
INSERT INTO sale_manage(sale_date, quantity, var_amount, total_amount, product_code) 
VALUES('2025-07-22', 20, 50000, 2500000, 2);
INSERT INTO sale_manage(sale_date, quantity, var_amount, total_amount, product_code) 
VALUES('2025-06-22', 20, 50000, 2000000, 5);
SELECT * FROM sale_manage;
DROP TABLE sale_mamage;

SELECT product_name, unit_price, quantity, var_amount, total_amount, purchase_date
FROM purchase
JOIN product_name USING(product_code)
WHERE product_category = '상의';

SELECT product_name, unit_price, quantity, var_amount, total_amount, purchase_date
FROM purchase
JOIN product_name USING(product_code)
WHERE product_name LIKE '%셔츠%';

SELECT trans_no, trans_type, trans_amount, category, trans_desc, trans_date, dept_name
FROM transaction
JOIN department USING(dept_no);

SELECT * FROM department;
SELECT period_type, annual_budget, plan, dept_name 
FROM budget JOIN department USING(dept_no)
WHERE dept_name = '인사팀';

SELECT * FROM product_name;
SELECT sm_no, sale_date, quantity, var_amount, total_amount, product_name 
FROM sale_manage
JOIN product_name USING(product_code)
WHERE product_category = '상의';

SELECT sm_no, sale_date, quantity, var_amount, total_amount, product_name 
FROM sale_manage
JOIN product_name USING(product_code)
WHERE product_category = '상의'
AND product_name LIKE CONCAT ('%폴로%');

SELECT sm_no, sale_date, quantity, var_amount, total_amount, product_name  
FROM sale_manage 
JOIN product_name USING(product_code)
WHERE sale_date LIKE '%2025-07%';


-- 예산 편성(관리자)
-- INSERT INTO budget(period_type, period_value, annual_budget, plan, created_at, dept_no) 
-- VALUES(#{periodType}, #{periodValue}, #{annualBudget}, #{plan}, #{createdAt}, #{deptNo});
-- 예산 전체 조회
SELECT dept_name, period_type, annual_budget, plan FROM budget JOIN department USING(dept_no);
-- 부서별 예산 선택 조회
SELECT dept_name, period_type, annual_budget, plan FROM budget JOIN department USING(dept_no) WHERE dept_name = '마케팅팀';
-- 매출 등록
-- INSERT INTO sale_manage(sale_date, quantity, var_amount, total_amount, product_code)
-- VALUES(#{saleDate}, #{quantity}, #{varAmount}, #{totalAmount}, #{product_code});
-- 전체 매출 내역 조회
SELECT * FROM sale_manage;
-- 매출 내역 선택 조회 (품목별, 카테고리별, 기간별)
SELECT * FROM sale_manage JOIN product_name USING(product_code) WHERE product_name = '티셔츠';
SELECT * FROM sale_manage JOIN product_name USING(product_code) WHERE category = '상의';
SELECT * FROM sale_manage WHERE sale_date LIKE '%2025-07%';
-- 부서별 지출 내역 조회
SELECT * FROM transaction JOIN department USING(dept_no) WHERE dept_name = '마케팅팀';
-- 카테고리별 지출 내역 조회
SELECT * FROM transaction WHERE category = '상의';
-- 전체 매입 내역 조회
SELECT * FROM purchase;
-- 월별 매입 내역 조회
SELECT * FROM purchase WHERE purchase_date LIKE '%2025-07%';
-- 급여 자동 계산/조회
SELECT emp_name, salary_date, (base_salary+bonus+payment-deduction-tax) 
FROM salary 
JOIN employee_info USING(emp_no)
JOIN department USING(dept_no)
JOIN bonus_payment USING(bonus_payment_no);
-- 전체 거래내역 조회
SELECT trans_no, trans_type, trans_amount, category, trans_desc, trans_date, dept_name FROM transaction JOIN department USING(dept_no);
-- 거래내역 선택 조회 (수입별/지출별)
SELECT trans_no, trans_type, trans_amount, category, trans_desc, trans_date, dept_name 
FROM transaction JOIN department USING(dept_no)
WHERE trans_type = '수입';
-- 거래내역 선택 조회 (월별)
SELECT trans_no, trans_type, trans_amount, category, trans_desc, trans_date, dept_name 
FROM transaction JOIN department USING(dept_no)
WHERE trans_date LIKE '%2025-07%';
-- 매입 단가 및 수량 등록
-- INSERT INTO purchase(product_code, unit_price, quantity, var_amount, total_amount, purchase_date) 
-- VALUES(#{productCode}, #{unitPrice}, #{quantity}, #{varAmount}, #{totalAmount}, #{purchaseDate});
-- 거래내역 등록
-- INSERT INTO transaction(trans_type, trans_amount, category, trans_desc, trans_date, dept_no) 
-- VALUES(#{transType}, #{transAmount}, #{category}, #{transDesc}, #{transDate}, #{deptNo});






/*
-- 재무관리 테이블 초안
CREATE TABLE financial (
 transaction_no INT AUTO_INCREMENT PRIMARY KEY, -- 거래번호
 budget INT, -- (배정된) 예산 (-> 잔액)
 -- sales INT, -- 매출액 (+ 금액)
 -- cost INT, -- 비용 (- 금액)
 transaction_amount INT, -- 거래 금액 
 STATUS VARCHAR(20) CHECK (STATUS IN ('매출액', '인건비', '매입 비용', '기타 비용')),
 transaction_desc TEXT, -- 거래내역
 transaction_date DATETIME DEFAULT (CURRENT_DATE), -- 거래 발생 날짜
 tax INT, -- 세금
 
 product_no INT, -- 제품 번호  foreign key
 dept_no INT, -- 부서 번호 foreign key
 user_no INT -- 사원 번호 foreign key
);
*/