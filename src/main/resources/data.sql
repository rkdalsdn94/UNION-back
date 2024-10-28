-- 모임 더미 데이터
INSERT INTO gatherings
    (
        current_member, latitude, longitude, views, max_member, created_at, gathering_date_time,
        updated_at, address, content, title, eup_myeon_dong
    )
VALUES
    (5, 37.7749, -122.4194, 10, 10, NOW(), '2024-11-01 14:00:00',
     NOW(), '1234 Main St', '첫 번째 모임 설명', '첫 번째 모임', '역삼동'),
    (2, 34.0522, -118.2437, 100, 5, NOW(), '2024-11-05 15:00:00',
     NOW(), '5678 Sunset Blvd', '두 번째 모임 설명', '두 번째 모임', '서교동'),
    (8, 40.7128, -74.0060, 1, 20, NOW(), '2024-11-10 10:00:00',
     NOW(), '9101 Broadway', '세 번째 모임 설명', '세 번째 모임', '성수동'),
    (3, 51.5074, -0.1278, 5, 15, NOW(), '2024-11-15 18:00:00',
     NOW(), '12 Oxford St', '네 번째 모임 설명', '네 번째 모임', '합정동'),
    (1, 48.8566, 2.3522, 2, 4, NOW(), '2024-11-20 12:00:00',
     NOW(), '34 Champs-Elysees', '다섯 번째 모임 설명', '다섯 번째 모임', '둔산동'),
    (6, 35.6895, 139.6917, 15, 12, NOW(), '2024-11-25 14:00:00',
     NOW(), '56 Shibuya', '여섯 번째 모임 설명', '여섯 번째 모임', '괴정동'),
    (4, 55.7558, 37.6173, 22, 8, NOW(), '2024-12-01 11:00:00',
     NOW(), '78 Red Square', '일곱 번째 모임 설명', '일곱 번째 모임', '신북읍'),
    (7, 35.6762, 139.6503, 0, 10, NOW(), '2024-12-05 13:00:00',
     NOW(), '90 Shinjuku', '여덟 번째 모임 설명', '여덟 번째 모임', '남면'),
    (2, 52.5200, 13.4050, 1, 6, NOW(), '2024-12-10 09:00:00',
     NOW(), '101 Brandenburg Gate', '아홉 번째 모임 설명', '아홉 번째 모임', '중앙동'),
    (9, 40.730610, -73.935242, 7, 20, NOW(), '2024-12-15 19:00:00',
     NOW(), '112 Broadway Ave', '열 번째 모임 설명', '열 번째 모임', '수민동');

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
    (created_at, updated_at, nickname, description, profile_image, univ_name, email, token, provider)
VALUES
    (NOW(), NOW(), 'user1nick', 'User 1 description', 'https://example.com/user1.png', 'University 1', 'test1@test.com',  'token1', 'google'),
    (NOW(), NOW(), 'user2nick', 'User 2 description', 'https://example.com/user2.png', 'University 2', 'test2@test.com', 'token2', 'google'),
    (NOW(), NOW(), 'user3nick', 'User 3 description', 'https://example.com/user3.png', 'University 3', 'test3@test.com', 'token3', 'google'),
    (NOW(), NOW(), 'user4nick', 'User 4 description', 'https://example.com/user4.png', 'University 4', 'test4@test.com', 'token4', 'google'),
    (NOW(), NOW(), 'user5nick', 'User 5 description', 'https://example.com/user5.png', 'University 5', 'test5@test.com', 'token5', 'google');
