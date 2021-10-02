package com.ljh.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ljh.blog.model.ChatRoom;
import com.ljh.blog.repository.ChatRepository;

@Service
public class ChatRoomService {
	
	@Autowired
	ChatRepository chatRepository;

	@Transactional(readOnly = true)
	public Page<ChatRoom> roomLists(Pageable pageable){
		return chatRepository.findAll(pageable);
	}
}
