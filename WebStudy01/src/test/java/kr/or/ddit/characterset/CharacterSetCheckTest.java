package kr.or.ddit.characterset;

import static org.junit.Assert.*;

import java.nio.charset.Charset;

import org.junit.Test;

public class CharacterSetCheckTest {

	@Test
	public void test() {
		System.out.println((int)'a');
		System.out.println((byte)'a');
		System.out.println((int)'한');
		System.out.println((byte)'한');
		System.out.println((char)(byte)92);
		Charset.availableCharsets().forEach((k,v)->{
			System.out.printf("%s : %s\n",k, v);
		});
		System.err.println(Charset.defaultCharset());
	}

}
