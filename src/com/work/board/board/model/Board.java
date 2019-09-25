package com.work.board.board.model;

import java.sql.Timestamp;
<<<<<<< HEAD
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


=======
<<<<<<< HEAD
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
public class Board {
	private int boardId; 		//野껊슣�뻻占쎈솇 占쎄퐜甕곤옙
	private int categoryId;		//燁삳똾��믤�⑥쥓�봺 占쎄퐜甕곤옙
	private String writer;		//占쎌삂占쎄쉐占쎌쁽
	private String email;		//e筌롫뗄�뵬
	private String title;		//占쎌젫筌륅옙
	private String content;		//占쎄땀占쎌뒠
	private Timestamp writeDate;//占쎌삂占쎄쉐占쎈뻻揶쏉옙
	private int readCount;		//野껊슣�뻻疫뀐옙占쎌뵠 占쎈땭�뵳占� 占쎌뒒占쎈땾
	private int seq;			//占쎈뼄筌욌뜆�읅占쎌뵥 野껊슣�뻻疫뀐옙 占쎄퐜甕곤옙
	private int page;			//野껊슣�뻻疫뀐옙占쎌벥 占쎈읂占쎌뵠筌욑옙
	private BoardCategory category;//燁삳똾��믤�⑥쥓�봺 占쎌뵠�뵳占�
	private List<MultipartFile> file; //筌ｂ뫀占� 占쎈솁占쎌뵬
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