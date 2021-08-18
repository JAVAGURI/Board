package com.joonho.portfolio.board.model.service;

import java.util.List;
import java.util.Map;

import com.joonho.portfolio.board.model.dto.AttachmentDTO;
import com.joonho.portfolio.board.model.dto.BoardDTO;
import com.joonho.portfolio.board.model.dto.PageInfoDTO;
import com.joonho.portfolio.board.model.dto.SearchDTO;

public interface BoardService {

	int selectTotalCount();
	List<BoardDTO> selectBoardList(PageInfoDTO pageInfo);
	
	int searchBoardCount(SearchDTO searchDTO);
	List<BoardDTO> searchBoardList(Map<String, Object> search);
	
	int insertBoard(BoardDTO newBoard);
	
	
	List<BoardDTO> selectThumbnailList();
	
	BoardDTO selectOneThumbnailBoard(int no);
	
	int thumbnailInsert(BoardDTO boardDTO);
	int insertAttachment(AttachmentDTO imgdto);

	
}
