package com.joonho.portfolio.member.model.dao;
//package com.greedy.jsp.member.model.dao;
//
//import static com.greedy.jsp.common.jdbc.JDBCTemplate.close;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Properties;
//
//import com.greedy.jsp.common.config.ConfigLocation;
//import com.joonho.portfolio.member.model.dto.MemberDTO;
//
//public class MemberDAO {
//	
//	private final Properties prop;
//	
//	public MemberDAO() {
//		prop = new Properties();
//		
//		try {
//			prop.loadFromXML(new FileInputStream(ConfigLocation.MAPPER_LOCATION + "member/member-mapper.xml"));
//			
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 암호화 처리 된 비밀번호 조회용 메소드
//	 * @param con 연결객체
//	 * @param requestMember 로그인입력값(아이디,패스워드)
//	 * @return 암호화된비밀번호
//	 */
//	public String selectEncryptedPwd(Connection con, MemberDTO requestMember) {
//		
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		
//		String encPwd = null;
//		
//		String query = prop.getProperty("selectEncryptedPwd");
//		
//		try {
//			pstmt = con.prepareStatement(query);
//			pstmt.setString(1, requestMember.getId());
//			
//			rset = pstmt.executeQuery();
//			
//			if(rset.next()) {
//				encPwd = rset.getString("MEMBER_PWD");
//			}
//		} catch(SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rset);
//			close(pstmt);
//		}
//		
//		return encPwd;
//	}
//
//	/**
//	 * 패스워드가 일치 시 회원 정ㅈ보 조회용 메소드
//	 * @param con 연결객체정보
//	 * @param requestMember 로그인정보(아이디,패스워드)
//	 * @return 조회된 사용자 정보
//	 */ 
//	public MemberDTO selectLoginMember(Connection con, MemberDTO requestMember) {
//		
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		
//		MemberDTO loginMember = null;
//		
//		String query = prop.getProperty("selectLoginMember");
//		
//		try {
//			pstmt = con.prepareStatement(query);
//			pstmt.setString(1, requestMember.getId());
//			
//			rset = pstmt.executeQuery();
//			
//			if(rset.next()) {
//				loginMember = new MemberDTO();
//				
//				loginMember.setNo(rset.getInt("MEMBER_NO"));
//				loginMember.setId(rset.getString("MEMBER_ID"));
//				loginMember.setNickname(rset.getString("NICKNAME"));
//				loginMember.setPhone(rset.getString("PHONE"));
//				loginMember.setEmail(rset.getString("EMAIL"));
//				loginMember.setAddress(rset.getString("ADDRESS"));
//				loginMember.setEnrollDate(rset.getDate("ENROLL_DATE"));
//				loginMember.setRole(rset.getString("MEMBER_ROLE"));
//				loginMember.setStatus(rset.getString("MEMBER_STATUS"));
//				
//			}
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rset);
//			close(pstmt);
//		}
//		
//		
//		return loginMember;
//	}
//
//
//}
