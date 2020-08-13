package com.nuribodeum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nuribodeum.mapper.EmergencyMapper;
import com.nuribodeum.vo.EmergencyVO;

public class EmergencyServiceImpl implements EmergencyService{

	@Autowired
	EmergencyMapper mapper;
	
	@Override
	public void insertEmergency(EmergencyVO vo) {
		// TODO Auto-generated method stub
		mapper.insertEmergency(vo);
	}

	@Override
	public EmergencyVO getEmergency(int emergency_seq) {
		// TODO Auto-generated method stub
		return mapper.getEmergency(emergency_seq);
	}

	@Override
	public String whoseEmergency(int emergency_seq) {
		// TODO Auto-generated method stub
		return mapper.whoseEmergency(emergency_seq);
	}

	@Override
	public List<EmergencyVO> getEmergencyList() {
		// TODO Auto-generated method stub
		return mapper.getEmergencyList();
	}

	@Override
	public List<EmergencyVO> getUsersEmergencyList(String user_id) {
		// TODO Auto-generated method stub
		return mapper.getUsersEmergencyList(user_id);
	}

	@Override
	public void completeEmergency(int emergency_seq) {
		// TODO Auto-generated method stub
		mapper.completeEmergency(emergency_seq);
	}

	@Override
	public void failEmergency(int emergency_seq) {
		// TODO Auto-generated method stub
		mapper.failEmergency(emergency_seq);
	}
	
}
