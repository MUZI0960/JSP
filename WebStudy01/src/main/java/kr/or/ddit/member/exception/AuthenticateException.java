package kr.or.ddit.member.exception;

public class AuthenticateException extends RuntimeException{

	public AuthenticateException(String memId) {
		super(String.format("%s 로그인 실패", memId));
	}
	
}
