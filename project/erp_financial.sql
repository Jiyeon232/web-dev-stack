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