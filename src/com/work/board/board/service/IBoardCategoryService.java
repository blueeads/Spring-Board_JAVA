package com.work.board.board.service;

import java.util.List;

import com.work.board.board.model.BoardCategory;

public interface IBoardCategoryService {
	List<BoardCategory> selectAllCategory();
	List<BoardCategory> selectAllCategoryByClass1(int class1);
}
