/*
   LIMIT
   - ORDER BY 절보다 뒤에 조건을 걸고 싶을 때 사용
   - 출력되는 행 수를 제한하는 MYSQL 전용 비표준 구문
   - 데이터 양을 제한하고자 할 때 유용 (페이징 처리)
*/
SELECT * 
FROM actor
ORDER BY first_name
-- LIMIT 10 OFFSET 20;
LIMIT 20, 10;

/*
   CONCAT(컬럼, 컬럼, ...)
   - 문자열을 하나로 합친 후 결과 반환 (검색 기능 - LIKE랑 %)
*/
SELECT CONCAT(title, description, special_features) 
FROM film;

/*
   날짜 처리 함수
   * NOW(), CURRENT_TIMESTAMP() : 현재 날짜와 시간 반환
   CURDATE(), * CURRENT_DATE() : 현재 날짜 반환
   CURTIME(), CURRENT_TIME() : 현재 시간 반환
*/
SELECT NOW(), CURRENT_TIMESTAMP(),
       CURDATE(), CURRENT_DATE(),
       CURTIME(), CURRENT_TIME();
       
/*
   YEAR(날짜), MONTH(날짜), DAY(날짜)
   HOUR(날짜), MINUTE(날짜), SECOND(날짜)
*/
SELECT YEAR(NOW()), MONTH(NOW()), DAY(NOW()),
	   HOUR(NOW()), MINUTE(NOW()), SECOND(NOW());
       
/*
   포맷 함수
   FORMAT(숫자, 위치) : 숫자에 3단위씩 콤마 추가해서 반환
   DATE_FORMAT(날짜, 포맷형식) : 날짜 형식을 변경해서 반환
*/
SELECT
   FORMAT(amount, 2),
   DATE_FORMAT(payment_date, '%Y년 %m월 %d일 %H시 %i분 %s초'),
   DATE_FORMAT(last_update, '%Y.%m.%d %T')
FROM payment;

/*
   null 처리 함수
   IFNULL|COALESCE(값, 값이 NULL일 경우 반환값)
*/
SELECT address, address2, IFNULL(address2, '주소 없음')
FROM address;

/*
   IF(값1, 값2, 값3) | IF(조건, 조건 True인 경우, 조건 False인 경우)
   - 값1이 null이 아니면 값2 반환, null이면 값3 반환
   - 조건에 해당하면 두번째 값 반환, 해당하지 않으면 마지막 값 반환
*/
SELECT
   address2, IF(address2, '주소 있음', '주소 없음'),
   address, IF(address LIKE '1%', '해당 값 True', 'False')
FROM address;