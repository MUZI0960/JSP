package kr.or.ddit.l306;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class PatternTest {
	
	private Pattern ptrn;
	
	@Before
	public void setUp() throws Exception {
		String regex = "<%=(\\w+)\\s*%>";
		ptrn = Pattern.compile(regex);
	}

	@Test
	public void test() {
		Map<String, Object> scope = new HashMap<String, Object>();
		scope.put("time", new Date());
		String target = "<h4>current <%=time1 %> server time : <%=time %> </h4>";
		Matcher matcher = ptrn.matcher(target);
		StringBuffer contents = new StringBuffer();
		
		while(matcher.find()) {
			System.out.println(matcher.group(1));
			String attributeName = matcher.group(1);
			Object attributeValue = scope.get(attributeName);
			String replaceValue = Optional.ofNullable(attributeValue)
					.map((av)->{return av.toString();})
					.orElse("");
			matcher.appendReplacement(contents, replaceValue);
		}
		matcher.appendTail(contents);
		System.out.println(contents);
	}

}
