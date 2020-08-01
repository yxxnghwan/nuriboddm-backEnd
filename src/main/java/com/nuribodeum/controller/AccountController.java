package com.nuribodeum.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuribodeum.mapper.AccountMapper;
import com.nuribodeum.vo.ManagerVO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountMapper accountMapper;
	
	@PostMapping("/manager")
	public void postManager(HttpServletRequest request, HttpServletResponse response, @RequestBody ManagerVO vo) {
		System.out.println("매니저 추가");
		System.out.println(vo);
		vo.setPassword(BCrypt.hashpw(vo.getPassword(), BCrypt.gensalt()));
		System.out.println("비크립트 해시 : " + vo.getPassword());
		accountMapper.insertManager(vo);
	}
	
	@PostMapping("/auth/manager")
	public ManagerVO loginManager(HttpServletRequest request, HttpServletResponse response, @RequestBody ManagerVO vo) {
		System.out.println("로그인하기");
		// db에서 아이디로 찾아서 vo를 불러오고 그 vo 비밀번호랑 입력받은 password랑 checkpw해서 로그인성공 실패 하면댐
		ManagerVO manager = accountMapper.getManager(vo.getManager_id());
		if(manager == null) {
			
		}
		return manager;
		
	}
}
