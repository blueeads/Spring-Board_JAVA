package com.work.board.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.work.board.board.model.BoardComment;

public interface IBoardCommentRepository {
	
	int selectMaxcommentNo();
	
    int commentCount();
 
    List<BoardComment> commentList(@Param("boardId") int boardId, @Param("start") int start, @Param("end") int end);
 
    int commentInsert(BoardComment comment);
    
    int commentUpdate(BoardComment comment);
 
    int commentDelete(int contentId);
    
	int ReCount(int boardId);

}
