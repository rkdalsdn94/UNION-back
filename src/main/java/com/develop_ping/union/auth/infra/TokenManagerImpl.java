package com.develop_ping.union.auth.infra;

import com.develop_ping.union.auth.domain.TokenManager;
import com.develop_ping.union.auth.exception.InvalidTokenException;
import com.develop_ping.union.user.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
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

    private final String issuer = "union";
    private final Key key;

    public TokenManagerImpl(@Value("${SECRET_KEY}") String secretKey) {
        this.key= Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // JWT 토큰 생성 메서드
    @Override
    public String generateToken(User user, Duration expiredAt) {
        log.info("JWT 토큰 생성: {}", user.getId());

        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    // JWT 토큰 생성 로직
    private String makeToken(Date expiry, User user) {
        log.debug("JWT 토큰 만료일: {} 사용자: {}", expiry, user.getId());
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")  // 헤더 typ : JWT
                .setIssuer(issuer)  // 발급자 설정
                .setIssuedAt(now)  // 발급 시간 설정
                .setExpiration(expiry)  // 만료 시간 설정
                .setSubject(user.getNickname())  // 이메일을 subject로 설정
                .claim("id", user.getId())  // 클레임에 유저 ID 추가
                .claim("token", user.getToken())  // 추가로 token도 클레임에 추가
                .signWith(key, SignatureAlgorithm.HS256)  // 서명 알고리즘 설정
                .compact();
    }

    // JWT 토큰 유효성 검증 메서드
    @Override
    public boolean validToken(String token) {
        log.info("JWT 검증 시작");

        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)  // 서명 키 설정
                    .build()              // 빌더를 완성
                    .parseClaimsJws(token);  // 토큰을 검증 및 파싱

            log.info("JWT 토큰 유효");
            return true;  // 유효한 토큰
        } catch (Exception e) {
            log.error("유효하지 않은 JWT 토큰: {}", token);
            throw new InvalidTokenException();
        }
    }

    // JWT 토큰에서 인증 정보를 가져오는 메서드
    @Override
    public Authentication getAuthentication(String token) {
        log.info("JWT 토큰에서 인증 정보 추출");

        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(
                new org.springframework.security.core.userdetails.User(claims.getSubject(), "", authorities),
                token,
                authorities
        );
    }

    // 클레임을 추출하는 메서드
    private Claims getClaims(String token) {
        log.info("JWT 토큰에서 클레임 추출");

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)  // 서명 키 설정
                    .build()              // 파서 빌더 완성
                    .parseClaimsJws(token)  // 토큰을 파싱하여 클레임 추출
                    .getBody();            // 클레임의 본문(body) 반환
        } catch (Exception e) {
            log.error("클레임 시도 했으나 올바르지 않은 토큰: {}", token, e);
            throw new InvalidTokenException();
        }
    }
}
