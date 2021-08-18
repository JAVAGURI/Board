package com.joonho.portfolio.member.model.dao;

import com.joonho.portfolio.member.model.dto.MemberDTO;

public interface MemberMapper {
	
	String selectEncryptedPwd(MemberDTO requestMember);

	MemberDTO selectLoginMember(MemberDTO requestMember);

	int insertMember(MemberDTO requestMember);


}
