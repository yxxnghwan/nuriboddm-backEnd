package com.nuribodeum.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import com.nuribodeum.vo.AccountVO;

@Configuration
public class SignInFilter implements Filter{
	
	private List<APIClass> allowedToSignInAPIs = new ArrayList<APIClass>();
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("로그인 필터링 진행중...");
		allowedToSignInAPIs.add(new APIClass("/accounts/user", "POST"));
		allowedToSignInAPIs.add(new APIClass("/accounts/protector", "POST"));
		allowedToSignInAPIs.add(new APIClass("/accounts/manager", "PUT"));
		allowedToSignInAPIs.add(new APIClass("/accounts/user", "PUT"));
		allowedToSignInAPIs.add(new APIClass("/accounts/protector", "PUT"));
		allowedToSignInAPIs.add(new APIClass("/accounts/password", "PATCH"));
		allowedToSignInAPIs.add(new APIClass("/accounts", "DELETE"));
		allowedToSignInAPIs.add(new APIClass("/emergency", "POST"));
		allowedToSignInAPIs.add(new APIClass("/emergency/complete", "PATCH"));
		allowedToSignInAPIs.add(new APIClass("/emergency/failure", "PATCH"));
		allowedToSignInAPIs.add(new APIClass("/help", "POST"));
		allowedToSignInAPIs.add(new APIClass("/help/complete", "PATCH"));

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		APIClass api = new APIClass(request.getRequestURI(), request.getMethod());
		if(api.isIn(allowedToSignInAPIs)) {
			System.out.println("로그인이 필요한 요청입니다. 로그인 체크합니다.");
			AccountVO account = LoginManagement.signInCheck(request, response);
			if(account != null) {
				System.out.println("로그인상태입니다.");
				System.out.println("type : "+ account.getAccount_type());
				System.out.println("id : "+ account.getId());
				request.setAttribute("account", account);
				chain.doFilter(request, response);
			} else {
				System.out.println("로그인 하셨나요..?");
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
			}
		} else {
			System.out.println("비로그인에게도 허용된 요청입니다.");
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
