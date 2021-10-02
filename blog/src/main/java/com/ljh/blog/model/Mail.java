package com.ljh.blog.model;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length=30)
	private String sender;
	
	@Column(nullable = false, length=30)
	private String receiver;

	@Column(nullable = false, length = 100)
	private String title;
	
	@Column(nullable = false, length =1000)
	private String content;
	
	@CreationTimestamp
	private Timestamp timestamp;
	
}
