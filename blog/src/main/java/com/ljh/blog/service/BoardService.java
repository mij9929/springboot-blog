package com.ljh.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ljh.blog.dto.ReplySaveRequestDto;
import com.ljh.blog.model.Board;
import com.ljh.blog.model.Reply;
import com.ljh.blog.model.User;
import com.ljh.blog.repository.BoardRepository;
import com.ljh.blog.repository.ReplyRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	

	@Transactional
	public void boardWrite(Board board, User user) {
		board.setUser(user);
		board.setCount(0);
		boardRepository.save(board);

	}

	@Transactional(readOnly = true)
	public Board boardDetail(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패");
		});
	}

	@Transactional
	public void boardDelete(int id, User user) {
		if (boardRepository.getById(id).getUser().getUsername().equals(user.getUsername())) {
			boardRepository.deleteById(id);
		}
	}

	@Transactional
	public void boardUpdate(int id, User user, Board requestBoard) {

		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 찾기 실패");
		});

		if (board.getUser().getId()==user.getId()) {
			board.setContent(requestBoard.getContent());
			board.setTitle(board.getTitle());
			System.out.println("success");
			boardRepository.save(board);
		}
	}

	@Transactional(readOnly = true)
	public Page<Board> boardLists(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	@Transactional
	public void  replyWrite(ReplySaveRequestDto replySaveRequestDto) {
		int result = replyRepository.mSave(replySaveRequestDto.getUserId(),
				replySaveRequestDto.getBoardId(),
				replySaveRequestDto.getContent()
				);
	}
	
	@Transactional
	public void replyDelete(int boardId, int replyId, User requestUser) {
		
		Reply reply = replyRepository.findById(replyId).orElseThrow(()->{
			return new IllegalArgumentException("댓글을 찾을 수 없음");
		});
		
		if(reply.getUser().getId() == requestUser.getId()) {
			replyRepository.deleteById(replyId);
		}
		
	}

}
