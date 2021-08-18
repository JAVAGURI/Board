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
// * Servlet implementation class BoardSelectListServlet
// */
//@WebServlet("/board/list")
//public class BoardSelectListServlet extends HttpServlet {
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		/* 프론트에서 넘어져오는 값 */
//		String currentPage = request.getParameter("currentPage");
//		/* 맨처음 페이지 클릭했을 때 보여줄 곳 = 1페이지     설정 안할 시 null로 아무것도 안뜬다.*/
//		int pageNo = 1;
//		
//		/* 숫자 버튼 누를시 1페이지만 이동않도록 분기처리 (빈문자열만 아니면 된다) */
//		if(currentPage != null && !"".equals(currentPage)) {
//			pageNo = Integer.parseInt(currentPage);
//		}
//		
//		/* 0이하 정수는 1로 처리하도록 분기처리*/
//		if(pageNo <= 0) {
//			pageNo = 1;
//		}
//		
//		/* 전체 게시물 수가 필요 */
//		/* 데이터베이스에서 먼저 전체 게시물 수를 조회 */
//		BoardServiceImpl boardService = new BoardServiceImpl();
//		int totalCount = boardService.selectTotalCount();
//		
//		System.out.println("totalCount 체크 : " + totalCount);
//		
//		/* 한 페이지에 보여 줄 게시물 수 */
//		int limit = 10;
//		/* 한 번에 보여질 페이징 버튼의 수*/
//		int buttonAmount = 5;
//		
//		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
//		PageInfoDTO pageInfo = PageNation.getPageInfo(pageNo, totalCount, limit, buttonAmount);
//		
//		System.out.println(pageInfo);
//		
//		/* 조회 해온다. */
//		List<BoardDTO> boardList = boardService.selectBoardList(pageInfo);
//		
//		System.out.println("boardList : " + boardList);
//		
//		String path = "";
//		if(boardList != null) {
//			path = "/WEB-INF/views/board/boardList.jsp";
//			request.setAttribute("boardList", boardList);
//			request.setAttribute("pageInfo", pageInfo);
//		} else {
//			path = "/WEB-INF/views/common/failed.jsp";
//			request.setAttribute("message", "게시물 목록 조회 실패!");
//		}
//		
//		request.getRequestDispatcher(path).forward(request, response);
//	}
//
//}
