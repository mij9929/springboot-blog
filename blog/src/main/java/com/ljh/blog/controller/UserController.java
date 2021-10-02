package com.ljh.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljh.blog.config.auth.PrincipalDetail;
import com.ljh.blog.model.KakaoProfile;
import com.ljh.blog.model.OAuthToken;
import com.ljh.blog.model.User;
import com.ljh.blog.service.MailService;
import com.ljh.blog.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Value("${ljh.key}")
	private String passKey;
	
	@GetMapping("/auth/joinform")
	public String joinForm() {
		return "user/joinform";
	}

	@GetMapping("/auth/loginform")
	public String loginForm() {
		return "user/loginform";
	}

	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) { // @ResponseBody: Data return

		RestTemplate rt = new RestTemplate();

		// Http header 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// Http body 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "60238d6273f07c2bbd97a009c80e9c7d");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);

		// Header와 Body를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청 (Post)
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenRequest, String.class);

		// Gson, Json Simple, ObjectMapper
		ObjectMapper obMapper = new ObjectMapper();
		OAuthToken oAuthToken = null;
		try {
			oAuthToken = obMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RestTemplate rt2 = new RestTemplate();

		// Http header 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// Header와 Body를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);

		// Http 요청 (Post)
		ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
				kakaoProfileRequest, String.class);

		ObjectMapper obMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			System.out.println(response2.getBody());
			kakaoProfile = obMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// User 오브젝트 : username, password, email
		User kakaoUser = User.builder().
				username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId()).
				password(passKey).
				email(kakaoProfile.getKakao_account().getEmail()).
				oauth("kakao").
				build();

		
		User originUser = userService.userSelect(kakaoUser.getUsername());
		if(originUser.getUsername()== null) {
			userService.회원가입(kakaoUser);
		}

		
		else {
			Authentication authenication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), passKey));
			SecurityContextHolder.getContext().setAuthentication(authenication);
		}
			
		
		return "redirect:/";
	}
	

	@GetMapping("/user/updateForm") 
	public String userUpdateForm() {
		return "user/updateForm";
	}
	
	@GetMapping("/user/mail")
	public String userMail(Model model, @PageableDefault(size=10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, 
			@AuthenticationPrincipal PrincipalDetail principalDetail) {
		model.addAttribute("sendMails", mailService.sendMailLists(pageable, principalDetail.getUser()));
		model.addAttribute("receiveMails", mailService.receiveMailLists(pageable, principalDetail.getUser()));
		return "user/mail";
	}
	
	@GetMapping("/user/mailSave") 
	public String userMailSaveForm(Model model) {
		return "user/mailSave";
	}

}
