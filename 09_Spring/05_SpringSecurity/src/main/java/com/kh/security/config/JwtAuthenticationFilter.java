package com.kh.security.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kh.security.model.vo.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenProvider tokenProvider;

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		// 클라이언트에서 보낸 토큰을 받아서 사용자 확인 후 인증 처리
		String token = parseBearerToken(request);
		System.out.println(token);
		
		if (token != null && !token.equalsIgnoreCase("null")) {
			User user = tokenProvider.validate(token);
			System.out.println(user);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private String parseBearerToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
			return bearerToken.substring(7);
		}
		
		return null;
	}
	
}
