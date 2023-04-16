package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.LoginGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="email")
public class SimpleRegistVO implements Serializable{
	@NotBlank(groups = {InsertGroup.class, LoginGroup.class})
	private String email;
	@NotBlank(groups = {InsertGroup.class, LoginGroup.class})
	private String password;
	@NotBlank
	private String name;
	private String nickName;
}
