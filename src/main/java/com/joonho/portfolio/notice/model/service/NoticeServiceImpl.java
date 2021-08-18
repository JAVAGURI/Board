package com.joonho.portfolio.notice.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.joonho.portfolio.notice.model.dao.NoticeMapper;
import com.joonho.portfolio.notice.model.dto.NoticeDTO;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	private final NoticeMapper mapper;
	
	public NoticeServiceImpl(NoticeMapper mapper) {
		this.mapper = mapper;
	}
	
	/* 공지사항 전체 목록 조회용 메소드 */
	public List<NoticeDTO> selectAllNoticeList() {
		
		List<NoticeDTO> noticeList = mapper.selectAllNoticeList();
		
		return noticeList;
	}
	
	/* 신규 공지 사항 등록용 메소드 */
	public int insertNotice(NoticeDTO newNotice) {
		
		int result = mapper.insertNotice(newNotice);
		
		return result;
	}
	
	/* 공지사항 상세보기용 메소드 */
	public NoticeDTO selectNoticeDetail(int no) {
		
		NoticeDTO noticeDetail = null;
		
		int result = mapper.incrementNoticeCount(no);
		
		if(result > 0) {
			noticeDetail = mapper.selectNoticeDetail(no);
		}
		
		return noticeDetail;
	}

	@Override
	public int noticeUpdate(NoticeDTO noticeDTO) {
		
		int result = mapper.noticeUpdate(noticeDTO);
		
		return result;
	}
	
}
