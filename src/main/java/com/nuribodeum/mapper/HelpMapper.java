package com.nuribodeum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nuribodeum.vo.HelpVO;

@Mapper
public interface HelpMapper {
	//C
	void insertHelp(HelpVO vo);
	
	//R
	HelpVO getHelp(int help_seq);
	List<HelpVO> getHelpList();
	List<HelpVO> getHelpersHelpList(String helper_id);
	List<HelpVO> getUsersHelpList(String user_id);
	
	//U
	void completeHelp(int help_seq);
	
	
}
