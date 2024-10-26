package com.develop_ping.union.config.auth;

import com.develop_ping.union.auth.domain.TokenManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenManager tokenManager;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        try {
            // 요청 헤더에서 Authorization 값 조회
            String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
            log.debug("인증 헤더를 찾음: {}", authorizationHeader);

            // Bearer 접두사 제거 후 토큰 추출
            String token = getAccessToken(authorizationHeader);
            log.debug("헤더에서 JWT 추출: {}", token);

            // 토큰이 유효한지 확인하고, 인증 정보가 없는 경우 인증 설정
            if (token != null && tokenManager.validToken(token) &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {

                Authentication authentication = tokenManager.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("유효한 JWT 토큰입니다. 유저 : {}", authentication.getName());
            }
        } catch (Exception ex) {
            // 예외 발생 시 로그 남기기
            log.error("JWT 필터에서 예외 발생", ex);
        }

        filterChain.doFilter(request, response);
    }

    // Authorization 헤더에서 Bearer 토큰을 추출하는 메서드
    private String getAccessToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            log.debug("유효한 형식의 헤더입니다: {}", authorizationHeader);
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        log.warn("헤더에 액세스 토큰이 없거나, Bearer 접두어가 빠져있습니다.");
        return null;
    }
}
