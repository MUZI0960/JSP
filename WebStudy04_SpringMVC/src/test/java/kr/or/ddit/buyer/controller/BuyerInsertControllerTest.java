package kr.or.ddit.buyer.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import kr.or.ddit.AbstractControllerTest;


public class BuyerInsertControllerTest extends AbstractControllerTest{

	private MockMvc mockMvc;
	@Inject
	private BuyerInsertController controller;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testBuyer() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertForm() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		mockMvc.perform(get("/buyer/buyerInsert.do"))
				.andExpect(model().attribute("buyer", null))
	}

}
