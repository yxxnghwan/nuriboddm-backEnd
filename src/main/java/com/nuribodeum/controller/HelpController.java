package com.nuribodeum.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuribodeum.config.LoginManagementService;
import com.nuribodeum.mapper.HelpMapper;
import com.nuribodeum.vo.AccountVO;
import com.nuribodeum.vo.HelpVO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/help")
public class HelpController {
	
	@Autowired
	LoginManagementService loginManagermentService;
	
	@Autowired
	HelpMapper helpMapper;
	
	@PostMapping
	public void postHelp(HttpServletRequest request, HttpServletResponse response, @RequestBody HelpVO vo) {
		System.out.println("도움등록");
		System.out.println(vo);
		AccountVO account = loginManagermentService.signInCheck(request, response);
		if(account != null) {
			if(account.getAccount_type().equals("helper") && account.getId().equals(vo.getHelper_id())) {
				System.out.println("인증성공 도움등록");
				helpMapper.insertHelp(vo);
			} else {
				System.out.println("보드미 본인만 도움을 등록할 수 있습니다.");
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
			}
		} else {
			System.out.println("로그인 정보 없음");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
	}
	
	@PatchMapping
	public void completeHelp(HttpServletRequest request, HttpServletResponse response, @RequestBody HelpVO vo) {
		
	}
}
