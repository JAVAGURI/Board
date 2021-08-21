package com.joonho.portfolio.board.model.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joonho.portfolio.board.model.dto.BoardDTO;
import com.joonho.portfolio.board.model.dto.PageInfoDTO;
import com.joonho.portfolio.board.model.dto.SearchDTO;
import com.joonho.portfolio.board.model.service.BoardService;
import com.joonho.portfolio.common.PageNation;
import com.joonho.portfolio.member.model.dto.MemberDTO;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private BoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("list")
	public void BoardSelectList(Model model, @RequestParam(required = false) String currentPage,  HttpSession session, 
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* 프론트에서 넘어져오는 값 */
		
		/* 맨처음 페이지 클릭했을 때 보여줄 곳 = 1페이지     설정 안할 시 null로 아무것도 안뜬다.*/
		int pageNo = 1;
		
		/* 숫자 버튼 누를시 1페이지만 이동않도록 분기처리 (빈문자열만 아니면 된다) */
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		/* 0이하 정수는 1로 처리하도록 분기처리*/
		if(pageNo <= 0) {
			pageNo = 1;
		}
		
		/* 전체 게시물 수가 필요 */
		/* 데이터베이스에서 먼저 전체 게시물 수를 조회 */
		int totalCount = boardService.selectTotalCount();
		
		System.out.println("totalCount 체크 : " + totalCount);
		
		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10;
		/* 한 번에 보여질 페이징 버튼의 수*/
		int buttonAmount = 5;
		
		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
		PageInfoDTO pageInfo = PageNation.getPageInfo(pageNo, totalCount, limit, buttonAmount);
		
		System.out.println(pageInfo);
		
		/* 조회 해온다. */
		List<BoardDTO> boardList = boardService.selectBoardList(pageInfo);
		
		System.out.println("boardList : " + boardList);
		
		String path = "";
		if(boardList != null) {
			path = "/WEB-INF/views/board/boardList.jsp";
			request.setAttribute("boardList", boardList);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("loginMember", (MemberDTO)session.getAttribute("loginMember"));

		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "게시물 목록 조회 실패!");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	
	}
	
	@GetMapping("search")
	public String BoardSearchList(Model model, @RequestParam(required = false) String currentPage, @ModelAttribute SearchDTO searchDTO) throws ServletException, IOException {
		
		System.out.println(currentPage);
		System.out.println(searchDTO);
		
		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		if(pageNo <= 0) {
			pageNo = 1;
		}
		
					int totalCount = boardService.searchBoardCount(searchDTO);
		
		System.out.println("totalBoardCount : " + totalCount);
		
		int limit = 10;
		int buttonAmount = 5;
		
		PageInfoDTO pageInfo = PageNation.getPageInfo(pageNo, totalCount, limit, buttonAmount);
		
		System.out.println(pageInfo);
		
		Map<String, Object> search = new HashMap<>();
		search.put("searchDTO", searchDTO);
		search.put("pageInfo", pageInfo);
		
					List<BoardDTO> boardList = boardService.searchBoardList(search);
		
		System.out.println("boardList : " + boardList);
		
		String path = "";
		if(boardList != null) {
			path = "/WEB-INF/views/board/boardList.jsp";
			model.addAttribute("boardList", boardList);
			model.addAttribute("pageInfo", pageInfo);
			model.addAttribute("searchCondition", searchDTO.getCondition());
			model.addAttribute("searchValue", searchDTO.getValue());
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			model.addAttribute("message", "게시물 목록 조회 실패!");
		}
		
		return "forward:" + path;
	}
	
	@GetMapping("insert")
	public String BoardInsert(Model model) {
		return "board/insertForm";
	}
	
	@PostMapping("insert")
	public void BoardInsert(Model model, @ModelAttribute @Nullable BoardDTO newBoard
			, HttpSession session,  HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginMember");
		newBoard.setWriterMemberNo(memberDTO.getNo());
		
		int result = boardService.insertBoard(newBoard);
		
		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertBoard");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "게시판 작성에 실패하셨습니다.");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}
	
	
}
