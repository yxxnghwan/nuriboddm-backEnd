package com.nuribodeum.service;

import java.util.List;

import com.nuribodeum.vo.HelpVO;

public interface HelpService {
	//C
	void insertHelp(HelpVO vo);
	
	//R
	HelpVO getHelp(int help_seq);
	List<HelpVO> getHelpList();
	List<HelpVO> getHelpersHelpList(String helper_id);
	List<HelpVO> getUsersHelpList(String user_id);
	List<HelpVO> getMonthHelpList(String strMonth);
	List<HelpVO> getDateHelpList(String strDate);
	
	//U
	void completeHelp(int help_seq);
	
	
}
