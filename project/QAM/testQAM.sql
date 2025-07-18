-- 제품명 추가:
INSERT INTO product_name (product_color, product_name, product_price, product_cost, product_category)
VALUES ('블랙', 'Slim Fit T-Shirt', 25000, 10000, '상의');

-- 제품 추가:
INSERT INTO product (production_date, product_code)
VALUES ('2025-07-16', 1);
-- 현재 날짜 자동 넣어줌
INSERT INTO product (production_date, product_code)
VALUES (CURDATE(), 1);

-- 제품명 수정:
UPDATE product_name
SET
product_name = 'Autumn Knit Sweater',
product_color = '베이지',
product_price = 59000,
product_cost = 25000,
product_category = '상의'
WHERE product_code = 1;

-- 제품 수정:
UPDATE product
SET
production_date = '2025-07-15'
WHERE product_no = 10;

-- 제품 조회 기능
SELECT
product.product_no,
product.production_date,
product_name.product_name,
product_name.product_color,
product_name.product_category,
product_name.product_price,
product_name.product_cost
FROM product
JOIN product_name ON product.product_code = product_name.product_code
ORDER BY product.product_no ASC;

-- 간략 버전: p - product / pn - product_name
SELECT
p.product_no,
p.production_date,
pn.product_name,
pn.product_color,
pn.product_category,
pn.product_price,
pn.product_cost
FROM product p
JOIN product_name pn ON p.product_code = pn.product_code
ORDER BY p.product_no ASC;

-- 품목군 상의 분류
SELECT
product_code,
product_name,
product_color,
product_price,
product_cost,
product_category
FROM product_name
WHERE product_category = '상의';

-- 품목군 하의 분류
SELECT
product_code,
product_name,
product_color,
product_price,
product_cost,
product_category
FROM product_name
WHERE product_category = '하의';

-- 품목군 악세사리 분류 
SELECT
product_code,
product_name,
product_color,
product_price,
product_cost,
product_category
FROM product_name
WHERE product_category = '악세사리';

-- 품목군 신발 분류
SELECT
product_code,
product_name,
product_color,
product_price,
product_cost,
product_category
FROM product_name
WHERE product_category = '신발';

-- 스타일, 가격, 컬러,  기반 분류/조회
-- 1. 상의 중 흰색 & 3만~6만 사이 제품 분류/조회
SELECT
product_code,
product_name,
product_color,
product_price,
product_category
FROM product_name
WHERE product_category = '상의'
AND product_color = '초록'
AND product_price BETWEEN 30000 AND 60000;

-- 2. 색상을 기준으로 분류/조회 
SELECT
product_color,
COUNT(*) AS total_count
FROM product_name
GROUP BY product_color
ORDER BY total_count DESC;

-- 3. 가격 구간별 분류 통계
SELECT
CASE
WHEN product_price <= 30000 THEN '3만원 이하'
WHEN product_price BETWEEN 30001 AND 60000 THEN '3만원 초과, 6만원 이하 제품'
WHEN product_price BETWEEN 60001 AND 100000 THEN '6만원 초과, 10만원 이하 제품'
ELSE '10만원 초과 제품'
END AS price_range,
COUNT(*) AS total_count
FROM product_name
GROUP BY price_range
ORDER BY price_range;

-- 재고 수량 확인 및 조회
-- 1. 제품별 재고 수량 조회
SELECT
pn.product_code,
pn.product_name,
pn.product_color,
pn.product_category,
COUNT(p.product_no) AS 재고수량
FROM product p
JOIN product_name pn ON p.product_code = pn.product_code
GROUP BY
pn.product_code,
pn.product_name,
pn.product_color,
pn.product_category
ORDER BY 재고수량;

-- 2. 상의 재고 수량 조회
SELECT
pn.product_code,
pn.product_name,
pn.product_color,
COUNT(p.product_no) AS 재고수량
FROM product p
JOIN product_name pn ON p.product_code = pn.product_code
WHERE pn.product_category = '상의'
GROUP BY
pn.product_code,
pn.product_name,
pn.product_color;

-- 아직 qc에 등록되지 않은 제품 목록 조회 (검수 대기 대상)
SELECT 
    p.product_no,
    p.production_date,
    pn.product_name,
    pn.product_category
FROM product p
JOIN product_name pn ON p.product_code = pn.product_code
WHERE p.product_no NOT IN (
    SELECT product_no FROM qc
);

-- 검수 대기 리스트를 qc 테이블에 일괄 등록 (자동 추가) -- GPT 도움
INSERT INTO qc (
    check_material,
    check_color,
    check_damage,
    qc_desc,
    qc_date,
    emp_no,
    product_no
)
SELECT 
    NULL, NULL, NULL,
    '검수 대기',
    CURDATE(),
    NULL, -- 검사자 아직 지정 안 함
    product.product_no
FROM product
WHERE product.product_no NOT IN (
    SELECT product_no FROM qc
);
-- 체크용 SELECT
SELECT * FROM qc;

-- 2. 검수 대상 제품 조회 (검수 대기 상태)
SELECT
    q.qc_code,
    p.product_no,
    pn.product_name,
    pn.product_color,
    q.qc_desc,
    q.qc_date
FROM qc q
JOIN product p ON q.product_no = p.product_no
JOIN product_name pn ON p.product_code = pn.product_code
WHERE q.qc_desc = '검수 대기';

-- 3. 이미 "검수 대기"로 등록된 제품의 검사 정보 업데이트
UPDATE qc
SET 
    check_material = '합격',
    check_color = '불합격',
    check_damage = '불합격',
    qc_desc = '소재는 양호하나 색상 오류 및 미세 손상 있음.',
    qc_date = CURDATE(),
    emp_no = 4
WHERE product_no = 19
  AND qc_desc = '검수 대기';
  
  -- 검사 결과 등록 SQL (수량 + 판정 포함)
  INSERT INTO qc (
    check_material,
    check_color,
    check_damage,
    qc_desc,
    qc_date,
    emp_no,
    product_no,
    quantity,
    result
)
VALUES (
    '불합격',
    '색상 불일치',
    '작은 찢김',
    '원단 색상 오차 및 손상 있음.',
    CURDATE(),
    6,
    7,
    5,         -- 검사 수량
    '불합격'   -- 판정
);

-- 검사 통과 제품 등록 (판매 전 상태)
INSERT INTO sale (sale_date, product_no)
SELECT NULL, q.product_no
FROM qc q
WHERE q.check_material = '합격'
  AND q.check_color = '합격'
  AND q.check_damage = '합격'
  AND q.product_no NOT IN (SELECT product_no FROM sale);

-- 불량 분류별 통계 조회 SQL
SELECT
    pn.product_category,
    COUNT(CASE WHEN q.check_material = '불합격' THEN 1 END) AS material_defect,
    COUNT(CASE WHEN q.check_color = '불합격' THEN 1 END) AS color_defect,
    COUNT(CASE WHEN q.check_damage = '불합격' THEN 1 END) AS damage_defect
FROM qc q
JOIN product p ON q.product_no = p.product_no
JOIN product_name pn ON p.product_code = pn.product_code
GROUP BY pn.product_category;

-- 불량 분류별 통계 조회 SQL
SELECT
    pn.product_category,
    COUNT(DISTINCT CASE
        WHEN q.check_material = '불합격'
          OR q.check_color = '불합격'
          OR q.check_damage = '불합격'
        THEN q.product_no
    END) AS '불량 총계',
    COUNT(CASE WHEN q.check_material = '불합격' THEN 1 END) AS '부자재 불량',
    COUNT(CASE WHEN q.check_color = '불합격' THEN 1 END) AS '색상 불량',
    COUNT(CASE WHEN q.check_damage = '불합격' THEN 1 END) AS '손상 불량'
FROM qc q
JOIN product p ON q.product_no = p.product_no
JOIN product_name pn ON p.product_code = pn.product_code
GROUP BY pn.product_category;


-- 색상 문제 제품 목록 조회
SELECT
    pn.product_name,
    pn.product_category,
    q.qc_date,
    q.check_material,
    q.check_color,
    q.check_damage,
    q.qc_desc
FROM qc q
JOIN product p ON q.product_no = p.product_no
JOIN product_name pn ON p.product_code = pn.product_code
WHERE q.check_color = '불합격';

-- 전체 손실액 및 불량률 계산 (카테고리별)
SELECT
    pn.product_category,
    COUNT(DISTINCT p.product_no) AS '총생산 제품 수',
    COUNT(DISTINCT CASE
        WHEN q.check_material = '불합격'
          OR q.check_color = '불합격'
          OR q.check_damage = '불합격'
        THEN p.product_no
    END) AS '불량 제품 수',
    ROUND(
        COUNT(DISTINCT CASE
            WHEN q.check_material = '불합격'
              OR q.check_color = '불합격'
              OR q.check_damage = '불합격'
            THEN p.product_no
        END) / COUNT(DISTINCT p.product_no) * 100, 2
    ) AS'불량률(%)',
    SUM(DISTINCT CASE
        WHEN q.check_material = '불합격'
          OR q.check_color = '불합격'
          OR q.check_damage = '불합격'
        THEN pn.product_cost
        ELSE 0
    END) AS '손실액(단가 기준)'
FROM product p
JOIN product_name pn ON p.product_code = pn.product_code
JOIN qc q ON p.product_no = q.product_no
GROUP BY pn.product_category;

--  전체 검사 이력 조회 쿼리 (품목 + 날짜 + 검사자까지)
SELECT
    q.qc_code,
    q.qc_date,
    q.emp_no,
    ei.emp_name AS '검사자',
    pn.product_category,
    pn.product_name,
    p.production_date,
    q.check_material,
    q.check_color,
    q.check_damage,
    q.qc_desc
FROM qc q
JOIN product p ON q.product_no = p.product_no
JOIN product_name pn ON p.product_code = pn.product_code
LEFT JOIN employee_info ei ON q.emp_no = ei.emp_no
ORDER BY q.qc_date DESC; -- 최근 검사일부터 보여줌

-- 특정 품목군 기준 필터들 (하나씩 사용도 가능)
SELECT
    q.qc_code,
    q.qc_date,
    q.emp_no,
    ei.emp_name AS inspector_name,
    pn.product_category,
    pn.product_name,
    p.production_date,
    q.check_material,
    q.check_color,
    q.check_damage,
    q.qc_desc
FROM qc q
JOIN product p ON q.product_no = p.product_no
JOIN product_name pn ON p.product_code = pn.product_code
LEFT JOIN employee_info ei ON q.emp_no = ei.emp_no
WHERE pn.product_category = '상의'                  -- 품목군 필터
  AND q.qc_date BETWEEN '2025-01-01' AND '2025-07-31' -- 날짜 필터
  AND ei.emp_name = '김민수'                        -- 검사자 이름 필터
ORDER BY q.qc_date DESC;

-- 검사자별 처리 건수
SELECT
ei.emp_no,
ei.emp_name,
COUNT(q.qc_code) AS total_inspections
FROM qc q
JOIN employee_info ei ON q.emp_no = ei.emp_no
GROUP BY ei.emp_no, ei.emp_name
ORDER BY total_inspections DESC;

-- 검사자별 특정 기간 내 검사 건수
SELECT
ei.emp_no,
ei.emp_name,
COUNT(q.qc_code) AS total_inspections
FROM qc q
JOIN employee_info ei ON q.emp_no = ei.emp_no
WHERE q.qc_date BETWEEN '2025-01-01' AND '2025-06-30'
GROUP BY ei.emp_no, ei.emp_name
ORDER BY total_inspections DESC;

-- 일자 + 품목군별 불량률 통계 
SELECT
    q.qc_date,
    pn.product_category,
    COUNT(DISTINCT p.product_no) AS '총 검사된 제품 수',
    COUNT(DISTINCT CASE
        WHEN q.check_material = '불합격'
          OR q.check_color = '불합격'
          OR q.check_damage = '불합격'
        THEN p.product_no
    END) AS '불량 제품 수',
    ROUND(
        COUNT(DISTINCT CASE
            WHEN q.check_material = '불합격'
              OR q.check_color = '불합격'
              OR q.check_damage = '불합격'
            THEN p.product_no
        END) / COUNT(DISTINCT p.product_no) * 100, 2
    ) AS '불량률(%)'
FROM qc q
JOIN product p ON q.product_no = p.product_no
JOIN product_name pn ON p.product_code = pn.product_code
GROUP BY q.qc_date, pn.product_category
ORDER BY q.qc_date DESC, pn.product_category;
