package com.nuribodeum.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuribodeum.config.LoginManagementService;
import com.nuribodeum.mapper.AccountMapper;
import com.nuribodeum.vo.AccountVO;
import com.nuribodeum.vo.ManagerVO;
import com.nuribodeum.vo.ProtectorVO;
import com.nuribodeum.vo.UserVO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	LoginManagementService loginManagementService;

	@Autowired
	AccountMapper accountMapper;
	
	@PostMapping("/manager")
	public void postManager(HttpServletRequest request, HttpServletResponse response, @RequestBody ManagerVO vo) {
		System.out.println("매니저 추가");
		System.out.println(vo);
		vo.setPassword(BCrypt.hashpw(vo.getPassword(), BCrypt.gensalt()));
		System.out.println("비크립트 해시 : " + vo.getPassword());
		try {
			accountMapper.insertManager(vo);
			response.setStatus(HttpStatus.CREATED.value());
		} catch (Exception e) {
			System.out.println("이미 있는 아이디");
			response.setStatus(HttpStatus.CONFLICT.value());
		}
	}
	
	@PostMapping("/user")
	public void postUser(HttpServletRequest request, HttpServletResponse response, @RequestBody UserVO vo) {
		System.out.println("유저 추가");
		System.out.println(vo);
		vo.setPassword(BCrypt.hashpw(vo.getPassword(), BCrypt.gensalt()));
		System.out.println("비크립트 해시 : " + vo.getPassword());
		try {
			accountMapper.insertUser(vo);
			response.setStatus(HttpStatus.CREATED.value());
		} catch (Exception e) {
			System.out.println("이미 있는 아이디");
			response.setStatus(HttpStatus.CONFLICT.value());
		}
	}
	
	@PostMapping("/protector")
	public void postProtector(HttpServletRequest request, HttpServletResponse response, @RequestBody ProtectorVO vo) {
		System.out.println("보호자 추가");
		System.out.println(vo);
		vo.setPassword(BCrypt.hashpw(vo.getPassword(), BCrypt.gensalt()));
		System.out.println("비크립트 해시 : " + vo.getPassword());
		try {
			accountMapper.insertProtector(vo);
			response.setStatus(HttpStatus.CREATED.value());
		} catch (Exception e) {
			System.out.println("이미 있는 아이디");
			response.setStatus(HttpStatus.CONFLICT.value());
		}
	}
	
	
	
	@PostMapping("/auth")
	public String loginManager(HttpServletRequest request, HttpServletResponse response, @RequestBody AccountVO vo) {
		System.out.println("로그인하기");
		String loginID = null;
		if(vo.getAccount_type().equals("manager")) {
			ManagerVO manager = accountMapper.getManager(vo.getId());
			if(manager == null) {
				System.out.println("아이디 없음");
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
			} else {
				System.out.println("아이디 있음");
				if(BCrypt.checkpw(vo.getPassword(), manager.getPassword())) { // 비밀번호 일치
					System.out.println("로그인 성공");
					loginID = manager.getManager_id();
					String jwt = loginManagementService.createJWT(vo);
					Cookie cookie = new Cookie("nuribodeumJWT",jwt);
					cookie.setMaxAge(60*60*24*180); // 만료시간 180일
					cookie.setPath("/");
					cookie.setHttpOnly(true);
					response.addCookie(cookie);
					response.setStatus(HttpStatus.OK.value());
					
				} else { // 비밀번호 틀림
					System.out.println("비밀번호 오류");
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
				}
			}
		}
		return loginID;
	}
}
