package kr.or.ddit.commons.event;

import kr.or.ddit.vo.MemberVO;

public class AuthMemberEvent {
	private final MemberVO authMember;

	public AuthMemberEvent(MemberVO authMember) {
		super();
		this.authMember = authMember;
	}
	
	public MemberVO getSource() {
		return authMember;
	}
}
