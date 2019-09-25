package com.work.board.board.dao;

import java.util.List;

import com.work.board.board.model.BoardCategory;

public interface IBoardCategoryRepository {
	int selectMaxCategoryId();
	List<BoardCategory> selectAllCategory();
	List<BoardCategory> selectAllCategoryByClass1(int class1);
}
