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
	UserVO getUser(String user_id);
	ProtectorVO getProtector(String protector_id);
	HelperVO getHelper(String helper_id);
	
	// U
	void updateManager(ManagerVO vo);
	
	// D
	void deleteManager(String manager_id);
	void deleteUser(String user_id);
	void deleteProtector(String protector_id);
	void deleteHelper(String helper_id);
}
