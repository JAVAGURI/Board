//package com.greedy.jsp.board.controller;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.greedy.jsp.board.model.dto.BoardDTO;
//import com.greedy.jsp.board.model.dto.PageInfoDTO;
//import com.greedy.jsp.board.model.service.BoardService;
//import com.greedy.jsp.common.paging.PageNation;
//
///**
// * Servlet implementation class BoardSearchListServlet
// */
//@WebServlet("/board/search")
//public class BoardSearchListServlet extends HttpServlet {
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		String condition = request.getParameter("searchCondition");
//		String value = request.getParameter("searchValue");
//		
//		/* 목록보기를 눌렀을 시 가장 처음에 보여지는 페이지는 1페이지이다.
//		 * 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로 전달받은 페이지 수 이다.
//		 * */
//		String currentPage = request.getParameter("currentPage");
//		int pageNo = 1;
//		
//		if(currentPage != null && !"".equals(currentPage)) {
//			pageNo = Integer.parseInt(currentPage);
//		}
//		
//		/* 0보다 작은 숫자값을 입력해도 1페이지를 보여준다 */
//		if(pageNo <= 0) {
//			pageNo = 1;
//		}
//		
//		/* 전체 게시물 수가 필요하다.
//		 * 데이터베이스에서 먼저 전체 게시물 수를 조회해올 것이다.
//		 * */
//		BoardServiceImpl boardService = new BoardServiceImpl();
//		int totalCount = boardService.searchBoardCount(condition, value);
//		
//		System.out.println("totalBoardCount : " + totalCount);
//		
//		/* 한 페이지에 보여 줄 게시물 수 */
//		int limit = 10;		//얘도 파라미터로 전달받아도 된다.
//		/* 한 번에 보여질 페이징 버튼의 갯수 */
//		int buttonAmount = 5;
//		
//		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
//		PageInfoDTO pageInfo = PageNation.getPageInfo(pageNo, totalCount, limit, buttonAmount);
//		
//		System.out.println(pageInfo);
//		
//		/* 조회해온다 */
//		List<BoardDTO> boardList = boardService.searchBoardList(condition, value, pageInfo);
//		
//		System.out.println("boardList : " + boardList);
//		
//		String path = "";
//		if(boardList != null) {
//			path = "/WEB-INF/views/board/boardList.jsp";
//			request.setAttribute("boardList", boardList);
//			request.setAttribute("pageInfo", pageInfo);
//			request.setAttribute("searchCondition", condition);
//			request.setAttribute("searchValue", value);
//		} else {
//			path = "/WEB-INF/views/common/failed.jsp";
//			request.setAttribute("message", "게시물 목록 조회 실패!");
//		}
//		
//		request.getRequestDispatcher(path).forward(request, response);
//	}
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		doGet(request, response);
//	}
//
//}
