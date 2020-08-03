package com.nuribodeum.vo;

import lombok.Data;


public @Data class UserVO {
	private String user_id;
	private String password;
	private String name;
	private int age;
	private String illness;
	private String phone;
	private String address;
	private String sex;
	private String manager_id;
}
