package kr.or.ddit.file.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/file/upload3")
public class FileUploadController3{
	@Value("#{appInfo['files']}")
	private File saveFolder;
	
	@PostConstruct
	public void init() {
		if(!saveFolder.exists()) saveFolder.mkdirs();
		log.info("로딩된 리소스 : {}", saveFolder);
	}
	
	@GetMapping
	public String doGet() {
		return "16/fileUploadForm";
	}
	
	@PostMapping
	public String doPost(MultipartFile uploadFile,  String uploader, RedirectAttributes redirectAttributes) throws IOException{
		
		//		req.getParameterMap();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("uploader", uploader);
		if(!uploadFile.isEmpty()) {
			String savename = processUploadFile(uploadFile);
			modelMap.put("savename", savename);
		}
		redirectAttributes.addFlashAttribute("modelMap", modelMap);
		
		return "redirect:/file/upload3";
	}

	private String processUploadFile(MultipartFile part) throws IOException {
		// 1. middle tier 에 저장(web resource 형태, /resources/files)
		String originalfilename = part.getOriginalFilename();
		String savename = UUID.randomUUID().toString();
		File saveFile = new File(saveFolder, savename);
		part.transferTo(saveFile);
		return savename;
	}
}

















