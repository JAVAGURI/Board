package com.joonho.portfolio.board.model.dao;

import java.util.List;
import java.util.Map;

import com.joonho.portfolio.board.model.dto.AttachmentDTO;
import com.joonho.portfolio.board.model.dto.BoardDTO;
import com.joonho.portfolio.board.model.dto.PageInfoDTO;
import com.joonho.portfolio.board.model.dto.SearchDTO;

public interface BoardMapper {

	int selectTotalCount();

	List<BoardDTO> selectBoardList(PageInfoDTO pageInfo);

	int insertBoard(BoardDTO newBoard);

	int searchBoardCount(SearchDTO searchDTO);

	List<BoardDTO> searchBoardList(Map<String, Object> search);

	List<BoardDTO> selectThumbnailList();
	
	int incrementBoardCount(int no);
	BoardDTO selectOneThumbnailBoard(int no);

	int insertThumbnailContent(BoardDTO boardDTO);
	int insertAttachment(AttachmentDTO imgdto);

}
