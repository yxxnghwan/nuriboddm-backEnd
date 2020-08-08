package com.nuribodeum.vo;

import java.util.Date;

import lombok.Data;

public @Data class HelpVO {
	private int help_seq;
	private int emergency_seq;
	private String helper_id;
	private boolean complete;
	private Date help_time;
	private Date complete_time;
	private EmergencyVO emergency;
}
