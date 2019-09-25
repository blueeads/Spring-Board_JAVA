package com.work.board.board.model;

import java.sql.Timestamp;

public class BoardReply {
	
	  	private int commentId;                                                                                       
	    private int boardId;                                                          
	    private String content;                                              
	    private String writer;                                                    
	    private Timestamp writeDate;                                               
		private int seq;                                                          
		private int page;                                                              
		                                                                                                       
		public int getcommentId() {                                                                                  
	        return commentId;                                                                                        
	    }
	 
	    public void setcommentId(int commentId) {
	        this.commentId = commentId;
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
	 
	    public Timestamp getwriteDate() {
	        return writeDate;
	    }
	 
	    public void setwriteDate(Timestamp writeDate) {
	        this.writeDate = writeDate;
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
		return "Reply [commentId=" + commentId + ", boardId=" + boardId + ", content=" + content 
				 + ", writer" + writer + ",writeDate=" + writeDate + ",seq=" + seq + "]";
	}
 
	
}
