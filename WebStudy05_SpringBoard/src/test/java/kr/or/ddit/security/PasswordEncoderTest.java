package kr.or.ddit.security;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.or.ddit.AbstractModelLayerTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordEncoderTest extends AbstractModelLayerTest{
//	@Inject
	private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	
	@Test
	public void encodeTest() {
		String plain = "java";
		// encrypt(암호화)/decrypt vs encode(부호화)/decode - 공통:데이터변환 차:(암)못읽도록/(부)읽을수 있도록
		String encoded = encoder.encode(plain);
		log.info("encoded password : {}", encoded);
		
		String saved = "{bcrypt}$2a$10$jR.Ue.SYiIn1SPV5/35gc.41bxXqTHr3mHj8LKZqFi6n5.t9nkNDa";
		log.info("인증 성공 여부 : {}", encoder.matches(plain, saved));
		
		
	}
}
