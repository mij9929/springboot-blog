package com.ljh.blog.dto;

import lombok.Data;

@Data
public class MailSaveRequestDto {
	
	private String receiver;
	private String title;
	private String content;
}
