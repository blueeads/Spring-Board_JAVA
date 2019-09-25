package com.work.board.board.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
public class Board {
	private int boardId;     //게시글 넘버
	private int categoryId;	 // 카테고리 넘버
	private String writer;   // 작성자
	private String email;	 // 이메일
	private String title;    // 제목
	private String content;  // 내용
	private Timestamp writeDate; //작성 일
	private int readCount;   //조회 수
	private int seq;		 // 실직적인 게시글 넘버
	private int page;		 // 페이지
	private BoardCategory category; //카테고리
	private int replyCount; // 댓글 카운트
	
	private List<MultipartFile> file;
	
	private int fileId;    //파일 아이디
	private String fileName; //파일 이름
	private long fileSize; //파일 사이즈
	private String fileContentType; //파일 타입
	
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