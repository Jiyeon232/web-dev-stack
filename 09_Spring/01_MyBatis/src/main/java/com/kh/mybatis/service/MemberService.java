package com.kh.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mybatis.mapper.MemberMapper;
import com.kh.mybatis.model.vo.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberMapper mapper;
	
	public void register(Member member) {
		mapper.register(member);
	}
	
	public Member login(Member member) {
		return mapper.login(member);
	}
	
	public List<Member> allMember(){
		return mapper.allMember();
	}
	
	public void update(Member member) {
		mapper.update(member);
	}

}
