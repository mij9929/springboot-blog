package com.ljh.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ljh.blog.dto.MailSaveRequestDto;
import com.ljh.blog.model.Mail;
import com.ljh.blog.model.User;
import com.ljh.blog.repository.MailRepository;
import com.ljh.blog.repository.UserRepository;

@Service
public class MailService {

	@Autowired
	private MailRepository mailRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public Page<Mail>  sendMailLists(Pageable pageable, User user) {
		return mailRepository.findBySender(user.getUsername(), pageable);
	}
	
	@Transactional(readOnly = true)
	public Page<Mail>  receiveMailLists(Pageable pageable, User user) {
		return mailRepository.findByReceiver(user.getUsername(), pageable);
	}
	
	@Transactional
	public void save(MailSaveRequestDto mailSaveRequestDto, User user) {

		int sendUserId = user.getId();
		User sendUser = userRepository.findById(sendUserId).orElseThrow(() -> {
			return new IllegalArgumentException("사용자 오류");
		});
			
		User receiveUser = userRepository.findByUsername(mailSaveRequestDto.getReceiver()).orElseThrow(() -> {
			return new IllegalArgumentException("찾을 수 없는 사용자");
		});
		
		Mail sendMail = new Mail();
		
		sendMail.setSender(sendUser.getUsername());
		sendMail.setReceiver(receiveUser.getUsername());
		sendMail.setTitle(mailSaveRequestDto.getTitle());
		sendMail.setContent(mailSaveRequestDto.getContent());

		mailRepository.save(sendMail);
	}
}
