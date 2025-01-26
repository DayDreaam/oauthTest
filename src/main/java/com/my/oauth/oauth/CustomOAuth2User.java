package com.my.oauth.oauth;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.my.oauth.dto.OAuth2Response;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User{
	private final OAuth2Response oauth2Response;

	@Override
	public Map<String, Object> getAttributes() {
		return Map.of();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public String getName() {
		return oauth2Response.getName();
	}

	public String getUserId() {
		return oauth2Response.getProvider() + "_" + oauth2Response.getProviderId();
	}

	public String getImgUrl(){
		return oauth2Response.getImgUrl();
	}
}
