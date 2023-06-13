package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.validate.InsertGroup;

@Controller
@RequestMapping("/board/boardInsert.do")
public class BoardInsertController {
	@Inject
	private BoardService service;
	
	@ModelAttribute("freeboard")
	public BoardVO board(Authentication authentication) {
		BoardVO board = new BoardVO();
		board.setBoWriter(authentication.getName());
		return board;
	}
	
	@GetMapping
	public String insertForm() {
		return "board/boardForm";
	}
	
	@Resource(name = "wsSessionList")
	private List<WebSocketSession> sessionList;
	
	@PostMapping
	public String insert(
		@Validated(InsertGroup.class) @ModelAttribute("freeboard") BoardVO board
		, BindingResult errors
	) {
		if(!errors.hasErrors()) {
			service.createBoard(board);
			// 알림 메세지 전송
			sessionList.forEach((ws)->{
				String payload =String.format("%d 번 글이 새로 등록됨.", board.getBoNo());
				try {
					ws.sendMessage(new TextMessage(payload));
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
			return "redirect:/board/boardList.do";
		}else {
			return "board/boardForm";
		}
	}
}












