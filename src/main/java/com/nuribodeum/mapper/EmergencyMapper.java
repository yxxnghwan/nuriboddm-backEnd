package com.nuribodeum.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nuribodeum.vo.EmergencyVO;

@Mapper
public interface EmergencyMapper {
	void insertEmergency(EmergencyVO vo);
	
	EmergencyVO getEmergency(int emergency_seq);
	
	String whoseEmergency(int emergency_seq);
	
	void completeEmergency(int emergency_seq);
	void failEmergency(int emergency_seq);
}
