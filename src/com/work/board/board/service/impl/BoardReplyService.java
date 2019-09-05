package com.work.board.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.work.board.board.dao.IBoardReplyRepository;
import com.work.board.board.model.BoardReply;
import com.work.board.board.service.IBoardReplyService;

@Service
public class BoardReplyService implements IBoardReplyService{

	@Autowired
	@Qualifier("IBoardReplyRepository")
	IBoardReplyRepository boardReplyRepository;
	
	@Override
	public int ReCount(int bno) {
		return boardReplyRepository.ReCount(bno);
	}
	
	@Override
	public List<BoardReply> commentList(int bno) {
		//int start = (page-1) * 10;
		List<BoardReply> reply = boardReplyRepository.commentList(bno, 1, 100);
		System.out.println(reply);
		return reply;
	}
	
	@Override
	public int ReplyInsertService(BoardReply reply){
		reply.setCno(boardReplyRepository.selectMaxReplyNo()+1);
		return boardReplyRepository.commentInsert(reply);
	}
	
	@Override
	public int ReplyUpdateService(BoardReply reply){
		System.out.println("댓글 수정");
		return boardReplyRepository.commentUpdate(reply);
	}
	
	@Override
	public int ReplyDeleteService(int cno){
		System.out.println("댓글 삭제");
		return boardReplyRepository.commentDelete(cno);
	}

}
