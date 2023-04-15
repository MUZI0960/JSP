package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.internalResourceViewResolver;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberUpdate.do")
public class MemberUpdateControllerServlet extends HttpServlet {
	private MemberService service = new MemberServiceImpl(); 
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO authMember =  (MemberVO) req.getSession().getAttribute("authMember");
//		요청의 디코딩 캐릭터셋 설정.
		req.setCharacterEncoding("UTF-8");
//		요청 파라미터 -> Command Object (request)
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		member.setMemId(authMember.getMemId());
//		요청 검증
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		boolean valid = validate(member, errors);
		String viewName = null;
		if(valid) {
//		통과
//			수정 로직
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
//			인증 실패
//				mypage 로 이동(forward) + message
				req.setAttribute("message", "비밀번호 오류");
				viewName = "member/mypage";
				break;
			case OK:
//			수정 성공
//				mypage 로 이동(redirect)
				viewName = "redirect:/mypage.do";
				break;

			default:
//			실패
				req.setAttribute("message", "서버 오류");
				viewName = "member/mypage";
				break;
			}
			
		}else {
//		불통
//			mypage 로 이동(forward) + errors
			viewName = "member/mypage";
		}
		new internalResourceViewResolver().viewResolve(viewName, req, resp);
	}
	
	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;

//		if (StringUtils.isBlank(member.getMemId())) {
//			valid = false;
//			errors.put("memId", "회원아이디 누락");
//		}
		if (StringUtils.isBlank(member.getMemPass())) {
			valid = false;
			errors.put("memPass", "비밀번호 누락");
		}
//		if (StringUtils.isBlank(member.getMemName())) {
//			valid = false;
//			errors.put("memName", "회원명 누락");
//		}
		if (StringUtils.isBlank(member.getMemZip())) {
			valid = false;
			errors.put("memZip", "우편번호 누락");
		}
		if (StringUtils.isBlank(member.getMemAdd1())) {
			valid = false;
			errors.put("memAdd1", "주소1 누락");
		}
		if (StringUtils.isBlank(member.getMemAdd2())) {
			valid = false;
			errors.put("memAdd2", "주소2 누락");
		}
		if (StringUtils.isBlank(member.getMemMail())) {
			valid = false;
			errors.put("memMail", "이메일 누락");
		}

		if(StringUtils.isNotBlank(member.getMemBir())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				sdf.parse(member.getMemBir());
			} catch (ParseException e) {
				valid = false;
				errors.put("memBir", "날짜 형식에 맞지 않음.");
			}
		}
		
		return valid;
	}

}
