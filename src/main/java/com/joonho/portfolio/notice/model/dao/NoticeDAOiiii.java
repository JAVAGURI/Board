//package com.joonho.portfolio.notice.model.dao;
//
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
//import com.joonho.portfolio.member.model.dto.MemberDTO;
//import com.joonho.portfolio.notice.model.dto.NoticeDTO;
//
//public class NoticeDAOiiii {
//	
//	private final Properties prop;
//	
//	public NoticeDAOiiii() {
//		
//		prop = new Properties();
//		
//		try {
//			prop.loadFromXML(new FileInputStream(ConfigLocation.MAPPER_LOCATION + "notice/notice-mapper.xml"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/* 공지사항 목록 전체 조회용 메소드 */
//	public List<NoticeDTO> selectAllNoticeList(Connection con) {
//		
//		Statement stmt = null;
//		ResultSet rset = null;
//		
//		List<NoticeDTO> noticeList = null;
//		
//		String query = prop.getProperty("selectAllNoticeList");
//		
//		try {
//			stmt = con.createStatement();
//			rset = stmt.executeQuery(query);
//			
//			noticeList = new ArrayList<>();
//			
//			while(rset.next()) {
//				
//				NoticeDTO notice = new NoticeDTO();
//				notice.setWriter(new MemberDTO());
//				
//				notice.setNo(rset.getInt("NOTICE_NO"));
//				notice.setTitle(rset.getString("NOTICE_TITLE"));
//				notice.setBody(rset.getString("NOTICE_BODY"));
//				notice.setWriterMemberNo(rset.getInt("NOTICE_WRITER_MEMBER_NO"));
//				notice.getWriter().setNickname(rset.getString("NICKNAME"));
//				notice.setCount(rset.getInt("NOTICE_COUNT"));
//				notice.setCreatedDate(rset.getDate("CREATED_DATE"));
//				
//				noticeList.add(notice);
//
//			}
//		
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rset);
//			close(stmt);
//		}
//		
//		return noticeList;
//	}
//	
//	/* 공지사항 테이블 삽입용 메소드 */
//	public int insertNotice(Connection con, NoticeDTO newNotice) {
//		
//		PreparedStatement pstmt = null;
//		
//		int result = 0;
//		
//		String query = prop.getProperty("insertNotice");
//		
//		try {
//			pstmt = con.prepareStatement(query);
//			pstmt.setString(1, newNotice.getTitle());
//			pstmt.setString(2, newNotice.getBody());
//			pstmt.setInt(3, newNotice.getWriterMemberNo());
//			
//			result = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		
//		return result;
//	}
//	
//	/* 공지사항 조회수 증가용 메소드 */
//	public int incrementNoticeCount(Connection con, int no) {
//		
//		PreparedStatement pstmt = null;
//		
//		int result = 0;
//		
//		String query = prop.getProperty("incrementNoticeCount");
//		
//		try {
//			pstmt = con.prepareStatement(query);
//			pstmt.setInt(1, no);
//			pstmt.setInt(2, no);
//			
//			result = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		
//		return result;
//	}
//	
//	/* 공지사항 상세보기 조회용 메소드 */
//	public NoticeDTO selectNoticeDetail(Connection con, int no) {
//		
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		
//		NoticeDTO noticeDetail = null;
//		
//		String query = prop.getProperty("selectNoticeDetail");
//		
//		try {
//			pstmt = con.prepareStatement(query);
//			pstmt.setInt(1, no);
//			
//			rset = pstmt.executeQuery();
//			
//			if(rset.next()) {
//				
//				noticeDetail = new NoticeDTO();
//				noticeDetail.setWriter(new MemberDTO());
//				
//				noticeDetail.setNo(rset.getInt("NOTICE_NO"));
//				noticeDetail.setTitle(rset.getString("NOTICE_TITLE"));
//				noticeDetail.setBody(rset.getString("NOTICE_BODY"));
//				noticeDetail.setWriterMemberNo(rset.getInt("NOTICE_WRITER_MEMBER_NO"));
//				noticeDetail.getWriter().setNickname(rset.getString("NICKNAME"));
//				noticeDetail.setCount(rset.getInt("NOTICE_COUNT"));
//				noticeDetail.setCreatedDate(rset.getDate("CREATED_DATE"));
//				
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rset);
//			close(pstmt);
//		}
//		
//		return noticeDetail;
//	}
//	
//	
//}
