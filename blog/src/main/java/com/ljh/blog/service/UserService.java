package com.ljh.blog.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ljh.blog.config.auth.PrincipalDetail;
import com.ljh.blog.model.RoleType;
import com.ljh.blog.model.User;
import com.ljh.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌
@Service
public class UserService {

	@Value("${file.path}")
	private String fileRealPath;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	
	}

	@Transactional
	public void userUpdate(User user) {
		User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원 찾기 실패");
		});
		if (persistance.getOauth() == null || persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}

	}

	@Transactional(readOnly = true)
	public User userSelect(String username) {
		User isUser = userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});

		return isUser;
	}

	@Transactional
	public void profileUpload(MultipartFile file, PrincipalDetail principalDetail) throws IOException {

		User user = principalDetail.getUser();
		UUID uuid = UUID.randomUUID();
		
		String originalFilename =  file.getOriginalFilename();
		String uuidFilename = uuid + "_" + originalFilename;
		String extention = FilenameUtils.getExtension(uuidFilename);
		
		try {
			if(extention.equals("png") == true || !extention.equals("jpg")){
				throw new IllegalArgumentException("올바른 확장자 사용");
			}
				
			Path filePath = Paths.get(fileRealPath + uuidFilename);
				Files.write(filePath, file.getBytes());
				user.setProfileImage(uuidFilename);
				userRepository.save(user);

			
		}catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
