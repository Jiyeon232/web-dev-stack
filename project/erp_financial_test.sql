-- 재무관리

-- 예산 계획
DROP TABLE budget;
CREATE TABLE budget(
	budget_no INT AUTO_INCREMENT PRIMARY KEY, -- 예산 번호
    period_type VARCHAR(2) CHECK (period_type IN ('Y', 'Q', 'M')), --  (연/분기/월: Y/Q/M)
    period_value VARCHAR(10), -- 적용 기간 값 (예 : 2025, 2025-Q1 등)
    annual_budget INT, -- 예산 금액
    -- target_sales INT, -- 목표 매출
    plan TEXT, -- 계획 상세
	-- achieved VARCHAR(2) CHECK (achieved IN ('T', 'F')), -- 목표 달성 여부	
    execution_date DATE, -- 생성일시
    dept_no INT -- 부서 번호
);

SELECT * FROM budget;
SELECT CONCAT(YEAR(execution_date), '-', period_type, budget_no) FROM budget;

UPDATE budget SET period_value = CONCAT(YEAR(execution_date), '-', period_type, budget_no);

SELECT * FROM budget
JOIN department USING(dept_no)
WHERE execution_date LIKE CONCAT('%2025-08%');

-- 남은 예산 계산
SELECT dept_no, dept_name, annual_budget,
    IFNULL(SUM(trans_amount), 0) AS used_amount,
    annual_budget - IFNULL(SUM(trans_amount), 0) AS remaining_budget
FROM department
LEFT JOIN budget USING(dept_no)
LEFT JOIN transaction USING(dept_no) 
WHERE period_type = 'Y' AND trans_type = '지출'
GROUP BY dept_no, dept_name, annual_budget
ORDER BY dept_no;

-- 급여 관리
DROP TABLE salary;
CREATE TABLE salary(
	salary_no INT AUTO_INCREMENT PRIMARY KEY, -- 급여 번호
    salary_date DATE, -- 지급일
    base_salary INT, -- 기본급
    bonus INT, -- 보너스
    -- overtime INT, -- 초과근무 수당 (OT)
    deduction INT, -- 공제 금액
    tax INT, -- 세금
    
    emp_no INT -- 사원 번호
    -- bonus_payment_no INT -- 보너스 수당 번호
);
SELECT * FROM salary;
SELECT * FROM employee_info;

SELECT bonus_payment_no, payment, pay_date, bonus_no, emp_no, dept_name, job_title, bonus_name,emp_name
		FROM bonus_payment
		JOIN employee_info USING(emp_no)
		JOIN department USING(dept_no)
		JOIN job_position USING(job_no)
		JOIN bonus USING(bonus_no);

SELECT emp_name, (base_salary+bonus-deduction) AS total FROM salary
JOIN employee_info USING(emp_no)
WHERE salary_date LIKE CONCAT('%2025-08%');

-- 부서별 월별 인건비 총합 조회
SELECT dept_no, salary_date, SUM(base_salary+bonus-deduction) AS totalSal
FROM salary
JOIN employee_info USING(emp_no)
JOIN department USING(dept_no)
WHERE salary_date LIKE CONCAT('%2025-08%')
GROUP BY dept_no, salary_date;

-- 수입/지출 관리
DROP TABLE transaction;
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
SELECT * FROM transaction;
SELECT * FROM department;

 /*  
CREATE OR REPLACE VIEW vw_balance
AS SELECT trans_no, trans_type, trans_amount, trans_date, trans_desc, annual_budget, dept_name,
	(annual_budget - trans_amount) AS balance
	FROM department
    JOIN budget USING(dept_no)
    JOIN transaction USING(dept_no);
  */  

-- 그룹별(부서->지점) 매출 내역 총액 조회 --> 거래내역 테이블에 금액 입력
SELECT dept_no, SUM(product_price)
	FROM sale
	JOIN product USING(product_no)
	JOIN product_name USING(product_code)
    JOIN department USING (dept_no)
	WHERE sale_date IS NOT NULL
	AND sale_date LIKE CONCAT('%', sale_date,'%')
GROUP BY dept_no;

-- 제품별 매출 내역 총액 조회
SELECT product_name, SUM(product_price)
	FROM sale
	JOIN product USING(product_no)
	JOIN product_name USING(product_code)
	WHERE sale_date IS NOT NULL
	AND sale_date LIKE CONCAT('%', sale_date,'%')
GROUP BY product_name;

-- 날짜별 매출 내역 조회
SELECT sale_date, SUM(product_price)
	FROM sale
	JOIN product USING(product_no)
	JOIN product_name USING(product_code)
    WHERE sale_date IS NOT NULL
GROUP BY sale_date;


-- 의류 ERP (매입 내역 관리용) -> 외부에서 구매한 내역
-- 상품/자재 , 매입일, 단가/수랑, 부가세, 공급업체, 부서
DROP TABLE purchase;
CREATE TABLE purchase(
	purchase_no INT AUTO_INCREMENT PRIMARY KEY, -- 매입 번호
    -- vendor VARCHAR(100), -- 공급업체
    unit_price INT, -- 단가
    quantity INT, -- 수량
    var_amount INT, -- 부가세 총액
    total_amount INT, -- 총액 unit_price * quantity 
    purchase_date DATE, -- 매입일
    product_code INT NOT NULL, -- 상품 번호 외래키
    brand_code INT NOT NULL -- 거래처 번호 외래키
);
DELETE FROM purchase;
SELECT * FROM purchase;
SELECT * FROM brand;
SELECT * FROM transaction;

SELECT brand_code, brand_name, purchase_date, SUM(total_amount) AS total_purchase FROM purchase
JOIN brand USING(brand_code)
WHERE brand_code = 1
AND purchase_date = '2025-08-08'
GROUP BY brand_code, brand_name, purchase_date;

SELECT purchase_no, product_name, product_category,  unit_price, quantity, var_amount, total_amount, purchase_date, brand_name
		FROM purchase
        JOIN brand USING(brand_code)
		JOIN product_name USING(product_code);
        
SELECT DISTINCT product_category
FROM product_name;


-- 일별, 거래처별 총 매입금액 조회하기
SELECT brand_code, purchase_date, SUM(total_amount) AS total_purchase
FROM purchase
JOIN brand USING(brand_code)
WHERE purchase_date = '2025-08-08'
GROUP BY brand_code, purchase_date;

DROP TABLE sale_manage;
CREATE TABLE sale_manage(
	sm_no INT AUTO_INCREMENT PRIMARY KEY, -- 매출 번호
    sale_date DATE, -- 매출 발생일자
    quantity INT, -- 수량
    var_amount INT, -- 부가세
    total_amount INT, -- 총액
    product_code INT NOT NULL -- 품목 번호 외래키
);
SELECT * FROM sale_manage;
SELECT * FROM sale;
INSERT INTO sale_manage(sale_date, quantity, total_amount, product_code)
VALUES('2025-07-18', 100, 24000000, 14);
-- ************************************************
-- 일별 매출액 합계
SELECT sale_date, SUM(total_amount)
FROM sale_manage
WHERE sale_date = '2025-08-08';

-- NOW() : 현재 날짜, 시간 포함 / CURDATE() : 현재 날짜만
-- 오늘 포함 7일
SELECT sale_date, SUM(total_amount) AS sale_amount
FROM sale_manage
WHERE sale_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 6 DAY) AND CURDATE()
GROUP BY sale_date
ORDER BY sale_date;

-- 00:00 ~ 23:59 선택 일주일 치 결과
SELECT sale_date, SUM(total_amount) AS sale_amount
FROM sale_manage
WHERE sale_date > DATE_SUB(CURDATE(), INTERVAL 7 DAY)
	AND sale_date < CURDATE() + INTERVAL 1 DAY
GROUP BY sale_date
ORDER BY sale_date;

-- 오늘 제외하고 7일
SELECT sale_date, SUM(total_amount) AS sale_amount
FROM sale_manage
WHERE sale_date >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)
	AND sale_date < CURDATE()
GROUP BY sale_date
ORDER BY sale_date;

-- 월별 매출액 합계
SELECT SUM(total_amount) AS month_amount
FROM sale_manage
WHERE sale_date LIKE CONCAT('%2025-08%');

-- 현재월 포함 최근 7개월
SELECT DATE_FORMAT(sale_date, '%Y-%m') AS sale_month,
       SUM(total_amount) AS month_amount
FROM sale_manage
WHERE DATE_FORMAT(sale_date, '%Y-%m')
      BETWEEN DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 6 MONTH), '%Y-%m')
          AND DATE_FORMAT(CURDATE(), '%Y-%m')
GROUP BY DATE_FORMAT(sale_date, '%Y-%m')
ORDER BY sale_month;

-- 현재월 제외 최근 6개월
SELECT DATE_FORMAT(sale_date, '%Y-%m') AS sale_month,
       SUM(total_amount) AS month_amount
FROM sale_manage
WHERE DATE_FORMAT(sale_date, '%Y-%m')
      BETWEEN DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 6 MONTH), '%Y-%m')
          AND DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%Y-%m')
GROUP BY DATE_FORMAT(sale_date, '%Y-%m')
ORDER BY sale_month;

SELECT DATE_FORMAT(NOW(), '%Y-%m');

-- 제품별 판매 수량(8월)
SELECT product_name, SUM(quantity) AS month_quantity
FROM sale_manage
JOIN product_name USING(product_code)
WHERE sale_date LIKE CONCAT('%2025-08%')
GROUP BY product_name;

-- 제품별 판매 수량(월별)
SELECT product_name, SUM(quantity) AS month_quantity
FROM sale_manage
JOIN product_name USING(product_code)
WHERE DATE_FORMAT(sale_date, '%Y-%m') = DATE_FORMAT(NOW(), '%Y-%m')
GROUP BY product_name;
-- ************************************************

SELECT * FROM product_name;
SELECT * FROM sale;

-- sale 테이블 플래그 컬럼 추가
ALTER TABLE sale ADD COLUMN sale_registered CHAR(1) DEFAULT 'N';

-- 매출 등록 시 sale_registered 값 업데이트
UPDATE sale
SET sale_registered = 'Y'
WHERE sale_date = '2025-08-21';

-- dailySale 조회
SELECT
	product_name,
	product_code,
	product_price,
	sale_date,
	count(*) AS quantity 
FROM sale
JOIN product USING(product_no)
JOIN product_name USING(product_code)
WHERE sale_date = '2025-08-08'
	AND sale_registered = 'N'
GROUP BY product_name, product_code, product_price, sale_date;

SELECT
	count(*)
FROM sale
JOIN product USING(product_no)
JOIN product_name USING(product_code)
WHERE sale_date = '2025-08-08'
GROUP BY product_name, product_code, product_price, sale_date;


-- 일별 매출 총합 조회
SELECT sale_date, SUM(total_amount) AS daily_sales
FROM sale_manage
WHERE sale_date = '2025-08-03';

SELECT * FROM sale;
SELECT * FROM product_name;
SELECT * FROM department;
select * from performance_review;

-- 일별 매출 조회
SELECT product_code, product_name, product_price, sale_date, dept_no FROM sale
JOIN product USING(product_no)
JOIN product_name USING(product_code);
-- WHERE sale_date = '2025-08-06';
-- 제품별 판매 수량 조회
SELECT product_name, product_code, product_price, sale_date, count(*), SUM(product_price) AS total_amount FROM sale
JOIN product USING(product_no)
JOIN product_name USING(product_code)
WHERE sale_date = '2025-08-06'
GROUP BY product_name, product_code, product_price, sale_date;
-- 일별 매출 총액 조회
SELECT sale_date, SUM(product_price) FROM sale
JOIN product USING(product_no)
JOIN product_name USING(product_code)
WHERE sale_date = '2025-08-06'
GROUP BY sale_date;

SELECT * FROM product
JOIN product_name USING(product_code);
SELECT * FROM product_name;

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
-- 예산 수정
/*
UPDATE budget
SET period_type = #{periodType}, 
	annual_budget = #{annualBudget}, 
    plan = #{plan}, 
    execution_date = #{executionDate}, 
    dept_no = #{deptNo} 
WHERE budget_no = #{budgetNo};
*/
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
-- 매입 단가 및 수량 등록
-- INSERT INTO purchase(product_code, unit_price, quantity, var_amount, total_amount, purchase_date) 
-- VALUES(#{productCode}, #{unitPrice}, #{quantity}, #{varAmount}, #{totalAmount}, #{purchaseDate});
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
-- 거래내역 등록
-- INSERT INTO transaction(trans_type, trans_amount, category, trans_desc, trans_date, dept_no) 
-- VALUES(#{transType}, #{transAmount}, #{category}, #{transDesc}, #{transDate}, #{deptNo});
-- 거래내역 수정
/*
UPDATE transaction
SET trans_type = #{transType}, 
	trans_amount = #{transAmount}, 
    category = #{category}, 
    trans_desc = #{transDesc}, 
    trans_date = #{transDate}, 
    dept_no = #{deptNo} 
WHERE trans_no = #{transNo};
*/





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