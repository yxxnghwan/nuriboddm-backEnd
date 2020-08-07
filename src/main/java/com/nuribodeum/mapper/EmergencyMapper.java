package com.nuribodeum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nuribodeum.vo.EmergencyVO;

@Mapper
public interface EmergencyMapper {
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
