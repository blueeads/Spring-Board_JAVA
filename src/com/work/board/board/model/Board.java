package com.work.board.board.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
public class Board {
	private int boardId;     //�Խñ� �ѹ�
	private int categoryId;	 // ī�װ� �ѹ�
	private String writer;   // �ۼ���
	private String email;	 // �̸���
	private String title;    // ����
	private String content;  // ����
	private Timestamp writeDate; //�ۼ� ��
	private int readCount;   //��ȸ ��
	private int seq;		 // �������� �Խñ� �ѹ�
	private int page;		 // ������
	private BoardCategory category; //ī�װ�
	private int replyCount; // ��� ī��Ʈ
	
	private List<MultipartFile> file;
	
	private int fileId;    //���� ���̵�
	private String fileName; //���� �̸�
	private long fileSize; //���� ������
	private String fileContentType; //���� Ÿ��
	
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Timestamp writeDate) {
		this.writeDate = writeDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public BoardCategory getCategory() {
		return category;
	}
	public void setCategory(BoardCategory category) {
		this.category = category;
	}
	public List<MultipartFile> getFile() {
		return file;
	}
	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}

	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	
	@Override
	public String toString() {
		return "Board [boardId=" + boardId + ", categoryId=" + categoryId + ", writer=" + writer + ", email=" + email
				+ ", title=" + title + ", writeDate=" + writeDate
				+ ", readCount=" + readCount + ", seq=" + seq + ", content=" + content + ", fileId=" + fileId
				+ ", fileName=" + fileName + ", fileSize=" + fileSize  +", replyCount=" + replyCount +", fileContentType=" + fileContentType + "]";
	}



}