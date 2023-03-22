package kr.or.ddit.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * JavaBean 규약
 * ValueObject, DataTransferObject, Model, Bean
 * ex) MemberVO, MemberDTO, MemberModel, MemberBean
 *
 *	1. 값을 저장할 속성(property) 정의
 *	2. 캡슐화
 *	3. 캡슐화된 데이터에 접근할 인터페이스 제공 (getter/setter)
 *		get[set] 프로퍼티명에서 첫문자만 대문자, camel case 적용
 *	4. 상태 비교 메소드 제공
 *	5. 상태 확인 메소드 제공
 *	6. 직렬화 가능
 *
 *	회원관리와 인증시스템을 위한 Domain Layer
 *	
 */
@Data
@EqualsAndHashCode(of = "memId")
@ToString(exclude = {"memPass", "memRegno1", "memRegno2"})
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class MemberVO implements Serializable{
	private String memId;
	@JsonIgnore
	private transient String memPass;
	private String memName;
	@JsonIgnore
	private transient String memRegno1;
	@JsonIgnore
	private transient String memRegno2;
	private String memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private String memMemorialday;
	private Integer memMileage;
	private String memDelete;
	
	public String getMemTest() {
		return "테스트";
	}
	
}













