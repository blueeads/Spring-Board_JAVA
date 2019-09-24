package com.work.board.board.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
public class Board {
	private int boardId; 		//寃뚯떆�뙋 �꽆踰�
	private int categoryId;		//移댄뀒怨좊━ �꽆踰�
	private String writer;		//�옉�꽦�옄
	private String email;		//e硫붿씪
	private String title;		//�젣紐�
	private String content;		//�궡�슜
	private Timestamp writeDate;//�옉�꽦�떆媛�
	private int readCount;		//寃뚯떆湲��씠 �닃由� �슏�닔
	private int seq;			//�떎吏덉쟻�씤 寃뚯떆湲� �꽆踰�
	private int page;			//寃뚯떆湲��쓽 �럹�씠吏�
	private BoardCategory category;//移댄뀒怨좊━ �씠由�
	private List<MultipartFile> file; //泥⑤� �뙆�씪
	private int commentCount;
	
	private int fileId;
	private String fileName;
	private long fileSize;
	private String fileContentType;
	
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
	public int getcommentCount() {
		return commentCount;
	}
	public void setcommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	@Override
	public String toString() {
		return "Board [boardId=" + boardId + ", categoryId=" + categoryId + ", writer=" + writer + ", email=" + email
				+ ", title=" + title + ", writeDate=" + writeDate
				+ ", readCount=" + readCount + ", seq=" + seq + ", content=" + content + ", fileId=" + fileId
				+ ", fileName=" + fileName + ", fileSize=" + fileSize  +", commentCount=" + commentCount +", fileContentType=" + fileContentType + "]";
	}



}