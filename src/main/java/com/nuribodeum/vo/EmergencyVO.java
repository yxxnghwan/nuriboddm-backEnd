package com.nuribodeum.vo;

import java.util.Date;

import lombok.Data;

public @Data class EmergencyVO {
	private int emergency_seq;
	private Date occur_time;
	private double latitude;
	private double longitude;
	private double heart_rate;
	private String door_lock_key;
	private String user_id;
}
