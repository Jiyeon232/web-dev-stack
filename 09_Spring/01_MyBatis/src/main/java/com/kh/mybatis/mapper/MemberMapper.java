package com.kh.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.mybatis.model.vo.Member;

@Mapper
public interface MemberMapper {
	// 기존의 DAO 역할!
	void register(Member vo);
	
	Member login(Member vo);
	
	List<Member> allMember();
	
	void update(Member vo);
}
