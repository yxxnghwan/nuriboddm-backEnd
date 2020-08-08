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

import com.nuribodeum.config.LoginManagementService;
import com.nuribodeum.mapper.EmergencyMapper;
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
	
	@Autowired
	EmergencyMapper emergencyMapper;
	
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
	
	@GetMapping("/list")
	public List<HelpVO> getHelpList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("총 도움내역");
		return helpMapper.getHelpList();
	}
	
	@GetMapping("/{help_seq}")
	public HelpVO getHelp(HttpServletRequest request, HttpServletResponse response, @PathVariable("help_seq") int help_seq) {
		HelpVO help = helpMapper.getHelp(help_seq);
		help.setEmergency(emergencyMapper.getEmergency(help.getEmergency_seq()));
		return help;
	}
	
	@PatchMapping("/complete")
	public void completeHelp(HttpServletRequest request, HttpServletResponse response, @RequestBody HelpVO vo) {
		System.out.println("도움완료");
		vo = helpMapper.getHelp(vo.getHelp_seq());
		System.out.println(vo);
		AccountVO account = loginManagermentService.signInCheck(request, response);
		if(account != null) {
			if(account.getAccount_type().equals("user") 
					&& account.getId().equals(emergencyMapper.whoseEmergency(vo.getEmergency_seq()))) {
					helpMapper.completeHelp(vo.getHelp_seq());
			} else {
				System.out.println("도움완료는 누리미가 할 수 있습니다.");
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
			}
		} else {
			System.out.println("로그인 정보 없음");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
	}
	
	@GetMapping("/helper/{helper_id}")
	public List<HelpVO> getHelpersHelpList(HttpServletRequest request, HttpServletResponse response, @PathVariable("helper_id") String helper_id) {
		System.out.println("보드미가 준 도움내역");
		List<HelpVO> helpList = helpMapper.getHelpersHelpList(helper_id);
		for(HelpVO help : helpList) {
			help.setEmergency(emergencyMapper.getEmergency(help.getEmergency_seq()));
		}
		return helpList;
	}
	
	@GetMapping("/user/{user_id}")
	public List<HelpVO> getUsersHelpList(HttpServletRequest request, HttpServletResponse response, @PathVariable("user_id") String user_id) {
		System.out.println("누리미가 받은 도움 내역");
		List<HelpVO> helpList = helpMapper.getUsersHelpList(user_id);
		for(HelpVO help : helpList) {
			help.setEmergency(emergencyMapper.getEmergency(help.getEmergency_seq()));
		}
		return helpList;
	}
	
	@GetMapping("/month/{strMonth}")
	public List<HelpVO> getMonthHelpList(HttpServletRequest request, HttpServletResponse response, @PathVariable("strMonth") String strMonth) {
		System.out.println("월별도움내역");
		List<HelpVO> helpList = helpMapper.getMonthHelpList(strMonth);
		for(HelpVO help : helpList) {
			help.setEmergency(emergencyMapper.getEmergency(help.getEmergency_seq()));
		}
		return helpList;
	}
	@GetMapping("/date/{strDate}")
	public List<HelpVO> getDateHelpList(HttpServletRequest request, HttpServletResponse response, @PathVariable("strDate") String strDate) {
		System.out.println("일별도움내역");
		List<HelpVO> helpList = helpMapper.getDateHelpList(strDate);
		for(HelpVO help : helpList) {
			help.setEmergency(emergencyMapper.getEmergency(help.getEmergency_seq()));
		}
		return helpList;
	}
	
}
