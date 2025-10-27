select * from user;
select * from workshop;
select * from program;
select * from schedule;
select * from review;
select * from qna_workshop;


SELECT * FROM schedule WHERE program_id =1;

SELECT * FROM program
        JOIN workshop USING(workshop_id)
        WHERE program_id =  1;
        
SELECT * FROM review
		JOIN user USING(user_id)
        WHERE program_id = 1;

SELECT * FROM qna_workshop 
		JOIN user USING(user_id)
        WHERE program_id = 1;



-- 소요시간 n시간 n분 형식으로 계산
SELECT
    duration_min,
    CASE
        WHEN duration_min < 60 THEN 
            CONCAT(duration_min, '분')
        WHEN MOD(duration_min, 60) = 0 THEN 
            CONCAT(FLOOR(duration_min / 60), '시간')
        ELSE 
            CONCAT(
                FLOOR(duration_min / 60), '시간 ',
                MOD(duration_min, 60), '분'
            )
    END AS duration
FROM program;