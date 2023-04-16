package kr.or.ddit.member.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import kr.or.ddit.AbstractControllerTest;


public class MemberListControllerTest extends AbstractControllerTest{

	private MockMvc mockMvc;
	@Inject
	private MemberListController controller;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testMemberList() throws Exception {
		mockMvc.perform(get("/member/memberList.do").param("page", "2"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("pagination"))
			.andExpect(view().name("member/memberList"))
			.andDo(log());
		
	}

}
