package com.nuribodeum.config;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

import com.nuribodeum.vo.AccountVO;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class LoginManagement {
	
	@Value("${jwtkey}")
	private static String JWTkey;
	
	private static String headerName = "Authorization";
	
	@Value("${jwtkey}")
	public void setJWTkey(String jWTkey) {
		JWTkey = jWTkey;
	}

	public LoginManagement() {
		// TODO Auto-generated constructor stub
		System.out.println("[Login Management Service] 생성 완료");
	}
	
	// JWT생성
	public static String createJWT(AccountVO vo) {
			
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("typ", "JWT");
		headers.put("alg", "HS256");
		Map<String, Object> payloads = new HashMap<String, Object>();
		Calendar calendar = Calendar.getInstance();
		Date now = new Date();
		calendar.setTimeInMillis(now.getTime());
		System.out.println("발급시간" + now);
		calendar.add(Calendar.YEAR, 1);
		now.setTime(calendar.getTimeInMillis());
		System.out.println("입력만료시간" + now);
		payloads.put("exp", now);
		payloads.put("account_type", vo.getAccount_type());
		payloads.put("id",vo.getId());
				
		String jwt = Jwts.builder()
							.setHeader(headers)
							.setClaims(payloads)
							.signWith(SignatureAlgorithm.HS256, LoginManagement.JWTkey.getBytes())
							.compact();
		return jwt;
	}
		
	// JWT 디코딩
	public static AccountVO getAccountFromJwtString(String jwtTokenString, HttpServletRequest request, HttpServletResponse response) {
		AccountVO account = null;
		
		try {
			Claims claims = Jwts.parser()
							.setSigningKey(LoginManagement.JWTkey.getBytes())
							.parseClaimsJws(jwtTokenString)
							.getBody();
		
			Date expiration = claims.get("exp", Date.class);
			expiration.setTime(expiration.getTime()/1000); // 이상하게 입력할때 밀리초를 초로 받음 그래서 나올땐 또 1000을 곱해서 나옴...
			System.out.println("받은만료시간 : " + expiration);
			Date now = new Date();
			account = new AccountVO();
			account.setId(claims.get("id", String.class));
			account.setAccount_type(claims.get("account_type", String.class));
			// 기간이 만료되면....?? null 을 리턴  ==> 쿠키삭제
			if(expiration.getTime() < now.getTime()) {
				System.out.println("기간이 만료된 토큰입니다");
				account =  null;
			}
			// 기간이 얼마 안남았을 때
			else if(expiration.getTime()-1000*60*60*24*90 < now.getTime()) {
				System.out.println("토큰기간이 얼마 안남아서 리프레쉬함");
				System.out.println("원래 jwt : " + jwtTokenString);
				String newJWT = LoginManagement.createJWT(account);
				System.out.println("바꾼 jwt : " + newJWT);
				// 다시 헤더에 넣어줌
				response.setHeader(LoginManagement.headerName, newJWT);
				account = LoginManagement.getAccountFromJwtString(newJWT, request, response);
				response.setStatus(HttpStatus.RESET_CONTENT.value());
			}
		} catch(JwtException e) {
			System.out.println("토큰변조위험!!");
			account = null;						// null값으로 넘겨줘서 다시 로그인하도록
		}
		// 기간 빵빵하면 그냥 계정 리턴
		return account;
	}
		
	// 로그인 체크
	public static AccountVO signInCheck(HttpServletRequest request, HttpServletResponse response) {
		AccountVO account = null;
		String readJWT = request.getHeader(headerName);
		if(readJWT != null) {
			// jwt 디코딩해서 정보 확인
			account = LoginManagement.getAccountFromJwtString(readJWT, request, response);
				
			// 만료 확인
			if(account == null) {
				System.out.println("기간만료된 토큰");
				response.setHeader(LoginManagement.headerName, "killed");
				response.setStatus(HttpStatus.NO_CONTENT.value());
			}
		}
		return account;
	}
	// JWT 발급
	public static void issueJWT(String jwt, HttpServletResponse response) {
		System.out.println("헤더에 토큰을 발급합니다.");
		response.setHeader(LoginManagement.headerName, jwt);
	}
}
