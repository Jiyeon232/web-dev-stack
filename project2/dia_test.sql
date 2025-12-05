select * from user;
select * from workshop;
select * from program;
select * from schedule;
select * from review;
select * from qna_workshop;
select * from follow;
select * from wish;
select * from notification;
select * from notification_user;
select * from reservation;
select * from payment;
select * from action_log;

select * from notification where target_type = 'CUSTOMER';
update notification set target_type = 'USER' where notification_id = 344;

SELECT
        nu.notification_user_id AS id,
        nu.user_id,
        nu.viewed,
        n.sender_id,
        n.message,
        n.created_at,

        u.name,
        w.workshop_id AS workshopId,
        w.name AS workshopName
        FROM notification_user nu
        LEFT JOIN notification n
        ON nu.notification_id = n.notification_id
        LEFT JOIN user u
        ON n.sender_id = u.user_id
        LEFT JOIN workshop w
        ON n.workshop_id = w.workshop_id
        WHERE nu.user_id = 20;

    INSERT INTO notification (
        sender_id, target_type, workshop_id, message, type, created_at
        ) VALUES (
        #{senderId}, #{targetType}, #{workshopId}, #{message}, #{type}, NOW()
        )


SELECT
        nu.notification_user_id AS id,
        nu.user_id,
        nu.viewed,
        n.message,
        n.created_at
        FROM notification_user nu
        JOIN user u 
        ON nu.user_id = u.user_id
        LEFT JOIN notification n
        ON nu.notification_id = n.notification_id
        WHERE
        nu.user_id = 5 AND u.role = 'CUSTOMER'
        ORDER BY n.created_at DESC;

SELECT *
FROM action_log
WHERE target_type IN ('WORKSHOP', 'PROGRAM')
ORDER BY created_at DESC;

SELECT
    a.action_log_id,
    a.target_type,
    a.target_id,
    a.admin_id,
    u.name AS adminName,
    a.action_type,
    a.reason,
    a.created_at,

    CASE a.target_type
        WHEN 'WORKSHOP' THEN w.name
        WHEN 'PROGRAM' THEN p.title
    END AS targetName,

    CASE a.target_type
        WHEN 'PROGRAM' THEN ws.name 
        ELSE NULL
    END AS workshopName

FROM action_log a
JOIN user u 
    ON a.admin_id = u.user_id

LEFT JOIN workshop w
    ON a.target_id = w.workshop_id
    AND a.target_type = 'WORKSHOP'

LEFT JOIN program p
    ON a.target_id = p.program_id
    AND a.target_type = 'PROGRAM'

LEFT JOIN workshop ws
    ON p.workshop_id = ws.workshop_id 
    AND a.target_type = 'PROGRAM'

WHERE a.target_type IN ('WORKSHOP', 'PROGRAM')
ORDER BY a.created_at DESC;


  SELECT
        a.action_log_id, a.target_type, a.target_id, a.admin_id, u.name AS adminName, a.action_type, a.reason, a.created_at,
        CASE a.target_type
            WHEN 'WORKSHOP' THEN w.name
            WHEN 'PROGRAM' THEN p.title
        END AS targetName
        FROM action_log a
        JOIN user u ON a.admin_id = u.user_id
        LEFT JOIN workshop w
        ON a.target_id = w.workshop_id
        AND a.target_type = 'WORKSHOP'
        LEFT JOIN program p
        ON a.target_id = p.program_id
        AND a.target_type = 'PROGRAM'
        WHERE target_type IN ('WORKSHOP', 'PROGRAM')
        ORDER BY a.created_at DESC;


ALTER TABLE program
ADD COLUMN approved ENUM('대기','승인','거절') DEFAULT '대기' AFTER status;

SELECT 
	p.program_id,
	p.title,
    p.description,
    p.category,
    p.price,
    p.thumb,
    p.duration_min,
    p.difficulty,
    p.schedule_type,
    p.status,
    p.active,
    p.approved,
    p.created_at,
    w.workshop_id,
    w.name,
    u.user_id,
	u.name AS user_name,
	u.email
FROM program p
JOIN workshop w ON w.workshop_id = p.workshop_id
LEFT JOIN user u ON w.owner_id = u.user_id;

SELECT 
	w.workshop_id,
	w.owner_id,
	w.name,
	w.description,
	w.profile_img,
	w.address,
	w.contact_number,
	w.status,
	w.approved,
	w.active,
	w.created_at,
	u.user_id,
	u.name AS user_name,
	u.email
FROM workshop w
LEFT JOIN user u ON w.owner_id = u.user_id;

/*
UPDATE workshop 
SET approved = #{approved} 
WHERE workshop_id = #{workshopId}

UPDATE program 
SET approved = #{approved} 
WHERE program_id = #{programId}

INSERT INTO action_log(target_type, target_id, admin_id, action_type, reason)
VALUES(#{targetType}, #{targetId}, #{adminId}, #{actionType}, #{reason})
*/

-- INSERT INTO payment(reservation_id, method, amount, status)
-- VALUES(#{reservation_id}, #{method}, #{amount}, '완료');

-- INSERT INTO reservation(schedule_id, user_id, num_people, total_price)
-- VALUES(#{schedule_id}, #{user_id}, #{num_people}, #{total_price});

UPDATE reservation SET status = '확정' WHERE reservation_id = 31;

SELECT 
	w.wish_id,
    w.user_id,
    w.program_id,
    w.active,
    ws.workshop_id
FROM wish w
	LEFT JOIN program p ON w.program_id = p.program_id
	LEFT JOIN workshop ws ON p.workshop_id = ws.workshop_id
WHERE w.user_id = 5 AND ws.workshop_id = 1;

SELECT
    p.program_id,
    p.title,
    p.price,
    p.category,
    p.description,
    p.duration_min,
    p.difficulty,
    p.thumb,
    ws.workshop_id,
    ws.name AS workshop_name,
    ws.address,
    ws.profile_img,
    IFNULL(AVG(r.rating), 0) AS average_rating,
    COUNT(DISTINCT r.review_id) AS count_review,
    COUNT(DISTINCT q.qna_id) AS count_qna,
    COUNT(DISTINCT w.wish_id) AS count_wish
FROM program p
JOIN workshop ws ON p.workshop_id = ws.workshop_id
LEFT JOIN review r ON p.program_id = r.program_id
LEFT JOIN qna_workshop q ON p.program_id = q.program_id
LEFT JOIN wish w ON p.program_id = w.program_id AND w.active = TRUE
WHERE p.program_id = 1;

 SELECT
      p.program_id,
      p.title,
      p.price,
      p.category,
      p.thumb,
      ws.workshop_id,
      ws.name,
      ws.address,
      IFNULL(AVG(r.rating), 0) AS average_rating,
      COUNT(DISTINCT r.review_id) AS count_review,
      COUNT(DISTINCT w.wish_id) AS count_wish
  FROM program p
  JOIN workshop ws ON p.workshop_id = ws.workshop_id
  LEFT JOIN review r ON p.program_id = r.program_id
  LEFT JOIN wish w ON p.program_id = w.program_id AND w.active = TRUE
  WHERE ws.workshop_id = 1 
  GROUP BY p.program_id;

SELECT 
	r.review_id,
    r.user_id,
    r.program_id,
    r.rating,
    r.content,
    r.created_at,
    u.name    
FROM review r
	LEFT JOIN user u ON r.user_id = u.user_id
WHERE program_id = 1
ORDER BY created_at DESC;

SELECT 
	q.qna_id,
    q.user_id,
    q.program_id,
    q.title,
    q.content,
    q.answer,
    q.created_at,
    q.answered_at,
    u.name AS user_name
FROM qna_workshop q
	LEFT JOIN user u ON q.user_id = u.user_id
WHERE qna_id = 1;

SELECT
		r.reservation_id,
		r.num_people,
        r.total_price,
		r.status,

		s.start_time,
		s.end_time,

		pr.program_id,
		pr.title,
		pr.price,
		pr.thumb,
		
		u.user_id,
		u.name,
		u.email,
		u.phone,
		
		w.workshop_id,
		w.address,
        
        p.payment_id,
        p.method,
        p.status AS payment_status,
        p.paid_at
	FROM reservation r
		LEFT JOIN schedule s
			ON r.schedule_id = s.schedule_id
		LEFT JOIN program pr
			ON s.program_id = pr.program_id
		LEFT JOIN user u
			ON r.user_id = u.user_id
		LEFT JOIN workshop w
			ON pr.workshop_id = w.workshop_id
		LEFT JOIN payment p
			ON r.reservation_id = p.reservation_id
	WHERE
		r.reservation_id = 12;

SELECT IFNULL(AVG(rating), 0) AS average_rating FROM review WHERE program_id = 1;
SELECT COUNT(*) AS count_wish FROM wish WHERE program_id = 1 AND active = TRUE;

-- -------------
SELECT p.program_id, IFNULL(AVG(r.rating), 0) AS average_rating, count(*) AS count_review
FROM review r
JOIN program p ON r.program_id = p.program_id
JOIN workshop w on p.workshop_id = w.workshop_id
WHERE w.workshop_id = 1
GROUP BY p.program_id;

SELECT p.program_id, count(*) AS count_wish 
FROM wish w
JOIN program p ON w.program_id = p.program_id
JOIN workshop ws on p.workshop_id = ws.workshop_id
WHERE ws.workshop_id = 1 AND w.active = TRUE
GROUP BY p.program_id;
-- ----------------

SELECT program_id,
		(SELECT AVG(rating) FROM review) AS average_rating,
		(SELECT COUNT(wish_id) FROM wish) AS count_wish
	FROM program
    WHERE program_id = 1;
SELECT COUNT(wish_id) FROM wish WHERE program_id = program_id;

SELECT * FROM wish WHERE user_id = 5 AND program_id = 2;

SELECT * FROM notification_user
JOIN notification USING(notification_id);

UPDATE notification_user SET viewed = TRUE WHERE notification_user_id = 1;
INSERT INTO notification_user(notification_id, user_id) VALUES(1, 5);

SELECT
            nu.notification_user_id AS id,
            nu.user_id,
            nu.viewed,

            n.sender_id,
            n.message,
            n.created_at,

            u.name,
            w.name AS workshop_name 
        FROM notification_user nu
            LEFT JOIN notification n
                ON nu.notification_id = n.notification_id
            LEFT JOIN user u
                ON n.sender_id = u.user_id
			LEFT JOIN workshop w
				ON n.workshop_id = w.workshop_id
        WHERE
            nu.notification_user_id = 1;


DELETE FROM review WHERE review_id = 1;
DELETE FROM qna_workshop WHERE qna_id = 1;

INSERT INTO qna_workshop(user_id, program_id, title, content) 
VALUES(5, 1, '일정 변경 문의', '지금 예약한 날짜 말고 다른 날짜로 일정 바꿀 수 있나요?');

DELETE FROM follow WHERE follow_id = 9;
INSERT INTO follow(user_id, workshop_id) VALUES(5, 8);
UPDATE follow SET is_active = true WHERE follow_id = 11;

SELECT * FROM follow WHERE user_id = 5 AND workshop_id = 1;

SELECT
	f.follow_id,
	f.user_id,
	f.workshop_id,
	f.active,
	w.profile_img,
	w.name
FROM follow f
	LEFT JOIN workshop w
	ON f.workshop_id = w.workshop_id
WHERE user_id = 5 AND f.active = true;

SELECT title, address, start_time, end_time, price FROM schedule 
JOIN program USING(program_id)
JOIN workshop USING(workshop_id)
WHERE schedule_id = 1;

SELECT * FROM user WHERE user_id = 5;

SELECT * FROM program
        JOIN workshop USING(workshop_id)
        WHERE program_id = 1;
        
SELECT * FROM program
        JOIN workshop USING(workshop_id)
        WHERE workshop_id = 1;
        
SELECT * FROM workshop
        JOIN program USING(workshop_id)
        WHERE program_id = 1;
        
SELECT count(*) FROM workshop
		JOIN follow USING(workshop_id)
        WHERE workshop_id = 1;
        
SELECT * FROM review
		JOIN user USING(user_id)
        WHERE program_id = 1;
SELECT count(*) AS count FROM review WHERE program_id = 1;

SELECT * FROM qna_workshop 
		JOIN user USING(user_id)
        WHERE program_id = 1;
SELECT count(*) AS count FROM qna_workshop WHERE program_id = 1;

SELECT
	q.qna_id,
	q.user_id,
	q.program_id,
	q.title,
	q.content,
	q.answer,
	q.created_at,
	q.answered_at,
	u.name AS user_name,
	p.workshop_id,
	w.name AS workshop_name
FROM qna_workshop AS q
	JOIN user AS u ON q.user_id = u.user_id
	JOIN program AS p on q.program_id = p.program_id
	JOIN workshop AS w on p.workshop_id = w.workshop_id
WHERE q.program_id = 1;

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