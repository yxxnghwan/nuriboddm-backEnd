package com.nuribodeum.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuribodeum.config.LoginManagement;
import com.nuribodeum.service.AccountService;
import com.nuribodeum.vo.AccountVO;
import com.nuribodeum.vo.HelperVO;
import com.nuribodeum.vo.ManagerVO;
import com.nuribodeum.vo.ProtectorVO;
import com.nuribodeum.vo.UserVO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@PostMapping("/manager")
	public void postManager(HttpServletRequest request, HttpServletResponse response, @RequestBody ManagerVO vo) {
		System.out.println("매니저 추가");
		System.out.println(vo);
		vo.setPassword(BCrypt.hashpw(vo.getPassword(), BCrypt.gensalt()));
		System.out.println("비크립트 해시 : " + vo.getPassword());
		try {
			accountService.insertManager(vo);
			response.setStatus(HttpStatus.CREATED.value());
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.CONFLICT.value());
		}
	}
	
	@PostMapping("/user")
	public void postUser(HttpServletRequest request, HttpServletResponse response, @RequestBody UserVO vo) {
		System.out.println("누리미 추가");
		System.out.println(vo);
		AccountVO account = (AccountVO)request.getAttribute("account");
		
		if(account.getAccount_type().equals("manager")) {
			vo.setPassword(BCrypt.hashpw(vo.getPassword(), BCrypt.gensalt()));
			System.out.println("비크립트 해시 : " + vo.getPassword());
			try {
				accountService.insertUser(vo);
				response.setStatus(HttpStatus.CREATED.value());
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(HttpStatus.CONFLICT.value());
			}
		} else {
			System.out.println("관리자만 누리미를 추가할 수 있습니다.");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
		
	}
	
	@PostMapping("/protector")
	public void postProtector(HttpServletRequest request, HttpServletResponse response, @RequestBody ProtectorVO vo) {
		System.out.println("보호자 추가");
		System.out.println(vo);
		AccountVO account = (AccountVO)request.getAttribute("account");
		
		if(account.getAccount_type().equals("manager")) {
			System.out.println("관리자 인증 성공");
			vo.setPassword(BCrypt.hashpw(vo.getPassword(), BCrypt.gensalt()));
			System.out.println("비크립트 해시 : " + vo.getPassword());
			try {
				accountService.insertProtector(vo);
				response.setStatus(HttpStatus.CREATED.value());
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(HttpStatus.CONFLICT.value());
			}
		} else {
			System.out.println("관리자만 보호자를 추가할 수 있습니다.");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
	}
	
	@PostMapping("/helper")
	public void postHelper(HttpServletRequest request, HttpServletResponse response, @RequestBody HelperVO vo) {
		System.out.println("보드미 추가");
		System.out.println(vo);
		vo.setPassword(BCrypt.hashpw(vo.getPassword(), BCrypt.gensalt()));
		System.out.println("비크립트 해시 : " + vo.getPassword());
		try {
			accountService.insertHelper(vo);
			response.setStatus(HttpStatus.CREATED.value());
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.CONFLICT.value());
		}
	}
	
	
	@PostMapping("/auth")
	public String loginAccount(HttpServletRequest request, HttpServletResponse response, @RequestBody AccountVO vo) {
		System.out.println("로그인하기");
		String loginID = null;
		if(vo.getAccount_type().equals("manager")) {
			ManagerVO manager = accountService.getManager(vo.getId());
			if(manager == null) {
				System.out.println("아이디 없음");
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
			} else {
				System.out.println("아이디 있음");
				if(BCrypt.checkpw(vo.getPassword(), manager.getPassword())) { // 비밀번호 일치
					System.out.println("매니저 로그인 성공");
					loginID = manager.getManager_id();
					String jwt = LoginManagement.createJWT(vo);
					LoginManagement.issueJWT(jwt, response);
					response.setStatus(HttpStatus.OK.value());
					
				} else { // 비밀번호 틀림
					System.out.println("비밀번호 오류");
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
				}
			}
		} else if(vo.getAccount_type().equals("user")) {
			UserVO user = accountService.getUser(vo.getId());
			if(user == null) {
				System.out.println("아이디 없음");
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
			} else {
				System.out.println("아이디 있음");
				if(BCrypt.checkpw(vo.getPassword(), user.getPassword())) { // 비밀번호 일치
					System.out.println("누리미 로그인 성공");
					loginID = user.getUser_id();
					String jwt = LoginManagement.createJWT(vo);
					LoginManagement.issueJWT(jwt, response);
					response.setStatus(HttpStatus.OK.value());
					
				} else { // 비밀번호 틀림
					System.out.println("비밀번호 오류");
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
				}
			}
		} else if(vo.getAccount_type().equals("protector")) {
			ProtectorVO protector = accountService.getProtector(vo.getId());
			if(protector == null) {
				System.out.println("아이디 없음");
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
			} else {
				System.out.println("아이디 있음");
				if(BCrypt.checkpw(vo.getPassword(), protector.getPassword())) { // 비밀번호 일치
					System.out.println("보호자 로그인 성공");
					loginID = protector.getProtector_id();
					String jwt = LoginManagement.createJWT(vo);
					LoginManagement.issueJWT(jwt, response);
					response.setStatus(HttpStatus.OK.value());
					
				} else { // 비밀번호 틀림
					System.out.println("비밀번호 오류");
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
				}
			}
		} else if(vo.getAccount_type().equals("helper")) {
			HelperVO helper = accountService.getHelper(vo.getId());
			if(helper == null) {
				System.out.println("아이디 없음");
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
			} else {
				System.out.println("아이디 있음");
				if(BCrypt.checkpw(vo.getPassword(), helper.getPassword())) { // 비밀번호 일치
					System.out.println("보드미 로그인 성공");
					loginID = helper.getHelper_id();
					String jwt = LoginManagement.createJWT(vo);
					LoginManagement.issueJWT(jwt, response);
					response.setStatus(HttpStatus.OK.value());
					
				} else { // 비밀번호 틀림
					System.out.println("비밀번호 오류");
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
				}
			}
		}
		return loginID;
	}
	
	@PutMapping("/manager")
	public void updateManager(HttpServletRequest request, HttpServletResponse response, @RequestBody ManagerVO vo) {
		System.out.println("매니저 수정");
		System.out.println(vo);
		AccountVO account = (AccountVO)request.getAttribute("account");
		if(account.getAccount_type().equals("manager") && vo.getManager_id().equals(account.getId())) { // 로그인 된 계정이 매니저이고 수정하려는 아이디랑 같아야함
			accountService.updateManager(vo);
			response.setStatus(HttpStatus.OK.value());
		} else {
			System.out.println("인증실패");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
	}
	
	@PutMapping("/user")
	public void updateUser(HttpServletRequest request, HttpServletResponse response, @RequestBody UserVO vo) {
		System.out.println("누리미 수정");
		System.out.println(vo);
		AccountVO account = (AccountVO)request.getAttribute("account");
		
		if(account.getAccount_type().equals("manager") ||		// 관리자계정이거나 누리미 본인일 경우만
				(account.getAccount_type().equals("user") && account.getId().equals(vo.getUser_id()))) {
			accountService.updateUser(vo);
			response.setStatus(HttpStatus.OK.value());
		} else {
			System.out.println("인증실패");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
		
	}
	
	@PutMapping("/protector")
	public void updateProtector(HttpServletRequest request, HttpServletResponse response, @RequestBody ProtectorVO vo) {
		System.out.println("보호자 수정");
		System.out.println(vo);
		AccountVO account = (AccountVO)request.getAttribute("account");
		
		if(account.getAccount_type().equals("manager") ||		// 관리자계정이거나 보호자 본인일 경우만
				(account.getAccount_type().equals("protector") && account.getId().equals(vo.getProtector_id()))) {
			accountService.updateProtector(vo);
			response.setStatus(HttpStatus.OK.value());
		} else {
			System.out.println("인증실패");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
	}
	
	@PutMapping("/helper")
	public void updateHelper(HttpServletRequest request, HttpServletResponse response, @RequestBody HelperVO vo) {
		System.out.println("보드미 수정");
		System.out.println(vo);
		AccountVO account = (AccountVO)request.getAttribute("account");
		
		if(account.getAccount_type().equals("manager") ||		// 관리자계정이거나 보드미 본인일 경우만
				(account.getAccount_type().equals("helper") && account.getId().equals(vo.getHelper_id()))) {
			accountService.updateHelper(vo);
			response.setStatus(HttpStatus.OK.value());
		} else {
			System.out.println("인증실패");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
	}
	
	@PatchMapping("/password")
	public void updatePassword(HttpServletRequest request, HttpServletResponse response, @RequestBody AccountVO vo) {
		System.out.println("비밀번호 변경");
		AccountVO account = (AccountVO)request.getAttribute("account");
		
		if(account.getId().equals(vo.getId())) { // 로그인 된 본인이면
			vo.setPassword(BCrypt.hashpw(vo.getPassword(), BCrypt.gensalt()));
			accountService.updatePassword(vo);
			response.setStatus(HttpStatus.OK.value());
		} else {
			System.out.println("본인만 비번 바꾸기 가능");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
	}
	
	
	@DeleteMapping("/")
	public void deleteAccount(HttpServletRequest request, HttpServletResponse response, @RequestBody AccountVO vo) {
		System.out.println("계정삭제");
		AccountVO account = (AccountVO)request.getAttribute("account");
		
		if(account.getAccount_type().equals("manager") || // 관리자거나 아이디본인
				(account.getAccount_type().equals(vo.getAccount_type())&&account.getId().equals(vo.getId()))) { 
			try {
				switch (vo.getAccount_type()) {
					case "manager":
						System.out.println("매니저삭제");
						accountService.deleteManager(vo.getId());
						response.setStatus(HttpStatus.OK.value());
						break;
					case "user" :
						System.out.println("누리미삭제");
						accountService.deleteUser(vo.getId());
						response.setStatus(HttpStatus.OK.value());
						break;
					case "protector" :
						System.out.println("보호자삭제");
						accountService.deleteProtector(vo.getId());
						response.setStatus(HttpStatus.OK.value());
						break;
					case "helper" :
						System.out.println("보드미삭제");
						accountService.deleteHelper(vo.getId());
						response.setStatus(HttpStatus.OK.value());
						break;
					default:
						System.out.println("account_type 오류!");
						response.setStatus(HttpStatus.BAD_REQUEST.value());
						break;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("삭제할 수 없음!! 하위레코드가 남아있을 수 있음");
				response.setStatus(HttpStatus.FORBIDDEN.value());
			}
		} else {
			System.out.println("관리자거나 본인만 탈퇴 가능합니다");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
	}
	
	@GetMapping("manager/{id}")
	public ManagerVO getManager(@PathVariable("id") String manager_id) {
		System.out.println("매니저 얻기 : " + manager_id);
		return accountService.getManager(manager_id);
	}
	@GetMapping("user/{id}")
	public UserVO getUser(@PathVariable("id") String user_id) {
		System.out.println("누리미 얻기 : " + user_id);
		return accountService.getUser(user_id);
	}
	@GetMapping("protector/{id}")
	public ProtectorVO getProtector(@PathVariable("id") String protector_id) {
		System.out.println("보호자 얻기 : " + protector_id);
		return accountService.getProtector(protector_id);
	}
	@GetMapping("helper/{id}")
	public HelperVO getHelper(@PathVariable("id") String helper_id) {
		System.out.println("보드미 얻기 : " + helper_id);
		return accountService.getHelper(helper_id);
	}
	
	@GetMapping("list/manager")
	public List<ManagerVO> getMangerList(){
		System.out.println("관리자리스트요청");
		return accountService.getManagerList();
	}
	@GetMapping("list/user")
	public List<UserVO> getUserList(){
		System.out.println("누리미리스트요청");
		return accountService.getUserList();
	}
	@GetMapping("list/protector")
	public List<ProtectorVO> getProtectorList(){
		System.out.println("보호자리스트요청");
		return accountService.getProtectorList();
	}
	@GetMapping("list/helper")
	public List<HelperVO> getHelperList(){
		System.out.println("보드미리스트요청");
		return accountService.getHelperList();
	}
}
