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
    (NOW(), NOW(), 'user5nick', 'User 5 description', 'https://example.com/user5.png', 'University 5', 'test5@test.com', 'token5', 'google', false),
    (NOW(), NOW(), '오이비누', '오이비누 왜 안 씀?', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/306d25a3-0ebc-4701-bc33-bfb6ce5c3ad2.jpg', '건국대학교', 'test6@test.com', 'token6', 'google', false),
    (NOW(), NOW(), '감튀염미굳', '롯데리아 감튀는 재앙임', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/60b549e8-9558-4260-ae9a-da70f34f0f9b.webp', '경북대학교', 'test7@test.com', 'token7', 'google', false),
    (NOW(), NOW(), '슬리퍼', null, 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/5038e72a-e560-4654-a4d1-c9fdf3e7bdbf.jpg', '부산대학교', 'test8@test.com', 'token8', 'google', false),
    (NOW(), NOW(), '닉넴뭐하지', null, 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/92622b05-455e-4519-a62c-321321122a92.png', '세종대학교', 'test9@test.com', 'token9', 'google', false),
    (NOW(), NOW(), 'soil baked', 'jul7me soil baked 향수를 써보세요', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/33497acd-5b68-438a-b53c-db0a2470cd02.jpeg', '서울과학기술대학교', 'test10@test.com', 'token10', 'google', false),
    (NOW(), NOW(), 'whtmddus', null, 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/ca5e05eb-18af-484c-a7b3-c02abf0ecf44.jpeg', '계명대학교', 'test11@test.com', 'token11', 'google', false),
    (NOW(), NOW(), '동남구 핵주먹', 'ㅋㅋ', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/92622b05-455e-4519-a62c-321321122a92.png', '백석대학교', 'test12@test.com', 'token12', 'google', false),
    (NOW(), NOW(), '녜힁', '여로를갈것', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/684712c7-5ffe-408f-8198-07adcbb3061a.jpg', '송원대학교', 'test13@test.com', 'token13', 'google', false),
    (NOW(), NOW(), '훈이', null, 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/ae423d9d-d864-426f-8f14-85dbd3835cb8.jpg', '백제예술대학교', 'test14@test.com', 'token14', 'google', false),
    (NOW(), NOW(), '고길동', null, 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/7d23a760-209b-4a6e-a215-a684c6ea5afb.jpg', '인하공업전문대학', 'test15@test.com', 'token15', 'google', false),
    (NOW(), NOW(), '커피중독자', '오늘도 커피 세 잔째..', 'https://example.com/user16.png', '동아대학교', 'test16@test.com', 'token16', 'google', false),
    (NOW(), NOW(), '페이커여친', 'ㅋㅋ대상혁은 전설이지', 'https://example.com/user17.png', '서울예술대학교', 'test17@test.com', 'token17', 'google', false),
    (NOW(), NOW(), '치킨', '치킹ㅋㅊ치킼ㅋ맄맄ㅋㅋ리친킼ㅋ킼치닠ㄴ키치킨', 'https://example.com/user18.png', '숭실대학교', 'test18@test.com', 'token18', 'google', false),
    (NOW(), NOW(), '닉넴추천좀', 'ㅊㅊ', 'https://example.com/user19.png', '가천대학교', 'test19@test.com', 'token19', 'google', false),
    (NOW(), NOW(), '망고요정', null, 'https://example.com/user20.png', '배재대학교', 'test20@test.com', 'token20', 'google', false),
    (NOW(), NOW(), '곰팡이', '야구 경기 매일 챙겨봄!', 'https://example.com/user21.png', '우송대학교', 'test21@test.com', 'token21', 'google', false),
    (NOW(), NOW(), '그레이브즈', '플레이리스트 공유해요', 'https://example.com/user22.png', '국민대학교', 'test22@test.com', 'token22', 'google', false),
    (NOW(), NOW(), '쫑이', '오늘도 멋지게 입고 나가요', 'https://example.com/user23.png', '청강문화산업대학', 'test23@test.com', 'token23', 'google', false),
    (NOW(), NOW(), '쿠키', '초밥은 질리지 않아요', 'https://example.com/user24.png', '신구대학교', 'test24@test.com', 'token24', 'google', false),
    (NOW(), NOW(), '홈프로텍터', '요즘 읽는 책 추천 좀 해주세요', 'https://example.com/user25.png', '서경대학교', 'test25@test.com', 'token25', 'google', false),
    (NOW(), NOW(), '비긴이계인', '오늘도 코딩을 합니다', 'https://example.com/user26.png', '한양여자대학교', 'test26@test.com', 'token26', 'google', false),
    (NOW(), NOW(), '생갈치1호의 행방불명', '다음 여행지는 어디로?', 'https://example.com/user27.png', '강릉원주대학교', 'test27@test.com', 'token27', 'google', false),
    (NOW(), NOW(), '카드값줘체리', '밀크티가 제일 좋아', 'https://example.com/user28.png', '부산외국어대학교', 'test28@test.com', 'token28', 'google', false),
    (NOW(), NOW(), '금쪽이', '왜요 제가 금쪽이 같나요?', 'https://example.com/user29.png', '영진전문대학', 'test29@test.com', 'token29', 'google', false),
    (NOW(), NOW(), '영화광', '이번 주말 영화 추천해주세요!', 'https://example.com/user30.png', '여주대학교', 'test30@test.com', 'token30', 'google', false);


-- 게시글 더미 데이터
INSERT INTO posts
    (title, content, type, views, created_at, updated_at, user_id, thumbnail)
VALUES
    ('저메추', '뭐먹지 추천받음', 'FREE', 10, NOW(), NOW(), 6, NULL),
    ('부산대 지갑 분실물', '지갑 주인을 찾습니다! DM 주시면 지갑 위치 알려드릴게요', 'FREE', 43, NOW(), NOW(), 8,
     'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/43e172a3-ad81-4fb3-bd81-e94cd269e931.jpg'),
    ('엽떡', '다들 엽떡 9900원 쿠폰 받는 거에 진심이구나........', 'FREE', 54, NOW(), NOW(), 1, NULL),
    ('군대 1학기 하고 가면 안 좋은 점 있나요?', '3학년 1학기하고 종강 후 군대갔다가 3학년 2학기에 복학하려고 합니다.', 'FREE', 22, NOW(), NOW(), 2, NULL),
    ('롤 해봤는데', '재밌어서 큰일났다...', 'FREE', 48, NOW(), NOW(), 3, NULL),
    ('넷플 추천', '어둠 속의 미사 개꿀잼 추천합니다 잘 만든 드라마임', 'FREE', 34, NOW(), NOW(), 4,
     'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/0829d93b-62ac-4bce-858f-8a3a1fe3f6f0.jpg'),
    ('친구가 자꾸 돈을 빌려 가는데... 말하는 게 맞을까요?', '몇 번 빌려주긴 했는데 갈수록 불편해져요. 다들 이런 경우 어떻게 대처하시나요?', 'FREE', 185, NOW(), NOW(), 5, NULL),
    ('ㅎㅎ고양이 보고가', '이름은 댕댕이야', 'FREE', 211, NOW(), NOW(), 6,
     'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/237543cc-db65-4fd2-8719-eeb4bb1ebcd7.jpg'),
    ('카페 알바생한테 번호 줬는데 연락이 없어ㅠㅠ', '용기 내서 번호 줬는데 연락이 없음... 괜히 준 건가 싶음... ㅠㅠ 진짜 존잘이었는데....', 'FREE', 93, NOW(), NOW(), 6, NULL),
    ('레전드 진상 썰 푼다', '진짜 알바하면서 별 진상을 다 봤지만 이거만큼 황당한 건 처음임ㅋㅋ 점심시간에 손님 엄청 몰리는데, 어떤 커플이 와서 앉더라고. 근데 주문할 때부터 느낌이 쎄했어. 손님이 메뉴판 한참 보더니 여기 모든 메뉴 다 설명 좀 해줘요~ 이러는 거임.. ㅠㅠ 바빠서 진짜 힘들었지만 친절하게 하나하나 설명해줬지.', 'FREE', 154, NOW(), NOW(), 7, NULL),
    ('노트북 추천 좀 해주세요!', '사무용, 편집, 간단한 게임 등등용.. 추천 부탁드려요~', 'FREE', 71, NOW(), NOW(), 8, NULL),
    ('불닭에 어울리는 치킨 뭐가있지', '갑자기 생각 안 나…… 뿌링클말고 추천좀!!!', 'FREE', 56, NOW(), NOW(), 9, NULL),
    ('다들 취준할때 급여가 1순위야?', '화사 규모나 이미지는 a가 더 좋은데 b가 급여 더 쎄면 b로 가?', 'FREE', 99, NOW(), NOW(), 10, NULL),
    ('아 역대급 수업 듣기싫다', '졸려 집 가고 싶어', 'FREE', 12, NOW(), NOW(), 11,
     'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/15b303af-7de0-4290-a394-cef2b7294afa.jpeg'),
    ('컴포즈vs메가 붙어있으면 어디가?', '난 컴포즈..\\n컴포즈가 더 맛있는거 같음', 'FREE', 57, NOW(), NOW(), 12, NULL),
    ('상한 거 먹으면 어지뤄워?', '어제부터 갑자기 어지러운데\\n원인 의심되는게 좀 오래된 반찬이랑 샌드위치밖에 없음\\n냉털하려고 걍 먹었거든...', 'FREE', 26, NOW(), NOW(), 13, NULL),
    ('허니콤보', '먹고 싶다..... 제발.....!......!!', 'FREE', 48, NOW(), NOW(), 14, NULL),
    ('오늘', '날씨 어때??? 후집 입으면 추울까.....??', 'FREE', 62, NOW(), NOW(), 15, NULL),
    ('필기', '어떤거 사용해??\\n한컴오피스 노타빌리티 등', 'FREE', 42, NOW(), NOW(), 16, NULL),
    ('애플 이번에 m4 나온거 봤냐??', '개쩔던데.. 가격도 완전 싸게 나와서 이번에 바꿀까 생각 중이야', 'ENGINEERING', 57, NOW(), NOW(), 17,
     'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/d3e6c318-68ba-440c-92fc-530e693df4aa.jpg'),
    ('코딩할때 쓰는 인공지능', '챗지피티 유료로 결재해서 쓰는게 가장 좋은가요?? 이미지 인식기능 어떤가요?', 'ENGINEERING', 74, NOW(), NOW(), 18, NULL),
    ('19, 20학번 이공계 여자들 취업 어케 됐어?', '19학번 남자들은 아직 졸업 시즌 아니고\\n19, 20학번 여자들 취업 잘 했어??', 'ENGINEERING', 91, NOW(), NOW(), 19, NULL),
    ('건축 실습할건데 스파게티 면으로 다리 지을사람?', '마시멜로도 쓸거임 어때? 같이 할 생각 있어?', 'ENGINEERING', 78, NOW(), NOW(), 20, NULL),
    ('반도체 전공하는 애들아', '너네 전공 따라갈만해?? 난 아무래도 전공 선택을 잘못한것같아..', 'ENGINEERING', 45, NOW(), NOW(), 21, NULL),
    ('학부연구생 어때?', '학부연구생 하려고 고민 중인데 실제로 어떤 일 하는지 궁금해! 일하면서 느낀 점이나 연구실 분위기 같은 것도 알려주면 좋겠어~ 연구실 생활 꿀팁도 환영!', 'ENGINEERING', 94, NOW(), NOW(), 22, NULL),
    ('원래 공부는 혼자하는거다', '공부는 혼자해라..', 'ENGINEERING', 73, NOW(), NOW(), 23, NULL),
    ('C#', '한번도 안해봤는데 추천할만한 책이나 강의 있을까?', 'ENGINEERING', 92, NOW(), NOW(), 24, NULL),
    ('학기 중 공모전 참여하는거 현실적으로 가능할까?', '학기 중에 공모전까지 하면 스케줄 관리가 진짜 힘들까…? 전공 과목도 빡세서 고민 중인데 다들 학기 중 공모전 참여 경험 어땠어? 준비 팁이나 시간 관리 방법 있으면 알려줘!', 'ENGINEERING', 88, NOW(), NOW(), 25, NULL),
    ('안드로이드스큐디오 쓰면서', '내 노트북 이제 바꿔야할 때가 왔구나 새삼 생각했다..\\n\\n걍 킬때부터 느리고 에뮬레이터 켜지지도 않아서 수업 따라가기도 힘들었음..', 'ENGINEERING', 36, NOW(), NOW(), 9, NULL),
    ('다들 엽떡앱 들어가서 쿠폰 다운받아~','ㅈㄱㄴ','FREE', 220, NOW(), NOW(), 11,
     'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/742453ee-4bbd-423f-8d04-87007c8a526a.jpeg'),
    ('파트라슈...','나 점점 잠이 와... 아직 0.5회독밖에 안했는데...','FREE', 194, NOW(), NOW(), 11,
     'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/b6dd16ef-e2cb-427e-872a-8fbf74184a6c.jpeg');

-- 게시글 사진 더미 데이터
INSERT INTO photos
    (target_id, target_type, url, created_at, updated_at)
VALUES
    (2, 'POST', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/43e172a3-ad81-4fb3-bd81-e94cd269e931.jpg', NOW(), NOW()),
    (6, 'POST', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/0829d93b-62ac-4bce-858f-8a3a1fe3f6f0.jpg', NOW(), NOW()),
    (8, 'POST', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/237543cc-db65-4fd2-8719-eeb4bb1ebcd7.jpg', NOW(), NOW()),
    (8, 'POST', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/7b474117-9377-40a5-b554-750609f90a14.jpg', NOW(), NOW()),
    (8, 'POST', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/78e5fb1b-8702-4683-9b5f-86d7bfee69d4.jpg', NOW(), NOW()),
    (8, 'POST', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/a16e1a61-af6f-4e7a-8aee-562edebb2541.jpg', NOW(), NOW()),
    (14, 'POST', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/15b303af-7de0-4290-a394-cef2b7294afa.jpeg', NOW(), NOW()),
    (20, 'POST', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/d3e6c318-68ba-440c-92fc-530e693df4aa.jpg', NOW(), NOW()),
    (30, 'POST', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/742453ee-4bbd-423f-8d04-87007c8a526a.jpeg', NOW(), NOW()),
    (31, 'POST', 'https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/token1/b6dd16ef-e2cb-427e-872a-8fbf74184a6c.jpeg''', NOW(), NOW());

-- 게시글 좋아요 더미 데이터
INSERT INTO reactions
(target_id, user_id, type, created_at, updated_at)
VALUES
    (2, 1, 'POST', NOW(), NOW()),
    (2, 2, 'POST', NOW(), NOW()),
    (2, 3, 'POST', NOW(), NOW()),
    (2, 4, 'POST', NOW(), NOW()),
    (5, 1, 'POST', NOW(), NOW()),
    (5, 2, 'POST', NOW(), NOW()),
    (5, 3, 'POST', NOW(), NOW()),
    (7, 1, 'POST', NOW(), NOW()),
    (6, 1, 'POST', NOW(), NOW()),
    (6, 2, 'POST', NOW(), NOW()),
    (8, 1, 'POST', NOW(), NOW()),
    (8, 2, 'POST', NOW(), NOW()),
    (8, 3, 'POST', NOW(), NOW()),
    (8, 4, 'POST', NOW(), NOW()),
    (8, 5, 'POST', NOW(), NOW()),
    (8, 6, 'POST', NOW(), NOW()),
    (8, 7, 'POST', NOW(), NOW()),
    (8, 8, 'POST', NOW(), NOW()),
    (8, 9, 'POST', NOW(), NOW()),
    (8, 10, 'POST', NOW(), NOW()),
    (8, 11, 'POST', NOW(), NOW()),
    (8, 12, 'POST', NOW(), NOW()),
    (8, 13, 'POST', NOW(), NOW()),
    (8, 14, 'POST', NOW(), NOW()),
    (8, 15, 'POST', NOW(), NOW()),
    (8, 16, 'POST', NOW(), NOW()),
    (8, 17, 'POST', NOW(), NOW()),
    (8, 18, 'POST', NOW(), NOW()),
    (8, 19, 'POST', NOW(), NOW()),
    (8, 20, 'POST', NOW(), NOW()),
    (8, 21, 'POST', NOW(), NOW()),
    (10, 22, 'POST', NOW(), NOW()),
    (10, 23, 'POST', NOW(), NOW()),
    (10, 24, 'POST', NOW(), NOW()),
    (10, 25, 'POST', NOW(), NOW()),
    (14, 1, 'POST', NOW(), NOW()),
    (14, 2, 'POST', NOW(), NOW()),
    (14, 3, 'POST', NOW(), NOW()),
    (14, 4, 'POST', NOW(), NOW()),
    (20, 1, 'POST', NOW(), NOW()),
    (20, 2, 'POST', NOW(), NOW()),
    (20, 3, 'POST', NOW(), NOW()),
    (20, 4, 'POST', NOW(), NOW()),
    (23, 1, 'POST', NOW(), NOW()),
    (24, 1, 'POST', NOW(), NOW()),
    (26, 1, 'POST', NOW(), NOW()),
    (26, 2, 'POST', NOW(), NOW()),
    (26, 3, 'POST', NOW(), NOW()),
    (26, 4, 'POST', NOW(), NOW()),
    (26, 5, 'POST', NOW(), NOW()),
    (26, 6, 'POST', NOW(), NOW()),
    (26, 7, 'POST', NOW(), NOW()),
    (30, 1, 'POST', NOW(), NOW()),
    (30, 2, 'POST', NOW(), NOW()),
    (30, 3, 'POST', NOW(), NOW()),
    (30, 4, 'POST', NOW(), NOW()),
    (30, 5, 'POST', NOW(), NOW()),
    (30, 6, 'POST', NOW(), NOW()),
    (30, 7, 'POST', NOW(), NOW()),
    (30, 8, 'POST', NOW(), NOW()),
    (30, 9, 'POST', NOW(), NOW()),
    (30, 10, 'POST', NOW(), NOW()),
    (30, 11, 'POST', NOW(), NOW()),
    (30, 12, 'POST', NOW(), NOW()),
    (30, 13, 'POST', NOW(), NOW()),
    (31, 1, 'POST', NOW(), NOW()),
    (31, 2, 'POST', NOW(), NOW()),
    (31, 3, 'POST', NOW(), NOW()),
    (31, 4, 'POST', NOW(), NOW()),
    (31, 5, 'POST', NOW(), NOW()),
    (31, 6, 'POST', NOW(), NOW()),
    (31, 7, 'POST', NOW(), NOW()),
    (31, 8, 'POST', NOW(), NOW()),
    (31, 9, 'POST', NOW(), NOW()),
    (31, 10, 'POST', NOW(), NOW()),
    (31, 11, 'POST', NOW(), NOW()),
    (31, 12, 'POST', NOW(), NOW()),
    (31, 13, 'POST', NOW(), NOW()),
    (31, 14, 'POST', NOW(), NOW()),
    (31, 15, 'POST', NOW(), NOW()),
    (31, 16, 'POST', NOW(), NOW()),
    (31, 17, 'POST', NOW(), NOW()),
    (31, 18, 'POST', NOW(), NOW());


-- 댓글 더미 데이터
INSERT INTO comments
(content, post_id, user_id, parent_id, parent_nickname, created_at, updated_at, deleted)
VALUES
    ('닭발', 1, 6, NULL, NULL, NOW(), NOW(), false),
    ('치킨', 1, 18, NULL, NULL, NOW(), NOW(), false),
    ('치킨 ㅇㅈ', 1, 22, 2, '치킨', NOW(), NOW(), false),
    ('김치찌개', 1, 8, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 2, 9, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 3, 3, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 3, 11, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 3, 22, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 4, 4, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 4, 17, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 4, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 4, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 4, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 4, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 5, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 5, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 6, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 6, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 6, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 6, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 7, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 7, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 7, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 7, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 7, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 7, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 7, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 7, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 7, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 7, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 7, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 7, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 7, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 7, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 8, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 8, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 8, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 8, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 8, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 8, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 8, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 8, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 8, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 8, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 8, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 8, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 9, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 9, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 9, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 10, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 10, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 10, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 10, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 10, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 11, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 12, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 12, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 12, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 12, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 13, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 13, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 14, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 15, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 15, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 15, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 15, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 15, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 16, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 16, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 18, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 19, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 19, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 19, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 19, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 20, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 20, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 20, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 20, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 20, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 20, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 20, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 21, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 21, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 21, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 22, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 22, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 24, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 24, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 24, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 24, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 24, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 24, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 25, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 25, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 25, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 26, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 26, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 26, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 26, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 26, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 26, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 26, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 26, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 27, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 27, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 28, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 28, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 28, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 29, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 30, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 30, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 30, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 30, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 30, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 31, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 31, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 31, 1, NULL, NULL, NOW(), NOW(), false),
    ('댓글', 31, 1, NULL, NULL, NOW(), NOW(), false);

-- 댓글 좋아요 더미 데이터
INSERT INTO reactions
    (user_id, target_id, type, created_at, updated_at)
VALUES
    (1, 2, 'COMMENT', NOW(), NOW()),
    (2, 2, 'COMMENT', NOW(), NOW()),
    (3, 2, 'COMMENT', NOW(), NOW()),
    (4, 2, 'COMMENT', NOW(), NOW()),
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

-- 모임에 대한 리액션 더미 데이터 (좋아요)
INSERT INTO reactions
(user_id, target_id, type, created_at, updated_at)
VALUES
    (1, 1, 'GATHERING', NOW(), NOW()),
    (2, 1, 'GATHERING', NOW(), NOW()),
    (3, 2, 'GATHERING', NOW(), NOW()),
    (4, 3, 'GATHERING', NOW(), NOW()),
    (5, 3, 'GATHERING', NOW(), NOW()),
    (1, 4, 'GATHERING', NOW(), NOW()),
    (2, 4, 'GATHERING', NOW(), NOW()),
    (3, 5, 'GATHERING', NOW(), NOW()),
    (4, 5, 'GATHERING', NOW(), NOW()),
    (5, 6, 'GATHERING', NOW(), NOW()),
    (1, 7, 'GATHERING', NOW(), NOW()),
    (2, 7, 'GATHERING', NOW(), NOW());
