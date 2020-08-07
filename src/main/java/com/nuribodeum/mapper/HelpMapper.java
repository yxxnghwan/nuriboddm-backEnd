package com.nuribodeum.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nuribodeum.vo.HelpVO;

@Mapper
public interface HelpMapper {
	void insertHelp(HelpVO vo);
}
