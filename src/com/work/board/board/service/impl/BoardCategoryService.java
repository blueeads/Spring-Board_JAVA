package com.work.board.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.board.board.dao.IBoardCategoryRepository;
import com.work.board.board.model.BoardCategory;
import com.work.board.board.service.IBoardCategoryService;

@Service
public class BoardCategoryService implements IBoardCategoryService{

	@Autowired
	IBoardCategoryRepository boardCategoryRepository;
	
	@Override
	public List<BoardCategory> selectAllCategory() {
		return boardCategoryRepository.selectAllCategory();
	}

	@Override
	public List<BoardCategory> selectAllCategoryByClass1(int class1) {
		return boardCategoryRepository.selectAllCategoryByClass1(class1);
	}
}
