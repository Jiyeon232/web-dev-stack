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
    profile_img 	VARCHAR(512),
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
    profile_img varchar(512),
    address VARCHAR(255),
    contact_number VARCHAR(50),
    status ENUM('정상','휴업','숨김') DEFAULT '정상',
    approved ENUM('대기','승인','거절') DEFAULT '대기',
    active BOOLEAN DEFAULT FALSE,
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
    thumb VARCHAR(512),
    status ENUM('모집','마감','취소') DEFAULT '모집',
    approved ENUM('대기','승인','거절') DEFAULT '대기',
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
    profit_paid BOOLEAN DEFAULT FALSE,
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
    review_image VARCHAR(512),
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
    active BOOLEAN DEFAULT TRUE,                   -- 활성 여부
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
    active BOOLEAN DEFAULT TRUE,                  -- 활성 여부
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
    qna_admin_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    admin_id INT,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    answer TEXT,
    status ENUM('대기','답변완료','종료') DEFAULT '대기',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    answered_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE,
    FOREIGN KEY (admin_id) REFERENCES user(user_id) ON DELETE SET NULL
);

-- =========================================
-- 11) QNA_WORKSHOP: 고객 → 공방 문의
-- =========================================
CREATE TABLE IF NOT EXISTS qna_workshop (
    qna_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    program_id INT NOT NULL,
    title VARCHAR(100),
    content TEXT,
    answer TEXT,
    status ENUM('대기','답변완료') DEFAULT '대기',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    answered_at DATETIME,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
    commission_rate INT DEFAULT 10,                 -- 수수료율(%)
    commission_amt BIGINT GENERATED ALWAYS AS (total_amount * commission_rate / 100 + 50000) STORED, -- 수수료 금액
    paid_status ENUM('정산완료','정산대기','정산중','환불','취소') DEFAULT '정산대기', -- 정산 상태
    settled_at DATETIME,                            -- 정산일
    monthly VARCHAR(7),                             -- YYYY-MM 형식으로 월별 조회용
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성일
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일
    UNIQUE KEY uk_profit(workshop_id, program_id, monthly),
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
    user_id INT NOT NULL,      
    workshop_id INT,-- [FK] 수신자(user.user_id)
    viewed BOOLEAN DEFAULT FALSE,                 -- 읽음 여부
    FOREIGN KEY (notification_id) REFERENCES notification(notification_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);

-- =========================================
-- 15) PROGRAM_IMAGE : 프로그램 이미지
-- =========================================
CREATE TABLE program_image (
    program_image_id INT AUTO_INCREMENT PRIMARY KEY,
    program_id INT NOT NULL,
    folder VARCHAR(255) COMMENT '이미지들이 저장된 서버/S3의 공통 폴더 경로 (예: program/101/)',
    image VARCHAR(512) NOT NULL COMMENT '이미지 파일의 전체 URL 또는 파일명',
    img_no INT NOT NULL COMMENT '프로그램 내에서 이미지가 노출될 순서 (0부터 시작)',
    
    -- 외래 키 (FK) 설정
    FOREIGN KEY (program_id) REFERENCES program(program_id) ON DELETE CASCADE 
);

-- =========================================
-- 16) ACTION_LOG : 관리자 활동내역 추적
-- =========================================
CREATE TABLE IF NOT EXISTS action_log (
    action_log_id INT AUTO_INCREMENT PRIMARY KEY,
    target_type VARCHAR(50) NOT NULL,     -- WORKSHOP / PROGRAM / QNA / BANNER / USER 등
    target_id INT NOT NULL,               -- workshop_id, program_id, qna_id 등
    admin_id INT NOT NULL,                -- 조작한 관리자
    action_type VARCHAR(100) NOT NULL,    -- APPROVE / REJECT / UPDATE / CREATE / DELETE 등
    reason TEXT,                           -- 거절 사유, 수정 이유
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (admin_id) REFERENCES user(user_id)
);

-- =========================================
-- 17) BANNER : 관리자 배너 수정
-- =========================================
CREATE TABLE banner (
    banner_id INT AUTO_INCREMENT PRIMARY KEY,
    admin_id INT NOT NULL,
    title VARCHAR(255),
    image VARCHAR(512) NOT NULL,
    link VARCHAR(255),
    sort_order INT DEFAULT 0,
    active BOOLEAN DEFAULT TRUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (admin_id) REFERENCES user(user_id) ON DELETE CASCADE
);

-- =========================================
-- 18) SETTLEMENT : 공방 정산 관리
-- =========================================
CREATE TABLE IF NOT EXISTS settlement (
    settlement_id INT AUTO_INCREMENT PRIMARY KEY,
    profit_id INT NOT NULL,
    origin_commission INT NOT NULL,
    adjust_amount INT DEFAULT 0,
    final_amount BIGINT GENERATED ALWAYS AS (origin_commission + adjust_amount) STORED,
    status VARCHAR(20) DEFAULT '정산중', -- 정산중, 정산완료, 취소, 환불
    bill_date DATETIME DEFAULT NOW(),
    paid_date DATETIME,
    admin_checker_id INT,
    FOREIGN KEY (profit_id) REFERENCES profit(profit_id),
    FOREIGN KEY (admin_checker_id) REFERENCES user(user_id)
);

-- =========================================
-- 19) ADMIN_ROLE: 관리자 상세 권한 관리
-- =========================================
CREATE TABLE IF NOT EXISTS admin_role (
    admin_role_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id       INT NOT NULL,
    -- SUPER : 최고 관리자 (모든 권한)
    -- PAY    : 정산/수익 관리자
    -- CS     : 고객 문의/신고 관리자
    role_name     ENUM('SUPER', 'PAY', 'CS') NOT NULL, 
    
    UNIQUE KEY uk_admin_role (user_id, role_name),
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);

-- =========================================
-- 20) VISIT_LOG: 방문자 로그
-- =========================================
CREATE TABLE IF NOT EXISTS visit_log (
    visit_id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id       INT,                                 -- 회원인 경우 ID 저장 (비회원은 NULL)
    workshop_id   INT,                                 -- [핵심] 공방 상세페이지 접속 시 해당 공방 ID
    program_id	  INT,                                 -- [핵심] 프로그램 상세페이지 접속 시 해당 프로그램 ID
    visited_at    DATETIME DEFAULT CURRENT_TIMESTAMP,  -- 방문 시간
    
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE SET NULL,
    FOREIGN KEY (workshop_id) REFERENCES workshop(workshop_id) ON DELETE SET NULL,
    FOREIGN KEY (program_id) REFERENCES program(program_id) ON DELETE SET NULL
);