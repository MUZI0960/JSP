package kr.or.ddit.member.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.exception.AuthenticateException;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.Pagination;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;
	@Inject
	private AuthenticateService authService;
	
	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		if(memberDAO.selectMember(member.getMemId())==null) {
			int rowcnt = memberDAO.insertMember(member);
			result = rowcnt > 0 ?ServiceResult.OK : ServiceResult.FAIL;
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}

	@Override
	public List<MemberVO> retrieveMemberList(Pagination<MemberVO> pagination) {
		pagination.setTotalRecord(memberDAO.selectTotalRecord(pagination));
		return memberDAO.selectMemberList(pagination);
	}

	@Override
	public MemberVO retrieveMember(String memId) throws UserNotFoundException {
		MemberVO member = memberDAO.selectMember(memId);
		if(member==null)
			throw new UserNotFoundException(memId);
		return member;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) throws UserNotFoundException {
		ServiceResult result = null;
		try {
			authService.authenticate(member);
			int rowcnt = memberDAO.updateMember(member);
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
			int rowcnt = memberDAO.deleteMember(member.getMemId());
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}catch (AuthenticateException e) {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

}










