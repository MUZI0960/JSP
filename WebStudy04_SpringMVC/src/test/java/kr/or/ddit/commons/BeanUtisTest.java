package kr.or.ddit.commons;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class BeanUtisTest {
	@Test
	public void timestampTest() throws IllegalAccessException, InvocationTargetException {
		Map<String, String> parameterMap = new HashMap<>();
		parameterMap.put("memBir", "2023-03-24T14:20");
		parameterMap.put("memMemorialday", "2023-03-24");
		
		MemberVO member = new MemberVO();
		DateConverter converter = new DateConverter();
		converter.setPattern("yyyy-MM-dd'T'HH:mm");
		
		ConvertUtils.register(converter, Timestamp.class);
		BeanUtils.populate(member, parameterMap);
		
		System.out.println(member.getMemBir());
		System.out.println(member.getMemMemorialday());
	}
}










