package kr.or.ddit.member.service;

import kr.or.ddit.member.exception.AuthenticateException;
import kr.or.ddit.vo.MemberVO;

public interface AuthenticateService {
	/**
	 * 아이디와 비밀번호 기반의 인증 처리
	 * @param input
	 * @return 인증에 성공한 경우, 해당 사용자의 정보를 가진 VO
	 * @throws AuthenticateException 인증 실패
	 */
	public MemberVO authenticate(MemberVO input) throws AuthenticateException;
}
