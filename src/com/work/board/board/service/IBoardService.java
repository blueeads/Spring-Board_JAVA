package com.work.board.board.service;

import java.util.List;

import com.work.board.board.model.Board;
import com.work.board.board.model.BoardUploadFile;

public interface IBoardService {
	int getBoardListCnt(int categoryId) throws Exception;
	
	void insertArticle(Board boardId);
	void insertArticle(Board boardId, BoardUploadFile file);
	
	List<Board> selectArticleListByCategory(int categoryId, int page);
	List<Board> selectArticleListByCategory(int categoryId);
	
	Board selectArticle(int boardId);
	
	BoardUploadFile getFile(int fileId);
	String getPassword(int boardId);
	
	void updateArticle(Board board);
	void updateArticle(Board board, BoardUploadFile file);
	
	Board selectDeleteArticle(int boardId);
	void deleteArticle(int boardId);
	
	int selectTotalArticleCount();
	int selectTotalArticleCountByCategoryId(int categoryId);

	List<Board> searchListByContentKeyword(String keyword, int page);
	int selectTotalArticleCountByKeyword(String keyword);
}
