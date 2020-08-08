package com.nuribodeum.vo;

import lombok.Data;

public @Data class HelpVO {
	private int help_seq;
	private int emergency_seq;
	private String helper_id;
	private boolean complete;
	private EmergencyVO emergency;
}
