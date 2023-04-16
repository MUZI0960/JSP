package kr.or.ddit.schema.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.schema.service.SchemaService;
import kr.or.ddit.vo.ColumnSchemaVO;
import kr.or.ddit.vo.TableSchemaVO;

@Controller
@RequestMapping("/schema")
public class TableSchemaController{
	@Inject
	private SchemaService service;
	
	@RequestMapping(value="tableSchema", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<TableSchemaVO> tableSchema(){
		return service.retrieveTableSchemaList();
	}
	
	@RequestMapping("columnSchema")
	public String columnSchema(@RequestParam(value="what", required = true) String tableName, Model model){
		List<ColumnSchemaVO> columnList = service.retrieveColumnSchemaListByTableName(tableName);
		model.addAttribute("columnList", columnList);
		return "jsonView";
	}
}


















