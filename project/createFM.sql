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
    
    emp_no INT NOT NULL, -- 사원 번호 외래키
    bonus_payment_no INT -- 보너스 수당 번호 외래키
);

-- 예산 계획
CREATE TABLE budget(
	budget_no INT AUTO_INCREMENT PRIMARY KEY, -- 예산 번호
    period_type VARCHAR(2) CHECK (period_type IN ('Y', 'Q', 'M')), --  (연/분기/월: Y/Q/M)
    period_value VARCHAR(10), -- 적용 기간 값 (예 : 2025, 2025-Q1 등)
    annual_budget INT, -- 예산 금액
    -- target_sales INT, -- 목표 매출
    plan TEXT, -- 계획 상세
	-- achieved VARCHAR(2) CHECK (achieved IN ('T', 'F')), -- 목표 달성 여부	
    created_at DATE, -- 생성일시
    dept_no INT NOT NULL -- 부서 번호 외래키
);

-- 수입/지출 관리
CREATE TABLE transaction(
	trans_no INT AUTO_INCREMENT PRIMARY KEY, -- 거래 번호
    trans_type VARCHAR(10) CHECK (trans_type IN ('수입', '지출')), -- 수입/지출
    trans_amount INT, -- 금액
    category VARCHAR(50), -- 분류
    trans_desc TEXT, -- 수입/지출 내역 상세
    trans_date DATE, -- 수입/지출 발생 일자
    dept_no INT NOT NULL -- 부서 번호 외래키 (통계 처리 하려면) 
);

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

CREATE TABLE sale_manage(
	sm_no INT AUTO_INCREMENT PRIMARY KEY, -- 매출 번호
    sale_date DATE, -- 매출 발생일자
    quantity INT, -- 수량
    var_amount INT, -- 부가세
    total_amount INT, -- 총액
    product_code INT NOT NULL -- 품목 번호 외래키
);