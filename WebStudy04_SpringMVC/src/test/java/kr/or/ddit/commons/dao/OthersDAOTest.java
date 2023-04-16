package kr.or.ddit.commons.dao;

import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.or.ddit.AbstractModelLayerTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class OthersDAOTest extends AbstractModelLayerTest{

	@Inject
	private OthersDAO dao;
	
	@Test
	public void testSelectLprodList() {
		dao.selectLprodList().stream()
			.forEach((m)->{
				log.info("key : {}, value : {}", "lprodGu", m.get("lprodGu"));
				log.info("key : {}, value : {}", "lprodNm", m.get("lprodNm"));
			});
	}

	@Test
	public void testSelectBuyerList() {
		dao.selectBuyerList().stream()
			.forEach((b)->{
				log.info("조회된 레코드 : {}", b);
			});
	}

}
















