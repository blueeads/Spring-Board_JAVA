package com.work.board.board.model;

public class BoardCategory {
	private int categoryId; //ī�װ� �ѹ�
	private int categoryClass1; //ī�װ� Ŭ���� �ѹ�
	private String categoryName; //ī�װ� �̸�
	private int categoryOrder; //ī�װ� ����
	
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
