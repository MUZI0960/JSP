package kr.or.ddit.utils;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.calculator.CalculateVO;

public class RequestBodyProcessor {
	public static <T> T getContentFromRequestBody(HttpServletRequest req, Class<T> modelType) throws IOException {
		try(
			InputStream is = req.getInputStream();
		){
			return new ObjectMapper().readValue(is, modelType);
		}
	}
	
}
