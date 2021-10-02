package com.ljh.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ljh.blog.config.auth.PrincipalDetail;
import com.ljh.blog.dto.MailSaveRequestDto;
import com.ljh.blog.dto.ResponseDTO;
import com.ljh.blog.repository.MailRepository;
import com.ljh.blog.repository.UserRepository;
import com.ljh.blog.service.MailService;

@RestController
public class MailApiController {

	@Autowired
	private MailRepository mailRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MailService mailService;
	
	
	@PostMapping("/api/mail")
	public  ResponseDTO<Integer> save(@RequestBody MailSaveRequestDto mailSaveRequestDto, @AuthenticationPrincipal PrincipalDetail principalDetail) {
		mailService.save(mailSaveRequestDto, principalDetail.getUser());
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
		
	}
}
