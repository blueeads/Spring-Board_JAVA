package com.work.board.board.model;

import java.sql.Timestamp;
<<<<<<< HEAD
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
public class Board {
	private int boardId;     //°Ô½Ã±Û ³Ñ¹ö
	private int categoryId;	 // Ä«Å×°í¸® ³Ñ¹ö
	private String writer;   // ÀÛ¼ºÀÚ
	private String email;	 // ÀÌ¸ÞÀÏ
	private String title;    // Á¦¸ñ
	private String content;  // ³»¿ë
	private Timestamp writeDate; //ÀÛ¼º ÀÏ
	private int readCount;   //Á¶È¸ ¼ö
	private int seq;		 // ½ÇÁ÷ÀûÀÎ °Ô½Ã±Û ³Ñ¹ö
	private int page;		 // ÆäÀÌÁö
	private BoardCategory category; //Ä«Å×°í¸®
	private int replyCount; // ´ñ±Û Ä«¿îÆ®
	
	private List<MultipartFile> file;
	
	private int fileId;    //ÆÄÀÏ ¾ÆÀÌµð
	private String fileName; //ÆÄÀÏ ÀÌ¸§
	private long fileSize; //ÆÄÀÏ »çÀÌÁî
	private String fileContentType; //ÆÄÀÏ Å¸ÀÔ
	
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


=======
<<<<<<< HEAD
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
public class Board {
	private int boardId; 		//å¯ƒëš¯ë–†ï¿½ë™‹ ï¿½ê½†è¸°ï¿½
	private int categoryId;		//ç§»ëŒ„ë€’æ€¨ì¢Šâ” ï¿½ê½†è¸°ï¿½
	private String writer;		//ï¿½ì˜‰ï¿½ê½¦ï¿½ì˜„
	private String email;		//eï§Žë¶¿ì”ª
	private String title;		//ï¿½ì £ï§ï¿½
	private String content;		//ï¿½ê¶¡ï¿½ìŠœ
	private Timestamp writeDate;//ï¿½ì˜‰ï¿½ê½¦ï¿½ë–†åª›ï¿½
	private int readCount;		//å¯ƒëš¯ë–†æ¹²ï¿½ï¿½ì”  ï¿½ë‹ƒç”±ï¿½ ï¿½ìŠï¿½ë‹”
	private int seq;			//ï¿½ë–Žï§žë‰ìŸ»ï¿½ì”¤ å¯ƒëš¯ë–†æ¹²ï¿½ ï¿½ê½†è¸°ï¿½
	private int page;			//å¯ƒëš¯ë–†æ¹²ï¿½ï¿½ì“½ ï¿½ëŸ¹ï¿½ì” ï§žï¿½
	private BoardCategory category;//ç§»ëŒ„ë€’æ€¨ì¢Šâ” ï¿½ì” ç”±ï¿½
	private List<MultipartFile> file; //ï§£â‘¤ï¿½ ï¿½ë™†ï¿½ì”ª
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


=======

import org.springframework.web.multipart.MultipartFile;

public class Board {
	private int boardId;
	private int categoryId;
	private String writer;
	private String email;
	private String title;
	private String content;
	private Timestamp writeDate;
	private int readCount;
	private int seq;
	private int page;
	private BoardCategory category;
	private MultipartFile file;

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
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
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
	
	@Override
	public String toString() {
		return "Board [boardId=" + boardId + ", categoryId=" + categoryId + ", writer=" + writer + ", email=" + email
				+ ", title=" + title + ", writeDate=" + writeDate
				+ ", readCount=" + readCount + ", seq=" + seq + ", category=" + category + ", fileId=" + fileId
				+ ", fileName=" + fileName + ", fileSize=" + fileSize + ", fileContentType=" + fileContentType + "]";
	}
>>>>>>> refs/remotes/origin/master
>>>>>>> refs/remotes/origin/master

}