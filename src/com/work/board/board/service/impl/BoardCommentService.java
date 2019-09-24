package com.work.board.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.work.board.board.dao.IBoardCommentRepository;
import com.work.board.board.model.BoardComment;
import com.work.board.board.service.IBoardCommentService;

@Service
public class BoardCommentService implements IBoardCommentService{

	@Autowired
	@Qualifier("IBoardCommentRepository")
	IBoardCommentRepository boardcommentRepository;
	
	@Override
	public int ReCount(int boardId) {
		return boardcommentRepository.ReCount(boardId);
	}
	
	@Override
	public List<BoardComment> commentList(int boardId, int page) {
		int start = (page-1) * 6;
		List<BoardComment> comment = boardcommentRepository.commentList(boardId, start, start+5);
		System.out.println(comment);
		return comment;
	}
	
	@Override
	public int commentInsertService(BoardComment comment){
		comment.setcontentId(boardcommentRepository.selectMaxcommentNo()+1);
		return boardcommentRepository.commentInsert(comment);
	}
	
	@Override
	public int commentUpdateService(BoardComment comment){
		return boardcommentRepository.commentUpdate(comment);
	}
	
	@Override
	public int commentDeleteService(int contentId){
		return boardcommentRepository.commentDelete(contentId);
	}

}
