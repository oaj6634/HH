package com.example.hh.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()//csrf 비활성화 이 기능을 활용하려면 클라이언트에서 csrf토근값을 보내줘야 하기 때문
                .sessionManagement().disable()// 세선 관리 안함
                .cors().disable()
                .formLogin().disable()
                //요청에 의한 보안 검사 시작
                .authorizeRequests()

                //post
                .antMatchers(HttpMethod.GET, "/posts").permitAll()
                .antMatchers(HttpMethod.POST, "/posts").permitAll()
                //user
                .antMatchers(HttpMethod.POST, "/user/join").permitAll()
                .antMatchers(HttpMethod.POST, "/user/login" ).permitAll()
                .antMatchers(HttpMethod.GET, "/user/*/posts","/user/*/profile").permitAll()
                .antMatchers(HttpMethod.PUT, "/user/*/profile").permitAll()

                //어떤 요청에도 보안검사를 한다.
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and().apply(new FilterConfigure(jwtTokenProvider));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}