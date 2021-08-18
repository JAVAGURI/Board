package com.joonho.portfolio.notice.model.service;

import java.util.List;

import com.joonho.portfolio.notice.model.dto.NoticeDTO;

public interface NoticeService {

	List<NoticeDTO> selectAllNoticeList();

	NoticeDTO selectNoticeDetail(int no);

	int insertNotice(NoticeDTO noticeDTO);

	int noticeUpdate(NoticeDTO noticeDTO);

	
}
