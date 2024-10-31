-- 모임 더미 데이터
INSERT INTO gatherings
(
    current_member, latitude, longitude, views, max_member, created_at, gathering_date_time,
    updated_at, address, content, title, eup_myeon_dong
)
VALUES
    (5, 37.5665, 126.9780, 10, 10, DATE_SUB(NOW(), INTERVAL 9 HOUR), DATE_ADD(NOW(), INTERVAL 1 HOUR),
     NOW(), '서울 중구 명동', '첫 번째 모임 설명', '첫 번째 모임', '명동'),
    (2, 36.3504, 127.3845, 100, 5, DATE_SUB(NOW(), INTERVAL 8 HOUR), DATE_ADD(NOW(), INTERVAL 2 HOUR),
     NOW(), '대전 서구 둔산동', '두 번째 모임 설명', '두 번째 모임', '둔산동'),
    (8, 35.8722, 128.6025, 1, 20, DATE_SUB(NOW(), INTERVAL 7 HOUR), DATE_ADD(NOW(), INTERVAL 3 HOUR),
     NOW(), '대구 수성구 수성동', '세 번째 모임 설명', '세 번째 모임', '수성동'),
    (3, 35.1796, 129.0756, 5, 15, DATE_SUB(NOW(), INTERVAL 6 HOUR), DATE_ADD(NOW(), INTERVAL 4 HOUR),
     NOW(), '부산 해운대구 해운대해변로', '네 번째 모임 설명', '네 번째 모임', '해운대동'),
    (1, 35.1595, 126.8526, 2, 4, DATE_SUB(NOW(), INTERVAL 5 HOUR), DATE_ADD(NOW(), INTERVAL 5 HOUR),
     NOW(), '광주 북구 용봉동', '다섯 번째 모임 설명', '다섯 번째 모임', '용봉동'),
    (6, 35.5384, 129.3114, 15, 12, DATE_SUB(NOW(), INTERVAL 4 HOUR), DATE_ADD(NOW(), INTERVAL 6 HOUR),
     NOW(), '울산 남구 삼산동', '여섯 번째 모임 설명', '여섯 번째 모임', '삼산동'),
    (4, 33.4996, 126.5312, 22, 8, DATE_SUB(NOW(), INTERVAL 3 HOUR), DATE_ADD(NOW(), INTERVAL 7 HOUR),
     NOW(), '제주 제주시 연동', '일곱 번째 모임 설명', '일곱 번째 모임', '연동'),
    (7, 37.4563, 126.7052, 0, 10, DATE_SUB(NOW(), INTERVAL 2 HOUR), DATE_ADD(NOW(), INTERVAL 8 HOUR),
     NOW(), '인천 중구 을왕동', '여덟 번째 모임 설명', '여덟 번째 모임', '을왕동'),
    (2, 37.2751, 127.0097, 1, 6, DATE_SUB(NOW(), INTERVAL 1 HOUR), DATE_ADD(NOW(), INTERVAL 9 HOUR),
     NOW(), '경기 성남시 분당구', '아홉 번째 모임 설명', '아홉 번째 모임', '분당동'),
    (9, 35.1798, 128.1076, 7, 20, NOW(), DATE_ADD(NOW(), INTERVAL 10 HOUR),
     NOW(), '경남 김해시 내외동', '열 번째 모임 설명', '열 번째 모임', '내외동');

-- 파티 더미 데이터
INSERT INTO parties (gathering_id, user_id, role, created_at, updated_at)
VALUES
    (1, 1, 'OWNER', NOW(), NOW()),
    (2, 2, 'OWNER', NOW(), NOW()),
    (3, 3, 'OWNER', NOW(), NOW()),
    (4, 4, 'OWNER', NOW(), NOW()),
    (5, 5, 'OWNER', NOW(), NOW()),
    (6, 1, 'OWNER', NOW(), NOW()),
    (7, 2, 'OWNER', NOW(), NOW()),
    (8, 3, 'OWNER', NOW(), NOW()),
    (9, 4, 'OWNER', NOW(), NOW()),
    (10, 5, 'OWNER', NOW(), NOW());

-- 유저 더미 데이터
INSERT INTO users
    (created_at, updated_at, nickname, description, profile_image, univ_name, email, token, provider, deleted)
VALUES
    (NOW(), NOW(), 'user1nick', 'User 1 description', 'https://example.com/user1.png', 'University 1', 'test1@test.com',  'token1', 'google', false),
    (NOW(), NOW(), 'user2nick', 'User 2 description', 'https://example.com/user2.png', 'University 2', 'test2@test.com', 'token2', 'google', false),
    (NOW(), NOW(), 'user3nick', 'User 3 description', 'https://example.com/user3.png', 'University 3', 'test3@test.com', 'token3', 'google', false),
    (NOW(), NOW(), 'user4nick', 'User 4 description', 'https://example.com/user4.png', 'University 4', 'test4@test.com', 'token4', 'google', false),
    (NOW(), NOW(), 'user5nick', 'User 5 description', 'https://example.com/user5.png', 'University 5', 'test5@test.com', 'token5', 'google', false);
