package com.work.board.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.work.board.board.model.Board;
import com.work.board.board.model.BoardUploadFile;

public interface IBoardRepository {
	int selectMaxArticleNo();
	int selectMaxFileId();
	
	int getBoardListCnt();
	
	void insertArticle(Board board);
	void insertFileData(BoardUploadFile file);
	
	List<Board> selectArticleListByCategory(@Param("categoryId") int categoryId, @Param("start") int start, @Param("end") int end);
	
	Board selectArticle(int boardId);
	List<BoardUploadFile> getFileList(int boardId);
	BoardUploadFile getFile(int fileId);
		
	void updateReadCount(int boardId);

	void updateArticle(Board board);
	void updateFileData(BoardUploadFile file);
	
	void deleteReplyData(int boardId);
	void deleteFileData(int boardId);
	Board selectDeleteArticle(int boardId);
	void deleteArticleByBoardId(int boardId);
	
	int selectTotalArticleCount();
	int selectTotalArticleCountByCategoryId(int categoryId);

	int selectTotalArticleCountByKeyword(String keyword);
	List<Board> searchListByContentKeyword(@Param("keyword") String keyword, @Param("start") int start, @Param("end") int end);
}
