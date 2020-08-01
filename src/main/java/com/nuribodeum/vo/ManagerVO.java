package com.nuribodeum.vo;

import lombok.Data;

@Data
public class ManagerVO {
	private String manager_id;
	private String password;
	private String name;
	private String organization;
	private String department;
	private String phone;
	
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
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
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "ManagerVO [manager_id=" + manager_id + ", password=" + password + ", name=" + name + ", organization="
				+ organization + ", department=" + department + ", phone=" + phone + "]";
	}
	
	
}
