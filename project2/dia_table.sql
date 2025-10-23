-- =========================================
-- 1) USER: 관리자 / 판매자 / 소비자 통합 관리
-- =========================================
CREATE TABLE IF NOT EXISTS user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,        -- [PK] 사용자 고유 ID
    email VARCHAR(100) NOT NULL,            -- 로그인 이메일
    password VARCHAR(255) NOT NULL,                -- 암호화된 비밀번호
    name VARCHAR(50) NOT NULL,                     -- 사용자 이름
    role ENUM('ADMIN','WORKSHOP','CUSTOMER') NOT NULL, -- 사용자 권한 (영어)
    phone VARCHAR(50),                             -- 연락처
    active BOOLEAN DEFAULT TRUE,                   -- 계정 활성 여부
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP  -- 생성일
);

-- =========================================
-- 2) WORKSHOP: 공방 정보
-- =========================================
CREATE TABLE IF NOT EXISTS workshop (
    workshop_id INT AUTO_INCREMENT PRIMARY KEY,   -- [PK] 공방 ID
    owner_id INT NOT NULL,                        -- [FK] 공방 소유자(user.user_id)
    name VARCHAR(100) NOT NULL,                   -- 공방 이름
    description TEXT,                             -- 공방 소개
    address VARCHAR(255),                         -- 공방 주소
    contact_number VARCHAR(50),                   -- 공방 연락처
    status ENUM('모집','마감','숨김') DEFAULT '모집', -- 공방 상태
    approved ENUM('대기','승인','거절') DEFAULT '대기', -- 관리자 승인 상태
    average_rating DECIMAL(3,2) DEFAULT 0.00,     -- 평균 평점
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성일
    FOREIGN KEY (owner_id) REFERENCES user(user_id) ON DELETE CASCADE
);

-- =========================================
-- 3) PROGRAM: 공방 프로그램/클래스
-- =========================================
CREATE TABLE IF NOT EXISTS program (
    program_id INT AUTO_INCREMENT PRIMARY KEY,    -- [PK] 프로그램 ID
    workshop_id INT NOT NULL,                     -- [FK] 소속 공방(workshop.workshop_id)
    title VARCHAR(255) NOT NULL,                  -- 프로그램 제목
    description TEXT,                             -- 프로그램 설명
    category VARCHAR(50),                         -- 카테고리
    price BIGINT NOT NULL,                        -- 가격
    capacity INT NOT NULL,                        -- 정원
    duration_min INT,                             -- 소요 시간(분)
    difficulty ENUM('초급','중급','고급') NOT NULL, -- 난이도 (한글)
    thumb VARCHAR(255),                           -- 썸네일 이미지 경로
    folder VARCHAR(255),                          -- 자료 폴더 경로
    status ENUM('모집','마감','취소') DEFAULT '모집', -- 프로그램 상태
    schedule_type ENUM('ALWAYS','PERIOD') DEFAULT 'ALWAYS', -- 일정 타입 (영어)
    period_start DATETIME,                        -- 기간 시작
    period_end DATETIME,                          -- 기간 종료
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성일
    FOREIGN KEY (workshop_id) REFERENCES workshop(workshop_id) ON DELETE CASCADE
);

-- =========================================
-- 4) SCHEDULE: 프로그램별 일정
-- =========================================
CREATE TABLE IF NOT EXISTS schedule (
    schedule_id INT AUTO_INCREMENT PRIMARY KEY,   -- [PK] 일정 ID
    program_id INT NOT NULL,                      -- [FK] 소속 프로그램(program.program_id)
    start_time DATETIME NOT NULL,                 -- 시작 시간
    end_time DATETIME,                            -- 종료 시간
    capacity INT NOT NULL,                        -- 일정 정원
    current_attendees INT DEFAULT 0,              -- 현재 참가 인원
    status ENUM('모집','마감','취소') DEFAULT '모집', -- 일정 상태
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성일
    FOREIGN KEY (program_id) REFERENCES program(program_id) ON DELETE CASCADE
);

-- =========================================
-- 5) PAYMENT: 결제 정보
-- =========================================
CREATE TABLE IF NOT EXISTS payment (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,    -- [PK] 결제 ID
    schedule_id INT NOT NULL,                     -- [FK] 결제 대상 일정(schedule.schedule_id)
    user_id INT,                                  -- [FK] 결제 사용자(user.user_id)
    method ENUM('CARD','BANK','KAKAOPAY','NAVERPAY') NOT NULL, -- 결제 수단 (영어)
    amount BIGINT NOT NULL,                       -- 결제 금액
    status ENUM('대기','결제완료','실패','환불') DEFAULT '대기', -- 결제 상태 (한글)
    paid_at DATETIME DEFAULT CURRENT_TIMESTAMP,   -- 결제 시각
    FOREIGN KEY (schedule_id) REFERENCES schedule(schedule_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE SET NULL
);

-- =========================================
-- 6) RESERVATION: 예약 정보
-- =========================================
CREATE TABLE IF NOT EXISTS reservation (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY, -- [PK] 예약 ID
    payment_id INT NOT NULL,                        -- [FK] 결제(payment.payment_id)
    schedule_id INT NOT NULL,                       -- [FK] 예약 대상 일정(schedule.schedule_id)
    user_id INT NOT NULL,                           -- [FK] 예약자(user.user_id)
    num_people INT DEFAULT 1,                       -- 예약 인원
    total_price BIGINT NOT NULL,                    -- 총 금액
    status ENUM('결제완료','취소','수강종료') DEFAULT '결제완료', -- 예약 상태
    reserved_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 예약 시각
    FOREIGN KEY (payment_id) REFERENCES payment(payment_id) ON DELETE CASCADE,
    FOREIGN KEY (schedule_id) REFERENCES schedule(schedule_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);

-- =========================================
-- 7) REVIEW: 사용자 후기
-- =========================================
CREATE TABLE IF NOT EXISTS review (
    review_id INT AUTO_INCREMENT PRIMARY KEY,        -- [PK] 후기 ID
    user_id INT,                                     -- [FK] 작성자(user.user_id), 탈퇴 시 NULL
    program_id INT NOT NULL,                         -- [FK] 대상 프로그램(program.program_id)
    rating TINYINT NOT NULL CHECK (rating BETWEEN 1 AND 5), -- 평점 1~5
    content TEXT,                                    -- 후기 내용
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,   -- 생성일
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일
    review_image VARCHAR(255),                       -- 후기 이미지 경로
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE SET NULL,
    FOREIGN KEY (program_id) REFERENCES program(program_id) ON DELETE CASCADE
);

-- =========================================
-- 8) FOLLOW: 고객-공방 관계
-- =========================================
CREATE TABLE IF NOT EXISTS follow (
    follow_id INT AUTO_INCREMENT PRIMARY KEY,        -- [PK] 팔로우 ID
    user_id INT NOT NULL,                             -- [FK] 팔로우 사용자(user.user_id)
    workshop_id INT NOT NULL,                          -- [FK] 팔로우 대상 공방(workshop.workshop_id)
    is_active BOOLEAN DEFAULT TRUE,                   -- 활성 여부
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,    -- 생성일
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일
    UNIQUE KEY uq_user_workshop (user_id, workshop_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE,
    FOREIGN KEY (workshop_id) REFERENCES workshop(workshop_id) ON DELETE CASCADE
);

-- =========================================
-- 9) WISH: 위시리스트
-- =========================================
CREATE TABLE IF NOT EXISTS wish (
    wish_id INT AUTO_INCREMENT PRIMARY KEY,          -- [PK] 위시리스트 ID
    user_id INT NOT NULL,                             -- [FK] 사용자(user.user_id)
    program_id INT,                                   -- [FK] 대상 프로그램(program.program_id)
    is_active BOOLEAN DEFAULT TRUE,                  -- 활성 여부
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,   -- 생성일
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE,
    FOREIGN KEY (program_id) REFERENCES program(program_id) ON DELETE CASCADE
);

-- =========================================
-- 10) QNA_ADMIN: 관리자 문의
-- =========================================
CREATE TABLE IF NOT EXISTS qna_admin (
    qna_id INT AUTO_INCREMENT PRIMARY KEY,           -- [PK] 문의 ID
    user_id INT NOT NULL,                             -- [FK] 문의자(user.user_id)
    admin_id INT,                                     -- [FK] 답변 관리자(user.user_id)
    title VARCHAR(255) NOT NULL,                      -- 문의 제목
    content TEXT NOT NULL,                            -- 문의 내용
    answer TEXT,                                      -- 답변 내용
    status ENUM('대기','답변완료','종료') DEFAULT '대기', -- 문의 상태 (한글)
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,   -- 생성일
    answered_at DATETIME,                             -- 답변일
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE,
    FOREIGN KEY (admin_id) REFERENCES user(user_id) ON DELETE SET NULL
);

-- =========================================
-- 11) QNA_WORKSHOP: 고객 → 공방 문의
-- =========================================
CREATE TABLE IF NOT EXISTS qna_workshop (
    qna_id INT AUTO_INCREMENT PRIMARY KEY,         -- [PK] 문의 ID
    user_id INT,                                    -- [FK] 문의자(user.user_id), 탈퇴 시 NULL
    program_id INT NOT NULL,                        -- [FK] 대상 프로그램(program.program_id)
    title VARCHAR(100),                             -- 문의 제목
    content TEXT,                                   -- 문의 내용
    status ENUM('대기','답변완료') DEFAULT '대기', -- 상태 (한글)
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성일
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE SET NULL,
    FOREIGN KEY (program_id) REFERENCES program(program_id) ON DELETE CASCADE
);

-- =========================================
-- 12) PROFIT: 공방/프로그램별 수익
-- =========================================
CREATE TABLE IF NOT EXISTS profit (
    profit_id INT AUTO_INCREMENT PRIMARY KEY,      -- [PK] 수익 ID
    workshop_id INT NOT NULL,                       -- [FK] 공방(workshop.workshop_id)
    program_id INT NULL,                            -- [FK] 프로그램(program.program_id), NULL 가능
    total_amount BIGINT NOT NULL,                  -- 총 수익
    commission_rate INT DEFAULT 0,                 -- 수수료율(%)
    commission_amt BIGINT GENERATED ALWAYS AS (total_amount * commission_rate / 100) STORED, -- 수수료 금액
    paid_status ENUM('정산완료','정산대기','환불','취소') DEFAULT '정산대기', -- 정산 상태
    settled_at DATETIME,                            -- 정산일
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성일
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일
    FOREIGN KEY (workshop_id) REFERENCES workshop(workshop_id) ON DELETE CASCADE,
    FOREIGN KEY (program_id) REFERENCES program(program_id) ON DELETE SET NULL
);

-- =========================================
-- 13) NOTIFICATION: 알림
-- =========================================
CREATE TABLE IF NOT EXISTS notification (
    notification_id INT AUTO_INCREMENT PRIMARY KEY, -- [PK] 알림 ID
    sender_id INT,                                  -- [FK] 발신자(user.user_id)
    target_type VARCHAR(100) NOT NULL,             -- 대상 타입(USER/WORKSHOP 등)
    workshop_id INT,                                -- 관련 공방(workshop.workshop_id)
    message TEXT NOT NULL,                          -- 메시지 내용
    type VARCHAR(100) NOT NULL,                     -- 알림 타입
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,  -- 생성일
    FOREIGN KEY (sender_id) REFERENCES user(user_id) ON DELETE CASCADE,
    FOREIGN KEY (workshop_id) REFERENCES workshop(workshop_id) ON DELETE SET NULL
);

-- =========================================
-- 14) NOTIFICATION_USER: 알림 수신자 관리
-- =========================================
CREATE TABLE IF NOT EXISTS notification_user (
    notification_user_id INT AUTO_INCREMENT PRIMARY KEY,             -- [PK] 알림-사용자 매핑 ID
    notification_id INT NOT NULL,                  -- [FK] 알림(notification.notification_id)
    user_id INT NOT NULL,                           -- [FK] 수신자(user.user_id)
    is_read BOOLEAN DEFAULT FALSE,                 -- 읽음 여부
    FOREIGN KEY (notification_id) REFERENCES notification(notification_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);
