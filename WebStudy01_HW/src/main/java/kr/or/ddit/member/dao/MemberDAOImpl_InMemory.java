package kr.or.ddit.member.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl_InMemory implements MemberDAO {

	private Map<String, MemberVO> memberDB;
	
	public MemberDAOImpl_InMemory() {
		super();
		memberDB = new LinkedHashMap<>();
		MemberVO a001VO = new MemberVO();
		a001VO.setMemId("a001");
		a001VO.setMemPass("java");
		a001VO.setMemName("김은대");
		memberDB.put("a001", a001VO);

		MemberVO b001VO = new MemberVO();
		b001VO.setMemId("b001");
		b001VO.setMemPass("java");
		b001VO.setMemName("이쁜이");
		memberDB.put("b001",b001VO);
	}


	@Override
	public MemberVO selectMemberForAuth(String memId) {
		return memberDB.get(memId);
	}

}











