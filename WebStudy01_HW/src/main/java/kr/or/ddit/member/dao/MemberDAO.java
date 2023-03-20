package kr.or.ddit.member.dao;

import kr.or.ddit.vo.MemberVO;

/**
 * 회원관리와 인증시스템을 위한 Persistence Layer
 *
 */
public interface MemberDAO {
	/**
	 * PK로 회원 한명의 정보 조회
	 * @param memId
	 * @return 존재하지 않는 경우, null 반환.
	 */
	public MemberVO selectMemberForAuth(String memId);
}
