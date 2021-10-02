package com.ljh.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ljh.blog.model.Mail;

public interface MailRepository extends JpaRepository<Mail, Integer>{
	
	Page<Mail> findBySender(String sender, Pageable pageable);
	Page<Mail> findByReceiver(String sender, Pageable pageable);
}
