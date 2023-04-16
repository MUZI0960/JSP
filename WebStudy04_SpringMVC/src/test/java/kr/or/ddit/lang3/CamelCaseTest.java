package kr.or.ddit.lang3;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.junit.Test;

public class CamelCaseTest {
	@Test
	public void caseConverting() {
		String columnName = "MEM_NAME";
		String capName = WordUtils.capitalizeFully(columnName, '_');
		String rvName = StringUtils.remove(capName, '_');
		String propName = StringUtils.uncapitalize(rvName);
		System.out.println(propName);
	}
}
