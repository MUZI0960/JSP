package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import kr.or.ddit.vo.ProdVO;

public class ProdDAOImplTest {

	private ProdDAO dao = new ProdDAOImpl();
	
	@Test
	public void testInsertProd() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectProdList() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectProd() {
		ProdVO prod = dao.selectProd("P101000001");
		assertNotNull(prod);
		System.out.println(prod);
		prod.getMemberList().stream()
						.forEach(System.out::println);
	}

	@Test
	public void testUpdateProd() {
		fail("Not yet implemented");
	}

}















