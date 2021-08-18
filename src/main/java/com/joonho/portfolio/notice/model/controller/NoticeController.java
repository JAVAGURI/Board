package com.joonho.portfolio.notice.model.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joonho.portfolio.member.model.dto.MemberDTO;
import com.joonho.portfolio.notice.model.dto.NoticeDTO;
import com.joonho.portfolio.notice.model.service.NoticeService;

@Controller
@RequestMapping("/notice/*")
public class NoticeController {

		private NoticeService noticeService;

		public NoticeController(NoticeService noticeService) {
			this.noticeService = noticeService;
		}
		
	@GetMapping("list")
	public String NoticeSelectList(Model model, HttpSession session) {
		
		List<NoticeDTO> noticeList = noticeService.selectAllNoticeList();
		
		System.out.println(noticeList);
		
		String path = "";
		if(noticeList != null) {
			path = "/WEB-INF/views/notice/noticeList.jsp";
			model.addAttribute("noticeList", noticeList);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			model.addAttribute("message", "공지사항 조회 실패!");
		}
		
		return "forward:" + path;
	}
	
	@GetMapping("detail")
	public void NoticeSelectDetail(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = 0;
		
		if(model.getAttribute("no") != null) {
			no = (int) model.getAttribute("no");
		} else {
		    no = Integer.parseInt(request.getParameter("no"));
		}
		
		NoticeDTO noticeDetail = noticeService.selectNoticeDetail(no);
		
		System.out.println("noticeDetail : " + noticeDetail);
		
		String path = "";
		if(noticeDetail != null) {
			path = "/WEB-INF/views/notice/noticeDetail.jsp";
			request.setAttribute("notice", noticeDetail);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "공지사항 상세 보기 조회에 실패하였습니다.");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	@GetMapping("insert")
	public String NoticeInsert(Model model) {
		
		return "notice/insertForm";
	}
	
	@PostMapping("insert")
	public void NoticeInsert(Model model, @ModelAttribute NoticeDTO noticeDTO, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		noticeDTO.setWriter((MemberDTO) session.getAttribute("loginMember"));
		
		int result = noticeService.insertNotice(noticeDTO);
		
		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertNotice");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "공지사항 등록에 실패하셨습니다.");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}
		
	@GetMapping("update")
	public String NoticeUpdate(Model model,@RequestParam int no) throws ServletException, IOException {
		System.out.println("no: " + no);
		NoticeDTO notice = noticeService.selectNoticeDetail(no);
		
		String path = "";
		if(notice != null) {
			path = "/WEB-INF/views/notice/updateForm.jsp";
			model.addAttribute("notice", notice);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			model.addAttribute("message", "공지사항 수정용 조회하기 실패!");
		}
		
		return "forward:"+ path;
	}
		
	@PostMapping("update/{no}")
	public String NoticeUpdate(Model model, @ModelAttribute NoticeDTO noticeDTO , @PathVariable("no") int no) {
		System.out.println("===================");
		System.out.println(noticeDTO);
		System.out.println("no : " + no);
		int result = noticeService.noticeUpdate(noticeDTO);

		String path = "";
		if(result>0) {
//	안됨		path = "/WEB-INF/views/notice/detail?no="+ no +".jsp";
//			 TODO 수정후 detail어노테이션을 통한 창으로 가는 방법은 ?
//			path = r "/portfolio/notice/detail";
//			model.addAttribute("no",no);
			path = "/WEB-INF/views/common/success.jsp";
			model.addAttribute("successCode", "updateNotice");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			model.addAttribute("message", "공지사항 수정용 조회하기 실패!");
		}
		
		return "forward:"+ path;
	}
	
}
