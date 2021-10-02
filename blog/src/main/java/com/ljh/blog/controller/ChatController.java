package com.ljh.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ljh.blog.model.ChatRoom;
import com.ljh.blog.repository.ChatRepository;
import com.ljh.blog.service.ChatRoomService;

@Controller
public class ChatController {

	@Autowired
	ChatRoomService chatRoomService;

	@Autowired
	ChatRepository chatRepository;
	
	@GetMapping("/chat/room")
	public String room(Model model,@PageableDefault(size=10, direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("rooms", chatRoomService.roomLists(pageable));
		return "chat/room";
	}
	
	@GetMapping("/chat/room/{roomNum}")
	public String chatingRoom(Model model, @PathVariable int roomNum)
	{
		ChatRoom room = chatRepository.findById(roomNum).orElseThrow(()->{
			return new IllegalArgumentException("Illegal Room number: " + roomNum);
		});
		
		model.addAttribute("room", room);
		return "chat/chat";
	}
	
	
	
}
