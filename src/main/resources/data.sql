-- 모임 더미 데이터
INSERT INTO gatherings
    (current_member, latitude, longitude, max_member, created_at, gathering_date_time, updated_at, address, content, title)
VALUES
    (5, 37.7749, -122.4194, 10, NOW(), '2024-11-01 14:00:00', NOW(), '1234 Main St', '첫 번째 모임 설명', '첫 번째 모임'),
    (2, 34.0522, -118.2437, 5, NOW(), '2024-11-05 15:00:00', NOW(), '5678 Sunset Blvd', '두 번째 모임 설명', '두 번째 모임'),
    (8, 40.7128, -74.0060, 20, NOW(), '2024-11-10 10:00:00', NOW(), '9101 Broadway', '세 번째 모임 설명', '세 번째 모임'),
    (3, 51.5074, -0.1278, 15, NOW(), '2024-11-15 18:00:00', NOW(), '12 Oxford St', '네 번째 모임 설명', '네 번째 모임'),
    (1, 48.8566, 2.3522, 4, NOW(), '2024-11-20 12:00:00', NOW(), '34 Champs-Elysees', '다섯 번째 모임 설명', '다섯 번째 모임'),
    (6, 35.6895, 139.6917, 12, NOW(), '2024-11-25 14:00:00', NOW(), '56 Shibuya', '여섯 번째 모임 설명', '여섯 번째 모임'),
    (4, 55.7558, 37.6173, 8, NOW(), '2024-12-01 11:00:00', NOW(), '78 Red Square', '일곱 번째 모임 설명', '일곱 번째 모임'),
    (7, 35.6762, 139.6503, 10, NOW(), '2024-12-05 13:00:00', NOW(), '90 Shinjuku', '여덟 번째 모임 설명', '여덟 번째 모임'),
    (2, 52.5200, 13.4050, 6, NOW(), '2024-12-10 09:00:00', NOW(), '101 Brandenburg Gate', '아홉 번째 모임 설명', '아홉 번째 모임'),
    (9, 40.730610, -73.935242, 20, NOW(), '2024-12-15 19:00:00', NOW(), '112 Broadway Ave', '열 번째 모임 설명', '열 번째 모임');

-- 유저 더미 데이터
INSERT INTO users
    (created_at, updated_at, nickname, description, profile_image, univ_name, email, token)
VALUES
    (NOW(), NOW(), 'user1nick', 'User 1 description', 'https://example.com/user1.png', 'University 1', 'test1@test.com',  'token1'),
    (NOW(), NOW(), 'user2nick', 'User 2 description', 'https://example.com/user2.png', 'University 2', 'test2@test.com', 'token2'),
    (NOW(), NOW(), 'user3nick', 'User 3 description', 'https://example.com/user3.png', 'University 3', 'test3@test.com', 'token3'),
    (NOW(), NOW(), 'user4nick', 'User 4 description', 'https://example.com/user4.png', 'University 4', 'test4@test.com', 'token4'),
    (NOW(), NOW(), 'user5nick', 'User 5 description', 'https://example.com/user5.png', 'University 5', 'test5@test.com', 'token5');
