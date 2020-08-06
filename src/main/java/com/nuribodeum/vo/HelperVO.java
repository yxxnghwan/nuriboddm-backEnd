package com.nuribodeum.vo;

import java.util.Date;

import lombok.Data;

public @Data class HelperVO {
	private String helper_id;
	private String password;
	private String name;
	private Date date_of_birth;
	private String organization;
	private String phone;
}
