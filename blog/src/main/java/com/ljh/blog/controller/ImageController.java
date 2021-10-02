package com.ljh.blog.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ljh.blog.config.auth.PrincipalDetail;
import com.ljh.blog.model.Image;
import com.ljh.blog.model.User;
import com.ljh.blog.repository.ImageRepository;

public class ImageController {

	@Value("${file.path}")
	private String fileRealPath;
	
	@Autowired
	ImageRepository imageRepository;
	
	@PostMapping("/image/uploadProc")
	public String imageUpload(@AuthenticationPrincipal PrincipalDetail principalDetail, @RequestParam("file") MultipartFile file){
		

		UUID uuid = UUID.randomUUID();
		String uuidFilename = uuid + "_" + file.getOriginalFilename();
		
		Path filepath = Paths.get(fileRealPath + uuid);
		
		try {
			Files.write(filepath, file.getBytes());
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		User user = principalDetail.getUser();
		
		Image image = new Image();
		image.setImageNameAndPath(uuidFilename);
		
		
		return "redirect:/";
	}
}
