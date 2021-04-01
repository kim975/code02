package com.spring5.code02.member;

import java.util.HashMap;

public interface MemberDAO {

	public int insertMember(MemberVO member);

	public String selectDuplicateId(String id);

	public String selectDuplicateEmail(String email);

	public String selectDuplicatePhone(String phone);

	public LoginVO selectLogin(HashMap<String, String> login);

	public MemberVO selectMemberById(String id);

	public int updateMember(MemberVO member);

	public int updateDelMember(String id);

}
