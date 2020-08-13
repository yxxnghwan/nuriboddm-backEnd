package com.nuribodeum.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuribodeum.config.LoginManagement;
import com.nuribodeum.mapper.EmergencyMapper;
import com.nuribodeum.vo.AccountVO;
import com.nuribodeum.vo.EmergencyVO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/emergency")
public class EmergencyController {
	
	
	@Autowired
	EmergencyMapper emergencyMapper;
	
	@PostMapping
	public void postEmergency(HttpServletRequest request, HttpServletResponse response, @RequestBody EmergencyVO vo) {
		System.out.println("응급상황 등록");
		System.out.println(vo);
		AccountVO account = (AccountVO)request.getAttribute("account");
		
		if(account.getAccount_type().equals("user") && account.getId().equals(vo.getUser_id())) { // 유저고 본인이어야함
			System.out.println("인증성공 응급상황등록");
			emergencyMapper.insertEmergency(vo);
		} else {
			System.out.println("유저 본인만 응급상황 등록이 가능합니다.");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
	}
	
	@PatchMapping("complete")
	public void completeEmergency(HttpServletRequest request, HttpServletResponse response, @RequestBody EmergencyVO vo) {
		System.out.println("응급상황 완료");
		vo = emergencyMapper.getEmergency(vo.getEmergency_seq());
		System.out.println(vo);
		AccountVO account = (AccountVO)request.getAttribute("account");
		
		if(account.getAccount_type().equals("manager") || // 매니저거나 누리미 본인
				(account.getAccount_type().equals("user") && account.getId().equals(vo.getUser_id()))) {
			System.out.println("인증완료 응급상황 완료");
			emergencyMapper.completeEmergency(vo.getEmergency_seq());
		} else {
			System.out.println("관리자나 유저 본인만 응급상황 완료가 가능합니다.");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
	}
	@PatchMapping("failure")
	public void failEmergency(HttpServletRequest request, HttpServletResponse response, @RequestBody EmergencyVO vo) {
		System.out.println("응급상황 실패");
		vo = emergencyMapper.getEmergency(vo.getEmergency_seq());
		System.out.println(vo);
		AccountVO account = (AccountVO)request.getAttribute("account");
		
		if(account.getAccount_type().equals("manager")) {
			System.out.println("인증완료 응급상황 실패");
			emergencyMapper.failEmergency(vo.getEmergency_seq());
		} else {
			System.out.println("관리자만 응급상황 실패가 가능합니다.");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
	}
	
	@GetMapping("/{emergency_seq}")
	public EmergencyVO getEmergency(HttpServletRequest request, HttpServletResponse response, @PathVariable("emergency_seq") int emergency_seq) {
		System.out.println("응급상황조회");
		return emergencyMapper.getEmergency(emergency_seq);
	}
	
	@GetMapping("/list")
	public List<EmergencyVO> getEmergencyList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("응급상황리스트조회");
		return emergencyMapper.getEmergencyList();
	}
	
	@GetMapping
	public List<EmergencyVO> getUsersEmergencyList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("누리미 응급상황내역");
		String user_id = request.getParameter("user_id");
		return emergencyMapper.getUsersEmergencyList(user_id);
	}
}
