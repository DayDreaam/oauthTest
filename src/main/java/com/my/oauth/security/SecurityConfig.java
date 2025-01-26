package com.my.oauth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import com.my.oauth.service.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final CustomOAuth2UserService oAuth2UserService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(AbstractHttpConfigurer::disable);
		http.httpBasic(AbstractHttpConfigurer::disable);
		http.formLogin(AbstractHttpConfigurer::disable);

		http.oauth2Login(oauth2 -> oauth2
			.loginPage("/login")
			.userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
				.userService(oAuth2UserService))
			.successHandler(((request, response, authentication) -> response.sendRedirect("/myPage")))
		);

		http.logout(logout ->
			logout.logoutSuccessUrl("/")
			.invalidateHttpSession(true) // 세션 무효화
			.deleteCookies("JSESSIONID")
		);

		http.authorizeHttpRequests(auth -> auth
			.requestMatchers("/", "/oauth2/**", "/login").permitAll()
			.anyRequest().authenticated());

		return http.build();
	}
}
