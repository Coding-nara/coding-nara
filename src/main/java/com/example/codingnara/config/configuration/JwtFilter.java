package com.example.codingnara.config.configuration;

import com.example.codingnara.service.UserService;
import com.example.codingnara.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String secretKey;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws SecurityException, ServletException, IOException {

        final String authorizaion = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info(authorizaion);


        //토큰없으면 ㅗ
        if(authorizaion == null || !authorizaion.startsWith("Bearer ")){
            log.error("authentication이 없습니다.");
            filterChain.doFilter(request, response);
            return;

        }
        //토큰 꺼내기
        String token = authorizaion.split(" ")[1];

        if(JwtUtil.isExpired(token, secretKey)){
            log.error("Token이 만료");
            filterChain.doFilter(request, response);
            return;
        }

        //username token에서 발출

        String username = JwtUtil.getUserName(token, secretKey);
        log.info("username: {}", username);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, List.of(new SimpleGrantedAuthority("USER")));

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
