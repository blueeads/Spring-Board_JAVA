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
	public List<BoardReply> commentList(int bno, int page) {
		int start = (page-1) * 6;
		List<BoardReply> reply = boardReplyRepository.commentList(bno, start, start+5);
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
		return boardReplyRepository.commentUpdate(reply);
	}
	
	@Override
	public int ReplyDeleteService(int cno){
		return boardReplyRepository.commentDelete(cno);
	}

}
