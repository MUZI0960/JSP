package kr.or.ddit.buyer.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import kr.or.ddit.AbstractControllerTest;

public class BuyerListControllerTest extends AbstractControllerTest{

	@Test
	public void testGetUI() throws Exception {
		mockMvc.perform(get("/buyer/buyerList.do"))
				.andExpect(view().name("buyer/buyerList"))
				.andDo(log());
	}
	
	@Test
	public void testContent() throws Exception{
		mockMvc.perform(get("/buyer/buyerList.do")
				.accept(MediaType.APPLICATION_JSON)
				.param("page", "2")
				).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(log());
		
	}
	
	
}
