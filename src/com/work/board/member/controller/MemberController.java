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
				// 아이디가 없을 경우
				model.addAttribute("message", "NOT_VALID_USER");
			} else {
				if (dbPassword.equals(password)) {
					// 로그인 성공
					session.setAttribute("userid", userid);
					session.setAttribute("admin", member.getAdmin());
					session.setAttribute("name", member.getName());
					session.setAttribute("email", member.getEmail());
					return "redirect:/board/cat/1/1";
				} else {
					// 비밀번호가 틀렸을 때
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

	// 아이디 중복체크
	// COUNT를 사용하여 아이디가 있을경우 1 / 아이디가 없을경우 0을 반환한다.
	@RequestMapping(value = "/member/check", method = RequestMethod.GET)
	@ResponseBody
	public int checkuserId(@RequestParam("userid") String userid) {
		int result = 0;
		result = memberService.idCheck(userid);
		return result;
	}
}
