//package com.greedy.jsp.board.controller;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.greedy.jsp.board.model.dto.BoardDTO;
//import com.greedy.jsp.board.model.service.BoardService;
//
//@WebServlet("/thumbnail/detail")
//public class ThumbnailDetailServlet extends HttpServlet {
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		int no = Integer.parseInt(request.getParameter("no"));
//		
//		BoardDTO thumbnail = new BoardServiceImpl().selectOneThumbnailBoard(no);
//		System.out.println(thumbnail);
//		
//		String path = "";
//		if(thumbnail != null) {
//			path = "/WEB-INF/views/thumbnail/thumbnailDetail.jsp";
//			request.setAttribute("thumbnail", thumbnail);
//		} else {
//			path = "/WEB-INF/views/common/failed.jsp";
//			request.setAttribute("message", "썸네일 게시판 상세 조회 실패!");
//		}
//		
//		request.getRequestDispatcher(path).forward(request, response);
//	}
//
//}
