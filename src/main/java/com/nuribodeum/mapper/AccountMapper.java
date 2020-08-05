package com.nuribodeum.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nuribodeum.vo.HelperVO;
import com.nuribodeum.vo.ManagerVO;
import com.nuribodeum.vo.ProtectorVO;
import com.nuribodeum.vo.UserVO;

@Mapper
public interface AccountMapper {
	// C
	void insertManager(ManagerVO vo);
	void insertUser(UserVO vo);
	void insertProtector(ProtectorVO vo);
	void insertHelper(HelperVO vo);
	
	// R
	ManagerVO getManager(String manager_id);
	
	// U
	void updateManager(ManagerVO vo);
}
