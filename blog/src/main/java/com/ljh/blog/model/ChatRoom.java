package com.ljh.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class ChatRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int roomNum;
	
	private String roomName;
	
	@Override
	public String toString() {
		return "ChatRoom [roomNum=" + roomNum + ", roomName=" + roomName + "]";
	}
	
	
}
