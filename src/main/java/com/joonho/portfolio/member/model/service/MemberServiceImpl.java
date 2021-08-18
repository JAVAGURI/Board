package com.joonho.portfolio.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.joonho.portfolio.member.model.dao.MemberMapper;
import com.joonho.portfolio.member.model.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService{

	/* 의존 관계에 있는 객체가 불변을 유지할 수 있도록 final 필드로 선언 */
	private final MemberMapper mapper;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public MemberServiceImpl(MemberMapper mapper, BCryptPasswordEncoder passwordEncoder) {
		this.mapper = mapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	public MemberDTO loginCheck(MemberDTO requestMember) {
		
		MemberDTO loginMember = null;
		
		String encPwd = mapper.selectEncryptedPwd(requestMember);
		
		System.out.println("encPwd : " + encPwd);
		
		/* 로그인 요청한 원문 비밀번호와 저장되어 있는 암호화된 비밀번호가 일치하는지 확인 */
		if(passwordEncoder.matches(requestMember.getPwd(), encPwd)) {
			/* 비밀번호가 일치하는 경우에만 회원 정보를 조회해온다. */
			loginMember = mapper.selectLoginMember(requestMember);
		}
		System.out.println("loginMember : " + loginMember);
		
		return loginMember;
	}

	/**
	 * 회원가입용 메소드
	 * @param requestMember
	 * @return
	 */
	public int registMember(MemberDTO requestMember) {
		
		int result = mapper.insertMember(requestMember);
		
		return result;
	}

}
