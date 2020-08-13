package com.nuribodeum.service;

import java.util.List;

import com.nuribodeum.vo.EmergencyVO;

public interface EmergencyService {
	// C
	void insertEmergency(EmergencyVO vo);
	
	//R
	EmergencyVO getEmergency(int emergency_seq);
	
	String whoseEmergency(int emergency_seq);
	
	List<EmergencyVO> getEmergencyList();
	List<EmergencyVO> getUsersEmergencyList(String user_id);
	
	//U
	void completeEmergency(int emergency_seq);
	void failEmergency(int emergency_seq);
}
