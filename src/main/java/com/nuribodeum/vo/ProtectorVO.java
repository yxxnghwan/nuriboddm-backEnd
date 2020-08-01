package com.nuribodeum.vo;

import lombok.Data;

@Data
public class ProtectorVO {
	private String protector_id;
	private String password;
	private String name;
	private String phone;
	private String user_id;
	public String getProtector_id() {
		return protector_id;
	}
	public void setProtector_id(String protector_id) {
		this.protector_id = protector_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "ProtectorVO [protector_id=" + protector_id + ", password=" + password + ", name=" + name + ", phone="
				+ phone + ", user_id=" + user_id + "]";
	}
	
	
}
