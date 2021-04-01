package com.spring5.code02.member;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Autowired
	private SqlSession sqlSession;

	public MemberDAOImpl() {
		super();
	}

	@Override
	public int insertMember(MemberVO member) {
		
		return sqlSession.insert("MemberMapper.insertMember", member);
	}

	@Override
	public String selectDuplicateId(String id) {

		return sqlSession.selectOne("MemberMapper.selectDuplicateId", id);
	}

	@Override
	public String selectDuplicateEmail(String email) {
		
		return sqlSession.selectOne("MemberMapper.selectDuplicateEmail", email);
	}

	@Override
	public String selectDuplicatePhone(String phone) {
		
		return sqlSession.selectOne("MemberMapper.selectDuplicatePhone", phone);
	}

	@Override
	public LoginVO selectLogin(HashMap<String, String> login) {
		
		return sqlSession.selectOne("MemberMapper.selectLogin", login);
		
	}
	
	@Override
	public MemberVO selectMemberById(String id) {
		
		return sqlSession.selectOne("MemberMapper.selectMemberById", id);
	}
	
	@Override
	public int updateMember(MemberVO member) {
		
		return sqlSession.update("MemberMapper.updateMember", member);
	}
	
	@Override
	public int updateDelMember(String id) {
		
		return sqlSession.update("MemberMapper.updateDelMember", id);
	}
}
