package kr.or.ddit.member.service;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.exception.AuthenticateException;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService {
	
	// 의존 객체를 직접 인스턴스화. 결합력 최상.
	private MemberDAO dao = new MemberDAOImpl();
	
	@Override
	public MemberVO authenticate(MemberVO input) throws AuthenticateException {
		MemberVO saved = dao.selectMemberForAuth(input.getMemId());
		if(saved!=null) {
			String inputPass = input.getMemPass();
			String savedPass = saved.getMemPass();
			if(savedPass.equals(inputPass)) {
				return saved;
			}
		}
		throw new AuthenticateException(input.getMemId());
	}

}
