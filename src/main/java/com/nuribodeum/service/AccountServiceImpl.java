package com.nuribodeum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuribodeum.mapper.AccountMapper;
import com.nuribodeum.vo.AccountVO;
import com.nuribodeum.vo.HelperVO;
import com.nuribodeum.vo.ManagerVO;
import com.nuribodeum.vo.ProtectorVO;
import com.nuribodeum.vo.UserVO;

@Service("accountService")
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountMapper mapper;

	@Override
	public void insertManager(ManagerVO vo) {
		// TODO Auto-generated method stub
		mapper.insertManager(vo);
	}

	@Override
	public void insertUser(UserVO vo) {
		// TODO Auto-generated method stub
		mapper.insertUser(vo);
	}

	@Override
	public void insertProtector(ProtectorVO vo) {
		// TODO Auto-generated method stub
		mapper.insertProtector(vo);
	}

	@Override
	public void insertHelper(HelperVO vo) {
		// TODO Auto-generated method stub
		mapper.insertHelper(vo);
	}

	@Override
	public ManagerVO getManager(String manager_id) {
		// TODO Auto-generated method stub
		return mapper.getManager(manager_id);
	}

	@Override
	public UserVO getUser(String user_id) {
		// TODO Auto-generated method stub
		return mapper.getUser(user_id);
	}

	@Override
	public ProtectorVO getProtector(String protector_id) {
		// TODO Auto-generated method stub
		return mapper.getProtector(protector_id);
	}

	@Override
	public HelperVO getHelper(String helper_id) {
		// TODO Auto-generated method stub
		return mapper.getHelper(helper_id);
	}

	@Override
	public List<ManagerVO> getManagerList() {
		// TODO Auto-generated method stub
		return mapper.getManagerList();
	}

	@Override
	public List<UserVO> getUserList() {
		// TODO Auto-generated method stub
		return mapper.getUserList();
	}

	@Override
	public List<ProtectorVO> getProtectorList() {
		// TODO Auto-generated method stub
		return mapper.getProtectorList();
	}

	@Override
	public List<HelperVO> getHelperList() {
		// TODO Auto-generated method stub
		return mapper.getHelperList();
	}

	@Override
	public void updateManager(ManagerVO vo) {
		// TODO Auto-generated method stub
		mapper.updateManager(vo);
	}

	@Override
	public void updateUser(UserVO vo) {
		// TODO Auto-generated method stub
		mapper.updateUser(vo);
	}

	@Override
	public void updateProtector(ProtectorVO vo) {
		// TODO Auto-generated method stub
		mapper.updateProtector(vo);
	}

	@Override
	public void updateHelper(HelperVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePassword(AccountVO vo) {
		// TODO Auto-generated method stub
		mapper.updatePassword(vo);
	}

	@Override
	public void deleteManager(String manager_id) {
		// TODO Auto-generated method stub
		mapper.deleteManager(manager_id);
	}

	@Override
	public void deleteUser(String user_id) {
		// TODO Auto-generated method stub
		mapper.deleteUser(user_id);
	}

	@Override
	public void deleteProtector(String protector_id) {
		// TODO Auto-generated method stub
		mapper.deleteProtector(protector_id);
	}

	@Override
	public void deleteHelper(String helper_id) {
		// TODO Auto-generated method stub
		mapper.deleteHelper(helper_id);
	}

}
