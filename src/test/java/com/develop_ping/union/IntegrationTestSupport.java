package com.develop_ping.union;

import com.develop_ping.union.auth.infra.TokenManagerImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@TestPropertySource(properties = {
    "AWS_ACCESS_KEY=test_key",
    "AWS_SECRET_KEY=test_secret",
    "SECRET_KEY=test_secret_key"
})
@SpringBootTest
public abstract class IntegrationTestSupport {

    @MockBean
    private TokenManagerImpl tokenManager;

    @Value("${SECRET_KEY}")
    private String secretKey;

    @Value("${AWS_ACCESS_KEY}")
    private String awsAccessKey;

    @Value("${AWS_SECRET_KEY}")
    private String awsSecretKey;

    @DisplayName("스프링 부트 테스트, 환경변수가 로드되어야 한다.")
    @Test
    void testPropertyLoad() {
        assertThat(awsAccessKey).isNotNull();
        assertThat(secretKey).isNotNull();
        assertThat(awsSecretKey).isNotNull();
    }
}
