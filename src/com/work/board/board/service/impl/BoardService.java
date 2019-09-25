package com.work.board.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.work.board.board.dao.IBoardRepository;
import com.work.board.board.model.Board;
import com.work.board.board.model.BoardUploadFile;
import com.work.board.board.service.IBoardService;

@Service
public class BoardService implements IBoardService {

	@Autowired
	@Qualifier("IBoardRepository")
	IBoardRepository boardRepository;
	
	@Override
	public int getBoardListCnt(int categoryId){
		return boardRepository.getBoardListCnt();
	}
	
	@Transactional
	public void insertArticle(Board board) {
		board.setBoardId(boardRepository.selectMaxArticleNo()+1);
		boardRepository.insertArticle(board);
	}
	
	@Transactional
	public void insertArticle(Board board, BoardUploadFile file) {
		//게시판 number +1해서 넣기
		board.setBoardId(boardRepository.selectMaxArticleNo()+1);
		boardRepository.insertArticle(board);
		//파일이 있는지 & 파일 이름이 NULL이 아니면 파일 넣기
        if(file != null && file.getFileName() != null && !file.getFileName().equals("")) {
        	file.setBoardId(board.getBoardId());
        	file.setFileId(boardRepository.selectMaxFileId()+1);
        	boardRepository.insertFileData(file);
        }
	}

	@Transactional
	public void insertFile(int boardId, BoardUploadFile file) {
		if(file != null && file.getFileName() != null && !file.getFileName().equals("")) {
        	file.setBoardId(boardId);
        	file.setFileId(boardRepository.selectMaxFileId()+1);
        	boardRepository.insertFileData(file);
        }
	}
	
	@Override
	public List<Board> selectArticleListByCategory(int categoryId, int page, int count) {
		int start = (page-1) * 11 ;
		return boardRepository.selectArticleListByCategory(categoryId, start, start+10);
	}

	@Override
	public List<Board> selectArticleListByCategory(int categoryId) {
		return boardRepository.selectArticleListByCategory(categoryId, 0, 100);
	}

	@Transactional
	public Board selectArticle(int boardId) {
		boardRepository.updateReadCount(boardId);
		return boardRepository.selectArticle(boardId);
	}
	
	@Override
	public List<BoardUploadFile> getFileList(int boardId) {
		return boardRepository.getFileList(boardId);
	}
	
	@Override
	public BoardUploadFile getFile(int fileId) {
		return boardRepository.getFile(fileId);
	}

	@Override
	public void updateArticle(Board board) {
		boardRepository.updateArticle(board);
	}

	@Transactional
	public void updateArticle(Board board, BoardUploadFile file) {
		boardRepository.updateArticle(board);
        if(file != null && file.getFileName() != null && !file.getFileName().equals("")) {
        	file.setBoardId(board.getBoardId());
//        	System.out.println(file.toString());
        	if(file.getFileId()>0) {
        		boardRepository.updateFileData(file);
        	}else {
        		boardRepository.insertFileData(file);
        	}
        }
	}
	
	@Transactional
	public void updateFile(int boardId, BoardUploadFile file) {
		
	}
	
	@Override
	public Board selectDeleteArticle(int boardId) {
		return boardRepository.selectDeleteArticle(boardId);
	}
	
	@Transactional
	public void deleteArticle(int boardId) {
		System.out.println("삭제");
			boardRepository.deleteFileData(boardId);
			boardRepository.deleteReplyData(boardId);
			boardRepository.deleteArticleByBoardId(boardId);
	}

	@Transactional
	public void deleteFile(int boardId) {
		boardRepository.deleteFileData(boardId);
	}
	
	@Override
	public int selectTotalArticleCount() {
		return boardRepository.selectTotalArticleCount();
	}

	@Override
	public int selectTotalArticleCountByCategoryId(int categoryId) {
		return boardRepository.selectTotalArticleCountByCategoryId(categoryId);
	}

	@Override
	public List<Board> searchListByContentKeyword(String keyword, int page) {
		int start = (page-1) * 10;
		return boardRepository.searchListByContentKeyword("%"+keyword+"%", start, start+11);
	}

	@Override
	public int selectTotalArticleCountByKeyword(String keyword) {
		return boardRepository.selectTotalArticleCountByKeyword("%"+keyword+"%");
	}

}

