package com.my.oauth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.my.oauth.oauth.CustomOAuth2User;

@Controller
public class MyPageController {

	@GetMapping("/myPage")
	public String myPage(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
		Model model){
		model.addAttribute(customOAuth2User);
		return "myPage";
	}
}
