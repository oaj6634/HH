package com.example.hh.security.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpMethod.OPTIONS;

public class CorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //모든 도메인에서 접근할 수 있음
        response.setHeader("Access-Control-Allow-Origin", "*");
        // Access-Control-Allow-Methods로 응답하고 value에 있는 요청메소드가 유용한 메소드 라고 가르쳐줌
        response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, POST, PUT, PATCH, DELETE");
        // Access-Control-Allow-Headers 의 값을 value에 있는 헤더로 전송해서 실제 전송 요청에 데허를 사용할 수 있음을 확인한다.
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Refresh-Token");
        //Access-Control-Max-Age는 다른 preflight request를 보내지 않고, preflight request` 에 대한 응답을 캐시할 수 있는 시간을 제공한다.
        response.setHeader("Access-Control-Max-Age", "3600");

        if (OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
