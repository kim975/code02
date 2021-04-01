package com.spring5.code02.member;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;

	public MemberServiceImpl() {
		super();
	}

	@Override
	public int addMember(MemberVO member) {

		return memberDAO.insertMember(member);
	}

	@Override
	public String duplicateId(String id) {
		
		return memberDAO.selectDuplicateId(id);
	}

	@Override
	public String duplicateEmail(String email) {

		return memberDAO.selectDuplicateEmail(email);
	}

	@Override
	public String duplicatePhone(String phone) {

		return memberDAO.selectDuplicatePhone(phone);
	}

	@Override
	public LoginVO loginMember(HashMap<String, String> login) {

		return memberDAO.selectLogin(login);
	}
	
	@Override
	public MemberVO selectMember(String id) {
		
		return memberDAO.selectMemberById(id);
	}
	
	@Override
	public int modMember(MemberVO member) {
		
		return memberDAO.updateMember(member);
	}
	
	@Override
	public int delMember(String id) {
		
		return memberDAO.updateDelMember(id);
	}
	
}
