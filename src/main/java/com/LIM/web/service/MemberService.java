package com.LIM.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.LIM.web.dao.MemberDAO;
import com.LIM.web.vo.MemberVO;

@Service
public class MemberService {

	@Autowired
	MemberDAO memberDAO;

	public void insertMember(MemberVO membervo) throws DataAccessException {
		memberDAO.insertMember(membervo);
	}

	public MemberVO loginById(MemberVO memberVO) throws DataAccessException {
		return memberDAO.loginById(memberVO);
	}

	public void deleteMember(String id) throws DataAccessException {
		memberDAO.deleteMember(id);
	}

	public void updateMember(MemberVO memberVO) throws DataAccessException {
		memberDAO.updateMember(memberVO);
	}

	public List<MemberVO> selectAllMemberList() throws DataAccessException {
		return memberDAO.selectAllMemberList();
	}

	public MemberVO getById(String id) {
		return memberDAO.getById(id);
	}

}
