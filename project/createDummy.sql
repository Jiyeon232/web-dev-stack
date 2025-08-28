INSERT INTO brand (brand_name, brand_phone, brand_account, brand_bank) VALUES
('샤넬', 01012345678, 100200300, '국민은행'), -- 1
('유니클로', 0212345678, 200300400, '신한은행'), -- 2
('자라', 0319876543, 300400500, '하나은행'); -- 3
INSERT INTO product_name (product_color, product_name, product_price, product_cost, product_category, brand_code) VALUES
('화이트', '베이직 티셔츠', 19900, 7000, '상의', 1),
('블랙', '헨리넥 셔츠', 29900, 12000, '상의', 2),
('네이비', '후드티', 39000, 17000, '상의', 3),
('그레이', '맨투맨', 35000, 15000, '상의', 1),
('레드', '폴로 셔츠', 42000, 18000, '상의', 2),
('베이지', '치노 팬츠', 45000, 20000, '하의', 1),
('블랙', '슬랙스', 49000, 22000, '하의', 2),
('청색', '데님 팬츠', 59000, 25000, '하의', 3),
('카키', '조거 팬츠', 43000, 19000, '하의', 1),
('아이보리', '반바지', 27000, 12000, '하의', 3),
('화이트', '운동화', 69000, 30000, '신발', 2),
('블랙', '로퍼', 79000, 35000, '신발', 3),
('브라운', '부츠', 89000, 40000, '신발', 1),
('그레이', '슬리퍼', 25000, 10000, '신발', 2),
('레드', '샌들', 33000, 14000, '신발', 1),
('실버', '메탈 시계', 99000, 50000, '악세사리', 2),
('블랙', '가죽 벨트', 38000, 15000, '악세사리', 3),
('화이트', '버킷햇', 22000, 8000, '악세사리', 1),
('그린', '에코백', 27000, 12000, '악세사리', 3),
('핑크', '양말 세트', 12000, 4000, '악세사리', 2);
-- product (10 records)
INSERT INTO product (production_date, product_code) VALUES
('2025-01-10', 1),
('2025-02-15', 2),
('2025-03-20', 3),
('2025-04-05', 4),
('2025-05-12', 5),
('2025-06-18', 6),
('2025-07-01', 7),
('2025-08-10', 8),
('2025-09-15', 9),
('2025-10-20', 10),
('2025-01-10', 11),
('2025-02-15', 12),
('2025-03-20', 13),
('2025-04-05', 14),
('2025-05-12', 15),
('2025-06-18', 16),
('2025-07-01', 17),
('2025-08-10', 18),
('2025-09-15', 19),
('2025-10-20', 0);
-- qc (10 records)
INSERT INTO qc (check_material, check_color, check_damage, qc_desc, qc_date, emp_no, product_no) VALUES
('합격', '합격', '합격', '부자재, 색상, 손상 모두 정상.', '2025-01-11', 1, 1),
('불합격', '불합격', '합격', '소재 불량, 색상 약간 다름.', '2025-02-16', 2, 2),
('합격', '합격', '합격', '문제 없음.', '2025-03-21', 3, 3),
('합격', '합격', '불합격', '표면에 작은 스크래치 발견.', '2025-04-06', 4, 4),
('합격', '합격', '합격', '모든 검사 통과.', '2025-05-13', 5, 5),
('합격', '불합격', '합격', '색상이 약간 퇴색됨.', '2025-06-19', 6, 6),
('합격', '합격', '불합격', '원단에 작은 얼룩 발견.', '2025-07-02', 7, 7),
('합격', '합격', '합격', '완벽한 상태.', '2025-08-11', 8, 8),
('불합격', '불합격', '합격', '잘못된 소재와 색상.', '2025-09-16', 9, 9),
('합격', '합격', '합격', '문제 없음.', '2025-10-21', 10, 10);
-- 체크용
SELECT * FROM product;
-- sale (productNo 안겹치게 salready 더미)
INSERT INTO sale (sale_date, product_no) VALUES
(NULL, 121),
(NULL, 122),
(NULL, 123),
(NULL, 124),
(NULL, 125),
(NULL, 126),
(NULL, 127),
(NULL, 128),
(NULL, 129),
(NULL, 130),
(NULL, 131),
(NULL, 132),
(NULL, 133),
(NULL, 134),
(NULL, 135),
(NULL, 136),
(NULL, 137),
(NULL, 138),
(NULL, 139),
(NULL, 140),
(NULL, 141),
(NULL, 142),
(NULL, 143),
(NULL, 144),
(NULL, 145),
(NULL, 146),
(NULL, 147),
(NULL, 148),
(NULL, 149),
(NULL, 150),
(NULL, 151),
(NULL, 152),
(NULL, 153),
(NULL, 154),
(NULL, 155),
(NULL, 156),
(NULL, 157),
(NULL, 158),
(NULL, 159),
(NULL, 160),
(NULL, 161),
(NULL, 162),
(NULL, 163),
(NULL, 164),
(NULL, 165),
(NULL, 166),
(NULL, 167),
(NULL, 168),
(NULL, 169),
(NULL, 170),
(NULL, 171),
(NULL, 172),
(NULL, 173),
(NULL, 174),
(NULL, 175),
(NULL, 176),
(NULL, 177),
(NULL, 178),
(NULL, 179),
(NULL, 180),
(NULL, 181),
(NULL, 182),
(NULL, 183),
(NULL, 184),
(NULL, 185),
(NULL, 186),
(NULL, 187),
(NULL, 188),
(NULL, 189),
(NULL, 190),
(NULL, 191),
(NULL, 192),
(NULL, 193),
(NULL, 194),
(NULL, 195),
(NULL, 196),
(NULL, 197),
(NULL, 198),
(NULL, 199),
(NULL, 200),
(NULL, 201),
(NULL, 202),
(NULL, 203),
(NULL, 204),
(NULL, 205),
(NULL, 206),
(NULL, 207),
(NULL, 208),
(NULL, 209),
(NULL, 210),
(NULL, 211),
(NULL, 212),
(NULL, 213),
(NULL, 214),
(NULL, 215),
(NULL, 216),
(NULL, 217),
(NULL, 218),
(NULL, 219),
(NULL, 220);
-- 체크용
SELECT * FROM sale WHERE sale_date IS NULL;
-- 체크용
SELECT
	p.product_no,
	pn.product_code,
	pn.product_price,
	pn.product_name,
	pn.product_category,
	s.sale_no,
	s.sale_date
FROM sale s
JOIN product p USING(product_no)
JOIN product_name pn USING(product_code)
WHERE s.sale_date IS NULL
ORDER BY s.sale_no ASC
LIMIT 0, 10;
-- 체크용
SELECT * FROM product;
SELECT
	p.product_no,
	pn.product_code,
	pn.product_price,
	pn.product_name,
	pn.product_category,
	s.sale_no,
	s.sale_date
FROM sale s
JOIN product p USING(product_no)
JOIN product_name pn USING(product_code)
WHERE s.sale_date IS NULL
ORDER BY s.sale_no ASC
LIMIT 0, 10;
-- 체크용
SELECT * FROM sale;
INSERT INTO defective (product_no) VALUES
(2), -- 소재 및 색상 불량
(4), -- 스크래치 발견
(6), -- 색상 퇴색
(7); -- 얼룩 발견