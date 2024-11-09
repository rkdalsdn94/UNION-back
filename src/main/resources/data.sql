-- 모임 더미 데이터
INSERT INTO gatherings
(
    current_member, latitude, longitude, views, max_member, created_at, gathering_date_time,
    updated_at, address, content, title, eup_myeon_dong, recruited,
    thumbnail
) VALUES
    (5, 37.5665, 126.9780, 10, 10, DATE_SUB(NOW(), INTERVAL 9 HOUR), DATE_ADD(NOW(), INTERVAL 1 HOUR),
     NOW(), '서울 중구 명동', '첫 번째 모임 설명', '첫 번째 모임', '명동', false,
     'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png'),
    (2, 36.3504, 127.3845, 100, 5, DATE_SUB(NOW(), INTERVAL 8 HOUR), DATE_ADD(NOW(), INTERVAL 2 HOUR),
     NOW(), '대전 서구 둔산동', '두 번째 모임 설명', '두 번째 모임', '둔산동', false,
    'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png'),
    (8, 35.8722, 128.6025, 1, 20, DATE_SUB(NOW(), INTERVAL 7 HOUR), DATE_ADD(NOW(), INTERVAL 3 HOUR),
     NOW(), '대구 수성구 수성동', '세 번째 모임 설명', '세 번째 모임', '수성동', false,
     'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png'),
    (3, 35.1796, 129.0756, 5, 15, DATE_SUB(NOW(), INTERVAL 6 HOUR), DATE_ADD(NOW(), INTERVAL 4 HOUR),
     NOW(), '부산 해운대구 해운대해변로', '네 번째 모임 설명', '네 번째 모임', '해운대동', true,
     'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png'),
    (1, 35.1595, 126.8526, 2, 4, DATE_SUB(NOW(), INTERVAL 5 HOUR), DATE_ADD(NOW(), INTERVAL 5 HOUR),
     NOW(), '광주 북구 용봉동', '다섯 번째 모임 설명', '다섯 번째 모임', '용봉동', true, null),
    (
     6, 35.5384, 129.3114, 15, 12, DATE_SUB(NOW(), INTERVAL 4 HOUR),
     DATE_ADD(NOW(), INTERVAL 6 HOUR),
     NOW(), '울산 남구 삼산동', '여섯 번째 모임 설명', '여섯 번째 모임', '삼산동', false,
     'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png'
    ),
    (4, 33.4996, 126.5312, 22, 8, DATE_SUB(NOW(), INTERVAL 3 HOUR),
     DATE_ADD(NOW(), INTERVAL 7 HOUR),
     NOW(), '제주 제주시 연동', '일곱 번째 모임 설명', '일곱 번째 모임', '연동', true, null
    ),
    (7, 37.4563, 126.7052, 0, 10, DATE_SUB(NOW(), INTERVAL 2 HOUR),
     DATE_ADD(NOW(), INTERVAL 8 HOUR),
     NOW(), '인천 중구 을왕동', '여덟 번째 모임 설명', '여덟 번째 모임', '을왕동', true, null
    ),
    (2, 37.2751, 127.0097, 1, 6, DATE_SUB(NOW(), INTERVAL 1 HOUR),
     DATE_ADD(NOW(), INTERVAL 9 HOUR),
     NOW(), '경기 성남시 분당구', '아홉 번째 모임 설명', '아홉 번째 모임', '분당동', false, null
    ),
    (9, 35.1798, 128.1076, 7, 20, NOW(), DATE_ADD(NOW(), INTERVAL 10 HOUR),
     NOW(), '경남 김해시 내외동', '열 번째 모임 설명', '열 번째 모임', '내외동', false, null
    );

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


-- 게시글 더미 데이터
INSERT INTO posts
    (title, content, type, views, created_at, updated_at, user_id, thumbnail)
VALUES
    ('첫 번째 게시글', '첫 번째 게시글 내용입니다.', 'FREE', 10, NOW(), NOW(), 1,
     'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png'),
    ('두 번째 게시글', '두 번째 게시글 내용입니다.', 'MARKET', 20, NOW(), NOW(), 2,
     'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png'),
    ('세 번째 게시글', '세 번째 게시글 내용입니다.', 'INFO', 5, NOW(), NOW(), 3,
     'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png'),
    ('네 번째 게시글', '네 번째 게시글 내용입니다.', 'FREE', 15, NOW(), NOW(), 4, NULL),
    ('다섯 번째 게시글', '다섯 번째 게시글 내용입니다.', 'MARKET', 25, NOW(), NOW(), 5,
     'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png'),
    ('여섯 번째 게시글', '여섯 번째 게시글 내용입니다.', 'INFO', 30, NOW(), NOW(), 1, NULL),
    ('일곱 번째 게시글', '일곱 번째 게시글 내용입니다.', 'FREE', 18, NOW(), NOW(), 2,
     'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png'),
    ('여덟 번째 게시글', '여덟 번째 게시글 내용입니다.', 'MARKET', 8, NOW(), NOW(), 3,
     'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png'),
    ('아홉 번째 게시글', '아홉 번째 게시글 내용입니다.', 'INFO', 12, NOW(), NOW(), 4,
     'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png'),
    ('열 번째 게시글', '열 번째 게시글 내용입니다.', 'FREE', 35, NOW(), NOW(), 5, NULL);

-- 게시글 사진 더미 데이터
INSERT INTO photos
    (target_id, target_type, url, created_at, updated_at)
VALUES
    (1, 'POST', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png', NOW(), NOW()),
    (2, 'POST', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png', NOW(), NOW()),
    (3, 'POST', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png', NOW(), NOW()),
    (3, 'POST', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png', NOW(), NOW()),
    (5, 'POST', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png', NOW(), NOW()),
    (7, 'POST', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png', NOW(), NOW()),
    (8, 'POST', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png', NOW(), NOW()),
    (9, 'POST', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png', NOW(), NOW()),
    (9, 'POST', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png', NOW(), NOW());

-- 게시글 좋아요 더미 데이터
INSERT INTO reactions
(user_id, target_id, type, created_at, updated_at)
VALUES
    (1, 1, 'POST', NOW(), NOW()),
    (2, 1, 'POST', NOW(), NOW()),
    (3, 2, 'POST', NOW(), NOW()),
    (4, 3, 'POST', NOW(), NOW()),
    (5, 3, 'POST', NOW(), NOW()),
    (1, 4, 'POST', NOW(), NOW()),
    (2, 5, 'POST', NOW(), NOW()),
    (3, 6, 'POST', NOW(), NOW()),
    (4, 6, 'POST', NOW(), NOW()),
    (5, 7, 'POST', NOW(), NOW());

-- 댓글 더미 데이터
INSERT INTO comments
(content, post_id, user_id, parent_id, parent_nickname, created_at, updated_at, deleted)
VALUES
    ('첫 번째 게시글의 첫 번째 댓글입니다.', 1, 1, NULL, NULL, NOW(), NOW(), false),
    ('첫 번째 게시글의 두 번째 댓글입니다.', 1, 2, NULL, NULL, NOW(), NOW(), false),
    ('첫 번째 게시글의 첫 번째 댓글에 대한 대댓글입니다.', 1, 3, 1, 'user1nick', NOW(), NOW(), false),
    ('두 번째 게시글의 첫 번째 댓글입니다.', 2, 4, NULL, NULL, NOW(), NOW(), false),
    ('세 번째 게시글의 첫 번째 댓글입니다.', 3, 2, NULL, NULL, NOW(), NOW(), false),
    ('네 번째 게시글의 첫 번째 댓글입니다.', 4, 5, NULL, NULL, NOW(), NOW(), false),
    ('네 번째 게시글의 첫 번째 댓글에 대한 대댓글입니다.', 4, 3, 6, 'user5nick', NOW(), NOW(), false),
    ('다섯 번째 게시글의 첫 번째 댓글입니다.', 5, 4, NULL, NULL, NOW(), NOW(), false),
    ('여섯 번째 게시글의 첫 번째 댓글입니다.', 6, 1, NULL, NULL, NOW(), NOW(), false),
    ('일곱 번째 게시글의 첫 번째 댓글입니다.', 7, 2, NULL, NULL, NOW(), NOW(), false);

-- 댓글 좋아요 더미 데이터
INSERT INTO reactions
    (user_id, target_id, type, created_at, updated_at)
VALUES
    (1, 1, 'COMMENT', NOW(), NOW()),
    (2, 1, 'COMMENT', NOW(), NOW()),
    (3, 2, 'COMMENT', NOW(), NOW()),
    (4, 3, 'COMMENT', NOW(), NOW()),
    (5, 3, 'COMMENT', NOW(), NOW()),
    (1, 4, 'COMMENT', NOW(), NOW()),
    (2, 5, 'COMMENT', NOW(), NOW()),
    (3, 6, 'COMMENT', NOW(), NOW()),
    (4, 6, 'COMMENT', NOW(), NOW()),
    (5, 7, 'COMMENT', NOW(), NOW());

-- notification test
INSERT INTO notifications (id, type, creator_type_id, attendee_type_id, is_read, creator_id, attendee_id, created_at, updated_at)
VALUES (1, 'POST', 1, 2, false, 1, 3, '2024-11-08 10:00:00', '2024-11-08 10:00:00');

INSERT INTO notifications (id, type, creator_type_id, attendee_type_id, is_read, creator_id, attendee_id, created_at, updated_at)
VALUES (6, 'POST', 1, 3, false, 1, 4, '2024-11-08 11:00:00', '2024-11-08 11:00:00');

INSERT INTO notifications (id, type, creator_type_id, attendee_type_id, is_read, creator_id, attendee_id, created_at, updated_at)
VALUES (2, 'COMMENT', 1, 2, false, 1, 2, '2024-11-08 10:00:00', '2024-11-08 10:00:00');

INSERT INTO notifications (id, type, creator_type_id, attendee_type_id, is_read, creator_id, attendee_id, created_at, updated_at)
VALUES (3, 'COMMENT', 1, 3, false, 1, 3, '2024-11-08 10:00:00', '2024-11-08 10:00:00');

INSERT INTO notifications (id, type, creator_type_id, attendee_type_id, is_read, creator_id, attendee_id, created_at, updated_at)
VALUES (5, 'COMMENT', 2, 3, false, 1, 4, '2024-11-08 10:00:00', '2024-11-08 10:00:00');

INSERT INTO notifications (id, type, creator_type_id, attendee_type_id, is_read, creator_id, attendee_id, created_at, updated_at)
VALUES (4, 'GATHERING', 1, 3, false, 1, 1, '2024-11-08 10:00:00', '2024-11-08 10:00:00');

INSERT INTO notifications (id, type, creator_type_id, attendee_type_id, is_read, creator_id, attendee_id, created_at, updated_at)
VALUES (7, 'GATHERING', 1, 2, false, 1, 1, '2024-11-08 10:00:00', '2024-11-08 10:00:00');