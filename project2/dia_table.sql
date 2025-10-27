-- =========================================
-- 1) USER: 관리자 / 판매자 / 소비자 통합 관리
-- =========================================
CREATE TABLE IF NOT EXISTS user (
    user_id       INT AUTO_INCREMENT PRIMARY KEY,
    email         VARCHAR(100) NOT NULL UNIQUE,          -- [수정] UNIQUE 제약조건 추가
    password      VARCHAR(255) NOT NULL,
    name          VARCHAR(50) NOT NULL,
    role          ENUM('ADMIN','WORKSHOP','CUSTOMER') NOT NULL,
    phone         VARCHAR(50),
    active        BOOLEAN DEFAULT TRUE,
    created_at    DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- 2) WORKSHOP: 공방 정보
-- =========================================
CREATE TABLE IF NOT EXISTS workshop (
    workshop_id INT AUTO_INCREMENT PRIMARY KEY,
    owner_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    profile_img varchar(200),
    address VARCHAR(255),
    contact_number VARCHAR(50),
    status ENUM('정상','휴업','숨김') DEFAULT '정상',
    approved ENUM('대기','승인','거절') DEFAULT '대기',
    active BOOLEAN DEFAULT TRUE,
    average_rating DECIMAL(3,2) DEFAULT 0.00,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (owner_id) REFERENCES user(user_id) ON DELETE CASCADE
);

-- =========================================
-- 3) PROGRAM: 공방 프로그램/클래스
-- =========================================
CREATE TABLE IF NOT EXISTS program (
    program_id INT AUTO_INCREMENT PRIMARY KEY,
    workshop_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    category VARCHAR(50),
    price BIGINT NOT NULL,
    duration_min INT,
    difficulty ENUM('초급','중급','고급') NOT NULL,
    thumb VARCHAR(255),
    folder VARCHAR(255),
    status ENUM('모집','마감','취소') DEFAULT '모집',
    schedule_type ENUM('ALWAYS','PERIOD') DEFAULT 'ALWAYS',
    period_start DATETIME,
    period_end DATETIME,
    active BOOLEAN DEFAULT TRUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (workshop_id) REFERENCES workshop(workshop_id) ON DELETE CASCADE
);

-- =========================================
-- 4) SCHEDULE: 프로그램별 일정
-- =========================================
CREATE TABLE IF NOT EXISTS schedule (
    schedule_id INT AUTO_INCREMENT PRIMARY KEY,
    program_id INT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME,
    capacity INT NOT NULL,
    current_attendees INT DEFAULT 0,
    status ENUM('모집','마감','취소') DEFAULT '모집',
    active BOOLEAN DEFAULT TRUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (program_id) REFERENCES program(program_id) ON DELETE CASCADE
);

-- =========================================
-- 5) RESERVATION: 예약 정보 (수정됨)
--  - '예약'이 중심이 되도록 구조 변경
-- =========================================
CREATE TABLE IF NOT EXISTS reservation (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    schedule_id    INT NOT NULL,
    user_id        INT, -- 탈퇴 시 예약 기록은 남기기 위해 NULL 허용
    num_people     INT DEFAULT 1,
    total_price    BIGINT NOT NULL,
    status         ENUM('대기','확정','취소','수강종료') DEFAULT '대기', -- '대기' 상태 추가, '결제완료' -> '확정'
    reserved_at    DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (schedule_id) REFERENCES schedule(schedule_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE SET NULL -- ON DELETE SET NULL로 변경
);


-- =========================================
-- 6) PAYMENT: 결제 정보 (수정됨)
--  - '예약'에 종속되는 구조로 변경
-- =========================================
CREATE TABLE IF NOT EXISTS payment (
    payment_id     INT AUTO_INCREMENT PRIMARY KEY,
    reservation_id INT NOT NULL, -- reservation_id를 FK로 참조
    method         ENUM('CARD','BANK','KAKAOPAY','NAVERPAY') NOT NULL,
    amount         BIGINT NOT NULL,
    status         ENUM('대기','완료','실패','환불') DEFAULT '대기', -- '결제완료' -> '완료'로 명확화
    paid_at        DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (reservation_id) REFERENCES reservation(reservation_id) ON DELETE CASCADE
);

-- =========================================
-- 7) REVIEW: 사용자 후기
-- =========================================
CREATE TABLE IF NOT EXISTS review (
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    program_id INT,
    reservation_id INT, -- [수정] program_id, schedule_id 대신 넣음
    rating TINYINT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    content TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    review_image VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE SET NULL,
    FOREIGN KEY (reservation_id ) REFERENCES reservation(reservation_id) ON DELETE CASCADE,
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
    user_id INT NOT NULL,                            -- [FK] 사용자(user.user_id)
    program_id INT NOT NULL,                         -- [FK] 대상 프로그램(program.program_id)
    is_active BOOLEAN DEFAULT TRUE,                  -- 활성 여부
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,   -- 생성일
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일
    UNIQUE KEY uq_user_program (user_id, program_id), -- [추가] 이 제약조건을 추가하면 중복 찜 방지 가능
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE,
    FOREIGN KEY (program_id) REFERENCES program(program_id) ON DELETE CASCADE
);

-- =========================================
-- 10) QNA_ADMIN: 관리자 문의
-- =========================================
CREATE TABLE IF NOT EXISTS qna_admin (
    qna_id INT AUTO_INCREMENT PRIMARY KEY,            -- [PK] 문의 ID
    user_id INT NOT NULL,                             -- [FK] 문의자(user.user_id)
    admin_id INT,                                     -- [FK] 답변 관리자(user.user_id)
    title VARCHAR(255) NOT NULL,                      -- 문의 제목
    content TEXT NOT NULL,                            -- 문의 내용
    answer TEXT,                                      -- 답변 내용
    status ENUM('대기','답변완료','종료') DEFAULT '대기', -- 문의 상태 (한글)
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,    -- 생성일
    answered_at DATETIME,                             -- 답변일
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE,
    FOREIGN KEY (admin_id) REFERENCES user(user_id) ON DELETE SET NULL
);

-- =========================================
-- 11) QNA_WORKSHOP: 고객 → 공방 문의
-- =========================================
CREATE TABLE IF NOT EXISTS qna_workshop (
    qna_id INT AUTO_INCREMENT PRIMARY KEY,          -- [PK] 문의 ID
    user_id INT,                                    -- [FK] 문의자(user.user_id), 탈퇴 시 NULL
    program_id INT NOT NULL,                        -- [FK] 대상 프로그램(program.program_id)
    title VARCHAR(100),                             -- 문의 제목
    content TEXT,                                   -- 문의 내용
    answer TEXT,                                    -- 답변 내용
    status ENUM('대기','답변완료') DEFAULT '대기',   -- 상태 (한글)
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,  -- 생성일
    answered_at DATETIME, -- 답변일
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
    monthly VARCHAR(7),                             -- YYYY-MM 형식으로 월별 조회용
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
    user_id INT NOT NULL,                          -- [FK] 수신자(user.user_id)
    is_read BOOLEAN DEFAULT FALSE,                 -- 읽음 여부
    FOREIGN KEY (notification_id) REFERENCES notification(notification_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);