package com.joonho.portfolio.member.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joonho.portfolio.member.model.dto.MemberDTO;
import com.joonho.portfolio.member.model.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private final MemberService memberService;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public MemberController(MemberService memberService, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("login")
	public String memberLogin(Model model, @ModelAttribute MemberDTO memberDTO, HttpSession session) {
		
		MemberDTO loginMember = memberService.loginCheck(memberDTO);

//		해당하는 유저가 없을 경우의 분기처리 
//		if(loginMember != null) {
			session.setAttribute("loginMember", loginMember);
//			System.out.println("request.getContextPath() : " + request.getContextPath());
//			response.sendRedirect(request.getContextPath());
//		} else {
//			request.setAttribute("message", "로그인 실패!");
//			request.getRequestDispatcher("/WEB-INF/views/common/failed.jsp").forward(request, response);
//		}
		
		return "main/main";
	}
	
	@GetMapping("regist")
	public String MemberRegist() {
		return "member/registForm";
	}
	
	@PostMapping("regist")
	public void MemberRegist(Model model, @ModelAttribute @Nullable MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		memberDTO.setPhone(memberDTO.getPhone().replace("-", ""));
		memberDTO.setAddress(request.getParameter("zipCode") + "$" + request.getParameter("address1") + "$"
		         + request.getParameter("address2"));
		
		
		System.out.println(memberDTO);
		
		int result = memberService.registMember(memberDTO);
		
		System.out.println("memberController result : " + result);
		
		/* 로그인 성공 또는 실패 후 무언가를 더 띄워주고 싶어서 포워드방식으로 띄웠다.*/
		
		String page = "";
		
		if(result > 0) {
			
			page = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertMember");
			
		} else {
			page = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "회원 가입 실패!");
		}
		/* sendRedirect 방식은 전혀 문제가 되지 않는다. 이후에 성공실패에 관한 상태창띄워주고 싶기에 이렇게 경로를 지정하여 넘겨주었고
		 * "경로를 설정해서 넘겨주었으므로" 새로고침해도 중복되어 값이 넘어오지않는다. */
		request.getRequestDispatcher(page).forward(request, response);
		
//		return "common/success";
	}
	
	@GetMapping("update")
	public String updateMember(Model model, HttpSession session) {
		
		return "member/updateMemberForm";
	}
	
	
	@GetMapping("logout")
	public String MemberLogout(HttpSession session) {
		
		session.invalidate();
		
		return "main/main";
	}
	
	
}
