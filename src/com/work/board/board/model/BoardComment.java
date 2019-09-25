package com.work.board.board.model;

import java.sql.Timestamp;

public class BoardComment {
	
	  	private int contentId;                                                                                       
	    private int boardId;                                                          
	    private String content;                                              
	    private String writer;                                                    
	    private Timestamp reg_date;                                               
		private int seq;                                                          
		private int page;                                                              
		                                                                                                       
		public int getcontentId() {                                                                                  
	        return contentId;                                                                                        
	    }
	 
	    public void setcontentId(int contentId) {
	        this.contentId = contentId;
	    }
	 
	    public int getboardId() {
	        return boardId;
	    }
	 
	    public void setboardId(int boardId) {
	        this.boardId = boardId;
	    }
	 
	    public String getContent() {
	        return content;
	    }
	 
	    public void setContent(String content) {
	        this.content = content;
	    }
	 
	    public String getWriter() {
	        return writer;
	    }
	 
	    public void setWriter(String writer) {
	        this.writer = writer;
	    }
	 
	    public Timestamp getReg_date() {
	        return reg_date;
	    }
	 
	    public void setReg_date(Timestamp reg_date) {
	        this.reg_date = reg_date;
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
	    
	@Override
	public String toString() {
		return "Comment [contentId=" + contentId + ", boardId=" + boardId + ", content=" + content 
				 + ", writer" + writer + ",reg_date=" + reg_date + ",seq=" + seq + "]";
	}
 
	
}
