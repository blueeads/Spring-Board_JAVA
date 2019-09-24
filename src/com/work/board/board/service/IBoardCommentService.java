package com.work.board.board.service;

import java.util.List;

import com.work.board.board.model.BoardComment;

public interface IBoardCommentService {
	int ReCount(int boardId);
	
	List<BoardComment> commentList(int boardId, int page);
	int commentInsertService(BoardComment comment);
	int commentUpdateService(BoardComment comment);
	int commentDeleteService(int contentId);
}
