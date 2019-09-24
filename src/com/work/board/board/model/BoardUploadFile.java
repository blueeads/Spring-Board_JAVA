package com.work.board.board.model;

import org.springframework.web.multipart.MultipartFile;

public class BoardUploadFile {
	private int fileId;
	private int boardId;
	private String fileName;
	private String fileOriginalName;
	private long fileSize;
	private String fileContentType;
	private byte[] fileData;
	
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
