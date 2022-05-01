package com.example.hh.security;

import com.example.hh.error.exception.InvalidTokenException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final AuthDetailsService userLoadService;

    @Value( "${auth.jwt.secret}" )
    private String secretKey;

    public String generateAccessToken(String value) {
        return makingToken(value, "access", 7200L);
    }

    public String generateRefreshToken(String value) {
        return makingToken(value, "refresh", 172800L);
    }

    public boolean validateAccessToken(String token){
        return validateToken(token, "access");
    }

    public boolean validateRefreshToken(String token){
        return validateToken(token, "refresh");
    }

    public String getId(String token) {
        try {
            return Jwts.parser().setSigningKey(encodingSecretKey()).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            throw new InvalidTokenException(token);
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(checkToken(token)) {
            return token.substring(7);
        }
        return null;
    }
//                                                                          startWith() 지정된 접두사로 시작하는지 확인
    public Boolean checkToken(String token) {
        return token != null && token.startsWith("Bearer");
    }

    public Authentication getAuthentication(String token) {
        UserDetails details = userLoadService.loadUserByUsername(getId(token));
        return new UsernamePasswordAuthenticationToken(details, "", details.getAuthorities());
    }


    private boolean validateToken(String token, String typeKind) {
        try {// parser() jwt를 분석해줌 setSigningKey() JWS 서명을 확인하는데 사용되는 키를 설정함.
            String type = Jwts.parser().setSigningKey(encodingSecretKey()).parseClaimsJws(token).getBody().get("type", String.class);
            return type.equals(typeKind);
        } catch (Exception e) {
            throw new InvalidTokenException(token);
        }
    }

    private String makingToken(String value, String type, Long time){
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + (time * 1000L))) //만료시간
                .signWith(SignatureAlgorithm.HS512, encodingSecretKey()) // 사용할 알고리즘과 키 적용
                .setIssuedAt(new Date()) // 생성일
                .setSubject(value) // 토큰대상자
                .claim("type", type) // JWT Claim 파라미터 값 설정
                .compact(); //JWT 빌드
    }

    private String encodingSecretKey(){
        return Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

}

