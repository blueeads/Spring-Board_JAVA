package com.work.board.board.model;

import org.springframework.web.multipart.MultipartFile;

public class BoardUploadFile {
	private int fileId; //파일 넙머
	private int boardId; //게시글 넘버
	private String fileName; // 파일 이름
	private String fileOriginalName; //파일 오리지널 이름
	private long fileSize; //파일 사이즈
	private String fileContentType; //파일 타입
	private byte[] fileData; //바이너리 데이터(Byte)
	
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
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
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}	

	public String getFileOriginalName() {
		return fileOriginalName;
	}
	public void setFileOriginalName(String fileOriginalName) {
		this.fileOriginalName = fileOriginalName;
	}
	
	@Override
	public String toString() {
		return "boardFile [fileId=" + fileId + ", boardId=" + boardId + ", fileName=" + fileName
				+ ", fileOriginalName=" + fileOriginalName + ", fileSize=" + fileSize + ", fileContentType=" + fileContentType + "]";
	}


}
