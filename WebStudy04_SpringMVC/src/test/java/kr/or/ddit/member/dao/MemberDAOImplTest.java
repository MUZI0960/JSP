package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.Pagination;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
@WebAppConfiguration
public class MemberDAOImplTest {
	@Inject
	private MemberDAO dao;
	
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
		Pagination pagination = new Pagination();
		pagination.setCurrentPage(2);
		List<MemberVO> list = dao.selectMemberList(pagination);
		assertNotNull(list);
		assertNotEquals(0, list.size());
		list.stream().forEach(System.out::println);
	}

	@Test
	public void testSelectMember() {
		MemberVO member = dao.selectMember("a001");
		assertNotNull(member);
	}

	@Test
	public void testUpdateMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteMember() {
		int rowcnt = dao.deleteMember("a001");
		assertEquals(1, rowcnt);
	}

}


















