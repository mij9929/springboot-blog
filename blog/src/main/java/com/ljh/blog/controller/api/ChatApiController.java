package com.ljh.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ljh.blog.model.ChatRoom;
import com.ljh.blog.repository.ChatRepository;

@RestController
public class ChatApiController {

	@Autowired
	private ChatRepository chatRepository;
	

	@PostMapping("/api/chat")
	public @ResponseBody String createRoom(@RequestBody String roomName) {
		ChatRoom room = new ChatRoom();
		System.out.println("roomName");
		room.setRoomName(roomName);
		chatRepository.save(room);
		
		return chatRepository.findByRoomName(room.getRoomName()).toString();
	}
	
}
