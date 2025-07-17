-- 예산 설정 테이블
CREATE TABLE budget (
    budget_id INT AUTO_INCREMENT PRIMARY KEY, 	      -- 예산 ID (고유 식별자) 예산의 고유번호 (자동 증가)
    department_id INT,                       		  -- 예산이 속한 부서 ID (외래키 예정)
    period_value VARCHAR(10) NOT NULL,                -- 어떤 분기의 예산인지 (예: 2025-Q1, 2025-07)
    amount INT NOT NULL,            -- 예산 금액
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP    -- 생성일시, 입력된 시점
);
-- department_id → department(department_id)를 참조 (부서 테이블이 필요함)
-- budget_exe와 연결됨 (예산이 실제로 집행된 내역)

-- 매출 관리 테이블
CREATE TABLE sale (
    sale_id INT AUTO_INCREMENT PRIMARY KEY,           -- 매출 ID, 판매 고유번호
    sale_date DATE NOT NULL,                          -- 매출 발생일, 판매된 날짜
    -- product_id
    quantity INT,                                     -- 판매 수량
    vat_amount INT,                			          -- 부가세 (찾아보니 10%라 통일하면 될 것 같음)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP    -- 등록일
);
-- 독립적 테이블 (다른 테이블 참조 없음)
-- 분석 시, 상품별/성별/기간별 매출 통계에 사용

-- 지출 관리 테이블
CREATE TABLE expense (
    expense_id INT AUTO_INCREMENT PRIMARY KEY,        -- 지출 ID, 지출 고유번호
    department_id INT,                                -- 해당 지출의 부서 ID (외래키 예정), 어느 부서에서 쓴 돈인지
    category VARCHAR(50),                             -- 지출 분류 (예: 인건비, 운영비, 원가 등)
    description TEXT,                                 -- 지출에 대한 상세 설명
    amount INT,                                       -- 지출 금액
    expense_date DATE,                                -- 지출 발생일
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP    -- 등록일
);
-- department(department_id)를 참조
-- budget_exe 테이블에서 예산과 연결됨 (이 지출이 어떤 예산으로 나간 건지)
