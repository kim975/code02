package com.spring5.code02.member;

import java.util.HashMap;

public interface MemberService {

	public int addMember(MemberVO member);

	public String duplicateId(String id);

	public String duplicateEmail(String email);

	public String duplicatePhone(String phone);

	public LoginVO loginMember(HashMap<String, String> login);

	public MemberVO selectMember(String id);

	public int modMember(MemberVO member);

	public int delMember(String id);

}
