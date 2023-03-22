package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOImplTest {


	private MemberDAO dao = new MemberDAOImpl();
	
	@Test
	public void testSelectMemberForAuth() {
		MemberVO saved = dao.selectMemberForAuth("a001");
		assertNotNull(saved);
		System.out.println(saved);
	}

	@Test
	public void testInsertMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectMemberList() {
		List<MemberVO> list = dao.selectMemberList();
		assertNotNull(list);
		assertNotEquals(0, list.size());
		list.stream().forEach(System.out::println);
	}

	@Test
	public void testSelectMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteMember() {
		fail("Not yet implemented");
	}

}
