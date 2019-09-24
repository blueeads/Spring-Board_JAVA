package com.work.board.board.service;

import java.util.List;

import com.work.board.board.model.Board;
import com.work.board.board.model.BoardUploadFile;

public interface IBoardService {
	int getBoardListCnt(int categoryId);
	
	void insertArticle(Board boardId);
	void insertArticle(Board boardId, BoardUploadFile file);
	void insertFile(int boardId, BoardUploadFile file);
	
	List<Board> selectArticleListByCategory(int categoryId, int page);
	List<Board> selectArticleListByCategory(int categoryId);
	
	Board selectArticle(int boardId);

	List<BoardUploadFile> getFileList(int boardId);
	BoardUploadFile getFile(int fileId);
	
	void updateArticle(Board board);
	void updateArticle(Board board, BoardUploadFile file);
	void updateFile(int boardId, BoardUploadFile file);
	
	Board selectDeleteArticle(int boardId);
	void deleteArticle(int boardId);
	void deleteFile(int boardId);
	
	int selectTotalArticleCount();
	int selectTotalArticleCountByCategoryId(int categoryId);

	List<Board> searchListByContentKeyword(String keyword, int page);
	int selectTotalArticleCountByKeyword(String keyword);
}
