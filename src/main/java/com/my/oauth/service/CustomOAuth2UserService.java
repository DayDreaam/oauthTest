package com.my.oauth.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.my.oauth.dto.KakaoResponse;
import com.my.oauth.dto.OAuth2Response;
import com.my.oauth.entity.UserEntity;
import com.my.oauth.oauth.CustomOAuth2User;
import com.my.oauth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	private final UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		OAuth2User oAuth2User = super.loadUser(userRequest);
		System.out.println(oAuth2User.getAttributes());

		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		OAuth2Response oauth2Response = null;
		if (registrationId.equals("kakao")) {
			oauth2Response = new KakaoResponse(oAuth2User.getAttributes());
		} else {
			return null;
		}

		String userId = oauth2Response.getProvider() + "_" + oauth2Response.getProviderId();

		UserEntity existData = userRepository.findByUserId(userId);
		if (existData == null) {
			UserEntity user = UserEntity.builder()
				.userId(userId)
				.nickname(oauth2Response.getName())
				.imgUrl(oauth2Response.getImgUrl())
				.build();
			userRepository.save(user);
		}
		return new CustomOAuth2User(oauth2Response);
	}
}
