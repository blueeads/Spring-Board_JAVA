package com.work.board.board.service;

import java.util.List;

import com.work.board.board.model.BoardReply;

public interface IBoardReplyService {
<<<<<<< HEAD
	int ReCount(int boardId);
	
	List<BoardReply> commentList(int boardId, int page);
	int ReplyInsertService(BoardReply reply);
	int ReplyUpdateService(BoardReply reply);
	int ReplyDeleteService(int commentId);
=======
	int ReCount(int bno);
	
	List<BoardReply> commentList(int bno, int page);
	int ReplyInsertService(BoardReply reply);
	int ReplyUpdateService(BoardReply reply);
	int ReplyDeleteService(int cno);
>>>>>>> refs/remotes/origin/master
}
