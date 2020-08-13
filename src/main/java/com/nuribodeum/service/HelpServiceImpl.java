package com.nuribodeum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuribodeum.mapper.HelpMapper;
import com.nuribodeum.vo.HelpVO;

@Service("helpService")
public class HelpServiceImpl implements HelpService{
	
	@Autowired
	HelpMapper mapper;

	@Override
	public void insertHelp(HelpVO vo) {
		// TODO Auto-generated method stub
		mapper.insertHelp(vo);
	}

	@Override
	public HelpVO getHelp(int help_seq) {
		// TODO Auto-generated method stub
		return mapper.getHelp(help_seq);
	}

	@Override
	public List<HelpVO> getHelpList() {
		// TODO Auto-generated method stub
		return mapper.getHelpList();
	}

	@Override
	public List<HelpVO> getHelpersHelpList(String helper_id) {
		// TODO Auto-generated method stub
		return mapper.getHelpersHelpList(helper_id);
	}

	@Override
	public List<HelpVO> getUsersHelpList(String user_id) {
		// TODO Auto-generated method stub
		return mapper.getUsersHelpList(user_id);
	}

	@Override
	public List<HelpVO> getMonthHelpList(String strMonth) {
		// TODO Auto-generated method stub
		return mapper.getMonthHelpList(strMonth);
	}

	@Override
	public List<HelpVO> getDateHelpList(String strDate) {
		// TODO Auto-generated method stub
		return mapper.getDateHelpList(strDate);
	}

	@Override
	public void completeHelp(int help_seq) {
		// TODO Auto-generated method stub
		mapper.completeHelp(help_seq);
	}
	
}
