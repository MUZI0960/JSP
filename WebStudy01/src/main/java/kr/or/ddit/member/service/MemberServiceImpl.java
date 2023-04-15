package kr.or.ddit.member.service;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.exception.AuthenticateException;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {

	MemberDAO memDAO = new MemberDAOImpl();
	private AuthenticateService authService = new AuthenticateServiceImpl();
	
	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		if(memDAO.selectMember(member.getMemId())==null) {
			int rowcnt = memDAO.insertMember(member);
			result = rowcnt > 0 ?ServiceResult.OK : ServiceResult.FAIL;
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}

	@Override
	public List<MemberVO> retrieveMemerList() {
		return memDAO.selectMemberList();
		
	}

	@Override
	public MemberVO retrieveMemer(String memId) throws UserNotFoundException {
		MemberVO vo = memDAO.selectMember(memId);
		if(vo==null) {
			throw new UserNotFoundException(memId);
		}
		return vo;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) throws UserNotFoundException {
		ServiceResult result = null;
		try {
			authService.authenticate(member);
			int rowcnt = memDAO.updateMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}catch (AuthenticateException e) {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) throws UserNotFoundException {
		ServiceResult result = null;
		try {
			authService.authenticate(member);
			int rowcnt = memDAO.deleteMember(member.getMemId());
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}catch (AuthenticateException e) {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

}








