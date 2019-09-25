package com.work.board.board.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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

	//게시글 리스트
	@RequestMapping(value="/board/cat/{categoryId}/{page}" ,  produces = "application/json; charset=utf8")
	public String getListByCategory(@PathVariable int categoryId, @PathVariable int page, HttpSession session,
			Model model) {
		session.setAttribute("page", page);
		model.addAttribute("categoryId", categoryId);

		List<Board> boardList = boardService.selectArticleListByCategory(categoryId, page, 10);
		model.addAttribute("boardList", boardList);

		// paging start
		int bbsCount = boardService.selectTotalArticleCountByCategoryId(categoryId);
		int totalPage = 0;
		if (bbsCount > 0) {
			totalPage = (int) Math.ceil(bbsCount / 10.0);
		}
		model.addAttribute("totalPageCount", totalPage);
		model.addAttribute("page", page);
		return "board/list";
	}

	//게시글 페이지 카운트
	@RequestMapping(value = "/board/paging", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int getListByPage(@RequestParam("categoryId") int categoryId, HttpSession session, Model model) {
		model.addAttribute("categoryId", categoryId);

		int bbsCount = boardService.selectTotalArticleCountByCategoryId(categoryId);
		int totalPage = 0;
		if (bbsCount > 0) {
			totalPage = (int) Math.ceil(bbsCount / 10.0);
		}

		model.addAttribute("totalPageCount", totalPage);
		return totalPage;
	}

	@RequestMapping(value="/board/cat/{categoryId}",  produces = "application/json; charset=utf8")
	public String getListByCategory(@PathVariable int categoryId, HttpSession session, Model model) {
		return getListByCategory(categoryId, 1, session, model);
	}

	//게시글 리스트 json으로 넘김
	@RequestMapping(value = "/board/boardlist/{categoryId}/{page}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf8")
	@ResponseBody
	public String getList(@PathVariable int categoryId, @PathVariable int page) {
		List<Board> List = boardService.selectArticleListByCategory(categoryId, page, 10);

		String str = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			str = mapper.writeValueAsString(List);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}

	@RequestMapping(value="/board/{boardId}/{page}")
	public String getBoardDetails(@PathVariable int boardId, @PathVariable int page, Model model) {
		Board board = boardService.selectArticle(boardId);
		List<BoardUploadFile> fileList = boardService.getFileList(boardId);
		System.out.println(fileList);
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		model.addAttribute("");
		model.addAttribute("categoryId", board.getCategoryId());
		model.addAttribute("fileList", fileList);
		
		logger.info("getBoardDetails " + board.toString());
		return "board/view";
	}

	@RequestMapping("/board/{boardId}")
	public String getBoardDetails(@PathVariable int boardId, Model model) {
		return getBoardDetails(boardId, 1, model);
	}

	@RequestMapping(value = "/board/write/{categoryId}", method = RequestMethod.GET)
	public String writeArticle(@PathVariable int categoryId, Model model) {
		List<BoardCategory> categoryList = categoryService.selectAllCategory();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("categoryId", categoryId);
		return "board/write";
	}

	@RequestMapping(value = "/board/write", method = RequestMethod.POST , produces = "application/json; charset=utf8")
	public String writeArticle(Board board, BindingResult result, RedirectAttributes redirectAttrs) {

		logger.info("/board/write : " + board.toString());
		try {
			board.setTitle(Jsoup.clean(board.getTitle(), Whitelist.basic()));
			board.setContent(Jsoup.clean(board.getContent(), Whitelist.basic()));
			List<MultipartFile> mfile = board.getFile();
			boardService.insertArticle(board);
			
			if (mfile != null && !mfile.isEmpty()) {
				for (MultipartFile mf : mfile) {
					BoardUploadFile file = new BoardUploadFile();
					file.setFileName(getEncodedFileName(mf.getOriginalFilename(), "Chrome"));
					file.setFileOriginalName(mf.getOriginalFilename());
					file.setFileSize(mf.getSize());
					file.setFileContentType(mf.getContentType());
					file.setFileData(mf.getBytes());
					logger.info("/board/write : " + file.toString());
					boardService.insertFile(board.getBoardId(), file);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/board/cat/" + board.getCategoryId();
	}

	@RequestMapping(value="/file/write/", method=RequestMethod.POST)
	@ResponseBody
	public String requestupload(MultipartHttpServletRequest mtfRequest) {
		List<MultipartFile> fileList = mtfRequest.getFiles("file");
		String path = "/";
		for (MultipartFile mf : fileList) {
			String originFileName = mf.getOriginalFilename(); // 占쎌뜚癰귨옙 占쎈솁占쎌뵬 筌륅옙
			long fileSize = mf.getSize(); // 占쎈솁占쎌뵬 占쎄텢占쎌뵠筌앾옙
			
			System.out.println("originFileName : " + originFileName);
			System.out.println("fileSize : " + fileSize);

			String safeFile = path + System.currentTimeMillis() + originFileName;
			System.out.println(safeFile);
			try {
				mf.transferTo(new File(safeFile));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return "success";
	}
	
	@RequestMapping(value = "/board/update/{boardId}", method = RequestMethod.GET)
	public String updateArticle(@PathVariable int boardId, Model model) {
		List<BoardCategory> categoryList = categoryService.selectAllCategory();
		model.addAttribute("categoryList", categoryList);
		Board board = boardService.selectArticle(boardId);
		List<BoardUploadFile> file = boardService.getFileList(boardId);
		model.addAttribute("file", file);
		boardService.deleteFile(boardId);
		model.addAttribute("categoryId", board.getCategoryId());
		model.addAttribute("board", board);
		return "board/update";
	}

	@RequestMapping(value = "/board/update", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public String updateArticle(Board board, BindingResult result, HttpSession session,
			RedirectAttributes redirectAttrs) {
		logger.info("/board/update " + board.toString());
		try {
			board.setTitle(Jsoup.clean(board.getTitle(), Whitelist.basic()));
			board.setContent(Jsoup.clean(board.getContent(), Whitelist.basic()));
			List<MultipartFile> mfile = board.getFile();
			boardService.updateArticle(board);
			if (mfile != null && !mfile.isEmpty()) {
				for (MultipartFile mf : mfile) {
				logger.info("/board/update : " + mf.getOriginalFilename());
				BoardUploadFile file = new BoardUploadFile();
				file.setFileName(getEncodedFileName(mf.getOriginalFilename(), "Chrome"));
				file.setFileOriginalName(mf.getOriginalFilename());
				file.setFileSize(mf.getSize());
				file.setFileContentType(mf.getContentType());
				file.setFileData(mf.getBytes());
				logger.info("/board/update : " + file.toString());
				boardService.insertFile(board.getBoardId(), file);
				}
			} else {
				boardService.updateArticle(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}

		return "redirect:/board/" + board.getBoardId();
	}

	@RequestMapping(value = "/board/delete/", method = RequestMethod.GET)
	public String deleteArticle(@PathVariable int boardId, Model model) {
		Board board = boardService.selectDeleteArticle(boardId);
		System.out.println("SBoard : " + board);
		model.addAttribute("categoryId", board.getCategoryId());
		model.addAttribute("boardId", boardId);
		System.out.println(board);
		return "board/delete";
	}

	@RequestMapping(value = "/board/delete/{boardId}", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String deleteArticle(@PathVariable int boardId, HttpServletRequest request) {
		try {
			System.out.println("게시글 Delete :" + boardId);
			boardService.deleteArticle(boardId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping("/board/search/{page}")
	public String search(@RequestParam("keyword") String keyword, @PathVariable int page, HttpSession session,
			Model model) {
		try {
			List<Board> boardList = boardService.searchListByContentKeyword(keyword, 1);
			model.addAttribute("boardList", boardList);

			// paging start
			int bbsCount = boardService.selectTotalArticleCountByKeyword(keyword);
			int totalPage = 0;
			if (bbsCount > 0) {
				totalPage = (int) Math.ceil(bbsCount / 10.0);
			}
			model.addAttribute("totalPageCount", totalPage);
			model.addAttribute("page", page);
			model.addAttribute("keyword", keyword);
			logger.info(totalPage + ":" + page + ":" + keyword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "board/search";
	}

	@RequestMapping(value="/file/{fileId}", produces = "application/json; charset=utf8")
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

	//댓글 페이지 개수 구하기
	@RequestMapping(value = "/reply/paging", method = RequestMethod.POST)
	@ResponseBody
	public int getReplyByPage(@RequestParam("boardId") int boardId, HttpSession session, Model model) {

		int bbsCount = replyService.ReCount(boardId);
		int totalPage = 0;
		if (bbsCount > 0) {
			totalPage = (int) Math.ceil(bbsCount / 5.0);
		}

		model.addAttribute("totalPageCount", totalPage);
		return totalPage;
	}

	@RequestMapping(value = "/reply/getboardReply/{boardId}/{page}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf8")
	@ResponseBody
	public String getcommentList(@PathVariable int boardId, @PathVariable int page, HttpServletRequest request)
			throws Exception {
		List<BoardReply> List = replyService.commentList(boardId, page);

		String str = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			str = mapper.writeValueAsString(List);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}

	// 댓글 작성
	@RequestMapping(value = "/reply/view/", method = { RequestMethod.GET, RequestMethod.POST })
	public String CommentServiceInsert(@RequestParam int boardId, @RequestParam String content, @RequestParam String writer,
			HttpServletRequest request, RedirectAttributes redirectAttrs) throws Exception {
		try {
			BoardReply reply = new BoardReply();
			reply.setboardId(boardId);
			reply.setContent(content);
			reply.setWriter(writer);
			replyService.ReplyInsertService(reply);
			return"redirect:/board/"+boardId;
			//return"OK";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return"redirect:/board/"+boardId;
	}

	@RequestMapping(value = "/reply/update", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	private String CommentServiceUpdateProc(@RequestParam("commentId") int commentId, @RequestParam("content") String content,
			@RequestParam("boardId") int boardId, HttpServletRequest request, RedirectAttributes redirectAttrs) throws Exception {
		BoardReply reply = new BoardReply();
		reply.setcommentId(commentId);
		reply.setContent(content);
		replyService.ReplyUpdateService(reply);
		return "redirect:/board/" + boardId;
	}

	@RequestMapping(value = "/reply/delete/{commentId}", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	private String CommentServiceDelete(@RequestParam("commentId") int commentId, @RequestParam("boardId") int boardId,
			HttpServletRequest request, RedirectAttributes redirectAttrs) throws Exception {
		replyService.ReplyDeleteService(commentId);
		return "redirect:/board/" + boardId;
	}

	
	//Open source	// 파일 인코딩
    public static String getEncodedFileName(String filename, String browser) throws Exception {
        String encodedFilename = null;
        if (browser.equals("MSIE")) {
             encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        } else if (browser.equals("Firefox")) {
             encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Opera")) {
             encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Chrome")) {
           StringBuffer sb = new StringBuffer();
           for (int i = 0; i < filename.length(); i++) {
                       char c = filename.charAt(i);
                       if (c > '~') {
                                  sb.append(URLEncoder.encode("" + c, "UTF-8"));
                       } else { 
                               sb.append(c);
                       }
           }
           encodedFilename = sb.toString();
        } else {
           throw new RuntimeException("Not supported browser");
        }
        return encodedFilename;
 }
	
}
