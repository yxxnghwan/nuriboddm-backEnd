package com.nuribodeum.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nuribodeum.vo.EmergencyVO;

@Mapper
public interface EmergencyMapper {
	void insertEmergency(EmergencyVO vo);
	
	String whoseEmergency(int emergency_seq);
}
