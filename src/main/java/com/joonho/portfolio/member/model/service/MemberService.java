package com.joonho.portfolio.member.model.service;

import com.joonho.portfolio.member.model.dto.MemberDTO;

public interface MemberService {

	MemberDTO loginCheck(MemberDTO memberDTO);

	int registMember(MemberDTO memberDTO);

	
	

}
