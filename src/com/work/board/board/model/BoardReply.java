package com.work.board.board.model;

import java.sql.Timestamp;

public class BoardReply {
	
	  	private int cno;                                                                                       
	    private int bno;                                                          
	    private String content;                                              
	    private String writer;                                                    
	    private Timestamp reg_date;                                               
		private int seq;                                                          
		private int page;                                                              
		                                                                                                       
		public int getCno() {                                                                                  
	        return cno;                                                                                        
	    }
	 
	    public void setCno(int cno) {
	        this.cno = cno;
	    }
	 
	    public int getBno() {
	        return bno;
	    }
	 
	    public void setBno(int bno) {
	        this.bno = bno;
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
		return "Reply [cno=" + cno + ", bno=" + bno + ", content=" + content 
				 + ", writer" + writer + ",reg_date=" + reg_date + ",seq=" + seq + "]";
	}
 
	
}
