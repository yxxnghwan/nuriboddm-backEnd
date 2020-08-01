package com.nuribodeum.vo;

import lombok.Data;

@Data
public class UserVO {
	private String user_id;
	private String password;
	private String name;
	private int age;
	private String illness;
	private String phone;
	private String address;
	private String sex;
	private String manager_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	public String getIllness() {
		return illness;
	}
	public void setIllness(String illness) {
		this.illness = illness;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	@Override
	public String toString() {
		return "UserVO [user_id=" + user_id + ", password=" + password + ", name=" + name + ", age=" + age
				+ ", illness=" + illness + ", phone=" + phone + ", address=" + address + ", sex=" + sex
				+ ", manager_id=" + manager_id + "]";
	}
	
	
}
