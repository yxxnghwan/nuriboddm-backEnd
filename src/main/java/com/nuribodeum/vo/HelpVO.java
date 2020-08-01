package com.nuribodeum.vo;

public class HelpVO {
	private int help_seq;
	private int emergency_seq;
	private String helper_id;
	public int getHelp_seq() {
		return help_seq;
	}
	public void setHelp_seq(int help_seq) {
		this.help_seq = help_seq;
	}
	public int getEmergency_seq() {
		return emergency_seq;
	}
	public void setEmergency_seq(int emergency_seq) {
		this.emergency_seq = emergency_seq;
	}
	public String getHelper_id() {
		return helper_id;
	}
	public void setHelper_id(String helper_id) {
		this.helper_id = helper_id;
	}
	@Override
	public String toString() {
		return "HelpVO [help_seq=" + help_seq + ", emergency_seq=" + emergency_seq + ", helper_id=" + helper_id + "]";
	}
	
	
}
