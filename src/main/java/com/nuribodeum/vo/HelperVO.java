package com.nuribodeum.vo;

public class HelperVO {
	private String helper_id;
	private String password;
	private String name;
	private int age;
	private String organization;
	private String phone;
	public String getHelper_id() {
		return helper_id;
	}
	public void setHelper_id(String helper_id) {
		this.helper_id = helper_id;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "HelperVO [helper_id=" + helper_id + ", password=" + password + ", name=" + name + ", age=" + age
				+ ", organization=" + organization + ", phone=" + phone + "]";
	}
	
	
}
