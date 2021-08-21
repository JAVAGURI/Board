package com.joonho.portfolio.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joonho.portfolio.board.model.dao.BoardMapper;
import com.joonho.portfolio.board.model.dto.AttachmentDTO;
import com.joonho.portfolio.board.model.dto.BoardDTO;
import com.joonho.portfolio.board.model.dto.PageInfoDTO;
import com.joonho.portfolio.board.model.dto.SearchDTO;

@Service
public class BoardServiceImpl implements BoardService{

	private final BoardMapper mapper;
	
	public BoardServiceImpl(BoardMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * 페이징 처리를 위한 전체 게시물 수 조회용 메소드
	 * @return
	 */
	public int selectTotalCount() {
		
		int totalCount = mapper.selectTotalCount();
		
		return totalCount;
	}

	/**
	 * 게시물 전체 조회용 메소드
	 * @param pageInfo
	 * @return
	 */
	public List<BoardDTO> selectBoardList(PageInfoDTO pageInfo) {
	
		List<BoardDTO> boardList = mapper.selectBoardList(pageInfo);
		
		return boardList;
	}

	/**
	 * 게시물 추가용 메소드
	 * @param newBoard
	 * @return
	 */
	public int insertBoard(BoardDTO newBoard) {
		
		int result = mapper.insertBoard(newBoard);
		
		return result;
	}
	
	/**
	 * Thumbnail 추가용 메소드
	 * @param thumbnail
	 * @return
	 */
//	public int insertThumbnail(BoardDTO thumbnail) {
//		
//		/* 최종적으로 반환할 result 선언 */
//		int result = 0;
//		
//		/* 먼저 board 테이블부터 thumbnail 내용부터 insert 한다. */
//		int boardResult = mapper.insertThumbnailContent(thumbnail);
//		
//		/* 방금 insert 한 board 테이블의 sequence를 조회한다. */
//		int boardNo = mapper.selectThumbnailSequence();
//		
//		/* Attachment 리스트를 불러온다. */
//		List<AttachmentDTO> fileList = thumbnail.getAttachmentList();
//		
//		/* fileList에 boardNo값을 넣는다. */
//		for(int i = 0; i < fileList.size(); i++) {
//			fileList.get(i).setRefBoardNo(boardNo);
//		}
//		
//		/* Attachment 테이블에 list size만큼 insert 한다. */
//		int attachmentResult = 0;
//		for(int i = 0; i < fileList.size(); i++) {
//			attachmentResult += mapper.insertAttachment(fileList.get(i));
//		}
//		
//		/* 수행 결과를 리턴한다. */
//		return result;
//	}
	
	/**
	 * Thumbnail 게시판 조회용 메소드
	 * @return
	 */
	public List<BoardDTO> selectThumbnailList() {
		
		/* List 조회 */
		List<BoardDTO> thumbnailList = mapper.selectThumbnailList();
		
		/* 조회 결과 반환 */
		return thumbnailList;
	}
	
	
	/**
	 * 게시판 검색 결과 갯수 조회용 메소드
	 * @param condition 검색조건
	 * @param value 값
	 * @return
	 */
	public int searchBoardCount(SearchDTO search) {
		
		int totalCount = mapper.searchBoardCount(search);
		
		return totalCount;
	}
	
	/**
	 * 게시판 검색 결과 조회용 메소드(페이징처리)
	 * @param condition 검색조건
	 * @param value 검색값
	 * @param pageInfo 페이지정보
	 * @return
	 */
	@Override
	public List<BoardDTO> searchBoardList(Map<String, Object> search) {
		
		List<BoardDTO> boardList = mapper.searchBoardList(search);
		
		return boardList;
	}
	
	/**
	 * Thumbnail 상세보기
	 * @param no
	 * @return
	 */
	public BoardDTO selectOneThumbnailBoard(int no) {
		
		BoardDTO thumbnail= null;
		
		int result = mapper.incrementBoardCount(no);
		
		if(result > 0) {
			thumbnail = mapper.selectOneThumbnailBoard(no);
		} 
		
		return thumbnail;
		
	}

	@Override
	public int thumbnailInsert(BoardDTO boardDTO) {
		
		int result = mapper.insertThumbnailContent(boardDTO);
		
		return result;
	}

	@Override
	public int insertAttachment(AttachmentDTO imgdto) {

		int result = mapper.insertAttachment(imgdto);
		
		return result;
	}

	
}
