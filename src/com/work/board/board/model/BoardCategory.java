package com.work.board.board.model;

public class BoardCategory {
	private int categoryId; //카테고리 넘버
	private int categoryClass1; //카테고리 클래스 넘버
	private String categoryName; //카테고리 이름
	private int categoryOrder; //카테고리 순서
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getCategoryClass1() {
		return categoryClass1;
	}
	public void setCategoryClass1(int categoryClass1) {
		this.categoryClass1 = categoryClass1;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getCategoryOrder() {
		return categoryOrder;
	}
	public void setCategoryOrder(int categoryOrder) {
		this.categoryOrder = categoryOrder;
	}
	
	@Override
	public String toString() {
		return "BoardCategory [categoryId=" + categoryId + ", categoryClass1=" + categoryClass1 + ","
				+ " categoryName=" + categoryName + ", categoryOrder=" + categoryOrder + "]";
	}
}
