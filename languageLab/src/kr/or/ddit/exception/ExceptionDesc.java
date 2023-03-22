package kr.or.ddit.exception;

import java.sql.SQLException;

/**
 * 예외? 프로그램의 정상 실행이 중단될 수 있는 모든 조건과 상황에 대한 통칭
 * ==> java.lang.Throwable 타입으로 캡슐화함.
 * 
 * 예외 계층(Throwable 하위 타입)
 * -> java.lang.Error : 시스템 실패 상황으로 일반적으로 개발자가 처리할 수 없는 에러.
 * -> java.lang.Exception : 예외 처리 정책에 따라 조건문의 형태로 처리가 가능한 예외.
 * 		-> checked exception(non RuntimeException) : 회피/복구/전환 중 어느 한가지 예외 처리 정책을 기반으로 반드시 처리를 해야 하는 예외.
 * 		-> unchecked exception(RuntimeException 하위 타입) : 개발자가 직접 처리하지 않더라도 자동으로 예외 회피(throws) 전략이 사용되는 구조
 * 
 * 예외 처리 정책
 * 예외 회피(소극적 처리, 수동 처리) : throws 키워드를 이용해 메소드의 콜스택 데이터를 기반으로 호출자에게 예외 처리를 떠넘기는 전략.
 * 예외 처리(적극적 처리, 능동 처리) : try(){}catch(){}finally{} 구문으로 직접 처리하는 전략
 * 	try(Closable 객체 생성 및 선언){
 * 		예외 발생 가능 구문
 * 	}catch(처리 가능한 예외 선언, Throwable 하위 타입){
 * 		-> 예외 복구
 * 				-> 예외 로그 기록 -> 일정시간 지연 -> 예외 발생 작업에 대한 재실행
 * 		-> 예외 전환 : 명확한 조건이나 상황을 표현할 수 있는 예외의 형태로 변경(wrapping).
 * 			catch(IOException e){
 * 				throw new FileCopyFailureException(e);
 * 			}
 * ** Custom Exception 
 * 		1. 예외의 특성에 따라 상위 타입을 결정 (Exception vs RuntimeException)
 * 		2. throw 예외 객체 생성
 * 	}finally{
 * 		예외 발생 여부와 상관없이 실행되는 구문.
 * 	}
 * 
 */
public class ExceptionDesc {
	public static void main(String[] args) throws InterruptedException{
		try {
			String data = method1();
			System.out.println(data);
		}catch(NullPointerException e) {
//			System.err.println(e.getMessage());
			e.printStackTrace();
			try {
				Thread.sleep(3000);
				System.out.println("정상 처리 가장.");
			}catch (InterruptedException e1) {
//				throw e1;
				throw new RuntimeException(e1);
			}
		}
	}
	
	// unchecked e -> throws RuntimeException 없어도 있는 것 처럼 실행된다.
	private static String method1() {
		if(1==1) {
//			throw new NullPointerException("강제 발생 예외");
			try {
				throw new SQLException("강제 발생 예외");
			}catch (SQLException e) {
				throw new DataAccessException(e);
			}
		}
		return "DATA";
	}
}



























