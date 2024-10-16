package com.develop_ping.union;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class UnionApplicationTests {

    @DisplayName("Union 컨텍스트가 로드되어야 한다.")
    @Test
    void contextLoads() {
        UnionApplication application = new UnionApplication();
        assertNotNull(application);
    }
}
