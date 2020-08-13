package com.nuribodeum.config;

import java.util.List;

import lombok.Data;

public @Data class APIClass {
	private String uri;
	private String method;
	public APIClass(String uri, String method) {
		this.uri = uri;
		this.method = method;
	}
	
	public boolean isIn(List<APIClass> apiList) {
		boolean result = false;
		
		for(APIClass api : apiList) {
			if(api.getMethod().equals(this.method) && api.getUri().equals(this.uri)) {
				result = true;
				break;
			}
		}
		return result;
	}
}
