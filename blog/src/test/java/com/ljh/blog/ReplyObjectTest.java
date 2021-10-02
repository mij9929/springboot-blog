package com.ljh.blog;

import org.junit.jupiter.api.Test;

import com.ljh.blog.model.Reply;

public class ReplyObjectTest {

	@Test
	public void toStringTest() {
	
		 Reply reply = Reply.builder()
				 .id(1)
				 .user(null).board(null).content("test").build();
		 
		 System.out.print(reply);
	}
}
