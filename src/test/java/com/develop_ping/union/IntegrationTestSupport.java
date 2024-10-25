package com.develop_ping.union;

import com.amazonaws.services.s3.AmazonS3Client;
import com.develop_ping.union.auth.infra.TokenManagerImpl;
import com.develop_ping.union.config.S3Config;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public abstract class IntegrationTestSupport {

    @MockBean
    private TokenManagerImpl tokenManager;

    @MockBean
    private S3Config s3Config;

    @MockBean
    private AmazonS3Client amazonS3Client;
}
