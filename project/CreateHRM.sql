-- 1. 인적 자원 테이블



-- 직원 정보 테이블
CREATE TABLE employee_info (
    emp_no INT AUTO_INCREMENT PRIMARY KEY,            -- 직원 고유 번호 (PK)
    emp_id VARCHAR(50) UNIQUE NOT NULL,               -- 로그인용 아이디
    emp_in VARCHAR(14) UNIQUE NOT NULL,               -- 주민등록번호 (예: 901010-1234567)
    emp_pwd VARCHAR(100) NOT NULL,                    -- 비밀번호 (해시 처리 전제)
    emp_name VARCHAR(50) NOT NULL,                    -- 이름
    -- gender ENUM('M', 'F') NOT NULL,                   -- 성별
    -- birthdate DATE NOT NULL,                          -- 생년월일
    job_no INT NOT NULL,                              -- 직급 번호 (FK)
    dept_no INT NOT NULL,                             -- 부서 번호 (FK)
    hire_date DATE NOT NULL,                          -- 입사일
    quit_date DATE DEFAULT NULL,                      -- 퇴사일 (NULL: 재직 중)
    salary INT NOT NULL,                    -- 기본급여
    addr VARCHAR(200),                                -- 주소
    phone VARCHAR(20),                                -- 연락처
    email VARCHAR(100)                             	  -- 이메일

    
);

-- 직급 테이블
CREATE TABLE job_position (
    job_no INT AUTO_INCREMENT PRIMARY KEY, -- 직급 번호
    job_title VARCHAR(50) NOT NULL UNIQUE, -- 직급명
    job_level INT NOT NULL, -- 직급 레벨 (낮을수록 상위)
    leave_count INT, -- 직급별 휴가 개수
    description TEXT -- 직책 설명
);
-- 부서 테이블
CREATE TABLE department (
    dept_no INT AUTO_INCREMENT PRIMARY KEY, -- 부서 번호
    dept_name VARCHAR(50) NOT NULL UNIQUE, -- 부서 이름
    location VARCHAR(100) -- 위치
    
);

-- 출퇴근/근태 관리
CREATE TABLE attendance_log (
    att_id INT AUTO_INCREMENT PRIMARY KEY, -- 출퇴근 기본키
    emp_no INT NOT NULL, 
    work_date DATE NOT NULL, 
    check_in TIME, -- 출근
    check_out TIME, -- 퇴근
    status ENUM('출근', '지각', '조퇴', '결근', '휴가') DEFAULT '결근' -- 출근 시간 변경 시 판별
);

-- 휴가 신청 테이블
CREATE TABLE leave_request (
    leave_id INT AUTO_INCREMENT PRIMARY KEY,
    emp_no INT NOT NULL,
    leave_type ENUM('연차', '병가', '경조사', '기타') NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    reason TEXT,
    status ENUM('대기', '승인', '반려') DEFAULT '대기',
    request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 휴가 개수 관리 테이블
CREATE TABLE leave_remain(
	lr_no INT AUTO_INCREMENT PRIMARY KEY,
    lr_count INT NOT NULL, -- 년도별 휴가 남은 개수
    leave_year INT, -- 받은 년도
    emp_no INT NOT NULL -- 직원번호 외래키
);

-- 인사 평가 테이블
CREATE TABLE performance_review (
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    emp_no INT NOT NULL,
    evaluator_emp_no INT NOT NULL,
    review_date DATE NOT NULL,
    attitude_score INT CHECK (attitude_score BETWEEN 1 AND 5), -- 근무 태도 점수
    achieve_score INT CHECK (achieve_score BETWEEN 1 AND 5), -- 성과 점수
    
    comments TEXT
);


-- 연봉 변경 테이블
CREATE TABLE salary_history (
    salary_id INT AUTO_INCREMENT PRIMARY KEY,
    emp_no INT NOT NULL, -- 직원 정보 외래키
    change_date DATE NOT NULL, -- 바뀐 날짜
    old_salary INT, -- 원래 연봉
    new_salary INT, -- 새로운 연봉
    reason TEXT -- 사유
);

-- 수당 종류 테이블
CREATE TABLE bonus (
bonus_no INT PRIMARY KEY,
bonus_name VARCHAR(50), -- 수당 이름
bonus_desc TEXT -- 수당 설명
);  
-- 수당 제공 되는 직원 정보 테이블
CREATE TABLE bonus_payment(
bonus_payment_no INT AUTO_INCREMENT PRIMARY KEY,
payment INT NOT NULL, -- 수당 지급 금액
pay_date DATE NOT NULL, -- 수당 지급 날짜
bonus_no INT NOT NULL, -- 외래키 (수당 종류)
emp_no INT NOT NULL -- 외래키 (직원)
);

-- 퇴사자 관리 테이블 (애매함) -- 퇴직금 지불 여부 및 지급 금액 표시
CREATE TABLE quitter(
quit_no INT PRIMARY KEY,
quit_pay int, -- 퇴사자 퇴직금
quit_pay_date DATE, -- 퇴사자 퇴직금 지급날짜
emp_no INT
);

-- 외래키 설정
ALTER TABLE employee_info ADD
FOREIGN KEY (job_no) REFERENCES job_position(job_no);
ALTER TABLE employee_info ADD
FOREIGN KEY (dept_no) REFERENCES department(dept_no);
ALTER TABLE attendance_log ADD
FOREIGN KEY (emp_no) REFERENCES employee_info(emp_no);
ALTER TABLE leave_request ADD
FOREIGN KEY (emp_no) REFERENCES employee_info(emp_no);
ALTER TABLE leave_remain ADD
FOREIGN KEY (emp_no) REFERENCES employee_info(emp_no);
ALTER TABLE performance_review ADD
FOREIGN KEY (emp_no) REFERENCES employee_info(emp_no);
ALTER TABLE performance_review ADD
FOREIGN KEY (evaluator_emp_no) REFERENCES employee_info(emp_no);
ALTER TABLE salary_history ADD
FOREIGN KEY (emp_no) REFERENCES employee_info(emp_no);
ALTER TABLE bonus_payment ADD
FOREIGN KEY (bonus_no) REFERENCES bonus(bonus_no);
ALTER TABLE bonus_payment ADD
FOREIGN KEY (emp_no) REFERENCES employee_info(emp_no);
ALTER TABLE quitter ADD
FOREIGN KEY (emp_no) REFERENCES employee_info(emp_no);


