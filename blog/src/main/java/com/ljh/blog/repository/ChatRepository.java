package com.ljh.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ljh.blog.model.ChatRoom;

public interface ChatRepository extends JpaRepository<ChatRoom, Integer>{

	Optional<ChatRoom> findByRoomName(String roomName);
}
