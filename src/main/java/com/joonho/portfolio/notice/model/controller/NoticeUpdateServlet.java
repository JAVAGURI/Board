//package com.greedy.jsp.notice.controller;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.greedy.jsp.notice.model.dto.NoticeDTO;
//import com.greedy.jsp.notice.model.service.NoticeService;
//
//@WebServlet("/notice/update")
//public class NoticeUpdateServlet extends HttpServlet {
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		int no = Integer.parseInt(request.getParameter("no"));
//		
//		NoticeService noticeService = new NoticeService();
//		NoticeDTO notice = noticeService.selectNoticeDetail(no);
//		
//		String path = "";
//		if(notice != null) {
//			path = "/WEB-INF/views/notice/updateForm.jsp";
//			request.setAttribute("notice", notice);
//		} else {
//			path = "/WEB-INF/views/common/failed.jsp";
//			request.setAttribute("message", "공지사항 수정용 조회하기 실패!");
//		}
//		
//		request.getRequestDispatcher(path).forward(request, response);
//		
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//	}
//
//}
