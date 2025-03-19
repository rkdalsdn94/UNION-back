package com.develop_ping.union;

import com.amazonaws.services.s3.AmazonS3Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public abstract class IntegrationTestSupport {

    @MockBean
    private AmazonS3Client amazonS3Client;

    @DisplayName("스프링 부트 테스트, 환경변수가 로드되어야 한다.")
    @Test
    void testPropertyLoad() {

    }
}
