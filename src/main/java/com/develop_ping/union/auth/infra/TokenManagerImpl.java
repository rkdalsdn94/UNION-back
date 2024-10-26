package com.develop_ping.union.auth.infra;

import com.develop_ping.union.auth.domain.TokenManager;
import com.develop_ping.union.auth.exception.InvalidTokenException;
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Slf4j
@Component
public class TokenManagerImpl implements TokenManager {
    private final UserManager userManager;
    private final String issuer = "union";

    private final Key key;

    public TokenManagerImpl(UserManager userManager, @Value("${SECRET_KEY}") String secretKey) {
        this.userManager = userManager;
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // JWT 토큰 생성 메서드
    @Override
    public String generateToken(User user, Duration expiredAt) {
        log.info("JWT 토큰 생성 시도. 사용자 ID: {}", user.getId());

        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    // JWT 토큰 생성 로직
    private String makeToken(Date expiry, User user) {
        log.info("JWT 토큰 생성. 만료일: {}, 사용자 ID: {}", expiry, user.getId());
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")  // 헤더 typ : JWT
                .setIssuer(issuer)  // 발급자 설정
                .setIssuedAt(now)  // 발급 시간 설정
                .setExpiration(expiry)  // 만료 시간 설정
                .setSubject(user.getToken())  // 이메일을 subject로 설정
                .signWith(key, SignatureAlgorithm.HS256)  // 서명 알고리즘 설정
                .compact();
    }

    // JWT 토큰 유효성 검증 메서드
    @Override
    public boolean validToken(String token) {
        log.info("JWT 토큰 검증 시작. 토큰: {}", token);

        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)  // 서명 키 설정
                    .build()              // 빌더를 완성
                    .parseClaimsJws(token);  // 토큰을 검증 및 파싱

            log.info("JWT 토큰 검증 완료. 유효한 토큰입니다.");
            return true;  // 유효한 토큰
        } catch (Exception e) {
            log.error("JWT 토큰 검증 실패. 유효하지 않은 토큰: {}", token);
            throw new InvalidTokenException();
        }
    }

    // JWT 토큰에서 인증 정보를 가져오는 메서드
    @Override
    public Authentication getAuthentication(String token) {
        log.info("JWT 토큰에서 인증 정보 추출 시도. 토큰: {}", token);

        Claims claims = getClaims(token);
        String userToken = claims.getSubject();

        // 데이터베이스에서 실제 User 객체를 조회
        User user = userManager.findByToken(userToken);

        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        log.info("JWT 토큰으로부터 인증 정보 추출 성공. 사용자 ID: {}", user.getId());

        // 실제 User 엔티티 객체를 Principal로 사용
        return new UsernamePasswordAuthenticationToken(user, token, authorities);
    }

    // 클레임을 추출하는 메서드
    private Claims getClaims(String token) {
        log.info("JWT 토큰에서 클레임 추출 시도. 토큰: {}", token);

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)  // 서명 키 설정
                    .build()              // 파서 빌더 완성
                    .parseClaimsJws(token)  // 토큰을 파싱하여 클레임 추출
                    .getBody();            // 클레임의 본문(body) 반환
        } catch (Exception e) {
            log.error("JWT 토큰에서 클레임 추출 실패. 잘못된 토큰: {}", token, e);
            throw new InvalidTokenException();
        }
    }
}
