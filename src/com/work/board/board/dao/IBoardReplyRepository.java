package com.work.board.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.work.board.board.model.BoardReply;

public interface IBoardReplyRepository {
	
	int selectMaxReplyNo();
	
    int commentCount();
 
    List<BoardReply> commentList(@Param("boardId") int boardId, @Param("start") int start, @Param("end") int end);
 
    int commentInsert(BoardReply comment);
    
    int commentUpdate(BoardReply comment);
 
    int commentDelete(int commentId);
    
	int ReCount(int boardId);

}
