package com.joonho.portfolio.notice.model.dao;

import java.util.List;

import com.joonho.portfolio.notice.model.dto.NoticeDTO;

public interface NoticeMapper {

	List<NoticeDTO> selectAllNoticeList();

	int insertNotice(NoticeDTO newNotice);

	int incrementNoticeCount(int no);
	NoticeDTO selectNoticeDetail(int no);

	int noticeUpdate(NoticeDTO noticeDTO);


}
