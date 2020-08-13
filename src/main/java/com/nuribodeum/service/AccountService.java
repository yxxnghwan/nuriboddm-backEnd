package com.nuribodeum.service;

import java.util.List;

import com.nuribodeum.vo.AccountVO;
import com.nuribodeum.vo.HelperVO;
import com.nuribodeum.vo.ManagerVO;
import com.nuribodeum.vo.ProtectorVO;
import com.nuribodeum.vo.UserVO;

public interface AccountService {
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
		
	List<ManagerVO> getManagerList();
	List<UserVO> getUserList();
	List<ProtectorVO> getProtectorList();
	List<HelperVO> getHelperList();
		
	// U
	void updateManager(ManagerVO vo);
	void updateUser(UserVO vo);
	void updateProtector(ProtectorVO vo);
	void updateHelper(HelperVO vo);
	void updatePassword(AccountVO vo);
		
	// D
	void deleteManager(String manager_id);
	void deleteUser(String user_id);
	void deleteProtector(String protector_id);
	void deleteHelper(String helper_id);
}
