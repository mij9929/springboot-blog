package com.ljh.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ljh.blog.config.auth.PrincipalDetail;
import com.ljh.blog.dto.ReplySaveRequestDto;
import com.ljh.blog.dto.ResponseDTO;
import com.ljh.blog.model.Board;
import com.ljh.blog.service.BoardService;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDTO<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal ) {
		boardService.boardWrite(board, principal.getUser());
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDTO<Integer> delete(@PathVariable int id, @AuthenticationPrincipal PrincipalDetail principal){
		boardService.boardDelete(id, principal.getUser());
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDTO<Integer> update(@RequestBody Board board, @PathVariable int id, @AuthenticationPrincipal PrincipalDetail principal){
		boardService.boardUpdate(id, principal.getUser(), board);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
	
	
	// 데이터를 받을 때 컨트롤러에서 dt를 만들어서 받는게 좋다.
	// dto를 사용하지 않는 이뉴는 프로젝트가 작아서
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDTO<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto ){
		boardService.replyWrite(replySaveRequestDto);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDTO<Integer> replyDelete(@PathVariable int boardId, @PathVariable int replyId, @AuthenticationPrincipal PrincipalDetail principal){
		boardService.replyDelete(boardId, replyId, principal.getUser());
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
	
	
}
