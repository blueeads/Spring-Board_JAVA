package com.work.board.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.work.board.board.controller.BoardController;
import com.work.board.member.model.Member;
import com.work.board.member.service.IMemberService;

@Controller
public class MemberController {
	// static final Logger logger = Logger.getLogger(MemberController.class);
	static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	IMemberService memberService;

	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	public String joinForm() {
		return "member/form";
	}

	@RequestMapping(value = "/member/insert", method = RequestMethod.POST)
	public String memberInsert(Member member, Model model, HttpSession session) {
		memberService.insertMember(member);
		session.invalidate();
		return "member/login";
	}

	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login() {
		return "member/login";
	}

	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String login(String userid, String password, HttpSession session, Model model, RedirectAttributes redirectAttrs) {
		Member member = memberService.selectMember(userid);
		if (member != null) {
			String dbPassword = member.getPassword();
			if (dbPassword == null) {
				// 아이디가 틀리면
				model.addAttribute("message", "NOT_VALID_USER");
			} else {
				if (dbPassword.equals(password)) {
					// 정상적으로 로그인
					session.setAttribute("userid", userid);
					session.setAttribute("admin", member.getAdmin());
					session.setAttribute("name", member.getName());
					session.setAttribute("email", member.getEmail());
					return "redirect:/board/cat/1/1";
				} else {
					// 비밀번호가 없을경우
					model.addAttribute("message", "WRONG_PASSWORD");
				}
			}
		} else {
			model.addAttribute("message", "USER_NOT_FOUND");
		}
		session.invalidate();
		return "member/login";
	}

	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request) {
		session.invalidate();
		return "member/login";
	}

	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public String updateMember(HttpSession session, Model model) {
		String userid = (String) session.getAttribute("userid");
		if (userid != null && !userid.equals("")) {
			Member member = memberService.selectMember(userid);
			model.addAttribute("member", member);
			model.addAttribute("message", "UPDATE_USER_INFO");
			return "member/update";
		} else {
			model.addAttribute("message", "NOT_LOGIN_USER");
			return "member/login";
		}
	}

	@RequestMapping(value = "/member/update", method = RequestMethod.POST)
	public String updateMember(Member member, HttpSession session, Model model) {
		try {
			memberService.updateMember(member);
			model.addAttribute("message", "UPDATED_MEMBER_INFO");
			model.addAttribute("member", member);
			session.setAttribute("email", member.getEmail());
			return "member/login";
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
			return "member/error";
		}
	}

	
	//怨꾩젙 �궘�젣 �뤌 �씠�룞
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String deleteMember(HttpSession session, Model model) {
		String userid = (String) session.getAttribute("userid");
		if (userid != null && !userid.equals("")) {
			Member member = memberService.selectMember(userid);
			model.addAttribute("member", member);
			model.addAttribute("message", "MEMBER_PW_CHECK");
			return "member/delete";
		} else {
			model.addAttribute("message", "NOT_LOGIN_USER");
			return "member/login";
		}
	}

	
	//
	@RequestMapping(value = "/member/delete", method = RequestMethod.POST)
	public String deleteMember(String password, HttpSession session, Model model) {
		try {
			Member member = new Member();
			member.setUserid((String) session.getAttribute("userid"));
			String dbpw = memberService.getPassword(member.getUserid());
			if (password != null && password.equals(dbpw)) {
				member.setPassword(password);
				memberService.deleteMember(member);
				session.invalidate();
				return "member/login";
			} else {
				model.addAttribute("message", "WRONG_PASSWORD");
				return "member/delete";
			}
		} catch (Exception e) {
			model.addAttribute("message", "DELETE_FAIL");
			e.printStackTrace();
			return "member/delete";
		}
	}

	// 아이디 중복 체크
	// COUNT를 사용해여 있으면 1 없으면 0을 반환한다.
	@RequestMapping(value = "/member/check", method = RequestMethod.GET)
	@ResponseBody
	public int checkuserId(@RequestParam("userid") String userid) {
		int result = 0;
		result = memberService.idCheck(userid);
		return result;
	}
}
=======
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.work.board.board.controller.BoardController;
import com.work.board.member.model.Member;
import com.work.board.member.service.IMemberService;

@Controller
public class MemberController {
	// static final Logger logger = Logger.getLogger(MemberController.class);
	static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	IMemberService memberService;

	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	public String joinForm() {
		return "member/form";
	}

	@RequestMapping(value = "/member/insert", method = RequestMethod.POST)
	public String memberInsert(Member member, Model model, HttpSession session) {
		memberService.insertMember(member);
		session.invalidate();
		return "member/login";
	}

	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login() {
		return "member/login";
	}

	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String login(String userid, String password, HttpSession session, Model model, RedirectAttributes redirectAttrs) {
		Member member = memberService.selectMember(userid);
		if (member != null) {
			String dbPassword = member.getPassword();
			if (dbPassword == null) {
				// �븘�씠�뵒媛� �뾾�쓬
				model.addAttribute("message", "NOT_VALID_USER");
			} else {
				// �븘�씠�뵒 �엳�쓬
				if (dbPassword.equals(password)) {
					// 鍮꾨�踰덊샇 �씪移�
					session.setAttribute("userid", userid);
					session.setAttribute("admin", member.getAdmin());
					session.setAttribute("name", member.getName());
					session.setAttribute("email", member.getEmail());
					return "redirect:/board/cat/1/1";
				} else {
					// 鍮꾨�踰덊샇 遺덉씪移�
					model.addAttribute("message", "WRONG_PASSWORD");
				}
			}
		} else {
			model.addAttribute("message", "USER_NOT_FOUND");
		}
		session.invalidate();
		return "member/login";
	}

	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request) {
		session.invalidate();
		return "member/login";
	}

	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public String updateMember(HttpSession session, Model model) {
		String userid = (String) session.getAttribute("userid");
		if (userid != null && !userid.equals("")) {
			Member member = memberService.selectMember(userid);
			model.addAttribute("member", member);
			model.addAttribute("message", "UPDATE_USER_INFO");
			return "member/update";
		} else {
			model.addAttribute("message", "NOT_LOGIN_USER");
			return "member/login";
		}
	}

	@RequestMapping(value = "/member/update", method = RequestMethod.POST)
	public String updateMember(Member member, HttpSession session, Model model) {
		try {
			memberService.updateMember(member);
			model.addAttribute("message", "UPDATED_MEMBER_INFO");
			model.addAttribute("member", member);
			session.setAttribute("email", member.getEmail());
			return "member/login";
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
			return "member/error";
		}
	}

	
	//계정 삭제 폼 이동
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String deleteMember(HttpSession session, Model model) {
		String userid = (String) session.getAttribute("userid");
		if (userid != null && !userid.equals("")) {
			Member member = memberService.selectMember(userid);
			model.addAttribute("member", member);
			model.addAttribute("message", "MEMBER_PW_CHECK");
			return "member/delete";
		} else {
			model.addAttribute("message", "NOT_LOGIN_USER");
			return "member/login";
		}
	}

	
	//계정 삭제
	@RequestMapping(value = "/member/delete", method = RequestMethod.POST)
	public String deleteMember(String password, HttpSession session, Model model) {
		try {
			Member member = new Member();
			member.setUserid((String) session.getAttribute("userid"));
			String dbpw = memberService.getPassword(member.getUserid());
			if (password != null && password.equals(dbpw)) {
				member.setPassword(password);
				memberService.deleteMember(member);
				session.invalidate();
				return "member/login";
			} else {
				model.addAttribute("message", "WRONG_PASSWORD");
				return "member/delete";
			}
		} catch (Exception e) {
			model.addAttribute("message", "DELETE_FAIL");
			e.printStackTrace();
			return "member/delete";
		}
	}

	//아이디 중복 확인
	@RequestMapping(value = "/member/check", method = RequestMethod.GET)
	@ResponseBody
	public int checkuserId(@RequestParam("userid") String userid) {
		int result = 0;
		result = memberService.idCheck(userid);
		return result;
	}
}
=======

import com.work.board.board.controller.BoardController;
import com.work.board.member.model.Member;
import com.work.board.member.service.IMemberService;

@Controller
public class MemberController {
	//static final Logger logger = Logger.getLogger(MemberController.class);
	static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	IMemberService memberService;

	@RequestMapping(value="/member/insert", method=RequestMethod.GET)
	public String joinForm() {
		return "member/form";
	}
	 
	@RequestMapping(value="/member/insert", method=RequestMethod.POST)
	public String memberInsert(Member member, Model model, HttpSession session) {
		memberService.insertMember(member);
		session.invalidate();
		return "member/login";
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.GET)
	public String login() {
		return "member/login";
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String login(String userid, String password, HttpSession session, Model model) {
		Member member = memberService.selectMember(userid);
		if(member != null) {
			String dbPassword = member.getPassword();
				if(dbPassword == null) {
				//�븘�씠�뵒媛� �뾾�쓬
				model.addAttribute("message", "NOT_VALID_USER");
			}else {
				//�븘�씠�뵒 �엳�쓬
				if(dbPassword.equals(password)) {
					//鍮꾨�踰덊샇 �씪移�
					session.setAttribute("userid", userid);
					session.setAttribute("name", member.getName());
					session.setAttribute("email", member.getEmail());
						return "member/login";
				}else {
					//鍮꾨�踰덊샇 遺덉씪移�
					model.addAttribute("message", "WRONG_PASSWORD");
				}
			}
		}else {
			model.addAttribute("message", "USER_NOT_FOUND");
		}
		session.invalidate();	
		return "member/login";
	}
	
	@RequestMapping(value="/member/logout", method=RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request) {
		session.invalidate(); 
		return "member/login";
	}
	
	@RequestMapping(value="/member/update", method=RequestMethod.GET)
	public String updateMember(HttpSession session, Model model) {
		String userid = (String)session.getAttribute("userid");
		if(userid != null && !userid.equals("")) {
			Member member = memberService.selectMember(userid);
			model.addAttribute("member", member);
			model.addAttribute("message", "UPDATE_USER_INFO");
			return "member/update";
		}else {
			model.addAttribute("message", "NOT_LOGIN_USER");
			return "member/login";
		}
	}
	
	@RequestMapping(value="/member/update", method=RequestMethod.POST)
	public String updateMember(Member member, HttpSession session, Model model) {
		try{
			memberService.updateMember(member);
			model.addAttribute("message", "UPDATED_MEMBER_INFO");
			model.addAttribute("member", member);
			session.setAttribute("email", member.getEmail());
			return "member/login";
		}catch(Exception e){
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
			return "member/error";
		}
	}
	
	@RequestMapping(value="/member/delete", method=RequestMethod.GET)
	public String deleteMember(HttpSession session, Model model) {
		String userid = (String)session.getAttribute("userid");
		if(userid != null && !userid.equals("")) {
			Member member = memberService.selectMember(userid);
			model.addAttribute("member", member);
			model.addAttribute("message", "MEMBER_PW_RE");
			return "member/delete";
		}else {
			model.addAttribute("message", "NOT_LOGIN_USER");
			return "member/login";
		}
	}
	
	@RequestMapping(value="/member/delete", method=RequestMethod.POST)
	public String deleteMember(String password, HttpSession session, Model model) {
		try {
			Member member = new Member();
			member.setUserid((String)session.getAttribute("userid"));
			String dbpw = memberService.getPassword(member.getUserid());
			if(password != null && password.equals(dbpw)) {
				member.setPassword(password);
				memberService.deleteMember(member);
				session.invalidate();
				return "member/login";
			}else {
				model.addAttribute("message", "WRONG_PASSWORD");
				return "member/delete";
			}
		}catch(Exception e){
			model.addAttribute("message", "DELETE_FAIL");
			e.printStackTrace();
			return "member/delete";
		}
	}
}

>>>>>>> refs/remotes/origin/master
>>>>>>> refs/remotes/origin/master
