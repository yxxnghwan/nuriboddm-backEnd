package com.nuribodeum.vo;

import java.util.Date;

public class EmergencyVO {
	private int emergency_seq;
	private Date occur_time;
	private double latitude;
	private double longitude;
	private double heart_rate;
	private String user_id;
	public int getEmergency_seq() {
		return emergency_seq;
	}
	public void setEmergency_seq(int emergency_seq) {
		this.emergency_seq = emergency_seq;
	}
	public Date getOccur_time() {
		return occur_time;
	}
	public void setOccur_time(Date occur_time) {
		this.occur_time = occur_time;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getHeart_rate() {
		return heart_rate;
	}
	public void setHeart_rate(double heart_rate) {
		this.heart_rate = heart_rate;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "EmergencyVO [emergency_seq=" + emergency_seq + ", occur_time=" + occur_time + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", heart_rate=" + heart_rate + ", user_id=" + user_id + "]";
	}
	
	
}
