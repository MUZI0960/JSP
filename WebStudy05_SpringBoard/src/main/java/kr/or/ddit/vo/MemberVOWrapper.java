package kr.or.ddit.vo;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class MemberVOWrapper extends User{
	private MemberVO realUser;

	public MemberVOWrapper(MemberVO realUser) {
		super(realUser.getMemId(), realUser.getMemPass(), AuthorityUtils.createAuthorityList(realUser.getMemRole()) );
		this.realUser = realUser;
	}
	
	public MemberVO getRealUser() {
		return realUser;
	}
	
}
