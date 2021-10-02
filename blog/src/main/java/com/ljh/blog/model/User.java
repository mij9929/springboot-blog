package com.ljh.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert // insert시 null인 필드를 제외시켜줌
@Entity // User 클래스가 MySQL에 테이블이 생성됨
public class User {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 연결된 DB의 넘버링 전략을 따라감.(MYSQL이면 AutoIncrement)
	private int id;
	
	@Column(unique = true, nullable=false, length=100)
	private String username;
	
	@Column(nullable=false, length=100)
	private String password;
	
	@Column(unique = true, nullable=false, length=100)
	private String email;
	
	private String profileImage;
	
	// @ColumnDefault("'user'")
	// DB는 RoleType이 없기 때문에 
	@Enumerated(EnumType.STRING)
	private RoleType role; 
	
	@Column(nullable=true)
	private String oauth; // kakao, google

	@CreationTimestamp
	private Timestamp createDate;
	

}
