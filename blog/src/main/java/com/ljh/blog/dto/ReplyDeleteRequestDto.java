package com.ljh.blog.dto;

import lombok.Data;

@Data
public class ReplyDeleteRequestDto {

	public int boardId;
	public int userId;
	public int replyId;
	
}
