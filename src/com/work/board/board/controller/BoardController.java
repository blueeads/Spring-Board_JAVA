package com.work.board.board.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.board.board.model.Board;
import com.work.board.board.model.BoardCategory;
import com.work.board.board.model.BoardUploadFile;
import com.work.board.board.model.BoardReply;
import com.work.board.board.service.IBoardCategoryService;
import com.work.board.board.service.IBoardService;
import com.work.board.board.service.IBoardReplyService;

@Controller
public class BoardController {
	static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	IBoardService boardService;
	
	@Autowired
	IBoardCategoryService categoryService;
	
	@Autowired
	IBoardReplyService replyService;         
	
	@RequestMapping ("/board/cat/{categoryId}/{page}")                                                                            
	//List                            
	public String getListByCategory(@PathVariable int categoryId, @PathVariable int page, HttpSession session, Model model) {
		session.setAttribute("page", page);                                                                                      
		model.addAttribute("categoryId", categoryId);                                                                            
	                                                                                                                             
		List<Board> boardList = boardService.selectArticleListByCategory(categoryId, page);                                      
		model.addAttribute("boardList", boardList);                                                                         
	                                                                                                                             
		// paging start                                                                                                          
		int bbsCount = boardService.selectTotalArticleCountByCategoryId(categoryId);                                             
		int totalPage = 0;                                                                                                       
		if(bbsCount > 0) {                                                                                                       
			totalPage= (int)Math.ceil(bbsCount/10.0);                                                                            
		}                                                                                                                        
		model.addAttribute("totalPageCount", totalPage);                                                                         
		model.addAttribute("page", page);                                                                                        
		return "board/list";                                                                                                     
	}                                                                                                                       
	
	@RequestMapping("/board/cat/{categoryId}")
	public String getListByCategory(@PathVariable int categoryId, HttpSession session, Model model) {
		return getListByCategory(categoryId, 1, session, model);
	}
	
	@RequestMapping(value="/board/boardlist", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String getList(@RequestParam(value="categoryId") int categoryId, @RequestParam(value="page") int page){
		List<Board> List = boardService.selectArticleListByCategory(categoryId, page);
		
		String str = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			str = mapper.writeValueAsString(List);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return str;
	}
	
	@RequestMapping("/board/{boardId}/{page}")
	public String getBoardDetails(@PathVariable int boardId, @PathVariable int page, Model model) {
		Board board = boardService.selectArticle(boardId);
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		model.addAttribute("");
		model.addAttribute("categoryId", board.getCategoryId());
		
		logger.info("getBoardDetails " + board.toString());
		return "board/view";
	}
	
	
	@RequestMapping("/board/{boardId}")
	public String getBoardDetails(@PathVariable int boardId, Model model) {
		return getBoardDetails(boardId, 1, model);
	}
	
	@RequestMapping(value="/board/write/{categoryId}", method=RequestMethod.GET)
	public String writeArticle(@PathVariable int categoryId, Model model) {
		List<BoardCategory> categoryList = categoryService.selectAllCategory();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("categoryId", categoryId);
		return "board/write";
	}
	
	@RequestMapping(value="/board/write", method=RequestMethod.POST)
	public String writeArticle(Board board, BindingResult result, RedirectAttributes redirectAttrs) {
		logger.info("/board/write : " + board.toString());

		try{
			board.setTitle(Jsoup.clean(board.getTitle(), Whitelist.basic()));
			board.setContent(Jsoup.clean(board.getContent(), Whitelist.basic()));
			MultipartFile mfile = board.getFile();
			if(mfile!=null && !mfile.isEmpty()) {
				logger.info("/board/write : " + mfile.getOriginalFilename());
				BoardUploadFile file = new BoardUploadFile();
				file.setFileName(mfile.getOriginalFilename());
				file.setFileSize(mfile.getSize());
				file.setFileContentType(mfile.getContentType());
				file.setFileData(mfile.getBytes());
				logger.info("/board/write : " + file.toString());

				boardService.insertArticle(board, file);
			}else {
				boardService.insertArticle(board);
			}
		}catch(Exception e){
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/board/cat/"+board.getCategoryId();
	}

	@RequestMapping("/file/{fileId}")
	public ResponseEntity<byte[]> getFile(@PathVariable int fileId) {
		BoardUploadFile file = boardService.getFile(fileId);
		logger.info("getFile " + file.toString());
		final HttpHeaders headers = new HttpHeaders();
		String[] mtypes = file.getFileContentType().split("/");
		headers.setContentType(new MediaType(mtypes[0], mtypes[1]));
		headers.setContentLength(file.getFileSize());
		headers.setContentDispositionFormData("attachment", file.getFileName());
		return new ResponseEntity<byte[]>(file.getFileData(), headers, HttpStatus.OK);
	}                                
	
	 @RequestMapping(value="/reply/getboardReply", method = {RequestMethod.GET,RequestMethod.POST})
	 @ResponseBody
	 public String getcommentList(@RequestParam("bno") int bno, HttpServletRequest request) throws Exception{
		List<BoardReply> List = replyService.commentList(bno);
		
		String str = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			str = mapper.writeValueAsString(List);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return str;
	 }
	
	 //Comment Insert
	 @RequestMapping(value="/reply/view", method = RequestMethod.POST)
	 @ResponseBody
	    public String CommentServiceInsert(@RequestParam int bno, @RequestParam String content, @RequestParam String writer, HttpServletRequest request) throws Exception{
		 try {
		 BoardReply reply = new BoardReply();
		 reply.setBno(bno);
		 reply.setContent(content);
		 reply.setWriter(writer);
	     replyService.ReplyInsertService(reply);
	     } catch (Exception e){
	    	 e.printStackTrace();
	     }
	        return (String)request.getHeader("REFERER");
	    }
	    
	    @RequestMapping(value="/reply/update", method = {RequestMethod.GET,RequestMethod.POST})
	    @ResponseBody
	    private String CommentServiceUpdateProc(@RequestParam("cno") int cno, @RequestParam("content") String content, @RequestParam("bno")int bno, HttpServletRequest request) throws Exception{
	        BoardReply reply = new BoardReply();
	        reply.setCno(cno);
	        reply.setContent(content);
	        replyService.ReplyUpdateService(reply);
	        return "redirect:/board/"+bno;
	    }
	    
	    @RequestMapping(value="/reply/delete/{cno}", method = {RequestMethod.GET,RequestMethod.POST})
	    @ResponseBody
	    private String CommentServiceDelete(@RequestParam("cno") int cno, @RequestParam("bno")int bno, HttpServletRequest request) throws Exception{
	    	replyService.ReplyDeleteService(cno);
	        return "redirect:/board/"+bno;
	    }
	
	@RequestMapping(value="/board/update/{boardId}", method=RequestMethod.GET)
	public String updateArticle(@PathVariable int boardId, Model model) {
		List<BoardCategory> categoryList = categoryService.selectAllCategory();
		model.addAttribute("categoryList", categoryList);
		Board board = boardService.selectArticle(boardId);
		model.addAttribute("categoryId", board.getCategoryId());
		model.addAttribute("board", board);
		return "board/update";
	}

	@RequestMapping(value="/board/update", method=RequestMethod.POST)
	public String updateArticle(Board board, BindingResult result, HttpSession session, RedirectAttributes redirectAttrs) {
		logger.info("/board/update " + board.toString());
		try{
			board.setTitle(Jsoup.clean(board.getTitle(), Whitelist.basic()));
			board.setContent(Jsoup.clean(board.getContent(), Whitelist.basic()));
			MultipartFile mfile = board.getFile();
			if(mfile!=null && !mfile.isEmpty()) {
				logger.info("/board/update : " + mfile.getOriginalFilename());
				BoardUploadFile file = new BoardUploadFile();
				file.setFileId(board.getFileId());
				file.setFileName(mfile.getOriginalFilename());
				file.setFileSize(mfile.getSize());
				file.setFileContentType(mfile.getContentType());
				file.setFileData(mfile.getBytes());
				logger.info("/board/update : " + file.toString());
				boardService.updateArticle(board, file);
			}else {
				boardService.updateArticle(board);
			}
		}catch(Exception e){
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}

		return "redirect:/board/"+board.getBoardId();
	}

	@RequestMapping(value="/board/delete/{boardId}", method=RequestMethod.GET)
	public String deleteArticle(@PathVariable int boardId, Model model) {
		Board board = boardService.selectDeleteArticle(boardId);
		System.out.println("SBoard : " + board);
		model.addAttribute("categoryId", board.getCategoryId());
		model.addAttribute("boardId", boardId);
		System.out.println(board);
		return "board/delete";
	}
	
	//@RequestMapping(value="/board/delete", method=RequestMethod.POST)
	//public String deleteArticle(Board board, BindingResult result, HttpSession session, Model model) {
	//	try {
	//		if(session.getAttribute("userId") == board.getWriter()) {
	//			boardService.deleteArticle(board.getBoardId());
	//			return "redirect:/board/cat/"+board.getCategoryId()+"/"+(Integer)session.getAttribute("page");
	//		} else {
	//			model.addAttribute("message", "Error");
	//			return "error/runtime";
	//		}
	//	}catch(Exception e){
	//		model.addAttribute("message", e.getMessage());
	//		e.printStackTrace();
	//		return "error/runtime";
	//	}
	//}
	
	@RequestMapping(value="/board/delete", method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String deleteArticle(@RequestParam("boardId") int boardId, BindingResult result, HttpSession session, Model model) {                                      
		try {                                   
			Board board = boardService.selectArticle(boardId);
			if(session.getAttribute("userid") == board.getWriter()) {
				System.out.println("SSSSSSS");
				boardService.deleteArticle(board.getBoardId());                                                                               
				return "redirect:/board/cat/"+board.getCategoryId()+"/"+(Integer)session.getAttribute("page");                                
			} else {                                                                                                                          
				model.addAttribute("message", "Error");
				System.out.println("Error");
				return "error/runtime";                                                                                                       
			}                                                                                                                                 
		}catch(Exception e){                                                                                                                  
			model.addAttribute("message", e.getMessage());
			System.out.println("Catch");
			e.printStackTrace();                                                                                                              
			return "error/runtime";                                                                                                           
		}                                                                                                                                     
	}                                                                                                                                       
	
	@RequestMapping("/board/search/{page}")                                                                                                          
	public String search(@RequestParam("keyword")String keyword, @PathVariable int page, HttpSession session, Model model) {  
		System.out.println(keyword);
		try {                                     
			List<Board> boardList = boardService.searchListByContentKeyword(keyword, 1);                                                            
			model.addAttribute("boardList", boardList);                                                                                                
			System.out.println(boardList);  
			
			// paging start                                                                                                                            
			int bbsCount = boardService.selectTotalArticleCountByKeyword(keyword);                                                                     
			int totalPage = 0;                                                                                                                         
			System.out.println(bbsCount);                                                                                                              
			if(bbsCount > 0) {                                                                                                                         
				totalPage= (int)Math.ceil(bbsCount/10.0);                                                                                              
			}                                                                                                                                          
			model.addAttribute("totalPageCount", totalPage);                                                                                           
			model.addAttribute("page", page);                                                                                                          
			model.addAttribute("keyword", keyword);                                                                                                    
			logger.info(totalPage + ":" + page + ":" + keyword);                                                                                       
		} catch(Exception e) {                                                                                                                         
			e.printStackTrace();                                                                                                                       
		}                                                                                                                                              
		return "board/search";                                                                                                                         
	}

}
